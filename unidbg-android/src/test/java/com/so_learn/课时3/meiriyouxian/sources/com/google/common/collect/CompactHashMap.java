package com.google.common.collect;

import com.google.common.base.j;
import com.google.common.base.m;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* access modifiers changed from: package-private */
public class CompactHashMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final int DEFAULT_SIZE = 3;
    private static final long HASH_MASK = -4294967296L;
    private static final float LOAD_FACTOR = 1.0f;
    private static final long NEXT_MASK = 4294967295L;
    static final int UNSET = -1;
    transient long[] entries;
    private transient Set<Map.Entry<K, V>> entrySetView;
    private transient Set<K> keySetView;
    transient Object[] keys;
    transient int modCount;
    private transient int size;
    private transient int[] table;
    transient Object[] values;
    private transient Collection<V> valuesView;

    private static int getHash(long j) {
        return (int) (j >>> 32);
    }

    private static int getNext(long j) {
        return (int) j;
    }

    private static long swapNext(long j, int i) {
        return (j & HASH_MASK) | (((long) i) & 4294967295L);
    }

    /* access modifiers changed from: package-private */
    public void accessEntry(int i) {
    }

    /* access modifiers changed from: package-private */
    public int adjustAfterRemove(int i, int i2) {
        return i - 1;
    }

    public static <K, V> CompactHashMap<K, V> create() {
        return new CompactHashMap<>();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int i) {
        return new CompactHashMap<>(i);
    }

    CompactHashMap() {
        init(3);
    }

    CompactHashMap(int i) {
        init(i);
    }

    /* access modifiers changed from: package-private */
    public void init(int i) {
        m.a(i >= 0, "Expected size must be non-negative");
        this.modCount = Math.max(1, i);
    }

    /* access modifiers changed from: package-private */
    public boolean needsAllocArrays() {
        return this.table == null;
    }

    /* access modifiers changed from: package-private */
    public void allocArrays() {
        m.b(needsAllocArrays(), "Arrays already allocated");
        int i = this.modCount;
        this.table = newTable(af.a(i, 1.0d));
        this.entries = newEntries(i);
        this.keys = new Object[i];
        this.values = new Object[i];
    }

    private static int[] newTable(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private static long[] newEntries(int i) {
        long[] jArr = new long[i];
        Arrays.fill(jArr, -1L);
        return jArr;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (needsAllocArrays()) {
            allocArrays();
        }
        long[] jArr = this.entries;
        Object[] objArr = this.keys;
        Object[] objArr2 = this.values;
        int a2 = af.a(k);
        int hashTableMask = hashTableMask() & a2;
        int i = this.size;
        int[] iArr = this.table;
        int i2 = iArr[hashTableMask];
        if (i2 == -1) {
            iArr[hashTableMask] = i;
        } else {
            while (true) {
                long j = jArr[i2];
                if (getHash(j) != a2 || !j.a(k, objArr[i2])) {
                    int next = getNext(j);
                    if (next == -1) {
                        jArr[i2] = swapNext(j, i);
                        break;
                    }
                    i2 = next;
                } else {
                    V v2 = (V) objArr2[i2];
                    objArr2[i2] = v;
                    accessEntry(i2);
                    return v2;
                }
            }
        }
        if (i != Integer.MAX_VALUE) {
            int i3 = i + 1;
            resizeMeMaybe(i3);
            insertEntry(i, k, v, a2);
            this.size = i3;
            int length = this.table.length;
            if (af.a(i, length, 1.0d)) {
                resizeTable(length * 2);
            }
            this.modCount++;
            return null;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    /* access modifiers changed from: package-private */
    public void insertEntry(int i, K k, V v, int i2) {
        this.entries[i] = (((long) i2) << 32) | 4294967295L;
        this.keys[i] = k;
        this.values[i] = v;
    }

    private void resizeMeMaybe(int i) {
        int length = this.entries.length;
        if (i > length) {
            int max = Math.max(1, length >>> 1) + length;
            if (max < 0) {
                max = Integer.MAX_VALUE;
            }
            if (max != length) {
                resizeEntries(max);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void resizeEntries(int i) {
        this.keys = Arrays.copyOf(this.keys, i);
        this.values = Arrays.copyOf(this.values, i);
        long[] jArr = this.entries;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        if (i > length) {
            Arrays.fill(copyOf, length, i, -1L);
        }
        this.entries = copyOf;
    }

    private void resizeTable(int i) {
        int[] newTable = newTable(i);
        long[] jArr = this.entries;
        int length = newTable.length - 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            int hash = getHash(jArr[i2]);
            int i3 = hash & length;
            int i4 = newTable[i3];
            newTable[i3] = i2;
            jArr[i2] = (((long) hash) << 32) | (((long) i4) & 4294967295L);
        }
        this.table = newTable;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int indexOf(Object obj) {
        if (needsAllocArrays()) {
            return -1;
        }
        int a2 = af.a(obj);
        int i = this.table[hashTableMask() & a2];
        while (i != -1) {
            long j = this.entries[i];
            if (getHash(j) == a2 && j.a(obj, this.keys[i])) {
                return i;
            }
            i = getNext(j);
        }
        return -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        int indexOf = indexOf(obj);
        accessEntry(indexOf);
        if (indexOf == -1) {
            return null;
        }
        return (V) this.values[indexOf];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        if (needsAllocArrays()) {
            return null;
        }
        return (V) remove(obj, af.a(obj));
    }

    private V remove(Object obj, int i) {
        int hashTableMask = hashTableMask() & i;
        int i2 = this.table[hashTableMask];
        if (i2 == -1) {
            return null;
        }
        int i3 = -1;
        while (true) {
            if (getHash(this.entries[i2]) != i || !j.a(obj, this.keys[i2])) {
                int next = getNext(this.entries[i2]);
                if (next == -1) {
                    return null;
                }
                i3 = i2;
                i2 = next;
            } else {
                V v = (V) this.values[i2];
                if (i3 == -1) {
                    this.table[hashTableMask] = getNext(this.entries[i2]);
                } else {
                    long[] jArr = this.entries;
                    jArr[i3] = swapNext(jArr[i3], getNext(jArr[i2]));
                }
                moveLastEntry(i2);
                this.size--;
                this.modCount++;
                return v;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private V removeEntry(int i) {
        return (V) remove(this.keys[i], getHash(this.entries[i]));
    }

    /* access modifiers changed from: package-private */
    public void moveLastEntry(int i) {
        int size = size() - 1;
        if (i < size) {
            Object[] objArr = this.keys;
            objArr[i] = objArr[size];
            Object[] objArr2 = this.values;
            objArr2[i] = objArr2[size];
            objArr[size] = null;
            objArr2[size] = null;
            long[] jArr = this.entries;
            long j = jArr[size];
            jArr[i] = j;
            jArr[size] = -1;
            int hash = getHash(j) & hashTableMask();
            int[] iArr = this.table;
            int i2 = iArr[hash];
            if (i2 == size) {
                iArr[hash] = i;
                return;
            }
            while (true) {
                long j2 = this.entries[i2];
                int next = getNext(j2);
                if (next == size) {
                    this.entries[i2] = swapNext(j2, i);
                    return;
                }
                i2 = next;
            }
        } else {
            this.keys[i] = null;
            this.values[i] = null;
            this.entries[i] = -1;
        }
    }

    /* access modifiers changed from: package-private */
    public int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    /* access modifiers changed from: package-private */
    public int getSuccessor(int i) {
        int i2 = i + 1;
        if (i2 < this.size) {
            return i2;
        }
        return -1;
    }

    private abstract class b<T> implements Iterator<T> {
        int b;
        int c;
        int d;

        /* access modifiers changed from: package-private */
        public abstract T a(int i);

        private b() {
            this.b = CompactHashMap.this.modCount;
            this.c = CompactHashMap.this.firstEntryIndex();
            this.d = -1;
        }

        /* synthetic */ b(CompactHashMap compactHashMap, AnonymousClass1 r2) {
            this();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.c >= 0;
        }

        @Override // java.util.Iterator
        public T next() {
            a();
            if (hasNext()) {
                int i = this.c;
                this.d = i;
                T t = (T) a(i);
                this.c = CompactHashMap.this.getSuccessor(this.c);
                return t;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            a();
            n.a(this.d >= 0);
            this.b++;
            CompactHashMap.this.removeEntry(this.d);
            this.c = CompactHashMap.this.adjustAfterRemove(this.c, this.d);
            this.d = -1;
        }

        private void a() {
            if (CompactHashMap.this.modCount != this.b) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySetView;
        if (set != null) {
            return set;
        }
        Set<K> createKeySet = createKeySet();
        this.keySetView = createKeySet;
        return createKeySet;
    }

    /* access modifiers changed from: package-private */
    public Set<K> createKeySet() {
        return new c();
    }

    /* access modifiers changed from: package-private */
    public class c extends AbstractSet<K> {
        c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return CompactHashMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return CompactHashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int indexOf = CompactHashMap.this.indexOf(obj);
            if (indexOf == -1) {
                return false;
            }
            CompactHashMap.this.removeEntry(indexOf);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return CompactHashMap.this.keySetIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            CompactHashMap.this.clear();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.CompactHashMap$1  reason: invalid class name */
    public class AnonymousClass1 extends CompactHashMap<K, V>.b {
        AnonymousClass1() {
            super(CompactHashMap.this, null);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.CompactHashMap.b
        public K a(int i) {
            return (K) CompactHashMap.this.keys[i];
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<K> keySetIterator() {
        return new AnonymousClass1();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySetView;
        if (set != null) {
            return set;
        }
        Set<Map.Entry<K, V>> createEntrySet = createEntrySet();
        this.entrySetView = createEntrySet;
        return createEntrySet;
    }

    /* access modifiers changed from: package-private */
    public Set<Map.Entry<K, V>> createEntrySet() {
        return new a();
    }

    /* access modifiers changed from: package-private */
    public class a extends AbstractSet<Map.Entry<K, V>> {
        a() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return CompactHashMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return CompactHashMap.this.entrySetIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int indexOf = CompactHashMap.this.indexOf(entry.getKey());
            if (indexOf == -1 || !j.a(CompactHashMap.this.values[indexOf], entry.getValue())) {
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
            int indexOf = CompactHashMap.this.indexOf(entry.getKey());
            if (indexOf == -1 || !j.a(CompactHashMap.this.values[indexOf], entry.getValue())) {
                return false;
            }
            CompactHashMap.this.removeEntry(indexOf);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.CompactHashMap$2  reason: invalid class name */
    public class AnonymousClass2 extends CompactHashMap<K, V>.b {
        AnonymousClass2() {
            super(CompactHashMap.this, null);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Map.Entry<K, V> a(int i) {
            return new d(i);
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> entrySetIterator() {
        return new AnonymousClass2();
    }

    /* access modifiers changed from: package-private */
    public final class d extends b<K, V> {
        private final K b;
        private int c;

        d(int i) {
            this.b = (K) CompactHashMap.this.keys[i];
            this.c = i;
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public K getKey() {
            return this.b;
        }

        private void a() {
            int i = this.c;
            if (i == -1 || i >= CompactHashMap.this.size() || !j.a(this.b, CompactHashMap.this.keys[this.c])) {
                this.c = CompactHashMap.this.indexOf(this.b);
            }
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public V getValue() {
            a();
            if (this.c == -1) {
                return null;
            }
            return (V) CompactHashMap.this.values[this.c];
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public V setValue(V v) {
            a();
            if (this.c == -1) {
                CompactHashMap.this.put(this.b, v);
                return null;
            }
            V v2 = (V) CompactHashMap.this.values[this.c];
            CompactHashMap.this.values[this.c] = v;
            return v2;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        for (int i = 0; i < this.size; i++) {
            if (j.a(obj, this.values[i])) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.valuesView;
        if (collection != null) {
            return collection;
        }
        Collection<V> createValues = createValues();
        this.valuesView = createValues;
        return createValues;
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createValues() {
        return new e();
    }

    /* access modifiers changed from: package-private */
    public class e extends AbstractCollection<V> {
        e() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return CompactHashMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return CompactHashMap.this.valuesIterator();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.CompactHashMap$3  reason: invalid class name */
    public class AnonymousClass3 extends CompactHashMap<K, V>.b {
        AnonymousClass3() {
            super(CompactHashMap.this, null);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.CompactHashMap.b
        public V a(int i) {
            return (V) CompactHashMap.this.values[i];
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> valuesIterator() {
        return new AnonymousClass3();
    }

    public void trimToSize() {
        if (!needsAllocArrays()) {
            int i = this.size;
            if (i < this.entries.length) {
                resizeEntries(i);
            }
            int a2 = af.a(i, 1.0d);
            if (a2 < this.table.length) {
                resizeTable(a2);
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        if (!needsAllocArrays()) {
            this.modCount++;
            Arrays.fill(this.keys, 0, this.size, (Object) null);
            Arrays.fill(this.values, 0, this.size, (Object) null);
            Arrays.fill(this.table, -1);
            Arrays.fill(this.entries, 0, this.size, -1L);
            this.size = 0;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.size);
        int firstEntryIndex = firstEntryIndex();
        while (firstEntryIndex >= 0) {
            objectOutputStream.writeObject(this.keys[firstEntryIndex]);
            objectOutputStream.writeObject(this.values[firstEntryIndex]);
            firstEntryIndex = getSuccessor(firstEntryIndex);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            init(readInt);
            for (int i = 0; i < readInt; i++) {
                put(objectInputStream.readObject(), objectInputStream.readObject());
            }
            return;
        }
        throw new InvalidObjectException("Invalid size: " + readInt);
    }
}
