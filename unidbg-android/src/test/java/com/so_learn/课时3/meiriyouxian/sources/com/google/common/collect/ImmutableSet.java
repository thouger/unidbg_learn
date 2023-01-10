package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.ImmutableCollection;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private static final int CUTOFF = 751619276;
    private static final double DESIRED_LOAD_FACTOR = 0.7d;
    static final int MAX_TABLE_SIZE = 1073741824;
    private transient ImmutableList<E> asList;

    /* access modifiers changed from: private */
    public static boolean shouldTrim(int i, int i2) {
        return i < (i2 >> 1) + (i2 >> 2);
    }

    /* access modifiers changed from: package-private */
    public boolean isHashCodeFast() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet
    public abstract bf<E> iterator();

    public static <E> ImmutableSet<E> of() {
        return RegularImmutableSet.EMPTY;
    }

    public static <E> ImmutableSet<E> of(E e) {
        return new SingletonImmutableSet(e);
    }

    public static <E> ImmutableSet<E> of(E e, E e2) {
        return construct(2, e, e2);
    }

    public static <E> ImmutableSet<E> of(E e, E e2, E e3) {
        return construct(3, e, e2, e3);
    }

    public static <E> ImmutableSet<E> of(E e, E e2, E e3, E e4) {
        return construct(4, e, e2, e3, e4);
    }

    public static <E> ImmutableSet<E> of(E e, E e2, E e3, E e4, E e5) {
        return construct(5, e, e2, e3, e4, e5);
    }

    @SafeVarargs
    public static <E> ImmutableSet<E> of(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        m.a(eArr.length <= 2147483641, "the total number of elements must fit in an int");
        Object[] objArr = new Object[(eArr.length + 6)];
        objArr[0] = e;
        objArr[1] = e2;
        objArr[2] = e3;
        objArr[3] = e4;
        objArr[4] = e5;
        objArr[5] = e6;
        System.arraycopy(eArr, 0, objArr, 6, eArr.length);
        return construct(objArr.length, objArr);
    }

    /* access modifiers changed from: private */
    public static <E> ImmutableSet<E> construct(int i, Object... objArr) {
        if (i == 0) {
            return of();
        }
        if (i == 1) {
            return of(objArr[0]);
        }
        int chooseTableSize = chooseTableSize(i);
        Object[] objArr2 = new Object[chooseTableSize];
        int i2 = chooseTableSize - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object a2 = ak.a(objArr[i5], i5);
            int hashCode = a2.hashCode();
            int a3 = af.a(hashCode);
            while (true) {
                int i6 = a3 & i2;
                Object obj = objArr2[i6];
                if (obj == null) {
                    objArr[i4] = a2;
                    objArr2[i6] = a2;
                    i3 += hashCode;
                    i4++;
                    break;
                } else if (obj.equals(a2)) {
                    break;
                } else {
                    a3++;
                }
            }
        }
        Arrays.fill(objArr, i4, i, (Object) null);
        if (i4 == 1) {
            return new SingletonImmutableSet(objArr[0], i3);
        }
        if (chooseTableSize(i4) < chooseTableSize / 2) {
            return construct(i4, objArr);
        }
        if (shouldTrim(i4, objArr.length)) {
            objArr = Arrays.copyOf(objArr, i4);
        }
        return new RegularImmutableSet(objArr, i3, objArr2, i2, i4);
    }

    static int chooseTableSize(int i) {
        int max = Math.max(i, 2);
        boolean z = true;
        if (max < CUTOFF) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (((double) highestOneBit) * DESIRED_LOAD_FACTOR < ((double) max)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        if (max >= 1073741824) {
            z = false;
        }
        m.a(z, "collection too large");
        return 1073741824;
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet)) {
            ImmutableSet<E> immutableSet = (ImmutableSet) collection;
            if (!immutableSet.isPartialView()) {
                return immutableSet;
            }
        }
        Object[] array = collection.toArray();
        return construct(array.length, array);
    }

    public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection) iterable);
        }
        return copyOf(iterable.iterator());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: com.google.common.collect.ImmutableSet$a */
    /* JADX WARN: Multi-variable type inference failed */
    public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> it2) {
        if (!it2.hasNext()) {
            return of();
        }
        Object next = it2.next();
        if (!it2.hasNext()) {
            return of(next);
        }
        return new a().b(next).a((Iterator) it2).a();
    }

    public static <E> ImmutableSet<E> copyOf(E[] eArr) {
        int length = eArr.length;
        if (length == 0) {
            return of();
        }
        if (length != 1) {
            return construct(eArr.length, (Object[]) eArr.clone());
        }
        return of((Object) eArr[0]);
    }

    ImmutableSet() {
    }

    @Override // java.util.Collection, java.lang.Object, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableSet) || !isHashCodeFast() || !((ImmutableSet) obj).isHashCodeFast() || hashCode() == obj.hashCode()) {
            return Sets.a(this, obj);
        }
        return false;
    }

    @Override // java.util.Collection, java.lang.Object, java.util.Set
    public int hashCode() {
        return Sets.a(this);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> createAsList = createAsList();
        this.asList = createAsList;
        return createAsList;
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> createAsList() {
        return ImmutableList.asImmutableList(toArray());
    }

    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return ImmutableSet.copyOf(this.elements);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public static <E> a<E> builder() {
        return new a<>();
    }

    public static <E> a<E> builderWithExpectedSize(int i) {
        n.a(i, "expectedSize");
        return new a<>(i);
    }

    public static class a<E> extends ImmutableCollection.a<E> {
        Object[] d;
        private int e;

        public a() {
            super(4);
        }

        a(int i) {
            super(i);
            this.d = new Object[ImmutableSet.chooseTableSize(i)];
        }

        /* renamed from: c */
        public a<E> b(E e) {
            m.a(e);
            if (this.d == null || ImmutableSet.chooseTableSize(this.b) > this.d.length) {
                this.d = null;
                super.a((a<E>) e);
                return this;
            }
            d(e);
            return this;
        }

        /* renamed from: b */
        public a<E> a(E... eArr) {
            if (this.d != null) {
                for (E e : eArr) {
                    b(e);
                }
            } else {
                super.a((Object[]) eArr);
            }
            return this;
        }

        private void d(E e) {
            int length = this.d.length - 1;
            int hashCode = e.hashCode();
            int a = af.a(hashCode);
            while (true) {
                int i = a & length;
                Object[] objArr = this.d;
                Object obj = objArr[i];
                if (obj == null) {
                    objArr[i] = e;
                    this.e += hashCode;
                    super.a((a<E>) e);
                    return;
                } else if (!obj.equals(e)) {
                    a = i + 1;
                } else {
                    return;
                }
            }
        }

        /* renamed from: b */
        public a<E> a(Iterable<? extends E> iterable) {
            m.a(iterable);
            if (this.d != null) {
                Iterator<? extends E> it2 = iterable.iterator();
                while (it2.hasNext()) {
                    b(it2.next());
                }
            } else {
                super.a((Iterable) iterable);
            }
            return this;
        }

        /* renamed from: b */
        public a<E> a(Iterator<? extends E> it2) {
            m.a(it2);
            while (it2.hasNext()) {
                b(it2.next());
            }
            return this;
        }

        public ImmutableSet<E> a() {
            ImmutableSet<E> immutableSet;
            int i = this.b;
            if (i == 0) {
                return ImmutableSet.of();
            }
            if (i == 1) {
                return ImmutableSet.of(this.a[0]);
            }
            if (this.d == null || ImmutableSet.chooseTableSize(this.b) != this.d.length) {
                immutableSet = ImmutableSet.construct(this.b, this.a);
                this.b = immutableSet.size();
            } else {
                Object[] copyOf = ImmutableSet.shouldTrim(this.b, this.a.length) ? Arrays.copyOf(this.a, this.b) : this.a;
                int i2 = this.e;
                Object[] objArr = this.d;
                immutableSet = new RegularImmutableSet<>(copyOf, i2, objArr, objArr.length - 1, this.b);
            }
            this.c = true;
            this.d = null;
            return immutableSet;
        }
    }
}
