package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.disposables.b;
import io.reactivex.e.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.e;
import io.reactivex.t;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleDelayWithObservable<T, U> extends x<T> {
    final ab<T> a;
    final t<U> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        this.b.subscribe(new OtherSubscriber(zVar, this.a));
    }

    static final class OtherSubscriber<T, U> extends AtomicReference<b> implements b, v<U> {
        private static final long serialVersionUID = -8565274649390031272L;
        boolean done;
        final z<? super T> downstream;
        final ab<T> source;

        OtherSubscriber(z<? super T> zVar, ab<T> abVar) {
            this.downstream = zVar;
            this.source = abVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.set(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(U u) {
            get().dispose();
            onComplete();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.done) {
                a.a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.source.a(new e(this, this.downstream));
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
