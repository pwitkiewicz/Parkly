package com.parkly.backend.repo.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "photo")
public class PhotoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    private long photoId;

    @NotNull
    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "parking_slot_id", nullable = false)
    private ParkingSlotDTO parkingSlot;

    public PhotoDTO(final String path, final ParkingSlotDTO parkingSlot) {
        this.path = path;
        this.parkingSlot = parkingSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoDTO photoDTO = (PhotoDTO) o;
        return photoId == photoDTO.photoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoId);
    }
}
