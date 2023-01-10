package com.google.common.util.concurrent;

import com.google.common.base.g;
import com.google.common.base.m;
import com.google.common.util.concurrent.h;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* compiled from: AbstractTransformFuture */
/* access modifiers changed from: package-private */
public abstract class c<I, O, F, T> extends h.a<O> implements Runnable {
    p<? extends I> a;
    F b;

    /* access modifiers changed from: package-private */
    public abstract T a(F f, I i) throws Exception;

    /* access modifiers changed from: package-private */
    public abstract void b(T t);

    static <I, O> p<O> a(p<I> pVar, g<? super I, ? extends O> gVar, Executor executor) {
        m.a(gVar);
        a aVar = new a(pVar, gVar);
        pVar.a(aVar, t.a(executor, aVar));
        return aVar;
    }

    c(p<? extends I> pVar, F f) {
        this.a = (p) m.a(pVar);
        this.b = (F) m.a(f);
    }

    @Override // java.lang.Runnable
    public final void run() {
        p<? extends I> pVar = this.a;
        F f = this.b;
        boolean z = true;
        boolean isCancelled = isCancelled() | (pVar == null);
        if (f != null) {
            z = false;
        }
        if (!isCancelled && !z) {
            this.a = null;
            if (pVar.isCancelled()) {
                a((p) pVar);
                return;
            }
            try {
                try {
                    Object a2 = a((c<I, O, F, T>) f, (F) l.a((Future<Object>) pVar));
                    this.b = null;
                    b((c<I, O, F, T>) a2);
                } catch (Throwable th) {
                    this.b = null;
                    throw th;
                }
            } catch (CancellationException unused) {
                cancel(false);
            } catch (ExecutionException e) {
                a(e.getCause());
            } catch (RuntimeException e2) {
                a((Throwable) e2);
            } catch (Error e3) {
                a((Throwable) e3);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.a
    public final void c() {
        a((Future<?>) this.a);
        this.a = null;
        this.b = null;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.a
    public String e() {
        String str;
        p<? extends I> pVar = this.a;
        F f = this.b;
        String e = super.e();
        if (pVar != null) {
            str = "inputFuture=[" + pVar + "], ";
        } else {
            str = "";
        }
        if (f != null) {
            return str + "function=[" + ((Object) f) + "]";
        } else if (e == null) {
            return null;
        } else {
            return str + e;
        }
    }

    /* compiled from: AbstractTransformFuture */
    /* access modifiers changed from: private */
    public static final class a<I, O> extends c<I, O, g<? super I, ? extends O>, O> {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.c
        public /* bridge */ /* synthetic */ Object a(Object obj, Object obj2) throws Exception {
            return a((g<? super g, ? extends Object>) ((g) obj), (g) obj2);
        }

        a(p<? extends I> pVar, g<? super I, ? extends O> gVar) {
            super(pVar, gVar);
        }

        /* access modifiers changed from: package-private */
        public O a(g<? super I, ? extends O> gVar, I i) {
            return (O) gVar.apply(i);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.c
        public void b(O o) {
            a((a<I, O>) o);
        }
    }
}
