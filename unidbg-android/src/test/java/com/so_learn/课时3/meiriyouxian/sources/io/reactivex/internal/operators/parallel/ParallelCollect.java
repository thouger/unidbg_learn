package io.reactivex.internal.operators.parallel;

import io.reactivex.c.b;
import io.reactivex.internal.subscribers.DeferredScalarSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.parallel.a;
import java.util.concurrent.Callable;
import org.a.c;
import org.a.d;

public final class ParallelCollect<T, C> extends a<C> {
    final a<? extends T> a;
    final Callable<? extends C> b;
    final b<? super C, ? super T> c;

    @Override // io.reactivex.parallel.a
    public void a(c<? super C>[] cVarArr) {
        if (b(cVarArr)) {
            int length = cVarArr.length;
            c[] cVarArr2 = new c[length];
            for (int i = 0; i < length; i++) {
                try {
                    cVarArr2[i] = new ParallelCollectSubscriber(cVarArr[i], io.reactivex.internal.functions.a.a(this.b.call(), "The initialSupplier returned a null value"), this.c);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    a(cVarArr, th);
                    return;
                }
            }
            this.a.a(cVarArr2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(c<?>[] cVarArr, Throwable th) {
        for (c<?> cVar : cVarArr) {
            EmptySubscription.error(th, cVar);
        }
    }

    @Override // io.reactivex.parallel.a
    public int a() {
        return this.a.a();
    }

    static final class ParallelCollectSubscriber<T, C> extends DeferredScalarSubscriber<T, C> {
        private static final long serialVersionUID = -4767392946044436228L;
        C collection;
        final b<? super C, ? super T> collector;
        boolean done;

        ParallelCollectSubscriber(c<? super C> cVar, C c, b<? super C, ? super T> bVar) {
            super(cVar);
            this.collection = c;
            this.collector = bVar;
        }

        @Override // io.reactivex.internal.subscribers.DeferredScalarSubscriber, io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    this.collector.a(this.collection, t);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    cancel();
                    onError(th);
                }
            }
        }

        @Override // io.reactivex.internal.subscribers.DeferredScalarSubscriber
        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.done = true;
            this.collection = null;
            this.downstream.onError(th);
        }

        @Override // io.reactivex.internal.subscribers.DeferredScalarSubscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                C c = this.collection;
                this.collection = null;
                complete(c);
            }
        }

        @Override // io.reactivex.internal.subscribers.DeferredScalarSubscriber, io.reactivex.internal.subscriptions.DeferredScalarSubscription
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
