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
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "owner")
public class OwnerDTO {

    public OwnerDTO(@NotNull final String firstName,
                     @NotNull final String lastName,
                     @NotNull final Long phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Id
    @Column(name = "ownerid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ownerId;

    @Column(name = "firstname", updatable = false)
    private String firstName;

    @Column(name = "lastname", updatable = false)
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
        return Objects.equals(ownerId, ownerDTO.getOwnerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId);
    }
}
