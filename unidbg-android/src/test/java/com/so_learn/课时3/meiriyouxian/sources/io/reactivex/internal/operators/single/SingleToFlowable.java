package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.disposables.b;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.z;
import org.a.c;

public final class SingleToFlowable<T> extends g<T> {
    final ab<? extends T> b;

    public SingleToFlowable(ab<? extends T> abVar) {
        this.b = abVar;
    }

    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a(new SingleToFlowableObserver(cVar));
    }

    static final class SingleToFlowableObserver<T> extends DeferredScalarSubscription<T> implements z<T> {
        private static final long serialVersionUID = 187782011903685568L;
        b upstream;

        SingleToFlowableObserver(c<? super T> cVar) {
            super(cVar);
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            complete(t);
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription
        public void cancel() {
            super.cancel();
            this.upstream.dispose();
        }
    }
}
