package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.offline.b;
import com.google.android.exoplayer2.offline.c;
import com.google.android.exoplayer2.upstream.q;
import java.util.Collections;
import java.util.List;

/* compiled from: DefaultHlsPlaylistParserFactory */
public final class a implements g {
    private final List<c> a;

    public a() {
        this(Collections.emptyList());
    }

    public a(List<c> list) {
        this.a = list;
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.g
    public q.a<e> a() {
        return new b(new f(), this.a);
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.g
    public q.a<e> a(c cVar) {
        return new b(new f(cVar), this.a);
    }
}
