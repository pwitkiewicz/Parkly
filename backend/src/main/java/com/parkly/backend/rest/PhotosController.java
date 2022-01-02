package com.parkly.backend.rest;

import static com.parkly.backend.utils.LogWriter.logHeaders;

import com.parkly.backend.bizz.photos.PhotoService;
import com.parkly.backend.bizz.security.SecurityService;
import com.parkly.backend.rest.domain.PhotoRest;
import java.net.URI;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(path = "/photos")
public class PhotosController {

    private final PhotoService photoService;
    private final SecurityService securityService;

    @Autowired
    public PhotosController(final PhotoService photoService, final SecurityService securityService) {
        this.photoService = photoService;
        this.securityService = securityService;
    }

    @PostMapping(value = "{parkingSlotId}", consumes = {"multipart/form-data"})
    public ResponseEntity<PhotoRest> savePhoto(@RequestHeader HttpHeaders headers,
                                               @PathVariable Long parkingSlotId,
                                               @RequestBody MultipartFile file) {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers)) {
            var savedPhotoRestOptional = photoService.savePhoto(parkingSlotId, file);

            if(savedPhotoRestOptional.isPresent()) {
                var photoRest = savedPhotoRestOptional.get();
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path(photoRest.getPath())
                    .build()
                    .toUri();

                return ResponseEntity.status(HttpStatus.OK).location(uri).body(photoRest);
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(PhotoRest.EMPTY_PHOTO);
    }

    @DeleteMapping("{photoId}")
    public ResponseEntity<String> deletePhoto(@RequestHeader HttpHeaders headers,
                                              @PathVariable Long photoId) {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers)) {
            if(photoService.deletePhoto(photoId)) {
                return ResponseEntity.ok().body(JSONObject.quote("Photo deleted"));
            }
            return ResponseEntity.ok().body(JSONObject.quote("Invalid request"));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(JSONObject.quote("Unauthorized access"));
    }
}
