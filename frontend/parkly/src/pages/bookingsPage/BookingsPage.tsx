import React, {useEffect, useState} from 'react';
import {CircularProgress, Grid,Pagination} from "@mui/material";
import styled from "@emotion/styled";

import Header from "../../components/header/Header";
import {Booking} from '../../models/models';
import BookingItem from '../../components/bookingItem/BookingItem';
import {getAllBookings,getPagination} from '../../queries/queries';

const BookingsPage = () => {
    const [pagesNumber, setPagesNumber] = useState<number>(10);
    const [isFetching, setIsFetching] = useState<boolean>(true);
    const [bookings, setBookings] = useState<Booking[]>();

    const getBookings = async (page?: number) => {
        const bookings = await getAllBookings(page);
        setBookings(bookings);
    }
    const getPaginationWrapper = async () => {
        const data = await getPagination();
        setPagesNumber(data);
   }

   const handlePageChange = async (event: any, value: number) => {
       setIsFetching(true);
       getBookings(value).then(() => {
           setIsFetching(false);
       })
   }


    useEffect(() => {
        getBookings().then(() => {
            setIsFetching(false)
        });
    }, [])
    useEffect(() => {
        getPaginationWrapper();
    }, [bookings])

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
            <PaginationContainer>
                <Pagination count={pagesNumber} color="primary" onChange={handlePageChange}/>
            </PaginationContainer>
        </>
    )
}

const StyledGrid = styled(Grid)`
  margin-top: 4vh;
`
const PaginationContainer = styled.div`
    width: 100%;
    display: flex;
    justify-content: center;
    padding-top: 20px;
    padding-bottom: 20px;
`

export default BookingsPage;