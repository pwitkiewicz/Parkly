package com.parkly.backend.rest;

import static com.parkly.backend.utils.LogWriter.logException;
import static com.parkly.backend.utils.LogWriter.logHeaders;

import com.parkly.backend.bizz.parking_slot.ParkingSlotService;
import com.parkly.backend.bizz.security.SecurityService;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.utils.domain.FilterEnum;
import com.parkly.backend.utils.domain.SortEnum;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(path = "/items")
public class ParkingSlotsController {

    private final ParkingSlotService parkingSlotService;
    private final SecurityService securityService;

    @Autowired
    public ParkingSlotsController(final ParkingSlotService parkingSlotService, final SecurityService securityService) {
        this.parkingSlotService = parkingSlotService;
        this.securityService = securityService;
    }

    @GetMapping("")
    public ResponseEntity<Collection<ParkingSlotRest>> getAllParkingSlots(@RequestHeader HttpHeaders headers,
                                                                          @RequestParam(defaultValue = "all") String filter,
                                                                          @RequestParam(defaultValue = "0") Integer page,
                                                                          @RequestParam(defaultValue = "none") String sort) {
        logHeaders(headers);

        if (securityService.isAuthenticated(headers)) {
            try {
                var filterType = FilterEnum.valueOf(filter.toUpperCase(Locale.ROOT));
                var sortType = SortEnum.valueOf(sort.toUpperCase(Locale.ROOT));
                return ResponseEntity.ok(parkingSlotService.getAllParkingSlots(filterType, page, sortType));
            }
            catch (IllegalArgumentException e) {
                logException(e);
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singleton(ParkingSlotRest.EMPTY_SLOT));
    }

    @GetMapping("{parkingSlotId}")
    public ResponseEntity<ParkingSlotRest> getParkingSlot(@RequestHeader HttpHeaders headers,
                                                          @PathVariable Long parkingSlotId) {
        logHeaders(headers);

        if (securityService.isAuthenticated(headers)) {
            var foundParkingSlot = parkingSlotService.getParkingSlotById(parkingSlotId);

            if(!foundParkingSlot.equals(ParkingSlotRest.EMPTY_SLOT)) {
                return ResponseEntity.ok(parkingSlotService.getParkingSlotById(parkingSlotId));
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ParkingSlotRest.EMPTY_SLOT);
    }

    @PostMapping("")
    public ResponseEntity<ParkingSlotRest> saveParkingSlot(@RequestHeader HttpHeaders headers,
                                                           @RequestBody ParkingSlotRest newParkingSlot) {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers)) {
            var savedParkingSlot = parkingSlotService.addParkingSlot(newParkingSlot);

            if(!savedParkingSlot.equals(ParkingSlotRest.EMPTY_SLOT)) {
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/items/{parkingSlotId}")
                    .buildAndExpand(savedParkingSlot.getParkingSlotId())
                    .toUri();

                return ResponseEntity.status(HttpStatus.OK).location(uri).body(savedParkingSlot);
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ParkingSlotRest.EMPTY_SLOT);
    }

    @PutMapping("{parkingSlotId}")
    public ResponseEntity<ParkingSlotRest> updateParkingSlot(@RequestHeader HttpHeaders headers,
                                                             @PathVariable Long parkingSlotId,
                                                             @RequestBody ParkingSlotRest parkingSlotToUpdate) {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers)) {
            var updatedParkingSlot = parkingSlotService.updateParkingSlot(parkingSlotId, parkingSlotToUpdate);

            if(!updatedParkingSlot.equals(ParkingSlotRest.EMPTY_SLOT)) {
                return ResponseEntity.ok(updatedParkingSlot);
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ParkingSlotRest.EMPTY_SLOT);
    }

    @DeleteMapping("{parkingSlotId}")
    public ResponseEntity<String> deleteParkingSlot(@RequestHeader HttpHeaders headers,
                                                    @PathVariable Long parkingSlotId) {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers)) {
            if(parkingSlotService.deleteParkingSlot(parkingSlotId)) {
                return ResponseEntity.ok().body(JSONObject.quote("Item deleted"));
            }
            return ResponseEntity.ok().body(JSONObject.quote("Invalid request"));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(JSONObject.quote("Unauthorized access"));
    }
}
