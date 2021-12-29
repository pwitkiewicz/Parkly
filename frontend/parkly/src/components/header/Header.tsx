import React from 'react';
import styled from "@emotion/styled";
import {Button, SvgIcon} from "@mui/material";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import DirectionsCarIcon from '@mui/icons-material/DirectionsCar';
import SettingsIcon from '@mui/icons-material/Settings';
import LogoutIcon from '@mui/icons-material/Logout';

import Theme from '../../constants/Styles'

const Header = () => {
    return (
        <HeaderBar>
            <HeaderContainer>
                <Logo>
                    Parking Spot System
                </Logo>
                <StyledButton>
                    <Icon>
                        <ShoppingCartIcon/>
                    </Icon>
                    Bookings
                </StyledButton>
                <StyledButton>
                    <Icon>
                        <DirectionsCarIcon/>
                    </Icon>
                    Parking spots
                </StyledButton>
                <StyledButton>
                    <Icon>
                        <SettingsIcon/>
                    </Icon>
                    Settings
                </StyledButton>
                <StyledButton style={{ marginLeft: 'auto', marginRight: '4vw' }}>
                    <Icon>
                        <LogoutIcon/>
                    </Icon>
                    Log Out
                </StyledButton>
            </HeaderContainer>
        </HeaderBar>
    )
}

const HeaderBar = styled.div`
    height: 6vh;
    background-color: ${Theme.colors.header};
`;

const HeaderContainer = styled.div`
    height: 100%;
    display: flex;
    align-items: center;
    margin-left: 8vw;
`;

const Logo = styled.div`
    font-size: 1em;
    font-weight: bold;
    color: white;
    margin-right: 8vw;
`;

const StyledButton = styled(Button)`
    margin-right: 2vw;
    display: inline-flex;
    align-items: center;
    color: white;
`

const Icon = styled(SvgIcon)`
    height: 3vh;
    color: white;
    margin-right: 0.5vw;
`

export default Header;