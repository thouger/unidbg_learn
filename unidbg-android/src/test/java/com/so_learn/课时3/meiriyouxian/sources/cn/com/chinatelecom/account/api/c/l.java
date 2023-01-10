package cn.com.chinatelecom.account.api.c;

import android.bluetooth.BluetoothInputHost;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class l {
    private static final String a = l.class.getSimpleName();
    private static byte[] b = {68, BluetoothInputHost.SUBCLASS1_KEYBOARD, 94, 49, 69, 35, 50, 83};

    static {
        AppMethodBeat.i(8376, false);
        AppMethodBeat.o(8376);
    }

    public static String a(byte[] bArr) {
        AppMethodBeat.i(8373, false);
        try {
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr2[i] = bArr[i];
                for (byte b2 : b) {
                    bArr2[i] = (byte) (b2 ^ bArr2[i]);
                }
            }
            String str = new String(bArr2);
            AppMethodBeat.o(8373);
            return str;
        } catch (Throwable th) {
            th.printStackTrace();
            AppMethodBeat.o(8373);
            return "";
        }
    }
}
