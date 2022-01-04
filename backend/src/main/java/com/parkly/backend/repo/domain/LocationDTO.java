package com.parkly.backend.repo.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "location")
public class LocationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "locationid")
    private long locationId;

    @NotNull
    @Column(name = "country")
    private String country;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "streetnumber")
    private String streetNumber;

    @NotNull
    @Column(name = "zipcode")
    private String zipCode;

    @NotNull
    @Column(name = "latitude")
    private double latitude;

    @NotNull
    @Column(name = "longitude")
    private double longitude;

    @OneToMany(mappedBy = "location")
    private Set<ParkingSlotDTO> parkingSlot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationDTO that = (LocationDTO) o;
        return locationId == that.locationId && Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, latitude, longitude);
    }
}
