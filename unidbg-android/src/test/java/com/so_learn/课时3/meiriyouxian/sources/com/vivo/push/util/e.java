package com.vivo.push.util;

import android.security.keystore.KeyProperties;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: CryptographicTool */
public final class e {
    public static String a(byte[] bArr) {
        AppMethodBeat.i(1376, false);
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) (bArr[i] ^ 16);
        }
        String str = new String(cArr);
        AppMethodBeat.o(1376);
        return str;
    }

    public static byte[] a(String str, String str2, byte[] bArr) throws Exception {
        AppMethodBeat.i(1380, false);
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(FileOpt.ENCODE_STR), KeyProperties.KEY_ALGORITHM_AES);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(str.getBytes(FileOpt.ENCODE_STR)));
        byte[] doFinal = instance.doFinal(bArr);
        AppMethodBeat.o(1380);
        return doFinal;
    }
}
