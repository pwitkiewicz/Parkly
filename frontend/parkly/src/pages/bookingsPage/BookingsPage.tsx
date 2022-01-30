import React, {useEffect, useState} from 'react';
import {CircularProgress, Grid} from "@mui/material";
import styled from "@emotion/styled";

import Header from "../../components/header/Header";
import {Booking} from '../../models/models';
import BookingItem from '../../components/bookingItem/BookingItem';
import {getAllBookings} from '../../queries/queries';

const BookingsPage = () => {

    const [isFetching, setIsFetching] = useState<boolean>(true);
    const [bookings, setBookings] = useState<Booking[]>();

    const getBookings = async () => {
        const bookings = await getAllBookings();
        setBookings(bookings);
    }
    useEffect(() => {
        getBookings().then(() => {
            setIsFetching(false)
        });
    }, [])

    return (
        <>
            <Header/>
            <StyledGrid container justifyContent="space-around">
                {isFetching &&
                    <CircularProgress/>
                }
                {bookings && bookings.map((booking: Booking) => (
                    <BookingItem id={booking.id}
                                 startDateTime={booking.startDateTime} endDateTime={booking.endDateTime}
                                 active={booking.active} ownerId={booking.ownerId} getBookings={getBookings}
                                 parkingSlot={booking.parkingSlot} firstName={booking.firstName}
                                 lastName={booking.lastName} key={booking.id}/>
                ))}
            </StyledGrid>
        </>
    )
}

const StyledGrid = styled(Grid)`
  margin-top: 4vh;
`

export default BookingsPage;