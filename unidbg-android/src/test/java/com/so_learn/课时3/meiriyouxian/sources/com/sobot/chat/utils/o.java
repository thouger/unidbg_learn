package com.sobot.chat.utils;

import android.media.MediaFormat;
import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;

/* compiled from: MediaFileUtils */
public class o {
    public static String a;
    private static HashMap<String, a> b = new HashMap<>();
    private static HashMap<String, Integer> c = new HashMap<>();

    public static boolean a(int i) {
        return i >= 21 && i <= 25;
    }

    /* compiled from: MediaFileUtils */
    /* access modifiers changed from: package-private */
    public static class a {
        int a;
        String b;

        a(int i, String str) {
            this.a = i;
            this.b = str;
        }
    }

    static {
        a("MP3", 1, MediaFormat.MIMETYPE_AUDIO_MPEG);
        a("M4A", 2, "audio/mp4");
        a("WAV", 3, "audio/x-wav");
        a("AMR", 4, "audio/amr");
        a("AWB", 5, MediaFormat.MIMETYPE_AUDIO_AMR_WB);
        a("WMA", 6, "audio/x-ms-wma");
        a("OGG", 7, "application/ogg");
        a("MID", 11, "audio/midi");
        a("XMF", 11, "audio/midi");
        a("RTTTL", 11, "audio/midi");
        a("SMF", 12, "audio/sp-midi");
        a("IMY", 13, "audio/imelody");
        a("MP4", 21, "video/mp4");
        a("M4V", 22, "video/mp4");
        a("3GP", 23, MediaFormat.MIMETYPE_VIDEO_H263);
        a("3GPP", 23, MediaFormat.MIMETYPE_VIDEO_H263);
        a("3G2", 24, "video/3gpp2");
        a("3GPP2", 24, "video/3gpp2");
        a("WMV", 25, "video/x-ms-wmv");
        a("JPG", 31, "image/jpeg");
        a("JPEG", 31, "image/jpeg");
        a("GIF", 32, "image/gif");
        a("PNG", 33, "image/png");
        a("BMP", 34, "image/x-ms-bmp");
        a("WBMP", 35, "image/vnd.wap.wbmp");
        a("M3U", 41, "audio/x-mpegurl");
        a("PLS", 42, "audio/x-scpls");
        a("WPL", 43, "application/vnd.ms-wpl");
        StringBuilder sb = new StringBuilder();
        for (String str : b.keySet()) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(str);
        }
        a = sb.toString();
    }

    static void a(String str, int i, String str2) {
        b.put(str, new a(i, str2));
        c.put(str2, new Integer(i));
    }

    public static a a(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf < 0) {
            return null;
        }
        return b.get(str.substring(lastIndexOf + 1).toUpperCase());
    }

    public static boolean b(String str) {
        a a2;
        if (TextUtils.isEmpty(str) || !new File(str).exists() || (a2 = a(str)) == null) {
            return false;
        }
        return a(a2.a);
    }
}
