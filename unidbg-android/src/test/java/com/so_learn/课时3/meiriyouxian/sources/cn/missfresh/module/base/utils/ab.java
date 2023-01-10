package cn.missfresh.module.base.utils;

import android.app.backup.FullBackup;
import android.security.keystore.KeyProperties;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/* compiled from: MD5 */
public class ab {
    private static final String[] a = {"0", "1", "2", "3", "4", "5", Constants.VIA_SHARE_TYPE_INFO, "7", "8", "9", "a", "b", "c", "d", "e", FullBackup.FILES_TREE_TOKEN};

    public static String a(byte[] bArr) {
        AppMethodBeat.i(23339, false);
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(a(b));
        }
        String stringBuffer2 = stringBuffer.toString();
        AppMethodBeat.o(23339);
        return stringBuffer2;
    }

    private static String a(byte b) {
        AppMethodBeat.i(23340, false);
        int i = b;
        if (b < 0) {
            i = b + 256;
        }
        int i2 = (i == 1 ? 1 : 0) / 16;
        String str = a[i2] + a[i % 16];
        AppMethodBeat.o(23340);
        return str;
    }

    public static String a(File file) {
        AppMethodBeat.i(23343, false);
        if (!file.isFile()) {
            AppMethodBeat.o(23343);
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    String a2 = a(instance.digest());
                    AppMethodBeat.o(23343);
                    return a2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(23343);
            return null;
        }
    }
}
