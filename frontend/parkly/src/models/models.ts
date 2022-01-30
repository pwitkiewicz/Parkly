export interface LoginInformation {
    login: string;
    password: string;
}

export interface ParkingSpotFetch {
    id: number;
    name: string;
    startDateTime: number;
    endDateTime: number;
    isActive: boolean;
    isDisabledFriendly: boolean;
    photos: PhotosList[];
    description: string;
    height: number;
    width: number;
    location: Location;
    cost: number;
}

export interface ParkingSpotSend {
    name: string;
    startDateTime: number;
    endDateTime: number;
    isActive: boolean;
    isDisabledFriendly: boolean;
    description: string;
    height: number;
    width: number;
    location: Location;
    cost: number;
}

export interface PhotosList {
    id: number;
    path: string;
}

export interface Location {
    id: number;
    city: string;
    street: string;
    number: number;
    zipcode: string;
    country: string;
    latitude: number;
    longitude: number;
}
export interface Booking {
    id: number;
    startDateTime: Date;
    endDateTime: Date;
    isActive: boolean;
    parkingSlotId: number; 
    ownerId: number;
    firstName: string;
    lastName: string;
    
}