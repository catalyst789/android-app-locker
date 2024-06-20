import React, { useEffect, useState } from 'react';
import { HomePage } from '../Screens/HomePage/HomePage.tsx';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { AppRouter } from '../navigations/Routes.ts';
import { LandingPage } from '../Screens/LandingPage/LandingPage.tsx';
import { Applock } from '../Screens/Applock/Applock.tsx';
import { UnlockAppScreen } from '../Screens/UnlockAppScreen/UnlockAppScreen.tsx';
import { NativeEventEmitter, NativeModules } from 'react-native';

const Stack = createNativeStackNavigator<AppRouter>();

export const App = () => {

  const [isShowUnlockAppScreen, setisShowUnlockAppScreen] = useState(false);

  useEffect(() => {
    (async () => {
      const eventEmitter = new NativeEventEmitter(NativeModules.EventEmitter);
      eventEmitter.addListener('OPEN_APP_UNLOCK_SCREEN', (data) => {
        console.log('OPEN_APP_UNLOCK_SCREEN event received with data:', data);
        // Handle the data received
        if (data && data.toString().length > 2) {
          setisShowUnlockAppScreen(true)
        } else {
          setisShowUnlockAppScreen(false)
        }
      });
      await NativeModules.HelperModule.cSetter();
      const is = await NativeModules.HelperModule.isShowLockScreenIntent();
      console.log({ is });
      if (is) {
        setisShowUnlockAppScreen(true);
      }
    })();
  }, []);


  if (isShowUnlockAppScreen) {
    return <UnlockAppScreen />
  }

  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{ headerShown: false }}>
        <Stack.Screen name="landing" component={LandingPage} />
        <Stack.Screen
          options={{ animation: 'slide_from_bottom' }}
          name="home"
          component={HomePage}
        />
        <Stack.Screen
          options={{ animation: 'slide_from_bottom' }}
          name="appLock"
          component={Applock}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
};
