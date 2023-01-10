package com.google.common.util.concurrent;

import com.google.common.base.g;
import com.google.common.base.m;
import com.google.common.collect.Ordering;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* access modifiers changed from: package-private */
public final class FuturesGetChecked {
    private static final Ordering<Constructor<?>> a = Ordering.natural().onResultOf(new AnonymousClass1()).reverse();

    /* access modifiers changed from: package-private */
    public interface a {
    }

    static a a() {
        return GetCheckedTypeValidatorHolder.WeakSetValidator.INSTANCE;
    }

    static class GetCheckedTypeValidatorHolder {
        static final String a = (GetCheckedTypeValidatorHolder.class.getName() + "$ClassValueValidator");
        static final a b = a();

        GetCheckedTypeValidatorHolder() {
        }

        enum ClassValueValidator implements a {
            INSTANCE;
            
            private static final ClassValue<Boolean> isValidClass = new AnonymousClass1();

            /* renamed from: com.google.common.util.concurrent.FuturesGetChecked$GetCheckedTypeValidatorHolder$ClassValueValidator$1  reason: invalid class name */
            static class AnonymousClass1 extends ClassValue<Boolean> {
                AnonymousClass1() {
                }
            }

            public void validateClass(Class<? extends Exception> cls) {
                isValidClass.get(cls);
            }
        }

        /* access modifiers changed from: package-private */
        public enum WeakSetValidator implements a {
            INSTANCE;
            
            private static final Set<WeakReference<Class<? extends Exception>>> validClasses = new CopyOnWriteArraySet();

            public void validateClass(Class<? extends Exception> cls) {
                for (WeakReference<Class<? extends Exception>> weakReference : validClasses) {
                    if (cls.equals(weakReference.get())) {
                        return;
                    }
                }
                FuturesGetChecked.b(cls);
                if (validClasses.size() > 1000) {
                    validClasses.clear();
                }
                validClasses.add(new WeakReference<>(cls));
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v4, resolved type: java.lang.Object[] */
        /* JADX WARN: Multi-variable type inference failed */
        static a a() {
            try {
                return (a) Class.forName(a).getEnumConstants()[0];
            } catch (Throwable unused) {
                return FuturesGetChecked.a();
            }
        }
    }

    private static boolean c(Class<? extends Exception> cls) {
        try {
            a(cls, new Exception());
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static <X extends Exception> X a(Class<X> cls, Throwable th) {
        for (Constructor constructor : a(Arrays.asList(cls.getConstructors()))) {
            X x = (X) ((Exception) a(constructor, th));
            if (x != null) {
                if (x.getCause() == null) {
                    x.initCause(th);
                }
                return x;
            }
        }
        throw new IllegalArgumentException("No appropriate constructor for exception of type " + cls + " in response to chained exception", th);
    }

    private static <X extends Exception> List<Constructor<X>> a(List<Constructor<X>> list) {
        return a.sortedCopy(list);
    }

    /* renamed from: com.google.common.util.concurrent.FuturesGetChecked$1  reason: invalid class name */
    static class AnonymousClass1 implements g<Constructor<?>, Boolean> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public Boolean apply(Constructor<?> constructor) {
            return Boolean.valueOf(Arrays.asList(constructor.getParameterTypes()).contains(String.class));
        }
    }

    private static <X> X a(Constructor<X> constructor, Throwable th) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] objArr = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> cls = parameterTypes[i];
            if (cls.equals(String.class)) {
                objArr[i] = th.toString();
            } else if (!cls.equals(Throwable.class)) {
                return null;
            } else {
                objArr[i] = th;
            }
        }
        try {
            return constructor.newInstance(objArr);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    static boolean a(Class<? extends Exception> cls) {
        return !RuntimeException.class.isAssignableFrom(cls);
    }

    static void b(Class<? extends Exception> cls) {
        m.a(a(cls), "Futures.getChecked exception type (%s) must not be a RuntimeException", cls);
        m.a(c(cls), "Futures.getChecked exception type (%s) must be an accessible class with an accessible constructor whose parameters (if any) must be of type String and/or Throwable", cls);
    }
}
