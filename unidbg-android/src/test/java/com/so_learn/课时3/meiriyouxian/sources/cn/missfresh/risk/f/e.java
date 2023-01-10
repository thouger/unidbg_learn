package cn.missfresh.risk.f;

import android.app.backup.FullBackup;
import android.security.keystore.KeyProperties;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.connect.common.Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: MD5 */
public class e {
    private static final String[] a = {"0", "1", "2", "3", "4", "5", Constants.VIA_SHARE_TYPE_INFO, "7", "8", "9", "a", "b", "c", "d", "e", FullBackup.FILES_TREE_TOKEN};

    public static String a(byte[] bArr) {
        AppMethodBeat.i(2870, false);
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(a(b));
        }
        String stringBuffer2 = stringBuffer.toString();
        AppMethodBeat.o(2870);
        return stringBuffer2;
    }

    private static String a(byte b) {
        AppMethodBeat.i(2873, false);
        int i = b;
        if (b < 0) {
            i = b + 256;
        }
        int i2 = (i == 1 ? 1 : 0) / 16;
        String str = a[i2] + a[i % 16];
        AppMethodBeat.o(2873);
        return str;
    }

    public static String a(String str, String str2) {
        String str3;
        AppMethodBeat.i(2877, false);
        try {
            String str4 = new String(str);
            try {
                str3 = a(MessageDigest.getInstance(str2).digest(str4.getBytes()));
            } catch (NoSuchAlgorithmException unused) {
                str3 = str4;
            }
        } catch (NoSuchAlgorithmException unused2) {
            str3 = null;
        }
        AppMethodBeat.o(2877);
        return str3;
    }

    public static String a(String str) {
        AppMethodBeat.i(2880, false);
        String a2 = a(str, KeyProperties.DIGEST_MD5);
        AppMethodBeat.o(2880);
        return a2;
    }
}
