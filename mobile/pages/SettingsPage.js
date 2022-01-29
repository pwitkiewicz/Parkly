import React from "react";
import { Text, View } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { useState } from "react";
import {  Card } from 'react-native-elements';
import DateTimePicker from '@react-native-community/datetimepicker';
import { StyleSheet,KeyboardAvoidingView } from "react-native";
import { TextInput } from "react-native-paper";
const SettingsPage = () => {
  const [startDate,setStartDate] = useState(new Date());
  const [endDate,setEndDate] = useState(new Date());
  const [costFrom,setCostFrom] = useState(0);
  const [costTo,setCostTo] = useState(10e6);
  const [location,setLocation] = useState("Warsaw");
  
  return (
    
    
    <KeyboardAvoidingView style={{ flex: 1,alignContent:"center",flexDirection:"column"}} behavior="padding">
      <Card>
    
        
    <Card.Title>Location</Card.Title>
    <Card.Divider />
     <TextInput onChange={(value) => setLocation(value)}/>
    
  </Card>
  <Card>
        
      <Card.Title>Price</Card.Title>
      <Card.Divider />
      
      
        <Text>From:</Text>
        <TextInput keyboardType="numeric" returnKeyType='done' onChange={(value) => setCostFrom(value)}/>
        
          <Text>To: </Text>
        <TextInput keyboardType="numeric" returnKeyType='done' onChange={(value) => setCostTo(value)}/>

    </Card>
      <Card>
        
      <Card.Title>Date</Card.Title>
      <Card.Divider />
      
      
        <Text>From:</Text>
      <DateTimePicker

          value={startDate}
          display="calendar"
          mode="date"
          display="default"
          onChange={(e,date) => setStartDate(date)}
        />
        
        
          <Text>To: </Text>
        <DateTimePicker
      
      value={endDate}
      mode="date"
      display="default"
      onChange={(date) => setEndDate(date)}
    />

    </Card>
    
    
    
    </KeyboardAvoidingView>
  );
};

  
  

export default SettingsPage;