package io.reactivex.internal.operators.maybe;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeOnErrorNext<T> extends a<T, T> {
    final h<? super Throwable, ? extends o<? extends T>> b;
    final boolean c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        this.a.a(new OnErrorNextMaybeObserver(mVar, this.b, this.c));
    }

    static final class OnErrorNextMaybeObserver<T> extends AtomicReference<b> implements b, m<T> {
        private static final long serialVersionUID = 2026620218879969836L;
        final boolean allowFatal;
        final m<? super T> downstream;
        final h<? super Throwable, ? extends o<? extends T>> resumeFunction;

        OnErrorNextMaybeObserver(m<? super T> mVar, h<? super Throwable, ? extends o<? extends T>> hVar, boolean z) {
            this.downstream = mVar;
            this.resumeFunction = hVar;
            this.allowFatal = z;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            if (this.allowFatal || (th instanceof Exception)) {
                try {
                    o oVar = (o) io.reactivex.internal.functions.a.a(this.resumeFunction.apply(th), "The resumeFunction returned a null MaybeSource");
                    DisposableHelper.replace(this, null);
                    oVar.a(new a(this.downstream, this));
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    this.downstream.onError(new CompositeException(th, th2));
                }
            } else {
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.downstream.onComplete();
        }

        static final class a<T> implements m<T> {
            final m<? super T> a;
            final AtomicReference<b> b;

            a(m<? super T> mVar, AtomicReference<b> atomicReference) {
                this.a = mVar;
                this.b = atomicReference;
            }

            @Override // io.reactivex.m
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this.b, bVar);
            }

            @Override // io.reactivex.m
            public void onSuccess(T t) {
                this.a.onSuccess(t);
            }

            @Override // io.reactivex.m
            public void onError(Throwable th) {
                this.a.onError(th);
            }

            @Override // io.reactivex.m
            public void onComplete() {
                this.a.onComplete();
            }
        }
    }
}
