package com.sina.weibo.sdk.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import com.sina.weibo.sdk.a;
import com.sina.weibo.sdk.b;

/* compiled from: SecurityHelper */
public class i {
    public static boolean a(Context context, Intent intent) {
        ResolveInfo resolveActivity;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 0)) == null) {
            return false;
        }
        try {
            return a(packageManager.getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures, "18da2bf10352443a00a5e046d9fca6bd");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean a(Context context, b.a aVar, Intent intent) {
        if ((aVar != null && aVar.b() <= 10352) || aVar == null) {
            return true;
        }
        String stringExtra = intent != null ? intent.getStringExtra("_weibo_appPackage") : null;
        if (stringExtra == null || intent.getStringExtra("_weibo_transaction") == null || !a.a(context, stringExtra)) {
            return false;
        }
        return true;
    }

    public static boolean a(Signature[] signatureArr, String str) {
        if (signatureArr == null || str == null) {
            return false;
        }
        for (Signature signature : signatureArr) {
            if (str.equals(e.a(signature.toByteArray()))) {
                return true;
            }
        }
        return false;
    }
}
