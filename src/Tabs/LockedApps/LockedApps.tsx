import React, {useEffect, useState} from 'react';
import {ActivityIndicator, FlatList, Text, View} from 'react-native';
import {AppItem} from '../../components/AppItem/AppItem';
import {useAppsHook} from '../useAppsHook';
import {LockedAppsStyles} from './LockedApps.style';
import {InstalledApp} from '../../types/InstalledApp';

export const LockedApps = () => {
  const {isFetching, appsList, processError, lockApp, unLockApp} =
    useAppsHook();

  const [lockedApps, setLockedApps] = useState<InstalledApp[]>([]);

  useEffect(() => {
    const lockedApps = appsList.filter(a => a.locked);
    setLockedApps(lockedApps);
    console.log('[LockedApps]: app list mutated');
  }, [appsList]);
  return (
    <View style={LockedAppsStyles.container}>
      {isFetching ? (
        <ActivityIndicator size={50} color={'#ffffff'} />
      ) : lockedApps.length ? (
        <FlatList
          data={lockedApps}
          renderItem={({item}) => (
            <AppItem
              key={item.packageName}
              lockApp={lockApp}
              unlockApp={unLockApp}
              installedApp={item}
            />
          )}
          keyExtractor={(item, index) => index.toString()}
          contentContainerStyle={LockedAppsStyles.flatListContainerStyle}
        />
      ) : processError ? (
        <Text style={LockedAppsStyles.errorText}>{processError}</Text>
      ) : (
        ''
      )}
    </View>
  );
};
