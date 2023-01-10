package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.disposables.b;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleCache<T> extends x<T> implements z<T> {
    static final CacheDisposable[] a = new CacheDisposable[0];
    static final CacheDisposable[] b = new CacheDisposable[0];
    final ab<? extends T> c;
    final AtomicInteger d;
    final AtomicReference<CacheDisposable<T>[]> e;
    T f;
    Throwable g;

    @Override // io.reactivex.z
    public void onSubscribe(b bVar) {
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        CacheDisposable cacheDisposable = new CacheDisposable(zVar, this);
        zVar.onSubscribe(cacheDisposable);
        if (a(cacheDisposable)) {
            if (cacheDisposable.isDisposed()) {
                b(cacheDisposable);
            }
            if (this.d.getAndIncrement() == 0) {
                this.c.a(this);
                return;
            }
            return;
        }
        Throwable th = this.g;
        if (th != null) {
            zVar.onError(th);
        } else {
            zVar.onSuccess(this.f);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(CacheDisposable<T> cacheDisposable) {
        CacheDisposable<T>[] cacheDisposableArr;
        CacheDisposable<T>[] cacheDisposableArr2;
        do {
            cacheDisposableArr = this.e.get();
            if (cacheDisposableArr == b) {
                return false;
            }
            int length = cacheDisposableArr.length;
            cacheDisposableArr2 = new CacheDisposable[(length + 1)];
            System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr2, 0, length);
            cacheDisposableArr2[length] = cacheDisposable;
        } while (!this.e.compareAndSet(cacheDisposableArr, cacheDisposableArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(CacheDisposable<T> cacheDisposable) {
        CacheDisposable<T>[] cacheDisposableArr;
        CacheDisposable<T>[] cacheDisposableArr2;
        do {
            cacheDisposableArr = this.e.get();
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
                        cacheDisposableArr2 = a;
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
        } while (!this.e.compareAndSet(cacheDisposableArr, cacheDisposableArr2));
    }

    @Override // io.reactivex.z
    public void onSuccess(T t) {
        this.f = t;
        CacheDisposable<T>[] andSet = this.e.getAndSet(b);
        for (CacheDisposable<T> cacheDisposable : andSet) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.downstream.onSuccess(t);
            }
        }
    }

    @Override // io.reactivex.z
    public void onError(Throwable th) {
        this.g = th;
        CacheDisposable<T>[] andSet = this.e.getAndSet(b);
        for (CacheDisposable<T> cacheDisposable : andSet) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.downstream.onError(th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class CacheDisposable<T> extends AtomicBoolean implements b {
        private static final long serialVersionUID = 7514387411091976596L;
        final z<? super T> downstream;
        final SingleCache<T> parent;

        CacheDisposable(z<? super T> zVar, SingleCache<T> singleCache) {
            this.downstream = zVar;
            this.parent = singleCache;
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.parent.b(this);
            }
        }
    }
}
