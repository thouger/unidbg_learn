package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.processors.a;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableRepeatWhen<T> extends a<T, T> {
    final h<? super g<Object>, ? extends b<?>> c;

    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        io.reactivex.subscribers.b bVar = new io.reactivex.subscribers.b(cVar);
        a<T> g = UnicastProcessor.b(8).g();
        try {
            b bVar2 = (b) io.reactivex.internal.functions.a.a(this.c.apply(g), "handler returned a null Publisher");
            WhenReceiver whenReceiver = new WhenReceiver(this.b);
            RepeatWhenSubscriber repeatWhenSubscriber = new RepeatWhenSubscriber(bVar, g, whenReceiver);
            whenReceiver.subscriber = repeatWhenSubscriber;
            cVar.onSubscribe(repeatWhenSubscriber);
            bVar2.subscribe(whenReceiver);
            whenReceiver.onNext(0);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }

    static final class WhenReceiver<T, U> extends AtomicInteger implements j<Object>, d {
        private static final long serialVersionUID = 2827772011130406689L;
        final AtomicLong requested = new AtomicLong();
        final b<T> source;
        WhenSourceSubscriber<T, U> subscriber;
        final AtomicReference<d> upstream = new AtomicReference<>();

        WhenReceiver(b<T> bVar) {
            this.source = bVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, dVar);
        }

        public void onNext(Object obj) {
            if (getAndIncrement() == 0) {
                while (this.upstream.get() != SubscriptionHelper.CANCELLED) {
                    this.source.subscribe(this.subscriber);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        public void onError(Throwable th) {
            this.subscriber.cancel();
            this.subscriber.downstream.onError(th);
        }

        public void onComplete() {
            this.subscriber.cancel();
            this.subscriber.downstream.onComplete();
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
        }
    }

    /* access modifiers changed from: package-private */
    public static abstract class WhenSourceSubscriber<T, U> extends SubscriptionArbiter implements j<T> {
        private static final long serialVersionUID = -5604623027276966720L;
        protected final c<? super T> downstream;
        protected final a<U> processor;
        private long produced;
        protected final d receiver;

        WhenSourceSubscriber(c<? super T> cVar, a<U> aVar, d dVar) {
            super(false);
            this.downstream = cVar;
            this.processor = aVar;
            this.receiver = dVar;
        }

        @Override // io.reactivex.j
        public final void onSubscribe(d dVar) {
            setSubscription(dVar);
        }

        public final void onNext(T t) {
            this.produced++;
            this.downstream.onNext(t);
        }

        /* access modifiers changed from: protected */
        public final void again(U u) {
            setSubscription(EmptySubscription.INSTANCE);
            long j = this.produced;
            if (j != 0) {
                this.produced = 0;
                produced(j);
            }
            this.receiver.request(1);
            this.processor.onNext(u);
        }

        @Override // io.reactivex.internal.subscriptions.SubscriptionArbiter
        public final void cancel() {
            super.cancel();
            this.receiver.cancel();
        }
    }

    static final class RepeatWhenSubscriber<T> extends WhenSourceSubscriber<T, Object> {
        private static final long serialVersionUID = -2680129890138081029L;

        RepeatWhenSubscriber(c<? super T> cVar, a<Object> aVar, d dVar) {
            super(cVar, aVar, dVar);
        }

        public void onError(Throwable th) {
            this.receiver.cancel();
            this.downstream.onError(th);
        }

        public void onComplete() {
            again(0);
        }
    }
}
