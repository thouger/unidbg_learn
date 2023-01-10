package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableFlatMapMaybe<T, R> extends a<T, R> {
    final h<? super T, ? extends o<? extends R>> c;
    final boolean d;
    final int e;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super R> cVar) {
        this.b.a((j) new FlatMapMaybeSubscriber(cVar, this.c, this.d, this.e));
    }

    static final class FlatMapMaybeSubscriber<T, R> extends AtomicInteger implements j<T>, d {
        private static final long serialVersionUID = 8600231336733376951L;
        final AtomicInteger active = new AtomicInteger(1);
        volatile boolean cancelled;
        final boolean delayErrors;
        final c<? super R> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final h<? super T, ? extends o<? extends R>> mapper;
        final int maxConcurrency;
        final AtomicReference<a<R>> queue = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();
        final io.reactivex.disposables.a set = new io.reactivex.disposables.a();
        d upstream;

        FlatMapMaybeSubscriber(c<? super R> cVar, h<? super T, ? extends o<? extends R>> hVar, boolean z, int i) {
            this.downstream = cVar;
            this.mapper = hVar;
            this.delayErrors = z;
            this.maxConcurrency = i;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                int i = this.maxConcurrency;
                if (i == Integer.MAX_VALUE) {
                    dVar.request(Long.MAX_VALUE);
                } else {
                    dVar.request((long) i);
                }
            }
        }

        public void onNext(T t) {
            try {
                o oVar = (o) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                this.active.getAndIncrement();
                InnerObserver innerObserver = new InnerObserver();
                if (!this.cancelled && this.set.a(innerObserver)) {
                    oVar.a(innerObserver);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.upstream.cancel();
                onError(th);
            }
        }

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

        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            this.set.dispose();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerSuccess(FlatMapMaybeSubscriber<T, R>.InnerObserver innerObserver, R r) {
            this.set.c(innerObserver);
            if (get() == 0) {
                boolean z = true;
                if (compareAndSet(0, 1)) {
                    if (this.active.decrementAndGet() != 0) {
                        z = false;
                    }
                    if (this.requested.get() != 0) {
                        this.downstream.onNext(r);
                        a<R> aVar = this.queue.get();
                        if (!z || (aVar != null && !aVar.isEmpty())) {
                            b.c(this.requested, 1);
                            if (this.maxConcurrency != Integer.MAX_VALUE) {
                                this.upstream.request(1);
                            }
                        } else {
                            Throwable terminate = this.errors.terminate();
                            if (terminate != null) {
                                this.downstream.onError(terminate);
                                return;
                            } else {
                                this.downstream.onComplete();
                                return;
                            }
                        }
                    } else {
                        a<R> orCreateQueue = getOrCreateQueue();
                        synchronized (orCreateQueue) {
                            orCreateQueue.offer(r);
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                    drainLoop();
                }
            }
            a<R> orCreateQueue2 = getOrCreateQueue();
            synchronized (orCreateQueue2) {
                orCreateQueue2.offer(r);
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
                aVar = new a<>(g.a());
            } while (!this.queue.compareAndSet(null, aVar));
            return aVar;
        }

        /* access modifiers changed from: package-private */
        public void innerError(FlatMapMaybeSubscriber<T, R>.InnerObserver innerObserver, Throwable th) {
            this.set.c(innerObserver);
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.upstream.cancel();
                    this.set.dispose();
                } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                    this.upstream.request(1);
                }
                this.active.decrementAndGet();
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        /* access modifiers changed from: package-private */
        public void innerComplete(FlatMapMaybeSubscriber<T, R>.InnerObserver innerObserver) {
            this.set.c(innerObserver);
            if (get() == 0) {
                boolean z = true;
                if (compareAndSet(0, 1)) {
                    if (this.active.decrementAndGet() != 0) {
                        z = false;
                    }
                    a<R> aVar = this.queue.get();
                    if (!z || (aVar != null && !aVar.isEmpty())) {
                        if (this.maxConcurrency != Integer.MAX_VALUE) {
                            this.upstream.request(1);
                        }
                        if (decrementAndGet() != 0) {
                            drainLoop();
                            return;
                        }
                        return;
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
            this.active.decrementAndGet();
            if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.upstream.request(1);
            }
            drain();
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
            int i;
            boolean z;
            c<? super R> cVar = this.downstream;
            AtomicInteger atomicInteger = this.active;
            AtomicReference<a<R>> atomicReference = this.queue;
            int i2 = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (true) {
                    i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    z = false;
                    if (i == 0) {
                        break;
                    } else if (this.cancelled) {
                        clear();
                        return;
                    } else if (this.delayErrors || this.errors.get() == null) {
                        boolean z2 = atomicInteger.get() == 0;
                        a<R> aVar = atomicReference.get();
                        Object poll = aVar != null ? aVar.poll() : null;
                        boolean z3 = poll == null;
                        if (z2 && z3) {
                            Throwable terminate = this.errors.terminate();
                            if (terminate != null) {
                                cVar.onError(terminate);
                                return;
                            } else {
                                cVar.onComplete();
                                return;
                            }
                        } else if (z3) {
                            break;
                        } else {
                            cVar.onNext(poll);
                            j2++;
                        }
                    } else {
                        Throwable terminate2 = this.errors.terminate();
                        clear();
                        cVar.onError(terminate2);
                        return;
                    }
                }
                if (i == 0) {
                    if (this.cancelled) {
                        clear();
                        return;
                    } else if (this.delayErrors || this.errors.get() == null) {
                        boolean z4 = atomicInteger.get() == 0;
                        a<R> aVar2 = atomicReference.get();
                        if (aVar2 == null || aVar2.isEmpty()) {
                            z = true;
                        }
                        if (z4 && z) {
                            Throwable terminate3 = this.errors.terminate();
                            if (terminate3 != null) {
                                cVar.onError(terminate3);
                                return;
                            } else {
                                cVar.onComplete();
                                return;
                            }
                        }
                    } else {
                        Throwable terminate4 = this.errors.terminate();
                        clear();
                        cVar.onError(terminate4);
                        return;
                    }
                }
                if (j2 != 0) {
                    b.c(this.requested, j2);
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        this.upstream.request(j2);
                    }
                }
                i2 = addAndGet(-i2);
            } while (i2 != 0);
        }

        final class InnerObserver extends AtomicReference<io.reactivex.disposables.b> implements io.reactivex.disposables.b, m<R> {
            private static final long serialVersionUID = -502562646270949838L;

            InnerObserver() {
            }

            @Override // io.reactivex.m
            public void onSubscribe(io.reactivex.disposables.b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.m
            public void onSuccess(R r) {
                FlatMapMaybeSubscriber.this.innerSuccess(this, r);
            }

            @Override // io.reactivex.m
            public void onError(Throwable th) {
                FlatMapMaybeSubscriber.this.innerError(this, th);
            }

            @Override // io.reactivex.m
            public void onComplete() {
                FlatMapMaybeSubscriber.this.innerComplete(this);
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
