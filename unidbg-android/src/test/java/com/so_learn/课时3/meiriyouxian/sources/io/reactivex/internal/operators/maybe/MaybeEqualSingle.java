package io.reactivex.internal.operators.maybe;

import io.reactivex.c.d;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeEqualSingle<T> extends x<Boolean> {
    final o<? extends T> a;
    final o<? extends T> b;
    final d<? super T, ? super T> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super Boolean> zVar) {
        EqualCoordinator equalCoordinator = new EqualCoordinator(zVar, this.c);
        zVar.onSubscribe(equalCoordinator);
        equalCoordinator.subscribe(this.a, this.b);
    }

    static final class EqualCoordinator<T> extends AtomicInteger implements b {
        final z<? super Boolean> downstream;
        final d<? super T, ? super T> isEqual;
        final EqualObserver<T> observer1 = new EqualObserver<>(this);
        final EqualObserver<T> observer2 = new EqualObserver<>(this);

        EqualCoordinator(z<? super Boolean> zVar, d<? super T, ? super T> dVar) {
            super(2);
            this.downstream = zVar;
            this.isEqual = dVar;
        }

        /* access modifiers changed from: package-private */
        public void subscribe(o<? extends T> oVar, o<? extends T> oVar2) {
            oVar.a(this.observer1);
            oVar2.a(this.observer2);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.observer1.dispose();
            this.observer2.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.observer1.get());
        }

        /* access modifiers changed from: package-private */
        public void done() {
            if (decrementAndGet() == 0) {
                Object obj = this.observer1.value;
                Object obj2 = this.observer2.value;
                if (obj == null || obj2 == null) {
                    this.downstream.onSuccess(Boolean.valueOf(obj == null && obj2 == null));
                    return;
                }
                try {
                    this.downstream.onSuccess(Boolean.valueOf(this.isEqual.a(obj, obj2)));
                } catch (Throwable th) {
                    a.b(th);
                    this.downstream.onError(th);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void error(EqualObserver<T> equalObserver, Throwable th) {
            if (getAndSet(0) > 0) {
                EqualObserver<T> equalObserver2 = this.observer1;
                if (equalObserver == equalObserver2) {
                    this.observer2.dispose();
                } else {
                    equalObserver2.dispose();
                }
                this.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class EqualObserver<T> extends AtomicReference<b> implements m<T> {
        private static final long serialVersionUID = -3031974433025990931L;
        final EqualCoordinator<T> parent;
        Object value;

        EqualObserver(EqualCoordinator<T> equalCoordinator) {
            this.parent = equalCoordinator;
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
            this.value = t;
            this.parent.done();
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.parent.error(this, th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.parent.done();
        }
    }
}
