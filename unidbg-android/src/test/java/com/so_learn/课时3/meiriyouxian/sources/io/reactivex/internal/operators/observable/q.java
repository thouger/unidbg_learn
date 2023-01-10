package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.t;
import io.reactivex.v;

/* compiled from: ObservableOnErrorNext */
public final class q<T> extends a<T, T> {
    final h<? super Throwable, ? extends t<? extends T>> b;
    final boolean c;

    public q(t<T> tVar, h<? super Throwable, ? extends t<? extends T>> hVar, boolean z) {
        super(tVar);
        this.b = hVar;
        this.c = z;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        a aVar = new a(vVar, this.b, this.c);
        vVar.onSubscribe(aVar.d);
        this.a.subscribe(aVar);
    }

    /* compiled from: ObservableOnErrorNext */
    static final class a<T> implements v<T> {
        final v<? super T> a;
        final h<? super Throwable, ? extends t<? extends T>> b;
        final boolean c;
        final SequentialDisposable d = new SequentialDisposable();
        boolean e;
        boolean f;

        a(v<? super T> vVar, h<? super Throwable, ? extends t<? extends T>> hVar, boolean z) {
            this.a = vVar;
            this.b = hVar;
            this.c = z;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            this.d.replace(bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (!this.f) {
                this.a.onNext(t);
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (!this.e) {
                this.e = true;
                if (!this.c || (th instanceof Exception)) {
                    try {
                        t tVar = (t) this.b.apply(th);
                        if (tVar == null) {
                            NullPointerException nullPointerException = new NullPointerException("Observable is null");
                            nullPointerException.initCause(th);
                            this.a.onError(nullPointerException);
                            return;
                        }
                        tVar.subscribe(this);
                    } catch (Throwable th2) {
                        io.reactivex.exceptions.a.b(th2);
                        this.a.onError(new CompositeException(th, th2));
                    }
                } else {
                    this.a.onError(th);
                }
            } else if (this.f) {
                io.reactivex.e.a.a(th);
            } else {
                this.a.onError(th);
            }
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.f) {
                this.f = true;
                this.e = true;
                this.a.onComplete();
            }
        }
    }
}
