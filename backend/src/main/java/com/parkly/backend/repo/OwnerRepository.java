package com.parkly.backend.repo;

import com.parkly.backend.repo.domain.OwnerDTO;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OwnerRepository extends PagingAndSortingRepository<Long, OwnerDTO> {

}
