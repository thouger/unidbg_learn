package com.sobot.chat.widget.statusbar;

import android.content.Context;
import android.content.res.Resources;

/* compiled from: StatusBarTools */
public class g {
    public static int a(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
