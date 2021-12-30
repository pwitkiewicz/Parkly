import React, {FC} from 'react';
import styled from "@emotion/styled";
import {Button, Card, CardActions, CardContent, CardMedia, Typography} from "@mui/material";

import {ParkingSpot} from "../../models/models";
import Theme from "../../constants/Styles";

const ParkingSpotItem: FC<ParkingSpot> = ({name, description, location, cost, photos}) => {
    return (
        <StyledCard>
            <CardMedia component="img" image={photos[0].path} alt="Parking spot image"/>
            <CardContent>
                <Typography variant="h4">
                    {name}
                </Typography>
                <TypographyContainer>
                    <Typography variant="body1" style={{marginTop: '1vh'}}>
                        {description}
                    </Typography>
                    <Typography variant="body1" style={{marginTop: '1vh', fontWeight: 'bold'}}>
                        {location.city}
                    </Typography>
                    <Typography variant="body1" style={{marginTop: '1vh', fontWeight: 'bold'}}>
                        {cost} PLN
                    </Typography>
                </TypographyContainer>
            </CardContent>
            <CardActions style={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
                <Button style={{color: `${Theme.colors.accept}`, marginRight: '1.5vw'}}>
                    Book
                </Button>
                <Button style={{color: `${Theme.colors.edit}`, marginRight: '1.5vw'}}>
                    Edit
                </Button>
                <Button style={{color: `${Theme.colors.remove}`}}>
                    Delete
                </Button>
            </CardActions>
        </StyledCard>
    )
}

const StyledCard = styled(Card)`
  max-width: 320px;
  margin: 30px;
`;

const TypographyContainer = styled.div`
  margin-left: 2px;
`

export default ParkingSpotItem;