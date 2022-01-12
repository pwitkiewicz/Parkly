package com.parkly.backend.bizz.owner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.parkly.backend.repo.OwnerRepository;
import com.parkly.backend.repo.domain.OwnerDTO;
import com.parkly.backend.rest.domain.OwnerRest;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.EmptyResultDataAccessException;


@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {

    @InjectMocks
    private OwnerServiceImpl ownerService;

    @Mock
    private OwnerRepository repository;
    private OwnerDTO owner;

    @BeforeEach
    void setUp() {
        // given
        owner = new OwnerDTO("Jan", "Kowalski", 48_123_456_789L);
        owner.setOwnerId(1L);

        Mockito.lenient().when(repository.findById(1L)).thenReturn(Optional.of(owner));
        Mockito.lenient().when(repository.findById(2137L)).thenReturn(Optional.empty());
        Mockito.lenient().when(repository.save(any())).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    void getCorrectOwnerRestWhenCorrectIdProvided() {
        // when
        final var optional = ownerService.getOwner(1L);

        // then
        assertThat(optional).isPresent();

        final var result = optional.get();
        assertThat(result.getOwnerId()).isEqualTo(1L);
        assertThat(result.getFirstName()).isEqualTo("Jan");
        assertThat(result.getLastName()).isEqualTo("Kowalski");
        assertThat(result.getPhoneNumber()).isEqualTo(48_123_456_789L);
        assertThat(result.getBookings()).isEmpty();
    }

    @Test
    void getEmptyOptionalWhenIncorrectIdProvided() {
        // when
        final var optional = ownerService.getOwner(2137L);

        // then
        assertThat(optional).isEmpty();
    }

    @Test
    void getNewOwnerWhenCorrectOwnerRestIsProvided() {
        // given
        final var newOwner = new OwnerRest(
            null,
            "Jan",
            "Kowalski",
            48_123_456_789L,
            Collections.emptySet());

        Mockito.lenient().when(repository.save(any())).thenAnswer(i -> {
            var temp = (OwnerDTO) i.getArguments()[0];
            temp.setOwnerId(1L);
            return temp;
        });

        // when
        var optional = ownerService.addOwner(newOwner);

        // then
        assertThat(optional).isPresent();

        var ownerRest = optional.get();
        assertThat(ownerRest.getOwnerId()).isEqualTo(owner.getOwnerId());
        assertThat(ownerRest.getFirstName()).isEqualTo(owner.getFirstName());
        assertThat(ownerRest.getLastName()).isEqualTo(owner.getLastName());
        assertThat(ownerRest.getPhoneNumber()).isEqualTo(owner.getPhoneNumber());
    }

    @Test
    void getEmptyOptionalWhenIncorrectOwnerIsGiven() {
        // given
        final var newOwner = new OwnerRest();
        Mockito.lenient().doThrow(new DataAccessResourceFailureException("Failure")).when(repository).save(any());

        // when
        var optional = ownerService.addOwner(newOwner);

        // then
        assertThat(optional).isEmpty();
    }

    @Test
    void updatePhoneNumberWhenCorrectOwnerIdAndOwnerRestIsProvided() {
        // given
        final var updatedOwner = new OwnerRest(
            null,
            "Jan",
            "Kowalski",
            48_987_654_321L,
            Collections.emptySet());

        Mockito.lenient().when(repository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        // when
        final var optional = ownerService.updateOwner(1L, updatedOwner);

        // then
        assertThat(optional).isPresent();

        final var result = optional.get();
        assertThat(result.getOwnerId()).isEqualTo(1L);
        assertThat(result.getFirstName()).isEqualTo("Jan");
        assertThat(result.getLastName()).isEqualTo("Kowalski");
        assertThat(result.getPhoneNumber()).isEqualTo(48_987_654_321L);
    }

    @Test
    void getEmptyOptional_whenGivenWrongId()
    {
        // given
        final var updatedOwner = new OwnerRest(
            null,
            "Jan",
            "Kowalski",
            48_987_654_321L,
            Collections.emptySet());

        // when
        final var optional = ownerService.updateOwner(2137L, updatedOwner);

        // then
        assertThat(optional).isEmpty();
    }

    @Test
    void getTrueWhenCorrectIdGiven()
    {
        // when
        boolean result = ownerService.deleteOwner(anyLong());

        // then
        assertThat(result).isTrue();
    }

    @Test
    void getFalseWhenIncorrectIdGiven()
    {
        // given
        Mockito.lenient().doThrow(new EmptyResultDataAccessException(2137)).when(repository).deleteById(anyLong());

        // when
        boolean result = ownerService.deleteOwner(1L);

        // then
        assertThat(result).isFalse();
    }
}