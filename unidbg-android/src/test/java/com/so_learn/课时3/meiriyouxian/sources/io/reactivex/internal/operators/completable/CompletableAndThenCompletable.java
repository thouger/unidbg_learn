package io.reactivex.internal.operators.completable;

import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableAndThenCompletable extends io.reactivex.a {
    final e a;
    final e b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.a
    public void b(c cVar) {
        this.a.a(new SourceObserver(cVar, this.b));
    }

    static final class SourceObserver extends AtomicReference<b> implements c, b {
        private static final long serialVersionUID = -4101678820158072998L;
        final c actualObserver;
        final e next;

        SourceObserver(c cVar, e eVar) {
            this.actualObserver = cVar;
            this.next = eVar;
        }

        @Override // io.reactivex.c
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                this.actualObserver.onSubscribe(this);
            }
        }

        @Override // io.reactivex.c
        public void onError(Throwable th) {
            this.actualObserver.onError(th);
        }

        @Override // io.reactivex.c
        public void onComplete() {
            this.next.a(new a(this, this.actualObserver));
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

    static final class a implements c {
        final AtomicReference<b> a;
        final c b;

        a(AtomicReference<b> atomicReference, c cVar) {
            this.a = atomicReference;
            this.b = cVar;
        }

        @Override // io.reactivex.c
        public void onSubscribe(b bVar) {
            DisposableHelper.replace(this.a, bVar);
        }

        @Override // io.reactivex.c
        public void onComplete() {
            this.b.onComplete();
        }

        @Override // io.reactivex.c
        public void onError(Throwable th) {
            this.b.onError(th);
        }
    }
}
