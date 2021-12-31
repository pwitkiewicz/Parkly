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
            locationDTO.setCountry(locationRest.getCountry());
            locationDTO.setCity(locationRest.getCity());
            locationDTO.setStreet(locationRest.getStreet());
            locationDTO.setStreetNumber(locationRest.getStreetNumber());
            locationDTO.setZipCode(locationRest.getZipCode());
            locationDTO.setLatitude(locationRest.getLatitude());
            locationDTO.setLongitude(locationRest.getLongitude());

            return locationDTO;
        }
        return null;
    }
}
