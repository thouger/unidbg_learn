package com.google.common.collect;

import com.google.common.base.j;
import com.google.common.base.m;
import com.google.common.collect.ImmutableCollection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class HashBiMap<K, V> extends AbstractMap<K, V> implements k<K, V>, Serializable {
    private static final int ABSENT = -1;
    private static final int ENDPOINT = -2;
    private transient Set<Map.Entry<K, V>> entrySet;
    private transient int firstInInsertionOrder;
    private transient int[] hashTableKToV;
    private transient int[] hashTableVToK;
    private transient k<V, K> inverse;
    private transient Set<K> keySet;
    transient K[] keys;
    private transient int lastInInsertionOrder;
    transient int modCount;
    private transient int[] nextInBucketKToV;
    private transient int[] nextInBucketVToK;
    private transient int[] nextInInsertionOrder;
    private transient int[] prevInInsertionOrder;
    transient int size;
    private transient Set<V> valueSet;
    transient V[] values;

    public static <K, V> HashBiMap<K, V> create() {
        return create(16);
    }

    public static <K, V> HashBiMap<K, V> create(int i) {
        return new HashBiMap<>(i);
    }

    public static <K, V> HashBiMap<K, V> create(Map<? extends K, ? extends V> map) {
        HashBiMap<K, V> create = create(map.size());
        create.putAll(map);
        return create;
    }

    private HashBiMap(int i) {
        init(i);
    }

    /* access modifiers changed from: package-private */
    public void init(int i) {
        n.a(i, "expectedSize");
        int a2 = af.a(i, 1.0d);
        this.size = 0;
        this.keys = (K[]) new Object[i];
        this.values = (V[]) new Object[i];
        this.hashTableKToV = createFilledWithAbsent(a2);
        this.hashTableVToK = createFilledWithAbsent(a2);
        this.nextInBucketKToV = createFilledWithAbsent(i);
        this.nextInBucketVToK = createFilledWithAbsent(i);
        this.firstInInsertionOrder = -2;
        this.lastInInsertionOrder = -2;
        this.prevInInsertionOrder = createFilledWithAbsent(i);
        this.nextInInsertionOrder = createFilledWithAbsent(i);
    }

    private static int[] createFilledWithAbsent(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private static int[] expandAndFillWithAbsent(int[] iArr, int i) {
        int length = iArr.length;
        int[] copyOf = Arrays.copyOf(iArr, i);
        Arrays.fill(copyOf, length, i, -1);
        return copyOf;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    private void ensureCapacity(int i) {
        int[] iArr = this.nextInBucketKToV;
        if (iArr.length < i) {
            int a2 = ImmutableCollection.b.a(iArr.length, i);
            this.keys = (K[]) Arrays.copyOf(this.keys, a2);
            this.values = (V[]) Arrays.copyOf(this.values, a2);
            this.nextInBucketKToV = expandAndFillWithAbsent(this.nextInBucketKToV, a2);
            this.nextInBucketVToK = expandAndFillWithAbsent(this.nextInBucketVToK, a2);
            this.prevInInsertionOrder = expandAndFillWithAbsent(this.prevInInsertionOrder, a2);
            this.nextInInsertionOrder = expandAndFillWithAbsent(this.nextInInsertionOrder, a2);
        }
        if (this.hashTableKToV.length < i) {
            int a3 = af.a(i, 1.0d);
            this.hashTableKToV = createFilledWithAbsent(a3);
            this.hashTableVToK = createFilledWithAbsent(a3);
            for (int i2 = 0; i2 < this.size; i2++) {
                int bucket = bucket(af.a(this.keys[i2]));
                int[] iArr2 = this.nextInBucketKToV;
                int[] iArr3 = this.hashTableKToV;
                iArr2[i2] = iArr3[bucket];
                iArr3[bucket] = i2;
                int bucket2 = bucket(af.a(this.values[i2]));
                int[] iArr4 = this.nextInBucketVToK;
                int[] iArr5 = this.hashTableVToK;
                iArr4[i2] = iArr5[bucket2];
                iArr5[bucket2] = i2;
            }
        }
    }

    private int bucket(int i) {
        return i & (this.hashTableKToV.length - 1);
    }

    /* access modifiers changed from: package-private */
    public int findEntryByKey(Object obj) {
        return findEntryByKey(obj, af.a(obj));
    }

    /* access modifiers changed from: package-private */
    public int findEntryByKey(Object obj, int i) {
        return findEntry(obj, i, this.hashTableKToV, this.nextInBucketKToV, this.keys);
    }

    /* access modifiers changed from: package-private */
    public int findEntryByValue(Object obj) {
        return findEntryByValue(obj, af.a(obj));
    }

    /* access modifiers changed from: package-private */
    public int findEntryByValue(Object obj, int i) {
        return findEntry(obj, i, this.hashTableVToK, this.nextInBucketVToK, this.values);
    }

    /* access modifiers changed from: package-private */
    public int findEntry(Object obj, int i, int[] iArr, int[] iArr2, Object[] objArr) {
        int i2 = iArr[bucket(i)];
        while (i2 != -1) {
            if (j.a(objArr[i2], obj)) {
                return i2;
            }
            i2 = iArr2[i2];
        }
        return -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return findEntryByKey(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        return findEntryByValue(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        int findEntryByKey = findEntryByKey(obj);
        if (findEntryByKey == -1) {
            return null;
        }
        return this.values[findEntryByKey];
    }

    /* access modifiers changed from: package-private */
    public K getInverse(Object obj) {
        int findEntryByValue = findEntryByValue(obj);
        if (findEntryByValue == -1) {
            return null;
        }
        return this.keys[findEntryByValue];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        return (V) put(k, v, false);
    }

    /* access modifiers changed from: package-private */
    public V put(K k, V v, boolean z) {
        int a2 = af.a(k);
        int findEntryByKey = findEntryByKey(k, a2);
        if (findEntryByKey != -1) {
            V v2 = this.values[findEntryByKey];
            if (j.a(v2, v)) {
                return v;
            }
            replaceValueInEntry(findEntryByKey, v, z);
            return v2;
        }
        int a3 = af.a(v);
        int findEntryByValue = findEntryByValue(v, a3);
        if (!z) {
            m.a(findEntryByValue == -1, "Value already present: %s", v);
        } else if (findEntryByValue != -1) {
            removeEntryValueHashKnown(findEntryByValue, a3);
        }
        ensureCapacity(this.size + 1);
        K[] kArr = this.keys;
        int i = this.size;
        kArr[i] = k;
        this.values[i] = v;
        insertIntoTableKToV(i, a2);
        insertIntoTableVToK(this.size, a3);
        setSucceeds(this.lastInInsertionOrder, this.size);
        setSucceeds(this.size, -2);
        this.size++;
        this.modCount++;
        return null;
    }

    @Override // com.google.common.collect.k
    public V forcePut(K k, V v) {
        return (V) put(k, v, true);
    }

    /* access modifiers changed from: package-private */
    public K putInverse(V v, K k, boolean z) {
        int a2 = af.a(v);
        int findEntryByValue = findEntryByValue(v, a2);
        if (findEntryByValue != -1) {
            K k2 = this.keys[findEntryByValue];
            if (j.a(k2, k)) {
                return k;
            }
            replaceKeyInEntry(findEntryByValue, k, z);
            return k2;
        }
        int i = this.lastInInsertionOrder;
        int a3 = af.a(k);
        int findEntryByKey = findEntryByKey(k, a3);
        if (!z) {
            m.a(findEntryByKey == -1, "Key already present: %s", k);
        } else if (findEntryByKey != -1) {
            i = this.prevInInsertionOrder[findEntryByKey];
            removeEntryKeyHashKnown(findEntryByKey, a3);
        }
        ensureCapacity(this.size + 1);
        K[] kArr = this.keys;
        int i2 = this.size;
        kArr[i2] = k;
        this.values[i2] = v;
        insertIntoTableKToV(i2, a3);
        insertIntoTableVToK(this.size, a2);
        int i3 = i == -2 ? this.firstInInsertionOrder : this.nextInInsertionOrder[i];
        setSucceeds(i, this.size);
        setSucceeds(this.size, i3);
        this.size++;
        this.modCount++;
        return null;
    }

    private void setSucceeds(int i, int i2) {
        if (i == -2) {
            this.firstInInsertionOrder = i2;
        } else {
            this.nextInInsertionOrder[i] = i2;
        }
        if (i2 == -2) {
            this.lastInInsertionOrder = i;
        } else {
            this.prevInInsertionOrder[i2] = i;
        }
    }

    private void insertIntoTableKToV(int i, int i2) {
        m.a(i != -1);
        int bucket = bucket(i2);
        int[] iArr = this.nextInBucketKToV;
        int[] iArr2 = this.hashTableKToV;
        iArr[i] = iArr2[bucket];
        iArr2[bucket] = i;
    }

    private void insertIntoTableVToK(int i, int i2) {
        m.a(i != -1);
        int bucket = bucket(i2);
        int[] iArr = this.nextInBucketVToK;
        int[] iArr2 = this.hashTableVToK;
        iArr[i] = iArr2[bucket];
        iArr2[bucket] = i;
    }

    private void deleteFromTableKToV(int i, int i2) {
        m.a(i != -1);
        int bucket = bucket(i2);
        int[] iArr = this.hashTableKToV;
        if (iArr[bucket] == i) {
            int[] iArr2 = this.nextInBucketKToV;
            iArr[bucket] = iArr2[i];
            iArr2[i] = -1;
            return;
        }
        int i3 = iArr[bucket];
        int i4 = this.nextInBucketKToV[i3];
        while (true) {
            i3 = i4;
            if (i3 == -1) {
                throw new AssertionError("Expected to find entry with key " + ((Object) this.keys[i]));
            } else if (i3 == i) {
                int[] iArr3 = this.nextInBucketKToV;
                iArr3[i3] = iArr3[i];
                iArr3[i] = -1;
                return;
            } else {
                i4 = this.nextInBucketKToV[i3];
            }
        }
    }

    private void deleteFromTableVToK(int i, int i2) {
        m.a(i != -1);
        int bucket = bucket(i2);
        int[] iArr = this.hashTableVToK;
        if (iArr[bucket] == i) {
            int[] iArr2 = this.nextInBucketVToK;
            iArr[bucket] = iArr2[i];
            iArr2[i] = -1;
            return;
        }
        int i3 = iArr[bucket];
        int i4 = this.nextInBucketVToK[i3];
        while (true) {
            i3 = i4;
            if (i3 == -1) {
                throw new AssertionError("Expected to find entry with value " + ((Object) this.values[i]));
            } else if (i3 == i) {
                int[] iArr3 = this.nextInBucketVToK;
                iArr3[i3] = iArr3[i];
                iArr3[i] = -1;
                return;
            } else {
                i4 = this.nextInBucketVToK[i3];
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void replaceValueInEntry(int i, V v, boolean z) {
        m.a(i != -1);
        int a2 = af.a(v);
        int findEntryByValue = findEntryByValue(v, a2);
        if (findEntryByValue != -1) {
            if (z) {
                removeEntryValueHashKnown(findEntryByValue, a2);
                if (i == this.size) {
                    i = findEntryByValue;
                }
            } else {
                throw new IllegalArgumentException("Value already present in map: " + ((Object) v));
            }
        }
        deleteFromTableVToK(i, af.a(this.values[i]));
        this.values[i] = v;
        insertIntoTableVToK(i, a2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void replaceKeyInEntry(int i, K k, boolean z) {
        int i2;
        int i3;
        m.a(i != -1);
        int a2 = af.a(k);
        int findEntryByKey = findEntryByKey(k, a2);
        int i4 = this.lastInInsertionOrder;
        if (findEntryByKey == -1) {
            i2 = i4;
            i3 = -2;
        } else if (z) {
            i2 = this.prevInInsertionOrder[findEntryByKey];
            i3 = this.nextInInsertionOrder[findEntryByKey];
            removeEntryKeyHashKnown(findEntryByKey, a2);
            if (i == this.size) {
                i = findEntryByKey;
            }
        } else {
            throw new IllegalArgumentException("Key already present in map: " + ((Object) k));
        }
        if (i2 == i) {
            i2 = this.prevInInsertionOrder[i];
        } else if (i2 == this.size) {
            i2 = findEntryByKey;
        }
        if (i3 == i) {
            findEntryByKey = this.nextInInsertionOrder[i];
        } else if (i3 != this.size) {
            findEntryByKey = i3;
        }
        setSucceeds(this.prevInInsertionOrder[i], this.nextInInsertionOrder[i]);
        deleteFromTableKToV(i, af.a(this.keys[i]));
        this.keys[i] = k;
        insertIntoTableKToV(i, af.a(k));
        setSucceeds(i2, i);
        setSucceeds(i, findEntryByKey);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        int a2 = af.a(obj);
        int findEntryByKey = findEntryByKey(obj, a2);
        if (findEntryByKey == -1) {
            return null;
        }
        V v = this.values[findEntryByKey];
        removeEntryKeyHashKnown(findEntryByKey, a2);
        return v;
    }

    /* access modifiers changed from: package-private */
    public K removeInverse(Object obj) {
        int a2 = af.a(obj);
        int findEntryByValue = findEntryByValue(obj, a2);
        if (findEntryByValue == -1) {
            return null;
        }
        K k = this.keys[findEntryByValue];
        removeEntryValueHashKnown(findEntryByValue, a2);
        return k;
    }

    /* access modifiers changed from: package-private */
    public void removeEntry(int i) {
        removeEntryKeyHashKnown(i, af.a(this.keys[i]));
    }

    private void removeEntry(int i, int i2, int i3) {
        m.a(i != -1);
        deleteFromTableKToV(i, i2);
        deleteFromTableVToK(i, i3);
        setSucceeds(this.prevInInsertionOrder[i], this.nextInInsertionOrder[i]);
        moveEntryToIndex(this.size - 1, i);
        K[] kArr = this.keys;
        int i4 = this.size;
        kArr[i4 - 1] = null;
        this.values[i4 - 1] = null;
        this.size = i4 - 1;
        this.modCount++;
    }

    /* access modifiers changed from: package-private */
    public void removeEntryKeyHashKnown(int i, int i2) {
        removeEntry(i, i2, af.a(this.values[i]));
    }

    /* access modifiers changed from: package-private */
    public void removeEntryValueHashKnown(int i, int i2) {
        removeEntry(i, af.a(this.keys[i]), i2);
    }

    private void moveEntryToIndex(int i, int i2) {
        if (i != i2) {
            int i3 = this.prevInInsertionOrder[i];
            int i4 = this.nextInInsertionOrder[i];
            setSucceeds(i3, i2);
            setSucceeds(i2, i4);
            K[] kArr = this.keys;
            K k = kArr[i];
            V[] vArr = this.values;
            V v = vArr[i];
            kArr[i2] = k;
            vArr[i2] = v;
            int bucket = bucket(af.a(k));
            int[] iArr = this.hashTableKToV;
            if (iArr[bucket] == i) {
                iArr[bucket] = i2;
            } else {
                int i5 = iArr[bucket];
                int i6 = this.nextInBucketKToV[i5];
                while (true) {
                    i5 = i6;
                    if (i5 == i) {
                        break;
                    }
                    i6 = this.nextInBucketKToV[i5];
                }
                this.nextInBucketKToV[i5] = i2;
            }
            int[] iArr2 = this.nextInBucketKToV;
            iArr2[i2] = iArr2[i];
            iArr2[i] = -1;
            int bucket2 = bucket(af.a(v));
            int[] iArr3 = this.hashTableVToK;
            if (iArr3[bucket2] == i) {
                iArr3[bucket2] = i2;
            } else {
                int i7 = iArr3[bucket2];
                int i8 = this.nextInBucketVToK[i7];
                while (true) {
                    i7 = i8;
                    if (i7 == i) {
                        break;
                    }
                    i8 = this.nextInBucketVToK[i7];
                }
                this.nextInBucketVToK[i7] = i2;
            }
            int[] iArr4 = this.nextInBucketVToK;
            iArr4[i2] = iArr4[i];
            iArr4[i] = -1;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, (Object) null);
        Arrays.fill(this.hashTableKToV, -1);
        Arrays.fill(this.hashTableVToK, -1);
        Arrays.fill(this.nextInBucketKToV, 0, this.size, -1);
        Arrays.fill(this.nextInBucketVToK, 0, this.size, -1);
        Arrays.fill(this.prevInInsertionOrder, 0, this.size, -1);
        Arrays.fill(this.nextInInsertionOrder, 0, this.size, -1);
        this.size = 0;
        this.firstInInsertionOrder = -2;
        this.lastInInsertionOrder = -2;
        this.modCount++;
    }

    /* access modifiers changed from: package-private */
    public static abstract class g<K, V, T> extends AbstractSet<T> {
        final HashBiMap<K, V> b;

        /* access modifiers changed from: package-private */
        public abstract T b(int i);

        g(HashBiMap<K, V> hashBiMap) {
            this.b = hashBiMap;
        }

        /* renamed from: com.google.common.collect.HashBiMap$g$1  reason: invalid class name */
        class AnonymousClass1 implements Iterator<T> {
            private int b = ((HashBiMap) g.this.b).firstInInsertionOrder;
            private int c = -1;
            private int d = g.this.b.modCount;
            private int e = g.this.b.size;

            AnonymousClass1() {
            }

            private void a() {
                if (g.this.b.modCount != this.d) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                a();
                return this.b != -2 && this.e > 0;
            }

            @Override // java.util.Iterator
            public T next() {
                if (hasNext()) {
                    T t = (T) g.this.b(this.b);
                    this.c = this.b;
                    this.b = ((HashBiMap) g.this.b).nextInInsertionOrder[this.b];
                    this.e--;
                    return t;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                a();
                n.a(this.c != -1);
                g.this.b.removeEntry(this.c);
                if (this.b == g.this.b.size) {
                    this.b = this.c;
                }
                this.c = -1;
                this.d = g.this.b.modCount;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<T> iterator() {
            return new AnonymousClass1();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.b.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.b.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        e eVar = new e();
        this.keySet = eVar;
        return eVar;
    }

    /* access modifiers changed from: package-private */
    public final class e extends g<K, V, K> {
        e() {
            super(HashBiMap.this);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.g
        public K b(int i) {
            return HashBiMap.this.keys[i];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return HashBiMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int a = af.a(obj);
            int findEntryByKey = HashBiMap.this.findEntryByKey(obj, a);
            if (findEntryByKey == -1) {
                return false;
            }
            HashBiMap.this.removeEntryKeyHashKnown(findEntryByKey, a);
            return true;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.k
    public Set<V> values() {
        Set<V> set = this.valueSet;
        if (set != null) {
            return set;
        }
        f fVar = new f();
        this.valueSet = fVar;
        return fVar;
    }

    /* access modifiers changed from: package-private */
    public final class f extends g<K, V, V> {
        f() {
            super(HashBiMap.this);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.g
        public V b(int i) {
            return HashBiMap.this.values[i];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return HashBiMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int a = af.a(obj);
            int findEntryByValue = HashBiMap.this.findEntryByValue(obj, a);
            if (findEntryByValue == -1) {
                return false;
            }
            HashBiMap.this.removeEntryValueHashKnown(findEntryByValue, a);
            return true;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        c cVar = new c();
        this.entrySet = cVar;
        return cVar;
    }

    final class c extends g<K, V, Map.Entry<K, V>> {
        c() {
            super(HashBiMap.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int findEntryByKey = HashBiMap.this.findEntryByKey(key);
            if (findEntryByKey == -1 || !j.a(value, HashBiMap.this.values[findEntryByKey])) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int a = af.a(key);
            int findEntryByKey = HashBiMap.this.findEntryByKey(key, a);
            if (findEntryByKey == -1 || !j.a(value, HashBiMap.this.values[findEntryByKey])) {
                return false;
            }
            HashBiMap.this.removeEntryKeyHashKnown(findEntryByKey, a);
            return true;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public Map.Entry<K, V> b(int i) {
            return new a(i);
        }
    }

    /* access modifiers changed from: package-private */
    public final class a extends b<K, V> {
        final K a;
        int b;

        a(int i) {
            this.a = HashBiMap.this.keys[i];
            this.b = i;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            int i = this.b;
            if (i == -1 || i > HashBiMap.this.size || !j.a(HashBiMap.this.keys[this.b], this.a)) {
                this.b = HashBiMap.this.findEntryByKey(this.a);
            }
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public K getKey() {
            return this.a;
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public V getValue() {
            a();
            if (this.b == -1) {
                return null;
            }
            return HashBiMap.this.values[this.b];
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public V setValue(V v) {
            a();
            if (this.b == -1) {
                return (V) HashBiMap.this.put(this.a, v);
            }
            V v2 = HashBiMap.this.values[this.b];
            if (j.a(v2, v)) {
                return v;
            }
            HashBiMap.this.replaceValueInEntry(this.b, v, false);
            return v2;
        }
    }

    @Override // com.google.common.collect.k
    public k<V, K> inverse() {
        k<V, K> kVar = this.inverse;
        if (kVar != null) {
            return kVar;
        }
        Inverse inverse = new Inverse(this);
        this.inverse = inverse;
        return inverse;
    }

    static class Inverse<K, V> extends AbstractMap<V, K> implements k<V, K>, Serializable {
        private final HashBiMap<K, V> forward;
        private transient Set<Map.Entry<V, K>> inverseEntrySet;

        Inverse(HashBiMap<K, V> hashBiMap) {
            this.forward = hashBiMap;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.forward.size;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.forward.containsValue(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public K get(Object obj) {
            return (K) this.forward.getInverse(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsValue(Object obj) {
            return this.forward.containsKey(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public K put(V v, K k) {
            return (K) this.forward.putInverse(v, k, false);
        }

        @Override // com.google.common.collect.k
        public K forcePut(V v, K k) {
            return (K) this.forward.putInverse(v, k, true);
        }

        @Override // com.google.common.collect.k
        public k<K, V> inverse() {
            return this.forward;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public K remove(Object obj) {
            return (K) this.forward.removeInverse(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            this.forward.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<V> keySet() {
            return this.forward.values();
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.k
        public Set<K> values() {
            return this.forward.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<V, K>> entrySet() {
            Set<Map.Entry<V, K>> set = this.inverseEntrySet;
            if (set != null) {
                return set;
            }
            d dVar = new d(this.forward);
            this.inverseEntrySet = dVar;
            return dVar;
        }

        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            ((HashBiMap) this.forward).inverse = this;
        }
    }

    static class d<K, V> extends g<K, V, Map.Entry<V, K>> {
        d(HashBiMap<K, V> hashBiMap) {
            super(hashBiMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int findEntryByValue = this.b.findEntryByValue(key);
            if (findEntryByValue == -1 || !j.a(this.b.keys[findEntryByValue], value)) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int a = af.a(key);
            int findEntryByValue = this.b.findEntryByValue(key, a);
            if (findEntryByValue == -1 || !j.a(this.b.keys[findEntryByValue], value)) {
                return false;
            }
            this.b.removeEntryValueHashKnown(findEntryByValue, a);
            return true;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public Map.Entry<V, K> b(int i) {
            return new b(this.b, i);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class b<K, V> extends b<V, K> {
        final HashBiMap<K, V> a;
        final V b;
        int c;

        b(HashBiMap<K, V> hashBiMap, int i) {
            this.a = hashBiMap;
            this.b = hashBiMap.values[i];
            this.c = i;
        }

        private void a() {
            int i = this.c;
            if (i == -1 || i > this.a.size || !j.a(this.b, this.a.values[this.c])) {
                this.c = this.a.findEntryByValue(this.b);
            }
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public V getKey() {
            return this.b;
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public K getValue() {
            a();
            if (this.c == -1) {
                return null;
            }
            return this.a.keys[this.c];
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public K setValue(K k) {
            a();
            if (this.c == -1) {
                return (K) this.a.putInverse(this.b, k, false);
            }
            K k2 = this.a.keys[this.c];
            if (j.a(k2, k)) {
                return k;
            }
            this.a.replaceKeyInEntry(this.c, k, false);
            return k2;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        as.a(this, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int a2 = as.a(objectInputStream);
        init(16);
        as.a(this, objectInputStream, a2);
    }
}
