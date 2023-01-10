package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.t;
import io.reactivex.v;

/* compiled from: ObservableSingleMaybe */
public final class u<T> extends k<T> {
    final t<T> a;

    public u(t<T> tVar) {
        this.a = tVar;
    }

    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        this.a.subscribe(new a(mVar));
    }

    /* compiled from: ObservableSingleMaybe */
    static final class a<T> implements b, v<T> {
        final m<? super T> a;
        b b;
        T c;
        boolean d;

        a(m<? super T> mVar) {
            this.a = mVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.b, bVar)) {
                this.b = bVar;
                this.a.onSubscribe(this);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.b.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.b.isDisposed();
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (!this.d) {
                if (this.c != null) {
                    this.d = true;
                    this.b.dispose();
                    this.a.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                    return;
                }
                this.c = t;
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.d) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.d = true;
            this.a.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.d) {
                this.d = true;
                T t = this.c;
                this.c = null;
                if (t == null) {
                    this.a.onComplete();
                } else {
                    this.a.onSuccess(t);
                }
            }
        }
    }
}
