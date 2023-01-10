package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.aj;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* access modifiers changed from: package-private */
public abstract class AbstractMapBasedMultiset<E> extends d<E> implements Serializable {
    private static final long serialVersionUID = 0;
    transient al<E> backingMap;
    transient long size;

    /* access modifiers changed from: package-private */
    public abstract void init(int i);

    AbstractMapBasedMultiset(int i) {
        init(i);
    }

    @Override // com.google.common.collect.aj
    public final int count(Object obj) {
        return this.backingMap.b(obj);
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public final int add(E e, int i) {
        if (i == 0) {
            return count(e);
        }
        boolean z = true;
        m.a(i > 0, "occurrences cannot be negative: %s", i);
        int a2 = this.backingMap.a(e);
        if (a2 == -1) {
            this.backingMap.a(e, i);
            this.size += (long) i;
            return 0;
        }
        int d = this.backingMap.d(a2);
        long j = (long) i;
        long j2 = ((long) d) + j;
        if (j2 > 2147483647L) {
            z = false;
        }
        m.a(z, "too many occurrences: %s", j2);
        this.backingMap.b(a2, (int) j2);
        this.size += j;
        return d;
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public final int remove(Object obj, int i) {
        if (i == 0) {
            return count(obj);
        }
        m.a(i > 0, "occurrences cannot be negative: %s", i);
        int a2 = this.backingMap.a(obj);
        if (a2 == -1) {
            return 0;
        }
        int d = this.backingMap.d(a2);
        if (d > i) {
            this.backingMap.b(a2, d - i);
        } else {
            this.backingMap.h(a2);
            i = d;
        }
        this.size -= (long) i;
        return d;
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public final int setCount(E e, int i) {
        n.a(i, "count");
        al<E> alVar = this.backingMap;
        int c = i == 0 ? alVar.c(e) : alVar.a(e, i);
        this.size += (long) (i - c);
        return c;
    }

    @Override // com.google.common.collect.d, com.google.common.collect.aj
    public final boolean setCount(E e, int i, int i2) {
        n.a(i, "oldCount");
        n.a(i2, "newCount");
        int a2 = this.backingMap.a(e);
        if (a2 == -1) {
            if (i != 0) {
                return false;
            }
            if (i2 > 0) {
                this.backingMap.a(e, i2);
                this.size += (long) i2;
            }
            return true;
        } else if (this.backingMap.d(a2) != i) {
            return false;
        } else {
            if (i2 == 0) {
                this.backingMap.h(a2);
                this.size -= (long) i;
            } else {
                this.backingMap.b(a2, i2);
                this.size += (long) (i2 - i);
            }
            return true;
        }
    }

    @Override // com.google.common.collect.d, java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.backingMap.d();
        this.size = 0;
    }

    abstract class a<T> implements Iterator<T> {
        int b = AbstractMapBasedMultiset.this.backingMap.b();
        int c = -1;
        int d = AbstractMapBasedMultiset.this.backingMap.d;

        /* access modifiers changed from: package-private */
        public abstract T a(int i);

        a() {
        }

        private void a() {
            if (AbstractMapBasedMultiset.this.backingMap.d != this.d) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            a();
            return this.b >= 0;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T t = (T) a(this.b);
                this.c = this.b;
                this.b = AbstractMapBasedMultiset.this.backingMap.b(this.b);
                return t;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            a();
            n.a(this.c != -1);
            AbstractMapBasedMultiset.this.size -= (long) AbstractMapBasedMultiset.this.backingMap.h(this.c);
            this.b = AbstractMapBasedMultiset.this.backingMap.a(this.b, this.c);
            this.c = -1;
            this.d = AbstractMapBasedMultiset.this.backingMap.d;
        }
    }

    /* renamed from: com.google.common.collect.AbstractMapBasedMultiset$1  reason: invalid class name */
    class AnonymousClass1 extends AbstractMapBasedMultiset<E>.a {
        AnonymousClass1() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultiset.a
        public E a(int i) {
            return (E) AbstractMapBasedMultiset.this.backingMap.c(i);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public final Iterator<E> elementIterator() {
        return new AnonymousClass1();
    }

    /* renamed from: com.google.common.collect.AbstractMapBasedMultiset$2  reason: invalid class name */
    class AnonymousClass2 extends AbstractMapBasedMultiset<E>.a {
        AnonymousClass2() {
            super();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public aj.a<E> a(int i) {
            return AbstractMapBasedMultiset.this.backingMap.e(i);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public final Iterator<aj.a<E>> entryIterator() {
        return new AnonymousClass2();
    }

    /* access modifiers changed from: package-private */
    public void addTo(aj<? super E> ajVar) {
        m.a(ajVar);
        int b = this.backingMap.b();
        while (b >= 0) {
            ajVar.add(this.backingMap.c(b), this.backingMap.d(b));
            b = this.backingMap.b(b);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.d
    public final int distinctElements() {
        return this.backingMap.c();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.aj
    public final Iterator<E> iterator() {
        return Multisets.b((aj) this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
    public final int size() {
        return Ints.b(this.size);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        as.a(this, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int a2 = as.a(objectInputStream);
        init(3);
        as.a(this, objectInputStream, a2);
    }
}
