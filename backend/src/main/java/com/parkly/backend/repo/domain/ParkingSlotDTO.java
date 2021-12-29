package com.parkly.backend.repo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "parkingslot")
public class ParkingSlotDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parkingslotId")
    private long parkingSlotId;

    @Column(name = "name")
    private String name;

    @Column(name = "startdate")
    private long startDate;

    @Column(name = "enddate")
    private long endDate;

    @Column(name = "isactive")
    private int isActive;

    @Column(name = "description")
    private String description;

    @Column(name = "width")
    private double width;

    @Column(name = "height")
    private double height;

    @Column(name = "cost")
    private double cost;

    @Column(name = "isdisabled")
    private int isDisabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "locationid", referencedColumnName = "parkingslotid")
    private LocationDTO location;

    @OneToMany(mappedBy = "parkingSlot")
    private Set<PhotoDTO> photoSet;

    @OneToMany(mappedBy = "parkingSlot")
    private Set<BookingHistoryDTO> bookingHistorySet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlotDTO that = (ParkingSlotDTO) o;
        return parkingSlotId == that.parkingSlotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingSlotId);
    }
}
