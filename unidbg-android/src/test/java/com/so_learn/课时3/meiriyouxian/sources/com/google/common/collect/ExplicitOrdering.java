package com.google.common.collect;

import com.google.common.collect.Ordering;
import com.umeng.message.proguard.l;
import java.io.Serializable;
import java.util.List;

/* access modifiers changed from: package-private */
public final class ExplicitOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final ImmutableMap<T, Integer> rankMap;

    ExplicitOrdering(List<T> list) {
        this(Maps.a(list));
    }

    ExplicitOrdering(ImmutableMap<T, Integer> immutableMap) {
        this.rankMap = immutableMap;
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T t, T t2) {
        return rank(t) - rank(t2);
    }

    private int rank(T t) {
        Integer num = (Integer) this.rankMap.get(t);
        if (num != null) {
            return num.intValue();
        }
        throw new Ordering.IncomparableValueException(t);
    }

    @Override // java.util.Comparator, java.lang.Object
    public boolean equals(Object obj) {
        if (obj instanceof ExplicitOrdering) {
            return this.rankMap.equals(((ExplicitOrdering) obj).rankMap);
        }
        return false;
    }

    @Override // java.lang.Object
    public int hashCode() {
        return this.rankMap.hashCode();
    }

    @Override // java.lang.Object
    public String toString() {
        return "Ordering.explicit(" + this.rankMap.keySet() + l.t;
    }
}
