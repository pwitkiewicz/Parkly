export interface LoginInformation {
    login: string;
    password: string;
}

export interface ParkingSpot {
    id: string;
    name: string;
    startDateTime: Date;
    endDateTime: Date;
    isActive: boolean;
    isDisabledFriendly: boolean;
    photos: object;
    description: string;
    height: number;
    width: number;
    location: object;
    cost: number;
}