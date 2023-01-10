package com.hmt.analytics.android;

import com.hmt.analytics.util.b;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* compiled from: UrlBase64Coder */
public class l {
    public static String a(String str) throws UnsupportedEncodingException {
        return new String(b.a(str.getBytes("UTF-8")), "UTF-8");
    }

    public static String b(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(str.getBytes("UTF-8"));
        gZIPOutputStream.close();
        String str2 = new String(byteArrayOutputStream.toByteArray(), "ISO-8859-1");
        byteArrayOutputStream.close();
        return str2;
    }

    public static String c(String str) throws IOException {
        byte[] bytes = str.getBytes("ISO-8859-1");
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr = new byte[256];
        while (true) {
            int read = gZIPInputStream.read(bArr);
            if (read >= 0) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                String str2 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                byteArrayInputStream.close();
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                return str2;
            }
        }
    }

    public static String d(String str) throws IOException {
        return b.a(h.d(g.n), b(str));
    }

    public static String e(String str) throws IOException {
        return c(b.b(h.d(g.n), str));
    }
}
