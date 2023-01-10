package com.alipay.sdk.c;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class b {
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.zip.GZIPOutputStream */
    /* JADX DEBUG: Multi-variable search result rejected for r2v5, resolved type: java.util.zip.GZIPOutputStream */
    /* JADX DEBUG: Multi-variable search result rejected for r2v7, resolved type: java.util.zip.GZIPOutputStream */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0045 A[SYNTHETIC, Splitter:B:33:0x0045] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x004a A[SYNTHETIC, Splitter:B:37:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x004f A[SYNTHETIC, Splitter:B:41:0x004f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(byte[] r6) throws java.io.IOException {
        /*
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x003e }
            r1.<init>(r6)     // Catch:{ all -> 0x003e }
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x003b }
            r6.<init>()     // Catch:{ all -> 0x003b }
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch:{ all -> 0x0036 }
            r2.<init>(r6)     // Catch:{ all -> 0x0036 }
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0034 }
        L_0x0014:
            int r3 = r1.read(r0)     // Catch:{ all -> 0x0034 }
            r4 = -1
            if (r3 == r4) goto L_0x0020
            r4 = 0
            r2.write(r0, r4, r3)     // Catch:{ all -> 0x0034 }
            goto L_0x0014
        L_0x0020:
            r2.flush()     // Catch:{ all -> 0x0034 }
            r2.finish()     // Catch:{ all -> 0x0034 }
            byte[] r0 = r6.toByteArray()     // Catch:{ all -> 0x0034 }
            r1.close()     // Catch:{ Exception -> 0x002d }
        L_0x002d:
            r6.close()     // Catch:{ Exception -> 0x0030 }
        L_0x0030:
            r2.close()     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            return r0
        L_0x0034:
            r0 = move-exception
            goto L_0x0043
        L_0x0036:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L_0x0043
        L_0x003b:
            r6 = move-exception
            r2 = r0
            goto L_0x0041
        L_0x003e:
            r6 = move-exception
            r1 = r0
            r2 = r1
        L_0x0041:
            r0 = r6
            r6 = r2
        L_0x0043:
            if (r1 == 0) goto L_0x0048
            r1.close()     // Catch:{ Exception -> 0x0048 }
        L_0x0048:
            if (r6 == 0) goto L_0x004d
            r6.close()     // Catch:{ Exception -> 0x004d }
        L_0x004d:
            if (r2 == 0) goto L_0x0052
            r2.close()     // Catch:{ Exception -> 0x0052 }
        L_0x0052:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.c.b.a(byte[]):byte[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v6 */
    public static byte[] b(byte[] bArr) throws IOException {
        GZIPInputStream gZIPInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        Throwable th2;
        GZIPInputStream gZIPInputStream2;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    byte[] bArr2 = new byte[4096];
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int read = gZIPInputStream.read(bArr2, 0, bArr2.length);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream2.write(bArr2, 0, read);
                        } catch (Throwable th3) {
                            th = th3;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception unused) {
                            }
                            try {
                                gZIPInputStream.close();
                            } catch (Exception unused2) {
                            }
                            try {
                                byteArrayInputStream.close();
                            } catch (Exception unused3) {
                            }
                            throw th;
                        }
                    }
                    byteArrayOutputStream2.flush();
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception unused4) {
                    }
                    try {
                        gZIPInputStream.close();
                    } catch (Exception unused5) {
                    }
                    try {
                        byteArrayInputStream.close();
                    } catch (Exception unused6) {
                    }
                    return byteArray;
                } catch (Throwable th4) {
                    byteArrayOutputStream = 0;
                    th = th4;
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    throw th;
                }
            } catch (Throwable th5) {
                th2 = th5;
                gZIPInputStream2 = null;
                th = th2;
                gZIPInputStream = gZIPInputStream2;
                byteArrayOutputStream = gZIPInputStream2;
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream.close();
                throw th;
            }
        } catch (Throwable th6) {
            th2 = th6;
            byteArrayInputStream = null;
            gZIPInputStream2 = null;
            th = th2;
            gZIPInputStream = gZIPInputStream2;
            byteArrayOutputStream = gZIPInputStream2;
            byteArrayOutputStream.close();
            gZIPInputStream.close();
            byteArrayInputStream.close();
            throw th;
        }
    }
}
