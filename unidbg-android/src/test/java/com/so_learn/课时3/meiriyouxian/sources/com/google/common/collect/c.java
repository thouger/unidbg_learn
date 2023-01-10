package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.Multimaps;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: AbstractMultimap */
/* access modifiers changed from: package-private */
public abstract class c<K, V> implements ai<K, V> {
    private transient Map<K, Collection<V>> asMap;
    private transient Collection<Map.Entry<K, V>> entries;
    private transient Set<K> keySet;
    private transient aj<K> keys;
    private transient Collection<V> values;

    /* access modifiers changed from: package-private */
    public abstract Map<K, Collection<V>> createAsMap();

    /* access modifiers changed from: package-private */
    public abstract Collection<Map.Entry<K, V>> createEntries();

    /* access modifiers changed from: package-private */
    public abstract Set<K> createKeySet();

    /* access modifiers changed from: package-private */
    public abstract aj<K> createKeys();

    /* access modifiers changed from: package-private */
    public abstract Collection<V> createValues();

    /* access modifiers changed from: package-private */
    public abstract Iterator<Map.Entry<K, V>> entryIterator();

    c() {
    }

    @Override // com.google.common.collect.ai
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // com.google.common.collect.ai
    public boolean containsValue(Object obj) {
        for (Collection<V> collection : asMap().values()) {
            if (collection.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.common.collect.ai
    public boolean containsEntry(Object obj, Object obj2) {
        Collection<V> collection = asMap().get(obj);
        return collection != null && collection.contains(obj2);
    }

    @Override // com.google.common.collect.ai
    public boolean remove(Object obj, Object obj2) {
        Collection<V> collection = asMap().get(obj);
        return collection != null && collection.remove(obj2);
    }

    @Override // com.google.common.collect.ai
    public boolean put(K k, V v) {
        return get(k).add(v);
    }

    @Override // com.google.common.collect.ai
    public boolean putAll(K k, Iterable<? extends V> iterable) {
        m.a(iterable);
        if (iterable instanceof Collection) {
            Collection<? extends V> collection = (Collection) iterable;
            return !collection.isEmpty() && get(k).addAll(collection);
        }
        Iterator<? extends V> it2 = iterable.iterator();
        return it2.hasNext() && Iterators.a(get(k), it2);
    }

    @Override // com.google.common.collect.ai
    public boolean putAll(ai<? extends K, ? extends V> aiVar) {
        boolean z = false;
        for (Map.Entry<? extends K, ? extends V> entry : aiVar.entries()) {
            z |= put(entry.getKey(), entry.getValue());
        }
        return z;
    }

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    public Collection<V> replaceValues(K k, Iterable<? extends V> iterable) {
        m.a(iterable);
        Collection<V> removeAll = removeAll(k);
        putAll(k, iterable);
        return removeAll;
    }

    @Override // com.google.common.collect.ai
    public Collection<Map.Entry<K, V>> entries() {
        Collection<Map.Entry<K, V>> collection = this.entries;
        if (collection != null) {
            return collection;
        }
        Collection<Map.Entry<K, V>> createEntries = createEntries();
        this.entries = createEntries;
        return createEntries;
    }

    /* compiled from: AbstractMultimap */
    class a extends Multimaps.b<K, V> {
        a() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Multimaps.b
        public ai<K, V> a() {
            return c.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return c.this.entryIterator();
        }
    }

    /* compiled from: AbstractMultimap */
    class b extends c<K, V>.a implements Set<Map.Entry<K, V>> {
        b() {
            super();
        }

        @Override // java.util.Collection, java.lang.Object, java.util.Set
        public int hashCode() {
            return Sets.a(this);
        }

        @Override // java.util.Collection, java.lang.Object, java.util.Set
        public boolean equals(Object obj) {
            return Sets.a(this, obj);
        }
    }

    @Override // com.google.common.collect.ai
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        Set<K> createKeySet = createKeySet();
        this.keySet = createKeySet;
        return createKeySet;
    }

    @Override // com.google.common.collect.ai
    public aj<K> keys() {
        aj<K> ajVar = this.keys;
        if (ajVar != null) {
            return ajVar;
        }
        aj<K> createKeys = createKeys();
        this.keys = createKeys;
        return createKeys;
    }

    @Override // com.google.common.collect.ai
    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Collection<V> createValues = createValues();
        this.values = createValues;
        return createValues;
    }

    /* compiled from: AbstractMultimap */
    /* renamed from: com.google.common.collect.c$c  reason: collision with other inner class name */
    class C0108c extends AbstractCollection<V> {
        C0108c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return c.this.valueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return c.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return c.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            c.this.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> valueIterator() {
        return Maps.b(entries().iterator());
    }

    @Override // com.google.common.collect.ai
    public Map<K, Collection<V>> asMap() {
        Map<K, Collection<V>> map = this.asMap;
        if (map != null) {
            return map;
        }
        Map<K, Collection<V>> createAsMap = createAsMap();
        this.asMap = createAsMap;
        return createAsMap;
    }

    @Override // com.google.common.collect.ai, java.lang.Object
    public boolean equals(Object obj) {
        return Multimaps.a(this, obj);
    }

    @Override // com.google.common.collect.ai, java.lang.Object
    public int hashCode() {
        return asMap().hashCode();
    }

    @Override // java.lang.Object
    public String toString() {
        return asMap().toString();
    }
}
