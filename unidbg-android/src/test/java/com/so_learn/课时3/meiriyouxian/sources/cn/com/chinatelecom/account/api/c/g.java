package cn.com.chinatelecom.account.api.c;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.CtAuth;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.connect.common.Constants;
import java.lang.reflect.Method;

public class g {
    private static int a(int i) {
        int i2 = -101;
        if (i != -101) {
            i2 = -1;
            if (i != -1) {
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                    case 16:
                        return 1;
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
                        return 2;
                    case 13:
                    case 18:
                    case 19:
                        return 3;
                    default:
                        return i;
                }
            }
        }
        return i2;
    }

    public static NetworkInfo a(Context context) {
        AppMethodBeat.i(8292, false);
        NetworkInfo activeNetworkInfo = context == null ? null : ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        AppMethodBeat.o(8292);
        return activeNetworkInfo;
    }

    public static String a() {
        return "id6.me";
    }

    public static boolean b(Context context) {
        boolean z = false;
        AppMethodBeat.i(8296, false);
        NetworkInfo a = a(context);
        if (a != null && a.isAvailable()) {
            z = true;
        }
        AppMethodBeat.o(8296);
        return z;
    }

    public static boolean c(Context context) {
        AppMethodBeat.i(8299, false);
        NetworkInfo a = a(context);
        if (a == null || a.getType() != 0) {
            AppMethodBeat.o(8299);
            return false;
        }
        AppMethodBeat.o(8299);
        return true;
    }

    public static boolean d(Context context) {
        AppMethodBeat.i(8301, false);
        if (context == null) {
            AppMethodBeat.o(8301);
            return true;
        }
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            boolean booleanValue = ((Boolean) declaredMethod.invoke((ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE), new Object[0])).booleanValue();
            AppMethodBeat.o(8301);
            return booleanValue;
        } catch (Throwable th) {
            CtAuth.warn("NetUtil", "isMobileEnable error ", th);
            AppMethodBeat.o(8301);
            return true;
        }
    }

    public static String e(Context context) {
        AppMethodBeat.i(8309, false);
        int h = h(context);
        String str = "null";
        if (h == -101) {
            str = "WIFI";
        } else if (!(h == -1 || h == 0)) {
            str = h != 1 ? h != 2 ? h != 3 ? Integer.toString(h) : "4G" : "3G" : "2G";
        }
        AppMethodBeat.o(8309);
        return str;
    }

    public static String f(Context context) {
        AppMethodBeat.i(8311, false);
        String e = e(context);
        if (e == null || !e.equals("WIFI") || !d(context)) {
            AppMethodBeat.o(8311);
            return e;
        }
        AppMethodBeat.o(8311);
        return "BOTH";
    }

    public static String g(Context context) {
        AppMethodBeat.i(8313, false);
        String f = f(context);
        if (TextUtils.isEmpty(f) || f.equals("null")) {
            AppMethodBeat.o(8313);
            return Constants.VIA_REPORT_TYPE_WPA_STATE;
        } else if (f.equals("2G")) {
            AppMethodBeat.o(8313);
            return Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
        } else if (f.equals("3G")) {
            AppMethodBeat.o(8313);
            return Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE;
        } else if (f.equals("4G")) {
            AppMethodBeat.o(8313);
            return Constants.VIA_REPORT_TYPE_SET_AVATAR;
        } else if (f.equals("WIFI")) {
            AppMethodBeat.o(8313);
            return Constants.VIA_REPORT_TYPE_JOININ_GROUP;
        } else {
            boolean equals = f.equals("BOTH");
            AppMethodBeat.o(8313);
            return equals ? Constants.VIA_REPORT_TYPE_MAKE_FRIEND : Constants.VIA_REPORT_TYPE_WPA_STATE;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0032, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
        r5.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0045, code lost:
        r5.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0044 A[ExcHandler: NullPointerException (r5v2 'e' java.lang.NullPointerException A[CUSTOM_DECLARE]), Splitter:B:1:0x0006] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int h(android.content.Context r5) {
        /*
            r0 = 0
            r1 = 8304(0x2070, float:1.1636E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            android.net.NetworkInfo r2 = a(r5)     // Catch:{ NullPointerException -> 0x0044, Exception -> 0x003f }
            if (r2 == 0) goto L_0x003d
            boolean r3 = r2.isAvailable()     // Catch:{ NullPointerException -> 0x0044, Exception -> 0x003f }
            if (r3 == 0) goto L_0x003d
            boolean r3 = r2.isConnected()     // Catch:{ NullPointerException -> 0x0044, Exception -> 0x003f }
            if (r3 == 0) goto L_0x003d
            int r3 = r2.getType()     // Catch:{ NullPointerException -> 0x0044, Exception -> 0x003f }
            r4 = 1
            if (r3 != r4) goto L_0x0022
            r0 = -101(0xffffffffffffff9b, float:NaN)
            goto L_0x0048
        L_0x0022:
            if (r3 != 0) goto L_0x0048
            java.lang.String r3 = "phone"
            java.lang.Object r5 = r5.getSystemService(r3)     // Catch:{ Exception -> 0x0032, NullPointerException -> 0x0044 }
            android.telephony.TelephonyManager r5 = (android.telephony.TelephonyManager) r5     // Catch:{ Exception -> 0x0032, NullPointerException -> 0x0044 }
            int r0 = r5.getNetworkType()     // Catch:{ Exception -> 0x0032, NullPointerException -> 0x0044 }
            goto L_0x0036
        L_0x0032:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0036:
            if (r0 != 0) goto L_0x0048
            int r0 = r2.getSubtype()
            goto L_0x0048
        L_0x003d:
            r0 = -1
            goto L_0x0048
        L_0x003f:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x0048
        L_0x0044:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0048:
            int r5 = a(r0)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.api.c.g.h(android.content.Context):int");
    }
}
