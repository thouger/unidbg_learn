package com.cmic.sso.wy.utils;

import android.content.Context;

/* compiled from: PermissionUtils */
public class l {
    public static boolean a(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }
}
