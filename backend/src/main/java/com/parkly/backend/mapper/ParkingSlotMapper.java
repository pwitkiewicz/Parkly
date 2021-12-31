package com.parkly.backend.mapper;

import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.ParkingSlotRest;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class ParkingSlotMapper {

    public static ParkingSlotDTO mapToParkingSlotDTO(final LocationDTO locationDTO,
                                                     final Set<PhotoDTO> photoDTOSet,
                                                     final ParkingSlotRest parkingSlotRest)
    {
        if(Objects.nonNull(locationDTO) && Objects.nonNull(parkingSlotRest) && Objects.nonNull(photoDTOSet))
        {
            final Optional<Double> widthOpt = Optional.of(parkingSlotRest.getWidth());
            final Optional<Double> heightOpt = Optional.of(parkingSlotRest.getHeight());
            final Optional<String> descOpt = Optional.ofNullable(parkingSlotRest.getDescription());

            final ParkingSlotDTO parkingSlotDTO = new ParkingSlotDTO();
            parkingSlotDTO.setName(parkingSlotRest.getName());
            parkingSlotDTO.setCost(parkingSlotRest.getCost());
            parkingSlotDTO.setEndDate(parkingSlotRest.getEndDate());
            parkingSlotDTO.setStartDate(parkingSlotRest.getStartDate());
            parkingSlotDTO.setLocation(locationDTO);
            parkingSlotDTO.setPhotoSet(photoDTOSet);
            parkingSlotDTO.setIsActive(parkingSlotRest.isActive()? 1 : 0);
            parkingSlotDTO.setIsDisabled(parkingSlotRest.isDisabledFriendly()? 1 : 0);
            widthOpt.ifPresent(parkingSlotDTO::setWidth);
            heightOpt.ifPresent(parkingSlotDTO::setHeight);
            descOpt.ifPresent(parkingSlotDTO::setDescription);

            return parkingSlotDTO;
        }
        return null;
    }
}
