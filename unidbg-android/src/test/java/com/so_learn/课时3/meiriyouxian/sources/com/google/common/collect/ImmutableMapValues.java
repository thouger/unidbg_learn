package com.google.common.collect;

import java.io.Serializable;
import java.util.Map;

final class ImmutableMapValues<K, V> extends ImmutableCollection<V> {
    private final ImmutableMap<K, V> map;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return true;
    }

    ImmutableMapValues(ImmutableMap<K, V> immutableMap) {
        this.map = immutableMap;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.map.size();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.ImmutableMapValues$1  reason: invalid class name */
    public class AnonymousClass1 extends bf<V> {
        final bf<Map.Entry<K, V>> a = ImmutableMapValues.this.map.entrySet().iterator();

        AnonymousClass1() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.a.hasNext();
        }

        @Override // java.util.Iterator
        public V next() {
            return this.a.next().getValue();
        }
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet
    public bf<V> iterator() {
        return new AnonymousClass1();
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return obj != null && Iterators.a(iterator(), obj);
    }

    /* renamed from: com.google.common.collect.ImmutableMapValues$2  reason: invalid class name */
    class AnonymousClass2 extends ImmutableList<V> {
        final /* synthetic */ ImmutableList val$entryList;

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        AnonymousClass2(ImmutableList immutableList) {
            this.val$entryList = immutableList;
        }

        @Override // java.util.List
        public V get(int i) {
            return (V) this.val$entryList.get(i).getValue();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.val$entryList.size();
        }
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<V> asList() {
        return new AnonymousClass2(this.map.entrySet().asList());
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(this.map);
    }

    private static class SerializedForm<V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<?, V> map;

        SerializedForm(ImmutableMap<?, V> immutableMap) {
            this.map = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.map.values();
        }
    }
}
