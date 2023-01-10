package com.vivo.push.util;

import android.Manifest;
import android.app.ActivityManager;
import android.bluetooth.le.AdvertisingSetParameters;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.h;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: Utility */
public final class y {
    private static String[] a = {"com.vivo.push.sdk.RegistrationReceiver", "com.vivo.push.sdk.service.PushService", "com.vivo.push.sdk.service.CommonJobService"};
    private static String[] b = {"android.permission.INTERNET", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_NETWORK_STATE", Manifest.permission.WRITE_SETTINGS, Manifest.permission.VIBRATE, "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WAKE_LOCK", Manifest.permission.GET_ACCOUNTS, "com.bbk.account.permission.READ_ACCOUNTINFO", Manifest.permission.AUTHENTICATE_ACCOUNTS, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS, "android.permission.GET_TASKS"};
    private static Boolean c;
    private static String[] d = {"com.vivo.push.sdk.service.CommandService", "com.vivo.push.sdk.service.CommonJobService"};
    private static String[] e = {"com.vivo.push.sdk.RegistrationReceiver"};
    private static String[] f = new String[0];

    public static boolean a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        AppMethodBeat.i(1586, false);
        Boolean bool = c;
        if (bool != null) {
            boolean booleanValue = bool.booleanValue();
            AppMethodBeat.o(1586);
            return booleanValue;
        } else if (context == null) {
            n.d("Utility", "isPushProcess context is null");
            AppMethodBeat.o(1586);
            return false;
        } else {
            String b2 = r.b(context);
            if (context == null || context.getPackageName() == null || !context.getPackageName().equals(b2)) {
                int myPid = Process.myPid();
                ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                String str = null;
                if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null && runningAppProcesses.size() != 0) {
                    Iterator<ActivityManager.RunningAppProcessInfo> it2 = runningAppProcesses.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        ActivityManager.RunningAppProcessInfo next = it2.next();
                        if (next.pid == myPid) {
                            str = next.processName;
                            break;
                        }
                    }
                }
                if (TextUtils.isEmpty(str)) {
                    AppMethodBeat.o(1586);
                    return false;
                }
                Boolean valueOf = Boolean.valueOf(str.contains(":pushservice"));
                c = valueOf;
                boolean booleanValue2 = valueOf.booleanValue();
                AppMethodBeat.o(1586);
                return booleanValue2;
            }
            Boolean bool2 = true;
            c = bool2;
            boolean booleanValue3 = bool2.booleanValue();
            AppMethodBeat.o(1586);
            return booleanValue3;
        }
    }

    public static long b(Context context) {
        AppMethodBeat.i(1589, false);
        String b2 = r.b(context);
        if (TextUtils.isEmpty(b2)) {
            n.a("Utility", "systemPushPkgName is null");
            AppMethodBeat.o(1589);
            return -1;
        }
        long a2 = a(context, b2);
        AppMethodBeat.o(1589);
        return a2;
    }

    public static long a(Context context, String str) {
        AppMethodBeat.i(1593, false);
        Object b2 = b(context, str, "com.vivo.push.sdk_version");
        if (b2 == null) {
            b2 = b(context, str, "sdk_version");
        }
        if (b2 != null) {
            try {
                long parseLong = Long.parseLong(b2.toString());
                AppMethodBeat.o(1593);
                return parseLong;
            } catch (Exception e2) {
                e2.printStackTrace();
                n.a("Utility", "getSdkVersionCode error ", e2);
                AppMethodBeat.o(1593);
                return -1;
            }
        } else {
            n.a("Utility", "getSdkVersionCode sdk version is null");
            AppMethodBeat.o(1593);
            return -1;
        }
    }

    public static String b(Context context, String str) {
        AppMethodBeat.i(1598, false);
        Object b2 = b(context, str, "com.vivo.push.app_id");
        if (b2 != null) {
            String obj = b2.toString();
            AppMethodBeat.o(1598);
            return obj;
        }
        Object b3 = b(context, str, "app_id");
        if (b3 != null) {
            String obj2 = b3.toString();
            AppMethodBeat.o(1598);
            return obj2;
        }
        AppMethodBeat.o(1598);
        return "";
    }

    private static Object b(Context context, String str, String str2) {
        AppMethodBeat.i(AdvertisingSetParameters.INTERVAL_HIGH, false);
        Object obj = null;
        if (context == null || TextUtils.isEmpty(str)) {
            AppMethodBeat.o(AdvertisingSetParameters.INTERVAL_HIGH);
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            Bundle bundle = applicationInfo != null ? applicationInfo.metaData : null;
            if (bundle != null) {
                obj = bundle.get(str2);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        AppMethodBeat.o(AdvertisingSetParameters.INTERVAL_HIGH);
        return obj;
    }

    public static Object a(String str, String str2) throws Exception {
        AppMethodBeat.i(1606, false);
        Class<?> cls = Class.forName(str);
        Object obj = cls.getField(str2).get(cls);
        AppMethodBeat.o(1606);
        return obj;
    }

    public static void c(Context context) throws VivoPushException {
        String str;
        AppMethodBeat.i(1611, false);
        n.d("Utility", "check PushService AndroidManifest declearation !");
        String b2 = r.b(context);
        boolean a2 = r.a(context, context.getPackageName(), "com.vivo.pushservice.action.RECEIVE");
        boolean a3 = r.a(context, context.getPackageName(), "com.vivo.pushservice.action.METHOD");
        boolean a4 = r.a(context, context.getPackageName(), "com.vivo.pushclient.action.RECEIVE");
        if (a3) {
            a = new String[]{"com.vivo.push.sdk.RegistrationReceiver", "com.vivo.push.sdk.service.PushService", "com.vivo.push.sdk.service.CommonJobService"};
            b = new String[]{"android.permission.INTERNET", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_NETWORK_STATE", Manifest.permission.WRITE_SETTINGS, Manifest.permission.VIBRATE, "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WAKE_LOCK", Manifest.permission.GET_ACCOUNTS, "com.bbk.account.permission.READ_ACCOUNTINFO", Manifest.permission.AUTHENTICATE_ACCOUNTS, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS, "android.permission.GET_TASKS"};
            d = new String[]{"com.vivo.push.sdk.service.CommandService", "com.vivo.push.sdk.service.CommonJobService"};
            e = new String[]{"com.vivo.push.sdk.RegistrationReceiver"};
        } else if (a4 || a2) {
            if (a4) {
                d = new String[]{"com.vivo.push.sdk.service.CommandClientService"};
            } else {
                d = new String[]{"com.vivo.push.sdk.service.CommandService"};
            }
            e = new String[0];
            a = new String[0];
            if (a2) {
                b = new String[]{"android.permission.INTERNET", Manifest.permission.WRITE_SETTINGS};
            } else {
                b = new String[]{"android.permission.INTERNET"};
            }
        } else {
            VivoPushException vivoPushException = new VivoPushException("AndroidManifest.xml\u4e2dreceiver\u914d\u7f6e\u9879\u9519\u8bef\uff0c\u8be6\u89c1\u63a5\u5165\u6587\u6863");
            AppMethodBeat.o(1611);
            throw vivoPushException;
        }
        if (a2 || a3) {
            long a5 = a(context, context.getPackageName());
            long j = 305;
            if (context.getPackageName().equals(b2)) {
                j = 1305;
            }
            if (a5 == -1) {
                VivoPushException vivoPushException2 = new VivoPushException("AndroidManifest.xml\u4e2d\u672a\u914d\u7f6esdk_version");
                AppMethodBeat.o(1611);
                throw vivoPushException2;
            } else if (a5 != j) {
                VivoPushException vivoPushException3 = new VivoPushException("AndroidManifest.xml\u4e2dsdk_version\u914d\u7f6e\u9879\u9519\u8bef\uff0c\u8bf7\u914d\u7f6e\u5f53\u524dsdk_version\u7248\u672c\u4e3a:" + j);
                AppMethodBeat.o(1611);
                throw vivoPushException3;
            }
        }
        f(context);
        e(context, b2);
        c(context, b2);
        d(context, b2);
        String packageName = context.getPackageName();
        Object b3 = b(context, packageName, "com.vivo.push.api_key");
        if (b3 != null) {
            str = b3.toString();
        } else {
            Object b4 = b(context, packageName, "api_key");
            if (b4 != null) {
                str = b4.toString();
            } else {
                str = "";
            }
        }
        if (TextUtils.isEmpty(str)) {
            VivoPushException vivoPushException4 = new VivoPushException("com.vivo.push.api_key is null");
            AppMethodBeat.o(1611);
            throw vivoPushException4;
        } else if (TextUtils.isEmpty(b(context, context.getPackageName()))) {
            VivoPushException vivoPushException5 = new VivoPushException("com.vivo.push.app_id is null");
            AppMethodBeat.o(1611);
            throw vivoPushException5;
        } else if ((a2 || a3) && a(context, context.getPackageName()) == -1) {
            VivoPushException vivoPushException6 = new VivoPushException("sdkversion is null");
            AppMethodBeat.o(1611);
            throw vivoPushException6;
        } else {
            if (a3) {
                a(context, "com.vivo.pushservice.action.METHOD", "com.vivo.push.sdk.RegistrationReceiver", true);
                a(context, "com.vivo.pushservice.action.PUSH_SERVICE", "com.vivo.push.sdk.service.PushService", false);
            }
            AppMethodBeat.o(1611);
        }
    }

    private static void c(Context context, String str) throws VivoPushException {
        AppMethodBeat.i(1614, false);
        try {
            if (context.getPackageManager() != null) {
                ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services;
                if (serviceInfoArr != null) {
                    for (String str2 : d) {
                        a(str2, serviceInfoArr, str);
                    }
                    AppMethodBeat.o(1614);
                    return;
                }
                VivoPushException vivoPushException = new VivoPushException("serviceInfos is null");
                AppMethodBeat.o(1614);
                throw vivoPushException;
            }
            VivoPushException vivoPushException2 = new VivoPushException("localPackageManager is null");
            AppMethodBeat.o(1614);
            throw vivoPushException2;
        } catch (Exception e2) {
            VivoPushException vivoPushException3 = new VivoPushException("error " + e2.getMessage());
            AppMethodBeat.o(1614);
            throw vivoPushException3;
        }
    }

    private static void d(Context context, String str) throws VivoPushException {
        AppMethodBeat.i(1618, false);
        if (f.length <= 0) {
            AppMethodBeat.o(1618);
            return;
        }
        try {
            if (context.getPackageManager() != null) {
                ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
                if (activityInfoArr != null) {
                    for (String str2 : f) {
                        a(str2, activityInfoArr, str);
                    }
                    AppMethodBeat.o(1618);
                    return;
                }
                VivoPushException vivoPushException = new VivoPushException("activityInfos is null");
                AppMethodBeat.o(1618);
                throw vivoPushException;
            }
            VivoPushException vivoPushException2 = new VivoPushException("localPackageManager is null");
            AppMethodBeat.o(1618);
            throw vivoPushException2;
        } catch (Exception e2) {
            VivoPushException vivoPushException3 = new VivoPushException("error " + e2.getMessage());
            AppMethodBeat.o(1618);
            throw vivoPushException3;
        }
    }

    private static void f(Context context) throws VivoPushException {
        AppMethodBeat.i(1621, false);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                String[] strArr = packageManager.getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
                if (strArr != null) {
                    String[] strArr2 = b;
                    for (String str : strArr2) {
                        for (String str2 : strArr) {
                            if (!str.equals(str2)) {
                            }
                        }
                        VivoPushException vivoPushException = new VivoPushException("permission : " + str + "  check fail : " + Arrays.toString(strArr));
                        AppMethodBeat.o(1621);
                        throw vivoPushException;
                    }
                    AppMethodBeat.o(1621);
                    return;
                }
                VivoPushException vivoPushException2 = new VivoPushException("Permissions is null!");
                AppMethodBeat.o(1621);
                throw vivoPushException2;
            }
            VivoPushException vivoPushException3 = new VivoPushException("localPackageManager is null");
            AppMethodBeat.o(1621);
            throw vivoPushException3;
        } catch (Exception e2) {
            VivoPushException vivoPushException4 = new VivoPushException(e2.getMessage());
            AppMethodBeat.o(1621);
            throw vivoPushException4;
        }
    }

    private static void a(String str, ComponentInfo[] componentInfoArr, String str2) throws VivoPushException {
        AppMethodBeat.i(1625, false);
        for (ComponentInfo componentInfo : componentInfoArr) {
            if (str.equals(componentInfo.name)) {
                if (componentInfo.enabled) {
                    a(componentInfo, str2);
                    AppMethodBeat.o(1625);
                    return;
                } else {
                    VivoPushException vivoPushException = new VivoPushException(componentInfo.name + " module Push-SDK need is illegitmacy !");
                    AppMethodBeat.o(1625);
                    throw vivoPushException;
                }
            }
        }
        VivoPushException vivoPushException2 = new VivoPushException(str + " module Push-SDK need is not exist");
        AppMethodBeat.o(1625);
        throw vivoPushException2;
    }

    private static void a(ComponentInfo componentInfo, String str) throws VivoPushException {
        AppMethodBeat.i(1628, false);
        if (componentInfo.applicationInfo.packageName.equals(str)) {
            AppMethodBeat.o(1628);
            return;
        }
        for (String str2 : a) {
            if (str2.equals(componentInfo.name) && !componentInfo.processName.contains(":pushservice")) {
                VivoPushException vivoPushException = new VivoPushException("module : " + componentInfo.name + " process :" + componentInfo.processName + "  check process fail");
                AppMethodBeat.o(1628);
                throw vivoPushException;
            }
        }
        AppMethodBeat.o(1628);
    }

    private static void e(Context context, String str) throws VivoPushException {
        AppMethodBeat.i(1632, false);
        try {
            if (context.getPackageManager() != null) {
                ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 2).receivers;
                if (activityInfoArr != null) {
                    for (String str2 : e) {
                        a(str2, activityInfoArr, str);
                    }
                    AppMethodBeat.o(1632);
                    return;
                }
                VivoPushException vivoPushException = new VivoPushException("receivers is null");
                AppMethodBeat.o(1632);
                throw vivoPushException;
            }
            VivoPushException vivoPushException2 = new VivoPushException("localPackageManager is null");
            AppMethodBeat.o(1632);
            throw vivoPushException2;
        } catch (Exception e2) {
            VivoPushException vivoPushException3 = new VivoPushException(e2.getMessage());
            AppMethodBeat.o(1632);
            throw vivoPushException3;
        }
    }

    private static void a(Context context, String str, String str2, boolean z) throws VivoPushException {
        AppMethodBeat.i(1637, false);
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                VivoPushException vivoPushException = new VivoPushException("localPackageManager is null");
                AppMethodBeat.o(1637);
                throw vivoPushException;
            } else if (z) {
                List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT);
                if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
                    VivoPushException vivoPushException2 = new VivoPushException("checkModule " + intent + " has no receivers");
                    AppMethodBeat.o(1637);
                    throw vivoPushException2;
                }
                for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                    if (str2.equals(resolveInfo.activityInfo.name)) {
                        AppMethodBeat.o(1637);
                        return;
                    }
                }
                VivoPushException vivoPushException3 = new VivoPushException(str2 + " is missing");
                AppMethodBeat.o(1637);
                throw vivoPushException3;
            } else {
                List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT);
                if (queryIntentServices == null || queryIntentServices.size() <= 0) {
                    VivoPushException vivoPushException4 = new VivoPushException("checkModule " + intent + " has no services");
                    AppMethodBeat.o(1637);
                    throw vivoPushException4;
                }
                for (ResolveInfo resolveInfo2 : queryIntentServices) {
                    if (str2.equals(resolveInfo2.serviceInfo.name)) {
                        if (resolveInfo2.serviceInfo.exported) {
                            AppMethodBeat.o(1637);
                            return;
                        }
                        VivoPushException vivoPushException5 = new VivoPushException(resolveInfo2.serviceInfo.name + " exported is false");
                        AppMethodBeat.o(1637);
                        throw vivoPushException5;
                    }
                }
                VivoPushException vivoPushException6 = new VivoPushException(str2 + " is missing");
                AppMethodBeat.o(1637);
                throw vivoPushException6;
            }
        } catch (Exception e2) {
            n.a("Utility", "error  " + e2.getMessage());
            VivoPushException vivoPushException7 = new VivoPushException("checkModule error" + e2.getMessage());
            AppMethodBeat.o(1637);
            throw vivoPushException7;
        }
    }

    public static String b(String str, String str2) {
        String str3;
        AppMethodBeat.i(1640, false);
        try {
            str3 = (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            str3 = str2;
        }
        if (str3 == null || str3.length() == 0) {
            str3 = str2;
        }
        AppMethodBeat.o(1640);
        return str3;
    }

    public static PublicKey d(Context context) {
        AppMethodBeat.i(1644, false);
        Cursor query = context.getContentResolver().query(h.a, null, null, null, null);
        if (query == null) {
            AppMethodBeat.o(1644);
            return null;
        }
        do {
            try {
                if (query.moveToNext()) {
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            } catch (Throwable th) {
                try {
                    query.close();
                } catch (Exception unused) {
                }
                AppMethodBeat.o(1644);
                throw th;
            }
            try {
                query.close();
            } catch (Exception unused2) {
            }
            AppMethodBeat.o(1644);
            return null;
        } while (!"pushkey".equals(query.getString(query.getColumnIndex("name"))));
        String string = query.getString(query.getColumnIndex("value"));
        n.d("Utility", "result key : " + string);
        PublicKey a2 = s.a(string);
        try {
            query.close();
        } catch (Exception unused3) {
        }
        AppMethodBeat.o(1644);
        return a2;
    }

    public static void a(Context context, Intent intent) {
        AppMethodBeat.i(1651, false);
        String b2 = r.b(context);
        String stringExtra = intent.getStringExtra("client_pkgname");
        if (TextUtils.isEmpty(b2)) {
            n.a("Utility", "illegality abe adapter : push pkg is null");
            AppMethodBeat.o(1651);
        } else if (TextUtils.isEmpty(stringExtra)) {
            n.a("Utility", "illegality abe adapter : src pkg is null");
            AppMethodBeat.o(1651);
        } else if (b2.equals(context.getPackageName())) {
            n.a("Utility", "illegality abe adapter : abe is not pushservice");
            AppMethodBeat.o(1651);
        } else if (!b2.equals(stringExtra)) {
            n.d("Utility", "proxy to core : intent pkg : " + intent.getPackage() + " ; src pkg : " + stringExtra + " ; push pkg : " + b2);
            intent.setPackage(b2);
            intent.setClassName(b2, "com.vivo.push.sdk.service.PushService");
            context.startService(intent);
            AppMethodBeat.o(1651);
        } else {
            n.a("Utility", "illegality abe adapter : pushPkg = " + b2 + " ; srcPkg = " + stringExtra);
            AppMethodBeat.o(1651);
        }
    }

    public static boolean e(Context context) {
        AppMethodBeat.i(1654, false);
        Cursor cursor = null;
        if (context == null) {
            try {
                n.a("Utility", "context is null");
                AppMethodBeat.o(1654);
                return false;
            } catch (Exception e2) {
                n.a("Utility", "isSupport", e2);
                if (0 != 0) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        cursor.close();
                    } catch (Exception e3) {
                        n.a("Utility", "close", e3);
                    }
                }
                AppMethodBeat.o(1654);
                throw th;
            }
        } else {
            String packageName = context.getPackageName();
            Cursor query = context.getContentResolver().query(h.b, null, "pushVersion = ? and appPkgName = ? and appCode = ? ", new String[]{"305", packageName, String.valueOf(context.getPackageManager().getPackageInfo(packageName, 0).versionCode)}, null);
            if (query == null) {
                n.a("Utility", "cursor is null");
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception e4) {
                        n.a("Utility", "close", e4);
                    }
                }
                AppMethodBeat.o(1654);
                return false;
            } else if (!query.moveToFirst() || (query.getInt(query.getColumnIndex(UsbManager.EXTRA_PERMISSION_GRANTED)) & 1) == 0) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception e5) {
                        n.a("Utility", "close", e5);
                    }
                }
                AppMethodBeat.o(1654);
                return false;
            } else {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception e6) {
                        n.a("Utility", "close", e6);
                    }
                }
                AppMethodBeat.o(1654);
                return true;
            }
        }
    }

    public static boolean a(Context context, String str, String str2) {
        AppMethodBeat.i(1656, false);
        Cursor cursor = null;
        if (context == null) {
            try {
                n.a("Utility", "context is null");
                AppMethodBeat.o(1656);
                return false;
            } catch (Exception e2) {
                n.a("Utility", "isOverdue", e2);
                if (0 != 0) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        cursor.close();
                    } catch (Exception e3) {
                        n.a("Utility", "close", e3);
                    }
                }
                AppMethodBeat.o(1656);
                throw th;
            }
        } else {
            Cursor query = context.getContentResolver().query(h.c, null, "appPkgName = ? and regId = ? sdkVersion = ? ", new String[]{str, str2, "305"}, null);
            if (query == null) {
                n.a("Utility", "cursor is null");
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception e4) {
                        n.a("Utility", "close", e4);
                    }
                }
                AppMethodBeat.o(1656);
                return false;
            } else if (query.moveToFirst()) {
                boolean parseBoolean = Boolean.parseBoolean(query.getString(query.getColumnIndex("clientState")));
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception e5) {
                        n.a("Utility", "close", e5);
                    }
                }
                AppMethodBeat.o(1656);
                return parseBoolean;
            } else {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception e6) {
                        n.a("Utility", "close", e6);
                    }
                }
                AppMethodBeat.o(1656);
                return false;
            }
        }
    }
}
