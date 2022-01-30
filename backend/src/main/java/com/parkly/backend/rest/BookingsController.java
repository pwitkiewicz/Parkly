package com.parkly.backend.rest;

import static com.parkly.backend.utils.LogWriter.logException;
import static com.parkly.backend.utils.LogWriter.logHeaders;

import com.parkly.backend.bizz.booking.BookingService;
import com.parkly.backend.bizz.security.SecurityService;
import com.parkly.backend.rest.domain.BookingRest;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;

import com.parkly.backend.utils.domain.FilterEnum;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/bookings")
public class BookingsController {

    private final BookingService bookingService;
    private final SecurityService securityService;

    @Autowired
    public BookingsController(final BookingService bookingService, final SecurityService securityService)
    {
        this.bookingService = bookingService;
        this.securityService = securityService;
    }

    @GetMapping("/pages")
    public ResponseEntity<Long> getBookingsPages(@RequestHeader HttpHeaders headers)
    {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers))
        {
            try
            {
                return ResponseEntity.ok(bookingService.getPageNumber());
            }
            catch (IllegalArgumentException e)
            {
                logException(e);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(-1L);
    }

    @GetMapping
    public ResponseEntity<Collection<BookingRest>> getAllBookings(@RequestHeader HttpHeaders headers,
                                                                  @RequestParam(defaultValue = "0") Integer page)
    {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers))
        {
            return ResponseEntity.ok(bookingService.getAllBookings(null, page.longValue()));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singleton(BookingRest.EMPTY_BOOKING));
    }

    @GetMapping("{bookingId}")
    public ResponseEntity<BookingRest> getBooking(@RequestHeader HttpHeaders headers,
                                                  @PathVariable Long bookingId)
    {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers))
        {
            final Optional<BookingRest> bookingOptional = bookingService.getBookingByBookingId(bookingId);

            if(bookingOptional.isPresent())
            {
                return ResponseEntity.ok(bookingOptional.get());
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(BookingRest.EMPTY_BOOKING);
    }

    @PostMapping
    public ResponseEntity<BookingRest> addBooking(@RequestHeader HttpHeaders headers,
                                                  @RequestBody BookingRest newBooking)
    {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers))
        {
            final Optional<BookingRest> addedBooking = bookingService.addBooking(newBooking);

            if(addedBooking.isPresent())
            {
                final BookingRest booking = addedBooking.get();

                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/bookings/{bookingId}")
                    .buildAndExpand(booking.getBookingId())
                    .toUri();

                return ResponseEntity.status(HttpStatus.OK).location(uri).body(booking);
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(BookingRest.EMPTY_BOOKING);
    }

    @DeleteMapping("{bookingId}")
    public ResponseEntity<String> deleteBooking(@RequestHeader HttpHeaders headers,
                                                @PathVariable Long bookingId)
    {
        logHeaders(headers);

        if(securityService.isAuthenticated(headers))
        {
            if(bookingService.deleteBooking(bookingId))
            {
                return ResponseEntity.ok().body(JSONObject.quote("Item deleted"));
            }
            return ResponseEntity.ok().body(JSONObject.quote("Invalid request"));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(JSONObject.quote("Unauthorized access"));
    }
}
