package com.parkly.backend.bizz;

import com.parkly.backend.mapper.LocationMapper;
import com.parkly.backend.mapper.ParkingSlotMapper;
import com.parkly.backend.mapper.PhotoMapper;
import com.parkly.backend.repo.LocationRepository;
import com.parkly.backend.repo.ParkingSlotRepository;
import com.parkly.backend.repo.PhotoRepository;
import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.rest.domain.PhotoRest;
import com.parkly.backend.utils.LogWriter;
import com.parkly.backend.utils.domain.FilterEnum;
import com.parkly.backend.utils.domain.LogTypeEnum;
import com.parkly.backend.utils.domain.SortEnum;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        final Optional<LocationDTO> locationOpt =
                locationRepository.findByLatitudeAndLongitude(parkingSlotRest.getLocationRest().getLatitude(),
                        parkingSlotRest.getLocationRest().getLongitude());

        if (locationOpt.isEmpty())
        {
            final Set<PhotoDTO> newPhotosSet = getNewPhotosForParkingSlot(parkingSlotRest);
            newPhotosSet.forEach(photoRepository::save);

            final LocationDTO newLocationDto = LocationMapper.mapToLocationDTO(parkingSlotRest.getLocationRest());
            locationRepository.save(Objects.requireNonNull(newLocationDto));

            final ParkingSlotDTO newParkingSlotDto =
                    ParkingSlotMapper.mapToParkingSlotDTO(newLocationDto, newPhotosSet, parkingSlotRest);
            parkingSlotRepository.save(Objects.requireNonNull(newParkingSlotDto));

            return parkingSlotRest;
        }

        LogWriter.logMessage("There already exists a parking spot for the given location", LogTypeEnum.ERROR);
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

    private Set<PhotoDTO> getNewPhotosForParkingSlot(final ParkingSlotRest parkingSlotRest)
    {
        if(Objects.nonNull(parkingSlotRest.getPhotoRestSet()))
        {
            return parkingSlotRest.getPhotoRestSet().stream()
                        .map(PhotoMapper::mapToPhotoDTO)
                        .filter(photoDTO -> Objects.nonNull(photoDTO) && Objects.isNull(photoRepository.findByPath(photoDTO.getPath())))
                        .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}
