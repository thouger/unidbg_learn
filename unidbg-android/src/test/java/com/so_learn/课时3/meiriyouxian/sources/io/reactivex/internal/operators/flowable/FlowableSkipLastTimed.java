package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.queue.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;
import org.a.d;

public final class FlowableSkipLastTimed<T> extends a<T, T> {
    final long c;
    final TimeUnit d;
    final w e;
    final int f;
    final boolean g;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new SkipLastTimedSubscriber(cVar, this.c, this.d, this.e, this.f, this.g));
    }

    static final class SkipLastTimedSubscriber<T> extends AtomicInteger implements j<T>, d {
        private static final long serialVersionUID = -5677354903406201275L;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        final c<? super T> downstream;
        Throwable error;
        final a<Object> queue;
        final AtomicLong requested = new AtomicLong();
        final w scheduler;
        final long time;
        final TimeUnit unit;
        d upstream;

        SkipLastTimedSubscriber(c<? super T> cVar, long j, TimeUnit timeUnit, w wVar, int i, boolean z) {
            this.downstream = cVar;
            this.time = j;
            this.unit = timeUnit;
            this.scheduler = wVar;
            this.queue = new a<>(i);
            this.delayError = z;
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
            this.queue.a(Long.valueOf(this.scheduler.a(this.unit)), t);
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
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                c<? super T> cVar = this.downstream;
                a<Object> aVar = this.queue;
                boolean z = this.delayError;
                TimeUnit timeUnit = this.unit;
                w wVar = this.scheduler;
                long j = this.time;
                int i = 1;
                do {
                    long j2 = this.requested.get();
                    long j3 = 0;
                    while (j3 != j2) {
                        boolean z2 = this.done;
                        Long l = (Long) aVar.a();
                        boolean z3 = l == null;
                        boolean z4 = (z3 || l.longValue() <= wVar.a(timeUnit) - j) ? z3 : true;
                        if (!checkTerminated(z2, z4, cVar, z)) {
                            if (z4) {
                                break;
                            }
                            aVar.poll();
                            cVar.onNext(aVar.poll());
                            j3++;
                        } else {
                            return;
                        }
                    }
                    if (j3 != 0) {
                        b.c(this.requested, j3);
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean z, boolean z2, c<? super T> cVar, boolean z3) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                if (!z3) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.queue.clear();
                        cVar.onError(th);
                        return true;
                    } else if (!z2) {
                        return false;
                    } else {
                        cVar.onComplete();
                        return true;
                    }
                } else if (!z2) {
                    return false;
                } else {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        cVar.onError(th2);
                    } else {
                        cVar.onComplete();
                    }
                    return true;
                }
            }
        }
    }
}
