package com.cmic.sso.wy.utils.a;

import android.telephony.PhoneNumberUtils;
import android.text.format.DateFormat;
import com.android.internal.transition.EpicenterTranslateClipReveal;

/* compiled from: BASE64Decoder */
public class a extends d {
    private static final char[] b = {DateFormat.CAPITAL_AM_PM, 'B', 'C', 'D', DateFormat.DAY, 'F', 'G', 'H', 'I', 'J', 'K', DateFormat.STANDALONE_MONTH, DateFormat.MONTH, PhoneNumberUtils.WILD, 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', DateFormat.AM_PM, 'b', 'c', DateFormat.DATE, 'e', 'f', 'g', DateFormat.HOUR, 'i', 'j', DateFormat.HOUR_OF_DAY, 'l', DateFormat.MINUTE, 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', EpicenterTranslateClipReveal.StateProperty.TARGET_X, 'y', DateFormat.TIME_ZONE, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] c = new byte[256];
    byte[] a = new byte[4];

    /* access modifiers changed from: protected */
    @Override // com.cmic.sso.wy.utils.a.d
    public int a() {
        return 4;
    }

    /* access modifiers changed from: protected */
    @Override // com.cmic.sso.wy.utils.a.d
    public int b() {
        return 72;
    }

    static {
        int i = 0;
        for (int i2 = 0; i2 < 255; i2++) {
            c[i2] = -1;
        }
        while (true) {
            char[] cArr = b;
            if (i < cArr.length) {
                c[cArr[i]] = (byte) i;
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00cf  */
    @Override // com.cmic.sso.wy.utils.a.d
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.io.PushbackInputStream r9, java.io.OutputStream r10, int r11) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 241
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.wy.utils.a.a.a(java.io.PushbackInputStream, java.io.OutputStream, int):void");
    }
}
