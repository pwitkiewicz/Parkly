package com.parkly.backend.mappers;

import com.parkly.backend.mapper.PhotoMapper;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.PhotoRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class PhotoMapperTest
{

    private PhotoRest mockPhotoRest;
    private ParkingSlotDTO mockParkingSlotDto;

    @Before
    public void setUp()
    {
        mockPhotoRest = PhotoRest.of(0L,"Test Path");
        setUpParkingSlot();
    }

    @Test
    public void mapToPhotoDTONonNullRest()
    {
        final Optional<PhotoDTO> retrievedPhoto = PhotoMapper.mapToPhotoDTO(mockPhotoRest, mockParkingSlotDto);

        Assert.assertTrue(retrievedPhoto.isPresent());
        Assert.assertEquals("Test Path", retrievedPhoto.get().getPath());
    }

    @Test
    public void mapToPhotoDTONullRest()
    {
        final Optional<PhotoDTO> retrievedPhoto = PhotoMapper.mapToPhotoDTO(null, mockParkingSlotDto);
        Assert.assertFalse(retrievedPhoto.isPresent());
    }

    private void setUpParkingSlot()
    {
        mockParkingSlotDto = new ParkingSlotDTO();
        mockParkingSlotDto.setCost(10D);
        mockParkingSlotDto.setEndDate(1609455600);
        mockParkingSlotDto.setStartDate(1577833200);
        mockParkingSlotDto.setName("Test Parking Slot");
        mockParkingSlotDto.setIsDisabled(1);
        mockParkingSlotDto.setIsActive(1);
    }


}
