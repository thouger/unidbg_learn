package com.google.common.collect;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import java.util.Map;

public abstract class ImmutableBiMap<K, V> extends ImmutableMap<K, V> implements k<K, V> {
    @Override // com.google.common.collect.k
    public abstract ImmutableBiMap<V, K> inverse();

    public static <K, V> ImmutableBiMap<K, V> of() {
        return RegularImmutableBiMap.EMPTY;
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k, V v) {
        n.a(k, v);
        return new RegularImmutableBiMap(new Object[]{k, v}, 1);
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k, V v, K k2, V v2) {
        n.a(k, v);
        n.a(k2, v2);
        return new RegularImmutableBiMap(new Object[]{k, v, k2, v2}, 2);
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3) {
        n.a(k, v);
        n.a(k2, v2);
        n.a(k3, v3);
        return new RegularImmutableBiMap(new Object[]{k, v, k2, v2, k3, v3}, 3);
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        n.a(k, v);
        n.a(k2, v2);
        n.a(k3, v3);
        n.a(k4, v4);
        return new RegularImmutableBiMap(new Object[]{k, v, k2, v2, k3, v3, k4, v4}, 4);
    }

    public static <K, V> ImmutableBiMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        n.a(k, v);
        n.a(k2, v2);
        n.a(k3, v3);
        n.a(k4, v4);
        n.a(k5, v5);
        return new RegularImmutableBiMap(new Object[]{k, v, k2, v2, k3, v3, k4, v4, k5, v5}, 5);
    }

    public static <K, V> a<K, V> builder() {
        return new a<>();
    }

    public static <K, V> a<K, V> builderWithExpectedSize(int i) {
        n.a(i, "expectedSize");
        return new a<>(i);
    }

    public static final class a<K, V> extends ImmutableMap.a<K, V> {
        public a() {
        }

        a(int i) {
            super(i);
        }

        /* renamed from: a */
        public a<K, V> b(K k, V v) {
            super.b(k, v);
            return this;
        }

        /* renamed from: a */
        public a<K, V> b(Map.Entry<? extends K, ? extends V> entry) {
            super.b(entry);
            return this;
        }

        /* renamed from: a */
        public a<K, V> b(Map<? extends K, ? extends V> map) {
            super.b(map);
            return this;
        }

        /* renamed from: a */
        public a<K, V> b(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            super.b(iterable);
            return this;
        }

        /* renamed from: a */
        public ImmutableBiMap<K, V> b() {
            if (this.c == 0) {
                return ImmutableBiMap.of();
            }
            c();
            this.d = true;
            return new RegularImmutableBiMap(this.b, this.c);
        }
    }

    public static <K, V> ImmutableBiMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if (map instanceof ImmutableBiMap) {
            ImmutableBiMap<K, V> immutableBiMap = (ImmutableBiMap) map;
            if (!immutableBiMap.isPartialView()) {
                return immutableBiMap;
            }
        }
        return copyOf((Iterable) map.entrySet());
    }

    public static <K, V> ImmutableBiMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        return new a(iterable instanceof Collection ? ((Collection) iterable).size() : 4).b(iterable).b();
    }

    ImmutableBiMap() {
    }

    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: com.google.common.collect.ImmutableSet<K>, com.google.common.collect.ImmutableSet<V> */
    @Override // com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.k
    public ImmutableSet<V> values() {
        return (ImmutableSet<K>) inverse().keySet();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableSet<V> createValues() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.k
    @Deprecated
    public V forcePut(K k, V v) {
        throw new UnsupportedOperationException();
    }

    private static class SerializedForm extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;

        SerializedForm(ImmutableBiMap<?, ?> immutableBiMap) {
            super(immutableBiMap);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap.SerializedForm
        public Object readResolve() {
            return createMap(new a());
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
