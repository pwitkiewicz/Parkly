package com.parkly.backend.repo;

import com.parkly.backend.repo.domain.LocationDTO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<LocationDTO, Long>
{
    Optional<LocationDTO> findByLatitudeAndLongitude(double latitude, double longitude);
}
