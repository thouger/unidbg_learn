package io.reactivex.internal.operators.mixed;

import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableAndThenObservable<R> extends q<R> {
    final e a;
    final t<? extends R> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super R> vVar) {
        AndThenObservableObserver andThenObservableObserver = new AndThenObservableObserver(vVar, this.b);
        vVar.onSubscribe(andThenObservableObserver);
        this.a.a(andThenObservableObserver);
    }

    static final class AndThenObservableObserver<R> extends AtomicReference<b> implements c, b, v<R> {
        private static final long serialVersionUID = -8948264376121066672L;
        final v<? super R> downstream;
        t<? extends R> other;

        AndThenObservableObserver(v<? super R> vVar, t<? extends R> tVar) {
            this.other = tVar;
            this.downstream = vVar;
        }

        @Override // io.reactivex.v
        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // io.reactivex.c
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.c
        public void onComplete() {
            t<? extends R> tVar = this.other;
            if (tVar == null) {
                this.downstream.onComplete();
                return;
            }
            this.other = null;
            tVar.subscribe(this);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.c
        public void onSubscribe(b bVar) {
            DisposableHelper.replace(this, bVar);
        }
    }
}
