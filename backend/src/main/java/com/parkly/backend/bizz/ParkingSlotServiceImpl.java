package com.parkly.backend.bizz;

import com.parkly.backend.mapper.LocationMapper;
import com.parkly.backend.mapper.ParkingSlotMapper;
import com.parkly.backend.mapper.PhotoMapper;
import com.parkly.backend.repo.ParkingSlotRepository;
import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.rest.domain.PhotoRest;
import com.parkly.backend.utils.LogWriter;
import com.parkly.backend.utils.domain.FilterEnum;
import com.parkly.backend.utils.domain.LogTypeEnum;
import com.parkly.backend.utils.domain.SortEnum;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;

    @Autowired
    public ParkingSlotServiceImpl(final ParkingSlotRepository parkingSlotRepository)
    {
        this.parkingSlotRepository = parkingSlotRepository;
    }

    @Override
    public Set<ParkingSlotRest> getAllParkingSlots(FilterEnum filter, Integer page, SortEnum sort) {
        return null;
    }

    @Override
    public ParkingSlotRest addParkingSlot(final ParkingSlotRest parkingSlotRest)
    {
        final Set<PhotoDTO> photoDTOSet = getPhotoSetFromPhotoRestSet(parkingSlotRest.getPhotoRestSet());
        final Optional<LocationDTO> locationDTOOpt = LocationMapper.mapToLocationDTO(parkingSlotRest.getLocationRest());

        final LocationDTO locationDTO = locationDTOOpt.orElseGet(() -> {
            LogWriter.logMessage("Location couldn't be saved in the database", LogTypeEnum.ERROR);
            return null;
        });

        final Optional<ParkingSlotDTO> newParkingSlotDto =
                    ParkingSlotMapper.mapToParkingSlotDTO(parkingSlotRest, locationDTO, photoDTOSet);
        newParkingSlotDto.ifPresentOrElse(parkingSlotRepository::save, () ->
                LogWriter.logMessage("Parking slot couldn't be saved in the database", LogTypeEnum.ERROR)
        );

        return newParkingSlotDto.isPresent()? parkingSlotRest : ParkingSlotRest.EMPTY_SLOT;
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

    private Set<PhotoDTO> getPhotoSetFromPhotoRestSet(final Set<PhotoRest> photoRestSet)
    {
        return photoRestSet.stream()
                .map(PhotoMapper::mapToPhotoDTO)
                .filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toSet());
    }
}
