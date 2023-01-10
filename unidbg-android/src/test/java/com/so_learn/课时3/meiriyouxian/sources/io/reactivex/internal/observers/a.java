package io.reactivex.internal.observers;

import io.reactivex.disposables.b;
import io.reactivex.internal.a.c;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;

/* compiled from: BasicFuseableObserver */
public abstract class a<T, R> implements c<R>, v<T> {
    protected final v<? super R> a;
    protected b b;
    protected c<T> c;
    protected boolean d;
    protected int e;

    /* access modifiers changed from: protected */
    public boolean a() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    public a(v<? super R> vVar) {
        this.a = vVar;
    }

    @Override // io.reactivex.v
    public final void onSubscribe(b bVar) {
        if (DisposableHelper.validate(this.b, bVar)) {
            this.b = bVar;
            if (bVar instanceof c) {
                this.c = (c) bVar;
            }
            if (a()) {
                this.a.onSubscribe(this);
                b();
            }
        }
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        if (this.d) {
            io.reactivex.e.a.a(th);
            return;
        }
        this.d = true;
        this.a.onError(th);
    }

    /* access modifiers changed from: protected */
    public final void a(Throwable th) {
        io.reactivex.exceptions.a.b(th);
        this.b.dispose();
        onError(th);
    }

    @Override // io.reactivex.v
    public void onComplete() {
        if (!this.d) {
            this.d = true;
            this.a.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public final int a(int i) {
        c<T> cVar = this.c;
        if (cVar == null || (i & 4) != 0) {
            return 0;
        }
        int requestFusion = cVar.requestFusion(i);
        if (requestFusion != 0) {
            this.e = requestFusion;
        }
        return requestFusion;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        this.b.dispose();
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.b.isDisposed();
    }

    @Override // io.reactivex.internal.a.h
    public boolean isEmpty() {
        return this.c.isEmpty();
    }

    @Override // io.reactivex.internal.a.h
    public void clear() {
        this.c.clear();
    }

    @Override // io.reactivex.internal.a.h
    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
