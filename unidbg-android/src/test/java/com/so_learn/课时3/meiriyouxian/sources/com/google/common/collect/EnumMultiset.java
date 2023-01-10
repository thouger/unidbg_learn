package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.Multisets;
import com.google.common.collect.aj;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Enum;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public final class EnumMultiset<E extends Enum<E>> extends d<E> implements Serializable {
    private static final long serialVersionUID = 0;
    private transient int[] counts = new int[this.enumConstants.length];
    private transient int distinctElements;
    private transient E[] enumConstants;
    private transient long size;
    private transient Class<E> type;

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public /* bridge */ /* synthetic */ int add(Object obj, int i) {
        return add((EnumMultiset<E>) ((Enum) obj), i);
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

    @Override // com.google.common.collect.d, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public /* bridge */ /* synthetic */ int setCount(Object obj, int i) {
        return setCount((EnumMultiset<E>) ((Enum) obj), i);
    }

    static /* synthetic */ int access$210(EnumMultiset enumMultiset) {
        int i = enumMultiset.distinctElements;
        enumMultiset.distinctElements = i - 1;
        return i;
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Class<E> cls) {
        return new EnumMultiset<>(cls);
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> iterable) {
        Iterator<E> it2 = iterable.iterator();
        m.a(it2.hasNext(), "EnumMultiset constructor passed empty Iterable");
        EnumMultiset<E> enumMultiset = new EnumMultiset<>(it2.next().getDeclaringClass());
        ag.a((Collection) enumMultiset, (Iterable) iterable);
        return enumMultiset;
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> iterable, Class<E> cls) {
        EnumMultiset<E> create = create(cls);
        ag.a((Collection) create, (Iterable) iterable);
        return create;
    }

    private EnumMultiset(Class<E> cls) {
        this.type = cls;
        m.a(cls.isEnum());
        this.enumConstants = cls.getEnumConstants();
    }

    private boolean isActuallyE(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        Enum r5 = (Enum) obj;
        int ordinal = r5.ordinal();
        E[] eArr = this.enumConstants;
        if (ordinal >= eArr.length || eArr[ordinal] != r5) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void checkIsE(Object obj) {
        m.a(obj);
        if (!isActuallyE(obj)) {
            throw new ClassCastException("Expected an " + this.type + " but got " + obj);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public int distinctElements() {
        return this.distinctElements;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
    public int size() {
        return Ints.b(this.size);
    }

    @Override // com.google.common.collect.aj
    public int count(Object obj) {
        if (obj == null || !isActuallyE(obj)) {
            return 0;
        }
        return this.counts[((Enum) obj).ordinal()];
    }

    public int add(E e, int i) {
        checkIsE(e);
        n.a(i, "occurrences");
        if (i == 0) {
            return count(e);
        }
        int ordinal = e.ordinal();
        int i2 = this.counts[ordinal];
        long j = (long) i;
        long j2 = ((long) i2) + j;
        m.a(j2 <= 2147483647L, "too many occurrences: %s", j2);
        this.counts[ordinal] = (int) j2;
        if (i2 == 0) {
            this.distinctElements++;
        }
        this.size += j;
        return i2;
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public int remove(Object obj, int i) {
        if (obj == null || !isActuallyE(obj)) {
            return 0;
        }
        Enum r1 = (Enum) obj;
        n.a(i, "occurrences");
        if (i == 0) {
            return count(obj);
        }
        int ordinal = r1.ordinal();
        int[] iArr = this.counts;
        int i2 = iArr[ordinal];
        if (i2 == 0) {
            return 0;
        }
        if (i2 <= i) {
            iArr[ordinal] = 0;
            this.distinctElements--;
            this.size -= (long) i2;
        } else {
            iArr[ordinal] = i2 - i;
            this.size -= (long) i;
        }
        return i2;
    }

    public int setCount(E e, int i) {
        checkIsE(e);
        n.a(i, "count");
        int ordinal = e.ordinal();
        int[] iArr = this.counts;
        int i2 = iArr[ordinal];
        iArr[ordinal] = i;
        this.size += (long) (i - i2);
        if (i2 == 0 && i > 0) {
            this.distinctElements++;
        } else if (i2 > 0 && i == 0) {
            this.distinctElements--;
        }
        return i2;
    }

    @Override // com.google.common.collect.d, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        Arrays.fill(this.counts, 0);
        this.size = 0;
        this.distinctElements = 0;
    }

    abstract class a<T> implements Iterator<T> {
        int b = 0;
        int c = -1;

        /* access modifiers changed from: package-private */
        public abstract T b(int i);

        a() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (this.b < EnumMultiset.this.enumConstants.length) {
                int[] iArr = EnumMultiset.this.counts;
                int i = this.b;
                if (iArr[i] > 0) {
                    return true;
                }
                this.b = i + 1;
            }
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T t = (T) b(this.b);
                int i = this.b;
                this.c = i;
                this.b = i + 1;
                return t;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            n.a(this.c >= 0);
            if (EnumMultiset.this.counts[this.c] > 0) {
                EnumMultiset.access$210(EnumMultiset.this);
                EnumMultiset.this.size -= (long) EnumMultiset.this.counts[this.c];
                EnumMultiset.this.counts[this.c] = 0;
            }
            this.c = -1;
        }
    }

    /* renamed from: com.google.common.collect.EnumMultiset$1  reason: invalid class name */
    class AnonymousClass1 extends EnumMultiset<E>.a {
        AnonymousClass1() {
            super();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public E b(int i) {
            return (E) EnumMultiset.this.enumConstants[i];
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public Iterator<E> elementIterator() {
        return new AnonymousClass1();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.EnumMultiset$2  reason: invalid class name */
    public class AnonymousClass2 extends EnumMultiset<E>.a {
        AnonymousClass2() {
            super();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.EnumMultiset$2$1  reason: invalid class name */
        public class AnonymousClass1 extends Multisets.a<E> {
            final /* synthetic */ int a;

            AnonymousClass1(int i) {
                this.a = i;
            }

            /* renamed from: a */
            public E getElement() {
                return (E) EnumMultiset.this.enumConstants[this.a];
            }

            @Override // com.google.common.collect.aj.a
            public int getCount() {
                return EnumMultiset.this.counts[this.a];
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public aj.a<E> b(int i) {
            return new AnonymousClass1(i);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public Iterator<aj.a<E>> entryIterator() {
        return new AnonymousClass2();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.aj
    public Iterator<E> iterator() {
        return Multisets.b((aj) this);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.type);
        as.a(this, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.type = (Class) objectInputStream.readObject();
        this.enumConstants = this.type.getEnumConstants();
        this.counts = new int[this.enumConstants.length];
        as.a(this, objectInputStream);
    }
}
