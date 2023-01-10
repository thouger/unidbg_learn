package cn.missfresh.basiclib.tool;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* compiled from: RSAUtils */
public final class d {
    private static String a = "RSA";

    public static byte[] a(byte[] bArr, PublicKey publicKey) {
        byte[] bArr2;
        AppMethodBeat.i(4300, false);
        try {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, publicKey);
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = length - i;
                if (i3 > 0) {
                    if (i3 > 117) {
                        bArr2 = instance.doFinal(bArr, i, 117);
                    } else {
                        bArr2 = instance.doFinal(bArr, i, i3);
                    }
                    byteArrayOutputStream.write(bArr2, 0, bArr2.length);
                    i2++;
                    i = i2 * 117;
                } else {
                    byteArrayOutputStream.close();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    AppMethodBeat.o(4300);
                    return byteArray;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(4300);
            return null;
        }
    }

    public static PublicKey a(String str) throws Exception {
        AppMethodBeat.i(4314, false);
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance(a).generatePublic(new X509EncodedKeySpec(b.a(str)));
            AppMethodBeat.o(4314);
            return rSAPublicKey;
        } catch (NoSuchAlgorithmException unused) {
            Exception exc = new Exception("\u65e0\u6b64\u7b97\u6cd5");
            AppMethodBeat.o(4314);
            throw exc;
        } catch (InvalidKeySpecException unused2) {
            Exception exc2 = new Exception("\u516c\u94a5\u975e\u6cd5");
            AppMethodBeat.o(4314);
            throw exc2;
        } catch (NullPointerException unused3) {
            Exception exc3 = new Exception("\u516c\u94a5\u6570\u636e\u4e3a\u7a7a");
            AppMethodBeat.o(4314);
            throw exc3;
        }
    }
}
