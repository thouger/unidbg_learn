package com.google.common.collect;

import com.google.common.base.m;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

public final class EvictingQueue<E> extends aa<E> implements Serializable {
    private static final long serialVersionUID = 0;
    private final Queue<E> delegate;
    final int maxSize;

    private EvictingQueue(int i) {
        m.a(i >= 0, "maxSize (%s) must >= 0", i);
        this.delegate = new ArrayDeque(i);
        this.maxSize = i;
    }

    public static <E> EvictingQueue<E> create(int i) {
        return new EvictingQueue<>(i);
    }

    public int remainingCapacity() {
        return this.maxSize - size();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.aa, com.google.common.collect.s, com.google.common.collect.z
    public Queue<E> delegate() {
        return this.delegate;
    }

    @Override // com.google.common.collect.aa, java.util.Queue
    public boolean offer(E e) {
        return add(e);
    }

    @Override // com.google.common.collect.s, java.util.Collection, java.util.Queue
    public boolean add(E e) {
        m.a(e);
        if (this.maxSize == 0) {
            return true;
        }
        if (size() == this.maxSize) {
            this.delegate.remove();
        }
        this.delegate.add(e);
        return true;
    }

    @Override // com.google.common.collect.s, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        int size = collection.size();
        if (size < this.maxSize) {
            return standardAddAll(collection);
        }
        clear();
        return ag.a((Collection) this, ag.a(collection, size - this.maxSize));
    }

    @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return delegate().contains(m.a(obj));
    }

    @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        return delegate().remove(m.a(obj));
    }
}
