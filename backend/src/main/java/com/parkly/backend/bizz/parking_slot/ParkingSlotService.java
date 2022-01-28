package com.parkly.backend.bizz.parking_slot;

import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.utils.domain.FilterEnum;
import com.parkly.backend.utils.domain.SortEnum;
import java.util.Set;

public interface ParkingSlotService {

    Set<ParkingSlotRest> getAllParkingSlots(FilterEnum filter, Integer page, SortEnum sort);

    ParkingSlotRest addParkingSlot(ParkingSlotRest parkingSlotRest);

    ParkingSlotRest getParkingSlotById(Long parkingSlotId);

    ParkingSlotRest updateParkingSlot(Long parkingSlotId, ParkingSlotRest parkingSlotRest);

    boolean deleteParkingSlot(Long parkingSlotId);
}
