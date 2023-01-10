package cn.missfresh.risk;

import android.content.pm.Signature;
import android.security.keystore.KeyProperties;
import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;

/* compiled from: SignaturesMsg */
public class m {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', DateFormat.CAPITAL_AM_PM, 'B', 'C', 'D', DateFormat.DAY, 'F'};
    private static final X500Principal b = new X500Principal("CN=Android Debug,O=Android,C=US");

    static {
        AppMethodBeat.i(MetricsProto.MetricsEvent.SETTINGS_ASSIST_GESTURE_TRAINING_INTRO, false);
        AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_ASSIST_GESTURE_TRAINING_INTRO);
    }

    public static String a(byte[] bArr) {
        AppMethodBeat.i(962, false);
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            sb.append(a[(bArr[i] & 240) >>> 4]);
            sb.append(a[bArr[i] & 15]);
        }
        String sb2 = sb.toString();
        AppMethodBeat.o(962);
        return sb2;
    }

    public static String a(Signature[] signatureArr) {
        AppMethodBeat.i(967, false);
        if (signatureArr == null) {
            AppMethodBeat.o(967);
            return "";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            if (signatureArr != null) {
                for (Signature signature : signatureArr) {
                    instance.update(signature.toByteArray());
                }
            }
            String a2 = a(instance.digest());
            AppMethodBeat.o(967);
            return a2;
        } catch (Exception unused) {
            AppMethodBeat.o(967);
            return "";
        }
    }

    public static X509Certificate b(Signature[] signatureArr) {
        AppMethodBeat.i(981, false);
        try {
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()));
            AppMethodBeat.o(981);
            return x509Certificate;
        } catch (Exception unused) {
            AppMethodBeat.o(981);
            return null;
        }
    }
}
