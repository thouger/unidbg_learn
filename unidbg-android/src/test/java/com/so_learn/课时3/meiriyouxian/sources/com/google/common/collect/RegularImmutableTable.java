package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.ba;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

/* access modifiers changed from: package-private */
public abstract class RegularImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {
    /* access modifiers changed from: package-private */
    public abstract ba.a<R, C, V> getCell(int i);

    /* access modifiers changed from: package-private */
    public abstract V getValue(int i);

    RegularImmutableTable() {
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.i
    public final ImmutableSet<ba.a<R, C, V>> createCellSet() {
        return isEmpty() ? ImmutableSet.of() : new CellSet(this, null);
    }

    /* access modifiers changed from: private */
    public final class CellSet extends IndexedImmutableSet<ba.a<R, C, V>> {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return false;
        }

        private CellSet() {
        }

        /* synthetic */ CellSet(RegularImmutableTable regularImmutableTable, AnonymousClass1 r2) {
            this();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return RegularImmutableTable.this.size();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.IndexedImmutableSet
        public ba.a<R, C, V> get(int i) {
            return RegularImmutableTable.this.getCell(i);
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof ba.a)) {
                return false;
            }
            ba.a aVar = (ba.a) obj;
            Object obj2 = RegularImmutableTable.this.get(aVar.getRowKey(), aVar.getColumnKey());
            if (obj2 == null || !obj2.equals(aVar.getValue())) {
                return false;
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.i
    public final ImmutableCollection<V> createValues() {
        return isEmpty() ? ImmutableList.of() : new Values(this, null);
    }

    /* access modifiers changed from: private */
    public final class Values extends ImmutableList<V> {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        private Values() {
        }

        /* synthetic */ Values(RegularImmutableTable regularImmutableTable, AnonymousClass1 r2) {
            this();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return RegularImmutableTable.this.size();
        }

        @Override // java.util.List
        public V get(int i) {
            return (V) RegularImmutableTable.this.getValue(i);
        }
    }

    static <R, C, V> RegularImmutableTable<R, C, V> forCells(List<ba.a<R, C, V>> list, Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        m.a(list);
        if (!(comparator == null && comparator2 == null)) {
            Collections.sort(list, new AnonymousClass1(comparator, comparator2));
        }
        return forCellsInternal(list, comparator, comparator2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.RegularImmutableTable$1  reason: invalid class name */
    public static class AnonymousClass1 implements Comparator<ba.a<R, C, V>> {
        final /* synthetic */ Comparator a;
        final /* synthetic */ Comparator b;

        AnonymousClass1(Comparator comparator, Comparator comparator2) {
            this.a = comparator;
            this.b = comparator2;
        }

        /* renamed from: a */
        public int compare(ba.a<R, C, V> aVar, ba.a<R, C, V> aVar2) {
            int i;
            Comparator comparator = this.a;
            if (comparator == null) {
                i = 0;
            } else {
                i = comparator.compare(aVar.getRowKey(), aVar2.getRowKey());
            }
            if (i != 0) {
                return i;
            }
            Comparator comparator2 = this.b;
            if (comparator2 == null) {
                return 0;
            }
            return comparator2.compare(aVar.getColumnKey(), aVar2.getColumnKey());
        }
    }

    static <R, C, V> RegularImmutableTable<R, C, V> forCells(Iterable<ba.a<R, C, V>> iterable) {
        return forCellsInternal(iterable, null, null);
    }

    private static <R, C, V> RegularImmutableTable<R, C, V> forCellsInternal(Iterable<ba.a<R, C, V>> iterable, Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        ImmutableSet immutableSet;
        ImmutableSet immutableSet2;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        ImmutableList copyOf = ImmutableList.copyOf(iterable);
        for (ba.a<R, C, V> aVar : iterable) {
            linkedHashSet.add(aVar.getRowKey());
            linkedHashSet2.add(aVar.getColumnKey());
        }
        if (comparator == null) {
            immutableSet = ImmutableSet.copyOf((Collection) linkedHashSet);
        } else {
            immutableSet = ImmutableSet.copyOf((Collection) ImmutableList.sortedCopyOf(comparator, linkedHashSet));
        }
        if (comparator2 == null) {
            immutableSet2 = ImmutableSet.copyOf((Collection) linkedHashSet2);
        } else {
            immutableSet2 = ImmutableSet.copyOf((Collection) ImmutableList.sortedCopyOf(comparator2, linkedHashSet2));
        }
        return forOrderedComponents(copyOf, immutableSet, immutableSet2);
    }

    static <R, C, V> RegularImmutableTable<R, C, V> forOrderedComponents(ImmutableList<ba.a<R, C, V>> immutableList, ImmutableSet<R> immutableSet, ImmutableSet<C> immutableSet2) {
        return ((long) immutableList.size()) > (((long) immutableSet.size()) * ((long) immutableSet2.size())) / 2 ? new DenseImmutableTable(immutableList, immutableSet, immutableSet2) : new SparseImmutableTable(immutableList, immutableSet, immutableSet2);
    }

    /* access modifiers changed from: package-private */
    public final void checkNoDuplicate(R r, C c, V v, V v2) {
        m.a(v == null, "Duplicate key: (row=%s, column=%s), values: [%s, %s].", r, c, v2, v);
    }
}
