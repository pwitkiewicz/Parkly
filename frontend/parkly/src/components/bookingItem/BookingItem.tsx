import React, {FC, useState, useEffect} from 'react';
import styled from "@emotion/styled";
import {Button, Card, CardActions, CardContent, CardMedia, Typography, CircularProgress} from "@mui/material";
import {Booking} from "../../models/models";
import Theme from "../../constants/Styles";
import {ParkingSpotFetch} from '../../models/models';
import {getParkingSpot} from '../../queries/queries'
import BookingModal from '../../pages/bookingsPage/components/BookingModal'
import moment from "moment";

type ParkingSpotDetailsModal = {
    isVisible: boolean;
}
const BookingItem: FC<Booking> = (booking) => {
    const [parkingSpot, setParkingSpot] = useState<ParkingSpotFetch>();
    const [isFetching, setIsFetching] = useState<boolean>(true);
    const [detailsBookingState, setDetailsBookingState] = useState<ParkingSpotDetailsModal>({
        isVisible: false
    });
    const getParking = async () => {
        const parking = await getParkingSpot(booking.id);
        setParkingSpot(parking);
    }
    useEffect(() => {
        getParking().then(() => {
            setIsFetching(false)
        });
    }, [])
    return (
        <>
            <BookingModal visible={detailsBookingState.isVisible} onCancel={() =>
                setDetailsBookingState({
                    isVisible: false,
                })} parkingSpot={parkingSpot} booking={booking}/>
            {isFetching ?
                <CircularProgress/> :
                <StyledCard>
                    <CardMedia component="img" image={parkingSpot?.photos[0]?.path} alt="Parking spot image"/>
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
                        <Button style={{color: `${Theme.colors.remove}`}}>
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