package com.google.android.exoplayer2.source;

import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.upstream.s;
import com.google.android.exoplayer2.z;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: BaseMediaSource */
public abstract class a implements m {
    private final ArrayList<m.b> a = new ArrayList<>(1);
    private final n.a b = new n.a();
    private Looper c;
    private z d;
    private Object e;

    /* access modifiers changed from: protected */
    public abstract void a();

    /* access modifiers changed from: protected */
    public abstract void a(s sVar);

    /* access modifiers changed from: protected */
    public final void a(z zVar, Object obj) {
        this.d = zVar;
        this.e = obj;
        Iterator<m.b> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().onSourceInfoRefreshed(this, zVar, obj);
        }
    }

    /* access modifiers changed from: protected */
    public final n.a a(m.a aVar) {
        return this.b.a(0, aVar, 0);
    }

    /* access modifiers changed from: protected */
    public final n.a a(m.a aVar, long j) {
        com.google.android.exoplayer2.util.a.a(aVar != null);
        return this.b.a(0, aVar, j);
    }

    /* access modifiers changed from: protected */
    public final n.a a(int i, m.a aVar, long j) {
        return this.b.a(i, aVar, j);
    }

    @Override // com.google.android.exoplayer2.source.m
    public final void a(Handler handler, n nVar) {
        this.b.a(handler, nVar);
    }

    @Override // com.google.android.exoplayer2.source.m
    public final void a(n nVar) {
        this.b.a(nVar);
    }

    @Override // com.google.android.exoplayer2.source.m
    public final void a(m.b bVar, s sVar) {
        Looper myLooper = Looper.myLooper();
        Looper looper = this.c;
        com.google.android.exoplayer2.util.a.a(looper == null || looper == myLooper);
        this.a.add(bVar);
        if (this.c == null) {
            this.c = myLooper;
            a(sVar);
            return;
        }
        z zVar = this.d;
        if (zVar != null) {
            bVar.onSourceInfoRefreshed(this, zVar, this.e);
        }
    }

    @Override // com.google.android.exoplayer2.source.m
    public final void a(m.b bVar) {
        this.a.remove(bVar);
        if (this.a.isEmpty()) {
            this.c = null;
            this.d = null;
            this.e = null;
            a();
        }
    }
}
