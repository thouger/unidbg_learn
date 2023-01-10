package com.google.common.reflect;

import com.google.common.base.m;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/* compiled from: Reflection */
public final class c {
    public static <T> T a(Class<T> cls, InvocationHandler invocationHandler) {
        m.a(invocationHandler);
        m.a(cls.isInterface(), "%s is not an interface", cls);
        return cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler));
    }
}
