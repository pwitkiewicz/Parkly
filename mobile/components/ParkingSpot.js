import { FlatList, StyleSheet, Text, View } from "react-native";
import { Image,Icon } from 'react-native-elements';
import { Modal, Portal, Provider, Button } from 'react-native-paper';
import { withNavigation } from 'react-navigation';
import React from "react";
//const Stack = createNativeStackNavigator();


function ParkingSpot(props) {
    const [visible, setVisible] = React.useState(false);

    const showModal = () => setVisible(true);
    const hideModal = () => setVisible(false);
    const containerStyle = {backgroundColor: 'white', padding: 0, height:150};



    return (<View style={styles.rowContainer}>
        <Image style={styles.image} source={{uri:'https://images.unsplash.com/photo-1561389881-0dc69054475b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80'}}/>
        <View style={styles.colContainer}> 
        <Text style={styles.text}>
            {`name: ${props.item.name.first}`}
        </Text>
        <Text style={styles.text}>
            {`surname:  ${props.item.name.last}`}
        </Text>
      <Icon type="antdesign" name="car" onPress={showModal} />
        </View>
        <Provider>
      <Portal>
        <Modal visible={visible} onDismiss={hideModal} contentContainerStyle={containerStyle}>
        <Icon type="antdesign" name="car" onPress={hideModal} />
        <Text style={styles.modaltext}>
            {`name: ${props.item.name.first}`}
        </Text>
        <Text style={styles.modaltext}>
            {`surname:  ${props.item.name.last}`}
        </Text>
        </Modal>
      </Portal>
      
    </Provider>

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
    },
    modaltext: {
        textAlign: 'center',
        fontStyle: 'italic',
        fontSize: 18,
    }
}); 

export default ParkingSpot;