package com.sobot.chat.widget.kpswitch.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Window;
import android.widget.LinearLayout;
import com.sobot.chat.widget.kpswitch.a.a;
import com.sobot.chat.widget.kpswitch.b;
import com.sobot.chat.widget.kpswitch.util.e;

public class KPSwitchFSPanelLinearLayout extends LinearLayout implements b {
    private a a;

    public KPSwitchFSPanelLinearLayout(Context context) {
        super(context);
        a();
    }

    public KPSwitchFSPanelLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public KPSwitchFSPanelLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public KPSwitchFSPanelLinearLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        this.a = new a(this);
    }

    @Override // com.sobot.chat.widget.kpswitch.b
    public void a(int i) {
        e.a(this, i);
    }

    @Override // com.sobot.chat.widget.kpswitch.b
    public void a(boolean z) {
        this.a.a(z);
    }

    public void a(Window window) {
        this.a.a(window);
    }
}
