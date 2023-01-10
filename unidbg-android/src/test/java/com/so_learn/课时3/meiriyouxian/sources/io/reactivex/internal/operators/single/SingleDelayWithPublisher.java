package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.e.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.e;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.d;

public final class SingleDelayWithPublisher<T, U> extends x<T> {
    final ab<T> a;
    final b<U> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        this.b.subscribe(new OtherSubscriber(zVar, this.a));
    }

    static final class OtherSubscriber<T, U> extends AtomicReference<io.reactivex.disposables.b> implements io.reactivex.disposables.b, j<U> {
        private static final long serialVersionUID = -8565274649390031272L;
        boolean done;
        final z<? super T> downstream;
        final ab<T> source;
        d upstream;

        OtherSubscriber(z<? super T> zVar, ab<T> abVar) {
            this.downstream = zVar;
            this.source = abVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(U u) {
            this.upstream.cancel();
            onComplete();
        }

        public void onError(Throwable th) {
            if (this.done) {
                a.a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.source.a(new e(this, this.downstream));
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.upstream.cancel();
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
