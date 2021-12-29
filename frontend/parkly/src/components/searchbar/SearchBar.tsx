import React, { useState } from "react";
import {
  TextField,
  Select,
  FormControl,
  InputLabel,
  MenuItem,
  Box,
  Button,
} from "@mui/material";
import styled from "@emotion/styled";

import Theme from "../../constants/Styles";

const SearchBar = () => {
  const [filter, setFilter] = useState("Location");
  const handleChange = (event: any) => {
    setFilter(event.target.value);
  };
  return (
    <SearchBarContainer>

      <TextField id="standard-basic" label="Search" variant="standard" />

      <FormControl>
        <InputLabel>Filter</InputLabel>
        <Select value={filter} label="Filter" onChange={handleChange}>
          <MenuItem value="Location">Location</MenuItem>
          <MenuItem value="??">??</MenuItem>
          <MenuItem value="???">???</MenuItem>
        </Select>
      </FormControl>

      <Button variant="outlined">Add a new spot</Button>

    </SearchBarContainer>
  );
  
};
const SearchBarContainer = styled.div`
width:35%;
min-width:450px;
display: flex;
justify-content: space-between;
margin-top: 2vw;
margin-left: 1vw;
margin-right: 1vw;
  `;

export default SearchBar;
