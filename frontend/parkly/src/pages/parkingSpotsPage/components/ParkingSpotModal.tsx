import React, {ChangeEventHandler, useCallback, useState} from 'react';
import {Moment} from "moment/moment";
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControlLabel,
    Switch,
    TextField
} from "@mui/material";
import DateAdapter from '@mui/lab/AdapterMoment';
import {DesktopDatePicker, LocalizationProvider} from "@mui/lab";
import styled from "@emotion/styled";

import {addParkingSpot} from "../../../queries/queries";
import { ParkingSpot } from '../../../models/models';

interface Props {
    visible: boolean;
    onCancel: () => void;
    parkingPlace: ParkingSpot;
    editing?: boolean;
}

/* TODO: Add a way to upload photos and enter location
*   Then modify their handlers to properly save data
*   At the end send data to BE with proper call */

// TODO 2: Fix date pickers warnings

const ParkingSpotModal: React.FC<Props> = ({visible, onCancel, parkingPlace, editing}) => {

    const [editedParkingModal, setEditedParkingModal] = useState({
        id: parkingPlace.id,
        name: parkingPlace.name,
        startDateTime: parkingPlace.startDateTime,
        endDateTime: parkingPlace.endDateTime,
        isActive: parkingPlace.isActive,
        isDisabledFriendly: parkingPlace.isDisabledFriendly,
        photos: parkingPlace.photos,
        description: parkingPlace.description,
        height: parkingPlace.height,
        width: parkingPlace.width,
        location: parkingPlace.location,
        cost: parkingPlace.cost
    })

    const handleChangeName: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev, name: event.target.value}));
    }
    const handleChangeStartDate = (date: Moment | null) => {
        const startDate = date?.toDate() || new Date();
        setEditedParkingModal(prev => ({...prev, startDateTime: startDate}));
    }
    const handleChangeEndDate = (date: Moment | null) => {
        const endDate = date?.toDate() || new Date();
        setEditedParkingModal(prev => ({...prev, endDateTime: endDate}));
    }
    const handleChangeIsActive: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev, isActive: event.target.checked}));
    }
    const handleChangeIsDisabledFriendly: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev, isDisabledFriendly: event.target.checked}));
    }
    const handleChangePhotos: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev}));
    }
    const handleChangeDescription: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev, description: event.target.value}));
    }
    const handleChangeHeight: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev, height: Number(event.target.value)}));
    }
    const handleChangeWidth: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev, width: Number(event.target.value)}));
    }
    const handleChangeLocation: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev, id: event.target.value}));
    }
    const handleChangeCost: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev, cost: Number(event.target.value)}));
    }

    const handleSubmit = useCallback(() => {
        addParkingSpot(editedParkingModal);
    }, []);

    return (
        <Dialog open={visible} onClose={onCancel}>
            <DialogTitle>{editing ? 'Edit parking spot details' : 'Add parking spot details'}</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    id="name"
                    label="Name"
                    type="text"
                    fullWidth
                    variant="standard"
                    onChange={handleChangeName}
                    value={editedParkingModal.name}
                />
                <LocalizationProvider dateAdapter={DateAdapter}>
                    <DatePickerContainer>
                        <DatePickerWrapper>
                            <DesktopDatePicker
                                label="Start Date"
                                inputFormat="DD/MM/yyyy"
                                value={editedParkingModal.startDateTime}
                                onChange={handleChangeStartDate}
                                renderInput={(params) => <TextField {...params} />}
                            />
                        </DatePickerWrapper>
                        <DesktopDatePicker
                            label="End Date"
                            inputFormat="DD/MM/yyyy"
                            value={editedParkingModal.endDateTime}
                            onChange={handleChangeEndDate}
                            renderInput={(params) => <TextField {...params} />}
                        />
                    </DatePickerContainer>
                </LocalizationProvider>
                <CheckBoxesContainer>
                    <FormControlLabel control={<Switch onChange={handleChangeIsActive}/>} label="Active"
                                      checked={editedParkingModal.isActive}/>
                    <FormControlLabel control={<Switch onChange={handleChangeIsDisabledFriendly}/>}
                                      label="Disabled friendly"
                                      checked={editedParkingModal.isDisabledFriendly}/>
                </CheckBoxesContainer>
                <TextField
                    margin="dense"
                    id="description"
                    label="Description"
                    type="text"
                    fullWidth
                    variant="standard"
                    multiline
                    rows={4}
                    onChange={handleChangeDescription}
                    value={editedParkingModal.description}
                />
                <TextField
                    inputProps={{inputMode: 'numeric', pattern: '[0-9]*'}}
                    margin="dense"
                    id="length"
                    label="Length"
                    type="text"
                    fullWidth
                    variant="standard"
                    onChange={handleChangeHeight}
                    value={editedParkingModal.height}
                />
                <TextField
                    inputProps={{inputMode: 'numeric', pattern: '[0-9]*'}}
                    margin="dense"
                    id="width"
                    label="Width"
                    type="text"
                    fullWidth
                    variant="standard"
                    onChange={handleChangeWidth}
                    value={editedParkingModal.width}
                />
                <TextField
                    inputProps={{inputMode: 'numeric', pattern: '[0-9]*'}}
                    margin="dense"
                    id="cost"
                    label="Cost"
                    type="text"
                    fullWidth
                    variant="standard"
                    onChange={handleChangeCost}
                    value={editedParkingModal.cost}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onCancel}>Cancel</Button>
                <Button onClick={handleSubmit}>{editing ? 'Edit' : 'Add'}</Button>
            </DialogActions>
        </Dialog>
    )
}

const DatePickerContainer = styled.div`
  margin-top: 4vh;
  display: flex;
  justify-content: space-around;
`;

const DatePickerWrapper = styled.div`
  margin-right: 2vw;
`

const CheckBoxesContainer = styled.div`
  margin-top: 3vh;
  display: flex;
  flex-direction: column;
`

export default ParkingSpotModal;