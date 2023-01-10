package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.b;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.schedulers.k;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableIntervalRange extends g<Long> {
    final w b;
    final long c;
    final long d;
    final long e;
    final long f;
    final TimeUnit g;

    @Override // io.reactivex.g
    public void a(c<? super Long> cVar) {
        IntervalRangeSubscriber intervalRangeSubscriber = new IntervalRangeSubscriber(cVar, this.c, this.d);
        cVar.onSubscribe(intervalRangeSubscriber);
        w wVar = this.b;
        if (wVar instanceof k) {
            w.c a = wVar.a();
            intervalRangeSubscriber.setResource(a);
            a.a(intervalRangeSubscriber, this.e, this.f, this.g);
            return;
        }
        intervalRangeSubscriber.setResource(wVar.a(intervalRangeSubscriber, this.e, this.f, this.g));
    }

    static final class IntervalRangeSubscriber extends AtomicLong implements Runnable, d {
        private static final long serialVersionUID = -2809475196591179431L;
        long count;
        final c<? super Long> downstream;
        final long end;
        final AtomicReference<b> resource = new AtomicReference<>();

        IntervalRangeSubscriber(c<? super Long> cVar, long j, long j2) {
            this.downstream = cVar;
            this.count = j;
            this.end = j2;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this, j);
            }
        }

        public void cancel() {
            DisposableHelper.dispose(this.resource);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.resource.get() != DisposableHelper.DISPOSED) {
                long j = get();
                if (j != 0) {
                    long j2 = this.count;
                    this.downstream.onNext(Long.valueOf(j2));
                    if (j2 == this.end) {
                        if (this.resource.get() != DisposableHelper.DISPOSED) {
                            this.downstream.onComplete();
                        }
                        DisposableHelper.dispose(this.resource);
                        return;
                    }
                    this.count = j2 + 1;
                    if (j != Long.MAX_VALUE) {
                        decrementAndGet();
                        return;
                    }
                    return;
                }
                c<? super Long> cVar = this.downstream;
                cVar.onError(new MissingBackpressureException("Can't deliver value " + this.count + " due to lack of requests"));
                DisposableHelper.dispose(this.resource);
            }
        }

        public void setResource(b bVar) {
            DisposableHelper.setOnce(this.resource, bVar);
        }
    }
}
