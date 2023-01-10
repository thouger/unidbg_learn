package de.greenrobot.event;

import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
public abstract class SubscriberIndex {
    private Map<Class<?>, SubscriberMethod[]> map = new HashMap();

    /* access modifiers changed from: package-private */
    public abstract SubscriberMethod[] createSubscribersFor(Class<?> cls);

    SubscriberIndex() {
    }

    /* access modifiers changed from: package-private */
    public SubscriberMethod[] getSubscribersFor(Class<?> cls) {
        SubscriberMethod[] subscriberMethodArr = this.map.get(cls);
        if (subscriberMethodArr == null && (subscriberMethodArr = createSubscribersFor(cls)) != null) {
            this.map.put(cls, subscriberMethodArr);
        }
        return subscriberMethodArr;
    }

    /* access modifiers changed from: package-private */
    public SubscriberMethod createSubscriberMethod(Class<?> cls, String str, Class<?> cls2, ThreadMode threadMode, int i, boolean z) {
        try {
            return new SubscriberMethod(cls.getDeclaredMethod(str, cls2), cls2, threadMode, i, z);
        } catch (NoSuchMethodException e) {
            throw new EventBusException("Could not find subscriber method in " + cls + ". Maybe a missing ProGuard rule?", e);
        }
    }
}
