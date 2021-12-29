import React, {useState} from "react";
import {
    TextField,
    Select,
    FormControl,
    InputLabel,
    MenuItem,
    Button,
} from "@mui/material";
import styled from "@emotion/styled";

const SearchBar = () => {
    const [filter, setFilter] = useState("Location");
    const handleChange = (event: any) => {
        setFilter(event.target.value);
    };

    return (
        <SearchBarContainer>
            <ItemsContainer>
                <StyledTextField id="standard-basic" label="Search"/>
                <StyledFormControl>
                    <InputLabel>Filter</InputLabel>
                    <Select value={filter} label="Filter" onChange={handleChange}>
                        <MenuItem value="Location">Location</MenuItem>
                        <MenuItem value="??">??</MenuItem>
                        <MenuItem value="???">???</MenuItem>
                    </Select>
                </StyledFormControl>
            </ItemsContainer>
            <Button variant="outlined">Add a new spot</Button>
        </SearchBarContainer>
    );

};

const StyledTextField = styled(TextField)`
  min-width: 40vw;
  margin-right: 4vw;
  font-size: 12px;
`

const StyledFormControl = styled(FormControl)`
  min-width: 24vw;
  margin-right: 4vw;
  font-size: 12px;
`

const SearchBarContainer = styled.div`
  width: 100%;
  min-width: 450px;
  display: flex;
  margin-top: 20px;
  justify-content: center;
`;

const ItemsContainer = styled.div`
`;

export default SearchBar;
