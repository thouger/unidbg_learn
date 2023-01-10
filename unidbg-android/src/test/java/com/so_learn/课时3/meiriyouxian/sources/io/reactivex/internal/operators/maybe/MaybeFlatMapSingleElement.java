package io.reactivex.internal.operators.maybe;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeFlatMapSingleElement<T, R> extends k<R> {
    final o<T> a;
    final h<? super T, ? extends ab<? extends R>> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super R> mVar) {
        this.a.a(new FlatMapMaybeObserver(mVar, this.b));
    }

    static final class FlatMapMaybeObserver<T, R> extends AtomicReference<b> implements b, m<T> {
        private static final long serialVersionUID = 4827726964688405508L;
        final m<? super R> downstream;
        final h<? super T, ? extends ab<? extends R>> mapper;

        FlatMapMaybeObserver(m<? super R> mVar, h<? super T, ? extends ab<? extends R>> hVar) {
            this.downstream = mVar;
            this.mapper = hVar;
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
            try {
                ((ab) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null SingleSource")).a(new a(this, this.downstream));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                onError(th);
            }
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.downstream.onComplete();
        }
    }

    static final class a<R> implements z<R> {
        final AtomicReference<b> a;
        final m<? super R> b;

        a(AtomicReference<b> atomicReference, m<? super R> mVar) {
            this.a = atomicReference;
            this.b = mVar;
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            DisposableHelper.replace(this.a, bVar);
        }

        @Override // io.reactivex.z
        public void onSuccess(R r) {
            this.b.onSuccess(r);
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.b.onError(th);
        }
    }
}
