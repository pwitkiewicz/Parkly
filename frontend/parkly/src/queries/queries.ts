import axios from "axios";
import {sha256} from 'crypto-hash';

import {server} from "../constants/constants"
import {LoginInformation} from "../models/models";
import {ParkingSpot} from "../models/models";

// TODO: Update this queries when BE is ready

export const sendLoginRequest = async (loginInfo: LoginInformation) => {
    const response = await axios.post(`${server}/login`, {
        login: loginInfo.login,
        password: await sha256(loginInfo.password)
    });
    return response.data;
}

export const getAllParkingSpots = async () => {
    const response = await axios.get(`${server}/items`);
    console.log(response.data);
    return response.data;
}

export const addParkingSpot = async (parkingSpot: ParkingSpot) => {
    const response = await axios.post(`${server}/items`, parkingSpot);
    return response.data;
}