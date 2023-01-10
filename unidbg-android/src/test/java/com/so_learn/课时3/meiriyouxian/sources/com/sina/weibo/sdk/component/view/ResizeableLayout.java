package com.sina.weibo.sdk.component.view;

import android.content.Context;
import android.widget.RelativeLayout;

public class ResizeableLayout extends RelativeLayout {
    private int a = 0;
    private a b = null;
    private int c = 0;

    public interface a {
        void a(int i, int i2, int i3, int i4);
    }

    public ResizeableLayout(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        a aVar = this.b;
        if (aVar != null) {
            aVar.a(getWidth(), getHeight(), this.c, this.a);
        }
        this.a = getHeight();
        this.c = getWidth();
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setSizeChangeListener(a aVar) {
        this.b = aVar;
    }
}
