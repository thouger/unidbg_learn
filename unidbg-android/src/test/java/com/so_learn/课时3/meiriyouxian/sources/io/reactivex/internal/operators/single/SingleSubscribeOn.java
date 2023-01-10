package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.w;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleSubscribeOn<T> extends x<T> {
    final ab<? extends T> a;
    final w b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        SubscribeOnObserver subscribeOnObserver = new SubscribeOnObserver(zVar, this.a);
        zVar.onSubscribe(subscribeOnObserver);
        subscribeOnObserver.task.replace(this.b.a(subscribeOnObserver));
    }

    static final class SubscribeOnObserver<T> extends AtomicReference<b> implements b, z<T>, Runnable {
        private static final long serialVersionUID = 7000911171163930287L;
        final z<? super T> downstream;
        final ab<? extends T> source;
        final SequentialDisposable task = new SequentialDisposable();

        SubscribeOnObserver(z<? super T> zVar, ab<? extends T> abVar) {
            this.downstream = zVar;
            this.source = abVar;
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.downstream.onError(th);
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

        @Override // java.lang.Runnable
        public void run() {
            this.source.a(this);
        }
    }
}
