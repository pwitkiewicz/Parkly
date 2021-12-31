package com.parkly.backend.mappers;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parkly.backend.mapper.PhotoMapper;
import com.parkly.backend.repo.PhotoRepository;
import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.PhotoRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

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
        final PhotoDTO retrievedPhoto = PhotoMapper.mapToPhotoDTO(mockPhotoRest);

        Assert.assertNotNull(retrievedPhoto);
        Assert.assertEquals("Test Path", retrievedPhoto.getPath());
    }

    @Test
    public void mapToPhotoDTONullRest()
    {
        final PhotoDTO retrievedPhoto = PhotoMapper.mapToPhotoDTO(null);
        Assert.assertNull(retrievedPhoto);
    }

}
