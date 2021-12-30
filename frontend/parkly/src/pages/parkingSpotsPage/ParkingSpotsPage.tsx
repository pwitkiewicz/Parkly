import React, {useEffect, useState} from 'react';
import {CircularProgress, Grid} from "@mui/material";
import styled from "@emotion/styled";

import SearchBar from "../../components/searchBar/SearchBar";
import {getAllParkingSpots} from "../../queries/queries";
import {ParkingSpot} from '../../models/models';
import ParkingSpotItem from "../../components/parkingSpotItem/ParkingSpotItem";

const ParkingSpotsPage = () => {

    const [isFetching, setIsFetching] = useState<boolean>(true);
    const [parkingSpots, setParkingSpots] = useState<ParkingSpot[]>();

    const getParkingSpots = async () => {
        const parkingSpots = await getAllParkingSpots();
        setParkingSpots(parkingSpots);
    }

    useEffect(() => {
        getParkingSpots().then(() => {setIsFetching(false)});
    }, [])

    return (
        <>
            <SearchBar/>
            <StyledGrid container justifyContent="space-around">
                {isFetching &&
                    <CircularProgress/>
                }
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

export default ParkingSpotsPage;