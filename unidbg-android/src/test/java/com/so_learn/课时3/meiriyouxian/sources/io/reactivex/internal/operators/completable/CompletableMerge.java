package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.d;

public final class CompletableMerge extends a {
    final b<? extends e> a;
    final int b;
    final boolean c;

    @Override // io.reactivex.a
    public void b(c cVar) {
        this.a.subscribe(new CompletableMergeSubscriber(cVar, this.b, this.c));
    }

    static final class CompletableMergeSubscriber extends AtomicInteger implements io.reactivex.disposables.b, j<e> {
        private static final long serialVersionUID = -2108443387387077490L;
        final boolean delayErrors;
        final c downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final int maxConcurrency;
        final io.reactivex.disposables.a set = new io.reactivex.disposables.a();
        d upstream;

        CompletableMergeSubscriber(c cVar, int i, boolean z) {
            this.downstream = cVar;
            this.maxConcurrency = i;
            this.delayErrors = z;
            lazySet(1);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.upstream.cancel();
            this.set.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.set.isDisposed();
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                int i = this.maxConcurrency;
                if (i == Integer.MAX_VALUE) {
                    dVar.request(Long.MAX_VALUE);
                } else {
                    dVar.request((long) i);
                }
            }
        }

        public void onNext(e eVar) {
            getAndIncrement();
            MergeInnerObserver mergeInnerObserver = new MergeInnerObserver();
            this.set.a(mergeInnerObserver);
            eVar.a(mergeInnerObserver);
        }

        public void onError(Throwable th) {
            if (!this.delayErrors) {
                this.set.dispose();
                if (!this.error.addThrowable(th)) {
                    io.reactivex.e.a.a(th);
                } else if (getAndSet(0) > 0) {
                    this.downstream.onError(this.error.terminate());
                }
            } else if (!this.error.addThrowable(th)) {
                io.reactivex.e.a.a(th);
            } else if (decrementAndGet() == 0) {
                this.downstream.onError(this.error.terminate());
            }
        }

        public void onComplete() {
            if (decrementAndGet() != 0) {
                return;
            }
            if (this.error.get() != null) {
                this.downstream.onError(this.error.terminate());
            } else {
                this.downstream.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerError(MergeInnerObserver mergeInnerObserver, Throwable th) {
            this.set.c(mergeInnerObserver);
            if (!this.delayErrors) {
                this.upstream.cancel();
                this.set.dispose();
                if (!this.error.addThrowable(th)) {
                    io.reactivex.e.a.a(th);
                } else if (getAndSet(0) > 0) {
                    this.downstream.onError(this.error.terminate());
                }
            } else if (!this.error.addThrowable(th)) {
                io.reactivex.e.a.a(th);
            } else if (decrementAndGet() == 0) {
                this.downstream.onError(this.error.terminate());
            } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.upstream.request(1);
            }
        }

        /* access modifiers changed from: package-private */
        public void innerComplete(MergeInnerObserver mergeInnerObserver) {
            this.set.c(mergeInnerObserver);
            if (decrementAndGet() == 0) {
                Throwable th = this.error.get();
                if (th != null) {
                    this.downstream.onError(th);
                } else {
                    this.downstream.onComplete();
                }
            } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.upstream.request(1);
            }
        }

        /* access modifiers changed from: package-private */
        public final class MergeInnerObserver extends AtomicReference<io.reactivex.disposables.b> implements c, io.reactivex.disposables.b {
            private static final long serialVersionUID = 251330541679988317L;

            MergeInnerObserver() {
            }

            @Override // io.reactivex.c
            public void onSubscribe(io.reactivex.disposables.b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.c
            public void onError(Throwable th) {
                CompletableMergeSubscriber.this.innerError(this, th);
            }

            @Override // io.reactivex.c
            public void onComplete() {
                CompletableMergeSubscriber.this.innerComplete(this);
            }

            @Override // io.reactivex.disposables.b
            public boolean isDisposed() {
                return DisposableHelper.isDisposed(get());
            }

            @Override // io.reactivex.disposables.b
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
