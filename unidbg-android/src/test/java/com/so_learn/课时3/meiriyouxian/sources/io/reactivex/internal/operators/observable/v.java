package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;
import io.reactivex.x;
import io.reactivex.z;
import java.util.NoSuchElementException;

/* compiled from: ObservableSingleSingle */
public final class v<T> extends x<T> {
    final t<? extends T> a;
    final T b;

    public v(t<? extends T> tVar, T t) {
        this.a = tVar;
        this.b = t;
    }

    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        this.a.subscribe(new a(zVar, this.b));
    }

    /* compiled from: ObservableSingleSingle */
    static final class a<T> implements b, io.reactivex.v<T> {
        final z<? super T> a;
        final T b;
        b c;
        T d;
        boolean e;

        a(z<? super T> zVar, T t) {
            this.a = zVar;
            this.b = t;
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
            if (!this.e) {
                if (this.d != null) {
                    this.e = true;
                    this.c.dispose();
                    this.a.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                    return;
                }
                this.d = t;
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.e) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.e = true;
            this.a.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.e) {
                this.e = true;
                T t = this.d;
                this.d = null;
                if (t == null) {
                    t = this.b;
                }
                if (t != null) {
                    this.a.onSuccess(t);
                } else {
                    this.a.onError(new NoSuchElementException());
                }
            }
        }
    }
}
