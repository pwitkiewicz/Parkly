import React, {useEffect, useState} from 'react';
import {Button, CircularProgress, FormControl, Grid, InputLabel, MenuItem, Select, TextField} from "@mui/material";
import styled from "@emotion/styled";

import {getAllParkingSpots} from "../../queries/queries";
import {ParkingSpotFetch} from '../../models/models';
import ParkingSpotItem from "../../components/parkingSpotItem/ParkingSpotItem";
import ParkingSpotModal from "./components/ParkingSpotModal";

type ParkingSpotModel = {
    isVisible: boolean;
}

const ParkingSpotsPage = () => {

    const [isFetching, setIsFetching] = useState<boolean>(true);
    const [parkingSpots, setParkingSpots] = useState<ParkingSpotFetch[]>();
    const [filter, setFilter] = useState("Location");
    const handleChange = (event: any) => {
        setFilter(event.target.value);
    };
    const [addParkingSpotState, setParkingSpotState] = useState<ParkingSpotModel>({
        isVisible: false
    });

    const getParkingSpots = async () => {
        const parkingSpots = await getAllParkingSpots();
        setParkingSpots(parkingSpots);
    }

    useEffect(() => {
        getParkingSpots().then(() => {
            setIsFetching(false)
        });
    }, [])

    return (
        <>
            <SearchBarContainer>
                <ItemsContainer>
                    <StyledTextField id="standard-basic" label="Search"/>
                    <StyledFormControl>
                        <InputLabel>Filter</InputLabel>
                        <Select value={filter} label="Filter" onChange={handleChange}>
                            <MenuItem value="Location">Location</MenuItem>
                            <MenuItem value="??">??</MenuItem>
                            <MenuItem value="???">???</MenuItem>
                        </Select>
                    </StyledFormControl>
                </ItemsContainer>
                <Button variant="outlined" onClick={() => {
                    setParkingSpotState({isVisible: true})
                }}>Add a new spot</Button>
            </SearchBarContainer>
            <StyledGrid container justifyContent="space-around">
                {isFetching &&
                    <CircularProgress/>
                }
                <ParkingSpotModal visible={addParkingSpotState.isVisible} onCancel={() =>
                    setParkingSpotState({
                        isVisible: false,
                    })
                } parkingPlace={{
                    id: 0,
                    name: '',
                    startDateTime: new Date(),
                    endDateTime: new Date(),
                    isActive: false,
                    isDisabledFriendly: false,
                    photos: [],
                    description: '',
                    height: 0,
                    width: 0,
                    location: {
                        id: 0,
                        city: '',
                        street: '',
                        number: 0,
                        zipcode: '',
                        country: '',
                        latitude: 0,
                        longitude: 0
                    },
                    cost: 0
                }} getParkingSpots={getParkingSpots}
                />
                {parkingSpots !== undefined && parkingSpots.map((parkingSpot: ParkingSpotFetch) => (
                    <ParkingSpotItem id={parkingSpot.id} name={parkingSpot.name}
                                     startDateTime={parkingSpot.startDateTime} endDateTime={parkingSpot.endDateTime}
                                     isActive={parkingSpot.isActive} isDisabledFriendly={parkingSpot.isDisabledFriendly}
                                     photos={parkingSpot.photos} description={parkingSpot.description}
                                     height={parkingSpot.height} width={parkingSpot.width}
                                     location={parkingSpot.location} cost={parkingSpot.cost} key={parkingSpot.id}  getParkingSpots={getParkingSpots}/>
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

export default ParkingSpotsPage;