import React from 'react';
import {View, Text, TouchableOpacity} from 'react-native';
import {ApplockStyles} from './Applock.style';
import BackArrowSvg from '../../assets/images/svg/back-arrow.svg';
import {useAppLock} from './ApplockHook';
import {UnlockedApps} from '../../Tabs/UnlockedApps/UnlockedApps';
import {LockedApps} from '../../Tabs/LockedApps/LockedApps';
import {createMaterialTopTabNavigator} from '@react-navigation/material-top-tabs';
import {ApplockTopTabBar} from './ApplockTopTabBar/ApplockTopTabBar';

const TopTab = createMaterialTopTabNavigator();

export const Applock = () => {
  const {handleBackNavigation} = useAppLock();

  return (
    <View style={ApplockStyles.container}>
      <TouchableOpacity onPress={handleBackNavigation}>
        <BackArrowSvg style={ApplockStyles.backArrowStyles} />
      </TouchableOpacity>
      <Text style={ApplockStyles.heading}>Applock</Text>
      <View style={ApplockStyles.navigationButtons}>
        <TopTab.Navigator backBehavior="none" tabBar={ApplockTopTabBar}>
          <TopTab.Screen name="unlockedApps" component={UnlockedApps} />
          <TopTab.Screen name="lockedApps" component={LockedApps} />
        </TopTab.Navigator>
      </View>
    </View>
  );
};
