package cn.com.chinatelecom.account.api.c;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class h {
    private static i a = new k();

    static {
        AppMethodBeat.i(8328, false);
        AppMethodBeat.o(8328);
    }

    public static String a(Context context) {
        AppMethodBeat.i(8321, false);
        String b = a.b(context) ? a.b() : a.a();
        AppMethodBeat.o(8321);
        return b;
    }

    public static String a(Context context, String str, String str2, String str3, long j, String str4) {
        AppMethodBeat.i(8322, false);
        boolean b = a.b(context);
        i iVar = a;
        String b2 = b ? iVar.b(context, str, str2, str3, j, str4) : iVar.a(context, str, str2, str3, j, str4);
        AppMethodBeat.o(8322);
        return b2;
    }

    public static String a(String str, String str2) {
        AppMethodBeat.i(8325, false);
        String a2 = a.a(str, str2);
        AppMethodBeat.o(8325);
        return a2;
    }
}
