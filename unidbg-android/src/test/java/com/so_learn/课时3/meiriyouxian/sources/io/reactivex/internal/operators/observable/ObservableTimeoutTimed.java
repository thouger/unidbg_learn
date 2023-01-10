package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTimeoutTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final w d;
    final t<? extends T> e;

    /* access modifiers changed from: package-private */
    public interface b {
        void onTimeout(long j);
    }

    public ObservableTimeoutTimed(q<T> qVar, long j, TimeUnit timeUnit, w wVar, t<? extends T> tVar) {
        super(qVar);
        this.b = j;
        this.c = timeUnit;
        this.d = wVar;
        this.e = tVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        if (this.e == null) {
            TimeoutObserver timeoutObserver = new TimeoutObserver(vVar, this.b, this.c, this.d.a());
            vVar.onSubscribe(timeoutObserver);
            timeoutObserver.startTimeout(0);
            this.a.subscribe(timeoutObserver);
            return;
        }
        TimeoutFallbackObserver timeoutFallbackObserver = new TimeoutFallbackObserver(vVar, this.b, this.c, this.d.a(), this.e);
        vVar.onSubscribe(timeoutFallbackObserver);
        timeoutFallbackObserver.startTimeout(0);
        this.a.subscribe(timeoutFallbackObserver);
    }

    static final class TimeoutObserver<T> extends AtomicLong implements io.reactivex.disposables.b, b, v<T> {
        private static final long serialVersionUID = 3764492702657003550L;
        final v<? super T> downstream;
        final SequentialDisposable task = new SequentialDisposable();
        final long timeout;
        final TimeUnit unit;
        final AtomicReference<io.reactivex.disposables.b> upstream = new AtomicReference<>();
        final w.c worker;

        TimeoutObserver(v<? super T> vVar, long j, TimeUnit timeUnit, w.c cVar) {
            this.downstream = vVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            long j = get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (compareAndSet(j, j2)) {
                    this.task.get().dispose();
                    this.downstream.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void startTimeout(long j) {
            this.task.replace(this.worker.a(new c(j, this), this.timeout, this.unit));
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onError(th);
                this.worker.dispose();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        @Override // io.reactivex.internal.operators.observable.ObservableTimeoutTimed.b
        public void onTimeout(long j) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                DisposableHelper.dispose(this.upstream);
                this.downstream.onError(new TimeoutException(ExceptionHelper.a(this.timeout, this.unit)));
                this.worker.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            this.worker.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }
    }

    /* access modifiers changed from: package-private */
    public static final class c implements Runnable {
        final b a;
        final long b;

        c(long j, b bVar) {
            this.b = j;
            this.a = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.onTimeout(this.b);
        }
    }

    static final class TimeoutFallbackObserver<T> extends AtomicReference<io.reactivex.disposables.b> implements io.reactivex.disposables.b, b, v<T> {
        private static final long serialVersionUID = 3764492702657003550L;
        final v<? super T> downstream;
        t<? extends T> fallback;
        final AtomicLong index = new AtomicLong();
        final SequentialDisposable task = new SequentialDisposable();
        final long timeout;
        final TimeUnit unit;
        final AtomicReference<io.reactivex.disposables.b> upstream = new AtomicReference<>();
        final w.c worker;

        TimeoutFallbackObserver(v<? super T> vVar, long j, TimeUnit timeUnit, w.c cVar, t<? extends T> tVar) {
            this.downstream = vVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
            this.fallback = tVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            long j = this.index.get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (this.index.compareAndSet(j, j2)) {
                    this.task.get().dispose();
                    this.downstream.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void startTimeout(long j) {
            this.task.replace(this.worker.a(new c(j, this), this.timeout, this.unit));
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onError(th);
                this.worker.dispose();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        @Override // io.reactivex.internal.operators.observable.ObservableTimeoutTimed.b
        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                DisposableHelper.dispose(this.upstream);
                t<? extends T> tVar = this.fallback;
                this.fallback = null;
                tVar.subscribe(new a(this.downstream, this));
                this.worker.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this);
            this.worker.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }

    static final class a<T> implements v<T> {
        final v<? super T> a;
        final AtomicReference<io.reactivex.disposables.b> b;

        a(v<? super T> vVar, AtomicReference<io.reactivex.disposables.b> atomicReference) {
            this.a = vVar;
            this.b = atomicReference;
        }

        @Override // io.reactivex.v
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.replace(this.b, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.a.onNext(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.a.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.a.onComplete();
        }
    }
}
