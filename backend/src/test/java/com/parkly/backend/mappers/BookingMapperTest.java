package com.parkly.backend.mappers;

import com.parkly.backend.common.CommonMockObjects;
import com.parkly.backend.mapper.BookingMapper;
import com.parkly.backend.repo.domain.BookingHistoryDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.rest.domain.BookingRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Optional;

public class BookingMapperTest extends CommonMockObjects
{

    private BookingHistoryDTO mockBookingHistoryDTO;
    private BookingRest mockBookingRest;

    @Before
    public void setUp()
    {
        mockBookingHistoryDTO = setUpBookingHistoryDTO();
        mockBookingRest = setUpBookingHistoryRest();
    }

    @Test
    public void mapToBookingRestNonEmptyBookingDTOAndParking()
    {
        final Optional<BookingRest> retrievedBooking = BookingMapper.mapToBookingRest(mockBookingHistoryDTO);
        Assert.assertTrue(retrievedBooking.isPresent());
        Assert.assertEquals(1L, retrievedBooking.get().getBookingId().longValue());
        Assert.assertEquals(100D, retrievedBooking.get().getTotalCost(),0);
    }

    @Test
    public void mapToBookingRestEmptyBookingDTO()
    {
        final Optional<BookingRest> retrievedBooking = BookingMapper.mapToBookingRest(null);
        Assert.assertFalse(retrievedBooking.isPresent());
    }

    @Test
    public void mapToBookingRestEmptyParking()
    {
        mockBookingHistoryDTO.setParkingSlot(null);
        final Optional<BookingRest> retrievedBooking = BookingMapper.mapToBookingRest(mockBookingHistoryDTO);
        Assert.assertFalse(retrievedBooking.isPresent());
    }

    @Test
    public void mapToBookingHistoryDTONonEmptyBookingRestAndParking()
    {
        final ParkingSlotDTO parkingSlotDTO = setUpParkingSlotDTO();
        final Optional<BookingHistoryDTO> retrievedBooking = BookingMapper.mapToBookingHistoryDTO(mockBookingRest, parkingSlotDTO);
        Assert.assertTrue(retrievedBooking.isPresent());
        Assert.assertEquals("Test first name REST", retrievedBooking.get().getFirstName());
        Assert.assertEquals(0, retrievedBooking.get().getIsActive());
    }

    @Test
    public void mapToBookingHistoryDTOEmptyBookingRest()
    {
        final ParkingSlotDTO parkingSlotDTO = setUpParkingSlotDTO();
        final Optional<BookingHistoryDTO> retrievedBooking = BookingMapper.mapToBookingHistoryDTO(null, setUpParkingSlotDTO());
        Assert.assertFalse(retrievedBooking.isPresent());
    }

    @Test
    public void mapToBookingHistoryDTOEmptyParking()
    {
        final Optional<BookingHistoryDTO> retrievedBooking =
                BookingMapper.mapToBookingHistoryDTO(mockBookingRest, null);
        Assert.assertFalse(retrievedBooking.isPresent());
    }
}
