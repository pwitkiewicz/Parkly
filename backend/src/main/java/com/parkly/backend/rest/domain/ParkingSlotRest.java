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
    private Long parkingSlotId;

    @EqualsAndHashCode.Include
    @JsonProperty("name")
    private String name;

    @JsonProperty("startDateTime")
    private Long startDate;

    @JsonProperty("endDateTime")
    private Long endDate;

    @JsonProperty("isActive")
    private Boolean isActive;

    @JsonProperty("isDisabledFriendly")
    private Boolean isDisabledFriendly;

    @JsonProperty("photos")
    private Set<PhotoRest> photoRestSet;

    @JsonProperty("description")
    private String description;

    @JsonProperty("height")
    private Double height;

    @JsonProperty("width")
    private Double width;

    @JsonProperty("location")
    private LocationRest locationRest;

    @JsonProperty("cost")
    private Double cost;
}
