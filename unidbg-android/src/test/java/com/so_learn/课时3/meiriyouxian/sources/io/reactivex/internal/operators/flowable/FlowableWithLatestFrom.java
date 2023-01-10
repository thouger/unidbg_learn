package io.reactivex.internal.operators.flowable;

import io.reactivex.c.c;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.d;

public final class FlowableWithLatestFrom<T, U, R> extends a<T, R> {
    final c<? super T, ? super U, ? extends R> c;
    final b<? extends U> d;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(org.a.c<? super R> cVar) {
        io.reactivex.subscribers.b bVar = new io.reactivex.subscribers.b(cVar);
        WithLatestFromSubscriber withLatestFromSubscriber = new WithLatestFromSubscriber(bVar, this.c);
        bVar.onSubscribe(withLatestFromSubscriber);
        this.d.subscribe(new a(withLatestFromSubscriber));
        this.b.a((j) withLatestFromSubscriber);
    }

    static final class WithLatestFromSubscriber<T, U, R> extends AtomicReference<U> implements io.reactivex.internal.a.a<T>, d {
        private static final long serialVersionUID = -312246233408980075L;
        final c<? super T, ? super U, ? extends R> combiner;
        final org.a.c<? super R> downstream;
        final AtomicReference<d> other = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<d> upstream = new AtomicReference<>();

        WithLatestFromSubscriber(org.a.c<? super R> cVar, c<? super T, ? super U, ? extends R> cVar2) {
            this.downstream = cVar;
            this.combiner = cVar2;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, dVar);
        }

        public void onNext(T t) {
            if (!tryOnNext(t)) {
                this.upstream.get().request(1);
            }
        }

        @Override // io.reactivex.internal.a.a
        public boolean tryOnNext(T t) {
            U u = get();
            if (u != null) {
                try {
                    this.downstream.onNext(io.reactivex.internal.functions.a.a(this.combiner.apply(t, u), "The combiner returned a null value"));
                    return true;
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    cancel();
                    this.downstream.onError(th);
                }
            }
            return false;
        }

        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            this.downstream.onError(th);
        }

        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            this.downstream.onComplete();
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            SubscriptionHelper.cancel(this.other);
        }

        public boolean setOther(d dVar) {
            return SubscriptionHelper.setOnce(this.other, dVar);
        }

        public void otherError(Throwable th) {
            SubscriptionHelper.cancel(this.upstream);
            this.downstream.onError(th);
        }
    }

    final class a implements j<U> {
        private final WithLatestFromSubscriber<T, U, R> b;

        public void onComplete() {
        }

        a(WithLatestFromSubscriber<T, U, R> withLatestFromSubscriber) {
            this.b = withLatestFromSubscriber;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (this.b.setOther(dVar)) {
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(U u) {
            this.b.lazySet(u);
        }

        public void onError(Throwable th) {
            this.b.otherError(th);
        }
    }
}
