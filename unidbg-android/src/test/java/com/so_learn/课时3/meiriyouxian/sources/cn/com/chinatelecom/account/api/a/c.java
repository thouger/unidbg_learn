package cn.com.chinatelecom.account.api.a;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class c {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', DateFormat.CAPITAL_AM_PM, 'B', 'C', 'D', DateFormat.DAY, 'F'};

    public static String a(byte[] bArr) {
        AppMethodBeat.i(7858, false);
        if (bArr == null || bArr.length == 0) {
            AppMethodBeat.o(7858);
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.append(a[(bArr[i] >> 4) & 15]);
            sb.append(a[bArr[i] & 15]);
        }
        String sb2 = sb.toString();
        AppMethodBeat.o(7858);
        return sb2;
    }

    public static byte[] a(String str) {
        AppMethodBeat.i(7860, false);
        if (str == null) {
            AppMethodBeat.o(7860);
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int digit = Character.digit(charArray[i2 + 1], 16) | (Character.digit(charArray[i2], 16) << 4);
            if (digit > 127) {
                digit -= 256;
            }
            bArr[i] = (byte) digit;
        }
        AppMethodBeat.o(7860);
        return bArr;
    }
}
