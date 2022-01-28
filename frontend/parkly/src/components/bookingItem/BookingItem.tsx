import React, {FC, useState} from 'react';
import styled from "@emotion/styled";
import {Button, Card, CardActions, CardContent, CardMedia, Typography} from "@mui/material";

import {Booking} from "../../models/models";
import Theme from "../../constants/Styles";
//import ParkingSpotModal from '../../pages/parkingSpotsPage/components/ParkingSpotModal';

type ParkingSpotEditModal = {
    isVisible: boolean;
}

const BookingItem: FC<Booking> = (booking) => {

    const [editParkingSpotState, setParkingSpotState] = useState<ParkingSpotEditModal>({
        isVisible: false
    });
    return (
        <>
            <StyledCard>
                <CardContent>
                    <Typography variant="h4">
                        {booking.id}
                    </Typography>
                    <TypographyContainer>
                        <Typography variant="body1" style={{marginTop: '1vh'}}>
                            {booking.startDateTime}
                        </Typography>
                        <Typography variant="body1" style={{marginTop: '1vh', fontWeight: 'bold'}}>
                            {booking.endDateTime}
                        </Typography>
                    </TypographyContainer>
                </CardContent>
                <CardActions style={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
                    <Button style={{color: `${Theme.colors.remove}`}}>
                        Cancel
                    </Button>
                </CardActions>
            </StyledCard>
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