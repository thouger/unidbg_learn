package com.sijla.lj;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class g<K, V> extends c implements Map<K, V> {
    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return false;
    }

    @Override // java.util.Map
    public void putAll(Map map) {
    }

    @Override // java.util.Map
    public Collection values() {
        return null;
    }

    @Override // java.util.Map
    public void clear() {
        c();
        this.b.d();
        while (this.b.v(-2) != 0) {
            this.b.x(1);
            this.b.b(-1);
            this.b.d();
            this.b.u(-4);
        }
        this.b.x(1);
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        c();
        try {
            this.b.c(obj);
            boolean z = this.b.s(-2) == 0;
            this.b.x(1);
            this.b.x(1);
            return z;
        } catch (LException unused) {
            return false;
        }
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        HashSet hashSet = new HashSet();
        c();
        this.b.d();
        while (this.b.v(-2) != 0) {
            try {
                hashSet.add(new a(this.b.B(-2), this.b.B(-1)));
            } catch (LException unused) {
            }
            this.b.x(1);
        }
        this.b.x(1);
        return hashSet;
    }

    @Override // java.util.Map
    public V get(Object obj) {
        c();
        V v = null;
        try {
            this.b.c(obj);
            this.b.s(-2);
            v = (V) this.b.B(-1);
            this.b.x(1);
        } catch (LException unused) {
        }
        this.b.x(1);
        return v;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        c();
        this.b.d();
        boolean z = this.b.v(-2) == 0;
        if (z) {
            this.b.x(1);
        } else {
            this.b.x(3);
        }
        return z;
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        HashSet hashSet = new HashSet();
        c();
        this.b.d();
        while (this.b.v(-2) != 0) {
            try {
                hashSet.add(this.b.B(-2));
            } catch (LException unused) {
            }
            this.b.x(1);
        }
        this.b.x(1);
        return hashSet;
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        c();
        try {
            this.b.c((Object) k);
            this.b.c((Object) v);
            this.b.u(-3);
        } catch (LException unused) {
        }
        this.b.x(1);
        return null;
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        c();
        try {
            this.b.c(obj);
            this.b.u(-2);
        } catch (LException unused) {
        }
        this.b.x(1);
        return null;
    }

    @Override // java.util.Map
    public int size() {
        c();
        this.b.d();
        int i = 0;
        while (this.b.v(-2) != 0) {
            i++;
            this.b.x(1);
        }
        this.b.x(1);
        return i;
    }

    protected g(L l, int i) {
        super(l, i);
    }

    public class a<K, V> implements Map.Entry<K, V> {
        private K b;
        private V c;

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.c;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.c;
            this.c = v;
            return v2;
        }

        public a(K k, V v) {
            this.b = k;
            this.c = v;
        }
    }
}
