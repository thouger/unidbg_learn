package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.q;
import io.reactivex.subjects.UnicastSubject;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableWindowBoundary<T, B> extends a<T, q<T>> {
    final t<B> b;
    final int c;

    @Override // io.reactivex.q
    public void a(v<? super q<T>> vVar) {
        WindowBoundaryMainObserver windowBoundaryMainObserver = new WindowBoundaryMainObserver(vVar, this.c);
        vVar.onSubscribe(windowBoundaryMainObserver);
        this.b.subscribe(windowBoundaryMainObserver.boundaryObserver);
        this.a.subscribe(windowBoundaryMainObserver);
    }

    static final class WindowBoundaryMainObserver<T, B> extends AtomicInteger implements b, v<T>, Runnable {
        static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 2233020065421370272L;
        final a<T, B> boundaryObserver = new a<>(this);
        final int capacityHint;
        volatile boolean done;
        final v<? super q<T>> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final MpscLinkedQueue<Object> queue = new MpscLinkedQueue<>();
        final AtomicBoolean stopWindows = new AtomicBoolean();
        final AtomicReference<b> upstream = new AtomicReference<>();
        UnicastSubject<T> window;
        final AtomicInteger windows = new AtomicInteger(1);

        WindowBoundaryMainObserver(v<? super q<T>> vVar, int i) {
            this.downstream = vVar;
            this.capacityHint = i;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this.upstream, bVar)) {
                innerNext();
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.boundaryObserver.dispose();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.boundaryObserver.dispose();
            this.done = true;
            drain();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.stopWindows.compareAndSet(false, true)) {
                this.boundaryObserver.dispose();
                if (this.windows.decrementAndGet() == 0) {
                    DisposableHelper.dispose(this.upstream);
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.stopWindows.get();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.windows.decrementAndGet() == 0) {
                DisposableHelper.dispose(this.upstream);
            }
        }

        /* access modifiers changed from: package-private */
        public void innerNext() {
            this.queue.offer(NEXT_WINDOW);
            drain();
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        /* access modifiers changed from: package-private */
        public void innerComplete() {
            DisposableHelper.dispose(this.upstream);
            this.done = true;
            drain();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                v<? super q<T>> vVar = this.downstream;
                MpscLinkedQueue<Object> mpscLinkedQueue = this.queue;
                AtomicThrowable atomicThrowable = this.errors;
                int i = 1;
                while (this.windows.get() != 0) {
                    UnicastSubject<T> unicastSubject = this.window;
                    boolean z = this.done;
                    if (!z || atomicThrowable.get() == null) {
                        Object poll = mpscLinkedQueue.poll();
                        boolean z2 = poll == null;
                        if (z && z2) {
                            Throwable terminate = atomicThrowable.terminate();
                            if (terminate == null) {
                                if (unicastSubject != null) {
                                    this.window = null;
                                    unicastSubject.onComplete();
                                }
                                vVar.onComplete();
                                return;
                            }
                            if (unicastSubject != null) {
                                this.window = null;
                                unicastSubject.onError(terminate);
                            }
                            vVar.onError(terminate);
                            return;
                        } else if (z2) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else if (poll != NEXT_WINDOW) {
                            unicastSubject.onNext(poll);
                        } else {
                            if (unicastSubject != null) {
                                this.window = null;
                                unicastSubject.onComplete();
                            }
                            if (!this.stopWindows.get()) {
                                UnicastSubject<T> a = UnicastSubject.a(this.capacityHint, this);
                                this.window = a;
                                this.windows.getAndIncrement();
                                vVar.onNext(a);
                            }
                        }
                    } else {
                        mpscLinkedQueue.clear();
                        Throwable terminate2 = atomicThrowable.terminate();
                        if (unicastSubject != null) {
                            this.window = null;
                            unicastSubject.onError(terminate2);
                        }
                        vVar.onError(terminate2);
                        return;
                    }
                }
                mpscLinkedQueue.clear();
                this.window = null;
            }
        }
    }

    static final class a<T, B> extends io.reactivex.observers.a<B> {
        final WindowBoundaryMainObserver<T, B> a;
        boolean b;

        a(WindowBoundaryMainObserver<T, B> windowBoundaryMainObserver) {
            this.a = windowBoundaryMainObserver;
        }

        @Override // io.reactivex.v
        public void onNext(B b) {
            if (!this.b) {
                this.a.innerNext();
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.b) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.b = true;
            this.a.innerError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.b) {
                this.b = true;
                this.a.innerComplete();
            }
        }
    }
}
