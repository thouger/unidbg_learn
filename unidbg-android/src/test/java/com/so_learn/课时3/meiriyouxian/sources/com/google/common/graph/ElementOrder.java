package com.google.common.graph;

import com.google.common.base.i;
import com.google.common.base.j;
import java.util.Comparator;

public final class ElementOrder<T> {
    private final Type a;
    private final Comparator<T> b;

    public enum Type {
        UNORDERED,
        INSERTION,
        SORTED
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ElementOrder)) {
            return false;
        }
        ElementOrder elementOrder = (ElementOrder) obj;
        return this.a == elementOrder.a && j.a(this.b, elementOrder.b);
    }

    public int hashCode() {
        return j.a(this.a, this.b);
    }

    public String toString() {
        i.a a = i.a(this).a("type", this.a);
        Comparator<T> comparator = this.b;
        if (comparator != null) {
            a.a("comparator", comparator);
        }
        return a.toString();
    }
}
