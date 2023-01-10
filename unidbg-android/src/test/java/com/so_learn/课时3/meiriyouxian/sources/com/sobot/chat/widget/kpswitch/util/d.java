package com.sobot.chat.widget.kpswitch.util;

import android.content.Context;
import android.util.Log;

/* compiled from: StatusBarHeightUtil */
public class d {
    private static boolean a = false;
    private static int b = 50;

    public static synchronized int a(Context context) {
        int i;
        int identifier;
        synchronized (d.class) {
            if (!a && (identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
                b = context.getResources().getDimensionPixelSize(identifier);
                a = true;
                Log.d("StatusBarHeightUtil", String.format("Get status bar height %d", Integer.valueOf(b)));
            }
            i = b;
        }
        return i;
    }
}
