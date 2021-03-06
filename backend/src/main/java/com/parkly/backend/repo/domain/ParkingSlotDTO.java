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
    @Column(name = "parking_slot_id")
    private long parkingSlotId;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "start_date")
    private long startDate;

    @NotNull
    @Column(name = "end_date")
    private long endDate;

    @NotNull
    @Column(name = "is_active")
    private int isActive;

    @Column(name = "description")
    private String description;

    @Column(name = "width")
    private Double width;

    @Column(name = "height")
    private Double height;

    @NotNull
    @Column(name = "cost")
    private double cost;

    @NotNull
    @Column(name = "is_disabled")
    private int isDisabled;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
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
