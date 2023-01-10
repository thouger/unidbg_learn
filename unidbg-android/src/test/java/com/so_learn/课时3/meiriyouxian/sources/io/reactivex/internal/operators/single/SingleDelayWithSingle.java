package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.e;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleDelayWithSingle<T, U> extends x<T> {
    final ab<T> a;
    final ab<U> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        this.b.a(new OtherObserver(zVar, this.a));
    }

    static final class OtherObserver<T, U> extends AtomicReference<b> implements b, z<U> {
        private static final long serialVersionUID = -8565274649390031272L;
        final z<? super T> downstream;
        final ab<T> source;

        OtherObserver(z<? super T> zVar, ab<T> abVar) {
            this.downstream = zVar;
            this.source = abVar;
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.z
        public void onSuccess(U u) {
            this.source.a(new e(this, this.downstream));
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.downstream.onError(th);
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
