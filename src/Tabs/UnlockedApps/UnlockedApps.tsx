import React, {useEffect, useState} from 'react';
import {ActivityIndicator, FlatList, Text, View} from 'react-native';
import {UnlockedAppsStyles} from './UnlockedApps.style';
import {AppItem} from '../../components/AppItem/AppItem';
import {useAppsHook} from '../useAppsHook';
import {InstalledApp} from '../../types/InstalledApp';

export const UnlockedApps = () => {
  const {isFetching, appsList, processError, lockApp, unLockApp} =
    useAppsHook();
  const [unLockedApps, setUnLockedApps] = useState<InstalledApp[]>([]);

  useEffect(() => {
    const unLockedApps = appsList.filter(a => !a.locked);
    setUnLockedApps(unLockedApps);
    console.log('[UnlockedApps]: app list mutated');
  }, [appsList]);
  return (
    <View style={UnlockedAppsStyles.container}>
      {isFetching ? (
        <ActivityIndicator size={50} color={'#ffffff'} />
      ) : unLockedApps.length ? (
        <FlatList
          data={unLockedApps}
          renderItem={({item}) => (
            <AppItem
              unlockApp={unLockApp}
              lockApp={lockApp}
              installedApp={item}
            />
          )}
          keyExtractor={(item, index) => index.toString()}
          contentContainerStyle={UnlockedAppsStyles.flatListContainerStyle}
        />
      ) : processError ? (
        <Text style={UnlockedAppsStyles.errorText}>{processError}</Text>
      ) : (
        ''
      )}
    </View>
  );
};
