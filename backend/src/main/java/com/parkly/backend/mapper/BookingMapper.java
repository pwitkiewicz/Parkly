package com.parkly.backend.mapper;

import com.parkly.backend.repo.domain.BookingHistoryDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.rest.domain.BookingRest;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import static com.parkly.backend.mapper.ParkingSlotMapper.mapToParkingSlotRest;
import static com.parkly.backend.utils.TimeUtils.*;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingMapper {

    public static Optional<BookingRest> mapToBookingRest(final BookingHistoryDTO bookingHistoryDTO)
    {
        if(Objects.nonNull(bookingHistoryDTO))
        {
            final Optional<ParkingSlotRest> parkingSlotRest = mapToParkingSlotRest(bookingHistoryDTO.getParkingSlot());

            if (parkingSlotRest.isPresent())
            {
                final Duration duration = timestampsToDuration(bookingHistoryDTO.getStartDate(), bookingHistoryDTO.getEndDate());
                final Double totalCost = duration.toHours() * parkingSlotRest.get().getCost();

                return Optional.of(new BookingRest(
                        bookingHistoryDTO.getBookingId(),
                        unixTimestampToString(bookingHistoryDTO.getStartDate()),
                        unixTimestampToString(bookingHistoryDTO.getEndDate()),
                        bookingHistoryDTO.getIsActive() == 1,
                        totalCost,
                        parkingSlotRest.get().getParkingSlotId(),
                        bookingHistoryDTO.getOwnerId(),
                        bookingHistoryDTO.getFirstName(),
                        bookingHistoryDTO.getLastName()
                ));
            }
        }
        return Optional.empty();
    }

    public static Optional<BookingHistoryDTO> mapToBookingHistoryDTO(final BookingRest bookingRest,
                                                                     final ParkingSlotDTO parkingSlotDTO)
    {
        if (Objects.nonNull(bookingRest) && Objects.nonNull(parkingSlotDTO))
        {
            final int isActive = Boolean.TRUE.equals(bookingRest.getIsActive()) ? 1 : 0;
            final BookingHistoryDTO bookingHistoryDTO = new BookingHistoryDTO();
            bookingHistoryDTO.setOwnerId(bookingRest.getOwnerId());
            bookingHistoryDTO.setEndDate(stringToUnixTimestamp(bookingRest.getEndDate()));
            bookingHistoryDTO.setStartDate(stringToUnixTimestamp(bookingRest.getStartDate()));
            bookingHistoryDTO.setIsActive(isActive);
            bookingHistoryDTO.setParkingSlot(parkingSlotDTO);
            bookingHistoryDTO.setFirstName(bookingRest.getFirstName());
            bookingHistoryDTO.setLastName(bookingRest.getLastName());

            return Optional.of(bookingHistoryDTO);
        }

        return Optional.empty();
    }
}
