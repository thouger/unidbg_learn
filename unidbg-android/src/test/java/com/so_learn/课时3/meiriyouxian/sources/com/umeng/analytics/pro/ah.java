package com.umeng.analytics.pro;

import android.util.Log;
import com.xiaomi.mipush.sdk.Constants;

/* compiled from: Logger */
public class ah {
    private static final String a = "OpenId";
    private static boolean b;

    public static void a(boolean z) {
        Log.d(a, "setDebug:" + z);
        b = z;
    }

    public static void a(String str, Object... objArr) {
        if (b) {
            Log.d(a, e(str, objArr));
        }
    }

    public static void b(String str, Object... objArr) {
        if (b) {
            Log.i(a, e(str, objArr));
        }
    }

    public static void c(String str, Object... objArr) {
        if (b) {
            Log.w(a, e(str, objArr));
        }
    }

    public static void d(String str, Object... objArr) {
        if (b) {
            Log.e(a, e(str, objArr));
        }
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0016: APUT  (r2v0 java.lang.Object[]), (0 ??[int, short, byte, char]), (r5v1 java.lang.String) */
    private static String e(String str, Object... objArr) {
        if (str == null && objArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Object[] objArr2 = new Object[1];
        if (str == null) {
            str = "-";
        }
        int i = 0;
        objArr2[0] = str;
        sb.append(String.format("[%s] ", objArr2));
        if (objArr != null) {
            int length = objArr.length;
            while (true) {
                int i2 = i + 1;
                if (i2 >= objArr.length) {
                    break;
                }
                sb.append(a(objArr[i], objArr[i2]));
                if (i2 < length - 1) {
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
                i = i2 + 1;
            }
            if (i == objArr.length - 1) {
                sb.append(objArr[i]);
            }
        }
        return sb.toString();
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x000a: APUT  (r0v1 java.lang.Object[]), (0 ??[int, short, byte, char]), (r3v1 java.lang.Object) */
    private static String a(Object obj, Object obj2) {
        Object[] objArr = new Object[2];
        if (obj == null) {
            obj = "";
        }
        objArr[0] = obj;
        if (obj2 == null) {
            obj2 = "";
        }
        objArr[1] = obj2;
        return String.format("%s:%s", objArr);
    }
}
