package com.parkly.backend.common;

import com.parkly.backend.repo.domain.BookingHistoryDTO;
import com.parkly.backend.repo.domain.LocationDTO;
import com.parkly.backend.repo.domain.ParkingSlotDTO;
import com.parkly.backend.repo.domain.PhotoDTO;
import com.parkly.backend.rest.domain.BookingRest;
import com.parkly.backend.rest.domain.LocationRest;
import com.parkly.backend.rest.domain.ParkingSlotRest;
import com.parkly.backend.rest.domain.PhotoRest;

import java.util.HashSet;
import java.util.Set;

public class CommonMockObjects extends AbstractTestConfig
{
    protected LocationDTO setUpLocationDTO()
    {
        final LocationDTO mockLocationDTO = new LocationDTO();
        mockLocationDTO.setLocationId(1L);
        mockLocationDTO.setCity("Test City DTO");
        mockLocationDTO.setCountry("Test Country DTO");
        mockLocationDTO.setStreet("Test Street DTO");
        mockLocationDTO.setStreetNumber("DTO");
        mockLocationDTO.setZipCode("00-000");
        return mockLocationDTO;
    }

    protected LocationRest setUpLocationRest()
    {
        final LocationRest mockLocationRest = new LocationRest();
        mockLocationRest.setCity("Test City REST");
        mockLocationRest.setCountry("Test Country REST");
        mockLocationRest.setStreet("Test Street REST");
        mockLocationRest.setStreetNumber("REST");
        mockLocationRest.setZipCode("00-000");
        return mockLocationRest;
    }

    protected ParkingSlotDTO setUpParkingSlotDTO()
    {
        final ParkingSlotDTO mockParkingSlotDTO = new ParkingSlotDTO();
        mockParkingSlotDTO.setParkingSlotId(1L);
        mockParkingSlotDTO.setCost(100D);
        mockParkingSlotDTO.setEndDate(1609455600L);
        mockParkingSlotDTO.setStartDate(1577833200L);
        mockParkingSlotDTO.setName("Test Parking Slot DTO");
        mockParkingSlotDTO.setIsDisabled(1);
        mockParkingSlotDTO.setIsActive(0);
        mockParkingSlotDTO.setLocation(setUpLocationDTO());
        return mockParkingSlotDTO;
    }

    protected ParkingSlotRest setUpParkingSlotRest()
    {
        final ParkingSlotRest mockParkingSlotRest = new ParkingSlotRest();
        mockParkingSlotRest.setCost(100D);
        mockParkingSlotRest.setEndDate(1609455600L);
        mockParkingSlotRest.setStartDate(1577833200L);
        mockParkingSlotRest.setName("Test Parking Slot REST");
        mockParkingSlotRest.setIsDisabledFriendly(true);
        mockParkingSlotRest.setIsActive(true);
        mockParkingSlotRest.setLocationRest(setUpLocationRest());
        return mockParkingSlotRest;
    }

    protected BookingHistoryDTO setUpBookingHistoryDTO()
    {
        final BookingHistoryDTO mockBookingHistoryDTO = new BookingHistoryDTO();
        mockBookingHistoryDTO.setBookingId(1);
        mockBookingHistoryDTO.setIsActive(1);
        mockBookingHistoryDTO.setLastName("Test last name DTO");
        mockBookingHistoryDTO.setFirstName("Test first name DTO");
        mockBookingHistoryDTO.setStartDate(1641340800L);
        mockBookingHistoryDTO.setEndDate(1641344400L);
        mockBookingHistoryDTO.setOwnerId(1);
        mockBookingHistoryDTO.setParkingSlot(setUpParkingSlotDTO());
        return mockBookingHistoryDTO;
    }

    protected BookingRest setUpBookingHistoryRest()
    {
        final BookingRest mockBookingHistoryRest = new BookingRest();
        mockBookingHistoryRest.setBookingId(1L);
        mockBookingHistoryRest.setIsActive(false);
        mockBookingHistoryRest.setLastName("Test last name REST");
        mockBookingHistoryRest.setFirstName("Test first name REST");
        mockBookingHistoryRest.setStartDate(1641340800L);
        mockBookingHistoryRest.setEndDate(1641344400L);
        mockBookingHistoryRest.setOwnerId(1);
        mockBookingHistoryRest.setParkingSlotId(1L);
        return mockBookingHistoryRest;
    }

    protected PhotoDTO setUpPhotoDTO()
    {
        final PhotoDTO mockPhotoDTO = new PhotoDTO();
        mockPhotoDTO.setPhotoId(1L);
        mockPhotoDTO.setPath("Test photo path DTO");
        return mockPhotoDTO;
    }

    protected PhotoRest setUpPhotoRest()
    {
        return PhotoRest.of(1L, "Test photo path REST");
    }

    protected Set<PhotoDTO> setUpPhotoDTOSet()
    {
        final Set<PhotoDTO> mockPhotoDTOSet = new HashSet<>();
        final ParkingSlotDTO mockParkingSlotDTO = setUpParkingSlotDTO();

        for(int i = 0; i < 2; i++)
        {
            final PhotoDTO mockPhotoDTO = new PhotoDTO();
            mockPhotoDTO.setPhotoId(i);
            mockPhotoDTO.setPath(String.format("Test photo path DTO %d",i));
            mockPhotoDTO.setParkingSlot(mockParkingSlotDTO);
            mockPhotoDTOSet.add(mockPhotoDTO);
        }
        return mockPhotoDTOSet;
    }
}
