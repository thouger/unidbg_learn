package cn.com.chinatelecom.account.api;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import cn.com.chinatelecom.account.api.a.b;
import cn.com.chinatelecom.account.api.c.d;
import cn.com.chinatelecom.account.api.c.g;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.security.interfaces.RSAPublicKey;

public final class ClientUtils {
    private static final String TAG = ClientUtils.class.getSimpleName();
    public static final int TYPE_SDK_API = 0;
    public static final int TYPE_SDK_HY = 1;
    private static int sdkType = 0;

    static {
        AppMethodBeat.i(7756, false);
        AppMethodBeat.o(7756);
    }

    public static String enrdata(String str, String str2) {
        AppMethodBeat.i(7726, false);
        try {
            String a = b.a(str, (RSAPublicKey) b.a(str2));
            AppMethodBeat.o(7726);
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(7726);
            return "";
        }
    }

    public static String getAT(Context context) {
        AppMethodBeat.i(7717, false);
        try {
            String className = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getClassName();
            AppMethodBeat.o(7717);
            return className;
        } catch (Throwable th) {
            th.printStackTrace();
            AppMethodBeat.o(7717);
            return "error";
        }
    }

    public static String getApiVersion() {
        return "3.0";
    }

    public static String getCurrentNetworkType(Context context) {
        AppMethodBeat.i(7707, false);
        String f = g.f(context);
        AppMethodBeat.o(7707);
        return f;
    }

    public static boolean getHealthy(Context context) {
        AppMethodBeat.i(7741, false);
        try {
            boolean d = d.d(context);
            AppMethodBeat.o(7741);
            return d;
        } catch (Throwable th) {
            String str = TAG;
            CtAuth.warn(str, "getHealthy error \uff1a" + th.getMessage(), th);
            AppMethodBeat.o(7741);
            return false;
        }
    }

    public static boolean getMacData() {
        AppMethodBeat.i(7729, false);
        try {
            boolean c = d.c();
            AppMethodBeat.o(7729);
            return c;
        } catch (Throwable th) {
            String str = TAG;
            CtAuth.warn(str, "getMacData error \uff1a" + th.getMessage(), th);
            AppMethodBeat.o(7729);
            return false;
        }
    }

    public static String getMobileBrand() {
        return Build.BRAND;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static boolean getNetSafe(Context context) {
        AppMethodBeat.i(7736, false);
        try {
            boolean c = d.c(context);
            AppMethodBeat.o(7736);
            return c;
        } catch (Throwable th) {
            String str = TAG;
            CtAuth.warn(str, "getNetSafe error \uff1a" + th.getMessage(), th);
            AppMethodBeat.o(7736);
            return false;
        }
    }

    public static String getOnlineType(Context context) {
        AppMethodBeat.i(7709, false);
        String g = g.g(context);
        AppMethodBeat.o(7709);
        return g;
    }

    public static String getOperatorType(Context context) {
        return "";
    }

    public static String getOs() {
        AppMethodBeat.i(7712, false);
        String str = getMobileBrand() + "-" + getModel() + "-A:" + Build.VERSION.RELEASE;
        AppMethodBeat.o(7712);
        return str;
    }

    public static String getPID() {
        String str = "";
        AppMethodBeat.i(7721, false);
        try {
            String str2 = Thread.currentThread().getId() + str + Process.myPid();
            str = str2.length() > 6 ? str2.substring(0, 6) : "ctacco";
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(7721);
        return str;
    }

    public static int getSdkType() {
        return sdkType;
    }

    public static String getSdkVersion() {
        return sdkType == 1 ? "SDK-HY-v3.7.0" : "SDK-API-v3.7.0";
    }

    public static boolean getTimePass(Context context) {
        boolean z = false;
        AppMethodBeat.i(7731, false);
        try {
            if (d.b(context) || d.d()) {
                z = true;
            }
            AppMethodBeat.o(7731);
            return z;
        } catch (Throwable th) {
            String str = TAG;
            CtAuth.warn(str, "getTimePass error \uff1a" + th.getMessage(), th);
            AppMethodBeat.o(7731);
            return false;
        }
    }

    public static long getTp() {
        AppMethodBeat.i(7705, false);
        long currentTimeMillis = System.currentTimeMillis();
        AppMethodBeat.o(7705);
        return currentTimeMillis;
    }

    public static boolean isAT(Context context, String str) {
        AppMethodBeat.i(7715, false);
        try {
            boolean equals = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getClassName().equals(str);
            AppMethodBeat.o(7715);
            return equals;
        } catch (Throwable th) {
            th.printStackTrace();
            AppMethodBeat.o(7715);
            return false;
        }
    }

    public static boolean objChange(Object obj, String str) {
        AppMethodBeat.i(7745, false);
        try {
            boolean a = d.a(obj, str);
            AppMethodBeat.o(7745);
            return a;
        } catch (Throwable th) {
            String str2 = TAG;
            CtAuth.warn(str2, "objChange error \uff1a" + th.getMessage(), th);
            AppMethodBeat.o(7745);
            return false;
        }
    }

    public static void setSdkType(int i) {
        sdkType = i;
    }

    public static String strBuf() {
        AppMethodBeat.i(7751, false);
        try {
            String stringBuffer = d.b().toString();
            AppMethodBeat.o(7751);
            return stringBuffer;
        } catch (Throwable th) {
            String str = TAG;
            CtAuth.warn(str, "strBuf error \uff1a" + th.getMessage(), th);
            AppMethodBeat.o(7751);
            return "";
        }
    }
}
