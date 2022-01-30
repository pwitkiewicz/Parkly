import axios from "axios";

import {server} from "../constants/constants"
import {ParkingSpotSend} from "../models/models";

export const getAllParkingSpots = async (filter: string, city: string,active:string) => {
    console.log(`Request to ${server}/items?sort=${filter}&location=${city}`);
    const response = await axios.get(`${server}/items?sort=${filter}&location=${city}&filter=${active}`, {
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
    return response.data;
}
export const getAllBookings = async () => {
    console.log("XD");
    const response = await axios.get(`${server}/bookings`, {
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
    return response.data;
}
export const getBooking = async(id: number) =>{
    const response = await axios.get(`${server}/bookings/${id}`, {
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
    return response.data;
}
export const getParkingSpot = async(id: number) =>{
    const response = await axios.get(`${server}/items/${id}`, {
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
    return response.data;
}

export const addParkingSpot = async (parkingSpot: ParkingSpotSend, id?: number) => {
    let response;
    if (id) {
        response = await axios.put(`${server}/items/${id}`, parkingSpot, {
            headers: {
                'security-header': sessionStorage.getItem('key') || ''
            }
        });
    } else {
        console.log("xD");
        response = await axios.post(`${server}/items`, parkingSpot, {
            headers: {
                'security-header': sessionStorage.getItem('key') || ''
            }
        });
    }
    return response.data;
}

export const deleteParkingSpot = async (id: number) => {
    const response = await axios.delete(`${server}/items/${id}`,{
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
    return response.data;
}

export const changePassword = async (password: string) => {
    alert('Changed password!'); //chuj wie
}
export const cancelBooking = async(id: number) => {
    const response = await axios.delete(`${server}/bookings/${id}`,{
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
    return response.data;
}