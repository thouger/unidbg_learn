package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.subscribers.b;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableSampleTimed<T> extends a<T, T> {
    final long c;
    final TimeUnit d;
    final w e;
    final boolean f;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        b bVar = new b(cVar);
        if (this.f) {
            this.b.a((j) new SampleTimedEmitLast(bVar, this.c, this.d, this.e));
        } else {
            this.b.a((j) new SampleTimedNoLast(bVar, this.c, this.d, this.e));
        }
    }

    static abstract class SampleTimedSubscriber<T> extends AtomicReference<T> implements j<T>, Runnable, d {
        private static final long serialVersionUID = -3517602651313910099L;
        final c<? super T> downstream;
        final long period;
        final AtomicLong requested = new AtomicLong();
        final w scheduler;
        final SequentialDisposable timer = new SequentialDisposable();
        final TimeUnit unit;
        d upstream;

        /* access modifiers changed from: package-private */
        public abstract void complete();

        SampleTimedSubscriber(c<? super T> cVar, long j, TimeUnit timeUnit, w wVar) {
            this.downstream = cVar;
            this.period = j;
            this.unit = timeUnit;
            this.scheduler = wVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                SequentialDisposable sequentialDisposable = this.timer;
                w wVar = this.scheduler;
                long j = this.period;
                sequentialDisposable.replace(wVar.a(this, j, j, this.unit));
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            lazySet(t);
        }

        public void onError(Throwable th) {
            cancelTimer();
            this.downstream.onError(th);
        }

        public void onComplete() {
            cancelTimer();
            complete();
        }

        /* access modifiers changed from: package-private */
        public void cancelTimer() {
            DisposableHelper.dispose(this.timer);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
            }
        }

        public void cancel() {
            cancelTimer();
            this.upstream.cancel();
        }

        /* access modifiers changed from: package-private */
        public void emit() {
            T andSet = getAndSet(null);
            if (andSet == null) {
                return;
            }
            if (this.requested.get() != 0) {
                this.downstream.onNext(andSet);
                io.reactivex.internal.util.b.c(this.requested, 1);
                return;
            }
            cancel();
            this.downstream.onError(new MissingBackpressureException("Couldn't emit value due to lack of requests!"));
        }
    }

    static final class SampleTimedNoLast<T> extends SampleTimedSubscriber<T> {
        private static final long serialVersionUID = -7139995637533111443L;

        SampleTimedNoLast(c<? super T> cVar, long j, TimeUnit timeUnit, w wVar) {
            super(cVar, j, timeUnit, wVar);
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableSampleTimed.SampleTimedSubscriber
        public void complete() {
            this.downstream.onComplete();
        }

        @Override // java.lang.Runnable
        public void run() {
            emit();
        }
    }

    static final class SampleTimedEmitLast<T> extends SampleTimedSubscriber<T> {
        private static final long serialVersionUID = -7139995637533111443L;
        final AtomicInteger wip = new AtomicInteger(1);

        SampleTimedEmitLast(c<? super T> cVar, long j, TimeUnit timeUnit, w wVar) {
            super(cVar, j, timeUnit, wVar);
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableSampleTimed.SampleTimedSubscriber
        public void complete() {
            emit();
            if (this.wip.decrementAndGet() == 0) {
                this.downstream.onComplete();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.wip.incrementAndGet() == 2) {
                emit();
                if (this.wip.decrementAndGet() == 0) {
                    this.downstream.onComplete();
                }
            }
        }
    }
}
