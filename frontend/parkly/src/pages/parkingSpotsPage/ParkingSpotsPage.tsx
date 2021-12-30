import React, {useEffect, useState} from 'react';
import {Button, CircularProgress, FormControl, Grid, InputLabel, MenuItem, Select, TextField} from "@mui/material";
import styled from "@emotion/styled";

import {getAllParkingSpots} from "../../queries/queries";
import {ParkingSpot} from '../../models/models';
import ParkingSpotItem from "../../components/parkingSpotItem/ParkingSpotItem";
import AddParkingSpotModal from "./components/AddParkingSpotModal";

type ParkingSpotModel = {
    isVisible: boolean;
}

const ParkingSpotsPage = () => {

    const [isFetching, setIsFetching] = useState<boolean>(true);
    const [parkingSpots, setParkingSpots] = useState<ParkingSpot[]>();
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
                <AddParkingSpotModal visible={addParkingSpotState.isVisible} onCancel={() =>
                    setParkingSpotState({
                        isVisible: false,
                    })
                }/>
                {parkingSpots !== undefined && parkingSpots.map((parkingSpot: ParkingSpot) => (
                    <ParkingSpotItem id={parkingSpot.id} name={parkingSpot.name}
                                     startDateTime={parkingSpot.startDateTime} endDateTime={parkingSpot.endDateTime}
                                     isActive={parkingSpot.isActive} isDisabledFriendly={parkingSpot.isDisabledFriendly}
                                     photos={parkingSpot.photos} description={parkingSpot.description}
                                     height={parkingSpot.height} width={parkingSpot.width}
                                     location={parkingSpot.location} cost={parkingSpot.cost}/>
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