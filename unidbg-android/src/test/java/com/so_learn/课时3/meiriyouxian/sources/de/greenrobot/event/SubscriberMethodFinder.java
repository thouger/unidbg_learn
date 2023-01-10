package de.greenrobot.event;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public class SubscriberMethodFinder {
    private static final int BRIDGE = 64;
    private static final SubscriberIndex INDEX;
    private static final Map<String, List<SubscriberMethod>> METHOD_CACHE = new HashMap();
    private static final int MODIFIERS_IGNORE = 5192;
    private static final int SYNTHETIC = 4096;
    private final boolean strictMethodVerification;

    static {
        SubscriberIndex subscriberIndex;
        try {
            subscriberIndex = (SubscriberIndex) Class.forName("de.greenrobot.event.GeneratedSubscriberIndex").newInstance();
        } catch (ClassNotFoundException unused) {
            Log.d(EventBus.TAG, "No subscriber index available, reverting to dynamic look-up");
        } catch (Exception e) {
            Log.w(EventBus.TAG, "Could not init subscriber index, reverting to dynamic look-up", e);
        }
        INDEX = subscriberIndex;
        subscriberIndex = null;
        INDEX = subscriberIndex;
    }

    SubscriberMethodFinder(boolean z) {
        this.strictMethodVerification = z;
    }

    /* access modifiers changed from: package-private */
    public List<SubscriberMethod> findSubscriberMethods(Class<?> cls, boolean z) {
        List<SubscriberMethod> list;
        List<SubscriberMethod> list2;
        String name = cls.getName();
        synchronized (METHOD_CACHE) {
            list = METHOD_CACHE.get(name);
        }
        if (list != null) {
            return list;
        }
        if (INDEX == null || z) {
            list2 = findSubscriberMethodsWithReflection(cls);
        } else {
            list2 = findSubscriberMethodsWithIndex(cls);
            if (list2.isEmpty()) {
                list2 = findSubscriberMethodsWithReflection(cls);
            }
        }
        if (!list2.isEmpty()) {
            synchronized (METHOD_CACHE) {
                METHOD_CACHE.put(name, list2);
            }
            return list2;
        }
        throw new EventBusException("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
    }

    private List<SubscriberMethod> findSubscriberMethodsWithIndex(Class<?> cls) {
        while (cls != null) {
            SubscriberMethod[] subscribersFor = INDEX.getSubscribersFor(cls);
            if (subscribersFor == null || subscribersFor.length <= 0) {
                String name = cls.getName();
                if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                    break;
                }
                cls = cls.getSuperclass();
            } else {
                ArrayList arrayList = new ArrayList();
                for (SubscriberMethod subscriberMethod : subscribersFor) {
                    arrayList.add(subscriberMethod);
                }
                return arrayList;
            }
        }
        return Collections.EMPTY_LIST;
    }

    private List<SubscriberMethod> findSubscriberMethodsWithReflection(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            String name = cls2.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break;
            }
            Method[] declaredMethods = cls2.getDeclaredMethods();
            int length = declaredMethods.length;
            int i = 0;
            int i2 = 0;
            while (i2 < length) {
                Method method = declaredMethods[i2];
                int modifiers = method.getModifiers();
                if ((modifiers & 1) != 0 && (modifiers & MODIFIERS_IGNORE) == 0) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1) {
                        Subscribe subscribe = (Subscribe) method.getAnnotation(Subscribe.class);
                        if (subscribe != null) {
                            String name2 = method.getName();
                            Class<?> cls3 = parameterTypes[i];
                            sb.setLength(i);
                            sb.append(name2);
                            sb.append('>');
                            sb.append(cls3.getName());
                            if (hashSet.add(sb.toString())) {
                                arrayList.add(new SubscriberMethod(method, cls3, subscribe.threadMode(), subscribe.priority(), subscribe.sticky()));
                            }
                        }
                    } else if (this.strictMethodVerification && method.isAnnotationPresent(Subscribe.class)) {
                        throw new EventBusException("@Subscribe method " + (name + "." + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                    }
                } else if (this.strictMethodVerification && method.isAnnotationPresent(Subscribe.class)) {
                    throw new EventBusException((name + "." + method.getName()) + " is a illegal @Subscribe method: must be public, non-static, and non-abstract");
                }
                i2++;
                i = 0;
            }
        }
        return arrayList;
    }

    static void clearCaches() {
        synchronized (METHOD_CACHE) {
            METHOD_CACHE.clear();
        }
    }
}
