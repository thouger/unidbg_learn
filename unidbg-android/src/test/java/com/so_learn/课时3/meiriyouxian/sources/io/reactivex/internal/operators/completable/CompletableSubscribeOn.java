package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.w;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableSubscribeOn extends a {
    final e a;
    final w b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.a
    public void b(c cVar) {
        SubscribeOnObserver subscribeOnObserver = new SubscribeOnObserver(cVar, this.a);
        cVar.onSubscribe(subscribeOnObserver);
        subscribeOnObserver.task.replace(this.b.a(subscribeOnObserver));
    }

    static final class SubscribeOnObserver extends AtomicReference<b> implements c, b, Runnable {
        private static final long serialVersionUID = 7000911171163930287L;
        final c downstream;
        final e source;
        final SequentialDisposable task = new SequentialDisposable();

        SubscribeOnObserver(c cVar, e eVar) {
            this.downstream = cVar;
            this.source = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.source.a(this);
        }

        @Override // io.reactivex.c
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.c
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.c
        public void onComplete() {
            this.downstream.onComplete();
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
    }
}
