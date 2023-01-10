package cn.missfresh.wsg;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SecurityManager */
public class a {
    private static Context a;

    public static int a(Context context, String str) {
        AppMethodBeat.i(22158, false);
        a = context;
        Context context2 = a;
        if (context2 == null) {
            int i = c.c;
            AppMethodBeat.o(22158);
            return i;
        }
        int a2 = SecurityLib.a(context2, str);
        AppMethodBeat.o(22158);
        return a2;
    }

    public static String a(String str) {
        AppMethodBeat.i(22161, false);
        if (str == null || str.length() == 0) {
            String valueOf = String.valueOf(c.e);
            AppMethodBeat.o(22161);
            return valueOf;
        }
        try {
            String a2 = SecurityLib.a(a, b.a(str).getBytes("UTF-8"));
            if (b.b(a2)) {
                AppMethodBeat.o(22161);
                return a2;
            }
            String valueOf2 = String.valueOf(c.g);
            AppMethodBeat.o(22161);
            return valueOf2;
        } catch (Exception e) {
            e.printStackTrace();
            String valueOf3 = String.valueOf(c.f);
            AppMethodBeat.o(22161);
            return valueOf3;
        }
    }

    public static String a(String str, byte[] bArr) {
        AppMethodBeat.i(22163, false);
        if ((str == null || str.length() == 0) && bArr.length == 0) {
            String valueOf = String.valueOf(c.e);
            AppMethodBeat.o(22163);
            return valueOf;
        }
        try {
            String a2 = SecurityLib.a(a, b.a(str, bArr).getBytes("UTF-8"));
            if (b.b(a2)) {
                AppMethodBeat.o(22163);
                return a2;
            }
            String valueOf2 = String.valueOf(c.g);
            AppMethodBeat.o(22163);
            return valueOf2;
        } catch (Exception e) {
            e.printStackTrace();
            String valueOf3 = String.valueOf(c.f);
            AppMethodBeat.o(22163);
            return valueOf3;
        }
    }
}
