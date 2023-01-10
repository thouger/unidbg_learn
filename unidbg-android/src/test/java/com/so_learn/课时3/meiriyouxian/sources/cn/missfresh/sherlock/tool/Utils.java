package cn.missfresh.sherlock.tool;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.security.keystore.KeyProperties;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.umeng.message.proguard.l;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

public class Utils {
    private static String a;
    private static String b;

    public enum NetworkType {
        NETWORK_ETHERNET,
        NETWORK_WIFI,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r3) {
        /*
            java.lang.String r0 = cn.missfresh.sherlock.e.k()     // Catch:{ Exception -> 0x001c }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x001a }
            if (r1 == 0) goto L_0x0021
            android.content.pm.PackageManager r1 = r3.getPackageManager()     // Catch:{ Exception -> 0x001a }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ Exception -> 0x001a }
            r2 = 0
            android.content.pm.PackageInfo r3 = r1.getPackageInfo(r3, r2)     // Catch:{ Exception -> 0x001a }
            java.lang.String r0 = r3.versionName     // Catch:{ Exception -> 0x001a }
            goto L_0x0021
        L_0x001a:
            r3 = move-exception
            goto L_0x001e
        L_0x001c:
            r3 = move-exception
            r0 = 0
        L_0x001e:
            r3.printStackTrace()
        L_0x0021:
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L_0x002a
            java.lang.String r0 = "unknown"
        L_0x002a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.sherlock.tool.Utils.a(android.content.Context):java.lang.String");
    }

    public static String b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationInfo(context.getPackageName(), 0).loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "unknown";
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static NetworkType c(Context context) {
        if (j(context)) {
            return NetworkType.NETWORK_ETHERNET;
        }
        NetworkInfo i = i(context);
        if (i != null && i.isAvailable()) {
            if (i.getType() == 1) {
                return NetworkType.NETWORK_WIFI;
            }
            if (i.getType() == 0) {
                switch (i.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                    case 16:
                        return NetworkType.NETWORK_2G;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                    case 17:
                        return NetworkType.NETWORK_3G;
                    case 13:
                    case 18:
                        return NetworkType.NETWORK_4G;
                    default:
                        String subtypeName = i.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            return NetworkType.NETWORK_3G;
                        }
                }
            }
        }
        return NetworkType.NETWORK_UNKNOWN;
    }

    public static String c() {
        return Build.VERSION.RELEASE;
    }

    public static String d(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        return telephonyManager.getNetworkOperatorName();
    }

    public static String e(Context context) {
        try {
            if (!TextUtils.isEmpty(b)) {
                return b;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                b = f(context);
                return b;
            }
            TelephonyManager k = k(context);
            if (Build.VERSION.SDK_INT >= 26) {
                b = k.getImei();
                return b;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                Method declaredMethod = k.getClass().getDeclaredMethod("getImei", new Class[0]);
                declaredMethod.setAccessible(true);
                String str = (String) declaredMethod.invoke(k, new Object[0]);
                if (str != null) {
                    b = str;
                    return b;
                }
            }
            String deviceId = k.getDeviceId();
            if (deviceId != null && deviceId.length() == 15) {
                b = deviceId;
                return b;
            }
            b = "unknown";
            return b;
        } catch (Exception e) {
            j.a("Utils", e.getMessage());
        }
    }

    public static String f(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public static boolean h(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (!(activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null || runningAppProcesses.size() == 0)) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(context.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static NetworkInfo i(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    private static boolean j(Context context) {
        NetworkInfo networkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(9)) == null || (state = networkInfo.getState()) == null) {
            return false;
        }
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return true;
        }
        return false;
    }

    private static TelephonyManager k(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    private static String g() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                            return nextElement.getHostAddress();
                        }
                    }
                }
            }
            return "0.0.0.0";
        } catch (SocketException unused) {
            return "0.0.0.0";
        }
    }

    public static String g(Context context) {
        try {
            if (!TextUtils.isEmpty(a)) {
                return a;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getType() == 0) {
                    try {
                        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                        while (networkInterfaces.hasMoreElements()) {
                            Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                            while (true) {
                                if (inetAddresses.hasMoreElements()) {
                                    InetAddress nextElement = inetAddresses.nextElement();
                                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                                        a = nextElement.getHostAddress();
                                        return a;
                                    }
                                }
                            }
                        }
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                } else if (activeNetworkInfo.getType() == 1) {
                    a = a(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress());
                    return a;
                } else if (activeNetworkInfo.getType() == 9) {
                    a = g();
                    return a;
                }
            }
            a = "unknown";
            return a;
        } catch (Exception e2) {
            j.a("Utils", "getIpAddress" + e2.getMessage());
        }
    }

    private static String a(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static String f() {
        return a(new Throwable().getStackTrace());
    }

    public static String a(float f, float f2) {
        NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(2);
        return instance.format((double) ((f / f2) * 100.0f));
    }

    public static String b() {
        return Build.MANUFACTURER;
    }

    public static String e() {
        return UUID.randomUUID().toString();
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] digest = MessageDigest.getInstance(KeyProperties.DIGEST_MD5).digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b2 : digest) {
                int i = b2 & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("UnsupportedEncodingException", e2);
        }
    }

    public static String a() {
        String str = Build.MODEL;
        String str2 = "";
        if (str != null) {
            str2 = str.trim().replaceAll("\\s*", str2);
        }
        return b() + str2;
    }

    public static boolean b(String str) {
        return str == null || str.equals("");
    }

    public static String a(StackTraceElement[] stackTraceElementArr) {
        return a(stackTraceElementArr, "", -1);
    }

    public static String a(StackTraceElement[] stackTraceElementArr, String str, int i) {
        if (stackTraceElementArr == null || stackTraceElementArr.length < 3) {
            return "";
        }
        if (i < 0) {
            i = Integer.MAX_VALUE;
        }
        StringBuilder sb = new StringBuilder(" \n");
        int i2 = 3;
        while (i2 < stackTraceElementArr.length - 3 && i2 < i) {
            sb.append(str);
            sb.append("at ");
            sb.append(stackTraceElementArr[i2].getClassName());
            sb.append(":");
            sb.append(stackTraceElementArr[i2].getMethodName());
            sb.append(l.s + stackTraceElementArr[i2].getLineNumber() + l.t);
            sb.append("\n");
            i2++;
        }
        return sb.toString();
    }

    public static String d() {
        String valueOf = String.valueOf(System.currentTimeMillis());
        String e = e();
        try {
            String a2 = a(valueOf.substring(5, valueOf.length()) + e.substring(0, 8));
            if (!TextUtils.isEmpty(a2)) {
                return a2.substring(8, 24);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return valueOf + e.substring(0, 3);
    }

    public static <T> T a(Object obj, String str) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return (T) declaredField.get(obj);
        } catch (Exception e) {
            j.e("Utils", e.toString());
            return null;
        }
    }

    public static String a(long j, long j2) {
        if (j <= 0) {
            return j2 > 1000 ? "0%" : "100%";
        }
        if (j >= j2) {
            return "100%";
        }
        return String.format("%.2f", Float.valueOf(((((float) j) * 1.0f) / ((float) j2)) * 100.0f)) + "%";
    }

    public static boolean a(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
