import React, {useEffect, useState} from 'react';
import {
    Button,
    CircularProgress,
    FormControl,
    Grid,
    InputLabel,
    MenuItem,
    Select,
    TextField,
    FormControlLabel,
    Switch
} from "@mui/material";
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
    const [filter, setFilter] = useState<string>("ascending");
    const [city, setCity] = useState<string>("");
    const [active, setActive] = useState<string>("all");

    const handleSwitch = (event: any) => {
        console.log(event);
        if (active === "all") setActive("active");
        else setActive("all");
    }

    const [addParkingSpotState, setParkingSpotState] = useState<ParkingSpotModel>({
        isVisible: false
    });

    const getParkingSpots = async () => {
        const parkingSpots = await getAllParkingSpots(filter, city, active);
        setParkingSpots(parkingSpots);
    }


    useEffect(() => {
        getParkingSpots().then(() => {
            setIsFetching(false)
        });
    }, [filter, city, active])

    return (
        <>
            <SearchBarContainer>
                <ItemsContainer>
                    <StyledTextField id="standard-basic" label="Search by city"
                                     onChange={(event: any) => setCity(event.target.value)}/>
                    <StyledFormControl>
                        <InputLabel htmlFor="filter-select">Sort by city name</InputLabel>
                        <Select id="filter-select" value={filter} label="Sort by city name:" onChange={(event: any) => setFilter(event.target.value)}>
                            <MenuItem value="ascending">A-Z</MenuItem>
                            <MenuItem value="descending">Z-A</MenuItem>
                        </Select>
                    </StyledFormControl>
                    <StyledFormControlLabel control={<Switch onChange={handleSwitch}/>} label="Only Active"
                    />
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
                                     location={parkingSpot.location} cost={parkingSpot.cost} key={parkingSpot.id}
                                     getParkingSpots={getParkingSpots}/>
                ))}
            </StyledGrid>
        </>
    )
}

const StyledFormControlLabel = styled(FormControlLabel)`
    margin-top: 10px;
`

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
  margin-right: 2vw;
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