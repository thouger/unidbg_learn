package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableSkipLastTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final w d;
    final int e;
    final boolean f;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new SkipLastTimedObserver(vVar, this.b, this.c, this.d, this.e, this.f));
    }

    static final class SkipLastTimedObserver<T> extends AtomicInteger implements b, v<T> {
        private static final long serialVersionUID = -5677354903406201275L;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        final v<? super T> downstream;
        Throwable error;
        final a<Object> queue;
        final w scheduler;
        final long time;
        final TimeUnit unit;
        b upstream;

        SkipLastTimedObserver(v<? super T> vVar, long j, TimeUnit timeUnit, w wVar, int i, boolean z) {
            this.downstream = vVar;
            this.time = j;
            this.unit = timeUnit;
            this.scheduler = wVar;
            this.queue = new a<>(i);
            this.delayError = z;
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
            this.queue.a(Long.valueOf(this.scheduler.a(this.unit)), t);
            drain();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                v<? super T> vVar = this.downstream;
                a<Object> aVar = this.queue;
                boolean z = this.delayError;
                TimeUnit timeUnit = this.unit;
                w wVar = this.scheduler;
                long j = this.time;
                int i = 1;
                while (!this.cancelled) {
                    boolean z2 = this.done;
                    Long l = (Long) aVar.a();
                    boolean z3 = l == null;
                    long a = wVar.a(timeUnit);
                    if (!z3 && l.longValue() > a - j) {
                        z3 = true;
                    }
                    if (z2) {
                        if (!z) {
                            Throwable th = this.error;
                            if (th != null) {
                                this.queue.clear();
                                vVar.onError(th);
                                return;
                            } else if (z3) {
                                vVar.onComplete();
                                return;
                            }
                        } else if (z3) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                vVar.onError(th2);
                                return;
                            } else {
                                vVar.onComplete();
                                return;
                            }
                        }
                    }
                    if (z3) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        aVar.poll();
                        vVar.onNext(aVar.poll());
                    }
                }
                this.queue.clear();
            }
        }
    }
}
