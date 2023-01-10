package com.sobot.chat.widget.kpswitch.a;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.sobot.chat.widget.kpswitch.a;
import com.sobot.chat.widget.kpswitch.util.d;
import com.sobot.chat.widget.kpswitch.util.e;

/* compiled from: KPSwitchRootLayoutHandler */
public class c {
    private int a = -1;
    private final View b;
    private final int c;
    private final boolean d;
    private a e;

    public c(View view) {
        this.b = view;
        this.c = d.a(view.getContext());
        this.d = e.b((Activity) view.getContext());
    }

    public void a(int i, int i2) {
        if (this.d && Build.VERSION.SDK_INT >= 16 && this.b.getFitsSystemWindows()) {
            Rect rect = new Rect();
            this.b.getWindowVisibleDisplayFrame(rect);
            i2 = rect.bottom - rect.top;
        }
        if (i2 >= 0) {
            int i3 = this.a;
            if (i3 < 0) {
                this.a = i2;
                return;
            }
            int i4 = i3 - i2;
            if (i4 != 0 && Math.abs(i4) != this.c) {
                this.a = i2;
                a a = a(this.b);
                if (a == null || Math.abs(i4) < com.sobot.chat.widget.kpswitch.util.c.e(this.b.getContext())) {
                    return;
                }
                if (i4 > 0) {
                    a.d();
                } else if (a.a() && a.b()) {
                    a.c();
                }
            }
        }
    }

    private a a(View view) {
        a aVar = this.e;
        if (aVar != null) {
            return aVar;
        }
        if (view instanceof a) {
            this.e = (a) view;
            return this.e;
        } else if (!(view instanceof ViewGroup)) {
            return null;
        } else {
            int i = 0;
            while (true) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (i >= viewGroup.getChildCount()) {
                    return null;
                }
                a a = a(viewGroup.getChildAt(i));
                if (a != null) {
                    this.e = a;
                    return this.e;
                }
                i++;
            }
        }
    }
}
