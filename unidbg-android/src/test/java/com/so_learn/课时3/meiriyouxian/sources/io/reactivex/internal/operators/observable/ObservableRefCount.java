package io.reactivex.internal.operators.observable;

import io.reactivex.c.g;
import io.reactivex.d.a;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.disposables.c;
import io.reactivex.q;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableRefCount<T> extends q<T> {
    final a<T> a;
    final int b;
    final long c;
    final TimeUnit d;
    final w e;
    RefConnection f;

    public ObservableRefCount(a<T> aVar) {
        this(aVar, 1, 0, TimeUnit.NANOSECONDS, null);
    }

    public ObservableRefCount(a<T> aVar, int i, long j, TimeUnit timeUnit, w wVar) {
        this.a = aVar;
        this.b = i;
        this.c = j;
        this.d = timeUnit;
        this.e = wVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        RefConnection refConnection;
        boolean z;
        synchronized (this) {
            refConnection = this.f;
            if (refConnection == null) {
                refConnection = new RefConnection(this);
                this.f = refConnection;
            }
            long j = refConnection.subscriberCount;
            if (j == 0 && refConnection.timer != null) {
                refConnection.timer.dispose();
            }
            long j2 = j + 1;
            refConnection.subscriberCount = j2;
            z = true;
            if (refConnection.connected || j2 != ((long) this.b)) {
                z = false;
            } else {
                refConnection.connected = true;
            }
        }
        this.a.subscribe(new RefCountObserver(vVar, this, refConnection));
        if (z) {
            this.a.f(refConnection);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(RefConnection refConnection) {
        synchronized (this) {
            if (this.f != null) {
                if (this.f == refConnection) {
                    long j = refConnection.subscriberCount - 1;
                    refConnection.subscriberCount = j;
                    if (j == 0) {
                        if (refConnection.connected) {
                            if (this.c == 0) {
                                e(refConnection);
                                return;
                            }
                            SequentialDisposable sequentialDisposable = new SequentialDisposable();
                            refConnection.timer = sequentialDisposable;
                            sequentialDisposable.replace(this.e.a(refConnection, this.c, this.d));
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(RefConnection refConnection) {
        synchronized (this) {
            if (this.a instanceof s) {
                if (this.f != null && this.f == refConnection) {
                    this.f = null;
                    c(refConnection);
                }
                long j = refConnection.subscriberCount - 1;
                refConnection.subscriberCount = j;
                if (j == 0) {
                    d(refConnection);
                }
            } else if (this.f != null && this.f == refConnection) {
                c(refConnection);
                long j2 = refConnection.subscriberCount - 1;
                refConnection.subscriberCount = j2;
                if (j2 == 0) {
                    this.f = null;
                    d(refConnection);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(RefConnection refConnection) {
        if (refConnection.timer != null) {
            refConnection.timer.dispose();
            refConnection.timer = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void d(RefConnection refConnection) {
        a<T> aVar = this.a;
        if (aVar instanceof b) {
            ((b) aVar).dispose();
        } else if (aVar instanceof c) {
            ((c) aVar).a(refConnection.get());
        }
    }

    /* access modifiers changed from: package-private */
    public void e(RefConnection refConnection) {
        synchronized (this) {
            if (refConnection.subscriberCount == 0 && refConnection == this.f) {
                this.f = null;
                b bVar = refConnection.get();
                DisposableHelper.dispose(refConnection);
                if (this.a instanceof b) {
                    ((b) this.a).dispose();
                } else if (this.a instanceof c) {
                    if (bVar == null) {
                        refConnection.disconnectedEarly = true;
                    } else {
                        ((c) this.a).a(bVar);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class RefConnection extends AtomicReference<b> implements g<b>, Runnable {
        private static final long serialVersionUID = -4552101107598366241L;
        boolean connected;
        boolean disconnectedEarly;
        final ObservableRefCount<?> parent;
        long subscriberCount;
        b timer;

        RefConnection(ObservableRefCount<?> observableRefCount) {
            this.parent = observableRefCount;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.parent.e(this);
        }

        public void accept(b bVar) throws Exception {
            DisposableHelper.replace(this, bVar);
            synchronized (this.parent) {
                if (this.disconnectedEarly) {
                    ((c) this.parent.a).a(bVar);
                }
            }
        }
    }

    static final class RefCountObserver<T> extends AtomicBoolean implements b, v<T> {
        private static final long serialVersionUID = -7419642935409022375L;
        final RefConnection connection;
        final v<? super T> downstream;
        final ObservableRefCount<T> parent;
        b upstream;

        RefCountObserver(v<? super T> vVar, ObservableRefCount<T> observableRefCount, RefConnection refConnection) {
            this.downstream = vVar;
            this.parent = observableRefCount;
            this.connection = refConnection;
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.parent.b(this.connection);
                this.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.parent.b(this.connection);
                this.downstream.onComplete();
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.upstream.dispose();
            if (compareAndSet(false, true)) {
                this.parent.a(this.connection);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }
    }
}
