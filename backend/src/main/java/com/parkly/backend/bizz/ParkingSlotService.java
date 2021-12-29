package com.parkly.backend.bizz;

import com.parkly.backend.rest.domain.ParkingSlotRest;

import java.util.Set;

public interface ParkingSlotService {

    Set<ParkingSlotRest> getAllParkingSlots();

    void addParkingSlot(ParkingSlotRest parkingSlotRest);

    ParkingSlotRest getParkingSlotById(Long parkingSlotId);

    ParkingSlotRest updateParkingSlot(ParkingSlotRest parkingSlotRest);

    void deleteParkingSlot(Long parkingSlotId);
}
