import axios from "axios";
import {sha256} from 'crypto-hash';

import {server} from "../constants/constants"
import {LoginInformation} from "../models/models";
import {ParkingSpot} from "../models/models";

export const sendLoginRequest = async (loginInfo: LoginInformation) => {
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

export const addParkingSpot = async (parkingSpot: ParkingSpot) => {
    const response = await axios.post(`${server}/posts`, parkingSpot);
    return response.data;
}