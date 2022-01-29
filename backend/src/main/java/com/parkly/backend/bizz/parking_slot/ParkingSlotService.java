package com.parkly.backend.bizz.parking_slot;

import com.parkly.backend.rest.domain.BookingRest;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.utils.domain.FilterEnum;
import com.parkly.backend.utils.domain.SortEnum;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.Set;

public interface ParkingSlotService {

    Set<ParkingSlotRest> getAllParkingSlots(FilterEnum filter, Integer page, SortEnum sort);

    Optional<ParkingSlotRest> addParkingSlot(ParkingSlotRest parkingSlotRest);

    Optional<ParkingSlotRest> getParkingSlotById(Long parkingSlotId);

    Optional<ParkingSlotRest>  updateParkingSlot(Long parkingSlotId, ParkingSlotRest parkingSlotRest);

    Optional<ParkingSlotRest> bookParkingSlot(Long parkingSlotId, BookingRest bookingRest);

    boolean deleteParkingSlot(Long parkingSlotId);
}
