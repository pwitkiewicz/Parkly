import axios from "axios";

import {server} from "../constants/constants"
import {ParkingSpotSend} from "../models/models";

export const getAllParkingSpots = async () => {
    const response = await axios.get(`${server}/items`, {
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
    return response.data;
}
export const getAllBookings = async () => {
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

export const bookParkingSpot = async (id: number) => {
    alert(`Booked parking place with id ${id}`); //addBooking()
}

export const uploadPhoto = async (fd: FormData, id: number) => {
    await axios.post(`${server}/photos/${id}`, fd, {
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    }).then(response => {
        return response.data;
    });
}
export const cancelBooking = async(id: number) => {
    const response = await axios.delete(`${server}/bookings/${id}`,{
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
    return response.data;
}

export const changePassword = async (password: string) => {
    alert('Changed password!');
}