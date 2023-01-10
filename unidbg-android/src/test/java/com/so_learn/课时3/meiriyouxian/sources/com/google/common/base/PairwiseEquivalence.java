package com.google.common.base;

import java.io.Serializable;
import java.util.Iterator;

final class PairwiseEquivalence<T> extends Equivalence<Iterable<T>> implements Serializable {
    private static final long serialVersionUID = 1;
    final Equivalence<? super T> elementEquivalence;

    PairwiseEquivalence(Equivalence<? super T> equivalence) {
        this.elementEquivalence = (Equivalence) m.a(equivalence);
    }

    /* access modifiers changed from: protected */
    public boolean doEquivalent(Iterable<T> iterable, Iterable<T> iterable2) {
        Iterator<T> it2 = iterable.iterator();
        Iterator<T> it3 = iterable2.iterator();
        while (it2.hasNext() && it3.hasNext()) {
            if (!this.elementEquivalence.equivalent(it2.next(), it3.next())) {
                return false;
            }
        }
        if (it2.hasNext() || it3.hasNext()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int doHash(Iterable<T> iterable) {
        int i = 78721;
        for (T t : iterable) {
            i = (i * 24943) + this.elementEquivalence.hash(t);
        }
        return i;
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (obj instanceof PairwiseEquivalence) {
            return this.elementEquivalence.equals(((PairwiseEquivalence) obj).elementEquivalence);
        }
        return false;
    }

    @Override // java.lang.Object
    public int hashCode() {
        return this.elementEquivalence.hashCode() ^ 1185147655;
    }

    @Override // java.lang.Object
    public String toString() {
        return this.elementEquivalence + ".pairwise()";
    }
}
