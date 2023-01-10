package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.t;
import io.reactivex.v;

/* compiled from: ObservableIgnoreElements */
public final class m<T> extends a<T, T> {
    public m(t<T> tVar) {
        super(tVar);
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new a(vVar));
    }

    /* compiled from: ObservableIgnoreElements */
    static final class a<T> implements b, v<T> {
        final v<? super T> a;
        b b;

        @Override // io.reactivex.v
        public void onNext(T t) {
        }

        a(v<? super T> vVar) {
            this.a = vVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            this.b = bVar;
            this.a.onSubscribe(this);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.a.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.a.onComplete();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.b.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.b.isDisposed();
        }
    }
}
