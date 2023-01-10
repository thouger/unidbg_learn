package com.sobot.chat.core.channel;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/* compiled from: LimitQueue */
public class b<E> implements Queue<E> {
    LinkedList<E> a = new LinkedList<>();
    private int b;

    public b(int i) {
        this.b = i;
    }

    @Override // java.util.Queue
    public boolean offer(E e) {
        if (this.a.size() >= this.b) {
            this.a.poll();
        }
        return this.a.offer(e);
    }

    @Override // java.util.Queue
    public E poll() {
        return this.a.poll();
    }

    @Override // java.util.Queue, java.util.Collection
    public boolean add(E e) {
        return this.a.add(e);
    }

    @Override // java.util.Queue
    public E element() {
        return this.a.element();
    }

    @Override // java.util.Queue
    public E peek() {
        return this.a.peek();
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.a.size() == 0;
    }

    @Override // java.util.Collection
    public int size() {
        return this.a.size();
    }

    @Override // java.util.Queue
    public E remove() {
        return this.a.remove();
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        return this.a.addAll(collection);
    }

    @Override // java.util.Collection
    public void clear() {
        this.a.clear();
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return this.a.contains(obj);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.a.containsAll(collection);
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return this.a.iterator();
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        return this.a.remove(obj);
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        return this.a.removeAll(collection);
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return this.a.retainAll(collection);
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return this.a.toArray();
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.a.toArray(tArr);
    }

    public int a(E e) {
        return this.a.indexOf(e);
    }
}
