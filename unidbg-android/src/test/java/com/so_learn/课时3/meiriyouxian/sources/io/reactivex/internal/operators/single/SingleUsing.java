package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.c.g;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleUsing<T, U> extends x<T> {
    final Callable<U> a;
    final h<? super U, ? extends ab<? extends T>> b;
    final g<? super U> c;
    final boolean d;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        try {
            U call = this.a.call();
            try {
                ((ab) a.a(this.b.apply(call), "The singleFunction returned a null SingleSource")).a(new UsingSingleObserver(zVar, call, this.d, this.c));
                return;
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                io.reactivex.e.a.a(th);
                return;
            }
            EmptyDisposable.error(th, zVar);
            if (!this.d) {
                this.c.accept(call);
            }
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            EmptyDisposable.error(th2, zVar);
        }
    }

    static final class UsingSingleObserver<T, U> extends AtomicReference<Object> implements b, z<T> {
        private static final long serialVersionUID = -5331524057054083935L;
        final g<? super U> disposer;
        final z<? super T> downstream;
        final boolean eager;
        b upstream;

        UsingSingleObserver(z<? super T> zVar, U u, boolean z, g<? super U> gVar) {
            super(u);
            this.downstream = zVar;
            this.eager = z;
            this.disposer = gVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
            disposeAfter();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            this.upstream = DisposableHelper.DISPOSED;
            if (this.eager) {
                Object andSet = getAndSet(this);
                if (andSet != this) {
                    try {
                        this.disposer.accept(andSet);
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.downstream.onError(th);
                        return;
                    }
                } else {
                    return;
                }
            }
            this.downstream.onSuccess(t);
            if (!this.eager) {
                disposeAfter();
            }
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            if (this.eager) {
                Object andSet = getAndSet(this);
                if (andSet != this) {
                    try {
                        this.disposer.accept(andSet);
                    } catch (Throwable th2) {
                        io.reactivex.exceptions.a.b(th2);
                        th = new CompositeException(th, th2);
                    }
                } else {
                    return;
                }
            }
            this.downstream.onError(th);
            if (!this.eager) {
                disposeAfter();
            }
        }

        /* access modifiers changed from: package-private */
        public void disposeAfter() {
            Object andSet = getAndSet(this);
            if (andSet != this) {
                try {
                    this.disposer.accept(andSet);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.e.a.a(th);
                }
            }
        }
    }
}
