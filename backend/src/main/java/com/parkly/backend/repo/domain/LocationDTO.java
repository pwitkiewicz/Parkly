package com.parkly.backend.repo.domain;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "location")
public class LocationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "locationid")
    private long locationId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "streetnumber")
    private String streetNumber;

    @Column(name = "zipcode")
    private String zipCode;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @OneToOne(mappedBy = "location")
    private ParkingSlotDTO parkingSlot;

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
