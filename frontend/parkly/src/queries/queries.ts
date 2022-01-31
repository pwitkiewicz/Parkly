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
export const getAllBookings = async (page?: number) => {
    let response;
    if(!page){
     response = await axios.get(`${server}/bookings`, {
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
}
else{
    response = await axios.get(`${server}/bookings?page=${page - 1}`, {
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
}
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
    let errorCom = '';
    if (id) {
        await axios.put(`${server}/items/${id}`, parkingSpot, {
            headers: {
                'security-header': sessionStorage.getItem('key') || ''
            }
        }).catch(error => {
            errorCom = error;
        });
    } else {
        await axios.post(`${server}/items`, parkingSpot, {
            headers: {
                'security-header': sessionStorage.getItem('key') || ''
            }
        }).catch(error => {
            errorCom = error;
        });
    }
    return errorCom !== '';
}

export const deleteParkingSpot = async (spot: ParkingSpotFetch) => {
    let errorCom = '';
    await axios.delete(`${server}/items/${spot.id}`,{
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    }).catch(error => {
        errorCom = error;
    });
    return errorCom !== '';
}

export const uploadPhoto = async (fd: FormData, id: number) => {
    let errorCom = '';
    await axios.post(`${server}/photos/${id}`, fd, {
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    }).catch(error => {
        errorCom = error;
    });
    return errorCom !== '';
}
export const cancelBooking = async (id: number) => {
    let errorCom = '';
    await axios.delete(`${server}/bookings/${id}`,{
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    }).catch(error => {
        errorCom = error;
    });
    return errorCom !== '';
}

export const getPagination = async (filter?: string, location?: string) => {
    let response;
    if(!filter && !location){
        response = await axios.get(`${server}/bookings/pages`, {
            headers: {
                'security-header': sessionStorage.getItem('key') || ''
            }
        });
    }else{
     response = await axios.get(`${server}/items/pages?filter=${filter}&location=${location}`, {
        headers: {
            'security-header': sessionStorage.getItem('key') || ''
        }
    });
}
    return response.data;
}

export const changePassword = async (password: string) => {
    alert('Changed password!');
}