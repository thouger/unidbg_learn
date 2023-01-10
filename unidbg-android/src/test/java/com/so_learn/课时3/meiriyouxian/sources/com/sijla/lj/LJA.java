package com.sijla.lj;

import android.accounts.AccountManager;
import android.content.Context;
import android.hardware.Camera;
import android.view.View;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LJA {
    private static Class<?> ArrayList_class = ArrayList.class;
    private static Class<?> Character_class = Character.class;
    private static Class<?> HashMap_class = HashMap.class;
    private static Class<?> List_class = List.class;
    private static Class<?> LuaFunction_class = b.class;
    private static Class<?> LuaObject_class = c.class;
    private static Class<?> LuaState_class = L.class;
    private static Class<?> LuaTable_class = g.class;
    private static Class<?> Map_class = Map.class;
    private static Class<?> Number_class = Number.class;
    private static Class<?> String_class = String.class;
    public static HashMap<String, Method[]> methodCache = new HashMap<>();
    public static HashMap<String, Method[]> methodsMap = new HashMap<>();

    private LJA() {
    }

    public static int objectIndex(long j, Object obj, String str, int i) {
        int checkField;
        L a = f.a(j);
        synchronized (a) {
            if (i == 0) {
                try {
                    if (checkMethod(a, obj, str) != 0) {
                        return 2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if ((i == 0 || i == 1 || i == 5) && (checkField = checkField(a, obj, str)) != 0) {
                return checkField;
            }
            if ((i == 0 || i == 4) && javaGetter(a, obj, str) != 0) {
                return 4;
            }
            if ((i == 0 || i == 3) && checkClass(a, obj, str) != 0) {
                return 3;
            }
            if ((i != 0 && i != 6) || !(obj instanceof e)) {
                return 0;
            }
            a.c(((e) obj).a(str));
            return 6;
        }
    }

    public static int callMethod(long j, Object obj, String str) {
        boolean z;
        L a = f.a(j);
        synchronized (a) {
            Method[] methodArr = methodCache.get(str);
            int c = a.c();
            Object[] objArr = new Object[c];
            Method method = null;
            int i = 0;
            while (true) {
                if (i >= methodArr.length) {
                    break;
                }
                Class<?>[] parameterTypes = methodArr[i].getParameterTypes();
                if (parameterTypes.length == c) {
                    int i2 = 0;
                    while (i2 < parameterTypes.length) {
                        try {
                            int i3 = i2 + 1;
                            objArr[i2] = compareTypes(a, parameterTypes[i2], i3);
                            i2 = i3;
                        } catch (Exception unused) {
                            z = false;
                        }
                    }
                    z = true;
                    if (z) {
                        method = methodArr[i];
                        break;
                    }
                }
                i++;
            }
            if (method == null) {
                StringBuilder sb = new StringBuilder();
                for (Method method2 : methodArr) {
                    sb.append(method2.toString());
                    sb.append("\n");
                }
                throw new LException("Invalid method call. Invalid Parameters.\n" + sb.toString());
            }
            try {
                if (!Modifier.isPublic(method.getModifiers())) {
                    method.setAccessible(true);
                }
                Object invoke = method.invoke(obj, objArr);
                if (invoke == null && method.getReturnType().equals(Void.TYPE)) {
                    return 0;
                }
                a.c(invoke);
                return 1;
            } catch (Exception e) {
                throw new LException(e);
            }
        }
    }

    public static int objectNewIndex(long j, Object obj, String str) {
        L a = f.a(j);
        synchronized (a) {
            if (setFieldValue(a, obj, str) != 0) {
                return 1;
            }
            if (javaSetter(a, obj, str) != 0) {
                return 1;
            }
            return 0;
        }
    }

    public static int setFieldValue(L l, Object obj, String str) {
        boolean z;
        Class<?> cls;
        synchronized (l) {
            if (obj == null) {
                return 0;
            }
            if (obj instanceof Class) {
                cls = (Class) obj;
                z = true;
            } else {
                cls = obj.getClass();
                z = false;
            }
            try {
                Field field = cls.getField(str);
                if (field == null) {
                    return 0;
                }
                if (z && !Modifier.isStatic(field.getModifiers())) {
                    return 0;
                }
                Class<?> type = field.getType();
                try {
                    if (!Modifier.isPublic(field.getModifiers())) {
                        field.setAccessible(true);
                    }
                    field.set(obj, compareTypes(l, type, 3));
                } catch (LException unused) {
                    argError(l, str, 3, type);
                } catch (Exception e) {
                    throw new LException(e);
                }
                return 1;
            } catch (NoSuchFieldException unused2) {
                return 0;
            }
        }
    }

    private static String argError(L l, String str, int i, Class cls) {
        throw new LException("bad argument to '" + str + "' (" + cls.getName() + " expected, got " + typeName(l, 3) + " value)");
    }

    private static String typeName(L l, int i) {
        if (l.z(i)) {
            return l.y(i).getClass().getName();
        }
        switch (l.l(i)) {
            case 1:
                return "boolean";
            case 2:
            case 7:
                return AccountManager.KEY_USERDATA;
            case 3:
                return "number";
            case 4:
                return "string";
            case 5:
                return "table";
            case 6:
                return "function";
            case 8:
                return "thread";
            default:
                return "unkown";
        }
    }

    public static int setArrayValue(long j, Object obj, int i) {
        L a = f.a(j);
        synchronized (a) {
            if (obj.getClass().isArray()) {
                Class<?> componentType = obj.getClass().getComponentType();
                try {
                    Array.set(obj, i, compareTypes(a, componentType, 3));
                } catch (LException unused) {
                    argError(a, obj.getClass().getName() + " [" + i + "]", 3, componentType);
                }
            } else if (obj instanceof List) {
                ((List) obj).set(i, a.B(3));
            } else if (obj instanceof Map) {
                ((Map) obj).put(Integer.valueOf(i), a.B(3));
            } else {
                throw new LException("can not set " + obj.getClass().getName() + " value: " + a.B(3) + " in " + i);
            }
        }
        return 0;
    }

    public static int getArrayValue(long j, Object obj, int i) {
        Object obj2;
        L a = f.a(j);
        synchronized (a) {
            if (obj.getClass().isArray()) {
                obj2 = Array.get(obj, i);
            } else if (obj instanceof List) {
                obj2 = ((List) obj).get(i);
            } else if (obj instanceof Map) {
                obj2 = ((Map) obj).get(Integer.valueOf(i));
            } else {
                throw new LException("can not get " + obj.getClass().getName() + " value in " + i);
            }
            a.c(obj2);
        }
        return 1;
    }

    public static int asTable(long j, Object obj) {
        L a = f.a(j);
        synchronized (a) {
            try {
                a.e();
                if (obj.getClass().isArray()) {
                    int length = Array.getLength(obj);
                    int i = 0;
                    while (i <= length - 1) {
                        a.c(Array.get(obj, i));
                        i++;
                        a.b(-2, (long) i);
                    }
                } else if (obj instanceof Collection) {
                    int i2 = 1;
                    for (Object obj2 : (Collection) obj) {
                        a.c(obj2);
                        a.b(-2, (long) i2);
                        i2++;
                    }
                } else if (obj instanceof Map) {
                    for (Map.Entry entry : ((Map) obj).entrySet()) {
                        a.c(entry.getKey());
                        a.c(entry.getValue());
                        a.u(-3);
                    }
                }
                a.b(-1);
            } catch (Exception e) {
                throw new LException("can not astable: " + e.getMessage());
            } catch (Throwable th) {
                throw th;
            }
        }
        return 1;
    }

    public static int newArray(long j, Class<?> cls, int i) {
        L a = f.a(j);
        synchronized (a) {
            try {
                a.b(Array.newInstance(cls, i));
            } catch (Exception e) {
                throw new LException("can not create a array: " + e.getMessage());
            } catch (Throwable th) {
                throw th;
            }
        }
        return 1;
    }

    public static int newArray(long j, Class<?> cls) {
        L a = f.a(j);
        synchronized (a) {
            try {
                int c = a.c() - 1;
                int[] iArr = new int[c];
                for (int i = 0; i < c; i++) {
                    iArr[i] = (int) a.o(i + 2);
                }
                a.b(Array.newInstance(cls, iArr));
            } catch (Exception e) {
                throw new LException("can not create a array: " + e.getMessage());
            } catch (Throwable th) {
                throw th;
            }
        }
        return 1;
    }

    public static Class javaBindClass(String str) {
        try {
            return Class.forName(str);
        } catch (Exception unused) {
            if (str.equals("boolean")) {
                return Boolean.TYPE;
            }
            if (str.equals("byte")) {
                return Byte.TYPE;
            }
            if (str.equals("char")) {
                return Character.TYPE;
            }
            if (str.equals("short")) {
                return Short.TYPE;
            }
            if (str.equals("int")) {
                return Integer.TYPE;
            }
            if (str.equals("long")) {
                return Long.TYPE;
            }
            if (str.equals("float")) {
                return Float.TYPE;
            }
            if (str.equals("double")) {
                return Double.TYPE;
            }
            throw new LException("Class not found: " + str);
        }
    }

    public static int javaNewInstance(long j, String str) {
        L a = f.a(j);
        synchronized (a) {
            Class javaBindClass = javaBindClass(str);
            if (javaBindClass.isPrimitive()) {
                return toPrimitive(a, javaBindClass, -1);
            }
            return getObjInstance(a, javaBindClass);
        }
    }

    public static int javaNew(long j, Class<?> cls) {
        L a = f.a(j);
        synchronized (a) {
            if (cls.isPrimitive()) {
                return toPrimitive(a, cls, -1);
            }
            return getObjInstance(a, cls);
        }
    }

    public static int javaCreate(long j, Class<?> cls) {
        L a = f.a(j);
        synchronized (a) {
            if (cls.isInterface()) {
                return createProxyObject(a, cls);
            } else if (cls.isPrimitive()) {
                return createArray(a, cls);
            } else if (List_class.isAssignableFrom(cls)) {
                return createList(a, cls);
            } else if (Map_class.isAssignableFrom(cls)) {
                return createMap(a, cls);
            } else if (a.r(-1) == 0) {
                return createArray(a, cls);
            } else if (cls.isAssignableFrom(new g(a, -1).get(1).getClass())) {
                return createArray(a, cls);
            } else {
                return getObjInstance(a, cls);
            }
        }
    }

    public static int objectCall(long j, Object obj) {
        L a = f.a(j);
        synchronized (a) {
            if (!(obj instanceof e)) {
                return 0;
            }
            int c = a.c();
            Object[] objArr = new Object[(c - 1)];
            for (int i = 2; i <= c; i++) {
                objArr[i - 2] = a.B(i);
            }
            a.c(((e) obj).a(objArr));
            return 1;
        }
    }

    public static int createProxy(long j, String str) {
        int createProxyObject;
        L a = f.a(j);
        synchronized (a) {
            createProxyObject = createProxyObject(a, str);
        }
        return createProxyObject;
    }

    public static int createArray(long j, String str) {
        int createArray;
        L a = f.a(j);
        synchronized (a) {
            createArray = createArray(a, javaBindClass(str));
        }
        return createArray;
    }

    public static int javaLoadLib(long j, String str, String str2) {
        L a = f.a(j);
        synchronized (a) {
            try {
                Object invoke = Class.forName(str).getMethod(str2, LuaState_class).invoke(null, a);
                if (invoke == null || !(invoke instanceof Integer)) {
                    return 0;
                }
                return ((Integer) invoke).intValue();
            } catch (ClassNotFoundException e) {
                throw new LException(e);
            } catch (Exception e2) {
                throw new LException("Error on calling method. Library could not be loaded. " + e2.getMessage());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static int javaToString(long j, Object obj) {
        L a = f.a(j);
        synchronized (a) {
            if (obj == null) {
                a.a("null");
            } else {
                a.a(obj.toString());
            }
        }
        return 1;
    }

    public static int javaEquals(long j, Object obj, Object obj2) {
        L a = f.a(j);
        synchronized (a) {
            a.a(obj.equals(obj2));
        }
        return 1;
    }

    public static int javaObjectLength(long j, Object obj) {
        int i;
        L a = f.a(j);
        synchronized (a) {
            try {
                if (obj instanceof CharSequence) {
                    i = ((CharSequence) obj).length();
                } else if (obj instanceof Collection) {
                    i = ((Collection) obj).size();
                } else if (obj instanceof Map) {
                    i = ((Map) obj).size();
                } else {
                    i = Array.getLength(obj);
                }
                a.a((long) i);
            } catch (Exception e) {
                throw new LException(e);
            } catch (Throwable th) {
                throw th;
            }
        }
        return 1;
    }

    private static int getObjInstance(L l, Class<?> cls) {
        boolean z;
        synchronized (l) {
            int c = l.c();
            if (c == 1) {
                try {
                    l.b(cls.newInstance());
                    return 1;
                } catch (Exception unused) {
                    if (View.class.isAssignableFrom(cls)) {
                        try {
                            l.b(cls.getConstructor(Context.class).newInstance(l.b()));
                            return 1;
                        } catch (Exception unused2) {
                        }
                    }
                }
            }
            int i = c - 1;
            Object[] objArr = new Object[i];
            Constructor<?>[] constructors = cls.getConstructors();
            Constructor<?> constructor = null;
            int i2 = 0;
            while (true) {
                if (i2 >= constructors.length) {
                    break;
                }
                Class<?>[] parameterTypes = constructors[i2].getParameterTypes();
                if (parameterTypes.length == i) {
                    for (int i3 = 0; i3 < parameterTypes.length; i3++) {
                        try {
                            objArr[i3] = compareTypes(l, parameterTypes[i3], i3 + 2);
                        } catch (Exception unused3) {
                            z = false;
                        }
                    }
                    z = true;
                    if (z) {
                        constructor = constructors[i2];
                        break;
                    }
                }
                i2++;
            }
            if (constructor == null) {
                StringBuilder sb = new StringBuilder();
                for (Constructor<?> constructor2 : constructors) {
                    sb.append(constructor2.toString());
                    sb.append("\n");
                }
                throw new LException("Invalid constructor method call. Invalid Parameters.\n" + sb.toString());
            }
            try {
                Object newInstance = constructor.newInstance(objArr);
                if (newInstance != null) {
                    l.b(newInstance);
                    return 1;
                }
                throw new LException("Couldn't instantiate java Object");
            } catch (Exception e) {
                throw new LException(e);
            }
        }
    }

    public static int checkField(L l, Object obj, String str) {
        boolean z;
        Class<?> cls;
        synchronized (l) {
            if (obj instanceof Class) {
                cls = (Class) obj;
                z = true;
            } else {
                cls = obj.getClass();
                z = false;
            }
            try {
                Field field = cls.getField(str);
                if (field == null) {
                    return 0;
                }
                if (z && !Modifier.isStatic(field.getModifiers())) {
                    return 0;
                }
                try {
                    if (!Modifier.isPublic(field.getModifiers())) {
                        field.setAccessible(true);
                    }
                    l.c(field.get(obj));
                    if (Modifier.isFinal(field.getModifiers())) {
                        return 5;
                    }
                    return 1;
                } catch (Exception e) {
                    throw new LException(e);
                }
            } catch (NoSuchFieldException unused) {
                return 0;
            }
        }
    }

    public static int checkMethod(L l, Object obj, String str) {
        Class<?> cls;
        boolean z;
        synchronized (l) {
            if (obj instanceof Class) {
                cls = (Class) obj;
                z = true;
            } else {
                cls = obj.getClass();
                z = false;
            }
            String name = cls.getName();
            String q = l.q(-1);
            Method[] methodArr = methodCache.get(q);
            if (methodArr == null) {
                Method[] methodArr2 = methodsMap.get(name);
                if (methodArr2 == null) {
                    methodArr2 = cls.getMethods();
                    methodsMap.put(name, methodArr2);
                }
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < methodArr2.length; i++) {
                    if (methodArr2[i].getName().equals(str)) {
                        if (!z || Modifier.isStatic(methodArr2[i].getModifiers())) {
                            arrayList.add(methodArr2[i]);
                        }
                    }
                }
                if (arrayList.isEmpty() && z) {
                    Method[] methods = cls.getClass().getMethods();
                    for (int i2 = 0; i2 < methods.length; i2++) {
                        if (methods[i2].getName().equals(str)) {
                            arrayList.add(methods[i2]);
                        }
                    }
                }
                methodArr = new Method[arrayList.size()];
                arrayList.toArray(methodArr);
                methodCache.put(q, methodArr);
            }
            if (methodArr.length == 0) {
                return 0;
            }
            return 2;
        }
    }

    public static int checkClass(L l, Object obj, String str) {
        synchronized (l) {
            if (!(obj instanceof Class)) {
                return 0;
            }
            Object[] classes = ((Class) obj).getClasses();
            for (int i = 0; i < classes.length; i++) {
                if (classes[i].getSimpleName().equals(str)) {
                    l.b(classes[i]);
                    return 3;
                }
            }
            return 0;
        }
    }

    public static int javaGetter(L l, Object obj, String str) {
        boolean z;
        Class<?> cls;
        synchronized (l) {
            if (obj instanceof Map) {
                l.c(((Map) obj).get(str));
                return 1;
            }
            if (obj instanceof Class) {
                cls = (Class) obj;
                z = true;
            } else {
                cls = obj.getClass();
                z = false;
            }
            try {
                Method method = cls.getMethod("get" + str, new Class[0]);
                if (z && !Modifier.isStatic(method.getModifiers())) {
                    return 0;
                }
                try {
                    Object invoke = method.invoke(obj, new Object[0]);
                    if (invoke instanceof CharSequence) {
                        l.a(invoke.toString());
                    } else {
                        l.c(invoke);
                    }
                    return 1;
                } catch (Exception e) {
                    throw new LException(e);
                }
            } catch (NoSuchMethodException unused) {
                return 0;
            }
        }
    }

    public static int javaSetter(L l, Object obj, String str) {
        Class<?> cls;
        synchronized (l) {
            boolean z = true;
            if (obj instanceof Map) {
                ((Map) obj).put(str, l.B(3));
                return 1;
            }
            if (obj instanceof Class) {
                cls = (Class) obj;
            } else {
                cls = obj.getClass();
                z = false;
            }
            String name = cls.getName();
            Method[] methodArr = methodsMap.get(name);
            if (methodArr == null) {
                methodArr = cls.getMethods();
                methodsMap.put(name, methodArr);
            }
            if (str.length() <= 2 || !str.substring(0, 2).equals(Camera.Parameters.FLASH_MODE_ON) || l.l(-1) != 6) {
                return javaSetMethod(l, obj, str, methodArr, z);
            }
            return javaSetListener(l, obj, str, methodArr, z);
        }
    }

    private static int javaSetListener(L l, Object obj, String str, Method[] methodArr, boolean z) {
        synchronized (l) {
            String str2 = "setOn" + str.substring(2) + "Listener";
            for (Method method : methodArr) {
                if (method.getName().equals(str2)) {
                    if (!z || Modifier.isStatic(method.getModifiers())) {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length == 1 && parameterTypes[0].isInterface()) {
                            l.e();
                            l.b(-2);
                            l.a(-2, str);
                            try {
                                method.invoke(obj, l.C(-1).a(parameterTypes[0]));
                                return 1;
                            } catch (Exception e) {
                                throw new LException(e);
                            }
                        }
                    }
                }
            }
            return 0;
        }
    }

    private static int javaSetMethod(L l, Object obj, String str, Method[] methodArr, boolean z) {
        synchronized (l) {
            String str2 = "set" + str;
            StringBuilder sb = new StringBuilder();
            for (Method method : methodArr) {
                if (method.getName().equals(str2)) {
                    if (!z || Modifier.isStatic(method.getModifiers())) {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length != 1) {
                            continue;
                        } else {
                            try {
                                try {
                                    method.invoke(obj, compareTypes(l, parameterTypes[0], -1));
                                    return 1;
                                } catch (Exception e) {
                                    throw new LException(e);
                                }
                            } catch (LException unused) {
                                sb.append(parameterTypes[0]);
                                sb.append("\n");
                            }
                        }
                    }
                }
            }
            if (sb.length() <= 0) {
                return 0;
            }
            throw new LException("Invalid setter " + str + ". Invalid Parameters.\n" + sb.toString() + l.m(-1));
        }
    }

    private static int createProxyObject(L l, String str) {
        synchronized (l) {
            try {
                l.b(l.C(2).c(str));
            } catch (Exception e) {
                throw new LException(e);
            } catch (Throwable th) {
                throw th;
            }
        }
        return 1;
    }

    private static int createProxyObject(L l, Class cls) {
        synchronized (l) {
            l.b(createProxyObject(l, cls, 2));
        }
        return 1;
    }

    private static Object createProxyObject(L l, Class cls, int i) {
        Object a;
        synchronized (l) {
            try {
                a = l.C(i).a(cls);
            } catch (Exception e) {
                throw new LException(e);
            } catch (Throwable th) {
                throw th;
            }
        }
        return a;
    }

    private static int createArray(L l, Class<?> cls) {
        synchronized (l) {
            l.b(createArray(l, cls, 2));
        }
        return 1;
    }

    private static Object createArray(L l, Class<?> cls, int i) {
        Object newInstance;
        synchronized (l) {
            try {
                int r = l.r(i);
                newInstance = Array.newInstance(cls, r);
                if (cls == String_class) {
                    for (int i2 = 1; i2 <= r; i2++) {
                        l.a((double) i2);
                        l.s(i);
                        Array.set(newInstance, i2 - 1, l.q(-1));
                        l.x(1);
                    }
                } else if (cls == Double.TYPE) {
                    for (int i3 = 1; i3 <= r; i3++) {
                        l.a((double) i3);
                        l.s(i);
                        Array.set(newInstance, i3 - 1, Double.valueOf(l.n(-1)));
                        l.x(1);
                    }
                } else if (cls == Float.TYPE) {
                    for (int i4 = 1; i4 <= r; i4++) {
                        l.a((double) i4);
                        l.s(i);
                        Array.set(newInstance, i4 - 1, Float.valueOf((float) l.n(-1)));
                        l.x(1);
                    }
                } else if (cls == Long.TYPE) {
                    for (int i5 = 1; i5 <= r; i5++) {
                        l.a((double) i5);
                        l.s(i);
                        Array.set(newInstance, i5 - 1, Long.valueOf(l.o(-1)));
                        l.x(1);
                    }
                } else if (cls == Integer.TYPE) {
                    for (int i6 = 1; i6 <= r; i6++) {
                        l.a((double) i6);
                        l.s(i);
                        Array.set(newInstance, i6 - 1, Integer.valueOf((int) l.o(-1)));
                        l.x(1);
                    }
                } else if (cls == Short.TYPE) {
                    for (int i7 = 1; i7 <= r; i7++) {
                        l.a((double) i7);
                        l.s(i);
                        Array.set(newInstance, i7 - 1, Short.valueOf((short) ((int) l.o(-1))));
                        l.x(1);
                    }
                } else if (cls == Character.TYPE) {
                    for (int i8 = 1; i8 <= r; i8++) {
                        l.a((double) i8);
                        l.s(i);
                        Array.set(newInstance, i8 - 1, Character.valueOf((char) ((int) l.o(-1))));
                        l.x(1);
                    }
                } else if (cls == Byte.TYPE) {
                    for (int i9 = 1; i9 <= r; i9++) {
                        l.a((double) i9);
                        l.s(i);
                        Array.set(newInstance, i9 - 1, Byte.valueOf((byte) ((int) l.o(-1))));
                        l.x(1);
                    }
                } else {
                    for (int i10 = 1; i10 <= r; i10++) {
                        l.a((double) i10);
                        l.s(i);
                        Array.set(newInstance, i10 - 1, compareTypes(l, cls, -1));
                        l.x(1);
                    }
                }
            } catch (Exception e) {
                throw new LException(e);
            } catch (Throwable th) {
                throw th;
            }
        }
        return newInstance;
    }

    private static int createList(L l, Class<?> cls) {
        synchronized (l) {
            l.b(createList(l, cls, 2));
        }
        return 1;
    }

    private static Object createList(L l, Class<?> cls, int i) {
        List list;
        synchronized (l) {
            int r = l.r(i);
            try {
                if (cls.equals(List_class)) {
                    cls = ArrayList_class;
                }
                list = (List) cls.newInstance();
                for (int i2 = 1; i2 <= r; i2++) {
                    l.a((double) i2);
                    l.s(i);
                    list.add(l.B(-1));
                    l.x(1);
                }
            } catch (Exception e) {
                throw new LException(e);
            }
        }
        return list;
    }

    private static int createMap(L l, Class<?> cls) {
        synchronized (l) {
            l.b(createMap(l, cls, 2));
        }
        return 1;
    }

    private static Object createMap(L l, Class<?> cls, int i) {
        Map map;
        synchronized (l) {
            try {
                if (cls.equals(Map_class)) {
                    cls = HashMap_class;
                }
                map = (Map) cls.newInstance();
                l.d();
                while (l.v(i) != 0) {
                    map.put(l.B(-2), l.B(-1));
                    l.x(1);
                }
            } catch (Exception e) {
                throw new LException(e);
            } catch (Throwable th) {
                throw th;
            }
        }
        return map;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e2, code lost:
        if (r2 == null) goto L_0x00b9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object compareTypes(com.sijla.lj.L r6, java.lang.Class<?> r7, int r8) {
        /*
        // Method dump skipped, instructions count: 269
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sijla.lj.LJA.compareTypes(com.sijla.lj.L, java.lang.Class, int):java.lang.Object");
    }

    private static int toPrimitive(L l, Class cls, int i) {
        Object obj;
        if (cls == Character.TYPE && l.l(i) == 4) {
            String q = l.q(i);
            if (q.length() == 1) {
                obj = Character.valueOf(q.charAt(0));
            } else {
                obj = q.toCharArray();
            }
        } else if (!l.d(i)) {
            throw new LException(l.q(i) + " is not number");
        } else if (cls == Double.TYPE) {
            obj = Double.valueOf(l.n(i));
        } else if (cls == Float.TYPE) {
            obj = Float.valueOf((float) l.n(i));
        } else if (cls == Long.TYPE) {
            obj = Long.valueOf(l.o(i));
        } else if (cls == Integer.TYPE) {
            obj = Integer.valueOf((int) l.o(i));
        } else if (cls == Short.TYPE) {
            obj = Short.valueOf((short) ((int) l.o(i)));
        } else if (cls == Character.TYPE) {
            obj = Character.valueOf((char) ((int) l.o(i)));
        } else if (cls == Byte.TYPE) {
            obj = Byte.valueOf((byte) ((int) l.o(i)));
        } else {
            obj = cls == Boolean.TYPE ? Boolean.valueOf(l.p(i)) : null;
        }
        l.b(obj);
        return 1;
    }
}
