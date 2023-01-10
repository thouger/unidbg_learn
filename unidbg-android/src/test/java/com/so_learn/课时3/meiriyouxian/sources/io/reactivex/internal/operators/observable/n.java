package io.reactivex.internal.operators.observable;

import io.reactivex.c;
import io.reactivex.internal.a.b;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;

/* compiled from: ObservableIgnoreElementsCompletable */
public final class n<T> extends io.reactivex.a implements b<T> {
    final t<T> a;

    public n(t<T> tVar) {
        this.a = tVar;
    }

    @Override // io.reactivex.a
    public void b(c cVar) {
        this.a.subscribe(new a(cVar));
    }

    @Override // io.reactivex.internal.a.b
    public q<T> a() {
        return io.reactivex.e.a.a(new m(this.a));
    }

    /* compiled from: ObservableIgnoreElementsCompletable */
    static final class a<T> implements io.reactivex.disposables.b, v<T> {
        final c a;
        io.reactivex.disposables.b b;

        @Override // io.reactivex.v
        public void onNext(T t) {
        }

        a(c cVar) {
            this.a = cVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(io.reactivex.disposables.b bVar) {
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
