package io.reactivex.internal.operators.observable;

import io.reactivex.c.g;
import io.reactivex.t;
import io.reactivex.v;

/* compiled from: ObservableDoAfterNext */
public final class d<T> extends a<T, T> {
    final g<? super T> b;

    public d(t<T> tVar, g<? super T> gVar) {
        super(tVar);
        this.b = gVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new a(vVar, this.b));
    }

    /* compiled from: ObservableDoAfterNext */
    static final class a<T> extends io.reactivex.internal.observers.a<T, T> {
        final g<? super T> f;

        a(v<? super T> vVar, g<? super T> gVar) {
            super(vVar);
            this.f = gVar;
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.a.onNext(t);
            if (this.e == 0) {
                try {
                    this.f.accept(t);
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            return a(i);
        }

        @Override // io.reactivex.internal.a.h
        public T poll() throws Exception {
            T poll = this.c.poll();
            if (poll != null) {
                this.f.accept(poll);
            }
            return poll;
        }
    }
}
