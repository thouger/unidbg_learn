package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.g;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.j;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableOnErrorNext<T> extends a<T, T> {
    final h<? super Throwable, ? extends b<? extends T>> c;
    final boolean d;

    public FlowableOnErrorNext(g<T> gVar, h<? super Throwable, ? extends b<? extends T>> hVar, boolean z) {
        super(gVar);
        this.c = hVar;
        this.d = z;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        OnErrorNextSubscriber onErrorNextSubscriber = new OnErrorNextSubscriber(cVar, this.c, this.d);
        cVar.onSubscribe(onErrorNextSubscriber);
        this.b.a((j) onErrorNextSubscriber);
    }

    static final class OnErrorNextSubscriber<T> extends SubscriptionArbiter implements j<T> {
        private static final long serialVersionUID = 4063763155303814625L;
        final boolean allowFatal;
        boolean done;
        final c<? super T> downstream;
        final h<? super Throwable, ? extends b<? extends T>> nextSupplier;
        boolean once;
        long produced;

        OnErrorNextSubscriber(c<? super T> cVar, h<? super Throwable, ? extends b<? extends T>> hVar, boolean z) {
            super(false);
            this.downstream = cVar;
            this.nextSupplier = hVar;
            this.allowFatal = z;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            setSubscription(dVar);
        }

        public void onNext(T t) {
            if (!this.done) {
                if (!this.once) {
                    this.produced++;
                }
                this.downstream.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (!this.once) {
                this.once = true;
                if (!this.allowFatal || (th instanceof Exception)) {
                    try {
                        b bVar = (b) a.a(this.nextSupplier.apply(th), "The nextSupplier returned a null Publisher");
                        long j = this.produced;
                        if (j != 0) {
                            produced(j);
                        }
                        bVar.subscribe(this);
                    } catch (Throwable th2) {
                        io.reactivex.exceptions.a.b(th2);
                        this.downstream.onError(new CompositeException(th, th2));
                    }
                } else {
                    this.downstream.onError(th);
                }
            } else if (this.done) {
                io.reactivex.e.a.a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.once = true;
                this.downstream.onComplete();
            }
        }
    }
}
