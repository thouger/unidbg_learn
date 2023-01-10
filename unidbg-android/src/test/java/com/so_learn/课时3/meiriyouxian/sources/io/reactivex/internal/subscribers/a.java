package io.reactivex.internal.subscribers;

import io.reactivex.internal.a.e;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.a.d;

/* compiled from: BasicFuseableConditionalSubscriber */
public abstract class a<T, R> implements io.reactivex.internal.a.a<T>, e<R> {
    protected final io.reactivex.internal.a.a<? super R> e;
    protected d f;
    protected e<T> g;
    protected boolean h;
    protected int i;

    /* access modifiers changed from: protected */
    public boolean a() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    public a(io.reactivex.internal.a.a<? super R> aVar) {
        this.e = aVar;
    }

    @Override // io.reactivex.j
    public final void onSubscribe(d dVar) {
        if (SubscriptionHelper.validate(this.f, dVar)) {
            this.f = dVar;
            if (dVar instanceof e) {
                this.g = (e) dVar;
            }
            if (a()) {
                this.e.onSubscribe(this);
                b();
            }
        }
    }

    public void onError(Throwable th) {
        if (this.h) {
            io.reactivex.e.a.a(th);
            return;
        }
        this.h = true;
        this.e.onError(th);
    }

    /* access modifiers changed from: protected */
    public final void a(Throwable th) {
        io.reactivex.exceptions.a.b(th);
        this.f.cancel();
        onError(th);
    }

    public void onComplete() {
        if (!this.h) {
            this.h = true;
            this.e.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public final int a(int i) {
        e<T> eVar = this.g;
        if (eVar == null || (i & 4) != 0) {
            return 0;
        }
        int requestFusion = eVar.requestFusion(i);
        if (requestFusion != 0) {
            this.i = requestFusion;
        }
        return requestFusion;
    }

    public void request(long j) {
        this.f.request(j);
    }

    public void cancel() {
        this.f.cancel();
    }

    @Override // io.reactivex.internal.a.h
    public boolean isEmpty() {
        return this.g.isEmpty();
    }

    @Override // io.reactivex.internal.a.h
    public void clear() {
        this.g.clear();
    }

    @Override // io.reactivex.internal.a.h
    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
