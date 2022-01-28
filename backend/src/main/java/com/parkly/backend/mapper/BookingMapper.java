package com.parkly.backend.mapper;

import com.parkly.backend.repo.OwnerRepository;
import com.parkly.backend.repo.domain.BookingHistoryDTO;
import com.parkly.backend.rest.domain.BookingRest;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class BookingMapper {

    private final ParkingSlotMapper parkingSlotMapper;
    private final OwnerRepository ownerRepository;

    @Autowired
    public BookingMapper(final ParkingSlotMapper parkingSlotMapper, final OwnerRepository ownerRepository) {
        this.parkingSlotMapper = parkingSlotMapper;
        this.ownerRepository = ownerRepository;
    }

    public Optional<BookingRest> mapEntityToRest(final BookingHistoryDTO bookingHistoryDTO) {
        var parkingSlot = bookingHistoryDTO.getParkingSlot();
        // TODO update totalCost logic when it is established to what time period parkingSlot cost refers to
        var totalCost = (bookingHistoryDTO.getEndDate() - bookingHistoryDTO.getStartDate()) * parkingSlot.getCost();

        return Optional.of(new BookingRest(
            bookingHistoryDTO.getBookingId(),
            bookingHistoryDTO.getStartDate(),
            bookingHistoryDTO.getEndDate(),
            bookingHistoryDTO.getIsActive() == 1,
            totalCost,
            parkingSlotMapper.mapEntityToRest(parkingSlot),
            bookingHistoryDTO.getOwner().getOwnerId()
        ));
    }

    public Optional<BookingHistoryDTO> mapRestToEntity(final BookingRest bookingRest) {
        var owner = ownerRepository.findById(bookingRest.getOwnerId());
        var isActive = bookingRest.getIsActive() ? 1 : 0;

        if(owner.isEmpty()) {
            log.error("Booking rest object has invalid owner id!");
            return Optional.empty();
        }

        return Optional.of(new BookingHistoryDTO(
            bookingRest.getBookingId(),
            bookingRest.getStartDate(),
            bookingRest.getEndDate(),
            isActive,
            parkingSlotMapper.mapRestToEntity(bookingRest.getParkingSlotRest()),
            owner.get()
        ));
    }
}
