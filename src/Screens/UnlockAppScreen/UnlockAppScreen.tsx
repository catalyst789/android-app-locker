import React from "react";
import { Text, View } from "react-native";

export const UnlockAppScreen = () => {
    return (
        <View style={{
            width: '100%',
            height: '100%',
            backgroundColor: '#fff'
        }}>
            <Text style={{
                fontSize: 50,
                color: '#000'
            }}>Unlock Your App to Continue</Text>
        </View>
    )
}