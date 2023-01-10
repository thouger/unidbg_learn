package com.alipay.sdk.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.ConditionVariable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.sdk.app.a;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.l;
import com.huawei.hms.api.ConnectionResult;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class b {
    private static volatile b a;
    private String b;
    private String c = "sdk-and-lite";
    private String d;

    private static String d() {
        return "1";
    }

    private static String e() {
        return "-1;-1";
    }

    private b() {
        String a2 = a.a();
        if (!a.b()) {
            this.c += '_' + a2;
        }
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b();
            }
            bVar = a;
        }
        return bVar;
    }

    public static synchronized void a(String str) {
        synchronized (b.class) {
            if (!TextUtils.isEmpty(str)) {
                PreferenceManager.getDefaultSharedPreferences(com.alipay.sdk.g.b.a().b()).edit().putString("trideskey", str).apply();
                com.alipay.sdk.a.a.b = str;
            }
        }
    }

    private static String b(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    public String a(com.alipay.sdk.g.a aVar, com.alipay.sdk.h.a aVar2) {
        Context b = com.alipay.sdk.g.b.a().b();
        com.alipay.sdk.util.b a2 = com.alipay.sdk.util.b.a(b);
        if (TextUtils.isEmpty(this.b)) {
            String b2 = l.b();
            String c = l.c();
            String d = l.d(b);
            String f = l.f(b);
            String e = l.e(b);
            String b3 = b(b);
            this.b = "Msp/15.7.9 (" + b2 + ";" + c + ";" + d + ";" + f + ";" + e + ";" + b3;
        }
        String b4 = com.alipay.sdk.util.b.b(b).b();
        String g = l.g(b);
        String d2 = d();
        String a3 = a2.a();
        String b5 = a2.b();
        String c2 = c();
        String b6 = b();
        if (aVar2 != null) {
            this.d = aVar2.b();
        }
        String replace = Build.MANUFACTURER.replace(";", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
        String replace2 = Build.MODEL.replace(";", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
        boolean d3 = com.alipay.sdk.g.b.d();
        String c3 = a2.c();
        String c4 = c(b);
        String d4 = d(b);
        StringBuilder sb = new StringBuilder();
        sb.append(this.b);
        sb.append(";");
        sb.append(b4);
        sb.append(";");
        sb.append(g);
        sb.append(";");
        sb.append(d2);
        sb.append(";");
        sb.append(a3);
        sb.append(";");
        sb.append(b5);
        sb.append(";");
        sb.append(this.d);
        sb.append(";");
        sb.append(replace);
        sb.append(";");
        sb.append(replace2);
        sb.append(";");
        sb.append(d3);
        sb.append(";");
        sb.append(c3);
        sb.append(";");
        sb.append(e());
        sb.append(";");
        sb.append(this.c);
        sb.append(";");
        sb.append(c2);
        sb.append(";");
        sb.append(b6);
        sb.append(";");
        sb.append(c4);
        sb.append(";");
        sb.append(d4);
        if (aVar2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("tid", com.alipay.sdk.h.a.a(b).a());
            hashMap.put("utdid", com.alipay.sdk.g.b.a().e());
            String c5 = c(aVar, b, hashMap);
            if (!TextUtils.isEmpty(c5)) {
                sb.append(";;;");
                sb.append(c5);
            }
        }
        sb.append(com.umeng.message.proguard.l.t);
        return sb.toString();
    }

    public static String b() {
        String str;
        Context b = com.alipay.sdk.g.b.a().b();
        SharedPreferences sharedPreferences = b.getSharedPreferences("virtualImeiAndImsi", 0);
        String string = sharedPreferences.getString("virtual_imei", null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        if (TextUtils.isEmpty(com.alipay.sdk.h.a.a(b).a())) {
            str = f();
        } else {
            str = com.alipay.sdk.util.b.a(b).b();
        }
        sharedPreferences.edit().putString("virtual_imei", str).apply();
        return str;
    }

    public static String c() {
        String str;
        Context b = com.alipay.sdk.g.b.a().b();
        SharedPreferences sharedPreferences = b.getSharedPreferences("virtualImeiAndImsi", 0);
        String string = sharedPreferences.getString("virtual_imsi", null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        if (TextUtils.isEmpty(com.alipay.sdk.h.a.a(b).a())) {
            String e = com.alipay.sdk.g.b.a().e();
            if (TextUtils.isEmpty(e) || e.length() < 18) {
                str = f();
            } else {
                str = e.substring(3, 18);
            }
        } else {
            str = com.alipay.sdk.util.b.a(b).a();
        }
        sharedPreferences.edit().putString("virtual_imsi", str).apply();
        return str;
    }

    private static String f() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        Random random = new Random();
        return hexString + (random.nextInt(ConnectionResult.NETWORK_ERROR) + 1000);
    }

    private static String c(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getSSID() : "-1";
    }

    private static String d(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getBSSID() : "00";
    }

    /* access modifiers changed from: private */
    public static String b(com.alipay.sdk.g.a aVar, Context context, HashMap<String, String> hashMap) {
        String[] strArr = {""};
        try {
            APSecuritySdk instance = APSecuritySdk.getInstance(context);
            ConditionVariable conditionVariable = new ConditionVariable();
            instance.initToken(0, hashMap, new AnonymousClass1(strArr, conditionVariable));
            conditionVariable.block(3000);
        } catch (Throwable th) {
            e.a(th);
            com.alipay.sdk.app.a.a.a(aVar, "third", "GetApdidEx", th);
        }
        if (TextUtils.isEmpty(strArr[0])) {
            com.alipay.sdk.app.a.a.a(aVar, "third", "GetApdidNull", "missing token");
        }
        e.a("mspl", "ap:" + strArr[0]);
        return strArr[0];
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.alipay.sdk.b.b$1  reason: invalid class name */
    public static class AnonymousClass1 implements APSecuritySdk.InitResultListener {
        final /* synthetic */ String[] a;
        final /* synthetic */ ConditionVariable b;

        AnonymousClass1(String[] strArr, ConditionVariable conditionVariable) {
            this.a = strArr;
            this.b = conditionVariable;
        }

        @Override // com.alipay.apmobilesecuritysdk.face.APSecuritySdk.InitResultListener
        public void onResult(APSecuritySdk.TokenResult tokenResult) {
            if (tokenResult != null) {
                this.a[0] = tokenResult.apdidToken;
            }
            this.b.open();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.alipay.sdk.b.b$2  reason: invalid class name */
    public static class AnonymousClass2 implements Callable<String> {
        final /* synthetic */ com.alipay.sdk.g.a a;
        final /* synthetic */ Context b;
        final /* synthetic */ HashMap c;

        AnonymousClass2(com.alipay.sdk.g.a aVar, Context context, HashMap hashMap) {
            this.a = aVar;
            this.b = context;
            this.c = hashMap;
        }

        /* renamed from: a */
        public String call() throws Exception {
            return b.b(this.a, this.b, this.c);
        }
    }

    private static String c(com.alipay.sdk.g.a aVar, Context context, HashMap<String, String> hashMap) {
        try {
            return (String) Executors.newFixedThreadPool(2).submit(new AnonymousClass2(aVar, context, hashMap)).get(3000, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a(aVar, "third", "GetApdidTimeout", th);
            return "";
        }
    }

    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            sb.append(com.umeng.message.proguard.l.s);
            sb.append(packageName);
            sb.append(";");
            sb.append(packageInfo.versionCode);
            sb.append(com.umeng.message.proguard.l.t);
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
