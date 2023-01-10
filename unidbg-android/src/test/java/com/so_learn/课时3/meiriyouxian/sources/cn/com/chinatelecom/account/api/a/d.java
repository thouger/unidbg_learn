package cn.com.chinatelecom.account.api.a;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.nio.charset.Charset;

public class d {
    private static final String a = d.class.getSimpleName();
    private static final Charset b = Charset.forName("UTF-8");
    private static byte[] c = "D@^12S".getBytes(b);

    static {
        AppMethodBeat.i(7870, false);
        AppMethodBeat.o(7870);
    }

    public static String a(byte[] bArr) {
        AppMethodBeat.i(7867, false);
        try {
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr2[i] = bArr[i];
                for (byte b2 : c) {
                    bArr2[i] = (byte) (b2 ^ bArr2[i]);
                }
            }
            String str = new String(bArr2);
            AppMethodBeat.o(7867);
            return str;
        } catch (Throwable th) {
            th.printStackTrace();
            AppMethodBeat.o(7867);
            return "";
        }
    }
}
