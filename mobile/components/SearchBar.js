import React from "react";
import { StyleSheet, TextInput, View, Keyboard, Button } from "react-native";
import { Feather, Entypo } from "@expo/vector-icons";
import { useState } from "react";

const SearchBar = (props) => {
 // const [filter, setFilter] = useState("Location");
  const [clicked, setClicked]=useState(false);
  const [field,resetField]=useState("");

    const handleChange = (text) => {
        props.onChange(text);
    };
  return (
    <View style={styles.container}>
      <View
        style={
          !clicked
            ? styles.searchBar__unclicked
            : styles.searchBar__clicked
        }
      >
        {/* search Icon */}
        <Feather
          name="search"
          size={20}
          color="black"
          style={{ marginLeft: 1 }}
        />
        {/* Input field */}
        <TextInput 
          ref={input => { this.textInput = input }}
          style={styles.input}
          placeholder="Search"
          
          //value={filter}
          onChangeText={newText => handleChange(newText)}
          onFocus={() => {
            setClicked(true);
          }}
        />
        {/* cross Icon, depending on whether the search bar is clicked or not */}
        {clicked && (
          <Entypo name="cross" size={20} color="black" style={{ padding: 1 }} onPress={() => {
            this.textInput.clear()  
            handleChange("")
              Keyboard.dismiss();         
              setClicked(false);
          }}/>
        )}
      </View>
      {/* cancel button, depending on whether the search bar is clicked or not */}
      {/*clicked && (
        <View>
          <Button
            title="Cancel"
            onPress={() => {
              Keyboard.dismiss();
              
              setClicked(false);
            }}
          ></Button>
        </View>
          )*/}
    </View>
  );
};
export default SearchBar;


const styles = StyleSheet.create({
  container: {
    margin: 15,
    justifyContent: "flex-start",
    alignItems: "center",
    flexDirection: "row",
    width: "100%",

  },
  searchBar__unclicked: {
    marginLeft: "10%",
    //marginBottom: "90%",
    padding: 10,
    flexDirection: "row",
    width: "80%",
    backgroundColor: "#d9dbda",
    borderRadius: 15,
    alignItems: "center",
  },
  searchBar__clicked: {
    marginLeft: "5%",
    //marginBottom: "90%",
    padding: 10,
    flexDirection: "row",
    width: "90%",
    backgroundColor: "#d9dbda",
    borderRadius: 15,
    alignItems: "center",
    justifyContent: "space-evenly",
  },
  input: {
    fontSize: 12,
    marginLeft: 10,
    width: "90%",
  },
});
