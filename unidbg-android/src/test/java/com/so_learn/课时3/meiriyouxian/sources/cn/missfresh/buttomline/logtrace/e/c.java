package cn.missfresh.buttomline.logtrace.e;

import android.app.ActivityManager;
import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import android.text.TextUtils;
import android.text.format.DateFormat;
import cn.missfresh.basiclib.tool.SystemUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

/* compiled from: DeviceInfoHelper */
public class c {
    private static volatile c a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;

    private c() {
    }

    public String toString() {
        AppMethodBeat.i(17030, false);
        String str = "DeviceInfoHelper{mAndroidId='" + this.b + DateFormat.QUOTE + ", mImei='" + this.c + DateFormat.QUOTE + ", mImsi='" + this.d + DateFormat.QUOTE + ", deviceName='" + Build.MANUFACTURER + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + Build.MODEL + DateFormat.QUOTE + ", FINGERPRINT='" + Build.FINGERPRINT + DateFormat.QUOTE + ", mAppVersionName='" + this.f + DateFormat.QUOTE + ", mAppVersionCode='" + this.g + DateFormat.QUOTE + ", mScreenResolution='" + this.h + DateFormat.QUOTE + ", mLanguage='" + this.i + DateFormat.QUOTE + '}';
        AppMethodBeat.o(17030);
        return str;
    }

    public static c a() {
        AppMethodBeat.i(17032, false);
        if (a == null) {
            synchronized (c.class) {
                try {
                    if (a == null) {
                        a = new c();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(17032);
                    throw th;
                }
            }
        }
        c cVar = a;
        AppMethodBeat.o(17032);
        return cVar;
    }

    public String a(Context context) {
        AppMethodBeat.i(17035, false);
        if (TextUtils.isEmpty(this.b)) {
            this.b = SystemUtils.d(context);
            if (TextUtils.isEmpty(this.b)) {
                this.b = b.a(context);
            }
        }
        String str = this.b;
        AppMethodBeat.o(17035);
        return str;
    }

    public String b(Context context) {
        AppMethodBeat.i(17046, false);
        if (TextUtils.isEmpty(this.e)) {
            this.e = context.getPackageName();
        }
        String str = this.e;
        AppMethodBeat.o(17046);
        return str;
    }

    public String c(Context context) {
        AppMethodBeat.i(17050, false);
        if (TextUtils.isEmpty(this.f)) {
            try {
                this.f = context.getPackageManager().getPackageInfo(b(context), 0).versionName;
            } catch (Exception e) {
                d.a("CensusConfig", e);
            }
        }
        String str = this.f;
        AppMethodBeat.o(17050);
        return str;
    }

    public String d(Context context) {
        String str;
        AppMethodBeat.i(17053, false);
        try {
            int i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            if (i > 0) {
                int i2 = i / 1000000;
                int i3 = (i % 1000000) / 10000;
                int i4 = (i % 10000) / 100;
                int i5 = i % 100;
                if (i2 > 0) {
                    str = i2 + "." + i3 + "." + i4 + "." + i5;
                } else {
                    str = null;
                }
                AppMethodBeat.o(17053);
                return str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String c = c(context);
        AppMethodBeat.o(17053);
        return c;
    }

    public String e(Context context) {
        AppMethodBeat.i(17057, false);
        if (TextUtils.isEmpty(this.g)) {
            try {
                this.g = String.valueOf(context.getPackageManager().getPackageInfo(b(context), 0).versionCode);
            } catch (Exception e) {
                d.a("CensusConfig", e);
            }
        }
        String str = this.g;
        AppMethodBeat.o(17057);
        return str;
    }

    public String b() {
        AppMethodBeat.i(17064, false);
        if (TextUtils.isEmpty(this.i)) {
            this.i = Locale.getDefault().getLanguage();
        }
        String str = this.i;
        AppMethodBeat.o(17064);
        return str;
    }

    public String c() {
        return Build.VERSION.RELEASE;
    }

    public String d() {
        return Build.MODEL;
    }

    public String e() {
        AppMethodBeat.i(17073, false);
        String str = ((Runtime.getRuntime().totalMemory() / 1024) / 1024) + "M";
        AppMethodBeat.o(17073);
        return str;
    }

    public boolean f(Context context) {
        boolean z = false;
        AppMethodBeat.i(17075, false);
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            AppMethodBeat.o(17075);
            return true;
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null && runningAppProcesses.size() > 0) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                    if (runningAppProcessInfo.importance == 400) {
                        z = true;
                    }
                    AppMethodBeat.o(17075);
                    return z;
                }
            }
        }
        AppMethodBeat.o(17075);
        return false;
    }

    public boolean f() {
        boolean z = false;
        AppMethodBeat.i(17087, false);
        if (g() || h() || i()) {
            z = true;
        }
        AppMethodBeat.o(17087);
        return z;
    }

    private static boolean g() {
        boolean z = false;
        AppMethodBeat.i(17089, false);
        String str = Build.TAGS;
        if (str != null && str.contains("test-keys")) {
            z = true;
        }
        AppMethodBeat.o(17089);
        return z;
    }

    private static boolean h() {
        AppMethodBeat.i(17092, false);
        for (String str : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}) {
            if (new File(str).exists()) {
                AppMethodBeat.o(17092);
                return true;
            }
        }
        AppMethodBeat.o(17092);
        return false;
    }

    private static boolean i() {
        AppMethodBeat.i(17096, false);
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            if (new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null) {
                if (process != null) {
                    process.destroy();
                }
                AppMethodBeat.o(17096);
                return true;
            }
            if (process != null) {
                process.destroy();
            }
            AppMethodBeat.o(17096);
            return false;
        } catch (Throwable unused) {
            if (process != null) {
                process.destroy();
            }
            AppMethodBeat.o(17096);
            return false;
        }
    }

    public int g(Context context) {
        AppMethodBeat.i(17100, false);
        int i = context.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        AppMethodBeat.o(17100);
        return i;
    }

    public int h(Context context) {
        AppMethodBeat.i(17102, false);
        int i = context.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        AppMethodBeat.o(17102);
        return i;
    }
}
