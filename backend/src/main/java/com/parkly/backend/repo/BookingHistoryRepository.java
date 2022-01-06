package com.parkly.backend.repo;

import com.parkly.backend.repo.domain.BookingHistoryDTO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingHistoryRepository extends PagingAndSortingRepository<BookingHistoryDTO, Long>
{

}
