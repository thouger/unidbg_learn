package com.google.common.collect;

import com.google.common.collect.ba;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: AbstractTable */
abstract class i<R, C, V> implements ba<R, C, V> {
    private transient Set<ba.a<R, C, V>> cellSet;
    private transient Collection<V> values;

    /* access modifiers changed from: package-private */
    public abstract Iterator<ba.a<R, C, V>> cellIterator();

    i() {
    }

    @Override // com.google.common.collect.ba
    public boolean containsRow(Object obj) {
        return Maps.b((Map<?, ?>) rowMap(), obj);
    }

    @Override // com.google.common.collect.ba
    public boolean containsColumn(Object obj) {
        return Maps.b((Map<?, ?>) columnMap(), obj);
    }

    @Override // com.google.common.collect.ba
    public Set<R> rowKeySet() {
        return rowMap().keySet();
    }

    @Override // com.google.common.collect.ba
    public Set<C> columnKeySet() {
        return columnMap().keySet();
    }

    @Override // com.google.common.collect.ba
    public boolean containsValue(Object obj) {
        for (Map<C, V> map : rowMap().values()) {
            if (map.containsValue(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.common.collect.ba
    public boolean contains(Object obj, Object obj2) {
        Map map = (Map) Maps.a((Map<?, Object>) rowMap(), obj);
        return map != null && Maps.b(map, obj2);
    }

    @Override // com.google.common.collect.ba
    public V get(Object obj, Object obj2) {
        Map map = (Map) Maps.a((Map<?, Object>) rowMap(), obj);
        if (map == null) {
            return null;
        }
        return (V) Maps.a((Map<?, Object>) map, obj2);
    }

    @Override // com.google.common.collect.ba
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // com.google.common.collect.ba
    public void clear() {
        Iterators.h(cellSet().iterator());
    }

    @Override // com.google.common.collect.ba
    public V remove(Object obj, Object obj2) {
        Map map = (Map) Maps.a((Map<?, Object>) rowMap(), obj);
        if (map == null) {
            return null;
        }
        return (V) Maps.c(map, obj2);
    }

    @Override // com.google.common.collect.ba
    public V put(R r, C c, V v) {
        return row(r).put(c, v);
    }

    @Override // com.google.common.collect.ba
    public void putAll(ba<? extends R, ? extends C, ? extends V> baVar) {
        for (ba.a<? extends R, ? extends C, ? extends V> aVar : baVar.cellSet()) {
            put(aVar.getRowKey(), aVar.getColumnKey(), aVar.getValue());
        }
    }

    @Override // com.google.common.collect.ba
    public Set<ba.a<R, C, V>> cellSet() {
        Set<ba.a<R, C, V>> set = this.cellSet;
        if (set != null) {
            return set;
        }
        Set<ba.a<R, C, V>> createCellSet = createCellSet();
        this.cellSet = createCellSet;
        return createCellSet;
    }

    /* access modifiers changed from: package-private */
    public Set<ba.a<R, C, V>> createCellSet() {
        return new a();
    }

    /* compiled from: AbstractTable */
    /* access modifiers changed from: package-private */
    public class a extends AbstractSet<ba.a<R, C, V>> {
        a() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof ba.a)) {
                return false;
            }
            ba.a aVar = (ba.a) obj;
            Map map = (Map) Maps.a((Map<?, Object>) i.this.rowMap(), aVar.getRowKey());
            if (map == null || !o.a(map.entrySet(), Maps.a(aVar.getColumnKey(), aVar.getValue()))) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof ba.a)) {
                return false;
            }
            ba.a aVar = (ba.a) obj;
            Map map = (Map) Maps.a((Map<?, Object>) i.this.rowMap(), aVar.getRowKey());
            if (map == null || !o.b(map.entrySet(), Maps.a(aVar.getColumnKey(), aVar.getValue()))) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            i.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<ba.a<R, C, V>> iterator() {
            return i.this.cellIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return i.this.size();
        }
    }

    @Override // com.google.common.collect.ba
    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Collection<V> createValues = createValues();
        this.values = createValues;
        return createValues;
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createValues() {
        return new b();
    }

    /* compiled from: AbstractTable */
    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.i$1  reason: invalid class name */
    public class AnonymousClass1 extends bc<ba.a<R, C, V>, V> {
        AnonymousClass1(Iterator it2) {
            super(it2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.bc
        public /* bridge */ /* synthetic */ Object a(Object obj) {
            return a((ba.a<R, C, Object>) ((ba.a) obj));
        }

        /* access modifiers changed from: package-private */
        public V a(ba.a<R, C, V> aVar) {
            return (V) aVar.getValue();
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> valuesIterator() {
        return new AnonymousClass1(cellSet().iterator());
    }

    /* compiled from: AbstractTable */
    /* access modifiers changed from: package-private */
    public class b extends AbstractCollection<V> {
        b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return i.this.valuesIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return i.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            i.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return i.this.size();
        }
    }

    @Override // com.google.common.collect.ba, java.lang.Object
    public boolean equals(Object obj) {
        return Tables.a(this, obj);
    }

    @Override // com.google.common.collect.ba, java.lang.Object
    public int hashCode() {
        return cellSet().hashCode();
    }

    @Override // java.lang.Object
    public String toString() {
        return rowMap().toString();
    }
}
