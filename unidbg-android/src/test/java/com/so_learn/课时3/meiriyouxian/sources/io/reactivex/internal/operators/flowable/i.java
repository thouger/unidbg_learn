package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.b;
import io.reactivex.g;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ArrayListSupplier;
import io.reactivex.j;
import io.reactivex.x;
import io.reactivex.z;
import java.util.Collection;
import java.util.concurrent.Callable;
import org.a.d;

/* compiled from: FlowableToListSingle */
public final class i<T, U extends Collection<? super T>> extends x<U> {
    final g<T> a;
    final Callable<U> b;

    public i(g<T> gVar) {
        this(gVar, ArrayListSupplier.asCallable());
    }

    public i(g<T> gVar, Callable<U> callable) {
        this.a = gVar;
        this.b = callable;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super U> zVar) {
        try {
            this.a.a((j) new a(zVar, (Collection) io.reactivex.internal.functions.a.a(this.b.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, zVar);
        }
    }

    /* compiled from: FlowableToListSingle */
    static final class a<T, U extends Collection<? super T>> implements b, j<T> {
        final z<? super U> a;
        d b;
        U c;

        a(z<? super U> zVar, U u) {
            this.a = zVar;
            this.c = u;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.b, dVar)) {
                this.b = dVar;
                this.a.onSubscribe(this);
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            this.c.add(t);
        }

        public void onError(Throwable th) {
            this.c = null;
            this.b = SubscriptionHelper.CANCELLED;
            this.a.onError(th);
        }

        public void onComplete() {
            this.b = SubscriptionHelper.CANCELLED;
            this.a.onSuccess(this.c);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.b.cancel();
            this.b = SubscriptionHelper.CANCELLED;
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.b == SubscriptionHelper.CANCELLED;
        }
    }
}
