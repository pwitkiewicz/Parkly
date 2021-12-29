package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class BookingRest implements Serializable {

    @JsonProperty("id")
    private long bookingid;

    @JsonProperty("startDateTime")
    private long startDate;

    @JsonProperty("isactive")
    private int isActive;

    @JsonProperty("totalCost")
    private long totalCost;

    @JsonProperty("parkingSlot")
    private ParkingSlotRest parkingSlotRest;

    @JsonProperty("customerid")
    private long customerId;

    public long getBookingid() { return bookingid; }

    public void setBookingid(long bookingid) { this.bookingid = bookingid; }

    public long getStartDate() { return startDate; }

    public void setStartDate(long startDate) { this.startDate = startDate; }

    public int getIsActive() { return isActive; }

    public void setIsActive(int isActive) { this.isActive = isActive; }

    public long getTotalCost() { return totalCost; }

    public void setTotalCost(long totalCost) { this.totalCost = totalCost; }

    public ParkingSlotRest getParkingSlotRest() { return parkingSlotRest; }

    public void setParkingSlotRest(ParkingSlotRest parkingSlotRest) { this.parkingSlotRest = parkingSlotRest; }

    public long getCustomerId() { return customerId; }

    public void setCustomerId(long customerId) { this.customerId = customerId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingRest that = (BookingRest) o;
        return bookingid == that.bookingid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingid);
    }
}
