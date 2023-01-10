package com.google.common.reflect;

import com.google.common.base.h;
import com.google.common.base.m;
import com.google.common.base.n;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.ab;
import com.google.common.collect.bf;
import com.google.common.collect.r;
import com.google.common.reflect.Types;
import com.google.common.reflect.b;
import com.google.common.reflect.f;
import com.umeng.message.proguard.l;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class TypeToken<T> extends d<T> implements Serializable {
    private static final long serialVersionUID = 3637540370352322684L;
    private transient f covariantTypeResolver;
    private transient f invariantTypeResolver;
    private final Type runtimeType;

    /* synthetic */ TypeToken(Type type, AnonymousClass1 r2) {
        this(type);
    }

    protected TypeToken() {
        this.runtimeType = capture();
        Type type = this.runtimeType;
        m.b(!(type instanceof TypeVariable), "Cannot construct a TypeToken for a type variable.\nYou probably meant to call new TypeToken<%s>(getClass()) that can resolve the type variable for you.\nIf you do need to create a TypeToken of a type variable, please use TypeToken.of() instead.", type);
    }

    protected TypeToken(Class<?> cls) {
        Type capture = super.capture();
        if (capture instanceof Class) {
            this.runtimeType = capture;
        } else {
            this.runtimeType = f.a(cls).c(capture);
        }
    }

    private TypeToken(Type type) {
        this.runtimeType = (Type) m.a(type);
    }

    public static <T> TypeToken<T> of(Class<T> cls) {
        return new SimpleTypeToken(cls);
    }

    public static TypeToken<?> of(Type type) {
        return new SimpleTypeToken(type);
    }

    public final Class<? super T> getRawType() {
        return getRawTypes().iterator().next();
    }

    public final Type getType() {
        return this.runtimeType;
    }

    public final <X> TypeToken<T> where(e<X> eVar, TypeToken<X> typeToken) {
        return new SimpleTypeToken(new f().a(ImmutableMap.of(new f.c(eVar.a), typeToken.runtimeType)).c(this.runtimeType));
    }

    public final <X> TypeToken<T> where(e<X> eVar, Class<X> cls) {
        return where(eVar, of((Class) cls));
    }

    public final TypeToken<?> resolveType(Type type) {
        m.a(type);
        return of(getInvariantTypeResolver().c(type));
    }

    private TypeToken<?> resolveSupertype(Type type) {
        TypeToken<?> of = of(getCovariantTypeResolver().c(type));
        of.covariantTypeResolver = this.covariantTypeResolver;
        of.invariantTypeResolver = this.invariantTypeResolver;
        return of;
    }

    /* access modifiers changed from: package-private */
    public final TypeToken<? super T> getGenericSuperclass() {
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            return boundAsSuperclass(((TypeVariable) type).getBounds()[0]);
        }
        if (type instanceof WildcardType) {
            return boundAsSuperclass(((WildcardType) type).getUpperBounds()[0]);
        }
        Type genericSuperclass = getRawType().getGenericSuperclass();
        if (genericSuperclass == null) {
            return null;
        }
        return (TypeToken<? super T>) resolveSupertype(genericSuperclass);
    }

    private TypeToken<? super T> boundAsSuperclass(Type type) {
        TypeToken<? super T> typeToken = (TypeToken<? super T>) of(type);
        if (typeToken.getRawType().isInterface()) {
            return null;
        }
        return typeToken;
    }

    /* access modifiers changed from: package-private */
    public final ImmutableList<TypeToken<? super T>> getGenericInterfaces() {
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            return boundsAsInterfaces(((TypeVariable) type).getBounds());
        }
        if (type instanceof WildcardType) {
            return boundsAsInterfaces(((WildcardType) type).getUpperBounds());
        }
        ImmutableList.a builder = ImmutableList.builder();
        for (Type type2 : getRawType().getGenericInterfaces()) {
            builder.b(resolveSupertype(type2));
        }
        return builder.a();
    }

    private ImmutableList<TypeToken<? super T>> boundsAsInterfaces(Type[] typeArr) {
        ImmutableList.a builder = ImmutableList.builder();
        for (Type type : typeArr) {
            TypeToken<?> of = of(type);
            if (of.getRawType().isInterface()) {
                builder.b(of);
            }
        }
        return builder.a();
    }

    public final TypeToken<T>.TypeSet getTypes() {
        return new TypeSet();
    }

    public final TypeToken<? super T> getSupertype(Class<? super T> cls) {
        m.a(someRawTypeIsSubclassOf(cls), "%s is not a super class of %s", cls, this);
        Type type = this.runtimeType;
        if (type instanceof TypeVariable) {
            return getSupertypeFromUpperBounds(cls, ((TypeVariable) type).getBounds());
        }
        if (type instanceof WildcardType) {
            return getSupertypeFromUpperBounds(cls, ((WildcardType) type).getUpperBounds());
        }
        return cls.isArray() ? getArraySupertype(cls) : (TypeToken<? super T>) resolveSupertype(toGenericType(cls).runtimeType);
    }

    public final TypeToken<? extends T> getSubtype(Class<?> cls) {
        m.a(!(this.runtimeType instanceof TypeVariable), "Cannot get subtype of type variable <%s>", this);
        Type type = this.runtimeType;
        if (type instanceof WildcardType) {
            return getSubtypeFromLowerBounds(cls, ((WildcardType) type).getLowerBounds());
        }
        if (isArray()) {
            return getArraySubtype(cls);
        }
        m.a(getRawType().isAssignableFrom(cls), "%s isn't a subclass of %s", cls, this);
        TypeToken<? extends T> typeToken = (TypeToken<? extends T>) of(resolveTypeArgsForSubclass(cls));
        m.a(typeToken.isSubtypeOf((TypeToken<?>) this), "%s does not appear to be a subtype of %s", typeToken, this);
        return typeToken;
    }

    public final boolean isSupertypeOf(TypeToken<?> typeToken) {
        return typeToken.isSubtypeOf(getType());
    }

    public final boolean isSupertypeOf(Type type) {
        return of(type).isSubtypeOf(getType());
    }

    public final boolean isSubtypeOf(TypeToken<?> typeToken) {
        return isSubtypeOf(typeToken.getType());
    }

    public final boolean isSubtypeOf(Type type) {
        m.a(type);
        if (type instanceof WildcardType) {
            return any(((WildcardType) type).getLowerBounds()).b(this.runtimeType);
        }
        Type type2 = this.runtimeType;
        if (type2 instanceof WildcardType) {
            return any(((WildcardType) type2).getUpperBounds()).a(type);
        }
        if (type2 instanceof TypeVariable) {
            if (type2.equals(type) || any(((TypeVariable) this.runtimeType).getBounds()).a(type)) {
                return true;
            }
            return false;
        } else if (type2 instanceof GenericArrayType) {
            return of(type).isSupertypeOfArray((GenericArrayType) this.runtimeType);
        } else {
            if (type instanceof Class) {
                return someRawTypeIsSubclassOf((Class) type);
            }
            if (type instanceof ParameterizedType) {
                return isSubtypeOfParameterizedType((ParameterizedType) type);
            }
            if (type instanceof GenericArrayType) {
                return isSubtypeOfArrayType((GenericArrayType) type);
            }
            return false;
        }
    }

    public final boolean isArray() {
        return getComponentType() != null;
    }

    public final boolean isPrimitive() {
        Type type = this.runtimeType;
        return (type instanceof Class) && ((Class) type).isPrimitive();
    }

    public final TypeToken<T> wrap() {
        return isPrimitive() ? of(com.google.common.primitives.a.a((Class) this.runtimeType)) : this;
    }

    private boolean isWrapper() {
        return com.google.common.primitives.a.a().contains(this.runtimeType);
    }

    public final TypeToken<T> unwrap() {
        return isWrapper() ? of(com.google.common.primitives.a.b((Class) this.runtimeType)) : this;
    }

    public final TypeToken<?> getComponentType() {
        Type e = Types.e(this.runtimeType);
        if (e == null) {
            return null;
        }
        return of(e);
    }

    public final b<T, Object> method(Method method) {
        m.a(someRawTypeIsSubclassOf(method.getDeclaringClass()), "%s not declared by %s", method, this);
        return new AnonymousClass1(method);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.reflect.TypeToken$1  reason: invalid class name */
    public class AnonymousClass1 extends b.C0110b<T> {
        AnonymousClass1(Method method) {
            super(method);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.b.C0110b, com.google.common.reflect.b
        public Type[] b() {
            return TypeToken.this.getInvariantTypeResolver().a(super.b());
        }

        @Override // com.google.common.reflect.b, com.google.common.reflect.a
        public TypeToken<T> a() {
            return TypeToken.this;
        }

        @Override // com.google.common.reflect.b, com.google.common.reflect.a, java.lang.Object
        public String toString() {
            return a() + "." + super.toString();
        }
    }

    public final b<T, T> constructor(Constructor<?> constructor) {
        m.a(constructor.getDeclaringClass() == getRawType(), "%s not declared by %s", constructor, getRawType());
        return new AnonymousClass2(constructor);
    }

    /* renamed from: com.google.common.reflect.TypeToken$2  reason: invalid class name */
    class AnonymousClass2 extends b.a<T> {
        AnonymousClass2(Constructor constructor) {
            super(constructor);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.b.a, com.google.common.reflect.b
        public Type[] b() {
            return TypeToken.this.getInvariantTypeResolver().a(super.b());
        }

        @Override // com.google.common.reflect.b, com.google.common.reflect.a
        public TypeToken<T> a() {
            return TypeToken.this;
        }

        @Override // com.google.common.reflect.b, com.google.common.reflect.a, java.lang.Object
        public String toString() {
            return a() + l.s + h.a(", ").a((Object[]) b()) + l.t;
        }
    }

    public class TypeSet extends ab<TypeToken<? super T>> implements Serializable {
        private static final long serialVersionUID = 0;
        private transient ImmutableSet<TypeToken<? super T>> types;

        TypeSet() {
        }

        public TypeToken<T>.TypeSet interfaces() {
            return new InterfaceSet(this);
        }

        public TypeToken<T>.TypeSet classes() {
            return new ClassSet(TypeToken.this, null);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ab, com.google.common.collect.s, com.google.common.collect.z
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.types;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<TypeToken<? super T>> a = r.a(b.a.a((b<TypeToken<?>>) TypeToken.this)).a(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).a();
            this.types = a;
            return a;
        }

        public Set<Class<? super T>> rawTypes() {
            return ImmutableSet.copyOf((Collection) b.b.a(TypeToken.this.getRawTypes()));
        }
    }

    /* access modifiers changed from: private */
    public final class InterfaceSet extends TypeToken<T>.TypeSet {
        private static final long serialVersionUID = 0;
        private final transient TypeToken<T>.TypeSet allTypes;
        private transient ImmutableSet<TypeToken<? super T>> interfaces;

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet interfaces() {
            return this;
        }

        InterfaceSet(TypeToken<T>.TypeSet typeSet) {
            super();
            this.allTypes = typeSet;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.reflect.TypeToken.TypeSet, com.google.common.collect.ab, com.google.common.collect.s, com.google.common.collect.z
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.interfaces;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<TypeToken<? super T>> a = r.a(this.allTypes).a(TypeFilter.INTERFACE_ONLY).a();
            this.interfaces = a;
            return a;
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public Set<Class<? super T>> rawTypes() {
            return r.a(b.b.a(TypeToken.this.getRawTypes())).a(new AnonymousClass1()).a();
        }

        /* renamed from: com.google.common.reflect.TypeToken$InterfaceSet$1  reason: invalid class name */
        class AnonymousClass1 implements n<Class<?>> {
            AnonymousClass1() {
            }

            /* renamed from: a */
            public boolean apply(Class<?> cls) {
                return cls.isInterface();
            }
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet classes() {
            throw new UnsupportedOperationException("interfaces().classes() not supported.");
        }

        private Object readResolve() {
            return TypeToken.this.getTypes().interfaces();
        }
    }

    /* access modifiers changed from: private */
    public final class ClassSet extends TypeToken<T>.TypeSet {
        private static final long serialVersionUID = 0;
        private transient ImmutableSet<TypeToken<? super T>> classes;

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet classes() {
            return this;
        }

        private ClassSet() {
            super();
        }

        /* synthetic */ ClassSet(TypeToken typeToken, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.reflect.TypeToken.TypeSet, com.google.common.collect.ab, com.google.common.collect.s, com.google.common.collect.z
        public Set<TypeToken<? super T>> delegate() {
            ImmutableSet<TypeToken<? super T>> immutableSet = this.classes;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<TypeToken<? super T>> a = r.a(b.a.a().a((b<TypeToken<?>>) TypeToken.this)).a(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).a();
            this.classes = a;
            return a;
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public Set<Class<? super T>> rawTypes() {
            return ImmutableSet.copyOf((Collection) b.b.a().a(TypeToken.this.getRawTypes()));
        }

        @Override // com.google.common.reflect.TypeToken.TypeSet
        public TypeToken<T>.TypeSet interfaces() {
            throw new UnsupportedOperationException("classes().interfaces() not supported.");
        }

        private Object readResolve() {
            return TypeToken.this.getTypes().classes();
        }
    }

    /* access modifiers changed from: private */
    public enum TypeFilter implements n<TypeToken<?>> {
        IGNORE_TYPE_VARIABLE_OR_WILDCARD {
            public boolean apply(TypeToken<?> typeToken) {
                return !(((TypeToken) typeToken).runtimeType instanceof TypeVariable) && !(((TypeToken) typeToken).runtimeType instanceof WildcardType);
            }
        },
        INTERFACE_ONLY {
            public boolean apply(TypeToken<?> typeToken) {
                return typeToken.getRawType().isInterface();
            }
        };

        /* synthetic */ TypeFilter(AnonymousClass1 r3) {
            this();
        }
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (obj instanceof TypeToken) {
            return this.runtimeType.equals(((TypeToken) obj).runtimeType);
        }
        return false;
    }

    @Override // java.lang.Object
    public int hashCode() {
        return this.runtimeType.hashCode();
    }

    @Override // java.lang.Object
    public String toString() {
        return Types.d(this.runtimeType);
    }

    /* access modifiers changed from: protected */
    public Object writeReplace() {
        return of(new f().c(this.runtimeType));
    }

    /* renamed from: com.google.common.reflect.TypeToken$3  reason: invalid class name */
    class AnonymousClass3 extends g {
        AnonymousClass3() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(TypeVariable<?> typeVariable) {
            throw new IllegalArgumentException(TypeToken.this.runtimeType + "contains a type variable and is not safe for the operation");
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(WildcardType wildcardType) {
            a(wildcardType.getLowerBounds());
            a(wildcardType.getUpperBounds());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(ParameterizedType parameterizedType) {
            a(parameterizedType.getActualTypeArguments());
            a(parameterizedType.getOwnerType());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(GenericArrayType genericArrayType) {
            a(genericArrayType.getGenericComponentType());
        }
    }

    /* access modifiers changed from: package-private */
    public final TypeToken<T> rejectTypeVariables() {
        new AnonymousClass3().a(this.runtimeType);
        return this;
    }

    private boolean someRawTypeIsSubclassOf(Class<?> cls) {
        bf<Class<? super T>> it2 = getRawTypes().iterator();
        while (it2.hasNext()) {
            if (cls.isAssignableFrom(it2.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean isSubtypeOfParameterizedType(ParameterizedType parameterizedType) {
        Class<? super Object> rawType = of(parameterizedType).getRawType();
        if (!someRawTypeIsSubclassOf(rawType)) {
            return false;
        }
        TypeVariable<Class<? super Object>>[] typeParameters = rawType.getTypeParameters();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int i = 0; i < typeParameters.length; i++) {
            if (!of(getCovariantTypeResolver().c(typeParameters[i])).is(actualTypeArguments[i], typeParameters[i])) {
                return false;
            }
        }
        if (Modifier.isStatic(((Class) parameterizedType.getRawType()).getModifiers()) || parameterizedType.getOwnerType() == null || isOwnedBySubtypeOf(parameterizedType.getOwnerType())) {
            return true;
        }
        return false;
    }

    private boolean isSubtypeOfArrayType(GenericArrayType genericArrayType) {
        Type type = this.runtimeType;
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (!cls.isArray()) {
                return false;
            }
            return of((Class) cls.getComponentType()).isSubtypeOf(genericArrayType.getGenericComponentType());
        } else if (type instanceof GenericArrayType) {
            return of(((GenericArrayType) type).getGenericComponentType()).isSubtypeOf(genericArrayType.getGenericComponentType());
        } else {
            return false;
        }
    }

    private boolean isSupertypeOfArray(GenericArrayType genericArrayType) {
        Type type = this.runtimeType;
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (!cls.isArray()) {
                return cls.isAssignableFrom(Object[].class);
            }
            return of(genericArrayType.getGenericComponentType()).isSubtypeOf(cls.getComponentType());
        } else if (type instanceof GenericArrayType) {
            return of(genericArrayType.getGenericComponentType()).isSubtypeOf(((GenericArrayType) this.runtimeType).getGenericComponentType());
        } else {
            return false;
        }
    }

    private boolean is(Type type, TypeVariable<?> typeVariable) {
        if (this.runtimeType.equals(type)) {
            return true;
        }
        if (!(type instanceof WildcardType)) {
            return canonicalizeWildcardsInType(this.runtimeType).equals(canonicalizeWildcardsInType(type));
        }
        WildcardType canonicalizeWildcardType = canonicalizeWildcardType(typeVariable, (WildcardType) type);
        if (!every(canonicalizeWildcardType.getUpperBounds()).b(this.runtimeType) || !every(canonicalizeWildcardType.getLowerBounds()).a(this.runtimeType)) {
            return false;
        }
        return true;
    }

    private static Type canonicalizeTypeArg(TypeVariable<?> typeVariable, Type type) {
        if (type instanceof WildcardType) {
            return canonicalizeWildcardType(typeVariable, (WildcardType) type);
        }
        return canonicalizeWildcardsInType(type);
    }

    private static Type canonicalizeWildcardsInType(Type type) {
        if (type instanceof ParameterizedType) {
            return canonicalizeWildcardsInParameterizedType((ParameterizedType) type);
        }
        return type instanceof GenericArrayType ? Types.a(canonicalizeWildcardsInType(((GenericArrayType) type).getGenericComponentType())) : type;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.ArrayList */
    /* JADX WARN: Multi-variable type inference failed */
    private static WildcardType canonicalizeWildcardType(TypeVariable<?> typeVariable, WildcardType wildcardType) {
        Type[] bounds = typeVariable.getBounds();
        ArrayList arrayList = new ArrayList();
        Type[] upperBounds = wildcardType.getUpperBounds();
        for (Type type : upperBounds) {
            if (!any(bounds).a(type)) {
                arrayList.add(canonicalizeWildcardsInType(type));
            }
        }
        return new Types.WildcardTypeImpl(wildcardType.getLowerBounds(), (Type[]) arrayList.toArray(new Type[0]));
    }

    private static ParameterizedType canonicalizeWildcardsInParameterizedType(ParameterizedType parameterizedType) {
        Class cls = (Class) parameterizedType.getRawType();
        TypeVariable<Class<T>>[] typeParameters = cls.getTypeParameters();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int i = 0; i < actualTypeArguments.length; i++) {
            actualTypeArguments[i] = canonicalizeTypeArg(typeParameters[i], actualTypeArguments[i]);
        }
        return Types.a(parameterizedType.getOwnerType(), cls, actualTypeArguments);
    }

    private static a every(Type[] typeArr) {
        return new a(typeArr, false);
    }

    private static a any(Type[] typeArr) {
        return new a(typeArr, true);
    }

    /* access modifiers changed from: private */
    public static class a {
        private final Type[] a;
        private final boolean b;

        a(Type[] typeArr, boolean z) {
            this.a = typeArr;
            this.b = z;
        }

        /* access modifiers changed from: package-private */
        public boolean a(Type type) {
            for (Type type2 : this.a) {
                boolean isSubtypeOf = TypeToken.of(type2).isSubtypeOf(type);
                boolean z = this.b;
                if (isSubtypeOf == z) {
                    return z;
                }
            }
            return !this.b;
        }

        /* access modifiers changed from: package-private */
        public boolean b(Type type) {
            TypeToken<?> of = TypeToken.of(type);
            for (Type type2 : this.a) {
                boolean isSubtypeOf = of.isSubtypeOf(type2);
                boolean z = this.b;
                if (isSubtypeOf == z) {
                    return z;
                }
            }
            return !this.b;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.reflect.TypeToken$4  reason: invalid class name */
    public class AnonymousClass4 extends g {
        final /* synthetic */ ImmutableSet.a a;

        AnonymousClass4(ImmutableSet.a aVar) {
            this.a = aVar;
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

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(ParameterizedType parameterizedType) {
            this.a.b((Class) parameterizedType.getRawType());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(Class<?> cls) {
            this.a.b(cls);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(GenericArrayType genericArrayType) {
            this.a.b(Types.a((Class<?>) TypeToken.of(genericArrayType.getGenericComponentType()).getRawType()));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ImmutableSet<Class<? super T>> getRawTypes() {
        ImmutableSet.a builder = ImmutableSet.builder();
        new AnonymousClass4(builder).a(this.runtimeType);
        return builder.a();
    }

    private boolean isOwnedBySubtypeOf(Type type) {
        Iterator<TypeToken<? super T>> it2 = getTypes().iterator();
        while (it2.hasNext()) {
            Type ownerTypeIfPresent = it2.next().getOwnerTypeIfPresent();
            if (ownerTypeIfPresent != null && of(ownerTypeIfPresent).isSubtypeOf(type)) {
                return true;
            }
        }
        return false;
    }

    private Type getOwnerTypeIfPresent() {
        Type type = this.runtimeType;
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType) type).getOwnerType();
        }
        if (type instanceof Class) {
            return ((Class) type).getEnclosingClass();
        }
        return null;
    }

    static <T> TypeToken<? extends T> toGenericType(Class<T> cls) {
        if (cls.isArray()) {
            return (TypeToken<? extends T>) of(Types.a(toGenericType(cls.getComponentType()).runtimeType));
        }
        TypeVariable<Class<T>>[] typeParameters = cls.getTypeParameters();
        Type type = (!cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) ? null : toGenericType(cls.getEnclosingClass()).runtimeType;
        return (typeParameters.length > 0 || !(type == null || type == cls.getEnclosingClass())) ? (TypeToken<? extends T>) of(Types.a(type, (Class<?>) cls, (Type[]) typeParameters)) : of((Class) cls);
    }

    /* access modifiers changed from: private */
    public f getCovariantTypeResolver() {
        f fVar = this.covariantTypeResolver;
        if (fVar != null) {
            return fVar;
        }
        f a2 = f.a(this.runtimeType);
        this.covariantTypeResolver = a2;
        return a2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private f getInvariantTypeResolver() {
        f fVar = this.invariantTypeResolver;
        if (fVar != null) {
            return fVar;
        }
        f b2 = f.b(this.runtimeType);
        this.invariantTypeResolver = b2;
        return b2;
    }

    private TypeToken<? super T> getSupertypeFromUpperBounds(Class<? super T> cls, Type[] typeArr) {
        for (Type type : typeArr) {
            TypeToken<?> of = of(type);
            if (of.isSubtypeOf(cls)) {
                return of.getSupertype(cls);
            }
        }
        throw new IllegalArgumentException(cls + " isn't a super type of " + this);
    }

    private TypeToken<? extends T> getSubtypeFromLowerBounds(Class<?> cls, Type[] typeArr) {
        if (typeArr.length > 0) {
            return of(typeArr[0]).getSubtype(cls);
        }
        throw new IllegalArgumentException(cls + " isn't a subclass of " + this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: com.google.common.reflect.TypeToken */
    /* JADX WARN: Multi-variable type inference failed */
    private TypeToken<? super T> getArraySupertype(Class<? super T> cls) {
        return (TypeToken<? super T>) of(newArrayClassOrGenericArrayType(((TypeToken) m.a(getComponentType(), "%s isn't a super type of %s", cls, this)).getSupertype(cls.getComponentType()).runtimeType));
    }

    private TypeToken<? extends T> getArraySubtype(Class<?> cls) {
        return (TypeToken<? extends T>) of(newArrayClassOrGenericArrayType(getComponentType().getSubtype(cls.getComponentType()).runtimeType));
    }

    private Type resolveTypeArgsForSubclass(Class<?> cls) {
        if ((this.runtimeType instanceof Class) && (cls.getTypeParameters().length == 0 || getRawType().getTypeParameters().length != 0)) {
            return cls;
        }
        TypeToken genericType = toGenericType(cls);
        return new f().a(genericType.getSupertype(getRawType()).runtimeType, this.runtimeType).c(genericType.runtimeType);
    }

    private static Type newArrayClassOrGenericArrayType(Type type) {
        return Types.JavaVersion.JAVA7.newArrayType(type);
    }

    /* access modifiers changed from: private */
    public static final class SimpleTypeToken<T> extends TypeToken<T> {
        private static final long serialVersionUID = 0;

        SimpleTypeToken(Type type) {
            super(type, null);
        }
    }

    /* access modifiers changed from: private */
    public static abstract class b<K> {
        static final b<TypeToken<?>> a = new AnonymousClass1();
        static final b<Class<?>> b = new AnonymousClass2();

        /* access modifiers changed from: package-private */
        public abstract Class<?> b(K k);

        /* access modifiers changed from: package-private */
        public abstract Iterable<? extends K> c(K k);

        /* access modifiers changed from: package-private */
        public abstract K d(K k);

        private b() {
        }

        /* synthetic */ b(AnonymousClass1 r1) {
            this();
        }

        /* renamed from: com.google.common.reflect.TypeToken$b$1  reason: invalid class name */
        static class AnonymousClass1 extends b<TypeToken<?>> {
            AnonymousClass1() {
                super(null);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public Class<?> b(TypeToken<?> typeToken) {
                return typeToken.getRawType();
            }

            /* access modifiers changed from: package-private */
            /* renamed from: b */
            public Iterable<? extends TypeToken<?>> c(TypeToken<?> typeToken) {
                return typeToken.getGenericInterfaces();
            }

            /* access modifiers changed from: package-private */
            /* renamed from: c */
            public TypeToken<?> d(TypeToken<?> typeToken) {
                return typeToken.getGenericSuperclass();
            }
        }

        /* renamed from: com.google.common.reflect.TypeToken$b$2  reason: invalid class name */
        static class AnonymousClass2 extends b<Class<?>> {
            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public Class<?> b(Class<?> cls) {
                return cls;
            }

            AnonymousClass2() {
                super(null);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: b */
            public Iterable<? extends Class<?>> c(Class<?> cls) {
                return Arrays.asList(cls.getInterfaces());
            }

            /* access modifiers changed from: package-private */
            /* renamed from: c */
            public Class<?> d(Class<?> cls) {
                return cls.getSuperclass();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.reflect.TypeToken$b$3  reason: invalid class name */
        public class AnonymousClass3 extends a<K> {
            AnonymousClass3(b bVar) {
                super(bVar);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeToken.b.a, com.google.common.reflect.TypeToken.b
            public Iterable<? extends K> c(K k) {
                return ImmutableSet.of();
            }

            /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.reflect.TypeToken$b$3 */
            /* JADX WARN: Multi-variable type inference failed */
            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeToken.b
            public ImmutableList<K> a(Iterable<? extends K> iterable) {
                ImmutableList.a builder = ImmutableList.builder();
                for (Object obj : iterable) {
                    if (!b(obj).isInterface()) {
                        builder.b(obj);
                    }
                }
                return super.a((Iterable) builder.a());
            }
        }

        /* access modifiers changed from: package-private */
        public final b<K> a() {
            return new AnonymousClass3(this);
        }

        /* access modifiers changed from: package-private */
        public final ImmutableList<K> a(K k) {
            return a((Iterable) ImmutableList.of(k));
        }

        /* access modifiers changed from: package-private */
        public ImmutableList<K> a(Iterable<? extends K> iterable) {
            HashMap c = Maps.c();
            Iterator<? extends K> it2 = iterable.iterator();
            while (it2.hasNext()) {
                a((b<K>) it2.next(), (Map<? super b<K>, Integer>) c);
            }
            return a(c, Ordering.natural().reverse());
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v4, resolved type: int */
        /* JADX DEBUG: Multi-variable search result rejected for r0v10, resolved type: int */
        /* JADX WARN: Multi-variable type inference failed */
        private int a(K k, Map<? super K, Integer> map) {
            Integer num = map.get(k);
            if (num != null) {
                return num.intValue();
            }
            boolean isInterface = b(k).isInterface();
            Iterator<? extends K> it2 = c(k).iterator();
            int i = isInterface;
            while (it2.hasNext()) {
                i = Math.max(i, a((b<K>) it2.next(), map));
            }
            Object d = d(k);
            int i2 = i;
            if (d != null) {
                i2 = Math.max(i, a((b<K>) d, map));
            }
            int i3 = (i2 == 1 ? 1 : 0) + 1;
            map.put(k, Integer.valueOf(i3));
            return i3;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.reflect.TypeToken$b$4  reason: invalid class name */
        public static class AnonymousClass4 extends Ordering<K> {
            final /* synthetic */ Comparator a;
            final /* synthetic */ Map b;

            AnonymousClass4(Comparator comparator, Map map) {
                this.a = comparator;
                this.b = map;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Comparator */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.Ordering, java.util.Comparator
            public int compare(K k, K k2) {
                return this.a.compare(this.b.get(k), this.b.get(k2));
            }
        }

        private static <K, V> ImmutableList<K> a(Map<K, V> map, Comparator<? super V> comparator) {
            return (ImmutableList<K>) new AnonymousClass4(comparator, map).immutableSortedCopy(map.keySet());
        }

        private static class a<K> extends b<K> {
            private final b<K> c;

            a(b<K> bVar) {
                super(null);
                this.c = bVar;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeToken.b
            public Class<?> b(K k) {
                return this.c.b(k);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeToken.b
            public Iterable<? extends K> c(K k) {
                return this.c.c(k);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeToken.b
            public K d(K k) {
                return (K) this.c.d(k);
            }
        }
    }
}
