package cn.missfresh.sherlock.tool;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* compiled from: RSAUtils */
public final class i {
    private static String a = "RSA";

    public static byte[] a(byte[] bArr, PublicKey publicKey) {
        byte[] bArr2;
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
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PublicKey a(String str) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance(a).generatePublic(new X509EncodedKeySpec(b.a(str)));
        } catch (NoSuchAlgorithmException unused) {
            throw new Exception("\u65e0\u6b64\u7b97\u6cd5");
        } catch (InvalidKeySpecException unused2) {
            throw new Exception("\u516c\u94a5\u975e\u6cd5");
        } catch (NullPointerException unused3) {
            throw new Exception("\u516c\u94a5\u6570\u636e\u4e3a\u7a7a");
        }
    }
}
