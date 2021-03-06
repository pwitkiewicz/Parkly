package com.parkly.backend.bizz.booking;

import com.parkly.backend.mapper.BookingMapper;
import com.parkly.backend.repo.BookingHistoryRepository;
import com.parkly.backend.repo.ParkingSlotRepository;
import com.parkly.backend.repo.domain.BookingHistoryDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.rest.domain.BookingRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import static com.parkly.backend.mapper.BookingMapper.mapToBookingHistoryDTO;
import static com.parkly.backend.mapper.BookingMapper.mapToBookingRest;


@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingHistoryRepository bookingHistoryRepository;
    private final ParkingSlotRepository parkingSlotRepository;

    private static final int PAGE_MAX = 10;

    @Autowired
    public BookingServiceImpl(final BookingHistoryRepository bookingHistoryRepository,
                              final ParkingSlotRepository parkingSlotRepository)
    {
        this.bookingHistoryRepository = bookingHistoryRepository;
        this.parkingSlotRepository = parkingSlotRepository;
    }

    @Override
    public Long getPageNumber()
    {
        return Math.round(Math.ceil((double) StreamSupport.stream(bookingHistoryRepository.findAll().spliterator(),false)
                .count() / 10));
    }

    @Override
    public Set<BookingRest> getAllBookings(final @Nullable Long parkingSlotId, final @Nullable Long page)
    {
        final Predicate<BookingHistoryDTO> filterByParkingSlot =
                (parkingSlot -> (Objects.nonNull(parkingSlotId) && parkingSlot.getParkingSlot().getParkingSlotId() == parkingSlotId) || (Objects.isNull(parkingSlotId)));

        final Stream<BookingRest> bookingRests =
                StreamSupport.stream(bookingHistoryRepository.findAll().spliterator(), false)
                .filter(filterByParkingSlot)
                .map(BookingMapper::mapToBookingRest)
                .filter(Optional::isPresent)
                .map(Optional::get);

        if(Objects.nonNull(page))
        {
            return bookingRests.skip(page * PAGE_MAX).limit(PAGE_MAX).collect(Collectors.toSet());
        }
        return bookingRests.collect(Collectors.toSet());
    }

    @Override
    public Optional<BookingRest> getBookingByBookingId(final Long bookingId)
    {
        final Optional<BookingHistoryDTO> booking = bookingHistoryRepository.findById(bookingId);

        if (booking.isEmpty())
        {
            log.warn("Can't find booking with id {}", bookingId);
            return Optional.empty();
        }

        return mapToBookingRest(booking.get());
    }

    @Override
    public Optional<BookingRest> addBooking(final BookingRest bookingRest)
    {
        final Optional<ParkingSlotDTO> parkingSlotDTO = parkingSlotRepository.findById(bookingRest.getParkingSlotId());
        final Optional<BookingHistoryDTO> bookingOptional = mapToBookingHistoryDTO(bookingRest, parkingSlotDTO.orElse(null));

        if (bookingOptional.isPresent())
        {
            try
            {
                final BookingHistoryDTO savedBooking = bookingHistoryRepository.save(bookingOptional.get());
                return mapToBookingRest(savedBooking);
            }
            catch (DataIntegrityViolationException e)
            {
                log.warn("Booking for parking slot {} at that time slot already exists", bookingOptional.get().getParkingSlot().getParkingSlotId(), e);
                return Optional.empty();
            }
            catch (DataAccessException e)
            {
                log.warn("Error occurred while saving booking {} to database", bookingOptional.get(), e);
                return Optional.empty();
            }
        }
        log.error("Invalid Booking rest object provided");
        return Optional.empty();
    }

    @Override
    public boolean deleteBooking(Long bookingId)
    {
        try
        {
            bookingHistoryRepository.deleteById(bookingId);
            return true;
        }
        catch (EmptyResultDataAccessException e)
        {
            log.warn("Booking id {} deletion failed!", bookingId, e);
            return false;
        }
    }

    @Override
    public boolean releaseBooking(final Long bookingId)
    {
        final Optional<BookingHistoryDTO> bookingHistoryOpt =
                bookingHistoryRepository.findById(bookingId);

        if(bookingHistoryOpt.isPresent())
        {
            try
            {
                final BookingHistoryDTO bookingHistoryDTO = bookingHistoryOpt.get();
                bookingHistoryDTO.setIsActive(0);
                bookingHistoryRepository.save(bookingHistoryDTO);
                return true;
            }
            catch (DataAccessException e)
            {
                log.warn("Error occurred while saving booking {} to database", bookingHistoryOpt.get().getBookingId(), e);
                return false;
            }
        }
        log.warn("Booking {} does not exist in database", bookingId);
        return false;
    }
}
