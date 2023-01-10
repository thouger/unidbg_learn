package com.google.common.base;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* access modifiers changed from: package-private */
public abstract class AbstractIterator<T> implements Iterator<T> {
    private State a = State.NOT_READY;
    private T b;

    /* access modifiers changed from: private */
    public enum State {
        READY,
        NOT_READY,
        DONE,
        FAILED
    }

    /* access modifiers changed from: protected */
    public abstract T a();

    protected AbstractIterator() {
    }

    /* access modifiers changed from: protected */
    public final T b() {
        this.a = State.DONE;
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.base.AbstractIterator$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[State.values().length];

        static {
            try {
                a[State.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[State.DONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        m.b(this.a != State.FAILED);
        int i = AnonymousClass1.a[this.a.ordinal()];
        if (i == 1) {
            return true;
        }
        if (i != 2) {
            return c();
        }
        return false;
    }

    private boolean c() {
        this.a = State.FAILED;
        this.b = (T) a();
        if (this.a == State.DONE) {
            return false;
        }
        this.a = State.READY;
        return true;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            this.a = State.NOT_READY;
            T t = this.b;
            this.b = null;
            return t;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
