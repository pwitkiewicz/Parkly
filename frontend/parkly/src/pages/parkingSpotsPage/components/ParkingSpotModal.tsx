import React, {ChangeEventHandler, useState} from 'react';
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
import Dropzone, {IDropzoneProps, IMeta} from 'react-dropzone-uploader'

import {addParkingSpot} from "../../../queries/queries";
import { ParkingSpot } from '../../../models/models';

interface Props {
    visible: boolean;
    onCancel: () => void;
    parkingPlace: ParkingSpot;
    editing?: boolean;
    getParkingSpots?: () => void;
}

// TODO: Add BE calls
// TODO 2: Fix date pickers warnings

const ParkingSpotModal: React.FC<Props> = ({visible, onCancel, parkingPlace, editing, getParkingSpots}) => {

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

    // TODO: Think how to send these photos
    const getUploadParams: IDropzoneProps['getUploadParams'] = () => ({ url: 'https://httpbin.org/post' })
    const handleSubmitPhotos: IDropzoneProps['onSubmit'] = (files, allFiles) => {
        console.log(files.map(f => f.meta))
        allFiles.forEach(f => f.remove())
    }

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
    const handleChangeDescription: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev, description: event.target.value}));
    }
    const handleChangeHeight: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev, height: Number(event.target.value)}));
    }
    const handleChangeWidth: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev, width: Number(event.target.value)}));
    }
    const handleChangeCost: ChangeEventHandler<HTMLInputElement> = event => {
        setEditedParkingModal(prev => ({...prev, cost: Number(event.target.value)}));
    }
    const handleChangeCity: ChangeEventHandler<HTMLInputElement> = event => {
        const location = {
            ...editedParkingModal.location,
            city: event.target.value
        }
        setEditedParkingModal(prev => ({...prev, location: location}));
    }
    const handleChangeStreet: ChangeEventHandler<HTMLInputElement> = event => {
        const location = {
            ...editedParkingModal.location,
            street: event.target.value
        }
        setEditedParkingModal(prev => ({...prev, location: location}));
    }
    const handleChangeNumber: ChangeEventHandler<HTMLInputElement> = event => {
        const location = {
            ...editedParkingModal.location,
            number: parseInt(event.target.value)
        }
        setEditedParkingModal(prev => ({...prev, location: location}));
    }
    const handleChangeZip: ChangeEventHandler<HTMLInputElement> = event => {
        const location = {
            ...editedParkingModal.location,
            zipcode: event.target.value
        }
        setEditedParkingModal(prev => ({...prev, location: location}));
    }
    const handleSubmit = () => {
        if (editing) {
            addParkingSpot(editedParkingModal, parkingPlace.id).then(() => {
                if (getParkingSpots) {
                    getParkingSpots();
                }
            });
        } else {
            addParkingSpot(editedParkingModal).then(() => {
                if (getParkingSpots) {
                    getParkingSpots();
                }
            });
        }
        onCancel();
    }

    return (
        <Dialog open={visible} onClose={onCancel}>
            <DialogTitle>{editing ? 'Edit parking spot details' : 'Add parking spot details'}</DialogTitle>
            <DialogContent>
                <Dropzone
                    getUploadParams={getUploadParams}
                    onSubmit={handleSubmitPhotos}
                    PreviewComponent={Preview}
                    accept="image/*,audio/*,video/*"
                    inputWithFilesContent = "Add photos "
                    inputContent={(files, extra) => (extra.reject ? 'Image, audio and video files only' : 'Drag Files')}
                    styles={{
                        dropzoneReject: { borderColor: 'red', backgroundColor: '#DAA' },
                        inputLabel: (files, extra) => (extra.reject ? { color: 'red' } : {}),
                    }}
                />
                <TextField
                    margin="dense"
                    id="name"
                    label="Name"
                    type="text"
                    fullWidth
                    variant="standard"
                    onChange={handleChangeName}
                    value={editedParkingModal.name}
                    style={{ marginTop: '20px' }}
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
                <TextField
                    margin="dense"
                    id="city"
                    label="City"
                    type="text"
                    fullWidth
                    variant="standard"
                    onChange={handleChangeCity}
                    value={editedParkingModal.location.city}
                />
                <TextField
                    margin="dense"
                    id="street"
                    label="Street"
                    type="text"
                    fullWidth
                    variant="standard"
                    onChange={handleChangeStreet}
                    value={editedParkingModal.location.street}
                />
                <TextField
                    inputProps={{inputMode: 'numeric', pattern: '[0-9]*'}}
                    margin="dense"
                    id="number"
                    label="Number"
                    type="text"
                    fullWidth
                    variant="standard"
                    onChange={handleChangeNumber}
                    value={editedParkingModal.location.number}
                />
                <TextField
                    inputProps={{inputMode: 'numeric', pattern: '[0-9]*'}}
                    margin="dense"
                    id="zip"
                    label="Zip code"
                    type="text"
                    fullWidth
                    variant="standard"
                    onChange={handleChangeZip}
                    value={editedParkingModal.location.zipcode}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onCancel}>Cancel</Button>
                <Button onClick={handleSubmit}>{editing ? 'Edit' : 'Add'}</Button>
            </DialogActions>
        </Dialog>
    )
}

const Preview = ({ meta }: {meta: IMeta}) => {
    const { name, percent, status } = meta
    return (
        <div style={{ alignSelf: 'flex-start', marginBottom: '10px' }}>
            {name}, {Math.round(percent)}%, {status}
        </div>
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