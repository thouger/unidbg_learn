package com.google.common.collect;

import com.google.common.base.g;
import com.google.common.base.m;
import com.google.common.base.n;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/* compiled from: Iterables */
public final class ag {
    public static <T> boolean a(Iterable<T> iterable, n<? super T> nVar) {
        if (!(iterable instanceof RandomAccess) || !(iterable instanceof List)) {
            return Iterators.a(iterable.iterator(), nVar);
        }
        return a((List) iterable, (n) m.a(nVar));
    }

    private static <T> boolean a(List<T> list, n<? super T> nVar) {
        int i = 0;
        int i2 = 0;
        while (i < list.size()) {
            T t = list.get(i);
            if (!nVar.apply(t)) {
                if (i > i2) {
                    try {
                        list.set(i2, t);
                    } catch (UnsupportedOperationException unused) {
                        a(list, nVar, i2, i);
                        return true;
                    } catch (IllegalArgumentException unused2) {
                        a(list, nVar, i2, i);
                        return true;
                    }
                }
                i2++;
            }
            i++;
        }
        list.subList(i2, list.size()).clear();
        return i != i2;
    }

    private static <T> void a(List<T> list, n<? super T> nVar, int i, int i2) {
        for (int size = list.size() - 1; size > i2; size--) {
            if (nVar.apply(list.get(size))) {
                list.remove(size);
            }
        }
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            list.remove(i3);
        }
    }

    public static String a(Iterable<?> iterable) {
        return Iterators.c(iterable.iterator());
    }

    public static <T> T b(Iterable<T> iterable) {
        return (T) Iterators.d(iterable.iterator());
    }

    static <T> T[] a(Iterable<? extends T> iterable, T[] tArr) {
        return (T[]) f(iterable).toArray(tArr);
    }

    static Object[] c(Iterable<?> iterable) {
        return f(iterable).toArray();
    }

    private static <E> Collection<E> f(Iterable<E> iterable) {
        if (iterable instanceof Collection) {
            return (Collection) iterable;
        }
        return Lists.a(iterable.iterator());
    }

    public static <T> boolean a(Collection<T> collection, Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            return collection.addAll(o.a(iterable));
        }
        return Iterators.a(collection, ((Iterable) m.a(iterable)).iterator());
    }

    public static <T> Iterable<T> a(Iterable<? extends T> iterable, Iterable<? extends T> iterable2) {
        return r.a(iterable, iterable2);
    }

    public static <T> Iterable<T> b(Iterable<T> iterable, n<? super T> nVar) {
        m.a(iterable);
        m.a(nVar);
        return new AnonymousClass1(iterable, nVar);
    }

    /* compiled from: Iterables */
    /* renamed from: com.google.common.collect.ag$1  reason: invalid class name */
    static class AnonymousClass1 extends r<T> {
        final /* synthetic */ Iterable a;
        final /* synthetic */ n b;

        AnonymousClass1(Iterable iterable, n nVar) {
            this.a = iterable;
            this.b = nVar;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.b(this.a.iterator(), this.b);
        }
    }

    public static <T> boolean c(Iterable<T> iterable, n<? super T> nVar) {
        return Iterators.c(iterable.iterator(), nVar);
    }

    public static <F, T> Iterable<T> a(Iterable<F> iterable, g<? super F, ? extends T> gVar) {
        m.a(iterable);
        m.a(gVar);
        return new AnonymousClass2(iterable, gVar);
    }

    /* compiled from: Iterables */
    /* renamed from: com.google.common.collect.ag$2  reason: invalid class name */
    static class AnonymousClass2 extends r<T> {
        final /* synthetic */ Iterable a;
        final /* synthetic */ g b;

        AnonymousClass2(Iterable iterable, g gVar) {
            this.a = iterable;
            this.b = gVar;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.a(this.a.iterator(), this.b);
        }
    }

    public static <T> T a(Iterable<? extends T> iterable, T t) {
        return (T) Iterators.b((Iterator) iterable.iterator(), (Object) t);
    }

    public static <T> T d(Iterable<T> iterable) {
        if (!(iterable instanceof List)) {
            return (T) Iterators.f(iterable.iterator());
        }
        List list = (List) iterable;
        if (!list.isEmpty()) {
            return (T) a((List<Object>) list);
        }
        throw new NoSuchElementException();
    }

    private static <T> T a(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T> Iterable<T> a(Iterable<T> iterable, int i) {
        m.a(iterable);
        m.a(i >= 0, "number to skip cannot be negative");
        return new AnonymousClass3(iterable, i);
    }

    /* compiled from: Iterables */
    /* renamed from: com.google.common.collect.ag$3  reason: invalid class name */
    static class AnonymousClass3 extends r<T> {
        final /* synthetic */ Iterable a;
        final /* synthetic */ int b;

        AnonymousClass3(Iterable iterable, int i) {
            this.a = iterable;
            this.b = i;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            Iterable iterable = this.a;
            if (iterable instanceof List) {
                List list = (List) iterable;
                return list.subList(Math.min(list.size(), this.b), list.size()).iterator();
            }
            Iterator it2 = iterable.iterator();
            Iterators.a(it2, this.b);
            return new AnonymousClass1(it2);
        }

        /* compiled from: Iterables */
        /* renamed from: com.google.common.collect.ag$3$1  reason: invalid class name */
        class AnonymousClass1 implements Iterator<T> {
            boolean a = true;
            final /* synthetic */ Iterator b;

            AnonymousClass1(Iterator it2) {
                this.b = it2;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.b.hasNext();
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
            /* JADX WARNING: Unknown variable types count: 1 */
            @Override // java.util.Iterator
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public T next() {
                /*
                    r2 = this;
                    java.util.Iterator r0 = r2.b
                    java.lang.Object r0 = r0.next()
                    r1 = 0
                    r2.a = r1
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ag.AnonymousClass3.AnonymousClass1.next():java.lang.Object");
            }

            @Override // java.util.Iterator
            public void remove() {
                n.a(!this.a);
                this.b.remove();
            }
        }
    }

    public static boolean e(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }
}
