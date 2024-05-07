import {StyleSheet} from 'react-native';
import {FONT_FAMILY} from '../../app/FontFamily';

export const LandingPageStyles = StyleSheet.create({
  container: {
    width: '100%',
    height: '100%',
    backgroundColor: '#8AC4FF',
  },
  landingIllus: {
    width: 400,
    height: 300,
    alignSelf: 'center',
    marginTop: 80,
  },
  headingText: {
    color: '#263238',
    fontSize: 40,
    marginTop: 60,
    marginLeft: 50,
    fontWeight: 'normal',
    fontFamily: FONT_FAMILY[400],
  },
  bodyText: {
    color: '#263238',
    fontSize: 18,
    fontWeight: '200',
    marginTop: 15,
    marginLeft: 50,
    marginRight: 39,
    fontFamily: FONT_FAMILY[300],
  },
  startBtn: {
    backgroundColor: '#E2E2E2',
    width: 130,
    borderRadius: 15,
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    marginLeft: 50,
    marginTop: 37,
    paddingVertical: 8,
  },
  startBtnText: {
    color: '#000000',
    fontSize: 25,
    fontFamily: FONT_FAMILY[400],
  },
});
