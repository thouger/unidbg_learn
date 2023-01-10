package com.umeng.message.common;

import android.app.Application;
import android.content.Context;

/* compiled from: AppContext */
public class a {
    private static Application a;

    public static void a(Context context) {
        a = (Application) context.getApplicationContext();
    }

    public static Application a() {
        return a;
    }
}
