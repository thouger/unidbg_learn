package com.google.common.reflect;

import com.google.common.base.h;
import com.google.common.base.j;
import com.google.common.base.m;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.reflect.Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: TypeResolver */
public final class f {
    private final b a;

    /* synthetic */ f(b bVar, AnonymousClass1 r2) {
        this(bVar);
    }

    public f() {
        this.a = new b();
    }

    private f(b bVar) {
        this.a = bVar;
    }

    static f a(Type type) {
        return new f().a(a.a(type));
    }

    static f b(Type type) {
        return new f().a(a.a(d.a.a(type)));
    }

    public f a(Type type, Type type2) {
        HashMap c2 = Maps.c();
        b(c2, (Type) m.a(type), (Type) m.a(type2));
        return a(c2);
    }

    /* access modifiers changed from: package-private */
    public f a(Map<c, ? extends Type> map) {
        return new f(this.a.a(map));
    }

    /* access modifiers changed from: private */
    public static void b(Map<c, Type> map, Type type, Type type2) {
        if (!type.equals(type2)) {
            new AnonymousClass1(map, type2).a(type);
        }
    }

    /* compiled from: TypeResolver */
    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.reflect.f$1  reason: invalid class name */
    public static class AnonymousClass1 extends g {
        final /* synthetic */ Map a;
        final /* synthetic */ Type b;

        AnonymousClass1(Map map, Type type) {
            this.a = map;
            this.b = type;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(TypeVariable<?> typeVariable) {
            this.a.put(new c(typeVariable), this.b);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(WildcardType wildcardType) {
            Type type = this.b;
            if (type instanceof WildcardType) {
                WildcardType wildcardType2 = (WildcardType) type;
                Type[] upperBounds = wildcardType.getUpperBounds();
                Type[] upperBounds2 = wildcardType2.getUpperBounds();
                Type[] lowerBounds = wildcardType.getLowerBounds();
                Type[] lowerBounds2 = wildcardType2.getLowerBounds();
                m.a(upperBounds.length == upperBounds2.length && lowerBounds.length == lowerBounds2.length, "Incompatible type: %s vs. %s", wildcardType, this.b);
                for (int i = 0; i < upperBounds.length; i++) {
                    f.b(this.a, upperBounds[i], upperBounds2[i]);
                }
                for (int i2 = 0; i2 < lowerBounds.length; i2++) {
                    f.b(this.a, lowerBounds[i2], lowerBounds2[i2]);
                }
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(ParameterizedType parameterizedType) {
            Type type = this.b;
            if (!(type instanceof WildcardType)) {
                ParameterizedType parameterizedType2 = (ParameterizedType) f.b(ParameterizedType.class, type);
                if (!(parameterizedType.getOwnerType() == null || parameterizedType2.getOwnerType() == null)) {
                    f.b(this.a, parameterizedType.getOwnerType(), parameterizedType2.getOwnerType());
                }
                m.a(parameterizedType.getRawType().equals(parameterizedType2.getRawType()), "Inconsistent raw type: %s vs. %s", parameterizedType, this.b);
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                Type[] actualTypeArguments2 = parameterizedType2.getActualTypeArguments();
                m.a(actualTypeArguments.length == actualTypeArguments2.length, "%s not compatible with %s", parameterizedType, parameterizedType2);
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    f.b(this.a, actualTypeArguments[i], actualTypeArguments2[i]);
                }
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(GenericArrayType genericArrayType) {
            Type type = this.b;
            if (!(type instanceof WildcardType)) {
                Type e = Types.e(type);
                m.a(e != null, "%s is not an array type.", this.b);
                f.b(this.a, genericArrayType.getGenericComponentType(), e);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(Class<?> cls) {
            if (!(this.b instanceof WildcardType)) {
                throw new IllegalArgumentException("No type mapping from " + cls + " to " + this.b);
            }
        }
    }

    public Type c(Type type) {
        m.a(type);
        if (type instanceof TypeVariable) {
            return this.a.a((TypeVariable) type);
        }
        if (type instanceof ParameterizedType) {
            return a((ParameterizedType) type);
        }
        if (type instanceof GenericArrayType) {
            return a((GenericArrayType) type);
        }
        return type instanceof WildcardType ? a((WildcardType) type) : type;
    }

    /* access modifiers changed from: package-private */
    public Type[] a(Type[] typeArr) {
        for (int i = 0; i < typeArr.length; i++) {
            typeArr[i] = c(typeArr[i]);
        }
        return typeArr;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Type[] b(Type[] typeArr) {
        Type[] typeArr2 = new Type[typeArr.length];
        for (int i = 0; i < typeArr.length; i++) {
            typeArr2[i] = c(typeArr[i]);
        }
        return typeArr2;
    }

    private WildcardType a(WildcardType wildcardType) {
        return new Types.WildcardTypeImpl(b(wildcardType.getLowerBounds()), b(wildcardType.getUpperBounds()));
    }

    private Type a(GenericArrayType genericArrayType) {
        return Types.a(c(genericArrayType.getGenericComponentType()));
    }

    private ParameterizedType a(ParameterizedType parameterizedType) {
        Type type;
        Type ownerType = parameterizedType.getOwnerType();
        if (ownerType == null) {
            type = null;
        } else {
            type = c(ownerType);
        }
        return Types.a(type, (Class) c(parameterizedType.getRawType()), b(parameterizedType.getActualTypeArguments()));
    }

    /* access modifiers changed from: private */
    public static <T> T b(Class<T> cls, Object obj) {
        try {
            return cls.cast(obj);
        } catch (ClassCastException unused) {
            throw new IllegalArgumentException(obj + " is not a " + cls.getSimpleName());
        }
    }

    /* compiled from: TypeResolver */
    /* access modifiers changed from: private */
    public static class b {
        private final ImmutableMap<c, Type> a;

        b() {
            this.a = ImmutableMap.of();
        }

        private b(ImmutableMap<c, Type> immutableMap) {
            this.a = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public final b a(Map<c, ? extends Type> map) {
            ImmutableMap.a builder = ImmutableMap.builder();
            builder.b(this.a);
            for (Map.Entry<c, ? extends Type> entry : map.entrySet()) {
                c key = entry.getKey();
                Type type = (Type) entry.getValue();
                m.a(!key.b(type), "Type variable %s bound to itself", key);
                builder.b(key, type);
            }
            return new b(builder.b());
        }

        /* compiled from: TypeResolver */
        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.reflect.f$b$1  reason: invalid class name */
        public class AnonymousClass1 extends b {
            final /* synthetic */ TypeVariable a;
            final /* synthetic */ b b;

            AnonymousClass1(TypeVariable typeVariable, b bVar) {
                this.a = typeVariable;
                this.b = bVar;
            }

            @Override // com.google.common.reflect.f.b
            public Type a(TypeVariable<?> typeVariable, b bVar) {
                if (typeVariable.getGenericDeclaration().equals(this.a.getGenericDeclaration())) {
                    return typeVariable;
                }
                return this.b.a(typeVariable, bVar);
            }
        }

        /* access modifiers changed from: package-private */
        public final Type a(TypeVariable<?> typeVariable) {
            return a(typeVariable, new AnonymousClass1(typeVariable, this));
        }

        /* access modifiers changed from: package-private */
        public Type a(TypeVariable<?> typeVariable, b bVar) {
            Type type = (Type) this.a.get(new c(typeVariable));
            if (type != null) {
                return new f(bVar, null).c(type);
            }
            Type[] bounds = typeVariable.getBounds();
            if (bounds.length == 0) {
                return typeVariable;
            }
            Type[] b = new f(bVar, null).b(bounds);
            if (!Types.a.a || !Arrays.equals(bounds, b)) {
                return Types.a(typeVariable.getGenericDeclaration(), typeVariable.getName(), b);
            }
            return typeVariable;
        }
    }

    /* compiled from: TypeResolver */
    private static final class a extends g {
        private final Map<c, Type> a = Maps.c();

        private a() {
        }

        static ImmutableMap<c, Type> a(Type type) {
            m.a(type);
            a aVar = new a();
            aVar.a(type);
            return ImmutableMap.copyOf(aVar.a);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(Class<?> cls) {
            a(cls.getGenericSuperclass());
            a(cls.getGenericInterfaces());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(ParameterizedType parameterizedType) {
            Class cls = (Class) parameterizedType.getRawType();
            TypeVariable[] typeParameters = cls.getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            m.b(typeParameters.length == actualTypeArguments.length);
            for (int i = 0; i < typeParameters.length; i++) {
                a(new c(typeParameters[i]), actualTypeArguments[i]);
            }
            a(cls);
            a(parameterizedType.getOwnerType());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(TypeVariable<?> typeVariable) {
            a(typeVariable.getBounds());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(WildcardType wildcardType) {
            a(wildcardType.getUpperBounds());
        }

        private void a(c cVar, Type type) {
            if (!this.a.containsKey(cVar)) {
                Type type2 = type;
                while (type2 != null) {
                    if (cVar.b(type2)) {
                        while (type != null) {
                            type = this.a.remove(c.a(type));
                        }
                        return;
                    }
                    type2 = this.a.get(c.a(type2));
                }
                this.a.put(cVar, type);
            }
        }
    }

    /* compiled from: TypeResolver */
    private static class d {
        static final d a = new d();
        private final AtomicInteger b;

        /* synthetic */ d(AtomicInteger atomicInteger, AnonymousClass1 r2) {
            this(atomicInteger);
        }

        private d() {
            this(new AtomicInteger());
        }

        private d(AtomicInteger atomicInteger) {
            this.b = atomicInteger;
        }

        /* access modifiers changed from: package-private */
        public final Type a(Type type) {
            m.a(type);
            if ((type instanceof Class) || (type instanceof TypeVariable)) {
                return type;
            }
            if (type instanceof GenericArrayType) {
                return Types.a(a().a(((GenericArrayType) type).getGenericComponentType()));
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class cls = (Class) parameterizedType.getRawType();
                TypeVariable<?>[] typeParameters = cls.getTypeParameters();
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    actualTypeArguments[i] = a(typeParameters[i]).a(actualTypeArguments[i]);
                }
                return Types.a(a().b(parameterizedType.getOwnerType()), cls, actualTypeArguments);
            } else if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                return wildcardType.getLowerBounds().length == 0 ? a(wildcardType.getUpperBounds()) : type;
            } else {
                throw new AssertionError("must have been one of the known types");
            }
        }

        /* access modifiers changed from: package-private */
        public TypeVariable<?> a(Type[] typeArr) {
            return Types.a(d.class, "capture#" + this.b.incrementAndGet() + "-of ? extends " + h.a('&').a((Object[]) typeArr), typeArr);
        }

        /* compiled from: TypeResolver */
        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.reflect.f$d$1  reason: invalid class name */
        public class AnonymousClass1 extends d {
            final /* synthetic */ TypeVariable b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            AnonymousClass1(AtomicInteger atomicInteger, TypeVariable typeVariable) {
                super(atomicInteger, null);
                this.b = typeVariable;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.f.d
            public TypeVariable<?> a(Type[] typeArr) {
                LinkedHashSet linkedHashSet = new LinkedHashSet(Arrays.asList(typeArr));
                linkedHashSet.addAll(Arrays.asList(this.b.getBounds()));
                if (linkedHashSet.size() > 1) {
                    linkedHashSet.remove(Object.class);
                }
                return super.a((Type[]) linkedHashSet.toArray(new Type[0]));
            }
        }

        private d a(TypeVariable<?> typeVariable) {
            return new AnonymousClass1(this.b, typeVariable);
        }

        private d a() {
            return new d(this.b);
        }

        private Type b(Type type) {
            if (type == null) {
                return null;
            }
            return a(type);
        }
    }

    /* compiled from: TypeResolver */
    /* access modifiers changed from: package-private */
    public static final class c {
        private final TypeVariable<?> a;

        c(TypeVariable<?> typeVariable) {
            this.a = (TypeVariable) m.a(typeVariable);
        }

        public int hashCode() {
            return j.a(this.a.getGenericDeclaration(), this.a.getName());
        }

        public boolean equals(Object obj) {
            if (obj instanceof c) {
                return a(((c) obj).a);
            }
            return false;
        }

        public String toString() {
            return this.a.toString();
        }

        static c a(Type type) {
            if (type instanceof TypeVariable) {
                return new c((TypeVariable) type);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public boolean b(Type type) {
            if (type instanceof TypeVariable) {
                return a((TypeVariable) type);
            }
            return false;
        }

        private boolean a(TypeVariable<?> typeVariable) {
            return this.a.getGenericDeclaration().equals(typeVariable.getGenericDeclaration()) && this.a.getName().equals(typeVariable.getName());
        }
    }
}
