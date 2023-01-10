package cn.missfresh.module.base.utils;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: DateUtils */
public class t {
    public static String a(long j) {
        AppMethodBeat.i(23278, false);
        long j2 = j / 86400;
        long j3 = j % 86400;
        long j4 = j3 / 3600;
        long j5 = j3 % 3600;
        long j6 = j5 / 60;
        long j7 = j5 % 60;
        if (j2 > 0) {
            String str = j2 + "\u5929" + j4 + "\u5c0f\u65f6" + j6 + "\u5206";
            AppMethodBeat.o(23278);
            return str;
        } else if (j4 > 0) {
            String str2 = j4 + "\u5c0f\u65f6" + j6 + "\u5206";
            AppMethodBeat.o(23278);
            return str2;
        } else {
            String str3 = j6 + "\u5206";
            AppMethodBeat.o(23278);
            return str3;
        }
    }
}
