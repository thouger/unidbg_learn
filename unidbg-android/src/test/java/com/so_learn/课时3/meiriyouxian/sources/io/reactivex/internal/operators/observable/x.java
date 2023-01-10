package io.reactivex.internal.operators.observable;

import io.reactivex.c.k;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;
import io.reactivex.v;

/* compiled from: ObservableTakeUntilPredicate */
public final class x<T> extends a<T, T> {
    final k<? super T> b;

    public x(t<T> tVar, k<? super T> kVar) {
        super(tVar);
        this.b = kVar;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new a(vVar, this.b));
    }

    /* compiled from: ObservableTakeUntilPredicate */
    static final class a<T> implements b, v<T> {
        final v<? super T> a;
        final k<? super T> b;
        b c;
        boolean d;

        a(v<? super T> vVar, k<? super T> kVar) {
            this.a = vVar;
            this.b = kVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.c, bVar)) {
                this.c = bVar;
                this.a.onSubscribe(this);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.c.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.c.isDisposed();
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (!this.d) {
                this.a.onNext(t);
                try {
                    if (this.b.test(t)) {
                        this.d = true;
                        this.c.dispose();
                        this.a.onComplete();
                    }
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.c.dispose();
                    onError(th);
                }
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (!this.d) {
                this.d = true;
                this.a.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.d) {
                this.d = true;
                this.a.onComplete();
            }
        }
    }
}
