package cn.missfresh.module.base.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* compiled from: CheckSignUtil */
public class n {
    private Context a;
    private String b = null;
    private String c = null;

    public n(Context context, String str) {
        AppMethodBeat.i(23141, false);
        this.a = context;
        this.c = str;
        this.b = a();
        AppMethodBeat.o(23141);
    }

    public String a() {
        PackageInfo packageInfo;
        CertificateFactory certificateFactory;
        X509Certificate x509Certificate;
        AppMethodBeat.i(23142, false);
        String str = null;
        try {
            packageInfo = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException e) {
            d.b("SignCheck", "", e);
            packageInfo = null;
        }
        if (packageInfo == null) {
            AppMethodBeat.o(23142);
            return "";
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
        try {
            certificateFactory = CertificateFactory.getInstance("X509");
        } catch (Exception e2) {
            d.b("SignCheck", "", e2);
            certificateFactory = null;
        }
        if (certificateFactory == null) {
            AppMethodBeat.o(23142);
            return "";
        }
        try {
            x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
        } catch (Exception e3) {
            d.b("SignCheck", "", e3);
            x509Certificate = null;
        }
        if (x509Certificate == null) {
            AppMethodBeat.o(23142);
            return "";
        }
        try {
            str = a(MessageDigest.getInstance("SHA1").digest(x509Certificate.getEncoded()));
        } catch (Exception e4) {
            d.b("SignCheck", "", e4);
        }
        AppMethodBeat.o(23142);
        return str;
    }

    private String a(byte[] bArr) {
        AppMethodBeat.i(23143, false);
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        String sb2 = sb.toString();
        AppMethodBeat.o(23143);
        return sb2;
    }

    public boolean b() {
        AppMethodBeat.i(23144, false);
        if (this.c != null) {
            this.b = this.b.trim();
            this.c = this.c.trim();
            if (this.b.equals(this.c)) {
                AppMethodBeat.o(23144);
                return true;
            }
        } else {
            d.b("SignCheck", "\u672a\u7ed9\u5b9a\u771f\u5b9e\u7684\u7b7e\u540d SHA-1 \u503c");
        }
        AppMethodBeat.o(23144);
        return false;
    }
}
