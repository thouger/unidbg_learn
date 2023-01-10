package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.umeng.analytics.pro.ai;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.utils.UMUtils;

/* compiled from: HeaderHelper */
public class a {
    private static Context a;
    private String b;
    private String c;

    private a() {
        this.b = null;
        this.c = null;
    }

    /* compiled from: HeaderHelper */
    /* access modifiers changed from: private */
    /* renamed from: com.umeng.commonsdk.statistics.internal.a$a  reason: collision with other inner class name */
    public static class C0184a {
        private static final a a = new a();

        private C0184a() {
        }
    }

    public static a a(Context context) {
        if (a == null && context != null) {
            a = context.getApplicationContext();
        }
        return C0184a.a;
    }

    public boolean a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith("a");
        }
        return false;
    }

    public boolean b(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith("t");
        }
        return false;
    }

    public boolean c(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith(ai.aB);
        }
        return false;
    }

    public void d(String str) {
        String substring = str.substring(0, str.indexOf(95));
        f(substring);
        e(substring);
    }

    private void e(String str) {
        try {
            String replaceAll = str.replaceAll("&=", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER).replaceAll("&&", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER).replaceAll("==", NotificationIconUtil.SPLIT_CHAR);
            this.b = replaceAll + NotificationIconUtil.SPLIT_CHAR + "Android" + NotificationIconUtil.SPLIT_CHAR + Build.DISPLAY + NotificationIconUtil.SPLIT_CHAR + Build.MODEL + NotificationIconUtil.SPLIT_CHAR + Build.VERSION.RELEASE + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + HelperUtils.getUmengMD5(UMUtils.getAppkey(a));
        } catch (Throwable th) {
            UMCrashManager.reportCrash(a, th);
        }
    }

    private void f(String str) {
        try {
            String str2 = str.split("&&")[0];
            if (!TextUtils.isEmpty(str2)) {
                String[] split = str2.split("&=");
                StringBuilder sb = new StringBuilder();
                sb.append(ai.aN);
                for (String str3 : split) {
                    if (!TextUtils.isEmpty(str3)) {
                        String substring = str3.substring(0, 2);
                        if (substring.endsWith(ContainerUtils.KEY_VALUE_DELIMITER)) {
                            substring = substring.replace(ContainerUtils.KEY_VALUE_DELIMITER, "");
                        }
                        sb.append(substring);
                    }
                }
                this.c = sb.toString();
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(a, th);
        }
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.b;
    }
}
