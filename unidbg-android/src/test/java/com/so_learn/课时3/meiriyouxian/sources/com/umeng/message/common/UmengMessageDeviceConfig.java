package com.umeng.message.common;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import com.android.internal.telephony.PhoneConstants;
import com.huawei.hms.support.api.push.utils.common.NotificationUtil;
import com.ta.utdid2.device.UTDevice;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.message.PushAgent;
import com.umeng.message.proguard.c;
import com.umeng.message.proguard.h;
import com.umeng.message.util.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class UmengMessageDeviceConfig {
    public static final int DEFAULT_TIMEZONE = 8;
    protected static final String a = "Unknown";
    private static final String b = UmengMessageDeviceConfig.class.getSimpleName();
    private static final String c = "2G/3G";
    private static final String d = "Wi-Fi";
    private static boolean e = false;

    public static String getDummyId(Context context) {
        return "02:00:00:00:00:00";
    }

    public static boolean isCommonConfig(String str) {
        try {
            return FieldManager.allow(str);
        } catch (Throwable unused) {
            return true;
        }
    }

    public static boolean isCommonConfigNew(String str) {
        Method method;
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.config.FieldManager");
            if (!(cls == null || (method = cls.getMethod("allow", String.class)) == null)) {
                return ((Boolean) method.invoke(null, str)).booleanValue();
            }
        } catch (Throwable unused) {
        }
        return true;
    }

    public static String getAppVersionCode(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            return a;
        }
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return a;
        }
    }

    public static boolean checkPermission(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    public static String getCPU() {
        String str = null;
        try {
            FileReader fileReader = new FileReader("/proc/cpuinfo");
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                str = bufferedReader.readLine();
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e2) {
                UMLog.mutlInfo(b, 0, "Could not read from file /proc/cpuinfo", e2.getMessage());
            }
        } catch (FileNotFoundException e3) {
            UMLog.mutlInfo(b, 0, "Could not open file /proc/cpuinfo", e3.getMessage());
        }
        return str != null ? str.substring(str.indexOf(58) + 1).trim() : str;
    }

    public static String getDeviceId(Context context) {
        String str = "";
        if (isCommonConfigNew(b.b)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                UMLog.mutlInfo(b, 1, "No IMEI.");
            }
            try {
                if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                    str = telephonyManager.getDeviceId();
                }
            } catch (Exception e2) {
                UMLog.mutlInfo(b, 1, "No IMEI.", e2.getMessage());
            }
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str)) {
            UMLog.mutlInfo(b, 1, "Failed to take imei as IMEI. Try to use Secure.ANDROID_ID instead.");
            if (isCommonConfigNew(b.d)) {
                str = Settings.Secure.getString(context.getContentResolver(), "android_id");
            }
            String str2 = b;
            UMLog.mutlInfo(str2, 2, "getDeviceId: Secure.ANDROID_ID: " + str);
            if (TextUtils.isEmpty(str)) {
                UMLog.mutlInfo(b, 1, "Failed to take Secure.ANDROID_ID as IMEI. Try to use Serial_number instead.");
                if (isCommonConfigNew(b.e)) {
                    str = getSerial_number();
                }
                String str3 = b;
                UMLog.mutlInfo(str3, 2, "getDeviceId: Serial_number: " + str);
            }
        }
        return str;
    }

    public static String getDIN(Context context) {
        if (!isCommonConfigNew(b.b)) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            UMLog.mutlInfo(b, 1, "No IMEI.");
        }
        try {
            if (!checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                return "";
            }
            String deviceId = telephonyManager.getDeviceId();
            if (!TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            return "";
        } catch (Exception e2) {
            UMLog.mutlInfo(b, 1, "No IMEI.", e2.getMessage());
            return "";
        }
    }

    public static String getDINAes(Context context) {
        Exception e2;
        String str;
        try {
            str = getDIN(context);
            try {
                String messageAppkey = PushAgent.getInstance(context).getMessageAppkey();
                if (messageAppkey == null || 24 != messageAppkey.length()) {
                    return c.a(str, FileOpt.ENCODE_STR);
                }
                return c.a(str, FileOpt.ENCODE_STR, messageAppkey.substring(0, 16));
            } catch (Exception e3) {
                e2 = e3;
                String str2 = b;
                UMLog.mutlInfo(str2, 1, "getDINAes:" + e2.getMessage());
                return str;
            }
        } catch (Exception e4) {
            e2 = e4;
            str = "";
            String str2 = b;
            UMLog.mutlInfo(str2, 1, "getDINAes:" + e2.getMessage());
            return str;
        }
    }

    public static String getDeviceIDAes(Context context) {
        Exception e2;
        String str;
        try {
            str = getDeviceId(context);
            try {
                String messageAppkey = PushAgent.getInstance(context).getMessageAppkey();
                if (messageAppkey == null || 24 != messageAppkey.length()) {
                    return c.a(str, FileOpt.ENCODE_STR);
                }
                return c.a(str, FileOpt.ENCODE_STR, messageAppkey.substring(0, 16));
            } catch (Exception e3) {
                e2 = e3;
                String str2 = b;
                UMLog.mutlInfo(str2, 1, "getDeviceIDAes:" + e2.getMessage());
                return str;
            }
        } catch (Exception e4) {
            e2 = e4;
            str = "";
            String str2 = b;
            UMLog.mutlInfo(str2, 1, "getDeviceIDAes:" + e2.getMessage());
            return str;
        }
    }

    public static String getAndroidId(Context context) {
        if (!isCommonConfigNew(b.d)) {
            return null;
        }
        String string = Settings.System.getString(context.getContentResolver(), "android_id");
        return string == null ? "" : string;
    }

    public static String getSerial_number() {
        String str;
        if (!isCommonConfigNew(b.e)) {
            return "";
        }
        if (Build.VERSION.SDK_INT <= 25) {
            str = Build.SERIAL;
        } else {
            try {
                Class<?> cls = Class.forName("android.os.Build");
                str = (String) cls.getMethod("getSerial", new Class[0]).invoke(cls, new Object[0]);
            } catch (Throwable unused) {
                str = "";
            }
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String getDeviceIdMD5(Context context) {
        return h.a(getDeviceId(context));
    }

    public static String[] getNetworkAccessMode(Context context) {
        String[] strArr = {a, a};
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                return strArr;
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo == null || networkInfo.getState() != NetworkInfo.State.CONNECTED) {
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo2 != null && networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                    strArr[0] = "2G/3G";
                    strArr[1] = networkInfo2.getSubtypeName();
                }
                return strArr;
            }
            strArr[0] = "Wi-Fi";
            return strArr;
        } catch (Exception unused) {
        }
    }

    public static boolean isOnline(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static int getTimeZone(Context context) {
        try {
            Calendar instance = Calendar.getInstance(a(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / 3600000;
            }
            return 8;
        } catch (Exception e2) {
            UMLog.mutlInfo(b, 2, "error in getTimeZone", e2.getMessage());
            return 8;
        }
    }

    public static String[] getLocaleInfo(Context context) {
        String[] strArr = new String[2];
        try {
            Locale a2 = a(context);
            if (a2 != null) {
                strArr[0] = a2.getCountry();
                strArr[1] = a2.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = a;
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = a;
            }
            return strArr;
        } catch (Exception e2) {
            UMLog.mutlInfo(b, 0, "error in getLocaleInfo", e2.getMessage());
            return strArr;
        }
    }

    private static Locale a(Context context) {
        Locale locale;
        try {
            Configuration configuration = new Configuration();
            Settings.System.getConfiguration(context.getContentResolver(), configuration);
            locale = configuration.locale;
        } catch (Exception unused) {
            UMLog.mutlInfo(b, 0, "fail to read user config locale");
            locale = null;
        }
        return locale == null ? Locale.getDefault() : locale;
    }

    public static String getMetaData(Context context, String str) {
        String string;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || (string = applicationInfo.metaData.getString(str)) == null)) {
                return string.trim();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        UMLog.mutlInfo(b, 0, String.format("Could not read meta-data %s from AndroidManifest.xml.", str));
        return null;
    }

    public static String getUmid(Context context) {
        String b2 = b(context);
        return b2 == null ? "" : b2;
    }

    private static String b(Context context) {
        return UMUtils.getUMId(context);
    }

    public static String getResolution(Context context) {
        int i;
        int i2;
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                i = a(displayMetrics, "noncompatWidthPixels");
                i2 = a(displayMetrics, "noncompatHeightPixels");
            } else {
                i = -1;
                i2 = -1;
            }
            if (i == -1 || i2 == -1) {
                i = displayMetrics.widthPixels;
                i2 = displayMetrics.heightPixels;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(i);
            stringBuffer.append(PhoneConstants.APN_TYPE_ALL);
            stringBuffer.append(i2);
            return stringBuffer.toString();
        } catch (Exception e2) {
            UMLog.mutlInfo(b, 0, "read resolution fail", e2.getMessage());
            return a;
        }
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            if (declaredField == null) {
                return -1;
            }
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static String getOperator(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception e2) {
            UMLog.mutlInfo(b, 2, "read carrier fail", e2.getMessage());
            return a;
        }
    }

    public static String getChannel(Context context) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("UMENG_CHANNEL")) == null) {
                return a;
            }
            String obj2 = obj.toString();
            if (obj2 != null) {
                return obj2;
            }
            UMLog.mutlInfo(b, 2, "\u5728AndroidManifest.xml\u4e2d\u8bfb\u53d6\u4e0d\u5230UMENG_CHANNEL meta-data");
            return a;
        } catch (Exception e2) {
            UMLog.mutlInfo(b, 2, "\u5728AndroidManifest.xml\u4e2d\u8bfb\u53d6\u4e0d\u5230UMENG_CHANNEL meta-data");
            e2.printStackTrace();
            return a;
        }
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static String getApplicationLable(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static String getUtdid(Context context) {
        try {
            return UTDevice.getUtdid(context);
        } catch (Throwable th) {
            String str = b;
            UMLog.mutlInfo(str, 0, "\u83b7\u53d6utdid\u5931\u8d25. " + th.getMessage());
            return "";
        }
    }

    public static boolean isServiceWork(Context context, String str, String str2) {
        if (!isCommonConfigNew(b.n)) {
            return false;
        }
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (runningServices.size() <= 0) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            String str3 = runningServices.get(i).service.getClassName().toString();
            String str4 = runningServices.get(i).service.getPackageName().toString();
            if (str3.equals(str) && str4.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static String getServiceName(Context context, String str, String str2) {
        if (!isCommonConfigNew(b.o)) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction(str);
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        ArrayList arrayList = new ArrayList();
        if (queryIntentServices != null && queryIntentServices.size() > 0) {
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (resolveInfo.serviceInfo.packageName.equals(str2)) {
                    arrayList.add(resolveInfo);
                }
            }
        }
        if (!arrayList.isEmpty()) {
            return ((ResolveInfo) arrayList.get(0)).serviceInfo.name;
        }
        return null;
    }

    public static boolean isIntentExist(Context context, String str, String str2) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(str)), 0);
        if (queryIntentActivities.isEmpty()) {
            return false;
        }
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            if (queryIntentActivities.get(i).activityInfo.packageName.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getDataData(String str) {
        boolean exists = new File("/data/app/" + str + "-1.apk").exists();
        if (!exists) {
            exists = new File("/data/app/" + str + "-2.apk").exists();
        }
        if (!exists) {
            exists = new File("/data/app/" + str + ".apk").exists();
        }
        if (!exists) {
            exists = new File("/data/app/" + str + "-1").exists();
        }
        if (exists) {
            return exists;
        }
        return new File("/data/app/" + str + "-2").exists();
    }

    public static boolean isMi8() {
        try {
            String a2 = a.g().a("ro.miui.ui.version.name");
            if (a2 == null || !a2.contains("8")) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String isNotificationEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                String packageName = context.getApplicationContext().getPackageName();
                int i = applicationInfo.uid;
                Class<?> cls = Class.forName(AppOpsManager.class.getName());
                boolean z = true;
                if (((Integer) cls.getMethod(NotificationUtil.CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField(NotificationUtil.OP_POST_NOTIFICATION).get(appOpsManager)).intValue()), Integer.valueOf(i), packageName)).intValue() != 0) {
                    z = false;
                }
                return String.valueOf(z);
            } catch (Exception unused) {
                UMLog.mutlInfo(b, 0, "\u901a\u77e5\u5f00\u5173\u662f\u5426\u6253\u5f00\u5f02\u5e38");
            }
        }
        return "unknown";
    }
}
