package com.parkly.backend.bizz.booking;

import com.parkly.backend.mapper.BookingMapper;
import com.parkly.backend.repo.BookingHistoryRepository;
import com.parkly.backend.repo.OwnerRepository;
import com.parkly.backend.rest.domain.BookingRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.parkly.backend.mapper.BookingMapper.mapToBookingHistoryDTO;
import static com.parkly.backend.mapper.BookingMapper.mapToBookingRest;


@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingHistoryRepository bookingHistoryRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public BookingServiceImpl(final BookingHistoryRepository bookingHistoryRepository,
                              final OwnerRepository ownerRepository) {
        this.bookingHistoryRepository = bookingHistoryRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<BookingRest> getAllBookings() {
        return StreamSupport
                .stream(bookingHistoryRepository.findAll().spliterator(), false)
                .map(BookingMapper::mapToBookingRest)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<BookingRest> getBookingByBookingId(final Long bookingId) {
        var booking = bookingHistoryRepository.findById(bookingId);

        if (booking.isEmpty()) {
            log.warn("can't find booking with id {}", bookingId);
            return Optional.empty();
        }

        return mapToBookingRest(booking.get());
    }

    @Override
    public Optional<BookingRest> addBooking(final BookingRest bookingRest) {
        var ownerOptional = ownerRepository.findById(bookingRest.getOwnerId());

        if(ownerOptional.isPresent()) {
            var bookingOptional = mapToBookingHistoryDTO(bookingRest, ownerOptional.get());

            if (bookingOptional.isPresent()) {
                try {
                    var savedBooking = bookingHistoryRepository.save(bookingOptional.get());
                    return mapToBookingRest(savedBooking);
                } catch (DataAccessException e) {
                    log.warn("Error occured while saving owner {} to database", bookingOptional.get(), e);
                }
            }
        }

        log.error("Invalid Booking rest object provided");
        return Optional.empty();
    }

    @Override
    public Optional<BookingRest> updateBooking(final Long bookingId, BookingRest bookingRest) {
        try {
            var owner = ownerRepository.findById(bookingRest.getOwnerId());
            var bookingOptional = bookingHistoryRepository.findById(bookingId);

            if (bookingOptional.isPresent()) {
                bookingRest.setBookingId(bookingOptional.get().getBookingId());
            } else {
                log.warn("Invalid booking id ({}) provided", bookingId);
            }

            if(owner.isPresent()) {
                var booking = mapToBookingHistoryDTO(bookingRest, owner.get());

                if (booking.isPresent()) {
                    var updatedBooking = bookingHistoryRepository.save(booking.get());
                    return mapToBookingRest(updatedBooking);
                }
            }
            else {
                log.warn("Incorrect owner id");
            }

        } catch (DataAccessException e) {
            log.warn("Error occured while updating booking {} to database", bookingRest, e);
        }

        return Optional.empty();
    }

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
