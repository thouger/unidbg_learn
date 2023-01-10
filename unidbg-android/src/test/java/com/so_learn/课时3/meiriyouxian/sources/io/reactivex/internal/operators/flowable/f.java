package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.g;
import io.reactivex.j;
import org.a.c;

/* compiled from: FlowableMap */
public final class f<T, U> extends a<T, U> {
    final h<? super T, ? extends U> c;

    public f(g<T> gVar, h<? super T, ? extends U> hVar) {
        super(gVar);
        this.c = hVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super U> cVar) {
        if (cVar instanceof io.reactivex.internal.a.a) {
            this.b.a((j) new a((io.reactivex.internal.a.a) cVar, this.c));
        } else {
            this.b.a((j) new b(cVar, this.c));
        }
    }

    /* compiled from: FlowableMap */
    static final class b<T, U> extends io.reactivex.internal.subscribers.b<T, U> {
        final h<? super T, ? extends U> a;

        b(c<? super U> cVar, h<? super T, ? extends U> hVar) {
            super(cVar);
            this.a = hVar;
        }

        public void onNext(T t) {
            if (!this.h) {
                if (this.i != 0) {
                    this.e.onNext((Object) null);
                    return;
                }
                try {
                    this.e.onNext(io.reactivex.internal.functions.a.a(this.a.apply(t), "The mapper function returned a null value."));
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
            T poll = this.g.poll();
            if (poll != null) {
                return (U) io.reactivex.internal.functions.a.a(this.a.apply(poll), "The mapper function returned a null value.");
            }
            return null;
        }
    }

    /* compiled from: FlowableMap */
    static final class a<T, U> extends io.reactivex.internal.subscribers.a<T, U> {
        final h<? super T, ? extends U> a;

        a(io.reactivex.internal.a.a<? super U> aVar, h<? super T, ? extends U> hVar) {
            super(aVar);
            this.a = hVar;
        }

        public void onNext(T t) {
            if (!this.h) {
                if (this.i != 0) {
                    this.e.onNext(null);
                    return;
                }
                try {
                    this.e.onNext(io.reactivex.internal.functions.a.a(this.a.apply(t), "The mapper function returned a null value."));
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: io.reactivex.internal.a.a */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.internal.a.a
        public boolean tryOnNext(T t) {
            if (this.h) {
                return false;
            }
            try {
                return this.e.tryOnNext(io.reactivex.internal.functions.a.a(this.a.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                a(th);
                return true;
            }
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            return a(i);
        }

        @Override // io.reactivex.internal.a.h
        public U poll() throws Exception {
            T poll = this.g.poll();
            if (poll != null) {
                return (U) io.reactivex.internal.functions.a.a(this.a.apply(poll), "The mapper function returned a null value.");
            }
            return null;
        }
    }
}
