import React from 'react';
import {HomePage} from '../Screens/HomePage/HomePage.tsx';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {AppRouter} from '../navigations/Routes.ts';
import {LandingPage} from '../Screens/LandingPage/LandingPage.tsx';
import {Applock} from '../Screens/Applock/Applock.tsx';

const Stack = createNativeStackNavigator<AppRouter>();

export const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{headerShown: false}}>
        <Stack.Screen name="landing" component={LandingPage} />
        <Stack.Screen
          options={{animation: 'slide_from_bottom'}}
          name="home"
          component={HomePage}
        />
        <Stack.Screen
          options={{animation: 'slide_from_bottom'}}
          name="appLock"
          component={Applock}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
};
