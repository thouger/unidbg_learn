package io.reactivex.parallel;

import io.reactivex.c.h;
import io.reactivex.g;
import io.reactivex.internal.operators.parallel.ParallelFromPublisher;
import io.reactivex.internal.operators.parallel.ParallelJoin;
import io.reactivex.internal.operators.parallel.ParallelRunOn;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.w;
import org.a.b;
import org.a.c;

/* compiled from: ParallelFlowable */
public abstract class a<T> {
    public abstract int a();

    public abstract void a(c<? super T>[] cVarArr);

    /* access modifiers changed from: protected */
    public final boolean b(c<?>[] cVarArr) {
        int a = a();
        if (cVarArr.length == a) {
            return true;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("parallelism = " + a + ", subscribers = " + cVarArr.length);
        int length = cVarArr.length;
        for (int i = 0; i < length; i++) {
            EmptySubscription.error(illegalArgumentException, cVarArr[i]);
        }
        return false;
    }

    public static <T> a<T> a(b<? extends T> bVar, int i) {
        return a(bVar, i, g.a());
    }

    public static <T> a<T> a(b<? extends T> bVar, int i, int i2) {
        io.reactivex.internal.functions.a.a(bVar, "source");
        io.reactivex.internal.functions.a.a(i, "parallelism");
        io.reactivex.internal.functions.a.a(i2, "prefetch");
        return io.reactivex.e.a.a(new ParallelFromPublisher(bVar, i, i2));
    }

    public final a<T> a(w wVar) {
        return a(wVar, g.a());
    }

    public final a<T> a(w wVar, int i) {
        io.reactivex.internal.functions.a.a(wVar, "scheduler");
        io.reactivex.internal.functions.a.a(i, "prefetch");
        return io.reactivex.e.a.a(new ParallelRunOn(this, wVar, i));
    }

    public final g<T> b() {
        return a(g.a());
    }

    public final g<T> a(int i) {
        io.reactivex.internal.functions.a.a(i, "prefetch");
        return io.reactivex.e.a.a(new ParallelJoin(this, i, false));
    }

    public final <R> a<R> a(h<? super T, ? extends b<? extends R>> hVar) {
        return a(hVar, false, Integer.MAX_VALUE, g.a());
    }

    public final <R> a<R> a(h<? super T, ? extends b<? extends R>> hVar, boolean z, int i, int i2) {
        io.reactivex.internal.functions.a.a(hVar, "mapper is null");
        io.reactivex.internal.functions.a.a(i, "maxConcurrency");
        io.reactivex.internal.functions.a.a(i2, "prefetch");
        return io.reactivex.e.a.a(new io.reactivex.internal.operators.parallel.a(this, hVar, z, i, i2));
    }
}
