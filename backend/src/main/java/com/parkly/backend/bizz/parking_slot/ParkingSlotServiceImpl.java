package com.parkly.backend.bizz.parking_slot;

import com.parkly.backend.mapper.LocationMapper;
import com.parkly.backend.mapper.ParkingSlotMapper;
import com.parkly.backend.mapper.PhotoMapper;
import com.parkly.backend.repo.LocationRepository;
import com.parkly.backend.repo.ParkingSlotRepository;
import com.parkly.backend.repo.PhotoRepository;
import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.rest.domain.LocationRest;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.rest.domain.PhotoRest;
import com.parkly.backend.utils.LogWriter;
import com.parkly.backend.utils.domain.FilterEnum;
import com.parkly.backend.utils.domain.LogTypeEnum;
import com.parkly.backend.utils.domain.SortEnum;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {


    private static final int PAGE_MAX = 10;

    private final ParkingSlotRepository parkingSlotRepository;
    private final LocationRepository locationRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public ParkingSlotServiceImpl(final ParkingSlotRepository parkingSlotRepository,
                                  final LocationRepository locationRepository,
                                  final PhotoRepository photoRepository)
    {
        this.parkingSlotRepository = parkingSlotRepository;
        this.locationRepository = locationRepository;
        this.photoRepository = photoRepository;
    }


    public Set<ParkingSlotRest> getAllParkingSlots(final FilterEnum filter,
                                                   final Integer page,
                                                   final SortEnum sort)
    {
        final Iterable<ParkingSlotDTO>  parkingSlots = parkingSlotRepository.findAll();

        final Predicate<ParkingSlotDTO> filterParkingSlots =
                (parkingSlot -> (filter.equals(FilterEnum.ALL)) || (parkingSlot.getIsActive() == filter.getValue() && Objects.nonNull(parkingSlot.getLocation())));

        final Comparator<ParkingSlotRest> sortParkingSlots = Comparator.comparing(ParkingSlotRest::getName,
                (parking1, parking2) ->  parking1.compareTo(parking2) * sort.getValue());

        return StreamSupport.stream(parkingSlots.spliterator(), false)
                .filter(filterParkingSlots)
                .map(ps -> ParkingSlotMapper.mapToParkingSlotRest(ps).get())
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
                        addPhotosToDatabase(parkingSlotRest.getPhotoRestSet(),parkingSlotDTO);
                        return parkingSlotRest;});
        }
        LogWriter.logMessage(String.format("Parking slot %s is already in the database", parkingSlotRest.getName()),LogTypeEnum.WARNING);
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
            parkingSlotDTO.setStartDate(parkingSlotRest.getStartDate());
            parkingSlotDTO.setEndDate(parkingSlotRest.getEndDate());
            widthOpt.ifPresent(parkingSlotDTO::setWidth);
            heightOpt.ifPresent(parkingSlotDTO::setHeight);
            descOpt.ifPresent(parkingSlotDTO::setDescription);
            parkingSlotRepository.save(parkingSlotDTO);

            return ParkingSlotMapper.mapToParkingSlotRest(parkingSlotDTO);

        }
        LogWriter.logMessage(String.format("Parking slot %s is already in the database",parkingSlotRest.getName()),LogTypeEnum.WARNING);
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
            LogWriter.logMessage(
                    String.format("Couldn't delete parking slot with id %d from database - parking slotnot found", parkingSlotId),
                    LogTypeEnum.WARNING);
            return false;
        });
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
                            LogWriter.logMessage("Photo couldn't be added - it already exists in the database", LogTypeEnum.WARNING);
                        }
                    });
        }
    }

    private LocationDTO addLocationToDatabase(final LocationRest locationRest)
    {
        return locationRepository.findByLatitudeAndLongitude(locationRest.getLatitude(),
                        locationRest.getLongitude())
                            .orElseGet(() -> {
                                final LocationDTO locationDTO =
                                        LocationMapper.mapToLocationDTO(locationRest).orElseGet(() ->{
                                            LogWriter.logMessage("Program encountered error while retrieving location", LogTypeEnum.ERROR);
                                            return null;});
                                if(Objects.nonNull(locationDTO))
                                {
                                    locationRepository.save(locationDTO);
                                }
                                return locationDTO;
                            });
    }
}