import { requireNativeComponent, View, ViewStylePropTypes } from 'react-native';
import { PropTypes } from 'react';

const myText = {
          name: 'MyText',
          propTypes: {
               ...View.propTypes,
               style: PropTypes.shape({
                 fontSize: PropTypes.number,
                 ...ViewStylePropTypes
               }),
               text: PropTypes.string,
               onTouch: PropTypes.func
          }
}

const MyText = requireNativeComponent('RNMyText', myText);
export default MyText
