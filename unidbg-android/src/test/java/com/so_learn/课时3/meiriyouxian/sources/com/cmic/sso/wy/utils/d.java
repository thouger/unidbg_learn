package com.cmic.sso.wy.utils;

import android.net.wifi.WifiEnterpriseConfig;
import android.text.format.DateFormat;
import com.huawei.hms.framework.common.ContainerUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/* compiled from: Base64Utils */
public class d {
    private static final char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    public static String a(byte[] bArr) {
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer((bArr.length * 3) / 2);
        int i = length - 3;
        int i2 = 0;
        int i3 = 0;
        while (i2 <= i) {
            int i4 = ((bArr[i2] & 255) << 16) | ((bArr[i2 + 1] & 255) << 8) | (bArr[i2 + 2] & 255);
            stringBuffer.append(a[(i4 >> 18) & 63]);
            stringBuffer.append(a[(i4 >> 12) & 63]);
            stringBuffer.append(a[(i4 >> 6) & 63]);
            stringBuffer.append(a[i4 & 63]);
            i2 += 3;
            int i5 = i3 + 1;
            if (i3 >= 14) {
                stringBuffer.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
                i3 = 0;
            } else {
                i3 = i5;
            }
        }
        int i6 = 0 + length;
        if (i2 == i6 - 2) {
            int i7 = ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2] & 255) << 16);
            stringBuffer.append(a[(i7 >> 18) & 63]);
            stringBuffer.append(a[(i7 >> 12) & 63]);
            stringBuffer.append(a[(i7 >> 6) & 63]);
            stringBuffer.append(ContainerUtils.KEY_VALUE_DELIMITER);
        } else if (i2 == i6 - 1) {
            int i8 = (bArr[i2] & 255) << 16;
            stringBuffer.append(a[(i8 >> 18) & 63]);
            stringBuffer.append(a[(i8 >> 12) & 63]);
            stringBuffer.append("==");
        }
        return stringBuffer.toString();
    }

    private static int a(char c) {
        int i;
        if (c >= 'A' && c <= 'Z') {
            return c - DateFormat.CAPITAL_AM_PM;
        }
        if (c >= 'a' && c <= 'z') {
            i = c - DateFormat.AM_PM;
        } else if (c >= '0' && c <= '9') {
            i = (c - '0') + 26;
        } else if (c == '+') {
            return 62;
        } else {
            if (c == '/') {
                return 63;
            }
            if (c == '=') {
                return 0;
            }
            throw new RuntimeException("unexpected code: " + c);
        }
        return i + 26;
    }

    public static byte[] a(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a(str, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                PrintStream printStream = System.err;
                printStream.println("Error while decoding BASE64: " + e.toString());
            }
            return byteArray;
        } catch (IOException unused) {
            throw new RuntimeException();
        }
    }

    private static void a(String str, OutputStream outputStream) throws IOException {
        int length = str.length();
        int i = 0;
        while (true) {
            if (i < length && str.charAt(i) <= ' ') {
                i++;
            } else if (i != length) {
                int i2 = i + 2;
                int i3 = i + 3;
                int a2 = (a(str.charAt(i)) << 18) + (a(str.charAt(i + 1)) << 12) + (a(str.charAt(i2)) << 6) + a(str.charAt(i3));
                outputStream.write((a2 >> 16) & 255);
                if (str.charAt(i2) != '=') {
                    outputStream.write((a2 >> 8) & 255);
                    if (str.charAt(i3) != '=') {
                        outputStream.write(a2 & 255);
                        i += 4;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }
}
