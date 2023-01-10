package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.e;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.a.h;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.d;

public final class CompletableConcat extends a {
    final b<? extends e> a;
    final int b;

    @Override // io.reactivex.a
    public void b(c cVar) {
        this.a.subscribe(new CompletableConcatSubscriber(cVar, this.b));
    }

    static final class CompletableConcatSubscriber extends AtomicInteger implements io.reactivex.disposables.b, j<e> {
        private static final long serialVersionUID = 9032184911934499404L;
        volatile boolean active;
        int consumed;
        volatile boolean done;
        final c downstream;
        final ConcatInnerObserver inner = new ConcatInnerObserver(this);
        final int limit;
        final AtomicBoolean once = new AtomicBoolean();
        final int prefetch;
        h<e> queue;
        int sourceFused;
        d upstream;

        CompletableConcatSubscriber(c cVar, int i) {
            this.downstream = cVar;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                int i = this.prefetch;
                long j = i == Integer.MAX_VALUE ? Long.MAX_VALUE : (long) i;
                if (dVar instanceof io.reactivex.internal.a.e) {
                    io.reactivex.internal.a.e eVar = (io.reactivex.internal.a.e) dVar;
                    int requestFusion = eVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceFused = requestFusion;
                        this.queue = eVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceFused = requestFusion;
                        this.queue = eVar;
                        this.downstream.onSubscribe(this);
                        dVar.request(j);
                        return;
                    }
                }
                int i2 = this.prefetch;
                if (i2 == Integer.MAX_VALUE) {
                    this.queue = new io.reactivex.internal.queue.a(g.a());
                } else {
                    this.queue = new SpscArrayQueue(i2);
                }
                this.downstream.onSubscribe(this);
                dVar.request(j);
            }
        }

        public void onNext(e eVar) {
            if (this.sourceFused != 0 || this.queue.offer(eVar)) {
                drain();
            } else {
                onError(new MissingBackpressureException());
            }
        }

        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.inner);
                this.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.upstream.cancel();
            DisposableHelper.dispose(this.inner);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.inner.get());
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                while (!isDisposed()) {
                    if (!this.active) {
                        boolean z = this.done;
                        try {
                            e eVar = (e) this.queue.poll();
                            boolean z2 = eVar == null;
                            if (!z || !z2) {
                                if (!z2) {
                                    this.active = true;
                                    eVar.a(this.inner);
                                    request();
                                }
                            } else if (this.once.compareAndSet(false, true)) {
                                this.downstream.onComplete();
                                return;
                            } else {
                                return;
                            }
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            innerError(th);
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void request() {
            if (this.sourceFused != 1) {
                int i = this.consumed + 1;
                if (i == this.limit) {
                    this.consumed = 0;
                    this.upstream.request((long) i);
                    return;
                }
                this.consumed = i;
            }
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                this.upstream.cancel();
                this.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        /* access modifiers changed from: package-private */
        public void innerComplete() {
            this.active = false;
            drain();
        }

        /* access modifiers changed from: package-private */
        public static final class ConcatInnerObserver extends AtomicReference<io.reactivex.disposables.b> implements c {
            private static final long serialVersionUID = -5454794857847146511L;
            final CompletableConcatSubscriber parent;

            ConcatInnerObserver(CompletableConcatSubscriber completableConcatSubscriber) {
                this.parent = completableConcatSubscriber;
            }

            @Override // io.reactivex.c
            public void onSubscribe(io.reactivex.disposables.b bVar) {
                DisposableHelper.replace(this, bVar);
            }

            @Override // io.reactivex.c
            public void onError(Throwable th) {
                this.parent.innerError(th);
            }

            @Override // io.reactivex.c
            public void onComplete() {
                this.parent.innerComplete();
            }
        }
    }
}
