import React from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    CardMedia,
    Typography,
    Divider,
} from "@mui/material";
import styled from "@emotion/styled";
import moment from "moment";
import  Carousel from 'react-bootstrap/Carousel';
import { CarouselItem } from 'react-bootstrap';

import {Booking, ParkingSpotFetch} from '../../../models/models';
import ParkingSpotPicture from "../../../assets/parking-place.jpg";

interface Props {
    visible: boolean;
    parkingSpot?: ParkingSpotFetch;
    booking: Booking;
    onCancel: () => void;
}

const BookingModal: React.FC<Props> = ({visible, parkingSpot, booking, onCancel}) => {
    console.log(booking);
    return (
        <Dialog open={visible} onClose={onCancel}>
            {parkingSpot?.photos && parkingSpot?.photos && parkingSpot?.photos.length > 0 &&
                <StyledCarousel fade={true} nextLabel="" prevLabel="" variant='dark' indicators={false}>
                    {parkingSpot?.photos.map(photo => <Carousel.Item><img src={photo.path} alt="Parking spot image" width='400px' height= '420px'/></Carousel.Item>)}
                </StyledCarousel>
               // <CardMedia component="img" image={parkingSpot?.photos[0]?.path} alt="Parking spot image" sx={{ width: '400px', height: '420px' }}/>
            }
            {parkingSpot?.photos && parkingSpot?.photos.length === 0 &&
                <CardMedia component="img" image={ParkingSpotPicture} alt="Parking spot image" sx={{ width: '400px', height: '420px' }}/>
            }
            <TypographyContainer>
                <StyledTextContainer>
                    <Typography variant="h6" style={{ fontWeight: 600 }}>Information: </Typography>
                    <Typography variant="body1">Booking ID: {booking.id}</Typography>
                    <Typography variant="body1">Parking Spot Name: {parkingSpot?.name}</Typography>
                    <Typography variant="body1">Owner Name: {`${booking.firstName} ` + booking.lastName}</Typography>
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
const StyledCarousel = styled(Carousel)`
width:400px;
  height:420px;
  margin: auto;
`
export default BookingModal;