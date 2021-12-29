package com.parkly.backend.bizz;

import com.parkly.backend.repo.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.parkly.backend.bizz.domain.ParkingSlot;

import java.util.Set;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {

    private final com.parkly.backend.repo.ParkingSlotRepository parkingSlotRepository;

    @Autowired
    public ParkingSlotServiceImpl(final ParkingSlotRepository parkingSlotRepository)
    {
        this.parkingSlotRepository=parkingSlotRepository;
    }

    @Override
    public Set<ParkingSlot> getAllParkingSlots() {
        return null;
    }

    @Override
    public void addParkingSlot(ParkingSlot parkingSlotRest) {

    }

    @Override
    public ParkingSlot getParkingSlotById(Long parkingSlotId) {
        return null;
    }

    @Override
    public void updateParkingSlot(ParkingSlot parkingSlotRest) {}

    @Override
    public void deleteParkingSlot(Long parkingSlotId) {}
}
