package io.reactivex.internal.operators.parallel;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.schedulers.h;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.w;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;
import org.a.d;

public final class ParallelRunOn<T> extends io.reactivex.parallel.a<T> {
    final io.reactivex.parallel.a<? extends T> a;
    final w b;
    final int c;

    public ParallelRunOn(io.reactivex.parallel.a<? extends T> aVar, w wVar, int i) {
        this.a = aVar;
        this.b = wVar;
        this.c = i;
    }

    @Override // io.reactivex.parallel.a
    public void a(c<? super T>[] cVarArr) {
        if (b(cVarArr)) {
            int length = cVarArr.length;
            c[] cVarArr2 = new c[length];
            w wVar = this.b;
            if (wVar instanceof h) {
                ((h) wVar).a(length, new a(cVarArr, cVarArr2));
            } else {
                for (int i = 0; i < length; i++) {
                    a(i, cVarArr, cVarArr2, this.b.a());
                }
            }
            this.a.a(cVarArr2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i, c<? super T>[] cVarArr, c<T>[] cVarArr2, w.c cVar) {
        c<? super T> cVar2 = cVarArr[i];
        SpscArrayQueue spscArrayQueue = new SpscArrayQueue(this.c);
        if (cVar2 instanceof io.reactivex.internal.a.a) {
            cVarArr2[i] = new RunOnConditionalSubscriber((io.reactivex.internal.a.a) cVar2, this.c, spscArrayQueue, cVar);
        } else {
            cVarArr2[i] = new RunOnSubscriber(cVar2, this.c, spscArrayQueue, cVar);
        }
    }

    final class a implements h.a {
        final c<? super T>[] a;
        final c<T>[] b;

        a(c<? super T>[] cVarArr, c<T>[] cVarArr2) {
            this.a = cVarArr;
            this.b = cVarArr2;
        }

        @Override // io.reactivex.internal.schedulers.h.a
        public void a(int i, w.c cVar) {
            ParallelRunOn.this.a(i, this.a, this.b, cVar);
        }
    }

    @Override // io.reactivex.parallel.a
    public int a() {
        return this.a.a();
    }

    static abstract class BaseRunOnSubscriber<T> extends AtomicInteger implements j<T>, Runnable, d {
        private static final long serialVersionUID = 9222303586456402150L;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        Throwable error;
        final int limit;
        final int prefetch;
        final SpscArrayQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        d upstream;
        final w.c worker;

        BaseRunOnSubscriber(int i, SpscArrayQueue<T> spscArrayQueue, w.c cVar) {
            this.prefetch = i;
            this.queue = spscArrayQueue;
            this.limit = i - (i >> 2);
            this.worker = cVar;
        }

        public final void onNext(T t) {
            if (!this.done) {
                if (!this.queue.offer(t)) {
                    this.upstream.cancel();
                    onError(new MissingBackpressureException("Queue is full?!"));
                    return;
                }
                schedule();
            }
        }

        public final void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.error = th;
            this.done = true;
            schedule();
        }

        public final void onComplete() {
            if (!this.done) {
                this.done = true;
                schedule();
            }
        }

        public final void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                schedule();
            }
        }

        public final void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                this.worker.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void schedule() {
            if (getAndIncrement() == 0) {
                this.worker.a(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class RunOnSubscriber<T> extends BaseRunOnSubscriber<T> {
        private static final long serialVersionUID = 1075119423897941642L;
        final c<? super T> downstream;

        RunOnSubscriber(c<? super T> cVar, int i, SpscArrayQueue<T> spscArrayQueue, w.c cVar2) {
            super(i, spscArrayQueue, cVar2);
            this.downstream = cVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                dVar.request((long) this.prefetch);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            Throwable th;
            int i2 = this.consumed;
            SpscArrayQueue spscArrayQueue = this.queue;
            c<? super T> cVar = this.downstream;
            int i3 = this.limit;
            int i4 = 1;
            while (true) {
                long j = this.requested.get();
                long j2 = 0;
                while (true) {
                    i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    if (i == 0) {
                        break;
                    } else if (this.cancelled) {
                        spscArrayQueue.clear();
                        return;
                    } else {
                        boolean z = this.done;
                        if (!z || (th = this.error) == null) {
                            Object poll = spscArrayQueue.poll();
                            boolean z2 = poll == null;
                            if (z && z2) {
                                cVar.onComplete();
                                this.worker.dispose();
                                return;
                            } else if (z2) {
                                break;
                            } else {
                                cVar.onNext(poll);
                                j2++;
                                i2++;
                                if (i2 == i3) {
                                    this.upstream.request((long) i2);
                                    i2 = 0;
                                }
                            }
                        } else {
                            spscArrayQueue.clear();
                            cVar.onError(th);
                            this.worker.dispose();
                            return;
                        }
                    }
                }
                if (i == 0) {
                    if (this.cancelled) {
                        spscArrayQueue.clear();
                        return;
                    } else if (this.done) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            spscArrayQueue.clear();
                            cVar.onError(th2);
                            this.worker.dispose();
                            return;
                        } else if (spscArrayQueue.isEmpty()) {
                            cVar.onComplete();
                            this.worker.dispose();
                            return;
                        }
                    }
                }
                if (!(j2 == 0 || j == Long.MAX_VALUE)) {
                    this.requested.addAndGet(-j2);
                }
                int i5 = get();
                if (i5 == i4) {
                    this.consumed = i2;
                    i4 = addAndGet(-i4);
                    if (i4 == 0) {
                        return;
                    }
                } else {
                    i4 = i5;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class RunOnConditionalSubscriber<T> extends BaseRunOnSubscriber<T> {
        private static final long serialVersionUID = 1075119423897941642L;
        final io.reactivex.internal.a.a<? super T> downstream;

        RunOnConditionalSubscriber(io.reactivex.internal.a.a<? super T> aVar, int i, SpscArrayQueue<T> spscArrayQueue, w.c cVar) {
            super(i, spscArrayQueue, cVar);
            this.downstream = aVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                dVar.request((long) this.prefetch);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            Throwable th;
            int i2 = this.consumed;
            SpscArrayQueue spscArrayQueue = this.queue;
            io.reactivex.internal.a.a<? super T> aVar = this.downstream;
            int i3 = this.limit;
            int i4 = 1;
            while (true) {
                long j = this.requested.get();
                long j2 = 0;
                while (true) {
                    i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    if (i == 0) {
                        break;
                    } else if (this.cancelled) {
                        spscArrayQueue.clear();
                        return;
                    } else {
                        boolean z = this.done;
                        if (!z || (th = this.error) == null) {
                            Object poll = spscArrayQueue.poll();
                            boolean z2 = poll == null;
                            if (z && z2) {
                                aVar.onComplete();
                                this.worker.dispose();
                                return;
                            } else if (z2) {
                                break;
                            } else {
                                if (aVar.tryOnNext(poll)) {
                                    j2++;
                                }
                                i2++;
                                if (i2 == i3) {
                                    this.upstream.request((long) i2);
                                    i2 = 0;
                                }
                            }
                        } else {
                            spscArrayQueue.clear();
                            aVar.onError(th);
                            this.worker.dispose();
                            return;
                        }
                    }
                }
                if (i == 0) {
                    if (this.cancelled) {
                        spscArrayQueue.clear();
                        return;
                    } else if (this.done) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            spscArrayQueue.clear();
                            aVar.onError(th2);
                            this.worker.dispose();
                            return;
                        } else if (spscArrayQueue.isEmpty()) {
                            aVar.onComplete();
                            this.worker.dispose();
                            return;
                        }
                    }
                }
                if (!(j2 == 0 || j == Long.MAX_VALUE)) {
                    this.requested.addAndGet(-j2);
                }
                int i5 = get();
                if (i5 == i4) {
                    this.consumed = i2;
                    i4 = addAndGet(-i4);
                    if (i4 == 0) {
                        return;
                    }
                } else {
                    i4 = i5;
                }
            }
        }
    }
}
