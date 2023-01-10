package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableDebounce<T, U> extends a<T, T> {
    final h<? super T, ? extends b<U>> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new DebounceSubscriber(new io.reactivex.subscribers.b(cVar), this.c));
    }

    /* access modifiers changed from: package-private */
    public static final class DebounceSubscriber<T, U> extends AtomicLong implements j<T>, d {
        private static final long serialVersionUID = 6725975399620862591L;
        final h<? super T, ? extends b<U>> debounceSelector;
        final AtomicReference<io.reactivex.disposables.b> debouncer = new AtomicReference<>();
        boolean done;
        final c<? super T> downstream;
        volatile long index;
        d upstream;

        DebounceSubscriber(c<? super T> cVar, h<? super T, ? extends b<U>> hVar) {
            this.downstream = cVar;
            this.debounceSelector = hVar;
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
                io.reactivex.disposables.b bVar = this.debouncer.get();
                if (bVar != null) {
                    bVar.dispose();
                }
                try {
                    b bVar2 = (b) io.reactivex.internal.functions.a.a(this.debounceSelector.apply(t), "The publisher supplied is null");
                    a aVar = new a(this, j, t);
                    if (this.debouncer.compareAndSet(bVar, aVar)) {
                        bVar2.subscribe(aVar);
                    }
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    cancel();
                    this.downstream.onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.debouncer);
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                io.reactivex.disposables.b bVar = this.debouncer.get();
                if (!DisposableHelper.isDisposed(bVar)) {
                    a aVar = (a) bVar;
                    if (aVar != null) {
                        aVar.a();
                    }
                    DisposableHelper.dispose(this.debouncer);
                    this.downstream.onComplete();
                }
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this, j);
            }
        }

        public void cancel() {
            this.upstream.cancel();
            DisposableHelper.dispose(this.debouncer);
        }

        /* access modifiers changed from: package-private */
        public void emit(long j, T t) {
            if (j != this.index) {
                return;
            }
            if (get() != 0) {
                this.downstream.onNext(t);
                io.reactivex.internal.util.b.c(this, 1);
                return;
            }
            cancel();
            this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
        }

        static final class a<T, U> extends io.reactivex.subscribers.a<U> {
            final DebounceSubscriber<T, U> a;
            final long b;
            final T c;
            boolean d;
            final AtomicBoolean e = new AtomicBoolean();

            a(DebounceSubscriber<T, U> debounceSubscriber, long j, T t) {
                this.a = debounceSubscriber;
                this.b = j;
                this.c = t;
            }

            public void onNext(U u) {
                if (!this.d) {
                    this.d = true;
                    c();
                    a();
                }
            }

            /* access modifiers changed from: package-private */
            public void a() {
                if (this.e.compareAndSet(false, true)) {
                    this.a.emit(this.b, this.c);
                }
            }

            public void onError(Throwable th) {
                if (this.d) {
                    io.reactivex.e.a.a(th);
                    return;
                }
                this.d = true;
                this.a.onError(th);
            }

            public void onComplete() {
                if (!this.d) {
                    this.d = true;
                    a();
                }
            }
        }
    }
}
