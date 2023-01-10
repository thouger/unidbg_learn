package com.sdk.base.framework.f.i;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.a.a.c;
import com.sdk.base.framework.c.f;
import com.sdk.base.framework.f.a;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class b extends a {
    private static String a = "RSA/ECB/PKCS1Padding";

    static {
        AppMethodBeat.i(19719, false);
        b.class.getSimpleName();
        Boolean.valueOf(f.a);
        AppMethodBeat.o(19719);
    }

    public static String a(String str, String str2) {
        AppMethodBeat.i(19713, false);
        RSAPublicKey rSAPublicKey = (RSAPublicKey) a.a(str2);
        if (!c.a(str).booleanValue()) {
            String trim = new String(a(rSAPublicKey, com.sdk.base.framework.f.j.c.a(str)), Charset.defaultCharset()).trim();
            AppMethodBeat.o(19713);
            return trim;
        }
        Exception exc = new Exception("rsaAes key is null");
        AppMethodBeat.o(19713);
        throw exc;
    }

    private static byte[] a(RSAPublicKey rSAPublicKey, byte[] bArr) {
        AppMethodBeat.i(19717, false);
        if (rSAPublicKey != null) {
            try {
                Cipher instance = Cipher.getInstance(a);
                instance.init(2, rSAPublicKey);
                byte[] doFinal = instance.doFinal(bArr);
                AppMethodBeat.o(19717);
                return doFinal;
            } catch (NoSuchAlgorithmException unused) {
                NoSuchAlgorithmException noSuchAlgorithmException = new NoSuchAlgorithmException("\u65e0\u6b64\u89e3\u5bc6\u7b97\u6cd5");
                AppMethodBeat.o(19717);
                throw noSuchAlgorithmException;
            } catch (NoSuchPaddingException unused2) {
                NoSuchPaddingException noSuchPaddingException = new NoSuchPaddingException("\u89e3\u5bc6\u51fa\u9519\uff01\u4e0d\u652f\u6301\u8be5\u586b\u5145\u673a\u5236");
                AppMethodBeat.o(19717);
                throw noSuchPaddingException;
            } catch (InvalidKeyException unused3) {
                InvalidKeyException invalidKeyException = new InvalidKeyException("\u89e3\u5bc6\u516c\u94a5\u975e\u6cd5,\u8bf7\u68c0\u67e5");
                AppMethodBeat.o(19717);
                throw invalidKeyException;
            } catch (IllegalBlockSizeException unused4) {
                IllegalBlockSizeException illegalBlockSizeException = new IllegalBlockSizeException("\u5bc6\u6587\u957f\u5ea6\u975e\u6cd5");
                AppMethodBeat.o(19717);
                throw illegalBlockSizeException;
            } catch (BadPaddingException unused5) {
                BadPaddingException badPaddingException = new BadPaddingException("\u5bc6\u6587\u6570\u636e\u5df2\u635f\u574f");
                AppMethodBeat.o(19717);
                throw badPaddingException;
            }
        } else {
            Exception exc = new Exception("\u89e3\u5bc6\u516c\u94a5\u4e3a\u7a7a, \u8bf7\u8bbe\u7f6e");
            AppMethodBeat.o(19717);
            throw exc;
        }
    }

    public static String b(String str, String str2) {
        AppMethodBeat.i(19715, false);
        PublicKey a2 = a.a(str);
        Cipher instance = Cipher.getInstance(a);
        instance.init(1, a2);
        String str3 = com.sdk.base.framework.f.j.c.a(instance.doFinal(str2.getBytes(Charset.defaultCharset()))).toString();
        AppMethodBeat.o(19715);
        return str3;
    }
}
