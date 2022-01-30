import { FlatList, StyleSheet, Text, View, Button } from "react-native";
import { Image,Icon } from 'react-native-elements';
import { Modal, Portal, Provider } from 'react-native-paper';
import { withNavigation } from 'react-navigation';
//const Stack = createNativeStackNavigator();



function ParkingSpot(props) {

    return (<View style={styles.rowContainer}>
        <Image style={styles.image} source={{uri:'https://images.unsplash.com/photo-1561389881-0dc69054475b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80'}}/>
        <View style={styles.colContainer}> 
        <Text style={styles.text}>
            {`name: ${props.item.name.first}`}
        </Text>
        <Text style={styles.text}>
            {`surname:  ${props.item.name.last}`}
        </Text>
        <Icon type="antdesign" name="car" onPress={() => (alert(props.item.phone))} />
        </View>
        

    </View>
    )
}
const styles = StyleSheet.create({
    container: {
        padding: 8,
        backgroundColor: "#ffffff",
    },
    rowContainer: {
        padding:10,
        flexDirection: 'row'
    },
    colContainer:{
        padding:10,
        flexDirection: 'column'
    },
    image: {
        width: 80,
        height: 80
    },
    text: {
        textAlign: 'center',
        fontWeight: 'bold',
        fontStyle: 'italic',
        fontSize: 20,
    }
}); 

export default ParkingSpot;