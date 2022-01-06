package com.parkly.backend.bizz.owner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.parkly.backend.config.ServiceTest;
import com.parkly.backend.repo.OwnerRepository;
import com.parkly.backend.repo.domain.OwnerDTO;
import com.parkly.backend.rest.domain.OwnerRest;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.EmptyResultDataAccessException;


@ServiceTest
class OwnerServiceImplTest {

    @Autowired
    @InjectMocks
    private OwnerServiceImpl ownerService;

    @MockBean
    private OwnerRepository repository;

    private OwnerDTO owner;

    @BeforeEach
    void setUp() {
        // given
        owner = new OwnerDTO("Jan", "Kowalski", 48_123_456_789L);
        owner.setOwnerId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(owner));
        when(repository.findById(2137L)).thenReturn(Optional.empty());
        when(repository.save(any())).thenAnswer(i -> i.getArguments()[0]);
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

        when(repository.save(any())).thenAnswer(i -> {
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
        doThrow(new DataAccessResourceFailureException("Failure")).when(repository).save(any());

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

        when(repository.save(any())).thenAnswer(i -> i.getArguments()[0]);

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
        doThrow(new EmptyResultDataAccessException(2137)).when(repository).deleteById(anyLong());

        // when
        boolean result = ownerService.deleteOwner(1L);

        // then
        assertThat(result).isFalse();
    }
}