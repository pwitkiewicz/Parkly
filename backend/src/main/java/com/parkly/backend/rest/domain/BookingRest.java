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
    private Long bookingid;

    @JsonProperty("startDateTime")
    private Long startDate;

    @JsonProperty("isactive")
    private Boolean isActive;

    @JsonProperty("totalCost")
    private Long totalCost;

    @JsonProperty("parkingSlot")
    private ParkingSlotRest parkingSlotRest;

    @JsonProperty("customerid")
    private Long customerId;
}
