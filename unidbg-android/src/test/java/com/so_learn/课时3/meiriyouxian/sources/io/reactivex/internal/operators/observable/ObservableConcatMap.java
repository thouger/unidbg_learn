package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.observers.c;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableConcatMap<T, U> extends a<T, U> {
    final h<? super T, ? extends t<? extends U>> b;
    final int c;
    final ErrorMode d;

    public ObservableConcatMap(t<T> tVar, h<? super T, ? extends t<? extends U>> hVar, int i, ErrorMode errorMode) {
        super(tVar);
        this.b = hVar;
        this.d = errorMode;
        this.c = Math.max(8, i);
    }

    @Override // io.reactivex.q
    public void a(v<? super U> vVar) {
        if (!ObservableScalarXMap.a(this.a, vVar, this.b)) {
            if (this.d == ErrorMode.IMMEDIATE) {
                this.a.subscribe(new SourceObserver(new c(vVar), this.b, this.c));
            } else {
                this.a.subscribe(new ConcatMapDelayErrorObserver(vVar, this.b, this.c, this.d == ErrorMode.END));
            }
        }
    }

    static final class SourceObserver<T, U> extends AtomicInteger implements b, v<T> {
        private static final long serialVersionUID = 8828587559905699186L;
        volatile boolean active;
        final int bufferSize;
        volatile boolean disposed;
        volatile boolean done;
        final v<? super U> downstream;
        int fusionMode;
        final InnerObserver<U> inner;
        final h<? super T, ? extends t<? extends U>> mapper;
        io.reactivex.internal.a.h<T> queue;
        b upstream;

        SourceObserver(v<? super U> vVar, h<? super T, ? extends t<? extends U>> hVar, int i) {
            this.downstream = vVar;
            this.mapper = hVar;
            this.bufferSize = i;
            this.inner = new InnerObserver<>(vVar, this);
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                if (bVar instanceof io.reactivex.internal.a.c) {
                    io.reactivex.internal.a.c cVar = (io.reactivex.internal.a.c) bVar;
                    int requestFusion = cVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = cVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
                        this.queue = cVar;
                        this.downstream.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new a(this.bufferSize);
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (!this.done) {
                if (this.fusionMode == 0) {
                    this.queue.offer(t);
                }
                drain();
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.done = true;
            dispose();
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerComplete() {
            this.active = false;
            drain();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.disposed = true;
            this.inner.dispose();
            this.upstream.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                while (!this.disposed) {
                    if (!this.active) {
                        boolean z = this.done;
                        try {
                            Object poll = this.queue.poll();
                            boolean z2 = poll == null;
                            if (z && z2) {
                                this.disposed = true;
                                this.downstream.onComplete();
                                return;
                            } else if (!z2) {
                                try {
                                    t tVar = (t) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null ObservableSource");
                                    this.active = true;
                                    tVar.subscribe(this.inner);
                                } catch (Throwable th) {
                                    io.reactivex.exceptions.a.b(th);
                                    dispose();
                                    this.queue.clear();
                                    this.downstream.onError(th);
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            io.reactivex.exceptions.a.b(th2);
                            dispose();
                            this.queue.clear();
                            this.downstream.onError(th2);
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                this.queue.clear();
            }
        }

        /* access modifiers changed from: package-private */
        public static final class InnerObserver<U> extends AtomicReference<b> implements v<U> {
            private static final long serialVersionUID = -7449079488798789337L;
            final v<? super U> downstream;
            final SourceObserver<?, ?> parent;

            InnerObserver(v<? super U> vVar, SourceObserver<?, ?> sourceObserver) {
                this.downstream = vVar;
                this.parent = sourceObserver;
            }

            @Override // io.reactivex.v
            public void onSubscribe(b bVar) {
                DisposableHelper.replace(this, bVar);
            }

            @Override // io.reactivex.v
            public void onNext(U u) {
                this.downstream.onNext(u);
            }

            @Override // io.reactivex.v
            public void onError(Throwable th) {
                this.parent.dispose();
                this.downstream.onError(th);
            }

            @Override // io.reactivex.v
            public void onComplete() {
                this.parent.innerComplete();
            }

            /* access modifiers changed from: package-private */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }

    static final class ConcatMapDelayErrorObserver<T, R> extends AtomicInteger implements b, v<T> {
        private static final long serialVersionUID = -6951100001833242599L;
        volatile boolean active;
        final int bufferSize;
        volatile boolean cancelled;
        volatile boolean done;
        final v<? super R> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final h<? super T, ? extends t<? extends R>> mapper;
        final DelayErrorInnerObserver<R> observer;
        io.reactivex.internal.a.h<T> queue;
        int sourceMode;
        final boolean tillTheEnd;
        b upstream;

        ConcatMapDelayErrorObserver(v<? super R> vVar, h<? super T, ? extends t<? extends R>> hVar, int i, boolean z) {
            this.downstream = vVar;
            this.mapper = hVar;
            this.bufferSize = i;
            this.tillTheEnd = z;
            this.observer = new DelayErrorInnerObserver<>(vVar, this);
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                if (bVar instanceof io.reactivex.internal.a.c) {
                    io.reactivex.internal.a.c cVar = (io.reactivex.internal.a.c) bVar;
                    int requestFusion = cVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = cVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = cVar;
                        this.downstream.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new a(this.bufferSize);
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (this.sourceMode == 0) {
                this.queue.offer(t);
            }
            drain();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.observer.dispose();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                v<? super R> vVar = this.downstream;
                io.reactivex.internal.a.h<T> hVar = this.queue;
                AtomicThrowable atomicThrowable = this.error;
                while (true) {
                    if (!this.active) {
                        if (this.cancelled) {
                            hVar.clear();
                            return;
                        } else if (this.tillTheEnd || atomicThrowable.get() == null) {
                            boolean z = this.done;
                            try {
                                Object poll = hVar.poll();
                                boolean z2 = poll == null;
                                if (z && z2) {
                                    this.cancelled = true;
                                    Throwable terminate = atomicThrowable.terminate();
                                    if (terminate != null) {
                                        vVar.onError(terminate);
                                        return;
                                    } else {
                                        vVar.onComplete();
                                        return;
                                    }
                                } else if (!z2) {
                                    try {
                                        t tVar = (t) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null ObservableSource");
                                        if (tVar instanceof Callable) {
                                            try {
                                                Object call = ((Callable) tVar).call();
                                                if (call != null && !this.cancelled) {
                                                    vVar.onNext(call);
                                                }
                                            } catch (Throwable th) {
                                                io.reactivex.exceptions.a.b(th);
                                                atomicThrowable.addThrowable(th);
                                            }
                                        } else {
                                            this.active = true;
                                            tVar.subscribe(this.observer);
                                        }
                                    } catch (Throwable th2) {
                                        io.reactivex.exceptions.a.b(th2);
                                        this.cancelled = true;
                                        this.upstream.dispose();
                                        hVar.clear();
                                        atomicThrowable.addThrowable(th2);
                                        vVar.onError(atomicThrowable.terminate());
                                        return;
                                    }
                                }
                            } catch (Throwable th3) {
                                io.reactivex.exceptions.a.b(th3);
                                this.cancelled = true;
                                this.upstream.dispose();
                                atomicThrowable.addThrowable(th3);
                                vVar.onError(atomicThrowable.terminate());
                                return;
                            }
                        } else {
                            hVar.clear();
                            this.cancelled = true;
                            vVar.onError(atomicThrowable.terminate());
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public static final class DelayErrorInnerObserver<R> extends AtomicReference<b> implements v<R> {
            private static final long serialVersionUID = 2620149119579502636L;
            final v<? super R> downstream;
            final ConcatMapDelayErrorObserver<?, R> parent;

            DelayErrorInnerObserver(v<? super R> vVar, ConcatMapDelayErrorObserver<?, R> concatMapDelayErrorObserver) {
                this.downstream = vVar;
                this.parent = concatMapDelayErrorObserver;
            }

            @Override // io.reactivex.v
            public void onSubscribe(b bVar) {
                DisposableHelper.replace(this, bVar);
            }

            @Override // io.reactivex.v
            public void onNext(R r) {
                this.downstream.onNext(r);
            }

            @Override // io.reactivex.v
            public void onError(Throwable th) {
                ConcatMapDelayErrorObserver<?, R> concatMapDelayErrorObserver = this.parent;
                if (concatMapDelayErrorObserver.error.addThrowable(th)) {
                    if (!concatMapDelayErrorObserver.tillTheEnd) {
                        concatMapDelayErrorObserver.upstream.dispose();
                    }
                    concatMapDelayErrorObserver.active = false;
                    concatMapDelayErrorObserver.drain();
                    return;
                }
                io.reactivex.e.a.a(th);
            }

            @Override // io.reactivex.v
            public void onComplete() {
                ConcatMapDelayErrorObserver<?, R> concatMapDelayErrorObserver = this.parent;
                concatMapDelayErrorObserver.active = false;
                concatMapDelayErrorObserver.drain();
            }

            /* access modifiers changed from: package-private */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
