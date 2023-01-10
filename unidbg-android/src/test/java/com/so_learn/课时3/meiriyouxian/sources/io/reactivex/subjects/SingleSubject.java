package io.reactivex.subjects;

import io.reactivex.disposables.b;
import io.reactivex.internal.functions.a;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleSubject<T> extends x<T> implements z<T> {
    static final SingleDisposable[] b = new SingleDisposable[0];
    static final SingleDisposable[] c = new SingleDisposable[0];
    final AtomicReference<SingleDisposable<T>[]> a = new AtomicReference<>(b);
    final AtomicBoolean d = new AtomicBoolean();
    T e;
    Throwable f;

    SingleSubject() {
    }

    @Override // io.reactivex.z
    public void onSubscribe(b bVar) {
        if (this.a.get() == c) {
            bVar.dispose();
        }
    }

    @Override // io.reactivex.z
    public void onSuccess(T t) {
        a.a((Object) t, "onSuccess called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.d.compareAndSet(false, true)) {
            this.e = t;
            for (SingleDisposable<T> singleDisposable : this.a.getAndSet(c)) {
                singleDisposable.downstream.onSuccess(t);
            }
        }
    }

    @Override // io.reactivex.z
    public void onError(Throwable th) {
        a.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.d.compareAndSet(false, true)) {
            this.f = th;
            for (SingleDisposable<T> singleDisposable : this.a.getAndSet(c)) {
                singleDisposable.downstream.onError(th);
            }
            return;
        }
        io.reactivex.e.a.a(th);
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        SingleDisposable singleDisposable = new SingleDisposable(zVar, this);
        zVar.onSubscribe(singleDisposable);
        if (!a(singleDisposable)) {
            Throwable th = this.f;
            if (th != null) {
                zVar.onError(th);
            } else {
                zVar.onSuccess(this.e);
            }
        } else if (singleDisposable.isDisposed()) {
            b(singleDisposable);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(SingleDisposable<T> singleDisposable) {
        SingleDisposable<T>[] singleDisposableArr;
        SingleDisposable<T>[] singleDisposableArr2;
        do {
            singleDisposableArr = this.a.get();
            if (singleDisposableArr == c) {
                return false;
            }
            int length = singleDisposableArr.length;
            singleDisposableArr2 = new SingleDisposable[(length + 1)];
            System.arraycopy(singleDisposableArr, 0, singleDisposableArr2, 0, length);
            singleDisposableArr2[length] = singleDisposable;
        } while (!this.a.compareAndSet(singleDisposableArr, singleDisposableArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(SingleDisposable<T> singleDisposable) {
        SingleDisposable<T>[] singleDisposableArr;
        SingleDisposable<T>[] singleDisposableArr2;
        do {
            singleDisposableArr = this.a.get();
            int length = singleDisposableArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (singleDisposableArr[i2] == singleDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        singleDisposableArr2 = b;
                    } else {
                        SingleDisposable<T>[] singleDisposableArr3 = new SingleDisposable[(length - 1)];
                        System.arraycopy(singleDisposableArr, 0, singleDisposableArr3, 0, i);
                        System.arraycopy(singleDisposableArr, i + 1, singleDisposableArr3, i, (length - i) - 1);
                        singleDisposableArr2 = singleDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.a.compareAndSet(singleDisposableArr, singleDisposableArr2));
    }

    /* access modifiers changed from: package-private */
    public static final class SingleDisposable<T> extends AtomicReference<SingleSubject<T>> implements b {
        private static final long serialVersionUID = -7650903191002190468L;
        final z<? super T> downstream;

        SingleDisposable(z<? super T> zVar, SingleSubject<T> singleSubject) {
            this.downstream = zVar;
            lazySet(singleSubject);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            SingleSubject<T> andSet = getAndSet(null);
            if (andSet != null) {
                andSet.b(this);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == null;
        }
    }
}
