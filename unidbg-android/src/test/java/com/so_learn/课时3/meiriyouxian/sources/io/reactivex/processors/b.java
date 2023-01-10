package io.reactivex.processors;

import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.a;
import org.a.c;
import org.a.d;

/* compiled from: SerializedProcessor */
final class b<T> extends a<T> {
    final a<T> b;
    boolean c;
    a<Object> d;
    volatile boolean e;

    b(a<T> aVar) {
        this.b = aVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.subscribe(cVar);
    }

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        boolean z = true;
        if (!this.e) {
            synchronized (this) {
                if (!this.e) {
                    if (this.c) {
                        a<Object> aVar = this.d;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.d = aVar;
                        }
                        aVar.a((a<Object>) NotificationLite.subscription(dVar));
                        return;
                    }
                    this.c = true;
                    z = false;
                }
            }
        }
        if (z) {
            dVar.cancel();
            return;
        }
        this.b.onSubscribe(dVar);
        h();
    }

    public void onNext(T t) {
        if (!this.e) {
            synchronized (this) {
                if (!this.e) {
                    if (this.c) {
                        a<Object> aVar = this.d;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.d = aVar;
                        }
                        aVar.a((a<Object>) NotificationLite.next(t));
                        return;
                    }
                    this.c = true;
                    this.b.onNext(t);
                    h();
                }
            }
        }
    }

    public void onError(Throwable th) {
        boolean z;
        if (this.e) {
            io.reactivex.e.a.a(th);
            return;
        }
        synchronized (this) {
            if (this.e) {
                z = true;
            } else {
                this.e = true;
                if (this.c) {
                    a<Object> aVar = this.d;
                    if (aVar == null) {
                        aVar = new a<>(4);
                        this.d = aVar;
                    }
                    aVar.b(NotificationLite.error(th));
                    return;
                }
                z = false;
                this.c = true;
            }
        }
        if (z) {
            io.reactivex.e.a.a(th);
        } else {
            this.b.onError(th);
        }
    }

    public void onComplete() {
        if (!this.e) {
            synchronized (this) {
                if (!this.e) {
                    this.e = true;
                    if (this.c) {
                        a<Object> aVar = this.d;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.d = aVar;
                        }
                        aVar.a((a<Object>) NotificationLite.complete());
                        return;
                    }
                    this.c = true;
                    this.b.onComplete();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void h() {
        a<Object> aVar;
        while (true) {
            synchronized (this) {
                aVar = this.d;
                if (aVar == null) {
                    this.c = false;
                    return;
                }
                this.d = null;
            }
            aVar.a((c) this.b);
        }
    }
}
