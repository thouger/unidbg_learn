package com.google.common.collect;

import com.google.common.base.Predicates;
import com.google.common.base.m;
import com.google.common.base.n;
import com.google.common.base.q;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.ba;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* access modifiers changed from: package-private */
public class StandardTable<R, C, V> extends i<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    final Map<R, Map<C, V>> backingMap;
    private transient Set<C> columnKeySet;
    private transient StandardTable<R, C, V>.e columnMap;
    final q<? extends Map<C, V>> factory;
    private transient Map<R, Map<C, V>> rowMap;

    StandardTable(Map<R, Map<C, V>> map, q<? extends Map<C, V>> qVar) {
        this.backingMap = map;
        this.factory = qVar;
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public boolean contains(Object obj, Object obj2) {
        return (obj == null || obj2 == null || !super.contains(obj, obj2)) ? false : true;
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public boolean containsColumn(Object obj) {
        if (obj == null) {
            return false;
        }
        for (Map<C, V> map : this.backingMap.values()) {
            if (Maps.b((Map<?, ?>) map, obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public boolean containsRow(Object obj) {
        return obj != null && Maps.b(this.backingMap, obj);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public boolean containsValue(Object obj) {
        return obj != null && super.containsValue(obj);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public V get(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return null;
        }
        return (V) super.get(obj, obj2);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public boolean isEmpty() {
        return this.backingMap.isEmpty();
    }

    @Override // com.google.common.collect.ba
    public int size() {
        int i = 0;
        for (Map<C, V> map : this.backingMap.values()) {
            i += map.size();
        }
        return i;
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public void clear() {
        this.backingMap.clear();
    }

    private Map<C, V> getOrCreate(R r) {
        Map<C, V> map = this.backingMap.get(r);
        if (map != null) {
            return map;
        }
        Map<C, V> map2 = (Map) this.factory.get();
        this.backingMap.put(r, map2);
        return map2;
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public V put(R r, C c2, V v) {
        m.a(r);
        m.a(c2);
        m.a(v);
        return getOrCreate(r).put(c2, v);
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public V remove(Object obj, Object obj2) {
        Map map;
        if (obj == null || obj2 == null || (map = (Map) Maps.a((Map<?, Object>) this.backingMap, obj)) == null) {
            return null;
        }
        V v = (V) map.remove(obj2);
        if (map.isEmpty()) {
            this.backingMap.remove(obj);
        }
        return v;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Map<R, V> removeColumn(Object obj) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<Map.Entry<R, Map<C, V>>> it2 = this.backingMap.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<R, Map<C, V>> next = it2.next();
            V remove = next.getValue().remove(obj);
            if (remove != null) {
                linkedHashMap.put(next.getKey(), remove);
                if (next.getValue().isEmpty()) {
                    it2.remove();
                }
            }
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean containsMapping(Object obj, Object obj2, Object obj3) {
        return obj3 != null && obj3.equals(get(obj, obj2));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean removeMapping(Object obj, Object obj2, Object obj3) {
        if (!containsMapping(obj, obj2, obj3)) {
            return false;
        }
        remove(obj, obj2);
        return true;
    }

    private abstract class h<T> extends Sets.b<T> {
        private h() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return StandardTable.this.backingMap.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            StandardTable.this.backingMap.clear();
        }
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public Set<ba.a<R, C, V>> cellSet() {
        return super.cellSet();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.i
    public Iterator<ba.a<R, C, V>> cellIterator() {
        return new a();
    }

    private class a implements Iterator<ba.a<R, C, V>> {
        final Iterator<Map.Entry<R, Map<C, V>>> a;
        Map.Entry<R, Map<C, V>> b;
        Iterator<Map.Entry<C, V>> c;

        private a() {
            this.a = StandardTable.this.backingMap.entrySet().iterator();
            this.c = Iterators.c();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.a.hasNext() || this.c.hasNext();
        }

        /* renamed from: a */
        public ba.a<R, C, V> next() {
            if (!this.c.hasNext()) {
                this.b = this.a.next();
                this.c = this.b.getValue().entrySet().iterator();
            }
            Map.Entry<C, V> next = this.c.next();
            return Tables.a(this.b.getKey(), next.getKey(), next.getValue());
        }

        @Override // java.util.Iterator
        public void remove() {
            this.c.remove();
            if (this.b.getValue().isEmpty()) {
                this.a.remove();
                this.b = null;
            }
        }
    }

    @Override // com.google.common.collect.ba
    public Map<C, V> row(R r) {
        return new f(r);
    }

    /* access modifiers changed from: package-private */
    public class f extends Maps.d<C, V> {
        final R a;
        Map<C, V> b;

        f(R r) {
            this.a = (R) m.a(r);
        }

        /* access modifiers changed from: package-private */
        public Map<C, V> a() {
            Map<C, V> map = this.b;
            if (map != null && (!map.isEmpty() || !StandardTable.this.backingMap.containsKey(this.a))) {
                return this.b;
            }
            Map<C, V> c = c();
            this.b = c;
            return c;
        }

        /* access modifiers changed from: package-private */
        public Map<C, V> c() {
            return StandardTable.this.backingMap.get(this.a);
        }

        /* access modifiers changed from: package-private */
        public void d() {
            if (a() != null && this.b.isEmpty()) {
                StandardTable.this.backingMap.remove(this.a);
                this.b = null;
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            Map<C, V> a = a();
            return (obj == null || a == null || !Maps.b(a, obj)) ? false : true;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            Map<C, V> a = a();
            if (obj == null || a == null) {
                return null;
            }
            return (V) Maps.a((Map<?, Object>) a, obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(C c, V v) {
            m.a(c);
            m.a(v);
            Map<C, V> map = this.b;
            return (map == null || map.isEmpty()) ? (V) StandardTable.this.put(this.a, c, v) : this.b.put(c, v);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            Map<C, V> a = a();
            if (a == null) {
                return null;
            }
            V v = (V) Maps.c(a, obj);
            d();
            return v;
        }

        @Override // com.google.common.collect.Maps.d, java.util.AbstractMap, java.util.Map
        public void clear() {
            Map<C, V> a = a();
            if (a != null) {
                a.clear();
            }
            d();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            Map<C, V> a = a();
            if (a == null) {
                return 0;
            }
            return a.size();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.d
        public Iterator<Map.Entry<C, V>> b() {
            Map<C, V> a = a();
            if (a == null) {
                return Iterators.c();
            }
            return new AnonymousClass1(a.entrySet().iterator());
        }

        /* renamed from: com.google.common.collect.StandardTable$f$1  reason: invalid class name */
        class AnonymousClass1 implements Iterator<Map.Entry<C, V>> {
            final /* synthetic */ Iterator a;

            AnonymousClass1(Iterator it2) {
                this.a = it2;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.a.hasNext();
            }

            /* renamed from: a */
            public Map.Entry<C, V> next() {
                return f.this.a((Map.Entry) this.a.next());
            }

            @Override // java.util.Iterator
            public void remove() {
                this.a.remove();
                f.this.d();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.collect.StandardTable$f$2  reason: invalid class name */
        public class AnonymousClass2 extends w<C, V> {
            final /* synthetic */ Map.Entry a;

            AnonymousClass2(Map.Entry entry) {
                this.a = entry;
            }

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.w
            /* renamed from: a */
            public Map.Entry<C, V> delegate() {
                return this.a;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.StandardTable$f$2 */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.w, java.util.Map.Entry
            public V setValue(V v) {
                return (V) super.setValue(m.a(v));
            }

            @Override // com.google.common.collect.w, java.util.Map.Entry, java.lang.Object
            public boolean equals(Object obj) {
                return a(obj);
            }
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<C, V> a(Map.Entry<C, V> entry) {
            return new AnonymousClass2(entry);
        }
    }

    @Override // com.google.common.collect.ba
    public Map<R, V> column(C c2) {
        return new b(c2);
    }

    /* access modifiers changed from: private */
    public class b extends Maps.m<R, V> {
        final C a;

        b(C c2) {
            this.a = (C) m.a(c2);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(R r, V v) {
            return (V) StandardTable.this.put(r, this.a, v);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            return (V) StandardTable.this.get(obj, this.a);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return StandardTable.this.contains(obj, this.a);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            return (V) StandardTable.this.remove(obj, this.a);
        }

        /* access modifiers changed from: package-private */
        public boolean a(n<? super Map.Entry<R, V>> nVar) {
            Iterator<Map.Entry<R, Map<C, V>>> it2 = StandardTable.this.backingMap.entrySet().iterator();
            boolean z = false;
            while (it2.hasNext()) {
                Map.Entry<R, Map<C, V>> next = it2.next();
                Map<C, V> value = next.getValue();
                V v = value.get(this.a);
                if (v != null && nVar.apply(Maps.a(next.getKey(), (Object) v))) {
                    value.remove(this.a);
                    z = true;
                    if (value.isEmpty()) {
                        it2.remove();
                    }
                }
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.m
        public Set<Map.Entry<R, V>> a() {
            return new a();
        }

        private class a extends Sets.b<Map.Entry<R, V>> {
            private a() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<R, V>> iterator() {
                return new C0107b();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                int i = 0;
                for (Map<C, V> map : StandardTable.this.backingMap.values()) {
                    if (map.containsKey(b.this.a)) {
                        i++;
                    }
                }
                return i;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean isEmpty() {
                return !StandardTable.this.containsColumn(b.this.a);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public void clear() {
                b.this.a(Predicates.a());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return StandardTable.this.containsMapping(entry.getKey(), b.this.a, entry.getValue());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return StandardTable.this.removeMapping(entry.getKey(), b.this.a, entry.getValue());
            }

            @Override // com.google.common.collect.Sets.b, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                return b.this.a(Predicates.a(Predicates.a((Collection) collection)));
            }
        }

        /* renamed from: com.google.common.collect.StandardTable$b$b  reason: collision with other inner class name */
        private class C0107b extends AbstractIterator<Map.Entry<R, V>> {
            final Iterator<Map.Entry<R, Map<C, V>>> a;

            private C0107b() {
                this.a = StandardTable.this.backingMap.entrySet().iterator();
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public Map.Entry<R, V> a() {
                while (this.a.hasNext()) {
                    Map.Entry<R, Map<C, V>> next = this.a.next();
                    if (next.getValue().containsKey(b.this.a)) {
                        return new a(next);
                    }
                }
                return b();
            }

            /* access modifiers changed from: package-private */
            /* renamed from: com.google.common.collect.StandardTable$b$b$a */
            public class a extends b<R, V> {
                final /* synthetic */ Map.Entry a;

                /* JADX WARN: Incorrect args count in method signature: ()V */
                a(Map.Entry entry) {
                    this.a = entry;
                }

                @Override // com.google.common.collect.b, java.util.Map.Entry
                public R getKey() {
                    return (R) this.a.getKey();
                }

                @Override // com.google.common.collect.b, java.util.Map.Entry
                public V getValue() {
                    return (V) ((Map) this.a.getValue()).get(b.this.a);
                }

                /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.util.Map */
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.google.common.collect.b, java.util.Map.Entry
                public V setValue(V v) {
                    return (V) ((Map) this.a.getValue()).put(b.this.a, m.a(v));
                }
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.m
        public Set<R> h() {
            return new c();
        }

        private class c extends Maps.e<R, V> {
            c() {
                super(b.this);
            }

            @Override // com.google.common.collect.Maps.e, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return StandardTable.this.contains(obj, b.this.a);
            }

            @Override // com.google.common.collect.Maps.e, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                return StandardTable.this.remove(obj, b.this.a) != null;
            }

            @Override // com.google.common.collect.Sets.b, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                return b.this.a(Maps.a(Predicates.a(Predicates.a((Collection) collection))));
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.m
        public Collection<V> i() {
            return new d();
        }

        private class d extends Maps.l<R, V> {
            d() {
                super(b.this);
            }

            @Override // com.google.common.collect.Maps.l, java.util.AbstractCollection, java.util.Collection
            public boolean remove(Object obj) {
                return obj != null && b.this.a(Maps.b(Predicates.a(obj)));
            }

            @Override // com.google.common.collect.Maps.l, java.util.AbstractCollection, java.util.Collection
            public boolean removeAll(Collection<?> collection) {
                return b.this.a(Maps.b(Predicates.a((Collection) collection)));
            }

            @Override // com.google.common.collect.Maps.l, java.util.AbstractCollection, java.util.Collection
            public boolean retainAll(Collection<?> collection) {
                return b.this.a(Maps.b(Predicates.a(Predicates.a((Collection) collection))));
            }
        }
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public Set<R> rowKeySet() {
        return rowMap().keySet();
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public Set<C> columnKeySet() {
        Set<C> set = this.columnKeySet;
        if (set != null) {
            return set;
        }
        d dVar = new d();
        this.columnKeySet = dVar;
        return dVar;
    }

    /* access modifiers changed from: private */
    public class d extends StandardTable<R, C, V>.h {
        private d() {
            super();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<C> iterator() {
            return StandardTable.this.createColumnKeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Iterators.b(iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            boolean z = false;
            if (obj == null) {
                return false;
            }
            Iterator<Map<C, V>> it2 = StandardTable.this.backingMap.values().iterator();
            while (it2.hasNext()) {
                Map<C, V> next = it2.next();
                if (next.keySet().remove(obj)) {
                    z = true;
                    if (next.isEmpty()) {
                        it2.remove();
                    }
                }
            }
            return z;
        }

        @Override // com.google.common.collect.Sets.b, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            m.a(collection);
            Iterator<Map<C, V>> it2 = StandardTable.this.backingMap.values().iterator();
            boolean z = false;
            while (it2.hasNext()) {
                Map<C, V> next = it2.next();
                if (Iterators.a((Iterator<?>) next.keySet().iterator(), collection)) {
                    z = true;
                    if (next.isEmpty()) {
                        it2.remove();
                    }
                }
            }
            return z;
        }

        @Override // com.google.common.collect.Sets.b, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            m.a(collection);
            Iterator<Map<C, V>> it2 = StandardTable.this.backingMap.values().iterator();
            boolean z = false;
            while (it2.hasNext()) {
                Map<C, V> next = it2.next();
                if (next.keySet().retainAll(collection)) {
                    z = true;
                    if (next.isEmpty()) {
                        it2.remove();
                    }
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return StandardTable.this.containsColumn(obj);
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<C> createColumnKeyIterator() {
        return new c();
    }

    /* access modifiers changed from: private */
    public class c extends AbstractIterator<C> {
        final Map<C, V> a;
        final Iterator<Map<C, V>> b;
        Iterator<Map.Entry<C, V>> c;

        private c() {
            this.a = (Map) StandardTable.this.factory.get();
            this.b = StandardTable.this.backingMap.values().iterator();
            this.c = Iterators.a();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractIterator
        public C a() {
            while (true) {
                if (this.c.hasNext()) {
                    Map.Entry<C, V> next = this.c.next();
                    if (!this.a.containsKey(next.getKey())) {
                        this.a.put(next.getKey(), next.getValue());
                        return next.getKey();
                    }
                } else if (!this.b.hasNext()) {
                    return b();
                } else {
                    this.c = this.b.next().entrySet().iterator();
                }
            }
        }
    }

    @Override // com.google.common.collect.i, com.google.common.collect.ba
    public Collection<V> values() {
        return super.values();
    }

    @Override // com.google.common.collect.ba
    public Map<R, Map<C, V>> rowMap() {
        Map<R, Map<C, V>> map = this.rowMap;
        if (map != null) {
            return map;
        }
        Map<R, Map<C, V>> createRowMap = createRowMap();
        this.rowMap = createRowMap;
        return createRowMap;
    }

    /* access modifiers changed from: package-private */
    public Map<R, Map<C, V>> createRowMap() {
        return new g();
    }

    /* access modifiers changed from: package-private */
    public class g extends Maps.m<R, Map<C, V>> {
        g() {
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return StandardTable.this.containsRow(obj);
        }

        /* renamed from: a */
        public Map<C, V> get(Object obj) {
            if (StandardTable.this.containsRow(obj)) {
                return StandardTable.this.row(obj);
            }
            return null;
        }

        /* renamed from: b */
        public Map<C, V> remove(Object obj) {
            if (obj == null) {
                return null;
            }
            return StandardTable.this.backingMap.remove(obj);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Maps.m
        public Set<Map.Entry<R, Map<C, V>>> a() {
            return new a();
        }

        /* access modifiers changed from: package-private */
        public class a extends StandardTable<R, C, V>.h {
            a() {
                super();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<R, Map<C, V>>> iterator() {
                return Maps.a((Set) StandardTable.this.backingMap.keySet(), (com.google.common.base.g) new AnonymousClass1());
            }

            /* renamed from: com.google.common.collect.StandardTable$g$a$1  reason: invalid class name */
            class AnonymousClass1 implements com.google.common.base.g<R, Map<C, V>> {
                AnonymousClass1() {
                }

                /* renamed from: a */
                public Map<C, V> apply(R r) {
                    return StandardTable.this.row(r);
                }
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return StandardTable.this.backingMap.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                if (entry.getKey() == null || !(entry.getValue() instanceof Map) || !o.a(StandardTable.this.backingMap.entrySet(), entry)) {
                    return false;
                }
                return true;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                if (entry.getKey() == null || !(entry.getValue() instanceof Map) || !StandardTable.this.backingMap.entrySet().remove(entry)) {
                    return false;
                }
                return true;
            }
        }
    }

    @Override // com.google.common.collect.ba
    public Map<C, Map<R, V>> columnMap() {
        StandardTable<R, C, V>.e eVar = this.columnMap;
        if (eVar != null) {
            return eVar;
        }
        StandardTable<R, C, V>.e eVar2 = new e();
        this.columnMap = eVar2;
        return eVar2;
    }

    /* access modifiers changed from: private */
    public class e extends Maps.m<C, Map<R, V>> {
        private e() {
        }

        /* renamed from: a */
        public Map<R, V> get(Object obj) {
            if (StandardTable.this.containsColumn(obj)) {
                return StandardTable.this.column(obj);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return StandardTable.this.containsColumn(obj);
        }

        /* renamed from: b */
        public Map<R, V> remove(Object obj) {
            if (StandardTable.this.containsColumn(obj)) {
                return StandardTable.this.removeColumn(obj);
            }
            return null;
        }

        @Override // com.google.common.collect.Maps.m
        public Set<Map.Entry<C, Map<R, V>>> a() {
            return new a();
        }

        @Override // com.google.common.collect.Maps.m, java.util.AbstractMap, java.util.Map
        public Set<C> keySet() {
            return StandardTable.this.columnKeySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.m
        public Collection<Map<R, V>> i() {
            return new b();
        }

        /* access modifiers changed from: package-private */
        public class a extends StandardTable<R, C, V>.h {
            a() {
                super();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<C, Map<R, V>>> iterator() {
                return Maps.a((Set) StandardTable.this.columnKeySet(), (com.google.common.base.g) new AnonymousClass1());
            }

            /* renamed from: com.google.common.collect.StandardTable$e$a$1  reason: invalid class name */
            class AnonymousClass1 implements com.google.common.base.g<C, Map<R, V>> {
                AnonymousClass1() {
                }

                /* renamed from: a */
                public Map<R, V> apply(C c) {
                    return StandardTable.this.column(c);
                }
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return StandardTable.this.columnKeySet().size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                if (!StandardTable.this.containsColumn(entry.getKey())) {
                    return false;
                }
                return e.this.get(entry.getKey()).equals(entry.getValue());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                StandardTable.this.removeColumn(((Map.Entry) obj).getKey());
                return true;
            }

            @Override // com.google.common.collect.Sets.b, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean removeAll(Collection<?> collection) {
                m.a(collection);
                return Sets.a((Set<?>) this, collection.iterator());
            }

            /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: com.google.common.collect.StandardTable */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.Sets.b, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean retainAll(Collection<?> collection) {
                m.a(collection);
                Iterator it2 = Lists.a(StandardTable.this.columnKeySet().iterator()).iterator();
                boolean z = false;
                while (it2.hasNext()) {
                    Object next = it2.next();
                    if (!collection.contains(Maps.a(next, StandardTable.this.column(next)))) {
                        StandardTable.this.removeColumn(next);
                        z = true;
                    }
                }
                return z;
            }
        }

        private class b extends Maps.l<C, Map<R, V>> {
            b() {
                super(e.this);
            }

            @Override // com.google.common.collect.Maps.l, java.util.AbstractCollection, java.util.Collection
            public boolean remove(Object obj) {
                for (Map.Entry<C, Map<R, V>> entry : e.this.entrySet()) {
                    if (entry.getValue().equals(obj)) {
                        StandardTable.this.removeColumn(entry.getKey());
                        return true;
                    }
                }
                return false;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: com.google.common.collect.StandardTable */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.Maps.l, java.util.AbstractCollection, java.util.Collection
            public boolean removeAll(Collection<?> collection) {
                m.a(collection);
                Iterator it2 = Lists.a(StandardTable.this.columnKeySet().iterator()).iterator();
                boolean z = false;
                while (it2.hasNext()) {
                    Object next = it2.next();
                    if (collection.contains(StandardTable.this.column(next))) {
                        StandardTable.this.removeColumn(next);
                        z = true;
                    }
                }
                return z;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: com.google.common.collect.StandardTable */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.Maps.l, java.util.AbstractCollection, java.util.Collection
            public boolean retainAll(Collection<?> collection) {
                m.a(collection);
                Iterator it2 = Lists.a(StandardTable.this.columnKeySet().iterator()).iterator();
                boolean z = false;
                while (it2.hasNext()) {
                    Object next = it2.next();
                    if (!collection.contains(StandardTable.this.column(next))) {
                        StandardTable.this.removeColumn(next);
                        z = true;
                    }
                }
                return z;
            }
        }
    }
}
