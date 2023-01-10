package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.operators.single.a;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleZipArray<T, R> extends x<R> {
    final ab<? extends T>[] a;
    final h<? super Object[], ? extends R> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super R> zVar) {
        ab<? extends T>[] abVarArr = this.a;
        int length = abVarArr.length;
        if (length == 1) {
            abVarArr[0].a(new a.C0210a(zVar, new a()));
            return;
        }
        ZipCoordinator zipCoordinator = new ZipCoordinator(zVar, length, this.b);
        zVar.onSubscribe(zipCoordinator);
        for (int i = 0; i < length && !zipCoordinator.isDisposed(); i++) {
            ab<? extends T> abVar = abVarArr[i];
            if (abVar == null) {
                zipCoordinator.innerError(new NullPointerException("One of the sources is null"), i);
                return;
            } else {
                abVar.a(zipCoordinator.observers[i]);
            }
        }
    }

    static final class ZipCoordinator<T, R> extends AtomicInteger implements b {
        private static final long serialVersionUID = -5556924161382950569L;
        final z<? super R> downstream;
        final ZipSingleObserver<T>[] observers;
        final Object[] values;
        final h<? super Object[], ? extends R> zipper;

        ZipCoordinator(z<? super R> zVar, int i, h<? super Object[], ? extends R> hVar) {
            super(i);
            this.downstream = zVar;
            this.zipper = hVar;
            ZipSingleObserver<T>[] zipSingleObserverArr = new ZipSingleObserver[i];
            for (int i2 = 0; i2 < i; i2++) {
                zipSingleObserverArr[i2] = new ZipSingleObserver<>(this, i2);
            }
            this.observers = zipSingleObserverArr;
            this.values = new Object[i];
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() <= 0;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (getAndSet(0) > 0) {
                for (ZipSingleObserver<T> zipSingleObserver : this.observers) {
                    zipSingleObserver.dispose();
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
            ZipSingleObserver<T>[] zipSingleObserverArr = this.observers;
            int length = zipSingleObserverArr.length;
            for (int i2 = 0; i2 < i; i2++) {
                zipSingleObserverArr[i2].dispose();
            }
            while (true) {
                i++;
                if (i < length) {
                    zipSingleObserverArr[i].dispose();
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
    }

    /* access modifiers changed from: package-private */
    public static final class ZipSingleObserver<T> extends AtomicReference<b> implements z<T> {
        private static final long serialVersionUID = 3323743579927613702L;
        final int index;
        final ZipCoordinator<T, ?> parent;

        ZipSingleObserver(ZipCoordinator<T, ?> zipCoordinator, int i) {
            this.parent = zipCoordinator;
            this.index = i;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            this.parent.innerSuccess(t, this.index);
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.parent.innerError(th, this.index);
        }
    }

    final class a implements h<T, R> {
        a() {
        }

        @Override // io.reactivex.c.h
        public R apply(T t) throws Exception {
            return (R) io.reactivex.internal.functions.a.a(SingleZipArray.this.b.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }
}
