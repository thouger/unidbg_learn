package com.google.common.base;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    public abstract Set<T> asSet();

    @Override // java.lang.Object
    public abstract boolean equals(Object obj);

    public abstract T get();

    @Override // java.lang.Object
    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract Optional<T> or(Optional<? extends T> optional);

    public abstract T or(q<? extends T> qVar);

    public abstract T or(T t);

    public abstract T orNull();

    @Override // java.lang.Object
    public abstract String toString();

    public abstract <V> Optional<V> transform(g<? super T, V> gVar);

    public static <T> Optional<T> absent() {
        return Absent.withType();
    }

    public static <T> Optional<T> of(T t) {
        return new Present(m.a(t));
    }

    public static <T> Optional<T> fromNullable(T t) {
        return t == null ? absent() : new Present(t);
    }

    Optional() {
    }

    /* renamed from: com.google.common.base.Optional$1  reason: invalid class name */
    static class AnonymousClass1 implements Iterable<T> {
        final /* synthetic */ Iterable a;

        AnonymousClass1(Iterable iterable) {
            this.a = iterable;
        }

        /* renamed from: com.google.common.base.Optional$1$1  reason: invalid class name */
        class AnonymousClass1 extends AbstractIterator<T> {
            private final Iterator<? extends Optional<? extends T>> b = ((Iterator) m.a(AnonymousClass1.this.a.iterator()));

            AnonymousClass1() {
            }

            /* access modifiers changed from: protected */
            @Override // com.google.common.base.AbstractIterator
            public T a() {
                while (this.b.hasNext()) {
                    Optional optional = (Optional) this.b.next();
                    if (optional.isPresent()) {
                        return (T) optional.get();
                    }
                }
                return b();
            }
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return new AnonymousClass1();
        }
    }

    public static <T> Iterable<T> presentInstances(Iterable<? extends Optional<? extends T>> iterable) {
        m.a(iterable);
        return new AnonymousClass1(iterable);
    }
}
