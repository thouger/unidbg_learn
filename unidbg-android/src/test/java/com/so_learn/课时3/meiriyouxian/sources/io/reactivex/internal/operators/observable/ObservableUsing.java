package io.reactivex.internal.operators.observable;

import io.reactivex.c.g;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableUsing<T, D> extends q<T> {
    final Callable<? extends D> a;
    final h<? super D, ? extends t<? extends T>> b;
    final g<? super D> c;
    final boolean d;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        try {
            Object call = this.a.call();
            try {
                ((t) a.a(this.b.apply(call), "The sourceSupplier returned a null ObservableSource")).subscribe(new UsingObserver(vVar, call, this.c, this.d));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptyDisposable.error(new CompositeException(th, th), vVar);
            }
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            EmptyDisposable.error(th2, vVar);
        }
    }

    static final class UsingObserver<T, D> extends AtomicBoolean implements b, v<T> {
        private static final long serialVersionUID = 5904473792286235046L;
        final g<? super D> disposer;
        final v<? super T> downstream;
        final boolean eager;
        final D resource;
        b upstream;

        UsingObserver(v<? super T> vVar, D d, g<? super D> gVar, boolean z) {
            this.downstream = vVar;
            this.resource = d;
            this.disposer = gVar;
            this.eager = z;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th2) {
                        io.reactivex.exceptions.a.b(th2);
                        th = new CompositeException(th, th2);
                    }
                }
                this.upstream.dispose();
                this.downstream.onError(th);
                return;
            }
            this.downstream.onError(th);
            this.upstream.dispose();
            disposeAfter();
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.downstream.onError(th);
                        return;
                    }
                }
                this.upstream.dispose();
                this.downstream.onComplete();
                return;
            }
            this.downstream.onComplete();
            this.upstream.dispose();
            disposeAfter();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            disposeAfter();
            this.upstream.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get();
        }

        /* access modifiers changed from: package-private */
        public void disposeAfter() {
            if (compareAndSet(false, true)) {
                try {
                    this.disposer.accept(this.resource);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.e.a.a(th);
                }
            }
        }
    }
}
