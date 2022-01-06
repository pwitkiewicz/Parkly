package com.parkly.backend.mapper;

import com.parkly.backend.bizz.booking.BookingService;
import com.parkly.backend.repo.domain.OwnerDTO;
import com.parkly.backend.rest.domain.OwnerRest;
import java.util.Collections;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {

    @Autowired
    private BookingService bookingService;

    public static OwnerDTO mapRestToEntity(@NotNull final OwnerRest ownerRest) {
        var entity = new OwnerDTO(
            ownerRest.getFirstName(),
            ownerRest.getLastName(),
            ownerRest.getPhoneNumber());

        if(ownerRest.getOwnerId() != null) {
            entity.setOwnerId(ownerRest.getOwnerId());
        }

        return entity;
    }

    public static OwnerRest mapEntityToRest(@NotNull final OwnerDTO ownerDTO) {
        return new OwnerRest(
            ownerDTO.getOwnerId(),
            ownerDTO.getFirstName(),
            ownerDTO.getLastName(),
            ownerDTO.getPhoneNumber(),
            //TODO replace next line with bookingService.getAllBookingsForOwnerId when service implemented
            Collections.emptySet()
        );
    }
}
