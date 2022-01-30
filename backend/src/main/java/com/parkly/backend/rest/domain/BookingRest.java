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
    private String startDate;

    @JsonProperty("endDateTime")
    private String endDate;

    @JsonProperty("active")
    private Boolean isActive;

    @JsonProperty("totalCost")
    private Double totalCost;

    @JsonProperty("parkingSlot")
    private Long parkingSlotId;

    @JsonProperty("ownerId")
    private long ownerId;
    
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;
}
