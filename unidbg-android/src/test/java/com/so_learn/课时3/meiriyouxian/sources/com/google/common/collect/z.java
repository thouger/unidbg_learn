package com.google.common.collect;

/* compiled from: ForwardingObject */
public abstract class z {
    /* access modifiers changed from: protected */
    public abstract Object delegate();

    protected z() {
    }

    @Override // java.lang.Object
    public String toString() {
        return delegate().toString();
    }
}
