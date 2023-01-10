package com.sobot.chat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class SobotWebview extends WebView {
    public SobotWebview(Context context) {
        super(context.getApplicationContext());
    }

    public SobotWebview(Context context, AttributeSet attributeSet) {
        super(context.getApplicationContext(), attributeSet);
    }

    public SobotWebview(Context context, AttributeSet attributeSet, int i) {
        super(context.getApplicationContext(), attributeSet, i);
    }
}
