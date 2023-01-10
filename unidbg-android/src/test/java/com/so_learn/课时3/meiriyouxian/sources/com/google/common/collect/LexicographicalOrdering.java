package com.google.common.collect;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

final class LexicographicalOrdering<T> extends Ordering<Iterable<T>> implements Serializable {
    private static final long serialVersionUID = 0;
    final Comparator<? super T> elementOrder;

    LexicographicalOrdering(Comparator<? super T> comparator) {
        this.elementOrder = comparator;
    }

    public int compare(Iterable<T> iterable, Iterable<T> iterable2) {
        Iterator<T> it2 = iterable2.iterator();
        for (T t : iterable) {
            if (!it2.hasNext()) {
                return 1;
            }
            int compare = this.elementOrder.compare(t, it2.next());
            if (compare != 0) {
                return compare;
            }
        }
        return it2.hasNext() ? -1 : 0;
    }

    @Override // java.util.Comparator, java.lang.Object
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LexicographicalOrdering) {
            return this.elementOrder.equals(((LexicographicalOrdering) obj).elementOrder);
        }
        return false;
    }

    @Override // java.lang.Object
    public int hashCode() {
        return this.elementOrder.hashCode() ^ 2075626741;
    }

    @Override // java.lang.Object
    public String toString() {
        return this.elementOrder + ".lexicographical()";
    }
}
