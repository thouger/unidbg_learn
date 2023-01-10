package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class UnsafeAllocator {
    public abstract <T> T newInstance(Class<T> cls) throws Exception;

    public static UnsafeAllocator create() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return new AnonymousClass1(cls.getMethod("allocateInstance", Class.class), declaredField.get(null));
        } catch (Exception unused) {
            try {
                Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                declaredMethod.setAccessible(true);
                int intValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                declaredMethod2.setAccessible(true);
                return new AnonymousClass2(declaredMethod2, intValue);
            } catch (Exception unused2) {
                try {
                    Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                    declaredMethod3.setAccessible(true);
                    return new AnonymousClass3(declaredMethod3);
                } catch (Exception unused3) {
                    return new AnonymousClass4();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.UnsafeAllocator$1  reason: invalid class name */
    public static class AnonymousClass1 extends UnsafeAllocator {
        final /* synthetic */ Method val$allocateInstance;
        final /* synthetic */ Object val$unsafe;

        AnonymousClass1(Method method, Object obj) {
            this.val$allocateInstance = method;
            this.val$unsafe = obj;
        }

        @Override // com.google.gson.internal.UnsafeAllocator
        public <T> T newInstance(Class<T> cls) throws Exception {
            assertInstantiable(cls);
            return (T) this.val$allocateInstance.invoke(this.val$unsafe, cls);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.UnsafeAllocator$2  reason: invalid class name */
    public static class AnonymousClass2 extends UnsafeAllocator {
        final /* synthetic */ int val$constructorId;
        final /* synthetic */ Method val$newInstance;

        AnonymousClass2(Method method, int i) {
            this.val$newInstance = method;
            this.val$constructorId = i;
        }

        @Override // com.google.gson.internal.UnsafeAllocator
        public <T> T newInstance(Class<T> cls) throws Exception {
            assertInstantiable(cls);
            return (T) this.val$newInstance.invoke(null, cls, Integer.valueOf(this.val$constructorId));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.UnsafeAllocator$3  reason: invalid class name */
    public static class AnonymousClass3 extends UnsafeAllocator {
        final /* synthetic */ Method val$newInstance;

        AnonymousClass3(Method method) {
            this.val$newInstance = method;
        }

        @Override // com.google.gson.internal.UnsafeAllocator
        public <T> T newInstance(Class<T> cls) throws Exception {
            assertInstantiable(cls);
            return (T) this.val$newInstance.invoke(null, cls, Object.class);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.gson.internal.UnsafeAllocator$4  reason: invalid class name */
    public static class AnonymousClass4 extends UnsafeAllocator {
        AnonymousClass4() {
        }

        @Override // com.google.gson.internal.UnsafeAllocator
        public <T> T newInstance(Class<T> cls) {
            throw new UnsupportedOperationException("Cannot allocate " + cls);
        }
    }

    static void assertInstantiable(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: " + cls.getName());
        } else if (Modifier.isAbstract(modifiers)) {
            throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: " + cls.getName());
        }
    }
}
