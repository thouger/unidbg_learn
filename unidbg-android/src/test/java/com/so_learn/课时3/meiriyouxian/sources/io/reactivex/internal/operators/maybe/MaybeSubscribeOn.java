package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.w;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeSubscribeOn<T> extends a<T, T> {
    final w b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        SubscribeOnMaybeObserver subscribeOnMaybeObserver = new SubscribeOnMaybeObserver(mVar);
        mVar.onSubscribe(subscribeOnMaybeObserver);
        subscribeOnMaybeObserver.task.replace(this.b.a(new a(subscribeOnMaybeObserver, this.a)));
    }

    static final class a<T> implements Runnable {
        final m<? super T> a;
        final o<T> b;

        a(m<? super T> mVar, o<T> oVar) {
            this.a = mVar;
            this.b = oVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b.a(this.a);
        }
    }

    static final class SubscribeOnMaybeObserver<T> extends AtomicReference<b> implements b, m<T> {
        private static final long serialVersionUID = 8571289934935992137L;
        final m<? super T> downstream;
        final SequentialDisposable task = new SequentialDisposable();

        SubscribeOnMaybeObserver(m<? super T> mVar) {
            this.downstream = mVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
            this.task.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
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
}
