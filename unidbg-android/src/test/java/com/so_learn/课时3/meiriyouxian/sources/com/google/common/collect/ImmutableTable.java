package com.google.common.collect;

import com.google.common.base.i;
import com.google.common.base.m;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Tables;
import com.google.common.collect.ba;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class ImmutableTable<R, C, V> extends i<R, C, V> implements Serializable {
    @Override // com.google.common.collect.ba
    public abstract ImmutableMap<C, Map<R, V>> columnMap();

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.i
    public abstract ImmutableSet<ba.a<R, C, V>> createCellSet();

    /* access modifiers changed from: package-private */
    public abstract SerializedForm createSerializedForm();

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.i
    public abstract ImmutableCollection<V> createValues();

    @Override // com.google.common.collect.ba
    public abstract ImmutableMap<R, Map<C, V>> rowMap();

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ boolean containsColumn(Object obj) {
        return super.containsColumn(obj);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ boolean containsRow(Object obj) {
        return super.containsRow(obj);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba, java.lang.Object
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ Object get(Object obj, Object obj2) {
        return super.get(obj, obj2);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba, java.lang.Object
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.i, java.lang.Object
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public static <R, C, V> ImmutableTable<R, C, V> of() {
        return (ImmutableTable<R, C, V>) SparseImmutableTable.EMPTY;
    }

    public static <R, C, V> ImmutableTable<R, C, V> of(R r, C c, V v) {
        return new SingletonImmutableTable(r, c, v);
    }

    public static <R, C, V> ImmutableTable<R, C, V> copyOf(ba<? extends R, ? extends C, ? extends V> baVar) {
        if (baVar instanceof ImmutableTable) {
            return (ImmutableTable) baVar;
        }
        return copyOf(baVar.cellSet());
    }

    private static <R, C, V> ImmutableTable<R, C, V> copyOf(Iterable<? extends ba.a<? extends R, ? extends C, ? extends V>> iterable) {
        a builder = builder();
        for (ba.a<? extends R, ? extends C, ? extends V> aVar : iterable) {
            builder.a(aVar);
        }
        return builder.a();
    }

    public static <R, C, V> a<R, C, V> builder() {
        return new a<>();
    }

    static <R, C, V> ba.a<R, C, V> cellOf(R r, C c, V v) {
        return Tables.a(m.a(r, "rowKey"), m.a(c, "columnKey"), m.a(v, "value"));
    }

    public static final class a<R, C, V> {
        private final List<ba.a<R, C, V>> a = Lists.a();
        private Comparator<? super R> b;
        private Comparator<? super C> c;

        public a<R, C, V> a(R r, C c, V v) {
            this.a.add(ImmutableTable.cellOf(r, c, v));
            return this;
        }

        public a<R, C, V> a(ba.a<? extends R, ? extends C, ? extends V> aVar) {
            if (aVar instanceof Tables.ImmutableCell) {
                m.a(aVar.getRowKey(), Constant.KEY_ROW);
                m.a(aVar.getColumnKey(), "column");
                m.a(aVar.getValue(), "value");
                this.a.add(aVar);
            } else {
                a(aVar.getRowKey(), aVar.getColumnKey(), aVar.getValue());
            }
            return this;
        }

        public ImmutableTable<R, C, V> a() {
            int size = this.a.size();
            if (size == 0) {
                return ImmutableTable.of();
            }
            if (size != 1) {
                return RegularImmutableTable.forCells(this.a, this.b, this.c);
            }
            return new SingletonImmutableTable((ba.a) ag.b(this.a));
        }
    }

    ImmutableTable() {
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public ImmutableSet<ba.a<R, C, V>> cellSet() {
        return (ImmutableSet) super.cellSet();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.i
    public final bf<ba.a<R, C, V>> cellIterator() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public ImmutableCollection<V> values() {
        return (ImmutableCollection) super.values();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.i
    public final Iterator<V> valuesIterator() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.ba
    public ImmutableMap<R, V> column(C c) {
        m.a(c, "columnKey");
        return (ImmutableMap) i.a((ImmutableMap) columnMap().get(c), ImmutableMap.of());
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public ImmutableSet<C> columnKeySet() {
        return columnMap().keySet();
    }

    @Override // com.google.common.collect.ba
    public ImmutableMap<C, V> row(R r) {
        m.a(r, "rowKey");
        return (ImmutableMap) i.a((ImmutableMap) rowMap().get(r), ImmutableMap.of());
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public ImmutableSet<R> rowKeySet() {
        return rowMap().keySet();
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public boolean contains(Object obj, Object obj2) {
        return get(obj, obj2) != null;
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    @Deprecated
    public final V put(R r, C c, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    @Deprecated
    public final void putAll(ba<? extends R, ? extends C, ? extends V> baVar) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    @Deprecated
    public final V remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    static final class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final int[] cellColumnIndices;
        private final int[] cellRowIndices;
        private final Object[] cellValues;
        private final Object[] columnKeys;
        private final Object[] rowKeys;

        private SerializedForm(Object[] objArr, Object[] objArr2, Object[] objArr3, int[] iArr, int[] iArr2) {
            this.rowKeys = objArr;
            this.columnKeys = objArr2;
            this.cellValues = objArr3;
            this.cellRowIndices = iArr;
            this.cellColumnIndices = iArr2;
        }

        static SerializedForm create(ImmutableTable<?, ?, ?> immutableTable, int[] iArr, int[] iArr2) {
            return new SerializedForm(immutableTable.rowKeySet().toArray(), immutableTable.columnKeySet().toArray(), immutableTable.values().toArray(), iArr, iArr2);
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            Object[] objArr = this.cellValues;
            if (objArr.length == 0) {
                return ImmutableTable.of();
            }
            int i = 0;
            if (objArr.length == 1) {
                return ImmutableTable.of(this.rowKeys[0], this.columnKeys[0], objArr[0]);
            }
            ImmutableList.a aVar = new ImmutableList.a(objArr.length);
            while (true) {
                Object[] objArr2 = this.cellValues;
                if (i >= objArr2.length) {
                    return RegularImmutableTable.forOrderedComponents(aVar.a(), ImmutableSet.copyOf(this.rowKeys), ImmutableSet.copyOf(this.columnKeys));
                }
                aVar.b(ImmutableTable.cellOf(this.rowKeys[this.cellRowIndices[i]], this.columnKeys[this.cellColumnIndices[i]], objArr2[i]));
                i++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Object writeReplace() {
        return createSerializedForm();
    }
}
