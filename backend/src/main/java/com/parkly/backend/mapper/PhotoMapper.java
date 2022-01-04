package com.parkly.backend.mapper;

import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.PhotoRest;

import java.util.Objects;
import java.util.Optional;

public class PhotoMapper {

    public static Optional<PhotoDTO> mapToPhotoDTO(final PhotoRest photoRest)
    {
        if(Objects.nonNull(photoRest))
        {
            final PhotoDTO photoDTO = new PhotoDTO();
            photoDTO.setPath(photoRest.getPath());

            return Optional.of(photoDTO);
        }
        return Optional.empty();
    }
}
