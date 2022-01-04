package com.parkly.backend.mappers;

import com.parkly.backend.mapper.LocationMapper;
import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.rest.domain.LocationRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Optional;

public class LocationMapperTest extends AbstractJUnit4SpringContextTests
{

    private LocationRest mockLocationRest;

    @Before
    public void setUp()
    {
        mockLocationRest = new LocationRest();
        mockLocationRest.setCity("Test City");
        mockLocationRest.setCountry("Test Country");
        mockLocationRest.setStreet("Test Street");
        mockLocationRest.setStreetNumber("0T");
        mockLocationRest.setZipCode("00-000");
        mockLocationRest.setLatitude(0D);
        mockLocationRest.setLongitude(0D);
    }

    @Test
    public void mapToLocationDTONonNullRest()
    {
        final Optional<LocationDTO> retrievedLocation = LocationMapper.mapToLocationDTO(mockLocationRest);

        Assert.assertTrue(retrievedLocation.isPresent());
        Assert.assertEquals("Test City", retrievedLocation.get().getCity());
        Assert.assertEquals(0D, retrievedLocation.get().getLongitude(), 0);
    }

    @Test
    public void mapToLocationDTONullRest()
    {
        final Optional<LocationDTO> retrievedLocation = LocationMapper.mapToLocationDTO(null);
        Assert.assertFalse(retrievedLocation.isPresent());
    }
}
