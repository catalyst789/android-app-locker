import {StyleSheet} from 'react-native';
import {FONT_FAMILY} from '../../../app/FontFamily';

export const ApplockTopTabBarStyles = StyleSheet.create({
  tabView: {
    display: 'flex',
    flexDirection: 'row',
    width: '100%',
    alignItems: 'center',
    gap: 30,
    backgroundColor: '#8AC4FF',
  },
  unlockedTab: {
    width: '40%',
    height: 48,
    backgroundColor: '#EBEBEB',
    borderRadius: 20,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    marginLeft: 22,
  },
  unlockedTab_Active: {
    width: '40%',
    height: 48,
    backgroundColor: '#4F5D73',
    borderRadius: 20,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    marginLeft: 22,
  },
  unlockedTabText: {
    color: '#263238',
    fontSize: 18,
    fontFamily: FONT_FAMILY[300],
  },
  unlockedTabText_Active: {
    color: '#EBEBEB',
    fontSize: 18,
    fontFamily: FONT_FAMILY[300],
  },
  lockedTab: {
    width: '40%',
    height: 48,
    backgroundColor: '#EBEBEB',
    borderRadius: 20,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
  },
  lockedTab_Active: {
    width: '40%',
    height: 48,
    backgroundColor: '#4F5D73',
    borderRadius: 20,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
  },
  lockedTabText: {
    color: '#263238',
    fontSize: 18,
    fontFamily: FONT_FAMILY[300],
  },
  lockedTabText_Active: {
    color: '#EBEBEB',
    fontSize: 18,
    fontFamily: FONT_FAMILY[300],
  },
});
