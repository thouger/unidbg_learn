package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableCache extends a implements c {
    static final InnerCompletableCache[] a = new InnerCompletableCache[0];
    static final InnerCompletableCache[] b = new InnerCompletableCache[0];
    final e c;
    final AtomicReference<InnerCompletableCache[]> d;
    final AtomicBoolean e;
    Throwable f;

    @Override // io.reactivex.c
    public void onSubscribe(b bVar) {
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.a
    public void b(c cVar) {
        InnerCompletableCache innerCompletableCache = new InnerCompletableCache(cVar);
        cVar.onSubscribe(innerCompletableCache);
        if (a(innerCompletableCache)) {
            if (innerCompletableCache.isDisposed()) {
                b(innerCompletableCache);
            }
            if (this.e.compareAndSet(false, true)) {
                this.c.a(this);
                return;
            }
            return;
        }
        Throwable th = this.f;
        if (th != null) {
            cVar.onError(th);
        } else {
            cVar.onComplete();
        }
    }

    @Override // io.reactivex.c
    public void onError(Throwable th) {
        this.f = th;
        InnerCompletableCache[] andSet = this.d.getAndSet(b);
        for (InnerCompletableCache innerCompletableCache : andSet) {
            if (!innerCompletableCache.get()) {
                innerCompletableCache.downstream.onError(th);
            }
        }
    }

    @Override // io.reactivex.c
    public void onComplete() {
        InnerCompletableCache[] andSet = this.d.getAndSet(b);
        for (InnerCompletableCache innerCompletableCache : andSet) {
            if (!innerCompletableCache.get()) {
                innerCompletableCache.downstream.onComplete();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(InnerCompletableCache innerCompletableCache) {
        InnerCompletableCache[] innerCompletableCacheArr;
        InnerCompletableCache[] innerCompletableCacheArr2;
        do {
            innerCompletableCacheArr = this.d.get();
            if (innerCompletableCacheArr == b) {
                return false;
            }
            int length = innerCompletableCacheArr.length;
            innerCompletableCacheArr2 = new InnerCompletableCache[(length + 1)];
            System.arraycopy(innerCompletableCacheArr, 0, innerCompletableCacheArr2, 0, length);
            innerCompletableCacheArr2[length] = innerCompletableCache;
        } while (!this.d.compareAndSet(innerCompletableCacheArr, innerCompletableCacheArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(InnerCompletableCache innerCompletableCache) {
        InnerCompletableCache[] innerCompletableCacheArr;
        InnerCompletableCache[] innerCompletableCacheArr2;
        do {
            innerCompletableCacheArr = this.d.get();
            int length = innerCompletableCacheArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (innerCompletableCacheArr[i2] == innerCompletableCache) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        innerCompletableCacheArr2 = a;
                    } else {
                        InnerCompletableCache[] innerCompletableCacheArr3 = new InnerCompletableCache[(length - 1)];
                        System.arraycopy(innerCompletableCacheArr, 0, innerCompletableCacheArr3, 0, i);
                        System.arraycopy(innerCompletableCacheArr, i + 1, innerCompletableCacheArr3, i, (length - i) - 1);
                        innerCompletableCacheArr2 = innerCompletableCacheArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.d.compareAndSet(innerCompletableCacheArr, innerCompletableCacheArr2));
    }

    /* access modifiers changed from: package-private */
    public final class InnerCompletableCache extends AtomicBoolean implements b {
        private static final long serialVersionUID = 8943152917179642732L;
        final c downstream;

        InnerCompletableCache(c cVar) {
            this.downstream = cVar;
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (compareAndSet(false, true)) {
                CompletableCache.this.b(this);
            }
        }
    }
}
