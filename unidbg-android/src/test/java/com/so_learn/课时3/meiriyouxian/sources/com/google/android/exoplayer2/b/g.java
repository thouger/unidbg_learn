package com.google.android.exoplayer2.b;

import com.google.android.exoplayer2.b.e;
import com.google.android.exoplayer2.b.f;
import com.google.android.exoplayer2.util.a;
import java.lang.Exception;
import java.util.ArrayDeque;

/* compiled from: SimpleDecoder */
public abstract class g<I extends e, O extends f, E extends Exception> implements c<I, O, E> {
    private final Thread a;
    private final Object b = new Object();
    private final ArrayDeque<I> c = new ArrayDeque<>();
    private final ArrayDeque<O> d = new ArrayDeque<>();
    private final I[] e;
    private final O[] f;
    private int g;
    private int h;
    private I i;
    private E j;
    private boolean k;
    private boolean l;
    private int m;

    /* access modifiers changed from: protected */
    public abstract E a(I i, O o, boolean z);

    /* access modifiers changed from: protected */
    public abstract E a(Throwable th);

    /* access modifiers changed from: protected */
    public abstract I g();

    /* access modifiers changed from: protected */
    public abstract O h();

    @Override // com.google.android.exoplayer2.b.c
    public /* bridge */ /* synthetic */ void a(Object obj) throws Exception {
        a((g<I, O, E>) ((e) obj));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v3, resolved type: O extends com.google.android.exoplayer2.b.f[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: I extends com.google.android.exoplayer2.b.e[] */
    /* JADX WARN: Multi-variable type inference failed */
    protected g(I[] iArr, O[] oArr) {
        this.e = iArr;
        this.g = iArr.length;
        for (int i = 0; i < this.g; i++) {
            this.e[i] = g();
        }
        this.f = oArr;
        this.h = oArr.length;
        for (int i2 = 0; i2 < this.h; i2++) {
            this.f[i2] = h();
        }
        this.a = new AnonymousClass1();
        this.a.start();
    }

    /* compiled from: SimpleDecoder */
    /* renamed from: com.google.android.exoplayer2.b.g$1  reason: invalid class name */
    class AnonymousClass1 extends Thread {
        AnonymousClass1() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            g.this.k();
        }
    }

    /* access modifiers changed from: protected */
    public final void a(int i) {
        a.b(this.g == this.e.length);
        for (I i2 : this.e) {
            i2.e(i);
        }
    }

    /* renamed from: e */
    public final I a() throws Exception {
        I i;
        I i2;
        synchronized (this.b) {
            i();
            a.b(this.i == null);
            if (this.g == 0) {
                i = null;
            } else {
                I[] iArr = this.e;
                int i3 = this.g - 1;
                this.g = i3;
                i = iArr[i3];
            }
            this.i = i;
            i2 = this.i;
        }
        return i2;
    }

    public final void a(I i) throws Exception {
        synchronized (this.b) {
            i();
            a.a(i == this.i);
            this.c.addLast(i);
            j();
            this.i = null;
        }
    }

    /* renamed from: f */
    public final O b() throws Exception {
        synchronized (this.b) {
            i();
            if (this.d.isEmpty()) {
                return null;
            }
            return this.d.removeFirst();
        }
    }

    /* access modifiers changed from: protected */
    public void a(O o) {
        synchronized (this.b) {
            b((g<I, O, E>) o);
            j();
        }
    }

    @Override // com.google.android.exoplayer2.b.c
    public final void c() {
        synchronized (this.b) {
            this.k = true;
            this.m = 0;
            if (this.i != null) {
                b((g<I, O, E>) this.i);
                this.i = null;
            }
            while (!this.c.isEmpty()) {
                b((g<I, O, E>) this.c.removeFirst());
            }
            while (!this.d.isEmpty()) {
                this.d.removeFirst().e();
            }
        }
    }

    @Override // com.google.android.exoplayer2.b.c
    public void d() {
        synchronized (this.b) {
            this.l = true;
            this.b.notify();
        }
        try {
            this.a.join();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    private void i() throws Exception {
        E e = this.j;
        if (e != null) {
            throw e;
        }
    }

    private void j() {
        if (m()) {
            this.b.notify();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void k() {
        do {
            try {
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        } while (l());
    }

    private boolean l() throws InterruptedException {
        I removeFirst;
        O o;
        boolean z;
        synchronized (this.b) {
            while (!this.l && !m()) {
                this.b.wait();
            }
            if (this.l) {
                return false;
            }
            removeFirst = this.c.removeFirst();
            O[] oArr = this.f;
            int i = this.h - 1;
            this.h = i;
            o = oArr[i];
            z = this.k;
            this.k = false;
        }
        if (removeFirst.c()) {
            o.b(4);
        } else {
            if (removeFirst.af_()) {
                o.b(Integer.MIN_VALUE);
            }
            try {
                this.j = (E) a(removeFirst, o, z);
            } catch (RuntimeException e) {
                this.j = (E) a((Throwable) e);
            } catch (OutOfMemoryError e2) {
                this.j = (E) a((Throwable) e2);
            }
            if (this.j != null) {
                synchronized (this.b) {
                }
                return false;
            }
        }
        synchronized (this.b) {
            if (this.k) {
                o.e();
            } else if (o.af_()) {
                this.m++;
                o.e();
            } else {
                o.b = this.m;
                this.m = 0;
                this.d.addLast(o);
            }
            b((g<I, O, E>) removeFirst);
        }
        return true;
    }

    private boolean m() {
        return !this.c.isEmpty() && this.h > 0;
    }

    private void b(I i) {
        i.a();
        I[] iArr = this.e;
        int i2 = this.g;
        this.g = i2 + 1;
        iArr[i2] = i;
    }

    private void b(O o) {
        o.a();
        O[] oArr = this.f;
        int i = this.h;
        this.h = i + 1;
        oArr[i] = o;
    }
}
