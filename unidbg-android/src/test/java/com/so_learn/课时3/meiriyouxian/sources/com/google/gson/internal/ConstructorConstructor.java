package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

public final class ConstructorConstructor {
    private final ReflectionAccessor accessor = ReflectionAccessor.getInstance();
    private final Map<Type, InstanceCreator<?>> instanceCreators;

    public ConstructorConstructor(Map<Type, InstanceCreator<?>> map) {
        this.instanceCreators = map;
    }

    public <T> ObjectConstructor<T> get(TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        Class<? super T> rawType = typeToken.getRawType();
        InstanceCreator<?> instanceCreator = this.instanceCreators.get(type);
        if (instanceCreator != null) {
            return new AnonymousClass1(instanceCreator, type);
        }
        InstanceCreator<?> instanceCreator2 = this.instanceCreators.get(rawType);
        if (instanceCreator2 != null) {
            return new AnonymousClass2(instanceCreator2, type);
        }
        ObjectConstructor<T> newDefaultConstructor = newDefaultConstructor(rawType);
        if (newDefaultConstructor != null) {
            return newDefaultConstructor;
        }
        ObjectConstructor<T> newDefaultImplementationConstructor = newDefaultImplementationConstructor(type, rawType);
        if (newDefaultImplementationConstructor != null) {
            return newDefaultImplementationConstructor;
        }
        return newUnsafeAllocator(type, rawType);
    }

    /* renamed from: com.google.gson.internal.ConstructorConstructor$1  reason: invalid class name */
    class AnonymousClass1 implements ObjectConstructor<T> {
        final /* synthetic */ Type val$type;
        final /* synthetic */ InstanceCreator val$typeCreator;

        AnonymousClass1(InstanceCreator instanceCreator, Type type) {
            this.val$typeCreator = instanceCreator;
            this.val$type = type;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r2 = this;
                com.google.gson.InstanceCreator r0 = r2.val$typeCreator
                java.lang.reflect.Type r1 = r2.val$type
                java.lang.Object r0 = r0.createInstance(r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass1.construct():java.lang.Object");
        }
    }

    /* renamed from: com.google.gson.internal.ConstructorConstructor$2  reason: invalid class name */
    class AnonymousClass2 implements ObjectConstructor<T> {
        final /* synthetic */ InstanceCreator val$rawTypeCreator;
        final /* synthetic */ Type val$type;

        AnonymousClass2(InstanceCreator instanceCreator, Type type) {
            this.val$rawTypeCreator = instanceCreator;
            this.val$type = type;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r2 = this;
                com.google.gson.InstanceCreator r0 = r2.val$rawTypeCreator
                java.lang.reflect.Type r1 = r2.val$type
                java.lang.Object r0 = r0.createInstance(r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass2.construct():java.lang.Object");
        }
    }

    private <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> cls) {
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                this.accessor.makeAccessible(declaredConstructor);
            }
            return new AnonymousClass3(declaredConstructor);
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.ConstructorConstructor$3  reason: invalid class name */
    public class AnonymousClass3 implements ObjectConstructor<T> {
        final /* synthetic */ Constructor val$constructor;

        AnonymousClass3(Constructor constructor) {
            this.val$constructor = constructor;
        }

        /* JADX WARN: Type inference failed for: r0v6, types: [T, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r5 = this;
                java.lang.String r0 = " with no args"
                java.lang.String r1 = "Failed to invoke "
                r2 = 0
                java.lang.reflect.Constructor r3 = r5.val$constructor     // Catch:{ InstantiationException -> 0x0034, InvocationTargetException -> 0x0015, IllegalAccessException -> 0x000e }
                java.lang.Object r0 = r3.newInstance(r2)     // Catch:{ InstantiationException -> 0x0034, InvocationTargetException -> 0x0015, IllegalAccessException -> 0x000e }
                return r0
            L_0x000e:
                r0 = move-exception
                java.lang.AssertionError r1 = new java.lang.AssertionError
                r1.<init>(r0)
                throw r1
            L_0x0015:
                r2 = move-exception
                java.lang.RuntimeException r3 = new java.lang.RuntimeException
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                r4.append(r1)
                java.lang.reflect.Constructor r1 = r5.val$constructor
                r4.append(r1)
                r4.append(r0)
                java.lang.String r0 = r4.toString()
                java.lang.Throwable r1 = r2.getTargetException()
                r3.<init>(r0, r1)
                throw r3
            L_0x0034:
                r2 = move-exception
                java.lang.RuntimeException r3 = new java.lang.RuntimeException
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                r4.append(r1)
                java.lang.reflect.Constructor r1 = r5.val$constructor
                r4.append(r1)
                r4.append(r0)
                java.lang.String r0 = r4.toString()
                r3.<init>(r0, r2)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass3.construct():java.lang.Object");
        }
    }

    private <T> ObjectConstructor<T> newDefaultImplementationConstructor(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new AnonymousClass4();
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new AnonymousClass5(type);
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new AnonymousClass6();
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new AnonymousClass7();
            }
            return new AnonymousClass8();
        } else if (!Map.class.isAssignableFrom(cls)) {
            return null;
        } else {
            if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                return new AnonymousClass9();
            }
            if (ConcurrentMap.class.isAssignableFrom(cls)) {
                return new AnonymousClass10();
            }
            if (SortedMap.class.isAssignableFrom(cls)) {
                return new AnonymousClass11();
            }
            if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(TypeToken.get(((ParameterizedType) type).getActualTypeArguments()[0]).getRawType())) {
                return new AnonymousClass13();
            }
            return new AnonymousClass12();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.ConstructorConstructor$4  reason: invalid class name */
    public class AnonymousClass4 implements ObjectConstructor<T> {
        AnonymousClass4() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.TreeSet] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r1 = this;
                java.util.TreeSet r0 = new java.util.TreeSet
                r0.<init>()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass4.construct():java.lang.Object");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.ConstructorConstructor$5  reason: invalid class name */
    public class AnonymousClass5 implements ObjectConstructor<T> {
        final /* synthetic */ Type val$type;

        AnonymousClass5(Type type) {
            this.val$type = type;
        }

        /* JADX WARN: Type inference failed for: r0v7, types: [T, java.util.EnumSet] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r3 = this;
                java.lang.reflect.Type r0 = r3.val$type
                boolean r1 = r0 instanceof java.lang.reflect.ParameterizedType
                java.lang.String r2 = "Invalid EnumSet type: "
                if (r1 == 0) goto L_0x0038
                java.lang.reflect.ParameterizedType r0 = (java.lang.reflect.ParameterizedType) r0
                java.lang.reflect.Type[] r0 = r0.getActualTypeArguments()
                r1 = 0
                r0 = r0[r1]
                boolean r1 = r0 instanceof java.lang.Class
                if (r1 == 0) goto L_0x001d
                java.lang.Class r0 = (java.lang.Class) r0
                java.util.EnumSet r0 = java.util.EnumSet.noneOf(r0)
                return r0
            L_0x001d:
                com.google.gson.JsonIOException r0 = new com.google.gson.JsonIOException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r2)
                java.lang.reflect.Type r2 = r3.val$type
                java.lang.String r2 = r2.toString()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x0038:
                com.google.gson.JsonIOException r0 = new com.google.gson.JsonIOException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r2)
                java.lang.reflect.Type r2 = r3.val$type
                java.lang.String r2 = r2.toString()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass5.construct():java.lang.Object");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.ConstructorConstructor$6  reason: invalid class name */
    public class AnonymousClass6 implements ObjectConstructor<T> {
        AnonymousClass6() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.LinkedHashSet] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r1 = this;
                java.util.LinkedHashSet r0 = new java.util.LinkedHashSet
                r0.<init>()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass6.construct():java.lang.Object");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.ConstructorConstructor$7  reason: invalid class name */
    public class AnonymousClass7 implements ObjectConstructor<T> {
        AnonymousClass7() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.ArrayDeque] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r1 = this;
                java.util.ArrayDeque r0 = new java.util.ArrayDeque
                r0.<init>()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass7.construct():java.lang.Object");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.ConstructorConstructor$8  reason: invalid class name */
    public class AnonymousClass8 implements ObjectConstructor<T> {
        AnonymousClass8() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.ArrayList] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r1 = this;
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass8.construct():java.lang.Object");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.ConstructorConstructor$9  reason: invalid class name */
    public class AnonymousClass9 implements ObjectConstructor<T> {
        AnonymousClass9() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.concurrent.ConcurrentSkipListMap, T] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r1 = this;
                java.util.concurrent.ConcurrentSkipListMap r0 = new java.util.concurrent.ConcurrentSkipListMap
                r0.<init>()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass9.construct():java.lang.Object");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.ConstructorConstructor$10  reason: invalid class name */
    public class AnonymousClass10 implements ObjectConstructor<T> {
        AnonymousClass10() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.concurrent.ConcurrentHashMap] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r1 = this;
                java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
                r0.<init>()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass10.construct():java.lang.Object");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.ConstructorConstructor$11  reason: invalid class name */
    public class AnonymousClass11 implements ObjectConstructor<T> {
        AnonymousClass11() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.TreeMap, T] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r1 = this;
                java.util.TreeMap r0 = new java.util.TreeMap
                r0.<init>()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass11.construct():java.lang.Object");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.ConstructorConstructor$12  reason: invalid class name */
    public class AnonymousClass12 implements ObjectConstructor<T> {
        AnonymousClass12() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.LinkedHashMap, T] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r1 = this;
                java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
                r0.<init>()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass12.construct():java.lang.Object");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.ConstructorConstructor$13  reason: invalid class name */
    public class AnonymousClass13 implements ObjectConstructor<T> {
        AnonymousClass13() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [T, com.google.gson.internal.LinkedTreeMap] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r1 = this;
                com.google.gson.internal.LinkedTreeMap r0 = new com.google.gson.internal.LinkedTreeMap
                r0.<init>()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass13.construct():java.lang.Object");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.ConstructorConstructor$14  reason: invalid class name */
    public class AnonymousClass14 implements ObjectConstructor<T> {
        private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
        final /* synthetic */ Class val$rawType;
        final /* synthetic */ Type val$type;

        AnonymousClass14(Class cls, Type type) {
            this.val$rawType = cls;
            this.val$type = type;
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.gson.internal.ObjectConstructor
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T construct() {
            /*
                r4 = this;
                com.google.gson.internal.UnsafeAllocator r0 = r4.unsafeAllocator     // Catch:{ Exception -> 0x0009 }
                java.lang.Class r1 = r4.val$rawType     // Catch:{ Exception -> 0x0009 }
                java.lang.Object r0 = r0.newInstance(r1)     // Catch:{ Exception -> 0x0009 }
                return r0
            L_0x0009:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Unable to invoke no-args constructor for "
                r2.append(r3)
                java.lang.reflect.Type r3 = r4.val$type
                r2.append(r3)
                java.lang.String r3 = ". Registering an InstanceCreator with Gson for this type may fix this problem."
                r2.append(r3)
                java.lang.String r2 = r2.toString()
                r1.<init>(r2, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.AnonymousClass14.construct():java.lang.Object");
        }
    }

    private <T> ObjectConstructor<T> newUnsafeAllocator(Type type, Class<? super T> cls) {
        return new AnonymousClass14(cls, type);
    }

    public String toString() {
        return this.instanceCreators.toString();
    }
}
