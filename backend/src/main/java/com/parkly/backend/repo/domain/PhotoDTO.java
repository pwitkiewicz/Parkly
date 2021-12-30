package com.parkly.backend.repo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "photo")
public class PhotoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photoid")
    private long photoId;

    @Column(name = "path")
    private String path;

    @ManyToMany
    @JoinColumn(name = "parkingslotid", nullable = false)
    private ParkingSlotDTO parkingSlot;

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
