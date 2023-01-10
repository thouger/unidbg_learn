package com.sobot.chat.widget.kpswitch.util;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: ViewUtil */
public class e {
    public static boolean a(View view, int i) {
        if (view.isInEditMode()) {
            return false;
        }
        Log.d("ViewUtil", String.format("refresh Height %d %d", Integer.valueOf(view.getHeight()), Integer.valueOf(i)));
        if (view.getHeight() == i || Math.abs(view.getHeight() - i) == d.a(view.getContext())) {
            return false;
        }
        int b = c.b(view.getContext());
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, b));
        } else {
            layoutParams.height = b;
            view.requestLayout();
        }
        return true;
    }

    public static boolean a(Activity activity) {
        return (activity.getWindow().getAttributes().flags & 1024) != 0;
    }

    public static boolean b(Activity activity) {
        if (Build.VERSION.SDK_INT < 19 || (activity.getWindow().getAttributes().flags & 67108864) == 0) {
            return false;
        }
        return true;
    }

    static boolean c(Activity activity) {
        if (Build.VERSION.SDK_INT >= 16) {
            return ((ViewGroup) activity.findViewById(16908290)).getChildAt(0).getFitsSystemWindows();
        }
        return false;
    }
}
