package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.b;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.m;
import io.reactivex.o;
import org.a.c;

public final class MaybeToFlowable<T> extends g<T> {
    final o<T> b;

    public MaybeToFlowable(o<T> oVar) {
        this.b = oVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a(new MaybeToFlowableSubscriber(cVar));
    }

    static final class MaybeToFlowableSubscriber<T> extends DeferredScalarSubscription<T> implements m<T> {
        private static final long serialVersionUID = 7603343402964826922L;
        b upstream;

        MaybeToFlowableSubscriber(c<? super T> cVar) {
            super(cVar);
        }

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            complete(t);
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription
        public void cancel() {
            super.cancel();
            this.upstream.dispose();
        }
    }
}
