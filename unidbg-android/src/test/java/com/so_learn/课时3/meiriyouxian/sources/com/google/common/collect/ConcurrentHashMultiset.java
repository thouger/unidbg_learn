package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.aj;
import com.google.common.collect.as;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class ConcurrentHashMultiset<E> extends d<E> implements Serializable {
    private static final long serialVersionUID = 1;
    private final transient ConcurrentMap<E, AtomicInteger> countMap;

    private static class b {
        static final as.a<ConcurrentHashMultiset> a = as.a(ConcurrentHashMultiset.class, "countMap");
    }

    @Override // com.google.common.collect.d, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
    public /* bridge */ /* synthetic */ boolean contains(Object obj) {
        return super.contains(obj);
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    public static <E> ConcurrentHashMultiset<E> create() {
        return new ConcurrentHashMultiset<>(new ConcurrentHashMap());
    }

    public static <E> ConcurrentHashMultiset<E> create(Iterable<? extends E> iterable) {
        ConcurrentHashMultiset<E> create = create();
        ag.a((Collection) create, (Iterable) iterable);
        return create;
    }

    public static <E> ConcurrentHashMultiset<E> create(ConcurrentMap<E, AtomicInteger> concurrentMap) {
        return new ConcurrentHashMultiset<>(concurrentMap);
    }

    ConcurrentHashMultiset(ConcurrentMap<E, AtomicInteger> concurrentMap) {
        m.a(concurrentMap.isEmpty(), "the backing map (%s) must be empty", concurrentMap);
        this.countMap = concurrentMap;
    }

    @Override // com.google.common.collect.aj
    public int count(Object obj) {
        AtomicInteger atomicInteger = (AtomicInteger) Maps.a((Map<?, Object>) this.countMap, obj);
        if (atomicInteger == null) {
            return 0;
        }
        return atomicInteger.get();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
    public int size() {
        long j = 0;
        for (AtomicInteger atomicInteger : this.countMap.values()) {
            j += (long) atomicInteger.get();
        }
        return Ints.b(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return snapshot().toArray();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) snapshot().toArray(tArr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.ArrayList */
    /* JADX WARN: Multi-variable type inference failed */
    private List<E> snapshot() {
        ArrayList c = Lists.c(size());
        for (E e : entrySet()) {
            Object element = e.getElement();
            for (int count = e.getCount(); count > 0; count--) {
                c.add(element);
            }
        }
        return c;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005d, code lost:
        r2 = new java.util.concurrent.atomic.AtomicInteger(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0068, code lost:
        if (r4.countMap.putIfAbsent(r5, r2) == null) goto L_0x0072;
     */
    @Override // com.google.common.collect.d, com.google.common.collect.aj
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int add(E r5, int r6) {
        /*
            r4 = this;
            com.google.common.base.m.a(r5)
            if (r6 != 0) goto L_0x000a
            int r5 = r4.count(r5)
            return r5
        L_0x000a:
            java.lang.String r0 = "occurences"
            com.google.common.collect.n.b(r6, r0)
        L_0x0010:
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r0 = r4.countMap
            java.lang.Object r0 = com.google.common.collect.Maps.a(r0, r5)
            java.util.concurrent.atomic.AtomicInteger r0 = (java.util.concurrent.atomic.AtomicInteger) r0
            r1 = 0
            if (r0 != 0) goto L_0x002b
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r0 = r4.countMap
            java.util.concurrent.atomic.AtomicInteger r2 = new java.util.concurrent.atomic.AtomicInteger
            r2.<init>(r6)
            java.lang.Object r0 = r0.putIfAbsent(r5, r2)
            java.util.concurrent.atomic.AtomicInteger r0 = (java.util.concurrent.atomic.AtomicInteger) r0
            if (r0 != 0) goto L_0x002b
            return r1
        L_0x002b:
            int r2 = r0.get()
            if (r2 == 0) goto L_0x005d
            int r3 = com.google.common.math.c.b(r2, r6)     // Catch:{ ArithmeticException -> 0x003c }
            boolean r3 = r0.compareAndSet(r2, r3)     // Catch:{ ArithmeticException -> 0x003c }
            if (r3 == 0) goto L_0x002b
            return r2
        L_0x003c:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Overflow adding "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = " occurrences to a count of "
            r0.append(r6)
            r0.append(r2)
            java.lang.String r6 = r0.toString()
            r5.<init>(r6)
            throw r5
        L_0x005d:
            java.util.concurrent.atomic.AtomicInteger r2 = new java.util.concurrent.atomic.AtomicInteger
            r2.<init>(r6)
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r3 = r4.countMap
            java.lang.Object r3 = r3.putIfAbsent(r5, r2)
            if (r3 == 0) goto L_0x0072
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r3 = r4.countMap
            boolean r0 = r3.replace(r5, r0, r2)
            if (r0 == 0) goto L_0x0010
        L_0x0072:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ConcurrentHashMultiset.add(java.lang.Object, int):int");
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public int remove(Object obj, int i) {
        int i2;
        int max;
        if (i == 0) {
            return count(obj);
        }
        n.b(i, "occurences");
        AtomicInteger atomicInteger = (AtomicInteger) Maps.a((Map<?, Object>) this.countMap, obj);
        if (atomicInteger == null) {
            return 0;
        }
        do {
            i2 = atomicInteger.get();
            if (i2 == 0) {
                return 0;
            }
            max = Math.max(0, i2 - i);
        } while (!atomicInteger.compareAndSet(i2, max));
        if (max == 0) {
            this.countMap.remove(obj, atomicInteger);
        }
        return i2;
    }

    public boolean removeExactly(Object obj, int i) {
        int i2;
        int i3;
        if (i == 0) {
            return true;
        }
        n.b(i, "occurences");
        AtomicInteger atomicInteger = (AtomicInteger) Maps.a((Map<?, Object>) this.countMap, obj);
        if (atomicInteger == null) {
            return false;
        }
        do {
            i2 = atomicInteger.get();
            if (i2 < i) {
                return false;
            }
            i3 = i2 - i;
        } while (!atomicInteger.compareAndSet(i2, i3));
        if (i3 == 0) {
            this.countMap.remove(obj, atomicInteger);
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
        if (r6 != 0) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        r2 = new java.util.concurrent.atomic.AtomicInteger(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003b, code lost:
        if (r4.countMap.putIfAbsent(r5, r2) == null) goto L_0x0045;
     */
    @Override // com.google.common.collect.d, com.google.common.collect.aj
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int setCount(E r5, int r6) {
        /*
            r4 = this;
            com.google.common.base.m.a(r5)
            java.lang.String r0 = "count"
            com.google.common.collect.n.a(r6, r0)
        L_0x0009:
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r0 = r4.countMap
            java.lang.Object r0 = com.google.common.collect.Maps.a(r0, r5)
            java.util.concurrent.atomic.AtomicInteger r0 = (java.util.concurrent.atomic.AtomicInteger) r0
            r1 = 0
            if (r0 != 0) goto L_0x0027
            if (r6 != 0) goto L_0x0017
            return r1
        L_0x0017:
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r0 = r4.countMap
            java.util.concurrent.atomic.AtomicInteger r2 = new java.util.concurrent.atomic.AtomicInteger
            r2.<init>(r6)
            java.lang.Object r0 = r0.putIfAbsent(r5, r2)
            java.util.concurrent.atomic.AtomicInteger r0 = (java.util.concurrent.atomic.AtomicInteger) r0
            if (r0 != 0) goto L_0x0027
            return r1
        L_0x0027:
            int r2 = r0.get()
            if (r2 != 0) goto L_0x0046
            if (r6 != 0) goto L_0x0030
            return r1
        L_0x0030:
            java.util.concurrent.atomic.AtomicInteger r2 = new java.util.concurrent.atomic.AtomicInteger
            r2.<init>(r6)
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r3 = r4.countMap
            java.lang.Object r3 = r3.putIfAbsent(r5, r2)
            if (r3 == 0) goto L_0x0045
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r3 = r4.countMap
            boolean r0 = r3.replace(r5, r0, r2)
            if (r0 == 0) goto L_0x0009
        L_0x0045:
            return r1
        L_0x0046:
            boolean r3 = r0.compareAndSet(r2, r6)
            if (r3 == 0) goto L_0x0027
            if (r6 != 0) goto L_0x0053
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r6 = r4.countMap
            r6.remove(r5, r0)
        L_0x0053:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ConcurrentHashMultiset.setCount(java.lang.Object, int):int");
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public boolean setCount(E e, int i, int i2) {
        m.a(e);
        n.a(i, "oldCount");
        n.a(i2, "newCount");
        AtomicInteger atomicInteger = (AtomicInteger) Maps.a((Map<?, Object>) this.countMap, (Object) e);
        if (atomicInteger != null) {
            int i3 = atomicInteger.get();
            if (i3 == i) {
                if (i3 == 0) {
                    if (i2 == 0) {
                        this.countMap.remove(e, atomicInteger);
                        return true;
                    }
                    AtomicInteger atomicInteger2 = new AtomicInteger(i2);
                    return this.countMap.putIfAbsent(e, atomicInteger2) == null || this.countMap.replace(e, atomicInteger, atomicInteger2);
                } else if (atomicInteger.compareAndSet(i3, i2)) {
                    if (i2 == 0) {
                        this.countMap.remove(e, atomicInteger);
                    }
                    return true;
                }
            }
            return false;
        } else if (i != 0) {
            return false;
        } else {
            if (i2 == 0) {
                return true;
            }
            return this.countMap.putIfAbsent(e, new AtomicInteger(i2)) == null;
        }
    }

    /* renamed from: com.google.common.collect.ConcurrentHashMultiset$1  reason: invalid class name */
    class AnonymousClass1 extends ab<E> {
        final /* synthetic */ Set a;

        AnonymousClass1(Set set) {
            this.a = set;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ab, com.google.common.collect.s, com.google.common.collect.z
        public Set<E> delegate() {
            return this.a;
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return obj != null && o.a(this.a, obj);
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return standardContainsAll(collection);
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return obj != null && o.b(this.a, obj);
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            return standardRemoveAll(collection);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public Set<E> createElementSet() {
        return new AnonymousClass1(this.countMap.keySet());
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public Iterator<E> elementIterator() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.d
    @Deprecated
    public Set<aj.a<E>> createEntrySet() {
        return new a(this, null);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public int distinctElements() {
        return this.countMap.size();
    }

    @Override // com.google.common.collect.d, java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.countMap.isEmpty();
    }

    /* renamed from: com.google.common.collect.ConcurrentHashMultiset$2  reason: invalid class name */
    class AnonymousClass2 extends AbstractIterator<aj.a<E>> {
        private final Iterator<Map.Entry<E, AtomicInteger>> b = ConcurrentHashMultiset.this.countMap.entrySet().iterator();

        AnonymousClass2() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public aj.a<E> a() {
            while (this.b.hasNext()) {
                Map.Entry<E, AtomicInteger> next = this.b.next();
                int i = next.getValue().get();
                if (i != 0) {
                    return Multisets.a(next.getKey(), i);
                }
            }
            return b();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public Iterator<aj.a<E>> entryIterator() {
        return new AnonymousClass3(new AnonymousClass2());
    }

    /* renamed from: com.google.common.collect.ConcurrentHashMultiset$3  reason: invalid class name */
    class AnonymousClass3 extends u<aj.a<E>> {
        final /* synthetic */ Iterator a;
        private aj.a<E> c;

        AnonymousClass3(Iterator it2) {
            this.a = it2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.u
        /* renamed from: a */
        public Iterator<aj.a<E>> delegate() {
            return this.a;
        }

        /* renamed from: b */
        public aj.a<E> next() {
            this.c = (aj.a) super.next();
            return this.c;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: com.google.common.collect.ConcurrentHashMultiset */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.u, java.util.Iterator
        public void remove() {
            n.a(this.c != null);
            ConcurrentHashMultiset.this.setCount(this.c.getElement(), 0);
            this.c = null;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.aj
    public Iterator<E> iterator() {
        return Multisets.b((aj) this);
    }

    @Override // com.google.common.collect.d, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.countMap.clear();
    }

    private class a extends d<E>.b {
        private a() {
            super();
        }

        /* synthetic */ a(ConcurrentHashMultiset concurrentHashMultiset, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public ConcurrentHashMultiset<E> a() {
            return ConcurrentHashMultiset.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return c().toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) c().toArray(tArr);
        }

        private List<aj.a<E>> c() {
            ArrayList c = Lists.c(size());
            Iterators.a(c, iterator());
            return c;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.countMap);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        b.a.a((as.a<ConcurrentHashMultiset>) this, (Object) ((ConcurrentMap) objectInputStream.readObject()));
    }
}
