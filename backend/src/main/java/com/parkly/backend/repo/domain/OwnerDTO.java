package com.parkly.backend.repo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "owner")
public class OwnerDTO {

    public OwnerDTO(@NotNull final String firstName,
                     @NotNull final String lastName,
                     @NotNull final Long telephoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
    }

    @Id
    @Column(name = "owner_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ownerId;

    @Column(name = "first_name", updatable = false)
    private String firstName;

    @Column(name = "last_name", updatable = false)
    private String lastName;

    @Column(name = "tel_number")
    private Long telephoneNumber;

    @OneToMany
    @JoinColumn(name = "booking_id", nullable = false)
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
        return Objects.equals(ownerId, ownerDTO.getOwnerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId);
    }
}
