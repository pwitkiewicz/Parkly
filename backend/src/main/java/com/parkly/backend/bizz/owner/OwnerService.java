package com.parkly.backend.bizz.owner;

import com.parkly.backend.rest.domain.OwnerRest;
import java.util.Optional;

public interface OwnerService {
    Optional<OwnerRest> getOwner(Long ownerId);

    Optional<OwnerRest> addOwner(OwnerRest owner);

    Optional<OwnerRest> updateOwner(Long ownerId, OwnerRest owner);

    boolean deleteOwner(Long ownerId);
}
