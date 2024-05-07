import {useNavigation} from '@react-navigation/native';
import {NativeStackNavigationProp} from '@react-navigation/native-stack';
import {AppRouter} from '../../navigations/Routes';

export const useAppLock = () => {
  const navigation = useNavigation<NativeStackNavigationProp<AppRouter>>();

  const handleBackNavigation = () => {
    if (navigation.canGoBack()) {
      navigation.goBack();
    }
  };
  return {
    handleBackNavigation,
  };
};
