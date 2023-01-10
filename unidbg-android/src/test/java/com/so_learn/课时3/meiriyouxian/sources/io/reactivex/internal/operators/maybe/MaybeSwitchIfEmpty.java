package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeSwitchIfEmpty<T> extends a<T, T> {
    final o<? extends T> b;

    public MaybeSwitchIfEmpty(o<T> oVar, o<? extends T> oVar2) {
        super(oVar);
        this.b = oVar2;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        this.a.a(new SwitchIfEmptyMaybeObserver(mVar, this.b));
    }

    static final class SwitchIfEmptyMaybeObserver<T> extends AtomicReference<b> implements b, m<T> {
        private static final long serialVersionUID = -2223459372976438024L;
        final m<? super T> downstream;
        final o<? extends T> other;

        SwitchIfEmptyMaybeObserver(m<? super T> mVar, o<? extends T> oVar) {
            this.downstream = mVar;
            this.other = oVar;
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
            this.downstream.onSuccess(t);
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            b bVar = get();
            if (bVar != DisposableHelper.DISPOSED && compareAndSet(bVar, null)) {
                this.other.a(new a(this.downstream, this));
            }
        }

        static final class a<T> implements m<T> {
            final m<? super T> a;
            final AtomicReference<b> b;

            a(m<? super T> mVar, AtomicReference<b> atomicReference) {
                this.a = mVar;
                this.b = atomicReference;
            }

            @Override // io.reactivex.m
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this.b, bVar);
            }

            @Override // io.reactivex.m
            public void onSuccess(T t) {
                this.a.onSuccess(t);
            }

            @Override // io.reactivex.m
            public void onError(Throwable th) {
                this.a.onError(th);
            }

            @Override // io.reactivex.m
            public void onComplete() {
                this.a.onComplete();
            }
        }
    }
}
