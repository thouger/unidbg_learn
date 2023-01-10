package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;
import io.reactivex.v;

/* compiled from: ObservableOnErrorReturn */
public final class r<T> extends a<T, T> {
    final h<? super Throwable, ? extends T> b;

    public r(t<T> tVar, h<? super Throwable, ? extends T> hVar) {
        super(tVar);
        this.b = hVar;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new a(vVar, this.b));
    }

    /* compiled from: ObservableOnErrorReturn */
    static final class a<T> implements b, v<T> {
        final v<? super T> a;
        final h<? super Throwable, ? extends T> b;
        b c;

        a(v<? super T> vVar, h<? super Throwable, ? extends T> hVar) {
            this.a = vVar;
            this.b = hVar;
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
            this.a.onNext(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            try {
                Object apply = this.b.apply(th);
                if (apply == null) {
                    NullPointerException nullPointerException = new NullPointerException("The supplied value is null");
                    nullPointerException.initCause(th);
                    this.a.onError(nullPointerException);
                    return;
                }
                this.a.onNext(apply);
                this.a.onComplete();
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.a.onError(new CompositeException(th, th2));
            }
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.a.onComplete();
        }
    }
}
