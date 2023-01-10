package cn.missfresh.risk.f;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* compiled from: RSAUtils */
public class g {
    private static String a = "RSA";

    public static byte[] a(byte[] bArr, PublicKey publicKey) {
        byte[] bArr2;
        AppMethodBeat.i(2942, false);
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
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    AppMethodBeat.o(2942);
                    return byteArray;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(2942);
            return null;
        }
    }

    public static PublicKey a(String str) throws Exception {
        AppMethodBeat.i(2964, false);
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance(a).generatePublic(new X509EncodedKeySpec(a.a(str)));
            AppMethodBeat.o(2964);
            return rSAPublicKey;
        } catch (NoSuchAlgorithmException unused) {
            Exception exc = new Exception("\u65e0\u6b64\u7b97\u6cd5");
            AppMethodBeat.o(2964);
            throw exc;
        } catch (InvalidKeySpecException unused2) {
            Exception exc2 = new Exception("\u516c\u94a5\u975e\u6cd5");
            AppMethodBeat.o(2964);
            throw exc2;
        } catch (NullPointerException unused3) {
            Exception exc3 = new Exception("\u516c\u94a5\u6570\u636e\u4e3a\u7a7a");
            AppMethodBeat.o(2964);
            throw exc3;
        }
    }

    public static PublicKey a(InputStream inputStream) throws Exception {
        AppMethodBeat.i(2972, false);
        try {
            PublicKey a2 = a(b(inputStream));
            AppMethodBeat.o(2972);
            return a2;
        } catch (IOException unused) {
            Exception exc = new Exception("\u516c\u94a5\u6570\u636e\u6d41\u8bfb\u53d6\u9519\u8bef");
            AppMethodBeat.o(2972);
            throw exc;
        } catch (NullPointerException unused2) {
            Exception exc2 = new Exception("\u516c\u94a5\u8f93\u5165\u6d41\u4e3a\u7a7a");
            AppMethodBeat.o(2972);
            throw exc2;
        }
    }

    private static String b(InputStream inputStream) throws IOException {
        AppMethodBeat.i(2982, false);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                String sb2 = sb.toString();
                AppMethodBeat.o(2982);
                return sb2;
            } else if (readLine.charAt(0) != '-') {
                sb.append(readLine);
                sb.append('\r');
            }
        }
    }
}
