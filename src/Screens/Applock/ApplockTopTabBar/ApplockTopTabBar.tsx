import {MaterialTopTabBarProps} from '@react-navigation/material-top-tabs';
import React from 'react';
import {Text, TouchableOpacity, View} from 'react-native';
import {ApplockTopTabBarStyles} from './ApplockTopTabBar.style';

export const ApplockTopTabBar = (props: MaterialTopTabBarProps) => {
  const {navigation, state} = props;
  return (
    <View style={ApplockTopTabBarStyles.tabView}>
      <TouchableOpacity
        disabled={state.index === 0}
        style={
          state.index === 0
            ? ApplockTopTabBarStyles.unlockedTab_Active
            : ApplockTopTabBarStyles.unlockedTab
        }
        onPress={() => navigation.navigate('unlockedApps')}>
        <Text
          style={
            state.index === 0
              ? ApplockTopTabBarStyles.unlockedTabText_Active
              : ApplockTopTabBarStyles.unlockedTabText
          }>
          Unlocked
        </Text>
      </TouchableOpacity>
      <TouchableOpacity
        disabled={state.index === 1}
        style={
          state.index === 1
            ? ApplockTopTabBarStyles.lockedTab_Active
            : ApplockTopTabBarStyles.lockedTab
        }
        onPress={() => navigation.navigate('lockedApps')}>
        <Text
          style={
            state.index === 1
              ? ApplockTopTabBarStyles.lockedTabText_Active
              : ApplockTopTabBarStyles.lockedTabText
          }>
          Locked
        </Text>
      </TouchableOpacity>
    </View>
  );
};
