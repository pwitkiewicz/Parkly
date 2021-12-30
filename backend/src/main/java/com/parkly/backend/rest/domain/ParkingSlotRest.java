package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonRootName("parkingSlot")
public class ParkingSlotRest implements Serializable {

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
}
