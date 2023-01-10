package com.sdk.base.framework.f.j;

import android.security.keystore.KeyProperties;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.c.f;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class a extends com.sdk.base.framework.f.a {
    private static final String a = a.class.getName();
    private static boolean b = f.a;
    private static String c = "a6Hy5Hb8IfX46D1f";

    static {
        AppMethodBeat.i(19664, false);
        AppMethodBeat.o(19664);
    }

    public static String a(int i) {
        AppMethodBeat.i(19655, false);
        Random random = new Random();
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            String str2 = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(str2)) {
                int i3 = random.nextInt(2) % 2 == 0 ? 65 : 97;
                str = str + ((char) (random.nextInt(26) + i3));
            } else if ("num".equalsIgnoreCase(str2)) {
                str = str + String.valueOf(random.nextInt(10));
            }
        }
        AppMethodBeat.o(19655);
        return str;
    }

    public static String a(String str, String str2) {
        AppMethodBeat.i(19652, false);
        String b2 = b(str, str2, c);
        AppMethodBeat.o(19652);
        return b2;
    }

    public static String a(String str, String str2, String str3) {
        AppMethodBeat.i(19657, false);
        if (str != null) {
            try {
                if (str.length() != 0) {
                    if (str.trim().length() != 0) {
                        if (str2 == null) {
                            a(a, "EncryptCbcIv", "encrypt key is null", b);
                            AppMethodBeat.o(19657);
                            return null;
                        } else if (str2.length() != 16) {
                            a(a, "EncryptCbcIv", "encrypt key length error", b);
                            AppMethodBeat.o(19657);
                            return null;
                        } else if (str3.length() != 16) {
                            a(a, "EncryptCbcIv", "ivStr length error", b);
                            AppMethodBeat.o(19657);
                            return null;
                        } else {
                            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                            instance.init(1, new SecretKeySpec(str2.getBytes(FileOpt.ENCODE_STR), KeyProperties.KEY_ALGORITHM_AES), new IvParameterSpec(str3.getBytes(FileOpt.ENCODE_STR)));
                            String a2 = c.a(instance.doFinal(str.getBytes(FileOpt.ENCODE_STR)));
                            AppMethodBeat.o(19657);
                            return a2;
                        }
                    }
                }
            } catch (Exception e) {
                a(a, "EncryptCbcIv", e.getMessage(), b);
                AppMethodBeat.o(19657);
                return null;
            }
        }
        a(a, "EncryptCbcIv", "encrypt content is null", b);
        AppMethodBeat.o(19657);
        return null;
    }

    public static String b(String str, String str2, String str3) {
        AppMethodBeat.i(19661, false);
        if (str != null) {
            try {
                if (str.length() != 0) {
                    if (str.trim().length() != 0) {
                        if (str2 == null) {
                            Exception exc = new Exception("decrypt key is null");
                            AppMethodBeat.o(19661);
                            throw exc;
                        } else if (str2.length() != 16) {
                            Exception exc2 = new Exception("decrypt key length error");
                            AppMethodBeat.o(19661);
                            throw exc2;
                        } else if (str3.length() == 16) {
                            byte[] a2 = c.a(str);
                            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                            instance.init(2, new SecretKeySpec(str2.getBytes(FileOpt.ENCODE_STR), KeyProperties.KEY_ALGORITHM_AES), new IvParameterSpec(str3.getBytes(FileOpt.ENCODE_STR)));
                            String str4 = new String(instance.doFinal(a2), FileOpt.ENCODE_STR);
                            AppMethodBeat.o(19661);
                            return str4;
                        } else {
                            Exception exc3 = new Exception(" iv decrypt key length error");
                            AppMethodBeat.o(19661);
                            throw exc3;
                        }
                    }
                }
            } catch (Exception e) {
                Exception exc4 = new Exception("decrypt errot", e);
                AppMethodBeat.o(19661);
                throw exc4;
            }
        }
        AppMethodBeat.o(19661);
        return null;
    }
}
