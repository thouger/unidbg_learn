package cn.missfresh.module.base.utils;

import android.content.Context;
import android.text.TextUtils;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.xiaomi.mipush.sdk.Constants;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: StringUtil */
public class at {
    public static String a(int i) {
        AppMethodBeat.i(23489, false);
        String b = b(i);
        AppMethodBeat.o(23489);
        return b;
    }

    public static String b(int i) {
        AppMethodBeat.i(23491, false);
        if (i % 10 != 0) {
            double a = a((((double) i) * 1.0d) / 100.0d);
            if (Double.isInfinite(a) || Double.isNaN(a)) {
                AppMethodBeat.o(23491);
                return null;
            }
            String bigDecimal = BigDecimal.valueOf(a).toString();
            AppMethodBeat.o(23491);
            return bigDecimal;
        } else if (i % 100 == 0) {
            String valueOf = String.valueOf(i / 100);
            AppMethodBeat.o(23491);
            return valueOf;
        } else {
            String valueOf2 = String.valueOf((i / 100) + "." + ((i / 10) % 10));
            AppMethodBeat.o(23491);
            return valueOf2;
        }
    }

    public static String c(int i) {
        AppMethodBeat.i(23492, false);
        int i2 = i % 100;
        int i3 = i2 / 10;
        int i4 = i / 100;
        double d = (double) i4;
        if (i2 % 10 != 0) {
            String bigDecimal = BigDecimal.valueOf(a(d + (((double) i2) * 0.01d))).toString();
            AppMethodBeat.o(23492);
            return bigDecimal;
        } else if (i3 != 0) {
            String bigDecimal2 = BigDecimal.valueOf(a(d + (((double) i3) * 0.1d))).toString();
            AppMethodBeat.o(23492);
            return bigDecimal2;
        } else {
            String valueOf = String.valueOf(i4);
            AppMethodBeat.o(23492);
            return valueOf;
        }
    }

    public static double a(double d) {
        AppMethodBeat.i(23493, false);
        double doubleValue = BigDecimal.valueOf(d).setScale(2, 4).doubleValue();
        AppMethodBeat.o(23493);
        return doubleValue;
    }

    public static boolean a(String str) {
        boolean z = false;
        AppMethodBeat.i(23494, false);
        if (str.length() == 11) {
            z = true;
        }
        AppMethodBeat.o(23494);
        return z;
    }

    public static final String a(Context context, long j) {
        AppMethodBeat.i(23495, false);
        long j2 = j / 3600;
        long j3 = j % 3600;
        String format = String.format(context.getResources().getString(R.string.duration_format_long), Long.valueOf(j2), Long.valueOf(j3 / 60), Long.valueOf(j3 % 60));
        AppMethodBeat.o(23495);
        return format;
    }

    public static String b(String str) {
        AppMethodBeat.i(23498, false);
        if (!b.a(str) && str.length() == 11) {
            str = str.substring(0, 3) + "****" + str.substring(7);
        }
        AppMethodBeat.o(23498);
        return str;
    }

    public static String c(String str) {
        AppMethodBeat.i(23499, false);
        String a = a(str, "#FF4891");
        AppMethodBeat.o(23499);
        return a;
    }

    public static String a(String str, String str2) {
        AppMethodBeat.i(23500, false);
        if (str == null || str.isEmpty()) {
            AppMethodBeat.o(23500);
            return "";
        }
        String[] split = str.split("#_\\u0024");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                sb.append(split[0]);
            } else if (i % 2 == 1) {
                sb.append(String.format("<font color=\"%1$s\">", str2));
                sb.append(split[i]);
            } else {
                sb.append("</font>");
                sb.append(split[i]);
            }
        }
        String sb2 = sb.toString();
        AppMethodBeat.o(23500);
        return sb2;
    }

    public static String d(String str) {
        AppMethodBeat.i(23503, false);
        String c = c(str.replaceAll("\\<", "#_\\$").replaceAll("\\>", "#_\\$"));
        AppMethodBeat.o(23503);
        return c;
    }

    public static String e(String str) {
        AppMethodBeat.i(23505, false);
        if (b.a(str)) {
            AppMethodBeat.o(23505);
            return "";
        }
        String[] split = str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i % 2 == 0) {
                sb.append("<font color=\"#FF4891\">");
                sb.append(split[i]);
            } else {
                sb.append("</font>");
                sb.append(split[i]);
            }
        }
        String sb2 = sb.toString();
        AppMethodBeat.o(23505);
        return sb2;
    }

    public static String a(List<String> list, char c) {
        AppMethodBeat.i(23509, false);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i));
                sb.append(c);
            }
        }
        String sb2 = sb.toString();
        AppMethodBeat.o(23509);
        return sb2;
    }

    public static boolean a(CharSequence charSequence, CharSequence charSequence2) {
        AppMethodBeat.i(23510, false);
        boolean equals = TextUtils.equals(charSequence, charSequence2);
        AppMethodBeat.o(23510);
        return equals;
    }

    public static boolean a(Object obj, Object obj2) {
        boolean z = false;
        AppMethodBeat.i(23511, false);
        if (obj == null || obj2 == null) {
            if (obj == null && obj2 == null) {
                z = true;
            }
            AppMethodBeat.o(23511);
            return z;
        }
        if (obj != obj2 && obj.equals(obj2)) {
            z = true;
        }
        AppMethodBeat.o(23511);
        return z;
    }

    public static String f(String str) {
        AppMethodBeat.i(23515, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(23515);
            return "";
        }
        Matcher matcher = Pattern.compile("[0-9]+").matcher(str);
        StringBuilder sb = new StringBuilder("");
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        String sb2 = sb.toString();
        if (sb2.startsWith("86")) {
            sb2 = sb2.substring(2);
        }
        if (sb2.trim().length() > 11) {
            String substring = sb2.substring(sb2.trim().length() - 11, sb2.trim().length());
            AppMethodBeat.o(23515);
            return substring;
        }
        AppMethodBeat.o(23515);
        return sb2;
    }
}
