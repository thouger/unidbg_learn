package com.umeng.umzid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class a {

    /* renamed from: com.umeng.umzid.a$a  reason: collision with other inner class name */
    public static class C0191a implements HostnameVerifier {
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            if (!TextUtils.isEmpty(str)) {
                return "aaid.umeng.com".equalsIgnoreCase(str) || "pre-aaid.umeng.com".equalsIgnoreCase(str);
            }
            return false;
        }
    }

    public static SharedPreferences a(Context context) {
        if (context != null) {
            return context.getSharedPreferences("umzid_general_config", 0);
        }
        return null;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String a(java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.Class<com.umeng.umzid.a> r0 = com.umeng.umzid.a.class
            monitor-enter(r0)
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.net.URLConnection r4 = r2.openConnection()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            javax.net.ssl.HttpsURLConnection r4 = (javax.net.ssl.HttpsURLConnection) r4     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            com.umeng.umzid.a$a r2 = new com.umeng.umzid.a$a     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r2.<init>()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r4.setHostnameVerifier(r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.lang.String r2 = "TLS"
            javax.net.ssl.SSLContext r2 = javax.net.ssl.SSLContext.getInstance(r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.security.SecureRandom r3 = new java.security.SecureRandom     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r3.<init>()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r2.init(r1, r1, r3)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            javax.net.ssl.SSLSocketFactory r2 = r2.getSocketFactory()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r4.setSSLSocketFactory(r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.lang.String r2 = "Content-Type"
            java.lang.String r3 = "application/json"
            r4.setRequestProperty(r2, r3)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r2 = 30000(0x7530, float:4.2039E-41)
            r4.setConnectTimeout(r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r4.setReadTimeout(r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.lang.String r2 = "POST"
            r4.setRequestMethod(r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r2 = 1
            r4.setDoOutput(r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r4.setDoInput(r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.io.OutputStream r2 = r4.getOutputStream()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            byte[] r5 = r5.getBytes()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r2.write(r5)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r2.flush()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r2.close()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            int r5 = r4.getResponseCode()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r2 = 200(0xc8, float:2.8E-43)
            if (r5 != r2) goto L_0x0087
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r5.<init>(r4)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r4.<init>()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
        L_0x0072:
            int r2 = r5.read()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r3 = -1
            if (r2 == r3) goto L_0x007e
            char r2 = (char) r2     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r4.append(r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            goto L_0x0072
        L_0x007e:
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            monitor-exit(r0)
            return r4
        L_0x0084:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        L_0x0087:
            monitor-exit(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.umzid.a.a(java.lang.String, java.lang.String):java.lang.String");
    }
}
