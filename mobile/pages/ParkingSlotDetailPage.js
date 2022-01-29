import { StyleSheet, Text, View, Button } from 'react-native';

function ParkingSpotDetailPage() {
    return (
      <View style={styles.container}>
        <Text style={styles.content}>This is a DETAIL screen</Text>
      </View>
    );
  }
  export default ParkingSpotDetailPage;
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