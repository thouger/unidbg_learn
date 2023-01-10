package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.util.w;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: HlsExtractorFactory */
public interface f {
    public static final f a = new c();

    Pair<g, Boolean> a(g gVar, Uri uri, Format format, List<Format> list, DrmInitData drmInitData, w wVar, Map<String, List<String>> map, h hVar) throws InterruptedException, IOException;
}
