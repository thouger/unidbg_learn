package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.aj;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class ImmutableMultiset<E> extends ImmutableMultisetGwtSerializationDependencies<E> implements aj<E> {
    private transient ImmutableList<E> asList;
    private transient ImmutableSet<aj.a<E>> entrySet;

    @Override // com.google.common.collect.aj
    public abstract ImmutableSet<E> elementSet();

    /* access modifiers changed from: package-private */
    public abstract aj.a<E> getEntry(int i);

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public abstract Object writeReplace();

    public static <E> ImmutableMultiset<E> of() {
        return RegularImmutableMultiset.EMPTY;
    }

    public static <E> ImmutableMultiset<E> of(E e) {
        return copyFromElements(e);
    }

    public static <E> ImmutableMultiset<E> of(E e, E e2) {
        return copyFromElements(e, e2);
    }

    public static <E> ImmutableMultiset<E> of(E e, E e2, E e3) {
        return copyFromElements(e, e2, e3);
    }

    public static <E> ImmutableMultiset<E> of(E e, E e2, E e3, E e4) {
        return copyFromElements(e, e2, e3, e4);
    }

    public static <E> ImmutableMultiset<E> of(E e, E e2, E e3, E e4, E e5) {
        return copyFromElements(e, e2, e3, e4, e5);
    }

    public static <E> ImmutableMultiset<E> of(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        return new a().a((a) e).a((a<E>) e2).a((a<E>) e3).a((a<E>) e4).a((a<E>) e5).a((a<E>) e6).a((Object[]) eArr).a();
    }

    public static <E> ImmutableMultiset<E> copyOf(E[] eArr) {
        return copyFromElements(eArr);
    }

    public static <E> ImmutableMultiset<E> copyOf(Iterable<? extends E> iterable) {
        if (iterable instanceof ImmutableMultiset) {
            ImmutableMultiset<E> immutableMultiset = (ImmutableMultiset) iterable;
            if (!immutableMultiset.isPartialView()) {
                return immutableMultiset;
            }
        }
        a aVar = new a(Multisets.a(iterable));
        aVar.a((Iterable) iterable);
        return aVar.a();
    }

    public static <E> ImmutableMultiset<E> copyOf(Iterator<? extends E> it2) {
        return new a().a((Iterator) it2).a();
    }

    private static <E> ImmutableMultiset<E> copyFromElements(E... eArr) {
        return new a().a((Object[]) eArr).a();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableMultiset$a */
    /* JADX WARN: Multi-variable type inference failed */
    static <E> ImmutableMultiset<E> copyFromEntries(Collection<? extends aj.a<? extends E>> collection) {
        a aVar = new a(collection.size());
        Iterator<? extends aj.a<? extends E>> it2 = collection.iterator();
        while (it2.hasNext()) {
            aj.a aVar2 = (aj.a) it2.next();
            aVar.a((a) aVar2.getElement(), aVar2.getCount());
        }
        return aVar.a();
    }

    ImmutableMultiset() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.ImmutableMultiset$1  reason: invalid class name */
    public class AnonymousClass1 extends bf<E> {
        int a;
        E b;
        final /* synthetic */ Iterator c;

        AnonymousClass1(Iterator it2) {
            this.c = it2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.a > 0 || this.c.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (this.a <= 0) {
                aj.a aVar = (aj.a) this.c.next();
                this.b = (E) aVar.getElement();
                this.a = aVar.getCount();
            }
            this.a--;
            return this.b;
        }
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet
    public bf<E> iterator() {
        return new AnonymousClass1(entrySet().iterator());
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> asList = super.asList();
        this.asList = asList;
        return asList;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return count(obj) > 0;
    }

    @Override // com.google.common.collect.aj
    @Deprecated
    public final int add(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.aj
    @Deprecated
    public final int remove(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.aj
    @Deprecated
    public final int setCount(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.aj
    @Deprecated
    public final boolean setCount(E e, int i, int i2) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i) {
        bf<aj.a<E>> it2 = entrySet().iterator();
        while (it2.hasNext()) {
            E next = it2.next();
            Arrays.fill(objArr, i, next.getCount() + i, next.getElement());
            i += next.getCount();
        }
        return i;
    }

    @Override // java.util.Collection, java.lang.Object, com.google.common.collect.aj
    public boolean equals(Object obj) {
        return Multisets.a(this, obj);
    }

    @Override // java.util.Collection, java.lang.Object, com.google.common.collect.aj
    public int hashCode() {
        return Sets.a(entrySet());
    }

    @Override // java.util.AbstractCollection, java.lang.Object
    public String toString() {
        return entrySet().toString();
    }

    @Override // com.google.common.collect.aj
    public ImmutableSet<aj.a<E>> entrySet() {
        ImmutableSet<aj.a<E>> immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<aj.a<E>> createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    private ImmutableSet<aj.a<E>> createEntrySet() {
        return isEmpty() ? ImmutableSet.of() : new EntrySet(this, null);
    }

    /* access modifiers changed from: private */
    public final class EntrySet extends IndexedImmutableSet<aj.a<E>> {
        private static final long serialVersionUID = 0;

        private EntrySet() {
        }

        /* synthetic */ EntrySet(ImmutableMultiset immutableMultiset, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return ImmutableMultiset.this.isPartialView();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.IndexedImmutableSet
        public aj.a<E> get(int i) {
            return ImmutableMultiset.this.getEntry(i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return ImmutableMultiset.this.elementSet().size();
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof aj.a)) {
                return false;
            }
            aj.a aVar = (aj.a) obj;
            if (aVar.getCount() > 0 && ImmutableMultiset.this.count(aVar.getElement()) == aVar.getCount()) {
                return true;
            }
            return false;
        }

        @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.lang.Object, java.util.Set
        public int hashCode() {
            return ImmutableMultiset.this.hashCode();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return new EntrySetSerializedForm(ImmutableMultiset.this);
        }
    }

    static class EntrySetSerializedForm<E> implements Serializable {
        final ImmutableMultiset<E> multiset;

        EntrySetSerializedForm(ImmutableMultiset<E> immutableMultiset) {
            this.multiset = immutableMultiset;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.multiset.entrySet();
        }
    }

    public static <E> a<E> builder() {
        return new a<>();
    }

    public static class a<E> extends ImmutableCollection.b<E> {
        al<E> a;
        boolean b;
        boolean c;

        @Override // com.google.common.collect.ImmutableCollection.b
        public /* synthetic */ ImmutableCollection.b b(Object obj) {
            return a((a<E>) obj);
        }

        public a() {
            this(4);
        }

        a(int i) {
            this.b = false;
            this.c = false;
            this.a = al.a(i);
        }

        a(boolean z) {
            this.b = false;
            this.c = false;
            this.a = null;
        }

        public a<E> a(E e) {
            return a(e, 1);
        }

        /* renamed from: b */
        public a<E> a(E... eArr) {
            super.a((Object[]) eArr);
            return this;
        }

        public a<E> a(E e, int i) {
            if (i == 0) {
                return this;
            }
            if (this.b) {
                this.a = new al<>(this.a);
                this.c = false;
            }
            this.b = false;
            m.a(e);
            al<E> alVar = this.a;
            alVar.a(e, i + alVar.b(e));
            return this;
        }

        /* renamed from: b */
        public a<E> a(Iterable<? extends E> iterable) {
            if (iterable instanceof aj) {
                aj b = Multisets.b(iterable);
                al c = c(b);
                if (c != null) {
                    al<E> alVar = this.a;
                    alVar.f(Math.max(alVar.c(), c.c()));
                    for (int b2 = c.b(); b2 >= 0; b2 = c.b(b2)) {
                        a((a<E>) c.c(b2), c.d(b2));
                    }
                } else {
                    Set<aj.a<E>> entrySet = b.entrySet();
                    al<E> alVar2 = this.a;
                    alVar2.f(Math.max(alVar2.c(), entrySet.size()));
                    for (aj.a<E> aVar : b.entrySet()) {
                        a((a<E>) aVar.getElement(), aVar.getCount());
                    }
                }
            } else {
                super.a((Iterable) iterable);
            }
            return this;
        }

        /* renamed from: b */
        public a<E> a(Iterator<? extends E> it2) {
            super.a((Iterator) it2);
            return this;
        }

        /* JADX DEBUG: Type inference failed for r1v3. Raw type applied. Possible types: com.google.common.collect.al<E>, com.google.common.collect.al<T> */
        /* JADX DEBUG: Type inference failed for r1v5. Raw type applied. Possible types: com.google.common.collect.al<E>, com.google.common.collect.al<T> */
        static <T> al<T> c(Iterable<T> iterable) {
            if (iterable instanceof RegularImmutableMultiset) {
                return (al<E>) ((RegularImmutableMultiset) iterable).contents;
            }
            if (iterable instanceof AbstractMapBasedMultiset) {
                return (al<E>) ((AbstractMapBasedMultiset) iterable).backingMap;
            }
            return null;
        }

        public ImmutableMultiset<E> a() {
            if (this.a.c() == 0) {
                return ImmutableMultiset.of();
            }
            if (this.c) {
                this.a = new al<>(this.a);
                this.c = false;
            }
            this.b = true;
            return new RegularImmutableMultiset(this.a);
        }
    }
}
