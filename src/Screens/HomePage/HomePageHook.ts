import {useNavigation} from '@react-navigation/native';
import {NativeStackNavigationProp} from '@react-navigation/native-stack';
import {AppRouter} from '../../navigations/Routes';

export const useHomeHook = () => {
  const navigation = useNavigation<NativeStackNavigationProp<AppRouter>>();

  const handleAppLockScreenNavigation = () => {
    navigation.navigate('appLock');
  };
  return {
    handleAppLockScreenNavigation,
  };
};
