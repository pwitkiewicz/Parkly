package com.parkly.backend.mappers;

import com.parkly.backend.common.CommonMockObjectsMappers;
import com.parkly.backend.mapper.PhotoMapper;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.PhotoRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class PhotoMapperTest extends CommonMockObjectsMappers
{

    private PhotoRest mockPhotoRest;
    private PhotoDTO mockPhotoDTO;
    private ParkingSlotDTO mockParkingSlotDto;

    @Before
    public void setUp()
    {
        mockPhotoDTO = setUpPhotoDTO();
        mockPhotoRest = setUpPhotoRest();
        mockParkingSlotDto = setUpParkingSlotDTO();
    }

    @Test
    public void mapToPhotoDTONonNullPhotoRest()
    {
        final Optional<PhotoDTO> retrievedPhoto = PhotoMapper.mapToPhotoDTO(mockPhotoRest, mockParkingSlotDto);

        Assert.assertTrue(retrievedPhoto.isPresent());
        Assert.assertEquals("Test photo path REST", retrievedPhoto.get().getPath());
    }

    @Test
    public void mapToPhotoDTONullPhotoRest()
    {
        final Optional<PhotoDTO> retrievedPhoto = PhotoMapper.mapToPhotoDTO(null, mockParkingSlotDto);
        Assert.assertFalse(retrievedPhoto.isPresent());
    }

    @Test
    public void mapToPhotoRestNonNullPhotoDTO()
    {
        final Optional<PhotoRest> retrievedPhoto = PhotoMapper.mapToPhotoRest(mockPhotoDTO);
        Assert.assertTrue(retrievedPhoto.isPresent());
        Assert.assertEquals("Test photo path DTO", retrievedPhoto.get().getPath());
    }

    @Test
    public void mapToPhotoDTONullPhotoDTO()
    {
        final Optional<PhotoRest> retrievedPhoto = PhotoMapper.mapToPhotoRest(null);
        Assert.assertFalse(retrievedPhoto.isPresent());
    }

}
