package com.parkly.backend.repo;

import com.parkly.backend.repo.domain.OwnerDTO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends PagingAndSortingRepository<OwnerDTO, Long> {

}
