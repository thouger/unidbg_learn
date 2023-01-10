package com.google.common.collect;

import com.google.common.base.m;
import java.util.Iterator;

/* compiled from: TransformedIterator */
/* access modifiers changed from: package-private */
public abstract class bc<F, T> implements Iterator<T> {
    final Iterator<? extends F> c;

    /* access modifiers changed from: package-private */
    public abstract T a(F f);

    bc(Iterator<? extends F> it2) {
        this.c = (Iterator) m.a(it2);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c.hasNext();
    }

    @Override // java.util.Iterator
    public final T next() {
        return (T) a(this.c.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.c.remove();
    }
}
