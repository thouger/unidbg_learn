package com.google.common.collect;

import android.net.wifi.WifiEnterpriseConfig;
import com.google.common.base.j;
import com.google.common.base.m;
import com.google.common.collect.Maps;
import com.google.common.collect.Tables;
import com.google.common.collect.ba;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class ArrayTable<R, C, V> extends i<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    private final V[][] array;
    private final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableList<C> columnList;
    private transient ArrayTable<R, C, V>.c columnMap;
    private final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableList<R> rowList;
    private transient ArrayTable<R, C, V>.e rowMap;

    @Override // com.google.common.collect.i, com.google.common.collect.ba, java.lang.Object
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba, java.lang.Object
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.i, java.lang.Object
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        return new ArrayTable<>(iterable, iterable2);
    }

    public static <R, C, V> ArrayTable<R, C, V> create(ba<R, C, V> baVar) {
        return baVar instanceof ArrayTable ? new ArrayTable<>((ArrayTable) baVar) : new ArrayTable<>(baVar);
    }

    private ArrayTable(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        this.rowList = ImmutableList.copyOf(iterable);
        this.columnList = ImmutableList.copyOf(iterable2);
        m.a(this.rowList.isEmpty() == this.columnList.isEmpty());
        this.rowKeyToIndex = Maps.a(this.rowList);
        this.columnKeyToIndex = Maps.a(this.columnList);
        this.array = (V[][]) ((Object[][]) Array.newInstance(Object.class, this.rowList.size(), this.columnList.size()));
        eraseAll();
    }

    private ArrayTable(ba<R, C, V> baVar) {
        this(baVar.rowKeySet(), baVar.columnKeySet());
        putAll(baVar);
    }

    private ArrayTable(ArrayTable<R, C, V> arrayTable) {
        this.rowList = arrayTable.rowList;
        this.columnList = arrayTable.columnList;
        this.rowKeyToIndex = arrayTable.rowKeyToIndex;
        this.columnKeyToIndex = arrayTable.columnKeyToIndex;
        V[][] vArr = (V[][]) ((Object[][]) Array.newInstance(Object.class, this.rowList.size(), this.columnList.size()));
        this.array = vArr;
        for (int i = 0; i < this.rowList.size(); i++) {
            V[][] vArr2 = arrayTable.array;
            System.arraycopy(vArr2[i], 0, vArr[i], 0, vArr2[i].length);
        }
    }

    /* access modifiers changed from: private */
    public static abstract class a<K, V> extends Maps.d<K, V> {
        private final ImmutableMap<K, Integer> a;

        /* access modifiers changed from: package-private */
        public abstract V a(int i, V v);

        /* access modifiers changed from: package-private */
        public abstract String a();

        /* access modifiers changed from: package-private */
        public abstract V b(int i);

        /* synthetic */ a(ImmutableMap immutableMap, AnonymousClass1 r2) {
            this(immutableMap);
        }

        private a(ImmutableMap<K, Integer> immutableMap) {
            this.a = immutableMap;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.a.keySet();
        }

        /* access modifiers changed from: package-private */
        public K a(int i) {
            return (K) this.a.keySet().asList().get(i);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.a.size();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return this.a.isEmpty();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.ArrayTable$a$1  reason: invalid class name */
        public class AnonymousClass1 extends b<K, V> {
            final /* synthetic */ int a;

            AnonymousClass1(int i) {
                this.a = i;
            }

            @Override // com.google.common.collect.b, java.util.Map.Entry
            public K getKey() {
                return (K) a.this.a(this.a);
            }

            @Override // com.google.common.collect.b, java.util.Map.Entry
            public V getValue() {
                return (V) a.this.b(this.a);
            }

            @Override // com.google.common.collect.b, java.util.Map.Entry
            public V setValue(V v) {
                return (V) a.this.a(this.a, v);
            }
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, V> c(int i) {
            m.a(i, size());
            return new AnonymousClass1(i);
        }

        /* renamed from: com.google.common.collect.ArrayTable$a$2  reason: invalid class name */
        class AnonymousClass2 extends a<Map.Entry<K, V>> {
            AnonymousClass2(int i) {
                super(i);
            }

            /* access modifiers changed from: protected */
            /* renamed from: b */
            public Map.Entry<K, V> a(int i) {
                return a.this.c(i);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.d
        public Iterator<Map.Entry<K, V>> b() {
            return new AnonymousClass2(size());
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.a.containsKey(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            Integer num = (Integer) this.a.get(obj);
            if (num == null) {
                return null;
            }
            return (V) b(num.intValue());
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K k, V v) {
            Integer num = (Integer) this.a.get(k);
            if (num != null) {
                return (V) a(num.intValue(), v);
            }
            throw new IllegalArgumentException(a() + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + ((Object) k) + " not in " + this.a.keySet());
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Maps.d, java.util.AbstractMap, java.util.Map
        public void clear() {
            throw new UnsupportedOperationException();
        }
    }

    public ImmutableList<R> rowKeyList() {
        return this.rowList;
    }

    public ImmutableList<C> columnKeyList() {
        return this.columnList;
    }

    public V at(int i, int i2) {
        m.a(i, this.rowList.size());
        m.a(i2, this.columnList.size());
        return this.array[i][i2];
    }

    public V set(int i, int i2, V v) {
        m.a(i, this.rowList.size());
        m.a(i2, this.columnList.size());
        V[][] vArr = this.array;
        V v2 = vArr[i][i2];
        vArr[i][i2] = v;
        return v2;
    }

    public V[][] toArray(Class<V> cls) {
        V[][] vArr = (V[][]) ((Object[][]) Array.newInstance((Class<?>) cls, this.rowList.size(), this.columnList.size()));
        for (int i = 0; i < this.rowList.size(); i++) {
            V[][] vArr2 = this.array;
            System.arraycopy(vArr2[i], 0, vArr[i], 0, vArr2[i].length);
        }
        return vArr;
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public void eraseAll() {
        for (V[] vArr : this.array) {
            Arrays.fill(vArr, (Object) null);
        }
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public boolean contains(Object obj, Object obj2) {
        return containsRow(obj) && containsColumn(obj2);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public boolean containsColumn(Object obj) {
        return this.columnKeyToIndex.containsKey(obj);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public boolean containsRow(Object obj) {
        return this.rowKeyToIndex.containsKey(obj);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public boolean containsValue(Object obj) {
        V[][] vArr = this.array;
        for (V[] vArr2 : vArr) {
            for (V v : vArr2) {
                if (j.a(obj, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public V get(Object obj, Object obj2) {
        Integer num = (Integer) this.rowKeyToIndex.get(obj);
        Integer num2 = (Integer) this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return (V) at(num.intValue(), num2.intValue());
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public boolean isEmpty() {
        return this.rowList.isEmpty() || this.columnList.isEmpty();
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public V put(R r, C c2, V v) {
        m.a(r);
        m.a(c2);
        Integer num = (Integer) this.rowKeyToIndex.get(r);
        boolean z = true;
        m.a(num != null, "Row %s not in %s", r, this.rowList);
        Integer num2 = (Integer) this.columnKeyToIndex.get(c2);
        if (num2 == null) {
            z = false;
        }
        m.a(z, "Column %s not in %s", c2, this.columnList);
        return (V) set(num.intValue(), num2.intValue(), v);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public void putAll(ba<? extends R, ? extends C, ? extends V> baVar) {
        super.putAll(baVar);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    @Deprecated
    public V remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public V erase(Object obj, Object obj2) {
        Integer num = (Integer) this.rowKeyToIndex.get(obj);
        Integer num2 = (Integer) this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return (V) set(num.intValue(), num2.intValue(), null);
    }

    @Override // com.google.common.collect.ba
    public int size() {
        return this.rowList.size() * this.columnList.size();
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public Set<ba.a<R, C, V>> cellSet() {
        return super.cellSet();
    }

    /* renamed from: com.google.common.collect.ArrayTable$1  reason: invalid class name */
    class AnonymousClass1 extends a<ba.a<R, C, V>> {
        AnonymousClass1(int i) {
            super(i);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public ba.a<R, C, V> a(int i) {
            return ArrayTable.this.getCell(i);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.i
    public Iterator<ba.a<R, C, V>> cellIterator() {
        return new AnonymousClass1(size());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.ArrayTable$2  reason: invalid class name */
    public class AnonymousClass2 extends Tables.a<R, C, V> {
        final int a = (this.c / ArrayTable.this.columnList.size());
        final int b = (this.c % ArrayTable.this.columnList.size());
        final /* synthetic */ int c;

        AnonymousClass2(int i) {
            this.c = i;
        }

        @Override // com.google.common.collect.ba.a
        public R getRowKey() {
            return (R) ArrayTable.this.rowList.get(this.a);
        }

        @Override // com.google.common.collect.ba.a
        public C getColumnKey() {
            return (C) ArrayTable.this.columnList.get(this.b);
        }

        @Override // com.google.common.collect.ba.a
        public V getValue() {
            return (V) ArrayTable.this.at(this.a, this.b);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ba.a<R, C, V> getCell(int i) {
        return new AnonymousClass2(i);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private V getValue(int i) {
        return (V) at(i / this.columnList.size(), i % this.columnList.size());
    }

    @Override // com.google.common.collect.ba
    public Map<R, V> column(C c2) {
        m.a(c2);
        Integer num = (Integer) this.columnKeyToIndex.get(c2);
        return num == null ? ImmutableMap.of() : new b(num.intValue());
    }

    /* access modifiers changed from: private */
    public class b extends a<R, V> {
        final int a;

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.a
        public String a() {
            return "Row";
        }

        b(int i) {
            super(ArrayTable.this.rowKeyToIndex, null);
            this.a = i;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.a
        public V b(int i) {
            return (V) ArrayTable.this.at(i, this.a);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.a
        public V a(int i, V v) {
            return (V) ArrayTable.this.set(i, this.a, v);
        }
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public ImmutableSet<C> columnKeySet() {
        return this.columnKeyToIndex.keySet();
    }

    @Override // com.google.common.collect.ba
    public Map<C, Map<R, V>> columnMap() {
        ArrayTable<R, C, V>.c cVar = this.columnMap;
        if (cVar != null) {
            return cVar;
        }
        ArrayTable<R, C, V>.c cVar2 = new c(this, null);
        this.columnMap = cVar2;
        return cVar2;
    }

    private class c extends a<C, Map<R, V>> {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.a
        public String a() {
            return "Column";
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.a
        public /* bridge */ /* synthetic */ Object a(int i, Object obj) {
            return a(i, (Map) ((Map) obj));
        }

        @Override // com.google.common.collect.ArrayTable.a, java.util.AbstractMap, java.util.Map
        public /* synthetic */ Object put(Object obj, Object obj2) {
            return a((c) obj, (Map) ((Map) obj2));
        }

        /* synthetic */ c(ArrayTable arrayTable, AnonymousClass1 r2) {
            this();
        }

        private c() {
            super(ArrayTable.this.columnKeyToIndex, null);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public Map<R, V> b(int i) {
            return new b(i);
        }

        /* access modifiers changed from: package-private */
        public Map<R, V> a(int i, Map<R, V> map) {
            throw new UnsupportedOperationException();
        }

        public Map<R, V> a(C c, Map<R, V> map) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.common.collect.ba
    public Map<C, V> row(R r) {
        m.a(r);
        Integer num = (Integer) this.rowKeyToIndex.get(r);
        return num == null ? ImmutableMap.of() : new d(num.intValue());
    }

    /* access modifiers changed from: private */
    public class d extends a<C, V> {
        final int a;

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.a
        public String a() {
            return "Column";
        }

        d(int i) {
            super(ArrayTable.this.columnKeyToIndex, null);
            this.a = i;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.a
        public V b(int i) {
            return (V) ArrayTable.this.at(this.a, i);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.a
        public V a(int i, V v) {
            return (V) ArrayTable.this.set(this.a, i, v);
        }
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public ImmutableSet<R> rowKeySet() {
        return this.rowKeyToIndex.keySet();
    }

    @Override // com.google.common.collect.ba
    public Map<R, Map<C, V>> rowMap() {
        ArrayTable<R, C, V>.e eVar = this.rowMap;
        if (eVar != null) {
            return eVar;
        }
        ArrayTable<R, C, V>.e eVar2 = new e(this, null);
        this.rowMap = eVar2;
        return eVar2;
    }

    private class e extends a<R, Map<C, V>> {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.a
        public String a() {
            return "Row";
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.a
        public /* bridge */ /* synthetic */ Object a(int i, Object obj) {
            return a(i, (Map) ((Map) obj));
        }

        @Override // com.google.common.collect.ArrayTable.a, java.util.AbstractMap, java.util.Map
        public /* synthetic */ Object put(Object obj, Object obj2) {
            return a((e) obj, (Map) ((Map) obj2));
        }

        /* synthetic */ e(ArrayTable arrayTable, AnonymousClass1 r2) {
            this();
        }

        private e() {
            super(ArrayTable.this.rowKeyToIndex, null);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public Map<C, V> b(int i) {
            return new d(i);
        }

        /* access modifiers changed from: package-private */
        public Map<C, V> a(int i, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }

        public Map<C, V> a(R r, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public Collection<V> values() {
        return super.values();
    }

    /* renamed from: com.google.common.collect.ArrayTable$3  reason: invalid class name */
    class AnonymousClass3 extends a<V> {
        AnonymousClass3(int i) {
            super(i);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.a
        public V a(int i) {
            return (V) ArrayTable.this.getValue(i);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.i
    public Iterator<V> valuesIterator() {
        return new AnonymousClass3(size());
    }
}
