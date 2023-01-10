package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.disposables.b;
import io.reactivex.e.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.w;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleTimeout<T> extends x<T> {
    final ab<T> a;
    final long b;
    final TimeUnit c;
    final w d;
    final ab<? extends T> e;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        TimeoutMainObserver timeoutMainObserver = new TimeoutMainObserver(zVar, this.e, this.b, this.c);
        zVar.onSubscribe(timeoutMainObserver);
        DisposableHelper.replace(timeoutMainObserver.task, this.d.a(timeoutMainObserver, this.b, this.c));
        this.a.a(timeoutMainObserver);
    }

    static final class TimeoutMainObserver<T> extends AtomicReference<b> implements b, z<T>, Runnable {
        private static final long serialVersionUID = 37497744973048446L;
        final z<? super T> downstream;
        final TimeoutFallbackObserver<T> fallback;
        ab<? extends T> other;
        final AtomicReference<b> task = new AtomicReference<>();
        final long timeout;
        final TimeUnit unit;

        static final class TimeoutFallbackObserver<T> extends AtomicReference<b> implements z<T> {
            private static final long serialVersionUID = 2071387740092105509L;
            final z<? super T> downstream;

            TimeoutFallbackObserver(z<? super T> zVar) {
                this.downstream = zVar;
            }

            @Override // io.reactivex.z
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.z
            public void onSuccess(T t) {
                this.downstream.onSuccess(t);
            }

            @Override // io.reactivex.z
            public void onError(Throwable th) {
                this.downstream.onError(th);
            }
        }

        TimeoutMainObserver(z<? super T> zVar, ab<? extends T> abVar, long j, TimeUnit timeUnit) {
            this.downstream = zVar;
            this.other = abVar;
            this.timeout = j;
            this.unit = timeUnit;
            if (abVar != null) {
                this.fallback = new TimeoutFallbackObserver<>(zVar);
            } else {
                this.fallback = null;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = get();
            if (bVar != DisposableHelper.DISPOSED && compareAndSet(bVar, DisposableHelper.DISPOSED)) {
                if (bVar != null) {
                    bVar.dispose();
                }
                ab<? extends T> abVar = this.other;
                if (abVar == null) {
                    this.downstream.onError(new TimeoutException(ExceptionHelper.a(this.timeout, this.unit)));
                    return;
                }
                this.other = null;
                abVar.a(this.fallback);
            }
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            b bVar = get();
            if (bVar != DisposableHelper.DISPOSED && compareAndSet(bVar, DisposableHelper.DISPOSED)) {
                DisposableHelper.dispose(this.task);
                this.downstream.onSuccess(t);
            }
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            b bVar = get();
            if (bVar == DisposableHelper.DISPOSED || !compareAndSet(bVar, DisposableHelper.DISPOSED)) {
                a.a(th);
                return;
            }
            DisposableHelper.dispose(this.task);
            this.downstream.onError(th);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
            DisposableHelper.dispose(this.task);
            TimeoutFallbackObserver<T> timeoutFallbackObserver = this.fallback;
            if (timeoutFallbackObserver != null) {
                DisposableHelper.dispose(timeoutFallbackObserver);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
