package com.parkly.backend.repo.domain;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "owner")
public class OwnerDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ownerid")
    private long ownerId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "number")
    private Long phoneNumber;

    @OneToMany
    @JoinColumn(name = "bookingid", nullable = false)
    private Set<BookingHistoryDTO> bookings;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OwnerDTO ownerDTO = (OwnerDTO) o;
        return ownerId == ownerDTO.getOwnerId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId);
    }
}
