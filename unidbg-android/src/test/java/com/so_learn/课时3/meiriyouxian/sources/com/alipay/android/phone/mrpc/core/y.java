package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public final class y implements InvocationHandler {
    protected g a;
    protected Class<?> b;
    protected z c;

    public y(g gVar, Class<?> cls, z zVar) {
        this.a = gVar;
        this.b = cls;
        this.c = zVar;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        return this.c.a(method, objArr);
    }
}
