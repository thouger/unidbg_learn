package com.google.common.collect;

import com.google.common.base.g;
import com.google.common.base.m;
import com.google.common.base.q;
import com.google.common.collect.AbstractMapBasedMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import com.google.common.collect.aj;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;

public final class Multimaps {

    private static class CustomMultimap<K, V> extends AbstractMapBasedMultimap<K, V> {
        private static final long serialVersionUID = 0;
        transient q<? extends Collection<V>> factory;

        CustomMultimap(Map<K, Collection<V>> map, q<? extends Collection<V>> qVar) {
            super(map);
            this.factory = (q) m.a(qVar);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c
        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c
        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractMapBasedMultimap
        public Collection<V> createCollection() {
            return (Collection) this.factory.get();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap
        public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
            if (collection instanceof NavigableSet) {
                return Sets.a((NavigableSet) collection);
            }
            if (collection instanceof SortedSet) {
                return Collections.unmodifiableSortedSet((SortedSet) collection);
            }
            if (collection instanceof Set) {
                return Collections.unmodifiableSet((Set) collection);
            }
            if (collection instanceof List) {
                return Collections.unmodifiableList((List) collection);
            }
            return Collections.unmodifiableCollection(collection);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap
        public Collection<V> wrapCollection(K k, Collection<V> collection) {
            if (collection instanceof List) {
                return wrapList(k, (List) collection, null);
            }
            if (collection instanceof NavigableSet) {
                return new AbstractMapBasedMultimap.k(k, (NavigableSet) collection, null);
            }
            if (collection instanceof SortedSet) {
                return new AbstractMapBasedMultimap.m(k, (SortedSet) collection, null);
            }
            if (collection instanceof Set) {
                return new AbstractMapBasedMultimap.l(k, (Set) collection);
            }
            return new AbstractMapBasedMultimap.i(k, collection, null);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.factory);
            objectOutputStream.writeObject(backingMap());
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.factory = (q) objectInputStream.readObject();
            setMap((Map) objectInputStream.readObject());
        }
    }

    private static class CustomListMultimap<K, V> extends AbstractListMultimap<K, V> {
        private static final long serialVersionUID = 0;
        transient q<? extends List<V>> factory;

        CustomListMultimap(Map<K, Collection<V>> map, q<? extends List<V>> qVar) {
            super(map);
            this.factory = (q) m.a(qVar);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c
        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c
        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractListMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public List<V> createCollection() {
            return (List) this.factory.get();
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.factory);
            objectOutputStream.writeObject(backingMap());
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.factory = (q) objectInputStream.readObject();
            setMap((Map) objectInputStream.readObject());
        }
    }

    private static class CustomSetMultimap<K, V> extends AbstractSetMultimap<K, V> {
        private static final long serialVersionUID = 0;
        transient q<? extends Set<V>> factory;

        CustomSetMultimap(Map<K, Collection<V>> map, q<? extends Set<V>> qVar) {
            super(map);
            this.factory = (q) m.a(qVar);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c
        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c
        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public Set<V> createCollection() {
            return (Set) this.factory.get();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
            if (collection instanceof NavigableSet) {
                return Sets.a((NavigableSet) collection);
            }
            if (collection instanceof SortedSet) {
                return Collections.unmodifiableSortedSet((SortedSet) collection);
            }
            return Collections.unmodifiableSet((Set) collection);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public Collection<V> wrapCollection(K k, Collection<V> collection) {
            if (collection instanceof NavigableSet) {
                return new AbstractMapBasedMultimap.k(k, (NavigableSet) collection, null);
            }
            if (collection instanceof SortedSet) {
                return new AbstractMapBasedMultimap.m(k, (SortedSet) collection, null);
            }
            return new AbstractMapBasedMultimap.l(k, (Set) collection);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.factory);
            objectOutputStream.writeObject(backingMap());
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.factory = (q) objectInputStream.readObject();
            setMap((Map) objectInputStream.readObject());
        }
    }

    private static class CustomSortedSetMultimap<K, V> extends AbstractSortedSetMultimap<K, V> {
        private static final long serialVersionUID = 0;
        transient q<? extends SortedSet<V>> factory;
        transient Comparator<? super V> valueComparator;

        CustomSortedSetMultimap(Map<K, Collection<V>> map, q<? extends SortedSet<V>> qVar) {
            super(map);
            this.factory = (q) m.a(qVar);
            this.valueComparator = ((SortedSet) qVar.get()).comparator();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c
        public Set<K> createKeySet() {
            return createMaybeNavigableKeySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.c
        public Map<K, Collection<V>> createAsMap() {
            return createMaybeNavigableAsMap();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractSortedSetMultimap, com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public SortedSet<V> createCollection() {
            return (SortedSet) this.factory.get();
        }

        @Override // com.google.common.collect.az
        public Comparator<? super V> valueComparator() {
            return this.valueComparator;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.factory);
            objectOutputStream.writeObject(backingMap());
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.factory = (q) objectInputStream.readObject();
            this.valueComparator = ((SortedSet) this.factory.get()).comparator();
            setMap((Map) objectInputStream.readObject());
        }
    }

    /* access modifiers changed from: private */
    public static class UnmodifiableMultimap<K, V> extends x<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final ai<K, V> delegate;
        transient Collection<Map.Entry<K, V>> entries;
        transient Set<K> keySet;
        transient aj<K> keys;
        transient Map<K, Collection<V>> map;
        transient Collection<V> values;

        UnmodifiableMultimap(ai<K, V> aiVar) {
            this.delegate = (ai) m.a(aiVar);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.x, com.google.common.collect.z
        public ai<K, V> delegate() {
            return this.delegate;
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai
        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map = this.map;
            if (map != null) {
                return map;
            }
            Map<K, Collection<V>> unmodifiableMap = Collections.unmodifiableMap(Maps.a((Map) this.delegate.asMap(), (g) new AnonymousClass1()));
            this.map = unmodifiableMap;
            return unmodifiableMap;
        }

        /* renamed from: com.google.common.collect.Multimaps$UnmodifiableMultimap$1  reason: invalid class name */
        class AnonymousClass1 implements g<Collection<V>, Collection<V>> {
            AnonymousClass1() {
            }

            /* renamed from: a */
            public Collection<V> apply(Collection<V> collection) {
                return Multimaps.c(collection);
            }
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai
        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection = this.entries;
            if (collection != null) {
                return collection;
            }
            Collection<Map.Entry<K, V>> d = Multimaps.d(this.delegate.entries());
            this.entries = d;
            return d;
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai, com.google.common.collect.ah
        public Collection<V> get(K k) {
            return Multimaps.c(this.delegate.get(k));
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai
        public aj<K> keys() {
            aj<K> ajVar = this.keys;
            if (ajVar != null) {
                return ajVar;
            }
            aj<K> a = Multisets.a((aj) this.delegate.keys());
            this.keys = a;
            return a;
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai
        public Set<K> keySet() {
            Set<K> set = this.keySet;
            if (set != null) {
                return set;
            }
            Set<K> unmodifiableSet = Collections.unmodifiableSet(this.delegate.keySet());
            this.keySet = unmodifiableSet;
            return unmodifiableSet;
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai
        public boolean put(K k, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai
        public boolean putAll(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai
        public boolean putAll(ai<? extends K, ? extends V> aiVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai
        public boolean remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai, com.google.common.collect.ah
        public Collection<V> removeAll(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai, com.google.common.collect.ah
        public Collection<V> replaceValues(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.x, com.google.common.collect.ai
        public Collection<V> values() {
            Collection<V> collection = this.values;
            if (collection != null) {
                return collection;
            }
            Collection<V> unmodifiableCollection = Collections.unmodifiableCollection(this.delegate.values());
            this.values = unmodifiableCollection;
            return unmodifiableCollection;
        }
    }

    private static class UnmodifiableListMultimap<K, V> extends UnmodifiableMultimap<K, V> implements ah<K, V> {
        private static final long serialVersionUID = 0;

        UnmodifiableListMultimap(ah<K, V> ahVar) {
            super(ahVar);
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.z
        public ah<K, V> delegate() {
            return (ah) super.delegate();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.ai, com.google.common.collect.ah
        public List<V> get(K k) {
            return Collections.unmodifiableList(delegate().get((ah<K, V>) k));
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.ai, com.google.common.collect.ah
        public List<V> removeAll(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.ai, com.google.common.collect.ah
        public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: private */
    public static class UnmodifiableSetMultimap<K, V> extends UnmodifiableMultimap<K, V> implements at<K, V> {
        private static final long serialVersionUID = 0;

        UnmodifiableSetMultimap(at<K, V> atVar) {
            super(atVar);
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.z
        public at<K, V> delegate() {
            return (at) super.delegate();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.ai, com.google.common.collect.ah
        public Set<V> get(K k) {
            return Collections.unmodifiableSet(delegate().get((at<K, V>) k));
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.ai
        public Set<Map.Entry<K, V>> entries() {
            return Maps.a((Set) delegate().entries());
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.ai, com.google.common.collect.ah
        public Set<V> removeAll(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.ai, com.google.common.collect.ah
        public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }
    }

    private static class UnmodifiableSortedSetMultimap<K, V> extends UnmodifiableSetMultimap<K, V> implements az<K, V> {
        private static final long serialVersionUID = 0;

        UnmodifiableSortedSetMultimap(az<K, V> azVar) {
            super(azVar);
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.z
        public az<K, V> delegate() {
            return (az) super.delegate();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.ai, com.google.common.collect.ah
        public SortedSet<V> get(K k) {
            return Collections.unmodifiableSortedSet(delegate().get((az<K, V>) k));
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.ai, com.google.common.collect.ah
        public SortedSet<V> removeAll(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.x, com.google.common.collect.ai, com.google.common.collect.ah
        public SortedSet<V> replaceValues(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.az
        public Comparator<? super V> valueComparator() {
            return delegate().valueComparator();
        }
    }

    /* access modifiers changed from: private */
    public static <V> Collection<V> c(Collection<V> collection) {
        if (collection instanceof SortedSet) {
            return Collections.unmodifiableSortedSet((SortedSet) collection);
        }
        if (collection instanceof Set) {
            return Collections.unmodifiableSet((Set) collection);
        }
        if (collection instanceof List) {
            return Collections.unmodifiableList((List) collection);
        }
        return Collections.unmodifiableCollection(collection);
    }

    /* access modifiers changed from: private */
    public static <K, V> Collection<Map.Entry<K, V>> d(Collection<Map.Entry<K, V>> collection) {
        if (collection instanceof Set) {
            return Maps.a((Set) collection);
        }
        return new Maps.j(Collections.unmodifiableCollection(collection));
    }

    /* access modifiers changed from: private */
    public static class MapMultimap<K, V> extends c<K, V> implements at<K, V>, Serializable {
        private static final long serialVersionUID = 7845222491160860175L;
        final Map<K, V> map;

        MapMultimap(Map<K, V> map) {
            this.map = (Map) m.a(map);
        }

        @Override // com.google.common.collect.ai
        public int size() {
            return this.map.size();
        }

        @Override // com.google.common.collect.ai
        public boolean containsKey(Object obj) {
            return this.map.containsKey(obj);
        }

        @Override // com.google.common.collect.c, com.google.common.collect.ai
        public boolean containsValue(Object obj) {
            return this.map.containsValue(obj);
        }

        @Override // com.google.common.collect.c, com.google.common.collect.ai
        public boolean containsEntry(Object obj, Object obj2) {
            return this.map.entrySet().contains(Maps.a(obj, obj2));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.Multimaps$MapMultimap$1  reason: invalid class name */
        public class AnonymousClass1 extends Sets.b<V> {
            final /* synthetic */ Object a;

            AnonymousClass1(Object obj) {
                this.a = obj;
            }

            /* renamed from: com.google.common.collect.Multimaps$MapMultimap$1$1  reason: invalid class name */
            class AnonymousClass1 implements Iterator<V> {
                int a;

                AnonymousClass1() {
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.a == 0 && MapMultimap.this.map.containsKey(AnonymousClass1.this.a);
                }

                @Override // java.util.Iterator
                public V next() {
                    if (hasNext()) {
                        this.a++;
                        return MapMultimap.this.map.get(AnonymousClass1.this.a);
                    }
                    throw new NoSuchElementException();
                }

                @Override // java.util.Iterator
                public void remove() {
                    boolean z = true;
                    if (this.a != 1) {
                        z = false;
                    }
                    n.a(z);
                    this.a = -1;
                    MapMultimap.this.map.remove(AnonymousClass1.this.a);
                }
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<V> iterator() {
                return new AnonymousClass1();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return MapMultimap.this.map.containsKey(this.a) ? 1 : 0;
            }
        }

        @Override // com.google.common.collect.ai, com.google.common.collect.ah
        public Set<V> get(K k) {
            return new AnonymousClass1(k);
        }

        @Override // com.google.common.collect.c, com.google.common.collect.ai
        public boolean put(K k, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.c, com.google.common.collect.ai
        public boolean putAll(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.c, com.google.common.collect.ai
        public boolean putAll(ai<? extends K, ? extends V> aiVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.c, com.google.common.collect.ai, com.google.common.collect.ah
        public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.c, com.google.common.collect.ai
        public boolean remove(Object obj, Object obj2) {
            return this.map.entrySet().remove(Maps.a(obj, obj2));
        }

        @Override // com.google.common.collect.ai, com.google.common.collect.ah
        public Set<V> removeAll(Object obj) {
            HashSet hashSet = new HashSet(2);
            if (!this.map.containsKey(obj)) {
                return hashSet;
            }
            hashSet.add(this.map.remove(obj));
            return hashSet;
        }

        @Override // com.google.common.collect.ai
        public void clear() {
            this.map.clear();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.c
        public Set<K> createKeySet() {
            return this.map.keySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.c
        public Collection<V> createValues() {
            return this.map.values();
        }

        @Override // com.google.common.collect.c, com.google.common.collect.ai
        public Set<Map.Entry<K, V>> entries() {
            return this.map.entrySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.c
        public Collection<Map.Entry<K, V>> createEntries() {
            throw new AssertionError("unreachable");
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.c
        public aj<K> createKeys() {
            return new c(this);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.c
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return this.map.entrySet().iterator();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.c
        public Map<K, Collection<V>> createAsMap() {
            return new a(this);
        }

        @Override // com.google.common.collect.c, com.google.common.collect.ai, java.lang.Object
        public int hashCode() {
            return this.map.hashCode();
        }
    }

    static class c<K, V> extends d<K> {
        final ai<K, V> a;

        c(ai<K, V> aiVar) {
            this.a = aiVar;
        }

        /* renamed from: com.google.common.collect.Multimaps$c$1  reason: invalid class name */
        class AnonymousClass1 extends bc<Map.Entry<K, Collection<V>>, aj.a<K>> {
            AnonymousClass1(Iterator it2) {
                super(it2);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.bc
            public /* bridge */ /* synthetic */ Object a(Object obj) {
                return a((Map.Entry) ((Map.Entry) obj));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: com.google.common.collect.Multimaps$c$1$1  reason: invalid class name */
            public class AnonymousClass1 extends Multisets.a<K> {
                final /* synthetic */ Map.Entry a;

                AnonymousClass1(Map.Entry entry) {
                    this.a = entry;
                }

                @Override // com.google.common.collect.aj.a
                public K getElement() {
                    return (K) this.a.getKey();
                }

                @Override // com.google.common.collect.aj.a
                public int getCount() {
                    return ((Collection) this.a.getValue()).size();
                }
            }

            /* access modifiers changed from: package-private */
            public aj.a<K> a(Map.Entry<K, Collection<V>> entry) {
                return new AnonymousClass1(entry);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.d
        public Iterator<aj.a<K>> entryIterator() {
            return new AnonymousClass1(this.a.asMap().entrySet().iterator());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.d
        public int distinctElements() {
            return this.a.asMap().size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
        public int size() {
            return this.a.size();
        }

        @Override // com.google.common.collect.d, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.aj
        public boolean contains(Object obj) {
            return this.a.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.aj
        public Iterator<K> iterator() {
            return Maps.a(this.a.entries().iterator());
        }

        @Override // com.google.common.collect.aj
        public int count(Object obj) {
            Collection collection = (Collection) Maps.a((Map<?, Object>) this.a.asMap(), obj);
            if (collection == null) {
                return 0;
            }
            return collection.size();
        }

        @Override // com.google.common.collect.d, com.google.common.collect.aj
        public int remove(Object obj, int i) {
            n.a(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            Collection collection = (Collection) Maps.a((Map<?, Object>) this.a.asMap(), obj);
            if (collection == null) {
                return 0;
            }
            int size = collection.size();
            if (i >= size) {
                collection.clear();
            } else {
                Iterator it2 = collection.iterator();
                for (int i2 = 0; i2 < i; i2++) {
                    it2.next();
                    it2.remove();
                }
            }
            return size;
        }

        @Override // com.google.common.collect.d, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.a.clear();
        }

        @Override // com.google.common.collect.d, com.google.common.collect.aj
        public Set<K> elementSet() {
            return this.a.keySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.d
        public Iterator<K> elementIterator() {
            throw new AssertionError("should never be called");
        }
    }

    static abstract class b<K, V> extends AbstractCollection<Map.Entry<K, V>> {
        /* access modifiers changed from: package-private */
        public abstract ai<K, V> a();

        b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return a().size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return a().containsEntry(entry.getKey(), entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return a().remove(entry.getKey(), entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            a().clear();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class a<K, V> extends Maps.m<K, Collection<V>> {
        private final ai<K, V> a;

        a(ai<K, V> aiVar) {
            this.a = (ai) m.a(aiVar);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.a.keySet().size();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Maps.m
        public Set<Map.Entry<K, Collection<V>>> a() {
            return new C0106a();
        }

        /* access modifiers changed from: package-private */
        public void a(Object obj) {
            this.a.keySet().remove(obj);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.Multimaps$a$a  reason: collision with other inner class name */
        public class C0106a extends Maps.b<K, Collection<V>> {
            C0106a() {
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.Maps.b
            public Map<K, Collection<V>> a() {
                return a.this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return Maps.a((Set) a.this.a.keySet(), (g) new AnonymousClass1());
            }

            /* renamed from: com.google.common.collect.Multimaps$a$a$1  reason: invalid class name */
            class AnonymousClass1 implements g<K, Collection<V>> {
                AnonymousClass1() {
                }

                /* renamed from: a */
                public Collection<V> apply(K k) {
                    return a.this.a.get(k);
                }
            }

            @Override // com.google.common.collect.Maps.b, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                a.this.a(((Map.Entry) obj).getKey());
                return true;
            }
        }

        /* renamed from: b */
        public Collection<V> get(Object obj) {
            if (containsKey(obj)) {
                return this.a.get(obj);
            }
            return null;
        }

        /* renamed from: c */
        public Collection<V> remove(Object obj) {
            if (containsKey(obj)) {
                return this.a.removeAll(obj);
            }
            return null;
        }

        @Override // com.google.common.collect.Maps.m, java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.a.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return this.a.isEmpty();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.a.containsKey(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            this.a.clear();
        }
    }

    static boolean a(ai<?, ?> aiVar, Object obj) {
        if (obj == aiVar) {
            return true;
        }
        if (obj instanceof ai) {
            return aiVar.asMap().equals(((ai) obj).asMap());
        }
        return false;
    }
}
