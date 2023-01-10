package com.sobot.chat.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.sobot.chat.api.model.SobotLocationModel;

/* compiled from: StMapOpenHelper */
public class aa {
    public static void a(Context context, SobotLocationModel sobotLocationModel) {
        String packageName = context.getPackageName();
        Intent a = a(packageName, sobotLocationModel);
        if (a == null || !a(context, a)) {
            Intent b = b(packageName, sobotLocationModel);
            if (b == null || !a(context, b)) {
                ae.a(context, q.f(context, "sobot_not_open_map"));
            }
        }
    }

    private static boolean a(Context context, Intent intent) {
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static Intent a(String str, SobotLocationModel sobotLocationModel) {
        if (sobotLocationModel == null) {
            return null;
        }
        try {
            Intent intent = new Intent();
            intent.setData(Uri.parse(String.format("baidumap://map/marker?location=%1$s,%2$s&title=%3$s&content=%4$s&traffic=on&src=%5$s", sobotLocationModel.getLat(), sobotLocationModel.getLng(), sobotLocationModel.getLocalName(), sobotLocationModel.getLocalLabel(), str)));
            return intent;
        } catch (Exception unused) {
            return null;
        }
    }

    public static Intent b(String str, SobotLocationModel sobotLocationModel) {
        if (sobotLocationModel == null) {
            return null;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse(String.format("androidamap://viewMap?lat=%1$s&lon=%2$s&poiname=%3$s&sourceApplication=%4$s&dev=0", sobotLocationModel.getLat(), sobotLocationModel.getLng(), sobotLocationModel.getLocalName(), str)));
            intent.setPackage("com.autonavi.minimap");
            return intent;
        } catch (Exception unused) {
            return null;
        }
    }
}
