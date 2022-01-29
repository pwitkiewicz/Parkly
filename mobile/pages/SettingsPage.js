import React from "react";
import { Text, View } from "react-native";
import { Ionicons } from "@expo/vector-icons";
  
const SettingsPage = () => {
  return (
    <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
      <Text style={{ color: "#006600", fontSize: 40 }}>Settings Page!</Text>
      <Ionicons name="ios-settings-outline" size={80} color="#006600" />
    </View>
  );
};
  
export default SettingsPage;