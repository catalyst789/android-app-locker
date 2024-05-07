import {useEffect, useState} from 'react';
import {InstalledApp} from '../types/InstalledApp';
import {NativeModules} from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import {AsyncKeys} from '../Constants';
import {useIsFocused} from '@react-navigation/native';

export const useAppsHook = () => {
  const isFocused = useIsFocused();
  const [appsList, setAppsList] = useState<InstalledApp[]>([]);
  const [isFetching, setIsFetching] = useState<boolean>(false);
  const [processError, setProcessError] = useState<string>('');

  const lockApp = async (packageName: string) => {
    console.log('hithtihtihtithithithith');
    const appIndexToBeLocked = appsList.findIndex(
      a => a.packageName === packageName,
    );
    if (appIndexToBeLocked !== -1) {
      const newAppList = [...appsList];
      const appToBeLocked = newAppList[appIndexToBeLocked];
      newAppList[appIndexToBeLocked] = {
        ...appToBeLocked,
        locked: true,
      };
      setAppsList(newAppList);
      await AsyncStorage.setItem(
        AsyncKeys.INSTALLED_APPS,
        JSON.stringify(newAppList),
      );
      console.log('locked');
    }
  };

  const unLockApp = async (packageName: string) => {
    console.log('hithtihtihtithithithith');

    const appIndexToBeLocked = appsList.findIndex(
      a => a.packageName === packageName,
    );
    if (appIndexToBeLocked !== -1) {
      const newAppList = [...appsList];
      const appToBeLocked = newAppList[appIndexToBeLocked];
      newAppList[appIndexToBeLocked] = {
        ...appToBeLocked,
        locked: false,
      };
      setAppsList(newAppList);
      await AsyncStorage.setItem(
        AsyncKeys.INSTALLED_APPS,
        JSON.stringify(newAppList),
      );
      console.log('unlocked');
    }
  };

  useEffect(() => {
    setIsFetching(true);
    setTimeout(async () => {
      try {
        const appsFromAsync = await AsyncStorage.getItem(
          AsyncKeys.INSTALLED_APPS,
        );
        if (appsFromAsync) {
          setAppsList(JSON.parse(appsFromAsync));
        } else {
          const installedAppsList: InstalledApp[] =
            await NativeModules.HelperModule.getAllInstalledAppList();
          await AsyncStorage.setItem(
            AsyncKeys.INSTALLED_APPS,
            JSON.stringify(installedAppsList),
          );
          setAppsList(installedAppsList);
        }
        setIsFetching(false);
      } catch (error) {
        setIsFetching(false);
        setProcessError(error as string);
        console.log('Error in installedAppsList: ', error);
      }
    });
  }, [isFocused]);
  return {
    isFetching,
    processError,
    appsList,
    lockApp,
    unLockApp,
  };
};
