package com.google.common.collect;

import com.google.common.base.m;
import java.util.NoSuchElementException;

/* compiled from: AbstractIndexedListIterator */
/* access modifiers changed from: package-private */
public abstract class a<E> extends bg<E> {
    private final int a;
    private int b;

    /* access modifiers changed from: protected */
    public abstract E a(int i);

    protected a(int i) {
        this(i, 0);
    }

    protected a(int i, int i2) {
        m.b(i2, i);
        this.a = i;
        this.b = i2;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.b < this.a;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final E next() {
        if (hasNext()) {
            int i = this.b;
            this.b = i + 1;
            return (E) a(i);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.b;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.b > 0;
    }

    @Override // java.util.ListIterator
    public final E previous() {
        if (hasPrevious()) {
            int i = this.b - 1;
            this.b = i;
            return (E) a(i);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.b - 1;
    }
}
