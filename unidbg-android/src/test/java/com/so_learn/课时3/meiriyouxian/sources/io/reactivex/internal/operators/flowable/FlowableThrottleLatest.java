package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableThrottleLatest<T> extends a<T, T> {
    final long c;
    final TimeUnit d;
    final w e;
    final boolean f;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new ThrottleLatestSubscriber(cVar, this.c, this.d, this.e.a(), this.f));
    }

    static final class ThrottleLatestSubscriber<T> extends AtomicInteger implements j<T>, Runnable, d {
        private static final long serialVersionUID = -8296689127439125014L;
        volatile boolean cancelled;
        volatile boolean done;
        final c<? super T> downstream;
        final boolean emitLast;
        long emitted;
        Throwable error;
        final AtomicReference<T> latest = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();
        final long timeout;
        volatile boolean timerFired;
        boolean timerRunning;
        final TimeUnit unit;
        d upstream;
        final w.c worker;

        ThrottleLatestSubscriber(c<? super T> cVar, long j, TimeUnit timeUnit, w.c cVar2, boolean z) {
            this.downstream = cVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar2;
            this.emitLast = z;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            this.latest.set(t);
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            this.worker.dispose();
            if (getAndIncrement() == 0) {
                this.latest.lazySet(null);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.timerFired = true;
            drain();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                AtomicReference<T> atomicReference = this.latest;
                AtomicLong atomicLong = this.requested;
                c<? super T> cVar = this.downstream;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = this.done;
                    if (!z || this.error == null) {
                        boolean z2 = atomicReference.get() == null;
                        if (z) {
                            if (z2 || !this.emitLast) {
                                atomicReference.lazySet(null);
                                cVar.onComplete();
                            } else {
                                T andSet = atomicReference.getAndSet(null);
                                long j = this.emitted;
                                if (j != atomicLong.get()) {
                                    this.emitted = j + 1;
                                    cVar.onNext(andSet);
                                    cVar.onComplete();
                                } else {
                                    cVar.onError(new MissingBackpressureException("Could not emit final value due to lack of requests"));
                                }
                            }
                            this.worker.dispose();
                            return;
                        }
                        if (z2) {
                            if (this.timerFired) {
                                this.timerRunning = false;
                                this.timerFired = false;
                            }
                        } else if (!this.timerRunning || this.timerFired) {
                            T andSet2 = atomicReference.getAndSet(null);
                            long j2 = this.emitted;
                            if (j2 != atomicLong.get()) {
                                cVar.onNext(andSet2);
                                this.emitted = j2 + 1;
                                this.timerFired = false;
                                this.timerRunning = true;
                                this.worker.a(this, this.timeout, this.unit);
                            } else {
                                this.upstream.cancel();
                                cVar.onError(new MissingBackpressureException("Could not emit value due to lack of requests"));
                                this.worker.dispose();
                                return;
                            }
                        }
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        atomicReference.lazySet(null);
                        cVar.onError(this.error);
                        this.worker.dispose();
                        return;
                    }
                }
                atomicReference.lazySet(null);
            }
        }
    }
}
