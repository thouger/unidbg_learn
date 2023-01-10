package com.alipay.b.a.a.a;

public final class b {
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003d, code lost:
        if (r4 != null) goto L_0x002e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0038 A[SYNTHETIC, Splitter:B:16:0x0038] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            r2.<init>(r4, r5)     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            boolean r4 = r2.exists()     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            if (r4 != 0) goto L_0x0012
            return r1
        L_0x0012:
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            java.lang.String r2 = "UTF-8"
            r5.<init>(r3, r2)     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x003c, all -> 0x0035 }
        L_0x0024:
            java.lang.String r5 = r4.readLine()     // Catch:{ IOException -> 0x003d, all -> 0x0032 }
            if (r5 == 0) goto L_0x002e
            r0.append(r5)     // Catch:{ IOException -> 0x003d, all -> 0x0032 }
            goto L_0x0024
        L_0x002e:
            r4.close()     // Catch:{ all -> 0x0040 }
            goto L_0x0040
        L_0x0032:
            r5 = move-exception
            r1 = r4
            goto L_0x0036
        L_0x0035:
            r5 = move-exception
        L_0x0036:
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ all -> 0x003b }
        L_0x003b:
            throw r5
        L_0x003c:
            r4 = r1
        L_0x003d:
            if (r4 == 0) goto L_0x0040
            goto L_0x002e
        L_0x0040:
            java.lang.String r4 = r0.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.a.b.a(java.lang.String, java.lang.String):java.lang.String");
    }
}
