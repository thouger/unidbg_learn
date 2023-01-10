package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.x;
import io.reactivex.z;

/* compiled from: SingleMap */
public final class a<T, R> extends x<R> {
    final ab<? extends T> a;
    final h<? super T, ? extends R> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super R> zVar) {
        this.a.a(new C0210a(zVar, this.b));
    }

    /* compiled from: SingleMap */
    /* renamed from: io.reactivex.internal.operators.single.a$a  reason: collision with other inner class name */
    static final class C0210a<T, R> implements z<T> {
        final z<? super R> a;
        final h<? super T, ? extends R> b;

        C0210a(z<? super R> zVar, h<? super T, ? extends R> hVar) {
            this.a = zVar;
            this.b = hVar;
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            this.a.onSubscribe(bVar);
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            try {
                this.a.onSuccess(io.reactivex.internal.functions.a.a(this.b.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                onError(th);
            }
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.a.onError(th);
        }
    }
}
