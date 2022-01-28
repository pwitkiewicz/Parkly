package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRest implements Serializable {

    @EqualsAndHashCode.Include
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
}
