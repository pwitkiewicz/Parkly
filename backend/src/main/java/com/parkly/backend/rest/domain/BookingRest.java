package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRest implements Serializable {

    public static final BookingRest EMPTY_BOOKING = new BookingRest();

    @EqualsAndHashCode.Include
    @JsonProperty("id")
    private Long bookingId;

    @JsonProperty("startDateTime")
    private Long startDate;

    @JsonProperty("endDateTime")
    private Long endDate;

    @JsonProperty("isactive")
    private Boolean isActive;

    @JsonProperty("totalCost")
    private Double totalCost;

    @JsonProperty("parkingSlot")
    private ParkingSlotRest parkingSlotRest;

    @JsonProperty("ownerId")
    private Long ownerId;
}
