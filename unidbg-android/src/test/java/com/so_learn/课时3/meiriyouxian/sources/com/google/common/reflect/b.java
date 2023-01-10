package com.google.common.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/* compiled from: Invokable */
public abstract class b<T, R> extends a implements GenericDeclaration {
    /* access modifiers changed from: package-private */
    public abstract Type[] b();

    @Override // com.google.common.reflect.a, java.lang.Object
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.reflect.a, java.lang.Object
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.reflect.a, java.lang.Object
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    <M extends AccessibleObject & Member> b(M m) {
        super(m);
    }

    @Override // com.google.common.reflect.a, java.lang.reflect.Member
    public final Class<? super T> getDeclaringClass() {
        return (Class<? super T>) super.getDeclaringClass();
    }

    @Override // com.google.common.reflect.a
    public TypeToken<T> a() {
        return TypeToken.of((Class) getDeclaringClass());
    }

    /* compiled from: Invokable */
    /* renamed from: com.google.common.reflect.b$b  reason: collision with other inner class name */
    static class C0110b<T> extends b<T, Object> {
        final Method a;

        C0110b(Method method) {
            super(method);
            this.a = method;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.b
        public Type[] b() {
            return this.a.getGenericParameterTypes();
        }

        @Override // java.lang.reflect.GenericDeclaration
        public final TypeVariable<?>[] getTypeParameters() {
            return this.a.getTypeParameters();
        }
    }

    /* compiled from: Invokable */
    static class a<T> extends b<T, T> {
        final Constructor<?> a;

        a(Constructor<?> constructor) {
            super(constructor);
            this.a = constructor;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.b
        public Type[] b() {
            Type[] genericParameterTypes = this.a.getGenericParameterTypes();
            if (genericParameterTypes.length <= 0 || !c()) {
                return genericParameterTypes;
            }
            Class<?>[] parameterTypes = this.a.getParameterTypes();
            return (genericParameterTypes.length == parameterTypes.length && parameterTypes[0] == getDeclaringClass().getEnclosingClass()) ? (Type[]) Arrays.copyOfRange(genericParameterTypes, 1, genericParameterTypes.length) : genericParameterTypes;
        }

        @Override // java.lang.reflect.GenericDeclaration
        public final TypeVariable<?>[] getTypeParameters() {
            TypeVariable<Class<? super T>>[] typeParameters = getDeclaringClass().getTypeParameters();
            TypeVariable<Constructor<?>>[] typeParameters2 = this.a.getTypeParameters();
            TypeVariable<?>[] typeVariableArr = new TypeVariable[(typeParameters.length + typeParameters2.length)];
            System.arraycopy(typeParameters, 0, typeVariableArr, 0, typeParameters.length);
            System.arraycopy(typeParameters2, 0, typeVariableArr, typeParameters.length, typeParameters2.length);
            return typeVariableArr;
        }

        private boolean c() {
            Class<?> declaringClass = this.a.getDeclaringClass();
            if (declaringClass.getEnclosingConstructor() != null) {
                return true;
            }
            Method enclosingMethod = declaringClass.getEnclosingMethod();
            if (enclosingMethod != null) {
                return !Modifier.isStatic(enclosingMethod.getModifiers());
            }
            if (declaringClass.getEnclosingClass() == null || Modifier.isStatic(declaringClass.getModifiers())) {
                return false;
            }
            return true;
        }
    }
}
