package com.parkly.backend.bizz.photos;

import com.parkly.backend.repo.ParkingSlotRepository;
import com.parkly.backend.repo.PhotoRepository;
import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.PhotoRest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Service
@ConfigurationProperties(prefix = "photos")
public class PhotoServiceImpl implements PhotoService {

    @Value("${photos.path}")
    private String path;
    private final PhotoRepository photoRepository;
    private final ParkingSlotRepository parkingSlotRepository;

    @Autowired
    public PhotoServiceImpl(final PhotoRepository photoRepository, final ParkingSlotRepository parkingSlotRepository) {
        this.photoRepository = photoRepository;
        this.parkingSlotRepository = parkingSlotRepository;
    }

    @Override
    public Optional<PhotoRest> savePhoto(final Long parkingSlotId, final MultipartFile file) {
        var parkingSlotOptional = parkingSlotRepository.findById(parkingSlotId);

        if (parkingSlotOptional.isEmpty()) {
            log.warn("Invalid parking slot id ({}) provided to save photo", parkingSlotId);
            return Optional.empty();
        }

        try (InputStream fileStream = file.getInputStream()) {
            var parkingSlot = parkingSlotOptional.get();
            var photoPath = savePhotoToStorage(parkingSlotId, fileStream);

            if (photoPath.isPresent()) {
                var photo = new PhotoDTO(photoPath.get(), parkingSlot);

                photoRepository.save(photo);
                return Optional.of(PhotoRest.of(photo.getPhotoId(), photo.getPath()));
            }
        } catch (Exception e) {
            log.error("Error saving image file ({}) for parking slot (id {}) ",
                file.getOriginalFilename(),
                parkingSlotId);
        }

        return Optional.empty();
    }

    @Override
    public boolean deletePhoto(Long photoId) {
        try {
            photoRepository.deleteById(photoId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            log.warn("Photo id {} deletion failed!", photoId);
            return false;
        }
    }

    private Optional<String> savePhotoToStorage(final Long parkingSlotId, final InputStream fileStream) {
        var image = readImage(fileStream);

        if (image.isPresent()) {
            log.info("Saving image file for parking slot (id: {})", parkingSlotId);

            var fileName = parkingSlotId + "_" + UUID.randomUUID() + ".jpg";
            var file = new File(path, fileName);

            try {
                ImageIO.write(image.get(), "jpg", file);
            } catch (IOException e) {
                log.error("Error saving image file for parking slot (id: {})", parkingSlotId);
            }

            return Optional.of(file.getAbsolutePath());
        }

        return Optional.empty();
    }

    private Optional<BufferedImage> readImage(final InputStream fileStream) {
        try {
            return Optional.of(ImageIO.read(fileStream));
        } catch (Exception e) {
            log.warn("Sent file is not an image");
            return Optional.empty();
        }
    }
}
