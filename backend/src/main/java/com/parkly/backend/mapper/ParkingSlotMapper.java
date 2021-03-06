package com.parkly.backend.mapper;

import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.LocationRest;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.utils.TimeUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParkingSlotMapper {

    public static Optional<ParkingSlotDTO> mapToParkingSlotDTO(final ParkingSlotRest parkingSlotRest,
                                                               final LocationDTO locationDTO)
    {
        if(Objects.nonNull(parkingSlotRest) && Objects.nonNull(locationDTO))
        {
            final Optional<Double> widthOpt = Optional.ofNullable(parkingSlotRest.getWidth());
            final Optional<Double> heightOpt = Optional.ofNullable(parkingSlotRest.getHeight());
            final Optional<String> descOpt = Optional.ofNullable(parkingSlotRest.getDescription());

            final ParkingSlotDTO parkingSlotDTO = new ParkingSlotDTO();
            parkingSlotDTO.setName(parkingSlotRest.getName());
            parkingSlotDTO.setCost(parkingSlotRest.getCost());
            parkingSlotDTO.setEndDate(TimeUtils.stringToUnixTimestamp(parkingSlotRest.getEndDate()));
            parkingSlotDTO.setStartDate(TimeUtils.stringToUnixTimestamp(parkingSlotRest.getStartDate()));
            parkingSlotDTO.setIsActive(Boolean.TRUE.equals(parkingSlotRest.getIsActive())? 1 : 0);
            parkingSlotDTO.setIsDisabled(Boolean.TRUE.equals(parkingSlotRest.getIsDisabledFriendly())? 1 : 0);
            widthOpt.ifPresent(parkingSlotDTO::setWidth);
            heightOpt.ifPresent(parkingSlotDTO::setHeight);
            descOpt.ifPresent(parkingSlotDTO::setDescription);
            parkingSlotDTO.setLocation(locationDTO);

            return Optional.of(parkingSlotDTO);
        }
        return Optional.empty();
    }

    public static Optional<ParkingSlotRest> mapToParkingSlotRest(final ParkingSlotDTO parkingSlotDTO)
    {
        if(Objects.nonNull(parkingSlotDTO))
        {
            final Optional<LocationRest> locationRest = LocationMapper.mapToLocationRest(parkingSlotDTO.getLocation());
            final Optional<Double> widthOpt = Optional.ofNullable(parkingSlotDTO.getWidth());
            final Optional<Double> heightOpt = Optional.ofNullable(parkingSlotDTO.getHeight());
            final Optional<String> descOpt = Optional.ofNullable(parkingSlotDTO.getDescription());

            final ParkingSlotRest parkingSlotRest = new ParkingSlotRest();
            parkingSlotRest.setParkingSlotId(parkingSlotDTO.getParkingSlotId());
            parkingSlotRest.setName(parkingSlotDTO.getName());
            parkingSlotRest.setCost(parkingSlotDTO.getCost());
            parkingSlotRest.setEndDate(TimeUtils.unixTimestampToString(parkingSlotDTO.getEndDate()));
            parkingSlotRest.setStartDate(TimeUtils.unixTimestampToString(parkingSlotDTO.getStartDate()));
            parkingSlotRest.setIsActive(parkingSlotDTO.getIsActive() == 1);
            parkingSlotRest.setIsDisabledFriendly(parkingSlotDTO.getIsDisabled() == 1);
            widthOpt.ifPresent(parkingSlotRest::setWidth);
            heightOpt.ifPresent(parkingSlotRest::setHeight);
            descOpt.ifPresent(parkingSlotRest::setDescription);
            if(Objects.nonNull(parkingSlotDTO.getPhotoSet())) {
                parkingSlotRest.setPhotoRestSet(parkingSlotDTO.getPhotoSet().stream()
                        .map(PhotoMapper::mapToPhotoRest)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toSet()));
            }

            if(locationRest.isPresent()) {
                parkingSlotRest.setLocationRest(locationRest.get());
            }
            else {
                return Optional.empty();
            }

            return Optional.of(parkingSlotRest);
        }
        return Optional.empty();
    }
}
