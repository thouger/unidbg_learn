package cn.com.chinatelecom.account.api.a;

import android.telephony.PhoneNumberUtils;
import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.transition.EpicenterTranslateClipReveal;
import java.io.UnsupportedEncodingException;

public class a {
    private static char[] a = {DateFormat.CAPITAL_AM_PM, 'B', 'C', 'D', DateFormat.DAY, 'F', 'G', 'H', 'I', 'J', 'K', DateFormat.STANDALONE_MONTH, DateFormat.MONTH, PhoneNumberUtils.WILD, 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', DateFormat.AM_PM, 'b', 'c', DateFormat.DATE, 'e', 'f', 'g', DateFormat.HOUR, 'i', 'j', DateFormat.HOUR_OF_DAY, 'l', DateFormat.MINUTE, 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', EpicenterTranslateClipReveal.StateProperty.TARGET_X, 'y', DateFormat.TIME_ZONE, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, GsmAlphabet.GSM_EXTENDED_ESCAPE, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public static byte[] a(String str) {
        AppMethodBeat.i(7833, false);
        try {
            byte[] b2 = b(str);
            AppMethodBeat.o(7833);
            return b2;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            byte[] bArr = new byte[0];
            AppMethodBeat.o(7833);
            return bArr;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0067, code lost:
        if (r7 != -1) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        r2.append((char) (((r6 & 15) << 4) | ((r7 & 60) >>> 2)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0077, code lost:
        r6 = r0 + 1;
        r0 = r10[r0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007b, code lost:
        if (r0 != 61) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007e, code lost:
        r0 = cn.com.chinatelecom.account.api.a.a.b[r0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0082, code lost:
        if (r6 >= r3) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0084, code lost:
        if (r0 == -1) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0087, code lost:
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0089, code lost:
        if (r0 != -1) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008c, code lost:
        r2.append((char) (r0 | ((r7 & 3) << 6)));
        r0 = r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] b(java.lang.String r10) {
        /*
            r0 = 0
            r1 = 7838(0x1e9e, float:1.0983E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "US-ASCII"
            byte[] r10 = r10.getBytes(r3)
            int r3 = r10.length
        L_0x0013:
            java.lang.String r4 = "iso8859-1"
            if (r0 >= r3) goto L_0x0050
        L_0x0018:
            byte[] r5 = cn.com.chinatelecom.account.api.a.a.b
            int r6 = r0 + 1
            byte r0 = r10[r0]
            byte r0 = r5[r0]
            r5 = -1
            if (r6 >= r3) goto L_0x0028
            if (r0 == r5) goto L_0x0026
            goto L_0x0028
        L_0x0026:
            r0 = r6
            goto L_0x0018
        L_0x0028:
            if (r0 != r5) goto L_0x002b
            goto L_0x0050
        L_0x002b:
            byte[] r7 = cn.com.chinatelecom.account.api.a.a.b
            int r8 = r6 + 1
            byte r6 = r10[r6]
            byte r6 = r7[r6]
            if (r8 >= r3) goto L_0x003a
            if (r6 == r5) goto L_0x0038
            goto L_0x003a
        L_0x0038:
            r6 = r8
            goto L_0x002b
        L_0x003a:
            if (r6 != r5) goto L_0x003d
            goto L_0x0050
        L_0x003d:
            int r0 = r0 << 2
            r7 = r6 & 48
            int r7 = r7 >>> 4
            r0 = r0 | r7
            char r0 = (char) r0
            r2.append(r0)
        L_0x0048:
            int r0 = r8 + 1
            byte r7 = r10[r8]
            r8 = 61
            if (r7 != r8) goto L_0x005c
        L_0x0050:
            java.lang.String r10 = r2.toString()
            byte[] r10 = r10.getBytes(r4)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r10
        L_0x005c:
            byte[] r9 = cn.com.chinatelecom.account.api.a.a.b
            byte r7 = r9[r7]
            if (r0 >= r3) goto L_0x0067
            if (r7 == r5) goto L_0x0065
            goto L_0x0067
        L_0x0065:
            r8 = r0
            goto L_0x0048
        L_0x0067:
            if (r7 != r5) goto L_0x006a
            goto L_0x0050
        L_0x006a:
            r6 = r6 & 15
            int r6 = r6 << 4
            r9 = r7 & 60
            int r9 = r9 >>> 2
            r6 = r6 | r9
            char r6 = (char) r6
            r2.append(r6)
        L_0x0077:
            int r6 = r0 + 1
            byte r0 = r10[r0]
            if (r0 != r8) goto L_0x007e
            goto L_0x0050
        L_0x007e:
            byte[] r9 = cn.com.chinatelecom.account.api.a.a.b
            byte r0 = r9[r0]
            if (r6 >= r3) goto L_0x0089
            if (r0 == r5) goto L_0x0087
            goto L_0x0089
        L_0x0087:
            r0 = r6
            goto L_0x0077
        L_0x0089:
            if (r0 != r5) goto L_0x008c
            goto L_0x0050
        L_0x008c:
            r4 = r7 & 3
            int r4 = r4 << 6
            r0 = r0 | r4
            char r0 = (char) r0
            r2.append(r0)
            r0 = r6
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.api.a.a.b(java.lang.String):byte[]");
    }
}
