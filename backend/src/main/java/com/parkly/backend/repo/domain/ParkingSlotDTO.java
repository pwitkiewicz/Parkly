package com.parkly.backend.repo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "startdate")
    private long startDate;

    @NotNull
    @Column(name = "enddate")
    private long endDate;

    @NotNull
    @Column(name = "isactive")
    private int isActive;

    @Column(name = "description")
    private String description;

    @Column(name = "width")
    private double width;

    @Column(name = "height")
    private double height;

    @NotNull
    @Column(name = "cost")
    private double cost;

    @NotNull
    @Column(name = "isdisabled")
    private int isDisabled;

    @ManyToOne
    @JoinColumn(name = "locationid", nullable = false)
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
