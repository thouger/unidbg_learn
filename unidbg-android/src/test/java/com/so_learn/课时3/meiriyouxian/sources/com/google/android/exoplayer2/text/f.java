package com.google.android.exoplayer2.text;

import android.media.MediaPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.text.a.c;
import com.google.android.exoplayer2.text.d.a;
import com.google.android.exoplayer2.text.h.b;
import com.google.android.exoplayer2.text.h.g;

/* compiled from: SubtitleDecoderFactory */
public interface f {
    public static final f a = new AnonymousClass1();

    boolean a(Format format);

    e b(Format format);

    /* compiled from: SubtitleDecoderFactory */
    /* renamed from: com.google.android.exoplayer2.text.f$1  reason: invalid class name */
    static class AnonymousClass1 implements f {
        AnonymousClass1() {
        }

        @Override // com.google.android.exoplayer2.text.f
        public boolean a(Format format) {
            String str = format.g;
            return "text/vtt".equals(str) || "text/x-ssa".equals(str) || "application/ttml+xml".equals(str) || "application/x-mp4-vtt".equals(str) || MediaPlayer.MEDIA_MIMETYPE_TEXT_SUBRIP.equals(str) || "application/x-quicktime-tx3g".equals(str) || "application/cea-608".equals(str) || "application/x-mp4-cea-608".equals(str) || "application/cea-708".equals(str) || "application/dvbsubs".equals(str) || "application/pgs".equals(str);
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.google.android.exoplayer2.text.f
        public e b(Format format) {
            char c;
            String str = format.g;
            switch (str.hashCode()) {
                case -1351681404:
                    if (str.equals("application/dvbsubs")) {
                        c = '\t';
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1248334819:
                    if (str.equals("application/pgs")) {
                        c = '\n';
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1026075066:
                    if (str.equals("application/x-mp4-vtt")) {
                        c = 2;
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1004728940:
                    if (str.equals("text/vtt")) {
                        c = 0;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 691401887:
                    if (str.equals("application/x-quicktime-tx3g")) {
                        c = 5;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 822864842:
                    if (str.equals("text/x-ssa")) {
                        c = 1;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 930165504:
                    if (str.equals("application/x-mp4-cea-608")) {
                        c = 7;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1566015601:
                    if (str.equals("application/cea-608")) {
                        c = 6;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1566016562:
                    if (str.equals("application/cea-708")) {
                        c = '\b';
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1668750253:
                    if (str.equals(MediaPlayer.MEDIA_MIMETYPE_TEXT_SUBRIP)) {
                        c = 4;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1693976202:
                    if (str.equals("application/ttml+xml")) {
                        c = 3;
                        break;
                    }
                    c = '\uffff';
                    break;
                default:
                    c = '\uffff';
                    break;
            }
            switch (c) {
                case 0:
                    return new g();
                case 1:
                    return new a(format.i);
                case 2:
                    return new b();
                case 3:
                    return new com.google.android.exoplayer2.text.f.a();
                case 4:
                    return new com.google.android.exoplayer2.text.e.a();
                case 5:
                    return new com.google.android.exoplayer2.text.g.a(format.i);
                case 6:
                case 7:
                    return new com.google.android.exoplayer2.text.a.a(format.g, format.A);
                case '\b':
                    return new c(format.A, format.i);
                case '\t':
                    return new com.google.android.exoplayer2.text.b.a(format.i);
                case '\n':
                    return new com.google.android.exoplayer2.text.c.a();
                default:
                    throw new IllegalArgumentException("Attempted to create decoder for unsupported format");
            }
        }
    }
}
