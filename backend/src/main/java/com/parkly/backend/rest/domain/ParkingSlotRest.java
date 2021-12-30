package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParkingSlotRest implements Serializable {

    public static final ParkingSlotRest EMPTY_SLOT = new ParkingSlotRest();

    @EqualsAndHashCode.Include
    @JsonProperty("id")
    private long parkingSlotId;

    @EqualsAndHashCode.Include
    @JsonProperty("name")
    private String name;

    @JsonProperty("startDateTime")
    private long startDate;

    @JsonProperty("endDateTime")
    private long endDate;

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("isDisabledFriendly")
    private boolean isDisabledFriendly;

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
}
