package com.sobot.chat.widget.photoview;

import android.os.Build;
import android.view.View;

/* compiled from: Compat */
public class a {
    public static void a(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            c.a(view, runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }
}
