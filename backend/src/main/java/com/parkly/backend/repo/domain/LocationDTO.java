package com.parkly.backend.repo.domain;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "location")
public class LocationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    public long getLocationId() { return locationId; }

    public void setLocationId(long locationId) { this.locationId = locationId; }

    public ParkingSlotDTO getParkingSlot() { return parkingSlot; }

    public void setParkingSlot(ParkingSlotDTO parkingSlot) { this.parkingSlot = parkingSlot; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getStreetNumber() { return streetNumber; }

    public void setStreetNumber(String streetNumber) { this.streetNumber = streetNumber; }

    public String getZipCode() { return zipCode; }

    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public double getLatitude() { return latitude; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }

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
