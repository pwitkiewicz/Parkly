package com.parkly.backend.bizz;

import com.parkly.backend.rest.domain.BookingRest;

public interface BookingService {

    void bookByBookingId(Long bookingId);

    BookingRest releaseBookingByBookingId(Long bookingId);
}
