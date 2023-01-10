package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.c;
import io.reactivex.t;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableDebounceTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final w d;

    public ObservableDebounceTimed(t<T> tVar, long j, TimeUnit timeUnit, w wVar) {
        super(tVar);
        this.b = j;
        this.c = timeUnit;
        this.d = wVar;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new a(new c(vVar), this.b, this.c, this.d.a()));
    }

    /* access modifiers changed from: package-private */
    public static final class a<T> implements b, v<T> {
        final v<? super T> a;
        final long b;
        final TimeUnit c;
        final w.c d;
        b e;
        b f;
        volatile long g;
        boolean h;

        a(v<? super T> vVar, long j, TimeUnit timeUnit, w.c cVar) {
            this.a = vVar;
            this.b = j;
            this.c = timeUnit;
            this.d = cVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.e, bVar)) {
                this.e = bVar;
                this.a.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (!this.h) {
                long j = this.g + 1;
                this.g = j;
                b bVar = this.f;
                if (bVar != null) {
                    bVar.dispose();
                }
                DebounceEmitter debounceEmitter = new DebounceEmitter(t, j, this);
                this.f = debounceEmitter;
                debounceEmitter.setResource(this.d.a(debounceEmitter, this.b, this.c));
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.h) {
                io.reactivex.e.a.a(th);
                return;
            }
            b bVar = this.f;
            if (bVar != null) {
                bVar.dispose();
            }
            this.h = true;
            this.a.onError(th);
            this.d.dispose();
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.h) {
                this.h = true;
                b bVar = this.f;
                if (bVar != null) {
                    bVar.dispose();
                }
                DebounceEmitter debounceEmitter = (DebounceEmitter) bVar;
                if (debounceEmitter != null) {
                    debounceEmitter.run();
                }
                this.a.onComplete();
                this.d.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.e.dispose();
            this.d.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        /* access modifiers changed from: package-private */
        public void a(long j, T t, DebounceEmitter<T> debounceEmitter) {
            if (j == this.g) {
                this.a.onNext(t);
                debounceEmitter.dispose();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class DebounceEmitter<T> extends AtomicReference<b> implements b, Runnable {
        private static final long serialVersionUID = 6812032969491025141L;
        final long idx;
        final AtomicBoolean once = new AtomicBoolean();
        final a<T> parent;
        final T value;

        DebounceEmitter(T t, long j, a<T> aVar) {
            this.value = t;
            this.idx = j;
            this.parent = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.a(this.idx, this.value, this);
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

        public void setResource(b bVar) {
            DisposableHelper.replace(this, bVar);
        }
    }
}
