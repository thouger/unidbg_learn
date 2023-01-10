package io.reactivex.observers;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.a;
import io.reactivex.v;

/* compiled from: SerializedObserver */
public final class c<T> implements b, v<T> {
    final v<? super T> a;
    final boolean b;
    b c;
    boolean d;
    a<Object> e;
    volatile boolean f;

    public c(v<? super T> vVar) {
        this(vVar, false);
    }

    public c(v<? super T> vVar, boolean z) {
        this.a = vVar;
        this.b = z;
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        if (DisposableHelper.validate(this.c, bVar)) {
            this.c = bVar;
            this.a.onSubscribe(this);
        }
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        this.c.dispose();
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.c.isDisposed();
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        if (!this.f) {
            if (t == null) {
                this.c.dispose();
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

    @Override // io.reactivex.v
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

    @Override // io.reactivex.v
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
        } while (!aVar.a((v) this.a));
    }
}
