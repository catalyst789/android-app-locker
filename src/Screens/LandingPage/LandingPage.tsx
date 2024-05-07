import React from 'react';
import {Image, Text, TouchableOpacity, View} from 'react-native';
import {LandingPageStyles} from './LandingPage.style';
import {useLandingPage} from './LandingPageHook';

export const LandingPage = () => {
  const {handleNavigateToHome} = useLandingPage();
  return (
    <View style={LandingPageStyles.container}>
      <Image
        style={LandingPageStyles.landingIllus}
        source={require('../../assets/images/png/Group1SecuredIllustration.png')}
      />
      <Text style={LandingPageStyles.headingText}>Protect your privacy</Text>
      <Text style={LandingPageStyles.bodyText}>
        Applock keeps your apps private with strong passwords ensuring a smooth
        and secure phone experience
      </Text>
      <TouchableOpacity
        onPress={handleNavigateToHome}
        style={LandingPageStyles.startBtn}>
        <Text style={LandingPageStyles.startBtnText}>Start</Text>
      </TouchableOpacity>
    </View>
  );
};
