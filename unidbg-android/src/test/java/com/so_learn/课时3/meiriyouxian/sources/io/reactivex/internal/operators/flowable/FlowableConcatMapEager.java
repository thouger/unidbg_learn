package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.subscribers.InnerQueuedSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableConcatMapEager<T, R> extends a<T, R> {
    final h<? super T, ? extends b<? extends R>> c;
    final int d;
    final int e;
    final ErrorMode f;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super R> cVar) {
        this.b.a((j) new ConcatMapEagerDelayErrorSubscriber(cVar, this.c, this.d, this.e, this.f));
    }

    static final class ConcatMapEagerDelayErrorSubscriber<T, R> extends AtomicInteger implements io.reactivex.internal.subscribers.c<R>, j<T>, d {
        private static final long serialVersionUID = -4255299542215038287L;
        volatile boolean cancelled;
        volatile InnerQueuedSubscriber<R> current;
        volatile boolean done;
        final c<? super R> downstream;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final h<? super T, ? extends b<? extends R>> mapper;
        final int maxConcurrency;
        final int prefetch;
        final AtomicLong requested = new AtomicLong();
        final a<InnerQueuedSubscriber<R>> subscribers;
        d upstream;

        ConcatMapEagerDelayErrorSubscriber(c<? super R> cVar, h<? super T, ? extends b<? extends R>> hVar, int i, int i2, ErrorMode errorMode) {
            this.downstream = cVar;
            this.mapper = hVar;
            this.maxConcurrency = i;
            this.prefetch = i2;
            this.errorMode = errorMode;
            this.subscribers = new a<>(Math.min(i2, i));
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                int i = this.maxConcurrency;
                dVar.request(i == Integer.MAX_VALUE ? Long.MAX_VALUE : (long) i);
            }
        }

        public void onNext(T t) {
            try {
                b bVar = (b) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null Publisher");
                InnerQueuedSubscriber innerQueuedSubscriber = new InnerQueuedSubscriber(this, this.prefetch);
                if (!this.cancelled) {
                    this.subscribers.offer(innerQueuedSubscriber);
                    bVar.subscribe(innerQueuedSubscriber);
                    if (this.cancelled) {
                        innerQueuedSubscriber.cancel();
                        drainAndCancel();
                    }
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.upstream.cancel();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                drainAndCancel();
            }
        }

        /* access modifiers changed from: package-private */
        public void drainAndCancel() {
            if (getAndIncrement() == 0) {
                do {
                    cancelAll();
                } while (decrementAndGet() != 0);
            }
        }

        /* access modifiers changed from: package-private */
        public void cancelAll() {
            InnerQueuedSubscriber<R> innerQueuedSubscriber = this.current;
            this.current = null;
            if (innerQueuedSubscriber != null) {
                innerQueuedSubscriber.cancel();
            }
            while (true) {
                InnerQueuedSubscriber innerQueuedSubscriber2 = (InnerQueuedSubscriber) this.subscribers.poll();
                if (innerQueuedSubscriber2 != null) {
                    innerQueuedSubscriber2.cancel();
                } else {
                    return;
                }
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
                drain();
            }
        }

        @Override // io.reactivex.internal.subscribers.c
        public void innerNext(InnerQueuedSubscriber<R> innerQueuedSubscriber, R r) {
            if (innerQueuedSubscriber.queue().offer(r)) {
                drain();
                return;
            }
            innerQueuedSubscriber.cancel();
            innerError(innerQueuedSubscriber, new MissingBackpressureException());
        }

        @Override // io.reactivex.internal.subscribers.c
        public void innerError(InnerQueuedSubscriber<R> innerQueuedSubscriber, Throwable th) {
            if (this.errors.addThrowable(th)) {
                innerQueuedSubscriber.setDone();
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.cancel();
                }
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.internal.subscribers.c
        public void innerComplete(InnerQueuedSubscriber<R> innerQueuedSubscriber) {
            innerQueuedSubscriber.setDone();
            drain();
        }

        @Override // io.reactivex.internal.subscribers.c
        public void drain() {
            InnerQueuedSubscriber<R> innerQueuedSubscriber;
            boolean z;
            int i;
            long j;
            long j2;
            io.reactivex.internal.a.h<R> queue;
            int i2;
            if (getAndIncrement() == 0) {
                InnerQueuedSubscriber<R> innerQueuedSubscriber2 = this.current;
                c<? super R> cVar = this.downstream;
                ErrorMode errorMode = this.errorMode;
                int i3 = 1;
                while (true) {
                    long j3 = this.requested.get();
                    if (innerQueuedSubscriber2 != null) {
                        innerQueuedSubscriber = innerQueuedSubscriber2;
                    } else if (errorMode == ErrorMode.END || this.errors.get() == null) {
                        boolean z2 = this.done;
                        innerQueuedSubscriber = (InnerQueuedSubscriber) this.subscribers.poll();
                        if (z2 && innerQueuedSubscriber == null) {
                            Throwable terminate = this.errors.terminate();
                            if (terminate != null) {
                                cVar.onError(terminate);
                                return;
                            } else {
                                cVar.onComplete();
                                return;
                            }
                        } else if (innerQueuedSubscriber != null) {
                            this.current = innerQueuedSubscriber;
                        }
                    } else {
                        cancelAll();
                        cVar.onError(this.errors.terminate());
                        return;
                    }
                    if (innerQueuedSubscriber == null || (queue = innerQueuedSubscriber.queue()) == null) {
                        i = i3;
                        innerQueuedSubscriber2 = innerQueuedSubscriber;
                        j2 = 0;
                        j = 0;
                        z = false;
                    } else {
                        j = 0;
                        while (true) {
                            i2 = (j > j3 ? 1 : (j == j3 ? 0 : -1));
                            i = i3;
                            if (i2 == 0) {
                                break;
                            } else if (this.cancelled) {
                                cancelAll();
                                return;
                            } else if (errorMode != ErrorMode.IMMEDIATE || this.errors.get() == null) {
                                boolean isDone = innerQueuedSubscriber.isDone();
                                try {
                                    Object poll = queue.poll();
                                    boolean z3 = poll == null;
                                    if (isDone && z3) {
                                        this.current = null;
                                        this.upstream.request(1);
                                        innerQueuedSubscriber = null;
                                        z = true;
                                        break;
                                    } else if (z3) {
                                        break;
                                    } else {
                                        cVar.onNext(poll);
                                        j++;
                                        innerQueuedSubscriber.requestOne();
                                        i3 = i;
                                    }
                                } catch (Throwable th) {
                                    io.reactivex.exceptions.a.b(th);
                                    this.current = null;
                                    innerQueuedSubscriber.cancel();
                                    cancelAll();
                                    cVar.onError(th);
                                    return;
                                }
                            } else {
                                this.current = null;
                                innerQueuedSubscriber.cancel();
                                cancelAll();
                                cVar.onError(this.errors.terminate());
                                return;
                            }
                        }
                        z = false;
                        if (i2 == 0) {
                            if (this.cancelled) {
                                cancelAll();
                                return;
                            } else if (errorMode != ErrorMode.IMMEDIATE || this.errors.get() == null) {
                                boolean isDone2 = innerQueuedSubscriber.isDone();
                                boolean isEmpty = queue.isEmpty();
                                if (isDone2 && isEmpty) {
                                    this.current = null;
                                    this.upstream.request(1);
                                    innerQueuedSubscriber2 = null;
                                    j2 = 0;
                                    z = true;
                                }
                            } else {
                                this.current = null;
                                innerQueuedSubscriber.cancel();
                                cancelAll();
                                cVar.onError(this.errors.terminate());
                                return;
                            }
                        }
                        innerQueuedSubscriber2 = innerQueuedSubscriber;
                        j2 = 0;
                    }
                    if (!(j == j2 || j3 == Long.MAX_VALUE)) {
                        this.requested.addAndGet(-j);
                    }
                    if (z) {
                        i3 = i;
                    } else {
                        i3 = addAndGet(-i);
                        if (i3 == 0) {
                            return;
                        }
                    }
                }
            }
        }
    }
}
