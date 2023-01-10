package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.j;
import io.reactivex.processors.UnicastProcessor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableWindowBoundary<T, B> extends a<T, g<T>> {
    final b<B> c;
    final int d;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super g<T>> cVar) {
        WindowBoundaryMainSubscriber windowBoundaryMainSubscriber = new WindowBoundaryMainSubscriber(cVar, this.d);
        cVar.onSubscribe(windowBoundaryMainSubscriber);
        windowBoundaryMainSubscriber.innerNext();
        this.c.subscribe(windowBoundaryMainSubscriber.boundarySubscriber);
        this.b.a((j) windowBoundaryMainSubscriber);
    }

    static final class WindowBoundaryMainSubscriber<T, B> extends AtomicInteger implements j<T>, Runnable, d {
        static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 2233020065421370272L;
        final a<T, B> boundarySubscriber = new a<>(this);
        final int capacityHint;
        volatile boolean done;
        final c<? super g<T>> downstream;
        long emitted;
        final AtomicThrowable errors = new AtomicThrowable();
        final MpscLinkedQueue<Object> queue = new MpscLinkedQueue<>();
        final AtomicLong requested = new AtomicLong();
        final AtomicBoolean stopWindows = new AtomicBoolean();
        final AtomicReference<d> upstream = new AtomicReference<>();
        UnicastProcessor<T> window;
        final AtomicInteger windows = new AtomicInteger(1);

        WindowBoundaryMainSubscriber(c<? super g<T>> cVar, int i) {
            this.downstream = cVar;
            this.capacityHint = i;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.setOnce(this.upstream, dVar, Long.MAX_VALUE);
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
            this.boundarySubscriber.dispose();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            this.boundarySubscriber.dispose();
            this.done = true;
            drain();
        }

        public void cancel() {
            if (this.stopWindows.compareAndSet(false, true)) {
                this.boundarySubscriber.dispose();
                if (this.windows.decrementAndGet() == 0) {
                    SubscriptionHelper.cancel(this.upstream);
                }
            }
        }

        public void request(long j) {
            io.reactivex.internal.util.b.a(this.requested, j);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.windows.decrementAndGet() == 0) {
                SubscriptionHelper.cancel(this.upstream);
            }
        }

        /* access modifiers changed from: package-private */
        public void innerNext() {
            this.queue.offer(NEXT_WINDOW);
            drain();
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable th) {
            SubscriptionHelper.cancel(this.upstream);
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        /* access modifiers changed from: package-private */
        public void innerComplete() {
            SubscriptionHelper.cancel(this.upstream);
            this.done = true;
            drain();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                c<? super g<T>> cVar = this.downstream;
                MpscLinkedQueue<Object> mpscLinkedQueue = this.queue;
                AtomicThrowable atomicThrowable = this.errors;
                long j = this.emitted;
                int i = 1;
                while (this.windows.get() != 0) {
                    UnicastProcessor<T> unicastProcessor = this.window;
                    boolean z = this.done;
                    if (!z || atomicThrowable.get() == null) {
                        Object poll = mpscLinkedQueue.poll();
                        boolean z2 = poll == null;
                        if (z && z2) {
                            Throwable terminate = atomicThrowable.terminate();
                            if (terminate == null) {
                                if (unicastProcessor != null) {
                                    this.window = null;
                                    unicastProcessor.onComplete();
                                }
                                cVar.onComplete();
                                return;
                            }
                            if (unicastProcessor != null) {
                                this.window = null;
                                unicastProcessor.onError(terminate);
                            }
                            cVar.onError(terminate);
                            return;
                        } else if (z2) {
                            this.emitted = j;
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else if (poll != NEXT_WINDOW) {
                            unicastProcessor.onNext(poll);
                        } else {
                            if (unicastProcessor != null) {
                                this.window = null;
                                unicastProcessor.onComplete();
                            }
                            if (!this.stopWindows.get()) {
                                UnicastProcessor<T> a = UnicastProcessor.a(this.capacityHint, this);
                                this.window = a;
                                this.windows.getAndIncrement();
                                if (j != this.requested.get()) {
                                    j++;
                                    cVar.onNext(a);
                                } else {
                                    SubscriptionHelper.cancel(this.upstream);
                                    this.boundarySubscriber.dispose();
                                    atomicThrowable.addThrowable(new MissingBackpressureException("Could not deliver a window due to lack of requests"));
                                    this.done = true;
                                }
                            }
                        }
                    } else {
                        mpscLinkedQueue.clear();
                        Throwable terminate2 = atomicThrowable.terminate();
                        if (unicastProcessor != null) {
                            this.window = null;
                            unicastProcessor.onError(terminate2);
                        }
                        cVar.onError(terminate2);
                        return;
                    }
                }
                mpscLinkedQueue.clear();
                this.window = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class a<T, B> extends io.reactivex.subscribers.a<B> {
        final WindowBoundaryMainSubscriber<T, B> a;
        boolean b;

        a(WindowBoundaryMainSubscriber<T, B> windowBoundaryMainSubscriber) {
            this.a = windowBoundaryMainSubscriber;
        }

        public void onNext(B b) {
            if (!this.b) {
                this.a.innerNext();
            }
        }

        public void onError(Throwable th) {
            if (this.b) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.b = true;
            this.a.innerError(th);
        }

        public void onComplete() {
            if (!this.b) {
                this.b = true;
                this.a.innerComplete();
            }
        }
    }
}
