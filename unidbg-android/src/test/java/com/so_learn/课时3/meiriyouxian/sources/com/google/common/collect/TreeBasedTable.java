package com.google.common.collect;

import com.google.common.base.g;
import com.google.common.base.m;
import com.google.common.base.q;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

public class TreeBasedTable<R, C, V> extends StandardRowSortedTable<R, C, V> {
    private static final long serialVersionUID = 0;
    private final Comparator<? super C> columnComparator;

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ Set cellSet() {
        return super.cellSet();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ Map column(Object obj) {
        return super.column(obj);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ Set columnKeySet() {
        return super.columnKeySet();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ Map columnMap() {
        return super.columnMap();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ boolean contains(Object obj, Object obj2) {
        return super.contains(obj, obj2);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ boolean containsColumn(Object obj) {
        return super.containsColumn(obj);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ boolean containsRow(Object obj) {
        return super.containsRow(obj);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ boolean containsValue(Object obj) {
        return super.containsValue(obj);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba, java.lang.Object
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ Object get(Object obj, Object obj2) {
        return super.get(obj, obj2);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba, java.lang.Object
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2, Object obj3) {
        return super.put(obj, obj2, obj3);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ void putAll(ba baVar) {
        super.putAll(baVar);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ Object remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    @Override // com.google.common.collect.i, java.lang.Object
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ Collection values() {
        return super.values();
    }

    private static class Factory<C, V> implements q<TreeMap<C, V>>, Serializable {
        private static final long serialVersionUID = 0;
        final Comparator<? super C> comparator;

        Factory(Comparator<? super C> comparator) {
            this.comparator = comparator;
        }

        @Override // com.google.common.base.q
        public TreeMap<C, V> get() {
            return new TreeMap<>(this.comparator);
        }
    }

    public static <R extends Comparable, C extends Comparable, V> TreeBasedTable<R, C, V> create() {
        return new TreeBasedTable<>(Ordering.natural(), Ordering.natural());
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        m.a(comparator);
        m.a(comparator2);
        return new TreeBasedTable<>(comparator, comparator2);
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(TreeBasedTable<R, C, ? extends V> treeBasedTable) {
        TreeBasedTable<R, C, V> treeBasedTable2 = new TreeBasedTable<>(treeBasedTable.rowComparator(), treeBasedTable.columnComparator());
        treeBasedTable2.putAll(treeBasedTable);
        return treeBasedTable2;
    }

    TreeBasedTable(Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        super(new TreeMap(comparator), new Factory(comparator2));
        this.columnComparator = comparator2;
    }

    @Deprecated
    public Comparator<? super R> rowComparator() {
        return rowKeySet().comparator();
    }

    @Deprecated
    public Comparator<? super C> columnComparator() {
        return this.columnComparator;
    }

    @Override // com.google.common.collect.StandardTable, com.google.common.collect.ba
    public SortedMap<C, V> row(R r) {
        return new a(this, r);
    }

    /* access modifiers changed from: private */
    public class a extends StandardTable<R, C, V>.f implements SortedMap<C, V> {
        final C d;
        final C e;
        transient SortedMap<C, V> f;

        a(TreeBasedTable treeBasedTable, R r) {
            this(r, null, null);
        }

        a(R r, C c, C c2) {
            super(r);
            this.d = c;
            this.e = c2;
            m.a(c == null || c2 == null || a(c, c2) <= 0);
        }

        /* renamed from: e */
        public SortedSet<C> keySet() {
            return new Maps.g(this);
        }

        @Override // java.util.SortedMap
        public Comparator<? super C> comparator() {
            return TreeBasedTable.this.columnComparator();
        }

        /* access modifiers changed from: package-private */
        public int a(Object obj, Object obj2) {
            return comparator().compare(obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public boolean a(Object obj) {
            C c;
            C c2;
            return obj != null && ((c = this.d) == null || a(c, obj) <= 0) && ((c2 = this.e) == null || a(c2, obj) > 0);
        }

        @Override // java.util.SortedMap
        public SortedMap<C, V> subMap(C c, C c2) {
            m.a(a(m.a(c)) && a(m.a(c2)));
            return new a(this.a, c, c2);
        }

        @Override // java.util.SortedMap
        public SortedMap<C, V> headMap(C c) {
            m.a(a(m.a(c)));
            return new a(this.a, this.d, c);
        }

        @Override // java.util.SortedMap
        public SortedMap<C, V> tailMap(C c) {
            m.a(a(m.a(c)));
            return new a(this.a, c, this.e);
        }

        @Override // java.util.SortedMap
        public C firstKey() {
            if (a() != null) {
                return a().firstKey();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.SortedMap
        public C lastKey() {
            if (a() != null) {
                return a().lastKey();
            }
            throw new NoSuchElementException();
        }

        /* access modifiers changed from: package-private */
        public SortedMap<C, V> f() {
            SortedMap<C, V> sortedMap = this.f;
            if (sortedMap == null || (sortedMap.isEmpty() && TreeBasedTable.this.backingMap.containsKey(this.a))) {
                this.f = (SortedMap) TreeBasedTable.this.backingMap.get(this.a);
            }
            return this.f;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: g */
        public SortedMap<C, V> a() {
            return (SortedMap) super.a();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: h */
        public SortedMap<C, V> c() {
            SortedMap<C, V> f = f();
            if (f == null) {
                return null;
            }
            C c = this.d;
            if (c != null) {
                f = f.tailMap(c);
            }
            C c2 = this.e;
            return c2 != null ? f.headMap(c2) : f;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.StandardTable.f
        public void d() {
            if (f() != null && this.f.isEmpty()) {
                TreeBasedTable.this.backingMap.remove(this.a);
                this.f = null;
                this.b = null;
            }
        }

        @Override // com.google.common.collect.StandardTable.f, java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return a(obj) && super.containsKey(obj);
        }

        @Override // com.google.common.collect.StandardTable.f, java.util.AbstractMap, java.util.Map
        public V put(C c, V v) {
            m.a(a(m.a(c)));
            return (V) super.put(c, v);
        }
    }

    @Override // com.google.common.collect.StandardRowSortedTable, com.google.common.collect.StandardTable, com.google.common.collect.i, com.google.common.collect.ba
    public SortedSet<R> rowKeySet() {
        return super.rowKeySet();
    }

    @Override // com.google.common.collect.StandardRowSortedTable, com.google.common.collect.StandardTable, com.google.common.collect.ba
    public SortedMap<R, Map<C, V>> rowMap() {
        return super.rowMap();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.StandardTable
    public Iterator<C> createColumnKeyIterator() {
        Comparator<? super C> columnComparator = columnComparator();
        return new AnonymousClass2(Iterators.a(ag.a((Iterable) this.backingMap.values(), (g) new AnonymousClass1()), columnComparator), columnComparator);
    }

    /* renamed from: com.google.common.collect.TreeBasedTable$1  reason: invalid class name */
    class AnonymousClass1 implements g<Map<C, V>, Iterator<C>> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public Iterator<C> apply(Map<C, V> map) {
            return map.keySet().iterator();
        }
    }

    /* renamed from: com.google.common.collect.TreeBasedTable$2  reason: invalid class name */
    class AnonymousClass2 extends AbstractIterator<C> {
        C a;
        final /* synthetic */ Iterator b;
        final /* synthetic */ Comparator c;

        AnonymousClass2(Iterator it2, Comparator comparator) {
            this.b = it2;
            this.c = comparator;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractIterator
        public C a() {
            boolean z;
            while (this.b.hasNext()) {
                C c = (C) this.b.next();
                C c2 = this.a;
                if (c2 == null || this.c.compare(c, c2) != 0) {
                    z = false;
                    continue;
                } else {
                    z = true;
                    continue;
                }
                if (!z) {
                    this.a = c;
                    return this.a;
                }
            }
            this.a = null;
            return b();
        }
    }
}
