package com.parkly.backend.repo;

import com.parkly.backend.repo.domain.BookingHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistoryDTO, Long>
{

}
