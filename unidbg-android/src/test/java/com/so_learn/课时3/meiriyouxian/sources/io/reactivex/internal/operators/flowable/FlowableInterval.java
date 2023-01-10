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

public final class FlowableInterval extends g<Long> {
    final w b;
    final long c;
    final long d;
    final TimeUnit e;

    @Override // io.reactivex.g
    public void a(c<? super Long> cVar) {
        IntervalSubscriber intervalSubscriber = new IntervalSubscriber(cVar);
        cVar.onSubscribe(intervalSubscriber);
        w wVar = this.b;
        if (wVar instanceof k) {
            w.c a = wVar.a();
            intervalSubscriber.setResource(a);
            a.a(intervalSubscriber, this.c, this.d, this.e);
            return;
        }
        intervalSubscriber.setResource(wVar.a(intervalSubscriber, this.c, this.d, this.e));
    }

    static final class IntervalSubscriber extends AtomicLong implements Runnable, d {
        private static final long serialVersionUID = -2809475196591179431L;
        long count;
        final c<? super Long> downstream;
        final AtomicReference<b> resource = new AtomicReference<>();

        IntervalSubscriber(c<? super Long> cVar) {
            this.downstream = cVar;
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
            if (this.resource.get() == DisposableHelper.DISPOSED) {
                return;
            }
            if (get() != 0) {
                c<? super Long> cVar = this.downstream;
                long j = this.count;
                this.count = j + 1;
                cVar.onNext(Long.valueOf(j));
                io.reactivex.internal.util.b.c(this, 1);
                return;
            }
            c<? super Long> cVar2 = this.downstream;
            cVar2.onError(new MissingBackpressureException("Can't deliver value " + this.count + " due to lack of requests"));
            DisposableHelper.dispose(this.resource);
        }

        public void setResource(b bVar) {
            DisposableHelper.setOnce(this.resource, bVar);
        }
    }
}
