package com.cmic.sso.wy.utils;

import android.content.Context;
import android.content.res.Resources;

/* compiled from: ResourceUtil */
public class o {
    public static int a(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }

    public static int a(Context context, String str) {
        int a = a(context, str, "drawable");
        if (a != 0) {
            return a;
        }
        throw new Resources.NotFoundException(str);
    }

    public static int b(Context context, String str) {
        int a = a(context, str, "anim");
        if (a != 0) {
            return a;
        }
        throw new Resources.NotFoundException(str);
    }
}
