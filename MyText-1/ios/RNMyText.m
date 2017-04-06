
#import "RNMyText.h"
#import "UIView+React.h"

@implementation RNMyText

RCT_EXPORT_MODULE();

- (UIView *)view
{
    return [UILabel new];
}

RCT_EXPORT_VIEW_PROPERTY(text, NSString *);

@end
  
