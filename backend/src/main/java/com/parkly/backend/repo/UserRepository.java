package com.parkly.backend.repo;

import com.parkly.backend.repo.domain.UserDTO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserDTO, Long>
{

}