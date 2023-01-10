package cn.missfresh.module.base.utils;

import android.app.job.JobInfo;
import android.util.TimeUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.text.DecimalFormat;

/* compiled from: NumberFormatter */
public class ah {
    public static String a(int i) {
        AppMethodBeat.i(23375, false);
        if (i < 10000) {
            String valueOf = String.valueOf(Math.max(0, i));
            AppMethodBeat.o(23375);
            return valueOf;
        } else if (i <= 1000000) {
            if (i % 1000 >= 500) {
                i = ((i / 1000) * 1000) + 1000;
            }
            String format = String.format("%.1f", Float.valueOf(((float) i) / 10000.0f));
            if (format.endsWith(".0")) {
                format = format.substring(0, format.length() - 2);
            }
            String str = format + "\u4e07";
            AppMethodBeat.o(23375);
            return str;
        } else {
            AppMethodBeat.o(23375);
            return "100\u4e07+";
        }
    }

    public static String a(long j) {
        AppMethodBeat.i(23376, false);
        if (j < JobInfo.MIN_BACKOFF_MILLIS) {
            String valueOf = String.valueOf(Math.max(0L, j));
            AppMethodBeat.o(23376);
            return valueOf;
        } else if (j <= TimeUtils.NANOS_PER_MS) {
            if (j % 1000 >= 500) {
                j = ((j / 1000) * 1000) + 1000;
            }
            String format = String.format("%.1f", Float.valueOf(((float) j) / 10000.0f));
            if (format.endsWith(".0")) {
                format = format.substring(0, format.length() - 2);
            }
            String str = format + "\u4e07";
            AppMethodBeat.o(23376);
            return str;
        } else {
            AppMethodBeat.o(23376);
            return "100\u4e07+";
        }
    }

    public static String b(int i) {
        AppMethodBeat.i(23377, false);
        if (i < 10000) {
            String valueOf = String.valueOf(Math.max(0, i));
            AppMethodBeat.o(23377);
            return valueOf;
        }
        if (i % 100 >= 50) {
            i = ((i / 100) * 100) + 100;
        }
        String format = String.format("%.2f", Float.valueOf(((float) i) / 10000.0f));
        if (format.endsWith("0")) {
            format = format.substring(0, format.length() - 1);
        }
        if (format.endsWith(".0")) {
            format = format.substring(0, format.length() - 2);
        }
        String str = format + "w";
        AppMethodBeat.o(23377);
        return str;
    }

    public static String a(float f) {
        AppMethodBeat.i(23378, false);
        DecimalFormat decimalFormat = new DecimalFormat("####.00");
        if (f < 1024.0f) {
            String str = f + "bytes";
            AppMethodBeat.o(23378);
            return str;
        } else if (f < 1048576.0f) {
            String str2 = decimalFormat.format((double) (f / 1024.0f)) + "KB";
            AppMethodBeat.o(23378);
            return str2;
        } else if (f < 1.07374182E9f) {
            String str3 = decimalFormat.format((double) ((f / 1024.0f) / 1024.0f)) + "MB";
            AppMethodBeat.o(23378);
            return str3;
        } else if (f < 0.0f) {
            String str4 = decimalFormat.format((double) (((f / 1024.0f) / 1024.0f) / 1024.0f)) + "GB";
            AppMethodBeat.o(23378);
            return str4;
        } else {
            AppMethodBeat.o(23378);
            return "size: error";
        }
    }
}
