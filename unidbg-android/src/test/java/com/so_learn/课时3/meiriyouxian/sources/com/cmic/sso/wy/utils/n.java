package com.cmic.sso.wy.utils;

import android.os.Build;
import android.security.keystore.KeyProperties;
import com.cmic.sso.wy.utils.a.a;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* compiled from: RSAUtil */
public class n {
    private static final String a = n.class.getSimpleName();
    private static n c = null;
    private PublicKey b = null;

    public static n a() {
        if (c == null) {
            c = new n();
        }
        return c;
    }

    private n() {
        try {
            if (this.b == null) {
                b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String a(String str) {
        if (this.b == null) {
            g.a(a, "mServerPublicKey == null");
            return null;
        }
        try {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, this.b);
            return s.a(instance.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void b() throws Exception {
        KeyFactory keyFactory;
        try {
            byte[] a2 = new a().a("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/YHP9utFGOhGk7Xf5L7jOgQz5\nv2JKxdrIE3yzYsHoZJwzKC7Ttx380UZmBFzr5I1k6FFMn/YGXd4ts6UHT/nzsCIc\ngZlTTem7Pjdm1V9bJgQ6iQvFHsvT+vNgJ3wAIRd+iCMXm8y96yZhD2+SH5odBYS2\nZzwTYXBQDvB/rTfdjwIDAQAB");
            if (Build.VERSION.SDK_INT >= 28) {
                keyFactory = KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_RSA);
            } else {
                keyFactory = KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "BC");
            }
            this.b = keyFactory.generatePublic(new X509EncodedKeySpec(a2));
        } catch (IOException unused) {
            throw new Exception("\u516c\u94a5\u6570\u636e\u6d41\u8bfb\u53d6\u9519\u8bef");
        } catch (NullPointerException unused2) {
            throw new Exception("\u516c\u94a5\u8f93\u5165\u6d41\u4e3a\u7a7a");
        }
    }
}
