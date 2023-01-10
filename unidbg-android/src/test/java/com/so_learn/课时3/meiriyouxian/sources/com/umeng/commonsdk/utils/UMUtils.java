package com.umeng.commonsdk.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.android.internal.telephony.PhoneConstants;
import com.umeng.analytics.pro.ai;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.b;
import com.umeng.commonsdk.internal.c;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.idtracking.i;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.microedition.khronos.opengles.GL10;

public class UMUtils {
    public static final int DEFAULT_TIMEZONE = 8;
    private static final String KEY_APP_KEY = "appkey";
    private static final String KEY_CHANNEL = "channel";
    private static final String KEY_LAST_APP_KEY = "last_appkey";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_SESSION_ID = "session_id";
    private static final String KEY_SHARED_PREFERENCES_NAME = "umeng_common_config";
    public static final String MOBILE_NETWORK = "2G/3G";
    private static final String SD_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String SP_FILE_NAME = "um_session_id";
    private static final String TAG = "UMUtils";
    public static final String UNKNOW = "";
    public static String VALUE_ABTEST_VERSION = null;
    public static String VALUE_ANALYTICS_VERSION = null;
    public static String VALUE_APM_VERSION = null;
    public static String VALUE_ASMS_VERSION = null;
    public static String VALUE_GAME_VERSION = null;
    public static String VALUE_LINK_VERSION = null;
    public static String VALUE_PUSH_VERSION = null;
    public static String VALUE_REC_VERSION_NAME = null;
    public static String VALUE_SHARE_VERSION = null;
    public static String VALUE_SMS_VERSION = null;
    public static String VALUE_VERIFY_VERSION = null;
    public static String VALUE_VISUAL_VERSION = null;
    public static final String WIFI = "Wi-Fi";
    private static final Pattern pattern = Pattern.compile("UTDID\">([^<]+)");
    private static Object spLock = new Object();

    public static String genId() {
        return "1234567890";
    }

    static {
        VALUE_ANALYTICS_VERSION = "";
        VALUE_GAME_VERSION = "";
        VALUE_PUSH_VERSION = "";
        VALUE_SHARE_VERSION = "";
        VALUE_APM_VERSION = "";
        VALUE_VERIFY_VERSION = "";
        VALUE_SMS_VERSION = "";
        VALUE_REC_VERSION_NAME = "";
        VALUE_VISUAL_VERSION = "";
        VALUE_ASMS_VERSION = "";
        VALUE_LINK_VERSION = "";
        VALUE_ABTEST_VERSION = "";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setMultiProcessSP(android.content.Context r4, java.lang.String r5, java.lang.String r6) {
        /*
            java.lang.Object r0 = com.umeng.commonsdk.utils.UMUtils.spLock
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0054
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0056 }
            if (r1 != 0) goto L_0x0054
            if (r6 != 0) goto L_0x000e
            goto L_0x0054
        L_0x000e:
            boolean r1 = isMainProgress(r4)     // Catch:{ all -> 0x0056 }
            r2 = 0
            if (r1 == 0) goto L_0x0021
            android.content.Context r4 = r4.getApplicationContext()     // Catch:{ all -> 0x0056 }
            java.lang.String r1 = "umeng_common_config"
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r1, r2)     // Catch:{ all -> 0x0056 }
            goto L_0x0045
        L_0x0021:
            java.lang.String r1 = com.umeng.commonsdk.framework.UMFrUtils.getSubProcessName(r4)     // Catch:{ all -> 0x0056 }
            android.content.Context r4 = r4.getApplicationContext()     // Catch:{ all -> 0x0056 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0056 }
            r3.<init>()     // Catch:{ all -> 0x0056 }
            r3.append(r1)     // Catch:{ all -> 0x0056 }
            java.lang.String r1 = "_"
            r3.append(r1)     // Catch:{ all -> 0x0056 }
            java.lang.String r1 = "umeng_common_config"
            r3.append(r1)     // Catch:{ all -> 0x0056 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0056 }
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r1, r2)     // Catch:{ all -> 0x0056 }
        L_0x0045:
            if (r4 == 0) goto L_0x0052
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch:{ all -> 0x0056 }
            android.content.SharedPreferences$Editor r4 = r4.putString(r5, r6)     // Catch:{ all -> 0x0056 }
            r4.commit()     // Catch:{ all -> 0x0056 }
        L_0x0052:
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            goto L_0x0059
        L_0x0054:
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            return
        L_0x0056:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            throw r4     // Catch:{ Exception -> 0x0059 }
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.setMultiProcessSP(android.content.Context, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0053, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getMultiProcessSP(android.content.Context r5, java.lang.String r6) {
        /*
            r0 = 0
            java.lang.Object r1 = com.umeng.commonsdk.utils.UMUtils.spLock
            monitor-enter(r1)
            if (r5 == 0) goto L_0x004e
            boolean r2 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0050 }
            if (r2 == 0) goto L_0x000d
            goto L_0x004e
        L_0x000d:
            boolean r2 = isMainProgress(r5)     // Catch:{ all -> 0x0050 }
            r3 = 0
            if (r2 == 0) goto L_0x0020
            android.content.Context r5 = r5.getApplicationContext()     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = "umeng_common_config"
            android.content.SharedPreferences r5 = r5.getSharedPreferences(r2, r3)     // Catch:{ all -> 0x0050 }
            goto L_0x0044
        L_0x0020:
            java.lang.String r2 = com.umeng.commonsdk.framework.UMFrUtils.getSubProcessName(r5)     // Catch:{ all -> 0x0050 }
            android.content.Context r5 = r5.getApplicationContext()     // Catch:{ all -> 0x0050 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0050 }
            r4.<init>()     // Catch:{ all -> 0x0050 }
            r4.append(r2)     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = "_"
            r4.append(r2)     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = "umeng_common_config"
            r4.append(r2)     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x0050 }
            android.content.SharedPreferences r5 = r5.getSharedPreferences(r2, r3)     // Catch:{ all -> 0x0050 }
        L_0x0044:
            if (r5 == 0) goto L_0x004c
            java.lang.String r5 = r5.getString(r6, r0)     // Catch:{ all -> 0x0050 }
            monitor-exit(r1)     // Catch:{ all -> 0x0050 }
            return r5
        L_0x004c:
            monitor-exit(r1)     // Catch:{ all -> 0x0050 }
            return r0
        L_0x004e:
            monitor-exit(r1)     // Catch:{ all -> 0x0050 }
            return r0
        L_0x0050:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0050 }
            throw r5     // Catch:{ Exception -> 0x0053 }
        L_0x0053:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getMultiProcessSP(android.content.Context, java.lang.String):java.lang.String");
    }

    public static void setLastAppkey(Context context, String str) {
        if (context != null && str != null) {
            try {
                setMultiProcessSP(context, KEY_LAST_APP_KEY, str);
            } catch (Exception e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "set last app key e is " + e);
                }
                UMCrashManager.reportCrash(context, e);
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "set last app key e is " + th);
                }
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    public static String getLastAppkey(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return getMultiProcessSP(context, KEY_LAST_APP_KEY);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get last app key e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get last app key e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static void setAppkey(Context context, String str) {
        if (context != null && str != null) {
            try {
                setMultiProcessSP(context, "appkey", str);
            } catch (Exception e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "set app key e is " + e);
                }
                UMCrashManager.reportCrash(context, e);
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "set app key e is " + th);
                }
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    public static String getAppkey(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (!TextUtils.isEmpty(UMConfigure.sAppkey)) {
                return UMConfigure.sAppkey;
            }
            return getMultiProcessSP(context, "appkey");
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app key e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app key e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static void setChannel(Context context, String str) {
        if (context != null && str != null) {
            try {
                setMultiProcessSP(context, "channel", str);
            } catch (Exception e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "set channel e is " + e);
                }
                UMCrashManager.reportCrash(context, e);
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "set channel e is " + th);
                }
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    public static String getChannel(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (!TextUtils.isEmpty(UMConfigure.sChannel)) {
                return UMConfigure.sChannel;
            }
            return getMultiProcessSP(context, "channel");
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get channel e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get channel e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    private static void safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    private static String parseId(String str) {
        if (str == null) {
            return null;
        }
        try {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    private static String readStreamToString(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[1024];
        StringWriter stringWriter = new StringWriter();
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (-1 == read) {
                return stringWriter.toString();
            }
            stringWriter.write(cArr, 0, read);
        }
    }

    public static String[] getGPU(GL10 gl10) {
        try {
            return new String[]{gl10.glGetString(7936), gl10.glGetString(7937)};
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "Could not read gpu infor, e is " + e);
            }
            return new String[0];
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "Could not read gpu infor, e is " + th);
            }
            return new String[0];
        }
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
            } catch (IOException e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "Could not read from file /proc/cpuinfo, e is " + e);
                }
            }
        } catch (FileNotFoundException e2) {
            try {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "Could not read from file /proc/cpuinfo, e is " + e2);
                }
            } catch (Exception e3) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get cpu e is " + e3);
                }
                return "";
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get cpu e is " + th);
                }
                return "";
            }
        }
        if (str != null) {
            return str.substring(str.indexOf(58) + 1).trim();
        }
        return "";
    }

    public static String getRegisteredOperator(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null && checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                return telephonyManager.getNetworkOperator();
            }
            return null;
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get registered operator e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get registered operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getNetworkOperatorName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (checkPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                return telephonyManager.getNetworkOperatorName();
            }
            return "";
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network operator e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "";
        }
    }

    public static String getDisplayResolution(Context context) {
        if (context == null) {
            return "";
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager == null) {
                return "";
            }
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            return String.valueOf(i2) + PhoneConstants.APN_TYPE_ALL + String.valueOf(i);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get display resolution e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get display resolution e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "";
        }
    }

    public static String[] getNetworkAccessMode(Context context) {
        String[] strArr = {"", ""};
        if (context == null) {
            return strArr;
        }
        try {
            if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
                strArr[0] = "";
                return strArr;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                strArr[0] = "";
                return strArr;
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo == null || networkInfo.getState() != NetworkInfo.State.CONNECTED) {
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo2 != null && networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                    strArr[0] = "2G/3G";
                    strArr[1] = networkInfo2.getSubtypeName();
                    return strArr;
                }
                return strArr;
            }
            strArr[0] = "Wi-Fi";
            return strArr;
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network access mode e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get network access mode e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
        }
    }

    public static boolean isSdCardWrittenable() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0023, code lost:
        android.util.Log.e(com.umeng.commonsdk.utils.UMUtils.TAG, "fail to read user config locale, e is " + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        android.util.Log.e(com.umeng.commonsdk.utils.UMUtils.TAG, "get locale e is " + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0056, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0059, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0023;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x001c A[ExcHandler: all (r3v6 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:3:0x000a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Locale getLocale(android.content.Context r6) {
        /*
            java.lang.String r0 = "get locale e is "
            java.lang.String r1 = "UMUtils"
            r2 = 0
            if (r6 != 0) goto L_0x000a
            return r2
        L_0x000a:
            android.content.res.Configuration r3 = new android.content.res.Configuration     // Catch:{ Exception -> 0x001e, all -> 0x001c }
            r3.<init>()     // Catch:{ Exception -> 0x001e, all -> 0x001c }
            r3.setToDefaults()     // Catch:{ Exception -> 0x001e, all -> 0x001c }
            android.content.ContentResolver r4 = r6.getContentResolver()     // Catch:{ Exception -> 0x001e, all -> 0x001c }
            android.provider.Settings.System.getConfiguration(r4, r3)     // Catch:{ Exception -> 0x001e, all -> 0x001c }
            java.util.Locale r3 = r3.locale     // Catch:{ Exception -> 0x001e, all -> 0x001c }
            goto L_0x0039
        L_0x001c:
            r3 = move-exception
            goto L_0x0040
        L_0x001e:
            r3 = move-exception
            boolean r4 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG     // Catch:{ Exception -> 0x005a, all -> 0x001c }
            if (r4 == 0) goto L_0x0038
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005a, all -> 0x001c }
            r4.<init>()     // Catch:{ Exception -> 0x005a, all -> 0x001c }
            java.lang.String r5 = "fail to read user config locale, e is "
            r4.append(r5)     // Catch:{ Exception -> 0x005a, all -> 0x001c }
            r4.append(r3)     // Catch:{ Exception -> 0x005a, all -> 0x001c }
            java.lang.String r3 = r4.toString()     // Catch:{ Exception -> 0x005a, all -> 0x001c }
            android.util.Log.e(r1, r3)     // Catch:{ Exception -> 0x005a, all -> 0x001c }
        L_0x0038:
            r3 = r2
        L_0x0039:
            if (r3 != 0) goto L_0x003f
            java.util.Locale r3 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x005a, all -> 0x001c }
        L_0x003f:
            return r3
        L_0x0040:
            boolean r4 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r4 == 0) goto L_0x0056
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            r4.append(r3)
            java.lang.String r0 = r4.toString()
            android.util.Log.e(r1, r0)
        L_0x0056:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3)
            return r2
        L_0x005a:
            r3 = move-exception
            boolean r4 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r4 == 0) goto L_0x0071
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            r4.append(r3)
            java.lang.String r0 = r4.toString()
            android.util.Log.e(r1, r0)
        L_0x0071:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getLocale(android.content.Context):java.util.Locale");
    }

    public static String getMac(Context context) {
        if (context == null) {
            return null;
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return null;
            }
            if (checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            if (!AnalyticsConstants.UM_DEBUG) {
                return "";
            }
            Log.e(TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get mac e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get mac e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getOperator(Context context) {
        if (context == null) {
            return "Unknown";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return "Unknown";
            }
            return telephonyManager.getNetworkOperator();
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get get operator e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return "Unknown";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get get operator e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return "Unknown";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        android.util.Log.e(com.umeng.commonsdk.utils.UMUtils.TAG, "get sub os name e is " + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0059, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005d, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005f, code lost:
        android.util.Log.e(com.umeng.commonsdk.utils.UMUtils.TAG, "get sub os name e is " + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0071, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0074, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f A[ExcHandler: all (r3v1 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:3:0x000a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getSubOSName(android.content.Context r6) {
        /*
            java.lang.String r0 = "get sub os name e is "
            java.lang.String r1 = "UMUtils"
            r2 = 0
            if (r6 != 0) goto L_0x000a
            return r2
        L_0x000a:
            java.util.Properties r3 = getBuildProp()     // Catch:{ Exception -> 0x005a, all -> 0x003f }
            java.lang.String r4 = "ro.miui.ui.version.name"
            java.lang.String r4 = r3.getProperty(r4)     // Catch:{ Exception -> 0x003a, all -> 0x003f }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Exception -> 0x003a, all -> 0x003f }
            if (r5 == 0) goto L_0x0035
            boolean r5 = isFlyMe()     // Catch:{ Exception -> 0x003a, all -> 0x003f }
            if (r5 == 0) goto L_0x0025
            java.lang.String r6 = "Flyme"
            goto L_0x0038
        L_0x0025:
            java.lang.String r3 = getYunOSVersion(r3)     // Catch:{ Exception -> 0x003a, all -> 0x003f }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x003a, all -> 0x003f }
            if (r3 != 0) goto L_0x0033
            java.lang.String r6 = "YunOS"
            goto L_0x0038
        L_0x0033:
            r2 = r4
            goto L_0x003e
        L_0x0035:
            java.lang.String r6 = "MIUI"
        L_0x0038:
            r2 = r6
            goto L_0x003e
        L_0x003a:
            r3 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3)
        L_0x003e:
            return r2
        L_0x003f:
            r3 = move-exception
            boolean r4 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r4 == 0) goto L_0x0056
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            r4.append(r3)
            java.lang.String r0 = r4.toString()
            android.util.Log.e(r1, r0)
        L_0x0056:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3)
            return r2
        L_0x005a:
            r3 = move-exception
            boolean r4 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r4 == 0) goto L_0x0071
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            r4.append(r3)
            java.lang.String r0 = r4.toString()
            android.util.Log.e(r1, r0)
        L_0x0071:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getSubOSName(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0033, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0038, code lost:
        android.util.Log.e(com.umeng.commonsdk.utils.UMUtils.TAG, "get sub os version e is " + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004a, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004d, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0051, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0053, code lost:
        android.util.Log.e(com.umeng.commonsdk.utils.UMUtils.TAG, "get sub os version e is " + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0065, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0068, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0033 A[ExcHandler: all (r3v1 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:3:0x000a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getSubOSVersion(android.content.Context r6) {
        /*
            java.lang.String r0 = "get sub os version e is "
            java.lang.String r1 = "UMUtils"
            r2 = 0
            if (r6 != 0) goto L_0x000a
            return r2
        L_0x000a:
            java.util.Properties r3 = getBuildProp()     // Catch:{ Exception -> 0x004e, all -> 0x0033 }
            java.lang.String r4 = "ro.miui.ui.version.name"
            java.lang.String r4 = r3.getProperty(r4)     // Catch:{ Exception -> 0x002e, all -> 0x0033 }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Exception -> 0x002e, all -> 0x0033 }
            if (r5 == 0) goto L_0x002c
            boolean r5 = isFlyMe()     // Catch:{ Exception -> 0x002e, all -> 0x0033 }
            if (r5 == 0) goto L_0x0026
            java.lang.String r6 = getFlymeVersion(r3)     // Catch:{ Exception -> 0x002c, all -> 0x0033 }
            goto L_0x002a
        L_0x0026:
            java.lang.String r6 = getYunOSVersion(r3)     // Catch:{ Exception -> 0x002c, all -> 0x0033 }
        L_0x002a:
            r2 = r6
            goto L_0x0032
        L_0x002c:
            r2 = r4
            goto L_0x0032
        L_0x002e:
            r3 = move-exception
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3)
        L_0x0032:
            return r2
        L_0x0033:
            r3 = move-exception
            boolean r4 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r4 == 0) goto L_0x004a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            r4.append(r3)
            java.lang.String r0 = r4.toString()
            android.util.Log.e(r1, r0)
        L_0x004a:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3)
            return r2
        L_0x004e:
            r3 = move-exception
            boolean r4 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r4 == 0) goto L_0x0065
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            r4.append(r3)
            java.lang.String r0 = r4.toString()
            android.util.Log.e(r1, r0)
        L_0x0065:
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r6, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getSubOSVersion(android.content.Context):java.lang.String");
    }

    private static String getYunOSVersion(Properties properties) {
        try {
            String property = properties.getProperty("ro.yunos.version");
            if (!TextUtils.isEmpty(property)) {
                return property;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String getFlymeVersion(Properties properties) {
        try {
            String lowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (lowerCase.contains("flyme os")) {
                return lowerCase.split(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER)[2];
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0026 A[SYNTHETIC, Splitter:B:13:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Properties getBuildProp() {
        /*
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x002a, all -> 0x0023 }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x002a, all -> 0x0023 }
            java.io.File r4 = android.os.Environment.getRootDirectory()     // Catch:{ IOException -> 0x002a, all -> 0x0023 }
            java.lang.String r5 = "build.prop"
            r3.<init>(r4, r5)     // Catch:{ IOException -> 0x002a, all -> 0x0023 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x002a, all -> 0x0023 }
            r0.load(r2)     // Catch:{ IOException -> 0x0021, all -> 0x001e }
            r2.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x002f
        L_0x001e:
            r0 = move-exception
            r1 = r2
            goto L_0x0024
        L_0x0021:
            r1 = r2
            goto L_0x002a
        L_0x0023:
            r0 = move-exception
        L_0x0024:
            if (r1 == 0) goto L_0x0029
            r1.close()     // Catch:{ IOException -> 0x0029 }
        L_0x0029:
            throw r0
        L_0x002a:
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getBuildProp():java.util.Properties");
    }

    private static boolean isFlyMe() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getDeviceType(Context context) {
        if (context == null) {
            return "Phone";
        }
        try {
            if ((context.getResources().getConfiguration().screenLayout & 15) >= 3) {
                return "Tablet";
            }
            return "Phone";
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get device type e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get device type e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    public static String getAppVersionCode(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version code e is " + e);
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version code e is " + th);
            }
            return "";
        }
    }

    public static String getAppVersinoCode(Context context, String str) {
        if (!(context == null || str == null)) {
            try {
                return String.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
            } catch (Exception e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get app version code e is " + e);
                }
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get app version code e is " + th);
                }
                return "";
            }
        }
        return "";
    }

    public static String getAppVersionName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version name e is " + e);
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app version name e is " + th);
            }
            return "";
        }
    }

    public static String getAppVersionName(Context context, String str) {
        if (!(context == null || str == null)) {
            try {
                return context.getPackageManager().getPackageInfo(str, 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get app version name e is " + e);
                }
                UMCrashManager.reportCrash(context, e);
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e(TAG, "get app version name e is " + th);
                }
                UMCrashManager.reportCrash(context, th);
                return "";
            }
        }
        return "";
    }

    public static boolean checkPermission(Context context, String str) {
        if (context == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(context, str)).intValue() != 0) {
                    return false;
                }
            } catch (Exception e) {
                UMCrashManager.reportCrash(context, e);
                return false;
            }
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    private static String byte2HexFormatted(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public static boolean isDebug(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            return false;
        }
    }

    public static String getAppName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app name e is " + e);
            }
            UMCrashManager.reportCrash(context, e);
            return null;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e(TAG, "get app name e is " + th);
            }
            UMCrashManager.reportCrash(context, th);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0046, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0055, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0057, code lost:
        android.util.Log.e(com.umeng.commonsdk.utils.UMUtils.TAG, "MD5 e is " + r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0069, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006a, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006d, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006f, code lost:
        android.util.Log.e(com.umeng.commonsdk.utils.UMUtils.TAG, "MD5 e is " + r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0081, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0046 A[ExcHandler: all (r10v4 'th' java.lang.Object A[CUSTOM_DECLARE]), Splitter:B:3:0x000a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String MD5(java.lang.String r10) {
        /*
            java.lang.String r0 = "MD5 e is "
            java.lang.String r1 = "UMUtils"
            r2 = 0
            if (r10 != 0) goto L_0x000a
            return r2
        L_0x000a:
            byte[] r3 = r10.getBytes()     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            java.lang.String r4 = "MD5"
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r4)     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            r4.reset()     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            r4.update(r3)     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            byte[] r3 = r4.digest()     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            r4.<init>()     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            r5 = 0
            r6 = r5
        L_0x0026:
            int r7 = r3.length     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            if (r6 >= r7) goto L_0x0041
            java.lang.String r7 = "%02X"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            byte r9 = r3[r6]     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            java.lang.Byte r9 = java.lang.Byte.valueOf(r9)     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            r8[r5] = r9     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            java.lang.String r7 = java.lang.String.format(r7, r8)     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            r4.append(r7)     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            int r6 = r6 + 1
            goto L_0x0026
        L_0x0041:
            java.lang.String r10 = r4.toString()     // Catch:{ Exception -> 0x0048, all -> 0x0046 }
            return r10
        L_0x0046:
            r10 = move-exception
            goto L_0x0053
        L_0x0048:
            java.lang.String r3 = "[^[a-z][A-Z][0-9][.][_]]"
            java.lang.String r4 = ""
            java.lang.String r10 = r10.replaceAll(r3, r4)     // Catch:{ Exception -> 0x006a, all -> 0x0046 }
            return r10
        L_0x0053:
            boolean r3 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r3 == 0) goto L_0x0069
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            r3.append(r10)
            java.lang.String r10 = r3.toString()
            android.util.Log.e(r1, r10)
        L_0x0069:
            return r2
        L_0x006a:
            r10 = move-exception
            boolean r3 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r3 == 0) goto L_0x0081
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            r3.append(r10)
            java.lang.String r10 = r3.toString()
            android.util.Log.e(r1, r10)
        L_0x0081:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.MD5(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004c, code lost:
        android.util.Log.e(com.umeng.commonsdk.utils.UMUtils.TAG, "get file MD5 e is " + r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005e, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005f, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0062, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0064, code lost:
        android.util.Log.e(com.umeng.commonsdk.utils.UMUtils.TAG, "get file MD5 e is " + r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0076, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0047 A[ExcHandler: all (r9v3 'th' java.lang.Object A[CUSTOM_DECLARE]), Splitter:B:1:0x0009] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFileMD5(java.io.File r9) {
        /*
            java.lang.String r0 = "get file MD5 e is "
            java.lang.String r1 = "UMUtils"
            r2 = 1024(0x400, float:1.435E-42)
            r3 = 0
            byte[] r4 = new byte[r2]     // Catch:{ Exception -> 0x005f, all -> 0x0047 }
            boolean r5 = r9.isFile()     // Catch:{ Exception -> 0x0046, all -> 0x0047 }
            if (r5 != 0) goto L_0x0015
            java.lang.String r9 = ""
            return r9
        L_0x0015:
            java.lang.String r5 = "MD5"
            java.security.MessageDigest r5 = java.security.MessageDigest.getInstance(r5)     // Catch:{ Exception -> 0x0046, all -> 0x0047 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0046, all -> 0x0047 }
            r6.<init>(r9)     // Catch:{ Exception -> 0x0046, all -> 0x0047 }
        L_0x0021:
            r9 = 0
            int r7 = r6.read(r4, r9, r2)     // Catch:{ Exception -> 0x0046, all -> 0x0047 }
            r8 = -1
            if (r7 == r8) goto L_0x002d
            r5.update(r4, r9, r7)     // Catch:{ Exception -> 0x0046, all -> 0x0047 }
            goto L_0x0021
        L_0x002d:
            r6.close()     // Catch:{ Exception -> 0x0046, all -> 0x0047 }
            java.math.BigInteger r2 = new java.math.BigInteger
            byte[] r4 = r5.digest()
            r5 = 1
            r2.<init>(r5, r4)
            java.lang.String r4 = "%1$032x"
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r9] = r2
            java.lang.String r9 = java.lang.String.format(r4, r5)
            return r9
        L_0x0046:
            return r3
        L_0x0047:
            r9 = move-exception
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r2 == 0) goto L_0x005e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            android.util.Log.e(r1, r9)
        L_0x005e:
            return r3
        L_0x005f:
            r9 = move-exception
            boolean r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r2 == 0) goto L_0x0076
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            android.util.Log.e(r1, r9)
        L_0x0076:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getFileMD5(java.io.File):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        android.util.Log.e(com.umeng.commonsdk.utils.UMUtils.TAG, "encrypt by SHA1 e is " + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        if (com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG != false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        android.util.Log.e(com.umeng.commonsdk.utils.UMUtils.TAG, "encrypt by SHA1 e is " + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001f A[ExcHandler: all (r4v3 'th' java.lang.Object A[CUSTOM_DECLARE]), Splitter:B:1:0x0007] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String encryptBySHA1(java.lang.String r4) {
        /*
            java.lang.String r0 = "encrypt by SHA1 e is "
            java.lang.String r1 = "UMUtils"
            r2 = 0
            byte[] r4 = r4.getBytes()     // Catch:{ Exception -> 0x0037, all -> 0x001f }
            java.lang.String r3 = "SHA1"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch:{ Exception -> 0x001e, all -> 0x001f }
            r3.update(r4)     // Catch:{ Exception -> 0x001e, all -> 0x001f }
            byte[] r4 = r3.digest()     // Catch:{ Exception -> 0x001e, all -> 0x001f }
            java.lang.String r4 = bytes2Hex(r4)     // Catch:{ Exception -> 0x001e, all -> 0x001f }
            return r4
        L_0x001e:
            return r2
        L_0x001f:
            r4 = move-exception
            boolean r3 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r3 == 0) goto L_0x0036
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            r3.append(r4)
            java.lang.String r4 = r3.toString()
            android.util.Log.e(r1, r4)
        L_0x0036:
            return r2
        L_0x0037:
            r4 = move-exception
            boolean r3 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG
            if (r3 == 0) goto L_0x004e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            r3.append(r4)
            java.lang.String r4 = r3.toString()
            android.util.Log.e(r1, r4)
        L_0x004e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.encryptBySHA1(java.lang.String):java.lang.String");
    }

    private static String bytes2Hex(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                str = str + "0";
            }
            str = str + hexString;
        }
        return str;
    }

    public static String getUMId(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return UMEnvelopeBuild.imprintProperty(context.getApplicationContext(), "umid", null);
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            return null;
        }
    }

    public static String getUmengToken(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return UMEnvelopeBuild.imprintProperty(context.getApplicationContext(), "ztoken", null);
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            return null;
        }
    }

    public static String getDeviceToken(Context context) {
        Method method;
        Object invoke;
        Method method2;
        Object invoke2;
        if (context == null) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        try {
            Class<?> cls = Class.forName("com.umeng.message.MessageSharedPrefs");
            if (cls == null || (method = cls.getMethod("getInstance", Context.class)) == null || (invoke = method.invoke(cls, applicationContext)) == null || (method2 = cls.getMethod("getDeviceToken", new Class[0])) == null || (invoke2 = method2.invoke(invoke, new Object[0])) == null || !(invoke2 instanceof String)) {
                return null;
            }
            return (String) invoke2;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getAppkeyByXML(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return null;
            }
            String string = applicationInfo.metaData.getString("UMENG_APPKEY");
            if (string != null) {
                return string.trim();
            }
            if (!AnalyticsConstants.UM_DEBUG) {
                return null;
            }
            MLog.i(AnalyticsConstants.LOG_TAG, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getChannelByXML(Context context) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("UMENG_CHANNEL")) == null) {
                return null;
            }
            String obj2 = obj.toString();
            if (obj2 != null) {
                return obj2.trim();
            }
            if (!AnalyticsConstants.UM_DEBUG) {
                return null;
            }
            MLog.i(AnalyticsConstants.LOG_TAG, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean checkPath(String str) {
        try {
            return Class.forName(str) != null;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean checkAndroidManifest(Context context, String str) {
        try {
            context.getApplicationContext().getPackageManager().getActivityInfo(new ComponentName(context.getApplicationContext().getPackageName(), str), 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean checkIntentFilterData(Context context, String str) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("tencent" + str + ":"));
            List<ResolveInfo> queryIntentActivities = context.getApplicationContext().getPackageManager().queryIntentActivities(intent, 64);
            if (queryIntentActivities.size() > 0) {
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.packageName.equals(context.getApplicationContext().getPackageName())) {
                        return true;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static boolean checkResource(Context context, String str, String str2) {
        try {
            return context.getApplicationContext().getResources().getIdentifier(str, str2, context.getApplicationContext().getPackageName()) > 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean checkMetaData(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationContext().getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData.get(str) == null) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public static String getAppMD5Signature(Context context) {
        try {
            String appMD5Signature = DeviceConfig.getAppMD5Signature(context);
            try {
                return !TextUtils.isEmpty(appMD5Signature) ? appMD5Signature.replace(":", "").toLowerCase() : appMD5Signature;
            } catch (Throwable unused) {
                return appMD5Signature;
            }
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static String getAppSHA1Key(Context context) {
        return DeviceConfig.getAppSHA1Key(context);
    }

    public static String getAppHashKey(Context context) {
        return DeviceConfig.getAppHashKey(context);
    }

    public static int getTargetSdkVersion(Context context) {
        try {
            return context.getApplicationInfo().targetSdkVersion;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static boolean isMainProgress(Context context) {
        try {
            String currentProcessName = UMFrUtils.getCurrentProcessName(context);
            String packageName = context.getApplicationContext().getPackageName();
            if (TextUtils.isEmpty(currentProcessName) || TextUtils.isEmpty(packageName) || !currentProcessName.equals(packageName)) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isApplication(Context context) {
        try {
            String name = context.getApplicationContext().getClass().getSuperclass().getName();
            if (TextUtils.isEmpty(name) || !name.equals("android.app.Application")) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getOaidRequiredTime(Context context) {
        if (!FieldManager.allow(b.G) || Build.VERSION.SDK_INT <= 28) {
            return null;
        }
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(i.a, 0);
            if (sharedPreferences != null) {
                return sharedPreferences.getString(i.c, "");
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getZid(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (!UMConfigure.needSendZcfgEnv(applicationContext)) {
            return b.a(applicationContext).a().a();
        }
        return null;
    }

    public static String getUUIDForZid(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(SP_FILE_NAME, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("session_id", "");
        }
        return "";
    }

    public static void setUUIDForZid(Context context) {
        String str;
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(SP_FILE_NAME, 0);
        try {
            str = UUID.randomUUID().toString();
        } catch (Throwable unused) {
            str = "";
        }
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("session_id", str).commit();
        }
    }

    public static String getSystemProperty(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Throwable unused) {
            return str2;
        }
    }

    private static Class<?> getClass(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void saveSDKComponent() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("a");
        if (UMConfigure.isDebugLog()) {
            UMLog.mutlInfo(2, "\u7edf\u8ba1SDK\u7248\u672c\u53f7: 9.3.8");
        }
        VALUE_ANALYTICS_VERSION = "9.3.8";
        String b = c.b();
        if (!TextUtils.isEmpty(b)) {
            VALUE_ASMS_VERSION = b;
            if (UMConfigure.isDebugLog()) {
                UMLog.mutlInfo(2, "ZID SDK\u7248\u672c\u53f7: " + b);
            }
        }
        Class<?> cls = getClass("com.umeng.analytics.game.GameSdkVersion");
        if (cls != null) {
            stringBuffer.append("g");
            try {
                String str = (String) cls.getDeclaredField("SDK_VERSION").get(cls);
                if (!TextUtils.isEmpty(str)) {
                    VALUE_GAME_VERSION = str;
                    if (UMConfigure.isDebugLog()) {
                        UMLog.mutlInfo(2, "\u6e38\u620f\u7edf\u8ba1SDK\u7248\u672c\u53f7: " + str);
                    }
                }
            } catch (Throwable unused) {
            }
        }
        Class<?> cls2 = getClass("com.umeng.vt.V");
        if (cls2 != null) {
            stringBuffer.append("v");
            try {
                String str2 = (String) cls2.getDeclaredField("VERSION").get(cls2);
                if (!TextUtils.isEmpty(str2)) {
                    VALUE_VISUAL_VERSION = str2;
                    if (UMConfigure.isDebugLog()) {
                        UMLog.mutlInfo(2, "\u53ef\u89c6\u5316\u57cb\u70b9SDK\u7248\u672c\u53f7: " + str2);
                    }
                }
            } catch (Throwable unused2) {
            }
        }
        if (getClass("com.umeng.message.PushAgent") != null) {
            stringBuffer.append("p");
            Class<?> cls3 = getClass("com.umeng.message.MsgConstant");
            if (cls3 != null) {
                try {
                    String str3 = (String) cls3.getDeclaredField("SDK_VERSION").get(cls3);
                    if (!TextUtils.isEmpty(str3)) {
                        VALUE_PUSH_VERSION = str3;
                        if (UMConfigure.isDebugLog()) {
                            UMLog.mutlInfo(2, "\u63a8\u9001SDK\u7248\u672c\u53f7: " + str3);
                        }
                    }
                } catch (Throwable unused3) {
                }
            }
        }
        if (getClass("com.umeng.socialize.UMShareAPI") != null) {
            stringBuffer.append(ai.az);
            Class<?> cls4 = getClass("com.umeng.a");
            if (cls4 != null) {
                try {
                    String str4 = (String) cls4.getDeclaredField("g").get(cls4);
                    if (!TextUtils.isEmpty(str4) && UMConfigure.isDebugLog()) {
                        UMLog.mutlInfo(2, "\u5206\u4eabSDK\u7248\u672c\u53f7: " + str4);
                    }
                } catch (Throwable unused4) {
                }
            }
        }
        if (getClass("com.umeng.error.UMError") != null) {
            stringBuffer.append("e");
        }
        if (getClass("com.umeng.umzid.ZIDManager") != null) {
            stringBuffer.append(ai.aB);
        }
        stringBuffer.append("i");
        if (!(SdkVersion.SDK_TYPE == 1 || getClass("com.umeng.commonsdk.internal.UMOplus") == null)) {
            stringBuffer.append("o");
        }
        if (getClass("com.umeng.airec.RecAgent") != null) {
            stringBuffer.append(ai.aE);
            Class<?> cls5 = getClass("com.umeng.airec.BuildConfig");
            if (cls5 != null) {
                try {
                    String str5 = (String) cls5.getDeclaredField("VERSION_NAME").get(cls5);
                    if (!TextUtils.isEmpty(str5)) {
                        VALUE_REC_VERSION_NAME = str5;
                        if (UMConfigure.isDebugLog()) {
                            UMLog.mutlInfo(2, "\u667a\u80fd\u63a8\u8350SDK\u7248\u672c\u53f7: " + str5);
                        }
                    }
                } catch (Throwable unused5) {
                }
            }
        }
        if (getClass("com.umeng.umverify.UMVerifyHelper") != null) {
            stringBuffer.append("n");
        }
        Class<?> cls6 = getClass("com.umeng.sms.UMSMS");
        if (cls6 != null) {
            stringBuffer.append("m");
            try {
                Method declaredMethod = cls6.getDeclaredMethod("getVersion", new Class[0]);
                if (declaredMethod != null) {
                    String str6 = (String) declaredMethod.invoke(cls6, new Object[0]);
                    if (!TextUtils.isEmpty(str6)) {
                        VALUE_SMS_VERSION = str6;
                        if (UMConfigure.isDebugLog()) {
                            UMLog.mutlInfo(2, "\u77ed\u4fe1\u9a8c\u8bc1\u7801SDK\u7248\u672c\u53f7: " + str6);
                        }
                    }
                }
            } catch (Throwable unused6) {
            }
        }
        try {
            Class<?> cls7 = getClass("com.umeng.umcrash.UMCrash");
            if (cls7 != null) {
                stringBuffer.append("c");
                Field declaredField = cls7.getDeclaredField("crashSdkVersion");
                declaredField.setAccessible(true);
                String str7 = (String) declaredField.get(cls7);
                if (!TextUtils.isEmpty(str7)) {
                    VALUE_APM_VERSION = str7;
                    if (UMConfigure.isDebugLog()) {
                        UMLog.mutlInfo(2, "APM SDK\u7248\u672c\u53f7: " + str7);
                    }
                }
            }
        } catch (Throwable unused7) {
        }
        Class<?> cls8 = getClass("com.umeng.umlink.MobclickLink");
        if (cls8 != null) {
            stringBuffer.append("l");
            try {
                Method declaredMethod2 = cls8.getDeclaredMethod("getVersion", new Class[0]);
                if (declaredMethod2 != null) {
                    String str8 = (String) declaredMethod2.invoke(cls8, new Object[0]);
                    if (!TextUtils.isEmpty(str8)) {
                        VALUE_LINK_VERSION = str8;
                        if (UMConfigure.isDebugLog()) {
                            UMLog.mutlInfo(2, "ULink SDK\u7248\u672c\u53f7: " + str8);
                        }
                    }
                }
            } catch (Throwable unused8) {
            }
        }
        Class<?> cls9 = getClass("com.umeng.cconfig.UMRemoteConfig");
        if (cls9 != null) {
            try {
                Method declaredMethod3 = cls9.getDeclaredMethod("getVersion", new Class[0]);
                if (declaredMethod3 != null) {
                    stringBuffer.append("t");
                    String str9 = (String) declaredMethod3.invoke(cls9, new Object[0]);
                    if (!TextUtils.isEmpty(str9)) {
                        VALUE_ABTEST_VERSION = str9;
                        if (UMConfigure.isDebugLog()) {
                            UMLog.mutlInfo(2, "UABTEST SDK\u7248\u672c\u53f7: " + str9);
                        }
                    }
                }
            } catch (Throwable unused9) {
            }
        }
        if (!TextUtils.isEmpty(stringBuffer)) {
            com.umeng.commonsdk.statistics.b.a = stringBuffer.toString();
            Log.i(AnalyticsConstants.LOG_TAG, "module init:" + com.umeng.commonsdk.statistics.b.a);
        }
    }

    public static String getApmFlag() {
        Method declaredMethod;
        try {
            Class<?> cls = getClass("com.umeng.umcrash.UMCrash");
            if (cls == null || (declaredMethod = cls.getDeclaredMethod("getUMAPMFlag", new Class[0])) == null) {
                return "";
            }
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(cls, new Object[0]);
        } catch (Throwable unused) {
            return "";
        }
    }
}
