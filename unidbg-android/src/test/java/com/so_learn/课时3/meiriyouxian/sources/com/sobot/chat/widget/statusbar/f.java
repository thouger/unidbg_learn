package com.sobot.chat.widget.statusbar;

import android.view.View;
import android.view.Window;

/* compiled from: StatusBarMImpl */
class f implements a {
    f() {
    }

    @Override // com.sobot.chat.widget.statusbar.a
    public void a(Window window, int i) {
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(i);
        View findViewById = window.findViewById(16908290);
        if (findViewById != null) {
            findViewById.setForeground(null);
        }
    }
}
