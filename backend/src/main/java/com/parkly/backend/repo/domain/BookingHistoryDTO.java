package com.parkly.backend.repo.domain;

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
    @Column(name = "bookingid")
    private long bookingId;

    @Column(name = "startdate")
    private long startDate;

    @Column(name = "isactive")
    private int isActive;

    @Column(name = "ownerid")
    private long ownerId;

    @ManyToOne
    @JoinColumn(name = "parkingslotid", nullable = false)
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
