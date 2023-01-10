package cn.missfresh.buttomline.logtrace.e;

import android.app.backup.FullBackup;
import android.security.keystore.KeyProperties;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.connect.common.Constants;
import java.security.MessageDigest;

/* compiled from: MD5 */
public class e {
    private static final String[] a = {"0", "1", "2", "3", "4", "5", Constants.VIA_SHARE_TYPE_INFO, "7", "8", "9", "a", "b", "c", "d", "e", FullBackup.FILES_TREE_TOKEN};

    private static String a(byte[] bArr) {
        AppMethodBeat.i(17121, false);
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(a(b));
        }
        String sb2 = sb.toString();
        AppMethodBeat.o(17121);
        return sb2;
    }

    private static String a(byte b) {
        AppMethodBeat.i(17124, false);
        int i = b;
        if (b < 0) {
            i = b + 256;
        }
        int i2 = (i == 1 ? 1 : 0) / 16;
        String str = a[i2] + a[i % 16];
        AppMethodBeat.o(17124);
        return str;
    }

    public static String a(String str) {
        AppMethodBeat.i(17126, false);
        try {
            str = a(MessageDigest.getInstance(KeyProperties.DIGEST_MD5).digest(str.getBytes()));
        } catch (Exception unused) {
        }
        AppMethodBeat.o(17126);
        return str;
    }
}
