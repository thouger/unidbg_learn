package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;
import java.util.ArrayDeque;

public final class ObservableSkipLast<T> extends a<T, T> {
    final int b;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new SkipLastObserver(vVar, this.b));
    }

    static final class SkipLastObserver<T> extends ArrayDeque<T> implements b, v<T> {
        private static final long serialVersionUID = -3807491841935125653L;
        final v<? super T> downstream;
        final int skip;
        b upstream;

        SkipLastObserver(v<? super T> vVar, int i) {
            super(i);
            this.downstream = vVar;
            this.skip = i;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.upstream.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (this.skip == size()) {
                this.downstream.onNext(poll());
            }
            offer(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
