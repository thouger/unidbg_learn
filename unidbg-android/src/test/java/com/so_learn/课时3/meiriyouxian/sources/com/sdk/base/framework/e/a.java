package com.sdk.base.framework.e;

import android.telephony.PhoneNumberUtils;
import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.transition.EpicenterTranslateClipReveal;

public final class a extends d {
    private static final char[] a = {DateFormat.CAPITAL_AM_PM, 'B', 'C', 'D', DateFormat.DAY, 'F', 'G', 'H', 'I', 'J', 'K', DateFormat.STANDALONE_MONTH, DateFormat.MONTH, PhoneNumberUtils.WILD, 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', DateFormat.AM_PM, 'b', 'c', DateFormat.DATE, 'e', 'f', 'g', DateFormat.HOUR, 'i', 'j', DateFormat.HOUR_OF_DAY, 'l', DateFormat.MINUTE, 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', EpicenterTranslateClipReveal.StateProperty.TARGET_X, 'y', DateFormat.TIME_ZONE, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] b = new byte[256];
    private byte[] c = new byte[4];

    static {
        for (int i = 0; i < 255; i++) {
            b[i] = -1;
        }
        for (int i2 = 0; i2 < 64; i2++) {
            b[a[i2]] = (byte) i2;
        }
    }

    public a() {
        AppMethodBeat.i(20207, false);
        AppMethodBeat.o(20207);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.io.PushbackInputStream r11, java.io.OutputStream r12, int r13) {
        /*
        // Method dump skipped, instructions count: 246
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sdk.base.framework.e.a.a(java.io.PushbackInputStream, java.io.OutputStream, int):void");
    }
}
