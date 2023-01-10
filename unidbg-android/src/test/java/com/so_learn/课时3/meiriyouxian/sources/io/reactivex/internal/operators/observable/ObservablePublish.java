package io.reactivex.internal.operators.observable;

import io.reactivex.c.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservablePublish<T> extends io.reactivex.d.a<T> implements s<T> {
    final t<T> a;
    final AtomicReference<a<T>> b;
    final t<T> c;

    public static <T> io.reactivex.d.a<T> e(t<T> tVar) {
        AtomicReference atomicReference = new AtomicReference();
        return io.reactivex.e.a.a((io.reactivex.d.a) new ObservablePublish(new b(atomicReference), tVar, atomicReference));
    }

    private ObservablePublish(t<T> tVar, t<T> tVar2, AtomicReference<a<T>> atomicReference) {
        this.c = tVar;
        this.a = tVar2;
        this.b = atomicReference;
    }

    @Override // io.reactivex.internal.operators.observable.s
    public t<T> a() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.c.subscribe(vVar);
    }

    @Override // io.reactivex.d.a
    public void f(g<? super io.reactivex.disposables.b> gVar) {
        a<T> aVar;
        while (true) {
            aVar = this.b.get();
            if (aVar != null && !aVar.isDisposed()) {
                break;
            }
            a<T> aVar2 = new a<>(this.b);
            if (this.b.compareAndSet(aVar, aVar2)) {
                aVar = aVar2;
                break;
            }
        }
        boolean z = true;
        if (aVar.e.get() || !aVar.e.compareAndSet(false, true)) {
            z = false;
        }
        try {
            gVar.accept(aVar);
            if (z) {
                this.a.subscribe(aVar);
            }
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            throw ExceptionHelper.a(th);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class a<T> implements io.reactivex.disposables.b, v<T> {
        static final InnerDisposable[] b = new InnerDisposable[0];
        static final InnerDisposable[] c = new InnerDisposable[0];
        final AtomicReference<a<T>> a;
        final AtomicReference<InnerDisposable<T>[]> d = new AtomicReference<>(b);
        final AtomicBoolean e;
        final AtomicReference<io.reactivex.disposables.b> f = new AtomicReference<>();

        a(AtomicReference<a<T>> atomicReference) {
            this.a = atomicReference;
            this.e = new AtomicBoolean();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.d.getAndSet(c) != c) {
                this.a.compareAndSet(this, null);
                DisposableHelper.dispose(this.f);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.d.get() == c;
        }

        @Override // io.reactivex.v
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this.f, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            for (InnerDisposable<T> innerDisposable : this.d.get()) {
                innerDisposable.child.onNext(t);
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.a.compareAndSet(this, null);
            InnerDisposable<T>[] andSet = this.d.getAndSet(c);
            if (andSet.length != 0) {
                for (InnerDisposable<T> innerDisposable : andSet) {
                    innerDisposable.child.onError(th);
                }
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.a.compareAndSet(this, null);
            for (InnerDisposable<T> innerDisposable : this.d.getAndSet(c)) {
                innerDisposable.child.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(InnerDisposable<T> innerDisposable) {
            InnerDisposable<T>[] innerDisposableArr;
            InnerDisposable<T>[] innerDisposableArr2;
            do {
                innerDisposableArr = this.d.get();
                if (innerDisposableArr == c) {
                    return false;
                }
                int length = innerDisposableArr.length;
                innerDisposableArr2 = new InnerDisposable[(length + 1)];
                System.arraycopy(innerDisposableArr, 0, innerDisposableArr2, 0, length);
                innerDisposableArr2[length] = innerDisposable;
            } while (!this.d.compareAndSet(innerDisposableArr, innerDisposableArr2));
            return true;
        }

        /* access modifiers changed from: package-private */
        public void b(InnerDisposable<T> innerDisposable) {
            InnerDisposable<T>[] innerDisposableArr;
            InnerDisposable<T>[] innerDisposableArr2;
            do {
                innerDisposableArr = this.d.get();
                int length = innerDisposableArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (innerDisposableArr[i2].equals(innerDisposable)) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            innerDisposableArr2 = b;
                        } else {
                            InnerDisposable<T>[] innerDisposableArr3 = new InnerDisposable[(length - 1)];
                            System.arraycopy(innerDisposableArr, 0, innerDisposableArr3, 0, i);
                            System.arraycopy(innerDisposableArr, i + 1, innerDisposableArr3, i, (length - i) - 1);
                            innerDisposableArr2 = innerDisposableArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.d.compareAndSet(innerDisposableArr, innerDisposableArr2));
        }
    }

    /* access modifiers changed from: package-private */
    public static final class InnerDisposable<T> extends AtomicReference<Object> implements io.reactivex.disposables.b {
        private static final long serialVersionUID = -1100270633763673112L;
        final v<? super T> child;

        InnerDisposable(v<? super T> vVar) {
            this.child = vVar;
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == this;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            Object andSet = getAndSet(this);
            if (andSet != null && andSet != this) {
                ((a) andSet).b(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void setParent(a<T> aVar) {
            if (!compareAndSet(null, aVar)) {
                aVar.b(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class b<T> implements t<T> {
        private final AtomicReference<a<T>> a;

        b(AtomicReference<a<T>> atomicReference) {
            this.a = atomicReference;
        }

        @Override // io.reactivex.t
        public void subscribe(v<? super T> vVar) {
            InnerDisposable innerDisposable = new InnerDisposable(vVar);
            vVar.onSubscribe(innerDisposable);
            while (true) {
                a<T> aVar = this.a.get();
                if (aVar == null || aVar.isDisposed()) {
                    a<T> aVar2 = new a<>(this.a);
                    if (!this.a.compareAndSet(aVar, aVar2)) {
                        continue;
                    } else {
                        aVar = aVar2;
                    }
                }
                if (aVar.a(innerDisposable)) {
                    innerDisposable.setParent(aVar);
                    return;
                }
            }
        }
    }
}
