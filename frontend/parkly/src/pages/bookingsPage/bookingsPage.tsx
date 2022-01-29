import React, {useEffect, useState} from 'react';
import {Button, CircularProgress, FormControl, Grid, InputLabel, MenuItem, Select, TextField} from "@mui/material";
import styled from "@emotion/styled";
import Header from "../../components/header/Header";
import {getAllParkingSpots} from "../../queries/queries";
import {Booking} from '../../models/models';
import BookingItem from '../../components/bookingItem/BookingItem';
import { getAllBookings } from '../../queries/queries';
//import ParkingSpotModal from "./components/ParkingSpotModal";

// type ParkingSpotModel = {
//     isVisible: boolean;
// }

const BookingsPage = () => {

    const [isFetching, setIsFetching] = useState<boolean>(true);
    const [bookings, setBookings] = useState<Booking[]>();
  //  const [addParkingSpotState, setBookingState] = useState<BookingModel>({
   //     isVisible: false
   // });

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
                {bookings !== undefined && bookings.map((booking: Booking) => (
                    <BookingItem id={booking.id} 
                                     startDateTime={booking.startDateTime} endDateTime={booking.endDateTime}
                                     isActive={booking.isActive} ownerId={booking.ownerId} parkingSlotId={booking.parkingSlotId}/>
                ))}
            </StyledGrid>
        </>
    )
}

const StyledGrid = styled(Grid)`
  margin-top: 4vh;
`

const StyledTextField = styled(TextField)`
  min-width: 40vw;
  margin-right: 4vw;
  font-size: 12px;
`

const StyledFormControl = styled(FormControl)`
  min-width: 24vw;
  margin-right: 4vw;
  font-size: 12px;
`

const SearchBarContainer = styled.div`
  width: 100%;
  min-width: 450px;
  display: flex;
  margin-top: 20px;
  justify-content: center;
`;

const ItemsContainer = styled.div`
`;

export default BookingsPage;