package com.google.common.reflect;

import com.google.common.collect.Sets;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Set;

/* compiled from: TypeVisitor */
/* access modifiers changed from: package-private */
public abstract class g {
    private final Set<Type> a = Sets.a();

    /* access modifiers changed from: package-private */
    public void a(Class<?> cls) {
    }

    /* access modifiers changed from: package-private */
    public void a(GenericArrayType genericArrayType) {
    }

    /* access modifiers changed from: package-private */
    public void a(ParameterizedType parameterizedType) {
    }

    /* access modifiers changed from: package-private */
    public void a(TypeVariable<?> typeVariable) {
    }

    /* access modifiers changed from: package-private */
    public void a(WildcardType wildcardType) {
    }

    g() {
    }

    public final void a(Type... typeArr) {
        for (Type type : typeArr) {
            if (type != null && this.a.add(type)) {
                try {
                    if (type instanceof TypeVariable) {
                        a((TypeVariable) type);
                    } else if (type instanceof WildcardType) {
                        a((WildcardType) type);
                    } else if (type instanceof ParameterizedType) {
                        a((ParameterizedType) type);
                    } else if (type instanceof Class) {
                        a((Class) type);
                    } else if (type instanceof GenericArrayType) {
                        a((GenericArrayType) type);
                    } else {
                        throw new AssertionError("Unknown type: " + type);
                    }
                } catch (Throwable th) {
                    this.a.remove(type);
                    throw th;
                }
            }
        }
    }
}
