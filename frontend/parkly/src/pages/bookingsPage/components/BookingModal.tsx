import React from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    CardMedia,
    Typography,
    Divider
} from "@mui/material";
import styled from "@emotion/styled";
import moment from "moment";

import {Booking, ParkingSpotFetch} from '../../../models/models';

interface Props {
    visible: boolean;
    parkingSpot?: ParkingSpotFetch;
    booking: Booking;
    onCancel: () => void;
}

const BookingModal: React.FC<Props> = ({visible, parkingSpot, booking, onCancel}) => {
    return (
        <Dialog open={visible} onClose={onCancel}>
            <CardMedia component="img" image={parkingSpot?.photos[0]?.path} alt="Parking spot image"/>
            <TypographyContainer>
                <StyledTextContainer>
                    <Typography variant="h6" style={{ fontWeight: 600 }}>Information: </Typography>
                    <Typography variant="body1">Booking ID: {booking.id}</Typography>
                    <Typography variant="body1">Parking Spot ID: {booking.parkingSlotId}</Typography>
                    <Typography variant="body1">Owner ID: {booking.ownerId}</Typography>
                    <StyledDivider/>
                    <Typography variant="h6" style={{ fontWeight: 600 }}>Location: </Typography>
                    <Typography variant="body1">City: {parkingSpot?.location.city}</Typography>
                    <Typography variant="body1">Zip-Code: {parkingSpot?.location.zipcode}</Typography>
                    <Typography variant="body1">Address: {parkingSpot?.location.street} {parkingSpot?.location.number}</Typography>
                    <StyledDivider/>
                    <Typography variant="h6" style={{ fontWeight: 600 }}>Booking Duration</Typography>
                    <Typography>From: {moment(booking?.startDateTime).format('DD/MM/YYYY')}</Typography>
                    <Typography>To: {moment(booking?.endDateTime).format('DD/MM/YYYY')}</Typography>
                </StyledTextContainer>
                <DialogActions>
                    <Button onClick={onCancel}>Close</Button>
                </DialogActions>
            </TypographyContainer>
        </Dialog>
    )
}

const StyledTextContainer = styled.div`
  margin: 10px;
`;

const StyledDivider = styled(Divider)`
    margin-top: 10px;
    margin-bottom: 10px;
`

const TypographyContainer = styled.div`
  margin-left: 4px;
`;

export default BookingModal;