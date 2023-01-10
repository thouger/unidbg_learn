package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.a.c;
import io.reactivex.internal.a.h;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.schedulers.k;
import io.reactivex.t;
import io.reactivex.v;
import io.reactivex.w;

public final class ObservableObserveOn<T> extends a<T, T> {
    final w b;
    final boolean c;
    final int d;

    public ObservableObserveOn(t<T> tVar, w wVar, boolean z, int i) {
        super(tVar);
        this.b = wVar;
        this.c = z;
        this.d = i;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        w wVar = this.b;
        if (wVar instanceof k) {
            this.a.subscribe(vVar);
            return;
        }
        this.a.subscribe(new ObserveOnObserver(vVar, wVar.a(), this.c, this.d));
    }

    static final class ObserveOnObserver<T> extends BasicIntQueueDisposable<T> implements v<T>, Runnable {
        private static final long serialVersionUID = 6576896619930983584L;
        final int bufferSize;
        final boolean delayError;
        volatile boolean disposed;
        volatile boolean done;
        final v<? super T> downstream;
        Throwable error;
        boolean outputFused;
        h<T> queue;
        int sourceMode;
        b upstream;
        final w.c worker;

        ObserveOnObserver(v<? super T> vVar, w.c cVar, boolean z, int i) {
            this.downstream = vVar;
            this.worker = cVar;
            this.delayError = z;
            this.bufferSize = i;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                if (bVar instanceof c) {
                    c cVar = (c) bVar;
                    int requestFusion = cVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = cVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        schedule();
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
            if (!this.done) {
                if (this.sourceMode != 2) {
                    this.queue.offer(t);
                }
                schedule();
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.error = th;
            this.done = true;
            schedule();
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                schedule();
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!this.disposed) {
                this.disposed = true;
                this.upstream.dispose();
                this.worker.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.disposed;
        }

        /* access modifiers changed from: package-private */
        public void schedule() {
            if (getAndIncrement() == 0) {
                this.worker.a(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void drainNormal() {
            h<T> hVar = this.queue;
            v<? super T> vVar = this.downstream;
            int i = 1;
            while (!checkTerminated(this.done, hVar.isEmpty(), vVar)) {
                while (true) {
                    boolean z = this.done;
                    try {
                        Object poll = hVar.poll();
                        boolean z2 = poll == null;
                        if (!checkTerminated(z, z2, vVar)) {
                            if (z2) {
                                i = addAndGet(-i);
                                if (i == 0) {
                                    return;
                                }
                            } else {
                                vVar.onNext(poll);
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.disposed = true;
                        this.upstream.dispose();
                        hVar.clear();
                        vVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void drainFused() {
            int i = 1;
            while (!this.disposed) {
                boolean z = this.done;
                Throwable th = this.error;
                if (this.delayError || !z || th == null) {
                    this.downstream.onNext(null);
                    if (z) {
                        this.disposed = true;
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            this.downstream.onError(th2);
                        } else {
                            this.downstream.onComplete();
                        }
                        this.worker.dispose();
                        return;
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    this.disposed = true;
                    this.downstream.onError(th);
                    this.worker.dispose();
                    return;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.outputFused) {
                drainFused();
            } else {
                drainNormal();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean z, boolean z2, v<? super T> vVar) {
            if (this.disposed) {
                this.queue.clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                Throwable th = this.error;
                if (this.delayError) {
                    if (!z2) {
                        return false;
                    }
                    this.disposed = true;
                    if (th != null) {
                        vVar.onError(th);
                    } else {
                        vVar.onComplete();
                    }
                    this.worker.dispose();
                    return true;
                } else if (th != null) {
                    this.disposed = true;
                    this.queue.clear();
                    vVar.onError(th);
                    this.worker.dispose();
                    return true;
                } else if (!z2) {
                    return false;
                } else {
                    this.disposed = true;
                    vVar.onComplete();
                    this.worker.dispose();
                    return true;
                }
            }
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        @Override // io.reactivex.internal.a.h
        public T poll() throws Exception {
            return (T) this.queue.poll();
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            this.queue.clear();
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }
}
