package com.alipay.sdk.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import android.os.Process;
import android.os.UserHandle;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.b;
import com.alipay.sdk.app.c;
import com.alipay.sdk.b.a;
import com.android.internal.telephony.PhoneConstants;
import com.huawei.hms.framework.common.ContainerUtils;
import com.taobao.accs.utl.BaseMonitor;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class l {
    private static final String[] a = {"10.1.5.1013151", "10.1.5.1013148"};

    public static String g(Context context) {
        return "-1;-1";
    }

    static String a() {
        if (EnvUtils.a()) {
            return "com.eg.android.AlipayGphoneRC";
        }
        try {
            return com.alipay.sdk.app.a.a.get(0).a;
        } catch (Throwable unused) {
            return "com.eg.android.AlipayGphone";
        }
    }

    static String a(String str) {
        return (!EnvUtils.a() || !TextUtils.equals(str, "com.eg.android.AlipayGphoneRC")) ? "com.eg.android.AlipayGphone.IAlixPay" : "com.eg.android.AlipayGphoneRC.IAlixPay";
    }

    public static Map<String, String> b(String str) {
        HashMap hashMap = new HashMap();
        String[] split = str.split("&");
        for (String str2 : split) {
            int indexOf = str2.indexOf(ContainerUtils.KEY_VALUE_DELIMITER, 1);
            if (-1 != indexOf) {
                hashMap.put(str2.substring(0, indexOf), URLDecoder.decode(str2.substring(indexOf + 1)));
            }
        }
        return hashMap;
    }

    public static Map<String, String> a(com.alipay.sdk.g.a aVar, String str) {
        HashMap hashMap = new HashMap(4);
        int indexOf = str.indexOf(63);
        if (indexOf != -1 && indexOf < str.length() - 1) {
            String[] split = str.substring(indexOf + 1).split("&");
            for (String str2 : split) {
                int indexOf2 = str2.indexOf(61, 1);
                if (indexOf2 != -1 && indexOf2 < str2.length() - 1) {
                    hashMap.put(str2.substring(0, indexOf2), b(aVar, str2.substring(indexOf2 + 1)));
                }
            }
        }
        return hashMap;
    }

    public static JSONObject c(String str) {
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }

    public static String b(com.alipay.sdk.g.a aVar, String str) {
        try {
            return URLDecoder.decode(str, FileOpt.ENCODE_STR);
        } catch (UnsupportedEncodingException e) {
            com.alipay.sdk.app.a.a.a(aVar, "biz", "H5PayDataAnalysisError", e);
            return "";
        }
    }

    public static String a(String str, String str2, String str3) {
        try {
            int indexOf = str3.indexOf(str) + str.length();
            if (indexOf <= str.length()) {
                return "";
            }
            int i = 0;
            if (!TextUtils.isEmpty(str2)) {
                i = str3.indexOf(str2, indexOf);
            }
            if (i < 1) {
                return str3.substring(indexOf);
            }
            return str3.substring(indexOf, i);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(com.alipay.sdk.g.a aVar, byte[] bArr) {
        BigInteger modulus;
        try {
            PublicKey publicKey = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey();
            if (!(publicKey instanceof RSAPublicKey) || (modulus = ((RSAPublicKey) publicKey).getModulus()) == null) {
                return null;
            }
            return modulus.toString(16);
        } catch (Exception e) {
            com.alipay.sdk.app.a.a.a(aVar, BaseMonitor.ALARM_POINT_AUTH, "GetPublicKeyFromSignEx", e);
            return null;
        }
    }

    public static a a(com.alipay.sdk.g.a aVar, Context context, List<a.C0063a> list) {
        a a2;
        if (list == null) {
            return null;
        }
        for (a.C0063a aVar2 : list) {
            if (aVar2 != null && (a2 = a(aVar, context, aVar2.a, aVar2.b, aVar2.c)) != null && !a2.a(aVar) && !a2.a()) {
                return a2;
            }
        }
        return null;
    }

    private static a a(com.alipay.sdk.g.a aVar, Context context, String str, int i, String str2) {
        PackageInfo packageInfo;
        if (EnvUtils.a() && "com.eg.android.AlipayGphone".equals(str)) {
            str = "com.eg.android.AlipayGphoneRC";
        }
        try {
            packageInfo = b(context, str);
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a(aVar, BaseMonitor.ALARM_POINT_AUTH, "GetPackageInfoEx", th.getMessage());
            packageInfo = null;
        }
        if (!a(aVar, packageInfo)) {
            return null;
        }
        return a(packageInfo, i, str2);
    }

    private static boolean a(com.alipay.sdk.g.a aVar, PackageInfo packageInfo) {
        String str = "";
        boolean z = false;
        if (packageInfo == null) {
            str = str + "info == null";
        } else if (packageInfo.signatures == null) {
            str = str + "info.signatures == null";
        } else if (packageInfo.signatures.length <= 0) {
            str = str + "info.signatures.length <= 0";
        } else {
            z = true;
        }
        if (!z) {
            com.alipay.sdk.app.a.a.a(aVar, BaseMonitor.ALARM_POINT_AUTH, "NotIncludeSignatures", str);
        }
        return z;
    }

    private static PackageInfo b(Context context, String str) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, 192);
    }

    private static a a(PackageInfo packageInfo, int i, String str) {
        if (packageInfo == null) {
            return null;
        }
        return new a(packageInfo, i, str);
    }

    public static final class a {
        public final PackageInfo a;
        public final int b;
        public final String c;

        public a(PackageInfo packageInfo, int i, String str) {
            this.a = packageInfo;
            this.b = i;
            this.c = str;
        }

        public boolean a(com.alipay.sdk.g.a aVar) {
            Signature[] signatureArr = this.a.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return false;
            }
            for (Signature signature : signatureArr) {
                String a = l.a(aVar, signature.toByteArray());
                if (a != null && !TextUtils.equals(a, this.c)) {
                    com.alipay.sdk.app.a.a.a(aVar, "biz", "PublicKeyUnmatch", String.format("Got %s, expected %s", a, this.c));
                    return true;
                }
            }
            return false;
        }

        public boolean a() {
            return this.a.versionCode < this.b;
        }
    }

    public static boolean a(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo("com.alipay.android.app", 128) == null) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean b(com.alipay.sdk.g.a aVar, Context context, List<a.C0063a> list) {
        try {
            for (a.C0063a aVar2 : list) {
                if (aVar2 != null) {
                    String str = aVar2.a;
                    if (EnvUtils.a() && "com.eg.android.AlipayGphone".equals(str)) {
                        str = "com.eg.android.AlipayGphoneRC";
                    }
                    try {
                        if (context.getPackageManager().getPackageInfo(str, 128) != null) {
                            return true;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        continue;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a(aVar, "biz", "CheckLaunchAppExistEx", th);
            return false;
        }
    }

    static boolean a(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        try {
            String str = packageInfo.versionName;
            if (TextUtils.equals(str, a[0]) || TextUtils.equals(str, a[1])) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a(), 128);
            if (packageInfo != null && packageInfo.versionCode < 99) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            e.a(th);
            return false;
        }
    }

    public static String c(Context context) {
        String b = b();
        String c = c();
        String d = d(context);
        String e = e(context);
        return " (" + b + ";" + c + ";" + d + ";;" + e + com.umeng.message.proguard.l.t + "(sdk android)";
    }

    public static String b() {
        return "Android " + Build.VERSION.RELEASE;
    }

    public static String c() {
        String f = f();
        int indexOf = f.indexOf("-");
        if (indexOf != -1) {
            f = f.substring(0, indexOf);
        }
        int indexOf2 = f.indexOf("\n");
        if (indexOf2 != -1) {
            f = f.substring(0, indexOf2);
        }
        return "Linux " + f;
    }

    /* JADX INFO: finally extract failed */
    private static String f() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(readLine);
                if (!matcher.matches() || matcher.groupCount() < 4) {
                    return "Unavailable";
                }
                return matcher.group(1) + "\n" + matcher.group(2) + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + matcher.group(3) + "\n" + matcher.group(4);
            } catch (Throwable th) {
                bufferedReader.close();
                throw th;
            }
        } catch (IOException unused) {
            return "Unavailable";
        }
    }

    public static String d(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static String e(Context context) {
        DisplayMetrics i = i(context);
        return i.widthPixels + PhoneConstants.APN_TYPE_ALL + i.heightPixels;
    }

    private static DisplayMetrics i(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String f(Context context) {
        String a2 = k.a(context);
        return a2.substring(0, a2.indexOf(HttpConstant.SCHEME_SPLIT));
    }

    public static String a(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            int nextInt = random.nextInt(3);
            if (nextInt == 0) {
                sb.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 65.0d))));
            } else if (nextInt == 1) {
                sb.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 97.0d))));
            } else if (nextInt == 2) {
                sb.append(String.valueOf(new Random().nextInt(10)));
            }
        }
        return sb.toString();
    }

    public static boolean d(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    static String a(Context context, String str) {
        String str2 = "";
        try {
            String str3 = str2;
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    str3 = str3 + "#M";
                } else {
                    if (runningAppProcessInfo.processName.startsWith(str + ":")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str3);
                        sb.append("#");
                        sb.append(runningAppProcessInfo.processName.replace(str + ":", str2));
                        str3 = sb.toString();
                    }
                }
            }
            str2 = str3;
        } catch (Throwable unused) {
        }
        if (str2.length() > 0) {
            str2 = str2.substring(1);
        }
        return str2.length() == 0 ? "N" : str2;
    }

    public static boolean a(com.alipay.sdk.g.a aVar, String str, Activity activity) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (activity == null) {
            return false;
        }
        if (str.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase()) || str.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase())) {
            try {
                a a2 = a(aVar, activity, com.alipay.sdk.app.a.a);
                if (a2 != null && !a2.a()) {
                    if (!a2.a(aVar)) {
                        if (str.startsWith("intent://platformapi/startapp")) {
                            str = str.replaceFirst("intent://platformapi/startapp\\?", "alipays://platformapi/startApp?");
                        }
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    }
                }
            } catch (Throwable unused) {
            }
            return true;
        } else if (TextUtils.equals(str, "sdklite://h5quit") || TextUtils.equals(str, "http://m.alipay.com/?action=h5quit")) {
            b.a(b.c());
            activity.finish();
            return true;
        } else if (!str.startsWith("sdklite://h5quit?result=")) {
            return false;
        } else {
            try {
                String substring = str.substring(str.indexOf("sdklite://h5quit?result=") + 24);
                int parseInt = Integer.parseInt(substring.substring(substring.lastIndexOf("&end_code=") + 10));
                if (parseInt != c.SUCCEEDED.a()) {
                    if (parseInt != c.PAY_WAITTING.a()) {
                        c b = c.b(c.FAILED.a());
                        b.a(b.a(b.a(), b.b(), ""));
                        activity.runOnUiThread(new AnonymousClass1(activity));
                        return true;
                    }
                }
                if (com.alipay.sdk.a.a.c) {
                    StringBuilder sb = new StringBuilder();
                    String decode = URLDecoder.decode(str);
                    String decode2 = URLDecoder.decode(decode);
                    String str3 = decode2.substring(decode2.indexOf("sdklite://h5quit?result=") + 24, decode2.lastIndexOf("&end_code=")).split("&return_url=")[0];
                    int indexOf = decode.indexOf("&return_url=") + 12;
                    sb.append(str3);
                    sb.append("&return_url=");
                    sb.append(decode.substring(indexOf, decode.indexOf("&", indexOf)));
                    sb.append(decode.substring(decode.indexOf("&", indexOf)));
                    str2 = sb.toString();
                } else {
                    String decode3 = URLDecoder.decode(str);
                    str2 = decode3.substring(decode3.indexOf("sdklite://h5quit?result=") + 24, decode3.lastIndexOf("&end_code="));
                }
                c b2 = c.b(parseInt);
                b.a(b.a(b2.a(), b2.b(), str2));
            } catch (Exception unused2) {
                b.a(b.e());
            }
            activity.runOnUiThread(new AnonymousClass1(activity));
            return true;
        }
    }

    /* renamed from: com.alipay.sdk.util.l$1  reason: invalid class name */
    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Activity a;

        AnonymousClass1(Activity activity) {
            this.a = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.finish();
        }
    }

    public static String e(String str) {
        try {
            Uri parse = Uri.parse(str);
            return String.format("%s%s", parse.getAuthority(), parse.getPath());
        } catch (Throwable th) {
            e.a(th);
            return "-";
        }
    }

    public static String f(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            instance.update(str.getBytes());
            return a(instance.digest());
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(Character.forDigit((b & 240) >> 4, 16));
            sb.append(Character.forDigit(b & 15, 16));
        }
        return sb.toString();
    }

    public static int d() {
        try {
            return Process.myUid();
        } catch (Throwable th) {
            e.a(th);
            return ErrorConstant.ERROR_NO_NETWORK;
        }
    }

    public static ActivityInfo h(Context context) {
        try {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
                for (ActivityInfo activityInfo : activityInfoArr) {
                    if (TextUtils.equals(activityInfo.name, activity.getClass().getName())) {
                        return activityInfo;
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            e.a(th);
            return null;
        }
    }

    public static int b(int i) {
        return i / UserHandle.PER_USER_RANGE;
    }

    public static boolean e() {
        try {
            String[] split = com.alipay.sdk.b.a.p().j().split("\\|");
            String str = Build.MODEL;
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            for (String str2 : split) {
                if (TextUtils.equals(str, str2)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            e.a(th);
            return false;
        }
    }

    public static String a(com.alipay.sdk.g.a aVar) {
        return c(aVar, "ro.build.fingerprint");
    }

    public static String c(com.alipay.sdk.g.a aVar, String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e) {
            com.alipay.sdk.app.a.a.a(aVar, "biz", "rflex", e.getClass().getSimpleName());
            return null;
        }
    }
}
