package com.parkly.backend.rest;

import static com.parkly.backend.utils.LogWriter.logHeaders;

import com.parkly.backend.bizz.owner.OwnerService;
import com.parkly.backend.bizz.security.SecurityService;
import com.parkly.backend.rest.domain.OwnerRest;
import java.net.URI;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(path = "/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final SecurityService securityService;

    @Autowired
    public OwnerController(final OwnerService ownerService, final SecurityService securityService) {
        this.ownerService = ownerService;
        this.securityService = securityService;
    }

    @PostMapping("")
    public ResponseEntity<OwnerRest> addOwner(@RequestHeader HttpHeaders headers,
                                              @RequestBody OwnerRest newOwner) {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers)) {
            var ownerOptional = ownerService.addOwner(newOwner);

            if(ownerOptional.isPresent()) {
                var addedOwner = ownerOptional.get();

                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/owner/{ownerId}")
                    .buildAndExpand(addedOwner.getOwnerId())
                    .toUri();

                return ResponseEntity.status(HttpStatus.OK).location(uri).body(addedOwner);
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(OwnerRest.EMPTY_OWNER);
    }

    @GetMapping("{ownerId}")
    public ResponseEntity<OwnerRest> getOwner(@RequestHeader HttpHeaders headers,
                                              @PathVariable Long ownerId) {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers)) {
            var owner = ownerService.getOwner(ownerId);

            if(owner.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(owner.get());
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(OwnerRest.EMPTY_OWNER);
    }

    @PutMapping("{ownerId}")
    public ResponseEntity<OwnerRest> updateOwner(@RequestHeader HttpHeaders headers,
                                                 @PathVariable Long ownerId,
                                                 @RequestBody OwnerRest owner) {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers)) {
            var updatedOwner = ownerService.updateOwner(ownerId, owner);

            if(updatedOwner.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedOwner.get());
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(OwnerRest.EMPTY_OWNER);
    }

    @DeleteMapping("{ownerId}")
    public ResponseEntity<String> deleteOwner(@RequestHeader HttpHeaders headers,
                                              @PathVariable Long ownerId) {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers)) {
            if(ownerService.deleteOwner(ownerId)) {
                return ResponseEntity.ok().body(JSONObject.quote("Item deleted"));
            }
            return ResponseEntity.ok().body(JSONObject.quote("Invalid request"));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(JSONObject.quote("Unauthorized access"));
    }
}
