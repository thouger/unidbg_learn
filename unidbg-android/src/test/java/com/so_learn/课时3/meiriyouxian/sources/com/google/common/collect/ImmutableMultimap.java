package com.google.common.collect;

import com.android.internal.app.DumpHeapActivity;
import com.google.common.collect.aj;
import com.google.common.collect.as;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class ImmutableMultimap<K, V> extends j<K, V> implements Serializable {
    private static final long serialVersionUID = 0;
    final transient ImmutableMap<K, ? extends ImmutableCollection<V>> map;
    final transient int size;

    static class b {
        static final as.a<ImmutableMultimap> a = as.a(ImmutableMultimap.class, "map");
        static final as.a<ImmutableMultimap> b = as.a(ImmutableMultimap.class, DumpHeapActivity.KEY_SIZE);
    }

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    public abstract ImmutableCollection<V> get(K k);

    public abstract ImmutableMultimap<V, K> inverse();

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ boolean containsEntry(Object obj, Object obj2) {
        return super.containsEntry(obj, obj2);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai, java.lang.Object
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai, java.lang.Object
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.c, java.lang.Object
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public static <K, V> ImmutableMultimap<K, V> of() {
        return ImmutableListMultimap.of();
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k, V v) {
        return ImmutableListMultimap.of((Object) k, (Object) v);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k, V v, K k2, V v2) {
        return ImmutableListMultimap.of((Object) k, (Object) v, (Object) k2, (Object) v2);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3) {
        return ImmutableListMultimap.of((Object) k, (Object) v, (Object) k2, (Object) v2, (Object) k3, (Object) v3);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        return ImmutableListMultimap.of((Object) k, (Object) v, (Object) k2, (Object) v2, (Object) k3, (Object) v3, (Object) k4, (Object) v4);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return ImmutableListMultimap.of((Object) k, (Object) v, (Object) k2, (Object) v2, (Object) k3, (Object) v3, (Object) k4, (Object) v4, (Object) k5, (Object) v5);
    }

    public static <K, V> a<K, V> builder() {
        return new a<>();
    }

    public static class a<K, V> {
        Map<K, Collection<V>> a = ao.a();
        Comparator<? super K> b;
        Comparator<? super V> c;

        /* access modifiers changed from: package-private */
        public Collection<V> c() {
            return new ArrayList();
        }

        public a<K, V> b(K k, V v) {
            n.a(k, v);
            Collection<V> collection = this.a.get(k);
            if (collection == null) {
                Map<K, Collection<V>> map = this.a;
                Collection<V> c = c();
                map.put(k, c);
                collection = c;
            }
            collection.add(v);
            return this;
        }

        public a<K, V> b(Map.Entry<? extends K, ? extends V> entry) {
            return b(entry.getKey(), entry.getValue());
        }

        public a<K, V> b(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            Iterator<? extends Map.Entry<? extends K, ? extends V>> it2 = iterable.iterator();
            while (it2.hasNext()) {
                b((Map.Entry) it2.next());
            }
            return this;
        }

        public ImmutableMultimap<K, V> b() {
            Collection entrySet = this.a.entrySet();
            Comparator<? super K> comparator = this.b;
            if (comparator != null) {
                entrySet = Ordering.from(comparator).onKeys().immutableSortedCopy(entrySet);
            }
            return ImmutableListMultimap.fromMapEntries(entrySet, this.c);
        }
    }

    public static <K, V> ImmutableMultimap<K, V> copyOf(ai<? extends K, ? extends V> aiVar) {
        if (aiVar instanceof ImmutableMultimap) {
            ImmutableMultimap<K, V> immutableMultimap = (ImmutableMultimap) aiVar;
            if (!immutableMultimap.isPartialView()) {
                return immutableMultimap;
            }
        }
        return ImmutableListMultimap.copyOf((ai) aiVar);
    }

    public static <K, V> ImmutableMultimap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        return ImmutableListMultimap.copyOf((Iterable) iterable);
    }

    ImmutableMultimap(ImmutableMap<K, ? extends ImmutableCollection<V>> immutableMap, int i) {
        this.map = immutableMap;
        this.size = i;
    }

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    @Deprecated
    public ImmutableCollection<V> removeAll(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai, com.google.common.collect.ah
    @Deprecated
    public ImmutableCollection<V> replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ai
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    @Deprecated
    public boolean put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    @Deprecated
    public boolean putAll(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    @Deprecated
    public boolean putAll(ai<? extends K, ? extends V> aiVar) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    @Deprecated
    public boolean remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return this.map.isPartialView();
    }

    @Override // com.google.common.collect.ai
    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public boolean containsValue(Object obj) {
        return obj != null && super.containsValue(obj);
    }

    @Override // com.google.common.collect.ai
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public ImmutableSet<K> keySet() {
        return this.map.keySet();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public Set<K> createKeySet() {
        throw new AssertionError("unreachable");
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: com.google.common.collect.ImmutableMap<K, ? extends com.google.common.collect.ImmutableCollection<V>>, com.google.common.collect.ImmutableMap<K, java.util.Collection<V>> */
    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public ImmutableMap<K, Collection<V>> asMap() {
        return (ImmutableMap<K, ? extends ImmutableCollection<V>>) this.map;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public Map<K, Collection<V>> createAsMap() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public ImmutableCollection<Map.Entry<K, V>> entries() {
        return (ImmutableCollection) super.entries();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public ImmutableCollection<Map.Entry<K, V>> createEntries() {
        return new EntryCollection(this);
    }

    /* access modifiers changed from: private */
    public static class EntryCollection<K, V> extends ImmutableCollection<Map.Entry<K, V>> {
        private static final long serialVersionUID = 0;
        final ImmutableMultimap<K, V> multimap;

        EntryCollection(ImmutableMultimap<K, V> immutableMultimap) {
            this.multimap = immutableMultimap;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet
        public bf<Map.Entry<K, V>> iterator() {
            return this.multimap.entryIterator();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return this.multimap.isPartialView();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.multimap.size();
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return this.multimap.containsEntry(entry.getKey(), entry.getValue());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.ImmutableMultimap$1  reason: invalid class name */
    public class AnonymousClass1 extends bf<Map.Entry<K, V>> {
        final Iterator<? extends Map.Entry<K, ? extends ImmutableCollection<V>>> a = ImmutableMultimap.this.map.entrySet().iterator();
        K b = null;
        Iterator<V> c = Iterators.a();

        AnonymousClass1() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.c.hasNext() || this.a.hasNext();
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            if (!this.c.hasNext()) {
                Map.Entry entry = (Map.Entry) this.a.next();
                this.b = (K) entry.getKey();
                this.c = ((ImmutableCollection) entry.getValue()).iterator();
            }
            return Maps.a((Object) this.b, (Object) this.c.next());
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public bf<Map.Entry<K, V>> entryIterator() {
        return new AnonymousClass1();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public ImmutableMultiset<K> keys() {
        return (ImmutableMultiset) super.keys();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public ImmutableMultiset<K> createKeys() {
        return new Keys();
    }

    /* access modifiers changed from: package-private */
    public class Keys extends ImmutableMultiset<K> {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        Keys() {
        }

        @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return ImmutableMultimap.this.containsKey(obj);
        }

        @Override // com.google.common.collect.aj
        public int count(Object obj) {
            Collection collection = (Collection) ImmutableMultimap.this.map.get(obj);
            if (collection == null) {
                return 0;
            }
            return collection.size();
        }

        @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.aj
        public ImmutableSet<K> elementSet() {
            return ImmutableMultimap.this.keySet();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
        public int size() {
            return ImmutableMultimap.this.size();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMultiset
        public aj.a<K> getEntry(int i) {
            Map.Entry<K, ? extends ImmutableCollection<V>> entry = ImmutableMultimap.this.map.entrySet().asList().get(i);
            return Multisets.a(entry.getKey(), ((Collection) entry.getValue()).size());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return new KeysSerializedForm(ImmutableMultimap.this);
        }
    }

    private static final class KeysSerializedForm implements Serializable {
        final ImmutableMultimap<?, ?> multimap;

        KeysSerializedForm(ImmutableMultimap<?, ?> immutableMultimap) {
            this.multimap = immutableMultimap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.multimap.keys();
        }
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public ImmutableCollection<V> values() {
        return (ImmutableCollection) super.values();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public ImmutableCollection<V> createValues() {
        return new Values(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.ImmutableMultimap$2  reason: invalid class name */
    public class AnonymousClass2 extends bf<V> {
        Iterator<? extends ImmutableCollection<V>> a = ImmutableMultimap.this.map.values().iterator();
        Iterator<V> b = Iterators.a();

        AnonymousClass2() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.b.hasNext() || this.a.hasNext();
        }

        @Override // java.util.Iterator
        public V next() {
            if (!this.b.hasNext()) {
                this.b = ((ImmutableCollection) this.a.next()).iterator();
            }
            return this.b.next();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public bf<V> valueIterator() {
        return new AnonymousClass2();
    }

    /* access modifiers changed from: private */
    public static final class Values<K, V> extends ImmutableCollection<V> {
        private static final long serialVersionUID = 0;
        private final transient ImmutableMultimap<K, V> multimap;

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        Values(ImmutableMultimap<K, V> immutableMultimap) {
            this.multimap = immutableMultimap;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.multimap.containsValue(obj);
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet
        public bf<V> iterator() {
            return this.multimap.valueIterator();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public int copyIntoArray(Object[] objArr, int i) {
            bf<? extends ImmutableCollection<V>> it2 = this.multimap.map.values().iterator();
            while (it2.hasNext()) {
                i = it2.next().copyIntoArray(objArr, i);
            }
            return i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.multimap.size();
        }
    }
}
