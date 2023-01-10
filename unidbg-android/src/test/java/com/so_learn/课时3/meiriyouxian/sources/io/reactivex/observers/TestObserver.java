package io.reactivex.observers;

import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.v;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public class TestObserver<T> extends BaseTestConsumer<T, TestObserver<T>> implements c, b, m<T>, v<T>, z<T> {
    private final v<? super T> i;
    private final AtomicReference<b> j;
    private io.reactivex.internal.a.c<T> k;

    enum EmptyObserver implements v<Object> {
        INSTANCE;

        @Override // io.reactivex.v
        public void onComplete() {
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
        }

        @Override // io.reactivex.v
        public void onNext(Object obj) {
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
        }
    }

    @Override // io.reactivex.c
    public void onSubscribe(b bVar) {
        this.e = Thread.currentThread();
        if (bVar == null) {
            this.c.add(new NullPointerException("onSubscribe received a null Subscription"));
        } else if (!this.j.compareAndSet(null, bVar)) {
            bVar.dispose();
            if (this.j.get() != DisposableHelper.DISPOSED) {
                this.c.add(new IllegalStateException("onSubscribe received multiple subscriptions: " + bVar));
            }
        } else {
            if (this.g != 0 && (bVar instanceof io.reactivex.internal.a.c)) {
                this.k = (io.reactivex.internal.a.c) bVar;
                int requestFusion = this.k.requestFusion(this.g);
                this.h = requestFusion;
                if (requestFusion == 1) {
                    this.f = true;
                    this.e = Thread.currentThread();
                    while (true) {
                        try {
                            T poll = this.k.poll();
                            if (poll != null) {
                                this.b.add(poll);
                            } else {
                                this.d++;
                                this.j.lazySet(DisposableHelper.DISPOSED);
                                return;
                            }
                        } catch (Throwable th) {
                            this.c.add(th);
                            return;
                        }
                    }
                }
            }
            this.i.onSubscribe(bVar);
        }
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        if (!this.f) {
            this.f = true;
            if (this.j.get() == null) {
                this.c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        this.e = Thread.currentThread();
        if (this.h == 2) {
            while (true) {
                try {
                    T poll = this.k.poll();
                    if (poll != null) {
                        this.b.add(poll);
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    this.c.add(th);
                    this.k.dispose();
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

    @Override // io.reactivex.c
    public void onError(Throwable th) {
        if (!this.f) {
            this.f = true;
            if (this.j.get() == null) {
                this.c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.e = Thread.currentThread();
            if (th == null) {
                this.c.add(new NullPointerException("onError received a null Throwable"));
            } else {
                this.c.add(th);
            }
            this.i.onError(th);
        } finally {
            this.a.countDown();
        }
    }

    @Override // io.reactivex.c
    public void onComplete() {
        if (!this.f) {
            this.f = true;
            if (this.j.get() == null) {
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

    @Override // io.reactivex.disposables.b
    public final void dispose() {
        DisposableHelper.dispose(this.j);
    }

    @Override // io.reactivex.disposables.b
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(this.j.get());
    }

    @Override // io.reactivex.m
    public void onSuccess(T t) {
        onNext(t);
        onComplete();
    }
}
