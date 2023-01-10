package io.reactivex.internal.operators.maybe;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.m;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.d;

public final class MaybeDelayOtherPublisher<T, U> extends a<T, T> {
    final b<U> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        this.a.a(new a(mVar, this.b));
    }

    static final class a<T, U> implements io.reactivex.disposables.b, m<T> {
        final OtherSubscriber<T> a;
        final b<U> b;
        io.reactivex.disposables.b c;

        a(m<? super T> mVar, b<U> bVar) {
            this.a = new OtherSubscriber<>(mVar);
            this.b = bVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.c.dispose();
            this.c = DisposableHelper.DISPOSED;
            SubscriptionHelper.cancel(this.a);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.a.get() == SubscriptionHelper.CANCELLED;
        }

        @Override // io.reactivex.m
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.c, bVar)) {
                this.c = bVar;
                this.a.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            this.c = DisposableHelper.DISPOSED;
            this.a.value = t;
            a();
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.c = DisposableHelper.DISPOSED;
            this.a.error = th;
            a();
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.c = DisposableHelper.DISPOSED;
            a();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.b.subscribe(this.a);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class OtherSubscriber<T> extends AtomicReference<d> implements j<Object> {
        private static final long serialVersionUID = -1215060610805418006L;
        final m<? super T> downstream;
        Throwable error;
        T value;

        OtherSubscriber(m<? super T> mVar) {
            this.downstream = mVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
        }

        public void onNext(Object obj) {
            SubscriptionHelper subscriptionHelper = (d) get();
            if (subscriptionHelper != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                subscriptionHelper.cancel();
                onComplete();
            }
        }

        public void onError(Throwable th) {
            Throwable th2 = this.error;
            if (th2 == null) {
                this.downstream.onError(th);
            } else {
                this.downstream.onError(new CompositeException(th2, th));
            }
        }

        public void onComplete() {
            Throwable th = this.error;
            if (th != null) {
                this.downstream.onError(th);
                return;
            }
            T t = this.value;
            if (t != null) {
                this.downstream.onSuccess(t);
            } else {
                this.downstream.onComplete();
            }
        }
    }
}
