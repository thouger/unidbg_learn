package com.google.common.collect;

import com.google.common.base.Optional;
import com.google.common.base.m;
import com.google.common.base.n;
import java.util.Iterator;

/* compiled from: FluentIterable */
public abstract class r<E> implements Iterable<E> {
    private final Optional<Iterable<E>> a;

    protected r() {
        this.a = Optional.absent();
    }

    r(Iterable<E> iterable) {
        m.a(iterable);
        this.a = Optional.fromNullable(this == iterable ? null : iterable);
    }

    private Iterable<E> b() {
        return (Iterable) this.a.or((Optional<Iterable<E>>) this);
    }

    public static <E> r<E> a(Iterable<E> iterable) {
        return iterable instanceof r ? (r) iterable : new AnonymousClass1(iterable, iterable);
    }

    /* compiled from: FluentIterable */
    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.r$1  reason: invalid class name */
    public static class AnonymousClass1 extends r<E> {
        final /* synthetic */ Iterable a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Iterable iterable, Iterable iterable2) {
            super(iterable);
            this.a = iterable2;
        }

        @Override // java.lang.Iterable
        public Iterator<E> iterator() {
            return this.a.iterator();
        }
    }

    public static <T> r<T> a(Iterable<? extends T> iterable, Iterable<? extends T> iterable2) {
        return a(iterable, iterable2);
    }

    private static <T> r<T> a(Iterable<? extends T>... iterableArr) {
        for (Iterable<? extends T> iterable : iterableArr) {
            m.a(iterable);
        }
        return new AnonymousClass2(iterableArr);
    }

    /* compiled from: FluentIterable */
    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.r$2  reason: invalid class name */
    public static class AnonymousClass2 extends r<T> {
        final /* synthetic */ Iterable[] a;

        AnonymousClass2(Iterable[] iterableArr) {
            this.a = iterableArr;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.e(new AnonymousClass1(this.a.length));
        }

        /* compiled from: FluentIterable */
        /* renamed from: com.google.common.collect.r$2$1  reason: invalid class name */
        class AnonymousClass1 extends a<Iterator<? extends T>> {
            AnonymousClass1(int i) {
                super(i);
            }

            /* renamed from: b */
            public Iterator<? extends T> a(int i) {
                return AnonymousClass2.this.a[i].iterator();
            }
        }
    }

    @Override // java.lang.Object
    public String toString() {
        return ag.a((Iterable<?>) b());
    }

    public final r<E> a(n<? super E> nVar) {
        return a(ag.b(b(), nVar));
    }

    public final ImmutableSet<E> a() {
        return ImmutableSet.copyOf(b());
    }
}
