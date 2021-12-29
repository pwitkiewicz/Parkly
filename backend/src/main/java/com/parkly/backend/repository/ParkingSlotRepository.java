package com.parkly.backend.repository;

import com.parkly.backend.models.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

}
