package com.parkly.backend.bizz.owner;

import com.parkly.backend.mapper.BookingMapper;
import com.parkly.backend.repo.BookingHistoryRepository;
import com.parkly.backend.repo.OwnerRepository;
import com.parkly.backend.repo.domain.OwnerDTO;
import com.parkly.backend.rest.domain.BookingRest;
import com.parkly.backend.rest.domain.OwnerRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.parkly.backend.mapper.OwnerMapper.mapToOwnerDTO;
import static com.parkly.backend.mapper.OwnerMapper.mapToOwnerRest;


@Slf4j
@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final BookingHistoryRepository bookingHistoryRepository;

    @Autowired
    public OwnerServiceImpl(final OwnerRepository ownerRepository, final BookingHistoryRepository bookingHistoryRepository) {
        this.ownerRepository = ownerRepository;
        this.bookingHistoryRepository = bookingHistoryRepository;
    }

    @Override
    public Optional<OwnerRest> getOwner(final Long ownerId) {
        var owner = ownerRepository.findById(ownerId);

        if(owner.isPresent()) {
            var ownerRest = mapToOwnerRest(owner.get());

            if(ownerRest.isPresent()) {
                ownerRest.get().setBookings(getBookingsForOwner(owner.get()));
                return ownerRest;
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<OwnerRest> addOwner(final OwnerRest owner) {
        try {
            var ownerDtoOptional = mapToOwnerDTO(owner);
            if(ownerDtoOptional.isPresent()) {
                var savedOwner = ownerRepository.save(ownerDtoOptional.get());
                return mapToOwnerRest(savedOwner);
            }
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
                var mappedOwner = mapToOwnerDTO(updatedOwner);

                if(mappedOwner.isPresent()) {
                    var savedOwner = ownerRepository.save(mappedOwner.get());
                    var ownerRest = mapToOwnerRest(savedOwner);

                    if(ownerRest.isPresent()) {
                        ownerRest.get().setBookings(getBookingsForOwner(savedOwner));
                        return ownerRest;
                    }
                }
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

    private Set<BookingRest> getBookingsForOwner(final OwnerDTO ownerDTO) {
        var bookings = bookingHistoryRepository.findBookingHistoryDTOSByOwner(ownerDTO);

        return bookings.stream()
                .map(BookingMapper::mapToBookingRest)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}
