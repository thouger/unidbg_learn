package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class LinkedListMultimap<K, V> extends c<K, V> implements ah<K, V>, Serializable {
    private static final long serialVersionUID = 0;
    private transient f<K, V> head;
    private transient Map<K, e<K, V>> keyToKeyList;
    private transient int modCount;
    private transient int size;
    private transient f<K, V> tail;

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ Map asMap() {
        return super.asMap();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ boolean containsEntry(Object obj, Object obj2) {
        return super.containsEntry(obj, obj2);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai, java.lang.Object
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai, java.lang.Object
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ Set keySet() {
        return super.keySet();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public /* bridge */ /* synthetic */ aj keys() {
        return super.keys();
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

    @Override // com.google.common.collect.c, java.lang.Object
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    /* access modifiers changed from: private */
    public static final class f<K, V> extends b<K, V> {
        final K a;
        V b;
        f<K, V> c;
        f<K, V> d;
        f<K, V> e;
        f<K, V> f;

        f(K k, V v) {
            this.a = k;
            this.b = v;
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public K getKey() {
            return this.a;
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public V getValue() {
            return this.b;
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.b;
            this.b = v;
            return v2;
        }
    }

    /* access modifiers changed from: private */
    public static class e<K, V> {
        f<K, V> a;
        f<K, V> b;
        int c = 1;

        e(f<K, V> fVar) {
            this.a = fVar;
            this.b = fVar;
            fVar.f = null;
            fVar.e = null;
        }
    }

    public static <K, V> LinkedListMultimap<K, V> create() {
        return new LinkedListMultimap<>();
    }

    public static <K, V> LinkedListMultimap<K, V> create(int i) {
        return new LinkedListMultimap<>(i);
    }

    public static <K, V> LinkedListMultimap<K, V> create(ai<? extends K, ? extends V> aiVar) {
        return new LinkedListMultimap<>(aiVar);
    }

    LinkedListMultimap() {
        this(12);
    }

    private LinkedListMultimap(int i) {
        this.keyToKeyList = ao.a(i);
    }

    private LinkedListMultimap(ai<? extends K, ? extends V> aiVar) {
        this(aiVar.keySet().size());
        putAll(aiVar);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private f<K, V> addNode(K k, V v, f<K, V> fVar) {
        f<K, V> fVar2 = new f<>(k, v);
        if (this.head == null) {
            this.tail = fVar2;
            this.head = fVar2;
            this.keyToKeyList.put(k, new e<>(fVar2));
            this.modCount++;
        } else if (fVar == null) {
            f<K, V> fVar3 = this.tail;
            fVar3.c = fVar2;
            fVar2.d = fVar3;
            this.tail = fVar2;
            e<K, V> eVar = this.keyToKeyList.get(k);
            if (eVar == null) {
                this.keyToKeyList.put(k, new e<>(fVar2));
                this.modCount++;
            } else {
                eVar.c++;
                f<K, V> fVar4 = eVar.b;
                fVar4.e = fVar2;
                fVar2.f = fVar4;
                eVar.b = fVar2;
            }
        } else {
            this.keyToKeyList.get(k).c++;
            fVar2.d = fVar.d;
            fVar2.f = fVar.f;
            fVar2.c = fVar;
            fVar2.e = fVar;
            if (fVar.f == null) {
                this.keyToKeyList.get(k).a = fVar2;
            } else {
                fVar.f.e = fVar2;
            }
            if (fVar.d == null) {
                this.head = fVar2;
            } else {
                fVar.d.c = fVar2;
            }
            fVar.d = fVar2;
            fVar.f = fVar2;
        }
        this.size++;
        return fVar2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeNode(f<K, V> fVar) {
        if (fVar.d != null) {
            fVar.d.c = fVar.c;
        } else {
            this.head = fVar.c;
        }
        if (fVar.c != null) {
            fVar.c.d = fVar.d;
        } else {
            this.tail = fVar.d;
        }
        if (fVar.f == null && fVar.e == null) {
            this.keyToKeyList.remove(fVar.a).c = 0;
            this.modCount++;
        } else {
            e<K, V> eVar = this.keyToKeyList.get(fVar.a);
            eVar.c--;
            if (fVar.f == null) {
                eVar.a = fVar.e;
            } else {
                fVar.f.e = fVar.e;
            }
            if (fVar.e == null) {
                eVar.b = fVar.f;
            } else {
                fVar.e.f = fVar.f;
            }
        }
        this.size--;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAllNodes(Object obj) {
        Iterators.h(new h(obj));
    }

    /* access modifiers changed from: private */
    public static void checkElement(Object obj) {
        if (obj == null) {
            throw new NoSuchElementException();
        }
    }

    private class g implements ListIterator<Map.Entry<K, V>> {
        int a;
        f<K, V> b;
        f<K, V> c;
        f<K, V> d;
        int e = LinkedListMultimap.this.modCount;

        @Override // java.util.ListIterator
        public /* synthetic */ void set(Object obj) {
            a((Map.Entry) ((Map.Entry) obj));
        }

        g(int i) {
            int size = LinkedListMultimap.this.size();
            m.b(i, size);
            if (i < size / 2) {
                this.b = LinkedListMultimap.this.head;
                while (true) {
                    int i2 = i - 1;
                    if (i <= 0) {
                        break;
                    }
                    next();
                    i = i2;
                }
            } else {
                this.d = LinkedListMultimap.this.tail;
                this.a = size;
                while (true) {
                    int i3 = i + 1;
                    if (i >= size) {
                        break;
                    }
                    previous();
                    i = i3;
                }
            }
            this.c = null;
        }

        private void c() {
            if (LinkedListMultimap.this.modCount != this.e) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            c();
            return this.b != null;
        }

        /* renamed from: a */
        public f<K, V> next() {
            c();
            LinkedListMultimap.checkElement(this.b);
            f<K, V> fVar = this.b;
            this.c = fVar;
            this.d = fVar;
            this.b = fVar.c;
            this.a++;
            return this.c;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            c();
            n.a(this.c != null);
            f<K, V> fVar = this.c;
            if (fVar != this.b) {
                this.d = fVar.d;
                this.a--;
            } else {
                this.b = fVar.c;
            }
            LinkedListMultimap.this.removeNode(this.c);
            this.c = null;
            this.e = LinkedListMultimap.this.modCount;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            c();
            return this.d != null;
        }

        /* renamed from: b */
        public f<K, V> previous() {
            c();
            LinkedListMultimap.checkElement(this.d);
            f<K, V> fVar = this.d;
            this.c = fVar;
            this.b = fVar;
            this.d = fVar.d;
            this.a--;
            return this.c;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.a;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.a - 1;
        }

        public void a(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        /* renamed from: b */
        public void add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: package-private */
        public void a(V v) {
            m.b(this.c != null);
            this.c.b = v;
        }
    }

    private class d implements Iterator<K> {
        final Set<K> a;
        f<K, V> b;
        f<K, V> c;
        int d;

        private d() {
            this.a = Sets.a(LinkedListMultimap.this.keySet().size());
            this.b = LinkedListMultimap.this.head;
            this.d = LinkedListMultimap.this.modCount;
        }

        /* synthetic */ d(LinkedListMultimap linkedListMultimap, AnonymousClass1 r2) {
            this();
        }

        private void a() {
            if (LinkedListMultimap.this.modCount != this.d) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            a();
            return this.b != null;
        }

        @Override // java.util.Iterator
        public K next() {
            f<K, V> fVar;
            a();
            LinkedListMultimap.checkElement(this.b);
            this.c = this.b;
            this.a.add(this.c.a);
            do {
                this.b = this.b.c;
                fVar = this.b;
                if (fVar == null) {
                    break;
                }
            } while (!this.a.add(fVar.a));
            return this.c.a;
        }

        @Override // java.util.Iterator
        public void remove() {
            a();
            n.a(this.c != null);
            LinkedListMultimap.this.removeAllNodes(this.c.a);
            this.c = null;
            this.d = LinkedListMultimap.this.modCount;
        }
    }

    /* access modifiers changed from: private */
    public class h implements ListIterator<V> {
        final Object a;
        int b;
        f<K, V> c;
        f<K, V> d;
        f<K, V> e;

        h(Object obj) {
            f<K, V> fVar;
            this.a = obj;
            e eVar = (e) LinkedListMultimap.this.keyToKeyList.get(obj);
            if (eVar == null) {
                fVar = null;
            } else {
                fVar = eVar.a;
            }
            this.c = fVar;
        }

        public h(Object obj, int i) {
            int i2;
            f<K, V> fVar;
            f<K, V> fVar2;
            e eVar = (e) LinkedListMultimap.this.keyToKeyList.get(obj);
            if (eVar == null) {
                i2 = 0;
            } else {
                i2 = eVar.c;
            }
            m.b(i, i2);
            if (i < i2 / 2) {
                if (eVar == null) {
                    fVar = null;
                } else {
                    fVar = eVar.a;
                }
                this.c = fVar;
                while (true) {
                    int i3 = i - 1;
                    if (i <= 0) {
                        break;
                    }
                    next();
                    i = i3;
                }
            } else {
                if (eVar == null) {
                    fVar2 = null;
                } else {
                    fVar2 = eVar.b;
                }
                this.e = fVar2;
                this.b = i2;
                while (true) {
                    int i4 = i + 1;
                    if (i >= i2) {
                        break;
                    }
                    previous();
                    i = i4;
                }
            }
            this.a = obj;
            this.d = null;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.c != null;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public V next() {
            LinkedListMultimap.checkElement(this.c);
            f<K, V> fVar = this.c;
            this.d = fVar;
            this.e = fVar;
            this.c = fVar.e;
            this.b++;
            return this.d.b;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.e != null;
        }

        @Override // java.util.ListIterator
        public V previous() {
            LinkedListMultimap.checkElement(this.e);
            f<K, V> fVar = this.e;
            this.d = fVar;
            this.c = fVar;
            this.e = fVar.f;
            this.b--;
            return this.d.b;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.b;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.b - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            n.a(this.d != null);
            f<K, V> fVar = this.d;
            if (fVar != this.c) {
                this.e = fVar.f;
                this.b--;
            } else {
                this.c = fVar.e;
            }
            LinkedListMultimap.this.removeNode(this.d);
            this.d = null;
        }

        @Override // java.util.ListIterator
        public void set(V v) {
            m.b(this.d != null);
            this.d.b = v;
        }

        @Override // java.util.ListIterator
        public void add(V v) {
            this.e = LinkedListMultimap.this.addNode(this.a, v, this.c);
            this.b++;
            this.d = null;
        }
    }

    @Override // com.google.common.collect.ai
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override // com.google.common.collect.ai
    public boolean containsKey(Object obj) {
        return this.keyToKeyList.containsKey(obj);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public boolean put(K k, V v) {
        addNode(k, v, null);
        return true;
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai, com.google.common.collect.ah
    public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
        List<V> copy = getCopy(k);
        h hVar = new h(k);
        Iterator<? extends V> it2 = iterable.iterator();
        while (hVar.hasNext() && it2.hasNext()) {
            hVar.next();
            hVar.set(it2.next());
        }
        while (hVar.hasNext()) {
            hVar.next();
            hVar.remove();
        }
        while (it2.hasNext()) {
            hVar.add(it2.next());
        }
        return copy;
    }

    private List<V> getCopy(Object obj) {
        return Collections.unmodifiableList(Lists.a(new h(obj)));
    }

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    public List<V> removeAll(Object obj) {
        List<V> copy = getCopy(obj);
        removeAllNodes(obj);
        return copy;
    }

    @Override // com.google.common.collect.ai
    public void clear() {
        this.head = null;
        this.tail = null;
        this.keyToKeyList.clear();
        this.size = 0;
        this.modCount++;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.LinkedListMultimap$1  reason: invalid class name */
    public class AnonymousClass1 extends AbstractSequentialList<V> {
        final /* synthetic */ Object a;

        AnonymousClass1(Object obj) {
            this.a = obj;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            e eVar = (e) LinkedListMultimap.this.keyToKeyList.get(this.a);
            if (eVar == null) {
                return 0;
            }
            return eVar.c;
        }

        @Override // java.util.AbstractSequentialList, java.util.List, java.util.AbstractList
        public ListIterator<V> listIterator(int i) {
            return new h(this.a, i);
        }
    }

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    public List<V> get(K k) {
        return new AnonymousClass1(k);
    }

    class b extends Sets.b<K> {
        b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedListMultimap.this.keyToKeyList.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return new d(LinkedListMultimap.this, null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedListMultimap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return !LinkedListMultimap.this.removeAll(obj).isEmpty();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public Set<K> createKeySet() {
        return new b();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public aj<K> createKeys() {
        return new Multimaps.c(this);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public List<V> values() {
        return (List) super.values();
    }

    /* access modifiers changed from: package-private */
    public class c extends AbstractSequentialList<V> {
        c() {
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            return LinkedListMultimap.this.size;
        }

        /* renamed from: com.google.common.collect.LinkedListMultimap$c$1  reason: invalid class name */
        class AnonymousClass1 extends bd<Map.Entry<K, V>, V> {
            final /* synthetic */ g a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            AnonymousClass1(ListIterator listIterator, g gVar) {
                super(listIterator);
                this.a = gVar;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.bc
            public /* bridge */ /* synthetic */ Object a(Object obj) {
                return a((Map.Entry<K, Object>) ((Map.Entry) obj));
            }

            /* access modifiers changed from: package-private */
            public V a(Map.Entry<K, V> entry) {
                return entry.getValue();
            }

            @Override // com.google.common.collect.bd, java.util.ListIterator
            public void set(V v) {
                this.a.a((g) v);
            }
        }

        @Override // java.util.AbstractSequentialList, java.util.List, java.util.AbstractList
        public ListIterator<V> listIterator(int i) {
            g gVar = new g(i);
            return new AnonymousClass1(gVar, gVar);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public List<V> createValues() {
        return new c();
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public List<Map.Entry<K, V>> entries() {
        return (List) super.entries();
    }

    /* access modifiers changed from: package-private */
    public class a extends AbstractSequentialList<Map.Entry<K, V>> {
        a() {
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            return LinkedListMultimap.this.size;
        }

        @Override // java.util.AbstractSequentialList, java.util.List, java.util.AbstractList
        public ListIterator<Map.Entry<K, V>> listIterator(int i) {
            return new g(i);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public List<Map.Entry<K, V>> createEntries() {
        return new a();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public Iterator<Map.Entry<K, V>> entryIterator() {
        throw new AssertionError("should never be called");
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public Map<K, Collection<V>> createAsMap() {
        return new Multimaps.a(this);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        for (Map.Entry<K, V> entry : entries()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.keyToKeyList = CompactLinkedHashMap.create();
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }
}
