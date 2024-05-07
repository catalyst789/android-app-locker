import React, {useEffect} from 'react';
import {NativeModules, Text, TouchableOpacity, View} from 'react-native';
import {HomeStyles} from './HomePage.style';
import LockedIconFilled from '../../assets/images/svg/lock-icon-filled.svg';
import PlusIcon from '../../assets/images/svg/plus.svg';
import {useHomeHook} from './HomePageHook';

export const HomePage = () => {
  const {handleAppLockScreenNavigation} = useHomeHook();

  useEffect(() => {
    (async () => {
      await NativeModules.HelperModule.handleDrawOverOtherAppsPermission();
      await NativeModules.HelperModule.handleUsageAccessPermission();
    })();
  }, []);

  return (
    <View style={HomeStyles.container}>
      <Text style={HomeStyles.headingText}>Hello</Text>
      <Text style={HomeStyles.subHeadingText}>Protect your privacy</Text>
      <View style={HomeStyles.card1}>
        <View style={HomeStyles.card1Left}>
          <Text style={HomeStyles.card1Heading}>App lock</Text>
          <Text style={HomeStyles.card1SubHeading}>Nice to meet you</Text>
          <TouchableOpacity onPress={handleAppLockScreenNavigation}>
            <PlusIcon
              width={45}
              height={45}
              style={HomeStyles.plsuFilledIcon}
            />
          </TouchableOpacity>
        </View>
        <LockedIconFilled style={HomeStyles.lockFilledIcon} />
      </View>
    </View>
  );
};
