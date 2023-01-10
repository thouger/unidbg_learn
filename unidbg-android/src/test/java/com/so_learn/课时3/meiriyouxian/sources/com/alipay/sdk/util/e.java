package com.alipay.sdk.util;

import android.net.wifi.WifiEnterpriseConfig;
import com.alipay.sdk.d.a;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class e {
    private static a.AbstractC0064a a;

    private static void a(String str) {
        try {
            a.AbstractC0064a aVar = a;
            if (aVar != null) {
                aVar.a(String.format("[AlipaySDK] %s %s", new SimpleDateFormat("hh:mm:ss.SSS", Locale.getDefault()).format(new Date()), str));
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, String str2) {
        a(e(str, str2));
    }

    public static void b(String str, String str2) {
        a(e(str, str2));
    }

    public static void c(String str, String str2) {
        a(e(str, str2));
    }

    public static void d(String str, String str2) {
        a(e(str, str2));
    }

    public static void a(String str, String str2, Throwable th) {
        String e = e(str, str2);
        a(e + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + b(th));
    }

    public static void a(Throwable th) {
        if (th != null) {
            try {
                a(b(th));
            } catch (Throwable unused) {
            }
        }
    }

    private static String e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        return String.format("[%s][%s]", str, str2);
    }

    private static String b(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
