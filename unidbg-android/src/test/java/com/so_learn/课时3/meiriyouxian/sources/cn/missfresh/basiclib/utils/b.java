package cn.missfresh.basiclib.utils;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ReflectBeanUtils */
public class b {
    public static Map<String, Object> a(Object obj) {
        AppMethodBeat.i(4720, false);
        if (obj == null) {
            AppMethodBeat.o(4720);
            return null;
        }
        Class<?> cls = obj.getClass();
        ArrayList arrayList = new ArrayList();
        do {
            arrayList.add(cls);
            cls = cls.getSuperclass();
            if (cls == null) {
                break;
            }
        } while (cls != Object.class);
        HashMap hashMap = new HashMap();
        for (int i = 0; i < arrayList.size(); i++) {
            Field[] declaredFields = ((Class) arrayList.get(i)).getDeclaredFields();
            for (Field field : declaredFields) {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers) && !Modifier.isFinal(modifiers)) {
                    field.setAccessible(true);
                    try {
                        hashMap.put(field.getName(), field.get(obj));
                    } catch (Exception e) {
                        d.b("ReflectBeanUtils", "", e);
                    }
                }
            }
        }
        AppMethodBeat.o(4720);
        return hashMap;
    }
}
