package com.sobot.chat.utils;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: CommonUtils */
public class d {
    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0040 A[SYNTHETIC, Splitter:B:24:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x004b A[SYNTHETIC, Splitter:B:30:0x004b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long a(java.lang.String r4) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            r1 = 0
            r4 = 0
            boolean r3 = r0.exists()     // Catch:{ Exception -> 0x003a }
            if (r3 == 0) goto L_0x0022
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x003a }
            r3.<init>(r0)     // Catch:{ Exception -> 0x003a }
            int r4 = r3.available()     // Catch:{ Exception -> 0x001e, all -> 0x001a }
            long r1 = (long) r4
            r4 = r3
            goto L_0x002d
        L_0x001a:
            r4 = move-exception
            r0 = r4
            r4 = r3
            goto L_0x0049
        L_0x001e:
            r4 = move-exception
            r0 = r4
            r4 = r3
            goto L_0x003b
        L_0x0022:
            r0.createNewFile()
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.String r3 = "\u6587\u4ef6\u4e0d\u5b58\u5728"
            r0.println(r3)
        L_0x002d:
            if (r4 == 0) goto L_0x0037
            r4.close()     // Catch:{ IOException -> 0x0033 }
            goto L_0x0037
        L_0x0033:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0037:
            return r1
        L_0x0038:
            r0 = move-exception
            goto L_0x0049
        L_0x003a:
            r0 = move-exception
        L_0x003b:
            r0.printStackTrace()     // Catch:{ all -> 0x0038 }
            if (r4 == 0) goto L_0x0048
            r4.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0048:
            return r1
        L_0x0049:
            if (r4 == 0) goto L_0x0053
            r4.close()     // Catch:{ IOException -> 0x004f }
            goto L_0x0053
        L_0x004f:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0053:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.d.a(java.lang.String):long");
    }

    public static boolean a() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String b(Context context) {
        String str;
        if (a()) {
            if (Build.VERSION.SDK_INT < 29 || Environment.isExternalStorageLegacy()) {
                str = c(context) + File.separator + "cache" + File.separator;
            } else {
                str = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator + "cache" + File.separator;
            }
            m.d("SD\u5361\u5df2\u88c5\u5165 \u5b58\u50a8\u8def\u5f84\uff1a" + str);
            return str;
        }
        String str2 = context.getFilesDir().getPath() + File.separator + "sobot";
        m.d("\u5916\u90e8\u5b58\u50a8\u4e0d\u53ef\u7528 \u5b58\u50a8\u8def\u5f84\uff1a" + str2);
        return str2;
    }

    public static String c(Context context) {
        String packageName = context != null ? context.getPackageName() : "";
        return Environment.getExternalStorageDirectory().getPath() + File.separator + Context.DOWNLOAD_SERVICE + File.separator + b(packageName + "cache_sobot");
    }

    public static String d(Context context) {
        return context.getFilesDir().getPath();
    }

    public static String e(Context context) {
        return f(context);
    }

    public static String f(Context context) {
        StringBuilder sb = new StringBuilder();
        String b = s.b(context, "deviceId", "");
        if (!TextUtils.isEmpty(b)) {
            sb.append(b);
            m.d("deviceId:" + sb.toString());
            return sb.toString();
        }
        String str = UUID.randomUUID().toString() + System.currentTimeMillis();
        sb.append(str);
        s.a(context, "deviceId", str);
        m.d("deviceId:" + sb.toString());
        return sb.toString();
    }

    public static String g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static synchronized String h(Context context) {
        String string;
        synchronized (d.class) {
            try {
                string = context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return string;
    }

    public static String b(String str) {
        if (str != null) {
            Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5]").matcher(str);
            while (matcher.find()) {
                String group = matcher.group();
                try {
                    str = str.replaceAll(group, URLEncoder.encode(group, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }

    public static boolean i(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (!(runningAppProcesses == null || runningAppProcesses.size() == 0)) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100) {
                    return runningAppProcessInfo.processName.equals(context.getPackageName());
                }
            }
        }
        return false;
    }

    public static String j(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return (runningTasks == null || runningTasks.size() <= 0) ? "" : runningTasks.get(0).topActivity.getClassName();
    }

    public static void a(Context context, Intent intent) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void b(Context context, Intent intent) {
        String packageName = context.getPackageName();
        if (!TextUtils.isEmpty(packageName)) {
            intent.setPackage(packageName);
        }
        context.sendBroadcast(intent);
    }

    public static boolean k(Context context) {
        return ((KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE)).inKeyguardRestrictedInputMode();
    }

    public static int l(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String a(Context context, String str) {
        return q.f(context, str);
    }

    public static int b(Context context, String str) {
        return q.a(context, "id", str);
    }

    public static int c(Context context, String str) {
        return q.a(context, "drawable", str);
    }

    public static int d(Context context, String str) {
        return q.a(context, "dimen", str);
    }

    public static float e(Context context, String str) {
        return context.getResources().getDimension(d(context, str));
    }

    public static boolean m(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return false;
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            String packageName = context.getPackageName();
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid && packageName.equals(runningAppProcessInfo.processName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void a(String str, Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setData(Uri.parse("tel:" + str));
        context.startActivity(intent);
    }
}
