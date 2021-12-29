package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@JsonRootName("parkingSlot")
public class ParkingSlotRest implements Serializable {

    @JsonProperty("id")
    private long parkingSlotId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("startDateTime")
    private long startDate;

    @JsonProperty("endDateTime")
    private long endDate;

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("isDisabledFriendly")
    private boolean isDisabled;

    @JsonProperty("photos")
    private Set<PhotoRest> photoRestSet;

    @JsonProperty("description")
    private String description;

    @JsonProperty("height")
    private double height;

    @JsonProperty("width")
    private double width;

    @JsonProperty("location")
    private LocationRest locationRest;

    @JsonProperty("cost")
    private double cost;

    public long getParkingSlotId() { return parkingSlotId; }

    public void setParkingSlotId(long parkingSlotId) { this.parkingSlotId = parkingSlotId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public long getStartDate() { return startDate; }

    public void setStartDate(long startDate) { this.startDate = startDate; }

    public long getEndDate() { return endDate; }

    public void setEndDate(long endDate) { this.endDate = endDate; }

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) { isActive = active; }

    public boolean isDisabled() { return isDisabled; }

    public void setDisabled(boolean disabled) { isDisabled = disabled; }

    public Set<PhotoRest> getPhotoRestSet() { return photoRestSet; }

    public void setPhotoRestSet(Set<PhotoRest> photoRestSet) { this.photoRestSet = photoRestSet; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getHeight() { return height; }

    public void setHeight(double height) { this.height = height; }

    public double getWidth() { return width; }

    public void setWidth(double width) { this.width = width; }

    public LocationRest getLocationRest() { return locationRest; }

    public void setLocationRest(LocationRest locationRest) { this.locationRest = locationRest; }

    public double getCost() { return cost; }

    public void setCost(double cost) { this.cost = cost; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlotRest that = (ParkingSlotRest) o;
        return parkingSlotId == that.parkingSlotId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingSlotId, name);
    }
}
