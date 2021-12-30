package com.parkly.backend.bizz;

import com.parkly.backend.repo.ParkingSlotRepository;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Set<ParkingSlotRest> getAllParkingSlots() {
        return null;
    }

    @Override
    public void addParkingSlot(ParkingSlotRest parkingSlotRest) {

    }

    @Override
    public ParkingSlotRest getParkingSlotById(Long parkingSlotId) {
        return null;
    }

    @Override
    public ParkingSlotRest updateParkingSlot(ParkingSlotRest parkingSlotRest) {return null;}

    @Override
    public void deleteParkingSlot(Long parkingSlotId) {}
}
