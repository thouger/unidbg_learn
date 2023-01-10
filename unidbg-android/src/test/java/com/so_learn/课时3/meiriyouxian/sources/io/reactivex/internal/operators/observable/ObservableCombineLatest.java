package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCombineLatest<T, R> extends q<R> {
    final t<? extends T>[] a;
    final Iterable<? extends t<? extends T>> b;
    final h<? super Object[], ? extends R> c;
    final int d;
    final boolean e;

    public ObservableCombineLatest(t<? extends T>[] tVarArr, Iterable<? extends t<? extends T>> iterable, h<? super Object[], ? extends R> hVar, int i, boolean z) {
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
            new LatestCoordinator(vVar, this.c, i, this.d, this.e).subscribe(tVarArr);
        }
    }

    static final class LatestCoordinator<T, R> extends AtomicInteger implements b {
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        volatile boolean cancelled;
        final h<? super Object[], ? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final v<? super R> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        Object[] latest;
        final CombinerObserver<T, R>[] observers;
        final a<Object[]> queue;

        LatestCoordinator(v<? super R> vVar, h<? super Object[], ? extends R> hVar, int i, int i2, boolean z) {
            this.downstream = vVar;
            this.combiner = hVar;
            this.delayError = z;
            this.latest = new Object[i];
            CombinerObserver<T, R>[] combinerObserverArr = new CombinerObserver[i];
            for (int i3 = 0; i3 < i; i3++) {
                combinerObserverArr[i3] = new CombinerObserver<>(this, i3);
            }
            this.observers = combinerObserverArr;
            this.queue = new a<>(i2);
        }

        public void subscribe(t<? extends T>[] tVarArr) {
            CombinerObserver<T, R>[] combinerObserverArr = this.observers;
            int length = combinerObserverArr.length;
            this.downstream.onSubscribe(this);
            for (int i = 0; i < length && !this.done && !this.cancelled; i++) {
                tVarArr[i].subscribe(combinerObserverArr[i]);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelSources();
                if (getAndIncrement() == 0) {
                    clear(this.queue);
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void cancelSources() {
            for (CombinerObserver<T, R> combinerObserver : this.observers) {
                combinerObserver.dispose();
            }
        }

        /* access modifiers changed from: package-private */
        public void clear(a<?> aVar) {
            synchronized (this) {
                this.latest = null;
            }
            aVar.clear();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                a<Object[]> aVar = this.queue;
                v<? super R> vVar = this.downstream;
                boolean z = this.delayError;
                int i = 1;
                while (!this.cancelled) {
                    if (z || this.errors.get() == null) {
                        boolean z2 = this.done;
                        Object[] objArr = (Object[]) aVar.poll();
                        boolean z3 = objArr == null;
                        if (z2 && z3) {
                            clear(aVar);
                            Throwable terminate = this.errors.terminate();
                            if (terminate == null) {
                                vVar.onComplete();
                                return;
                            } else {
                                vVar.onError(terminate);
                                return;
                            }
                        } else if (z3) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else {
                            try {
                                vVar.onNext(io.reactivex.internal.functions.a.a(this.combiner.apply(objArr), "The combiner returned a null value"));
                            } catch (Throwable th) {
                                io.reactivex.exceptions.a.b(th);
                                this.errors.addThrowable(th);
                                cancelSources();
                                clear(aVar);
                                vVar.onError(this.errors.terminate());
                                return;
                            }
                        }
                    } else {
                        cancelSources();
                        clear(aVar);
                        vVar.onError(this.errors.terminate());
                        return;
                    }
                }
                clear(aVar);
            }
        }

        /* access modifiers changed from: package-private */
        public void innerNext(int i, T t) {
            boolean z;
            synchronized (this) {
                Object[] objArr = this.latest;
                if (objArr != null) {
                    Object obj = objArr[i];
                    int i2 = this.active;
                    if (obj == null) {
                        i2++;
                        this.active = i2;
                    }
                    objArr[i] = t;
                    if (i2 == objArr.length) {
                        this.queue.offer(objArr.clone());
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    return;
                }
            }
            if (z) {
                drain();
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
            if (r1 == r4.length) goto L_0x0025;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void innerError(int r3, java.lang.Throwable r4) {
            /*
                r2 = this;
                io.reactivex.internal.util.AtomicThrowable r0 = r2.errors
                boolean r0 = r0.addThrowable(r4)
                if (r0 == 0) goto L_0x0036
                boolean r4 = r2.delayError
                r0 = 1
                if (r4 == 0) goto L_0x002c
                monitor-enter(r2)
                java.lang.Object[] r4 = r2.latest     // Catch:{ all -> 0x0029 }
                if (r4 != 0) goto L_0x0014
                monitor-exit(r2)     // Catch:{ all -> 0x0029 }
                return
            L_0x0014:
                r3 = r4[r3]     // Catch:{ all -> 0x0029 }
                if (r3 != 0) goto L_0x001a
                r3 = r0
                goto L_0x001b
            L_0x001a:
                r3 = 0
            L_0x001b:
                if (r3 != 0) goto L_0x0025
                int r1 = r2.complete     // Catch:{ all -> 0x0029 }
                int r1 = r1 + r0
                r2.complete = r1     // Catch:{ all -> 0x0029 }
                int r4 = r4.length     // Catch:{ all -> 0x0029 }
                if (r1 != r4) goto L_0x0027
            L_0x0025:
                r2.done = r0     // Catch:{ all -> 0x0029 }
            L_0x0027:
                monitor-exit(r2)     // Catch:{ all -> 0x0029 }
                goto L_0x002d
            L_0x0029:
                r3 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0029 }
                throw r3
            L_0x002c:
                r3 = r0
            L_0x002d:
                if (r3 == 0) goto L_0x0032
                r2.cancelSources()
            L_0x0032:
                r2.drain()
                goto L_0x0039
            L_0x0036:
                io.reactivex.e.a.a(r4)
            L_0x0039:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableCombineLatest.LatestCoordinator.innerError(int, java.lang.Throwable):void");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
            if (r2 == r0.length) goto L_0x0019;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void innerComplete(int r4) {
            /*
                r3 = this;
                monitor-enter(r3)
                java.lang.Object[] r0 = r3.latest     // Catch:{ all -> 0x0025 }
                if (r0 != 0) goto L_0x0007
                monitor-exit(r3)     // Catch:{ all -> 0x0025 }
                return
            L_0x0007:
                r4 = r0[r4]     // Catch:{ all -> 0x0025 }
                r1 = 1
                if (r4 != 0) goto L_0x000e
                r4 = r1
                goto L_0x000f
            L_0x000e:
                r4 = 0
            L_0x000f:
                if (r4 != 0) goto L_0x0019
                int r2 = r3.complete     // Catch:{ all -> 0x0025 }
                int r2 = r2 + r1
                r3.complete = r2     // Catch:{ all -> 0x0025 }
                int r0 = r0.length     // Catch:{ all -> 0x0025 }
                if (r2 != r0) goto L_0x001b
            L_0x0019:
                r3.done = r1     // Catch:{ all -> 0x0025 }
            L_0x001b:
                monitor-exit(r3)     // Catch:{ all -> 0x0025 }
                if (r4 == 0) goto L_0x0021
                r3.cancelSources()
            L_0x0021:
                r3.drain()
                return
            L_0x0025:
                r4 = move-exception
                monitor-exit(r3)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableCombineLatest.LatestCoordinator.innerComplete(int):void");
        }
    }

    /* access modifiers changed from: package-private */
    public static final class CombinerObserver<T, R> extends AtomicReference<b> implements v<T> {
        private static final long serialVersionUID = -4823716997131257941L;
        final int index;
        final LatestCoordinator<T, R> parent;

        CombinerObserver(LatestCoordinator<T, R> latestCoordinator, int i) {
            this.parent = latestCoordinator;
            this.index = i;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.parent.innerNext(this.index, t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.parent.innerComplete(this.index);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
