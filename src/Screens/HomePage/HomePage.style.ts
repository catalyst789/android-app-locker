import {StyleSheet} from 'react-native';
import {FONT_FAMILY} from '../../app/FontFamily';

export const HomeStyles = StyleSheet.create({
  container: {
    width: '100%',
    height: '100%',
    backgroundColor: '#8AC4FF',
  },
  headingText: {
    fontFamily: FONT_FAMILY[400],
    fontSize: 36,
    color: '#263238',
    marginLeft: 28,
    marginTop: 82,
  },
  subHeadingText: {
    fontFamily: FONT_FAMILY[300],
    fontSize: 20,
    color: '#263238',
    marginLeft: 28,
    marginTop: 5,
  },
  card1: {
    backgroundColor: '#EBEBEB',
    width: '88%',
    height: 180,
    borderRadius: 30,
    alignSelf: 'center',
    marginTop: 35,
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  card1Left: {
    marginTop: 34,
    marginLeft: 40,
  },
  card1Heading: {
    color: '#000000',
    fontFamily: FONT_FAMILY[300],
    fontSize: 24,
  },
  card1SubHeading: {
    color: '#263238',
    fontFamily: FONT_FAMILY[300],
    fontSize: 16,
  },
  plsuFilledIcon: {
    marginTop: 20,
  },
  lockFilledIcon: {
    marginTop: 34,
    marginRight: 50,
  },
});
