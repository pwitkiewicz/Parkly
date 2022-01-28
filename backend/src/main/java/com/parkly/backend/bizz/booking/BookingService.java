package com.parkly.backend.bizz.booking;

import com.parkly.backend.rest.domain.BookingRest;

public interface BookingService {

    void bookByBookingId(Long bookingId);

    BookingRest releaseBookingByBookingId(Long bookingId);
}
