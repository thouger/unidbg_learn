package com.google.common.collect;

/* access modifiers changed from: package-private */
public abstract class IndexedImmutableSet<E> extends ImmutableSet<E> {
    /* access modifiers changed from: package-private */
    public abstract E get(int i);

    IndexedImmutableSet() {
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet
    public bf<E> iterator() {
        return asList().iterator();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i) {
        return asList().copyIntoArray(objArr, i);
    }

    /* renamed from: com.google.common.collect.IndexedImmutableSet$1  reason: invalid class name */
    class AnonymousClass1 extends ImmutableList<E> {
        AnonymousClass1() {
        }

        @Override // java.util.List
        public E get(int i) {
            return (E) IndexedImmutableSet.this.get(i);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return IndexedImmutableSet.this.isPartialView();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IndexedImmutableSet.this.size();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet
    public ImmutableList<E> createAsList() {
        return new AnonymousClass1();
    }
}
