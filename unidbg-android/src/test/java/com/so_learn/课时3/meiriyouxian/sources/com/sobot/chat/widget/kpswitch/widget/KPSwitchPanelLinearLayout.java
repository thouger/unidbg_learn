package com.sobot.chat.widget.kpswitch.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.sobot.chat.widget.kpswitch.a;
import com.sobot.chat.widget.kpswitch.b;

public class KPSwitchPanelLinearLayout extends LinearLayout implements a, b {
    private com.sobot.chat.widget.kpswitch.a.b a;

    public KPSwitchPanelLinearLayout(Context context) {
        super(context);
        a((AttributeSet) null);
    }

    public KPSwitchPanelLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public KPSwitchPanelLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        this.a = new com.sobot.chat.widget.kpswitch.a.b(this, attributeSet);
    }

    @Override // com.sobot.chat.widget.kpswitch.b
    public void a(int i) {
        this.a.b(i);
    }

    @Override // com.sobot.chat.widget.kpswitch.b
    public void a(boolean z) {
        this.a.a(z);
    }

    @Override // com.sobot.chat.widget.kpswitch.a
    public boolean a() {
        return this.a.a();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (!this.a.a(i)) {
            super.setVisibility(i);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int[] a = this.a.a(i, i2);
        super.onMeasure(a[0], a[1]);
    }

    @Override // com.sobot.chat.widget.kpswitch.a
    public boolean b() {
        return this.a.b();
    }

    @Override // com.sobot.chat.widget.kpswitch.a
    public void c() {
        super.setVisibility(0);
    }

    @Override // com.sobot.chat.widget.kpswitch.a
    public void d() {
        this.a.d();
    }

    public void setIgnoreRecommendHeight(boolean z) {
        this.a.b(z);
    }
}
