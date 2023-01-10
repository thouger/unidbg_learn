package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.j;
import io.reactivex.processors.UnicastProcessor;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableWindowBoundarySupplier<T, B> extends a<T, g<T>> {
    final Callable<? extends b<B>> c;
    final int d;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super g<T>> cVar) {
        this.b.a((j) new WindowBoundaryMainSubscriber(cVar, this.d, this.c));
    }

    static final class WindowBoundaryMainSubscriber<T, B> extends AtomicInteger implements j<T>, Runnable, d {
        static final a<Object, Object> BOUNDARY_DISPOSED = new a<>(null);
        static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 2233020065421370272L;
        final AtomicReference<a<T, B>> boundarySubscriber = new AtomicReference<>();
        final int capacityHint;
        volatile boolean done;
        final c<? super g<T>> downstream;
        long emitted;
        final AtomicThrowable errors = new AtomicThrowable();
        final Callable<? extends b<B>> other;
        final MpscLinkedQueue<Object> queue = new MpscLinkedQueue<>();
        final AtomicLong requested;
        final AtomicBoolean stopWindows = new AtomicBoolean();
        d upstream;
        UnicastProcessor<T> window;
        final AtomicInteger windows = new AtomicInteger(1);

        WindowBoundaryMainSubscriber(c<? super g<T>> cVar, int i, Callable<? extends b<B>> callable) {
            this.downstream = cVar;
            this.capacityHint = i;
            this.other = callable;
            this.requested = new AtomicLong();
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                this.queue.offer(NEXT_WINDOW);
                drain();
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
            disposeBoundary();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            disposeBoundary();
            this.done = true;
            drain();
        }

        public void cancel() {
            if (this.stopWindows.compareAndSet(false, true)) {
                disposeBoundary();
                if (this.windows.decrementAndGet() == 0) {
                    this.upstream.cancel();
                }
            }
        }

        public void request(long j) {
            io.reactivex.internal.util.b.a(this.requested, j);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowableWindowBoundarySupplier$a<T, B>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void disposeBoundary() {
            io.reactivex.disposables.b bVar = (io.reactivex.disposables.b) this.boundarySubscriber.getAndSet(BOUNDARY_DISPOSED);
            if (bVar != null && bVar != BOUNDARY_DISPOSED) {
                bVar.dispose();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.windows.decrementAndGet() == 0) {
                this.upstream.cancel();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerNext(a<T, B> aVar) {
            this.boundarySubscriber.compareAndSet(aVar, null);
            this.queue.offer(NEXT_WINDOW);
            drain();
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable th) {
            this.upstream.cancel();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        /* access modifiers changed from: package-private */
        public void innerComplete() {
            this.upstream.cancel();
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
                                if (j != this.requested.get()) {
                                    UnicastProcessor<T> a = UnicastProcessor.a(this.capacityHint, this);
                                    this.window = a;
                                    this.windows.getAndIncrement();
                                    try {
                                        b bVar = (b) io.reactivex.internal.functions.a.a(this.other.call(), "The other Callable returned a null Publisher");
                                        a<T, B> aVar = new a<>(this);
                                        if (this.boundarySubscriber.compareAndSet(null, aVar)) {
                                            bVar.subscribe(aVar);
                                            j++;
                                            cVar.onNext(a);
                                        }
                                    } catch (Throwable th) {
                                        io.reactivex.exceptions.a.b(th);
                                        atomicThrowable.addThrowable(th);
                                        this.done = true;
                                    }
                                } else {
                                    this.upstream.cancel();
                                    disposeBoundary();
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
                this.b = true;
                dispose();
                this.a.innerNext(this);
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
