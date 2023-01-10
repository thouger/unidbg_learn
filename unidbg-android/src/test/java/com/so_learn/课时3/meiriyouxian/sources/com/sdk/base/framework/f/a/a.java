package com.sdk.base.framework.f.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyProperties;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.a.a.c;
import com.sdk.base.framework.c.f;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.Stack;

public class a extends com.sdk.base.framework.f.a {
    private static final String a = a.class.getName();
    private static boolean b = f.a;

    static {
        AppMethodBeat.i(19614, false);
        new Stack();
        AppMethodBeat.o(19614);
    }

    public static String a() {
        AppMethodBeat.i(19593, false);
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                            String str = nextElement.getHostAddress().toString();
                            AppMethodBeat.o(19593);
                            return str;
                        }
                    }
                }
            }
        } catch (SocketException unused) {
        }
        AppMethodBeat.o(19593);
        return "null";
    }

    public static String a(Context context) {
        AppMethodBeat.i(19597, false);
        String str = null;
        if (context == null) {
            AppMethodBeat.o(19597);
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                str = packageInfo.versionName;
            }
        } catch (Exception e) {
            c.b(a, e.getMessage(), Boolean.valueOf(b));
        }
        AppMethodBeat.o(19597);
        return str;
    }

    public static String a(Context context, String str) {
        AppMethodBeat.i(19600, false);
        String str2 = (String) b(context, str);
        if (c.a(str2).booleanValue()) {
            str2 = com.sdk.base.framework.f.b.a.a(context, "api_key");
        }
        AppMethodBeat.o(19600);
        return str2;
    }

    public static int b() {
        int i;
        AppMethodBeat.i(19607, false);
        try {
            i = Build.VERSION.SDK_INT;
        } catch (Exception e) {
            c.b(a, e.getMessage(), Boolean.valueOf(b));
            i = -1;
        }
        AppMethodBeat.o(19607);
        return i;
    }

    public static int b(Context context) {
        AppMethodBeat.i(19602, false);
        int i = -1;
        if (context == null) {
            AppMethodBeat.o(19602);
            return -1;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(c(context), 1);
            if (packageInfo != null) {
                i = packageInfo.versionCode;
            }
        } catch (Exception e) {
            c.b(a, e.getMessage(), Boolean.valueOf(b));
        }
        AppMethodBeat.o(19602);
        return i;
    }

    private static <T> T b(Context context, String str) {
        Bundle bundle;
        AppMethodBeat.i(19599, false);
        T t = null;
        if (context == null || c.a(str).booleanValue()) {
            AppMethodBeat.o(19599);
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(c(context), 128);
            if (!(applicationInfo == null || (bundle = applicationInfo.metaData) == null)) {
                t = bundle.get(str);
            }
        } catch (Exception e) {
            c.a(a, e.getMessage(), Boolean.valueOf(b));
        }
        AppMethodBeat.o(19599);
        return t;
    }

    public static String c(Context context) {
        AppMethodBeat.i(19605, false);
        if (context == null) {
            c.c(a, "mContext \u4e3a\u7a7a", Boolean.valueOf(b));
        } else {
            try {
                String packageName = context.getPackageName();
                AppMethodBeat.o(19605);
                return packageName;
            } catch (Exception e) {
                c.b(a, e.getMessage(), Boolean.valueOf(b));
            }
        }
        AppMethodBeat.o(19605);
        return null;
    }

    public static boolean c() {
        return false;
    }

    public static String d(Context context) {
        AppMethodBeat.i(19609, false);
        String str = null;
        if (context == null) {
            a(a, "getAppLable", "mContext \u4e3a\u7a7a", b);
            AppMethodBeat.o(19609);
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            str = packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 128)).toString();
        } catch (Exception e) {
            c.b(a, e.getMessage(), Boolean.valueOf(b));
        }
        AppMethodBeat.o(19609);
        return str;
    }

    public static String e(Context context) {
        PackageInfo packageInfo;
        String str;
        AppMethodBeat.i(19611, false);
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException e) {
            c.b(a, e.getMessage(), Boolean.valueOf(b));
            packageInfo = null;
        }
        if (packageInfo != null) {
            int i = packageInfo.applicationInfo.flags;
            try {
                byte[] byteArray = packageInfo.signatures[0].toByteArray();
                MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
                instance.update(byteArray);
                byte[] digest = instance.digest();
                str = "";
                for (int i2 = 0; i2 < digest.length; i2++) {
                    if (i2 != 0) {
                        str = str + ":";
                    }
                    String hexString = Integer.toHexString(digest[i2] & 255);
                    if (hexString.length() == 1) {
                        str = str + "0";
                    }
                    str = str + hexString;
                }
            } catch (Exception e2) {
                c.b(a, e2.getMessage(), Boolean.valueOf(b));
            }
            AppMethodBeat.o(19611);
            return str;
        }
        str = null;
        AppMethodBeat.o(19611);
        return str;
    }
}
