package io.reactivex.internal.operators.observable;

import io.reactivex.c.g;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;
import io.reactivex.v;

/* compiled from: ObservableDoOnEach */
public final class e<T> extends a<T, T> {
    final g<? super T> b;
    final g<? super Throwable> c;
    final io.reactivex.c.a d;
    final io.reactivex.c.a e;

    public e(t<T> tVar, g<? super T> gVar, g<? super Throwable> gVar2, io.reactivex.c.a aVar, io.reactivex.c.a aVar2) {
        super(tVar);
        this.b = gVar;
        this.c = gVar2;
        this.d = aVar;
        this.e = aVar2;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new a(vVar, this.b, this.c, this.d, this.e));
    }

    /* compiled from: ObservableDoOnEach */
    static final class a<T> implements b, v<T> {
        final v<? super T> a;
        final g<? super T> b;
        final g<? super Throwable> c;
        final io.reactivex.c.a d;
        final io.reactivex.c.a e;
        b f;
        boolean g;

        a(v<? super T> vVar, g<? super T> gVar, g<? super Throwable> gVar2, io.reactivex.c.a aVar, io.reactivex.c.a aVar2) {
            this.a = vVar;
            this.b = gVar;
            this.c = gVar2;
            this.d = aVar;
            this.e = aVar2;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.f, bVar)) {
                this.f = bVar;
                this.a.onSubscribe(this);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f.isDisposed();
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (!this.g) {
                try {
                    this.b.accept(t);
                    this.a.onNext(t);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.f.dispose();
                    onError(th);
                }
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.g) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.g = true;
            try {
                this.c.accept(th);
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                th = new CompositeException(th, th2);
            }
            this.a.onError(th);
            try {
                this.e.a();
            } catch (Throwable th3) {
                io.reactivex.exceptions.a.b(th3);
                io.reactivex.e.a.a(th3);
            }
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.g) {
                try {
                    this.d.a();
                    this.g = true;
                    this.a.onComplete();
                    try {
                        this.e.a();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        io.reactivex.e.a.a(th);
                    }
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    onError(th2);
                }
            }
        }
    }
}
