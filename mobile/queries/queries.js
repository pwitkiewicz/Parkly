import axios from "axios";
import {sha256} from 'crypto-hash';

import {server} from "../../frontend/parkly/src/constants/constants"
import {LoginInformation} from "../../frontend/parkly/src/models/models";
import {ParkingSpot} from "../../frontend/parkly/src/models/models";

// TODO: Update this queries when BE is ready

export const sendLoginRequest = async (loginInfo) => {
    const response = await axios.post(`${server}/login`, {
        login: loginInfo.login,
        password: await sha256(loginInfo.password)
    });
    return response.data;
}

export const getAllParkingSpots = async () => {
    const response = await axios.get(`${server}/items`);
    return response.data;
}

export const addParkingSpot = async (parkingSpot) => {
    const response = await axios.post(`${server}/items`, parkingSpot);
    return response.data;
}