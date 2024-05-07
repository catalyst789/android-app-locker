import React from 'react';
import {Image, Text, TouchableOpacity, View} from 'react-native';
import {AppItemStyles} from './AppItem.style';
import AppLockedIcon from '../../assets/images/svg/app-locked-icon.svg';
import AppUnlockedIcon from '../../assets/images/svg/app-unlocked-icon.svg';
import {InstalledApp} from '../../types/InstalledApp';

export const AppItem = ({
  installedApp,
  lockApp,
  unlockApp,
}: {
  installedApp: InstalledApp;
  lockApp: (packageName: string) => Promise<void>;
  unlockApp: (packageName: string) => Promise<void>;
}) => {
  const {appName, appIconBase64, packageName, locked} = installedApp;
  return (
    <View style={AppItemStyles.container}>
      <View style={AppItemStyles.appInfo}>
        <Image source={{uri: appIconBase64}} style={AppItemStyles.appIcon} />
        <Text style={AppItemStyles.appNameText}>{appName}</Text>
      </View>
      {locked ? (
        <TouchableOpacity onPress={() => unlockApp(packageName)}>
          <AppLockedIcon style={AppItemStyles.lockedStatusIcon} />
        </TouchableOpacity>
      ) : (
        <TouchableOpacity onPress={() => lockApp(packageName)}>
          <AppUnlockedIcon style={AppItemStyles.lockedStatusIcon} />
        </TouchableOpacity>
      )}
    </View>
  );
};
