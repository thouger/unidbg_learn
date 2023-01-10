package io.reactivex.internal.operators.observable;

import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableMergeWithCompletable<T> extends a<T, T> {
    final e b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        MergeWithObserver mergeWithObserver = new MergeWithObserver(vVar);
        vVar.onSubscribe(mergeWithObserver);
        this.a.subscribe(mergeWithObserver);
        this.b.a(mergeWithObserver.otherObserver);
    }

    static final class MergeWithObserver<T> extends AtomicInteger implements b, v<T> {
        private static final long serialVersionUID = -4592979584110982903L;
        final v<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final AtomicReference<b> mainDisposable = new AtomicReference<>();
        volatile boolean mainDone;
        volatile boolean otherDone;
        final OtherObserver otherObserver = new OtherObserver(this);

        MergeWithObserver(v<? super T> vVar) {
            this.downstream = vVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.mainDisposable, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            io.reactivex.internal.util.e.a((v) this.downstream, (Object) t, (AtomicInteger) this, this.error);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.otherObserver);
            io.reactivex.internal.util.e.a((v<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.mainDone = true;
            if (this.otherDone) {
                io.reactivex.internal.util.e.a(this.downstream, this, this.error);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.mainDisposable.get());
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.mainDisposable);
            DisposableHelper.dispose(this.otherObserver);
        }

        /* access modifiers changed from: package-private */
        public void otherError(Throwable th) {
            DisposableHelper.dispose(this.mainDisposable);
            io.reactivex.internal.util.e.a((v<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        /* access modifiers changed from: package-private */
        public void otherComplete() {
            this.otherDone = true;
            if (this.mainDone) {
                io.reactivex.internal.util.e.a(this.downstream, this, this.error);
            }
        }

        static final class OtherObserver extends AtomicReference<b> implements c {
            private static final long serialVersionUID = -2935427570954647017L;
            final MergeWithObserver<?> parent;

            OtherObserver(MergeWithObserver<?> mergeWithObserver) {
                this.parent = mergeWithObserver;
            }

            @Override // io.reactivex.c
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.c
            public void onError(Throwable th) {
                this.parent.otherError(th);
            }

            @Override // io.reactivex.c
            public void onComplete() {
                this.parent.otherComplete();
            }
        }
    }
}
