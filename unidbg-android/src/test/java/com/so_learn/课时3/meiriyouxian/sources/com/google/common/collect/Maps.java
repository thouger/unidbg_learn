package com.google.common.collect;

import com.google.common.base.Converter;
import com.google.common.base.Predicates;
import com.google.common.base.n;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public final class Maps {

    public interface c<K, V1, V2> {
        V2 a(K k, V1 v1);
    }

    /* access modifiers changed from: private */
    public enum EntryFunction implements com.google.common.base.g<Map.Entry<?, ?>, Object> {
        KEY {
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getKey();
            }
        },
        VALUE {
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getValue();
            }
        };

        /* synthetic */ EntryFunction(AnonymousClass1 r3) {
            this();
        }
    }

    static <K> com.google.common.base.g<Map.Entry<K, ?>, K> a() {
        return EntryFunction.KEY;
    }

    static <V> com.google.common.base.g<Map.Entry<?, V>, V> b() {
        return EntryFunction.VALUE;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.Maps$1  reason: invalid class name */
    public static class AnonymousClass1 extends bc<Map.Entry<K, V>, K> {
        AnonymousClass1(Iterator it2) {
            super(it2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.bc
        public /* bridge */ /* synthetic */ Object a(Object obj) {
            return a((Map.Entry<Object, V>) ((Map.Entry) obj));
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, K] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public K a(java.util.Map.Entry<K, V> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = r1.getKey()
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Maps.AnonymousClass1.a(java.util.Map$Entry):java.lang.Object");
        }
    }

    static <K, V> Iterator<K> a(Iterator<Map.Entry<K, V>> it2) {
        return new AnonymousClass1(it2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.Maps$4  reason: invalid class name */
    public static class AnonymousClass4 extends bc<Map.Entry<K, V>, V> {
        AnonymousClass4(Iterator it2) {
            super(it2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.bc
        public /* bridge */ /* synthetic */ Object a(Object obj) {
            return a((Map.Entry<K, Object>) ((Map.Entry) obj));
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [V, java.lang.Object] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V a(java.util.Map.Entry<K, V> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = r1.getValue()
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Maps.AnonymousClass4.a(java.util.Map$Entry):java.lang.Object");
        }
    }

    static <K, V> Iterator<V> b(Iterator<Map.Entry<K, V>> it2) {
        return new AnonymousClass4(it2);
    }

    public static <K, V> HashMap<K, V> c() {
        return new HashMap<>();
    }

    public static <K, V> HashMap<K, V> a(int i2) {
        return new HashMap<>(b(i2));
    }

    static int b(int i2) {
        if (i2 < 3) {
            n.a(i2, "expectedSize");
            return i2 + 1;
        } else if (i2 < 1073741824) {
            return (int) ((((float) i2) / 0.75f) + 1.0f);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public static <K, V> LinkedHashMap<K, V> d() {
        return new LinkedHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> c(int i2) {
        return new LinkedHashMap<>(b(i2));
    }

    public static <K, V> IdentityHashMap<K, V> e() {
        return new IdentityHashMap<>();
    }

    /* renamed from: com.google.common.collect.Maps$5  reason: invalid class name */
    static class AnonymousClass5 extends bc<K, Map.Entry<K, V>> {
        final /* synthetic */ com.google.common.base.g a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass5(Iterator it2, com.google.common.base.g gVar) {
            super(it2);
            this.a = gVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Map.Entry<K, V> a(K k) {
            return Maps.a((Object) k, this.a.apply(k));
        }
    }

    static <K, V> Iterator<Map.Entry<K, V>> a(Set<K> set, com.google.common.base.g<? super K, V> gVar) {
        return new AnonymousClass5(set.iterator(), gVar);
    }

    public static <K, V> Map.Entry<K, V> a(K k2, V v) {
        return new ImmutableEntry(k2, v);
    }

    static <K, V> Set<Map.Entry<K, V>> a(Set<Map.Entry<K, V>> set) {
        return new k(Collections.unmodifiableSet(set));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.Maps$6  reason: invalid class name */
    public static class AnonymousClass6 extends b<K, V> {
        final /* synthetic */ Map.Entry a;

        AnonymousClass6(Map.Entry entry) {
            this.a = entry;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, K] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.common.collect.b, java.util.Map.Entry
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public K getKey() {
            /*
                r1 = this;
                java.util.Map$Entry r0 = r1.a
                java.lang.Object r0 = r0.getKey()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Maps.AnonymousClass6.getKey():java.lang.Object");
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [V, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.common.collect.b, java.util.Map.Entry
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V getValue() {
            /*
                r1 = this;
                java.util.Map$Entry r0 = r1.a
                java.lang.Object r0 = r0.getValue()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Maps.AnonymousClass6.getValue():java.lang.Object");
        }
    }

    static <K, V> Map.Entry<K, V> a(Map.Entry<? extends K, ? extends V> entry) {
        com.google.common.base.m.a(entry);
        return new AnonymousClass6(entry);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.Maps$7  reason: invalid class name */
    public static class AnonymousClass7 extends bf<Map.Entry<K, V>> {
        final /* synthetic */ Iterator a;

        AnonymousClass7(Iterator it2) {
            this.a = it2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.a.hasNext();
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            return Maps.a((Map.Entry) this.a.next());
        }
    }

    static <K, V> bf<Map.Entry<K, V>> c(Iterator<Map.Entry<K, V>> it2) {
        return new AnonymousClass7(it2);
    }

    static class j<K, V> extends s<Map.Entry<K, V>> {
        private final Collection<Map.Entry<K, V>> a;

        j(Collection<Map.Entry<K, V>> collection) {
            this.a = collection;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.s, com.google.common.collect.z
        public Collection<Map.Entry<K, V>> delegate() {
            return this.a;
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return Maps.c(this.a.iterator());
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }
    }

    static class k<K, V> extends j<K, V> implements Set<Map.Entry<K, V>> {
        k(Set<Map.Entry<K, V>> set) {
            super(set);
        }

        @Override // java.util.Collection, java.lang.Object, java.util.Set
        public boolean equals(Object obj) {
            return Sets.a(this, obj);
        }

        @Override // java.util.Collection, java.lang.Object, java.util.Set
        public int hashCode() {
            return Sets.a(this);
        }
    }

    private static final class BiMapConverter<A, B> extends Converter<A, B> implements Serializable {
        private static final long serialVersionUID = 0;
        private final k<A, B> bimap;

        BiMapConverter(k<A, B> kVar) {
            this.bimap = (k) com.google.common.base.m.a(kVar);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public B doForward(A a) {
            return (B) convert(this.bimap, a);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public A doBackward(B b) {
            return (A) convert(this.bimap.inverse(), b);
        }

        private static <X, Y> Y convert(k<X, Y> kVar, X x) {
            Y y = (Y) kVar.get(x);
            com.google.common.base.m.a(y != null, "No non-null mapping present for input: %s", x);
            return y;
        }

        @Override // com.google.common.base.Converter, com.google.common.base.g, java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof BiMapConverter) {
                return this.bimap.equals(((BiMapConverter) obj).bimap);
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.bimap.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return "Maps.asConverter(" + this.bimap + com.umeng.message.proguard.l.t;
        }
    }

    private static class UnmodifiableBiMap<K, V> extends v<K, V> implements k<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final k<? extends K, ? extends V> delegate;
        k<V, K> inverse;
        final Map<K, V> unmodifiableMap;
        transient Set<V> values;

        UnmodifiableBiMap(k<? extends K, ? extends V> kVar, k<V, K> kVar2) {
            this.unmodifiableMap = Collections.unmodifiableMap(kVar);
            this.delegate = kVar;
            this.inverse = kVar2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.v, com.google.common.collect.z
        public Map<K, V> delegate() {
            return this.unmodifiableMap;
        }

        @Override // com.google.common.collect.k
        public V forcePut(K k, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.k
        public k<V, K> inverse() {
            k<V, K> kVar = this.inverse;
            if (kVar != null) {
                return kVar;
            }
            UnmodifiableBiMap unmodifiableBiMap = new UnmodifiableBiMap(this.delegate.inverse(), this);
            this.inverse = unmodifiableBiMap;
            return unmodifiableBiMap;
        }

        @Override // com.google.common.collect.v, java.util.Map, com.google.common.collect.k
        public Set<V> values() {
            Set<V> set = this.values;
            if (set != null) {
                return set;
            }
            Set<V> unmodifiableSet = Collections.unmodifiableSet(this.delegate.values());
            this.values = unmodifiableSet;
            return unmodifiableSet;
        }
    }

    public static <K, V1, V2> Map<K, V2> a(Map<K, V1> map, com.google.common.base.g<? super V1, V2> gVar) {
        return a((Map) map, a(gVar));
    }

    public static <K, V1, V2> SortedMap<K, V2> a(SortedMap<K, V1> sortedMap, com.google.common.base.g<? super V1, V2> gVar) {
        return a((SortedMap) sortedMap, a(gVar));
    }

    public static <K, V1, V2> Map<K, V2> a(Map<K, V1> map, c<? super K, ? super V1, V2> cVar) {
        return new h(map, cVar);
    }

    public static <K, V1, V2> SortedMap<K, V2> a(SortedMap<K, V1> sortedMap, c<? super K, ? super V1, V2> cVar) {
        return new i(sortedMap, cVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.Maps$8  reason: invalid class name */
    public static class AnonymousClass8 implements c<K, V1, V2> {
        final /* synthetic */ com.google.common.base.g a;

        AnonymousClass8(com.google.common.base.g gVar) {
            this.a = gVar;
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, V2] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.common.collect.Maps.c
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V2 a(K r1, V1 r2) {
            /*
                r0 = this;
                com.google.common.base.g r1 = r0.a
                java.lang.Object r1 = r1.apply(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Maps.AnonymousClass8.a(java.lang.Object, java.lang.Object):java.lang.Object");
        }
    }

    static <K, V1, V2> c<K, V1, V2> a(com.google.common.base.g<? super V1, V2> gVar) {
        com.google.common.base.m.a(gVar);
        return new AnonymousClass8(gVar);
    }

    static <V2, K, V1> Map.Entry<K, V2> a(c<? super K, ? super V1, V2> cVar, Map.Entry<K, V1> entry) {
        com.google.common.base.m.a(cVar);
        com.google.common.base.m.a(entry);
        return new AnonymousClass2(entry, cVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.Maps$2  reason: invalid class name */
    public static class AnonymousClass2 extends b<K, V2> {
        final /* synthetic */ Map.Entry a;
        final /* synthetic */ c b;

        AnonymousClass2(Map.Entry entry, c cVar) {
            this.a = entry;
            this.b = cVar;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, K] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.common.collect.b, java.util.Map.Entry
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public K getKey() {
            /*
                r1 = this;
                java.util.Map$Entry r0 = r1.a
                java.lang.Object r0 = r0.getKey()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Maps.AnonymousClass2.getKey():java.lang.Object");
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, V2] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.common.collect.b, java.util.Map.Entry
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V2 getValue() {
            /*
                r3 = this;
                com.google.common.collect.Maps$c r0 = r3.b
                java.util.Map$Entry r1 = r3.a
                java.lang.Object r1 = r1.getKey()
                java.util.Map$Entry r2 = r3.a
                java.lang.Object r2 = r2.getValue()
                java.lang.Object r0 = r0.a(r1, r2)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Maps.AnonymousClass2.getValue():java.lang.Object");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.Maps$3  reason: invalid class name */
    public static class AnonymousClass3 implements com.google.common.base.g<Map.Entry<K, V1>, Map.Entry<K, V2>> {
        final /* synthetic */ c a;

        AnonymousClass3(c cVar) {
            this.a = cVar;
        }

        /* renamed from: a */
        public Map.Entry<K, V2> apply(Map.Entry<K, V1> entry) {
            return Maps.a(this.a, (Map.Entry) entry);
        }
    }

    static <K, V1, V2> com.google.common.base.g<Map.Entry<K, V1>, Map.Entry<K, V2>> a(c<? super K, ? super V1, V2> cVar) {
        com.google.common.base.m.a(cVar);
        return new AnonymousClass3(cVar);
    }

    /* access modifiers changed from: package-private */
    public static class h<K, V1, V2> extends d<K, V2> {
        final Map<K, V1> a;
        final c<? super K, ? super V1, V2> b;

        h(Map<K, V1> map, c<? super K, ? super V1, V2> cVar) {
            this.a = (Map) com.google.common.base.m.a(map);
            this.b = (c) com.google.common.base.m.a(cVar);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.a.size();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.a.containsKey(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V2 get(Object obj) {
            V1 v1 = this.a.get(obj);
            if (v1 != null || this.a.containsKey(obj)) {
                return (V2) this.b.a(obj, v1);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V2 remove(Object obj) {
            if (this.a.containsKey(obj)) {
                return (V2) this.b.a(obj, this.a.remove(obj));
            }
            return null;
        }

        @Override // com.google.common.collect.Maps.d, java.util.AbstractMap, java.util.Map
        public void clear() {
            this.a.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.a.keySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.d
        public Iterator<Map.Entry<K, V2>> b() {
            return Iterators.a((Iterator) this.a.entrySet().iterator(), Maps.a(this.b));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V2> values() {
            return new l(this);
        }
    }

    /* access modifiers changed from: package-private */
    public static class i<K, V1, V2> extends h<K, V1, V2> implements SortedMap<K, V2> {
        /* access modifiers changed from: protected */
        public SortedMap<K, V1> a() {
            return (SortedMap) this.a;
        }

        i(SortedMap<K, V1> sortedMap, c<? super K, ? super V1, V2> cVar) {
            super(sortedMap, cVar);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return a().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return a().firstKey();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V2> headMap(K k) {
            return Maps.a((SortedMap) a().headMap(k), this.b);
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return a().lastKey();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V2> subMap(K k, K k2) {
            return Maps.a((SortedMap) a().subMap(k, k2), this.b);
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V2> tailMap(K k) {
            return Maps.a((SortedMap) a().tailMap(k), this.b);
        }
    }

    static <K> n<Map.Entry<K, ?>> a(n<? super K> nVar) {
        return Predicates.a(nVar, a());
    }

    static <V> n<Map.Entry<?, V>> b(n<? super V> nVar) {
        return Predicates.a(nVar, b());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.NavigableMap<K, ? extends V> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> NavigableMap<K, V> a(NavigableMap<K, ? extends V> navigableMap) {
        com.google.common.base.m.a(navigableMap);
        if (navigableMap instanceof UnmodifiableNavigableMap) {
            return navigableMap;
        }
        return new UnmodifiableNavigableMap(navigableMap);
    }

    /* access modifiers changed from: private */
    public static <K, V> Map.Entry<K, V> e(Map.Entry<K, ? extends V> entry) {
        if (entry == null) {
            return null;
        }
        return a(entry);
    }

    /* access modifiers changed from: package-private */
    public static class UnmodifiableNavigableMap<K, V> extends ac<K, V> implements Serializable, NavigableMap<K, V> {
        private final NavigableMap<K, ? extends V> delegate;
        private transient UnmodifiableNavigableMap<K, V> descendingMap;

        UnmodifiableNavigableMap(NavigableMap<K, ? extends V> navigableMap) {
            this.delegate = navigableMap;
        }

        UnmodifiableNavigableMap(NavigableMap<K, ? extends V> navigableMap, UnmodifiableNavigableMap<K, V> unmodifiableNavigableMap) {
            this.delegate = navigableMap;
            this.descendingMap = unmodifiableNavigableMap;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ac, com.google.common.collect.v, com.google.common.collect.z
        public SortedMap<K, V> delegate() {
            return Collections.unmodifiableSortedMap(this.delegate);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K k) {
            return Maps.e(this.delegate.lowerEntry(k));
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            return this.delegate.lowerKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K k) {
            return Maps.e(this.delegate.floorEntry(k));
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            return this.delegate.floorKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K k) {
            return Maps.e(this.delegate.ceilingEntry(k));
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            return this.delegate.ceilingKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K k) {
            return Maps.e(this.delegate.higherEntry(k));
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            return this.delegate.higherKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            return Maps.e(this.delegate.firstEntry());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            return Maps.e(this.delegate.lastEntry());
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> pollFirstEntry() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> pollLastEntry() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            UnmodifiableNavigableMap<K, V> unmodifiableNavigableMap = this.descendingMap;
            if (unmodifiableNavigableMap != null) {
                return unmodifiableNavigableMap;
            }
            UnmodifiableNavigableMap<K, V> unmodifiableNavigableMap2 = new UnmodifiableNavigableMap<>(this.delegate.descendingMap(), this);
            this.descendingMap = unmodifiableNavigableMap2;
            return unmodifiableNavigableMap2;
        }

        @Override // com.google.common.collect.v, java.util.Map
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return Sets.a((NavigableSet) this.delegate.navigableKeySet());
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return Sets.a((NavigableSet) this.delegate.descendingKeySet());
        }

        @Override // com.google.common.collect.ac, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return Maps.a((NavigableMap) this.delegate.subMap(k, z, k2, z2));
        }

        @Override // com.google.common.collect.ac, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> headMap(K k) {
            return headMap(k, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k, boolean z) {
            return Maps.a((NavigableMap) this.delegate.headMap(k, z));
        }

        @Override // com.google.common.collect.ac, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> tailMap(K k) {
            return tailMap(k, true);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return Maps.a((NavigableMap) this.delegate.tailMap(k, z));
        }
    }

    static abstract class m<K, V> extends AbstractMap<K, V> {
        private transient Set<Map.Entry<K, V>> a;
        private transient Set<K> b;
        private transient Collection<V> c;

        /* access modifiers changed from: package-private */
        public abstract Set<Map.Entry<K, V>> a();

        m() {
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set = this.a;
            if (set != null) {
                return set;
            }
            Set<Map.Entry<K, V>> a = a();
            this.a = a;
            return a;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            Set<K> set = this.b;
            if (set != null) {
                return set;
            }
            Set<K> h = h();
            this.b = h;
            return h;
        }

        /* access modifiers changed from: package-private */
        public Set<K> h() {
            return new e(this);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> values() {
            Collection<V> collection = this.c;
            if (collection != null) {
                return collection;
            }
            Collection<V> i = i();
            this.c = i;
            return i;
        }

        /* access modifiers changed from: package-private */
        public Collection<V> i() {
            return new l(this);
        }
    }

    static abstract class d<K, V> extends AbstractMap<K, V> {
        /* access modifiers changed from: package-private */
        public abstract Iterator<Map.Entry<K, V>> b();

        d() {
        }

        /* renamed from: com.google.common.collect.Maps$d$1  reason: invalid class name */
        class AnonymousClass1 extends b<K, V> {
            AnonymousClass1() {
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.Maps.b
            public Map<K, V> a() {
                return d.this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<K, V>> iterator() {
                return d.this.b();
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            return new AnonymousClass1();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            Iterators.h(b());
        }
    }

    static <V> V a(Map<?, V> map, Object obj) {
        com.google.common.base.m.a(map);
        try {
            return map.get(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return null;
        }
    }

    static boolean b(Map<?, ?> map, Object obj) {
        com.google.common.base.m.a(map);
        try {
            return map.containsKey(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    static <V> V c(Map<?, V> map, Object obj) {
        com.google.common.base.m.a(map);
        try {
            return map.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return null;
        }
    }

    static boolean d(Map<?, ?> map, Object obj) {
        return Iterators.a(a(map.entrySet().iterator()), obj);
    }

    static boolean e(Map<?, ?> map, Object obj) {
        return Iterators.a(b(map.entrySet().iterator()), obj);
    }

    static <K, V> boolean a(Collection<Map.Entry<K, V>> collection, Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        return collection.contains(a((Map.Entry) obj));
    }

    static <K, V> boolean b(Collection<Map.Entry<K, V>> collection, Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        return collection.remove(a((Map.Entry) obj));
    }

    static boolean f(Map<?, ?> map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return map.entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    static String a(Map<?, ?> map) {
        StringBuilder a2 = o.a(map.size());
        a2.append('{');
        boolean z = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!z) {
                a2.append(", ");
            }
            z = false;
            a2.append(entry.getKey());
            a2.append('=');
            a2.append(entry.getValue());
        }
        a2.append('}');
        return a2.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Map<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    static <K, V> void a(Map<K, V> map, Map<? extends K, ? extends V> map2) {
        for (Map.Entry<? extends K, ? extends V> entry : map2.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
    }

    /* access modifiers changed from: package-private */
    public static class e<K, V> extends Sets.b<K> {
        final Map<K, V> d;

        e(Map<K, V> map) {
            this.d = (Map) com.google.common.base.m.a(map);
        }

        /* access modifiers changed from: package-private */
        public Map<K, V> c() {
            return this.d;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return Maps.a(c().entrySet().iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return c().size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return c().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return c().containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!contains(obj)) {
                return false;
            }
            c().remove(obj);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            c().clear();
        }
    }

    static <K> K b(Map.Entry<K, ?> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    static <V> V c(Map.Entry<?, V> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    static class g<K, V> extends e<K, V> implements SortedSet<K> {
        g(SortedMap<K, V> sortedMap) {
            super(sortedMap);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public SortedMap<K, V> c() {
            return (SortedMap) super.c();
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return c().comparator();
        }

        @Override // java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> subSet(K k, K k2) {
            return new g(c().subMap(k, k2));
        }

        @Override // java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> headSet(K k) {
            return new g(c().headMap(k));
        }

        @Override // java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> tailSet(K k) {
            return new g(c().tailMap(k));
        }

        @Override // java.util.SortedSet
        public K first() {
            return c().firstKey();
        }

        @Override // java.util.SortedSet
        public K last() {
            return c().lastKey();
        }
    }

    /* access modifiers changed from: package-private */
    public static class f<K, V> extends g<K, V> implements NavigableSet<K> {
        f(NavigableMap<K, V> navigableMap) {
            super(navigableMap);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public NavigableMap<K, V> c() {
            return (NavigableMap) this.d;
        }

        @Override // java.util.NavigableSet
        public K lower(K k) {
            return c().lowerKey(k);
        }

        @Override // java.util.NavigableSet
        public K floor(K k) {
            return c().floorKey(k);
        }

        @Override // java.util.NavigableSet
        public K ceiling(K k) {
            return c().ceilingKey(k);
        }

        @Override // java.util.NavigableSet
        public K higher(K k) {
            return c().higherKey(k);
        }

        @Override // java.util.NavigableSet
        public K pollFirst() {
            return (K) Maps.b(c().pollFirstEntry());
        }

        @Override // java.util.NavigableSet
        public K pollLast() {
            return (K) Maps.b(c().pollLastEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> descendingSet() {
            return c().descendingKeySet();
        }

        @Override // java.util.NavigableSet
        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> subSet(K k, boolean z, K k2, boolean z2) {
            return c().subMap(k, z, k2, z2).navigableKeySet();
        }

        @Override // com.google.common.collect.Maps.g, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> subSet(K k, K k2) {
            return subSet(k, true, k2, false);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> headSet(K k, boolean z) {
            return c().headMap(k, z).navigableKeySet();
        }

        @Override // com.google.common.collect.Maps.g, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> headSet(K k) {
            return headSet(k, false);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> tailSet(K k, boolean z) {
            return c().tailMap(k, z).navigableKeySet();
        }

        @Override // com.google.common.collect.Maps.g, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> tailSet(K k) {
            return tailSet(k, true);
        }
    }

    /* access modifiers changed from: package-private */
    public static class l<K, V> extends AbstractCollection<V> {
        final Map<K, V> a;

        l(Map<K, V> map) {
            this.a = (Map) com.google.common.base.m.a(map);
        }

        /* access modifiers changed from: package-private */
        public final Map<K, V> a() {
            return this.a;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return Maps.b(a().entrySet().iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            try {
                return super.remove(obj);
            } catch (UnsupportedOperationException unused) {
                for (Map.Entry<K, V> entry : a().entrySet()) {
                    if (com.google.common.base.j.a(obj, entry.getValue())) {
                        a().remove(entry.getKey());
                        return true;
                    }
                }
                return false;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            try {
                return super.removeAll((Collection) com.google.common.base.m.a(collection));
            } catch (UnsupportedOperationException unused) {
                HashSet a = Sets.a();
                for (Map.Entry<K, V> entry : a().entrySet()) {
                    if (collection.contains(entry.getValue())) {
                        a.add(entry.getKey());
                    }
                }
                return a().keySet().removeAll(a);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            try {
                return super.retainAll((Collection) com.google.common.base.m.a(collection));
            } catch (UnsupportedOperationException unused) {
                HashSet a = Sets.a();
                for (Map.Entry<K, V> entry : a().entrySet()) {
                    if (collection.contains(entry.getValue())) {
                        a.add(entry.getKey());
                    }
                }
                return a().keySet().retainAll(a);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return a().size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return a().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return a().containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            a().clear();
        }
    }

    static abstract class b<K, V> extends Sets.b<Map.Entry<K, V>> {
        /* access modifiers changed from: package-private */
        public abstract Map<K, V> a();

        b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return a().size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            a().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object a = Maps.a((Map<?, Object>) a(), key);
            if (!com.google.common.base.j.a(a, entry.getValue())) {
                return false;
            }
            if (a != null || a().containsKey(key)) {
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return a().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (contains(obj)) {
                return a().keySet().remove(((Map.Entry) obj).getKey());
            }
            return false;
        }

        @Override // com.google.common.collect.Sets.b, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            try {
                return super.removeAll((Collection) com.google.common.base.m.a(collection));
            } catch (UnsupportedOperationException unused) {
                return Sets.a((Set<?>) this, collection.iterator());
            }
        }

        @Override // com.google.common.collect.Sets.b, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            try {
                return super.retainAll((Collection) com.google.common.base.m.a(collection));
            } catch (UnsupportedOperationException unused) {
                HashSet a = Sets.a(collection.size());
                for (Object obj : collection) {
                    if (contains(obj)) {
                        a.add(((Map.Entry) obj).getKey());
                    }
                }
                return a().keySet().retainAll(a);
            }
        }
    }

    static abstract class a<K, V> extends v<K, V> implements NavigableMap<K, V> {
        private transient Comparator<? super K> a;
        private transient Set<Map.Entry<K, V>> b;
        private transient NavigableSet<K> c;

        /* access modifiers changed from: package-private */
        public abstract NavigableMap<K, V> a();

        /* access modifiers changed from: package-private */
        public abstract Iterator<Map.Entry<K, V>> b();

        a() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.v, com.google.common.collect.z
        public final Map<K, V> delegate() {
            return a();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator = this.a;
            if (comparator != null) {
                return comparator;
            }
            Comparator<? super K> comparator2 = a().comparator();
            if (comparator2 == null) {
                comparator2 = Ordering.natural();
            }
            Ordering a = a(comparator2);
            this.a = a;
            return a;
        }

        private static <T> Ordering<T> a(Comparator<T> comparator) {
            return Ordering.from(comparator).reverse();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return a().lastKey();
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return a().firstKey();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K k) {
            return a().higherEntry(k);
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            return a().higherKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K k) {
            return a().ceilingEntry(k);
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            return a().ceilingKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K k) {
            return a().floorEntry(k);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            return a().floorKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K k) {
            return a().lowerEntry(k);
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            return a().lowerKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            return a().lastEntry();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            return a().firstEntry();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            return a().pollLastEntry();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            return a().pollFirstEntry();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            return a();
        }

        @Override // com.google.common.collect.v, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set = this.b;
            if (set != null) {
                return set;
            }
            Set<Map.Entry<K, V>> c = c();
            this.b = c;
            return c;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.Maps$a$a  reason: collision with other inner class name */
        public class C0105a extends b<K, V> {
            C0105a() {
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.Maps.b
            public Map<K, V> a() {
                return a.this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<K, V>> iterator() {
                return a.this.b();
            }
        }

        /* access modifiers changed from: package-private */
        public Set<Map.Entry<K, V>> c() {
            return new C0105a();
        }

        @Override // com.google.common.collect.v, java.util.Map
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            NavigableSet<K> navigableSet = this.c;
            if (navigableSet != null) {
                return navigableSet;
            }
            f fVar = new f(this);
            this.c = fVar;
            return fVar;
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return a().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return a().subMap(k2, z2, k, z).descendingMap();
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k, boolean z) {
            return a().tailMap(k, z).descendingMap();
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> headMap(K k) {
            return headMap(k, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return a().headMap(k, z).descendingMap();
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> tailMap(K k) {
            return tailMap(k, true);
        }

        @Override // com.google.common.collect.v, java.util.Map, com.google.common.collect.k
        public Collection<V> values() {
            return new l(this);
        }

        @Override // com.google.common.collect.z, java.lang.Object
        public String toString() {
            return standardToString();
        }
    }

    static <E> ImmutableMap<E, Integer> a(Collection<E> collection) {
        ImmutableMap.a aVar = new ImmutableMap.a(collection.size());
        int i2 = 0;
        for (E e2 : collection) {
            aVar.b(e2, Integer.valueOf(i2));
            i2++;
        }
        return aVar.b();
    }
}
