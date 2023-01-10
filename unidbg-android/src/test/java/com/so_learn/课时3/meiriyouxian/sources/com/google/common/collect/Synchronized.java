package com.google.common.collect;

import com.google.common.base.g;
import com.google.common.base.m;
import com.google.common.collect.aj;
import com.google.common.collect.ba;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/* access modifiers changed from: package-private */
public final class Synchronized {

    /* access modifiers changed from: package-private */
    public static class SynchronizedObject implements Serializable {
        private static final long serialVersionUID = 0;
        final Object delegate;
        final Object mutex;

        SynchronizedObject(Object obj, Object obj2) {
            this.delegate = m.a(obj);
            this.mutex = obj2 == null ? this : obj2;
        }

        /* access modifiers changed from: package-private */
        public Object delegate() {
            return this.delegate;
        }

        @Override // java.lang.Object
        public String toString() {
            String obj;
            synchronized (this.mutex) {
                obj = this.delegate.toString();
            }
            return obj;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            synchronized (this.mutex) {
                objectOutputStream.defaultWriteObject();
            }
        }
    }

    /* access modifiers changed from: private */
    public static <E> Collection<E> c(Collection<E> collection, Object obj) {
        return new SynchronizedCollection(collection, obj);
    }

    /* access modifiers changed from: package-private */
    public static class SynchronizedCollection<E> extends SynchronizedObject implements Collection<E> {
        private static final long serialVersionUID = 0;

        private SynchronizedCollection(Collection<E> collection, Object obj) {
            super(collection, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Collection<E> delegate() {
            return (Collection) super.delegate();
        }

        @Override // java.util.Collection
        public boolean add(E e) {
            boolean add;
            synchronized (this.mutex) {
                add = delegate().add(e);
            }
            return add;
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = delegate().addAll(collection);
            }
            return addAll;
        }

        @Override // java.util.Collection
        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        @Override // java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            boolean contains;
            synchronized (this.mutex) {
                contains = delegate().contains(obj);
            }
            return contains;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            boolean containsAll;
            synchronized (this.mutex) {
                containsAll = delegate().containsAll(collection);
            }
            return containsAll;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return delegate().iterator();
        }

        @Override // java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            boolean remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj);
            }
            return remove;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            boolean removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(collection);
            }
            return removeAll;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            boolean retainAll;
            synchronized (this.mutex) {
                retainAll = delegate().retainAll(collection);
            }
            return retainAll;
        }

        @Override // java.util.Collection
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        @Override // java.util.Collection, java.util.Set
        public Object[] toArray() {
            Object[] array;
            synchronized (this.mutex) {
                array = delegate().toArray();
            }
            return array;
        }

        @Override // java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            T[] tArr2;
            synchronized (this.mutex) {
                tArr2 = (T[]) delegate().toArray(tArr);
            }
            return tArr2;
        }
    }

    static <E> Set<E> a(Set<E> set, Object obj) {
        return new SynchronizedSet(set, obj);
    }

    /* access modifiers changed from: package-private */
    public static class SynchronizedSet<E> extends SynchronizedCollection<E> implements Set<E> {
        private static final long serialVersionUID = 0;

        SynchronizedSet(Set<E> set, Object obj) {
            super(set, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Set<E> delegate() {
            return (Set) super.delegate();
        }

        @Override // java.lang.Object, java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @Override // java.lang.Object, java.util.Collection, java.util.Set
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }
    }

    /* access modifiers changed from: private */
    public static <E> SortedSet<E> b(SortedSet<E> sortedSet, Object obj) {
        return new SynchronizedSortedSet(sortedSet, obj);
    }

    /* access modifiers changed from: package-private */
    public static class SynchronizedSortedSet<E> extends SynchronizedSet<E> implements SortedSet<E> {
        private static final long serialVersionUID = 0;

        SynchronizedSortedSet(SortedSet<E> sortedSet, Object obj) {
            super(sortedSet, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public SortedSet<E> delegate() {
            return (SortedSet) super.delegate();
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            Comparator<? super E> comparator;
            synchronized (this.mutex) {
                comparator = delegate().comparator();
            }
            return comparator;
        }

        @Override // java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> subSet(E e, E e2) {
            SortedSet<E> b;
            synchronized (this.mutex) {
                b = Synchronized.b((SortedSet) delegate().subSet(e, e2), this.mutex);
            }
            return b;
        }

        @Override // java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> headSet(E e) {
            SortedSet<E> b;
            synchronized (this.mutex) {
                b = Synchronized.b((SortedSet) delegate().headSet(e), this.mutex);
            }
            return b;
        }

        @Override // java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> tailSet(E e) {
            SortedSet<E> b;
            synchronized (this.mutex) {
                b = Synchronized.b((SortedSet) delegate().tailSet(e), this.mutex);
            }
            return b;
        }

        @Override // java.util.SortedSet
        public E first() {
            E first;
            synchronized (this.mutex) {
                first = delegate().first();
            }
            return first;
        }

        @Override // java.util.SortedSet
        public E last() {
            E last;
            synchronized (this.mutex) {
                last = delegate().last();
            }
            return last;
        }
    }

    /* access modifiers changed from: private */
    public static <E> List<E> b(List<E> list, Object obj) {
        return list instanceof RandomAccess ? new SynchronizedRandomAccessList(list, obj) : new SynchronizedList(list, obj);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedList<E> extends SynchronizedCollection<E> implements List<E> {
        private static final long serialVersionUID = 0;

        SynchronizedList(List<E> list, Object obj) {
            super(list, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public List<E> delegate() {
            return (List) super.delegate();
        }

        @Override // java.util.List
        public void add(int i, E e) {
            synchronized (this.mutex) {
                delegate().add(i, e);
            }
        }

        @Override // java.util.List
        public boolean addAll(int i, Collection<? extends E> collection) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = delegate().addAll(i, collection);
            }
            return addAll;
        }

        @Override // java.util.List
        public E get(int i) {
            E e;
            synchronized (this.mutex) {
                e = delegate().get(i);
            }
            return e;
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            int indexOf;
            synchronized (this.mutex) {
                indexOf = delegate().indexOf(obj);
            }
            return indexOf;
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            int lastIndexOf;
            synchronized (this.mutex) {
                lastIndexOf = delegate().lastIndexOf(obj);
            }
            return lastIndexOf;
        }

        @Override // java.util.List
        public ListIterator<E> listIterator() {
            return delegate().listIterator();
        }

        @Override // java.util.List
        public ListIterator<E> listIterator(int i) {
            return delegate().listIterator(i);
        }

        @Override // java.util.List
        public E remove(int i) {
            E remove;
            synchronized (this.mutex) {
                remove = delegate().remove(i);
            }
            return remove;
        }

        @Override // java.util.List
        public E set(int i, E e) {
            E e2;
            synchronized (this.mutex) {
                e2 = delegate().set(i, e);
            }
            return e2;
        }

        @Override // java.util.List
        public List<E> subList(int i, int i2) {
            List<E> b;
            synchronized (this.mutex) {
                b = Synchronized.b((List) delegate().subList(i, i2), this.mutex);
            }
            return b;
        }

        @Override // java.lang.Object, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @Override // java.lang.Object, java.util.Collection, java.util.List
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }
    }

    /* access modifiers changed from: private */
    public static class SynchronizedRandomAccessList<E> extends SynchronizedList<E> implements RandomAccess {
        private static final long serialVersionUID = 0;

        SynchronizedRandomAccessList(List<E> list, Object obj) {
            super(list, obj);
        }
    }

    static <E> aj<E> a(aj<E> ajVar, Object obj) {
        return ((ajVar instanceof SynchronizedMultiset) || (ajVar instanceof ImmutableMultiset)) ? ajVar : new SynchronizedMultiset(ajVar, obj);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedMultiset<E> extends SynchronizedCollection<E> implements aj<E> {
        private static final long serialVersionUID = 0;
        transient Set<E> elementSet;
        transient Set<aj.a<E>> entrySet;

        SynchronizedMultiset(aj<E> ajVar, Object obj) {
            super(ajVar, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public aj<E> delegate() {
            return (aj) super.delegate();
        }

        @Override // com.google.common.collect.aj
        public int count(Object obj) {
            int count;
            synchronized (this.mutex) {
                count = delegate().count(obj);
            }
            return count;
        }

        @Override // com.google.common.collect.aj
        public int add(E e, int i) {
            int add;
            synchronized (this.mutex) {
                add = delegate().add(e, i);
            }
            return add;
        }

        @Override // com.google.common.collect.aj
        public int remove(Object obj, int i) {
            int remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj, i);
            }
            return remove;
        }

        @Override // com.google.common.collect.aj
        public int setCount(E e, int i) {
            int count;
            synchronized (this.mutex) {
                count = delegate().setCount(e, i);
            }
            return count;
        }

        @Override // com.google.common.collect.aj
        public boolean setCount(E e, int i, int i2) {
            boolean count;
            synchronized (this.mutex) {
                count = delegate().setCount(e, i, i2);
            }
            return count;
        }

        @Override // com.google.common.collect.aj
        public Set<E> elementSet() {
            Set<E> set;
            synchronized (this.mutex) {
                if (this.elementSet == null) {
                    this.elementSet = Synchronized.c((Set) delegate().elementSet(), this.mutex);
                }
                set = this.elementSet;
            }
            return set;
        }

        @Override // com.google.common.collect.aj
        public Set<aj.a<E>> entrySet() {
            Set<aj.a<E>> set;
            synchronized (this.mutex) {
                if (this.entrySet == null) {
                    this.entrySet = Synchronized.c((Set) delegate().entrySet(), this.mutex);
                }
                set = this.entrySet;
            }
            return set;
        }

        @Override // java.lang.Object, java.util.Collection, com.google.common.collect.aj
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @Override // java.lang.Object, java.util.Collection, com.google.common.collect.aj
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }
    }

    /* access modifiers changed from: private */
    public static class SynchronizedMultimap<K, V> extends SynchronizedObject implements ai<K, V> {
        private static final long serialVersionUID = 0;
        transient Map<K, Collection<V>> asMap;
        transient Collection<Map.Entry<K, V>> entries;
        transient Set<K> keySet;
        transient aj<K> keys;
        transient Collection<V> valuesCollection;

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public ai<K, V> delegate() {
            return (ai) super.delegate();
        }

        SynchronizedMultimap(ai<K, V> aiVar, Object obj) {
            super(aiVar, obj);
        }

        @Override // com.google.common.collect.ai
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        @Override // com.google.common.collect.ai
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        @Override // com.google.common.collect.ai
        public boolean containsKey(Object obj) {
            boolean containsKey;
            synchronized (this.mutex) {
                containsKey = delegate().containsKey(obj);
            }
            return containsKey;
        }

        @Override // com.google.common.collect.ai
        public boolean containsValue(Object obj) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = delegate().containsValue(obj);
            }
            return containsValue;
        }

        @Override // com.google.common.collect.ai
        public boolean containsEntry(Object obj, Object obj2) {
            boolean containsEntry;
            synchronized (this.mutex) {
                containsEntry = delegate().containsEntry(obj, obj2);
            }
            return containsEntry;
        }

        @Override // com.google.common.collect.ai, com.google.common.collect.ah
        public Collection<V> get(K k) {
            Collection<V> d;
            synchronized (this.mutex) {
                d = Synchronized.d(delegate().get(k), this.mutex);
            }
            return d;
        }

        @Override // com.google.common.collect.ai
        public boolean put(K k, V v) {
            boolean put;
            synchronized (this.mutex) {
                put = delegate().put(k, v);
            }
            return put;
        }

        @Override // com.google.common.collect.ai
        public boolean putAll(K k, Iterable<? extends V> iterable) {
            boolean putAll;
            synchronized (this.mutex) {
                putAll = delegate().putAll(k, iterable);
            }
            return putAll;
        }

        @Override // com.google.common.collect.ai
        public boolean putAll(ai<? extends K, ? extends V> aiVar) {
            boolean putAll;
            synchronized (this.mutex) {
                putAll = delegate().putAll(aiVar);
            }
            return putAll;
        }

        @Override // com.google.common.collect.ai, com.google.common.collect.ah
        public Collection<V> replaceValues(K k, Iterable<? extends V> iterable) {
            Collection<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues(k, iterable);
            }
            return replaceValues;
        }

        @Override // com.google.common.collect.ai
        public boolean remove(Object obj, Object obj2) {
            boolean remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj, obj2);
            }
            return remove;
        }

        @Override // com.google.common.collect.ai, com.google.common.collect.ah
        public Collection<V> removeAll(Object obj) {
            Collection<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(obj);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.ai
        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        @Override // com.google.common.collect.ai
        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.mutex) {
                if (this.keySet == null) {
                    this.keySet = Synchronized.c((Set) delegate().keySet(), this.mutex);
                }
                set = this.keySet;
            }
            return set;
        }

        @Override // com.google.common.collect.ai
        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.mutex) {
                if (this.valuesCollection == null) {
                    this.valuesCollection = Synchronized.c(delegate().values(), this.mutex);
                }
                collection = this.valuesCollection;
            }
            return collection;
        }

        @Override // com.google.common.collect.ai
        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection;
            synchronized (this.mutex) {
                if (this.entries == null) {
                    this.entries = Synchronized.d(delegate().entries(), this.mutex);
                }
                collection = this.entries;
            }
            return collection;
        }

        @Override // com.google.common.collect.ai
        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map;
            synchronized (this.mutex) {
                if (this.asMap == null) {
                    this.asMap = new SynchronizedAsMap(delegate().asMap(), this.mutex);
                }
                map = this.asMap;
            }
            return map;
        }

        @Override // com.google.common.collect.ai
        public aj<K> keys() {
            aj<K> ajVar;
            synchronized (this.mutex) {
                if (this.keys == null) {
                    this.keys = Synchronized.a((aj) delegate().keys(), this.mutex);
                }
                ajVar = this.keys;
            }
            return ajVar;
        }

        @Override // java.lang.Object, com.google.common.collect.ai
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @Override // java.lang.Object, com.google.common.collect.ai
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }
    }

    private static class SynchronizedListMultimap<K, V> extends SynchronizedMultimap<K, V> implements ah<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedListMultimap(ah<K, V> ahVar, Object obj) {
            super(ahVar, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedObject
        public ah<K, V> delegate() {
            return (ah) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.ai, com.google.common.collect.ah
        public List<V> get(K k) {
            List<V> b;
            synchronized (this.mutex) {
                b = Synchronized.b((List) delegate().get((ah<K, V>) k), this.mutex);
            }
            return b;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.ai, com.google.common.collect.ah
        public List<V> removeAll(Object obj) {
            List<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(obj);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.ai, com.google.common.collect.ah
        public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
            List<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues((ah<K, V>) k, (Iterable) iterable);
            }
            return replaceValues;
        }
    }

    /* access modifiers changed from: private */
    public static class SynchronizedSetMultimap<K, V> extends SynchronizedMultimap<K, V> implements at<K, V> {
        private static final long serialVersionUID = 0;
        transient Set<Map.Entry<K, V>> entrySet;

        SynchronizedSetMultimap(at<K, V> atVar, Object obj) {
            super(atVar, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedObject
        public at<K, V> delegate() {
            return (at) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.ai, com.google.common.collect.ah
        public Set<V> get(K k) {
            Set<V> a;
            synchronized (this.mutex) {
                a = Synchronized.a((Set) delegate().get((at<K, V>) k), this.mutex);
            }
            return a;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.ai, com.google.common.collect.ah
        public Set<V> removeAll(Object obj) {
            Set<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(obj);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.ai, com.google.common.collect.ah
        public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
            Set<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues((at<K, V>) k, (Iterable) iterable);
            }
            return replaceValues;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.ai
        public Set<Map.Entry<K, V>> entries() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.mutex) {
                if (this.entrySet == null) {
                    this.entrySet = Synchronized.a((Set) delegate().entries(), this.mutex);
                }
                set = this.entrySet;
            }
            return set;
        }
    }

    private static class SynchronizedSortedSetMultimap<K, V> extends SynchronizedSetMultimap<K, V> implements az<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedSortedSetMultimap(az<K, V> azVar, Object obj) {
            super(azVar, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedObject
        public az<K, V> delegate() {
            return (az) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.ai, com.google.common.collect.ah
        public SortedSet<V> get(K k) {
            SortedSet<V> b;
            synchronized (this.mutex) {
                b = Synchronized.b((SortedSet) delegate().get((az<K, V>) k), this.mutex);
            }
            return b;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.ai, com.google.common.collect.ah
        public SortedSet<V> removeAll(Object obj) {
            SortedSet<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(obj);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.ai, com.google.common.collect.ah
        public SortedSet<V> replaceValues(K k, Iterable<? extends V> iterable) {
            SortedSet<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues((az<K, V>) k, (Iterable) iterable);
            }
            return replaceValues;
        }

        @Override // com.google.common.collect.az
        public Comparator<? super V> valueComparator() {
            Comparator<? super V> valueComparator;
            synchronized (this.mutex) {
                valueComparator = delegate().valueComparator();
            }
            return valueComparator;
        }
    }

    /* access modifiers changed from: private */
    public static <E> Collection<E> d(Collection<E> collection, Object obj) {
        if (collection instanceof SortedSet) {
            return b((SortedSet) collection, obj);
        }
        if (collection instanceof Set) {
            return a((Set) collection, obj);
        }
        if (collection instanceof List) {
            return b((List) collection, obj);
        }
        return c(collection, obj);
    }

    /* access modifiers changed from: private */
    public static <E> Set<E> c(Set<E> set, Object obj) {
        if (set instanceof SortedSet) {
            return b((SortedSet) set, obj);
        }
        return a((Set) set, obj);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedAsMapEntries<K, V> extends SynchronizedSet<Map.Entry<K, Collection<V>>> {
        private static final long serialVersionUID = 0;

        SynchronizedAsMapEntries(Set<Map.Entry<K, Collection<V>>> set, Object obj) {
            super(set, obj);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.Synchronized$SynchronizedAsMapEntries$1  reason: invalid class name */
        public class AnonymousClass1 extends bc<Map.Entry<K, Collection<V>>, Map.Entry<K, Collection<V>>> {
            AnonymousClass1(Iterator it2) {
                super(it2);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.bc
            public /* bridge */ /* synthetic */ Object a(Object obj) {
                return a((Map.Entry) ((Map.Entry) obj));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: com.google.common.collect.Synchronized$SynchronizedAsMapEntries$1$1  reason: invalid class name */
            public class AnonymousClass1 extends w<K, Collection<V>> {
                final /* synthetic */ Map.Entry a;

                AnonymousClass1(Map.Entry entry) {
                    this.a = entry;
                }

                /* access modifiers changed from: protected */
                @Override // com.google.common.collect.w
                /* renamed from: a */
                public Map.Entry<K, Collection<V>> delegate() {
                    return this.a;
                }

                /* renamed from: b */
                public Collection<V> getValue() {
                    return Synchronized.d((Collection) this.a.getValue(), SynchronizedAsMapEntries.this.mutex);
                }
            }

            /* access modifiers changed from: package-private */
            public Map.Entry<K, Collection<V>> a(Map.Entry<K, Collection<V>> entry) {
                return new AnonymousClass1(entry);
            }
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, Collection<V>>> iterator() {
            return new AnonymousClass1(super.iterator());
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            Object[] a;
            synchronized (this.mutex) {
                a = ak.a(delegate());
            }
            return a;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            T[] tArr2;
            synchronized (this.mutex) {
                tArr2 = (T[]) ak.a((Collection<?>) delegate(), (Object[]) tArr);
            }
            return tArr2;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            boolean a;
            synchronized (this.mutex) {
                a = Maps.a((Collection) delegate(), obj);
            }
            return a;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            boolean a;
            synchronized (this.mutex) {
                a = o.a((Collection<?>) delegate(), collection);
            }
            return a;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSet, java.lang.Object, java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            boolean a;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                a = Sets.a(delegate(), obj);
            }
            return a;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            boolean b;
            synchronized (this.mutex) {
                b = Maps.b(delegate(), obj);
            }
            return b;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            boolean a;
            synchronized (this.mutex) {
                a = Iterators.a((Iterator<?>) delegate().iterator(), collection);
            }
            return a;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            boolean b;
            synchronized (this.mutex) {
                b = Iterators.b((Iterator<?>) delegate().iterator(), collection);
            }
            return b;
        }
    }

    static <K, V> Map<K, V> a(Map<K, V> map, Object obj) {
        return new SynchronizedMap(map, obj);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedMap<K, V> extends SynchronizedObject implements Map<K, V> {
        private static final long serialVersionUID = 0;
        transient Set<Map.Entry<K, V>> entrySet;
        transient Set<K> keySet;
        transient Collection<V> values;

        SynchronizedMap(Map<K, V> map, Object obj) {
            super(map, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Map<K, V> delegate() {
            return (Map) super.delegate();
        }

        @Override // java.util.Map
        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            boolean containsKey;
            synchronized (this.mutex) {
                containsKey = delegate().containsKey(obj);
            }
            return containsKey;
        }

        @Override // java.util.Map
        public boolean containsValue(Object obj) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = delegate().containsValue(obj);
            }
            return containsValue;
        }

        @Override // java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.mutex) {
                if (this.entrySet == null) {
                    this.entrySet = Synchronized.a((Set) delegate().entrySet(), this.mutex);
                }
                set = this.entrySet;
            }
            return set;
        }

        @Override // java.util.Map
        public V get(Object obj) {
            V v;
            synchronized (this.mutex) {
                v = delegate().get(obj);
            }
            return v;
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        @Override // java.util.Map
        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.mutex) {
                if (this.keySet == null) {
                    this.keySet = Synchronized.a((Set) delegate().keySet(), this.mutex);
                }
                set = this.keySet;
            }
            return set;
        }

        @Override // java.util.Map
        public V put(K k, V v) {
            V put;
            synchronized (this.mutex) {
                put = delegate().put(k, v);
            }
            return put;
        }

        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            synchronized (this.mutex) {
                delegate().putAll(map);
            }
        }

        @Override // java.util.Map
        public V remove(Object obj) {
            V remove;
            synchronized (this.mutex) {
                remove = delegate().remove(obj);
            }
            return remove;
        }

        @Override // java.util.Map
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        @Override // java.util.Map
        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.mutex) {
                if (this.values == null) {
                    this.values = Synchronized.c(delegate().values(), this.mutex);
                }
                collection = this.values;
            }
            return collection;
        }

        @Override // java.lang.Object, java.util.Map
        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @Override // java.lang.Object, java.util.Map
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }
    }

    static <K, V> SortedMap<K, V> a(SortedMap<K, V> sortedMap, Object obj) {
        return new SynchronizedSortedMap(sortedMap, obj);
    }

    /* access modifiers changed from: package-private */
    public static class SynchronizedSortedMap<K, V> extends SynchronizedMap<K, V> implements SortedMap<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedSortedMap(SortedMap<K, V> sortedMap, Object obj) {
            super(sortedMap, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedObject
        public SortedMap<K, V> delegate() {
            return (SortedMap) super.delegate();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator;
            synchronized (this.mutex) {
                comparator = delegate().comparator();
            }
            return comparator;
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            K firstKey;
            synchronized (this.mutex) {
                firstKey = delegate().firstKey();
            }
            return firstKey;
        }

        @Override // java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> headMap(K k) {
            SortedMap<K, V> a;
            synchronized (this.mutex) {
                a = Synchronized.a((SortedMap) delegate().headMap(k), this.mutex);
            }
            return a;
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            K lastKey;
            synchronized (this.mutex) {
                lastKey = delegate().lastKey();
            }
            return lastKey;
        }

        @Override // java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> subMap(K k, K k2) {
            SortedMap<K, V> a;
            synchronized (this.mutex) {
                a = Synchronized.a((SortedMap) delegate().subMap(k, k2), this.mutex);
            }
            return a;
        }

        @Override // java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> tailMap(K k) {
            SortedMap<K, V> a;
            synchronized (this.mutex) {
                a = Synchronized.a((SortedMap) delegate().tailMap(k), this.mutex);
            }
            return a;
        }
    }

    static class SynchronizedBiMap<K, V> extends SynchronizedMap<K, V> implements k<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        private transient k<V, K> inverse;
        private transient Set<V> valueSet;

        private SynchronizedBiMap(k<K, V> kVar, Object obj, k<V, K> kVar2) {
            super(kVar, obj);
            this.inverse = kVar2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedObject
        public k<K, V> delegate() {
            return (k) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Set<V> values() {
            Set<V> set;
            synchronized (this.mutex) {
                if (this.valueSet == null) {
                    this.valueSet = Synchronized.a((Set) delegate().values(), this.mutex);
                }
                set = this.valueSet;
            }
            return set;
        }

        @Override // com.google.common.collect.k
        public V forcePut(K k, V v) {
            V v2;
            synchronized (this.mutex) {
                v2 = (V) delegate().forcePut(k, v);
            }
            return v2;
        }

        @Override // com.google.common.collect.k
        public k<V, K> inverse() {
            k<V, K> kVar;
            synchronized (this.mutex) {
                if (this.inverse == null) {
                    this.inverse = new SynchronizedBiMap(delegate().inverse(), this.mutex, this);
                }
                kVar = this.inverse;
            }
            return kVar;
        }
    }

    private static class SynchronizedAsMap<K, V> extends SynchronizedMap<K, Collection<V>> {
        private static final long serialVersionUID = 0;
        transient Set<Map.Entry<K, Collection<V>>> asMapEntrySet;
        transient Collection<Collection<V>> asMapValues;

        SynchronizedAsMap(Map<K, Collection<V>> map, Object obj) {
            super(map, obj);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Collection<V> get(Object obj) {
            Collection<V> collection;
            synchronized (this.mutex) {
                Collection collection2 = (Collection) super.get(obj);
                if (collection2 == null) {
                    collection = null;
                } else {
                    collection = Synchronized.d(collection2, this.mutex);
                }
            }
            return collection;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Set<Map.Entry<K, Collection<V>>> entrySet() {
            Set<Map.Entry<K, Collection<V>>> set;
            synchronized (this.mutex) {
                if (this.asMapEntrySet == null) {
                    this.asMapEntrySet = new SynchronizedAsMapEntries(delegate().entrySet(), this.mutex);
                }
                set = this.asMapEntrySet;
            }
            return set;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Collection<Collection<V>> values() {
            Collection<Collection<V>> collection;
            synchronized (this.mutex) {
                if (this.asMapValues == null) {
                    this.asMapValues = new SynchronizedAsMapValues(delegate().values(), this.mutex);
                }
                collection = this.asMapValues;
            }
            return collection;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public boolean containsValue(Object obj) {
            return values().contains(obj);
        }
    }

    /* access modifiers changed from: private */
    public static class SynchronizedAsMapValues<V> extends SynchronizedCollection<Collection<V>> {
        private static final long serialVersionUID = 0;

        SynchronizedAsMapValues(Collection<Collection<V>> collection, Object obj) {
            super(collection, obj);
        }

        /* renamed from: com.google.common.collect.Synchronized$SynchronizedAsMapValues$1  reason: invalid class name */
        class AnonymousClass1 extends bc<Collection<V>, Collection<V>> {
            AnonymousClass1(Iterator it2) {
                super(it2);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.bc
            public /* bridge */ /* synthetic */ Object a(Object obj) {
                return a((Collection) ((Collection) obj));
            }

            /* access modifiers changed from: package-private */
            public Collection<V> a(Collection<V> collection) {
                return Synchronized.d(collection, SynchronizedAsMapValues.this.mutex);
            }
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Collection<V>> iterator() {
            return new AnonymousClass1(super.iterator());
        }
    }

    /* access modifiers changed from: package-private */
    public static class SynchronizedNavigableSet<E> extends SynchronizedSortedSet<E> implements NavigableSet<E> {
        private static final long serialVersionUID = 0;
        transient NavigableSet<E> descendingSet;

        SynchronizedNavigableSet(NavigableSet<E> navigableSet, Object obj) {
            super(navigableSet, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public NavigableSet<E> delegate() {
            return (NavigableSet) super.delegate();
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            E ceiling;
            synchronized (this.mutex) {
                ceiling = delegate().ceiling(e);
            }
            return ceiling;
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return delegate().descendingIterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            synchronized (this.mutex) {
                if (this.descendingSet == null) {
                    NavigableSet<E> a = Synchronized.a((NavigableSet) delegate().descendingSet(), this.mutex);
                    this.descendingSet = a;
                    return a;
                }
                return this.descendingSet;
            }
        }

        @Override // java.util.NavigableSet
        public E floor(E e) {
            E floor;
            synchronized (this.mutex) {
                floor = delegate().floor(e);
            }
            return floor;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E e, boolean z) {
            NavigableSet<E> a;
            synchronized (this.mutex) {
                a = Synchronized.a((NavigableSet) delegate().headSet(e, z), this.mutex);
            }
            return a;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> headSet(E e) {
            return headSet(e, false);
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            E higher;
            synchronized (this.mutex) {
                higher = delegate().higher(e);
            }
            return higher;
        }

        @Override // java.util.NavigableSet
        public E lower(E e) {
            E lower;
            synchronized (this.mutex) {
                lower = delegate().lower(e);
            }
            return lower;
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            E pollFirst;
            synchronized (this.mutex) {
                pollFirst = delegate().pollFirst();
            }
            return pollFirst;
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            E pollLast;
            synchronized (this.mutex) {
                pollLast = delegate().pollLast();
            }
            return pollLast;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            NavigableSet<E> a;
            synchronized (this.mutex) {
                a = Synchronized.a((NavigableSet) delegate().subSet(e, z, e2, z2), this.mutex);
            }
            return a;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> subSet(E e, E e2) {
            return subSet(e, true, e2, false);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E e, boolean z) {
            NavigableSet<E> a;
            synchronized (this.mutex) {
                a = Synchronized.a((NavigableSet) delegate().tailSet(e, z), this.mutex);
            }
            return a;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedSet, java.util.SortedSet, java.util.NavigableSet
        public SortedSet<E> tailSet(E e) {
            return tailSet(e, true);
        }
    }

    static <E> NavigableSet<E> a(NavigableSet<E> navigableSet, Object obj) {
        return new SynchronizedNavigableSet(navigableSet, obj);
    }

    static <K, V> NavigableMap<K, V> a(NavigableMap<K, V> navigableMap, Object obj) {
        return new SynchronizedNavigableMap(navigableMap, obj);
    }

    /* access modifiers changed from: package-private */
    public static class SynchronizedNavigableMap<K, V> extends SynchronizedSortedMap<K, V> implements NavigableMap<K, V> {
        private static final long serialVersionUID = 0;
        transient NavigableSet<K> descendingKeySet;
        transient NavigableMap<K, V> descendingMap;
        transient NavigableSet<K> navigableKeySet;

        SynchronizedNavigableMap(NavigableMap<K, V> navigableMap, Object obj) {
            super(navigableMap, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedObject
        public NavigableMap<K, V> delegate() {
            return (NavigableMap) super.delegate();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K k) {
            Map.Entry<K, V> b;
            synchronized (this.mutex) {
                b = Synchronized.b(delegate().ceilingEntry(k), this.mutex);
            }
            return b;
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            K ceilingKey;
            synchronized (this.mutex) {
                ceilingKey = delegate().ceilingKey(k);
            }
            return ceilingKey;
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            synchronized (this.mutex) {
                if (this.descendingKeySet == null) {
                    NavigableSet<K> a = Synchronized.a((NavigableSet) delegate().descendingKeySet(), this.mutex);
                    this.descendingKeySet = a;
                    return a;
                }
                return this.descendingKeySet;
            }
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            synchronized (this.mutex) {
                if (this.descendingMap == null) {
                    NavigableMap<K, V> a = Synchronized.a((NavigableMap) delegate().descendingMap(), this.mutex);
                    this.descendingMap = a;
                    return a;
                }
                return this.descendingMap;
            }
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            Map.Entry<K, V> b;
            synchronized (this.mutex) {
                b = Synchronized.b(delegate().firstEntry(), this.mutex);
            }
            return b;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K k) {
            Map.Entry<K, V> b;
            synchronized (this.mutex) {
                b = Synchronized.b(delegate().floorEntry(k), this.mutex);
            }
            return b;
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            K floorKey;
            synchronized (this.mutex) {
                floorKey = delegate().floorKey(k);
            }
            return floorKey;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K k, boolean z) {
            NavigableMap<K, V> a;
            synchronized (this.mutex) {
                a = Synchronized.a((NavigableMap) delegate().headMap(k, z), this.mutex);
            }
            return a;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> headMap(K k) {
            return headMap(k, false);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K k) {
            Map.Entry<K, V> b;
            synchronized (this.mutex) {
                b = Synchronized.b(delegate().higherEntry(k), this.mutex);
            }
            return b;
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            K higherKey;
            synchronized (this.mutex) {
                higherKey = delegate().higherKey(k);
            }
            return higherKey;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            Map.Entry<K, V> b;
            synchronized (this.mutex) {
                b = Synchronized.b(delegate().lastEntry(), this.mutex);
            }
            return b;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K k) {
            Map.Entry<K, V> b;
            synchronized (this.mutex) {
                b = Synchronized.b(delegate().lowerEntry(k), this.mutex);
            }
            return b;
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            K lowerKey;
            synchronized (this.mutex) {
                lowerKey = delegate().lowerKey(k);
            }
            return lowerKey;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            synchronized (this.mutex) {
                if (this.navigableKeySet == null) {
                    NavigableSet<K> a = Synchronized.a((NavigableSet) delegate().navigableKeySet(), this.mutex);
                    this.navigableKeySet = a;
                    return a;
                }
                return this.navigableKeySet;
            }
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            Map.Entry<K, V> b;
            synchronized (this.mutex) {
                b = Synchronized.b(delegate().pollFirstEntry(), this.mutex);
            }
            return b;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            Map.Entry<K, V> b;
            synchronized (this.mutex) {
                b = Synchronized.b(delegate().pollLastEntry(), this.mutex);
            }
            return b;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            NavigableMap<K, V> a;
            synchronized (this.mutex) {
                a = Synchronized.a((NavigableMap) delegate().subMap(k, z, k2, z2), this.mutex);
            }
            return a;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K k, boolean z) {
            NavigableMap<K, V> a;
            synchronized (this.mutex) {
                a = Synchronized.a((NavigableMap) delegate().tailMap(k, z), this.mutex);
            }
            return a;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, V> tailMap(K k) {
            return tailMap(k, true);
        }
    }

    /* access modifiers changed from: private */
    public static <K, V> Map.Entry<K, V> b(Map.Entry<K, V> entry, Object obj) {
        if (entry == null) {
            return null;
        }
        return new SynchronizedEntry(entry, obj);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedEntry<K, V> extends SynchronizedObject implements Map.Entry<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedEntry(Map.Entry<K, V> entry, Object obj) {
            super(entry, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Map.Entry<K, V> delegate() {
            return (Map.Entry) super.delegate();
        }

        @Override // java.lang.Object, java.util.Map.Entry
        public boolean equals(Object obj) {
            boolean equals;
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        @Override // java.lang.Object, java.util.Map.Entry
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            K key;
            synchronized (this.mutex) {
                key = delegate().getKey();
            }
            return key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            V value;
            synchronized (this.mutex) {
                value = delegate().getValue();
            }
            return value;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V value;
            synchronized (this.mutex) {
                value = delegate().setValue(v);
            }
            return value;
        }
    }

    /* access modifiers changed from: private */
    public static class SynchronizedQueue<E> extends SynchronizedCollection<E> implements Queue<E> {
        private static final long serialVersionUID = 0;

        SynchronizedQueue(Queue<E> queue, Object obj) {
            super(queue, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Queue<E> delegate() {
            return (Queue) super.delegate();
        }

        @Override // java.util.Queue
        public E element() {
            E element;
            synchronized (this.mutex) {
                element = delegate().element();
            }
            return element;
        }

        @Override // java.util.Queue
        public boolean offer(E e) {
            boolean offer;
            synchronized (this.mutex) {
                offer = delegate().offer(e);
            }
            return offer;
        }

        @Override // java.util.Queue
        public E peek() {
            E peek;
            synchronized (this.mutex) {
                peek = delegate().peek();
            }
            return peek;
        }

        @Override // java.util.Queue
        public E poll() {
            E poll;
            synchronized (this.mutex) {
                poll = delegate().poll();
            }
            return poll;
        }

        @Override // java.util.Queue
        public E remove() {
            E remove;
            synchronized (this.mutex) {
                remove = delegate().remove();
            }
            return remove;
        }
    }

    private static final class SynchronizedDeque<E> extends SynchronizedQueue<E> implements Deque<E> {
        private static final long serialVersionUID = 0;

        SynchronizedDeque(Deque<E> deque, Object obj) {
            super(deque, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedQueue, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedObject
        public Deque<E> delegate() {
            return (Deque) super.delegate();
        }

        @Override // java.util.Deque
        public void addFirst(E e) {
            synchronized (this.mutex) {
                delegate().addFirst(e);
            }
        }

        @Override // java.util.Deque
        public void addLast(E e) {
            synchronized (this.mutex) {
                delegate().addLast(e);
            }
        }

        @Override // java.util.Deque
        public boolean offerFirst(E e) {
            boolean offerFirst;
            synchronized (this.mutex) {
                offerFirst = delegate().offerFirst(e);
            }
            return offerFirst;
        }

        @Override // java.util.Deque
        public boolean offerLast(E e) {
            boolean offerLast;
            synchronized (this.mutex) {
                offerLast = delegate().offerLast(e);
            }
            return offerLast;
        }

        @Override // java.util.Deque
        public E removeFirst() {
            E removeFirst;
            synchronized (this.mutex) {
                removeFirst = delegate().removeFirst();
            }
            return removeFirst;
        }

        @Override // java.util.Deque
        public E removeLast() {
            E removeLast;
            synchronized (this.mutex) {
                removeLast = delegate().removeLast();
            }
            return removeLast;
        }

        @Override // java.util.Deque
        public E pollFirst() {
            E pollFirst;
            synchronized (this.mutex) {
                pollFirst = delegate().pollFirst();
            }
            return pollFirst;
        }

        @Override // java.util.Deque
        public E pollLast() {
            E pollLast;
            synchronized (this.mutex) {
                pollLast = delegate().pollLast();
            }
            return pollLast;
        }

        @Override // java.util.Deque
        public E getFirst() {
            E first;
            synchronized (this.mutex) {
                first = delegate().getFirst();
            }
            return first;
        }

        @Override // java.util.Deque
        public E getLast() {
            E last;
            synchronized (this.mutex) {
                last = delegate().getLast();
            }
            return last;
        }

        @Override // java.util.Deque
        public E peekFirst() {
            E peekFirst;
            synchronized (this.mutex) {
                peekFirst = delegate().peekFirst();
            }
            return peekFirst;
        }

        @Override // java.util.Deque
        public E peekLast() {
            E peekLast;
            synchronized (this.mutex) {
                peekLast = delegate().peekLast();
            }
            return peekLast;
        }

        @Override // java.util.Deque
        public boolean removeFirstOccurrence(Object obj) {
            boolean removeFirstOccurrence;
            synchronized (this.mutex) {
                removeFirstOccurrence = delegate().removeFirstOccurrence(obj);
            }
            return removeFirstOccurrence;
        }

        @Override // java.util.Deque
        public boolean removeLastOccurrence(Object obj) {
            boolean removeLastOccurrence;
            synchronized (this.mutex) {
                removeLastOccurrence = delegate().removeLastOccurrence(obj);
            }
            return removeLastOccurrence;
        }

        @Override // java.util.Deque
        public void push(E e) {
            synchronized (this.mutex) {
                delegate().push(e);
            }
        }

        @Override // java.util.Deque
        public E pop() {
            E pop;
            synchronized (this.mutex) {
                pop = delegate().pop();
            }
            return pop;
        }

        @Override // java.util.Deque
        public Iterator<E> descendingIterator() {
            Iterator<E> descendingIterator;
            synchronized (this.mutex) {
                descendingIterator = delegate().descendingIterator();
            }
            return descendingIterator;
        }
    }

    /* access modifiers changed from: private */
    public static final class SynchronizedTable<R, C, V> extends SynchronizedObject implements ba<R, C, V> {
        SynchronizedTable(ba<R, C, V> baVar, Object obj) {
            super(baVar, obj);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public ba<R, C, V> delegate() {
            return (ba) super.delegate();
        }

        @Override // com.google.common.collect.ba
        public boolean contains(Object obj, Object obj2) {
            boolean contains;
            synchronized (this.mutex) {
                contains = delegate().contains(obj, obj2);
            }
            return contains;
        }

        @Override // com.google.common.collect.ba
        public boolean containsRow(Object obj) {
            boolean containsRow;
            synchronized (this.mutex) {
                containsRow = delegate().containsRow(obj);
            }
            return containsRow;
        }

        @Override // com.google.common.collect.ba
        public boolean containsColumn(Object obj) {
            boolean containsColumn;
            synchronized (this.mutex) {
                containsColumn = delegate().containsColumn(obj);
            }
            return containsColumn;
        }

        @Override // com.google.common.collect.ba
        public boolean containsValue(Object obj) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = delegate().containsValue(obj);
            }
            return containsValue;
        }

        @Override // com.google.common.collect.ba
        public V get(Object obj, Object obj2) {
            V v;
            synchronized (this.mutex) {
                v = (V) delegate().get(obj, obj2);
            }
            return v;
        }

        @Override // com.google.common.collect.ba
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        @Override // com.google.common.collect.ba
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        @Override // com.google.common.collect.ba
        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        @Override // com.google.common.collect.ba
        public V put(R r, C c, V v) {
            V v2;
            synchronized (this.mutex) {
                v2 = (V) delegate().put(r, c, v);
            }
            return v2;
        }

        @Override // com.google.common.collect.ba
        public void putAll(ba<? extends R, ? extends C, ? extends V> baVar) {
            synchronized (this.mutex) {
                delegate().putAll(baVar);
            }
        }

        @Override // com.google.common.collect.ba
        public V remove(Object obj, Object obj2) {
            V v;
            synchronized (this.mutex) {
                v = (V) delegate().remove(obj, obj2);
            }
            return v;
        }

        @Override // com.google.common.collect.ba
        public Map<C, V> row(R r) {
            Map<C, V> a;
            synchronized (this.mutex) {
                a = Synchronized.a(delegate().row(r), this.mutex);
            }
            return a;
        }

        @Override // com.google.common.collect.ba
        public Map<R, V> column(C c) {
            Map<R, V> a;
            synchronized (this.mutex) {
                a = Synchronized.a(delegate().column(c), this.mutex);
            }
            return a;
        }

        @Override // com.google.common.collect.ba
        public Set<ba.a<R, C, V>> cellSet() {
            Set<ba.a<R, C, V>> a;
            synchronized (this.mutex) {
                a = Synchronized.a((Set) delegate().cellSet(), this.mutex);
            }
            return a;
        }

        @Override // com.google.common.collect.ba
        public Set<R> rowKeySet() {
            Set<R> a;
            synchronized (this.mutex) {
                a = Synchronized.a((Set) delegate().rowKeySet(), this.mutex);
            }
            return a;
        }

        @Override // com.google.common.collect.ba
        public Set<C> columnKeySet() {
            Set<C> a;
            synchronized (this.mutex) {
                a = Synchronized.a((Set) delegate().columnKeySet(), this.mutex);
            }
            return a;
        }

        @Override // com.google.common.collect.ba
        public Collection<V> values() {
            Collection<V> c;
            synchronized (this.mutex) {
                c = Synchronized.c(delegate().values(), this.mutex);
            }
            return c;
        }

        @Override // com.google.common.collect.ba
        public Map<R, Map<C, V>> rowMap() {
            Map<R, Map<C, V>> a;
            synchronized (this.mutex) {
                a = Synchronized.a(Maps.a((Map) delegate().rowMap(), (g) new AnonymousClass1()), this.mutex);
            }
            return a;
        }

        /* renamed from: com.google.common.collect.Synchronized$SynchronizedTable$1  reason: invalid class name */
        class AnonymousClass1 implements g<Map<C, V>, Map<C, V>> {
            AnonymousClass1() {
            }

            /* renamed from: a */
            public Map<C, V> apply(Map<C, V> map) {
                return Synchronized.a(map, SynchronizedTable.this.mutex);
            }
        }

        @Override // com.google.common.collect.ba
        public Map<C, Map<R, V>> columnMap() {
            Map<C, Map<R, V>> a;
            synchronized (this.mutex) {
                a = Synchronized.a(Maps.a((Map) delegate().columnMap(), (g) new AnonymousClass2()), this.mutex);
            }
            return a;
        }

        /* renamed from: com.google.common.collect.Synchronized$SynchronizedTable$2  reason: invalid class name */
        class AnonymousClass2 implements g<Map<R, V>, Map<R, V>> {
            AnonymousClass2() {
            }

            /* renamed from: a */
            public Map<R, V> apply(Map<R, V> map) {
                return Synchronized.a(map, SynchronizedTable.this.mutex);
            }
        }

        @Override // java.lang.Object, com.google.common.collect.ba
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        @Override // java.lang.Object, com.google.common.collect.ba
        public boolean equals(Object obj) {
            boolean equals;
            if (this == obj) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }
    }
}
