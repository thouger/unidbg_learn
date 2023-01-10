package com.sobot.chat.widget.statusbar;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

/* compiled from: StatusBarKitkatImpl */
class e implements a {
    e() {
    }

    @Override // com.sobot.chat.widget.statusbar.a
    public void a(Window window, int i) {
        window.addFlags(67108864);
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        View findViewWithTag = viewGroup.findViewWithTag("ghStatusBarView");
        if (findViewWithTag == null) {
            findViewWithTag = new StatusBarView(window.getContext());
            findViewWithTag.setTag("ghStatusBarView");
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 48;
            findViewWithTag.setLayoutParams(layoutParams);
            viewGroup.addView(findViewWithTag);
        }
        findViewWithTag.setBackgroundColor(i);
        c.a(window, true);
    }
}
