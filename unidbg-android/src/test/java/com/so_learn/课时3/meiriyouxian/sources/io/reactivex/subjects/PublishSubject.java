package io.reactivex.subjects;

import io.reactivex.disposables.b;
import io.reactivex.internal.functions.a;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class PublishSubject<T> extends b<T> {
    static final PublishDisposable[] a = new PublishDisposable[0];
    static final PublishDisposable[] b = new PublishDisposable[0];
    final AtomicReference<PublishDisposable<T>[]> c = new AtomicReference<>(b);
    Throwable d;

    public static <T> PublishSubject<T> a() {
        return new PublishSubject<>();
    }

    PublishSubject() {
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        PublishDisposable publishDisposable = new PublishDisposable(vVar, this);
        vVar.onSubscribe(publishDisposable);
        if (!a(publishDisposable)) {
            Throwable th = this.d;
            if (th != null) {
                vVar.onError(th);
            } else {
                vVar.onComplete();
            }
        } else if (publishDisposable.isDisposed()) {
            b(publishDisposable);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(PublishDisposable<T> publishDisposable) {
        PublishDisposable<T>[] publishDisposableArr;
        PublishDisposable<T>[] publishDisposableArr2;
        do {
            publishDisposableArr = this.c.get();
            if (publishDisposableArr == a) {
                return false;
            }
            int length = publishDisposableArr.length;
            publishDisposableArr2 = new PublishDisposable[(length + 1)];
            System.arraycopy(publishDisposableArr, 0, publishDisposableArr2, 0, length);
            publishDisposableArr2[length] = publishDisposable;
        } while (!this.c.compareAndSet(publishDisposableArr, publishDisposableArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(PublishDisposable<T> publishDisposable) {
        PublishDisposable<T>[] publishDisposableArr;
        PublishDisposable<T>[] publishDisposableArr2;
        do {
            publishDisposableArr = this.c.get();
            if (publishDisposableArr != a && publishDisposableArr != b) {
                int length = publishDisposableArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (publishDisposableArr[i2] == publishDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        publishDisposableArr2 = b;
                    } else {
                        PublishDisposable<T>[] publishDisposableArr3 = new PublishDisposable[(length - 1)];
                        System.arraycopy(publishDisposableArr, 0, publishDisposableArr3, 0, i);
                        System.arraycopy(publishDisposableArr, i + 1, publishDisposableArr3, i, (length - i) - 1);
                        publishDisposableArr2 = publishDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.c.compareAndSet(publishDisposableArr, publishDisposableArr2));
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        if (this.c.get() == a) {
            bVar.dispose();
        }
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        a.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        for (PublishDisposable<T> publishDisposable : this.c.get()) {
            publishDisposable.onNext(t);
        }
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        a.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        PublishDisposable<T>[] publishDisposableArr = this.c.get();
        PublishDisposable<T>[] publishDisposableArr2 = a;
        if (publishDisposableArr == publishDisposableArr2) {
            io.reactivex.e.a.a(th);
            return;
        }
        this.d = th;
        for (PublishDisposable<T> publishDisposable : this.c.getAndSet(publishDisposableArr2)) {
            publishDisposable.onError(th);
        }
    }

    @Override // io.reactivex.v
    public void onComplete() {
        PublishDisposable<T>[] publishDisposableArr = this.c.get();
        PublishDisposable<T>[] publishDisposableArr2 = a;
        if (publishDisposableArr != publishDisposableArr2) {
            for (PublishDisposable<T> publishDisposable : this.c.getAndSet(publishDisposableArr2)) {
                publishDisposable.onComplete();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class PublishDisposable<T> extends AtomicBoolean implements b {
        private static final long serialVersionUID = 3562861878281475070L;
        final v<? super T> downstream;
        final PublishSubject<T> parent;

        PublishDisposable(v<? super T> vVar, PublishSubject<T> publishSubject) {
            this.downstream = vVar;
            this.parent = publishSubject;
        }

        public void onNext(T t) {
            if (!get()) {
                this.downstream.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (get()) {
                io.reactivex.e.a.a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        public void onComplete() {
            if (!get()) {
                this.downstream.onComplete();
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.parent.b((PublishDisposable) this);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get();
        }
    }
}
