package com.sdk.base.framework.f.j;

import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.a.a.c;
import com.sdk.base.framework.c.f;
import com.sdk.base.framework.f.a;
import java.nio.charset.Charset;
import java.security.MessageDigest;

public class e extends a {
    private static final String a = e.class.getName();
    private static boolean b = f.a;

    static {
        AppMethodBeat.i(19644, false);
        AppMethodBeat.o(19644);
    }

    public static String a(String str) {
        AppMethodBeat.i(19642, false);
        String str2 = null;
        if (c.a(str).booleanValue()) {
            AppMethodBeat.o(19642);
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str.getBytes(Charset.defaultCharset()));
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b2 : digest) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() == 1) {
                    sb.append("0");
                }
                sb.append(hexString);
            }
            str2 = sb.toString();
        } catch (Exception e) {
            a(a, ABTest.KEY_ENCRYPT, e.getMessage(), b);
        }
        AppMethodBeat.o(19642);
        return str2;
    }
}
