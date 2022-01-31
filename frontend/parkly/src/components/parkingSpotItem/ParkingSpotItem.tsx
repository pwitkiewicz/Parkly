import React, {FC, useState} from 'react';
import styled from "@emotion/styled";
import {Alert, Button, Card, CardActions, CardContent, CardMedia, Snackbar, Typography} from "@mui/material";

import {ParkingSpotFetch} from "../../models/models";
import Theme from "../../constants/Styles";
import ParkingSpotModal from '../../pages/parkingSpotsPage/components/ParkingSpotModal';
import {deleteParkingSpot} from "../../queries/queries";
import ParkingSpotPicture from "../../assets/parking-place.jpg";

type ParkingSpotEditModal = {
    isVisible: boolean;
}

type GetParkingSpotsFunction = {
    getParkingSpots: () => void;
}

const ParkingSpotItem: FC<ParkingSpotFetch & GetParkingSpotsFunction> = (parkingPlace) => {

    const [editParkingSpotState, setParkingSpotState] = useState<ParkingSpotEditModal>({
        isVisible: false
    });
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarOpenError, setSnackbarOpenError] = useState(false);

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
            <ParkingSpotModal visible={editParkingSpotState.isVisible} onCancel={() =>
                setParkingSpotState({
                    isVisible: false,
                })} parkingPlace={parkingPlace} editing={true} getParkingSpots={parkingPlace.getParkingSpots}/>
            <StyledCard>
                {parkingPlace?.photos && parkingPlace?.photos.length > 0 &&
                    <CardMedia component="img" image={parkingPlace?.photos[0]?.path} alt="Parking spot image" sx={{ width: '320px', height: '300px' }}/>
                }
                {parkingPlace?.photos && parkingPlace?.photos.length === 0 &&
                    <CardMedia component="img" image={ParkingSpotPicture} alt="Parking spot image" sx={{ width: '320px', height: '300px' }}/>
                }
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
                    <Button onClick={() => {
                        setParkingSpotState({isVisible: true});
                    }} style={{color: `${Theme.colors.edit}`, marginRight: '1.5vw'}}>
                        Edit
                    </Button>
                    <Button style={{color: `${Theme.colors.remove}`}} onClick={async () => {
                       await deleteParkingSpot(parkingPlace).then((response) => {
                            parkingPlace.getParkingSpots();
                           if (response) {
                               handleOpenSnackbarError();
                           } else {
                               handleOpenSnackbar();
                           }
                        });
                    }}>
                        Delete
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

export default ParkingSpotItem;