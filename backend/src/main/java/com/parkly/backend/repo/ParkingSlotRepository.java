package com.parkly.backend.repo;

import com.parkly.backend.repo.domain.ParkingSlotDTO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParkingSlotRepository extends PagingAndSortingRepository<ParkingSlotDTO, Long>
{

}

