package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.offline.a;
import com.google.android.exoplayer2.upstream.q;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* compiled from: FilteringManifestParser */
public final class b<T extends a<T>> implements q.a<T> {
    private final q.a<T> a;
    private final List<c> b;

    public b(q.a<T> aVar, List<c> list) {
        this.a = aVar;
        this.b = list;
    }

    /* renamed from: a */
    public T b(Uri uri, InputStream inputStream) throws IOException {
        T t = (T) ((a) this.a.b(uri, inputStream));
        List<c> list = this.b;
        return (list == null || list.isEmpty()) ? t : (T) ((a) t.a(this.b));
    }
}
