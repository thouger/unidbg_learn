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

public final class FlowableTakeLastTimed<T> extends a<T, T> {
    final long c;
    final long d;
    final TimeUnit e;
    final w f;
    final int g;
    final boolean h;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new TakeLastTimedSubscriber(cVar, this.c, this.d, this.e, this.f, this.g, this.h));
    }

    static final class TakeLastTimedSubscriber<T> extends AtomicInteger implements j<T>, d {
        private static final long serialVersionUID = -5677354903406201275L;
        volatile boolean cancelled;
        final long count;
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

        TakeLastTimedSubscriber(c<? super T> cVar, long j, long j2, TimeUnit timeUnit, w wVar, int i, boolean z) {
            this.downstream = cVar;
            this.count = j;
            this.time = j2;
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
            a<Object> aVar = this.queue;
            long a = this.scheduler.a(this.unit);
            aVar.a(Long.valueOf(a), t);
            trim(a, aVar);
        }

        public void onError(Throwable th) {
            if (this.delayError) {
                trim(this.scheduler.a(this.unit), this.queue);
            }
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            trim(this.scheduler.a(this.unit), this.queue);
            this.done = true;
            drain();
        }

        /* access modifiers changed from: package-private */
        public void trim(long j, a<Object> aVar) {
            long j2 = this.time;
            long j3 = this.count;
            boolean z = j3 == Long.MAX_VALUE;
            while (!aVar.isEmpty()) {
                if (((Long) aVar.a()).longValue() < j - j2 || (!z && ((long) (aVar.b() >> 1)) > j3)) {
                    aVar.poll();
                    aVar.poll();
                } else {
                    return;
                }
            }
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
                int i = 1;
                do {
                    if (this.done) {
                        if (!checkTerminated(aVar.isEmpty(), cVar, z)) {
                            long j = this.requested.get();
                            long j2 = 0;
                            while (true) {
                                if (!checkTerminated(aVar.a() == null, cVar, z)) {
                                    if (j != j2) {
                                        aVar.poll();
                                        cVar.onNext(aVar.poll());
                                        j2++;
                                    } else if (j2 != 0) {
                                        b.c(this.requested, j2);
                                    }
                                } else {
                                    return;
                                }
                            }
                        } else {
                            return;
                        }
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean z, c<? super T> cVar, boolean z2) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            } else if (!z2) {
                Throwable th = this.error;
                if (th != null) {
                    this.queue.clear();
                    cVar.onError(th);
                    return true;
                } else if (!z) {
                    return false;
                } else {
                    cVar.onComplete();
                    return true;
                }
            } else if (!z) {
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
