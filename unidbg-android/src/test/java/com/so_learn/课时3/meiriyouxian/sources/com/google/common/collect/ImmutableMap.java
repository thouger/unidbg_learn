package com.google.common.collect;

import com.google.common.collect.ImmutableCollection;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public abstract class ImmutableMap<K, V> implements Serializable, Map<K, V> {
    static final Map.Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];
    private transient ImmutableSet<Map.Entry<K, V>> entrySet;
    private transient ImmutableSet<K> keySet;
    private transient ImmutableSetMultimap<K, V> multimapView;
    private transient ImmutableCollection<V> values;

    /* access modifiers changed from: package-private */
    public abstract ImmutableSet<Map.Entry<K, V>> createEntrySet();

    /* access modifiers changed from: package-private */
    public abstract ImmutableSet<K> createKeySet();

    /* access modifiers changed from: package-private */
    public abstract ImmutableCollection<V> createValues();

    @Override // java.util.Map
    public abstract V get(Object obj);

    /* access modifiers changed from: package-private */
    public boolean isHashCodeFast() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public abstract boolean isPartialView();

    public static <K, V> ImmutableMap<K, V> of() {
        return (ImmutableMap<K, V>) RegularImmutableMap.EMPTY;
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v) {
        n.a(k, v);
        return RegularImmutableMap.create(1, new Object[]{k, v});
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v, K k2, V v2) {
        n.a(k, v);
        n.a(k2, v2);
        return RegularImmutableMap.create(2, new Object[]{k, v, k2, v2});
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3) {
        n.a(k, v);
        n.a(k2, v2);
        n.a(k3, v3);
        return RegularImmutableMap.create(3, new Object[]{k, v, k2, v2, k3, v3});
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        n.a(k, v);
        n.a(k2, v2);
        n.a(k3, v3);
        n.a(k4, v4);
        return RegularImmutableMap.create(4, new Object[]{k, v, k2, v2, k3, v3, k4, v4});
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        n.a(k, v);
        n.a(k2, v2);
        n.a(k3, v3);
        n.a(k4, v4);
        n.a(k5, v5);
        return RegularImmutableMap.create(5, new Object[]{k, v, k2, v2, k3, v3, k4, v4, k5, v5});
    }

    static <K, V> Map.Entry<K, V> entryOf(K k, V v) {
        n.a(k, v);
        return new AbstractMap.SimpleImmutableEntry(k, v);
    }

    public static <K, V> a<K, V> builder() {
        return new a<>();
    }

    public static <K, V> a<K, V> builderWithExpectedSize(int i) {
        n.a(i, "expectedSize");
        return new a<>(i);
    }

    static void checkNoConflict(boolean z, String str, Map.Entry<?, ?> entry, Map.Entry<?, ?> entry2) {
        if (!z) {
            throw conflictException(str, entry, entry2);
        }
    }

    static IllegalArgumentException conflictException(String str, Object obj, Object obj2) {
        return new IllegalArgumentException("Multiple entries with same " + str + ": " + obj + " and " + obj2);
    }

    public static class a<K, V> {
        Comparator<? super V> a;
        Object[] b;
        int c;
        boolean d;

        public a() {
            this(4);
        }

        a(int i) {
            this.b = new Object[(i * 2)];
            this.c = 0;
            this.d = false;
        }

        private void a(int i) {
            int i2 = i * 2;
            Object[] objArr = this.b;
            if (i2 > objArr.length) {
                this.b = Arrays.copyOf(objArr, ImmutableCollection.b.a(objArr.length, i2));
                this.d = false;
            }
        }

        public a<K, V> b(K k, V v) {
            a(this.c + 1);
            n.a(k, v);
            Object[] objArr = this.b;
            int i = this.c;
            objArr[i * 2] = k;
            objArr[(i * 2) + 1] = v;
            this.c = i + 1;
            return this;
        }

        public a<K, V> b(Map.Entry<? extends K, ? extends V> entry) {
            return b(entry.getKey(), entry.getValue());
        }

        public a<K, V> b(Map<? extends K, ? extends V> map) {
            return b(map.entrySet());
        }

        public a<K, V> b(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            if (iterable instanceof Collection) {
                a(this.c + ((Collection) iterable).size());
            }
            Iterator<? extends Map.Entry<? extends K, ? extends V>> it2 = iterable.iterator();
            while (it2.hasNext()) {
                b((Map.Entry) it2.next());
            }
            return this;
        }

        public ImmutableMap<K, V> b() {
            c();
            this.d = true;
            return RegularImmutableMap.create(this.c, this.b);
        }

        /* access modifiers changed from: package-private */
        public void c() {
            int i;
            if (this.a != null) {
                if (this.d) {
                    this.b = Arrays.copyOf(this.b, this.c * 2);
                }
                Map.Entry[] entryArr = new Map.Entry[this.c];
                int i2 = 0;
                while (true) {
                    i = this.c;
                    if (i2 >= i) {
                        break;
                    }
                    Object[] objArr = this.b;
                    int i3 = i2 * 2;
                    entryArr[i2] = new AbstractMap.SimpleImmutableEntry(objArr[i3], objArr[i3 + 1]);
                    i2++;
                }
                Arrays.sort(entryArr, 0, i, Ordering.from(this.a).onResultOf(Maps.b()));
                for (int i4 = 0; i4 < this.c; i4++) {
                    int i5 = i4 * 2;
                    this.b[i5] = entryArr[i4].getKey();
                    this.b[i5 + 1] = entryArr[i4].getValue();
                }
            }
        }
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if ((map instanceof ImmutableMap) && !(map instanceof SortedMap)) {
            ImmutableMap<K, V> immutableMap = (ImmutableMap) map;
            if (!immutableMap.isPartialView()) {
                return immutableMap;
            }
        }
        return copyOf(map.entrySet());
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        a aVar = new a(iterable instanceof Collection ? ((Collection) iterable).size() : 4);
        aVar.b(iterable);
        return aVar.b();
    }

    /* access modifiers changed from: package-private */
    public static abstract class IteratorBasedImmutableMap<K, V> extends ImmutableMap<K, V> {
        /* access modifiers changed from: package-private */
        public abstract bf<Map.Entry<K, V>> entryIterator();

        IteratorBasedImmutableMap() {
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public /* bridge */ /* synthetic */ Set entrySet() {
            return ImmutableMap.super.entrySet();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public /* bridge */ /* synthetic */ Set keySet() {
            return ImmutableMap.super.keySet();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map, com.google.common.collect.k
        public /* bridge */ /* synthetic */ Collection values() {
            return ImmutableMap.super.values();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public ImmutableSet<K> createKeySet() {
            return new ImmutableMapKeySet(this);
        }

        /* renamed from: com.google.common.collect.ImmutableMap$IteratorBasedImmutableMap$1EntrySetImpl  reason: invalid class name */
        class AnonymousClass1EntrySetImpl extends ImmutableMapEntrySet<K, V> {
            AnonymousClass1EntrySetImpl() {
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableMapEntrySet
            public ImmutableMap<K, V> map() {
                return IteratorBasedImmutableMap.this;
            }

            @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet
            public bf<Map.Entry<K, V>> iterator() {
                return IteratorBasedImmutableMap.this.entryIterator();
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
            return new AnonymousClass1EntrySetImpl();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public ImmutableCollection<V> createValues() {
            return new ImmutableMapValues(this);
        }
    }

    ImmutableMap() {
    }

    @Override // java.util.Map
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    @Override // java.util.Map
    public final V getOrDefault(Object obj, V v) {
        V v2 = (V) get(obj);
        return v2 != null ? v2 : v;
    }

    @Override // java.util.Map
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<Map.Entry<K, V>> createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    @Override // java.util.Map
    public ImmutableSet<K> keySet() {
        ImmutableSet<K> immutableSet = this.keySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<K> createKeySet = createKeySet();
        this.keySet = createKeySet;
        return createKeySet;
    }

    /* renamed from: com.google.common.collect.ImmutableMap$1  reason: invalid class name */
    class AnonymousClass1 extends bf<K> {
        final /* synthetic */ bf a;

        AnonymousClass1(bf bfVar) {
            this.a = bfVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.a.hasNext();
        }

        @Override // java.util.Iterator
        public K next() {
            return (K) this.a.next().getKey();
        }
    }

    /* access modifiers changed from: package-private */
    public bf<K> keyIterator() {
        return new AnonymousClass1(entrySet().iterator());
    }

    @Override // java.util.Map, com.google.common.collect.k
    public ImmutableCollection<V> values() {
        ImmutableCollection<V> immutableCollection = this.values;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        ImmutableCollection<V> createValues = createValues();
        this.values = createValues;
        return createValues;
    }

    public ImmutableSetMultimap<K, V> asMultimap() {
        if (isEmpty()) {
            return ImmutableSetMultimap.of();
        }
        ImmutableSetMultimap<K, V> immutableSetMultimap = this.multimapView;
        if (immutableSetMultimap != null) {
            return immutableSetMultimap;
        }
        ImmutableSetMultimap<K, V> immutableSetMultimap2 = new ImmutableSetMultimap<>(new MapViewOfValuesAsSingletonSets(this, null), size(), null);
        this.multimapView = immutableSetMultimap2;
        return immutableSetMultimap2;
    }

    private final class MapViewOfValuesAsSingletonSets extends IteratorBasedImmutableMap<K, ImmutableSet<V>> {
        private MapViewOfValuesAsSingletonSets() {
        }

        /* synthetic */ MapViewOfValuesAsSingletonSets(ImmutableMap immutableMap, AnonymousClass1 r2) {
            this();
        }

        @Override // java.util.Map
        public int size() {
            return ImmutableMap.this.size();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        public ImmutableSet<K> createKeySet() {
            return ImmutableMap.this.keySet();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public boolean containsKey(Object obj) {
            return ImmutableMap.this.containsKey(obj);
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public ImmutableSet<V> get(Object obj) {
            Object obj2 = ImmutableMap.this.get(obj);
            if (obj2 == null) {
                return null;
            }
            return ImmutableSet.of(obj2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return ImmutableMap.this.isPartialView();
        }

        @Override // com.google.common.collect.ImmutableMap, java.lang.Object, java.util.Map
        public int hashCode() {
            return ImmutableMap.this.hashCode();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap
        public boolean isHashCodeFast() {
            return ImmutableMap.this.isHashCodeFast();
        }

        /* renamed from: com.google.common.collect.ImmutableMap$MapViewOfValuesAsSingletonSets$1  reason: invalid class name */
        class AnonymousClass1 extends bf<Map.Entry<K, ImmutableSet<V>>> {
            final /* synthetic */ Iterator a;

            AnonymousClass1(Iterator it2) {
                this.a = it2;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.a.hasNext();
            }

            /* access modifiers changed from: package-private */
            /* renamed from: com.google.common.collect.ImmutableMap$MapViewOfValuesAsSingletonSets$1$1  reason: invalid class name */
            public class AnonymousClass1 extends b<K, ImmutableSet<V>> {
                final /* synthetic */ Map.Entry a;

                AnonymousClass1(Map.Entry entry) {
                    this.a = entry;
                }

                @Override // com.google.common.collect.b, java.util.Map.Entry
                public K getKey() {
                    return (K) this.a.getKey();
                }

                /* renamed from: a */
                public ImmutableSet<V> getValue() {
                    return ImmutableSet.of(this.a.getValue());
                }
            }

            /* renamed from: a */
            public Map.Entry<K, ImmutableSet<V>> next() {
                return new AnonymousClass1((Map.Entry) this.a.next());
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap
        public bf<Map.Entry<K, ImmutableSet<V>>> entryIterator() {
            return new AnonymousClass1(ImmutableMap.this.entrySet().iterator());
        }
    }

    @Override // java.lang.Object, java.util.Map
    public boolean equals(Object obj) {
        return Maps.f(this, obj);
    }

    @Override // java.lang.Object, java.util.Map
    public int hashCode() {
        return Sets.a(entrySet());
    }

    @Override // java.lang.Object
    public String toString() {
        return Maps.a(this);
    }

    static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object[] keys;
        private final Object[] values;

        SerializedForm(ImmutableMap<?, ?> immutableMap) {
            this.keys = new Object[immutableMap.size()];
            this.values = new Object[immutableMap.size()];
            bf<Map.Entry<?, ?>> it2 = immutableMap.entrySet().iterator();
            int i = 0;
            while (it2.hasNext()) {
                Map.Entry<?, ?> next = it2.next();
                this.keys[i] = next.getKey();
                this.values[i] = next.getValue();
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return createMap(new a<>(this.keys.length));
        }

        /* access modifiers changed from: package-private */
        public Object createMap(a<Object, Object> aVar) {
            int i = 0;
            while (true) {
                Object[] objArr = this.keys;
                if (i >= objArr.length) {
                    return aVar.b();
                }
                aVar.b(objArr[i], this.values[i]);
                i++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
