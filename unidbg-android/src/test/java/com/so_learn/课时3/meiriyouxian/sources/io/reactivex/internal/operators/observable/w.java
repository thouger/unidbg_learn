package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.t;
import io.reactivex.v;

/* compiled from: ObservableSwitchIfEmpty */
public final class w<T> extends a<T, T> {
    final t<? extends T> b;

    public w(t<T> tVar, t<? extends T> tVar2) {
        super(tVar);
        this.b = tVar2;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        a aVar = new a(vVar, this.b);
        vVar.onSubscribe(aVar.c);
        this.a.subscribe(aVar);
    }

    /* compiled from: ObservableSwitchIfEmpty */
    static final class a<T> implements v<T> {
        final v<? super T> a;
        final t<? extends T> b;
        final SequentialDisposable c = new SequentialDisposable();
        boolean d = true;

        a(v<? super T> vVar, t<? extends T> tVar) {
            this.a = vVar;
            this.b = tVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            this.c.update(bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (this.d) {
                this.d = false;
            }
            this.a.onNext(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.a.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (this.d) {
                this.d = false;
                this.b.subscribe(this);
                return;
            }
            this.a.onComplete();
        }
    }
}
