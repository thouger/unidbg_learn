package com.vivo.push.util;

import android.util.Base64;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/* compiled from: RSAUtils */
public final class s {
    private static String a = "RSA";

    public static PublicKey a(String str) throws Exception {
        AppMethodBeat.i(1701, false);
        try {
            PublicKey generatePublic = KeyFactory.getInstance(a).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 2)));
            AppMethodBeat.o(1701);
            return generatePublic;
        } catch (NoSuchAlgorithmException unused) {
            Exception exc = new Exception("\u65e0\u6b64\u7b97\u6cd5");
            AppMethodBeat.o(1701);
            throw exc;
        } catch (InvalidKeySpecException unused2) {
            Exception exc2 = new Exception("\u516c\u94a5\u975e\u6cd5");
            AppMethodBeat.o(1701);
            throw exc2;
        } catch (NullPointerException unused3) {
            Exception exc3 = new Exception("\u516c\u94a5\u6570\u636e\u4e3a\u7a7a");
            AppMethodBeat.o(1701);
            throw exc3;
        }
    }

    public static boolean a(byte[] bArr, PublicKey publicKey, byte[] bArr2) throws Exception {
        AppMethodBeat.i(1703, false);
        Signature instance = Signature.getInstance("MD5withRSA");
        instance.initVerify(publicKey);
        instance.update(bArr);
        boolean verify = instance.verify(bArr2);
        AppMethodBeat.o(1703);
        return verify;
    }
}
