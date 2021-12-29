package com.parkly.backend.repo.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "parkingslot")
public class ParkingSlotDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long parkingSlotId;

    @Column(name = "name")
    private String name;

    @Column(name = "startdate")
    private long startDate;

    @Column(name = "enddate")
    private long endDate;

    @Column(name = "isactive")
    private int isActive;

    @Column(name = "description")
    private String description;

    @Column(name = "width")
    private double width;

    @Column(name = "height")
    private double height;

    @Column(name = "cost")
    private double cost;

    @Column(name = "isdisabled")
    private int isDisabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "locationid", referencedColumnName = "parkingslotid")
    private LocationDTO location;

    @OneToMany(mappedBy = "parkingSlot")
    private Set<PhotoDTO> photoSet;

    @OneToMany(mappedBy = "parkingSlot")
    private Set<BookingHistoryDTO> bookingHistorySet;

    public long getParkingSlotId() { return parkingSlotId; }

    public void setParkingSlotId(long parkingSlotId) { this.parkingSlotId = parkingSlotId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public long getStartDate() { return startDate; }

    public void setStartDate(long startDate) { this.startDate = startDate; }

    public long getEndDate() { return endDate; }

    public void setEndDate(long endDate) { this.endDate = endDate; }

    public int getIsActive() { return isActive; }

    public void setIsActive(int isActive) { this.isActive = isActive; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getWidth() { return width; }

    public void setWidth(double width) { this.width = width; }

    public double getHeight() { return height; }

    public void setHeight(double height) { this.height = height; }

    public double getCost() { return cost; }

    public void setCost(double cost) { this.cost = cost; }

    public int getIsDisabled() { return isDisabled; }

    public void setIsDisabled(int isDisabled) { this.isDisabled = isDisabled; }

    public LocationDTO getLocation() { return location; }

    public void setLocation(LocationDTO location) { this.location = location; }

    public Set<PhotoDTO> getPhotoSet() { return photoSet; }

    public void setPhotoSet(Set<PhotoDTO> photoSet) { this.photoSet = photoSet; }

    public Set<BookingHistoryDTO> getBookingHistorySet() { return bookingHistorySet; }

    public void setBookingHistorySet(Set<BookingHistoryDTO> bookingHistorySet) { this.bookingHistorySet = bookingHistorySet; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlotDTO that = (ParkingSlotDTO) o;
        return parkingSlotId == that.parkingSlotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingSlotId);
    }
}
