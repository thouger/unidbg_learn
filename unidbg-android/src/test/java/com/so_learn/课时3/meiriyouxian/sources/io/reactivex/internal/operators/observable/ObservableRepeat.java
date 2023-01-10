package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableRepeat<T> extends a<T, T> {
    final long b;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        vVar.onSubscribe(sequentialDisposable);
        long j = this.b;
        long j2 = Long.MAX_VALUE;
        if (j != Long.MAX_VALUE) {
            j2 = j - 1;
        }
        new RepeatObserver(vVar, j2, sequentialDisposable, this.a).subscribeNext();
    }

    static final class RepeatObserver<T> extends AtomicInteger implements v<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final v<? super T> downstream;
        long remaining;
        final SequentialDisposable sd;
        final t<? extends T> source;

        RepeatObserver(v<? super T> vVar, long j, SequentialDisposable sequentialDisposable, t<? extends T> tVar) {
            this.downstream = vVar;
            this.sd = sequentialDisposable;
            this.source = tVar;
            this.remaining = j;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            this.sd.replace(bVar);
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
            long j = this.remaining;
            if (j != Long.MAX_VALUE) {
                this.remaining = j - 1;
            }
            if (j != 0) {
                subscribeNext();
            } else {
                this.downstream.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        public void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.sd.isDisposed()) {
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }
}
