package cn.missfresh.buttomline.logtrace.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import cn.missfresh.basiclib.tool.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.c;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/* compiled from: DeviceIdsHelper */
public class b {
    public static final SimpleDateFormat a = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    private static SharedPreferences b;

    static {
        AppMethodBeat.i(17023, false);
        AppMethodBeat.o(17023);
    }

    static String a(Context context) {
        AppMethodBeat.i(16990, false);
        String c = c(context);
        if (TextUtils.isEmpty(c)) {
            c = b(context);
            if (TextUtils.isEmpty(c)) {
                c = e.a(b());
            }
            a(d(context), c);
        }
        AppMethodBeat.o(16990);
        return c;
    }

    private static String b() {
        AppMethodBeat.i(16993, false);
        String str = UUID.randomUUID().toString() + System.currentTimeMillis();
        AppMethodBeat.o(16993);
        return str;
    }

    private static String b(Context context) {
        AppMethodBeat.i(16995, false);
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (!TextUtils.isEmpty(string) && string.length() < 32) {
            String a2 = e.a(string);
            if (a2.length() + string.length() > 32) {
                string = string + a2.substring(0, 32 - string.length());
            }
        }
        AppMethodBeat.o(16995);
        return string;
    }

    private static String c(Context context) {
        AppMethodBeat.i(16997, false);
        String a2 = a(d(context));
        AppMethodBeat.o(16997);
        return a2;
    }

    private static String d(Context context) {
        AppMethodBeat.i(17000, false);
        String str = e(context) + File.separator + "stat.aid";
        AppMethodBeat.o(17000);
        return str;
    }

    private static String a(String str) {
        AppMethodBeat.i(17006, false);
        try {
            String b2 = c.b(new File(str));
            AppMethodBeat.o(17006);
            return b2;
        } catch (Exception unused) {
            AppMethodBeat.o(17006);
            return "";
        }
    }

    private static void a(String str, String str2) {
        AppMethodBeat.i(17008, false);
        try {
            c.a(str, str2, true);
        } catch (Exception unused) {
        }
        AppMethodBeat.o(17008);
    }

    private static String e(Context context) {
        AppMethodBeat.i(17011, false);
        String str = c.a(context) + "censusconfig";
        AppMethodBeat.o(17011);
        return str;
    }

    public static String a(Context context, String str) {
        String str2;
        int i = 17018;
        AppMethodBeat.i(17018, false);
        if (b == null) {
            b = context.getSharedPreferences("seq.id", 0);
        }
        c();
        synchronized (b.class) {
            try {
                long j = b.getLong(str, -1) + 1;
                b.edit().putLong(str, j).apply();
                str2 = e.a(System.currentTimeMillis(), a) + Session.SESSION_SEPARATION_CHAR_CHILD + j;
            } finally {
                AppMethodBeat.o(i);
            }
        }
        return str2;
    }

    private static void c() {
        AppMethodBeat.i(17020, false);
        String format = DateFormat.getDateInstance(3, Locale.getDefault()).format(new Date());
        String string = b.getString("seq_time", "");
        if (TextUtils.isEmpty(string)) {
            b.edit().putString("seq_time", format).apply();
            AppMethodBeat.o(17020);
            return;
        }
        if (!format.equals(string)) {
            b.edit().clear().apply();
            b.edit().putString("seq_time", format).apply();
        }
        AppMethodBeat.o(17020);
    }

    public static SharedPreferences a() {
        return b;
    }
}
