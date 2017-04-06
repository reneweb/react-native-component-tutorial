import { requireNativeComponent, View } from 'react-native';
import { PropTypes } from 'react';

const myText = {
          name: 'MyText',
          propTypes: {
               ...View.propTypes,
               text: PropTypes.string
          }
}

const MyText = requireNativeComponent('RNMyText', myText);
export default MyText
