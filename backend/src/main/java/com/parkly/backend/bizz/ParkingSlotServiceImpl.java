package com.parkly.backend.bizz;

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
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {

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

    @Override
    public Set<ParkingSlotRest> getAllParkingSlots(FilterEnum filter, Integer page, SortEnum sort) {
        return null;
    }

    @Override
    public ParkingSlotRest addParkingSlot(final ParkingSlotRest parkingSlotRest)
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
                        return parkingSlotRest;})
                    .orElseGet(() -> {
                        LogWriter.logMessage("Program encountered error while saving parking slot", LogTypeEnum.ERROR);
                        return ParkingSlotRest.EMPTY_SLOT;
                    });
        }
        LogWriter.logMessage(String.format("Parking slot %s is already in the database", parkingSlotRest.getName()),LogTypeEnum.WARNING);
        return ParkingSlotRest.EMPTY_SLOT;
    }

    @Override
    public ParkingSlotRest getParkingSlotById(final Long parkingSlotId) {
        return null;
    }

    @Override
    public ParkingSlotRest updateParkingSlot(final Long parkingSlotId, final ParkingSlotRest parkingSlotRest) {
        return null;
    }

    @Override
    public boolean deleteParkingSlot(final Long parkingSlotId) {
        return false;
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
        return locationRepository.findByLatitudeAndLongitude(locationRest.getLatitude(),locationRest.getLongitude())
                .orElseGet(() -> LocationMapper.mapToLocationDTO(locationRest).orElseGet(() ->{
                    LogWriter.logMessage("Program encountered error while retrieving location", LogTypeEnum.ERROR);
                    return null;
                }));
    }
}
