package com.parkly.backend.bizz;

import com.parkly.backend.repo.BookingHistoryRepository;
import com.parkly.backend.rest.domain.BookingRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{

    private BookingHistoryRepository bookingHistoryRepository;

    @Autowired
    public BookingServiceImpl(final BookingHistoryRepository bookingHistoryRepository)
    {
        this.bookingHistoryRepository=bookingHistoryRepository;
    }

    @Override
    public void bookByBookingId(Long bookingId) {

    }

    @Override
    public BookingRest releaseBookingByBookingId(Long bookingId) {
        return null;
    }
}
