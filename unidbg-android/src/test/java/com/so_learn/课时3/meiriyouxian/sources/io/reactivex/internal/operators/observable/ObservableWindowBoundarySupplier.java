package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.q;
import io.reactivex.subjects.UnicastSubject;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableWindowBoundarySupplier<T, B> extends a<T, q<T>> {
    final Callable<? extends t<B>> b;
    final int c;

    @Override // io.reactivex.q
    public void a(v<? super q<T>> vVar) {
        this.a.subscribe(new WindowBoundaryMainObserver(vVar, this.c, this.b));
    }

    static final class WindowBoundaryMainObserver<T, B> extends AtomicInteger implements b, v<T>, Runnable {
        static final a<Object, Object> BOUNDARY_DISPOSED = new a<>(null);
        static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 2233020065421370272L;
        final AtomicReference<a<T, B>> boundaryObserver = new AtomicReference<>();
        final int capacityHint;
        volatile boolean done;
        final v<? super q<T>> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        final Callable<? extends t<B>> other;
        final MpscLinkedQueue<Object> queue = new MpscLinkedQueue<>();
        final AtomicBoolean stopWindows = new AtomicBoolean();
        b upstream;
        UnicastSubject<T> window;
        final AtomicInteger windows = new AtomicInteger(1);

        WindowBoundaryMainObserver(v<? super q<T>> vVar, int i, Callable<? extends t<B>> callable) {
            this.downstream = vVar;
            this.capacityHint = i;
            this.other = callable;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
                this.queue.offer(NEXT_WINDOW);
                drain();
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            disposeBoundary();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            disposeBoundary();
            this.done = true;
            drain();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.stopWindows.compareAndSet(false, true)) {
                disposeBoundary();
                if (this.windows.decrementAndGet() == 0) {
                    this.upstream.dispose();
                }
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableWindowBoundarySupplier$a<T, B>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void disposeBoundary() {
            b bVar = (b) this.boundaryObserver.getAndSet(BOUNDARY_DISPOSED);
            if (bVar != null && bVar != BOUNDARY_DISPOSED) {
                bVar.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.stopWindows.get();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.windows.decrementAndGet() == 0) {
                this.upstream.dispose();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerNext(a<T, B> aVar) {
            this.boundaryObserver.compareAndSet(aVar, null);
            this.queue.offer(NEXT_WINDOW);
            drain();
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable th) {
            this.upstream.dispose();
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        /* access modifiers changed from: package-private */
        public void innerComplete() {
            this.upstream.dispose();
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
                                try {
                                    t tVar = (t) io.reactivex.internal.functions.a.a(this.other.call(), "The other Callable returned a null ObservableSource");
                                    a<T, B> aVar = new a<>(this);
                                    if (this.boundaryObserver.compareAndSet(null, aVar)) {
                                        tVar.subscribe(aVar);
                                        vVar.onNext(a);
                                    }
                                } catch (Throwable th) {
                                    io.reactivex.exceptions.a.b(th);
                                    atomicThrowable.addThrowable(th);
                                    this.done = true;
                                }
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

    /* access modifiers changed from: package-private */
    public static final class a<T, B> extends io.reactivex.observers.a<B> {
        final WindowBoundaryMainObserver<T, B> a;
        boolean b;

        a(WindowBoundaryMainObserver<T, B> windowBoundaryMainObserver) {
            this.a = windowBoundaryMainObserver;
        }

        @Override // io.reactivex.v
        public void onNext(B b) {
            if (!this.b) {
                this.b = true;
                dispose();
                this.a.innerNext(this);
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
