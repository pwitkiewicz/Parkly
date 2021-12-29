package com.parkly.backend.repo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photo")
public class PhotoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long photoId;

    @Column(name = "path")
    private String path;

    @ManyToMany
    @JoinColumn(name = "parkingslotid", nullable = false)
    private ParkingSlotDTO parkingSlot;

    public long getPhotoId() { return photoId; }

    public void setPhotoId(long photoId) { this.photoId = photoId; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public ParkingSlotDTO getParkingSlot() { return parkingSlot; }

    public void setParkingSlot(ParkingSlotDTO parkingSlot) { this.parkingSlot = parkingSlot; }

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
