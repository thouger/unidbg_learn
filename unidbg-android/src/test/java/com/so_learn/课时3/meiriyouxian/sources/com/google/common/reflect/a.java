package com.google.common.reflect;

import com.google.common.base.m;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;

/* compiled from: Element */
/* access modifiers changed from: package-private */
public class a extends AccessibleObject implements Member {
    private final AccessibleObject a;
    private final Member b;

    <M extends AccessibleObject & Member> a(M m) {
        m.a(m);
        this.a = m;
        this.b = m;
    }

    public TypeToken<?> a() {
        return TypeToken.of((Class) getDeclaringClass());
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.AnnotatedElement
    public final boolean isAnnotationPresent(Class<? extends Annotation> cls) {
        return this.a.isAnnotationPresent(cls);
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.AnnotatedElement
    public final <A extends Annotation> A getAnnotation(Class<A> cls) {
        return (A) this.a.getAnnotation(cls);
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.AnnotatedElement
    public final Annotation[] getAnnotations() {
        return this.a.getAnnotations();
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.AnnotatedElement
    public final Annotation[] getDeclaredAnnotations() {
        return this.a.getDeclaredAnnotations();
    }

    @Override // java.lang.reflect.AccessibleObject
    public final void setAccessible(boolean z) throws SecurityException {
        this.a.setAccessible(z);
    }

    @Override // java.lang.reflect.AccessibleObject
    public final boolean isAccessible() {
        return this.a.isAccessible();
    }

    @Override // java.lang.reflect.Member
    public Class<?> getDeclaringClass() {
        return this.b.getDeclaringClass();
    }

    @Override // java.lang.reflect.Member
    public final String getName() {
        return this.b.getName();
    }

    @Override // java.lang.reflect.Member
    public final int getModifiers() {
        return this.b.getModifiers();
    }

    @Override // java.lang.reflect.Member
    public final boolean isSynthetic() {
        return this.b.isSynthetic();
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (!a().equals(aVar.a()) || !this.b.equals(aVar.b)) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Object
    public int hashCode() {
        return this.b.hashCode();
    }

    @Override // java.lang.Object
    public String toString() {
        return this.b.toString();
    }
}
