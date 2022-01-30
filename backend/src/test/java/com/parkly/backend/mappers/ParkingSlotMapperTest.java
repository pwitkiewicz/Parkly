package com.parkly.backend.mappers;

import com.parkly.backend.common.CommonMockObjects;
import com.parkly.backend.mapper.ParkingSlotMapper;
import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ParkingSlotMapperTest extends CommonMockObjects
{

    private ParkingSlotRest mockParkingSlotRest;
    private ParkingSlotDTO mockParkingSlotDTO;
    private LocationDTO mockLocationDto;

    @Before
    public void setUp()
    {
        mockParkingSlotDTO = setUpParkingSlotDTO();
        mockParkingSlotDTO.setPhotoSet(setUpPhotoDTOSet());
        mockLocationDto = setUpLocationDTO();
        mockParkingSlotRest = setUpParkingSlotRest();
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
        Assert.assertEquals("Test Parking Slot REST", retrievedParkingSlotDto.get().getName());
    }

    @Test
    public void mapToParkingSlotDTOLocationIsNull()
    {
        final Optional<ParkingSlotDTO> retrievedParkingSlotDto =
                ParkingSlotMapper.mapToParkingSlotDTO(mockParkingSlotRest,null);

        Assert.assertTrue(retrievedParkingSlotDto.isEmpty());
    }

    @Test
    public void mapToParkingSlotRestAllFieldsPresent()
    {
        mockParkingSlotDTO.setDescription("Test desc");
        mockParkingSlotDTO.setHeight(15D);
        mockParkingSlotDTO.setWidth(10D);

        final Optional<ParkingSlotRest> retrievedParkingSlotRest =
                ParkingSlotMapper.mapToParkingSlotRest(mockParkingSlotDTO);

        Assert.assertTrue(retrievedParkingSlotRest.isPresent());
        Assert.assertEquals(15D, retrievedParkingSlotRest.get().getHeight(), 0);
        Assert.assertEquals(2L, retrievedParkingSlotRest.get().getPhotoRestSet().size());
    }

    @Test
    public void mapToParkingSlotRestLocationNotPresent()
    {
        mockParkingSlotDTO.setLocation(null);
        final Optional<ParkingSlotRest> retrievedParkingSlotRest =
                ParkingSlotMapper.mapToParkingSlotRest(mockParkingSlotDTO);

        Assert.assertFalse(retrievedParkingSlotRest.isPresent());
    }

    @Test
    public void mapToParkingSlotRestWidthNotPresent()
    {
        mockParkingSlotDTO.setDescription("Test desc");
        mockParkingSlotDTO.setHeight(15D);

        final Optional<ParkingSlotRest> retrievedParkingSlotRest =
                ParkingSlotMapper.mapToParkingSlotRest(mockParkingSlotDTO);

        Assert.assertTrue(retrievedParkingSlotRest.isPresent());
        Assert.assertEquals("Test desc", retrievedParkingSlotRest.get().getDescription());
        Assert.assertEquals(2L, retrievedParkingSlotRest.get().getPhotoRestSet().size());
    }

    @Test
    public void mapToParkingSlotRestPhotoSetEmpty()
    {
        mockParkingSlotDTO.setDescription("Test desc");
        mockParkingSlotDTO.setPhotoSet(null);

        final Optional<ParkingSlotRest> retrievedParkingSlotRest =
                ParkingSlotMapper.mapToParkingSlotRest(mockParkingSlotDTO);

        Assert.assertTrue(retrievedParkingSlotRest.isPresent());
        Assert.assertNull(retrievedParkingSlotRest.get().getPhotoRestSet());
    }

    @Test
    public void mapToParkingSlotRestNullParkingSlotDTO()
    {
        final Optional<ParkingSlotRest> retrievedParkingSlotRest =
                ParkingSlotMapper.mapToParkingSlotRest(null);
        Assert.assertTrue(retrievedParkingSlotRest.isEmpty());
    }


}
