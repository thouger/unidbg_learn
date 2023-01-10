package com.sobot.chat.core.http.h;

import android.text.TextUtils;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.lidroid.xutils.http.client.multipart.MIME;
import java.io.Closeable;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import okhttp3.Response;

/* compiled from: IOUtils */
public class b {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static boolean a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return a(new File(str));
        }
        return false;
    }

    public static boolean a(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            }
            file.delete();
        }
        return file.mkdirs();
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return b(new File(str));
    }

    public static boolean b(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isFile()) {
            file.delete();
            return true;
        } else if (!file.isDirectory()) {
            return true;
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    b(file2);
                }
            }
            file.delete();
            return true;
        }
    }

    public static String a(Response response, String str) {
        String a = a(response);
        if (TextUtils.isEmpty(a)) {
            a = c(str);
        }
        if (TextUtils.isEmpty(a)) {
            a = "unknownfile_" + System.currentTimeMillis();
        }
        try {
            return URLDecoder.decode(a, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return a;
        }
    }

    private static String a(Response response) {
        String header = response.header(MIME.CONTENT_DISPOSITION);
        if (header == null) {
            return null;
        }
        String replaceAll = header.replaceAll("\"", "");
        int indexOf = replaceAll.indexOf("filename=");
        if (indexOf != -1) {
            return replaceAll.substring(indexOf + 9, replaceAll.length());
        }
        int indexOf2 = replaceAll.indexOf("filename*=");
        if (indexOf2 == -1) {
            return null;
        }
        String substring = replaceAll.substring(indexOf2 + 10, replaceAll.length());
        return substring.startsWith("UTF-8''") ? substring.substring(7, substring.length()) : substring;
    }

    private static String c(String str) {
        int indexOf;
        String[] split = str.split(NotificationIconUtil.SPLIT_CHAR);
        for (String str2 : split) {
            if (str2.contains("?") && (indexOf = str2.indexOf("?")) != -1) {
                return str2.substring(0, indexOf);
            }
        }
        if (split.length > 0) {
            return split[split.length - 1];
        }
        return null;
    }
}
