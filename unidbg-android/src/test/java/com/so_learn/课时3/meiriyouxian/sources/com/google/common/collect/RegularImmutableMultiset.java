package com.google.common.collect;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.aj;
import com.google.common.primitives.Ints;
import java.io.Serializable;

/* access modifiers changed from: package-private */
public class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    static final RegularImmutableMultiset<Object> EMPTY = new RegularImmutableMultiset<>(al.a());
    final transient al<E> contents;
    private transient ImmutableSet<E> elementSet;
    private final transient int size;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    RegularImmutableMultiset(al<E> alVar) {
        this.contents = alVar;
        long j = 0;
        for (int i = 0; i < alVar.c(); i++) {
            j += (long) alVar.d(i);
        }
        this.size = Ints.b(j);
    }

    @Override // com.google.common.collect.aj
    public int count(Object obj) {
        return this.contents.b(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.aj
    public ImmutableSet<E> elementSet() {
        ImmutableSet<E> immutableSet = this.elementSet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ElementSet elementSet = new ElementSet();
        this.elementSet = elementSet;
        return elementSet;
    }

    /* access modifiers changed from: private */
    public final class ElementSet extends IndexedImmutableSet<E> {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        private ElementSet() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.IndexedImmutableSet
        public E get(int i) {
            return (E) RegularImmutableMultiset.this.contents.c(i);
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return RegularImmutableMultiset.this.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return RegularImmutableMultiset.this.contents.c();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMultiset
    public aj.a<E> getEntry(int i) {
        return this.contents.e(i);
    }

    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final int[] counts;
        final Object[] elements;

        SerializedForm(aj<?> ajVar) {
            int size = ajVar.entrySet().size();
            this.elements = new Object[size];
            this.counts = new int[size];
            int i = 0;
            for (aj.a<?> aVar : ajVar.entrySet()) {
                this.elements[i] = aVar.getElement();
                this.counts[i] = aVar.getCount();
                i++;
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableMultiset$a */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public Object readResolve() {
            ImmutableMultiset.a aVar = new ImmutableMultiset.a(this.elements.length);
            int i = 0;
            while (true) {
                Object[] objArr = this.elements;
                if (i >= objArr.length) {
                    return aVar.a();
                }
                aVar.a((ImmutableMultiset.a) objArr[i], this.counts[i]);
                i++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
