package com.google.common.util.concurrent;

import com.google.common.collect.Sets;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: AggregateFutureState */
abstract class e {
    private static final a c;
    private static final Logger d = Logger.getLogger(e.class.getName());
    private volatile Set<Throwable> a;
    private volatile int b;

    /* access modifiers changed from: package-private */
    public abstract void a(Set<Throwable> set);

    static /* synthetic */ int b(e eVar) {
        int i = eVar.b;
        eVar.b = i - 1;
        return i;
    }

    static {
        a aVar;
        Throwable th;
        try {
            aVar = new b(AtomicReferenceFieldUpdater.newUpdater(e.class, Set.class, "a"), AtomicIntegerFieldUpdater.newUpdater(e.class, "b"));
            th = null;
        } catch (Throwable th2) {
            th = th2;
            aVar = new c();
        }
        c = aVar;
        if (th != null) {
            d.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
    }

    /* access modifiers changed from: package-private */
    public final Set<Throwable> d() {
        Set<Throwable> set = this.a;
        if (set != null) {
            return set;
        }
        Set<Throwable> b2 = Sets.b();
        a(b2);
        c.a(this, null, b2);
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public final int e() {
        return c.a(this);
    }

    /* compiled from: AggregateFutureState */
    private static abstract class a {
        /* access modifiers changed from: package-private */
        public abstract int a(e eVar);

        /* access modifiers changed from: package-private */
        public abstract void a(e eVar, Set<Throwable> set, Set<Throwable> set2);

        private a() {
        }
    }

    /* compiled from: AggregateFutureState */
    private static final class b extends a {
        final AtomicReferenceFieldUpdater<e, Set<Throwable>> a;
        final AtomicIntegerFieldUpdater<e> b;

        b(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            super();
            this.a = atomicReferenceFieldUpdater;
            this.b = atomicIntegerFieldUpdater;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.e.a
        public void a(e eVar, Set<Throwable> set, Set<Throwable> set2) {
            this.a.compareAndSet(eVar, set, set2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.e.a
        public int a(e eVar) {
            return this.b.decrementAndGet(eVar);
        }
    }

    /* compiled from: AggregateFutureState */
    private static final class c extends a {
        private c() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.e.a
        public void a(e eVar, Set<Throwable> set, Set<Throwable> set2) {
            synchronized (eVar) {
                if (eVar.a == set) {
                    eVar.a = set2;
                }
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.e.a
        public int a(e eVar) {
            int i;
            synchronized (eVar) {
                e.b(eVar);
                i = eVar.b;
            }
            return i;
        }
    }
}
