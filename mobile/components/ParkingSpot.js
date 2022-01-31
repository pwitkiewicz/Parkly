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
        <Image style={styles.image} source={{uri:'https://cdn.abcotvs.com/dip/images/5896174_013120-kgo-parking-spot-img_Image_00-00-59,06.jpg?w=1600'}}/>
        <View style={styles.colContainer}> 
        <Text style={styles.text}>
            {`Location: ${props.item.location.city}`}
        </Text>
        <Text style={styles.text}>
            {`Available`}
        </Text>
      <Icon type="antdesign" name="car" onPress={showModal} />
        </View>
        <Provider>
      <Portal>
        <Modal visible={visible} onDismiss={hideModal} contentContainerStyle={containerStyle}>
        <Icon type="antdesign" name="car" onPress={hideModal} />
        <Text style={styles.modaltext}>
            {`Size: ${props.item.dob.age}`}
        </Text>
        <Text style={styles.modaltext}>
            {`Cost:  ${props.item.location.street.number}`}
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