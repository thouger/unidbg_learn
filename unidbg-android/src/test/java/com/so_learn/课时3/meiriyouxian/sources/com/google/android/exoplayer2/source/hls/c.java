package com.google.android.exoplayer2.source.hls;

import android.media.MediaFormat;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.f.a;
import com.google.android.exoplayer2.extractor.f.z;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.mp4.e;
import com.google.android.exoplayer2.util.l;
import com.google.android.exoplayer2.util.w;
import java.io.EOFException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: DefaultHlsExtractorFactory */
public final class c implements f {
    private final int b;

    public c() {
        this(0);
    }

    public c(int i) {
        this.b = i;
    }

    @Override // com.google.android.exoplayer2.source.hls.f
    public Pair<g, Boolean> a(g gVar, Uri uri, Format format, List<Format> list, DrmInitData drmInitData, w wVar, Map<String, List<String>> map, h hVar) throws InterruptedException, IOException {
        List<Format> list2;
        if (gVar == null) {
            g a = a(uri, format, list, drmInitData, wVar);
            hVar.a();
            if (a(a, hVar)) {
                return a(a);
            }
            if (!(a instanceof n)) {
                n nVar = new n(format.z, wVar);
                if (a(nVar, hVar)) {
                    return a(nVar);
                }
            }
            if (!(a instanceof com.google.android.exoplayer2.extractor.f.c)) {
                com.google.android.exoplayer2.extractor.f.c cVar = new com.google.android.exoplayer2.extractor.f.c();
                if (a(cVar, hVar)) {
                    return a(cVar);
                }
            }
            if (!(a instanceof a)) {
                a aVar = new a();
                if (a(aVar, hVar)) {
                    return a(aVar);
                }
            }
            if (!(a instanceof com.google.android.exoplayer2.extractor.c.c)) {
                com.google.android.exoplayer2.extractor.c.c cVar2 = new com.google.android.exoplayer2.extractor.c.c(0, 0);
                if (a(cVar2, hVar)) {
                    return a(cVar2);
                }
            }
            if (!(a instanceof e)) {
                if (list != null) {
                    list2 = list;
                } else {
                    list2 = Collections.emptyList();
                }
                e eVar = new e(0, wVar, null, drmInitData, list2);
                if (a(eVar, hVar)) {
                    return a(eVar);
                }
            }
            if (!(a instanceof z)) {
                z a2 = a(this.b, format, list, wVar);
                if (a(a2, hVar)) {
                    return a(a2);
                }
            }
            return a(a);
        } else if ((gVar instanceof z) || (gVar instanceof e)) {
            return a(gVar);
        } else {
            if (gVar instanceof n) {
                return a(new n(format.z, wVar));
            }
            if (gVar instanceof com.google.android.exoplayer2.extractor.f.c) {
                return a(new com.google.android.exoplayer2.extractor.f.c());
            }
            if (gVar instanceof a) {
                return a(new a());
            }
            if (gVar instanceof com.google.android.exoplayer2.extractor.c.c) {
                return a(new com.google.android.exoplayer2.extractor.c.c());
            }
            throw new IllegalArgumentException("Unexpected previousExtractor type: " + gVar.getClass().getSimpleName());
        }
    }

    private g a(Uri uri, Format format, List<Format> list, DrmInitData drmInitData, w wVar) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment == null) {
            lastPathSegment = "";
        }
        if ("text/vtt".equals(format.g) || lastPathSegment.endsWith(".webvtt") || lastPathSegment.endsWith(".vtt")) {
            return new n(format.z, wVar);
        }
        if (lastPathSegment.endsWith(".aac")) {
            return new com.google.android.exoplayer2.extractor.f.c();
        }
        if (lastPathSegment.endsWith(".ac3") || lastPathSegment.endsWith(".ec3")) {
            return new a();
        }
        if (lastPathSegment.endsWith(".mp3")) {
            return new com.google.android.exoplayer2.extractor.c.c(0, 0);
        }
        if (!lastPathSegment.endsWith(".mp4") && !lastPathSegment.startsWith(".m4", lastPathSegment.length() - 4) && !lastPathSegment.startsWith(".mp4", lastPathSegment.length() - 5) && !lastPathSegment.startsWith(".cmf", lastPathSegment.length() - 5)) {
            return a(this.b, format, list, wVar);
        }
        if (list == null) {
            list = Collections.emptyList();
        }
        return new e(0, wVar, null, drmInitData, list);
    }

    private static z a(int i, Format format, List<Format> list, w wVar) {
        int i2 = i | 16;
        if (list != null) {
            i2 |= 32;
        } else {
            list = Collections.singletonList(Format.a(null, "application/cea-608", 0, null));
        }
        String str = format.d;
        if (!TextUtils.isEmpty(str)) {
            if (!MediaFormat.MIMETYPE_AUDIO_AAC.equals(l.e(str))) {
                i2 |= 2;
            }
            if (!MediaFormat.MIMETYPE_VIDEO_AVC.equals(l.d(str))) {
                i2 |= 4;
            }
        }
        return new z(2, wVar, new com.google.android.exoplayer2.extractor.f.e(i2, list));
    }

    private static Pair<g, Boolean> a(g gVar) {
        return new Pair<>(gVar, Boolean.valueOf((gVar instanceof com.google.android.exoplayer2.extractor.f.c) || (gVar instanceof a) || (gVar instanceof com.google.android.exoplayer2.extractor.c.c)));
    }

    /* JADX INFO: finally extract failed */
    private static boolean a(g gVar, h hVar) throws InterruptedException, IOException {
        try {
            boolean a = gVar.a(hVar);
            hVar.a();
            return a;
        } catch (EOFException unused) {
            hVar.a();
            return false;
        } catch (Throwable th) {
            hVar.a();
            throw th;
        }
    }
}
