package io.reactivex.processors;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class PublishProcessor<T> extends a<T> {
    static final PublishSubscription[] b = new PublishSubscription[0];
    static final PublishSubscription[] c = new PublishSubscription[0];
    final AtomicReference<PublishSubscription<T>[]> d;
    Throwable e;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        PublishSubscription publishSubscription = new PublishSubscription(cVar, this);
        cVar.onSubscribe(publishSubscription);
        if (!a(publishSubscription)) {
            Throwable th = this.e;
            if (th != null) {
                cVar.onError(th);
            } else {
                cVar.onComplete();
            }
        } else if (publishSubscription.isCancelled()) {
            b(publishSubscription);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(PublishSubscription<T> publishSubscription) {
        PublishSubscription<T>[] publishSubscriptionArr;
        PublishSubscription<T>[] publishSubscriptionArr2;
        do {
            publishSubscriptionArr = this.d.get();
            if (publishSubscriptionArr == b) {
                return false;
            }
            int length = publishSubscriptionArr.length;
            publishSubscriptionArr2 = new PublishSubscription[(length + 1)];
            System.arraycopy(publishSubscriptionArr, 0, publishSubscriptionArr2, 0, length);
            publishSubscriptionArr2[length] = publishSubscription;
        } while (!this.d.compareAndSet(publishSubscriptionArr, publishSubscriptionArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(PublishSubscription<T> publishSubscription) {
        PublishSubscription<T>[] publishSubscriptionArr;
        PublishSubscription<T>[] publishSubscriptionArr2;
        do {
            publishSubscriptionArr = this.d.get();
            if (publishSubscriptionArr != b && publishSubscriptionArr != c) {
                int length = publishSubscriptionArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (publishSubscriptionArr[i2] == publishSubscription) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        publishSubscriptionArr2 = c;
                    } else {
                        PublishSubscription<T>[] publishSubscriptionArr3 = new PublishSubscription[(length - 1)];
                        System.arraycopy(publishSubscriptionArr, 0, publishSubscriptionArr3, 0, i);
                        System.arraycopy(publishSubscriptionArr, i + 1, publishSubscriptionArr3, i, (length - i) - 1);
                        publishSubscriptionArr2 = publishSubscriptionArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.d.compareAndSet(publishSubscriptionArr, publishSubscriptionArr2));
    }

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        if (this.d.get() == b) {
            dVar.cancel();
        } else {
            dVar.request(Long.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        a.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        for (PublishSubscription<T> publishSubscription : this.d.get()) {
            publishSubscription.onNext(t);
        }
    }

    public void onError(Throwable th) {
        a.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        PublishSubscription<T>[] publishSubscriptionArr = this.d.get();
        PublishSubscription<T>[] publishSubscriptionArr2 = b;
        if (publishSubscriptionArr == publishSubscriptionArr2) {
            io.reactivex.e.a.a(th);
            return;
        }
        this.e = th;
        for (PublishSubscription<T> publishSubscription : this.d.getAndSet(publishSubscriptionArr2)) {
            publishSubscription.onError(th);
        }
    }

    public void onComplete() {
        PublishSubscription<T>[] publishSubscriptionArr = this.d.get();
        PublishSubscription<T>[] publishSubscriptionArr2 = b;
        if (publishSubscriptionArr != publishSubscriptionArr2) {
            for (PublishSubscription<T> publishSubscription : this.d.getAndSet(publishSubscriptionArr2)) {
                publishSubscription.onComplete();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class PublishSubscription<T> extends AtomicLong implements d {
        private static final long serialVersionUID = 3562861878281475070L;
        final c<? super T> downstream;
        final PublishProcessor<T> parent;

        PublishSubscription(c<? super T> cVar, PublishProcessor<T> publishProcessor) {
            this.downstream = cVar;
            this.parent = publishProcessor;
        }

        public void onNext(T t) {
            long j = get();
            if (j != Long.MIN_VALUE) {
                if (j != 0) {
                    this.downstream.onNext(t);
                    b.d(this, 1);
                    return;
                }
                cancel();
                this.downstream.onError(new MissingBackpressureException("Could not emit value due to lack of requests"));
            }
        }

        public void onError(Throwable th) {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onError(th);
            } else {
                io.reactivex.e.a.a(th);
            }
        }

        public void onComplete() {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onComplete();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.b(this, j);
            }
        }

        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.b(this);
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }

        /* access modifiers changed from: package-private */
        public boolean isFull() {
            return get() == 0;
        }
    }
}
