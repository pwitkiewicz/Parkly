import React, {ChangeEventHandler, useCallback, useState} from 'react';
import {Moment} from "moment/moment";
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    DialogContentText,
    FormControlLabel,
    Switch,
    TextField,
    CardMedia,
    Typography,
    Divider
} from "@mui/material";
import DateAdapter from '@mui/lab/AdapterMoment';
import {DesktopDatePicker, LocalizationProvider} from "@mui/lab";
import styled from "@emotion/styled";

import { Booking,ParkingSpot } from '../../../models/models';

interface Props {
    visible: boolean;
    parkingSpot?: ParkingSpot;
    booking: Booking;
    onCancel: () => void;
}
const BookingModal: React.FC<Props> = ({visible, parkingSpot, booking,onCancel}) => {
    return (
        <Dialog open={visible} onClose={onCancel}>
            
            <CardMedia component="img" image={parkingSpot?.photos[0]?.path} alt="Parking spot image"/>
            <TypographyContainer>
               <Typography variant="h6">Booking ID: {booking.id}</Typography> 
               <Typography variant="h6">Parking Spot ID: {booking.parkingSlotId}</Typography> 
               <Typography variant="h6">Owner ID: {booking.ownerId}</Typography>
                 
               <Divider/>
            
                
               <Typography>Location: </Typography>
            <DialogContent >
               <DialogContentText>City: {parkingSpot?.location.city}</DialogContentText>    
               <DialogContentText>Zip-Code: {parkingSpot?.location.zipcode}</DialogContentText>    
               <DialogContentText>Address: {parkingSpot?.location.street} {parkingSpot?.location.number}</DialogContentText>    
            </DialogContent>
            <Divider/>
            <Typography>Booking Duration</Typography>
            <DialogContent >
               <DialogContentText>From: {parkingSpot?.startDateTime}</DialogContentText>    
               <DialogContentText>To: {parkingSpot?.endDateTime}</DialogContentText>    
                   
            </DialogContent>
            <Divider/>
            <DialogActions>
                <Button onClick={onCancel}>Close</Button>
            </DialogActions>
            </TypographyContainer>
        </Dialog>
        
    )
}


const TypographyContainer = styled.div`
  margin-left: 4px;
`

export default BookingModal;