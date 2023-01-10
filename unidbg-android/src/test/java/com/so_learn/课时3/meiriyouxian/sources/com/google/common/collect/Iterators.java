package com.google.common.collect;

import com.google.common.base.g;
import com.google.common.base.m;
import com.google.common.base.n;
import com.google.common.primitives.Ints;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

public final class Iterators {
    static <T> bf<T> a() {
        return b();
    }

    static <T> bg<T> b() {
        return (bg<T>) a.a;
    }

    private enum EmptyModifiableIterator implements Iterator<Object> {
        INSTANCE;

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            n.a(false);
        }
    }

    static <T> Iterator<T> c() {
        return EmptyModifiableIterator.INSTANCE;
    }

    public static <T> bf<T> a(Iterator<? extends T> it2) {
        m.a(it2);
        if (it2 instanceof bf) {
            return (bf) it2;
        }
        return new AnonymousClass1(it2);
    }

    /* renamed from: com.google.common.collect.Iterators$1  reason: invalid class name */
    static class AnonymousClass1 extends bf<T> {
        final /* synthetic */ Iterator a;

        AnonymousClass1(Iterator it2) {
            this.a = it2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.a.hasNext();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // java.util.Iterator
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T next() {
            /*
                r1 = this;
                java.util.Iterator r0 = r1.a
                java.lang.Object r0 = r0.next()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Iterators.AnonymousClass1.next():java.lang.Object");
        }
    }

    public static int b(Iterator<?> it2) {
        long j = 0;
        while (it2.hasNext()) {
            it2.next();
            j++;
        }
        return Ints.b(j);
    }

    public static boolean a(Iterator<?> it2, Object obj) {
        if (obj == null) {
            while (it2.hasNext()) {
                if (it2.next() == null) {
                    return true;
                }
            }
            return false;
        }
        while (it2.hasNext()) {
            if (obj.equals(it2.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Iterator<?> it2, Collection<?> collection) {
        m.a(collection);
        boolean z = false;
        while (it2.hasNext()) {
            if (collection.contains(it2.next())) {
                it2.remove();
                z = true;
            }
        }
        return z;
    }

    public static <T> boolean a(Iterator<T> it2, n<? super T> nVar) {
        m.a(nVar);
        boolean z = false;
        while (it2.hasNext()) {
            if (nVar.apply(it2.next())) {
                it2.remove();
                z = true;
            }
        }
        return z;
    }

    public static boolean b(Iterator<?> it2, Collection<?> collection) {
        m.a(collection);
        boolean z = false;
        while (it2.hasNext()) {
            if (!collection.contains(it2.next())) {
                it2.remove();
                z = true;
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:2:0x0006  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.util.Iterator<?> r3, java.util.Iterator<?> r4) {
        /*
        L_0x0000:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x001d
            boolean r0 = r4.hasNext()
            r1 = 0
            if (r0 != 0) goto L_0x000e
            return r1
        L_0x000e:
            java.lang.Object r0 = r3.next()
            java.lang.Object r2 = r4.next()
            boolean r0 = com.google.common.base.j.a(r0, r2)
            if (r0 != 0) goto L_0x0000
            return r1
        L_0x001d:
            boolean r3 = r4.hasNext()
            r3 = r3 ^ 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Iterators.a(java.util.Iterator, java.util.Iterator):boolean");
    }

    public static String c(Iterator<?> it2) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean z = true;
        while (it2.hasNext()) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            sb.append(it2.next());
        }
        sb.append(']');
        return sb.toString();
    }

    public static <T> T d(Iterator<T> it2) {
        T next = it2.next();
        if (!it2.hasNext()) {
            return next;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("expected one element but was: <");
        sb.append((Object) next);
        for (int i = 0; i < 4 && it2.hasNext(); i++) {
            sb.append(", ");
            sb.append((Object) it2.next());
        }
        if (it2.hasNext()) {
            sb.append(", ...");
        }
        sb.append('>');
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Collection<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> boolean a(Collection<T> collection, Iterator<? extends T> it2) {
        m.a(collection);
        m.a(it2);
        boolean z = false;
        while (it2.hasNext()) {
            z |= collection.add(it2.next());
        }
        return z;
    }

    public static <T> Iterator<T> e(Iterator<? extends Iterator<? extends T>> it2) {
        return new b(it2);
    }

    public static <T> bf<T> b(Iterator<T> it2, n<? super T> nVar) {
        m.a(it2);
        m.a(nVar);
        return new AnonymousClass2(it2, nVar);
    }

    /* renamed from: com.google.common.collect.Iterators$2  reason: invalid class name */
    static class AnonymousClass2 extends AbstractIterator<T> {
        final /* synthetic */ Iterator a;
        final /* synthetic */ n b;

        AnonymousClass2(Iterator it2, n nVar) {
            this.a = it2;
            this.b = nVar;
        }

        /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object] */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.common.collect.AbstractIterator
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T a() {
            /*
                r2 = this;
            L_0x0000:
                java.util.Iterator r0 = r2.a
                boolean r0 = r0.hasNext()
                if (r0 == 0) goto L_0x0017
                java.util.Iterator r0 = r2.a
                java.lang.Object r0 = r0.next()
                com.google.common.base.n r1 = r2.b
                boolean r1 = r1.apply(r0)
                if (r1 == 0) goto L_0x0000
                return r0
            L_0x0017:
                java.lang.Object r0 = r2.b()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Iterators.AnonymousClass2.a():java.lang.Object");
        }
    }

    public static <T> boolean c(Iterator<T> it2, n<? super T> nVar) {
        return d(it2, nVar) != -1;
    }

    public static <T> int d(Iterator<T> it2, n<? super T> nVar) {
        m.a(nVar, "predicate");
        int i = 0;
        while (it2.hasNext()) {
            if (nVar.apply(it2.next())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /* renamed from: com.google.common.collect.Iterators$3  reason: invalid class name */
    static class AnonymousClass3 extends bc<F, T> {
        final /* synthetic */ g a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Iterator it2, g gVar) {
            super(it2);
            this.a = gVar;
        }

        /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Object] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // com.google.common.collect.bc
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T a(F r2) {
            /*
                r1 = this;
                com.google.common.base.g r0 = r1.a
                java.lang.Object r2 = r0.apply(r2)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Iterators.AnonymousClass3.a(java.lang.Object):java.lang.Object");
        }
    }

    public static <F, T> Iterator<T> a(Iterator<F> it2, g<? super F, ? extends T> gVar) {
        m.a(gVar);
        return new AnonymousClass3(it2, gVar);
    }

    public static <T> T b(Iterator<? extends T> it2, T t) {
        return it2.hasNext() ? (T) it2.next() : t;
    }

    public static <T> T f(Iterator<T> it2) {
        T next;
        do {
            next = it2.next();
        } while (it2.hasNext());
        return next;
    }

    public static int a(Iterator<?> it2, int i) {
        m.a(it2);
        int i2 = 0;
        m.a(i >= 0, "numberToAdvance must be nonnegative");
        while (i2 < i && it2.hasNext()) {
            it2.next();
            i2++;
        }
        return i2;
    }

    static <T> T g(Iterator<T> it2) {
        if (!it2.hasNext()) {
            return null;
        }
        T next = it2.next();
        it2.remove();
        return next;
    }

    static void h(Iterator<?> it2) {
        m.a(it2);
        while (it2.hasNext()) {
            it2.next();
            it2.remove();
        }
    }

    /* access modifiers changed from: private */
    public static final class a<T> extends a<T> {
        static final bg<Object> a = new a(new Object[0], 0, 0, 0);
        private final T[] b;
        private final int c;

        a(T[] tArr, int i, int i2, int i3) {
            super(i2, i3);
            this.b = tArr;
            this.c = i;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.a
        public T a(int i) {
            return this.b[this.c + i];
        }
    }

    /* renamed from: com.google.common.collect.Iterators$4  reason: invalid class name */
    static class AnonymousClass4 extends bf<T> {
        boolean a;
        final /* synthetic */ Object b;

        AnonymousClass4(Object obj) {
            this.b = obj;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.a;
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // java.util.Iterator
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T next() {
            /*
                r1 = this;
                boolean r0 = r1.a
                if (r0 != 0) goto L_0x000a
                r0 = 1
                r1.a = r0
                java.lang.Object r0 = r1.b
                return r0
            L_0x000a:
                java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
                r0.<init>()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Iterators.AnonymousClass4.next():java.lang.Object");
        }
    }

    public static <T> bf<T> a(T t) {
        return new AnonymousClass4(t);
    }

    /* access modifiers changed from: private */
    public static class d<E> implements an<E> {
        private final Iterator<? extends E> a;
        private boolean b;
        private E c;

        public d(Iterator<? extends E> it2) {
            this.a = (Iterator) m.a(it2);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.b || this.a.hasNext();
        }

        @Override // com.google.common.collect.an, java.util.Iterator
        public E next() {
            if (!this.b) {
                return (E) this.a.next();
            }
            E e = this.c;
            this.b = false;
            this.c = null;
            return e;
        }

        @Override // java.util.Iterator
        public void remove() {
            m.b(!this.b, "Can't remove after you've peeked at next");
            this.a.remove();
        }

        @Override // com.google.common.collect.an
        public E a() {
            if (!this.b) {
                this.c = (E) this.a.next();
                this.b = true;
            }
            return this.c;
        }
    }

    public static <T> an<T> i(Iterator<? extends T> it2) {
        if (it2 instanceof d) {
            return (d) it2;
        }
        return new d(it2);
    }

    public static <T> bf<T> a(Iterable<? extends Iterator<? extends T>> iterable, Comparator<? super T> comparator) {
        m.a(iterable, "iterators");
        m.a(comparator, "comparator");
        return new c(iterable, comparator);
    }

    private static class c<T> extends bf<T> {
        final Queue<an<T>> a;

        public c(Iterable<? extends Iterator<? extends T>> iterable, Comparator<? super T> comparator) {
            this.a = new PriorityQueue(2, new AnonymousClass1(comparator));
            Iterator<? extends Iterator<? extends T>> it2 = iterable.iterator();
            while (it2.hasNext()) {
                Iterator it3 = (Iterator) it2.next();
                if (it3.hasNext()) {
                    this.a.add(Iterators.i(it3));
                }
            }
        }

        /* renamed from: com.google.common.collect.Iterators$c$1  reason: invalid class name */
        class AnonymousClass1 implements Comparator<an<T>> {
            final /* synthetic */ Comparator a;

            AnonymousClass1(Comparator comparator) {
                this.a = comparator;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Comparator */
            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: a */
            public int compare(an<T> anVar, an<T> anVar2) {
                return this.a.compare(anVar.a(), anVar2.a());
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.a.isEmpty();
        }

        @Override // java.util.Iterator
        public T next() {
            an<T> remove = this.a.remove();
            T t = (T) remove.next();
            if (remove.hasNext()) {
                this.a.add(remove);
            }
            return t;
        }
    }

    private static class b<T> implements Iterator<T> {
        private Iterator<? extends T> a;
        private Iterator<? extends T> b = Iterators.a();
        private Iterator<? extends Iterator<? extends T>> c;
        private Deque<Iterator<? extends Iterator<? extends T>>> d;

        b(Iterator<? extends Iterator<? extends T>> it2) {
            this.c = (Iterator) m.a(it2);
        }

        private Iterator<? extends Iterator<? extends T>> a() {
            while (true) {
                Iterator<? extends Iterator<? extends T>> it2 = this.c;
                if (it2 != null && it2.hasNext()) {
                    return this.c;
                }
                Deque<Iterator<? extends Iterator<? extends T>>> deque = this.d;
                if (deque == null || deque.isEmpty()) {
                    return null;
                }
                this.c = this.d.removeFirst();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (!((Iterator) m.a(this.b)).hasNext()) {
                this.c = a();
                Iterator<? extends Iterator<? extends T>> it2 = this.c;
                if (it2 == null) {
                    return false;
                }
                this.b = (Iterator) it2.next();
                Iterator<? extends T> it3 = this.b;
                if (it3 instanceof b) {
                    b bVar = (b) it3;
                    this.b = bVar.b;
                    if (this.d == null) {
                        this.d = new ArrayDeque();
                    }
                    this.d.addFirst(this.c);
                    if (bVar.d != null) {
                        while (!bVar.d.isEmpty()) {
                            this.d.addFirst(bVar.d.removeLast());
                        }
                    }
                    this.c = bVar.c;
                }
            }
            return true;
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                Iterator<? extends T> it2 = this.b;
                this.a = it2;
                return (T) it2.next();
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            n.a(this.a != null);
            this.a.remove();
            this.a = null;
        }
    }

    static <T> ListIterator<T> j(Iterator<T> it2) {
        return (ListIterator) it2;
    }
}
