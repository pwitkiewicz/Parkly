package com.parkly.backend.repo;

import com.parkly.backend.repo.domain.PhotoDTO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends PagingAndSortingRepository<PhotoDTO, Long>
{
    Optional<PhotoDTO> findByPath(String path);
}
