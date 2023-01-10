package io.reactivex.subjects;

import io.reactivex.disposables.b;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicReference;

public final class AsyncSubject<T> extends b<T> {
    static final AsyncDisposable[] a = new AsyncDisposable[0];
    static final AsyncDisposable[] b = new AsyncDisposable[0];
    final AtomicReference<AsyncDisposable<T>[]> c;
    Throwable d;
    T e;

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        if (this.c.get() == b) {
            bVar.dispose();
        }
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        a.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.c.get() != b) {
            this.e = t;
        }
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        a.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        AsyncDisposable<T>[] asyncDisposableArr = this.c.get();
        AsyncDisposable<T>[] asyncDisposableArr2 = b;
        if (asyncDisposableArr == asyncDisposableArr2) {
            io.reactivex.e.a.a(th);
            return;
        }
        this.e = null;
        this.d = th;
        for (AsyncDisposable<T> asyncDisposable : this.c.getAndSet(asyncDisposableArr2)) {
            asyncDisposable.onError(th);
        }
    }

    @Override // io.reactivex.v
    public void onComplete() {
        AsyncDisposable<T>[] asyncDisposableArr = this.c.get();
        AsyncDisposable<T>[] asyncDisposableArr2 = b;
        if (asyncDisposableArr != asyncDisposableArr2) {
            T t = this.e;
            AsyncDisposable<T>[] andSet = this.c.getAndSet(asyncDisposableArr2);
            int i = 0;
            if (t == null) {
                int length = andSet.length;
                while (i < length) {
                    andSet[i].onComplete();
                    i++;
                }
                return;
            }
            int length2 = andSet.length;
            while (i < length2) {
                andSet[i].complete(t);
                i++;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        AsyncDisposable asyncDisposable = new AsyncDisposable(vVar, this);
        vVar.onSubscribe(asyncDisposable);
        if (!a(asyncDisposable)) {
            Throwable th = this.d;
            if (th != null) {
                vVar.onError(th);
                return;
            }
            T t = this.e;
            if (t != null) {
                asyncDisposable.complete(t);
            } else {
                asyncDisposable.onComplete();
            }
        } else if (asyncDisposable.isDisposed()) {
            b(asyncDisposable);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(AsyncDisposable<T> asyncDisposable) {
        AsyncDisposable<T>[] asyncDisposableArr;
        AsyncDisposable<T>[] asyncDisposableArr2;
        do {
            asyncDisposableArr = this.c.get();
            if (asyncDisposableArr == b) {
                return false;
            }
            int length = asyncDisposableArr.length;
            asyncDisposableArr2 = new AsyncDisposable[(length + 1)];
            System.arraycopy(asyncDisposableArr, 0, asyncDisposableArr2, 0, length);
            asyncDisposableArr2[length] = asyncDisposable;
        } while (!this.c.compareAndSet(asyncDisposableArr, asyncDisposableArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(AsyncDisposable<T> asyncDisposable) {
        AsyncDisposable<T>[] asyncDisposableArr;
        AsyncDisposable<T>[] asyncDisposableArr2;
        do {
            asyncDisposableArr = this.c.get();
            int length = asyncDisposableArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (asyncDisposableArr[i2] == asyncDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        asyncDisposableArr2 = a;
                    } else {
                        AsyncDisposable<T>[] asyncDisposableArr3 = new AsyncDisposable[(length - 1)];
                        System.arraycopy(asyncDisposableArr, 0, asyncDisposableArr3, 0, i);
                        System.arraycopy(asyncDisposableArr, i + 1, asyncDisposableArr3, i, (length - i) - 1);
                        asyncDisposableArr2 = asyncDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.c.compareAndSet(asyncDisposableArr, asyncDisposableArr2));
    }

    /* access modifiers changed from: package-private */
    public static final class AsyncDisposable<T> extends DeferredScalarDisposable<T> {
        private static final long serialVersionUID = 5629876084736248016L;
        final AsyncSubject<T> parent;

        AsyncDisposable(v<? super T> vVar, AsyncSubject<T> asyncSubject) {
            super(vVar);
            this.parent = asyncSubject;
        }

        @Override // io.reactivex.internal.observers.DeferredScalarDisposable, io.reactivex.disposables.b
        public void dispose() {
            if (super.tryDispose()) {
                this.parent.b((AsyncDisposable) this);
            }
        }

        /* access modifiers changed from: package-private */
        public void onComplete() {
            if (!isDisposed()) {
                this.downstream.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        public void onError(Throwable th) {
            if (isDisposed()) {
                io.reactivex.e.a.a(th);
            } else {
                this.downstream.onError(th);
            }
        }
    }
}
