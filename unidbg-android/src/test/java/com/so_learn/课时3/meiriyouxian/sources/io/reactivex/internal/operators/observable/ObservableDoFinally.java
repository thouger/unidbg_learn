package io.reactivex.internal.operators.observable;

import io.reactivex.c.a;
import io.reactivex.disposables.b;
import io.reactivex.internal.a.c;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.v;

public final class ObservableDoFinally<T> extends a<T, T> {
    final a b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new DoFinallyObserver(vVar, this.b));
    }

    static final class DoFinallyObserver<T> extends BasicIntQueueDisposable<T> implements v<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final v<? super T> downstream;
        final a onFinally;
        c<T> qd;
        boolean syncFused;
        b upstream;

        DoFinallyObserver(v<? super T> vVar, a aVar) {
            this.downstream = vVar;
            this.onFinally = aVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                if (bVar instanceof c) {
                    this.qd = (c) bVar;
                }
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.downstream.onComplete();
            runFinally();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.upstream.dispose();
            runFinally();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            c<T> cVar = this.qd;
            if (cVar == null || (i & 4) != 0) {
                return 0;
            }
            int requestFusion = cVar.requestFusion(i);
            if (requestFusion != 0) {
                boolean z = true;
                if (requestFusion != 1) {
                    z = false;
                }
                this.syncFused = z;
            }
            return requestFusion;
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            this.qd.clear();
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.qd.isEmpty();
        }

        @Override // io.reactivex.internal.a.h
        public T poll() throws Exception {
            T poll = this.qd.poll();
            if (poll == null && this.syncFused) {
                runFinally();
            }
            return poll;
        }

        /* access modifiers changed from: package-private */
        public void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.e.a.a(th);
                }
            }
        }
    }
}
