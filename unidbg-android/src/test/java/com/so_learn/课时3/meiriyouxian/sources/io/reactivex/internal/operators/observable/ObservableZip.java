package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableZip<T, R> extends q<R> {
    final t<? extends T>[] a;
    final Iterable<? extends t<? extends T>> b;
    final h<? super Object[], ? extends R> c;
    final int d;
    final boolean e;

    public ObservableZip(t<? extends T>[] tVarArr, Iterable<? extends t<? extends T>> iterable, h<? super Object[], ? extends R> hVar, int i, boolean z) {
        this.a = tVarArr;
        this.b = iterable;
        this.c = hVar;
        this.d = i;
        this.e = z;
    }

    @Override // io.reactivex.q
    public void a(v<? super R> vVar) {
        int i;
        t<? extends T>[] tVarArr = this.a;
        if (tVarArr == null) {
            tVarArr = new q[8];
            i = 0;
            for (t<? extends T> tVar : this.b) {
                if (i == tVarArr.length) {
                    t<? extends T>[] tVarArr2 = new t[((i >> 2) + i)];
                    System.arraycopy(tVarArr, 0, tVarArr2, 0, i);
                    tVarArr = tVarArr2;
                }
                tVarArr[i] = tVar;
                i++;
            }
        } else {
            i = tVarArr.length;
        }
        if (i == 0) {
            EmptyDisposable.complete(vVar);
        } else {
            new ZipCoordinator(vVar, this.c, i, this.e).subscribe(tVarArr, this.d);
        }
    }

    static final class ZipCoordinator<T, R> extends AtomicInteger implements b {
        private static final long serialVersionUID = 2983708048395377667L;
        volatile boolean cancelled;
        final boolean delayError;
        final v<? super R> downstream;
        final a<T, R>[] observers;
        final T[] row;
        final h<? super Object[], ? extends R> zipper;

        ZipCoordinator(v<? super R> vVar, h<? super Object[], ? extends R> hVar, int i, boolean z) {
            this.downstream = vVar;
            this.zipper = hVar;
            this.observers = new a[i];
            this.row = (T[]) new Object[i];
            this.delayError = z;
        }

        public void subscribe(t<? extends T>[] tVarArr, int i) {
            a<T, R>[] aVarArr = this.observers;
            int length = aVarArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                aVarArr[i2] = new a<>(this, i);
            }
            lazySet(0);
            this.downstream.onSubscribe(this);
            for (int i3 = 0; i3 < length && !this.cancelled; i3++) {
                tVarArr[i3].subscribe(aVarArr[i3]);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelSources();
                if (getAndIncrement() == 0) {
                    clear();
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void cancel() {
            clear();
            cancelSources();
        }

        /* access modifiers changed from: package-private */
        public void cancelSources() {
            for (a<T, R> aVar : this.observers) {
                aVar.a();
            }
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            for (a<T, R> aVar : this.observers) {
                aVar.b.clear();
            }
        }

        public void drain() {
            Throwable th;
            if (getAndIncrement() == 0) {
                a<T, R>[] aVarArr = this.observers;
                v<? super R> vVar = this.downstream;
                T[] tArr = this.row;
                boolean z = this.delayError;
                int i = 1;
                while (true) {
                    int i2 = 0;
                    int i3 = 0;
                    for (a<T, R> aVar : aVarArr) {
                        if (tArr[i3] == null) {
                            boolean z2 = aVar.c;
                            Object poll = aVar.b.poll();
                            boolean z3 = poll == null;
                            if (checkTerminated(z2, z3, vVar, z, aVar)) {
                                return;
                            }
                            if (!z3) {
                                tArr[i3] = poll;
                            } else {
                                i2++;
                            }
                        } else if (aVar.c && !z && (th = aVar.d) != null) {
                            this.cancelled = true;
                            cancel();
                            vVar.onError(th);
                            return;
                        }
                        i3++;
                    }
                    if (i2 != 0) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        try {
                            vVar.onNext(io.reactivex.internal.functions.a.a(this.zipper.apply(tArr.clone()), "The zipper returned a null value"));
                            Arrays.fill(tArr, (Object) null);
                        } catch (Throwable th2) {
                            io.reactivex.exceptions.a.b(th2);
                            cancel();
                            vVar.onError(th2);
                            return;
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean z, boolean z2, v<? super R> vVar, boolean z3, a<?, ?> aVar) {
            if (this.cancelled) {
                cancel();
                return true;
            } else if (!z) {
                return false;
            } else {
                if (!z3) {
                    Throwable th = aVar.d;
                    if (th != null) {
                        this.cancelled = true;
                        cancel();
                        vVar.onError(th);
                        return true;
                    } else if (!z2) {
                        return false;
                    } else {
                        this.cancelled = true;
                        cancel();
                        vVar.onComplete();
                        return true;
                    }
                } else if (!z2) {
                    return false;
                } else {
                    Throwable th2 = aVar.d;
                    this.cancelled = true;
                    cancel();
                    if (th2 != null) {
                        vVar.onError(th2);
                    } else {
                        vVar.onComplete();
                    }
                    return true;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class a<T, R> implements v<T> {
        final ZipCoordinator<T, R> a;
        final io.reactivex.internal.queue.a<T> b;
        volatile boolean c;
        Throwable d;
        final AtomicReference<b> e = new AtomicReference<>();

        a(ZipCoordinator<T, R> zipCoordinator, int i) {
            this.a = zipCoordinator;
            this.b = new io.reactivex.internal.queue.a<>(i);
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.e, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.b.offer(t);
            this.a.drain();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.d = th;
            this.c = true;
            this.a.drain();
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.c = true;
            this.a.drain();
        }

        public void a() {
            DisposableHelper.dispose(this.e);
        }
    }
}
