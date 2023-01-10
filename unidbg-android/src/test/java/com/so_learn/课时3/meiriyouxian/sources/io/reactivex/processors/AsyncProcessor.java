package io.reactivex.processors;

import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class AsyncProcessor<T> extends a<T> {
    static final AsyncSubscription[] b = new AsyncSubscription[0];
    static final AsyncSubscription[] c = new AsyncSubscription[0];
    final AtomicReference<AsyncSubscription<T>[]> d;
    Throwable e;
    T f;

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        if (this.d.get() == c) {
            dVar.cancel();
        } else {
            dVar.request(Long.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        a.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.d.get() != c) {
            this.f = t;
        }
    }

    public void onError(Throwable th) {
        a.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        AsyncSubscription<T>[] asyncSubscriptionArr = this.d.get();
        AsyncSubscription<T>[] asyncSubscriptionArr2 = c;
        if (asyncSubscriptionArr == asyncSubscriptionArr2) {
            io.reactivex.e.a.a(th);
            return;
        }
        this.f = null;
        this.e = th;
        for (AsyncSubscription<T> asyncSubscription : this.d.getAndSet(asyncSubscriptionArr2)) {
            asyncSubscription.onError(th);
        }
    }

    public void onComplete() {
        AsyncSubscription<T>[] asyncSubscriptionArr = this.d.get();
        AsyncSubscription<T>[] asyncSubscriptionArr2 = c;
        if (asyncSubscriptionArr != asyncSubscriptionArr2) {
            T t = this.f;
            AsyncSubscription<T>[] andSet = this.d.getAndSet(asyncSubscriptionArr2);
            int i = 0;
            if (t == null) {
                int length = andSet.length;
                while (i < length) {
                    andSet[i].onComplete();
                    i++;
                }
                return;
            }
            int length2 = andSet.length;
            while (i < length2) {
                andSet[i].complete(t);
                i++;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        AsyncSubscription asyncSubscription = new AsyncSubscription(cVar, this);
        cVar.onSubscribe(asyncSubscription);
        if (!a(asyncSubscription)) {
            Throwable th = this.e;
            if (th != null) {
                cVar.onError(th);
                return;
            }
            T t = this.f;
            if (t != null) {
                asyncSubscription.complete(t);
            } else {
                asyncSubscription.onComplete();
            }
        } else if (asyncSubscription.isCancelled()) {
            b(asyncSubscription);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(AsyncSubscription<T> asyncSubscription) {
        AsyncSubscription<T>[] asyncSubscriptionArr;
        AsyncSubscription<T>[] asyncSubscriptionArr2;
        do {
            asyncSubscriptionArr = this.d.get();
            if (asyncSubscriptionArr == c) {
                return false;
            }
            int length = asyncSubscriptionArr.length;
            asyncSubscriptionArr2 = new AsyncSubscription[(length + 1)];
            System.arraycopy(asyncSubscriptionArr, 0, asyncSubscriptionArr2, 0, length);
            asyncSubscriptionArr2[length] = asyncSubscription;
        } while (!this.d.compareAndSet(asyncSubscriptionArr, asyncSubscriptionArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(AsyncSubscription<T> asyncSubscription) {
        AsyncSubscription<T>[] asyncSubscriptionArr;
        AsyncSubscription<T>[] asyncSubscriptionArr2;
        do {
            asyncSubscriptionArr = this.d.get();
            int length = asyncSubscriptionArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (asyncSubscriptionArr[i2] == asyncSubscription) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        asyncSubscriptionArr2 = b;
                    } else {
                        AsyncSubscription<T>[] asyncSubscriptionArr3 = new AsyncSubscription[(length - 1)];
                        System.arraycopy(asyncSubscriptionArr, 0, asyncSubscriptionArr3, 0, i);
                        System.arraycopy(asyncSubscriptionArr, i + 1, asyncSubscriptionArr3, i, (length - i) - 1);
                        asyncSubscriptionArr2 = asyncSubscriptionArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.d.compareAndSet(asyncSubscriptionArr, asyncSubscriptionArr2));
    }

    /* access modifiers changed from: package-private */
    public static final class AsyncSubscription<T> extends DeferredScalarSubscription<T> {
        private static final long serialVersionUID = 5629876084736248016L;
        final AsyncProcessor<T> parent;

        AsyncSubscription(c<? super T> cVar, AsyncProcessor<T> asyncProcessor) {
            super(cVar);
            this.parent = asyncProcessor;
        }

        @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription
        public void cancel() {
            if (super.tryCancel()) {
                this.parent.b(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void onComplete() {
            if (!isCancelled()) {
                this.downstream.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        public void onError(Throwable th) {
            if (isCancelled()) {
                io.reactivex.e.a.a(th);
            } else {
                this.downstream.onError(th);
            }
        }
    }
}
