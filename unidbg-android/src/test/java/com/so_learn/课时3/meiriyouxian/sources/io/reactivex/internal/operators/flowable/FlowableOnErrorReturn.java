package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.j;
import org.a.c;

public final class FlowableOnErrorReturn<T> extends a<T, T> {
    final h<? super Throwable, ? extends T> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new OnErrorReturnSubscriber(cVar, this.c));
    }

    static final class OnErrorReturnSubscriber<T> extends SinglePostCompleteSubscriber<T, T> {
        private static final long serialVersionUID = -3740826063558713822L;
        final h<? super Throwable, ? extends T> valueSupplier;

        OnErrorReturnSubscriber(c<? super T> cVar, h<? super Throwable, ? extends T> hVar) {
            super(cVar);
            this.valueSupplier = hVar;
        }

        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            try {
                complete(a.a(this.valueSupplier.apply(th), "The valueSupplier returned a null value"));
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
