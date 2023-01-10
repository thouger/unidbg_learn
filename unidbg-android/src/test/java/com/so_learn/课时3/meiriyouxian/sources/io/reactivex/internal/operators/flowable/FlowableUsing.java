package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.g;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableUsing<T, D> extends g<T> {
    final Callable<? extends D> b;
    final h<? super D, ? extends b<? extends T>> c;
    final io.reactivex.c.g<? super D> d;
    final boolean e;

    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        try {
            Object call = this.b.call();
            try {
                ((b) a.a(this.c.apply(call), "The sourceSupplier returned a null Publisher")).subscribe(new UsingSubscriber(cVar, call, this.d, this.e));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptySubscription.error(new CompositeException(th, th), cVar);
            }
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            EmptySubscription.error(th2, cVar);
        }
    }

    static final class UsingSubscriber<T, D> extends AtomicBoolean implements j<T>, d {
        private static final long serialVersionUID = 5904473792286235046L;
        final io.reactivex.c.g<? super D> disposer;
        final c<? super T> downstream;
        final boolean eager;
        final D resource;
        d upstream;

        UsingSubscriber(c<? super T> cVar, D d, io.reactivex.c.g<? super D> gVar, boolean z) {
            this.downstream = cVar;
            this.resource = d;
            this.disposer = gVar;
            this.eager = z;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            if (this.eager) {
                Throwable th2 = null;
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th3) {
                        th2 = th3;
                        io.reactivex.exceptions.a.b(th2);
                    }
                }
                this.upstream.cancel();
                if (th2 != null) {
                    this.downstream.onError(new CompositeException(th, th2));
                } else {
                    this.downstream.onError(th);
                }
            } else {
                this.downstream.onError(th);
                this.upstream.cancel();
                disposeAfter();
            }
        }

        public void onComplete() {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.downstream.onError(th);
                        return;
                    }
                }
                this.upstream.cancel();
                this.downstream.onComplete();
                return;
            }
            this.downstream.onComplete();
            this.upstream.cancel();
            disposeAfter();
        }

        public void request(long j) {
            this.upstream.request(j);
        }

        public void cancel() {
            disposeAfter();
            this.upstream.cancel();
        }

        /* access modifiers changed from: package-private */
        public void disposeAfter() {
            if (compareAndSet(false, true)) {
                try {
                    this.disposer.accept(this.resource);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.e.a.a(th);
                }
            }
        }
    }
}
