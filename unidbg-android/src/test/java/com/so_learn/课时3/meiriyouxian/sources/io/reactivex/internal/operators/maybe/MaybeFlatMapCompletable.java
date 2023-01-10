package io.reactivex.internal.operators.maybe;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeFlatMapCompletable<T> extends a {
    final o<T> a;
    final h<? super T, ? extends e> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.a
    public void b(c cVar) {
        FlatMapCompletableObserver flatMapCompletableObserver = new FlatMapCompletableObserver(cVar, this.b);
        cVar.onSubscribe(flatMapCompletableObserver);
        this.a.a(flatMapCompletableObserver);
    }

    static final class FlatMapCompletableObserver<T> extends AtomicReference<b> implements c, b, m<T> {
        private static final long serialVersionUID = -2177128922851101253L;
        final c downstream;
        final h<? super T, ? extends e> mapper;

        FlatMapCompletableObserver(c cVar, h<? super T, ? extends e> hVar) {
            this.downstream = cVar;
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

        @Override // io.reactivex.c
        public void onSubscribe(b bVar) {
            DisposableHelper.replace(this, bVar);
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            try {
                e eVar = (e) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null CompletableSource");
                if (!isDisposed()) {
                    eVar.a(this);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                onError(th);
            }
        }

        @Override // io.reactivex.c
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.c
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
