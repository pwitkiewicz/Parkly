package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OwnerRest {

    public static final OwnerRest EMPTY_OWNER = new OwnerRest();

    @JsonProperty("id")
    @EqualsAndHashCode.Include
    private Long ownerId;

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("phoneNumber")
    private Long phoneNumber;

    @JsonProperty("bookings")
    private Set<BookingRest> bookings;
}
