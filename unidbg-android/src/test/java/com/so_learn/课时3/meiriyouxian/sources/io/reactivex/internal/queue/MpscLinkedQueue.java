package io.reactivex.internal.queue;

import io.reactivex.internal.a.g;
import java.util.concurrent.atomic.AtomicReference;

public final class MpscLinkedQueue<T> implements g<T> {
    private final AtomicReference<LinkedQueueNode<T>> a = new AtomicReference<>();
    private final AtomicReference<LinkedQueueNode<T>> b = new AtomicReference<>();

    public MpscLinkedQueue() {
        LinkedQueueNode linkedQueueNode = new LinkedQueueNode();
        b(linkedQueueNode);
        a(linkedQueueNode);
    }

    @Override // io.reactivex.internal.a.h
    public boolean offer(T t) {
        if (t != null) {
            LinkedQueueNode linkedQueueNode = new LinkedQueueNode(t);
            a(linkedQueueNode).soNext(linkedQueueNode);
            return true;
        }
        throw new NullPointerException("Null is not a valid element");
    }

    @Override // io.reactivex.internal.a.g, io.reactivex.internal.a.h
    public T poll() {
        LinkedQueueNode<T> lvNext;
        LinkedQueueNode<T> c = c();
        LinkedQueueNode<T> lvNext2 = c.lvNext();
        if (lvNext2 != null) {
            T t = (T) lvNext2.getAndNullValue();
            b(lvNext2);
            return t;
        } else if (c == a()) {
            return null;
        } else {
            do {
                lvNext = c.lvNext();
            } while (lvNext == null);
            T t2 = (T) lvNext.getAndNullValue();
            b(lvNext);
            return t2;
        }
    }

    @Override // io.reactivex.internal.a.h
    public void clear() {
        while (poll() != null && !isEmpty()) {
        }
    }

    /* access modifiers changed from: package-private */
    public LinkedQueueNode<T> a() {
        return this.a.get();
    }

    /* access modifiers changed from: package-private */
    public LinkedQueueNode<T> a(LinkedQueueNode<T> linkedQueueNode) {
        return this.a.getAndSet(linkedQueueNode);
    }

    /* access modifiers changed from: package-private */
    public LinkedQueueNode<T> b() {
        return this.b.get();
    }

    /* access modifiers changed from: package-private */
    public LinkedQueueNode<T> c() {
        return this.b.get();
    }

    /* access modifiers changed from: package-private */
    public void b(LinkedQueueNode<T> linkedQueueNode) {
        this.b.lazySet(linkedQueueNode);
    }

    @Override // io.reactivex.internal.a.h
    public boolean isEmpty() {
        return b() == a();
    }

    /* access modifiers changed from: package-private */
    public static final class LinkedQueueNode<E> extends AtomicReference<LinkedQueueNode<E>> {
        private static final long serialVersionUID = 2404266111789071508L;
        private E value;

        LinkedQueueNode() {
        }

        LinkedQueueNode(E e) {
            spValue(e);
        }

        public E getAndNullValue() {
            E e = (E) lpValue();
            spValue(null);
            return e;
        }

        public E lpValue() {
            return this.value;
        }

        public void spValue(E e) {
            this.value = e;
        }

        public void soNext(LinkedQueueNode<E> linkedQueueNode) {
            lazySet(linkedQueueNode);
        }

        public LinkedQueueNode<E> lvNext() {
            return get();
        }
    }
}
