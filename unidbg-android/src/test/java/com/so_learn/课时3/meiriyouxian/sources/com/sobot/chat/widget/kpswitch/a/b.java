package com.sobot.chat.widget.kpswitch.a;

import android.util.AttributeSet;
import android.view.View;
import com.sobot.chat.widget.kpswitch.a;
import com.sobot.chat.widget.kpswitch.util.e;

/* compiled from: KPSwitchPanelLayoutHandler */
public class b implements a {
    private final View a;
    private boolean b = false;
    private boolean c = false;
    private final int[] d = new int[2];
    private boolean e = false;

    public b(View view, AttributeSet attributeSet) {
        this.a = view;
    }

    public boolean a(int i) {
        if (i == 0) {
            this.b = false;
        }
        if (i == this.a.getVisibility()) {
            return true;
        }
        if (!a() || i != 0) {
            return false;
        }
        return true;
    }

    public int[] a(int i, int i2) {
        if (this.b) {
            this.a.setVisibility(8);
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 1073741824);
            i2 = View.MeasureSpec.makeMeasureSpec(0, 1073741824);
            i = makeMeasureSpec;
        }
        int[] iArr = this.d;
        iArr[0] = i;
        iArr[1] = i2;
        return iArr;
    }

    public void a(boolean z) {
        this.e = z;
    }

    @Override // com.sobot.chat.widget.kpswitch.a
    public boolean a() {
        return this.e;
    }

    @Override // com.sobot.chat.widget.kpswitch.a
    public boolean b() {
        return !this.b;
    }

    @Override // com.sobot.chat.widget.kpswitch.a
    public void c() {
        throw new IllegalAccessError("You can't invoke handle show in handler, please instead of handling in the panel layout, maybe just need invoke super.setVisibility(View.VISIBLE)");
    }

    @Override // com.sobot.chat.widget.kpswitch.a
    public void d() {
        this.b = true;
    }

    public void b(int i) {
        if (!this.c) {
            e.a(this.a, i);
        }
    }

    public void b(boolean z) {
        this.c = z;
    }
}
