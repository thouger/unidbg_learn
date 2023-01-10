package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableSamplePublisher<T> extends g<T> {
    final b<T> b;
    final b<?> c;
    final boolean d;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        io.reactivex.subscribers.b bVar = new io.reactivex.subscribers.b(cVar);
        if (this.d) {
            this.b.subscribe(new SampleMainEmitLast(bVar, this.c));
        } else {
            this.b.subscribe(new SampleMainNoLast(bVar, this.c));
        }
    }

    static abstract class SamplePublisherSubscriber<T> extends AtomicReference<T> implements j<T>, d {
        private static final long serialVersionUID = -3517602651313910099L;
        final c<? super T> downstream;
        final AtomicReference<d> other = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();
        final b<?> sampler;
        d upstream;

        /* access modifiers changed from: package-private */
        public abstract void completion();

        /* access modifiers changed from: package-private */
        public abstract void run();

        SamplePublisherSubscriber(c<? super T> cVar, b<?> bVar) {
            this.downstream = cVar;
            this.sampler = bVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                if (this.other.get() == null) {
                    this.sampler.subscribe(new a(this));
                    dVar.request(Long.MAX_VALUE);
                }
            }
        }

        public void onNext(T t) {
            lazySet(t);
        }

        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            this.downstream.onError(th);
        }

        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            completion();
        }

        /* access modifiers changed from: package-private */
        public void setOther(d dVar) {
            SubscriptionHelper.setOnce(this.other, dVar, Long.MAX_VALUE);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.other);
            this.upstream.cancel();
        }

        public void error(Throwable th) {
            this.upstream.cancel();
            this.downstream.onError(th);
        }

        public void complete() {
            this.upstream.cancel();
            completion();
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

    static final class a<T> implements j<Object> {
        final SamplePublisherSubscriber<T> a;

        a(SamplePublisherSubscriber<T> samplePublisherSubscriber) {
            this.a = samplePublisherSubscriber;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            this.a.setOther(dVar);
        }

        public void onNext(Object obj) {
            this.a.run();
        }

        public void onError(Throwable th) {
            this.a.error(th);
        }

        public void onComplete() {
            this.a.complete();
        }
    }

    static final class SampleMainNoLast<T> extends SamplePublisherSubscriber<T> {
        private static final long serialVersionUID = -3029755663834015785L;

        SampleMainNoLast(c<? super T> cVar, b<?> bVar) {
            super(cVar, bVar);
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableSamplePublisher.SamplePublisherSubscriber
        public void completion() {
            this.downstream.onComplete();
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableSamplePublisher.SamplePublisherSubscriber
        public void run() {
            emit();
        }
    }

    static final class SampleMainEmitLast<T> extends SamplePublisherSubscriber<T> {
        private static final long serialVersionUID = -3029755663834015785L;
        volatile boolean done;
        final AtomicInteger wip = new AtomicInteger();

        SampleMainEmitLast(c<? super T> cVar, b<?> bVar) {
            super(cVar, bVar);
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableSamplePublisher.SamplePublisherSubscriber
        public void completion() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.downstream.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableSamplePublisher.SamplePublisherSubscriber
        public void run() {
            if (this.wip.getAndIncrement() == 0) {
                do {
                    boolean z = this.done;
                    emit();
                    if (z) {
                        this.downstream.onComplete();
                        return;
                    }
                } while (this.wip.decrementAndGet() != 0);
            }
        }
    }
}
