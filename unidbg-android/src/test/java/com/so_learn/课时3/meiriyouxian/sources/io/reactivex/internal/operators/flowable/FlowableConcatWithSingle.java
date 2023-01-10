package io.reactivex.internal.operators.flowable;

import io.reactivex.ab;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;

public final class FlowableConcatWithSingle<T> extends a<T, T> {
    final ab<? extends T> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new ConcatWithSubscriber(cVar, this.c));
    }

    static final class ConcatWithSubscriber<T> extends SinglePostCompleteSubscriber<T, T> implements z<T> {
        private static final long serialVersionUID = -7346385463600070225L;
        ab<? extends T> other;
        final AtomicReference<b> otherDisposable = new AtomicReference<>();

        ConcatWithSubscriber(c<? super T> cVar, ab<? extends T> abVar) {
            super(cVar);
            this.other = abVar;
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.otherDisposable, bVar);
        }

        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            complete(t);
        }

        public void onComplete() {
            this.upstream = SubscriptionHelper.CANCELLED;
            ab<? extends T> abVar = this.other;
            this.other = null;
            abVar.a(this);
        }

        @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber
        public void cancel() {
            super.cancel();
            DisposableHelper.dispose(this.otherDisposable);
        }
    }
}
