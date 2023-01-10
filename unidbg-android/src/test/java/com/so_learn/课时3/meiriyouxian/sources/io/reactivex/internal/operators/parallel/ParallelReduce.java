package io.reactivex.internal.operators.parallel;

import io.reactivex.c.c;
import io.reactivex.internal.subscribers.DeferredScalarSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.parallel.a;
import java.util.concurrent.Callable;
import org.a.d;

public final class ParallelReduce<T, R> extends a<R> {
    final a<? extends T> a;
    final Callable<R> b;
    final c<R, ? super T, R> c;

    @Override // io.reactivex.parallel.a
    public void a(org.a.c<? super R>[] cVarArr) {
        if (b(cVarArr)) {
            int length = cVarArr.length;
            org.a.c[] cVarArr2 = new org.a.c[length];
            for (int i = 0; i < length; i++) {
                try {
                    cVarArr2[i] = new ParallelReduceSubscriber(cVarArr[i], io.reactivex.internal.functions.a.a(this.b.call(), "The initialSupplier returned a null value"), this.c);
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
    public void a(org.a.c<?>[] cVarArr, Throwable th) {
        for (org.a.c<?> cVar : cVarArr) {
            EmptySubscription.error(th, cVar);
        }
    }

    @Override // io.reactivex.parallel.a
    public int a() {
        return this.a.a();
    }

    static final class ParallelReduceSubscriber<T, R> extends DeferredScalarSubscriber<T, R> {
        private static final long serialVersionUID = 8200530050639449080L;
        R accumulator;
        boolean done;
        final c<R, ? super T, R> reducer;

        ParallelReduceSubscriber(org.a.c<? super R> cVar, R r, c<R, ? super T, R> cVar2) {
            super(cVar);
            this.accumulator = r;
            this.reducer = cVar2;
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
                    this.accumulator = (R) io.reactivex.internal.functions.a.a(this.reducer.apply(this.accumulator, t), "The reducer returned a null value");
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
            this.accumulator = null;
            this.downstream.onError(th);
        }

        @Override // io.reactivex.internal.subscribers.DeferredScalarSubscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                R r = this.accumulator;
                this.accumulator = null;
                complete(r);
            }
        }

        @Override // io.reactivex.internal.subscribers.DeferredScalarSubscriber, io.reactivex.internal.subscriptions.DeferredScalarSubscription
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
