package com.reactlibrary;

import android.widget.TextView;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import javax.annotation.Nullable;

public class RNMyTextViewManager extends SimpleViewManager<TextView> {
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
}
