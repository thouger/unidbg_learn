package io.reactivex.internal.operators.observable;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.q;
import io.reactivex.v;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableFlatMapSingle<T, R> extends a<T, R> {
    final h<? super T, ? extends ab<? extends R>> b;
    final boolean c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super R> vVar) {
        this.a.subscribe(new FlatMapSingleObserver(vVar, this.b, this.c));
    }

    static final class FlatMapSingleObserver<T, R> extends AtomicInteger implements b, v<T> {
        private static final long serialVersionUID = 8600231336733376951L;
        final AtomicInteger active = new AtomicInteger(1);
        volatile boolean cancelled;
        final boolean delayErrors;
        final v<? super R> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final h<? super T, ? extends ab<? extends R>> mapper;
        final AtomicReference<a<R>> queue = new AtomicReference<>();
        final io.reactivex.disposables.a set = new io.reactivex.disposables.a();
        b upstream;

        FlatMapSingleObserver(v<? super R> vVar, h<? super T, ? extends ab<? extends R>> hVar, boolean z) {
            this.downstream = vVar;
            this.mapper = hVar;
            this.delayErrors = z;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            try {
                ab abVar = (ab) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null SingleSource");
                this.active.getAndIncrement();
                InnerObserver innerObserver = new InnerObserver();
                if (!this.cancelled && this.set.a(innerObserver)) {
                    abVar.a(innerObserver);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.active.decrementAndGet();
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.set.dispose();
                }
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.set.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void innerSuccess(FlatMapSingleObserver<T, R>.InnerObserver innerObserver, R r) {
            this.set.c(innerObserver);
            if (get() == 0) {
                boolean z = true;
                if (compareAndSet(0, 1)) {
                    this.downstream.onNext(r);
                    if (this.active.decrementAndGet() != 0) {
                        z = false;
                    }
                    a<R> aVar = this.queue.get();
                    if (!z || (aVar != null && !aVar.isEmpty())) {
                        if (decrementAndGet() == 0) {
                            return;
                        }
                        drainLoop();
                    }
                    Throwable terminate = this.errors.terminate();
                    if (terminate != null) {
                        this.downstream.onError(terminate);
                        return;
                    } else {
                        this.downstream.onComplete();
                        return;
                    }
                }
            }
            a<R> orCreateQueue = getOrCreateQueue();
            synchronized (orCreateQueue) {
                orCreateQueue.offer(r);
            }
            this.active.decrementAndGet();
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        /* access modifiers changed from: package-private */
        public a<R> getOrCreateQueue() {
            a<R> aVar;
            do {
                a<R> aVar2 = this.queue.get();
                if (aVar2 != null) {
                    return aVar2;
                }
                aVar = new a<>(q.c());
            } while (!this.queue.compareAndSet(null, aVar));
            return aVar;
        }

        /* access modifiers changed from: package-private */
        public void innerError(FlatMapSingleObserver<T, R>.InnerObserver innerObserver, Throwable th) {
            this.set.c(innerObserver);
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.upstream.dispose();
                    this.set.dispose();
                }
                this.active.decrementAndGet();
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            a<R> aVar = this.queue.get();
            if (aVar != null) {
                aVar.clear();
            }
        }

        /* access modifiers changed from: package-private */
        public void drainLoop() {
            v<? super R> vVar = this.downstream;
            AtomicInteger atomicInteger = this.active;
            AtomicReference<a<R>> atomicReference = this.queue;
            int i = 1;
            while (!this.cancelled) {
                if (this.delayErrors || this.errors.get() == null) {
                    boolean z = false;
                    boolean z2 = atomicInteger.get() == 0;
                    a<R> aVar = atomicReference.get();
                    Object poll = aVar != null ? aVar.poll() : null;
                    if (poll == null) {
                        z = true;
                    }
                    if (z2 && z) {
                        Throwable terminate = this.errors.terminate();
                        if (terminate != null) {
                            vVar.onError(terminate);
                            return;
                        } else {
                            vVar.onComplete();
                            return;
                        }
                    } else if (z) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        vVar.onNext(poll);
                    }
                } else {
                    Throwable terminate2 = this.errors.terminate();
                    clear();
                    vVar.onError(terminate2);
                    return;
                }
            }
            clear();
        }

        final class InnerObserver extends AtomicReference<b> implements b, z<R> {
            private static final long serialVersionUID = -502562646270949838L;

            InnerObserver() {
            }

            @Override // io.reactivex.z
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.z
            public void onSuccess(R r) {
                FlatMapSingleObserver.this.innerSuccess(this, r);
            }

            @Override // io.reactivex.z
            public void onError(Throwable th) {
                FlatMapSingleObserver.this.innerError(this, th);
            }

            @Override // io.reactivex.disposables.b
            public boolean isDisposed() {
                return DisposableHelper.isDisposed(get());
            }

            @Override // io.reactivex.disposables.b
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
