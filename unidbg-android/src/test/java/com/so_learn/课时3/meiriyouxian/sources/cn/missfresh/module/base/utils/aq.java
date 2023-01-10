package cn.missfresh.module.base.utils;

import android.security.keystore.KeyProperties;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: SHA512 */
public class aq {
    public static String a(String str) {
        String str2;
        AppMethodBeat.i(23417, false);
        try {
            try {
                MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_SHA512);
                instance.update(str.getBytes());
                StringBuffer stringBuffer = new StringBuffer();
                byte[] digest = instance.digest();
                for (byte b : digest) {
                    int i = b;
                    if (b < 0) {
                        i = b + 256;
                    }
                    if (i < 16) {
                        stringBuffer.append("0");
                    }
                    stringBuffer.append(Integer.toHexString(i == 1 ? 1 : 0));
                }
                str2 = stringBuffer.toString();
            } catch (NoSuchAlgorithmException unused) {
                str2 = new String(str);
            }
        } catch (NoSuchAlgorithmException unused2) {
            str2 = null;
        }
        AppMethodBeat.o(23417);
        return str2;
    }
}
