package cn.missfresh.module.base.utils;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.huawei.hms.framework.common.ContainerUtils;
import java.util.Map;

/* compiled from: UrlUtils */
public class ax {
    public static String a(String str, Map<String, String> map) {
        AppMethodBeat.i(23574, false);
        if (TextUtils.isEmpty(str) || map == null || map.isEmpty()) {
            AppMethodBeat.o(23574);
            return str;
        }
        String b = b(str, map);
        AppMethodBeat.o(23574);
        return b;
    }

    public static String b(String str, Map<String, String> map) {
        AppMethodBeat.i(23575, false);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String str2 = "?";
        if (str.contains(str2)) {
            str2 = "&";
        }
        sb.append(str2);
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append(entry.getValue());
                sb.append("&");
            }
        }
        String substring = sb.substring(0, sb.length() - 1);
        AppMethodBeat.o(23575);
        return substring;
    }

    public static boolean a(String str) {
        boolean z = false;
        AppMethodBeat.i(23576, false);
        if (b.a(str)) {
            AppMethodBeat.o(23576);
            return false;
        }
        String upperCase = str.toUpperCase();
        if (upperCase.contains(".JPG") || upperCase.contains(".PNG") || upperCase.contains(".JPEG") || upperCase.contains(".WEBP") || upperCase.contains(".GIF") || upperCase.contains(".BMP")) {
            z = true;
        }
        AppMethodBeat.o(23576);
        return z;
    }

    public static boolean b(String str) {
        boolean z = false;
        AppMethodBeat.i(23577, false);
        if (b.a(str)) {
            AppMethodBeat.o(23577);
            return false;
        }
        String upperCase = str.toUpperCase();
        if (upperCase.contains(".MP4") || upperCase.contains(".AVI") || upperCase.contains(".MOV") || upperCase.contains(".3GP") || upperCase.contains(".3GPP")) {
            z = true;
        }
        AppMethodBeat.o(23577);
        return z;
    }
}
