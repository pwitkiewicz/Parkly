package com.parkly.backend.mapper;

import com.parkly.backend.repo.domain.OwnerDTO;
import com.parkly.backend.rest.domain.OwnerRest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OwnerMapper {

    public static Optional<OwnerDTO> mapToOwnerDTO(final OwnerRest ownerRest) {
        if(Objects.nonNull(ownerRest)) {
            var entity = new OwnerDTO(
                    ownerRest.getFirstName(),
                    ownerRest.getLastName(),
                    ownerRest.getPhoneNumber());

            if(ownerRest.getOwnerId() != null) {
                entity.setOwnerId(ownerRest.getOwnerId());
            }

            return Optional.of(entity);
        }

        return Optional.empty();
    }

    public static Optional<OwnerRest> mapToOwnerRest(final OwnerDTO ownerDTO) {
        if(Objects.nonNull(ownerDTO)) {
            return Optional.of(new OwnerRest(
                    ownerDTO.getOwnerId(),
                    ownerDTO.getFirstName(),
                    ownerDTO.getLastName(),
                    ownerDTO.getTelephoneNumber(),
                    Collections.emptySet()
            ));
        }

        return Optional.empty();
    }
}
