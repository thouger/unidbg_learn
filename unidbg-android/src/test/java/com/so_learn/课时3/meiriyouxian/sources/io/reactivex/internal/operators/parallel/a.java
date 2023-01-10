package io.reactivex.internal.operators.parallel;

import io.reactivex.c.h;
import io.reactivex.internal.operators.flowable.FlowableFlatMap;
import org.a.b;
import org.a.c;

/* compiled from: ParallelFlatMap */
public final class a<T, R> extends io.reactivex.parallel.a<R> {
    final io.reactivex.parallel.a<T> a;
    final h<? super T, ? extends b<? extends R>> b;
    final boolean c;
    final int d;
    final int e;

    public a(io.reactivex.parallel.a<T> aVar, h<? super T, ? extends b<? extends R>> hVar, boolean z, int i, int i2) {
        this.a = aVar;
        this.b = hVar;
        this.c = z;
        this.d = i;
        this.e = i2;
    }

    @Override // io.reactivex.parallel.a
    public int a() {
        return this.a.a();
    }

    @Override // io.reactivex.parallel.a
    public void a(c<? super R>[] cVarArr) {
        if (b(cVarArr)) {
            int length = cVarArr.length;
            c[] cVarArr2 = new c[length];
            for (int i = 0; i < length; i++) {
                cVarArr2[i] = FlowableFlatMap.a(cVarArr[i], this.b, this.c, this.d, this.e);
            }
            this.a.a(cVarArr2);
        }
    }
}
