package io.reactivex.internal.operators.maybe;

import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.d;

public final class MaybeDelaySubscriptionOtherPublisher<T, U> extends a<T, T> {
    final b<U> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        this.b.subscribe(new a(mVar, this.a));
    }

    static final class a<T> implements io.reactivex.disposables.b, j<Object> {
        final DelayMaybeObserver<T> a;
        o<T> b;
        d c;

        a(m<? super T> mVar, o<T> oVar) {
            this.a = new DelayMaybeObserver<>(mVar);
            this.b = oVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.c, dVar)) {
                this.c = dVar;
                this.a.downstream.onSubscribe(this);
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object obj) {
            if (this.c != SubscriptionHelper.CANCELLED) {
                this.c.cancel();
                this.c = SubscriptionHelper.CANCELLED;
                a();
            }
        }

        public void onError(Throwable th) {
            if (this.c != SubscriptionHelper.CANCELLED) {
                this.c = SubscriptionHelper.CANCELLED;
                this.a.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            if (this.c != SubscriptionHelper.CANCELLED) {
                this.c = SubscriptionHelper.CANCELLED;
                a();
            }
        }

        /* access modifiers changed from: package-private */
        public void a() {
            o<T> oVar = this.b;
            this.b = null;
            oVar.a(this.a);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.a.get());
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.c.cancel();
            this.c = SubscriptionHelper.CANCELLED;
            DisposableHelper.dispose(this.a);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class DelayMaybeObserver<T> extends AtomicReference<io.reactivex.disposables.b> implements m<T> {
        private static final long serialVersionUID = 706635022205076709L;
        final m<? super T> downstream;

        DelayMaybeObserver(m<? super T> mVar) {
            this.downstream = mVar;
        }

        @Override // io.reactivex.m
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
