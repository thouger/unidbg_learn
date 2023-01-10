package com.google.common.collect;

import com.google.common.base.j;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: ForwardingCollection */
public abstract class s<E> extends z implements Collection<E> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.z
    public abstract Collection<E> delegate();

    protected s() {
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return delegate().iterator();
    }

    @Override // java.util.Collection
    public int size() {
        return delegate().size();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        return delegate().removeAll(collection);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return delegate().contains(obj);
    }

    @Override // java.util.Collection, java.util.Queue
    public boolean add(E e) {
        return delegate().add(e);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        return delegate().remove(obj);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        return delegate().containsAll(collection);
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        return delegate().addAll(collection);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        return delegate().retainAll(collection);
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        delegate().clear();
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        return delegate().toArray();
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        return (T[]) delegate().toArray(tArr);
    }

    /* access modifiers changed from: protected */
    public boolean standardContains(Object obj) {
        return Iterators.a((Iterator<?>) iterator(), obj);
    }

    /* access modifiers changed from: protected */
    public boolean standardContainsAll(Collection<?> collection) {
        return o.a((Collection<?>) this, collection);
    }

    /* access modifiers changed from: protected */
    public boolean standardAddAll(Collection<? extends E> collection) {
        return Iterators.a(this, collection.iterator());
    }

    /* access modifiers changed from: protected */
    public boolean standardRemove(Object obj) {
        Iterator<E> it2 = iterator();
        while (it2.hasNext()) {
            if (j.a(it2.next(), obj)) {
                it2.remove();
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean standardRemoveAll(Collection<?> collection) {
        return Iterators.a((Iterator<?>) iterator(), collection);
    }

    /* access modifiers changed from: protected */
    public boolean standardRetainAll(Collection<?> collection) {
        return Iterators.b((Iterator<?>) iterator(), collection);
    }

    /* access modifiers changed from: protected */
    public void standardClear() {
        Iterators.h(iterator());
    }

    /* access modifiers changed from: protected */
    public boolean standardIsEmpty() {
        return !iterator().hasNext();
    }

    /* access modifiers changed from: protected */
    public String standardToString() {
        return o.a((Collection<?>) this);
    }

    /* access modifiers changed from: protected */
    public Object[] standardToArray() {
        return toArray(new Object[size()]);
    }

    /* access modifiers changed from: protected */
    public <T> T[] standardToArray(T[] tArr) {
        return (T[]) ak.a((Collection<?>) this, (Object[]) tArr);
    }
}
