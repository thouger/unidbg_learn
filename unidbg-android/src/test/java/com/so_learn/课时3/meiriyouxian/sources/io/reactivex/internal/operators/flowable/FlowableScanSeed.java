package io.reactivex.internal.operators.flowable;

import io.reactivex.c.c;
import io.reactivex.internal.a.g;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.a.d;

public final class FlowableScanSeed<T, R> extends a<T, R> {
    final c<R, ? super T, R> c;
    final Callable<R> d;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(org.a.c<? super R> cVar) {
        try {
            this.b.a((j) new ScanSeedSubscriber(cVar, this.c, a.a(this.d.call(), "The seed supplied is null"), a()));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }

    static final class ScanSeedSubscriber<T, R> extends AtomicInteger implements j<T>, d {
        private static final long serialVersionUID = -1776795561228106469L;
        final c<R, ? super T, R> accumulator;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        final org.a.c<? super R> downstream;
        Throwable error;
        final int limit;
        final int prefetch;
        final g<R> queue;
        final AtomicLong requested = new AtomicLong();
        d upstream;
        R value;

        ScanSeedSubscriber(org.a.c<? super R> cVar, c<R, ? super T, R> cVar2, R r, int i) {
            this.downstream = cVar;
            this.accumulator = cVar2;
            this.value = r;
            this.prefetch = i;
            this.limit = i - (i >> 2);
            this.queue = new SpscArrayQueue(i);
            this.queue.offer(r);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                dVar.request((long) (this.prefetch - 1));
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    R r = (R) a.a(this.accumulator.apply(this.value, t), "The accumulator returned a null value");
                    this.value = r;
                    this.queue.offer(r);
                    drain();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.upstream.cancel();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            int i;
            Throwable th;
            if (getAndIncrement() == 0) {
                org.a.c<? super R> cVar = this.downstream;
                g<R> gVar = this.queue;
                int i2 = this.limit;
                int i3 = this.consumed;
                int i4 = 1;
                do {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (true) {
                        i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                        if (i == 0) {
                            break;
                        } else if (this.cancelled) {
                            gVar.clear();
                            return;
                        } else {
                            boolean z = this.done;
                            if (!z || (th = this.error) == null) {
                                Object poll = gVar.poll();
                                boolean z2 = poll == null;
                                if (z && z2) {
                                    cVar.onComplete();
                                    return;
                                } else if (z2) {
                                    break;
                                } else {
                                    cVar.onNext(poll);
                                    j2++;
                                    i3++;
                                    if (i3 == i2) {
                                        this.upstream.request((long) i2);
                                        i3 = 0;
                                    }
                                }
                            } else {
                                gVar.clear();
                                cVar.onError(th);
                                return;
                            }
                        }
                    }
                    if (i == 0 && this.done) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            gVar.clear();
                            cVar.onError(th2);
                            return;
                        } else if (gVar.isEmpty()) {
                            cVar.onComplete();
                            return;
                        }
                    }
                    if (j2 != 0) {
                        b.c(this.requested, j2);
                    }
                    this.consumed = i3;
                    i4 = addAndGet(-i4);
                } while (i4 != 0);
            }
        }
    }
}
