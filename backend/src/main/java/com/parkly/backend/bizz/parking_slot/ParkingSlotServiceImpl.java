package com.parkly.backend.bizz.parking_slot;

import com.parkly.backend.repo.ParkingSlotRepository;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.utils.Filter;
import com.parkly.backend.utils.Sort;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;

    @Autowired
    public ParkingSlotServiceImpl(final ParkingSlotRepository parkingSlotRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
    }

    @Override
    public Set<ParkingSlotRest> getAllParkingSlots(Filter filter, Integer page, Sort sort) {
        return Collections.emptySet();
    }

    @Override
    public Optional<ParkingSlotRest> addParkingSlot(final ParkingSlotRest parkingSlotRest) {
        return Optional.empty();
    }

    @Override
    public Optional<ParkingSlotRest> getParkingSlotById(final Long parkingSlotId) {
        return null;
    }

    @Override
    public Optional<ParkingSlotRest> updateParkingSlot(final Long parkingSlotId, final ParkingSlotRest parkingSlotRest) {
        return Optional.empty();
    }

    @Override
    public boolean deleteParkingSlot(final Long parkingSlotId) {
        return false;
    }
}
