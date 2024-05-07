import {StyleSheet} from 'react-native';
import {FONT_FAMILY} from '../../app/FontFamily';

export const UnlockedAppsStyles = StyleSheet.create({
  container: {
    width: '100%',
    height: '100%',
    backgroundColor: '#8AC4FF',
    paddingTop: 20,
  },
  flatListContainerStyle: {
    paddingBottom: 180,
  },
  loader: {
    width: '100%',
    height: '100%',
    backgroundColor: '#8AC4FF',
  },
  errorText: {
    color: 'red',
    textAlign: 'center',
    fontFamily: FONT_FAMILY[500],
    fontSize: 20,
  },
});
