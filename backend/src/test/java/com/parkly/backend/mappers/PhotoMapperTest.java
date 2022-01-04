package com.parkly.backend.mappers;

import com.parkly.backend.mapper.PhotoMapper;
import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.PhotoRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Optional;

public class PhotoMapperTest extends AbstractJUnit4SpringContextTests
{

    private PhotoRest mockPhotoRest;

    @Before
    public void setUp()
    {
        mockPhotoRest = new PhotoRest();
        mockPhotoRest.setPath("Test Path");
    }

    @Test
    public void mapToPhotoDTONonNullRest()
    {
        final Optional<PhotoDTO> retrievedPhoto = PhotoMapper.mapToPhotoDTO(mockPhotoRest);

        Assert.assertTrue(retrievedPhoto.isPresent());
        Assert.assertEquals("Test Path", retrievedPhoto.get().getPath());
    }

    @Test
    public void mapToPhotoDTONullRest()
    {
        final Optional<PhotoDTO> retrievedPhoto = PhotoMapper.mapToPhotoDTO(null);
        Assert.assertFalse(retrievedPhoto.isPresent());
    }

}
