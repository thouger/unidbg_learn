package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCache<T> extends a<T, T> implements v<T> {
    static final CacheDisposable[] e = new CacheDisposable[0];
    static final CacheDisposable[] f = new CacheDisposable[0];
    final AtomicBoolean b;
    final int c;
    final AtomicReference<CacheDisposable<T>[]> d;
    volatile long g;
    final a<T> h;
    a<T> i;
    int j;
    Throwable k;
    volatile boolean l;

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        CacheDisposable cacheDisposable = new CacheDisposable(vVar, this);
        vVar.onSubscribe(cacheDisposable);
        a(cacheDisposable);
        if (this.b.get() || !this.b.compareAndSet(false, true)) {
            c(cacheDisposable);
        } else {
            this.a.subscribe(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(CacheDisposable<T> cacheDisposable) {
        CacheDisposable<T>[] cacheDisposableArr;
        CacheDisposable<T>[] cacheDisposableArr2;
        do {
            cacheDisposableArr = this.d.get();
            if (cacheDisposableArr != f) {
                int length = cacheDisposableArr.length;
                cacheDisposableArr2 = new CacheDisposable[(length + 1)];
                System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr2, 0, length);
                cacheDisposableArr2[length] = cacheDisposable;
            } else {
                return;
            }
        } while (!this.d.compareAndSet(cacheDisposableArr, cacheDisposableArr2));
    }

    /* access modifiers changed from: package-private */
    public void b(CacheDisposable<T> cacheDisposable) {
        CacheDisposable<T>[] cacheDisposableArr;
        CacheDisposable<T>[] cacheDisposableArr2;
        do {
            cacheDisposableArr = this.d.get();
            int length = cacheDisposableArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (cacheDisposableArr[i2] == cacheDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        cacheDisposableArr2 = e;
                    } else {
                        CacheDisposable<T>[] cacheDisposableArr3 = new CacheDisposable[(length - 1)];
                        System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr3, 0, i);
                        System.arraycopy(cacheDisposableArr, i + 1, cacheDisposableArr3, i, (length - i) - 1);
                        cacheDisposableArr2 = cacheDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.d.compareAndSet(cacheDisposableArr, cacheDisposableArr2));
    }

    /* access modifiers changed from: package-private */
    public void c(CacheDisposable<T> cacheDisposable) {
        if (cacheDisposable.getAndIncrement() == 0) {
            long j = cacheDisposable.index;
            int i = cacheDisposable.offset;
            a<T> aVar = cacheDisposable.node;
            v<? super T> vVar = cacheDisposable.downstream;
            int i2 = this.c;
            int i3 = 1;
            while (!cacheDisposable.disposed) {
                boolean z = this.l;
                boolean z2 = this.g == j;
                if (z && z2) {
                    cacheDisposable.node = null;
                    Throwable th = this.k;
                    if (th != null) {
                        vVar.onError(th);
                        return;
                    } else {
                        vVar.onComplete();
                        return;
                    }
                } else if (!z2) {
                    if (i == i2) {
                        aVar = aVar.b;
                        i = 0;
                    }
                    vVar.onNext(aVar.a[i]);
                    i++;
                    j++;
                } else {
                    cacheDisposable.index = j;
                    cacheDisposable.offset = i;
                    cacheDisposable.node = aVar;
                    i3 = cacheDisposable.addAndGet(-i3);
                    if (i3 == 0) {
                        return;
                    }
                }
            }
            cacheDisposable.node = null;
        }
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        int i = this.j;
        if (i == this.c) {
            a<T> aVar = new a<>(i);
            aVar.a[0] = t;
            this.j = 1;
            this.i.b = aVar;
            this.i = aVar;
        } else {
            this.i.a[i] = t;
            this.j = i + 1;
        }
        this.g++;
        for (CacheDisposable<T> cacheDisposable : this.d.get()) {
            c((CacheDisposable) cacheDisposable);
        }
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        this.k = th;
        this.l = true;
        for (CacheDisposable<T> cacheDisposable : this.d.getAndSet(f)) {
            c((CacheDisposable) cacheDisposable);
        }
    }

    @Override // io.reactivex.v
    public void onComplete() {
        this.l = true;
        for (CacheDisposable<T> cacheDisposable : this.d.getAndSet(f)) {
            c((CacheDisposable) cacheDisposable);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class CacheDisposable<T> extends AtomicInteger implements b {
        private static final long serialVersionUID = 6770240836423125754L;
        volatile boolean disposed;
        final v<? super T> downstream;
        long index;
        a<T> node;
        int offset;
        final ObservableCache<T> parent;

        CacheDisposable(v<? super T> vVar, ObservableCache<T> observableCache) {
            this.downstream = vVar;
            this.parent = observableCache;
            this.node = observableCache.h;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!this.disposed) {
                this.disposed = true;
                this.parent.b((CacheDisposable) this);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.disposed;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class a<T> {
        final T[] a;
        volatile a<T> b;

        a(int i) {
            this.a = (T[]) new Object[i];
        }
    }
}
