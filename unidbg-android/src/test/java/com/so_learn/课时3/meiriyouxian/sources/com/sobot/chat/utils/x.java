package com.sobot.chat.utils;

import com.umeng.analytics.pro.ai;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/* compiled from: SobotJsonUtils */
public class x {
    public static String a(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof String) {
            return a((String) obj);
        }
        if (obj instanceof Boolean) {
            return a((Boolean) obj);
        }
        if (obj instanceof Number) {
            return a((Number) obj);
        }
        if (obj instanceof Map) {
            return a((Map<String, Object>) ((Map) obj));
        }
        if (obj instanceof Collection) {
            return a((Collection<Object>) ((Collection) obj));
        }
        if (obj instanceof Object[]) {
            return a((Object[]) obj);
        }
        if (obj instanceof int[]) {
            return a((int[]) obj);
        }
        if (obj instanceof boolean[]) {
            return a((boolean[]) obj);
        }
        if (obj instanceof long[]) {
            return a((long[]) obj);
        }
        if (obj instanceof float[]) {
            return a((float[]) obj);
        }
        if (obj instanceof double[]) {
            return a((double[]) obj);
        }
        if (obj instanceof short[]) {
            return a((short[]) obj);
        }
        if (obj instanceof byte[]) {
            return a((byte[]) obj);
        }
        if (obj instanceof Object) {
            return b(obj);
        }
        throw new RuntimeException("\u4e0d\u652f\u6301\u7684\u7c7b\u578b: " + obj.getClass().getName());
    }

    static String a(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 20);
        sb.append('\"');
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\f') {
                sb.append("\\f");
            } else if (charAt == '\r') {
                sb.append("\\r");
            } else if (charAt == '\"') {
                sb.append("\\\"");
            } else if (charAt == '/') {
                sb.append("\\/");
            } else if (charAt != '\\') {
                switch (charAt) {
                    case '\b':
                        sb.append("\\b");
                        continue;
                    case '\t':
                        sb.append("\\t");
                        continue;
                    case '\n':
                        sb.append("\\n");
                        continue;
                    default:
                        sb.append(charAt);
                        continue;
                }
            } else {
                sb.append("\\\\");
            }
        }
        sb.append('\"');
        return sb.toString();
    }

    static String a(Number number) {
        return number.toString();
    }

    static String a(Boolean bool) {
        return bool.toString();
    }

    static String a(Collection<Object> collection) {
        return a((Object) collection.toArray());
    }

    static String a(Map<String, Object> map) {
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(map.size() << 4);
        sb.append('{');
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            sb.append('\"');
            sb.append(str);
            sb.append('\"');
            sb.append(':');
            sb.append(a(obj));
            sb.append(',');
        }
        sb.setCharAt(sb.length() - 1, '}');
        return sb.toString();
    }

    static String a(Object[] objArr) {
        if (objArr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(objArr.length << 4);
        sb.append('[');
        for (Object obj : objArr) {
            sb.append(a(obj));
            sb.append(',');
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String a(int[] iArr) {
        if (iArr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(iArr.length << 4);
        sb.append('[');
        for (int i : iArr) {
            sb.append(Integer.toString(i));
            sb.append(',');
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String a(long[] jArr) {
        if (jArr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(jArr.length << 4);
        sb.append('[');
        for (long j : jArr) {
            sb.append(Long.toString(j));
            sb.append(',');
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String a(boolean[] zArr) {
        if (zArr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(zArr.length << 4);
        sb.append('[');
        for (boolean z : zArr) {
            sb.append(Boolean.toString(z));
            sb.append(',');
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String a(float[] fArr) {
        if (fArr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(fArr.length << 4);
        sb.append('[');
        for (float f : fArr) {
            sb.append(Float.toString(f));
            sb.append(',');
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String a(double[] dArr) {
        if (dArr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(dArr.length << 4);
        sb.append('[');
        for (double d : dArr) {
            sb.append(Double.toString(d));
            sb.append(',');
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String a(short[] sArr) {
        if (sArr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(sArr.length << 4);
        sb.append('[');
        for (short s : sArr) {
            sb.append(Short.toString(s));
            sb.append(',');
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    static String a(byte[] bArr) {
        if (bArr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(bArr.length << 4);
        sb.append('[');
        for (byte b : bArr) {
            sb.append(Byte.toString(b));
            sb.append(',');
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static String b(Object obj) {
        if (obj == null) {
            return "{}";
        }
        Method[] methods = obj.getClass().getMethods();
        StringBuilder sb = new StringBuilder(methods.length << 4);
        sb.append('{');
        for (Method method : methods) {
            try {
                String name = method.getName();
                String str = "";
                if (name.startsWith("get")) {
                    str = name.substring(3);
                    boolean z = false;
                    for (String str2 : new String[]{"Class"}) {
                        if (str2.equals(str)) {
                            z = true;
                        }
                    }
                    if (z) {
                    }
                } else if (name.startsWith(ai.ae)) {
                    str = name.substring(2);
                }
                if (str.length() > 0 && Character.isUpperCase(str.charAt(0)) && method.getParameterTypes().length == 0) {
                    if (str.length() == 1) {
                        str = str.toLowerCase();
                    } else if (!Character.isUpperCase(str.charAt(1))) {
                        str = str.substring(0, 1).toLowerCase() + str.substring(1);
                    }
                    Object invoke = method.invoke(obj, new Object[0]);
                    sb.append('\"');
                    sb.append(str);
                    sb.append('\"');
                    sb.append(':');
                    sb.append(a(invoke));
                    sb.append(',');
                }
            } catch (Exception e) {
                throw new RuntimeException("\u5728\u5c06bean\u5c01\u88c5\u6210JSON\u683c\u5f0f\u65f6\u5f02\u5e38\uff1a" + e.getMessage(), e);
            }
        }
        if (sb.length() == 1) {
            return obj.toString();
        }
        sb.setCharAt(sb.length() - 1, '}');
        return sb.toString();
    }
}
