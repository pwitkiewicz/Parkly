package com.parkly.backend.mapper;

import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.rest.domain.ParkingSlotRest;

import java.util.Objects;
import java.util.Optional;

public class ParkingSlotMapper {

    public static Optional<ParkingSlotDTO> mapToParkingSlotDTO(final ParkingSlotRest parkingSlotRest,
                                                               final LocationDTO locationDTO)
    {
        if(Objects.nonNull(parkingSlotRest) && Objects.nonNull(locationDTO))
        {
            final Optional<Double> widthOpt = Optional.of(parkingSlotRest.getWidth());
            final Optional<Double> heightOpt = Optional.of(parkingSlotRest.getHeight());
            final Optional<String> descOpt = Optional.ofNullable(parkingSlotRest.getDescription());

            final ParkingSlotDTO parkingSlotDTO = new ParkingSlotDTO();
            parkingSlotDTO.setName(parkingSlotRest.getName());
            parkingSlotDTO.setCost(parkingSlotRest.getCost());
            parkingSlotDTO.setEndDate(parkingSlotRest.getEndDate());
            parkingSlotDTO.setStartDate(parkingSlotRest.getStartDate());
            parkingSlotDTO.setIsActive(parkingSlotRest.isActive()? 1 : 0);
            parkingSlotDTO.setIsDisabled(parkingSlotRest.isDisabledFriendly()? 1 : 0);
            widthOpt.ifPresent(parkingSlotDTO::setWidth);
            heightOpt.ifPresent(parkingSlotDTO::setHeight);
            descOpt.ifPresent(parkingSlotDTO::setDescription);
            parkingSlotDTO.setLocation(locationDTO);

            return Optional.of(parkingSlotDTO);
        }
        return Optional.empty();
    }
}
