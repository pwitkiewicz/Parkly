import Header from "../../components/header/Header";
import React from "react";
import {Box, Button, TextField, Typography} from "@mui/material";
import styled from "@emotion/styled";
import {changePassword} from "../../queries/queries";

const SettingsPage = () => {
    return (
        <>
            <Header/>
            <StyledBox>
                <StyledTypography variant="h4" gutterBottom>
                    Settings
                </StyledTypography>
                <StyledTypography variant="h5" gutterBottom>
                    Change password
                </StyledTypography>
                <StyledTypography variant="body1" gutterBottom>
                    This field doesn't really do anything 🤡
                </StyledTypography>
                <FormContainer>
                    <StyledTextField id="standard-basic" label="New password"/>
                    <StyledButton variant="outlined" onClick={() => {
                        changePassword('Piwo');
                    }}>Save</StyledButton>
                </FormContainer>
            </StyledBox>
        </>
    )
}

const StyledBox = styled(Box)`
    width: 100%;
    max-width: 500px;
    margin: 50px;
`;

const StyledTypography = styled(Typography)`
    margin-bottom: 30px;
`

const StyledTextField = styled(TextField)`
    font-size: 12px;
    min-width: 300px;
    margin-right: 30px;
`

const FormContainer = styled.div`
    display: flex;
`;

const StyledButton = styled(Button)`
    min-width: 100px;
`

export default SettingsPage;