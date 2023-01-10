package io.reactivex.subjects;

import io.reactivex.disposables.b;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.a;
import io.reactivex.v;

/* compiled from: SerializedSubject */
final class a<T> extends b<T> implements a.AbstractC0213a<Object> {
    final b<T> a;
    boolean b;
    io.reactivex.internal.util.a<Object> c;
    volatile boolean d;

    a(b<T> bVar) {
        this.a = bVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(vVar);
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        boolean z = true;
        if (!this.d) {
            synchronized (this) {
                if (!this.d) {
                    if (this.b) {
                        io.reactivex.internal.util.a<Object> aVar = this.c;
                        if (aVar == null) {
                            aVar = new io.reactivex.internal.util.a<>(4);
                            this.c = aVar;
                        }
                        aVar.a((io.reactivex.internal.util.a<Object>) NotificationLite.disposable(bVar));
                        return;
                    }
                    this.b = true;
                    z = false;
                }
            }
        }
        if (z) {
            bVar.dispose();
            return;
        }
        this.a.onSubscribe(bVar);
        a();
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        if (!this.d) {
            synchronized (this) {
                if (!this.d) {
                    if (this.b) {
                        io.reactivex.internal.util.a<Object> aVar = this.c;
                        if (aVar == null) {
                            aVar = new io.reactivex.internal.util.a<>(4);
                            this.c = aVar;
                        }
                        aVar.a((io.reactivex.internal.util.a<Object>) NotificationLite.next(t));
                        return;
                    }
                    this.b = true;
                    this.a.onNext(t);
                    a();
                }
            }
        }
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        boolean z;
        if (this.d) {
            io.reactivex.e.a.a(th);
            return;
        }
        synchronized (this) {
            if (this.d) {
                z = true;
            } else {
                this.d = true;
                if (this.b) {
                    io.reactivex.internal.util.a<Object> aVar = this.c;
                    if (aVar == null) {
                        aVar = new io.reactivex.internal.util.a<>(4);
                        this.c = aVar;
                    }
                    aVar.b(NotificationLite.error(th));
                    return;
                }
                z = false;
                this.b = true;
            }
        }
        if (z) {
            io.reactivex.e.a.a(th);
        } else {
            this.a.onError(th);
        }
    }

    @Override // io.reactivex.v
    public void onComplete() {
        if (!this.d) {
            synchronized (this) {
                if (!this.d) {
                    this.d = true;
                    if (this.b) {
                        io.reactivex.internal.util.a<Object> aVar = this.c;
                        if (aVar == null) {
                            aVar = new io.reactivex.internal.util.a<>(4);
                            this.c = aVar;
                        }
                        aVar.a((io.reactivex.internal.util.a<Object>) NotificationLite.complete());
                        return;
                    }
                    this.b = true;
                    this.a.onComplete();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        io.reactivex.internal.util.a<Object> aVar;
        while (true) {
            synchronized (this) {
                aVar = this.c;
                if (aVar == null) {
                    this.b = false;
                    return;
                }
                this.c = null;
            }
            aVar.a((a.AbstractC0213a<? super Object>) this);
        }
    }

    @Override // io.reactivex.internal.util.a.AbstractC0213a, io.reactivex.c.k
    public boolean test(Object obj) {
        return NotificationLite.acceptFull(obj, this.a);
    }
}
