package com.parkly.backend.mapper;

import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.rest.domain.LocationRest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocationMapper
{
    public static Optional<LocationDTO> mapToLocationDTO(final LocationRest locationRest)
    {
        if(Objects.nonNull(locationRest))
        {
            final LocationDTO locationDTO = new LocationDTO();
            locationDTO.setCountry(locationRest.getCountry());
            locationDTO.setCity(locationRest.getCity());
            locationDTO.setStreet(locationRest.getStreet());
            locationDTO.setStreetNumber(locationRest.getStreetNumber());
            locationDTO.setZipCode(locationRest.getZipCode());
            locationDTO.setLatitude(locationRest.getLatitude());
            locationDTO.setLongitude(locationRest.getLongitude());

            return Optional.of(locationDTO);
        }
        return Optional.empty();
    }

    public static Optional<LocationRest> mapToLocationRest(final LocationDTO locationDTO)
    {
        if(Objects.nonNull(locationDTO))
        {
            final LocationRest locationRest = new LocationRest();
            locationRest.setCountry(locationDTO.getCountry());
            locationRest.setCity(locationDTO.getCity());
            locationRest.setStreet(locationDTO.getStreet());
            locationRest.setStreetNumber(locationDTO.getStreetNumber());
            locationRest.setZipCode(locationDTO.getZipCode());
            locationRest.setLatitude(locationDTO.getLatitude());
            locationRest.setLongitude(locationDTO.getLongitude());

            return Optional.of(locationRest);
        }
        return Optional.empty();
    }
}
