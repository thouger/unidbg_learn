package cn.missfresh.basiclib.tool;

import android.security.keystore.KeyProperties;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: AESUtils */
public final class a {
    public static String a(String str, String str2) throws Exception {
        AppMethodBeat.i(4221, false);
        String a = b.a(a(str.getBytes("UTF-8"), str2.getBytes("UTF-8")));
        AppMethodBeat.o(4221);
        return a;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        AppMethodBeat.i(4223, false);
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
        instance.init(1, new SecretKeySpec(bArr2, KeyProperties.KEY_ALGORITHM_AES));
        byte[] doFinal = instance.doFinal(bArr);
        AppMethodBeat.o(4223);
        return doFinal;
    }
}
