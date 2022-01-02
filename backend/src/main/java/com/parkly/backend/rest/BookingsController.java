package com.parkly.backend.rest;

import com.parkly.backend.bizz.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bookings")
public class BookingsController {

    private final BookingService bookingService;

    @Autowired
    public BookingsController(final BookingService bookingService) {
        this.bookingService = bookingService;
    }
}
