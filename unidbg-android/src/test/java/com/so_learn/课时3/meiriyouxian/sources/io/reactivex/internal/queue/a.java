package io.reactivex.internal.queue;

import io.reactivex.internal.a.g;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: SpscLinkedArrayQueue */
public final class a<T> implements g<T> {
    static final int a = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final Object j = new Object();
    final AtomicLong b = new AtomicLong();
    int c;
    long d;
    final int e;
    AtomicReferenceArray<Object> f;
    final int g;
    AtomicReferenceArray<Object> h;
    final AtomicLong i = new AtomicLong();

    private static int b(int i) {
        return i;
    }

    public a(int i) {
        int a2 = io.reactivex.internal.util.g.a(Math.max(8, i));
        int i2 = a2 - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(a2 + 1);
        this.f = atomicReferenceArray;
        this.e = i2;
        a(a2);
        this.h = atomicReferenceArray;
        this.g = i2;
        this.d = (long) (i2 - 1);
        a(0L);
    }

    @Override // io.reactivex.internal.a.h
    public boolean offer(T t) {
        if (t != null) {
            AtomicReferenceArray<Object> atomicReferenceArray = this.f;
            long e = e();
            int i = this.e;
            int a2 = a(e, i);
            if (e < this.d) {
                return a(atomicReferenceArray, t, e, a2);
            }
            long j2 = ((long) this.c) + e;
            if (b(atomicReferenceArray, a(j2, i)) == null) {
                this.d = j2 - 1;
                return a(atomicReferenceArray, t, e, a2);
            } else if (b(atomicReferenceArray, a(1 + e, i)) == null) {
                return a(atomicReferenceArray, t, e, a2);
            } else {
                a(atomicReferenceArray, e, a2, t, (long) i);
                return true;
            }
        } else {
            throw new NullPointerException("Null is not a valid element");
        }
    }

    private boolean a(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j2, int i) {
        a(atomicReferenceArray, i, t);
        a(j2 + 1);
        return true;
    }

    private void a(AtomicReferenceArray<Object> atomicReferenceArray, long j2, int i, T t, long j3) {
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.f = atomicReferenceArray2;
        this.d = (j3 + j2) - 1;
        a(atomicReferenceArray2, i, t);
        a(atomicReferenceArray, atomicReferenceArray2);
        a(atomicReferenceArray, i, j);
        a(j2 + 1);
    }

    private void a(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        a(atomicReferenceArray, b(atomicReferenceArray.length() - 1), atomicReferenceArray2);
    }

    private AtomicReferenceArray<Object> a(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        int b = b(i);
        AtomicReferenceArray<Object> atomicReferenceArray2 = (AtomicReferenceArray) b(atomicReferenceArray, b);
        a(atomicReferenceArray, b, (Object) null);
        return atomicReferenceArray2;
    }

    @Override // io.reactivex.internal.a.g, io.reactivex.internal.a.h
    public T poll() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.h;
        long f = f();
        int i = this.g;
        int a2 = a(f, i);
        T t = (T) b(atomicReferenceArray, a2);
        boolean z = t == j;
        if (t != null && !z) {
            a(atomicReferenceArray, a2, (Object) null);
            b(f + 1);
            return t;
        } else if (z) {
            return (T) a(a(atomicReferenceArray, i + 1), f, i);
        } else {
            return null;
        }
    }

    private T a(AtomicReferenceArray<Object> atomicReferenceArray, long j2, int i) {
        this.h = atomicReferenceArray;
        int a2 = a(j2, i);
        T t = (T) b(atomicReferenceArray, a2);
        if (t != null) {
            a(atomicReferenceArray, a2, (Object) null);
            b(j2 + 1);
        }
        return t;
    }

    public T a() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.h;
        long f = f();
        int i = this.g;
        T t = (T) b(atomicReferenceArray, a(f, i));
        return t == j ? (T) b(a(atomicReferenceArray, i + 1), f, i) : t;
    }

    private T b(AtomicReferenceArray<Object> atomicReferenceArray, long j2, int i) {
        this.h = atomicReferenceArray;
        return (T) b(atomicReferenceArray, a(j2, i));
    }

    @Override // io.reactivex.internal.a.h
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    public int b() {
        long d = d();
        while (true) {
            long c = c();
            long d2 = d();
            if (d == d2) {
                return (int) (c - d2);
            }
            d = d2;
        }
    }

    @Override // io.reactivex.internal.a.h
    public boolean isEmpty() {
        return c() == d();
    }

    private void a(int i) {
        this.c = Math.min(i / 4, a);
    }

    private long c() {
        return this.b.get();
    }

    private long d() {
        return this.i.get();
    }

    private long e() {
        return this.b.get();
    }

    private long f() {
        return this.i.get();
    }

    private void a(long j2) {
        this.b.lazySet(j2);
    }

    private void b(long j2) {
        this.i.lazySet(j2);
    }

    private static int a(long j2, int i) {
        return b(((int) j2) & i);
    }

    private static void a(AtomicReferenceArray<Object> atomicReferenceArray, int i, Object obj) {
        atomicReferenceArray.lazySet(i, obj);
    }

    private static <E> Object b(AtomicReferenceArray<Object> atomicReferenceArray, int i) {
        return atomicReferenceArray.get(i);
    }

    public boolean a(T t, T t2) {
        AtomicReferenceArray<Object> atomicReferenceArray = this.f;
        long c = c();
        int i = this.e;
        long j2 = 2 + c;
        if (b(atomicReferenceArray, a(j2, i)) == null) {
            int a2 = a(c, i);
            a(atomicReferenceArray, a2 + 1, t2);
            a(atomicReferenceArray, a2, t);
            a(j2);
            return true;
        }
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.f = atomicReferenceArray2;
        int a3 = a(c, i);
        a(atomicReferenceArray2, a3 + 1, t2);
        a(atomicReferenceArray2, a3, t);
        a(atomicReferenceArray, atomicReferenceArray2);
        a(atomicReferenceArray, a3, j);
        a(j2);
        return true;
    }
}
