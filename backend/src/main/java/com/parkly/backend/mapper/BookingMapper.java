package com.parkly.backend.mapper;

import com.parkly.backend.repo.domain.BookingHistoryDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.rest.domain.BookingRest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;
import java.util.Optional;
import static com.parkly.backend.mapper.ParkingSlotMapper.mapToParkingSlotRest;
import static com.parkly.backend.utils.TimeUtils.timestampsToDuration;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingMapper {

    public static Optional<BookingRest> mapToBookingRest(final BookingHistoryDTO bookingHistoryDTO)
    {
        var parkingSlotDTO = mapToParkingSlotRest(bookingHistoryDTO.getParkingSlot());

        if(parkingSlotDTO.isPresent())
        {
            var duration = timestampsToDuration(bookingHistoryDTO.getStartDate(), bookingHistoryDTO.getEndDate());

            var totalCost = duration.toHours() * parkingSlotDTO.get().getCost();

            return Optional.of(new BookingRest(
                    bookingHistoryDTO.getBookingId(),
                    bookingHistoryDTO.getStartDate(),
                    bookingHistoryDTO.getEndDate(),
                    bookingHistoryDTO.getIsActive() == 1,
                    totalCost,
                    parkingSlotDTO.get().getParkingSlotId(),
                    bookingHistoryDTO.getOwnerId(),
                    bookingHistoryDTO.getFirstName(),
                    bookingHistoryDTO.getLastName()
            ));
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
            bookingHistoryDTO.setEndDate(bookingRest.getEndDate());
            bookingHistoryDTO.setStartDate(bookingRest.getStartDate());
            bookingHistoryDTO.setIsActive(isActive);
            bookingHistoryDTO.setParkingSlot(parkingSlotDTO);
            bookingHistoryDTO.setFirstName(bookingHistoryDTO.getFirstName());
            bookingHistoryDTO.setLastName(bookingHistoryDTO.getLastName());

            return Optional.of(bookingHistoryDTO);
        }

        return Optional.empty();
    }
}
