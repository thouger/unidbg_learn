package com.google.android.exoplayer2.source.hls;

import android.util.SparseArray;
import com.google.android.exoplayer2.util.w;

/* compiled from: TimestampAdjusterProvider */
public final class m {
    private final SparseArray<w> a = new SparseArray<>();

    public w a(int i) {
        w wVar = (w) this.a.get(i);
        if (wVar != null) {
            return wVar;
        }
        w wVar2 = new w(Long.MAX_VALUE);
        this.a.put(i, wVar2);
        return wVar2;
    }

    public void a() {
        this.a.clear();
    }
}
