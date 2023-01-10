package com.cmic.sso.wy.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import java.util.UUID;

/* compiled from: UmcUtils */
public class aa {
    public static String a() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String b() {
        return c().replace("-", "");
    }

    private static String c() {
        return UUID.randomUUID().toString();
    }

    public static void a(Context context, Bundle bundle, String str, String str2) {
        b(context, bundle, str, str2);
    }

    private static void b(Context context, Bundle bundle, String str, String str2) {
        try {
            Intent intent = new Intent();
            intent.setClassName(context, "com.cmic.sso.wy.activity.LoginAuthActivity");
            intent.putExtras(bundle);
            intent.setFlags(268435456);
            if (str == null || str2 == null) {
                context.startActivity(intent);
            } else {
                ActivityCompat.startActivity(context, intent, ActivityOptionsCompat.makeCustomAnimation(context, o.b(context, str), o.b(context, str2)).toBundle());
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (str != null && str2 != null) {
                Intent intent2 = new Intent();
                intent2.setClassName(context, "com.cmic.sso.wy.activity.LoginAuthActivity");
                intent2.putExtras(bundle);
                intent2.setFlags(268435456);
                context.startActivity(intent2);
            }
        }
    }
}
