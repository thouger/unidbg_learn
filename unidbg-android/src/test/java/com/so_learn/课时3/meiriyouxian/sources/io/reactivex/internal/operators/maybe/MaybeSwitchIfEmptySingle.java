package io.reactivex.internal.operators.maybe;

import io.reactivex.ab;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeSwitchIfEmptySingle<T> extends x<T> {
    final o<T> a;
    final ab<? extends T> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        this.a.a(new SwitchIfEmptyMaybeObserver(zVar, this.b));
    }

    static final class SwitchIfEmptyMaybeObserver<T> extends AtomicReference<b> implements b, m<T> {
        private static final long serialVersionUID = 4603919676453758899L;
        final z<? super T> downstream;
        final ab<? extends T> other;

        SwitchIfEmptyMaybeObserver(z<? super T> zVar, ab<? extends T> abVar) {
            this.downstream = zVar;
            this.other = abVar;
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

        static final class a<T> implements z<T> {
            final z<? super T> a;
            final AtomicReference<b> b;

            a(z<? super T> zVar, AtomicReference<b> atomicReference) {
                this.a = zVar;
                this.b = atomicReference;
            }

            @Override // io.reactivex.z
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this.b, bVar);
            }

            @Override // io.reactivex.z
            public void onSuccess(T t) {
                this.a.onSuccess(t);
            }

            @Override // io.reactivex.z
            public void onError(Throwable th) {
                this.a.onError(th);
            }
        }
    }
}
