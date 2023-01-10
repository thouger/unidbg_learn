package com.google.common.reflect;

import com.google.common.base.Predicates;
import com.google.common.base.g;
import com.google.common.base.h;
import com.google.common.base.j;
import com.google.common.base.m;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ag;
import com.google.common.collect.bf;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
public final class Types {
    private static final g<Type, String> a = new AnonymousClass1();
    private static final h b = h.a(", ").b("null");

    /* renamed from: com.google.common.reflect.Types$1  reason: invalid class name */
    static class AnonymousClass1 implements g<Type, String> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public String apply(Type type) {
            return JavaVersion.CURRENT.typeName(type);
        }
    }

    static Type a(Type type) {
        if (!(type instanceof WildcardType)) {
            return JavaVersion.CURRENT.newArrayType(type);
        }
        WildcardType wildcardType = (WildcardType) type;
        Type[] lowerBounds = wildcardType.getLowerBounds();
        boolean z = true;
        m.a(lowerBounds.length <= 1, "Wildcard cannot have more than one lower bounds.");
        if (lowerBounds.length == 1) {
            return c(a(lowerBounds[0]));
        }
        Type[] upperBounds = wildcardType.getUpperBounds();
        if (upperBounds.length != 1) {
            z = false;
        }
        m.a(z, "Wildcard should have only one upper bound.");
        return b(a(upperBounds[0]));
    }

    static ParameterizedType a(Type type, Class<?> cls, Type... typeArr) {
        if (type == null) {
            return a(cls, typeArr);
        }
        m.a(typeArr);
        m.a(cls.getEnclosingClass() != null, "Owner type for unenclosed %s", cls);
        return new ParameterizedTypeImpl(type, cls, typeArr);
    }

    static ParameterizedType a(Class<?> cls, Type... typeArr) {
        return new ParameterizedTypeImpl(ClassOwnership.JVM_BEHAVIOR.getOwnerType(cls), cls, typeArr);
    }

    /* access modifiers changed from: private */
    public enum ClassOwnership {
        OWNED_BY_ENCLOSING_CLASS {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.ClassOwnership
            public Class<?> getOwnerType(Class<?> cls) {
                return cls.getEnclosingClass();
            }
        },
        LOCAL_CLASS_HAS_NO_OWNER {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.ClassOwnership
            public Class<?> getOwnerType(Class<?> cls) {
                if (cls.isLocalClass()) {
                    return null;
                }
                return cls.getEnclosingClass();
            }
        };
        
        static final ClassOwnership JVM_BEHAVIOR = detectJvmBehavior();

        /* access modifiers changed from: package-private */
        public abstract Class<?> getOwnerType(Class<?> cls);

        /* synthetic */ ClassOwnership(AnonymousClass1 r3) {
            this();
        }

        /* access modifiers changed from: package-private */
        public class a<T> {
            a() {
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.reflect.Types$ClassOwnership$3  reason: invalid class name */
        public static class AnonymousClass3 extends a<String> {
            AnonymousClass3() {
            }
        }

        private static ClassOwnership detectJvmBehavior() {
            ParameterizedType parameterizedType = (ParameterizedType) new AnonymousClass3().getClass().getGenericSuperclass();
            ClassOwnership[] values = values();
            for (ClassOwnership classOwnership : values) {
                if (classOwnership.getOwnerType(a.class) == parameterizedType.getOwnerType()) {
                    return classOwnership;
                }
            }
            throw new AssertionError();
        }
    }

    static <D extends GenericDeclaration> TypeVariable<D> a(D d, String str, Type... typeArr) {
        if (typeArr.length == 0) {
            typeArr = new Type[]{Object.class};
        }
        return b(d, str, typeArr);
    }

    static WildcardType b(Type type) {
        return new WildcardTypeImpl(new Type[0], new Type[]{type});
    }

    static WildcardType c(Type type) {
        return new WildcardTypeImpl(new Type[]{type}, new Type[]{Object.class});
    }

    static String d(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    static Type e(Type type) {
        m.a(type);
        AtomicReference atomicReference = new AtomicReference();
        new AnonymousClass2(atomicReference).a(type);
        return (Type) atomicReference.get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.reflect.Types$2  reason: invalid class name */
    public static class AnonymousClass2 extends g {
        final /* synthetic */ AtomicReference a;

        AnonymousClass2(AtomicReference atomicReference) {
            this.a = atomicReference;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(TypeVariable<?> typeVariable) {
            this.a.set(Types.b(typeVariable.getBounds()));
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(WildcardType wildcardType) {
            this.a.set(Types.b(wildcardType.getUpperBounds()));
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(GenericArrayType genericArrayType) {
            this.a.set(genericArrayType.getGenericComponentType());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.g
        public void a(Class<?> cls) {
            this.a.set(cls.getComponentType());
        }
    }

    /* access modifiers changed from: private */
    public static Type b(Type[] typeArr) {
        for (Type type : typeArr) {
            Type e = e(type);
            if (e != null) {
                if (e instanceof Class) {
                    Class cls = (Class) e;
                    if (cls.isPrimitive()) {
                        return cls;
                    }
                }
                return b(e);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final class GenericArrayTypeImpl implements Serializable, GenericArrayType {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        GenericArrayTypeImpl(Type type) {
            this.componentType = JavaVersion.CURRENT.usedInGenericType(type);
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.componentType;
        }

        @Override // java.lang.Object
        public String toString() {
            return Types.d(this.componentType) + "[]";
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.componentType.hashCode();
        }

        @Override // java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof GenericArrayType) {
                return j.a(getGenericComponentType(), ((GenericArrayType) obj).getGenericComponentType());
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static final class ParameterizedTypeImpl implements Serializable, ParameterizedType {
        private static final long serialVersionUID = 0;
        private final ImmutableList<Type> argumentsList;
        private final Type ownerType;
        private final Class<?> rawType;

        ParameterizedTypeImpl(Type type, Class<?> cls, Type[] typeArr) {
            m.a(cls);
            m.a(typeArr.length == cls.getTypeParameters().length);
            Types.b(typeArr, "type parameter");
            this.ownerType = type;
            this.rawType = cls;
            this.argumentsList = JavaVersion.CURRENT.usedInGenericType(typeArr);
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return Types.b((Collection<Type>) this.argumentsList);
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.rawType;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.ownerType;
        }

        @Override // java.lang.Object
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.ownerType != null && JavaVersion.CURRENT.jdkTypeDuplicatesOwnerName()) {
                sb.append(JavaVersion.CURRENT.typeName(this.ownerType));
                sb.append('.');
            }
            sb.append(this.rawType.getName());
            sb.append('<');
            sb.append(Types.b.a(ag.a((Iterable) this.argumentsList, Types.a)));
            sb.append('>');
            return sb.toString();
        }

        @Override // java.lang.Object
        public int hashCode() {
            Type type = this.ownerType;
            return ((type == null ? 0 : type.hashCode()) ^ this.argumentsList.hashCode()) ^ this.rawType.hashCode();
        }

        @Override // java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) obj;
            if (!getRawType().equals(parameterizedType.getRawType()) || !j.a(getOwnerType(), parameterizedType.getOwnerType()) || !Arrays.equals(getActualTypeArguments(), parameterizedType.getActualTypeArguments())) {
                return false;
            }
            return true;
        }
    }

    private static <D extends GenericDeclaration> TypeVariable<D> b(D d, String str, Type[] typeArr) {
        return (TypeVariable) c.a(TypeVariable.class, new c(new b(d, str, typeArr)));
    }

    /* access modifiers changed from: private */
    public static final class c implements InvocationHandler {
        private static final ImmutableMap<String, Method> a;
        private final b<?> b;

        static {
            ImmutableMap.a builder = ImmutableMap.builder();
            Method[] methods = b.class.getMethods();
            for (Method method : methods) {
                if (method.getDeclaringClass().equals(b.class)) {
                    try {
                        method.setAccessible(true);
                    } catch (AccessControlException unused) {
                    }
                    builder.b(method.getName(), method);
                }
            }
            a = builder.b();
        }

        c(b<?> bVar) {
            this.b = bVar;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Method method2 = (Method) a.get(name);
            if (method2 != null) {
                try {
                    return method2.invoke(this.b, objArr);
                } catch (InvocationTargetException e) {
                    throw e.getCause();
                }
            } else {
                throw new UnsupportedOperationException(name);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class b<D extends GenericDeclaration> {
        private final D a;
        private final String b;
        private final ImmutableList<Type> c;

        b(D d, String str, Type[] typeArr) {
            Types.b(typeArr, "bound for type variable");
            this.a = (D) ((GenericDeclaration) m.a(d));
            this.b = (String) m.a(str);
            this.c = ImmutableList.copyOf(typeArr);
        }

        public D a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String toString() {
            return this.b;
        }

        public int hashCode() {
            return this.a.hashCode() ^ this.b.hashCode();
        }

        public boolean equals(Object obj) {
            if (a.a) {
                if (obj == null || !Proxy.isProxyClass(obj.getClass()) || !(Proxy.getInvocationHandler(obj) instanceof c)) {
                    return false;
                }
                b bVar = ((c) Proxy.getInvocationHandler(obj)).b;
                return this.b.equals(bVar.b()) && this.a.equals(bVar.a()) && this.c.equals(bVar.c);
            } else if (!(obj instanceof TypeVariable)) {
                return false;
            } else {
                TypeVariable typeVariable = (TypeVariable) obj;
                return this.b.equals(typeVariable.getName()) && this.a.equals(typeVariable.getGenericDeclaration());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class WildcardTypeImpl implements Serializable, WildcardType {
        private static final long serialVersionUID = 0;
        private final ImmutableList<Type> lowerBounds;
        private final ImmutableList<Type> upperBounds;

        WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            Types.b(typeArr, "lower bound for wildcard");
            Types.b(typeArr2, "upper bound for wildcard");
            this.lowerBounds = JavaVersion.CURRENT.usedInGenericType(typeArr);
            this.upperBounds = JavaVersion.CURRENT.usedInGenericType(typeArr2);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            return Types.b((Collection<Type>) this.lowerBounds);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return Types.b((Collection<Type>) this.upperBounds);
        }

        @Override // java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) obj;
            if (!this.lowerBounds.equals(Arrays.asList(wildcardType.getLowerBounds())) || !this.upperBounds.equals(Arrays.asList(wildcardType.getUpperBounds()))) {
                return false;
            }
            return true;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.lowerBounds.hashCode() ^ this.upperBounds.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            StringBuilder sb = new StringBuilder("?");
            bf<Type> it2 = this.lowerBounds.iterator();
            while (it2.hasNext()) {
                sb.append(" super ");
                sb.append(JavaVersion.CURRENT.typeName(it2.next()));
            }
            for (Type type : Types.b((Iterable<Type>) this.upperBounds)) {
                sb.append(" extends ");
                sb.append(JavaVersion.CURRENT.typeName(type));
            }
            return sb.toString();
        }
    }

    /* access modifiers changed from: private */
    public static Type[] b(Collection<Type> collection) {
        return (Type[]) collection.toArray(new Type[collection.size()]);
    }

    /* access modifiers changed from: private */
    public static Iterable<Type> b(Iterable<Type> iterable) {
        return ag.b(iterable, Predicates.a(Predicates.a(Object.class)));
    }

    /* access modifiers changed from: private */
    public static void b(Type[] typeArr, String str) {
        for (Type type : typeArr) {
            if (type instanceof Class) {
                Class cls = (Class) type;
                m.a(!cls.isPrimitive(), "Primitive type '%s' used as %s", cls, str);
            }
        }
    }

    static Class<?> a(Class<?> cls) {
        return Array.newInstance(cls, 0).getClass();
    }

    /* access modifiers changed from: package-private */
    public enum JavaVersion {
        JAVA6 {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public GenericArrayType newArrayType(Type type) {
                return new GenericArrayTypeImpl(type);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type usedInGenericType(Type type) {
                m.a(type);
                if (!(type instanceof Class)) {
                    return type;
                }
                Class cls = (Class) type;
                return cls.isArray() ? new GenericArrayTypeImpl(cls.getComponentType()) : type;
            }
        },
        JAVA7 {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type newArrayType(Type type) {
                if (type instanceof Class) {
                    return Types.a((Class<?>) ((Class) type));
                }
                return new GenericArrayTypeImpl(type);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type usedInGenericType(Type type) {
                return (Type) m.a(type);
            }
        },
        JAVA8 {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type newArrayType(Type type) {
                return JAVA7.newArrayType(type);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type usedInGenericType(Type type) {
                return JAVA7.usedInGenericType(type);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public String typeName(Type type) {
                try {
                    return (String) Type.class.getMethod("getTypeName", new Class[0]).invoke(type, new Object[0]);
                } catch (NoSuchMethodException unused) {
                    throw new AssertionError("Type.getTypeName should be available in Java 8");
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                }
            }
        },
        JAVA9 {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public boolean jdkTypeDuplicatesOwnerName() {
                return false;
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type newArrayType(Type type) {
                return JAVA8.newArrayType(type);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type usedInGenericType(Type type) {
                return JAVA8.usedInGenericType(type);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public String typeName(Type type) {
                return JAVA8.typeName(type);
            }
        };
        
        static final JavaVersion CURRENT;

        /* access modifiers changed from: package-private */
        public boolean jdkTypeDuplicatesOwnerName() {
            return true;
        }

        /* access modifiers changed from: package-private */
        public abstract Type newArrayType(Type type);

        /* access modifiers changed from: package-private */
        public abstract Type usedInGenericType(Type type);

        /* synthetic */ JavaVersion(AnonymousClass1 r3) {
            this();
        }

        static {
            if (AnnotatedElement.class.isAssignableFrom(TypeVariable.class)) {
                if (new AnonymousClass5().capture().toString().contains("java.util.Map.java.util.Map")) {
                    CURRENT = JAVA8;
                } else {
                    CURRENT = JAVA9;
                }
            } else if (new AnonymousClass6().capture() instanceof Class) {
                CURRENT = JAVA7;
            } else {
                CURRENT = JAVA6;
            }
        }

        /* renamed from: com.google.common.reflect.Types$JavaVersion$5  reason: invalid class name */
        static class AnonymousClass5 extends d<Map.Entry<String, int[][]>> {
            AnonymousClass5() {
            }
        }

        /* renamed from: com.google.common.reflect.Types$JavaVersion$6  reason: invalid class name */
        static class AnonymousClass6 extends d<int[]> {
            AnonymousClass6() {
            }
        }

        /* access modifiers changed from: package-private */
        public final ImmutableList<Type> usedInGenericType(Type[] typeArr) {
            ImmutableList.a builder = ImmutableList.builder();
            for (Type type : typeArr) {
                builder.b(usedInGenericType(type));
            }
            return builder.a();
        }

        /* access modifiers changed from: package-private */
        public String typeName(Type type) {
            return Types.d(type);
        }
    }

    static final class a<X> {
        static final boolean a = (!a.class.getTypeParameters()[0].equals(Types.a(a.class, "X", new Type[0])));

        a() {
        }
    }
}
