package com.parkly.backend.mappers;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parkly.backend.mapper.ParkingSlotMapper;
import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.LocationRest;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.rest.domain.PhotoRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.*;

public class ParkingSlotTest extends AbstractJUnit4SpringContextTests
{

    private ParkingSlotRest mockParkingSlotRest;
    private LocationDTO mockLocationDto;
    private Set<PhotoDTO> mockPhotoDtoSet;

    @Before
    public void setUp()
    {
        setUpLocation();
        setUpParkingSlotRest();
        setUpPhotoSet();
    }

    @Test
    public void mapToParkingSlotDTOAllFieldsPresent()
    {
        mockParkingSlotRest.setDescription("Test desc");
        mockParkingSlotRest.setHeight(15D);
        mockParkingSlotRest.setWidth(10D);

        final ParkingSlotDTO retrievedParkingSlotDto =
                ParkingSlotMapper.mapToParkingSlotDTO(mockLocationDto, mockPhotoDtoSet, mockParkingSlotRest);

        Assert.assertNotNull(retrievedParkingSlotDto);
        Assert.assertEquals("Test desc", retrievedParkingSlotDto.getDescription());
        Assert.assertEquals(mockLocationDto, retrievedParkingSlotDto.getLocation());
    }

    @Test
    public void mapToParkingSlotDTODescriptionNotPresent()
    {
        mockParkingSlotRest.setHeight(15D);
        mockParkingSlotRest.setWidth(10D);

        final ParkingSlotDTO retrievedParkingSlotDto =
                ParkingSlotMapper.mapToParkingSlotDTO(mockLocationDto, mockPhotoDtoSet, mockParkingSlotRest);

        Assert.assertNotNull(retrievedParkingSlotDto);
        Assert.assertNull(retrievedParkingSlotDto.getDescription());
        Assert.assertEquals("Test Parking Slot", retrievedParkingSlotDto.getName());
    }

    @Test
    public void mapToParkingSlotDTOEmptyPhotoSet()
    {
        final ParkingSlotDTO retrievedParkingSlotDto =
                ParkingSlotMapper.mapToParkingSlotDTO(mockLocationDto, Collections.emptySet(), mockParkingSlotRest);

        Assert.assertNotNull(retrievedParkingSlotDto);
        Assert.assertEquals(0, retrievedParkingSlotDto.getPhotoSet().size());
        Assert.assertEquals(1, retrievedParkingSlotDto.getIsDisabled());
    }

    @Test
    public void mapToParkingSlotDTOLocationIsNull()
    {
        final ParkingSlotDTO retrievedParkingSlotDto =
                ParkingSlotMapper.mapToParkingSlotDTO(null, mockPhotoDtoSet, mockParkingSlotRest);

        Assert.assertNull(retrievedParkingSlotDto);
    }

    private void setUpPhotoSet()
    {
        mockPhotoDtoSet = new HashSet<>();

        for(int i = 0; i < 4; i++)
        {
            final PhotoDTO mockPhotoDTO = new PhotoDTO();
            mockPhotoDTO.setPhotoId(i);
            mockPhotoDTO.setPath(String.format("Test Path %d", i));
            mockPhotoDtoSet.add(mockPhotoDTO);
        }
    }

    private void setUpLocation()
    {
        mockLocationDto = new LocationDTO();
        mockLocationDto.setLocationId(1);
        mockLocationDto.setCity("Test City");
        mockLocationDto.setCountry("Test Country");
        mockLocationDto.setStreet("Test Street");
        mockLocationDto.setStreetNumber("0T");
        mockLocationDto.setZipCode("00-000");
        mockLocationDto.setLatitude(0D);
        mockLocationDto.setLongitude(0D);
    }

    private void setUpParkingSlotRest()
    {
        mockParkingSlotRest = new ParkingSlotRest();
        mockParkingSlotRest.setCost(10D);
        mockParkingSlotRest.setEndDate(1609455600);
        mockParkingSlotRest.setStartDate(1577833200);
        mockParkingSlotRest.setName("Test Parking Slot");
        mockParkingSlotRest.setDisabledFriendly(true);
        mockParkingSlotRest.setActive(false);
    }

}
