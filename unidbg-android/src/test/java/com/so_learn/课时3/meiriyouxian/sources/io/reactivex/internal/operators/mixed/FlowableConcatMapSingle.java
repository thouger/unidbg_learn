package io.reactivex.internal.operators.mixed;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.e.a;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableConcatMapSingle<T, R> extends g<R> {
    final g<T> b;
    final h<? super T, ? extends ab<? extends R>> c;
    final ErrorMode d;
    final int e;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super R> cVar) {
        this.b.a((j) new ConcatMapSingleSubscriber(cVar, this.c, this.e, this.d));
    }

    static final class ConcatMapSingleSubscriber<T, R> extends AtomicInteger implements j<T>, d {
        static final int STATE_ACTIVE = 1;
        static final int STATE_INACTIVE = 0;
        static final int STATE_RESULT_VALUE = 2;
        private static final long serialVersionUID = -9140123220065488293L;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        final c<? super R> downstream;
        long emitted;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final ConcatMapSingleObserver<R> inner = new ConcatMapSingleObserver<>(this);
        R item;
        final h<? super T, ? extends ab<? extends R>> mapper;
        final int prefetch;
        final io.reactivex.internal.a.g<T> queue;
        final AtomicLong requested = new AtomicLong();
        volatile int state;
        d upstream;

        ConcatMapSingleSubscriber(c<? super R> cVar, h<? super T, ? extends ab<? extends R>> hVar, int i, ErrorMode errorMode) {
            this.downstream = cVar;
            this.mapper = hVar;
            this.prefetch = i;
            this.errorMode = errorMode;
            this.queue = new SpscArrayQueue(i);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                dVar.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            if (!this.queue.offer(t)) {
                this.upstream.cancel();
                onError(new MissingBackpressureException("queue full?!"));
                return;
            }
            drain();
        }

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (this.errorMode == ErrorMode.IMMEDIATE) {
                    this.inner.dispose();
                }
                this.done = true;
                drain();
                return;
            }
            a.a(th);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void request(long j) {
            b.a(this.requested, j);
            drain();
        }

        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            this.inner.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
                this.item = null;
            }
        }

        /* access modifiers changed from: package-private */
        public void innerSuccess(R r) {
            this.item = r;
            this.state = 2;
            drain();
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (this.errorMode != ErrorMode.END) {
                    this.upstream.cancel();
                }
                this.state = 0;
                drain();
                return;
            }
            a.a(th);
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                c<? super R> cVar = this.downstream;
                ErrorMode errorMode = this.errorMode;
                io.reactivex.internal.a.g<T> gVar = this.queue;
                AtomicThrowable atomicThrowable = this.errors;
                AtomicLong atomicLong = this.requested;
                int i = this.prefetch;
                int i2 = i - (i >> 1);
                int i3 = 1;
                while (true) {
                    if (this.cancelled) {
                        gVar.clear();
                        this.item = null;
                    } else {
                        int i4 = this.state;
                        if (atomicThrowable.get() == null || !(errorMode == ErrorMode.IMMEDIATE || (errorMode == ErrorMode.BOUNDARY && i4 == 0))) {
                            if (i4 == 0) {
                                boolean z = this.done;
                                Object poll = gVar.poll();
                                boolean z2 = poll == null;
                                if (z && z2) {
                                    Throwable terminate = atomicThrowable.terminate();
                                    if (terminate == null) {
                                        cVar.onComplete();
                                        return;
                                    } else {
                                        cVar.onError(terminate);
                                        return;
                                    }
                                } else if (!z2) {
                                    int i5 = this.consumed + 1;
                                    if (i5 == i2) {
                                        this.consumed = 0;
                                        this.upstream.request((long) i2);
                                    } else {
                                        this.consumed = i5;
                                    }
                                    try {
                                        ab abVar = (ab) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null SingleSource");
                                        this.state = 1;
                                        abVar.a(this.inner);
                                    } catch (Throwable th) {
                                        io.reactivex.exceptions.a.b(th);
                                        this.upstream.cancel();
                                        gVar.clear();
                                        atomicThrowable.addThrowable(th);
                                        cVar.onError(atomicThrowable.terminate());
                                        return;
                                    }
                                }
                            } else if (i4 == 2) {
                                long j = this.emitted;
                                if (j != atomicLong.get()) {
                                    R r = this.item;
                                    this.item = null;
                                    cVar.onNext(r);
                                    this.emitted = j + 1;
                                    this.state = 0;
                                }
                            }
                        }
                    }
                    i3 = addAndGet(-i3);
                    if (i3 == 0) {
                        return;
                    }
                }
                gVar.clear();
                this.item = null;
                cVar.onError(atomicThrowable.terminate());
            }
        }

        /* access modifiers changed from: package-private */
        public static final class ConcatMapSingleObserver<R> extends AtomicReference<io.reactivex.disposables.b> implements z<R> {
            private static final long serialVersionUID = -3051469169682093892L;
            final ConcatMapSingleSubscriber<?, R> parent;

            ConcatMapSingleObserver(ConcatMapSingleSubscriber<?, R> concatMapSingleSubscriber) {
                this.parent = concatMapSingleSubscriber;
            }

            @Override // io.reactivex.z
            public void onSubscribe(io.reactivex.disposables.b bVar) {
                DisposableHelper.replace(this, bVar);
            }

            @Override // io.reactivex.z
            public void onSuccess(R r) {
                this.parent.innerSuccess(r);
            }

            @Override // io.reactivex.z
            public void onError(Throwable th) {
                this.parent.innerError(th);
            }

            /* access modifiers changed from: package-private */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
