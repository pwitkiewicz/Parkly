import React from "react";
import { Text, View, FlatList, ScrollView } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import { useState } from "react";
import {  Card, Icon } from 'react-native-elements';
import DateTimePicker from '@react-native-community/datetimepicker';
import { StyleSheet,KeyboardAvoidingView } from "react-native";
import { TextInput } from "react-native-paper";
const SettingsPage = () => {
  const [startDate,setStartDate] = useState(new Date());
  const [endDate,setEndDate] = useState(new Date());
  const [costFrom,setCostFrom] = useState(0);
  const [costTo,setCostTo] = useState(10e6);
  const [location,setLocation] = useState("Warsaw");
  const [show, setShow] = useState(false);
  const [mode, setMode] = useState('date');
  const showMode = (currentMode) => {
    setShow(true);
    setMode(currentMode);
  };

  const showDatepicker = () => {
    showMode('date');
  };


  
  return (
    
    
    <View style={{ flex: 1,alignContent:"center",flexDirection:"column"}} behavior="padding">
      <ScrollView>
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
        
      <Card.Title>Start Date</Card.Title>
      <Card.Divider />
      
      <View>
      <Icon type="antdesign" name="calendar" onPress={showDatepicker} />
      </View>
      {show && (
        <DateTimePicker
          testID="dateTimePicker"
          value={startDate}
          mode={mode}
          is24Hour={true}
          display="default"
          onChange={(event, date) => {
            setShow(false);
            setStartDate(new Date(date));
            
          }}
        />
      )}
        
    </Card>
    <Card>
        
      <Card.Title>End Date</Card.Title>
      <Card.Divider />
      
      <View>
      <Icon type="antdesign" name="calendar" onPress={showDatepicker} />
      </View>
      {show && (
        <DateTimePicker
          testID="dateTimePicker"
          value={endDate}
          mode={mode}
          is24Hour={true}
          display="default"
          onChange={(event, date) => {
            setShow(false);
            setEndDate(new Date(date));
            
          }}
        />
      )}
        
    </Card>
    </ScrollView>
    </View>
  );
};

  
  

export default SettingsPage;
