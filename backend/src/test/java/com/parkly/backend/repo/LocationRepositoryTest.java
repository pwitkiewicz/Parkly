package com.parkly.backend.repo;


import com.parkly.backend.common.AbstractTestConfig;
import com.parkly.backend.repo.domain.LocationDTO;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class LocationRepositoryTest extends AbstractTestConfig {

    @Autowired
    private LocationRepository locationRepository;

    private LocationDTO mockLocation;

    @Before
    public void setUp()
    {
        setUpLocationDtoMock();
    }

    @Test
    public void saveLocationNonEmpty()
    {
        mockLocation.setCity("Test City");

        final LocationDTO savedLocation = locationRepository.save(mockLocation);
        Assertions.assertThat(savedLocation).usingRecursiveComparison().ignoringFields("id").isEqualTo(savedLocation);
        assertThat(savedLocation.getLocationId()).isEqualTo(1);
    }

    @Test
    public void saveLocationEmptyLocation()
    {
        final LocationDTO mockEmptyLocation = new LocationDTO();

        assertThatThrownBy(() -> locationRepository.save(mockEmptyLocation))
                .isInstanceOf(TransactionSystemException.class)
                .hasRootCauseInstanceOf(ConstraintViolationException.class);
    }


    private void setUpLocationDtoMock()
    {
        mockLocation = new LocationDTO();
        mockLocation.setLatitude(0L);
        mockLocation.setLongitude(0L);
        mockLocation.setZipCode("00-000");
        mockLocation.setCountry("Test Country");
        mockLocation.setStreet("Test Street");
        mockLocation.setStreetNumber("0A");
    }
}
