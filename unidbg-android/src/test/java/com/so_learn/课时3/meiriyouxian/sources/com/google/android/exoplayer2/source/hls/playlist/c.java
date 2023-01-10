package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: HlsMasterPlaylist */
public final class c extends e {
    public static final c a = new c("", Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, Collections.emptyList(), false, Collections.emptyMap());
    public final List<a> b;
    public final List<a> c;
    public final List<a> d;
    public final Format e;
    public final List<Format> f;
    public final Map<String, String> g;

    /* compiled from: HlsMasterPlaylist */
    public static final class a {
        public final String a;
        public final Format b;

        public static a a(String str) {
            return new a(str, Format.b("0", null, "application/x-mpegURL", null, null, -1, 0, null));
        }

        public a(String str, Format format) {
            this.a = str;
            this.b = format;
        }
    }

    public c(String str, List<String> list, List<a> list2, List<a> list3, List<a> list4, Format format, List<Format> list5, boolean z, Map<String, String> map) {
        super(str, list, z);
        this.b = Collections.unmodifiableList(list2);
        this.c = Collections.unmodifiableList(list3);
        this.d = Collections.unmodifiableList(list4);
        this.e = format;
        this.f = list5 != null ? Collections.unmodifiableList(list5) : null;
        this.g = Collections.unmodifiableMap(map);
    }

    /* renamed from: b */
    public c a(List<com.google.android.exoplayer2.offline.c> list) {
        return new c(this.n, this.o, a(this.b, 0, list), a(this.c, 1, list), a(this.d, 2, list), this.e, this.f, this.p, this.g);
    }

    public static c a(String str) {
        List singletonList = Collections.singletonList(a.a(str));
        List emptyList = Collections.emptyList();
        return new c(null, Collections.emptyList(), singletonList, emptyList, emptyList, null, null, false, Collections.emptyMap());
    }

    private static List<a> a(List<a> list, int i, List<com.google.android.exoplayer2.offline.c> list2) {
        ArrayList arrayList = new ArrayList(list2.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            a aVar = list.get(i2);
            int i3 = 0;
            while (true) {
                if (i3 >= list2.size()) {
                    break;
                }
                com.google.android.exoplayer2.offline.c cVar = list2.get(i3);
                if (cVar.b == i && cVar.c == i2) {
                    arrayList.add(aVar);
                    break;
                }
                i3++;
            }
        }
        return arrayList;
    }
}
