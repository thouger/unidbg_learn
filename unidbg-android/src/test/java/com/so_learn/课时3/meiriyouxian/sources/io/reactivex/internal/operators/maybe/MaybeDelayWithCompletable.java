package io.reactivex.internal.operators.maybe;

import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeDelayWithCompletable<T> extends k<T> {
    final o<T> a;
    final e b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        this.b.a(new OtherObserver(mVar, this.a));
    }

    static final class OtherObserver<T> extends AtomicReference<b> implements c, b {
        private static final long serialVersionUID = 703409937383992161L;
        final m<? super T> downstream;
        final o<T> source;

        OtherObserver(m<? super T> mVar, o<T> oVar) {
            this.downstream = mVar;
            this.source = oVar;
        }

        @Override // io.reactivex.c
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.c
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.c
        public void onComplete() {
            this.source.a(new a(this, this.downstream));
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }

    static final class a<T> implements m<T> {
        final AtomicReference<b> a;
        final m<? super T> b;

        a(AtomicReference<b> atomicReference, m<? super T> mVar) {
            this.a = atomicReference;
            this.b = mVar;
        }

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            DisposableHelper.replace(this.a, bVar);
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            this.b.onSuccess(t);
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.b.onError(th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.b.onComplete();
        }
    }
}
