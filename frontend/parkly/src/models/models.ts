export interface LoginInformation {
    login: string;
    password: string;
}

export interface ParkingSpot {
    id: string;
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
    latitude: number;
    longitude: number;
}
