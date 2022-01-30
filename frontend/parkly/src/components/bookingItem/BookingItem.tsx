import React, {FC, useState, useEffect} from 'react';
import styled from "@emotion/styled";
import {
    Button,
    Card,
    CardActions,
    CardContent,
    CardMedia,
    Typography,
    CircularProgress,
    Snackbar,
    Alert
} from "@mui/material";
import {Booking} from "../../models/models";
import Theme from "../../constants/Styles";
import {ParkingSpotFetch} from '../../models/models';
import {getParkingSpot, cancelBooking} from '../../queries/queries'
import BookingModal from '../../pages/bookingsPage/components/BookingModal'
import moment from "moment";
import ParkingSpotPicture from "../../assets/parking-place.jpg";

type ParkingSpotDetailsModal = {
    isVisible: boolean;
}
type GetBookingsFunction = {
    getBookings: () => void;
}
const BookingItem: FC<Booking & GetBookingsFunction> = (booking) => {

    const [parkingSpot, setParkingSpot] = useState<ParkingSpotFetch>();
    const [isFetching, setIsFetching] = useState<boolean>(true);
    const [detailsBookingState, setDetailsBookingState] = useState<ParkingSpotDetailsModal>({
        isVisible: false
    });
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarOpenError, setSnackbarOpenError] = useState(false);

    const getParking = async () => {
        console.log(booking);
        const parking = await getParkingSpot(booking.parkingSlot);
        setParkingSpot(parking);
    }

    useEffect(() => {
        getParking().then(() => {
            setIsFetching(false)
        });
    }, [])

    const handleOpenSnackbar = () => {
        setSnackbarOpen(true);
    }
    const handleCloseSnackbar = () => {
        setSnackbarOpen(false);
    }
    const handleOpenSnackbarError = () => {
        setSnackbarOpenError(true);
    }
    const handleCloseSnackbarError = () => {
        setSnackbarOpenError(false);
    }

    return (
        <>
            <Snackbar open={snackbarOpen} autoHideDuration={5000} onClose={handleCloseSnackbar}>
                <Alert onClose={handleCloseSnackbar} severity="success" sx={{ width: '100%' }}>
                    Success!
                </Alert>
            </Snackbar>
            <Snackbar open={snackbarOpenError} autoHideDuration={5000} onClose={handleCloseSnackbarError}>
                <Alert onClose={handleCloseSnackbarError} severity="error" sx={{ width: '100%' }}>
                    Error!
                </Alert>
            </Snackbar>
            <BookingModal visible={detailsBookingState.isVisible} onCancel={() =>
                setDetailsBookingState({
                    isVisible: false,
                })} parkingSpot={parkingSpot} booking={booking}/>
            {isFetching ?
                <CircularProgress/> :
                <StyledCard>
                    {parkingSpot?.photos && parkingSpot?.photos.length > 0 &&
                        <CardMedia component="img" image={parkingSpot?.photos[0]?.path} alt="Parking spot image"
                                   sx={{width: '280px', height: '300px'}}/>
                    }
                    {parkingSpot?.photos && parkingSpot?.photos.length === 0 &&
                        <CardMedia component="img" image={ParkingSpotPicture} alt="Parking spot image"
                                   sx={{width: '280px', height: '300px'}}/>
                    }
                    <CardContent>
                        <Typography variant="h4">
                            Booking ID: {booking.id}
                        </Typography>
                        <TypographyContainer>
                            <Typography variant="body1" style={{marginTop: '1vh'}}>
                                From: {moment(booking?.startDateTime).format('DD/MM/YYYY')}
                            </Typography>
                            <Typography variant="body1" style={{marginTop: '1vh'}}>
                                To: {moment(booking?.endDateTime).format('DD/MM/YYYY')}
                            </Typography>
                            <Typography variant="body1" style={{marginTop: '1vh', fontWeight: 'bold'}}>
                                {parkingSpot?.location.city}
                            </Typography>
                        </TypographyContainer>
                    </CardContent>
                    <CardActions style={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
                        <Button onClick={() => {
                            setDetailsBookingState({isVisible: true})
                        }} style={{color: `${Theme.colors.edit}`, marginRight: '1.5vw'}}>
                            Details
                        </Button>
                        <Button style={{color: `${Theme.colors.remove}`}} onClick={() => {
                            cancelBooking(booking.id).then((response) => {
                                booking.getBookings();
                                if (response) {
                                    handleOpenSnackbarError();
                                } else {
                                    handleOpenSnackbar();
                                }
                            });
                        }}>
                            Cancel
                        </Button>
                    </CardActions>
                </StyledCard>
            }
        </>
    )
}
const StyledCard = styled(Card)`
  max-width: 320px;
  margin: 30px;
`;

const TypographyContainer = styled.div`
  margin-left: 2px;
`

export default BookingItem;