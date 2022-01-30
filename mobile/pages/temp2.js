import React, { Component, useState, useEffect } from "react";
import { FlatList, StyleSheet, Text, View } from "react-native";


  
export default function ParkingSpots(props) {
    const [isFetching, setIsFetching] = useState(true);
    const [parkingSpots, setParkingSpots] = useState();
    const getParkingSpots = async () => {
        //const parkingSpots = {}//await getAllParkingSpots();
        const response = await fetch("https://randomuser.me/api?results=500");
        const json = await response.json();
        setParkingSpots(json.results);
      }
    useEffect(() => {
        getParkingSpots().then(() => {
          setIsFetching(false)
        });
      }, [])
      getParkingSpots();
/*   fetchData = async () => {
    const response = await fetch("https://randomuser.me/api?results=500");
    const json = await response.json();
    this.setState({ data: json.results });
  };
 */
    return (
      <View style={styles.container}>
        <FlatList
          data={parkingSpots}
          keyExtractor={(x, i) => i}
          renderItem={({ item }) =>
            <Text>
              {`${item.name.first} ${item.name.last}`}
            </Text>}
        />
      </View>
    );
}

const styles = StyleSheet.create({
  container: {
    marginTop: 15,
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#F5FCFF"
  }
});