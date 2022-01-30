package com.parkly.backend.mapper;

import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.PhotoRest;

import java.util.Objects;
import java.util.Optional;


public class PhotoMapper {

    public static Optional<PhotoDTO> mapToPhotoDTO(final PhotoRest photoRest, final ParkingSlotDTO parkingSlotDTO)
    {
        if(Objects.nonNull(photoRest) && Objects.nonNull(parkingSlotDTO))
        {
            final PhotoDTO photoDTO = new PhotoDTO();
            photoDTO.setPath(photoRest.getPath());
            photoDTO.setParkingSlot(parkingSlotDTO);

            return Optional.of(photoDTO);
        }
        return Optional.empty();
    }

    public static Optional<PhotoRest> mapToPhotoRest(final PhotoDTO photoDTO)
    {
        if(Objects.nonNull(photoDTO))
        {
            return Optional.of(PhotoRest.of(photoDTO.getPhotoId(), photoDTO.getPath()));
        }
        return Optional.empty();
    }
}
