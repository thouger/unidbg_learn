package io.reactivex.internal.operators.observable;

import io.reactivex.c.c;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.v;

/* compiled from: ObservableReduceMaybe */
public final class t<T> extends k<T> {
    final io.reactivex.t<T> a;
    final c<T, T, T> b;

    public t(io.reactivex.t<T> tVar, c<T, T, T> cVar) {
        this.a = tVar;
        this.b = cVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        this.a.subscribe(new a(mVar, this.b));
    }

    /* compiled from: ObservableReduceMaybe */
    static final class a<T> implements b, v<T> {
        final m<? super T> a;
        final c<T, T, T> b;
        boolean c;
        T d;
        b e;

        a(m<? super T> mVar, c<T, T, T> cVar) {
            this.a = mVar;
            this.b = cVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.e, bVar)) {
                this.e = bVar;
                this.a.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (!this.c) {
                T t2 = this.d;
                if (t2 == null) {
                    this.d = t;
                    return;
                }
                try {
                    this.d = (T) io.reactivex.internal.functions.a.a(this.b.apply(t2, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.e.dispose();
                    onError(th);
                }
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.c) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.c = true;
            this.d = null;
            this.a.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.c) {
                this.c = true;
                T t = this.d;
                this.d = null;
                if (t != null) {
                    this.a.onSuccess(t);
                } else {
                    this.a.onComplete();
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.e.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.e.isDisposed();
        }
    }
}
