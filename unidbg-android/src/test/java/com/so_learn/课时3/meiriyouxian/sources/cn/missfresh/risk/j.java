package cn.missfresh.risk;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import cn.missfresh.risk.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.e;
import com.android.internal.logging.nano.MetricsProto;
import com.android.internal.telephony.TelephonyProperties;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.util.Locale;

/* compiled from: RiskOsUtil */
public class j {
    public static String a(Context context) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.NOTIFICATION_ID, false);
        try {
            String string = Settings.System.getString(context.getContentResolver(), "android_id");
            AppMethodBeat.o(MetricsProto.MetricsEvent.NOTIFICATION_ID);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(MetricsProto.MetricsEvent.NOTIFICATION_ID);
            return "";
        }
    }

    public static String a() {
        AppMethodBeat.i(800, false);
        try {
            c.a a = c.a("cat /proc/sys/kernel/random/boot_id", false);
            if (a != null) {
                String str = a.c;
                AppMethodBeat.o(800);
                return str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(800);
        return "";
    }

    public static boolean b() {
        boolean z = false;
        AppMethodBeat.i(805, false);
        try {
            Exception exc = new Exception("blah");
            AppMethodBeat.o(805);
            throw exc;
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            int length = stackTrace.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                StackTraceElement stackTraceElement = stackTrace[i];
                if (stackTraceElement.getClassName().toLowerCase().contains("xpose") || stackTraceElement.getMethodName().toLowerCase().contains("xpose")) {
                    break;
                }
                i++;
            }
            z = true;
            AppMethodBeat.o(805);
            return z;
        }
    }

    public static String c() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.MANAGE_EXTERNAL_SOURCES, false);
        try {
            String country = Locale.getDefault().getCountry();
            AppMethodBeat.o(MetricsProto.MetricsEvent.MANAGE_EXTERNAL_SOURCES);
            return country;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(MetricsProto.MetricsEvent.MANAGE_EXTERNAL_SOURCES);
            return "";
        }
    }

    public static String d() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.PROVISIONING_TERMS_COUNT, false);
        try {
            String language = Locale.getDefault().getLanguage();
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_TERMS_COUNT);
            return language;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_TERMS_COUNT);
            return "";
        }
    }

    public static String b(Context context) {
        String str;
        AppMethodBeat.i(MetricsProto.MetricsEvent.APP_PICTURE_IN_PICTURE_ALLOW, false);
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED);
            intentFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
            intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
            intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
            intentFilter.addAction(UsbManager.ACTION_USB_STATE);
            Intent registerReceiver = context.registerReceiver(null, intentFilter);
            if (registerReceiver == null) {
                AppMethodBeat.o(MetricsProto.MetricsEvent.APP_PICTURE_IN_PICTURE_ALLOW);
                return "";
            }
            boolean booleanExtra = registerReceiver.getBooleanExtra(UsbManager.USB_FUNCTION_ADB, false);
            boolean booleanExtra2 = registerReceiver.getBooleanExtra(UsbManager.USB_FUNCTION_RNDIS, false);
            boolean booleanExtra3 = registerReceiver.getBooleanExtra(UsbManager.USB_FUNCTION_MTP, false);
            boolean booleanExtra4 = registerReceiver.getBooleanExtra(UsbManager.USB_FUNCTION_PTP, false);
            boolean booleanExtra5 = registerReceiver.getBooleanExtra(UsbManager.USB_FUNCTION_AUDIO_SOURCE, false);
            boolean booleanExtra6 = registerReceiver.getBooleanExtra("midi", false);
            if (booleanExtra) {
                str = "" + UsbManager.USB_FUNCTION_ADB;
            } else {
                str = "";
            }
            if (booleanExtra2) {
                str = str + ",rndis";
            }
            if (booleanExtra3) {
                str = str + ",mtp";
            }
            if (booleanExtra4) {
                str = str + ",ptp";
            }
            if (booleanExtra5) {
                str = str + ",audio_source";
            }
            if (booleanExtra6) {
                str = str + ",midi";
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.APP_PICTURE_IN_PICTURE_ALLOW);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(MetricsProto.MetricsEvent.APP_PICTURE_IN_PICTURE_ALLOW);
            return "";
        }
    }

    public static String e() {
        String str = "";
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_THEME, false);
        try {
            String a = o.a(new String[]{"getprop", "net.hostname"}, str);
            if (!e.a(a)) {
                str = a.trim();
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_THEME);
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_THEME);
            return str;
        }
    }

    public static String f() {
        String str;
        AppMethodBeat.i(821, false);
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                str = Build.getSerial();
            } else if (Build.VERSION.SDK_INT > 24) {
                str = Build.SERIAL;
            } else {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                str = (String) cls.getMethod("get", String.class).invoke(cls, "ro.serialno");
            }
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
        }
        AppMethodBeat.o(821);
        return str;
    }

    public static String g() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PICTURE_IN_PICTURE_ASPECT_RATIO_CHANGED, false);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            NetworkInterface byName = NetworkInterface.getByName("eth1");
            if (byName == null) {
                byName = NetworkInterface.getByName("wlan0");
            }
            if (byName == null) {
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PICTURE_IN_PICTURE_ASPECT_RATIO_CHANGED);
                return "02:00:00:00:00:02";
            }
            byte[] hardwareAddress = byName.getHardwareAddress();
            if (hardwareAddress != null && hardwareAddress.length > 0) {
                int length = hardwareAddress.length;
                for (int i = 0; i < length; i++) {
                    stringBuffer.append(String.format("%02X:", Byte.valueOf(hardwareAddress[i])));
                }
            }
            if (stringBuffer.length() > 0) {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            String stringBuffer2 = stringBuffer.toString();
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PICTURE_IN_PICTURE_ASPECT_RATIO_CHANGED);
            return stringBuffer2;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PICTURE_IN_PICTURE_ASPECT_RATIO_CHANGED);
            return "02:00:00:00:00:02";
        }
    }

    public static String h() {
        String str = "";
        AppMethodBeat.i(MetricsProto.MetricsEvent.CARRIER_DEMO_MODE_PASSWORD, false);
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            String str2 = "unknow";
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    Field declaredField = defaultAdapter.getClass().getDeclaredField("mService");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(defaultAdapter);
                    if (obj != null) {
                        str = (String) obj.getClass().getMethod("getAddress", new Class[0]).invoke(obj, new Object[0]);
                    }
                } catch (Exception unused) {
                }
                AppMethodBeat.o(MetricsProto.MetricsEvent.CARRIER_DEMO_MODE_PASSWORD);
                return str2;
            } else if (defaultAdapter != null) {
                str = defaultAdapter.getAddress();
            }
            str2 = str;
            AppMethodBeat.o(MetricsProto.MetricsEvent.CARRIER_DEMO_MODE_PASSWORD);
            return str2;
        } catch (Exception unused2) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.CARRIER_DEMO_MODE_PASSWORD);
            return str;
        }
    }

    public static String i() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_TILE_CLICK, false);
        String h = h();
        if (h != null) {
            h = h.replaceAll(":", "").toLowerCase();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_TILE_CLICK);
        return h;
    }

    public static String j() {
        return Build.MODEL;
    }

    public static String k() {
        return Build.BRAND;
    }

    public static String l() {
        return Build.VERSION.RELEASE;
    }

    public static String m() {
        String str;
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_ADVANCED_BUTTON_EXPAND, false);
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), TelephonyProperties.PROPERTY_BASEBAND_VERSION, "no message");
        } catch (Exception unused) {
            str = "unknow";
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_ADVANCED_BUTTON_EXPAND);
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0035 A[SYNTHETIC, Splitter:B:16:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0051 A[Catch:{ Exception -> 0x004d, all -> 0x004b }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0062 A[SYNTHETIC, Splitter:B:30:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x006a A[Catch:{ IOException -> 0x0066 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x006f A[Catch:{ IOException -> 0x0066 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String n() {
        /*
            r0 = 839(0x347, float:1.176E-42)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x0029 }
            java.lang.String r3 = "cat /proc/version"
            java.lang.Process r2 = r2.exec(r3)     // Catch:{ Exception -> 0x0029 }
            java.io.InputStream r3 = r2.getInputStream()     // Catch:{ Exception -> 0x0026 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0026 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0026 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0024 }
            r5 = 8192(0x2000, float:1.14794E-41)
            r3.<init>(r4, r5)     // Catch:{ Exception -> 0x0024 }
            r1 = r3
            goto L_0x002f
        L_0x0024:
            r3 = move-exception
            goto L_0x002c
        L_0x0026:
            r3 = move-exception
            r4 = r1
            goto L_0x002c
        L_0x0029:
            r3 = move-exception
            r2 = r1
            r4 = r2
        L_0x002c:
            r3.printStackTrace()
        L_0x002f:
            java.lang.String r3 = ""
            r5 = r3
        L_0x0033:
            if (r1 == 0) goto L_0x004f
            java.lang.String r6 = r1.readLine()     // Catch:{ Exception -> 0x004d }
            if (r6 == 0) goto L_0x004f
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004d }
            r7.<init>()     // Catch:{ Exception -> 0x004d }
            r7.append(r5)     // Catch:{ Exception -> 0x004d }
            r7.append(r6)     // Catch:{ Exception -> 0x004d }
            java.lang.String r5 = r7.toString()     // Catch:{ Exception -> 0x004d }
            goto L_0x0033
        L_0x004b:
            r3 = move-exception
            goto L_0x008d
        L_0x004d:
            r5 = move-exception
            goto L_0x0077
        L_0x004f:
            if (r5 == 0) goto L_0x0059
            boolean r6 = r5.equals(r3)     // Catch:{ Exception -> 0x004d }
            if (r6 != 0) goto L_0x0059
            r3 = r5
            goto L_0x0060
        L_0x0059:
            java.lang.String r5 = "os.version"
            java.lang.String r3 = java.lang.System.getProperty(r5)     // Catch:{ Exception -> 0x004d }
        L_0x0060:
            if (r4 == 0) goto L_0x0068
            r4.close()     // Catch:{ IOException -> 0x0066 }
            goto L_0x0068
        L_0x0066:
            r1 = move-exception
            goto L_0x0073
        L_0x0068:
            if (r1 == 0) goto L_0x006d
            r1.close()     // Catch:{ IOException -> 0x0066 }
        L_0x006d:
            if (r2 == 0) goto L_0x0089
            r2.destroy()     // Catch:{ IOException -> 0x0066 }
            goto L_0x0089
        L_0x0073:
            r1.printStackTrace()
            goto L_0x0089
        L_0x0077:
            r5.printStackTrace()     // Catch:{ all -> 0x004b }
            if (r4 == 0) goto L_0x007f
            r4.close()
        L_0x007f:
            if (r1 == 0) goto L_0x0084
            r1.close()
        L_0x0084:
            if (r2 == 0) goto L_0x0089
            r2.destroy()
        L_0x0089:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r3
        L_0x008d:
            if (r4 == 0) goto L_0x0095
            r4.close()     // Catch:{ IOException -> 0x0093 }
            goto L_0x0095
        L_0x0093:
            r1 = move-exception
            goto L_0x00a0
        L_0x0095:
            if (r1 == 0) goto L_0x009a
            r1.close()     // Catch:{ IOException -> 0x0093 }
        L_0x009a:
            if (r2 == 0) goto L_0x00a3
            r2.destroy()     // Catch:{ IOException -> 0x0093 }
            goto L_0x00a3
        L_0x00a0:
            r1.printStackTrace()
        L_0x00a3:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.risk.j.n():java.lang.String");
    }

    public static boolean o() {
        boolean z = false;
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_SETTINGS_SEARCH_RESULT_RANK, false);
        try {
            if (new File("/system/bin/su").exists() || new File("/system/xbin/su").exists()) {
                z = true;
            }
        } catch (Exception unused) {
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SETTINGS_SEARCH_RESULT_RANK);
        return z;
    }

    public static String c(Context context) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_MENU_BATTERY_USAGE_ALERTS, false);
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
            String str = displayMetrics.heightPixels + Constants.ACCEPT_TIME_SEPARATOR_SP + displayMetrics.widthPixels + Constants.ACCEPT_TIME_SEPARATOR_SP + displayMetrics.densityDpi;
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_MENU_BATTERY_USAGE_ALERTS);
            return str;
        } catch (Exception unused) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_MENU_BATTERY_USAGE_ALERTS);
            return "";
        }
    }

    public static int d(Context context) {
        int i = 0;
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_SETTINGS_PREFERENCE_CHANGE_NAME, false);
        try {
            i = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SETTINGS_PREFERENCE_CHANGE_NAME);
        return i;
    }

    public static long e(Context context) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_SETTINGS_HARDWARE_INFO, false);
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            long j = memoryInfo.totalMem;
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_SETTINGS_HARDWARE_INFO);
            return j;
        } catch (Exception unused) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_SETTINGS_HARDWARE_INFO);
            return 0;
        }
    }

    public static long p() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_MASTER_SWITCH_BLUETOOTH_TOGGLE, false);
        long j = 0;
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                j = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_MASTER_SWITCH_BLUETOOTH_TOGGLE);
            return j;
        } catch (Exception unused) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_MASTER_SWITCH_BLUETOOTH_TOGGLE);
            return 0;
        }
    }

    public static long q() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_DISABLE_APP, false);
        long j = 0;
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                j = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_DISABLE_APP);
            return j;
        } catch (Exception unused) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_DISABLE_APP);
            return 0;
        }
    }

    public static String f(Context context) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_APPOP_GRANT_ACCESS_NOTIFICATIONS, false);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String str = packageInfo != null ? packageInfo.packageName : "";
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APPOP_GRANT_ACCESS_NOTIFICATIONS);
            return str;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APPOP_GRANT_ACCESS_NOTIFICATIONS);
            return null;
        }
    }

    public static String g(Context context) {
        ApplicationInfo applicationInfo;
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_APPOP_REQUEST_SYSTEM_ALERT_WINDOW, false);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (!(packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null)) {
                String string = context.getResources().getString(applicationInfo.labelRes);
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APPOP_REQUEST_SYSTEM_ALERT_WINDOW);
                return string;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APPOP_REQUEST_SYSTEM_ALERT_WINDOW);
        return null;
    }

    public static String h(Context context) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_APPOP_REQUEST_WRITE_SETTINGS, false);
        try {
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    String str = runningAppProcessInfo.processName;
                    AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APPOP_REQUEST_WRITE_SETTINGS);
                    return str;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APPOP_REQUEST_WRITE_SETTINGS);
        return null;
    }

    public static long r() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_APPOP_REVOKE_WRITE_SETTINGS, false);
        long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APPOP_REVOKE_WRITE_SETTINGS);
        return currentTimeMillis;
    }

    public static long s() {
        AppMethodBeat.i(896, false);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        AppMethodBeat.o(896);
        return elapsedRealtime;
    }
}
