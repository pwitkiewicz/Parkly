package com.parkly.backend.repo;

import com.parkly.backend.repo.domain.PhotoDTO;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhotoRepository extends PagingAndSortingRepository<PhotoDTO, Long> {

}
