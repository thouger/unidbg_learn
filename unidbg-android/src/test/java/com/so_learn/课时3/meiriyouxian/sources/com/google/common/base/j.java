package com.google.common.base;

import java.util.Arrays;

/* compiled from: Objects */
public final class j extends f {
    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int a(Object... objArr) {
        return Arrays.hashCode(objArr);
    }
}
