package com.vivo.push.util;

import android.os.Build;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: Device */
public final class i {
    public static final boolean a = y.b("ro.vivo.product.overseas", "no").equals("yes");
    public static final String b;
    public static final boolean c = "RU".equals(b);
    public static final boolean d = "IN".equals(b);
    public static final boolean e = b("rom_1.0");
    public static final boolean f = b("rom_2.0");
    public static final boolean g = b("rom_2.5");
    public static final boolean h = b("rom_3.0");
    private static Method i;
    private static String j = null;
    private static String k = null;
    private static String l = "";
    private static String m = "";

    public static String a(String str, String str2) {
        String str3;
        AppMethodBeat.i(1433, false);
        try {
            str3 = (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            str3 = str2;
        }
        if (str3 == null || str3.length() == 0) {
            str3 = str2;
        }
        AppMethodBeat.o(1433);
        return str3;
    }

    public static synchronized String a() {
        synchronized (i.class) {
            AppMethodBeat.i(1438, false);
            if (j == null && k == null) {
                try {
                    Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class, String.class);
                    i = declaredMethod;
                    declaredMethod.setAccessible(true);
                    j = (String) i.invoke(null, "ro.vivo.rom", "@><@");
                    k = (String) i.invoke(null, "ro.vivo.rom.version", "@><@");
                } catch (Exception unused) {
                    n.b("Device", "getRomCode error");
                }
            }
            n.d("Device", "sRomProperty1 : " + j + " ; sRomProperty2 : " + k);
            String a2 = a(j);
            if (!TextUtils.isEmpty(a2)) {
                AppMethodBeat.o(1438);
                return a2;
            }
            String a3 = a(k);
            if (!TextUtils.isEmpty(a3)) {
                AppMethodBeat.o(1438);
                return a3;
            }
            AppMethodBeat.o(1438);
            return null;
        }
    }

    private static String a(String str) {
        AppMethodBeat.i(1443, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(1443);
            return null;
        }
        Matcher matcher = Pattern.compile("rom_([\\d]*).?([\\d]*)", 2).matcher(str);
        if (matcher.find()) {
            StringBuilder sb = new StringBuilder();
            sb.append(matcher.group(1));
            sb.append(TextUtils.isEmpty(matcher.group(2)) ? "0" : matcher.group(2).substring(0, 1));
            String sb2 = sb.toString();
            AppMethodBeat.o(1443);
            return sb2;
        }
        AppMethodBeat.o(1443);
        return null;
    }

    private static boolean b(String str) {
        AppMethodBeat.i(1447, false);
        String b2 = y.b("ro.vivo.rom", "");
        String b3 = y.b("ro.vivo.rom.version", "");
        n.d("Device", "ro.vivo.rom = " + b2 + " ; ro.vivo.rom.version = " + b3);
        if ((b2 == null || !b2.contains(str)) && (b3 == null || !b3.contains(str))) {
            AppMethodBeat.o(1447);
            return false;
        }
        AppMethodBeat.o(1447);
        return true;
    }

    public static boolean b() {
        AppMethodBeat.i(1449, false);
        if (TextUtils.isEmpty(Build.MANUFACTURER)) {
            n.d("Device", "Build.MANUFACTURER is null");
            AppMethodBeat.o(1449);
            return false;
        }
        n.d("Device", "Build.MANUFACTURER is " + Build.MANUFACTURER);
        if (Build.MANUFACTURER.toLowerCase().contains("bbk") || Build.MANUFACTURER.toLowerCase().startsWith("vivo")) {
            AppMethodBeat.o(1449);
            return true;
        }
        AppMethodBeat.o(1449);
        return false;
    }

    static {
        String str;
        AppMethodBeat.i(1452, false);
        if (Build.VERSION.SDK_INT >= 26) {
            str = y.b("ro.product.country.region", "N");
        } else {
            str = y.b("ro.product.customize.bbk", "N");
        }
        b = str;
        AppMethodBeat.o(1452);
    }
}
