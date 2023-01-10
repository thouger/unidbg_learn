package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.a;
import com.google.common.base.i;
import com.google.common.base.m;
import com.google.common.collect.MapMakerInternalMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class MapMaker {
    boolean a;
    int b = -1;
    int c = -1;
    MapMakerInternalMap.Strength d;
    MapMakerInternalMap.Strength e;
    Equivalence<Object> f;

    enum Dummy {
        VALUE
    }

    /* access modifiers changed from: package-private */
    public MapMaker a(Equivalence<Object> equivalence) {
        m.b(this.f == null, "key equivalence was already set to %s", this.f);
        this.f = (Equivalence) m.a(equivalence);
        this.a = true;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Equivalence<Object> a() {
        return (Equivalence) i.a(this.f, e().defaultEquivalence());
    }

    public MapMaker a(int i) {
        boolean z = true;
        m.b(this.b == -1, "initial capacity was already set to %s", this.b);
        if (i < 0) {
            z = false;
        }
        m.a(z);
        this.b = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        int i = this.b;
        if (i == -1) {
            return 16;
        }
        return i;
    }

    public MapMaker b(int i) {
        boolean z = true;
        m.b(this.c == -1, "concurrency level was already set to %s", this.c);
        if (i <= 0) {
            z = false;
        }
        m.a(z);
        this.c = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        int i = this.c;
        if (i == -1) {
            return 4;
        }
        return i;
    }

    public MapMaker d() {
        return a(MapMakerInternalMap.Strength.WEAK);
    }

    /* access modifiers changed from: package-private */
    public MapMaker a(MapMakerInternalMap.Strength strength) {
        m.b(this.d == null, "Key strength was already set to %s", this.d);
        this.d = (MapMakerInternalMap.Strength) m.a(strength);
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.a = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public MapMakerInternalMap.Strength e() {
        return (MapMakerInternalMap.Strength) i.a(this.d, MapMakerInternalMap.Strength.STRONG);
    }

    /* access modifiers changed from: package-private */
    public MapMaker b(MapMakerInternalMap.Strength strength) {
        m.b(this.e == null, "Value strength was already set to %s", this.e);
        this.e = (MapMakerInternalMap.Strength) m.a(strength);
        if (strength != MapMakerInternalMap.Strength.STRONG) {
            this.a = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public MapMakerInternalMap.Strength f() {
        return (MapMakerInternalMap.Strength) i.a(this.e, MapMakerInternalMap.Strength.STRONG);
    }

    public <K, V> ConcurrentMap<K, V> g() {
        if (!this.a) {
            return new ConcurrentHashMap(b(), 0.75f, c());
        }
        return MapMakerInternalMap.create(this);
    }

    public String toString() {
        i.a a = i.a(this);
        int i = this.b;
        if (i != -1) {
            a.a("initialCapacity", i);
        }
        int i2 = this.c;
        if (i2 != -1) {
            a.a("concurrencyLevel", i2);
        }
        MapMakerInternalMap.Strength strength = this.d;
        if (strength != null) {
            a.a("keyStrength", a.a(strength.toString()));
        }
        MapMakerInternalMap.Strength strength2 = this.e;
        if (strength2 != null) {
            a.a("valueStrength", a.a(strength2.toString()));
        }
        if (this.f != null) {
            a.a("keyEquivalence");
        }
        return a.toString();
    }
}
