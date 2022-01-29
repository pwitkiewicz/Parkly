import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';
import { Icon } from 'react-native-elements';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import HomePage from './pages/HomePage';
import SettingsPage from './pages/SettingsPage';
import ParkingSpotDetailPage from './pages/ParkingSlotDetailPage';
import { Ionicons } from "@expo/vector-icons";
/* import {
  Item,
  HeaderButton,
  HeaderButtons,
} from "react-navigation-header-buttons"; */


const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="HomePage" component={HomePage} options={({ navigation }) =>({ title: 'Parkly',headerRight: () => (
       <Icon type="antdesign" name="setting" onPress={() => navigation.navigate('SettingsPage')} />
         ) })} />
        <Stack.Screen name="ParkingSpotDetailPage" component={ParkingSpotDetailPage} options={{ title: 'Details' }} />
        <Stack.Screen name="SettingsPage" component={SettingsPage} options={{ title: 'Settings' }} />


      </Stack.Navigator>
    </NavigationContainer>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center'
  },
  content: {
    margin: 20,
    fontSize: 18,
  }
});
