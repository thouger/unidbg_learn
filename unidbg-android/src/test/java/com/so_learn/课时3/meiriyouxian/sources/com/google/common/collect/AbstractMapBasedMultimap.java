package com.google.common.collect;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.c;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/* access modifiers changed from: package-private */
public abstract class AbstractMapBasedMultimap<K, V> extends c<K, V> implements Serializable {
    private static final long serialVersionUID = 2447537837011683357L;
    private transient Map<K, Collection<V>> map;
    private transient int totalSize;

    /* access modifiers changed from: package-private */
    public abstract Collection<V> createCollection();

    static /* synthetic */ int access$208(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i2 = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i2 + 1;
        return i2;
    }

    static /* synthetic */ int access$210(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i2 = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i2 - 1;
        return i2;
    }

    protected AbstractMapBasedMultimap(Map<K, Collection<V>> map) {
        com.google.common.base.m.a(map.isEmpty());
        this.map = map;
    }

    /* access modifiers changed from: package-private */
    public final void setMap(Map<K, Collection<V>> map) {
        this.map = map;
        this.totalSize = 0;
        for (Collection<V> collection : map.values()) {
            com.google.common.base.m.a(!collection.isEmpty());
            this.totalSize += collection.size();
        }
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createUnmodifiableEmptyCollection() {
        return unmodifiableCollectionSubclass(createCollection());
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createCollection(K k2) {
        return createCollection();
    }

    /* access modifiers changed from: package-private */
    public Map<K, Collection<V>> backingMap() {
        return this.map;
    }

    @Override // com.google.common.collect.ai
    public int size() {
        return this.totalSize;
    }

    @Override // com.google.common.collect.ai
    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public boolean put(K k2, V v) {
        Collection<V> collection = this.map.get(k2);
        if (collection == null) {
            Collection<V> createCollection = createCollection(k2);
            if (createCollection.add(v)) {
                this.totalSize++;
                this.map.put(k2, createCollection);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (!collection.add(v)) {
            return false;
        } else {
            this.totalSize++;
            return true;
        }
    }

    private Collection<V> getOrCreateCollection(K k2) {
        Collection<V> collection = this.map.get(k2);
        if (collection != null) {
            return collection;
        }
        Collection<V> createCollection = createCollection(k2);
        this.map.put(k2, createCollection);
        return createCollection;
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai, com.google.common.collect.ah
    public Collection<V> replaceValues(K k2, Iterable<? extends V> iterable) {
        Iterator<? extends V> it2 = iterable.iterator();
        if (!it2.hasNext()) {
            return removeAll(k2);
        }
        Collection<? extends V> orCreateCollection = getOrCreateCollection(k2);
        Collection<V> createCollection = createCollection();
        createCollection.addAll(orCreateCollection);
        this.totalSize -= orCreateCollection.size();
        orCreateCollection.clear();
        while (it2.hasNext()) {
            if (orCreateCollection.add((Object) it2.next())) {
                this.totalSize++;
            }
        }
        return unmodifiableCollectionSubclass(createCollection);
    }

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    public Collection<V> removeAll(Object obj) {
        Collection<V> remove = this.map.remove(obj);
        if (remove == null) {
            return createUnmodifiableEmptyCollection();
        }
        Collection<V> createCollection = createCollection();
        createCollection.addAll(remove);
        this.totalSize -= remove.size();
        remove.clear();
        return unmodifiableCollectionSubclass(createCollection);
    }

    /* access modifiers changed from: package-private */
    public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
        return Collections.unmodifiableCollection(collection);
    }

    @Override // com.google.common.collect.ai
    public void clear() {
        for (Collection<V> collection : this.map.values()) {
            collection.clear();
        }
        this.map.clear();
        this.totalSize = 0;
    }

    @Override // com.google.common.collect.ai, com.google.common.collect.ah
    public Collection<V> get(K k2) {
        Collection<V> collection = this.map.get(k2);
        if (collection == null) {
            collection = createCollection(k2);
        }
        return wrapCollection(k2, collection);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> wrapCollection(K k2, Collection<V> collection) {
        return new i(k2, collection, null);
    }

    /* access modifiers changed from: package-private */
    public final List<V> wrapList(K k2, List<V> list, AbstractMapBasedMultimap<K, V>.i iVar) {
        return list instanceof RandomAccess ? new f(k2, list, iVar) : new j(k2, list, iVar);
    }

    /* access modifiers changed from: package-private */
    public class i extends AbstractCollection<V> {
        final K b;
        Collection<V> c;
        final AbstractMapBasedMultimap<K, V>.i d;
        final Collection<V> e;

        i(K k, Collection<V> collection, AbstractMapBasedMultimap<K, V>.i iVar) {
            Collection<V> collection2;
            this.b = k;
            this.c = collection;
            this.d = iVar;
            if (iVar == null) {
                collection2 = null;
            } else {
                collection2 = iVar.e();
            }
            this.e = collection2;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            Collection<V> collection;
            AbstractMapBasedMultimap<K, V>.i iVar = this.d;
            if (iVar != null) {
                iVar.a();
                if (this.d.e() != this.e) {
                    throw new ConcurrentModificationException();
                }
            } else if (this.c.isEmpty() && (collection = (Collection) AbstractMapBasedMultimap.this.map.get(this.b)) != null) {
                this.c = collection;
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            AbstractMapBasedMultimap<K, V>.i iVar = this.d;
            if (iVar != null) {
                iVar.b();
            } else if (this.c.isEmpty()) {
                AbstractMapBasedMultimap.this.map.remove(this.b);
            }
        }

        /* access modifiers changed from: package-private */
        public K c() {
            return this.b;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.util.Map */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void d() {
            AbstractMapBasedMultimap<K, V>.i iVar = this.d;
            if (iVar != null) {
                iVar.d();
            } else {
                AbstractMapBasedMultimap.this.map.put(this.b, this.c);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            a();
            return this.c.size();
        }

        @Override // java.util.Collection, java.lang.Object
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            a();
            return this.c.equals(obj);
        }

        @Override // java.util.Collection, java.lang.Object
        public int hashCode() {
            a();
            return this.c.hashCode();
        }

        @Override // java.util.AbstractCollection, java.lang.Object
        public String toString() {
            a();
            return this.c.toString();
        }

        /* access modifiers changed from: package-private */
        public Collection<V> e() {
            return this.c;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            a();
            return new a();
        }

        /* access modifiers changed from: package-private */
        public class a implements Iterator<V> {
            final Iterator<V> a;
            final Collection<V> b = i.this.c;

            a() {
                this.a = AbstractMapBasedMultimap.iteratorOrListIterator(i.this.c);
            }

            a(Iterator<V> it2) {
                this.a = it2;
            }

            /* access modifiers changed from: package-private */
            public void a() {
                i.this.a();
                if (i.this.c != this.b) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                a();
                return this.a.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                a();
                return this.a.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.a.remove();
                AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
                i.this.b();
            }

            /* access modifiers changed from: package-private */
            public Iterator<V> b() {
                a();
                return this.a;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(V v) {
            a();
            boolean isEmpty = this.c.isEmpty();
            boolean add = this.c.add(v);
            if (add) {
                AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
                if (isEmpty) {
                    d();
                }
            }
            return add;
        }

        /* access modifiers changed from: package-private */
        public AbstractMapBasedMultimap<K, V>.i f() {
            return this.d;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = this.c.addAll(collection);
            if (addAll) {
                int size2 = this.c.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                if (size == 0) {
                    d();
                }
            }
            return addAll;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            a();
            return this.c.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            a();
            return this.c.containsAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            int size = size();
            if (size != 0) {
                this.c.clear();
                AbstractMapBasedMultimap.this.totalSize -= size;
                b();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            a();
            boolean remove = this.c.remove(obj);
            if (remove) {
                AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
                b();
            }
            return remove;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean removeAll = this.c.removeAll(collection);
            if (removeAll) {
                int size2 = this.c.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                b();
            }
            return removeAll;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            com.google.common.base.m.a(collection);
            int size = size();
            boolean retainAll = this.c.retainAll(collection);
            if (retainAll) {
                int size2 = this.c.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                b();
            }
            return retainAll;
        }
    }

    /* access modifiers changed from: private */
    public static <E> Iterator<E> iteratorOrListIterator(Collection<E> collection) {
        if (collection instanceof List) {
            return ((List) collection).listIterator();
        }
        return collection.iterator();
    }

    class l extends AbstractMapBasedMultimap<K, V>.i implements Set<V> {
        l(K k, Set<V> set) {
            super(k, set, null);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.i, java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean a = Sets.a((Set<?>) ((Set) this.c), collection);
            if (a) {
                int size2 = this.c.size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                b();
            }
            return a;
        }
    }

    /* access modifiers changed from: package-private */
    public class m extends AbstractMapBasedMultimap<K, V>.i implements SortedSet<V> {
        m(K k, SortedSet<V> sortedSet, AbstractMapBasedMultimap<K, V>.i iVar) {
            super(k, sortedSet, iVar);
        }

        /* access modifiers changed from: package-private */
        public SortedSet<V> h() {
            return (SortedSet) e();
        }

        @Override // java.util.SortedSet
        public Comparator<? super V> comparator() {
            return h().comparator();
        }

        @Override // java.util.SortedSet
        public V first() {
            a();
            return h().first();
        }

        @Override // java.util.SortedSet
        public V last() {
            a();
            return h().last();
        }

        @Override // java.util.SortedSet
        public SortedSet<V> headSet(V v) {
            a();
            return new m(c(), h().headSet(v), f() == null ? this : f());
        }

        @Override // java.util.SortedSet
        public SortedSet<V> subSet(V v, V v2) {
            a();
            return new m(c(), h().subSet(v, v2), f() == null ? this : f());
        }

        @Override // java.util.SortedSet
        public SortedSet<V> tailSet(V v) {
            a();
            return new m(c(), h().tailSet(v), f() == null ? this : f());
        }
    }

    class k extends AbstractMapBasedMultimap<K, V>.m implements NavigableSet<V> {
        k(K k, NavigableSet<V> navigableSet, AbstractMapBasedMultimap<K, V>.i iVar) {
            super(k, navigableSet, iVar);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: g */
        public NavigableSet<V> h() {
            return (NavigableSet) super.h();
        }

        @Override // java.util.NavigableSet
        public V lower(V v) {
            return h().lower(v);
        }

        @Override // java.util.NavigableSet
        public V floor(V v) {
            return h().floor(v);
        }

        @Override // java.util.NavigableSet
        public V ceiling(V v) {
            return h().ceiling(v);
        }

        @Override // java.util.NavigableSet
        public V higher(V v) {
            return h().higher(v);
        }

        @Override // java.util.NavigableSet
        public V pollFirst() {
            return (V) Iterators.g(iterator());
        }

        @Override // java.util.NavigableSet
        public V pollLast() {
            return (V) Iterators.g(descendingIterator());
        }

        private NavigableSet<V> a(NavigableSet<V> navigableSet) {
            return new k(this.b, navigableSet, f() == null ? this : f());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> descendingSet() {
            return a(h().descendingSet());
        }

        @Override // java.util.NavigableSet
        public Iterator<V> descendingIterator() {
            return new i.a(h().descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> subSet(V v, boolean z, V v2, boolean z2) {
            return a(h().subSet(v, z, v2, z2));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> headSet(V v, boolean z) {
            return a(h().headSet(v, z));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> tailSet(V v, boolean z) {
            return a(h().tailSet(v, z));
        }
    }

    /* access modifiers changed from: package-private */
    public class j extends AbstractMapBasedMultimap<K, V>.i implements List<V> {
        j(K k, List<V> list, AbstractMapBasedMultimap<K, V>.i iVar) {
            super(k, list, iVar);
        }

        /* access modifiers changed from: package-private */
        public List<V> g() {
            return (List) e();
        }

        @Override // java.util.List
        public boolean addAll(int i, Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = g().addAll(i, collection);
            if (addAll) {
                int size2 = e().size();
                AbstractMapBasedMultimap.this.totalSize += size2 - size;
                if (size == 0) {
                    d();
                }
            }
            return addAll;
        }

        @Override // java.util.List
        public V get(int i) {
            a();
            return g().get(i);
        }

        @Override // java.util.List
        public V set(int i, V v) {
            a();
            return g().set(i, v);
        }

        @Override // java.util.List
        public void add(int i, V v) {
            a();
            boolean isEmpty = e().isEmpty();
            g().add(i, v);
            AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
            if (isEmpty) {
                d();
            }
        }

        @Override // java.util.List
        public V remove(int i) {
            a();
            V remove = g().remove(i);
            AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
            b();
            return remove;
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            a();
            return g().indexOf(obj);
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            a();
            return g().lastIndexOf(obj);
        }

        @Override // java.util.List
        public ListIterator<V> listIterator() {
            a();
            return new a();
        }

        @Override // java.util.List
        public ListIterator<V> listIterator(int i) {
            a();
            return new a(i);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.AbstractMapBasedMultimap */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.List
        public List<V> subList(int i, int i2) {
            a();
            return AbstractMapBasedMultimap.this.wrapList(c(), g().subList(i, i2), f() == null ? this : f());
        }

        private class a extends AbstractMapBasedMultimap<K, V>.i.a implements ListIterator<V> {
            a() {
                super();
            }

            public a(int i) {
                super(j.this.g().listIterator(i));
            }

            private ListIterator<V> c() {
                return (ListIterator) b();
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return c().hasPrevious();
            }

            @Override // java.util.ListIterator
            public V previous() {
                return c().previous();
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return c().nextIndex();
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return c().previousIndex();
            }

            @Override // java.util.ListIterator
            public void set(V v) {
                c().set(v);
            }

            @Override // java.util.ListIterator
            public void add(V v) {
                boolean isEmpty = j.this.isEmpty();
                c().add(v);
                AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
                if (isEmpty) {
                    j.this.d();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public class f extends AbstractMapBasedMultimap<K, V>.j implements RandomAccess {
        f(K k, List<V> list, AbstractMapBasedMultimap<K, V>.i iVar) {
            super(k, list, iVar);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public Set<K> createKeySet() {
        return new c(this.map);
    }

    /* access modifiers changed from: package-private */
    public final Set<K> createMaybeNavigableKeySet() {
        Map<K, Collection<V>> map = this.map;
        if (map instanceof NavigableMap) {
            return new e((NavigableMap) map);
        }
        if (map instanceof SortedMap) {
            return new h((SortedMap) map);
        }
        return new c(map);
    }

    private class c extends Maps.e<K, Collection<V>> {
        c(Map<K, Collection<V>> map) {
            super(map);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.AbstractMapBasedMultimap$c$1  reason: invalid class name */
        public class AnonymousClass1 implements Iterator<K> {
            Map.Entry<K, Collection<V>> a;
            final /* synthetic */ Iterator b;

            AnonymousClass1(Iterator it2) {
                this.b = it2;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.b.hasNext();
            }

            @Override // java.util.Iterator
            public K next() {
                this.a = (Map.Entry) this.b.next();
                return this.a.getKey();
            }

            @Override // java.util.Iterator
            public void remove() {
                n.a(this.a != null);
                Collection<V> value = this.a.getValue();
                this.b.remove();
                AbstractMapBasedMultimap.this.totalSize -= value.size();
                value.clear();
                this.a = null;
            }
        }

        @Override // com.google.common.collect.Maps.e, java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return new AnonymousClass1(c().entrySet().iterator());
        }

        @Override // com.google.common.collect.Maps.e, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int i;
            Collection<V> remove = c().remove(obj);
            if (remove != null) {
                i = remove.size();
                remove.clear();
                AbstractMapBasedMultimap.this.totalSize -= i;
            } else {
                i = 0;
            }
            if (i > 0) {
                return true;
            }
            return false;
        }

        @Override // com.google.common.collect.Maps.e, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Iterators.h(iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return c().keySet().containsAll(collection);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set, java.lang.Object
        public boolean equals(Object obj) {
            return this == obj || c().keySet().equals(obj);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set, java.lang.Object
        public int hashCode() {
            return c().keySet().hashCode();
        }
    }

    /* access modifiers changed from: private */
    public class h extends AbstractMapBasedMultimap<K, V>.c implements SortedSet<K> {
        h(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        /* access modifiers changed from: package-private */
        public SortedMap<K, Collection<V>> b() {
            return (SortedMap) super.c();
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return b().comparator();
        }

        @Override // java.util.SortedSet
        public K first() {
            return b().firstKey();
        }

        @Override // java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> headSet(K k) {
            return new h(b().headMap(k));
        }

        @Override // java.util.SortedSet
        public K last() {
            return b().lastKey();
        }

        @Override // java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> subSet(K k, K k2) {
            return new h(b().subMap(k, k2));
        }

        @Override // java.util.SortedSet, java.util.NavigableSet
        public SortedSet<K> tailSet(K k) {
            return new h(b().tailMap(k));
        }
    }

    /* access modifiers changed from: package-private */
    public class e extends AbstractMapBasedMultimap<K, V>.h implements NavigableSet<K> {
        e(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public NavigableMap<K, Collection<V>> b() {
            return (NavigableMap) super.b();
        }

        @Override // java.util.NavigableSet
        public K lower(K k) {
            return b().lowerKey(k);
        }

        @Override // java.util.NavigableSet
        public K floor(K k) {
            return b().floorKey(k);
        }

        @Override // java.util.NavigableSet
        public K ceiling(K k) {
            return b().ceilingKey(k);
        }

        @Override // java.util.NavigableSet
        public K higher(K k) {
            return b().higherKey(k);
        }

        @Override // java.util.NavigableSet
        public K pollFirst() {
            return (K) Iterators.g(iterator());
        }

        @Override // java.util.NavigableSet
        public K pollLast() {
            return (K) Iterators.g(descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> descendingSet() {
            return new e(b().descendingMap());
        }

        @Override // java.util.NavigableSet
        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        /* renamed from: a */
        public NavigableSet<K> headSet(K k) {
            return headSet(k, false);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> headSet(K k, boolean z) {
            return new e(b().headMap(k, z));
        }

        /* renamed from: a */
        public NavigableSet<K> subSet(K k, K k2) {
            return subSet(k, true, k2, false);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> subSet(K k, boolean z, K k2, boolean z2) {
            return new e(b().subMap(k, z, k2, z2));
        }

        /* renamed from: b */
        public NavigableSet<K> tailSet(K k) {
            return tailSet(k, true);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> tailSet(K k, boolean z) {
            return new e(b().tailMap(k, z));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeValuesForKey(Object obj) {
        Collection collection = (Collection) Maps.c(this.map, obj);
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            this.totalSize -= size;
        }
    }

    private abstract class b<T> implements Iterator<T> {
        final Iterator<Map.Entry<K, Collection<V>>> b;
        K c = null;
        Collection<V> d = null;
        Iterator<V> e = Iterators.c();

        /* access modifiers changed from: package-private */
        public abstract T a(K k, V v);

        b() {
            this.b = AbstractMapBasedMultimap.this.map.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.b.hasNext() || this.e.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (!this.e.hasNext()) {
                Map.Entry<K, Collection<V>> next = this.b.next();
                this.c = next.getKey();
                this.d = next.getValue();
                this.e = this.d.iterator();
            }
            return (T) a(this.c, this.e.next());
        }

        @Override // java.util.Iterator
        public void remove() {
            this.e.remove();
            if (this.d.isEmpty()) {
                this.b.remove();
            }
            AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
        }
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public Collection<V> values() {
        return super.values();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public Collection<V> createValues() {
        return new c.C0108c();
    }

    /* renamed from: com.google.common.collect.AbstractMapBasedMultimap$1  reason: invalid class name */
    class AnonymousClass1 extends AbstractMapBasedMultimap<K, V>.b {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.b
        public V a(K k, V v) {
            return v;
        }

        AnonymousClass1() {
            super();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public Iterator<V> valueIterator() {
        return new AnonymousClass1();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public aj<K> createKeys() {
        return new Multimaps.c(this);
    }

    @Override // com.google.common.collect.c, com.google.common.collect.ai
    public Collection<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public Collection<Map.Entry<K, V>> createEntries() {
        if (this instanceof at) {
            return new c.b();
        }
        return new c.a();
    }

    /* renamed from: com.google.common.collect.AbstractMapBasedMultimap$2  reason: invalid class name */
    class AnonymousClass2 extends AbstractMapBasedMultimap<K, V>.b {
        AnonymousClass2() {
            super();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Map.Entry<K, V> a(K k, V v) {
            return Maps.a((Object) k, (Object) v);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new AnonymousClass2();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.c
    public Map<K, Collection<V>> createAsMap() {
        return new a(this.map);
    }

    /* access modifiers changed from: package-private */
    public final Map<K, Collection<V>> createMaybeNavigableAsMap() {
        Map<K, Collection<V>> map = this.map;
        if (map instanceof NavigableMap) {
            return new d((NavigableMap) map);
        }
        if (map instanceof SortedMap) {
            return new g((SortedMap) map);
        }
        return new a(map);
    }

    /* access modifiers changed from: private */
    public class a extends Maps.m<K, Collection<V>> {
        final transient Map<K, Collection<V>> a;

        a(Map<K, Collection<V>> map) {
            this.a = map;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Maps.m
        public Set<Map.Entry<K, Collection<V>>> a() {
            return new C0104a();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return Maps.b((Map<?, ?>) this.a, obj);
        }

        /* renamed from: a */
        public Collection<V> get(Object obj) {
            Collection<V> collection = (Collection) Maps.a((Map<?, Object>) this.a, obj);
            if (collection == null) {
                return null;
            }
            return AbstractMapBasedMultimap.this.wrapCollection(obj, collection);
        }

        @Override // com.google.common.collect.Maps.m, java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return AbstractMapBasedMultimap.this.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.a.size();
        }

        /* renamed from: b */
        public Collection<V> remove(Object obj) {
            Collection<V> remove = this.a.remove(obj);
            if (remove == null) {
                return null;
            }
            Collection<V> createCollection = AbstractMapBasedMultimap.this.createCollection();
            createCollection.addAll(remove);
            AbstractMapBasedMultimap.this.totalSize -= remove.size();
            remove.clear();
            return createCollection;
        }

        @Override // java.util.AbstractMap, java.util.Map, java.lang.Object
        public boolean equals(Object obj) {
            return this == obj || this.a.equals(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map, java.lang.Object
        public int hashCode() {
            return this.a.hashCode();
        }

        @Override // java.util.AbstractMap, java.lang.Object
        public String toString() {
            return this.a.toString();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            if (this.a == AbstractMapBasedMultimap.this.map) {
                AbstractMapBasedMultimap.this.clear();
            } else {
                Iterators.h(new b());
            }
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, Collection<V>> a(Map.Entry<K, Collection<V>> entry) {
            K key = entry.getKey();
            return Maps.a((Object) key, AbstractMapBasedMultimap.this.wrapCollection(key, entry.getValue()));
        }

        /* renamed from: com.google.common.collect.AbstractMapBasedMultimap$a$a  reason: collision with other inner class name */
        class C0104a extends Maps.b<K, Collection<V>> {
            C0104a() {
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.Maps.b
            public Map<K, Collection<V>> a() {
                return a.this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return new b();
            }

            @Override // com.google.common.collect.Maps.b, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return o.a(a.this.a.entrySet(), obj);
            }

            @Override // com.google.common.collect.Maps.b, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                AbstractMapBasedMultimap.this.removeValuesForKey(((Map.Entry) obj).getKey());
                return true;
            }
        }

        class b implements Iterator<Map.Entry<K, Collection<V>>> {
            final Iterator<Map.Entry<K, Collection<V>>> a = a.this.a.entrySet().iterator();
            Collection<V> b;

            b() {
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.a.hasNext();
            }

            /* renamed from: a */
            public Map.Entry<K, Collection<V>> next() {
                Map.Entry<K, Collection<V>> next = this.a.next();
                this.b = next.getValue();
                return a.this.a((Map.Entry) next);
            }

            @Override // java.util.Iterator
            public void remove() {
                n.a(this.b != null);
                this.a.remove();
                AbstractMapBasedMultimap.this.totalSize -= this.b.size();
                this.b.clear();
                this.b = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public class g extends AbstractMapBasedMultimap<K, V>.a implements SortedMap<K, Collection<V>> {
        SortedSet<K> d;

        g(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        /* access modifiers changed from: package-private */
        public SortedMap<K, Collection<V>> g() {
            return (SortedMap) this.a;
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return g().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return g().firstKey();
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return g().lastKey();
        }

        @Override // java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, Collection<V>> headMap(K k) {
            return new g(g().headMap(k));
        }

        @Override // java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, Collection<V>> subMap(K k, K k2) {
            return new g(g().subMap(k, k2));
        }

        @Override // java.util.SortedMap, java.util.NavigableMap
        public SortedMap<K, Collection<V>> tailMap(K k) {
            return new g(g().tailMap(k));
        }

        /* renamed from: f */
        public SortedSet<K> keySet() {
            SortedSet<K> sortedSet = this.d;
            if (sortedSet != null) {
                return sortedSet;
            }
            SortedSet<K> e = h();
            this.d = e;
            return e;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: e */
        public SortedSet<K> h() {
            return new h(g());
        }
    }

    class d extends AbstractMapBasedMultimap<K, V>.g implements NavigableMap<K, Collection<V>> {
        d(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public NavigableMap<K, Collection<V>> g() {
            return (NavigableMap) super.g();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> lowerEntry(K k) {
            Map.Entry<K, Collection<V>> lowerEntry = g().lowerEntry(k);
            if (lowerEntry == null) {
                return null;
            }
            return a((Map.Entry) lowerEntry);
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            return g().lowerKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> floorEntry(K k) {
            Map.Entry<K, Collection<V>> floorEntry = g().floorEntry(k);
            if (floorEntry == null) {
                return null;
            }
            return a((Map.Entry) floorEntry);
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            return g().floorKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> ceilingEntry(K k) {
            Map.Entry<K, Collection<V>> ceilingEntry = g().ceilingEntry(k);
            if (ceilingEntry == null) {
                return null;
            }
            return a((Map.Entry) ceilingEntry);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            return g().ceilingKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> higherEntry(K k) {
            Map.Entry<K, Collection<V>> higherEntry = g().higherEntry(k);
            if (higherEntry == null) {
                return null;
            }
            return a((Map.Entry) higherEntry);
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            return g().higherKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> firstEntry() {
            Map.Entry<K, Collection<V>> firstEntry = g().firstEntry();
            if (firstEntry == null) {
                return null;
            }
            return a((Map.Entry) firstEntry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> lastEntry() {
            Map.Entry<K, Collection<V>> lastEntry = g().lastEntry();
            if (lastEntry == null) {
                return null;
            }
            return a((Map.Entry) lastEntry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> pollFirstEntry() {
            return a((Iterator) entrySet().iterator());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> pollLastEntry() {
            return a((Iterator) descendingMap().entrySet().iterator());
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, Collection<V>> a(Iterator<Map.Entry<K, Collection<V>>> it2) {
            if (!it2.hasNext()) {
                return null;
            }
            Map.Entry<K, Collection<V>> next = it2.next();
            Collection<V> createCollection = AbstractMapBasedMultimap.this.createCollection();
            createCollection.addAll(next.getValue());
            it2.remove();
            return Maps.a((Object) next.getKey(), AbstractMapBasedMultimap.this.unmodifiableCollectionSubclass(createCollection));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> descendingMap() {
            return new d(g().descendingMap());
        }

        /* renamed from: c */
        public NavigableSet<K> keySet() {
            return (NavigableSet) super.keySet();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public NavigableSet<K> h() {
            return new e(g());
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return keySet();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        /* renamed from: a */
        public NavigableMap<K, Collection<V>> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> subMap(K k, boolean z, K k2, boolean z2) {
            return new d(g().subMap(k, z, k2, z2));
        }

        /* renamed from: c */
        public NavigableMap<K, Collection<V>> headMap(K k) {
            return headMap(k, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> headMap(K k, boolean z) {
            return new d(g().headMap(k, z));
        }

        /* renamed from: d */
        public NavigableMap<K, Collection<V>> tailMap(K k) {
            return tailMap(k, true);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> tailMap(K k, boolean z) {
            return new d(g().tailMap(k, z));
        }
    }
}
