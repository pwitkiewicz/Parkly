package com.parkly.backend.mappers;

import com.parkly.backend.mapper.LocationMapper;
import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.rest.domain.LocationRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class LocationMapperTest
{

    private LocationRest mockLocationRest;
    private LocationDTO mockLocationDTO;

    @Before
    public void setUp()
    {
        setUpLocationRest();
        setUpLocationDTO();
    }

    @Test
    public void mapToLocationDTONonNullRest()
    {
        final Optional<LocationDTO> retrievedLocation = LocationMapper.mapToLocationDTO(mockLocationRest);

        Assert.assertTrue(retrievedLocation.isPresent());
        Assert.assertEquals("Test City 1", retrievedLocation.get().getCity());
        Assert.assertEquals("10-000", retrievedLocation.get().getZipCode());
    }

    @Test
    public void mapToLocationDTONullRest()
    {
        final Optional<LocationDTO> retrievedLocation = LocationMapper.mapToLocationDTO(null);
        Assert.assertFalse(retrievedLocation.isPresent());
    }

    @Test
    public void mapToLocationRestNotNullDTO()
    {
        final Optional<LocationRest> retrievedLocation = LocationMapper.mapToLocationRest(mockLocationDTO);

        Assert.assertTrue(retrievedLocation.isPresent());
        Assert.assertEquals("Test City 2", retrievedLocation.get().getCity());
        Assert.assertEquals("20-000", retrievedLocation.get().getZipCode());
    }

    @Test
    public void mapToLocationRestNullDTO()
    {
        final Optional<LocationRest> retrievedLocation = LocationMapper.mapToLocationRest(null);

        Assert.assertFalse(retrievedLocation.isPresent());
    }

    private void setUpLocationRest()
    {
        mockLocationRest = new LocationRest();
        mockLocationRest.setCity("Test City 1");
        mockLocationRest.setCountry("Test Country 1");
        mockLocationRest.setStreet("Test Street 1");
        mockLocationRest.setStreetNumber("1T");
        mockLocationRest.setZipCode("10-000");
    }

    private void setUpLocationDTO()
    {
        mockLocationDTO = new LocationDTO();
        mockLocationDTO.setCity("Test City 2");
        mockLocationDTO.setCountry("Test Country 2");
        mockLocationDTO.setStreet("Test Street 2");
        mockLocationDTO.setStreetNumber("2T");
        mockLocationDTO.setZipCode("20-000");
    }
}
