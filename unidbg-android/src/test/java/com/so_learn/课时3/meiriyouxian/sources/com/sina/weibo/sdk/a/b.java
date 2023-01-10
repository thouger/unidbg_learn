package com.sina.weibo.sdk.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.provider.Settings;
import android.provider.Telephony;
import android.security.keystore.KeyProperties;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.android.internal.telephony.PhoneConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.e;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AidTask */
public class b {
    private static b a;
    private Context b;
    private String c;
    private a d;
    private volatile ReentrantLock e = new ReentrantLock(true);
    private c f;

    /* compiled from: AidTask */
    /* renamed from: com.sina.weibo.sdk.a.b$b  reason: collision with other inner class name */
    public interface AbstractC0134b {
        void a(a aVar);

        void a(Exception exc);
    }

    /* compiled from: AidTask */
    private static class c extends Handler {
        private WeakReference<AbstractC0134b> a;

        public c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AbstractC0134b bVar = this.a.get();
            int i = message.what;
            if (i != 1001) {
                if (i == 1002 && bVar != null) {
                    bVar.a((WeiboException) message.obj);
                }
            } else if (bVar != null) {
                bVar.a(((a) message.obj).b());
            }
        }
    }

    /* compiled from: AidTask */
    public static final class a {
        private String a;
        private String b;

        public String a() {
            return this.a;
        }

        public static a a(String str) throws WeiboException {
            a aVar = new a();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("error") || jSONObject.has(Telephony.TextBasedSmsColumns.ERROR_CODE)) {
                    d.a("AidTask", "loadAidFromNet has error !!!");
                    throw new WeiboException("loadAidFromNet has error !!!");
                }
                aVar.a = jSONObject.optString("aid", "");
                aVar.b = jSONObject.optString(Telephony.BaseMmsColumns.SUBJECT, "");
                return aVar;
            } catch (JSONException e) {
                d.a("AidTask", "loadAidFromNet JSONException Msg : " + e.getMessage());
                throw new WeiboException("loadAidFromNet has error !!!");
            }
        }

        /* access modifiers changed from: package-private */
        public a b() {
            a aVar = new a();
            aVar.a = this.a;
            aVar.b = this.b;
            return aVar;
        }
    }

    private b(Context context) {
        this.b = context.getApplicationContext();
        this.f = new c(this.b.getMainLooper());
        new Thread(new AnonymousClass1()).start();
    }

    /* compiled from: AidTask */
    /* renamed from: com.sina.weibo.sdk.a.b$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (int i = 0; i < 1; i++) {
                try {
                    b.this.a(i).delete();
                } catch (Exception unused) {
                }
            }
        }
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b(context);
            }
            bVar = a;
        }
        return bVar;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            d.c("AidTask", "aidTaskInit ");
            c(str);
        }
    }

    private void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.c = str;
            new Thread(new AnonymousClass2()).start();
        }
    }

    /* compiled from: AidTask */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.a.b$2  reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!b.this.e.tryLock()) {
                d.c("AidTask", "tryLock : false, return");
                return;
            }
            a a = b.this.a();
            if (a == null) {
                int i = 1;
                do {
                    i++;
                    try {
                        String b = b.this.b();
                        a a2 = a.a(b);
                        b.this.d(b);
                        b.this.d = a2;
                        break;
                    } catch (WeiboException e) {
                        d.c("AidTask", "AidTaskInit WeiboException Msg : " + e.getMessage());
                        if (i >= 3) {
                        }
                    }
                } while (i >= 3);
            } else {
                b.this.d = a;
            }
            b.this.e.unlock();
        }
    }

    public a b(String str) throws WeiboException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        d.c("AidTask", "getAidSync ");
        if (this.d == null) {
            a(str);
        }
        return this.d;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0029 A[SYNTHETIC, Splitter:B:15:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0030 A[SYNTHETIC, Splitter:B:24:0x0030] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized com.sina.weibo.sdk.a.b.a a() {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 1
            r1 = 0
            java.io.File r0 = r4.a(r0)     // Catch:{ Exception -> 0x002d, all -> 0x0025 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x002d, all -> 0x0025 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x002d, all -> 0x0025 }
            int r0 = r2.available()     // Catch:{ Exception -> 0x002e, all -> 0x0023 }
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x002e, all -> 0x0023 }
            r2.read(r0)     // Catch:{ Exception -> 0x002e, all -> 0x0023 }
            java.lang.String r3 = new java.lang.String     // Catch:{ Exception -> 0x002e, all -> 0x0023 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x002e, all -> 0x0023 }
            com.sina.weibo.sdk.a.b$a r0 = com.sina.weibo.sdk.a.b.a.a(r3)     // Catch:{ Exception -> 0x002e, all -> 0x0023 }
            r2.close()     // Catch:{ IOException -> 0x0021 }
        L_0x0021:
            monitor-exit(r4)
            return r0
        L_0x0023:
            r0 = move-exception
            goto L_0x0027
        L_0x0025:
            r0 = move-exception
            r2 = r1
        L_0x0027:
            if (r2 == 0) goto L_0x002c
            r2.close()     // Catch:{ IOException -> 0x002c }
        L_0x002c:
            throw r0     // Catch:{ all -> 0x0034 }
        L_0x002d:
            r2 = r1
        L_0x002e:
            if (r2 == 0) goto L_0x0037
            r2.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x0037
        L_0x0034:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x0037:
            monitor-exit(r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.a.b.a():com.sina.weibo.sdk.a.b$a");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private File a(int i) {
        File filesDir = this.b.getFilesDir();
        return new File(filesDir, "weibo_sdk_aid" + i);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String b() throws WeiboException {
        String packageName = this.b.getPackageName();
        String a2 = k.a(this.b, packageName);
        String b = b(this.b);
        e eVar = new e(this.c);
        eVar.a("appkey", this.c);
        eVar.a("mfp", b);
        eVar.a("packagename", packageName);
        eVar.a("key_hash", a2);
        try {
            String b2 = com.sina.weibo.sdk.net.b.b(this.b, "https://api.weibo.com/oauth2/getaid.json", "GET", eVar);
            d.a("AidTask", "loadAidFromNet response : " + b2);
            return b2;
        } catch (WeiboException e) {
            d.a("AidTask", "loadAidFromNet WeiboException Msg : " + e.getMessage());
            throw e;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0027 A[SYNTHETIC, Splitter:B:19:0x0027] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void d(java.lang.String r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r3)
            return
        L_0x0009:
            r0 = 0
            r1 = 1
            java.io.File r1 = r3.a(r1)     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x002b, all -> 0x0024 }
            byte[] r4 = r4.getBytes()     // Catch:{ Exception -> 0x0022, all -> 0x001f }
            r2.write(r4)     // Catch:{ Exception -> 0x0022, all -> 0x001f }
            r2.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0030
        L_0x001f:
            r4 = move-exception
            r0 = r2
            goto L_0x0025
        L_0x0022:
            r0 = r2
            goto L_0x002b
        L_0x0024:
            r4 = move-exception
        L_0x0025:
            if (r0 == 0) goto L_0x002a
            r0.close()     // Catch:{ IOException -> 0x002a }
        L_0x002a:
            throw r4
        L_0x002b:
            if (r0 == 0) goto L_0x0030
            r0.close()
        L_0x0030:
            monitor-exit(r3)
            return
        L_0x0032:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.a.b.d(java.lang.String):void");
    }

    private static String b(Context context) {
        String str;
        try {
            str = new String(c(context).getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            str = "";
        }
        d.a("AidTask", "genMfpString() utf-8 string : " + str);
        try {
            String a2 = a(str, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHHM0Fi2Z6+QYKXqFUX2Cy6AaWq3cPi+GSn9oeAwQbPZR75JB7Netm0HtBVVbtPhzT7UO2p1JhFUKWqrqoYuAjkgMVPmA0sFrQohns5EE44Y86XQopD4ZO+dE5KjUZFE6vrPO3rWW3np2BqlgKpjnYZri6TJApmIpGcQg9/G/3zQIDAQAB");
            d.a("AidTask", "encryptRsa() string : " + a2);
            return a2;
        } catch (Exception e) {
            d.c("AidTask", e.getMessage());
            return "";
        }
    }

    private static String c(Context context) {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            String c2 = c();
            if (!TextUtils.isEmpty(c2)) {
                jSONObject.put("1", c2);
            }
            String d = d(context);
            if (!TextUtils.isEmpty(d)) {
                jSONObject.put("2", d);
            }
            String e = e(context);
            if (!TextUtils.isEmpty(e)) {
                jSONObject.put("3", e);
            }
            String f = f(context);
            if (!TextUtils.isEmpty(f)) {
                jSONObject.put("4", f);
            }
            String g = g(context);
            if (!TextUtils.isEmpty(g)) {
                jSONObject.put("5", g);
            }
            String h = h(context);
            if (!TextUtils.isEmpty(h)) {
                jSONObject.put(Constants.VIA_SHARE_TYPE_INFO, h);
            }
            String d2 = d();
            if (!TextUtils.isEmpty(d2)) {
                jSONObject.put("7", d2);
            }
            String i = i(context);
            if (!TextUtils.isEmpty(i)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, i);
            }
            String e2 = e();
            if (!TextUtils.isEmpty(e2)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_JOININ_GROUP, e2);
            }
            String f2 = f();
            if (!TextUtils.isEmpty(f2)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_MAKE_FRIEND, f2);
            }
            String g2 = g();
            if (!TextUtils.isEmpty(g2)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_WPA_STATE, g2);
            }
            String j = j(context);
            if (!TextUtils.isEmpty(j)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_START_WAP, j);
            }
            String k = k(context);
            if (!TextUtils.isEmpty(k)) {
                jSONObject.put(Constants.VIA_REPORT_TYPE_START_GROUP, k);
            }
            String h2 = h();
            if (!TextUtils.isEmpty(h2)) {
                jSONObject.put("18", h2);
            }
            String l = l(context);
            if (!TextUtils.isEmpty(l)) {
                jSONObject.put(Constants.VIA_ACT_TYPE_NINETEEN, l);
            }
            try {
                str = k.c(context);
            } catch (Exception e3) {
                e3.printStackTrace();
                str = "";
            }
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("20", str);
            }
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00b9 A[SYNTHETIC, Splitter:B:19:0x00b9] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String r8, java.lang.String r9) throws java.lang.Exception {
        /*
            java.lang.String r0 = "RSA/ECB/PKCS1Padding"
            javax.crypto.Cipher r0 = javax.crypto.Cipher.getInstance(r0)
            java.security.PublicKey r9 = e(r9)
            r1 = 1
            r0.init(r1, r9)
            java.lang.String r9 = "UTF-8"
            byte[] r8 = r8.getBytes(r9)
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x00b5 }
            r2.<init>()     // Catch:{ all -> 0x00b5 }
            r1 = 0
        L_0x001d:
            r3 = 117(0x75, float:1.64E-43)
            int r3 = a(r8, r1, r3)     // Catch:{ all -> 0x00b3 }
            r4 = -1
            java.lang.String r5 = "AidTask"
            if (r3 != r4) goto L_0x0084
            r2.flush()
            byte[] r8 = r2.toByteArray()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "encryptRsa total enBytes len = "
            r0.<init>(r1)
            int r1 = r8.length
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.sina.weibo.sdk.a.d.a(r5, r0)
            byte[] r8 = com.sina.weibo.sdk.a.c.b(r8)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "encryptRsa total base64byte len = "
            r0.<init>(r1)
            int r1 = r8.length
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.sina.weibo.sdk.a.d.a(r5, r0)
            java.lang.String r0 = new java.lang.String
            r0.<init>(r8, r9)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "01"
            r8.<init>(r9)
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r0 = "encryptRsa total base64string : "
            r9.<init>(r0)
            r9.append(r8)
            java.lang.String r9 = r9.toString()
            com.sina.weibo.sdk.a.d.a(r5, r9)
            r2.close()     // Catch:{ IOException -> 0x0083 }
        L_0x0083:
            return r8
        L_0x0084:
            byte[] r4 = r0.doFinal(r8, r1, r3)
            r2.write(r4)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "encryptRsa offset = "
            r6.<init>(r7)
            r6.append(r1)
            java.lang.String r7 = "     len = "
            r6.append(r7)
            r6.append(r3)
            java.lang.String r7 = "     enBytes len = "
            r6.append(r7)
            int r4 = r4.length
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            com.sina.weibo.sdk.a.d.a(r5, r4)
            int r1 = r1 + r3
            goto L_0x001d
        L_0x00b3:
            r8 = move-exception
            goto L_0x00b7
        L_0x00b5:
            r8 = move-exception
            r2 = r1
        L_0x00b7:
            if (r2 == 0) goto L_0x00bc
            r2.close()     // Catch:{ IOException -> 0x00bc }
        L_0x00bc:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.a.b.a(java.lang.String, java.lang.String):java.lang.String");
    }

    private static int a(byte[] bArr, int i, int i2) {
        if (i >= bArr.length) {
            return -1;
        }
        return Math.min(bArr.length - i, i2);
    }

    private static PublicKey e(String str) throws Exception {
        return KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_RSA).generatePublic(new X509EncodedKeySpec(c.a(str.getBytes())));
    }

    private static String c() {
        try {
            return "Android " + Build.VERSION.RELEASE;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String d(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String e(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String f(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String g(Context context) {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
                return "";
            }
            return connectionInfo.getMacAddress();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String h(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String d() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, "ro.serialno", "unknown");
        } catch (Exception unused) {
            return "";
        }
    }

    private static String i(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception unused) {
            return "";
        }
    }

    private static String e() {
        try {
            return Build.CPU_ABI;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String f() {
        try {
            return Build.MODEL;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String g() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return Long.toString(((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (Exception unused) {
            return "";
        }
    }

    private static String j(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
            return String.valueOf(String.valueOf(displayMetrics.widthPixels)) + PhoneConstants.APN_TYPE_ALL + String.valueOf(displayMetrics.heightPixels);
        } catch (Exception unused) {
            return "";
        }
    }

    private static String k(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getSSID() : "";
        } catch (Exception unused) {
            return "";
        }
    }

    private static String h() {
        try {
            return Build.BRAND;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String l(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "none";
            }
            if (activeNetworkInfo.getType() == 0) {
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return "2G";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return "3G";
                    case 13:
                        return "4G";
                    default:
                        return "none";
                }
            } else if (activeNetworkInfo.getType() == 1) {
                return "wifi";
            } else {
                return "none";
            }
        } catch (Exception unused) {
            return "none";
        }
    }
}
