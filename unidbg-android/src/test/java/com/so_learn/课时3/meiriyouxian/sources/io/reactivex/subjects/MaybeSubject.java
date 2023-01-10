package io.reactivex.subjects;

import io.reactivex.disposables.b;
import io.reactivex.internal.functions.a;
import io.reactivex.k;
import io.reactivex.m;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeSubject<T> extends k<T> implements m<T> {
    static final MaybeDisposable[] b = new MaybeDisposable[0];
    static final MaybeDisposable[] c = new MaybeDisposable[0];
    final AtomicReference<MaybeDisposable<T>[]> a = new AtomicReference<>(b);
    final AtomicBoolean d = new AtomicBoolean();
    T e;
    Throwable f;

    MaybeSubject() {
    }

    @Override // io.reactivex.m
    public void onSubscribe(b bVar) {
        if (this.a.get() == c) {
            bVar.dispose();
        }
    }

    @Override // io.reactivex.m
    public void onSuccess(T t) {
        a.a((Object) t, "onSuccess called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.d.compareAndSet(false, true)) {
            this.e = t;
            for (MaybeDisposable<T> maybeDisposable : this.a.getAndSet(c)) {
                maybeDisposable.downstream.onSuccess(t);
            }
        }
    }

    @Override // io.reactivex.m
    public void onError(Throwable th) {
        a.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.d.compareAndSet(false, true)) {
            this.f = th;
            for (MaybeDisposable<T> maybeDisposable : this.a.getAndSet(c)) {
                maybeDisposable.downstream.onError(th);
            }
            return;
        }
        io.reactivex.e.a.a(th);
    }

    @Override // io.reactivex.m
    public void onComplete() {
        if (this.d.compareAndSet(false, true)) {
            for (MaybeDisposable<T> maybeDisposable : this.a.getAndSet(c)) {
                maybeDisposable.downstream.onComplete();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        MaybeDisposable maybeDisposable = new MaybeDisposable(mVar, this);
        mVar.onSubscribe(maybeDisposable);
        if (!a(maybeDisposable)) {
            Throwable th = this.f;
            if (th != null) {
                mVar.onError(th);
                return;
            }
            T t = this.e;
            if (t == null) {
                mVar.onComplete();
            } else {
                mVar.onSuccess(t);
            }
        } else if (maybeDisposable.isDisposed()) {
            b(maybeDisposable);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(MaybeDisposable<T> maybeDisposable) {
        MaybeDisposable<T>[] maybeDisposableArr;
        MaybeDisposable<T>[] maybeDisposableArr2;
        do {
            maybeDisposableArr = this.a.get();
            if (maybeDisposableArr == c) {
                return false;
            }
            int length = maybeDisposableArr.length;
            maybeDisposableArr2 = new MaybeDisposable[(length + 1)];
            System.arraycopy(maybeDisposableArr, 0, maybeDisposableArr2, 0, length);
            maybeDisposableArr2[length] = maybeDisposable;
        } while (!this.a.compareAndSet(maybeDisposableArr, maybeDisposableArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(MaybeDisposable<T> maybeDisposable) {
        MaybeDisposable<T>[] maybeDisposableArr;
        MaybeDisposable<T>[] maybeDisposableArr2;
        do {
            maybeDisposableArr = this.a.get();
            int length = maybeDisposableArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (maybeDisposableArr[i2] == maybeDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        maybeDisposableArr2 = b;
                    } else {
                        MaybeDisposable<T>[] maybeDisposableArr3 = new MaybeDisposable[(length - 1)];
                        System.arraycopy(maybeDisposableArr, 0, maybeDisposableArr3, 0, i);
                        System.arraycopy(maybeDisposableArr, i + 1, maybeDisposableArr3, i, (length - i) - 1);
                        maybeDisposableArr2 = maybeDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.a.compareAndSet(maybeDisposableArr, maybeDisposableArr2));
    }

    /* access modifiers changed from: package-private */
    public static final class MaybeDisposable<T> extends AtomicReference<MaybeSubject<T>> implements b {
        private static final long serialVersionUID = -7650903191002190468L;
        final m<? super T> downstream;

        MaybeDisposable(m<? super T> mVar, MaybeSubject<T> maybeSubject) {
            this.downstream = mVar;
            lazySet(maybeSubject);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            MaybeSubject<T> andSet = getAndSet(null);
            if (andSet != null) {
                andSet.b((MaybeDisposable) this);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == null;
        }
    }
}
