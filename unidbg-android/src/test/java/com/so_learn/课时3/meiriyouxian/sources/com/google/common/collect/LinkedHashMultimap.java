package com.google.common.collect;

import com.google.common.base.j;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedHashMultimap<K, V> extends LinkedHashMultimapGwtSerializationDependencies<K, V> {
    private static final int DEFAULT_KEY_CAPACITY = 16;
    private static final int DEFAULT_VALUE_SET_CAPACITY = 2;
    static final double VALUE_SET_LOAD_FACTOR = 1.0d;
    private static final long serialVersionUID = 1;
    private transient ValueEntry<K, V> multimapHeaderEntry;
    transient int valueSetCapacity = 2;

    /* access modifiers changed from: private */
    public interface b<K, V> {
        b<K, V> getPredecessorInValueSet();

        b<K, V> getSuccessorInValueSet();

        void setPredecessorInValueSet(b<K, V> bVar);

        void setSuccessorInValueSet(b<K, V> bVar);
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ Map asMap() {
        return super.asMap();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ boolean containsEntry(Object obj, Object obj2) {
        return super.containsEntry(obj, obj2);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ boolean containsValue(Object obj) {
        return super.containsValue(obj);
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.c, com.google.common.collect.ai, java.lang.Object
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.ai, com.google.common.collect.ah
    public /* bridge */ /* synthetic */ Set get(Object obj) {
        return super.get(obj);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai, java.lang.Object
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ aj keys() {
        return super.keys();
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ boolean put(Object obj, Object obj2) {
        return super.put(obj, obj2);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ boolean putAll(ai aiVar) {
        return super.putAll(aiVar);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ boolean putAll(Object obj, Iterable iterable) {
        return super.putAll(obj, iterable);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.ai, com.google.common.collect.ah
    public /* bridge */ /* synthetic */ Set removeAll(Object obj) {
        return super.removeAll(obj);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    @Override // com.google.common.collect.c, java.lang.Object
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public static <K, V> LinkedHashMultimap<K, V> create() {
        return new LinkedHashMultimap<>(16, 2);
    }

    public static <K, V> LinkedHashMultimap<K, V> create(int i, int i2) {
        return new LinkedHashMultimap<>(Maps.b(i), Maps.b(i2));
    }

    public static <K, V> LinkedHashMultimap<K, V> create(ai<? extends K, ? extends V> aiVar) {
        LinkedHashMultimap<K, V> create = create(aiVar.keySet().size(), 2);
        create.putAll(aiVar);
        return create;
    }

    /* access modifiers changed from: private */
    public static <K, V> void succeedsInValueSet(b<K, V> bVar, b<K, V> bVar2) {
        bVar.setSuccessorInValueSet(bVar2);
        bVar2.setPredecessorInValueSet(bVar);
    }

    /* access modifiers changed from: private */
    public static <K, V> void succeedsInMultimap(ValueEntry<K, V> valueEntry, ValueEntry<K, V> valueEntry2) {
        valueEntry.setSuccessorInMultimap(valueEntry2);
        valueEntry2.setPredecessorInMultimap(valueEntry);
    }

    /* access modifiers changed from: private */
    public static <K, V> void deleteFromValueSet(b<K, V> bVar) {
        succeedsInValueSet(bVar.getPredecessorInValueSet(), bVar.getSuccessorInValueSet());
    }

    /* access modifiers changed from: private */
    public static <K, V> void deleteFromMultimap(ValueEntry<K, V> valueEntry) {
        succeedsInMultimap(valueEntry.getPredecessorInMultimap(), valueEntry.getSuccessorInMultimap());
    }

    /* access modifiers changed from: package-private */
    public static final class ValueEntry<K, V> extends ImmutableEntry<K, V> implements b<K, V> {
        ValueEntry<K, V> nextInValueBucket;
        ValueEntry<K, V> predecessorInMultimap;
        b<K, V> predecessorInValueSet;
        final int smearedValueHash;
        ValueEntry<K, V> successorInMultimap;
        b<K, V> successorInValueSet;

        ValueEntry(K k, V v, int i, ValueEntry<K, V> valueEntry) {
            super(k, v);
            this.smearedValueHash = i;
            this.nextInValueBucket = valueEntry;
        }

        /* access modifiers changed from: package-private */
        public boolean matchesValue(Object obj, int i) {
            return this.smearedValueHash == i && j.a(getValue(), obj);
        }

        @Override // com.google.common.collect.LinkedHashMultimap.b
        public b<K, V> getPredecessorInValueSet() {
            return this.predecessorInValueSet;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.b
        public b<K, V> getSuccessorInValueSet() {
            return this.successorInValueSet;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.b
        public void setPredecessorInValueSet(b<K, V> bVar) {
            this.predecessorInValueSet = bVar;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.b
        public void setSuccessorInValueSet(b<K, V> bVar) {
            this.successorInValueSet = bVar;
        }

        public ValueEntry<K, V> getPredecessorInMultimap() {
            return this.predecessorInMultimap;
        }

        public ValueEntry<K, V> getSuccessorInMultimap() {
            return this.successorInMultimap;
        }

        public void setSuccessorInMultimap(ValueEntry<K, V> valueEntry) {
            this.successorInMultimap = valueEntry;
        }

        public void setPredecessorInMultimap(ValueEntry<K, V> valueEntry) {
            this.predecessorInMultimap = valueEntry;
        }
    }

    private LinkedHashMultimap(int i, int i2) {
        super(ao.b(i));
        n.a(i2, "expectedValuesPerKey");
        this.valueSetCapacity = i2;
        this.multimapHeaderEntry = new ValueEntry<>(null, null, 0, null);
        ValueEntry<K, V> valueEntry = this.multimapHeaderEntry;
        succeedsInMultimap(valueEntry, valueEntry);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
    public Set<V> createCollection() {
        return ao.d(this.valueSetCapacity);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMapBasedMultimap
    public Collection<V> createCollection(K k) {
        return new a(k, this.valueSetCapacity);
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c, com.google.common.collect.ai, com.google.common.collect.ah
    public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
        return super.replaceValues((Object) k, (Iterable) iterable);
    }

    @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c, com.google.common.collect.ai
    public Set<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public Set<K> keySet() {
        return super.keySet();
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c, com.google.common.collect.ai
    public Collection<V> values() {
        return super.values();
    }

    /* access modifiers changed from: package-private */
    public final class a extends Sets.b<V> implements b<K, V> {
        ValueEntry<K, V>[] a;
        private final K c;
        private int d = 0;
        private int e = 0;
        private b<K, V> f;
        private b<K, V> g;

        a(K k, int i) {
            this.c = k;
            this.f = this;
            this.g = this;
            this.a = new ValueEntry[af.a(i, LinkedHashMultimap.VALUE_SET_LOAD_FACTOR)];
        }

        private int a() {
            return this.a.length - 1;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.b
        public b<K, V> getPredecessorInValueSet() {
            return this.g;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.b
        public b<K, V> getSuccessorInValueSet() {
            return this.f;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.b
        public void setPredecessorInValueSet(b<K, V> bVar) {
            this.g = bVar;
        }

        @Override // com.google.common.collect.LinkedHashMultimap.b
        public void setSuccessorInValueSet(b<K, V> bVar) {
            this.f = bVar;
        }

        /* renamed from: com.google.common.collect.LinkedHashMultimap$a$1  reason: invalid class name */
        class AnonymousClass1 implements Iterator<V> {
            b<K, V> a = a.this.f;
            ValueEntry<K, V> b;
            int c = a.this.e;

            AnonymousClass1() {
            }

            private void a() {
                if (a.this.e != this.c) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                a();
                return this.a != a.this;
            }

            @Override // java.util.Iterator
            public V next() {
                if (hasNext()) {
                    ValueEntry<K, V> valueEntry = (ValueEntry) this.a;
                    V value = valueEntry.getValue();
                    this.b = valueEntry;
                    this.a = valueEntry.getSuccessorInValueSet();
                    return value;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                a();
                n.a(this.b != null);
                a.this.remove(this.b.getValue());
                this.c = a.this.e;
                this.b = null;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<V> iterator() {
            return new AnonymousClass1();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.d;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            int a = af.a(obj);
            for (ValueEntry<K, V> valueEntry = this.a[a() & a]; valueEntry != null; valueEntry = valueEntry.nextInValueBucket) {
                if (valueEntry.matchesValue(obj, a)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(V v) {
            int a = af.a(v);
            int a2 = a() & a;
            ValueEntry<K, V> valueEntry = this.a[a2];
            for (ValueEntry<K, V> valueEntry2 = valueEntry; valueEntry2 != null; valueEntry2 = valueEntry2.nextInValueBucket) {
                if (valueEntry2.matchesValue(v, a)) {
                    return false;
                }
            }
            ValueEntry<K, V> valueEntry3 = new ValueEntry<>(this.c, v, a, valueEntry);
            LinkedHashMultimap.succeedsInValueSet(this.g, valueEntry3);
            LinkedHashMultimap.succeedsInValueSet(valueEntry3, this);
            LinkedHashMultimap.succeedsInMultimap(LinkedHashMultimap.this.multimapHeaderEntry.getPredecessorInMultimap(), valueEntry3);
            LinkedHashMultimap.succeedsInMultimap(valueEntry3, LinkedHashMultimap.this.multimapHeaderEntry);
            this.a[a2] = valueEntry3;
            this.d++;
            this.e++;
            b();
            return true;
        }

        private void b() {
            if (af.a(this.d, this.a.length, LinkedHashMultimap.VALUE_SET_LOAD_FACTOR)) {
                ValueEntry<K, V>[] valueEntryArr = new ValueEntry[(this.a.length * 2)];
                this.a = valueEntryArr;
                int length = valueEntryArr.length - 1;
                for (b<K, V> bVar = this.f; bVar != this; bVar = bVar.getSuccessorInValueSet()) {
                    ValueEntry<K, V> valueEntry = (ValueEntry) bVar;
                    int i = valueEntry.smearedValueHash & length;
                    valueEntry.nextInValueBucket = valueEntryArr[i];
                    valueEntryArr[i] = valueEntry;
                }
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int a = af.a(obj);
            int a2 = a() & a;
            ValueEntry<K, V> valueEntry = this.a[a2];
            ValueEntry<K, V> valueEntry2 = null;
            while (true) {
                valueEntry2 = valueEntry;
                if (valueEntry2 == null) {
                    return false;
                }
                if (valueEntry2.matchesValue(obj, a)) {
                    if (valueEntry2 == null) {
                        this.a[a2] = valueEntry2.nextInValueBucket;
                    } else {
                        valueEntry2.nextInValueBucket = valueEntry2.nextInValueBucket;
                    }
                    LinkedHashMultimap.deleteFromValueSet(valueEntry2);
                    LinkedHashMultimap.deleteFromMultimap(valueEntry2);
                    this.d--;
                    this.e++;
                    return true;
                }
                valueEntry = valueEntry2.nextInValueBucket;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Arrays.fill(this.a, (Object) null);
            this.d = 0;
            for (b<K, V> bVar = this.f; bVar != this; bVar = bVar.getSuccessorInValueSet()) {
                LinkedHashMultimap.deleteFromMultimap((ValueEntry) bVar);
            }
            LinkedHashMultimap.succeedsInValueSet(this, this);
            this.e++;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.LinkedHashMultimap$1  reason: invalid class name */
    public class AnonymousClass1 implements Iterator<Map.Entry<K, V>> {
        ValueEntry<K, V> a = LinkedHashMultimap.this.multimapHeaderEntry.successorInMultimap;
        ValueEntry<K, V> b;

        AnonymousClass1() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.a != LinkedHashMultimap.this.multimapHeaderEntry;
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            if (hasNext()) {
                ValueEntry<K, V> valueEntry = this.a;
                this.b = valueEntry;
                this.a = valueEntry.successorInMultimap;
                return valueEntry;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            n.a(this.b != null);
            LinkedHashMultimap.this.remove(this.b.getKey(), this.b.getValue());
            this.b = null;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c
    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new AnonymousClass1();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c
    public Iterator<V> valueIterator() {
        return Maps.b(entryIterator());
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.ai
    public void clear() {
        super.clear();
        ValueEntry<K, V> valueEntry = this.multimapHeaderEntry;
        succeedsInMultimap(valueEntry, valueEntry);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(keySet().size());
        for (K k : keySet()) {
            objectOutputStream.writeObject(k);
        }
        objectOutputStream.writeInt(size());
        for (Map.Entry<K, V> entry : entries()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v2, resolved type: java.util.Map */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.multimapHeaderEntry = new ValueEntry<>(null, null, 0, null);
        ValueEntry<K, V> valueEntry = this.multimapHeaderEntry;
        succeedsInMultimap(valueEntry, valueEntry);
        this.valueSetCapacity = 2;
        int readInt = objectInputStream.readInt();
        Map b2 = ao.b(12);
        for (int i = 0; i < readInt; i++) {
            Object readObject = objectInputStream.readObject();
            b2.put(readObject, createCollection(readObject));
        }
        int readInt2 = objectInputStream.readInt();
        for (int i2 = 0; i2 < readInt2; i2++) {
            ((Collection) b2.get(objectInputStream.readObject())).add(objectInputStream.readObject());
        }
        setMap(b2);
    }
}
