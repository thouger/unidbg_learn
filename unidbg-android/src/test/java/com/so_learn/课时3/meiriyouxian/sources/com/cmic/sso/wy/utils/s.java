package com.cmic.sso.wy.utils;

import android.text.format.DateFormat;
import java.util.Locale;

/* compiled from: StringUtils */
/* access modifiers changed from: package-private */
public class s {
    private static char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', DateFormat.CAPITAL_AM_PM, 'B', 'C', 'D', DateFormat.DAY, 'F'};

    static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(a[(b >>> 4) & 15]);
            sb.append(a[b & 15]);
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }
}
