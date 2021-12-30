import React, {FC, useState} from 'react';
import styled from "@emotion/styled";
import {Button, Card, CardActions, CardContent, CardMedia, Typography} from "@mui/material";

import {ParkingSpot} from "../../models/models";
import Theme from "../../constants/Styles";
import EditParkingSpotModal from '../../pages/parkingSpotsPage/components/EditParkingSpotModal';

type ParkingSpotEditModal = {
    isVisible: boolean;
}

const ParkingSpotItem: FC<ParkingSpot> = (parkingPlace) => {
   
    const [editParkingSpotState, setParkingSpotState] = useState<ParkingSpotEditModal>({
        isVisible: false
    });
    return (
        <StyledCard>
            <CardMedia component="img" image={parkingPlace.photos[0]?.path} alt="Parking spot image"/>
            <CardContent>
                <Typography variant="h4">
                    {parkingPlace.name}
                </Typography>
                <TypographyContainer>
                    <Typography variant="body1" style={{marginTop: '1vh'}}>
                        {parkingPlace.description}
                    </Typography>
                    <Typography variant="body1" style={{marginTop: '1vh', fontWeight: 'bold'}}>
                        {parkingPlace.location.city}
                    </Typography>
                    <Typography variant="body1" style={{marginTop: '1vh', fontWeight: 'bold'}}>
                        {parkingPlace.cost} PLN
                    </Typography>
                </TypographyContainer>
            </CardContent>
            <CardActions style={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
                <Button style={{color: `${Theme.colors.accept}`, marginRight: '1.5vw'}}>
                    Book
                </Button>
                <Button onClick={() => {
                    setParkingSpotState({isVisible: true})
                }} style={{color: `${Theme.colors.edit}`, marginRight: '1.5vw'}}>
                    Edit
                </Button>
                <Button style={{color: `${Theme.colors.remove}`}}>
                    Delete
                </Button>
                <EditParkingSpotModal visible={editParkingSpotState.isVisible} onCancel={() =>
                    setParkingSpotState({
                        isVisible: false,
                    })} parkingPlace={parkingPlace}
                    
                />
            </CardActions>
        </StyledCard>
    )
}
const StyledCard = styled(Card)`
  max-width: 320px;
  margin: 30px;
`;

const TypographyContainer = styled.div`
  margin-left: 2px;
`

export default ParkingSpotItem;