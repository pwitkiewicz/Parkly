package com.parkly.backend.bizz;

import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.utils.Filter;
import com.parkly.backend.utils.Sort;
import java.util.Set;

public interface ParkingSlotService {

    Set<ParkingSlotRest> getAllParkingSlots(Filter filter, Integer page, Sort sort);

    ParkingSlotRest addParkingSlot(ParkingSlotRest parkingSlotRest);

    ParkingSlotRest getParkingSlotById(Long parkingSlotId);

    ParkingSlotRest updateParkingSlot(Long parkingSlotId, ParkingSlotRest parkingSlotRest);

    boolean deleteParkingSlot(Long parkingSlotId);
}
