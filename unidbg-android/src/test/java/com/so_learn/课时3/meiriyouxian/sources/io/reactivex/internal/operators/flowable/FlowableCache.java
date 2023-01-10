package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableCache<T> extends a<T, T> implements j<T> {
    static final CacheSubscription[] f = new CacheSubscription[0];
    static final CacheSubscription[] g = new CacheSubscription[0];
    final AtomicBoolean c;
    final int d;
    final AtomicReference<CacheSubscription<T>[]> e;
    volatile long h;
    final a<T> i;
    a<T> j;
    int k;
    Throwable l;
    volatile boolean m;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        CacheSubscription cacheSubscription = new CacheSubscription(cVar, this);
        cVar.onSubscribe(cacheSubscription);
        a(cacheSubscription);
        if (this.c.get() || !this.c.compareAndSet(false, true)) {
            c(cacheSubscription);
        } else {
            this.b.a((j) this);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(CacheSubscription<T> cacheSubscription) {
        CacheSubscription<T>[] cacheSubscriptionArr;
        CacheSubscription<T>[] cacheSubscriptionArr2;
        do {
            cacheSubscriptionArr = this.e.get();
            if (cacheSubscriptionArr != g) {
                int length = cacheSubscriptionArr.length;
                cacheSubscriptionArr2 = new CacheSubscription[(length + 1)];
                System.arraycopy(cacheSubscriptionArr, 0, cacheSubscriptionArr2, 0, length);
                cacheSubscriptionArr2[length] = cacheSubscription;
            } else {
                return;
            }
        } while (!this.e.compareAndSet(cacheSubscriptionArr, cacheSubscriptionArr2));
    }

    /* access modifiers changed from: package-private */
    public void b(CacheSubscription<T> cacheSubscription) {
        CacheSubscription<T>[] cacheSubscriptionArr;
        CacheSubscription<T>[] cacheSubscriptionArr2;
        do {
            cacheSubscriptionArr = this.e.get();
            int length = cacheSubscriptionArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (cacheSubscriptionArr[i2] == cacheSubscription) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        cacheSubscriptionArr2 = f;
                    } else {
                        CacheSubscription<T>[] cacheSubscriptionArr3 = new CacheSubscription[(length - 1)];
                        System.arraycopy(cacheSubscriptionArr, 0, cacheSubscriptionArr3, 0, i);
                        System.arraycopy(cacheSubscriptionArr, i + 1, cacheSubscriptionArr3, i, (length - i) - 1);
                        cacheSubscriptionArr2 = cacheSubscriptionArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.e.compareAndSet(cacheSubscriptionArr, cacheSubscriptionArr2));
    }

    /* access modifiers changed from: package-private */
    public void c(CacheSubscription<T> cacheSubscription) {
        if (cacheSubscription.getAndIncrement() == 0) {
            long j = cacheSubscription.index;
            int i = cacheSubscription.offset;
            a<T> aVar = cacheSubscription.node;
            AtomicLong atomicLong = cacheSubscription.requested;
            c<? super T> cVar = cacheSubscription.downstream;
            int i2 = this.d;
            a<T> aVar2 = aVar;
            int i3 = i;
            int i4 = 1;
            while (true) {
                boolean z = this.m;
                boolean z2 = this.h == j;
                if (!z || !z2) {
                    if (!z2) {
                        long j2 = atomicLong.get();
                        if (j2 == Long.MIN_VALUE) {
                            cacheSubscription.node = null;
                            return;
                        } else if (j2 != j) {
                            if (i3 == i2) {
                                aVar2 = aVar2.b;
                                i3 = 0;
                            }
                            cVar.onNext(aVar2.a[i3]);
                            i3++;
                            j++;
                        }
                    }
                    cacheSubscription.index = j;
                    cacheSubscription.offset = i3;
                    cacheSubscription.node = aVar2;
                    i4 = cacheSubscription.addAndGet(-i4);
                    if (i4 == 0) {
                        return;
                    }
                } else {
                    cacheSubscription.node = null;
                    Throwable th = this.l;
                    if (th != null) {
                        cVar.onError(th);
                        return;
                    } else {
                        cVar.onComplete();
                        return;
                    }
                }
            }
        }
    }

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        dVar.request(Long.MAX_VALUE);
    }

    public void onNext(T t) {
        int i = this.k;
        if (i == this.d) {
            a<T> aVar = new a<>(i);
            aVar.a[0] = t;
            this.k = 1;
            this.j.b = aVar;
            this.j = aVar;
        } else {
            this.j.a[i] = t;
            this.k = i + 1;
        }
        this.h++;
        for (CacheSubscription<T> cacheSubscription : this.e.get()) {
            c(cacheSubscription);
        }
    }

    public void onError(Throwable th) {
        if (this.m) {
            io.reactivex.e.a.a(th);
            return;
        }
        this.l = th;
        this.m = true;
        for (CacheSubscription<T> cacheSubscription : this.e.getAndSet(g)) {
            c(cacheSubscription);
        }
    }

    public void onComplete() {
        this.m = true;
        for (CacheSubscription<T> cacheSubscription : this.e.getAndSet(g)) {
            c(cacheSubscription);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class CacheSubscription<T> extends AtomicInteger implements d {
        private static final long serialVersionUID = 6770240836423125754L;
        final c<? super T> downstream;
        long index;
        a<T> node;
        int offset;
        final FlowableCache<T> parent;
        final AtomicLong requested = new AtomicLong();

        CacheSubscription(c<? super T> cVar, FlowableCache<T> flowableCache) {
            this.downstream = cVar;
            this.parent = flowableCache;
            this.node = flowableCache.i;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.b(this.requested, j);
                this.parent.c(this);
            }
        }

        public void cancel() {
            if (this.requested.getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.b(this);
            }
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
