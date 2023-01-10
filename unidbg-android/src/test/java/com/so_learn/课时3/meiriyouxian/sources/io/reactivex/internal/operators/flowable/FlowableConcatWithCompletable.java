package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableConcatWithCompletable<T> extends a<T, T> {
    final e c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new ConcatWithSubscriber(cVar, this.c));
    }

    static final class ConcatWithSubscriber<T> extends AtomicReference<b> implements io.reactivex.c, j<T>, d {
        private static final long serialVersionUID = -7346385463600070225L;
        final c<? super T> downstream;
        boolean inCompletable;
        e other;
        d upstream;

        ConcatWithSubscriber(c<? super T> cVar, e eVar) {
            this.downstream = cVar;
            this.other = eVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.c
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.c
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.c
        public void onComplete() {
            if (this.inCompletable) {
                this.downstream.onComplete();
                return;
            }
            this.inCompletable = true;
            this.upstream = SubscriptionHelper.CANCELLED;
            e eVar = this.other;
            this.other = null;
            eVar.a(this);
        }

        public void request(long j) {
            this.upstream.request(j);
        }

        public void cancel() {
            this.upstream.cancel();
            DisposableHelper.dispose(this);
        }
    }
}
