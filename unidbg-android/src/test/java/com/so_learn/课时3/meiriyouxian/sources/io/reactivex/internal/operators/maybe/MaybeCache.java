package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.b;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeCache<T> extends k<T> implements m<T> {
    static final CacheDisposable[] a = new CacheDisposable[0];
    static final CacheDisposable[] b = new CacheDisposable[0];
    final AtomicReference<o<T>> c;
    final AtomicReference<CacheDisposable<T>[]> d;
    T e;
    Throwable f;

    @Override // io.reactivex.m
    public void onSubscribe(b bVar) {
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        CacheDisposable cacheDisposable = new CacheDisposable(mVar, this);
        mVar.onSubscribe(cacheDisposable);
        if (a(cacheDisposable)) {
            if (cacheDisposable.isDisposed()) {
                b(cacheDisposable);
                return;
            }
            o<T> andSet = this.c.getAndSet(null);
            if (andSet != null) {
                andSet.a(this);
            }
        } else if (!cacheDisposable.isDisposed()) {
            Throwable th = this.f;
            if (th != null) {
                mVar.onError(th);
                return;
            }
            T t = this.e;
            if (t != null) {
                mVar.onSuccess(t);
            } else {
                mVar.onComplete();
            }
        }
    }

    @Override // io.reactivex.m
    public void onSuccess(T t) {
        this.e = t;
        CacheDisposable<T>[] andSet = this.d.getAndSet(b);
        for (CacheDisposable<T> cacheDisposable : andSet) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.downstream.onSuccess(t);
            }
        }
    }

    @Override // io.reactivex.m
    public void onError(Throwable th) {
        this.f = th;
        CacheDisposable<T>[] andSet = this.d.getAndSet(b);
        for (CacheDisposable<T> cacheDisposable : andSet) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.downstream.onError(th);
            }
        }
    }

    @Override // io.reactivex.m
    public void onComplete() {
        CacheDisposable<T>[] andSet = this.d.getAndSet(b);
        for (CacheDisposable<T> cacheDisposable : andSet) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.downstream.onComplete();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(CacheDisposable<T> cacheDisposable) {
        CacheDisposable<T>[] cacheDisposableArr;
        CacheDisposable<T>[] cacheDisposableArr2;
        do {
            cacheDisposableArr = this.d.get();
            if (cacheDisposableArr == b) {
                return false;
            }
            int length = cacheDisposableArr.length;
            cacheDisposableArr2 = new CacheDisposable[(length + 1)];
            System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr2, 0, length);
            cacheDisposableArr2[length] = cacheDisposable;
        } while (!this.d.compareAndSet(cacheDisposableArr, cacheDisposableArr2));
        return true;
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
        } while (!this.d.compareAndSet(cacheDisposableArr, cacheDisposableArr2));
    }

    /* access modifiers changed from: package-private */
    public static final class CacheDisposable<T> extends AtomicReference<MaybeCache<T>> implements b {
        private static final long serialVersionUID = -5791853038359966195L;
        final m<? super T> downstream;

        CacheDisposable(m<? super T> mVar, MaybeCache<T> maybeCache) {
            super(maybeCache);
            this.downstream = mVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            MaybeCache<T> andSet = getAndSet(null);
            if (andSet != null) {
                andSet.b((CacheDisposable) this);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == null;
        }
    }
}
