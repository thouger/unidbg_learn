package com.sdk.base.framework.f.k;

import android.content.ContentResolver;
import android.telecom.Logging.Session;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.a.a.c;
import com.sdk.base.framework.c.f;
import com.sdk.base.framework.f.j.e;
import java.util.Map;
import java.util.TreeMap;

public class a extends com.sdk.base.framework.f.a {
    private static final String a = a.class.getName();
    private static boolean b = f.a;

    static {
        AppMethodBeat.i(20168, false);
        AppMethodBeat.o(20168);
    }

    /* JADX DEBUG: TODO: convert one arg to string using `String.valueOf()`, args: [(wrap: java.lang.Object : 0x0037: INVOKE  (r4v0 java.lang.Object) = (r6v4 java.util.Map$Entry<java.lang.String, java.lang.Object>) type: INTERFACE call: java.util.Map.Entry.getValue():java.lang.Object)] */
    public static String a(String str, String str2, TreeMap<String, Object> treeMap) {
        String str3;
        AppMethodBeat.i(20166, false);
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(str2);
            stringBuffer.append('?');
            for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
                String key = entry.getKey();
                StringBuilder sb = new StringBuilder();
                sb.append(entry.getValue());
                String sb2 = sb.toString();
                if (entry.getValue() != null && sb2.length() > 0 && !"null".equals(sb2) && !"sign".equals(key) && !key.startsWith(Session.SESSION_SEPARATION_CHAR_CHILD) && !ContentResolver.SCHEME_FILE.equals(key)) {
                    stringBuffer.append(key);
                    stringBuffer.append('=');
                    stringBuffer.append(entry.getValue());
                    stringBuffer.append('&');
                }
            }
            if (stringBuffer.charAt(stringBuffer.length() - 1) == '&') {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            str3 = e.a(stringBuffer.toString());
        } catch (Exception e) {
            c.b(a, e.getMessage(), Boolean.valueOf(b));
            str3 = null;
        }
        AppMethodBeat.o(20166);
        return str3;
    }
}
