package io.reactivex.internal.operators.mixed;

import io.reactivex.e;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class CompletableAndThenPublisher<R> extends g<R> {
    final e b;
    final b<? extends R> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super R> cVar) {
        this.b.a(new AndThenPublisherSubscriber(cVar, this.c));
    }

    static final class AndThenPublisherSubscriber<R> extends AtomicReference<d> implements io.reactivex.c, j<R>, d {
        private static final long serialVersionUID = -8948264376121066672L;
        final c<? super R> downstream;
        b<? extends R> other;
        final AtomicLong requested = new AtomicLong();
        io.reactivex.disposables.b upstream;

        AndThenPublisherSubscriber(c<? super R> cVar, b<? extends R> bVar) {
            this.downstream = cVar;
            this.other = bVar;
        }

        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // io.reactivex.c
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.c
        public void onComplete() {
            b<? extends R> bVar = this.other;
            if (bVar == null) {
                this.downstream.onComplete();
                return;
            }
            this.other = null;
            bVar.subscribe(this);
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this, this.requested, j);
        }

        public void cancel() {
            this.upstream.dispose();
            SubscriptionHelper.cancel(this);
        }

        @Override // io.reactivex.c
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.deferredSetOnce(this, this.requested, dVar);
        }
    }
}
