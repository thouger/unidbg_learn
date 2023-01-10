package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.e.a;
import io.reactivex.internal.a.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.q;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableMergeWithMaybe<T> extends a<T, T> {
    final o<? extends T> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        MergeWithObserver mergeWithObserver = new MergeWithObserver(vVar);
        vVar.onSubscribe(mergeWithObserver);
        this.a.subscribe(mergeWithObserver);
        this.b.a(mergeWithObserver.otherObserver);
    }

    static final class MergeWithObserver<T> extends AtomicInteger implements b, v<T> {
        static final int OTHER_STATE_CONSUMED_OR_EMPTY = 2;
        static final int OTHER_STATE_HAS_VALUE = 1;
        private static final long serialVersionUID = -4592979584110982903L;
        volatile boolean disposed;
        final v<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final AtomicReference<b> mainDisposable = new AtomicReference<>();
        volatile boolean mainDone;
        final OtherObserver<T> otherObserver = new OtherObserver<>(this);
        volatile int otherState;
        volatile g<T> queue;
        T singleItem;

        MergeWithObserver(v<? super T> vVar) {
            this.downstream = vVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.mainDisposable, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (compareAndSet(0, 1)) {
                this.downstream.onNext(t);
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                getOrCreateQueue().offer(t);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                DisposableHelper.dispose(this.otherObserver);
                drain();
                return;
            }
            a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.mainDone = true;
            drain();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.mainDisposable.get());
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.disposed = true;
            DisposableHelper.dispose(this.mainDisposable);
            DisposableHelper.dispose(this.otherObserver);
            if (getAndIncrement() == 0) {
                this.queue = null;
                this.singleItem = null;
            }
        }

        /* access modifiers changed from: package-private */
        public void otherSuccess(T t) {
            if (compareAndSet(0, 1)) {
                this.downstream.onNext(t);
                this.otherState = 2;
            } else {
                this.singleItem = t;
                this.otherState = 1;
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        /* access modifiers changed from: package-private */
        public void otherError(Throwable th) {
            if (this.error.addThrowable(th)) {
                DisposableHelper.dispose(this.mainDisposable);
                drain();
                return;
            }
            a.a(th);
        }

        /* access modifiers changed from: package-private */
        public void otherComplete() {
            this.otherState = 2;
            drain();
        }

        /* access modifiers changed from: package-private */
        public g<T> getOrCreateQueue() {
            g<T> gVar = this.queue;
            if (gVar != null) {
                return gVar;
            }
            io.reactivex.internal.queue.a aVar = new io.reactivex.internal.queue.a(q.c());
            this.queue = aVar;
            return aVar;
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: package-private */
        public void drainLoop() {
            v<? super T> vVar = this.downstream;
            int i = 1;
            while (!this.disposed) {
                if (this.error.get() != null) {
                    this.singleItem = null;
                    this.queue = null;
                    vVar.onError(this.error.terminate());
                    return;
                }
                int i2 = this.otherState;
                if (i2 == 1) {
                    T t = this.singleItem;
                    this.singleItem = null;
                    this.otherState = 2;
                    vVar.onNext(t);
                    i2 = 2;
                }
                boolean z = this.mainDone;
                g<T> gVar = this.queue;
                Object poll = gVar != null ? gVar.poll() : null;
                boolean z2 = poll == null;
                if (z && z2 && i2 == 2) {
                    this.queue = null;
                    vVar.onComplete();
                    return;
                } else if (z2) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    vVar.onNext(poll);
                }
            }
            this.singleItem = null;
            this.queue = null;
        }

        static final class OtherObserver<T> extends AtomicReference<b> implements m<T> {
            private static final long serialVersionUID = -2935427570954647017L;
            final MergeWithObserver<T> parent;

            OtherObserver(MergeWithObserver<T> mergeWithObserver) {
                this.parent = mergeWithObserver;
            }

            @Override // io.reactivex.m
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.m
            public void onSuccess(T t) {
                this.parent.otherSuccess(t);
            }

            @Override // io.reactivex.m
            public void onError(Throwable th) {
                this.parent.otherError(th);
            }

            @Override // io.reactivex.m
            public void onComplete() {
                this.parent.otherComplete();
            }
        }
    }
}
