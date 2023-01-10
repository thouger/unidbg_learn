package io.reactivex.internal.operators.observable;

import io.reactivex.c.d;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableSequenceEqual<T> extends q<Boolean> {
    final t<? extends T> a;
    final t<? extends T> b;
    final d<? super T, ? super T> c;
    final int d;

    public ObservableSequenceEqual(t<? extends T> tVar, t<? extends T> tVar2, d<? super T, ? super T> dVar, int i) {
        this.a = tVar;
        this.b = tVar2;
        this.c = dVar;
        this.d = i;
    }

    @Override // io.reactivex.q
    public void a(v<? super Boolean> vVar) {
        EqualCoordinator equalCoordinator = new EqualCoordinator(vVar, this.d, this.a, this.b, this.c);
        vVar.onSubscribe(equalCoordinator);
        equalCoordinator.subscribe();
    }

    static final class EqualCoordinator<T> extends AtomicInteger implements b {
        private static final long serialVersionUID = -6178010334400373240L;
        volatile boolean cancelled;
        final d<? super T, ? super T> comparer;
        final v<? super Boolean> downstream;
        final t<? extends T> first;
        final a<T>[] observers;
        final ArrayCompositeDisposable resources = new ArrayCompositeDisposable(2);
        final t<? extends T> second;
        T v1;
        T v2;

        EqualCoordinator(v<? super Boolean> vVar, int i, t<? extends T> tVar, t<? extends T> tVar2, d<? super T, ? super T> dVar) {
            this.downstream = vVar;
            this.first = tVar;
            this.second = tVar2;
            this.comparer = dVar;
            a<T>[] aVarArr = new a[2];
            this.observers = aVarArr;
            aVarArr[0] = new a<>(this, 0, i);
            aVarArr[1] = new a<>(this, 1, i);
        }

        /* access modifiers changed from: package-private */
        public boolean setDisposable(b bVar, int i) {
            return this.resources.setResource(i, bVar);
        }

        /* access modifiers changed from: package-private */
        public void subscribe() {
            a<T>[] aVarArr = this.observers;
            this.first.subscribe(aVarArr[0]);
            this.second.subscribe(aVarArr[1]);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.resources.dispose();
                if (getAndIncrement() == 0) {
                    a<T>[] aVarArr = this.observers;
                    aVarArr[0].b.clear();
                    aVarArr[1].b.clear();
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void cancel(io.reactivex.internal.queue.a<T> aVar, io.reactivex.internal.queue.a<T> aVar2) {
            this.cancelled = true;
            aVar.clear();
            aVar2.clear();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            Throwable th;
            Throwable th2;
            if (getAndIncrement() == 0) {
                a<T>[] aVarArr = this.observers;
                a<T> aVar = aVarArr[0];
                io.reactivex.internal.queue.a<T> aVar2 = aVar.b;
                a<T> aVar3 = aVarArr[1];
                io.reactivex.internal.queue.a<T> aVar4 = aVar3.b;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = aVar.d;
                    if (!z || (th2 = aVar.e) == null) {
                        boolean z2 = aVar3.d;
                        if (!z2 || (th = aVar3.e) == null) {
                            if (this.v1 == null) {
                                this.v1 = (T) aVar2.poll();
                            }
                            boolean z3 = this.v1 == null;
                            if (this.v2 == null) {
                                this.v2 = (T) aVar4.poll();
                            }
                            boolean z4 = this.v2 == null;
                            if (z && z2 && z3 && z4) {
                                this.downstream.onNext(true);
                                this.downstream.onComplete();
                                return;
                            } else if (!z || !z2 || z3 == z4) {
                                if (!z3 && !z4) {
                                    try {
                                        if (!this.comparer.a(this.v1, this.v2)) {
                                            cancel(aVar2, aVar4);
                                            this.downstream.onNext(false);
                                            this.downstream.onComplete();
                                            return;
                                        }
                                        this.v1 = null;
                                        this.v2 = null;
                                    } catch (Throwable th3) {
                                        io.reactivex.exceptions.a.b(th3);
                                        cancel(aVar2, aVar4);
                                        this.downstream.onError(th3);
                                        return;
                                    }
                                }
                                if (z3 || z4) {
                                    i = addAndGet(-i);
                                    if (i == 0) {
                                        return;
                                    }
                                }
                            } else {
                                cancel(aVar2, aVar4);
                                this.downstream.onNext(false);
                                this.downstream.onComplete();
                                return;
                            }
                        } else {
                            cancel(aVar2, aVar4);
                            this.downstream.onError(th);
                            return;
                        }
                    } else {
                        cancel(aVar2, aVar4);
                        this.downstream.onError(th2);
                        return;
                    }
                }
                aVar2.clear();
                aVar4.clear();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class a<T> implements v<T> {
        final EqualCoordinator<T> a;
        final io.reactivex.internal.queue.a<T> b;
        final int c;
        volatile boolean d;
        Throwable e;

        a(EqualCoordinator<T> equalCoordinator, int i, int i2) {
            this.a = equalCoordinator;
            this.c = i;
            this.b = new io.reactivex.internal.queue.a<>(i2);
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            this.a.setDisposable(bVar, this.c);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.b.offer(t);
            this.a.drain();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.e = th;
            this.d = true;
            this.a.drain();
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.d = true;
            this.a.drain();
        }
    }
}
