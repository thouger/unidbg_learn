package io.reactivex.subscribers;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.a;
import io.reactivex.j;
import org.a.c;
import org.a.d;

/* compiled from: SerializedSubscriber */
public final class b<T> implements j<T>, d {
    final c<? super T> a;
    final boolean b;
    d c;
    boolean d;
    a<Object> e;
    volatile boolean f;

    public b(c<? super T> cVar) {
        this(cVar, false);
    }

    public b(c<? super T> cVar, boolean z) {
        this.a = cVar;
        this.b = z;
    }

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        if (SubscriptionHelper.validate(this.c, dVar)) {
            this.c = dVar;
            this.a.onSubscribe(this);
        }
    }

    public void onNext(T t) {
        if (!this.f) {
            if (t == null) {
                this.c.cancel();
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            synchronized (this) {
                if (!this.f) {
                    if (this.d) {
                        a<Object> aVar = this.e;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.e = aVar;
                        }
                        aVar.a((a<Object>) NotificationLite.next(t));
                        return;
                    }
                    this.d = true;
                    this.a.onNext(t);
                    a();
                }
            }
        }
    }

    public void onError(Throwable th) {
        boolean z;
        if (this.f) {
            io.reactivex.e.a.a(th);
            return;
        }
        synchronized (this) {
            z = true;
            if (!this.f) {
                if (this.d) {
                    this.f = true;
                    a<Object> aVar = this.e;
                    if (aVar == null) {
                        aVar = new a<>(4);
                        this.e = aVar;
                    }
                    Object error = NotificationLite.error(th);
                    if (this.b) {
                        aVar.a((a<Object>) error);
                    } else {
                        aVar.b(error);
                    }
                    return;
                }
                this.f = true;
                this.d = true;
                z = false;
            }
        }
        if (z) {
            io.reactivex.e.a.a(th);
        } else {
            this.a.onError(th);
        }
    }

    public void onComplete() {
        if (!this.f) {
            synchronized (this) {
                if (!this.f) {
                    if (this.d) {
                        a<Object> aVar = this.e;
                        if (aVar == null) {
                            aVar = new a<>(4);
                            this.e = aVar;
                        }
                        aVar.a((a<Object>) NotificationLite.complete());
                        return;
                    }
                    this.f = true;
                    this.d = true;
                    this.a.onComplete();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        a<Object> aVar;
        do {
            synchronized (this) {
                aVar = this.e;
                if (aVar == null) {
                    this.d = false;
                    return;
                }
                this.e = null;
            }
        } while (!aVar.a((c) this.a));
    }

    public void request(long j) {
        this.c.request(j);
    }

    public void cancel() {
        this.c.cancel();
    }
}
