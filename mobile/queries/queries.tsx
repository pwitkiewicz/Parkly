import axios from "axios";
//import {sha256} from 'crypto-hash';
import { sha256 } from 'react-native-sha256';

import {server} from "../constants/constants"
import {LoginInformation} from "../models/models";
import {ParkingSpot} from "../models/models";
import {Booking} from "../models/models";
import { sessionStorage } from "../components/Storage";
// TODO: Update this queries when BE is ready

export const sendLoginRequest = async (loginInfo: LoginInformation) => {
    const response = await axios.post(`${server}/login`, {
        login: loginInfo.login,
        password: await sha256(loginInfo.password)
    });
    return response.data;
}

export const getAllParkingSpots = async () => {
    let response;
   
        response = await axios.get(`${server}/items`, {
            headers: {
                'security-header': sessionStorage.getItem('key') || ''
            }
        });
    return response.data;
}
export const getAllBookings = async () => {
    const response = await axios.get(`${server}/bookings`);
    return response.data;
}
export const getBooking = async(id: Number) =>{
    const response = await axios.get(`${server}/bookings/${id}`);
    return response.data;
}
export const getParkingSpot = async(id: Number) =>{
    const response = await axios.get(`${server}/items/${id}`);
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

export const changePassword = async (password: string) => {
    alert('Changed password!');
}