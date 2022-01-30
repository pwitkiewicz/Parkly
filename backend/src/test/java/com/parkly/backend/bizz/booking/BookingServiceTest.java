package com.parkly.backend.bizz.booking;

import com.parkly.backend.common.CommonMockObjects;
import com.parkly.backend.repo.BookingHistoryRepository;
import com.parkly.backend.repo.domain.BookingHistoryDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.rest.domain.BookingRest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookingServiceTest extends CommonMockObjects
{
    @Autowired
    private BookingService bookingService;

    @MockBean
    private BookingHistoryRepository mockBookingHistoryRepository;

    private Set<BookingHistoryDTO> mockBookingHistoryAll;
    private Set<BookingHistoryDTO> mockBookingHistoryParking1;

    @Before
    public void setUp()
    {
        mockBookingHistoryAll = new HashSet<>();
        setUpBookingHistory();
        Mockito.doReturn(mockBookingHistoryAll).when(mockBookingHistoryRepository).findAll();
    }

    @Test
    public void getAllBookingsPresentParkingSlotIdAndParkingSlotPresent()
    {
        final Long mockParkingSlotId = 1L;
        final Set<BookingRest> retrievedBookingSet = bookingService.getAllBookings(mockParkingSlotId);
        Assert.assertEquals(1L, retrievedBookingSet.size());
        Assert.assertEquals(1L, retrievedBookingSet.iterator().next().getBookingId().longValue());
    }

    @Test
    public void getAllBookingsPresentParkingSlotIdAndParkingSlotNotPresent()
    {
        final Long mockParkingSlotId = 2L;
        Mockito.doReturn(mockBookingHistoryParking1).when(mockBookingHistoryRepository).findAll();
        final Set<BookingRest> retrievedBookingSet = bookingService.getAllBookings(mockParkingSlotId);
        Assert.assertEquals(0L, retrievedBookingSet.size());
    }

    @Test
    public void getAllBookingsWithoutParkingSlotId()
    {
        final Set<BookingRest> retrievedBookingSet = bookingService.getAllBookings(null);
        final Optional<BookingRest> bookingParking2 =
                retrievedBookingSet.stream().filter(bookingRest -> bookingRest.getParkingSlotId() == 2).findFirst();
        Assert.assertEquals(2L, retrievedBookingSet.size());
        Assert.assertNotNull(bookingParking2);
    }

    @Test
    public void getBookingByBookingIdBookingExists()
    {
        final Optional<BookingRest> retrievedBooking = bookingService.getBookingByBookingId(1L);
        Assert.assertTrue(retrievedBooking.isPresent());
        Assert.assertEquals("Test last name DTO 1", retrievedBooking.get().getLastName());
        Assert.assertEquals(1L, retrievedBooking.get().getParkingSlotId().longValue());
    }

    @Test
    public void getBookingByBookingIdBookingDoesNotExist()
    {
        final Optional<BookingRest> retrievedBooking = bookingService.getBookingByBookingId(-1L);
        Assert.assertFalse(retrievedBooking.isPresent());
    }

    @Test
    public void addBookingParkingSlotEmpty()
    {
        final BookingRest mockBookingRest = setUpBookingHistoryRest();
        mockBookingRest.setParkingSlotId(-1L);

        final Optional<BookingRest> retrievedBookingRest = bookingService.addBooking(mockBookingRest);
        Assert.assertFalse(retrievedBookingRest.isPresent());
    }

    private void setUpBookingHistory()
    {
        final ParkingSlotDTO mockParkingSpot1 = setUpParkingSlotDTO();
        final ParkingSlotDTO mockParkingSpot2 = setUpParkingSlotDTO();
        mockParkingSpot2.setParkingSlotId(2L);
        mockBookingHistoryParking1 = new HashSet<>();
        final Set<BookingHistoryDTO> bookingHistoryDtoSet2 = new HashSet<>();

        final BookingHistoryDTO mockBookingHistoryDTO1 = new BookingHistoryDTO();
        mockBookingHistoryDTO1.setBookingId(1);
        mockBookingHistoryDTO1.setIsActive(1);
        mockBookingHistoryDTO1.setLastName(String.format("Test last name DTO %d",1));
        mockBookingHistoryDTO1.setFirstName(String.format("Test first name DTO %d",1));
        mockBookingHistoryDTO1.setStartDate(1640991600L);
        mockBookingHistoryDTO1.setEndDate(1640995200L);
        mockBookingHistoryDTO1.setOwnerId(1);
        mockBookingHistoryDTO1.setParkingSlot(mockParkingSpot1);
        mockBookingHistoryParking1.add(mockBookingHistoryDTO1);

        final BookingHistoryDTO mockBookingHistoryDTO2 = new BookingHistoryDTO();
        mockBookingHistoryDTO2.setBookingId(2);
        mockBookingHistoryDTO2.setIsActive(2);
        mockBookingHistoryDTO2.setLastName(String.format("Test last name DTO %d",2));
        mockBookingHistoryDTO2.setFirstName(String.format("Test first name DTO %d",2));
        mockBookingHistoryDTO2.setStartDate(1641337200L);
        mockBookingHistoryDTO2.setEndDate(1641340800L);
        mockBookingHistoryDTO2.setOwnerId(2);
        mockBookingHistoryDTO2.setParkingSlot(mockParkingSpot2);
        bookingHistoryDtoSet2.add(mockBookingHistoryDTO2);

        mockBookingHistoryAll = Stream.concat(mockBookingHistoryParking1.stream(),bookingHistoryDtoSet2.stream()).collect(Collectors.toSet());
        Mockito.doReturn(Optional.of(mockBookingHistoryDTO1)).when(mockBookingHistoryRepository).findById(1L);
    }

}
