package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;
import java.util.ArrayDeque;

public final class ObservableTakeLast<T> extends a<T, T> {
    final int b;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new TakeLastObserver(vVar, this.b));
    }

    static final class TakeLastObserver<T> extends ArrayDeque<T> implements b, v<T> {
        private static final long serialVersionUID = 7240042530241604978L;
        volatile boolean cancelled;
        final int count;
        final v<? super T> downstream;
        b upstream;

        TakeLastObserver(v<? super T> vVar, int i) {
            this.downstream = vVar;
            this.count = i;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (this.count == size()) {
                poll();
            }
            offer(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            v<? super T> vVar = this.downstream;
            while (!this.cancelled) {
                T poll = poll();
                if (poll != null) {
                    vVar.onNext(poll);
                } else if (!this.cancelled) {
                    vVar.onComplete();
                    return;
                } else {
                    return;
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
        }
    }
}
