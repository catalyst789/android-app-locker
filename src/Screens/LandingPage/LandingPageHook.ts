import {useNavigation} from '@react-navigation/native';
import {AppRouter} from '../../navigations/Routes';
import {NativeStackNavigationProp} from '@react-navigation/native-stack';

export const useLandingPage = () => {
  const navigation = useNavigation<NativeStackNavigationProp<AppRouter>>();

  const handleNavigateToHome = () => {
    navigation.navigate('home');
  };

  return {
    handleNavigateToHome,
  };
};
