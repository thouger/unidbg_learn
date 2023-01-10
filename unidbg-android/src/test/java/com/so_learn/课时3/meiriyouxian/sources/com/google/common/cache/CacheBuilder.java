package com.google.common.cache;

import com.google.common.base.Equivalence;
import com.google.common.base.Suppliers;
import com.google.common.base.i;
import com.google.common.base.m;
import com.google.common.base.q;
import com.google.common.base.s;
import com.google.common.cache.LocalCache;
import com.google.common.cache.a;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class CacheBuilder<K, V> {
    static final q<? extends a.b> a = Suppliers.a(new AnonymousClass1());
    static final c b = new c(0, 0, 0, 0, 0, 0);
    static final q<a.b> c = new AnonymousClass2();
    static final s d = new AnonymousClass3();
    private static final Logger u = Logger.getLogger(CacheBuilder.class.getName());
    boolean e = true;
    int f = -1;
    int g = -1;
    long h = -1;
    long i = -1;
    i<? super K, ? super V> j;
    LocalCache.Strength k;
    LocalCache.Strength l;
    long m = -1;
    long n = -1;
    long o = -1;
    Equivalence<Object> p;
    Equivalence<Object> q;
    h<? super K, ? super V> r;
    s s;
    q<? extends a.b> t = a;

    /* access modifiers changed from: package-private */
    public enum NullListener implements h<Object, Object> {
        INSTANCE;

        @Override // com.google.common.cache.h
        public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
        }
    }

    /* access modifiers changed from: package-private */
    public enum OneWeigher implements i<Object, Object> {
        INSTANCE;

        @Override // com.google.common.cache.i
        public int weigh(Object obj, Object obj2) {
            return 1;
        }
    }

    /* renamed from: com.google.common.cache.CacheBuilder$1  reason: invalid class name */
    static class AnonymousClass1 implements a.b {
        @Override // com.google.common.cache.a.b
        public void a() {
        }

        @Override // com.google.common.cache.a.b
        public void a(int i) {
        }

        @Override // com.google.common.cache.a.b
        public void a(long j) {
        }

        @Override // com.google.common.cache.a.b
        public void b(int i) {
        }

        @Override // com.google.common.cache.a.b
        public void b(long j) {
        }

        AnonymousClass1() {
        }

        @Override // com.google.common.cache.a.b
        public c b() {
            return CacheBuilder.b;
        }
    }

    /* renamed from: com.google.common.cache.CacheBuilder$2  reason: invalid class name */
    static class AnonymousClass2 implements q<a.b> {
        AnonymousClass2() {
        }

        /* renamed from: a */
        public a.b get() {
            return new a.C0103a();
        }
    }

    /* renamed from: com.google.common.cache.CacheBuilder$3  reason: invalid class name */
    static class AnonymousClass3 extends s {
        @Override // com.google.common.base.s
        public long a() {
            return 0;
        }

        AnonymousClass3() {
        }
    }

    private CacheBuilder() {
    }

    public static CacheBuilder<Object, Object> a() {
        return new CacheBuilder<>();
    }

    /* access modifiers changed from: package-private */
    public CacheBuilder<K, V> a(Equivalence<Object> equivalence) {
        m.b(this.p == null, "key equivalence was already set to %s", this.p);
        this.p = (Equivalence) m.a(equivalence);
        return this;
    }

    /* access modifiers changed from: package-private */
    public Equivalence<Object> b() {
        return (Equivalence) i.a(this.p, h().defaultEquivalence());
    }

    /* access modifiers changed from: package-private */
    public CacheBuilder<K, V> b(Equivalence<Object> equivalence) {
        m.b(this.q == null, "value equivalence was already set to %s", this.q);
        this.q = (Equivalence) m.a(equivalence);
        return this;
    }

    /* access modifiers changed from: package-private */
    public Equivalence<Object> c() {
        return (Equivalence) i.a(this.q, i().defaultEquivalence());
    }

    /* access modifiers changed from: package-private */
    public int d() {
        int i = this.f;
        if (i == -1) {
            return 16;
        }
        return i;
    }

    public CacheBuilder<K, V> a(int i) {
        boolean z = true;
        m.b(this.g == -1, "concurrency level was already set to %s", this.g);
        if (i <= 0) {
            z = false;
        }
        m.a(z);
        this.g = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        int i = this.g;
        if (i == -1) {
            return 4;
        }
        return i;
    }

    public CacheBuilder<K, V> a(long j) {
        boolean z = true;
        m.b(this.h == -1, "maximum size was already set to %s", this.h);
        m.b(this.i == -1, "maximum weight was already set to %s", this.i);
        m.b(this.j == null, "maximum size can not be combined with weigher");
        if (j < 0) {
            z = false;
        }
        m.a(z, "maximum size must not be negative");
        this.h = j;
        return this;
    }

    public CacheBuilder<K, V> b(long j) {
        boolean z = true;
        m.b(this.i == -1, "maximum weight was already set to %s", this.i);
        m.b(this.h == -1, "maximum size was already set to %s", this.h);
        this.i = j;
        if (j < 0) {
            z = false;
        }
        m.a(z, "maximum weight must not be negative");
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: com.google.common.cache.CacheBuilder<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> a(i<? super K1, ? super V1> iVar) {
        boolean z = true;
        m.b(this.j == null);
        if (this.e) {
            if (this.h != -1) {
                z = false;
            }
            m.b(z, "weigher can not be combined with maximum size", this.h);
        }
        this.j = (i) m.a(iVar);
        return this;
    }

    /* access modifiers changed from: package-private */
    public long f() {
        if (this.m == 0 || this.n == 0) {
            return 0;
        }
        return this.j == null ? this.h : this.i;
    }

    /* access modifiers changed from: package-private */
    public <K1 extends K, V1 extends V> i<K1, V1> g() {
        return (i) i.a(this.j, OneWeigher.INSTANCE);
    }

    /* access modifiers changed from: package-private */
    public CacheBuilder<K, V> a(LocalCache.Strength strength) {
        m.b(this.k == null, "Key strength was already set to %s", this.k);
        this.k = (LocalCache.Strength) m.a(strength);
        return this;
    }

    /* access modifiers changed from: package-private */
    public LocalCache.Strength h() {
        return (LocalCache.Strength) i.a(this.k, LocalCache.Strength.STRONG);
    }

    /* access modifiers changed from: package-private */
    public CacheBuilder<K, V> b(LocalCache.Strength strength) {
        m.b(this.l == null, "Value strength was already set to %s", this.l);
        this.l = (LocalCache.Strength) m.a(strength);
        return this;
    }

    /* access modifiers changed from: package-private */
    public LocalCache.Strength i() {
        return (LocalCache.Strength) i.a(this.l, LocalCache.Strength.STRONG);
    }

    public CacheBuilder<K, V> a(long j, TimeUnit timeUnit) {
        boolean z = true;
        m.b(this.m == -1, "expireAfterWrite was already set to %s ns", this.m);
        if (j < 0) {
            z = false;
        }
        m.a(z, "duration cannot be negative: %s %s", j, timeUnit);
        this.m = timeUnit.toNanos(j);
        return this;
    }

    /* access modifiers changed from: package-private */
    public long j() {
        long j = this.m;
        if (j == -1) {
            return 0;
        }
        return j;
    }

    public CacheBuilder<K, V> b(long j, TimeUnit timeUnit) {
        boolean z = true;
        m.b(this.n == -1, "expireAfterAccess was already set to %s ns", this.n);
        if (j < 0) {
            z = false;
        }
        m.a(z, "duration cannot be negative: %s %s", j, timeUnit);
        this.n = timeUnit.toNanos(j);
        return this;
    }

    /* access modifiers changed from: package-private */
    public long k() {
        long j = this.n;
        if (j == -1) {
            return 0;
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public long l() {
        long j = this.o;
        if (j == -1) {
            return 0;
        }
        return j;
    }

    public CacheBuilder<K, V> a(s sVar) {
        m.b(this.s == null);
        this.s = (s) m.a(sVar);
        return this;
    }

    /* access modifiers changed from: package-private */
    public s a(boolean z) {
        s sVar = this.s;
        if (sVar != null) {
            return sVar;
        }
        return z ? s.b() : d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.common.cache.CacheBuilder<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> a(h<? super K1, ? super V1> hVar) {
        m.b(this.r == null);
        this.r = (h) m.a(hVar);
        return this;
    }

    /* access modifiers changed from: package-private */
    public <K1 extends K, V1 extends V> h<K1, V1> m() {
        return (h) i.a(this.r, NullListener.INSTANCE);
    }

    /* access modifiers changed from: package-private */
    public q<? extends a.b> n() {
        return this.t;
    }

    public <K1 extends K, V1 extends V> e<K1, V1> a(CacheLoader<? super K1, V1> cacheLoader) {
        q();
        return new LocalCache.LocalLoadingCache(this, cacheLoader);
    }

    public <K1 extends K, V1 extends V> b<K1, V1> o() {
        q();
        p();
        return new LocalCache.LocalManualCache(this);
    }

    private void p() {
        m.b(this.o == -1, "refreshAfterWrite requires a LoadingCache");
    }

    private void q() {
        boolean z = true;
        if (this.j == null) {
            if (this.i != -1) {
                z = false;
            }
            m.b(z, "maximumWeight requires weigher");
        } else if (this.e) {
            if (this.i == -1) {
                z = false;
            }
            m.b(z, "weigher requires maximumWeight");
        } else if (this.i == -1) {
            u.log(Level.WARNING, "ignoring weigher specified without maximumWeight");
        }
    }

    public String toString() {
        i.a a2 = i.a(this);
        int i = this.f;
        if (i != -1) {
            a2.a("initialCapacity", i);
        }
        int i2 = this.g;
        if (i2 != -1) {
            a2.a("concurrencyLevel", i2);
        }
        long j = this.h;
        if (j != -1) {
            a2.a("maximumSize", j);
        }
        long j2 = this.i;
        if (j2 != -1) {
            a2.a("maximumWeight", j2);
        }
        if (this.m != -1) {
            a2.a("expireAfterWrite", this.m + "ns");
        }
        if (this.n != -1) {
            a2.a("expireAfterAccess", this.n + "ns");
        }
        LocalCache.Strength strength = this.k;
        if (strength != null) {
            a2.a("keyStrength", com.google.common.base.a.a(strength.toString()));
        }
        LocalCache.Strength strength2 = this.l;
        if (strength2 != null) {
            a2.a("valueStrength", com.google.common.base.a.a(strength2.toString()));
        }
        if (this.p != null) {
            a2.a("keyEquivalence");
        }
        if (this.q != null) {
            a2.a("valueEquivalence");
        }
        if (this.r != null) {
            a2.a("removalListener");
        }
        return a2.toString();
    }
}
