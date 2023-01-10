package cn.missfresh.risk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import cn.missfresh.risk.bean.SignaturesBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;

/* compiled from: SignaturesUtil */
public class n {
    public static SignaturesBean a(Context context) {
        AppMethodBeat.i(1001, false);
        SignaturesBean signaturesBean = new SignaturesBean("", "");
        try {
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            signaturesBean.setSignMD5(m.a(packageInfo.signatures));
            X509Certificate b = m.b(packageInfo.signatures);
            if (b == null) {
                AppMethodBeat.o(1001);
                return signaturesBean;
            }
            signaturesBean.setSignDn(b.getIssuerDN().toString());
            b.getIssuerX500Principal().toString();
            String str = b.getVersion() + "";
            b.getSigAlgName();
            b.getSigAlgOID();
            b.getSerialNumber().toString();
            m.a(b.getTBSCertificate());
            AppMethodBeat.o(1001);
            return signaturesBean;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (CertificateEncodingException e2) {
            e2.printStackTrace();
        }
    }
}
