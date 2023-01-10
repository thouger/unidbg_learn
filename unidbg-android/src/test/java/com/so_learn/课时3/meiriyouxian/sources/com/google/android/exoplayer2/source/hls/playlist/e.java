package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.offline.a;
import java.util.Collections;
import java.util.List;

/* compiled from: HlsPlaylist */
public abstract class e implements a<e> {
    public final String n;
    public final List<String> o;
    public final boolean p;

    protected e(String str, List<String> list, boolean z) {
        this.n = str;
        this.o = Collections.unmodifiableList(list);
        this.p = z;
    }
}
