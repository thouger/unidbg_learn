package com.sobot.chat.utils;

import android.content.Context;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: StringUtils */
public final class ac {
    private static Pattern a = Pattern.compile("<([a-zA-Z]+)[^<>]*>");

    public static boolean a(String str) {
        return str.toLowerCase().matches("^((https|http|ftp|rtsp|mms)?://)?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\.[a-z]{2,6})(:[0-9]{1,5})?((/?)|(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$");
    }

    public static List<String> b(String str) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile("<img.*src\\s*=\\s*(.*?)[^>]*?>", 2).matcher(str);
        String str2 = "";
        while (matcher.find()) {
            str2 = str2 + Constants.ACCEPT_TIME_SEPARATOR_SP + matcher.group();
            Matcher matcher2 = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(str2);
            while (matcher2.find()) {
                arrayList.add(matcher2.group(1));
            }
        }
        return arrayList;
    }

    public static boolean a(Object obj) {
        return obj == null || "".equals(obj) || "null".equals(obj);
    }

    public static boolean c(String str) {
        return d(str) || e(str);
    }

    public static boolean d(String str) {
        if (a((Object) str)) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static boolean e(String str) {
        if (a((Object) str)) {
            return false;
        }
        try {
            Double.parseDouble(str);
            if ((str.length() <= 0 || !".".equals(str.substring(str.length() - 1, str.length()))) && str.contains(".")) {
                return true;
            }
            return false;
        } catch (NumberFormatException unused) {
        }
    }

    public static boolean a(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }
}
