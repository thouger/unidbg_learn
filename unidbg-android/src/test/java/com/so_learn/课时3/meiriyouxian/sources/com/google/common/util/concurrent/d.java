package com.google.common.util.concurrent;

import com.google.common.base.m;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.bf;
import com.google.common.util.concurrent.a;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/* compiled from: AggregateFuture */
/* access modifiers changed from: package-private */
public abstract class d<InputT, OutputT> extends a.i<OutputT> {
    private static final Logger a = Logger.getLogger(d.class.getName());
    private d<InputT, OutputT>.a b;

    d() {
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.a
    public final void c() {
        super.c();
        h();
    }

    /* access modifiers changed from: protected */
    public final void h() {
        d<InputT, OutputT>.a aVar = this.b;
        if (aVar != null) {
            this.b = null;
            ImmutableCollection immutableCollection = ((a) aVar).b;
            boolean b = b();
            if (b) {
                aVar.c();
            }
            if (isCancelled() && (immutableCollection != null)) {
                bf it2 = immutableCollection.iterator();
                while (it2.hasNext()) {
                    it2.next().cancel(b);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.a
    public String e() {
        ImmutableCollection immutableCollection;
        d<InputT, OutputT>.a aVar = this.b;
        if (aVar == null || (immutableCollection = ((a) aVar).b) == null) {
            return null;
        }
        return "futures=[" + immutableCollection + "]";
    }

    /* compiled from: AggregateFuture */
    /* access modifiers changed from: package-private */
    public abstract class a extends e implements Runnable {
        final /* synthetic */ d a;
        private ImmutableCollection<? extends p<? extends InputT>> b;
        private final boolean c;
        private final boolean d;

        /* access modifiers changed from: package-private */
        public abstract void a(boolean z, int i, InputT inputt);

        /* access modifiers changed from: package-private */
        public abstract void b();

        /* access modifiers changed from: package-private */
        public void c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            f();
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x0029  */
        /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void a(java.lang.Throwable r6) {
            /*
                r5 = this;
                com.google.common.base.m.a(r6)
                boolean r0 = r5.c
                r1 = 1
                if (r0 == 0) goto L_0x001d
                com.google.common.util.concurrent.d r0 = r5.a
                boolean r0 = r0.a(r6)
                if (r0 == 0) goto L_0x0014
                r5.a()
                goto L_0x001e
            L_0x0014:
                java.util.Set r2 = r5.d()
                boolean r2 = com.google.common.util.concurrent.d.a(r2, r6)
                goto L_0x001f
            L_0x001d:
                r0 = 0
            L_0x001e:
                r2 = r1
            L_0x001f:
                boolean r3 = r6 instanceof java.lang.Error
                boolean r4 = r5.c
                r0 = r0 ^ r1
                r0 = r0 & r4
                r0 = r0 & r2
                r0 = r0 | r3
                if (r0 == 0) goto L_0x003b
                if (r3 == 0) goto L_0x002f
                java.lang.String r0 = "Input Future failed with Error"
                goto L_0x0032
            L_0x002f:
                java.lang.String r0 = "Got more than one input Future failure. Logging failures after the first"
            L_0x0032:
                java.util.logging.Logger r1 = com.google.common.util.concurrent.d.i()
                java.util.logging.Level r2 = java.util.logging.Level.SEVERE
                r1.log(r2, r0, r6)
            L_0x003b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.d.a.a(java.lang.Throwable):void");
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.e
        public final void a(Set<Throwable> set) {
            if (!this.a.isCancelled()) {
                d.b(set, this.a.d());
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.util.concurrent.d$a */
        /* JADX WARN: Multi-variable type inference failed */
        private void a(int i, Future<? extends InputT> future) {
            m.b(this.c || !this.a.isDone() || this.a.isCancelled(), "Future was done before all dependencies completed");
            try {
                m.b(future.isDone(), "Tried to set value from future which is not done");
                if (this.c) {
                    if (future.isCancelled()) {
                        this.a.b = null;
                        this.a.cancel(false);
                        return;
                    }
                    Object a = l.a((Future<Object>) future);
                    if (this.d) {
                        a(this.c, i, a);
                    }
                } else if (this.d && !future.isCancelled()) {
                    a(this.c, i, l.a((Future<Object>) future));
                }
            } catch (ExecutionException e) {
                a(e.getCause());
            } catch (Throwable th) {
                a(th);
            }
        }

        private void f() {
            int e = e();
            m.b(e >= 0, "Less than 0 remaining futures");
            if (e == 0) {
                g();
            }
        }

        private void g() {
            if (this.d && (!this.c)) {
                int i = 0;
                bf<? extends p<? extends InputT>> it2 = this.b.iterator();
                while (it2.hasNext()) {
                    a(i, it2.next());
                    i++;
                }
            }
            b();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.b = null;
        }
    }

    /* access modifiers changed from: private */
    public static boolean b(Set<Throwable> set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }
}
