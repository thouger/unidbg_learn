package cn.missfresh.sherlock.tool;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;
import okhttp3.Headers;

/* compiled from: GzipUtils */
public class f {
    public static boolean a(Headers headers) {
        for (String str : headers.names()) {
            if ("Content-Encoding".equalsIgnoreCase(str) && headers.get(str).contains("gzip")) {
                return true;
            }
        }
        return false;
    }

    public static byte[] a(String str, String str2) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes(str2));
            gZIPOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] a(String str) {
        return a(str, "UTF-8");
    }

    public static String a(byte[] bArr) {
        return a(bArr, "UTF-8");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0053 A[SYNTHETIC, Splitter:B:39:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x005f A[SYNTHETIC, Splitter:B:50:0x005f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(byte[] r6, java.lang.String r7) {
        /*
            r0 = 0
            if (r6 == 0) goto L_0x0063
            int r1 = r6.length
            if (r1 != 0) goto L_0x0007
            goto L_0x0063
        L_0x0007:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r6)
            java.util.zip.GZIPInputStream r6 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x0042, all -> 0x003e }
            r6.<init>(r2)     // Catch:{ IOException -> 0x0042, all -> 0x003e }
            r3 = 256(0x100, float:3.59E-43)
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x0043 }
        L_0x001a:
            int r4 = r6.read(r3)     // Catch:{ IOException -> 0x0043 }
            if (r4 < 0) goto L_0x0025
            r5 = 0
            r1.write(r3, r5, r4)     // Catch:{ IOException -> 0x0043 }
            goto L_0x001a
        L_0x0025:
            r6.close()     // Catch:{ IOException -> 0x0043 }
            r2.close()     // Catch:{ IOException -> 0x0043 }
            r1.close()     // Catch:{ IOException -> 0x0043 }
            java.lang.String r7 = r1.toString(r7)     // Catch:{ IOException -> 0x0043 }
            r1.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0035:
            r2.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            r6.close()     // Catch:{ IOException -> 0x003b }
        L_0x003b:
            return r7
        L_0x003c:
            r7 = move-exception
            goto L_0x0057
        L_0x003e:
            r6 = move-exception
            r7 = r6
            r6 = r0
            goto L_0x0057
        L_0x0042:
            r6 = r0
        L_0x0043:
            java.io.PrintStream r7 = java.lang.System.out     // Catch:{ all -> 0x003c }
            java.lang.String r3 = "gzip uncompress to string error"
            r7.println(r3)     // Catch:{ all -> 0x003c }
            r1.close()     // Catch:{ IOException -> 0x004e }
        L_0x004e:
            r2.close()     // Catch:{ IOException -> 0x0051 }
        L_0x0051:
            if (r6 == 0) goto L_0x0056
            r6.close()     // Catch:{ IOException -> 0x0056 }
        L_0x0056:
            return r0
        L_0x0057:
            r1.close()     // Catch:{ IOException -> 0x005a }
        L_0x005a:
            r2.close()     // Catch:{ IOException -> 0x005d }
        L_0x005d:
            if (r6 == 0) goto L_0x0062
            r6.close()     // Catch:{ IOException -> 0x0062 }
        L_0x0062:
            throw r7
        L_0x0063:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.sherlock.tool.f.a(byte[], java.lang.String):java.lang.String");
    }
}
