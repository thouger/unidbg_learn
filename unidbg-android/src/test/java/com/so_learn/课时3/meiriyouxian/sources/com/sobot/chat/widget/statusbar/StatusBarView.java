package com.sobot.chat.widget.statusbar;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class StatusBarView extends View {
    private int a;

    public StatusBarView(Context context) {
        this(context, null);
    }

    public StatusBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (Build.VERSION.SDK_INT >= 19) {
            this.a = g.a(context);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(View.MeasureSpec.getSize(i), this.a);
    }
}
