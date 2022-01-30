import React, { Component } from "react";
import { FlatList, StyleSheet, Text, View, Button } from "react-native";
//import { getAllParkingSpots } from "../queries/queries";
import ParkingSpot from "../components/ParkingSpot";
import { withNavigation } from 'react-navigation';
class ParkingSpots extends Component {
    state = {
        data: []
    };
    ParkingSpots(){
        this.fetchData();
    }
    componentDidMount() {
        this.fetchData();
    }

    handleButtonClick = () => {
        var lang = this.dropdown.value;
        this.props.onSelectLanguage(lang);            
    }

    fetchData = async () => {
        const response = await fetch("https://randomuser.me/api?results=50");//getAllParkingSpots();//
        const json = await response.json();
        this.setState({ data: json.results });
    };

    render() {
        return (
            <View style={styles.container}>
                <FlatList
                    data={this.state.data.filter(data => data.name.first.includes(this.props.filter))}
                    keyExtractor={(x, i) => i}
                    renderItem={({ item }) =>
                        <ParkingSpot item={item}></ParkingSpot>
                    }

                />
            </View>
        );
    }
}


const styles = StyleSheet.create({
    container: {
        marginTop: 15,
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "white"
    }
});

export default ParkingSpots;