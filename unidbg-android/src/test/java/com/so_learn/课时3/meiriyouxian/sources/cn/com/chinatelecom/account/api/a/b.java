package cn.com.chinatelecom.account.api.a;

import android.security.keystore.KeyProperties;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class b {
    private static String a = "RSA/ECB/PKCS1Padding";

    public static String a(String str, RSAPublicKey rSAPublicKey) {
        AppMethodBeat.i(7849, false);
        try {
            String a2 = c.a(a(rSAPublicKey, str.getBytes()));
            AppMethodBeat.o(7849);
            return a2;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(7849);
            return "";
        }
    }

    public static PublicKey a(String str) {
        AppMethodBeat.i(7845, false);
        PublicKey generatePublic = KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_RSA).generatePublic(new X509EncodedKeySpec(a.a(str)));
        AppMethodBeat.o(7845);
        return generatePublic;
    }

    public static byte[] a(RSAPublicKey rSAPublicKey, byte[] bArr) {
        AppMethodBeat.i(7852, false);
        if (rSAPublicKey != null) {
            try {
                Cipher instance = Cipher.getInstance(a);
                instance.init(1, rSAPublicKey);
                byte[] doFinal = instance.doFinal(bArr);
                AppMethodBeat.o(7852);
                return doFinal;
            } catch (NoSuchAlgorithmException unused) {
                Exception exc = new Exception("\u65e0\u6b64\u52a0\u5bc6\u7b97\u6cd5");
                AppMethodBeat.o(7852);
                throw exc;
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
                AppMethodBeat.o(7852);
                return null;
            } catch (InvalidKeyException unused2) {
                Exception exc2 = new Exception("\u52a0\u5bc6\u516c\u94a5\u975e\u6cd5,\u8bf7\u68c0\u67e5");
                AppMethodBeat.o(7852);
                throw exc2;
            } catch (IllegalBlockSizeException unused3) {
                Exception exc3 = new Exception("\u660e\u6587\u957f\u5ea6\u975e\u6cd5");
                AppMethodBeat.o(7852);
                throw exc3;
            } catch (BadPaddingException unused4) {
                Exception exc4 = new Exception("\u660e\u6587\u6570\u636e\u5df2\u635f\u574f");
                AppMethodBeat.o(7852);
                throw exc4;
            }
        } else {
            Exception exc5 = new Exception("\u52a0\u5bc6\u516c\u94a5\u4e3a\u7a7a, \u8bf7\u8bbe\u7f6e");
            AppMethodBeat.o(7852);
            throw exc5;
        }
    }
}
