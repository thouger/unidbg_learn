package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableAmb<T> extends q<T> {
    final t<? extends T>[] a;
    final Iterable<? extends t<? extends T>> b;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        int i;
        t<? extends T>[] tVarArr = this.a;
        if (tVarArr == null) {
            tVarArr = new q[8];
            try {
                i = 0;
                for (t<? extends T> tVar : this.b) {
                    if (tVar == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), vVar);
                        return;
                    }
                    if (i == tVarArr.length) {
                        t<? extends T>[] tVarArr2 = new t[((i >> 2) + i)];
                        System.arraycopy(tVarArr, 0, tVarArr2, 0, i);
                        tVarArr = tVarArr2;
                    }
                    int i2 = i + 1;
                    tVarArr[i] = tVar;
                    i = i2;
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptyDisposable.error(th, vVar);
                return;
            }
        } else {
            i = tVarArr.length;
        }
        if (i == 0) {
            EmptyDisposable.complete(vVar);
        } else if (i == 1) {
            tVarArr[0].subscribe(vVar);
        } else {
            new a(vVar, i).a(tVarArr);
        }
    }

    static final class a<T> implements b {
        final v<? super T> a;
        final AmbInnerObserver<T>[] b;
        final AtomicInteger c = new AtomicInteger();

        a(v<? super T> vVar, int i) {
            this.a = vVar;
            this.b = new AmbInnerObserver[i];
        }

        public void a(t<? extends T>[] tVarArr) {
            AmbInnerObserver<T>[] ambInnerObserverArr = this.b;
            int length = ambInnerObserverArr.length;
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                ambInnerObserverArr[i] = new AmbInnerObserver<>(this, i2, this.a);
                i = i2;
            }
            this.c.lazySet(0);
            this.a.onSubscribe(this);
            for (int i3 = 0; i3 < length && this.c.get() == 0; i3++) {
                tVarArr[i3].subscribe(ambInnerObserverArr[i3]);
            }
        }

        public boolean a(int i) {
            int i2 = this.c.get();
            int i3 = 0;
            if (i2 != 0) {
                return i2 == i;
            }
            if (!this.c.compareAndSet(0, i)) {
                return false;
            }
            AmbInnerObserver<T>[] ambInnerObserverArr = this.b;
            int length = ambInnerObserverArr.length;
            while (i3 < length) {
                int i4 = i3 + 1;
                if (i4 != i) {
                    ambInnerObserverArr[i3].dispose();
                }
                i3 = i4;
            }
            return true;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.c.get() != -1) {
                this.c.lazySet(-1);
                for (AmbInnerObserver<T> ambInnerObserver : this.b) {
                    ambInnerObserver.dispose();
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.c.get() == -1;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class AmbInnerObserver<T> extends AtomicReference<b> implements v<T> {
        private static final long serialVersionUID = -1185974347409665484L;
        final v<? super T> downstream;
        final int index;
        final a<T> parent;
        boolean won;

        AmbInnerObserver(a<T> aVar, int i, v<? super T> vVar) {
            this.parent = aVar;
            this.index = i;
            this.downstream = vVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (this.won) {
                this.downstream.onNext(t);
            } else if (this.parent.a(this.index)) {
                this.won = true;
                this.downstream.onNext(t);
            } else {
                get().dispose();
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.won) {
                this.downstream.onError(th);
            } else if (this.parent.a(this.index)) {
                this.won = true;
                this.downstream.onError(th);
            } else {
                io.reactivex.e.a.a(th);
            }
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (this.won) {
                this.downstream.onComplete();
            } else if (this.parent.a(this.index)) {
                this.won = true;
                this.downstream.onComplete();
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
