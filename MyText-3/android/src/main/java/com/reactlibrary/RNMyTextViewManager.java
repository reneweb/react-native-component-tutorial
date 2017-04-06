package com.reactlibrary;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import javax.annotation.Nullable;
import java.util.Map;

public class RNMyTextViewManager extends BaseViewManager<TextView, RNMyTextViewManager.RNMyTextShadowNode> {
    @Override
    public String getName() {
        return "RNMyText";
    }

    @Override
    protected TextView createViewInstance(ThemedReactContext reactContext) {
        return new TextView(reactContext);
    }

    @Override
    public void addEventEmitters(final ThemedReactContext reactContext, final TextView view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final WritableMap eventData = Arguments.createMap();
                eventData.putDouble("y", event.getY());
                eventData.putDouble("x", event.getX());

                reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher()
                        .dispatchEvent(new Event(view.getId()) {
                            @Override
                            public String getEventName() {
                                return "topTouch";
                            }

                            @Override
                            public void dispatch(RCTEventEmitter rctEventEmitter) {
                                rctEventEmitter.receiveEvent(getViewTag(), getEventName(), eventData);
                            }
                        });
                return false;
            }
        });
    }

    @ReactProp(name = "text")
    public void setText(TextView view, @Nullable String text) {
        view.setText(text);
    }

    @Override
    public RNMyTextShadowNode createShadowNodeInstance() {
        return new RNMyTextShadowNode();
    }

    @Override
    public Class<? extends RNMyTextShadowNode> getShadowNodeClass() {
        return RNMyTextShadowNode.class;
    }

    @Override
    public void updateExtraData(TextView root, Object extraData) {
        root.setTextSize((int)extraData);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("topTouch", MapBuilder.of("registrationName", "onTouch"))
                .build();
    }

    class RNMyTextShadowNode extends LayoutShadowNode {

        private int fontSize = 12;

        @ReactProp(name = "fontSize")
        public void setFontSize(@Nullable int fontSize) {
            this.fontSize = fontSize;
        }

        @Override
        public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
            uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), fontSize);
        }
    }
}
