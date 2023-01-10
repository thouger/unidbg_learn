package io.reactivex.internal.operators.mixed;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.a.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.q;
import io.reactivex.v;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableConcatMapSingle<T, R> extends q<R> {
    final q<T> a;
    final h<? super T, ? extends ab<? extends R>> b;
    final ErrorMode c;
    final int d;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super R> vVar) {
        if (!a.b(this.a, this.b, vVar)) {
            this.a.subscribe(new ConcatMapSingleMainObserver(vVar, this.b, this.d, this.c));
        }
    }

    static final class ConcatMapSingleMainObserver<T, R> extends AtomicInteger implements b, v<T> {
        static final int STATE_ACTIVE = 1;
        static final int STATE_INACTIVE = 0;
        static final int STATE_RESULT_VALUE = 2;
        private static final long serialVersionUID = -9140123220065488293L;
        volatile boolean cancelled;
        volatile boolean done;
        final v<? super R> downstream;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final ConcatMapSingleObserver<R> inner = new ConcatMapSingleObserver<>(this);
        R item;
        final h<? super T, ? extends ab<? extends R>> mapper;
        final g<T> queue;
        volatile int state;
        b upstream;

        ConcatMapSingleMainObserver(v<? super R> vVar, h<? super T, ? extends ab<? extends R>> hVar, int i, ErrorMode errorMode) {
            this.downstream = vVar;
            this.mapper = hVar;
            this.errorMode = errorMode;
            this.queue = new a(i);
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (this.errorMode == ErrorMode.IMMEDIATE) {
                    this.inner.dispose();
                }
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
            this.cancelled = true;
            this.upstream.dispose();
            this.inner.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
                this.item = null;
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
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
                    this.upstream.dispose();
                }
                this.state = 0;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                v<? super R> vVar = this.downstream;
                ErrorMode errorMode = this.errorMode;
                g<T> gVar = this.queue;
                AtomicThrowable atomicThrowable = this.errors;
                int i = 1;
                while (true) {
                    if (this.cancelled) {
                        gVar.clear();
                        this.item = null;
                    } else {
                        int i2 = this.state;
                        if (atomicThrowable.get() == null || !(errorMode == ErrorMode.IMMEDIATE || (errorMode == ErrorMode.BOUNDARY && i2 == 0))) {
                            boolean z = false;
                            if (i2 == 0) {
                                boolean z2 = this.done;
                                Object poll = gVar.poll();
                                if (poll == null) {
                                    z = true;
                                }
                                if (z2 && z) {
                                    Throwable terminate = atomicThrowable.terminate();
                                    if (terminate == null) {
                                        vVar.onComplete();
                                        return;
                                    } else {
                                        vVar.onError(terminate);
                                        return;
                                    }
                                } else if (!z) {
                                    try {
                                        ab abVar = (ab) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null SingleSource");
                                        this.state = 1;
                                        abVar.a(this.inner);
                                    } catch (Throwable th) {
                                        io.reactivex.exceptions.a.b(th);
                                        this.upstream.dispose();
                                        gVar.clear();
                                        atomicThrowable.addThrowable(th);
                                        vVar.onError(atomicThrowable.terminate());
                                        return;
                                    }
                                }
                            } else if (i2 == 2) {
                                R r = this.item;
                                this.item = null;
                                vVar.onNext(r);
                                this.state = 0;
                            }
                        }
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
                gVar.clear();
                this.item = null;
                vVar.onError(atomicThrowable.terminate());
            }
        }

        /* access modifiers changed from: package-private */
        public static final class ConcatMapSingleObserver<R> extends AtomicReference<b> implements z<R> {
            private static final long serialVersionUID = -3051469169682093892L;
            final ConcatMapSingleMainObserver<?, R> parent;

            ConcatMapSingleObserver(ConcatMapSingleMainObserver<?, R> concatMapSingleMainObserver) {
                this.parent = concatMapSingleMainObserver;
            }

            @Override // io.reactivex.z
            public void onSubscribe(b bVar) {
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
