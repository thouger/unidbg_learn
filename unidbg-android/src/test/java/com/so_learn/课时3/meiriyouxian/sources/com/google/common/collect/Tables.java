package com.google.common.collect;

import com.google.common.base.g;
import com.google.common.base.j;
import com.google.common.base.m;
import com.google.common.collect.ba;
import com.umeng.message.proguard.l;
import com.xiaomi.mipush.sdk.Constants;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public final class Tables {
    private static final g<? extends Map<?, ?>, ? extends Map<?, ?>> a = new AnonymousClass1();

    public static <R, C, V> ba.a<R, C, V> a(R r, C c, V v) {
        return new ImmutableCell(r, c, v);
    }

    static final class ImmutableCell<R, C, V> extends a<R, C, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final C columnKey;
        private final R rowKey;
        private final V value;

        ImmutableCell(R r, C c, V v) {
            this.rowKey = r;
            this.columnKey = c;
            this.value = v;
        }

        @Override // com.google.common.collect.ba.a
        public R getRowKey() {
            return this.rowKey;
        }

        @Override // com.google.common.collect.ba.a
        public C getColumnKey() {
            return this.columnKey;
        }

        @Override // com.google.common.collect.ba.a
        public V getValue() {
            return this.value;
        }
    }

    static abstract class a<R, C, V> implements ba.a<R, C, V> {
        a() {
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ba.a)) {
                return false;
            }
            ba.a aVar = (ba.a) obj;
            return j.a(getRowKey(), aVar.getRowKey()) && j.a(getColumnKey(), aVar.getColumnKey()) && j.a(getValue(), aVar.getValue());
        }

        public int hashCode() {
            return j.a(getRowKey(), getColumnKey(), getValue());
        }

        public String toString() {
            return l.s + ((Object) getRowKey()) + Constants.ACCEPT_TIME_SEPARATOR_SP + ((Object) getColumnKey()) + ")=" + ((Object) getValue());
        }
    }

    /* access modifiers changed from: private */
    public static class UnmodifiableTable<R, C, V> extends ae<R, C, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ba<? extends R, ? extends C, ? extends V> delegate;

        UnmodifiableTable(ba<? extends R, ? extends C, ? extends V> baVar) {
            this.delegate = (ba) m.a(baVar);
        }

        /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: com.google.common.collect.ba<? extends R, ? extends C, ? extends V>, com.google.common.collect.ba<R, C, V> */
        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ae, com.google.common.collect.z
        public ba<R, C, V> delegate() {
            return (ba<? extends R, ? extends C, ? extends V>) this.delegate;
        }

        @Override // com.google.common.collect.ae, com.google.common.collect.ba
        public Set<ba.a<R, C, V>> cellSet() {
            return Collections.unmodifiableSet(super.cellSet());
        }

        @Override // com.google.common.collect.ae, com.google.common.collect.ba
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ae, com.google.common.collect.ba
        public Map<R, V> column(C c) {
            return Collections.unmodifiableMap(super.column(c));
        }

        @Override // com.google.common.collect.ae, com.google.common.collect.ba
        public Set<C> columnKeySet() {
            return Collections.unmodifiableSet(super.columnKeySet());
        }

        @Override // com.google.common.collect.ae, com.google.common.collect.ba
        public Map<C, Map<R, V>> columnMap() {
            return Collections.unmodifiableMap(Maps.a((Map) super.columnMap(), Tables.b()));
        }

        @Override // com.google.common.collect.ae, com.google.common.collect.ba
        public V put(R r, C c, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ae, com.google.common.collect.ba
        public void putAll(ba<? extends R, ? extends C, ? extends V> baVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ae, com.google.common.collect.ba
        public V remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ae, com.google.common.collect.ba
        public Map<C, V> row(R r) {
            return Collections.unmodifiableMap(super.row(r));
        }

        @Override // com.google.common.collect.ae, com.google.common.collect.ba
        public Set<R> rowKeySet() {
            return Collections.unmodifiableSet(super.rowKeySet());
        }

        @Override // com.google.common.collect.ae, com.google.common.collect.ba
        public Map<R, Map<C, V>> rowMap() {
            return Collections.unmodifiableMap(Maps.a((Map) super.rowMap(), Tables.b()));
        }

        @Override // com.google.common.collect.ae, com.google.common.collect.ba
        public Collection<V> values() {
            return Collections.unmodifiableCollection(super.values());
        }
    }

    static final class UnmodifiableRowSortedMap<R, C, V> extends UnmodifiableTable<R, C, V> implements ar<R, C, V> {
        private static final long serialVersionUID = 0;

        public UnmodifiableRowSortedMap(ar<R, ? extends C, ? extends V> arVar) {
            super(arVar);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ae, com.google.common.collect.z
        public ar<R, C, V> delegate() {
            return (ar) super.delegate();
        }

        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ae, com.google.common.collect.ba
        public SortedMap<R, Map<C, V>> rowMap() {
            return Collections.unmodifiableSortedMap(Maps.a((SortedMap) delegate().rowMap(), Tables.b()));
        }

        @Override // com.google.common.collect.Tables.UnmodifiableTable, com.google.common.collect.ae, com.google.common.collect.ba
        public SortedSet<R> rowKeySet() {
            return Collections.unmodifiableSortedSet(delegate().rowKeySet());
        }
    }

    /* access modifiers changed from: private */
    public static <K, V> g<Map<K, V>, Map<K, V>> b() {
        return (g<Map<K, V>, Map<K, V>>) a;
    }

    /* renamed from: com.google.common.collect.Tables$1  reason: invalid class name */
    static class AnonymousClass1 implements g<Map<Object, Object>, Map<Object, Object>> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public Map<Object, Object> apply(Map<Object, Object> map) {
            return Collections.unmodifiableMap(map);
        }
    }

    static boolean a(ba<?, ?, ?> baVar, Object obj) {
        if (obj == baVar) {
            return true;
        }
        if (obj instanceof ba) {
            return baVar.cellSet().equals(((ba) obj).cellSet());
        }
        return false;
    }
}
