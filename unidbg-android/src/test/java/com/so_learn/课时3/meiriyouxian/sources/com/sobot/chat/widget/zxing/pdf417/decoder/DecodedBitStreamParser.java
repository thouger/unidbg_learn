package com.sobot.chat.widget.zxing.pdf417.decoder;

import java.math.BigInteger;

final class DecodedBitStreamParser {
    private static final char[] a = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] b = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final BigInteger[] c = new BigInteger[16];

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        c[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        c[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr = c;
            if (i < bigIntegerArr.length) {
                bigIntegerArr[i] = bigIntegerArr[i - 1].multiply(valueOf);
                i++;
            } else {
                return;
            }
        }
    }
}
