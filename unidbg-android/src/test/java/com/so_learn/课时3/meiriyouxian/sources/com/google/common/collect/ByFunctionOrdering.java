package com.google.common.collect;

import com.google.common.base.g;
import com.google.common.base.j;
import com.google.common.base.m;
import com.umeng.message.proguard.l;
import java.io.Serializable;

/* access modifiers changed from: package-private */
public final class ByFunctionOrdering<F, T> extends Ordering<F> implements Serializable {
    private static final long serialVersionUID = 0;
    final g<F, ? extends T> function;
    final Ordering<T> ordering;

    ByFunctionOrdering(g<F, ? extends T> gVar, Ordering<T> ordering) {
        this.function = (g) m.a(gVar);
        this.ordering = (Ordering) m.a(ordering);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(F f, F f2) {
        return this.ordering.compare(this.function.apply(f), this.function.apply(f2));
    }

    @Override // java.util.Comparator, java.lang.Object
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByFunctionOrdering)) {
            return false;
        }
        ByFunctionOrdering byFunctionOrdering = (ByFunctionOrdering) obj;
        return this.function.equals(byFunctionOrdering.function) && this.ordering.equals(byFunctionOrdering.ordering);
    }

    @Override // java.lang.Object
    public int hashCode() {
        return j.a(this.function, this.ordering);
    }

    @Override // java.lang.Object
    public String toString() {
        return this.ordering + ".onResultOf(" + this.function + l.t;
    }
}
