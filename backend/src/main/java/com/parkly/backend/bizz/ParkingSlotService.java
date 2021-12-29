package com.parkly.backend.bizz;

import com.parkly.backend.bizz.domain.ParkingSlot;

import java.util.Set;

public interface ParkingSlotService {

    Set<ParkingSlot> getAllParkingSlots();

    void addParkingSlot(ParkingSlot parkingSlotRest);

    ParkingSlot getParkingSlotById(Long parkingSlotId);

    void updateParkingSlot(ParkingSlot parkingSlotRest);

    void deleteParkingSlot(Long parkingSlotId);
}
