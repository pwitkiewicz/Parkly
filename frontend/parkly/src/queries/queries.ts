import axios from "axios";

import {server} from "../constants/constants"
import {ParkingSpotFetch, ParkingSpotSend} from "../models/models";

export const getAllParkingSpots = async (filter: string, city: string, active: string, page?: number) => {
    let response;
    if (!page) {
        response = await axios.get(`${server}/items?sort=${filter}&location=${city}&filter=${active}`, {
            headers: {
                'security-header': sessionStorage.getItem('key') || ''
            }
        });
    } else {
        response = await axios.get(`${server}/items?sort=${filter}&location=${city}&filter=${active}&page=${page - 1}`, {
            headers: {
                'security-header': sessionStorage.getItem('key') || ''
            }
        });
    }
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

export const deleteParkingSpot = async (spot: ParkingSpotFetch) => {
    for(let i=0;i<spot.photos.length;i++){
        const response = await axios.delete(`${server}/photos/${spot.photos[i].id}`,{
            headers: {
                'security-header': sessionStorage.getItem('key') || ''
            }
        });
    }
    const response = await axios.delete(`${server}/items/${spot.id}`,{
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
    return response.data;
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
export const cancelBooking = async (id: number) => {
    const response = await axios.delete(`${server}/bookings/${id}`,{
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
    return response.data;
}

export const getPagination = async (filter: string, location: string) => {
    const response = await axios.get(`${server}/items/pages?filter=${filter}&location=${location}`, {
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
    return response.data;
}

export const changePassword = async (password: string) => {
    alert('Changed password!');
}