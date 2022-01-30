import SearchBar from "../components/SearchBar";
import { StyleSheet, Text, View, Button } from 'react-native';
import React, { useEffect, useState } from "react";
import ParkingSpots from "./ParkingSpots";
import { withNavigation } from 'react-navigation';
import { useNavigation } from '@react-navigation/native';

function HomePage({ navigation }) {
  //const navigation = useNavigation();


  const [isFetching, setIsFetching] = useState(true);
  const [parkingSpots, setParkingSpots] = useState();
  const [filter, setFilter] = useState("");
  const handleChange = (text) => {
    setFilter(text);
  };

  const getParkingSpots = async () => {
    const parkingSpots = {}//await getAllParkingSpots();
    setParkingSpots(parkingSpots);
  }
  const onButtonClick=()=>{
    navigation.navigate('SettingsPage')}
  
  useEffect(() => {
    getParkingSpots().then(() => {
      setIsFetching(false)
    });
  }, [])
  return (
    <View style={styles.container}>
      <SearchBar onChange={handleChange}></SearchBar>
      <ParkingSpots onButtonClick={onButtonClick} filter={filter}></ParkingSpots>

      <View style={{ flexDirection: 'row', alignItems: 'center' }}>
        <View style={{ flex: 1, height: 1, backgroundColor: 'black' }} />
        <View>
          <Text style={{ width: 120, textAlign: 'center' }}>available: 1000</Text>
          <Text style={{ width: 120, textAlign: 'center' }}>taken: 1000</Text>
        </View>
        <View style={{ flex: 1, height: 1, backgroundColor: 'black' }} />
      </View>
      {//<Button title="sd" onButtonClick={onButtonClick}/>
      }
    </View>
  );
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center'
  },
  bottom: {
    margin: 20,
    fontSize: 10,
  },
  button: {
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: 12,
    paddingHorizontal: 32,
    borderRadius: 4,
    elevation: 3,
    backgroundColor: 'white',
  }
});
export default withNavigation(HomePage);