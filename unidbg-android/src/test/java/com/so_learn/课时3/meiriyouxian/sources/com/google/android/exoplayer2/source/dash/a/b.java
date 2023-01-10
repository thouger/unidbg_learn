package com.google.android.exoplayer2.source.dash.a;

import android.net.Uri;
import com.google.android.exoplayer2.c;
import com.google.android.exoplayer2.offline.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* compiled from: DashManifest */
public class b implements a<b> {
    public final long a;
    public final long b;
    public final long c;
    public final boolean d;
    public final long e;
    public final long f;
    public final long g;
    public final long h;
    public final m i;
    public final Uri j;
    public final g k;
    private final List<f> l;

    public b(long j, long j2, long j3, boolean z, long j4, long j5, long j6, long j7, g gVar, m mVar, Uri uri, List<f> list) {
        this.a = j;
        this.b = j2;
        this.c = j3;
        this.d = z;
        this.e = j4;
        this.f = j5;
        this.g = j6;
        this.h = j7;
        this.k = gVar;
        this.i = mVar;
        this.j = uri;
        this.l = list == null ? Collections.emptyList() : list;
    }

    public final int a() {
        return this.l.size();
    }

    public final f a(int i) {
        return this.l.get(i);
    }

    public final long b(int i) {
        if (i != this.l.size() - 1) {
            return this.l.get(i + 1).b - this.l.get(i).b;
        }
        long j = this.b;
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return j - this.l.get(i).b;
    }

    public final long c(int i) {
        return c.b(b(i));
    }

    /* renamed from: b */
    public final b a(List<com.google.android.exoplayer2.offline.c> list) {
        long j;
        LinkedList linkedList = new LinkedList(list);
        Collections.sort(linkedList);
        linkedList.add(new com.google.android.exoplayer2.offline.c(-1, -1, -1));
        ArrayList arrayList = new ArrayList();
        long j2 = 0;
        int i = 0;
        while (true) {
            j = -9223372036854775807L;
            if (i >= a()) {
                break;
            }
            if (((com.google.android.exoplayer2.offline.c) linkedList.peek()).a != i) {
                long b = b(i);
                if (b != -9223372036854775807L) {
                    j2 += b;
                }
            } else {
                f a = a(i);
                arrayList.add(new f(a.a, a.b - j2, a(a.c, linkedList), a.d));
            }
            i++;
        }
        long j3 = this.b;
        if (j3 != -9223372036854775807L) {
            j = j3 - j2;
        }
        return new b(this.a, j, this.c, this.d, this.e, this.f, this.g, this.h, this.k, this.i, this.j, arrayList);
    }

    private static ArrayList<a> a(List<a> list, LinkedList<com.google.android.exoplayer2.offline.c> linkedList) {
        com.google.android.exoplayer2.offline.c poll = linkedList.poll();
        int i = poll.a;
        ArrayList<a> arrayList = new ArrayList<>();
        do {
            int i2 = poll.b;
            a aVar = list.get(i2);
            List<i> list2 = aVar.c;
            ArrayList arrayList2 = new ArrayList();
            do {
                arrayList2.add(list2.get(poll.c));
                poll = linkedList.poll();
                if (poll.a != i) {
                    break;
                }
            } while (poll.b == i2);
            arrayList.add(new a(aVar.a, aVar.b, arrayList2, aVar.d, aVar.e));
        } while (poll.a == i);
        linkedList.addFirst(poll);
        return arrayList;
    }
}
