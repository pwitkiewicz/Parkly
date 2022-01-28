package com.parkly.backend.repo.domain;


import javax.validation.constraints.NotNull;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "bookinghistory")
public class BookingHistoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "booking_id")
    private long bookingId;

    @NotNull
    @Column(name = "start_date")
    private long startDate;

    @NotNull
    @Column(name = "is_active")
    private int isActive;

    @NotNull
    @Column(name = "owner_id")
    private long ownerId;

    @ManyToOne
    @JoinColumn(name = "parking_slot_id", nullable = false)
    private ParkingSlotDTO parkingSlot;

    @ManyToOne
    @JoinColumn(name = "ownerid", nullable = false)
    private OwnerDTO owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookingHistoryDTO that = (BookingHistoryDTO) o;
        return bookingId == that.getBookingId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }
}
