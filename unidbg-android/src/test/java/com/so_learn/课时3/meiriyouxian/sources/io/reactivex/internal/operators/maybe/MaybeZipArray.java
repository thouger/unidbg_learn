package io.reactivex.internal.operators.maybe;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.operators.maybe.c;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeZipArray<T, R> extends k<R> {
    final o<? extends T>[] a;
    final h<? super Object[], ? extends R> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super R> mVar) {
        o<? extends T>[] oVarArr = this.a;
        int length = oVarArr.length;
        if (length == 1) {
            oVarArr[0].a(new c.a(mVar, new a()));
            return;
        }
        ZipCoordinator zipCoordinator = new ZipCoordinator(mVar, length, this.b);
        mVar.onSubscribe(zipCoordinator);
        for (int i = 0; i < length && !zipCoordinator.isDisposed(); i++) {
            o<? extends T> oVar = oVarArr[i];
            if (oVar == null) {
                zipCoordinator.innerError(new NullPointerException("One of the sources is null"), i);
                return;
            } else {
                oVar.a(zipCoordinator.observers[i]);
            }
        }
    }

    static final class ZipCoordinator<T, R> extends AtomicInteger implements b {
        private static final long serialVersionUID = -5556924161382950569L;
        final m<? super R> downstream;
        final ZipMaybeObserver<T>[] observers;
        final Object[] values;
        final h<? super Object[], ? extends R> zipper;

        ZipCoordinator(m<? super R> mVar, int i, h<? super Object[], ? extends R> hVar) {
            super(i);
            this.downstream = mVar;
            this.zipper = hVar;
            ZipMaybeObserver<T>[] zipMaybeObserverArr = new ZipMaybeObserver[i];
            for (int i2 = 0; i2 < i; i2++) {
                zipMaybeObserverArr[i2] = new ZipMaybeObserver<>(this, i2);
            }
            this.observers = zipMaybeObserverArr;
            this.values = new Object[i];
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() <= 0;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (getAndSet(0) > 0) {
                for (ZipMaybeObserver<T> zipMaybeObserver : this.observers) {
                    zipMaybeObserver.dispose();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void innerSuccess(T t, int i) {
            this.values[i] = t;
            if (decrementAndGet() == 0) {
                try {
                    this.downstream.onSuccess(io.reactivex.internal.functions.a.a(this.zipper.apply(this.values), "The zipper returned a null value"));
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.downstream.onError(th);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void disposeExcept(int i) {
            ZipMaybeObserver<T>[] zipMaybeObserverArr = this.observers;
            int length = zipMaybeObserverArr.length;
            for (int i2 = 0; i2 < i; i2++) {
                zipMaybeObserverArr[i2].dispose();
            }
            while (true) {
                i++;
                if (i < length) {
                    zipMaybeObserverArr[i].dispose();
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable th, int i) {
            if (getAndSet(0) > 0) {
                disposeExcept(i);
                this.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        /* access modifiers changed from: package-private */
        public void innerComplete(int i) {
            if (getAndSet(0) > 0) {
                disposeExcept(i);
                this.downstream.onComplete();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ZipMaybeObserver<T> extends AtomicReference<b> implements m<T> {
        private static final long serialVersionUID = 3323743579927613702L;
        final int index;
        final ZipCoordinator<T, ?> parent;

        ZipMaybeObserver(ZipCoordinator<T, ?> zipCoordinator, int i) {
            this.parent = zipCoordinator;
            this.index = i;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            this.parent.innerSuccess(t, this.index);
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.parent.innerError(th, this.index);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.parent.innerComplete(this.index);
        }
    }

    final class a implements h<T, R> {
        a() {
        }

        @Override // io.reactivex.c.h
        public R apply(T t) throws Exception {
            return (R) io.reactivex.internal.functions.a.a(MaybeZipArray.this.b.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }
}
