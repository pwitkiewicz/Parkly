package com.parkly.backend.mappers;

import com.parkly.backend.mapper.ParkingSlotMapper;
import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ParkingSlotMapperTest
{

    private ParkingSlotRest mockParkingSlotRest;
    private LocationDTO mockLocationDto;

    @Before
    public void setUp()
    {
        setUpLocation();
        setUpParkingSlotRest();
    }

    @Test
    public void mapToParkingSlotDTOAllFieldsPresent()
    {
        mockParkingSlotRest.setDescription("Test desc");
        mockParkingSlotRest.setHeight(15D);
        mockParkingSlotRest.setWidth(10D);

        final Optional<ParkingSlotDTO> retrievedParkingSlotDto =
                ParkingSlotMapper.mapToParkingSlotDTO(mockParkingSlotRest, mockLocationDto);

        Assert.assertTrue(retrievedParkingSlotDto.isPresent());
        Assert.assertEquals("Test desc", retrievedParkingSlotDto.get().getDescription());
        Assert.assertEquals(mockLocationDto, retrievedParkingSlotDto.get().getLocation());
    }

    @Test
    public void mapToParkingSlotDTODescriptionNotPresent()
    {
        mockParkingSlotRest.setHeight(15D);
        mockParkingSlotRest.setWidth(10D);

        final Optional<ParkingSlotDTO> retrievedParkingSlotDto =
                ParkingSlotMapper.mapToParkingSlotDTO(mockParkingSlotRest, mockLocationDto);

        Assert.assertTrue(retrievedParkingSlotDto.isPresent());
        Assert.assertNull(retrievedParkingSlotDto.get().getDescription());
        Assert.assertEquals("Test Parking Slot", retrievedParkingSlotDto.get().getName());
    }

    @Test
    public void mapToParkingSlotDTOLocationIsNull()
    {
        final Optional<ParkingSlotDTO> retrievedParkingSlotDto =
                ParkingSlotMapper.mapToParkingSlotDTO(mockParkingSlotRest,null);

        Assert.assertTrue(retrievedParkingSlotDto.isEmpty());
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
