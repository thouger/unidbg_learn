package com.sobot.chat.utils;

import android.content.Context;
import android.media.MediaCodec;
import android.os.Build;
import android.os.Environment;
import android.security.keystore.KeyProperties;
import com.android.internal.inputmethod.InputMethodUtils;
import com.sobot.chat.api.apiUtils.SobotApp;
import com.sobot.chat.application.MyApplication;
import java.io.File;
import java.security.MessageDigest;

/* compiled from: SobotPathManager */
public class z {
    private static String b;
    private static z c;
    private Context a;

    private z(Context context) {
        if (context != null) {
            this.a = context.getApplicationContext();
        } else {
            this.a = MyApplication.getInstance().getLastActivity();
        }
    }

    public static z a() {
        if (c == null) {
            synchronized (z.class) {
                if (c == null) {
                    c = new z(SobotApp.getApplicationContext());
                }
            }
        }
        return c;
    }

    public String b() {
        if (b == null) {
            Context context = this.a;
            String packageName = context != null ? context.getPackageName() : "";
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.getExternalStorageDirectory().getPath());
            sb.append(File.separator);
            sb.append(Context.DOWNLOAD_SERVICE);
            sb.append(File.separator);
            sb.append(a(packageName + "cache_sobot"));
            b = sb.toString();
        }
        return b;
    }

    public String c() {
        if (Build.VERSION.SDK_INT < 29 || Environment.isExternalStorageLegacy()) {
            return b() + File.separator + MediaCodec.MetricsConstants.MODE_VIDEO + File.separator;
        }
        return this.a.getExternalFilesDir(Environment.DIRECTORY_MOVIES).getPath() + File.separator;
    }

    public String d() {
        if (Build.VERSION.SDK_INT < 29 || Environment.isExternalStorageLegacy()) {
            return b() + File.separator + InputMethodUtils.SUBTYPE_MODE_VOICE + File.separator;
        }
        return this.a.getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath() + File.separator;
    }

    public String e() {
        if (Build.VERSION.SDK_INT < 29 || Environment.isExternalStorageLegacy()) {
            return b() + File.separator + "pic" + File.separator;
        }
        return this.a.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath() + File.separator;
    }

    public String f() {
        if (Build.VERSION.SDK_INT < 29 || Environment.isExternalStorageLegacy()) {
            return b() + File.separator + "cache" + File.separator;
        }
        return this.a.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator + "cache" + File.separator;
    }

    private String a(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (byte b2 : MessageDigest.getInstance(KeyProperties.DIGEST_MD5).digest(str.getBytes())) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() < 2) {
                    sb.append("0");
                }
                sb.append(hexString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
