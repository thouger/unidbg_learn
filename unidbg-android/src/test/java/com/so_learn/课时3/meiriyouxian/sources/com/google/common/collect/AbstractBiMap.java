package com.google.common.collect;

import com.google.common.base.j;
import com.google.common.base.m;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* access modifiers changed from: package-private */
public abstract class AbstractBiMap<K, V> extends v<K, V> implements k<K, V>, Serializable {
    private static final long serialVersionUID = 0;
    private transient Map<K, V> delegate;
    private transient Set<Map.Entry<K, V>> entrySet;
    transient AbstractBiMap<V, K> inverse;
    private transient Set<K> keySet;
    private transient Set<V> valueSet;

    /* access modifiers changed from: package-private */
    public K checkKey(K k) {
        return k;
    }

    /* access modifiers changed from: package-private */
    public V checkValue(V v) {
        return v;
    }

    /* synthetic */ AbstractBiMap(Map map, AbstractBiMap abstractBiMap, AnonymousClass1 r3) {
        this(map, abstractBiMap);
    }

    AbstractBiMap(Map<K, V> map, Map<V, K> map2) {
        setDelegates(map, map2);
    }

    private AbstractBiMap(Map<K, V> map, AbstractBiMap<V, K> abstractBiMap) {
        this.delegate = map;
        this.inverse = abstractBiMap;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.v, com.google.common.collect.z
    public Map<K, V> delegate() {
        return this.delegate;
    }

    /* access modifiers changed from: package-private */
    public void setDelegates(Map<K, V> map, Map<V, K> map2) {
        boolean z = true;
        m.b(this.delegate == null);
        m.b(this.inverse == null);
        m.a(map.isEmpty());
        m.a(map2.isEmpty());
        if (map == map2) {
            z = false;
        }
        m.a(z);
        this.delegate = map;
        this.inverse = makeInverse(map2);
    }

    /* access modifiers changed from: package-private */
    public AbstractBiMap<V, K> makeInverse(Map<V, K> map) {
        return new Inverse(map, this);
    }

    /* access modifiers changed from: package-private */
    public void setInverse(AbstractBiMap<V, K> abstractBiMap) {
        this.inverse = abstractBiMap;
    }

    @Override // com.google.common.collect.v, java.util.Map
    public boolean containsValue(Object obj) {
        return this.inverse.containsKey(obj);
    }

    @Override // com.google.common.collect.v, java.util.Map
    public V put(K k, V v) {
        return (V) putInBothMaps(k, v, false);
    }

    @Override // com.google.common.collect.k
    public V forcePut(K k, V v) {
        return (V) putInBothMaps(k, v, true);
    }

    private V putInBothMaps(K k, V v, boolean z) {
        checkKey(k);
        checkValue(v);
        boolean containsKey = containsKey(k);
        if (containsKey && j.a(v, get(k))) {
            return v;
        }
        if (z) {
            inverse().remove(v);
        } else {
            m.a(!containsValue(v), "value already present: %s", v);
        }
        V put = this.delegate.put(k, v);
        updateInverseMap(k, containsKey, put, v);
        return put;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateInverseMap(K k, boolean z, V v, V v2) {
        if (z) {
            removeFromInverseMap(v);
        }
        this.inverse.delegate.put(v2, k);
    }

    @Override // com.google.common.collect.v, java.util.Map
    public V remove(Object obj) {
        if (containsKey(obj)) {
            return (V) removeFromBothMaps(obj);
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private V removeFromBothMaps(Object obj) {
        V remove = this.delegate.remove(obj);
        removeFromInverseMap(remove);
        return remove;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeFromInverseMap(V v) {
        this.inverse.delegate.remove(v);
    }

    @Override // com.google.common.collect.v, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.common.collect.v, java.util.Map
    public void clear() {
        this.delegate.clear();
        this.inverse.delegate.clear();
    }

    @Override // com.google.common.collect.k
    public k<V, K> inverse() {
        return this.inverse;
    }

    @Override // com.google.common.collect.v, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        c cVar = new c(this, null);
        this.keySet = cVar;
        return cVar;
    }

    /* access modifiers changed from: private */
    public class c extends ab<K> {
        private c() {
        }

        /* synthetic */ c(AbstractBiMap abstractBiMap, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ab, com.google.common.collect.s, com.google.common.collect.z
        public Set<K> delegate() {
            return AbstractBiMap.this.delegate.keySet();
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public void clear() {
            AbstractBiMap.this.clear();
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!contains(obj)) {
                return false;
            }
            AbstractBiMap.this.removeFromBothMaps(obj);
            return true;
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            return standardRemoveAll(collection);
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            return standardRetainAll(collection);
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return Maps.a(AbstractBiMap.this.entrySet().iterator());
        }
    }

    @Override // com.google.common.collect.v, java.util.Map, com.google.common.collect.k
    public Set<V> values() {
        Set<V> set = this.valueSet;
        if (set != null) {
            return set;
        }
        d dVar = new d(this, null);
        this.valueSet = dVar;
        return dVar;
    }

    /* access modifiers changed from: private */
    public class d extends ab<V> {
        final Set<V> a;

        private d() {
            this.a = AbstractBiMap.this.inverse.keySet();
        }

        /* synthetic */ d(AbstractBiMap abstractBiMap, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ab, com.google.common.collect.s, com.google.common.collect.z
        public Set<V> delegate() {
            return this.a;
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<V> iterator() {
            return Maps.b(AbstractBiMap.this.entrySet().iterator());
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }

        @Override // com.google.common.collect.z, java.lang.Object
        public String toString() {
            return standardToString();
        }
    }

    @Override // com.google.common.collect.v, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        b bVar = new b(this, null);
        this.entrySet = bVar;
        return bVar;
    }

    /* access modifiers changed from: package-private */
    public class a extends w<K, V> {
        private final Map.Entry<K, V> b;

        a(Map.Entry<K, V> entry) {
            this.b = entry;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.w
        /* renamed from: a */
        public Map.Entry<K, V> delegate() {
            return this.b;
        }

        @Override // com.google.common.collect.w, java.util.Map.Entry
        public V setValue(V v) {
            AbstractBiMap.this.checkValue(v);
            m.b(AbstractBiMap.this.entrySet().contains(this), "entry no longer in map");
            if (j.a(v, getValue())) {
                return v;
            }
            m.a(!AbstractBiMap.this.containsValue(v), "value already present: %s", v);
            V value = this.b.setValue(v);
            m.b(j.a(v, AbstractBiMap.this.get(getKey())), "entry no longer in map");
            AbstractBiMap.this.updateInverseMap(getKey(), true, value, v);
            return value;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.AbstractBiMap$1  reason: invalid class name */
    public class AnonymousClass1 implements Iterator<Map.Entry<K, V>> {
        Map.Entry<K, V> a;
        final /* synthetic */ Iterator b;

        AnonymousClass1(Iterator it2) {
            this.b = it2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.b.hasNext();
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            this.a = (Map.Entry) this.b.next();
            return new a(this.a);
        }

        @Override // java.util.Iterator
        public void remove() {
            n.a(this.a != null);
            V value = this.a.getValue();
            this.b.remove();
            AbstractBiMap.this.removeFromInverseMap(value);
            this.a = null;
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> entrySetIterator() {
        return new AnonymousClass1(this.delegate.entrySet().iterator());
    }

    /* access modifiers changed from: private */
    public class b extends ab<Map.Entry<K, V>> {
        final Set<Map.Entry<K, V>> a;

        private b() {
            this.a = AbstractBiMap.this.delegate.entrySet();
        }

        /* synthetic */ b(AbstractBiMap abstractBiMap, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ab, com.google.common.collect.s, com.google.common.collect.z
        public Set<Map.Entry<K, V>> delegate() {
            return this.a;
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public void clear() {
            AbstractBiMap.this.clear();
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!this.a.contains(obj)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            ((AbstractBiMap) AbstractBiMap.this.inverse).delegate.remove(entry.getValue());
            this.a.remove(entry);
            return true;
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return AbstractBiMap.this.entrySetIterator();
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return Maps.a((Collection) delegate(), obj);
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return standardContainsAll(collection);
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            return standardRemoveAll(collection);
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            return standardRetainAll(collection);
        }
    }

    /* access modifiers changed from: package-private */
    public static class Inverse<K, V> extends AbstractBiMap<K, V> {
        private static final long serialVersionUID = 0;

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractBiMap, com.google.common.collect.v, com.google.common.collect.z
        public /* bridge */ /* synthetic */ Object delegate() {
            return AbstractBiMap.super.delegate();
        }

        @Override // com.google.common.collect.AbstractBiMap, com.google.common.collect.v, java.util.Map, com.google.common.collect.k
        public /* bridge */ /* synthetic */ Collection values() {
            return AbstractBiMap.super.values();
        }

        Inverse(Map<K, V> map, AbstractBiMap<V, K> abstractBiMap) {
            super(map, abstractBiMap, null);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractBiMap
        public K checkKey(K k) {
            return (K) this.inverse.checkValue(k);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractBiMap
        public V checkValue(V v) {
            return (V) this.inverse.checkKey(v);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(inverse());
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            setInverse((AbstractBiMap) objectInputStream.readObject());
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return inverse().inverse();
        }
    }
}
