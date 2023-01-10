package com.cmic.sso.wy.utils;

import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Bundle;
import android.text.TextUtils;
import com.cmic.sso.wy.utils.v;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.pro.ai;

/* compiled from: PhoneScripUtils */
public class m {
    private static boolean a = t.a();
    private static String b;
    private static String c;
    private static long d = 0;

    public static void a(boolean z) {
        q.a("phonescripcache");
        q.a("phonescripstarttime");
        q.a("pre_sim_key");
        if (z) {
            b = null;
            c = null;
            d = 0;
        }
    }

    public static boolean a() {
        return a;
    }

    public static void a(Context context, String str, long j, String str2) {
        b = str;
        d = j;
        c = str2;
        if (!a && !TextUtils.isEmpty(str2)) {
            v.a(new AnonymousClass1(context, str, j, str2));
        }
    }

    /* compiled from: PhoneScripUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.utils.m$1  reason: invalid class name */
    public static class AnonymousClass1 extends v.a {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;
        final /* synthetic */ long c;
        final /* synthetic */ String d;

        AnonymousClass1(Context context, String str, long j, String str2) {
            this.a = context;
            this.b = str;
            this.c = j;
            this.d = str2;
        }

        /* access modifiers changed from: protected */
        @Override // com.cmic.sso.wy.utils.v.a
        public void a() {
            g.b("PhoneScripUtils", "start save scrip to sp in sub thread");
            m.c(this.a, this.b, this.c, this.d);
        }
    }

    /* access modifiers changed from: private */
    public static void c(Context context, String str, long j, String str2) {
        String a2 = f.a(context, str);
        if (!TextUtils.isEmpty(a2)) {
            q.a("phonescripcache", a2);
            q.a("phonescripstarttime", j);
            q.a("pre_sim_key", str2);
        }
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String b2 = q.b("phonescripcache", "");
        if (TextUtils.isEmpty(b2)) {
            g.a("PhoneScripUtils", "null");
            return null;
        }
        d = q.b("phonescripstarttime", 0);
        String b3 = f.b(context, b2);
        b = b3;
        return b3;
    }

    private static boolean b() {
        g.b("PhoneScripUtils", b + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + c + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + d);
        if (!TextUtils.isEmpty(b)) {
            return a(d);
        }
        return !TextUtils.isEmpty(q.b("phonescripcache", "")) && a(q.b("phonescripstarttime", 0));
    }

    private static boolean a(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        g.b("PhoneScripUtils", j + "");
        g.b("PhoneScripUtils", currentTimeMillis + "");
        return j - currentTimeMillis > 120000;
    }

    private static int a(String str) {
        String str2;
        if (!TextUtils.isEmpty(c)) {
            str2 = c;
        } else {
            str2 = q.b("pre_sim_key", "");
            c = str2;
        }
        if (TextUtils.isEmpty(str2)) {
            return 0;
        }
        return str2.equals(str) ? 1 : 2;
    }

    public static boolean a(Bundle bundle) {
        int i;
        if (!bundle.getBoolean("keyIsSimKeyICCID", false)) {
            i = a(bundle.getString(Constants.KEY_IMSI));
        } else {
            i = a(bundle.getString(ai.aa));
        }
        bundle.putString("imsiState", i + "");
        g.b("PhoneScripUtils", "simState = " + i);
        if (i != 1) {
            return false;
        }
        if (a) {
            g.b("PhoneScripUtils", "phone is root");
            a(false);
        }
        return b();
    }
}
