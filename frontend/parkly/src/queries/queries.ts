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
    return response.data;
}

export const addParkingSpot = async (parkingSpot: ParkingSpot, id?: string) => {
    let response;
    if (id) {
        response = await axios.put(`${server}/items/${id}`, parkingSpot);
    } else {
        response = await axios.post(`${server}/items`, parkingSpot);
    }
    return response.data;
}

export const deleteParkingSpot = async (id: string) => {
    const response = await axios.delete(`${server}/items/${id}`);
    return response.data;
}

export const bookParkingSpot = async (id: string) => {
    alert(`Booked parking place with id ${id}`);
}