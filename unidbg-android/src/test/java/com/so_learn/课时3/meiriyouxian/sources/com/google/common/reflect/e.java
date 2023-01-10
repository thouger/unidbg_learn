package com.google.common.reflect;

import com.google.common.base.m;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* compiled from: TypeParameter */
public abstract class e<T> extends d<T> {
    final TypeVariable<?> a;

    protected e() {
        Type capture = capture();
        m.a(capture instanceof TypeVariable, "%s should be a type variable.", capture);
        this.a = (TypeVariable) capture;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof e) {
            return this.a.equals(((e) obj).a);
        }
        return false;
    }

    public String toString() {
        return this.a.toString();
    }
}
