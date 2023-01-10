package io.reactivex.internal.operators.observable;

import io.reactivex.ab;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableConcatWithSingle<T> extends a<T, T> {
    final ab<? extends T> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new ConcatWithObserver(vVar, this.b));
    }

    static final class ConcatWithObserver<T> extends AtomicReference<b> implements b, v<T>, z<T> {
        private static final long serialVersionUID = -1953724749712440952L;
        final v<? super T> downstream;
        boolean inSingle;
        ab<? extends T> other;

        ConcatWithObserver(v<? super T> vVar, ab<? extends T> abVar) {
            this.downstream = vVar;
            this.other = abVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar) && !this.inSingle) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            this.downstream.onNext(t);
            this.downstream.onComplete();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.inSingle = true;
            DisposableHelper.replace(this, null);
            ab<? extends T> abVar = this.other;
            this.other = null;
            abVar.a(this);
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
