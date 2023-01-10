package com.cmic.sso.wy.utils;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/* compiled from: SystemUtils */
public class t {
    public static boolean a() {
        try {
            for (String str : new String[]{"/system/xbin/", "/system/bin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/"}) {
                String str2 = str + "su";
                if (new File(str2).exists()) {
                    String a = a(new String[]{"ls", "-l", str2});
                    g.b("cyb", "isRooted=" + a);
                    if (TextUtils.isEmpty(a) || a.indexOf("root") == a.lastIndexOf("root")) {
                        return false;
                    }
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String a(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        try {
            Process start = new ProcessBuilder(strArr).start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            start.getInputStream().close();
            start.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
