package com.alipay.sdk.f;

import com.alipay.sdk.a.a;
import com.alipay.sdk.c.b;
import com.alipay.sdk.c.d;
import com.alipay.sdk.c.e;
import com.alipay.sdk.util.l;
import java.util.Locale;

public final class c {
    private boolean a;
    private String b = l.a(24);

    public c(boolean z) {
        this.a = z;
    }

    public d a(b bVar, boolean z, String str) {
        byte[] bArr;
        if (bVar == null) {
            return null;
        }
        byte[] bytes = bVar.a().getBytes();
        byte[] bytes2 = bVar.b().getBytes();
        if (z) {
            try {
                bytes2 = b.a(bytes2);
            } catch (Exception unused) {
                z = false;
            }
        }
        if (this.a) {
            bArr = a(bytes, a(this.b, a.b), a(this.b, bytes2, str));
        } else {
            bArr = a(bytes, bytes2);
        }
        return new d(z, bArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0067 A[SYNTHETIC, Splitter:B:29:0x0067] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x006d A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0079 A[SYNTHETIC, Splitter:B:41:0x0079] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alipay.sdk.f.b a(com.alipay.sdk.f.d r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x005f, all -> 0x005c }
            byte[] r2 = r6.b()     // Catch:{ Exception -> 0x005f, all -> 0x005c }
            r1.<init>(r2)     // Catch:{ Exception -> 0x005f, all -> 0x005c }
            r2 = 5
            byte[] r3 = new byte[r2]     // Catch:{ Exception -> 0x0059 }
            r1.read(r3)     // Catch:{ Exception -> 0x0059 }
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x0059 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0059 }
            int r3 = a(r4)     // Catch:{ Exception -> 0x0059 }
            byte[] r3 = new byte[r3]     // Catch:{ Exception -> 0x0059 }
            r1.read(r3)     // Catch:{ Exception -> 0x0059 }
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x0059 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0059 }
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x0057 }
            r1.read(r2)     // Catch:{ Exception -> 0x0057 }
            java.lang.String r3 = new java.lang.String     // Catch:{ Exception -> 0x0057 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0057 }
            int r2 = a(r3)     // Catch:{ Exception -> 0x0057 }
            if (r2 <= 0) goto L_0x0052
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x0057 }
            r1.read(r2)     // Catch:{ Exception -> 0x0057 }
            boolean r3 = r5.a     // Catch:{ Exception -> 0x0057 }
            if (r3 == 0) goto L_0x0042
            java.lang.String r3 = r5.b     // Catch:{ Exception -> 0x0057 }
            byte[] r2 = b(r3, r2, r7)     // Catch:{ Exception -> 0x0057 }
        L_0x0042:
            boolean r6 = r6.a()     // Catch:{ Exception -> 0x0057 }
            if (r6 == 0) goto L_0x004c
            byte[] r2 = com.alipay.sdk.c.b.b(r2)     // Catch:{ Exception -> 0x0057 }
        L_0x004c:
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x0057 }
            r6.<init>(r2)     // Catch:{ Exception -> 0x0057 }
            goto L_0x0053
        L_0x0052:
            r6 = r0
        L_0x0053:
            r1.close()     // Catch:{ Exception -> 0x006b }
            goto L_0x006b
        L_0x0057:
            r6 = move-exception
            goto L_0x0062
        L_0x0059:
            r6 = move-exception
            r4 = r0
            goto L_0x0062
        L_0x005c:
            r6 = move-exception
            r1 = r0
            goto L_0x0077
        L_0x005f:
            r6 = move-exception
            r1 = r0
            r4 = r1
        L_0x0062:
            com.alipay.sdk.util.e.a(r6)     // Catch:{ all -> 0x0076 }
            if (r1 == 0) goto L_0x006a
            r1.close()     // Catch:{ Exception -> 0x006a }
        L_0x006a:
            r6 = r0
        L_0x006b:
            if (r4 != 0) goto L_0x0070
            if (r6 != 0) goto L_0x0070
            return r0
        L_0x0070:
            com.alipay.sdk.f.b r7 = new com.alipay.sdk.f.b
            r7.<init>(r4, r6)
            return r7
        L_0x0076:
            r6 = move-exception
        L_0x0077:
            if (r1 == 0) goto L_0x007c
            r1.close()     // Catch:{ Exception -> 0x007c }
        L_0x007c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.f.c.a(com.alipay.sdk.f.d, java.lang.String):com.alipay.sdk.f.b");
    }

    private static byte[] a(String str, String str2) {
        return d.a(str, str2);
    }

    private static byte[] a(String str, byte[] bArr, String str2) {
        return e.a(str, bArr, str2);
    }

    private static byte[] b(String str, byte[] bArr, String str2) {
        return e.b(str, bArr, str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004e, code lost:
        if (r2 == null) goto L_0x0051;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x004b A[SYNTHETIC, Splitter:B:30:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0055 A[SYNTHETIC, Splitter:B:38:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x005a A[SYNTHETIC, Splitter:B:42:0x005a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] a(byte[]... r7) {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x005e
            int r1 = r7.length
            if (r1 != 0) goto L_0x0007
            goto L_0x005e
        L_0x0007:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0043, all -> 0x003f }
            r1.<init>()     // Catch:{ Exception -> 0x0043, all -> 0x003f }
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x003c, all -> 0x0039 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x003c, all -> 0x0039 }
            int r3 = r7.length     // Catch:{ Exception -> 0x0037 }
            r4 = 0
        L_0x0013:
            if (r4 >= r3) goto L_0x0029
            r5 = r7[r4]     // Catch:{ Exception -> 0x0037 }
            int r6 = r5.length     // Catch:{ Exception -> 0x0037 }
            java.lang.String r6 = a(r6)     // Catch:{ Exception -> 0x0037 }
            byte[] r6 = r6.getBytes()     // Catch:{ Exception -> 0x0037 }
            r2.write(r6)     // Catch:{ Exception -> 0x0037 }
            r2.write(r5)     // Catch:{ Exception -> 0x0037 }
            int r4 = r4 + 1
            goto L_0x0013
        L_0x0029:
            r2.flush()     // Catch:{ Exception -> 0x0037 }
            byte[] r0 = r1.toByteArray()     // Catch:{ Exception -> 0x0037 }
            r1.close()     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            r2.close()     // Catch:{ Exception -> 0x0051 }
            goto L_0x0051
        L_0x0037:
            r7 = move-exception
            goto L_0x0046
        L_0x0039:
            r7 = move-exception
            r2 = r0
            goto L_0x0053
        L_0x003c:
            r7 = move-exception
            r2 = r0
            goto L_0x0046
        L_0x003f:
            r7 = move-exception
            r1 = r0
            r2 = r1
            goto L_0x0053
        L_0x0043:
            r7 = move-exception
            r1 = r0
            r2 = r1
        L_0x0046:
            com.alipay.sdk.util.e.a(r7)     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x004e
            r1.close()     // Catch:{ Exception -> 0x004e }
        L_0x004e:
            if (r2 == 0) goto L_0x0051
            goto L_0x0033
        L_0x0051:
            return r0
        L_0x0052:
            r7 = move-exception
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.close()     // Catch:{ Exception -> 0x0058 }
        L_0x0058:
            if (r2 == 0) goto L_0x005d
            r2.close()     // Catch:{ Exception -> 0x005d }
        L_0x005d:
            throw r7
        L_0x005e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.f.c.a(byte[][]):byte[]");
    }

    private static String a(int i) {
        return String.format(Locale.getDefault(), "%05d", Integer.valueOf(i));
    }

    private static int a(String str) {
        return Integer.parseInt(str);
    }
}
