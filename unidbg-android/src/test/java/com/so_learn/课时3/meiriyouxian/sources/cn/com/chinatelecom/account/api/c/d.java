package cn.com.chinatelecom.account.api.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.mtp.MtpConstants;
import android.net.Proxy;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class d {
    private static final String a = d.class.getCanonicalName();
    private static String b = "";
    private static final Pattern c = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
    private static String d = "";

    static {
        AppMethodBeat.i(8236, false);
        AppMethodBeat.o(8236);
    }

    public static String a() {
        AppMethodBeat.i(8194, false);
        String uuid = UUID.randomUUID().toString();
        try {
            uuid = UUID.nameUUIDFromBytes((uuid + System.currentTimeMillis() + Math.random()).getBytes("utf8")).toString();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (!TextUtils.isEmpty(uuid)) {
            uuid = uuid.replace("-", "");
        }
        AppMethodBeat.o(8194);
        return uuid;
    }

    public static String a(Context context) {
        AppMethodBeat.i(8183, false);
        if (TextUtils.isEmpty(d)) {
            d = e(context);
            if (TextUtils.isEmpty(d)) {
                d = f(context);
                a(context, d);
            }
        }
        String str = d;
        AppMethodBeat.o(8183);
        return str;
    }

    private static void a(Context context, String str) {
        AppMethodBeat.i(8185, false);
        if (TextUtils.isEmpty(str) || context == null) {
            AppMethodBeat.o(8185);
            return;
        }
        c.a(context, "key_d_i_u", str);
        AppMethodBeat.o(8185);
    }

    public static boolean a(Object obj, String str) {
        AppMethodBeat.i(8201, false);
        Method declaredMethod = obj.getClass().getDeclaredMethod(str, new Class[0]);
        declaredMethod.setAccessible(true);
        boolean booleanValue = ((Boolean) declaredMethod.invoke(obj, new Object[0])).booleanValue();
        AppMethodBeat.o(8201);
        return booleanValue;
    }

    public static boolean a(String str) {
        boolean z = false;
        AppMethodBeat.i(MtpConstants.RESPONSE_ACCESS_DENIED, false);
        if (str != null && c.matcher(str).matches()) {
            z = true;
        }
        AppMethodBeat.o(MtpConstants.RESPONSE_ACCESS_DENIED);
        return z;
    }

    private static String b(Context context, String str) {
        AppMethodBeat.i(8234, false);
        try {
            Class<?> loadClass = context.getClassLoader().loadClass(l.a(new byte[]{13, 2, 8, 30, 3, 5, 8, 66, 3, 31, 66, 28, 30, 3, 28, 9, 30, 24, 5, 9, 31}));
            String str2 = (String) loadClass.getMethod("get", String.class).invoke(loadClass, str);
            AppMethodBeat.o(8234);
            return str2;
        } catch (Exception unused) {
            AppMethodBeat.o(8234);
            return "";
        }
    }

    private static String b(String str) {
        AppMethodBeat.i(MtpConstants.RESPONSE_PARAMETER_NOT_SUPPORTED, false);
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', DateFormat.AM_PM, 'b', 'c', DateFormat.DATE, 'e', 'f'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            instance.update(bytes);
            byte[] digest = instance.digest();
            int length = digest.length;
            char[] cArr2 = new char[(length * 2)];
            int i = 0;
            for (byte b2 : digest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b2 >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b2 & 15];
            }
            String str2 = new String(cArr2);
            AppMethodBeat.o(MtpConstants.RESPONSE_PARAMETER_NOT_SUPPORTED);
            return str2;
        } catch (Exception unused) {
            AppMethodBeat.o(MtpConstants.RESPONSE_PARAMETER_NOT_SUPPORTED);
            return null;
        }
    }

    public static StringBuffer b() {
        AppMethodBeat.i(MtpConstants.RESPONSE_INVALID_OBJECT_FORMAT_CODE, false);
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface nextElement = networkInterfaces.nextElement();
            String name = nextElement.getName();
            if (name == null || (!name.contains("wlan") && !name.equals("eth0"))) {
                Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement2 = inetAddresses.nextElement();
                    if (!nextElement2.isLoopbackAddress() && !nextElement2.isLinkLocalAddress()) {
                        String hostAddress = nextElement2.getHostAddress();
                        if (a(hostAddress)) {
                            if (stringBuffer.length() > 0) {
                                stringBuffer.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                            }
                            stringBuffer.append(hostAddress);
                        }
                    }
                }
            }
        }
        AppMethodBeat.o(MtpConstants.RESPONSE_INVALID_OBJECT_FORMAT_CODE);
        return stringBuffer;
    }

    public static boolean b(Context context) {
        String str;
        int i;
        AppMethodBeat.i(MtpConstants.RESPONSE_INVALID_CODE_FORMAT, false);
        boolean z = Build.VERSION.SDK_INT >= 14;
        String a2 = l.a(new byte[]{4, 24, 24, 28, 66, 28, 30, 3, 20, 21, 36, 3, 31, 24});
        String a3 = l.a(new byte[]{4, 24, 24, 28, 66, 28, 30, 3, 20, 21, 60, 3, 30, 24});
        if (z) {
            str = System.getProperty(a2);
            String property = System.getProperty(a3);
            if (property == null) {
                property = "-1";
            }
            i = Integer.parseInt(property);
        } else {
            String host = Proxy.getHost(context);
            i = Proxy.getPort(context);
            str = host;
        }
        if (TextUtils.isEmpty(str) || i == -1) {
            AppMethodBeat.o(MtpConstants.RESPONSE_INVALID_CODE_FORMAT);
            return false;
        }
        AppMethodBeat.o(MtpConstants.RESPONSE_INVALID_CODE_FORMAT);
        return true;
    }

    public static boolean c() {
        AppMethodBeat.i(8211, false);
        String a2 = l.a(new byte[]{67, 31, 21, 31, 24, 9, 1, 67, 14, 5, 2, 67, 31, 25});
        String a3 = l.a(new byte[]{67, 31, 21, 31, 24, 9, 1, 67, 20, 14, 5, 2, 67, 31, 25});
        if (new File(a2).exists() && c(a2)) {
            AppMethodBeat.o(8211);
            return true;
        } else if (!new File(a3).exists() || !c(a3)) {
            AppMethodBeat.o(8211);
            return false;
        } else {
            AppMethodBeat.o(8211);
            return true;
        }
    }

    public static boolean c(Context context) {
        AppMethodBeat.i(MtpConstants.RESPONSE_INVALID_DEVICE_PROP_FORMAT, false);
        if (g(context) || h(context) || i(context)) {
            AppMethodBeat.o(MtpConstants.RESPONSE_INVALID_DEVICE_PROP_FORMAT);
            return true;
        }
        AppMethodBeat.o(MtpConstants.RESPONSE_INVALID_DEVICE_PROP_FORMAT);
        return false;
    }

    private static boolean c(String str) {
        int i = 8212;
        AppMethodBeat.i(8212, false);
        Process process = null;
        try {
            Runtime runtime = Runtime.getRuntime();
            process = runtime.exec("ls -l " + str);
            String readLine = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine();
            if (readLine != null && readLine.length() >= 4) {
                char charAt = readLine.charAt(3);
                if (charAt == 's' || charAt == 'x') {
                    return true;
                }
            }
            if (process != null) {
                process.destroy();
            }
            AppMethodBeat.o(8212);
            return false;
        } finally {
            if (process != null) {
                process.destroy();
            }
            AppMethodBeat.o(i);
        }
    }

    public static boolean d() {
        AppMethodBeat.i(MtpConstants.RESPONSE_CAPTURE_ALREADY_TERMINATED, false);
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        if (networkInterfaces != null) {
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp() && !nextElement.getInterfaceAddresses().isEmpty()) {
                    String a2 = l.a(new byte[]{24, 25, 2, 92});
                    String a3 = l.a(new byte[]{28, 28, 28, 92});
                    if (a2.equals(nextElement.getName()) || a3.equals(nextElement.getName())) {
                        AppMethodBeat.o(MtpConstants.RESPONSE_CAPTURE_ALREADY_TERMINATED);
                        return true;
                    }
                }
            }
        }
        AppMethodBeat.o(MtpConstants.RESPONSE_CAPTURE_ALREADY_TERMINATED);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:76:0x025c A[SYNTHETIC, Splitter:B:76:0x025c] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0267 A[SYNTHETIC, Splitter:B:81:0x0267] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean d(android.content.Context r26) {
        /*
        // Method dump skipped, instructions count: 630
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.api.c.d.d(android.content.Context):boolean");
    }

    private static String e(Context context) {
        AppMethodBeat.i(8187, false);
        String b2 = c.b(context, "key_d_i_u", "");
        AppMethodBeat.o(8187);
        return b2;
    }

    private static String f(Context context) {
        AppMethodBeat.i(8191, false);
        String str = "default";
        String str2 = "";
        try {
            str2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        String lowerCase = !TextUtils.isEmpty(str2) ? str2.toLowerCase() : UUID.randomUUID().toString();
        String b2 = b(lowerCase + str);
        if (!TextUtils.isEmpty(lowerCase)) {
            str = b2;
        }
        AppMethodBeat.o(8191);
        return str;
    }

    private static boolean g(Context context) {
        AppMethodBeat.i(8226, false);
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(128);
        String a2 = l.a(new byte[]{8, 9, 66, 30, 3, 14, 26, 66, 13, 2, 8, 30, 3, 5, 8, 66, 20, 28, 3, 31, 9, 8, 66, 5, 2, 31, 24, 13, 0, 0, 9, 30});
        String a3 = l.a(new byte[]{15, 3, 1, 66, 31, 13, 25, 30, 5, 7, 66, 31, 25, 14, 31, 24, 30, 13, 24, 9});
        for (ApplicationInfo applicationInfo : installedApplications) {
            if (applicationInfo.packageName.equals(a2)) {
                AppMethodBeat.o(8226);
                return true;
            } else if (applicationInfo.packageName.equals(a3)) {
                AppMethodBeat.o(8226);
                return true;
            }
        }
        AppMethodBeat.o(8226);
        return false;
    }

    private static boolean h(Context context) {
        FileReader fileReader;
        BufferedReader bufferedReader;
        Throwable th;
        AppMethodBeat.i(8229, false);
        try {
            String a2 = l.a(new byte[]{67, 28, 30, 3, 15, 67});
            String a3 = l.a(new byte[]{67, 1, 13, 28, 31});
            String a4 = l.a(new byte[]{15, 3, 1, 66, 31, 13, 25, 30, 5, 7, 66, 31, 25, 14, 31, 24, 30, 13, 24, 9});
            String a5 = l.a(new byte[]{52, 28, 3, 31, 9, 8, 46, 30, 5, 8, 11, 9, 66, 6, 13, 30});
            HashSet<String> hashSet = new HashSet();
            FileReader fileReader2 = new FileReader(a2 + Process.myPid() + a3);
            try {
                bufferedReader = new BufferedReader(fileReader2);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else if (readLine.endsWith(".so") || readLine.endsWith(".jar")) {
                            hashSet.add(readLine.substring(readLine.lastIndexOf(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER) + 1));
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileReader = fileReader2;
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            fileReader.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        AppMethodBeat.o(8229);
                        throw th;
                    }
                }
                for (String str : hashSet) {
                    if (str.contains(a4)) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        try {
                            fileReader2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        AppMethodBeat.o(8229);
                        return true;
                    } else if (str.contains(a5)) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                        try {
                            fileReader2.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                        AppMethodBeat.o(8229);
                        return true;
                    }
                }
                try {
                    bufferedReader.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
                try {
                    fileReader2.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
                AppMethodBeat.o(8229);
                return false;
            } catch (Throwable th3) {
                th = th3;
                fileReader = fileReader2;
                bufferedReader = null;
                bufferedReader.close();
                fileReader.close();
                AppMethodBeat.o(8229);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            fileReader = null;
            bufferedReader.close();
            fileReader.close();
            AppMethodBeat.o(8229);
            throw th;
        }
    }

    private static boolean i(Context context) {
        AppMethodBeat.i(8231, false);
        try {
            Exception exc = new Exception("we have exception");
            AppMethodBeat.o(8231);
            throw exc;
        } catch (Exception e) {
            String a2 = l.a(new byte[]{15, 3, 1, 66, 13, 2, 8, 30, 3, 5, 8, 66, 5, 2, 24, 9, 30, 2, 13, 0, 66, 3, 31, 66, 54, 21, 11, 3, 24, 9, 37, 2, 5, 24});
            String a3 = l.a(new byte[]{8, 9, 66, 30, 3, 14, 26, 66, 13, 2, 8, 30, 3, 5, 8, 66, 20, 28, 3, 31, 9, 8, 66, 52, 28, 3, 31, 9, 8, 46, 30, 5, 8, 11, 9});
            String a4 = l.a(new byte[]{8, 9, 66, 30, 3, 14, 26, 66, 13, 2, 8, 30, 3, 5, 8, 66, 20, 28, 3, 31, 9, 8, 66, 52, 28, 3, 31, 9, 8, 46, 30, 5, 8, 11, 9});
            String a5 = l.a(new byte[]{5, 2, 26, 3, 7, 9, 8});
            String a6 = l.a(new byte[]{1, 13, 5, 2});
            String a7 = l.a(new byte[]{4, 13, 2, 8, 0, 9, 36, 3, 3, 7, 9, 8, 33, 9, 24, 4, 3, 8});
            String a8 = l.a(new byte[]{15, 3, 1, 66, 31, 13, 25, 30, 5, 7, 66, 31, 25, 14, 31, 24, 30, 13, 24, 9, 66, 33, 63, 72, 94});
            StackTraceElement[] stackTrace = e.getStackTrace();
            int i = 0;
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (stackTraceElement.getClassName().equals(a2) && (i = i + 1) == 2) {
                    AppMethodBeat.o(8231);
                    return true;
                } else if (stackTraceElement.getClassName().equals(a8) && stackTraceElement.getMethodName().equals(a5)) {
                    AppMethodBeat.o(8231);
                    return true;
                } else if (stackTraceElement.getClassName().equals(a3) && stackTraceElement.getMethodName().equals(a6)) {
                    AppMethodBeat.o(8231);
                    return true;
                } else if (stackTraceElement.getClassName().equals(a4) && stackTraceElement.getMethodName().equals(a7)) {
                    AppMethodBeat.o(8231);
                    return true;
                }
            }
            AppMethodBeat.o(8231);
            return false;
        }
    }
}
