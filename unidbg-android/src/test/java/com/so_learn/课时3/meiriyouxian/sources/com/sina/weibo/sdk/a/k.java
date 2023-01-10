package com.sina.weibo.sdk.a;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.sina.weibo.sdk.a.b;
import com.sina.weibo.sdk.statistic.WBAgent;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* compiled from: Utility */
public class k {
    public static Bundle a(String str) {
        try {
            URL url = new URL(str);
            Bundle c = c(url.getQuery());
            c.putAll(c(url.getRef()));
            return c;
        } catch (MalformedURLException unused) {
            return new Bundle();
        }
    }

    public static Bundle b(String str) {
        try {
            return c(new URI(str).getQuery());
        } catch (Exception unused) {
            return new Bundle();
        }
    }

    public static Bundle c(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String str2 : str.split("&")) {
                String[] split = str2.split(ContainerUtils.KEY_VALUE_DELIMITER);
                try {
                    bundle.putString(URLDecoder.decode(split[0], "UTF-8"), URLDecoder.decode(split[1], "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return bundle;
    }

    public static boolean a(Context context) {
        try {
            Locale locale = context.getResources().getConfiguration().locale;
            if (Locale.CHINA.equals(locale) || Locale.CHINESE.equals(locale) || Locale.SIMPLIFIED_CHINESE.equals(locale) || Locale.TAIWAN.equals(locale)) {
                return true;
            }
            return false;
        } catch (Exception unused) {
        }
    }

    public static String a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            for (int i = 0; i < packageInfo.signatures.length; i++) {
                byte[] byteArray = packageInfo.signatures[i].toByteArray();
                if (byteArray != null) {
                    return e.a(byteArray);
                }
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static String b(Context context, String str) {
        b.a b = b.a(context).b(str);
        return b != null ? b.a() : "";
    }

    public static String b(Context context) {
        return Build.MANUFACTURER + "-" + Build.MODEL + Session.SESSION_SEPARATION_CHAR_CHILD + Build.VERSION.RELEASE + Session.SESSION_SEPARATION_CHAR_CHILD + "weibosdk" + Session.SESSION_SEPARATION_CHAR_CHILD + "0031405000_android";
    }

    public static String c(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(Build.MANUFACTURER);
        sb.append("-");
        sb.append(Build.MODEL);
        sb.append("__");
        sb.append("weibosdk");
        sb.append("__");
        try {
            sb.append("0031405000".replaceAll("\\s+", Session.SESSION_SEPARATION_CHAR_CHILD));
        } catch (Exception unused) {
            sb.append("unknown");
        }
        sb.append("__");
        sb.append("android");
        sb.append("__android");
        sb.append(Build.VERSION.RELEASE);
        return sb.toString();
    }

    public static void a(Context context, String str, Bundle bundle) {
        try {
            Intent intent = new Intent();
            String valueOf = String.valueOf(System.currentTimeMillis());
            intent.putExtra("_weibo_transaction", valueOf);
            HashMap hashMap = new HashMap();
            hashMap.put("other_app_action_start_time", valueOf);
            try {
                WBAgent.onEvent(context, "message", hashMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            intent.setAction("android.intent.action.VIEW");
            intent.putExtra("_weibo_appPackage", context.getPackageName());
            intent.setData(Uri.parse(str));
            intent.setFlags(268435456);
            intent.putExtras(bundle);
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
        }
    }

    public static Boolean d(Context context) {
        Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        boolean z = false;
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return false;
        }
        int i = 0;
        for (ResolveInfo resolveInfo : queryIntentServices) {
            if (!(resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.applicationInfo == null || TextUtils.isEmpty(resolveInfo.serviceInfo.applicationInfo.packageName))) {
                try {
                    i = context.getPackageManager().getPackageInfo(resolveInfo.serviceInfo.applicationInfo.packageName, 0).versionCode;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (i >= 1920) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
