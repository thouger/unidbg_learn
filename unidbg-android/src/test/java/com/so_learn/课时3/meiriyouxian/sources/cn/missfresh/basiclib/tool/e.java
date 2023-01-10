package cn.missfresh.basiclib.tool;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: TimeUtils */
public class e {
    private static final ThreadLocal<SimpleDateFormat> a = new ThreadLocal<>();

    static {
        AppMethodBeat.i(4503, false);
        AppMethodBeat.o(4503);
    }

    private static SimpleDateFormat a() {
        AppMethodBeat.i(4492, false);
        SimpleDateFormat simpleDateFormat = a.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            a.set(simpleDateFormat);
        }
        AppMethodBeat.o(4492);
        return simpleDateFormat;
    }

    public static String a(long j) {
        AppMethodBeat.i(4494, false);
        String a2 = a(j, a());
        AppMethodBeat.o(4494);
        return a2;
    }

    public static String a(long j, DateFormat dateFormat) {
        AppMethodBeat.i(4496, false);
        if (dateFormat == null) {
            AppMethodBeat.o(4496);
            return null;
        }
        String format = dateFormat.format(new Date(j));
        AppMethodBeat.o(4496);
        return format;
    }
}
