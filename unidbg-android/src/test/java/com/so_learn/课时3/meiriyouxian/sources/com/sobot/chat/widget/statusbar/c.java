package com.sobot.chat.widget.statusbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/* compiled from: StatusBarCompat */
public class c {
    static final a a;

    static {
        if (Build.VERSION.SDK_INT >= 23 && !a()) {
            a = new f();
        } else if (Build.VERSION.SDK_INT >= 19) {
            a = new e();
        } else {
            a = new AnonymousClass1();
        }
    }

    /* compiled from: StatusBarCompat */
    /* renamed from: com.sobot.chat.widget.statusbar.c$1  reason: invalid class name */
    static class AnonymousClass1 implements a {
        @Override // com.sobot.chat.widget.statusbar.a
        public void a(Window window, int i) {
        }

        AnonymousClass1() {
        }
    }

    private static boolean a() {
        return Build.DISPLAY.startsWith("Flyme");
    }

    public static void a(Activity activity, int i) {
        a(activity, i, a(i) > 225);
    }

    public static int a(int i) {
        int blue = Color.blue(i);
        return (((Color.red(i) * 38) + (Color.green(i) * 75)) + (blue * 15)) >> 7;
    }

    public static void a(Activity activity, int i, boolean z) {
        a(activity.getWindow(), i, z);
    }

    public static void a(Window window, int i, boolean z) {
        if ((window.getAttributes().flags & 1024) <= 0 && !d.a) {
            a.a(window, i);
            b.a(window, z);
        }
    }

    static void a(Window window, boolean z) {
        View childAt = ((ViewGroup) window.findViewById(16908290)).getChildAt(0);
        if (childAt != null) {
            childAt.setFitsSystemWindows(z);
        }
    }

    public static void b(Activity activity, int i) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(201326592);
            window.getDecorView().setSystemUiVisibility(514);
            window.addFlags(Integer.MIN_VALUE);
            window.setNavigationBarColor(i);
        } else if (Build.VERSION.SDK_INT >= 19) {
            window.addFlags(134217728);
        }
    }
}
