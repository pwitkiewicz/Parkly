package com.parkly.backend.rest;

import com.parkly.backend.bizz.booking.BookingService;
import com.parkly.backend.bizz.parking_slot.ParkingSlotService;
import com.parkly.backend.bizz.security.SecurityService;
import com.parkly.backend.rest.domain.BookingRest;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.utils.domain.FilterEnum;
import com.parkly.backend.utils.domain.SortEnum;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;

import static com.parkly.backend.utils.LogWriter.logException;
import static com.parkly.backend.utils.LogWriter.logHeaders;


@RestController
@RequestMapping(path = "/items")
public class ParkingSlotsController {

    private final ParkingSlotService parkingSlotService;
    private final BookingService bookingService;
    private final SecurityService securityService;

    @Autowired
    public ParkingSlotsController(final ParkingSlotService parkingSlotService,
                                  final BookingService bookingService,
                                  final SecurityService securityService)
    {
        this.parkingSlotService = parkingSlotService;
        this.bookingService = bookingService;
        this.securityService = securityService;
    }

    @GetMapping("/pages")
    public ResponseEntity<Long> getParkingSlotsPages(@RequestHeader HttpHeaders headers,
                                                     @RequestParam(defaultValue = "all") String location,
                                                     @RequestParam(defaultValue = "all") String filter)
    {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers))
        {
            try
            {
                final FilterEnum filterType = FilterEnum.valueOf(filter.toUpperCase(Locale.ROOT));
                return ResponseEntity.ok(parkingSlotService.getPageNumber(filterType, location));
            }
            catch (IllegalArgumentException e)
            {
                logException(e);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(-1L);
    }

    @GetMapping
    public ResponseEntity<Collection<ParkingSlotRest>> getAllParkingSlots(@RequestHeader HttpHeaders headers,
                                                                          @RequestParam(defaultValue = "all") String location,
                                                                          @RequestParam(required = false) Long startDate,
                                                                          @RequestParam(required = false) Long endDate,
                                                                          @RequestParam(defaultValue = "all") String filter,
                                                                          @RequestParam(defaultValue = "0") Integer page,
                                                                          @RequestParam(defaultValue = "none") String sort)
    {
        logHeaders(headers);

        if (securityService.isAuthenticated(headers))
        {
            try
            {
                final FilterEnum filterType = FilterEnum.valueOf(filter.toUpperCase(Locale.ROOT));
                final SortEnum sortType = SortEnum.valueOf(sort.toUpperCase(Locale.ROOT));
                return ResponseEntity.ok(parkingSlotService.getAllParkingSlots(filterType, page, sortType, location,
                        startDate, endDate));
            }
            catch (IllegalArgumentException e)
            {
                logException(e);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singleton(ParkingSlotRest.EMPTY_SLOT));
    }

    @GetMapping("{parkingSlotId}")
    public ResponseEntity<ParkingSlotRest> getParkingSlot(@RequestHeader HttpHeaders headers,
                                                          @PathVariable Long parkingSlotId)
    {
        logHeaders(headers);

        if (securityService.isAuthenticated(headers))
        {
            final Optional<ParkingSlotRest> foundParkingSlot = parkingSlotService.getParkingSlotById(parkingSlotId);

            if (foundParkingSlot.isPresent())
            {
                return ResponseEntity.status(HttpStatus.OK).body(foundParkingSlot.get());
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ParkingSlotRest.EMPTY_SLOT);
    }

    @PostMapping
    public ResponseEntity<ParkingSlotRest> addParkingSlot(@RequestHeader HttpHeaders headers,
                                                          @RequestBody ParkingSlotRest newParkingSlot)
    {
        logHeaders(headers);

        if (securityService.isAuthenticated(headers))
        {
            final Optional<ParkingSlotRest> addedParkingSlot = parkingSlotService.addParkingSlot(newParkingSlot);

            if(addedParkingSlot.isPresent())
            {
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/items/{parkingSlotId}")
                        .buildAndExpand(addedParkingSlot.get().getParkingSlotId())
                        .toUri();
                return ResponseEntity.status(HttpStatus.OK).location(uri).body(addedParkingSlot.get());
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ParkingSlotRest.EMPTY_SLOT);
    }

    @PutMapping("{parkingSlotId}")
    public ResponseEntity<ParkingSlotRest> updateParkingSlot(@RequestHeader HttpHeaders headers,
                                                             @PathVariable Long parkingSlotId,
                                                             @RequestBody ParkingSlotRest parkingSlotToUpdate)
    {
        logHeaders(headers);

        if (securityService.isAuthenticated(headers))
        {
            final Optional<ParkingSlotRest> updatedParkingSlot = parkingSlotService.updateParkingSlot(parkingSlotId, parkingSlotToUpdate);

            if (updatedParkingSlot.isPresent())
            {
                return ResponseEntity.status(HttpStatus.OK).body(updatedParkingSlot.get());
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ParkingSlotRest.EMPTY_SLOT);
    }

    @DeleteMapping("{parkingSlotId}")
    public ResponseEntity<String> deleteParkingSlot(@RequestHeader HttpHeaders headers,
                                                    @PathVariable Long parkingSlotId)
    {
        logHeaders(headers);

        if (securityService.isAuthenticated(headers))
        {
            if (parkingSlotService.deleteParkingSlot(parkingSlotId))
            {
                return ResponseEntity.ok().body(JSONObject.quote("Item deleted"));
            }
            return ResponseEntity.ok().body(JSONObject.quote("Invalid request"));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(JSONObject.quote("Unauthorized access"));
    }

    @PutMapping("{parkingSlotId}/book")
    public ResponseEntity<ParkingSlotRest> bookParkingSlot(@RequestHeader HttpHeaders headers,
                                                           @PathVariable Long parkingSlotId,
                                                           @RequestBody BookingRest bookingRest)
    {
        logHeaders(headers);
        if (securityService.isAuthenticated(headers))
        {
            final Optional<ParkingSlotRest> bookedParkingSlot = parkingSlotService.bookParkingSlot(parkingSlotId, bookingRest);

            if (bookedParkingSlot.isPresent())
            {
                return ResponseEntity.status(HttpStatus.OK).body(bookedParkingSlot.get());
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ParkingSlotRest.EMPTY_SLOT);
    }

    @PutMapping("{parkingSlotId}/release")
    public ResponseEntity<ParkingSlotRest> releaseParkingSlot(@RequestHeader HttpHeaders headers,
                                                              @PathVariable Long parkingSlotId,
                                                              @RequestBody Long bookingId) {
        logHeaders(headers);

        if (securityService.isAuthenticated(headers)) {
            var parkingSlotOptional = parkingSlotService.getParkingSlotById(parkingSlotId);

            if (parkingSlotOptional.isPresent() && bookingService.releaseBooking(bookingId))
            {
                    return ResponseEntity.status(HttpStatus.OK).body(parkingSlotOptional.get());
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ParkingSlotRest.EMPTY_SLOT);
    }
}
