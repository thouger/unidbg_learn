package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.observers.b;
import io.reactivex.q;
import io.reactivex.v;
import java.util.Iterator;

/* compiled from: ObservableFromIterable */
public final class k<T> extends q<T> {
    final Iterable<? extends T> a;

    public k(Iterable<? extends T> iterable) {
        this.a = iterable;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        try {
            Iterator<? extends T> it2 = this.a.iterator();
            try {
                if (!it2.hasNext()) {
                    EmptyDisposable.complete(vVar);
                    return;
                }
                a aVar = new a(vVar, it2);
                vVar.onSubscribe(aVar);
                if (!aVar.d) {
                    aVar.a();
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptyDisposable.error(th, vVar);
            }
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            EmptyDisposable.error(th2, vVar);
        }
    }

    /* compiled from: ObservableFromIterable */
    static final class a<T> extends b<T> {
        final v<? super T> a;
        final Iterator<? extends T> b;
        volatile boolean c;
        boolean d;
        boolean e;
        boolean f;

        a(v<? super T> vVar, Iterator<? extends T> it2) {
            this.a = vVar;
            this.b = it2;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            while (!isDisposed()) {
                try {
                    this.a.onNext(io.reactivex.internal.functions.a.a(this.b.next(), "The iterator returned a null value"));
                    if (!isDisposed()) {
                        try {
                            if (!this.b.hasNext()) {
                                if (!isDisposed()) {
                                    this.a.onComplete();
                                    return;
                                }
                                return;
                            }
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            this.a.onError(th);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    this.a.onError(th2);
                    return;
                }
            }
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.d = true;
            return 1;
        }

        @Override // io.reactivex.internal.a.h
        public T poll() {
            if (this.e) {
                return null;
            }
            if (!this.f) {
                this.f = true;
            } else if (!this.b.hasNext()) {
                this.e = true;
                return null;
            }
            return (T) io.reactivex.internal.functions.a.a(this.b.next(), "The iterator returned a null value");
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.e;
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            this.e = true;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.c = true;
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.c;
        }
    }
}
