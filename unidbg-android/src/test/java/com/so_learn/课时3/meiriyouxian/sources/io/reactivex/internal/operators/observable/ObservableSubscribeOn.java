package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSubscribeOn<T> extends a<T, T> {
    final w b;

    public ObservableSubscribeOn(t<T> tVar, w wVar) {
        super(tVar);
        this.b = wVar;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        SubscribeOnObserver subscribeOnObserver = new SubscribeOnObserver(vVar);
        vVar.onSubscribe(subscribeOnObserver);
        subscribeOnObserver.setDisposable(this.b.a(new a(subscribeOnObserver)));
    }

    static final class SubscribeOnObserver<T> extends AtomicReference<b> implements b, v<T> {
        private static final long serialVersionUID = 8094547886072529208L;
        final v<? super T> downstream;
        final AtomicReference<b> upstream = new AtomicReference<>();

        SubscribeOnObserver(v<? super T> vVar) {
            this.downstream = vVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        /* access modifiers changed from: package-private */
        public void setDisposable(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }
    }

    final class a implements Runnable {
        private final SubscribeOnObserver<T> b;

        a(SubscribeOnObserver<T> subscribeOnObserver) {
            this.b = subscribeOnObserver;
        }

        @Override // java.lang.Runnable
        public void run() {
            ObservableSubscribeOn.this.a.subscribe(this.b);
        }
    }
}
