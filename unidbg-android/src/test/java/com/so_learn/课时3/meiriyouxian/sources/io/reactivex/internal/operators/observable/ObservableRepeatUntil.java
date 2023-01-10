package io.reactivex.internal.operators.observable;

import io.reactivex.c.e;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableRepeatUntil<T> extends a<T, T> {
    final e b;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        vVar.onSubscribe(sequentialDisposable);
        new RepeatUntilObserver(vVar, this.b, sequentialDisposable, this.a).subscribeNext();
    }

    static final class RepeatUntilObserver<T> extends AtomicInteger implements v<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final v<? super T> downstream;
        final t<? extends T> source;
        final e stop;
        final SequentialDisposable upstream;

        RepeatUntilObserver(v<? super T> vVar, e eVar, SequentialDisposable sequentialDisposable, t<? extends T> tVar) {
            this.downstream = vVar;
            this.upstream = sequentialDisposable;
            this.source = tVar;
            this.stop = eVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            this.upstream.replace(bVar);
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
            try {
                if (this.stop.getAsBoolean()) {
                    this.downstream.onComplete();
                } else {
                    subscribeNext();
                }
            } catch (Throwable th) {
                a.b(th);
                this.downstream.onError(th);
            }
        }

        /* access modifiers changed from: package-private */
        public void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                do {
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }
    }
}
