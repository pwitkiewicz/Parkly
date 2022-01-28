package com.parkly.backend.bizz.booking;

import com.parkly.backend.mapper.BookingMapper;
import com.parkly.backend.repo.BookingHistoryRepository;
import com.parkly.backend.rest.domain.BookingRest;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingHistoryRepository bookingHistoryRepository;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingServiceImpl(final BookingHistoryRepository bookingHistoryRepository,
                              final BookingMapper bookingMapper) {
        this.bookingHistoryRepository = bookingHistoryRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public Set<BookingRest> getAllBookings() {
        return StreamSupport
            .stream(bookingHistoryRepository.findAll().spliterator(), false)
            .map(bookingMapper::mapEntityToRest)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet());
    }

    @Override
    public Optional<BookingRest> getBookingByBookingId(final Long bookingId) {
        var booking = bookingHistoryRepository.findById(bookingId);

        if(booking.isEmpty()) {
            log.warn("can't find booking with id {}", bookingId);
            return Optional.empty();
        }

        return booking.map(bookingMapper::mapEntityToRest).map(Optional::get);
    }

    @Override
    public Optional<BookingRest> addBooking(final BookingRest bookingRest) {
        var bookingOptional = bookingMapper.mapRestToEntity(bookingRest);

        if(bookingOptional.isPresent()) {
            try {
                var savedBooking = bookingHistoryRepository.save(bookingOptional.get());
                return bookingMapper.mapEntityToRest(savedBooking);
            } catch (DataAccessException e) {
                log.warn("Error occured while saving owner {} to database", bookingOptional.get(), e);
            }
        }

        log.error("Invalid Booking rest object provided");
        return Optional.empty();
    }

    @Override
    public Optional<BookingRest> updateBooking(final Long bookingId, BookingRest bookingRest) {
        try {
            var bookingOptional = bookingHistoryRepository.findById(bookingId);

            if (bookingOptional.isPresent()) {
                bookingRest.setBookingId(bookingOptional.get().getBookingId());
            } else {
                log.warn("Invalid booking id ({}) provided", bookingId);
            }

            var booking = bookingMapper.mapRestToEntity(bookingRest);

            if(booking.isPresent()) {
                var updatedBooking = bookingHistoryRepository.save(booking.get());
                return bookingMapper.mapEntityToRest(updatedBooking);
            }
        } catch (DataAccessException e) {
            log.warn("Error occured while updating booking {} to database", bookingRest, e);
        }

        return Optional.empty();    }

    @Override
    public boolean deleteBooking(Long bookingId) {
        try {
            bookingHistoryRepository.deleteById(bookingId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            log.warn("booking id {} deletion failed!", bookingId, e);
            return false;
        }
    }
}
