package io.reactivex.internal.operators.flowable;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.c.a;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;
import org.a.d;

public final class FlowableOnBackpressureBufferStrategy<T> extends a<T, T> {
    final long c;
    final a d;
    final BackpressureOverflowStrategy e;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new OnBackpressureBufferStrategySubscriber(cVar, this.d, this.e, this.c));
    }

    static final class OnBackpressureBufferStrategySubscriber<T> extends AtomicInteger implements j<T>, d {
        private static final long serialVersionUID = 3240706908776709697L;
        final long bufferSize;
        volatile boolean cancelled;
        final Deque<T> deque = new ArrayDeque();
        volatile boolean done;
        final c<? super T> downstream;
        Throwable error;
        final a onOverflow;
        final AtomicLong requested = new AtomicLong();
        final BackpressureOverflowStrategy strategy;
        d upstream;

        OnBackpressureBufferStrategySubscriber(c<? super T> cVar, a aVar, BackpressureOverflowStrategy backpressureOverflowStrategy, long j) {
            this.downstream = cVar;
            this.onOverflow = aVar;
            this.strategy = backpressureOverflowStrategy;
            this.bufferSize = j;
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
            boolean z;
            boolean z2;
            if (!this.done) {
                Deque<T> deque = this.deque;
                synchronized (deque) {
                    z = false;
                    z2 = true;
                    if (((long) deque.size()) == this.bufferSize) {
                        int i = AnonymousClass1.a[this.strategy.ordinal()];
                        if (i == 1) {
                            deque.pollLast();
                            deque.offer(t);
                        } else if (i == 2) {
                            deque.poll();
                            deque.offer(t);
                        }
                        z2 = false;
                        z = true;
                    } else {
                        deque.offer(t);
                        z2 = false;
                    }
                }
                if (z) {
                    a aVar = this.onOverflow;
                    if (aVar != null) {
                        try {
                            aVar.a();
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            this.upstream.cancel();
                            onError(th);
                        }
                    }
                } else if (z2) {
                    this.upstream.cancel();
                    onError(new MissingBackpressureException());
                } else {
                    drain();
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            if (getAndIncrement() == 0) {
                clear(this.deque);
            }
        }

        /* access modifiers changed from: package-private */
        public void clear(Deque<T> deque) {
            synchronized (deque) {
                deque.clear();
            }
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            int i;
            boolean isEmpty;
            T poll;
            if (getAndIncrement() == 0) {
                Deque<T> deque = this.deque;
                c<? super T> cVar = this.downstream;
                int i2 = 1;
                do {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (true) {
                        i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                        if (i == 0) {
                            break;
                        } else if (this.cancelled) {
                            clear(deque);
                            return;
                        } else {
                            boolean z = this.done;
                            synchronized (deque) {
                                poll = deque.poll();
                            }
                            boolean z2 = poll == null;
                            if (z) {
                                Throwable th = this.error;
                                if (th != null) {
                                    clear(deque);
                                    cVar.onError(th);
                                    return;
                                } else if (z2) {
                                    cVar.onComplete();
                                    return;
                                }
                            }
                            if (z2) {
                                break;
                            }
                            cVar.onNext(poll);
                            j2++;
                        }
                    }
                    if (i == 0) {
                        if (this.cancelled) {
                            clear(deque);
                            return;
                        }
                        boolean z3 = this.done;
                        synchronized (deque) {
                            isEmpty = deque.isEmpty();
                        }
                        if (z3) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                clear(deque);
                                cVar.onError(th2);
                                return;
                            } else if (isEmpty) {
                                cVar.onComplete();
                                return;
                            }
                        }
                    }
                    if (j2 != 0) {
                        b.c(this.requested, j2);
                    }
                    i2 = addAndGet(-i2);
                } while (i2 != 0);
            }
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.FlowableOnBackpressureBufferStrategy$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[BackpressureOverflowStrategy.values().length];

        static {
            try {
                a[BackpressureOverflowStrategy.DROP_LATEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[BackpressureOverflowStrategy.DROP_OLDEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
