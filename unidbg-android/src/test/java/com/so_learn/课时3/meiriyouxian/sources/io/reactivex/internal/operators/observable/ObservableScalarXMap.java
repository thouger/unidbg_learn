package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.internal.a.c;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableScalarXMap {
    public static <T, R> boolean a(t<T> tVar, v<? super R> vVar, h<? super T, ? extends t<? extends R>> hVar) {
        if (!(tVar instanceof Callable)) {
            return false;
        }
        try {
            Object call = ((Callable) tVar).call();
            if (call == null) {
                EmptyDisposable.complete(vVar);
                return true;
            }
            try {
                t tVar2 = (t) io.reactivex.internal.functions.a.a(hVar.apply(call), "The mapper returned a null ObservableSource");
                if (tVar2 instanceof Callable) {
                    try {
                        Object call2 = ((Callable) tVar2).call();
                        if (call2 == null) {
                            EmptyDisposable.complete(vVar);
                            return true;
                        }
                        ScalarDisposable scalarDisposable = new ScalarDisposable(vVar, call2);
                        vVar.onSubscribe(scalarDisposable);
                        scalarDisposable.run();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        EmptyDisposable.error(th, vVar);
                        return true;
                    }
                } else {
                    tVar2.subscribe(vVar);
                }
                return true;
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                EmptyDisposable.error(th2, vVar);
                return true;
            }
        } catch (Throwable th3) {
            io.reactivex.exceptions.a.b(th3);
            EmptyDisposable.error(th3, vVar);
            return true;
        }
    }

    public static <T, U> q<U> a(T t, h<? super T, ? extends t<? extends U>> hVar) {
        return io.reactivex.e.a.a(new a(t, hVar));
    }

    /* access modifiers changed from: package-private */
    public static final class a<T, R> extends q<R> {
        final T a;
        final h<? super T, ? extends t<? extends R>> b;

        a(T t, h<? super T, ? extends t<? extends R>> hVar) {
            this.a = t;
            this.b = hVar;
        }

        @Override // io.reactivex.q
        public void a(v<? super R> vVar) {
            try {
                t tVar = (t) io.reactivex.internal.functions.a.a(this.b.apply(this.a), "The mapper returned a null ObservableSource");
                if (tVar instanceof Callable) {
                    try {
                        Object call = ((Callable) tVar).call();
                        if (call == null) {
                            EmptyDisposable.complete(vVar);
                            return;
                        }
                        ScalarDisposable scalarDisposable = new ScalarDisposable(vVar, call);
                        vVar.onSubscribe(scalarDisposable);
                        scalarDisposable.run();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        EmptyDisposable.error(th, vVar);
                    }
                } else {
                    tVar.subscribe(vVar);
                }
            } catch (Throwable th2) {
                EmptyDisposable.error(th2, vVar);
            }
        }
    }

    public static final class ScalarDisposable<T> extends AtomicInteger implements c<T>, Runnable {
        static final int FUSED = 1;
        static final int ON_COMPLETE = 3;
        static final int ON_NEXT = 2;
        static final int START = 0;
        private static final long serialVersionUID = 3880992722410194083L;
        final v<? super T> observer;
        final T value;

        public ScalarDisposable(v<? super T> vVar, T t) {
            this.observer = vVar;
            this.value = t;
        }

        @Override // io.reactivex.internal.a.h
        public boolean offer(T t) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        @Override // io.reactivex.internal.a.h
        public T poll() throws Exception {
            if (get() != 1) {
                return null;
            }
            lazySet(3);
            return this.value;
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return get() != 1;
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            lazySet(3);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            set(3);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == 3;
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            lazySet(1);
            return 1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (get() == 0 && compareAndSet(0, 2)) {
                this.observer.onNext(this.value);
                if (get() == 2) {
                    lazySet(3);
                    this.observer.onComplete();
                }
            }
        }
    }
}
