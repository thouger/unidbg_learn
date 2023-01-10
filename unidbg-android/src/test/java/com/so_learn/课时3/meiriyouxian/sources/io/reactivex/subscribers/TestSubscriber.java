package io.reactivex.subscribers;

import io.reactivex.disposables.b;
import io.reactivex.internal.a.e;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.observers.BaseTestConsumer;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public class TestSubscriber<T> extends BaseTestConsumer<T, TestSubscriber<T>> implements b, j<T>, d {
    private final c<? super T> i;
    private volatile boolean j;
    private final AtomicReference<d> k;
    private final AtomicLong l;
    private e<T> m;

    enum EmptySubscriber implements j<Object> {
        INSTANCE;

        public void onComplete() {
        }

        public void onError(Throwable th) {
        }

        public void onNext(Object obj) {
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        this.e = Thread.currentThread();
        if (dVar == null) {
            this.c.add(new NullPointerException("onSubscribe received a null Subscription"));
        } else if (!this.k.compareAndSet(null, dVar)) {
            dVar.cancel();
            if (this.k.get() != SubscriptionHelper.CANCELLED) {
                this.c.add(new IllegalStateException("onSubscribe received multiple subscriptions: " + dVar));
            }
        } else {
            if (this.g != 0 && (dVar instanceof e)) {
                this.m = (e) dVar;
                int requestFusion = this.m.requestFusion(this.g);
                this.h = requestFusion;
                if (requestFusion == 1) {
                    this.f = true;
                    this.e = Thread.currentThread();
                    while (true) {
                        try {
                            T poll = this.m.poll();
                            if (poll != null) {
                                this.b.add(poll);
                            } else {
                                this.d++;
                                return;
                            }
                        } catch (Throwable th) {
                            this.c.add(th);
                            return;
                        }
                    }
                }
            }
            this.i.onSubscribe(dVar);
            long andSet = this.l.getAndSet(0);
            if (andSet != 0) {
                dVar.request(andSet);
            }
            a();
        }
    }

    public void onNext(T t) {
        if (!this.f) {
            this.f = true;
            if (this.k.get() == null) {
                this.c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        this.e = Thread.currentThread();
        if (this.h == 2) {
            while (true) {
                try {
                    T poll = this.m.poll();
                    if (poll != null) {
                        this.b.add(poll);
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    this.c.add(th);
                    this.m.cancel();
                    return;
                }
            }
        } else {
            this.b.add(t);
            if (t == null) {
                this.c.add(new NullPointerException("onNext received a null value"));
            }
            this.i.onNext(t);
        }
    }

    public void onError(Throwable th) {
        if (!this.f) {
            this.f = true;
            if (this.k.get() == null) {
                this.c.add(new NullPointerException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.e = Thread.currentThread();
            this.c.add(th);
            if (th == null) {
                this.c.add(new IllegalStateException("onError received a null Throwable"));
            }
            this.i.onError(th);
        } finally {
            this.a.countDown();
        }
    }

    public void onComplete() {
        if (!this.f) {
            this.f = true;
            if (this.k.get() == null) {
                this.c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.e = Thread.currentThread();
            this.d++;
            this.i.onComplete();
        } finally {
            this.a.countDown();
        }
    }

    public final void request(long j) {
        SubscriptionHelper.deferredRequest(this.k, this.l, j);
    }

    public final void cancel() {
        if (!this.j) {
            this.j = true;
            SubscriptionHelper.cancel(this.k);
        }
    }

    @Override // io.reactivex.disposables.b
    public final void dispose() {
        cancel();
    }

    @Override // io.reactivex.disposables.b
    public final boolean isDisposed() {
        return this.j;
    }
}
