package com.parkly.backend.repo;

import com.parkly.backend.repo.domain.BookingHistoryDTO;
import com.parkly.backend.repo.domain.OwnerDTO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingHistoryRepository extends PagingAndSortingRepository<BookingHistoryDTO, Long>
{
    List<BookingHistoryDTO> findBookingHistoryDTOSByOwner(OwnerDTO ownerDTO);
}
