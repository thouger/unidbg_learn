package io.reactivex.internal.operators.observable;

import io.reactivex.c.g;
import io.reactivex.d.a;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.c;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservablePublishAlt<T> extends a<T> implements c {
    final t<T> a;
    final AtomicReference<PublishConnection<T>> b = new AtomicReference<>();

    public ObservablePublishAlt(t<T> tVar) {
        this.a = tVar;
    }

    @Override // io.reactivex.d.a
    public void f(g<? super b> gVar) {
        PublishConnection<T> publishConnection;
        while (true) {
            publishConnection = this.b.get();
            if (publishConnection != null && !publishConnection.isDisposed()) {
                break;
            }
            PublishConnection<T> publishConnection2 = new PublishConnection<>(this.b);
            if (this.b.compareAndSet(publishConnection, publishConnection2)) {
                publishConnection = publishConnection2;
                break;
            }
        }
        boolean z = true;
        if (publishConnection.connect.get() || !publishConnection.connect.compareAndSet(false, true)) {
            z = false;
        }
        try {
            gVar.accept(publishConnection);
            if (z) {
                this.a.subscribe(publishConnection);
            }
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            throw ExceptionHelper.a(th);
        }
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        PublishConnection<T> publishConnection;
        while (true) {
            publishConnection = this.b.get();
            if (publishConnection != null) {
                break;
            }
            PublishConnection<T> publishConnection2 = new PublishConnection<>(this.b);
            if (this.b.compareAndSet(publishConnection, publishConnection2)) {
                publishConnection = publishConnection2;
                break;
            }
        }
        InnerDisposable innerDisposable = new InnerDisposable(vVar, publishConnection);
        vVar.onSubscribe(innerDisposable);
        if (!publishConnection.add(innerDisposable)) {
            Throwable th = publishConnection.error;
            if (th != null) {
                vVar.onError(th);
            } else {
                vVar.onComplete();
            }
        } else if (innerDisposable.isDisposed()) {
            publishConnection.remove(innerDisposable);
        }
    }

    @Override // io.reactivex.internal.disposables.c
    public void a(b bVar) {
        this.b.compareAndSet((PublishConnection) bVar, null);
    }

    static final class PublishConnection<T> extends AtomicReference<InnerDisposable<T>[]> implements b, v<T> {
        static final InnerDisposable[] EMPTY = new InnerDisposable[0];
        static final InnerDisposable[] TERMINATED = new InnerDisposable[0];
        private static final long serialVersionUID = -3251430252873581268L;
        final AtomicBoolean connect = new AtomicBoolean();
        final AtomicReference<PublishConnection<T>> current;
        Throwable error;
        final AtomicReference<b> upstream;

        PublishConnection(AtomicReference<PublishConnection<T>> atomicReference) {
            this.current = atomicReference;
            this.upstream = new AtomicReference<>();
            lazySet(EMPTY);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            getAndSet(TERMINATED);
            this.current.compareAndSet(this, null);
            DisposableHelper.dispose(this.upstream);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == TERMINATED;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            for (InnerDisposable<T> innerDisposable : get()) {
                innerDisposable.downstream.onNext(t);
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.error = th;
            this.upstream.lazySet(DisposableHelper.DISPOSED);
            for (InnerDisposable<T> innerDisposable : getAndSet(TERMINATED)) {
                innerDisposable.downstream.onError(th);
            }
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.upstream.lazySet(DisposableHelper.DISPOSED);
            for (InnerDisposable<T> innerDisposable : getAndSet(TERMINATED)) {
                innerDisposable.downstream.onComplete();
            }
        }

        public boolean add(InnerDisposable<T> innerDisposable) {
            InnerDisposable<T>[] innerDisposableArr;
            InnerDisposable[] innerDisposableArr2;
            do {
                innerDisposableArr = get();
                if (innerDisposableArr == TERMINATED) {
                    return false;
                }
                int length = innerDisposableArr.length;
                innerDisposableArr2 = new InnerDisposable[(length + 1)];
                System.arraycopy(innerDisposableArr, 0, innerDisposableArr2, 0, length);
                innerDisposableArr2[length] = innerDisposable;
            } while (!compareAndSet(innerDisposableArr, innerDisposableArr2));
            return true;
        }

        public void remove(InnerDisposable<T> innerDisposable) {
            InnerDisposable<T>[] innerDisposableArr;
            InnerDisposable[] innerDisposableArr2;
            do {
                innerDisposableArr = get();
                int length = innerDisposableArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (innerDisposableArr[i2] == innerDisposable) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        innerDisposableArr2 = EMPTY;
                        if (length != 1) {
                            innerDisposableArr2 = new InnerDisposable[(length - 1)];
                            System.arraycopy(innerDisposableArr, 0, innerDisposableArr2, 0, i);
                            System.arraycopy(innerDisposableArr, i + 1, innerDisposableArr2, i, (length - i) - 1);
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(innerDisposableArr, innerDisposableArr2));
        }
    }

    /* access modifiers changed from: package-private */
    public static final class InnerDisposable<T> extends AtomicReference<PublishConnection<T>> implements b {
        private static final long serialVersionUID = 7463222674719692880L;
        final v<? super T> downstream;

        InnerDisposable(v<? super T> vVar, PublishConnection<T> publishConnection) {
            this.downstream = vVar;
            lazySet(publishConnection);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            PublishConnection<T> andSet = getAndSet(null);
            if (andSet != null) {
                andSet.remove(this);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == null;
        }
    }
}
