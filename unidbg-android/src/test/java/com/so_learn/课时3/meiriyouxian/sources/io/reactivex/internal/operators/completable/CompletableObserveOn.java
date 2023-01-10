package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.w;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableObserveOn extends a {
    final e a;
    final w b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.a
    public void b(c cVar) {
        this.a.a(new ObserveOnCompletableObserver(cVar, this.b));
    }

    static final class ObserveOnCompletableObserver extends AtomicReference<b> implements c, b, Runnable {
        private static final long serialVersionUID = 8571289934935992137L;
        final c downstream;
        Throwable error;
        final w scheduler;

        ObserveOnCompletableObserver(c cVar, w wVar) {
            this.downstream = cVar;
            this.scheduler = wVar;
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
            if (DisposableHelper.setOnce(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.c
        public void onError(Throwable th) {
            this.error = th;
            DisposableHelper.replace(this, this.scheduler.a(this));
        }

        @Override // io.reactivex.c
        public void onComplete() {
            DisposableHelper.replace(this, this.scheduler.a(this));
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable th = this.error;
            if (th != null) {
                this.error = null;
                this.downstream.onError(th);
                return;
            }
            this.downstream.onComplete();
        }
    }
}
