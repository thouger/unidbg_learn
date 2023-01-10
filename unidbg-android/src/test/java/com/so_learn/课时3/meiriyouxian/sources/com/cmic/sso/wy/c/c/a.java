package com.cmic.sso.wy.c.c;

import android.os.Bundle;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* compiled from: TrustManager */
public class a {
    private static String d = "";
    private X509Certificate a;
    private SSLContext b;
    private Bundle c;

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004d A[SYNTHETIC, Splitter:B:23:0x004d] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b() {
        /*
            r6 = this;
            java.security.cert.X509Certificate r0 = r6.a
            if (r0 != 0) goto L_0x0056
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x002f, all -> 0x002a }
            java.lang.String r2 = com.cmic.sso.wy.c.c.a.d     // Catch:{ Exception -> 0x002f, all -> 0x002a }
            byte[] r2 = r2.getBytes()     // Catch:{ Exception -> 0x002f, all -> 0x002a }
            r3 = 0
            byte[] r2 = android.util.Base64.decode(r2, r3)     // Catch:{ Exception -> 0x002f, all -> 0x002a }
            r1.<init>(r2)     // Catch:{ Exception -> 0x002f, all -> 0x002a }
            java.lang.String r0 = "X.509"
            java.security.cert.CertificateFactory r0 = java.security.cert.CertificateFactory.getInstance(r0)     // Catch:{ Exception -> 0x0028 }
            java.security.cert.Certificate r0 = r0.generateCertificate(r1)     // Catch:{ Exception -> 0x0028 }
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0     // Catch:{ Exception -> 0x0028 }
            r6.a = r0     // Catch:{ Exception -> 0x0028 }
            r1.close()     // Catch:{ IOException -> 0x0045 }
            goto L_0x0056
        L_0x0028:
            r0 = move-exception
            goto L_0x0033
        L_0x002a:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x004b
        L_0x002f:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L_0x0033:
            android.os.Bundle r2 = r6.c     // Catch:{ all -> 0x004a }
            java.lang.String r3 = "isNeedToGetCert"
            r4 = 1
            r2.putBoolean(r3, r4)     // Catch:{ all -> 0x004a }
            r0.printStackTrace()     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x0056
            r1.close()
            goto L_0x0056
        L_0x0045:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0056
        L_0x004a:
            r0 = move-exception
        L_0x004b:
            if (r1 == 0) goto L_0x0055
            r1.close()     // Catch:{ IOException -> 0x0051 }
            goto L_0x0055
        L_0x0051:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0055:
            throw r0
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.wy.c.c.a.b():void");
    }

    public a(Bundle bundle) {
        this.c = bundle;
        b();
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            instance.setCertificateEntry("cert", this.a);
            TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance2.init(instance);
            this.b = SSLContext.getInstance(SSLSocketFactory.SSL);
            this.b.init(null, instance2.getTrustManagers(), null);
        } catch (IOException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            bundle.putBoolean("isNeedToGetCert", true);
            e.printStackTrace();
        }
    }

    public SSLContext a() {
        return this.b;
    }

    public static void a(String str) {
        d = str;
    }
}
