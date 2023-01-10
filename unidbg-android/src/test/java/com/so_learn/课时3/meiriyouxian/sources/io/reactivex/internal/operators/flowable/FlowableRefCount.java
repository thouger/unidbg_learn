package io.reactivex.internal.operators.flowable;

import io.reactivex.b.a;
import io.reactivex.disposables.b;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableRefCount<T> extends g<T> {
    final a<T> b;
    final int c;
    final long d;
    final TimeUnit e;
    final w f;
    RefConnection g;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        RefConnection refConnection;
        boolean z;
        synchronized (this) {
            refConnection = this.g;
            if (refConnection == null) {
                refConnection = new RefConnection(this);
                this.g = refConnection;
            }
            long j = refConnection.subscriberCount;
            if (j == 0 && refConnection.timer != null) {
                refConnection.timer.dispose();
            }
            long j2 = j + 1;
            refConnection.subscriberCount = j2;
            z = true;
            if (refConnection.connected || j2 != ((long) this.c)) {
                z = false;
            } else {
                refConnection.connected = true;
            }
        }
        this.b.a((j) new RefCountSubscriber(cVar, this, refConnection));
        if (z) {
            this.b.c(refConnection);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(RefConnection refConnection) {
        synchronized (this) {
            if (this.g != null) {
                if (this.g == refConnection) {
                    long j = refConnection.subscriberCount - 1;
                    refConnection.subscriberCount = j;
                    if (j == 0) {
                        if (refConnection.connected) {
                            if (this.d == 0) {
                                e(refConnection);
                                return;
                            }
                            SequentialDisposable sequentialDisposable = new SequentialDisposable();
                            refConnection.timer = sequentialDisposable;
                            sequentialDisposable.replace(this.f.a(refConnection, this.d, this.e));
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(RefConnection refConnection) {
        synchronized (this) {
            if (this.b instanceof g) {
                if (this.g != null && this.g == refConnection) {
                    this.g = null;
                    c(refConnection);
                }
                long j = refConnection.subscriberCount - 1;
                refConnection.subscriberCount = j;
                if (j == 0) {
                    d(refConnection);
                }
            } else if (this.g != null && this.g == refConnection) {
                c(refConnection);
                long j2 = refConnection.subscriberCount - 1;
                refConnection.subscriberCount = j2;
                if (j2 == 0) {
                    this.g = null;
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
        a<T> aVar = this.b;
        if (aVar instanceof b) {
            ((b) aVar).dispose();
        } else if (aVar instanceof io.reactivex.internal.disposables.c) {
            ((io.reactivex.internal.disposables.c) aVar).a(refConnection.get());
        }
    }

    /* access modifiers changed from: package-private */
    public void e(RefConnection refConnection) {
        synchronized (this) {
            if (refConnection.subscriberCount == 0 && refConnection == this.g) {
                this.g = null;
                b bVar = refConnection.get();
                DisposableHelper.dispose(refConnection);
                if (this.b instanceof b) {
                    ((b) this.b).dispose();
                } else if (this.b instanceof io.reactivex.internal.disposables.c) {
                    if (bVar == null) {
                        refConnection.disconnectedEarly = true;
                    } else {
                        ((io.reactivex.internal.disposables.c) this.b).a(bVar);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class RefConnection extends AtomicReference<b> implements io.reactivex.c.g<b>, Runnable {
        private static final long serialVersionUID = -4552101107598366241L;
        boolean connected;
        boolean disconnectedEarly;
        final FlowableRefCount<?> parent;
        long subscriberCount;
        b timer;

        RefConnection(FlowableRefCount<?> flowableRefCount) {
            this.parent = flowableRefCount;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.parent.e(this);
        }

        public void accept(b bVar) throws Exception {
            DisposableHelper.replace(this, bVar);
            synchronized (this.parent) {
                if (this.disconnectedEarly) {
                    ((io.reactivex.internal.disposables.c) this.parent.b).a(bVar);
                }
            }
        }
    }

    static final class RefCountSubscriber<T> extends AtomicBoolean implements j<T>, d {
        private static final long serialVersionUID = -7419642935409022375L;
        final RefConnection connection;
        final c<? super T> downstream;
        final FlowableRefCount<T> parent;
        d upstream;

        RefCountSubscriber(c<? super T> cVar, FlowableRefCount<T> flowableRefCount, RefConnection refConnection) {
            this.downstream = cVar;
            this.parent = flowableRefCount;
            this.connection = refConnection;
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.parent.b(this.connection);
                this.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.parent.b(this.connection);
                this.downstream.onComplete();
            }
        }

        public void request(long j) {
            this.upstream.request(j);
        }

        public void cancel() {
            this.upstream.cancel();
            if (compareAndSet(false, true)) {
                this.parent.a(this.connection);
            }
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
            }
        }
    }
}
