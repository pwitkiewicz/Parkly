package com.parkly.backend.bizz.parking_slot;

import com.parkly.backend.bizz.booking.BookingService;
import com.parkly.backend.mapper.LocationMapper;
import com.parkly.backend.mapper.ParkingSlotMapper;
import com.parkly.backend.mapper.PhotoMapper;
import com.parkly.backend.repo.LocationRepository;
import com.parkly.backend.repo.ParkingSlotRepository;
import com.parkly.backend.repo.PhotoRepository;
import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.rest.domain.BookingRest;
import com.parkly.backend.rest.domain.LocationRest;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.rest.domain.PhotoRest;
import com.parkly.backend.utils.TimeUtils;
import com.parkly.backend.utils.domain.FilterEnum;
import com.parkly.backend.utils.domain.SortEnum;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Slf4j
@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {


    private static final int PAGE_MAX = 10;

    private final ParkingSlotRepository parkingSlotRepository;
    private final LocationRepository locationRepository;
    private final PhotoRepository photoRepository;
    private final BookingService bookingService;

    @Autowired
    public ParkingSlotServiceImpl(final ParkingSlotRepository parkingSlotRepository,
                                  final LocationRepository locationRepository,
                                  final PhotoRepository photoRepository,
                                  final BookingService bookingService)
    {
        this.parkingSlotRepository = parkingSlotRepository;
        this.locationRepository = locationRepository;
        this.photoRepository = photoRepository;
        this.bookingService = bookingService;
    }

    @Override
    public Long getPageNumber(final FilterEnum filter, final String location)
    {
        final Predicate<ParkingSlotDTO> filterParkingSlotsByActive =
                (parkingSlot -> (filter.equals(FilterEnum.ALL)) || (parkingSlot.getIsActive() == filter.getValue() && Objects.nonNull(parkingSlot.getLocation())));
        final Predicate<ParkingSlotDTO> filterParkingSlotsByLocation =
                (parkingSlot -> (location.equals("all")) || (parkingSlot.getLocation().getCity().equals(location)));

        return Math.round(Math.ceil((double) StreamSupport.stream(parkingSlotRepository.findAll().spliterator(),false)
                                            .filter(filterParkingSlotsByActive.and(filterParkingSlotsByLocation))
                                            .count() / 10));
    }

    @Override
    public Set<ParkingSlotRest> getAllParkingSlots(final FilterEnum filter,
                                                   final Integer page,
                                                   final SortEnum sort,
                                                   final String location,
                                                   @Nullable String startDate,
                                                   @Nullable String endDate)
    {
        final Iterable<ParkingSlotDTO>  parkingSlots = parkingSlotRepository.findAll();

        final Predicate<ParkingSlotDTO> filterParkingSlotsByActive =
                (parkingSlot -> (filter.equals(FilterEnum.ALL)) || (parkingSlot.getIsActive() == filter.getValue() && Objects.nonNull(parkingSlot.getLocation())));
        final Predicate<ParkingSlotDTO> filterParkingSlotsByLocation =
                (parkingSlot -> (location.equals("all")) || (parkingSlot.getLocation().getCity().toLowerCase().contains(location.toLowerCase())));
        final Predicate<ParkingSlotDTO> filterParkingSlotsByDates =
                (parkingSlot -> filterByDates(TimeUtils.stringToUnixTimestamp(startDate), TimeUtils.stringToUnixTimestamp(endDate), parkingSlot));

        final Comparator<ParkingSlotRest> sortParkingSlots = Comparator.comparing(ParkingSlotRest::getLocationRest,
                (parking1, parking2) ->  parking1.getCity().compareTo(parking2.getCity()) * sort.getValue());

        return StreamSupport.stream(parkingSlots.spliterator(), false)
                .filter(filterParkingSlotsByActive.and(filterParkingSlotsByDates).and(filterParkingSlotsByLocation))
                .map(ParkingSlotMapper::mapToParkingSlotRest)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted(sortParkingSlots)
                .skip((long) page * PAGE_MAX)
                .limit(PAGE_MAX)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Optional<ParkingSlotRest> addParkingSlot(final ParkingSlotRest parkingSlotRest)
    {
        Predicate<ParkingSlotRest> isInDatabase = parkingSlot -> parkingSlotRepository.findByName(parkingSlotRest.getName()).isPresent();

        if(!isInDatabase.test(parkingSlotRest))
        {
            final LocationDTO locationDTO = addLocationToDatabase(parkingSlotRest.getLocationRest());
            final Optional<ParkingSlotDTO> newParkingSlotDtoOpt =
                    ParkingSlotMapper.mapToParkingSlotDTO(parkingSlotRest, locationDTO);

            return newParkingSlotDtoOpt
                    .map(parkingSlotDTO -> {
                        parkingSlotRepository.save(parkingSlotDTO);
                        parkingSlotRest.setParkingSlotId(parkingSlotDTO.getParkingSlotId());
                        parkingSlotRest.getLocationRest().setLocationId(locationDTO.getLocationId());
                        addPhotosToDatabase(parkingSlotRest.getPhotoRestSet(),parkingSlotDTO);
                        return parkingSlotRest;});
        }
        log.warn(String.format("Parking slot %s is already in the database", parkingSlotRest.getName()));
        return Optional.empty();
    }

    @Override
    public Optional<ParkingSlotRest> getParkingSlotById(final Long parkingSlotId)
    {
        return (parkingSlotRepository.findById(parkingSlotId)).flatMap(ParkingSlotMapper::mapToParkingSlotRest);
    }

    @Override
    public Optional<ParkingSlotRest> updateParkingSlot(final Long parkingSlotId, final ParkingSlotRest parkingSlotRest)
    {
        final Optional<ParkingSlotDTO> parkingSlotDTOOpt = parkingSlotRepository.findById(parkingSlotId);

        if (parkingSlotDTOOpt.isPresent())
        {
            final Optional<Double> widthOpt = Optional.ofNullable(parkingSlotRest.getWidth());
            final Optional<Double> heightOpt = Optional.ofNullable(parkingSlotRest.getHeight());
            final Optional<String> descOpt = Optional.ofNullable(parkingSlotRest.getDescription());

            final LocationDTO locationDTO = parkingSlotDTOOpt.get().getLocation();
            locationDTO.setStreet(parkingSlotRest.getLocationRest().getStreet());
            locationDTO.setStreetNumber(parkingSlotRest.getLocationRest().getStreetNumber());
            locationDTO.setCity(parkingSlotRest.getLocationRest().getCity());
            locationDTO.setCountry(parkingSlotRest.getLocationRest().getCountry());
            locationDTO.setZipCode(parkingSlotRest.getLocationRest().getZipCode());
            locationRepository.save(locationDTO);

            final ParkingSlotDTO parkingSlotDTO = parkingSlotDTOOpt.get();
            parkingSlotDTO.setName(parkingSlotRest.getName());
            parkingSlotDTO.setIsActive(Boolean.TRUE.equals(parkingSlotRest.getIsActive())? 1 : 0);
            parkingSlotDTO.setIsDisabled(Boolean.TRUE.equals(parkingSlotRest.getIsDisabledFriendly())? 1 : 0);
            parkingSlotDTO.setCost(parkingSlotRest.getCost());
            parkingSlotDTO.setStartDate(TimeUtils.stringToUnixTimestamp(parkingSlotRest.getStartDate()));
            parkingSlotDTO.setEndDate(TimeUtils.stringToUnixTimestamp(parkingSlotRest.getEndDate()));
            widthOpt.ifPresent(parkingSlotDTO::setWidth);
            heightOpt.ifPresent(parkingSlotDTO::setHeight);
            descOpt.ifPresent(parkingSlotDTO::setDescription);
            parkingSlotRepository.save(parkingSlotDTO);

            return ParkingSlotMapper.mapToParkingSlotRest(parkingSlotDTO);

        }
        log.warn(String.format("Parking slot %s is already in the database",parkingSlotRest.getName()));
        return Optional.empty();
    }

    @Override
    public boolean deleteParkingSlot(final Long parkingSlotId)
    {
        final Optional<ParkingSlotDTO> parkingSlotDTOOpt = parkingSlotRepository.findById(parkingSlotId);

        return parkingSlotDTOOpt.map(parkingSlotDTO -> {
            parkingSlotRepository.delete(parkingSlotDTO);
            return true;
        }).orElseGet(() -> {
            log.warn(String.format("Couldn't delete parking slot with id %d from database - parking slot not found", parkingSlotId));
            return false;
        });
    }

    @Override
    public Optional<ParkingSlotRest> bookParkingSlot(final Long parkingSlotId, final BookingRest bookingRest)
    {
        final Optional<ParkingSlotDTO> parkingSlotOptional = parkingSlotRepository.findById(parkingSlotId);

        if(parkingSlotOptional.isPresent() && parkingSlotOptional.get().getIsActive() == 1)
        {
            bookingRest.setIsActive(true);

            if(bookingService.addBooking(bookingRest).isPresent())
            {
                return ParkingSlotMapper.mapToParkingSlotRest(parkingSlotOptional.get());
            }

            log.warn("An error encountered while booking parking slot with id {}",parkingSlotId);
            return Optional.empty();
        }
        log.warn("Parking slot " + ((parkingSlotOptional.isPresent())? "not found" : "not active"));
        return Optional.empty();
    }

    private void addPhotosToDatabase(final Set<PhotoRest> photoRestSet, final ParkingSlotDTO parkingSlotDTO)
    {
        if(Objects.nonNull(photoRestSet))
        {
            photoRestSet.stream()
                    .map(photoRest -> PhotoMapper.mapToPhotoDTO(photoRest, parkingSlotDTO))
                    .filter(Optional::isPresent)
                    .forEach(photoDTO -> {
                        try {
                            photoRepository.save(photoDTO.get());
                        } catch (final ConstraintViolationException e) {
                            log.warn("Photo couldn't be added - it already exists in the database");
                        }
                    });
        }
    }

    private LocationDTO addLocationToDatabase(final LocationRest locationRest)
    {
        return locationRepository.findByZipCodeAndStreetAndStreetNumber(locationRest.getZipCode(), locationRest.getStreet(), locationRest.getStreetNumber())
                .orElseGet(() ->
                {
                    final LocationDTO locationDTO =
                            LocationMapper.mapToLocationDTO(locationRest).orElseGet(() ->{
                                log.error("Program encountered error while retrieving location");
                                return null;});
                    if(Objects.nonNull(locationDTO))
                    {
                        locationRepository.save(locationDTO);
                    }
                    return locationDTO;
                });
    }

    private boolean filterByDates(final Long startDate, final Long endDate, final ParkingSlotDTO parkingSlot)
    {
        if(Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
            final Set<BookingRest> bookingHistoryForParking =
                    bookingService.getAllBookings(parkingSlot.getParkingSlotId(), null).stream()
                            .filter(bookingRest ->
                                    (TimeUtils.stringToUnixTimestamp(bookingRest.getStartDate()) >= startDate && TimeUtils.stringToUnixTimestamp(bookingRest.getStartDate()) <= endDate) ||
                                    (TimeUtils.stringToUnixTimestamp(bookingRest.getEndDate()) <= endDate && TimeUtils.stringToUnixTimestamp(bookingRest.getEndDate()) >= startDate))
                            .collect(Collectors.toSet());
            return bookingHistoryForParking.isEmpty();
        }
        return true;
    }
}
