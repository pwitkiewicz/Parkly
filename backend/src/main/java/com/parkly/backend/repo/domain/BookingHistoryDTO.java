package com.parkly.backend.repo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bookinghistory")
public class BookingHistoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookingid;

    @Column(name = "startdate")
    private long startDate;

    @Column(name = "isactive")
    private int isActive;

    @Column(name = "ownerid")
    private long ownerId;

    @ManyToMany
    @JoinColumn(name = "parkingslotid", nullable = false)
    private ParkingSlotDTO parkingSlot;

    public long getBookingid() { return bookingid; }

    public void setBookingid(long bookingid) { this.bookingid = bookingid; }

    public long getStartDate() { return startDate; }

    public void setStartDate(long startDate) { this.startDate = startDate; }

    public int getIsActive() { return isActive; }

    public void setIsActive(int isActive) { this.isActive = isActive; }

    public long getOwnerId() { return ownerId; }

    public void setOwnerId(long ownerId) { this.ownerId = ownerId; }

    public ParkingSlotDTO getParkingSlot() { return parkingSlot; }

    public void setParkingSlot(ParkingSlotDTO parkingSlot) { this.parkingSlot = parkingSlot; }

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
