package com.alipay.sdk.c;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class d {
    private static PublicKey b(String str, String str2) throws NoSuchAlgorithmException, Exception {
        return KeyFactory.getInstance(str).generatePublic(new X509EncodedKeySpec(a.a(str2)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0058 A[SYNTHETIC, Splitter:B:29:0x0058] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.lang.String r5, java.lang.String r6) {
        /*
            r0 = 0
            java.lang.String r1 = "RSA"
            java.security.PublicKey r6 = b(r1, r6)     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            java.lang.String r1 = "RSA/ECB/PKCS1Padding"
            javax.crypto.Cipher r1 = javax.crypto.Cipher.getInstance(r1)     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            r2 = 1
            r1.init(r2, r6)     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            java.lang.String r6 = "UTF-8"
            byte[] r5 = r5.getBytes(r6)     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            int r6 = r1.getBlockSize()     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            r2.<init>()     // Catch:{ Exception -> 0x0049, all -> 0x0047 }
            r3 = 0
        L_0x0024:
            int r4 = r5.length     // Catch:{ Exception -> 0x0045 }
            if (r3 >= r4) goto L_0x0038
            int r4 = r5.length     // Catch:{ Exception -> 0x0045 }
            int r4 = r4 - r3
            if (r4 >= r6) goto L_0x002e
            int r4 = r5.length     // Catch:{ Exception -> 0x0045 }
            int r4 = r4 - r3
            goto L_0x002f
        L_0x002e:
            r4 = r6
        L_0x002f:
            byte[] r4 = r1.doFinal(r5, r3, r4)     // Catch:{ Exception -> 0x0045 }
            r2.write(r4)     // Catch:{ Exception -> 0x0045 }
            int r3 = r3 + r6
            goto L_0x0024
        L_0x0038:
            byte[] r0 = r2.toByteArray()     // Catch:{ Exception -> 0x0045 }
            r2.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0053
        L_0x0040:
            r5 = move-exception
            com.alipay.sdk.util.e.a(r5)
            goto L_0x0053
        L_0x0045:
            r5 = move-exception
            goto L_0x004b
        L_0x0047:
            r5 = move-exception
            goto L_0x0056
        L_0x0049:
            r5 = move-exception
            r2 = r0
        L_0x004b:
            com.alipay.sdk.util.e.a(r5)     // Catch:{ all -> 0x0054 }
            if (r2 == 0) goto L_0x0053
            r2.close()
        L_0x0053:
            return r0
        L_0x0054:
            r5 = move-exception
            r0 = r2
        L_0x0056:
            if (r0 == 0) goto L_0x0060
            r0.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r6 = move-exception
            com.alipay.sdk.util.e.a(r6)
        L_0x0060:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.c.d.a(java.lang.String, java.lang.String):byte[]");
    }
}
