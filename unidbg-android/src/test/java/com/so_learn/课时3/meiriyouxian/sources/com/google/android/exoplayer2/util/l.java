package com.google.android.exoplayer2.util;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.text.TextUtils;
import java.util.ArrayList;

/* compiled from: MimeTypes */
public final class l {
    private static final ArrayList<a> a = new ArrayList<>();

    /* compiled from: MimeTypes */
    /* access modifiers changed from: private */
    public static final class a {
        public final String a;
        public final String b;
        public final int c;
    }

    public static String a(int i) {
        if (i == 32) {
            return MediaFormat.MIMETYPE_VIDEO_MPEG4;
        }
        if (i == 33) {
            return MediaFormat.MIMETYPE_VIDEO_AVC;
        }
        if (i == 35) {
            return MediaFormat.MIMETYPE_VIDEO_HEVC;
        }
        if (i == 64) {
            return MediaFormat.MIMETYPE_AUDIO_AAC;
        }
        if (i == 163) {
            return "video/wvc1";
        }
        if (i == 177) {
            return MediaFormat.MIMETYPE_VIDEO_VP9;
        }
        if (i == 165) {
            return MediaFormat.MIMETYPE_AUDIO_AC3;
        }
        if (i == 166) {
            return MediaFormat.MIMETYPE_AUDIO_EAC3;
        }
        switch (i) {
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
                return MediaFormat.MIMETYPE_VIDEO_MPEG2;
            case 102:
            case 103:
            case 104:
                return MediaFormat.MIMETYPE_AUDIO_AAC;
            case 105:
            case 107:
                return MediaFormat.MIMETYPE_AUDIO_MPEG;
            case 106:
                return "video/mpeg";
            default:
                switch (i) {
                    case 169:
                    case 172:
                        return "audio/vnd.dts";
                    case 170:
                    case 171:
                        return "audio/vnd.dts.hd";
                    case 173:
                        return MediaFormat.MIMETYPE_AUDIO_OPUS;
                    default:
                        return null;
                }
        }
    }

    public static boolean a(String str) {
        return "audio".equals(j(str));
    }

    public static boolean b(String str) {
        return MediaCodec.MetricsConstants.MODE_VIDEO.equals(j(str));
    }

    public static boolean c(String str) {
        return "text".equals(j(str));
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : z.j(str)) {
            String f = f(str2);
            if (f != null && b(f)) {
                return f;
            }
        }
        return null;
    }

    public static String e(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : z.j(str)) {
            String f = f(str2);
            if (f != null && a(f)) {
                return f;
            }
        }
        return null;
    }

    public static String f(String str) {
        String str2 = null;
        if (str == null) {
            return null;
        }
        String d = z.d(str.trim());
        if (d.startsWith("avc1") || d.startsWith("avc3")) {
            return MediaFormat.MIMETYPE_VIDEO_AVC;
        }
        if (d.startsWith("hev1") || d.startsWith("hvc1")) {
            return MediaFormat.MIMETYPE_VIDEO_HEVC;
        }
        if (d.startsWith("vp9") || d.startsWith("vp09")) {
            return MediaFormat.MIMETYPE_VIDEO_VP9;
        }
        if (d.startsWith("vp8") || d.startsWith("vp08")) {
            return MediaFormat.MIMETYPE_VIDEO_VP8;
        }
        if (d.startsWith("mp4a")) {
            if (d.startsWith("mp4a.")) {
                String substring = d.substring(5);
                if (substring.length() >= 2) {
                    try {
                        str2 = a(Integer.parseInt(z.e(substring.substring(0, 2)), 16));
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            return str2 == null ? MediaFormat.MIMETYPE_AUDIO_AAC : str2;
        } else if (d.startsWith("ac-3") || d.startsWith("dac3")) {
            return MediaFormat.MIMETYPE_AUDIO_AC3;
        } else {
            if (d.startsWith("ec-3") || d.startsWith("dec3")) {
                return MediaFormat.MIMETYPE_AUDIO_EAC3;
            }
            if (d.startsWith("ec+3")) {
                return "audio/eac3-joc";
            }
            if (d.startsWith("dtsc") || d.startsWith("dtse")) {
                return "audio/vnd.dts";
            }
            if (d.startsWith("dtsh") || d.startsWith("dtsl")) {
                return "audio/vnd.dts.hd";
            }
            if (d.startsWith("opus")) {
                return MediaFormat.MIMETYPE_AUDIO_OPUS;
            }
            if (d.startsWith("vorbis")) {
                return MediaFormat.MIMETYPE_AUDIO_VORBIS;
            }
            if (d.startsWith("flac")) {
                return MediaFormat.MIMETYPE_AUDIO_FLAC;
            }
            return k(d);
        }
    }

    public static int g(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (a(str)) {
            return 1;
        }
        if (b(str)) {
            return 2;
        }
        if (c(str) || "application/cea-608".equals(str) || "application/cea-708".equals(str) || "application/x-mp4-cea-608".equals(str) || MediaPlayer.MEDIA_MIMETYPE_TEXT_SUBRIP.equals(str) || "application/ttml+xml".equals(str) || "application/x-quicktime-tx3g".equals(str) || "application/x-mp4-vtt".equals(str) || "application/x-rawcc".equals(str) || "application/vobsub".equals(str) || "application/pgs".equals(str) || "application/dvbsubs".equals(str)) {
            return 3;
        }
        if ("application/id3".equals(str) || "application/x-emsg".equals(str) || "application/x-scte35".equals(str)) {
            return 4;
        }
        if ("application/x-camera-motion".equals(str)) {
            return 5;
        }
        return l(str);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static int h(String str) {
        char c;
        switch (str.hashCode()) {
            case -2123537834:
                if (str.equals("audio/eac3-joc")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case -1095064472:
                if (str.equals("audio/vnd.dts")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 187078296:
                if (str.equals(MediaFormat.MIMETYPE_AUDIO_AC3)) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 1504578661:
                if (str.equals(MediaFormat.MIMETYPE_AUDIO_EAC3)) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case 1505942594:
                if (str.equals("audio/vnd.dts.hd")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case 1556697186:
                if (str.equals("audio/true-hd")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        if (c == 0) {
            return 5;
        }
        if (c == 1 || c == 2) {
            return 6;
        }
        if (c == 3) {
            return 7;
        }
        if (c != 4) {
            return c != 5 ? 0 : 14;
        }
        return 8;
    }

    public static int i(String str) {
        return g(f(str));
    }

    private static String j(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(47);
        if (indexOf != -1) {
            return str.substring(0, indexOf);
        }
        throw new IllegalArgumentException("Invalid mime type: " + str);
    }

    private static String k(String str) {
        int size = a.size();
        for (int i = 0; i < size; i++) {
            a aVar = a.get(i);
            if (str.startsWith(aVar.b)) {
                return aVar.a;
            }
        }
        return null;
    }

    private static int l(String str) {
        int size = a.size();
        for (int i = 0; i < size; i++) {
            a aVar = a.get(i);
            if (str.equals(aVar.a)) {
                return aVar.c;
            }
        }
        return -1;
    }
}
