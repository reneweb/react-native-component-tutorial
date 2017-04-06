package com.reactlibrary;

import android.widget.TextView;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;

import javax.annotation.Nullable;

public class RNMyTextViewManager extends BaseViewManager<TextView, RNMyTextViewManager.RNMyTextShadowNode> {
    @Override
    public String getName() {
        return "RNMyText";
    }

    @Override
    protected TextView createViewInstance(ThemedReactContext reactContext) {
        return new TextView(reactContext);
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
