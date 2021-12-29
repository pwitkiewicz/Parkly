package com.parkly.backend.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;

public class LocationRest implements Serializable {

    @JsonProperty("id")
    private long locationId;

    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;

    @JsonProperty("street")
    private String street;

    @JsonProperty("number")
    private String streetNumber;

    @JsonProperty("zipcode")
    private String zipCode;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    public long getLocationId() { return locationId; }

    public void setLocationId(long locationId) { this.locationId = locationId; }

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
        LocationRest that = (LocationRest) o;
        return locationId == that.locationId && Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, latitude, longitude);
    }
}
