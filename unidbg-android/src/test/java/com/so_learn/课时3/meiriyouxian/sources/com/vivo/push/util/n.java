package com.vivo.push.util;

import android.content.Context;
import android.location.GnssNavigationMessage;
import android.net.UrlQuerySanitizer;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.ims.ImsReasonInfo;

/* compiled from: LogUtil */
public final class n {
    public static final m a = new l();
    private static boolean b = y.b("persist.sys.log.ctrl", "no").equals("yes");
    private static boolean c;

    static {
        AppMethodBeat.i(1543, false);
        AppMethodBeat.o(1543);
    }

    public static boolean a() {
        return b;
    }

    public static void a(boolean z) {
        b = z;
        c = z;
    }

    public static int a(String str, String str2) {
        AppMethodBeat.i(ImsReasonInfo.CODE_RADIO_LINK_LOST, false);
        int a2 = a.a(str, str2);
        AppMethodBeat.o(ImsReasonInfo.CODE_RADIO_LINK_LOST);
        return a2;
    }

    public static int a(String str, Throwable th) {
        AppMethodBeat.i(ImsReasonInfo.CODE_RADIO_RELEASE_ABNORMAL, false);
        int a2 = a.a(str, th);
        AppMethodBeat.o(ImsReasonInfo.CODE_RADIO_RELEASE_ABNORMAL);
        return a2;
    }

    public static int a(String str, String str2, Throwable th) {
        AppMethodBeat.i(1514, false);
        int a2 = a.a(str, str2, th);
        AppMethodBeat.o(1514);
        return a2;
    }

    public static int b(String str, String str2) {
        AppMethodBeat.i(1517, false);
        int b2 = a.b(str, str2);
        AppMethodBeat.o(1517);
        return b2;
    }

    public static int c(String str, String str2) {
        AppMethodBeat.i(1518, false);
        int c2 = a.c(str, str2);
        AppMethodBeat.o(1518);
        return c2;
    }

    public static int d(String str, String str2) {
        AppMethodBeat.i(1521, false);
        int d = a.d(str, str2);
        AppMethodBeat.o(1521);
        return d;
    }

    public static int b(String str, String str2, Throwable th) {
        AppMethodBeat.i(1525, false);
        int b2 = a.b(str, str2, th);
        AppMethodBeat.o(1525);
        return b2;
    }

    public static int e(String str, String str2) {
        AppMethodBeat.i(1528, false);
        int e = a.e(str, str2);
        AppMethodBeat.o(1528);
        return e;
    }

    public static String a(Throwable th) {
        AppMethodBeat.i(UrlQuerySanitizer.IllegalCharacterValueSanitizer.ALL_BUT_WHITESPACE_LEGAL, false);
        String a2 = a.a(th);
        AppMethodBeat.o(UrlQuerySanitizer.IllegalCharacterValueSanitizer.ALL_BUT_WHITESPACE_LEGAL);
        return a2;
    }

    public static void a(Context context, String str) {
        AppMethodBeat.i(1536, false);
        a.a(context, str);
        AppMethodBeat.o(1536);
    }

    public static void b(Context context, String str) {
        AppMethodBeat.i(GnssNavigationMessage.TYPE_GAL_F, false);
        a.b(context, str);
        AppMethodBeat.o(GnssNavigationMessage.TYPE_GAL_F);
    }

    public static void c(Context context, String str) {
        AppMethodBeat.i(1540, false);
        a.c(context, str);
        AppMethodBeat.o(1540);
    }
}
