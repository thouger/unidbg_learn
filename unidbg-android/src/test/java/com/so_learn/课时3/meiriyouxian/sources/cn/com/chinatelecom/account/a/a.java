package cn.com.chinatelecom.account.a;

public class a {
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r7, java.lang.String r8, java.lang.String r9) {
        /*
            java.lang.String r7 = "UTF-8"
            r0 = 0
            r1 = 8380(0x20bc, float:1.1743E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            java.lang.String r2 = ""
            r3 = 0
            java.net.URL r4 = new java.net.URL     // Catch:{ all -> 0x00b0 }
            r4.<init>(r8)     // Catch:{ all -> 0x00b0 }
            java.net.URLConnection r8 = r4.openConnection()     // Catch:{ all -> 0x00b0 }
            java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8     // Catch:{ all -> 0x00b0 }
            java.lang.String r4 = "accept"
        */
        //  java.lang.String r5 = "*/*"
        /*
            r8.setRequestProperty(r4, r5)     // Catch:{ all -> 0x00b0 }
            java.lang.String r4 = "POST"
            r8.setRequestMethod(r4)     // Catch:{ all -> 0x00b0 }
            r4 = 1
            r8.setDoOutput(r4)     // Catch:{ all -> 0x00b0 }
            r8.setDoInput(r4)     // Catch:{ all -> 0x00b0 }
            r4 = 5000(0x1388, float:7.006E-42)
            r8.setConnectTimeout(r4)     // Catch:{ all -> 0x00b0 }
            r8.setReadTimeout(r4)     // Catch:{ all -> 0x00b0 }
            r8.setUseCaches(r0)     // Catch:{ all -> 0x00b0 }
            java.lang.String r0 = "Accept-Charset"
            r8.addRequestProperty(r0, r7)     // Catch:{ all -> 0x00b0 }
            boolean r0 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x00b0 }
            if (r0 != 0) goto L_0x0061
            java.io.DataOutputStream r0 = new java.io.DataOutputStream     // Catch:{ all -> 0x00b0 }
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00b0 }
            java.io.OutputStream r5 = r8.getOutputStream()     // Catch:{ all -> 0x00b0 }
            r4.<init>(r5)     // Catch:{ all -> 0x00b0 }
            r0.<init>(r4)     // Catch:{ all -> 0x00b0 }
            byte[] r7 = r9.getBytes(r7)     // Catch:{ all -> 0x00b0 }
            r0.write(r7)     // Catch:{ all -> 0x00b0 }
            r0.flush()     // Catch:{ all -> 0x00b0 }
            r0.close()     // Catch:{ all -> 0x00b0 }
            goto L_0x0064
        L_0x0061:
            r8.connect()     // Catch:{ all -> 0x00b0 }
        L_0x0064:
            int r7 = r8.getResponseCode()     // Catch:{ all -> 0x00b0 }
            r9 = 200(0xc8, float:2.8E-43)
            if (r7 != r9) goto L_0x009c
            java.io.InputStream r7 = r8.getInputStream()     // Catch:{ all -> 0x00b0 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0097 }
            r8.<init>()     // Catch:{ all -> 0x0097 }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ all -> 0x0097 }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ all -> 0x0097 }
            r0.<init>(r7)     // Catch:{ all -> 0x0097 }
            r9.<init>(r0)     // Catch:{ all -> 0x0097 }
        L_0x007f:
            java.lang.String r0 = r9.readLine()     // Catch:{ all -> 0x0094 }
            if (r0 == 0) goto L_0x008f
            r8.append(r0)     // Catch:{ all -> 0x0094 }
            java.lang.String r0 = "\n"
            r8.append(r0)     // Catch:{ all -> 0x0094 }
            goto L_0x007f
        L_0x008f:
            java.lang.String r2 = r8.toString()     // Catch:{ all -> 0x0094 }
            goto L_0x009e
        L_0x0094:
            r8 = move-exception
            r3 = r9
            goto L_0x0098
        L_0x0097:
            r8 = move-exception
        L_0x0098:
            r6 = r8
            r8 = r7
            r7 = r6
            goto L_0x00b2
        L_0x009c:
            r7 = r3
            r9 = r7
        L_0x009e:
            if (r9 == 0) goto L_0x00a6
            r9.close()     // Catch:{ Exception -> 0x00a4 }
            goto L_0x00a6
        L_0x00a4:
            r7 = move-exception
            goto L_0x00ac
        L_0x00a6:
            if (r7 == 0) goto L_0x00bf
            r7.close()     // Catch:{ Exception -> 0x00a4 }
            goto L_0x00bf
        L_0x00ac:
            r7.printStackTrace()
            goto L_0x00bf
        L_0x00b0:
            r7 = move-exception
            r8 = r3
        L_0x00b2:
            r7.printStackTrace()     // Catch:{ all -> 0x00c3 }
            if (r3 == 0) goto L_0x00ba
            r3.close()
        L_0x00ba:
            if (r8 == 0) goto L_0x00bf
            r8.close()
        L_0x00bf:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r2
        L_0x00c3:
            r7 = move-exception
            if (r3 == 0) goto L_0x00cc
            r3.close()     // Catch:{ Exception -> 0x00ca }
            goto L_0x00cc
        L_0x00ca:
            r8 = move-exception
            goto L_0x00d2
        L_0x00cc:
            if (r8 == 0) goto L_0x00d5
            r8.close()     // Catch:{ Exception -> 0x00ca }
            goto L_0x00d5
        L_0x00d2:
            r8.printStackTrace()
        L_0x00d5:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.a.a.a(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }
}
