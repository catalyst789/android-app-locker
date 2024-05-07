import {StyleSheet} from 'react-native';
import {FONT_FAMILY} from '../../app/FontFamily';

export const ApplockStyles = StyleSheet.create({
  container: {
    width: '100%',
    height: '100%',
    backgroundColor: '#8AC4FF',
  },
  backArrowStyles: {
    marginTop: 20,
    marginLeft: 28,
  },
  heading: {
    color: '#263238',
    fontSize: 36,
    fontFamily: FONT_FAMILY[500],
    marginLeft: 28,
  },
  navigationButtons: {
    width: '100%',
    height: '100%',
    marginTop: 50,
    backgroundColor: '#8AC4FF',
  },
});
