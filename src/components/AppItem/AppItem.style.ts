import {StyleSheet} from 'react-native';
import {FONT_FAMILY} from '../../app/FontFamily';

export const AppItemStyles = StyleSheet.create({
  container: {
    width: '92%',
    height: 55,
    backgroundColor: '#D9D9D9',
    alignSelf: 'center',
    justifyContent: 'space-between',
    display: 'flex',
    alignItems: 'center',
    flexDirection: 'row',
    borderRadius: 15,
    marginTop: 10,
  },
  appInfo: {
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'center',
    marginLeft: 20,
  },
  appIcon: {
    width: 40,
    height: 40,
    // backgroundColor: '#fff',
    borderRadius: 20,
    marginRight: 10,
  },
  appNameText: {
    color: '#263238',
    fontSize: 16,
    fontFamily: FONT_FAMILY[300],
  },
  lockedStatusIcon: {
    marginRight: 23,
  },
});
