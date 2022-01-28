package com.parkly.backend.bizz.owner;

import static com.parkly.backend.mapper.OwnerMapper.mapEntityToRest;
import static com.parkly.backend.mapper.OwnerMapper.mapRestToEntity;

import com.parkly.backend.mapper.OwnerMapper;
import com.parkly.backend.repo.OwnerRepository;
import com.parkly.backend.rest.domain.OwnerRest;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(final OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Optional<OwnerRest> getOwner(final Long ownerId) {
        var owner = ownerRepository.findById(ownerId);

        return owner.map(OwnerMapper::mapEntityToRest);
    }

    @Override
    public Optional<OwnerRest> addOwner(final OwnerRest owner) {
        try {
            var savedOwner = ownerRepository.save(mapRestToEntity(owner));
            return Optional.of(mapEntityToRest(savedOwner));
        } catch (DataAccessException e) {
            log.warn("Error occured while saving owner {} to database", owner, e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<OwnerRest> updateOwner(final Long ownerId, final OwnerRest updatedOwner) {
        try {
            var ownerOptional = ownerRepository.findById(ownerId);

            if (ownerOptional.isPresent()) {
                updatedOwner.setOwnerId(ownerOptional.get().getOwnerId());
                var savedOwner = ownerRepository.save(mapRestToEntity(updatedOwner));

                return Optional.of(mapEntityToRest(savedOwner));
            } else {
                log.warn("Invalid owner id ({}) provided", ownerId);
            }
        } catch (DataAccessException e) {
            log.warn("Error occured while updating owner {} to database", updatedOwner, e);
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteOwner(final Long ownerId) {
        try {
            ownerRepository.deleteById(ownerId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            log.warn("Owner id {} deletion failed!", ownerId, e);
            return false;
        }
    }
}
