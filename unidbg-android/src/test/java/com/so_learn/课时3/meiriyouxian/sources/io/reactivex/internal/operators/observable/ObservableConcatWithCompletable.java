package io.reactivex.internal.operators.observable;

import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableConcatWithCompletable<T> extends a<T, T> {
    final e b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new ConcatWithObserver(vVar, this.b));
    }

    static final class ConcatWithObserver<T> extends AtomicReference<b> implements c, b, v<T> {
        private static final long serialVersionUID = -1953724749712440952L;
        final v<? super T> downstream;
        boolean inCompletable;
        e other;

        ConcatWithObserver(v<? super T> vVar, e eVar) {
            this.downstream = vVar;
            this.other = eVar;
        }

        @Override // io.reactivex.c
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar) && !this.inCompletable) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
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
            DisposableHelper.replace(this, null);
            e eVar = this.other;
            this.other = null;
            eVar.a(this);
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
