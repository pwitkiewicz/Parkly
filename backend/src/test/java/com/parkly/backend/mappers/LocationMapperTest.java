package com.parkly.backend.mappers;

import com.parkly.backend.common.CommonMockObjectsMappers;
import com.parkly.backend.mapper.LocationMapper;
import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.rest.domain.LocationRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class LocationMapperTest extends CommonMockObjectsMappers
{

    private LocationRest mockLocationRest;
    private LocationDTO mockLocationDTO;

    @Before
    public void setUp()
    {
        mockLocationRest = setUpLocationRest();
        mockLocationDTO = setUpLocationDTO();
    }

    @Test
    public void mapToLocationDTONonNullRest()
    {
        final Optional<LocationDTO> retrievedLocation = LocationMapper.mapToLocationDTO(mockLocationRest);

        Assert.assertTrue(retrievedLocation.isPresent());
        Assert.assertEquals("Test City REST", retrievedLocation.get().getCity());
        Assert.assertEquals("00-000", retrievedLocation.get().getZipCode());
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
        Assert.assertEquals("Test City DTO", retrievedLocation.get().getCity());
        Assert.assertEquals(1L, retrievedLocation.get().getLocationId());
    }

    @Test
    public void mapToLocationRestNullDTO()
    {
        final Optional<LocationRest> retrievedLocation = LocationMapper.mapToLocationRest(null);
        Assert.assertFalse(retrievedLocation.isPresent());
    }
}
