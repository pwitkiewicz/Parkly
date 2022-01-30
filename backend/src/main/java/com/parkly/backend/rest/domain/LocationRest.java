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
public class LocationRest implements Serializable {

    @JsonProperty("id")
    private long locationId;

    @EqualsAndHashCode.Include
    @JsonProperty("country")
    private String country;

    @EqualsAndHashCode.Include
    @JsonProperty("city")
    private String city;

    @EqualsAndHashCode.Include
    @JsonProperty("street")
    private String street;

    @EqualsAndHashCode.Include
    @JsonProperty("number")
    private String streetNumber;

    @JsonProperty("zipcode")
    private String zipCode;
}
