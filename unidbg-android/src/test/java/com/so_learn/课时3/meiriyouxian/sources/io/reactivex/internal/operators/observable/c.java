package io.reactivex.internal.operators.observable;

import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;

/* compiled from: ObservableDelay */
public final class c<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final w d;
    final boolean e;

    public c(t<T> tVar, long j, TimeUnit timeUnit, w wVar, boolean z) {
        super(tVar);
        this.b = j;
        this.c = timeUnit;
        this.d = wVar;
        this.e = z;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        io.reactivex.observers.c cVar;
        if (this.e) {
            cVar = vVar;
        } else {
            cVar = new io.reactivex.observers.c(vVar);
        }
        this.a.subscribe(new a(cVar, this.b, this.c, this.d.a(), this.e));
    }

    /* compiled from: ObservableDelay */
    static final class a<T> implements io.reactivex.disposables.b, v<T> {
        final v<? super T> a;
        final long b;
        final TimeUnit c;
        final w.c d;
        final boolean e;
        io.reactivex.disposables.b f;

        a(v<? super T> vVar, long j, TimeUnit timeUnit, w.c cVar, boolean z) {
            this.a = vVar;
            this.b = j;
            this.c = timeUnit;
            this.d = cVar;
            this.e = z;
        }

        @Override // io.reactivex.v
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.f, bVar)) {
                this.f = bVar;
                this.a.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.d.a(new RunnableC0209c(t), this.b, this.c);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.d.a(new b(th), this.e ? this.b : 0, this.c);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.d.a(new RunnableC0208a(), this.b, this.c);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f.dispose();
            this.d.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        /* compiled from: ObservableDelay */
        /* renamed from: io.reactivex.internal.operators.observable.c$a$c  reason: collision with other inner class name */
        final class RunnableC0209c implements Runnable {
            private final T b;

            RunnableC0209c(T t) {
                this.b = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.a.onNext(this.b);
            }
        }

        /* compiled from: ObservableDelay */
        final class b implements Runnable {
            private final Throwable b;

            b(Throwable th) {
                this.b = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.a.onError(this.b);
                } finally {
                    a.this.d.dispose();
                }
            }
        }

        /* compiled from: ObservableDelay */
        /* renamed from: io.reactivex.internal.operators.observable.c$a$a  reason: collision with other inner class name */
        final class RunnableC0208a implements Runnable {
            RunnableC0208a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.a.onComplete();
                } finally {
                    a.this.d.dispose();
                }
            }
        }
    }
}
