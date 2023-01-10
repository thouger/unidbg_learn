package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;

public final class FlowableConcatWithMaybe<T> extends a<T, T> {
    final o<? extends T> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new ConcatWithSubscriber(cVar, this.c));
    }

    static final class ConcatWithSubscriber<T> extends SinglePostCompleteSubscriber<T, T> implements m<T> {
        private static final long serialVersionUID = -7346385463600070225L;
        boolean inMaybe;
        o<? extends T> other;
        final AtomicReference<b> otherDisposable = new AtomicReference<>();

        ConcatWithSubscriber(c<? super T> cVar, o<? extends T> oVar) {
            super(cVar);
            this.other = oVar;
        }

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.otherDisposable, bVar);
        }

        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            complete(t);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            if (this.inMaybe) {
                this.downstream.onComplete();
                return;
            }
            this.inMaybe = true;
            this.upstream = SubscriptionHelper.CANCELLED;
            o<? extends T> oVar = this.other;
            this.other = null;
            oVar.a(this);
        }

        @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber
        public void cancel() {
            super.cancel();
            DisposableHelper.dispose(this.otherDisposable);
        }
    }
}
