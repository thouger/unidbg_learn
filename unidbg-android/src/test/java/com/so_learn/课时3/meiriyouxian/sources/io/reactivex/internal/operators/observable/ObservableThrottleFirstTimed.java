package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.e.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.c;
import io.reactivex.t;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableThrottleFirstTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final w d;

    public ObservableThrottleFirstTimed(t<T> tVar, long j, TimeUnit timeUnit, w wVar) {
        super(tVar);
        this.b = j;
        this.c = timeUnit;
        this.d = wVar;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new DebounceTimedObserver(new c(vVar), this.b, this.c, this.d.a()));
    }

    static final class DebounceTimedObserver<T> extends AtomicReference<b> implements b, v<T>, Runnable {
        private static final long serialVersionUID = 786994795061867455L;
        boolean done;
        final v<? super T> downstream;
        volatile boolean gate;
        final long timeout;
        final TimeUnit unit;
        b upstream;
        final w.c worker;

        DebounceTimedObserver(v<? super T> vVar, long j, TimeUnit timeUnit, w.c cVar) {
            this.downstream = vVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
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
            if (!this.gate && !this.done) {
                this.gate = true;
                this.downstream.onNext(t);
                b bVar = get();
                if (bVar != null) {
                    bVar.dispose();
                }
                DisposableHelper.replace(this, this.worker.a(this, this.timeout, this.unit));
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.gate = false;
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.done) {
                a.a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
            this.worker.dispose();
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.upstream.dispose();
            this.worker.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.worker.isDisposed();
        }
    }
}
