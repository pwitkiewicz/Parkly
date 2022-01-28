package com.parkly.backend.bizz.photos;

import com.parkly.backend.rest.domain.PhotoRest;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    Optional<PhotoRest> savePhoto(Long parkingSlotId, MultipartFile file);

    boolean deletePhoto(Long photoId);
}
