package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.t;
import io.reactivex.v;

/* compiled from: ObservableMap */
public final class p<T, U> extends a<T, U> {
    final h<? super T, ? extends U> b;

    public p(t<T> tVar, h<? super T, ? extends U> hVar) {
        super(tVar);
        this.b = hVar;
    }

    @Override // io.reactivex.q
    public void a(v<? super U> vVar) {
        this.a.subscribe(new a(vVar, this.b));
    }

    /* compiled from: ObservableMap */
    static final class a<T, U> extends io.reactivex.internal.observers.a<T, U> {
        final h<? super T, ? extends U> f;

        a(v<? super U> vVar, h<? super T, ? extends U> hVar) {
            super(vVar);
            this.f = hVar;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v4, resolved type: io.reactivex.v */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.v
        public void onNext(T t) {
            if (!this.d) {
                if (this.e != 0) {
                    this.a.onNext(null);
                    return;
                }
                try {
                    this.a.onNext(io.reactivex.internal.functions.a.a(this.f.apply(t), "The mapper function returned a null value."));
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
        public U poll() throws Exception {
            T poll = this.c.poll();
            if (poll != null) {
                return (U) io.reactivex.internal.functions.a.a(this.f.apply(poll), "The mapper function returned a null value.");
            }
            return null;
        }
    }
}
