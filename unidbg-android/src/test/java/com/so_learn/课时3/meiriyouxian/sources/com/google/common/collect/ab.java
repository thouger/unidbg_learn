package com.google.common.collect;

import com.google.common.base.m;
import java.util.Collection;
import java.util.Set;

/* compiled from: ForwardingSet */
public abstract class ab<E> extends s<E> implements Set<E> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.s, com.google.common.collect.z
    public abstract Set<E> delegate();

    protected ab() {
    }

    @Override // java.util.Collection, java.lang.Object, java.util.Set
    public boolean equals(Object obj) {
        return obj == this || delegate().equals(obj);
    }

    @Override // java.util.Collection, java.lang.Object, java.util.Set
    public int hashCode() {
        return delegate().hashCode();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.s
    public boolean standardRemoveAll(Collection<?> collection) {
        return Sets.a((Set<?>) this, (Collection<?>) ((Collection) m.a(collection)));
    }

    /* access modifiers changed from: protected */
    public boolean standardEquals(Object obj) {
        return Sets.a(this, obj);
    }

    /* access modifiers changed from: protected */
    public int standardHashCode() {
        return Sets.a(this);
    }
}
