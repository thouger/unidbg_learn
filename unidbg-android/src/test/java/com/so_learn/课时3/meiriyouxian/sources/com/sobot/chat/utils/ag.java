package com.sobot.chat.utils;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;

/* compiled from: VersionUtils */
public class ag {
    public static void a(Drawable drawable, TextView textView) {
        if (Build.VERSION.SDK_INT >= 16) {
            textView.setBackground(drawable);
        } else {
            textView.setBackgroundDrawable(drawable);
        }
    }
}
