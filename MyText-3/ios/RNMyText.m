
#import "RNMyText.h"
#import "RCTShadowView.h"
#import "RCTFont.h"

@interface RNMyTextView : UILabel

@property (nonatomic, copy) RCTBubblingEventBlock onTouch;

@end

@implementation RNMyTextView

- (instancetype)init {
    if ( self = [super init] ) {
    
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(tapRecognizer:)];
        [self setUserInteractionEnabled:YES];
        [self addGestureRecognizer:tap];
    
        return self;
    } else {
        return nil;
    }
}

- (void)tapRecognizer:(UIGestureRecognizer *)recognizer
{
    CGPoint tappedPoint = [recognizer locationInView:self];
    
    NSDictionary *eventData = @{
                                @"x": @(tappedPoint.x),
                                @"y": @(tappedPoint.y)
                                };
    
    self.onTouch(eventData);
}

@end



@interface TextViewShadow : RCTShadowView

@property (nonatomic, assign) NSNumber *fontSize;

@end

@implementation TextViewShadow

- (NSDictionary<NSString *, id> *)processUpdatedProperties:(NSMutableSet<RCTApplierBlock> *)applierBlocks
                                          parentProperties:(NSDictionary<NSString *, id> *)parentProperties
{
    if ([[self reactSuperview] isKindOfClass:[TextViewShadow class]]) {
        return parentProperties;
    }
 
    parentProperties = [super processUpdatedProperties:applierBlocks
                                      parentProperties:parentProperties];
    
    [applierBlocks addObject:^(NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        UILabel *view = (UILabel *)viewRegistry[self.reactTag];
        view.font = [RCTFont updateFont:view.font withSize: self.fontSize ?: [NSNumber numberWithInt:12]];
    }];
    
    return parentProperties;
}

@end



@implementation RNMyText

RCT_EXPORT_MODULE();

- (UIView *)view
{
    return [RNMyTextView new];
}

- (RCTShadowView *)shadowView {
    TextViewShadow *shadowView = [TextViewShadow new];
    return shadowView;
}

RCT_EXPORT_VIEW_PROPERTY(text, NSString *);
RCT_EXPORT_SHADOW_PROPERTY(fontSize, NSNumber *);

RCT_EXPORT_VIEW_PROPERTY(onTouch, RCTBubblingEventBlock)

@end
  
