package com.sobot.chat.widget.kpswitch.a;

import android.view.View;
import android.view.Window;
import com.sobot.chat.widget.kpswitch.util.c;

/* compiled from: KPSwitchFSPanelLayoutHandler */
public class a {
    private final View a;
    private boolean b;
    private View c;

    public a(View view) {
        this.a = view;
    }

    public void a(boolean z) {
        this.b = z;
        if (!z && this.a.getVisibility() == 4) {
            this.a.setVisibility(8);
        }
        if (!z && this.c != null) {
            a();
            this.c = null;
        }
    }

    public void a(Window window) {
        View currentFocus = window.getCurrentFocus();
        if (currentFocus != null) {
            if (this.b) {
                a(currentFocus);
            } else {
                currentFocus.clearFocus();
            }
        }
    }

    private void a(View view) {
        this.c = view;
        view.clearFocus();
        this.a.setVisibility(8);
    }

    private void a() {
        this.a.setVisibility(4);
        c.a(this.c);
    }
}
