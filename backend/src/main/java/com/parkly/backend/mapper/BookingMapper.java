package com.parkly.backend.mapper;

import com.parkly.backend.repo.domain.BookingHistoryDTO;
import com.parkly.backend.repo.domain.OwnerDTO;
import com.parkly.backend.rest.domain.BookingRest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

import static com.parkly.backend.mapper.LocationMapper.mapToLocationDTO;
import static com.parkly.backend.mapper.ParkingSlotMapper.mapToParkingSlotDTO;
import static com.parkly.backend.mapper.ParkingSlotMapper.mapToParkingSlotRest;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingMapper {

    public static Optional<BookingRest> mapToBookingRest(final BookingHistoryDTO bookingHistoryDTO) {
        var parkingSlotDTO = mapToParkingSlotRest(bookingHistoryDTO.getParkingSlot());

        if(parkingSlotDTO.isPresent()) {
            var startInstant = Instant.ofEpochMilli(bookingHistoryDTO.getStartDate());
            var endInstant = Instant.ofEpochMilli(bookingHistoryDTO.getEndDate());
            var duration = Duration.between(startInstant, endInstant);

            var totalCost = duration.toHours() * parkingSlotDTO.get().getCost();

            return Optional.of(new BookingRest(
                    bookingHistoryDTO.getBookingId(),
                    bookingHistoryDTO.getStartDate(),
                    bookingHistoryDTO.getEndDate(),
                    bookingHistoryDTO.getIsActive() == 1,
                    totalCost,
                    parkingSlotDTO.get(),
                    bookingHistoryDTO.getOwner().getOwnerId()
            ));
        }

        return Optional.empty();
    }

    public static Optional<BookingHistoryDTO> mapToBookingHistoryDTO(final BookingRest bookingRest, final OwnerDTO ownerDTO) {
        if (Objects.nonNull(bookingRest)) {
            var isActive = bookingRest.getIsActive() ? 1 : 0;
            var location = bookingRest.getParkingSlotRest().getLocationRest();
            var locationDTO = mapToLocationDTO(location);

            if (locationDTO.isPresent()) {
                var parkingSlotDTO = mapToParkingSlotDTO(bookingRest.getParkingSlotRest(), locationDTO.get());

                if (parkingSlotDTO.isPresent()) {
                    return Optional.of(new BookingHistoryDTO(
                            bookingRest.getBookingId(),
                            bookingRest.getStartDate(),
                            bookingRest.getEndDate(),
                            isActive,
                            parkingSlotDTO.get(),
                            ownerDTO
                    ));
                }
            }
        }

        return Optional.empty();
    }
}
