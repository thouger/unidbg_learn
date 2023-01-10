package cn.missfresh.risk.f;

import android.telephony.PhoneNumberUtils;
import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.transition.EpicenterTranslateClipReveal;
import com.huawei.hms.framework.common.ContainerUtils;
import com.umeng.message.proguard.f;
import java.io.UnsupportedEncodingException;

/* compiled from: Base64Utils */
public class a {
    private static char[] a = {DateFormat.CAPITAL_AM_PM, 'B', 'C', 'D', DateFormat.DAY, 'F', 'G', 'H', 'I', 'J', 'K', DateFormat.STANDALONE_MONTH, DateFormat.MONTH, PhoneNumberUtils.WILD, 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', DateFormat.AM_PM, 'b', 'c', DateFormat.DATE, 'e', 'f', 'g', DateFormat.HOUR, 'i', 'j', DateFormat.HOUR_OF_DAY, 'l', DateFormat.MINUTE, 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', EpicenterTranslateClipReveal.StateProperty.TARGET_X, 'y', DateFormat.TIME_ZONE, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, GsmAlphabet.GSM_EXTENDED_ESCAPE, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public static String a(byte[] bArr) {
        int i = 0;
        AppMethodBeat.i(2764, false);
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        while (true) {
            if (i >= length) {
                break;
            }
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            if (i2 == length) {
                stringBuffer.append(a[i3 >>> 2]);
                stringBuffer.append(a[(i3 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }
            int i4 = i2 + 1;
            int i5 = bArr[i2] & 255;
            if (i4 == length) {
                stringBuffer.append(a[i3 >>> 2]);
                stringBuffer.append(a[((i3 & 3) << 4) | ((i5 & 240) >>> 4)]);
                stringBuffer.append(a[(i5 & 15) << 2]);
                stringBuffer.append(ContainerUtils.KEY_VALUE_DELIMITER);
                break;
            }
            int i6 = i4 + 1;
            int i7 = bArr[i4] & 255;
            stringBuffer.append(a[i3 >>> 2]);
            stringBuffer.append(a[((i3 & 3) << 4) | ((i5 & 240) >>> 4)]);
            stringBuffer.append(a[((i5 & 15) << 2) | ((i7 & 192) >>> 6)]);
            stringBuffer.append(a[i7 & 63]);
            i = i6;
        }
        String stringBuffer2 = stringBuffer.toString();
        AppMethodBeat.o(2764);
        return stringBuffer2;
    }

    public static byte[] a(String str) {
        AppMethodBeat.i(2768, false);
        try {
            byte[] b2 = b(str);
            AppMethodBeat.o(2768);
            return b2;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            byte[] bArr = new byte[0];
            AppMethodBeat.o(2768);
            return bArr;
        }
    }

    private static byte[] b(String str) throws UnsupportedEncodingException {
        int i;
        byte b2;
        int i2;
        byte b3;
        int i3;
        byte b4;
        int i4;
        byte b5;
        int i5 = 0;
        AppMethodBeat.i(2773, false);
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bytes = str.getBytes(f.b);
        int length = bytes.length;
        while (i5 < length) {
            while (true) {
                i = i5 + 1;
                b2 = b[bytes[i5]];
                if (i >= length || b2 != -1) {
                    break;
                }
                i5 = i;
            }
            if (b2 == -1) {
                break;
            }
            while (true) {
                i2 = i + 1;
                b3 = b[bytes[i]];
                if (i2 >= length || b3 != -1) {
                    break;
                }
                i = i2;
            }
            if (b3 == -1) {
                break;
            }
            stringBuffer.append((char) ((b2 << 2) | ((b3 & 48) >>> 4)));
            while (true) {
                i3 = i2 + 1;
                byte b6 = bytes[i2];
                if (b6 == 61) {
                    byte[] bytes2 = stringBuffer.toString().getBytes("iso8859-1");
                    AppMethodBeat.o(2773);
                    return bytes2;
                }
                b4 = b[b6];
                if (i3 >= length || b4 != -1) {
                    break;
                }
                i2 = i3;
            }
            if (b4 == -1) {
                break;
            }
            stringBuffer.append((char) (((b3 & 15) << 4) | ((b4 & 60) >>> 2)));
            while (true) {
                i4 = i3 + 1;
                byte b7 = bytes[i3];
                if (b7 == 61) {
                    byte[] bytes3 = stringBuffer.toString().getBytes("iso8859-1");
                    AppMethodBeat.o(2773);
                    return bytes3;
                }
                b5 = b[b7];
                if (i4 >= length || b5 != -1) {
                    break;
                }
                i3 = i4;
            }
            if (b5 == -1) {
                break;
            }
            stringBuffer.append((char) (b5 | ((b4 & 3) << 6)));
            i5 = i4;
        }
        byte[] bytes4 = stringBuffer.toString().getBytes("iso8859-1");
        AppMethodBeat.o(2773);
        return bytes4;
    }
}
