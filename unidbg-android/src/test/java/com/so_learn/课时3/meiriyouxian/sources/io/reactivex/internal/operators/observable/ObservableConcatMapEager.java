package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.a.c;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.InnerQueuedObserver;
import io.reactivex.internal.observers.d;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.t;
import io.reactivex.v;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableConcatMapEager<T, R> extends a<T, R> {
    final h<? super T, ? extends t<? extends R>> b;
    final ErrorMode c;
    final int d;
    final int e;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super R> vVar) {
        this.a.subscribe(new ConcatMapEagerMainObserver(vVar, this.b, this.d, this.e, this.c));
    }

    static final class ConcatMapEagerMainObserver<T, R> extends AtomicInteger implements b, d<R>, v<T> {
        private static final long serialVersionUID = 8080567949447303262L;
        int activeCount;
        volatile boolean cancelled;
        InnerQueuedObserver<R> current;
        volatile boolean done;
        final v<? super R> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final ErrorMode errorMode;
        final h<? super T, ? extends t<? extends R>> mapper;
        final int maxConcurrency;
        final ArrayDeque<InnerQueuedObserver<R>> observers = new ArrayDeque<>();
        final int prefetch;
        io.reactivex.internal.a.h<T> queue;
        int sourceMode;
        b upstream;

        ConcatMapEagerMainObserver(v<? super R> vVar, h<? super T, ? extends t<? extends R>> hVar, int i, int i2, ErrorMode errorMode) {
            this.downstream = vVar;
            this.mapper = hVar;
            this.maxConcurrency = i;
            this.prefetch = i2;
            this.errorMode = errorMode;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                if (bVar instanceof c) {
                    c cVar = (c) bVar;
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
                this.queue = new a(this.prefetch);
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
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
                drainAndDispose();
            }
        }

        /* access modifiers changed from: package-private */
        public void drainAndDispose() {
            if (getAndIncrement() == 0) {
                do {
                    this.queue.clear();
                    disposeAll();
                } while (decrementAndGet() != 0);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void disposeAll() {
            InnerQueuedObserver<R> innerQueuedObserver = this.current;
            if (innerQueuedObserver != null) {
                innerQueuedObserver.dispose();
            }
            while (true) {
                InnerQueuedObserver<R> poll = this.observers.poll();
                if (poll != null) {
                    poll.dispose();
                } else {
                    return;
                }
            }
        }

        @Override // io.reactivex.internal.observers.d
        public void innerNext(InnerQueuedObserver<R> innerQueuedObserver, R r) {
            innerQueuedObserver.queue().offer(r);
            drain();
        }

        @Override // io.reactivex.internal.observers.d
        public void innerError(InnerQueuedObserver<R> innerQueuedObserver, Throwable th) {
            if (this.error.addThrowable(th)) {
                if (this.errorMode == ErrorMode.IMMEDIATE) {
                    this.upstream.dispose();
                }
                innerQueuedObserver.setDone();
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.internal.observers.d
        public void innerComplete(InnerQueuedObserver<R> innerQueuedObserver) {
            innerQueuedObserver.setDone();
            drain();
        }

        @Override // io.reactivex.internal.observers.d
        public void drain() {
            if (getAndIncrement() == 0) {
                io.reactivex.internal.a.h<T> hVar = this.queue;
                ArrayDeque<InnerQueuedObserver<R>> arrayDeque = this.observers;
                v<? super R> vVar = this.downstream;
                ErrorMode errorMode = this.errorMode;
                int i = 1;
                while (true) {
                    int i2 = this.activeCount;
                    while (true) {
                        if (i2 == this.maxConcurrency) {
                            break;
                        } else if (this.cancelled) {
                            hVar.clear();
                            disposeAll();
                            return;
                        } else if (errorMode != ErrorMode.IMMEDIATE || this.error.get() == null) {
                            try {
                                Object poll = hVar.poll();
                                if (poll == null) {
                                    break;
                                }
                                t tVar = (t) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null ObservableSource");
                                InnerQueuedObserver<R> innerQueuedObserver = new InnerQueuedObserver<>(this, this.prefetch);
                                arrayDeque.offer(innerQueuedObserver);
                                tVar.subscribe(innerQueuedObserver);
                                i2++;
                            } catch (Throwable th) {
                                io.reactivex.exceptions.a.b(th);
                                this.upstream.dispose();
                                hVar.clear();
                                disposeAll();
                                this.error.addThrowable(th);
                                vVar.onError(this.error.terminate());
                                return;
                            }
                        } else {
                            hVar.clear();
                            disposeAll();
                            vVar.onError(this.error.terminate());
                            return;
                        }
                    }
                    this.activeCount = i2;
                    if (this.cancelled) {
                        hVar.clear();
                        disposeAll();
                        return;
                    } else if (errorMode != ErrorMode.IMMEDIATE || this.error.get() == null) {
                        InnerQueuedObserver<R> innerQueuedObserver2 = this.current;
                        if (innerQueuedObserver2 == null) {
                            if (errorMode != ErrorMode.BOUNDARY || this.error.get() == null) {
                                boolean z = this.done;
                                InnerQueuedObserver<R> poll2 = arrayDeque.poll();
                                boolean z2 = poll2 == null;
                                if (!z || !z2) {
                                    if (!z2) {
                                        this.current = poll2;
                                    }
                                    innerQueuedObserver2 = poll2;
                                } else if (this.error.get() != null) {
                                    hVar.clear();
                                    disposeAll();
                                    vVar.onError(this.error.terminate());
                                    return;
                                } else {
                                    vVar.onComplete();
                                    return;
                                }
                            } else {
                                hVar.clear();
                                disposeAll();
                                vVar.onError(this.error.terminate());
                                return;
                            }
                        }
                        if (innerQueuedObserver2 != null) {
                            io.reactivex.internal.a.h<R> queue = innerQueuedObserver2.queue();
                            while (!this.cancelled) {
                                boolean isDone = innerQueuedObserver2.isDone();
                                if (errorMode != ErrorMode.IMMEDIATE || this.error.get() == null) {
                                    try {
                                        Object poll3 = queue.poll();
                                        boolean z3 = poll3 == null;
                                        if (isDone && z3) {
                                            this.current = null;
                                            this.activeCount--;
                                        } else if (!z3) {
                                            vVar.onNext(poll3);
                                        }
                                    } catch (Throwable th2) {
                                        io.reactivex.exceptions.a.b(th2);
                                        this.error.addThrowable(th2);
                                        this.current = null;
                                        this.activeCount--;
                                    }
                                } else {
                                    hVar.clear();
                                    disposeAll();
                                    vVar.onError(this.error.terminate());
                                    return;
                                }
                            }
                            hVar.clear();
                            disposeAll();
                            return;
                        }
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        hVar.clear();
                        disposeAll();
                        vVar.onError(this.error.terminate());
                        return;
                    }
                }
            }
        }
    }
}
