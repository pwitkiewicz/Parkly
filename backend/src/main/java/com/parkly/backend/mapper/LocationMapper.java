package com.parkly.backend.mapper;

import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.rest.domain.LocationRest;

import java.util.Objects;

public class LocationMapper
{
    public static LocationDTO mapToLocationDTO(final LocationRest locationRest)
    {
        if(Objects.nonNull(locationRest))
        {
            final LocationDTO locationDTO = new LocationDTO();
            locationDTO.setCountry(locationDTO.getCountry());
            locationDTO.setCity(locationDTO.getCity());
            locationDTO.setStreet(locationDTO.getStreet());
            locationDTO.setStreetNumber(locationDTO.getStreetNumber());
            locationDTO.setZipCode(locationDTO.getZipCode());
            locationDTO.setLatitude(locationDTO.getLatitude());
            locationDTO.setLongitude(locationDTO.getLongitude());

            return locationDTO;
        }
        return null;
    }
}
