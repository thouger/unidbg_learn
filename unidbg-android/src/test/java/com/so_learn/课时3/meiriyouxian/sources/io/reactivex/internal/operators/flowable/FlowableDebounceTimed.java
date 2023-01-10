package io.reactivex.internal.operators.flowable;

import io.reactivex.e.a;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.subscribers.b;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableDebounceTimed<T> extends a<T, T> {
    final long c;
    final TimeUnit d;
    final w e;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new DebounceTimedSubscriber(new b(cVar), this.c, this.d, this.e.a()));
    }

    /* access modifiers changed from: package-private */
    public static final class DebounceTimedSubscriber<T> extends AtomicLong implements j<T>, d {
        private static final long serialVersionUID = -9102637559663639004L;
        boolean done;
        final c<? super T> downstream;
        volatile long index;
        final long timeout;
        io.reactivex.disposables.b timer;
        final TimeUnit unit;
        d upstream;
        final w.c worker;

        DebounceTimedSubscriber(c<? super T> cVar, long j, TimeUnit timeUnit, w.c cVar2) {
            this.downstream = cVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar2;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.index + 1;
                this.index = j;
                io.reactivex.disposables.b bVar = this.timer;
                if (bVar != null) {
                    bVar.dispose();
                }
                DebounceEmitter debounceEmitter = new DebounceEmitter(t, j, this);
                this.timer = debounceEmitter;
                debounceEmitter.setResource(this.worker.a(debounceEmitter, this.timeout, this.unit));
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                a.a(th);
                return;
            }
            this.done = true;
            io.reactivex.disposables.b bVar = this.timer;
            if (bVar != null) {
                bVar.dispose();
            }
            this.downstream.onError(th);
            this.worker.dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                io.reactivex.disposables.b bVar = this.timer;
                if (bVar != null) {
                    bVar.dispose();
                }
                DebounceEmitter debounceEmitter = (DebounceEmitter) bVar;
                if (debounceEmitter != null) {
                    debounceEmitter.emit();
                }
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this, j);
            }
        }

        public void cancel() {
            this.upstream.cancel();
            this.worker.dispose();
        }

        /* access modifiers changed from: package-private */
        public void emit(long j, T t, DebounceEmitter<T> debounceEmitter) {
            if (j != this.index) {
                return;
            }
            if (get() != 0) {
                this.downstream.onNext(t);
                io.reactivex.internal.util.b.c(this, 1);
                debounceEmitter.dispose();
                return;
            }
            cancel();
            this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
        }
    }

    /* access modifiers changed from: package-private */
    public static final class DebounceEmitter<T> extends AtomicReference<io.reactivex.disposables.b> implements io.reactivex.disposables.b, Runnable {
        private static final long serialVersionUID = 6812032969491025141L;
        final long idx;
        final AtomicBoolean once = new AtomicBoolean();
        final DebounceTimedSubscriber<T> parent;
        final T value;

        DebounceEmitter(T t, long j, DebounceTimedSubscriber<T> debounceTimedSubscriber) {
            this.value = t;
            this.idx = j;
            this.parent = debounceTimedSubscriber;
        }

        @Override // java.lang.Runnable
        public void run() {
            emit();
        }

        /* access modifiers changed from: package-private */
        public void emit() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.emit(this.idx, this.value, this);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void setResource(io.reactivex.disposables.b bVar) {
            DisposableHelper.replace(this, bVar);
        }
    }
}
