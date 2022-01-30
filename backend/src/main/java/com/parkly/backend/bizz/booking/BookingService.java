package com.parkly.backend.bizz.booking;

import com.parkly.backend.rest.domain.BookingRest;
import org.springframework.lang.Nullable;

import java.util.Optional;
import java.util.Set;

public interface BookingService {
    Set<BookingRest> getAllBookings(@Nullable Long parkingSlotId);

    Optional<BookingRest> getBookingByBookingId(Long bookingId);

    Optional<BookingRest> addBooking(BookingRest bookingRest);

    boolean deleteBooking(Long bookingId);

    boolean releaseBooking(Long bookingId);
}
