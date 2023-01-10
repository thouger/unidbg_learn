package cn.missfresh.sherlock.crash;

import java.util.LinkedList;

public class LimitedQueue<E> extends LinkedList<E> {
    private static final long serialVersionUID = 1;
    private int limit;

    public LimitedQueue(int i) {
        this.limit = i;
    }

    @Override // java.util.LinkedList, java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.util.Queue, java.util.Deque
    public boolean add(E e) {
        super.add(e);
        while (size() > this.limit) {
            super.remove();
        }
        return true;
    }
}
