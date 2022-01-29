import SearchBar from "../components/SearchBar";
import { StyleSheet, Text, View, Button, FlatList, ScrollView, Pressable } from 'react-native';
import { Icon } from 'react-native-elements';
import React, { useEffect, useState } from "react";
import AnimatedProgressWheel from 'react-native-progress-wheel';
import { Ionicons } from "@expo/vector-icons";
//import { getAllParkingSpots } from "../queries/queries";
//import { ParkingSpot } from '../models/models';
//import ParkingSpotItem from "../components/ParkingSpotItem";
//import ParkingSpotModal from "../components/ParkingSpotModal";





function HomePage({ navigation }) {
  const [isFetching, setIsFetching] = useState(true);
  const [parkingSpots, setParkingSpots] = useState();
  const [filter, setFilter] = useState("Location");
  const handleChange = (text) => {
    setFilter(text);
  };
  const [addParkingSpotState, setParkingSpotState] = useState({
    isVisible: false
  });

  const getParkingSpots = async () => {
    const parkingSpots = await getAllParkingSpots();
    setParkingSpots(parkingSpots);
  }

  useEffect(() => {
    getParkingSpots().then(() => {
      setIsFetching(false)
    });
  }, [])

  return (
    <View style={styles.container}>
      <SearchBar onChange={handleChange}></SearchBar>

      <ScrollView>
        <Text style={{ fontSize: 96 }}>JEst</Text>
        <Text style={{ fontSize: 96 }}>Ciezko</Text>
        <Text style={{ fontSize: 96 }}>Ciezko</Text>
        <Text style={{ fontSize: 96 }}>Ciezko</Text>
        <Text style={{ fontSize: 96 }}>Ciezko</Text>
        <Text style={{ fontSize: 96 }}>Ciezko</Text>
      </ScrollView>
      {//<Pressable style={styles.button}
        //onPress={() => navigation.navigate('ParkingSpotDetailPage')}>
        //</View><Text style={styles.content}>Button</Text>
        //</Pressable>
      }
      <View style={{ flexDirection: 'row', alignItems: 'center' }}>
        <View style={{ flex: 1, height: 1, backgroundColor: 'black' }} />
        <View>
          <Text style={{ width: 120, textAlign: 'center' }}>available: 1000</Text>
          <Text style={{ width: 120, textAlign: 'center' }}>taken: 1000</Text>
        </View>
        <View style={{ flex: 1, height: 1, backgroundColor: 'black' }} />
      </View>
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
export default HomePage