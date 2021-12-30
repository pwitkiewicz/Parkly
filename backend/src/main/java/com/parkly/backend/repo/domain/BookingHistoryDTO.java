package com.parkly.backend.repo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bookinghistory")
public class BookingHistoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookingid")
    private long bookingid;

    @Column(name = "startdate")
    private long startDate;

    @Column(name = "isactive")
    private int isActive;

    @Column(name = "ownerid")
    private long ownerId;

    @ManyToOne
    @JoinColumn(name = "parkingslotid", nullable = false)
    private ParkingSlotDTO parkingSlot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingHistoryDTO that = (BookingHistoryDTO) o;
        return bookingid == that.bookingid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingid);
    }
}
