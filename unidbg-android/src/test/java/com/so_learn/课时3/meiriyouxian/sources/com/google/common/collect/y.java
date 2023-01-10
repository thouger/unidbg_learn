package com.google.common.collect;

import com.google.common.base.j;
import com.google.common.collect.aj;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* compiled from: ForwardingMultiset */
public abstract class y<E> extends s<E> implements aj<E> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.s, com.google.common.collect.z
    public abstract aj<E> delegate();

    protected y() {
    }

    @Override // com.google.common.collect.aj
    public int count(Object obj) {
        return delegate().count(obj);
    }

    @Override // com.google.common.collect.aj
    public int add(E e, int i) {
        return delegate().add(e, i);
    }

    @Override // com.google.common.collect.aj
    public int remove(Object obj, int i) {
        return delegate().remove(obj, i);
    }

    @Override // com.google.common.collect.aj
    public Set<E> elementSet() {
        return delegate().elementSet();
    }

    @Override // com.google.common.collect.aj
    public Set<aj.a<E>> entrySet() {
        return delegate().entrySet();
    }

    @Override // java.util.Collection, java.lang.Object, com.google.common.collect.aj
    public boolean equals(Object obj) {
        return obj == this || delegate().equals(obj);
    }

    @Override // java.util.Collection, java.lang.Object, com.google.common.collect.aj
    public int hashCode() {
        return delegate().hashCode();
    }

    @Override // com.google.common.collect.aj
    public int setCount(E e, int i) {
        return delegate().setCount(e, i);
    }

    @Override // com.google.common.collect.aj
    public boolean setCount(E e, int i, int i2) {
        return delegate().setCount(e, i, i2);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.s
    public boolean standardContains(Object obj) {
        return count(obj) > 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.s
    public void standardClear() {
        Iterators.h(entrySet().iterator());
    }

    /* access modifiers changed from: protected */
    public int standardCount(Object obj) {
        for (aj.a<E> aVar : entrySet()) {
            if (j.a(aVar.getElement(), obj)) {
                return aVar.getCount();
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean standardAdd(E e) {
        add(e, 1);
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.s
    public boolean standardAddAll(Collection<? extends E> collection) {
        return Multisets.a((aj) this, (Collection) collection);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.s
    public boolean standardRemove(Object obj) {
        return remove(obj, 1) > 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.s
    public boolean standardRemoveAll(Collection<?> collection) {
        return Multisets.b(this, collection);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.s
    public boolean standardRetainAll(Collection<?> collection) {
        return Multisets.c(this, collection);
    }

    /* access modifiers changed from: protected */
    public int standardSetCount(E e, int i) {
        return Multisets.a(this, e, i);
    }

    /* access modifiers changed from: protected */
    public boolean standardSetCount(E e, int i, int i2) {
        return Multisets.a(this, e, i, i2);
    }

    /* access modifiers changed from: protected */
    public Iterator<E> standardIterator() {
        return Multisets.b((aj) this);
    }

    /* access modifiers changed from: protected */
    public int standardSize() {
        return Multisets.c(this);
    }

    /* access modifiers changed from: protected */
    public boolean standardEquals(Object obj) {
        return Multisets.a(this, obj);
    }

    /* access modifiers changed from: protected */
    public int standardHashCode() {
        return entrySet().hashCode();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.s
    public String standardToString() {
        return entrySet().toString();
    }
}
