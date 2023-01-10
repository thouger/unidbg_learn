package cn.missfresh.wsg;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SecurityLib {
    private static boolean a = false;
    private static boolean b;
    private static final Object c = new Object();

    private static native int nativeInit(Context context, String str);

    private static native String nativeSign(Context context, long j, byte[] bArr);

    static {
        AppMethodBeat.i(22155, false);
        b = false;
        try {
            System.loadLibrary("sign");
            b = true;
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        }
        AppMethodBeat.o(22155);
    }

    public static String a(Context context, byte[] bArr) {
        AppMethodBeat.i(22151, false);
        if (!a) {
            String valueOf = String.valueOf(c.d);
            AppMethodBeat.o(22151);
            return valueOf;
        }
        String nativeSign = nativeSign(context, System.currentTimeMillis(), bArr);
        AppMethodBeat.o(22151);
        return nativeSign;
    }

    public static int a(Context context, String str) {
        int i = 0;
        int i2 = 22152;
        AppMethodBeat.i(22152, false);
        if (!b || context == null) {
            int i3 = c.d;
            AppMethodBeat.o(22152);
            return i3;
        }
        synchronized (c) {
            try {
                if (!a) {
                    i = nativeInit(context, str);
                    if (i == 0) {
                        a = true;
                    }
                    c.notifyAll();
                }
            } finally {
                AppMethodBeat.o(i2);
            }
        }
        return i;
    }
}
