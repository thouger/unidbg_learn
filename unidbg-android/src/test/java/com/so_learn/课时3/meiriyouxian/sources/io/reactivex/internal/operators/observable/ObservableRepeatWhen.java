package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.e;
import io.reactivex.q;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.b;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableRepeatWhen<T> extends a<T, T> {
    final h<? super q<Object>, ? extends t<?>> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        b<T> b = PublishSubject.a().b();
        try {
            t tVar = (t) a.a(this.b.apply(b), "The handler returned a null ObservableSource");
            RepeatWhenObserver repeatWhenObserver = new RepeatWhenObserver(vVar, b, this.a);
            vVar.onSubscribe(repeatWhenObserver);
            tVar.subscribe(repeatWhenObserver.inner);
            repeatWhenObserver.subscribeNext();
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, vVar);
        }
    }

    static final class RepeatWhenObserver<T> extends AtomicInteger implements io.reactivex.disposables.b, v<T> {
        private static final long serialVersionUID = 802743776666017014L;
        volatile boolean active;
        final v<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final RepeatWhenObserver<T>.InnerRepeatObserver inner = new InnerRepeatObserver();
        final b<Object> signaller;
        final t<T> source;
        final AtomicReference<io.reactivex.disposables.b> upstream = new AtomicReference<>();
        final AtomicInteger wip = new AtomicInteger();

        RepeatWhenObserver(v<? super T> vVar, b<Object> bVar, t<T> tVar) {
            this.downstream = vVar;
            this.signaller = bVar;
            this.source = tVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            e.a((v) this.downstream, (Object) t, (AtomicInteger) this, this.error);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.inner);
            e.a((v<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            DisposableHelper.replace(this.upstream, null);
            this.active = false;
            this.signaller.onNext(0);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this.inner);
        }

        /* access modifiers changed from: package-private */
        public void innerNext() {
            subscribeNext();
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            e.a((v<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        /* access modifiers changed from: package-private */
        public void innerComplete() {
            DisposableHelper.dispose(this.upstream);
            e.a(this.downstream, this, this.error);
        }

        /* access modifiers changed from: package-private */
        public void subscribeNext() {
            if (this.wip.getAndIncrement() == 0) {
                while (!isDisposed()) {
                    if (!this.active) {
                        this.active = true;
                        this.source.subscribe(this);
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        final class InnerRepeatObserver extends AtomicReference<io.reactivex.disposables.b> implements v<Object> {
            private static final long serialVersionUID = 3254781284376480842L;

            InnerRepeatObserver() {
            }

            @Override // io.reactivex.v
            public void onSubscribe(io.reactivex.disposables.b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.v
            public void onNext(Object obj) {
                RepeatWhenObserver.this.innerNext();
            }

            @Override // io.reactivex.v
            public void onError(Throwable th) {
                RepeatWhenObserver.this.innerError(th);
            }

            @Override // io.reactivex.v
            public void onComplete() {
                RepeatWhenObserver.this.innerComplete();
            }
        }
    }
}
