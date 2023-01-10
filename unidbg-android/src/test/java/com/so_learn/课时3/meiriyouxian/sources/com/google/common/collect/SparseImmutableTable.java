package com.google.common.collect;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.ba;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class SparseImmutableTable<R, C, V> extends RegularImmutableTable<R, C, V> {
    static final ImmutableTable<Object, Object, Object> EMPTY = new SparseImmutableTable(ImmutableList.of(), ImmutableSet.of(), ImmutableSet.of());
    private final int[] cellColumnInRowIndices;
    private final int[] cellRowIndices;
    private final ImmutableMap<C, ImmutableMap<R, V>> columnMap;
    private final ImmutableMap<R, ImmutableMap<C, V>> rowMap;

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.LinkedHashMap */
    /* JADX DEBUG: Multi-variable search result rejected for r11v2, resolved type: java.util.LinkedHashMap */
    /* JADX DEBUG: Multi-variable search result rejected for r10v1, resolved type: com.google.common.collect.ImmutableMap$a */
    /* JADX DEBUG: Multi-variable search result rejected for r10v3, resolved type: com.google.common.collect.ImmutableMap$a */
    /* JADX DEBUG: Multi-variable search result rejected for r7v4, resolved type: java.util.Map */
    /* JADX DEBUG: Multi-variable search result rejected for r6v2, resolved type: java.util.Map */
    /* JADX WARN: Multi-variable type inference failed */
    SparseImmutableTable(ImmutableList<ba.a<R, C, V>> immutableList, ImmutableSet<R> immutableSet, ImmutableSet<C> immutableSet2) {
        ImmutableMap a = Maps.a((Collection) immutableSet);
        LinkedHashMap d = Maps.d();
        bf<R> it2 = immutableSet.iterator();
        while (it2.hasNext()) {
            d.put(it2.next(), new LinkedHashMap());
        }
        LinkedHashMap d2 = Maps.d();
        bf<C> it3 = immutableSet2.iterator();
        while (it3.hasNext()) {
            d2.put(it3.next(), new LinkedHashMap());
        }
        int[] iArr = new int[immutableList.size()];
        int[] iArr2 = new int[immutableList.size()];
        for (int i = 0; i < immutableList.size(); i++) {
            ba.a<R, C, V> aVar = immutableList.get(i);
            Object rowKey = aVar.getRowKey();
            Object columnKey = aVar.getColumnKey();
            Object value = aVar.getValue();
            iArr[i] = a.get(rowKey).intValue();
            Map map = (Map) d.get(rowKey);
            iArr2[i] = map.size();
            checkNoDuplicate(rowKey, columnKey, map.put(columnKey, value), value);
            ((Map) d2.get(columnKey)).put(rowKey, value);
        }
        this.cellRowIndices = iArr;
        this.cellColumnInRowIndices = iArr2;
        ImmutableMap.a aVar2 = new ImmutableMap.a(d.size());
        for (Map.Entry entry : d.entrySet()) {
            aVar2.b(entry.getKey(), ImmutableMap.copyOf((Map) entry.getValue()));
        }
        this.rowMap = aVar2.b();
        ImmutableMap.a aVar3 = new ImmutableMap.a(d2.size());
        for (Map.Entry entry2 : d2.entrySet()) {
            aVar3.b(entry2.getKey(), ImmutableMap.copyOf((Map) entry2.getValue()));
        }
        this.columnMap = aVar3.b();
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.ba
    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.copyOf(this.columnMap);
    }

    @Override // com.google.common.collect.ImmutableTable, com.google.common.collect.ba
    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.copyOf(this.rowMap);
    }

    @Override // com.google.common.collect.ba
    public int size() {
        return this.cellRowIndices.length;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.RegularImmutableTable
    public ba.a<R, C, V> getCell(int i) {
        Map.Entry<R, ImmutableMap<C, V>> entry = this.rowMap.entrySet().asList().get(this.cellRowIndices[i]);
        Map.Entry<C, V> entry2 = entry.getValue().entrySet().asList().get(this.cellColumnInRowIndices[i]);
        return cellOf(entry.getKey(), entry2.getKey(), entry2.getValue());
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.RegularImmutableTable
    public V getValue(int i) {
        int i2 = this.cellRowIndices[i];
        return (V) this.rowMap.values().asList().get(i2).values().asList().get(this.cellColumnInRowIndices[i]);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableTable
    public ImmutableTable.SerializedForm createSerializedForm() {
        ImmutableMap a = Maps.a((Collection) columnKeySet());
        int[] iArr = new int[cellSet().size()];
        bf<ba.a<R, C, V>> it2 = cellSet().iterator();
        int i = 0;
        while (it2.hasNext()) {
            iArr[i] = a.get(it2.next().getColumnKey()).intValue();
            i++;
        }
        return ImmutableTable.SerializedForm.create(this, this.cellRowIndices, iArr);
    }
}
