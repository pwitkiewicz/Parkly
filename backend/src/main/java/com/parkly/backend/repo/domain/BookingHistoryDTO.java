package com.parkly.backend.repo.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "end_date")
    private long endDate;

    @NotNull
    @Column(name = "is_active")
    private int isActive;

    @NotNull
    @Column(name = "owner_id")
    private long owner;

    @NotNull
    @Column(name = "owner_data")
    private String ownerData;

    @ManyToOne
    @JoinColumn(name = "parking_slot_id", nullable = false)
    private ParkingSlotDTO parkingSlot;

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
