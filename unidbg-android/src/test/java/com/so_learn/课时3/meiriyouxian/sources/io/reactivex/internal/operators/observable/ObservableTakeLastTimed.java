package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableTakeLastTimed<T> extends a<T, T> {
    final long b;
    final long c;
    final TimeUnit d;
    final w e;
    final int f;
    final boolean g;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new TakeLastTimedObserver(vVar, this.b, this.c, this.d, this.e, this.f, this.g));
    }

    static final class TakeLastTimedObserver<T> extends AtomicBoolean implements b, v<T> {
        private static final long serialVersionUID = -5677354903406201275L;
        volatile boolean cancelled;
        final long count;
        final boolean delayError;
        final v<? super T> downstream;
        Throwable error;
        final a<Object> queue;
        final w scheduler;
        final long time;
        final TimeUnit unit;
        b upstream;

        TakeLastTimedObserver(v<? super T> vVar, long j, long j2, TimeUnit timeUnit, w wVar, int i, boolean z) {
            this.downstream = vVar;
            this.count = j;
            this.time = j2;
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
            a<Object> aVar = this.queue;
            long a = this.scheduler.a(this.unit);
            long j = this.time;
            long j2 = this.count;
            boolean z = j2 == Long.MAX_VALUE;
            aVar.a(Long.valueOf(a), t);
            while (!aVar.isEmpty()) {
                if (((Long) aVar.a()).longValue() <= a - j || (!z && ((long) (aVar.b() >> 1)) > j2)) {
                    aVar.poll();
                    aVar.poll();
                } else {
                    return;
                }
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.error = th;
            drain();
        }

        @Override // io.reactivex.v
        public void onComplete() {
            drain();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
                if (compareAndSet(false, true)) {
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
            Throwable th;
            if (compareAndSet(false, true)) {
                v<? super T> vVar = this.downstream;
                a<Object> aVar = this.queue;
                boolean z = this.delayError;
                long a = this.scheduler.a(this.unit) - this.time;
                while (!this.cancelled) {
                    if (z || (th = this.error) == null) {
                        Object poll = aVar.poll();
                        if (poll == null) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                vVar.onError(th2);
                                return;
                            } else {
                                vVar.onComplete();
                                return;
                            }
                        } else {
                            Object poll2 = aVar.poll();
                            if (((Long) poll).longValue() >= a) {
                                vVar.onNext(poll2);
                            }
                        }
                    } else {
                        aVar.clear();
                        vVar.onError(th);
                        return;
                    }
                }
                aVar.clear();
            }
        }
    }
}
