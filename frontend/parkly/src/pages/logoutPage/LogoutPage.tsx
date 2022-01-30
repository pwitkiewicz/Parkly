import React, { useEffect } from "react";
import styled from "@emotion/styled";
import {Box, Typography} from "@mui/material";

import Header from "../../components/header/Header";
import AuthService from "../../services/AuthService";
import {useNavigate} from "react-router-dom";

const LogoutPage = () => {

    let navigate = useNavigate();

    useEffect(() => {
        AuthService.logout();
        setTimeout(() => {
            navigate('/');
        }, 1000)
    }, [])

    return (
        <>
            <Header/>
            <StyledBox>
                <StyledTypography>
                    Logged out. Redirecting you to the login page...
                </StyledTypography>
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
  margin-bottom: 20px;
`

export default LogoutPage;