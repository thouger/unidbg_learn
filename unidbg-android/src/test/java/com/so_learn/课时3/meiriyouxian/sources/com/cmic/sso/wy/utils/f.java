package com.cmic.sso.wy.utils;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.util.Base64;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.security.auth.x500.X500Principal;

/* compiled from: KeystoreUtil */
public class f {
    public static synchronized boolean a(Context context) {
        synchronized (f.class) {
            try {
                KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
                instance.load(null);
                if (instance.getCertificate("CMCC_SDK") != null) {
                    return true;
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    try {
                        KeyPairGenerator instance2 = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore");
                        instance2.initialize(new KeyGenParameterSpec.Builder("CMCC_SDK", 3).setDigests("SHA-256", KeyProperties.DIGEST_SHA512).setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1).build());
                        Thread.sleep(1000);
                        instance2.generateKeyPair();
                        return true;
                    } catch (Exception e) {
                        g.a("KeystoreUtil", e.getMessage());
                        return false;
                    }
                } else {
                    Calendar instance3 = Calendar.getInstance();
                    Calendar instance4 = Calendar.getInstance();
                    instance4.add(1, 30);
                    try {
                        if (Build.VERSION.SDK_INT < 18) {
                            return false;
                        }
                        KeyPairGeneratorSpec build = new KeyPairGeneratorSpec.Builder(context).setAlias("CMCC_SDK").setSubject(new X500Principal("CN=CMCC_SDK")).setSerialNumber(BigInteger.TEN).setStartDate(instance3.getTime()).setEndDate(instance4.getTime()).build();
                        KeyPairGenerator instance5 = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore");
                        instance5.initialize(build);
                        Thread.sleep(1000);
                        instance5.generateKeyPair();
                        return true;
                    } catch (Exception e2) {
                        g.a("KeystoreUtil", e2.getMessage());
                        return false;
                    }
                }
            } catch (Exception e3) {
                g.a("KeystoreUtil", e3.getMessage());
                return false;
            }
        }
    }

    private static byte[] a() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    static String a(Context context, String str) {
        byte[] b = b(context);
        if (b != null) {
            return a.a(b, str);
        }
        return null;
    }

    private static byte[] b(Context context) {
        try {
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            instance.load(null);
            if (!TextUtils.isEmpty(b())) {
                String b = b();
                if (TextUtils.isEmpty(b)) {
                    return null;
                }
                byte[] decode = Base64.decode(b, 0);
                PrivateKey privateKey = (PrivateKey) instance.getKey("CMCC_SDK", null);
                if (privateKey == null) {
                    return null;
                }
                Cipher instance2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                instance2.init(2, privateKey);
                return instance2.doFinal(decode);
            } else if (!a(context)) {
                return null;
            } else {
                byte[] a = a();
                PublicKey publicKey = instance.getCertificate("CMCC_SDK").getPublicKey();
                Cipher instance3 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                instance3.init(1, publicKey);
                a(Base64.encodeToString(instance3.doFinal(a), 0));
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static String b(Context context, String str) {
        byte[] b;
        if (TextUtils.isEmpty(str) || (b = b(context)) == null) {
            return null;
        }
        return a.b(b, str);
    }

    private static void a(String str) {
        q.a("AES_KEY", str);
    }

    private static String b() {
        return q.b("AES_KEY", "");
    }
}
