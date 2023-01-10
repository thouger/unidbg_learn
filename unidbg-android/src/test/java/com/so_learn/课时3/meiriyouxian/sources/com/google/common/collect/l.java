package com.google.common.collect;

import com.google.common.base.m;
import java.util.AbstractList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: CartesianList */
/* access modifiers changed from: package-private */
public final class l<E> extends AbstractList<List<E>> implements RandomAccess {
    private final transient ImmutableList<List<E>> a;
    private final transient int[] b;

    /* access modifiers changed from: private */
    public int a(int i, int i2) {
        return (i / this.b[i2 + 1]) % this.a.get(i2).size();
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        if (!(obj instanceof List)) {
            return -1;
        }
        List list = (List) obj;
        if (list.size() != this.a.size()) {
            return -1;
        }
        ListIterator<E> listIterator = list.listIterator();
        int i = 0;
        while (listIterator.hasNext()) {
            int nextIndex = listIterator.nextIndex();
            int indexOf = this.a.get(nextIndex).indexOf(listIterator.next());
            if (indexOf == -1) {
                return -1;
            }
            i += indexOf * this.b[nextIndex + 1];
        }
        return i;
    }

    /* renamed from: a */
    public ImmutableList<E> get(int i) {
        m.a(i, size());
        return new CartesianList$1(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public int size() {
        return this.b[0];
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }
}
