package com.google.common.collect;

import java.util.NoSuchElementException;
import java.util.Queue;

/* compiled from: ForwardingQueue */
public abstract class aa<E> extends s<E> implements Queue<E> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.s, com.google.common.collect.z
    public abstract Queue<E> delegate();

    protected aa() {
    }

    @Override // java.util.Queue
    public boolean offer(E e) {
        return delegate().offer(e);
    }

    @Override // java.util.Queue
    public E poll() {
        return delegate().poll();
    }

    @Override // java.util.Queue
    public E remove() {
        return delegate().remove();
    }

    @Override // java.util.Queue
    public E peek() {
        return delegate().peek();
    }

    @Override // java.util.Queue
    public E element() {
        return delegate().element();
    }

    /* access modifiers changed from: protected */
    public boolean standardOffer(E e) {
        try {
            return add(e);
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public E standardPeek() {
        try {
            return (E) element();
        } catch (NoSuchElementException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public E standardPoll() {
        try {
            return (E) remove();
        } catch (NoSuchElementException unused) {
            return null;
        }
    }
}
