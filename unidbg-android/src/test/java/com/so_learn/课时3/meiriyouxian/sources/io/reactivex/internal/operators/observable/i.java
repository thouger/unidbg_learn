package io.reactivex.internal.operators.observable;

import io.reactivex.c.k;
import io.reactivex.t;
import io.reactivex.v;

/* compiled from: ObservableFilter */
public final class i<T> extends a<T, T> {
    final k<? super T> b;

    public i(t<T> tVar, k<? super T> kVar) {
        super(tVar);
        this.b = kVar;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new a(vVar, this.b));
    }

    /* compiled from: ObservableFilter */
    static final class a<T> extends io.reactivex.internal.observers.a<T, T> {
        final k<? super T> f;

        a(v<? super T> vVar, k<? super T> kVar) {
            super(vVar);
            this.f = kVar;
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (this.e == 0) {
                try {
                    if (this.f.test(t)) {
                        this.a.onNext(t);
                    }
                } catch (Throwable th) {
                    a(th);
                }
            } else {
                this.a.onNext(null);
            }
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            return a(i);
        }

        @Override // io.reactivex.internal.a.h
        public T poll() throws Exception {
            T poll;
            do {
                poll = this.c.poll();
                if (poll == null) {
                    break;
                }
            } while (!this.f.test(poll));
            return poll;
        }
    }
}
