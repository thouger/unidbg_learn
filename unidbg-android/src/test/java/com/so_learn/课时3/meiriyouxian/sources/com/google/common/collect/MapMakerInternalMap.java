package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap.Segment;
import com.google.common.collect.MapMakerInternalMap.g;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;

/* access modifiers changed from: package-private */
public class MapMakerInternalMap<K, V, E extends g<K, V, E>, S extends Segment<K, V, E, S>> extends AbstractMap<K, V> implements Serializable, ConcurrentMap<K, V> {
    static final long CLEANUP_EXECUTOR_DELAY_SECS = 60;
    static final int CONTAINS_VALUE_RETRIES = 3;
    static final int DRAIN_MAX = 16;
    static final int DRAIN_THRESHOLD = 63;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    static final v<Object, Object, c> UNSET_WEAK_VALUE_REFERENCE = new AnonymousClass1();
    private static final long serialVersionUID = 5;
    final int concurrencyLevel;
    final transient h<K, V, E, S> entryHelper;
    transient Set<Map.Entry<K, V>> entrySet;
    final Equivalence<Object> keyEquivalence;
    transient Set<K> keySet;
    final transient int segmentMask;
    final transient int segmentShift;
    final transient Segment<K, V, E, S>[] segments;
    transient Collection<V> values;

    /* access modifiers changed from: package-private */
    public interface g<K, V, E extends g<K, V, E>> {
        K a();

        int b();

        E c();

        V e();
    }

    /* access modifiers changed from: package-private */
    public interface h<K, V, E extends g<K, V, E>, S extends Segment<K, V, E, S>> {
        S a(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i, int i2);

        Strength a();

        E a(S s, E e, E e2);

        E a(S s, K k, int i, E e);

        void a(S s, E e, V v);

        Strength b();
    }

    interface o extends g {
    }

    /* access modifiers changed from: package-private */
    public interface u<K, V, E extends g<K, V, E>> extends g<K, V, E> {
        v<K, V, E> d();
    }

    /* access modifiers changed from: package-private */
    public interface v<K, V, E extends g<K, V, E>> {
        v<K, V, E> a(ReferenceQueue<V> referenceQueue, E e);

        E b();

        void clear();

        V get();
    }

    static int rehash(int i2) {
        int i3 = i2 + ((i2 << 15) ^ -12931);
        int i4 = i3 ^ (i3 >>> 10);
        int i5 = i4 + (i4 << 3);
        int i6 = i5 ^ (i5 >>> 6);
        int i7 = i6 + (i6 << 2) + (i6 << 14);
        return i7 ^ (i7 >>> 16);
    }

    private MapMakerInternalMap(MapMaker mapMaker, h<K, V, E, S> hVar) {
        this.concurrencyLevel = Math.min(mapMaker.c(), 65536);
        this.keyEquivalence = mapMaker.a();
        this.entryHelper = hVar;
        int min = Math.min(mapMaker.b(), 1073741824);
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        int i5 = 1;
        while (i5 < this.concurrencyLevel) {
            i4++;
            i5 <<= 1;
        }
        this.segmentShift = 32 - i4;
        this.segmentMask = i5 - 1;
        this.segments = newSegmentArray(i5);
        int i6 = min / i5;
        while (i3 < (i5 * i6 < min ? i6 + 1 : i6)) {
            i3 <<= 1;
        }
        while (true) {
            Segment<K, V, E, S>[] segmentArr = this.segments;
            if (i2 < segmentArr.length) {
                segmentArr[i2] = createSegment(i3, -1);
                i2++;
            } else {
                return;
            }
        }
    }

    static <K, V> MapMakerInternalMap<K, V, ? extends g<K, V, ?>, ?> create(MapMaker mapMaker) {
        if (mapMaker.e() == Strength.STRONG && mapMaker.f() == Strength.STRONG) {
            return new MapMakerInternalMap<>(mapMaker, m.a.c());
        }
        if (mapMaker.e() == Strength.STRONG && mapMaker.f() == Strength.WEAK) {
            return new MapMakerInternalMap<>(mapMaker, n.a.c());
        }
        if (mapMaker.e() == Strength.WEAK && mapMaker.f() == Strength.STRONG) {
            return new MapMakerInternalMap<>(mapMaker, s.a.c());
        }
        if (mapMaker.e() == Strength.WEAK && mapMaker.f() == Strength.WEAK) {
            return new MapMakerInternalMap<>(mapMaker, t.a.c());
        }
        throw new AssertionError();
    }

    static <K> MapMakerInternalMap<K, MapMaker.Dummy, ? extends g<K, MapMaker.Dummy, ?>, ?> createWithDummyValues(MapMaker mapMaker) {
        if (mapMaker.e() == Strength.STRONG && mapMaker.f() == Strength.STRONG) {
            return new MapMakerInternalMap<>(mapMaker, l.a.c());
        }
        if (mapMaker.e() == Strength.WEAK && mapMaker.f() == Strength.STRONG) {
            return new MapMakerInternalMap<>(mapMaker, r.a.c());
        }
        if (mapMaker.f() == Strength.WEAK) {
            throw new IllegalArgumentException("Map cannot have both weak and dummy values");
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public enum Strength {
        STRONG {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }
        },
        WEAK {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        };

        /* access modifiers changed from: package-private */
        public abstract Equivalence<Object> defaultEquivalence();

        /* synthetic */ Strength(AnonymousClass1 r3) {
            this();
        }
    }

    static abstract class a<K, V, E extends g<K, V, E>> implements g<K, V, E> {
        final K a;
        final int b;
        final E c;

        a(K k, int i, E e) {
            this.a = k;
            this.b = i;
            this.c = e;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public K a() {
            return this.a;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public int b() {
            return this.b;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public E c() {
            return this.c;
        }
    }

    static <K, V, E extends g<K, V, E>> v<K, V, E> unsetWeakValueReference() {
        return (v<K, V, E>) UNSET_WEAK_VALUE_REFERENCE;
    }

    /* access modifiers changed from: package-private */
    public static final class m<K, V> extends a<K, V, m<K, V>> implements o<K, V, m<K, V>> {
        private volatile V d = null;

        m(K k, int i, m<K, V> mVar) {
            super(k, i, mVar);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public V e() {
            return this.d;
        }

        /* access modifiers changed from: package-private */
        public void a(V v) {
            this.d = v;
        }

        /* access modifiers changed from: package-private */
        public m<K, V> a(m<K, V> mVar) {
            m<K, V> mVar2 = new m<>(this.a, this.b, mVar);
            mVar2.d = this.d;
            return mVar2;
        }

        /* access modifiers changed from: package-private */
        public static final class a<K, V> implements h<K, V, m<K, V>, StrongKeyStrongValueSegment<K, V>> {
            private static final a<?, ?> a = new a<>();

            a() {
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public /* bridge */ /* synthetic */ g a(Segment segment, Object obj, int i, g gVar) {
                return a((StrongKeyStrongValueSegment<StrongKeyStrongValueSegment, V>) ((StrongKeyStrongValueSegment) segment), (StrongKeyStrongValueSegment) obj, i, (m<StrongKeyStrongValueSegment, V>) ((m) gVar));
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public /* bridge */ /* synthetic */ void a(Segment segment, g gVar, Object obj) {
                a((StrongKeyStrongValueSegment<K, m>) ((StrongKeyStrongValueSegment) segment), (m<K, m>) ((m) gVar), (m) obj);
            }

            static <K, V> a<K, V> c() {
                return (a<K, V>) a;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public Strength a() {
                return Strength.STRONG;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public Strength b() {
                return Strength.STRONG;
            }

            /* renamed from: b */
            public StrongKeyStrongValueSegment<K, V> a(MapMakerInternalMap<K, V, m<K, V>, StrongKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
                return new StrongKeyStrongValueSegment<>(mapMakerInternalMap, i, i2);
            }

            public m<K, V> a(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, m<K, V> mVar, m<K, V> mVar2) {
                return mVar.a((m) mVar2);
            }

            public void a(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, m<K, V> mVar, V v) {
                mVar.a((m<K, V>) v);
            }

            public m<K, V> a(StrongKeyStrongValueSegment<K, V> strongKeyStrongValueSegment, K k, int i, m<K, V> mVar) {
                return new m<>(k, i, mVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class n<K, V> extends a<K, V, n<K, V>> implements u<K, V, n<K, V>> {
        private volatile v<K, V, n<K, V>> d = MapMakerInternalMap.unsetWeakValueReference();

        n(K k, int i, n<K, V> nVar) {
            super(k, i, nVar);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public V e() {
            return (V) this.d.get();
        }

        /* access modifiers changed from: package-private */
        public void a(V v, ReferenceQueue<V> referenceQueue) {
            v<K, V, n<K, V>> vVar = this.d;
            this.d = new w(referenceQueue, v, this);
            vVar.clear();
        }

        /* access modifiers changed from: package-private */
        public n<K, V> a(ReferenceQueue<V> referenceQueue, n<K, V> nVar) {
            n<K, V> nVar2 = new n<>(this.a, this.b, nVar);
            nVar2.d = this.d.a(referenceQueue, nVar2);
            return nVar2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.u
        public v<K, V, n<K, V>> d() {
            return this.d;
        }

        /* access modifiers changed from: package-private */
        public static final class a<K, V> implements h<K, V, n<K, V>, StrongKeyWeakValueSegment<K, V>> {
            private static final a<?, ?> a = new a<>();

            a() {
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public /* bridge */ /* synthetic */ g a(Segment segment, Object obj, int i, g gVar) {
                return a((StrongKeyWeakValueSegment<StrongKeyWeakValueSegment, V>) ((StrongKeyWeakValueSegment) segment), (StrongKeyWeakValueSegment) obj, i, (n<StrongKeyWeakValueSegment, V>) ((n) gVar));
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public /* bridge */ /* synthetic */ void a(Segment segment, g gVar, Object obj) {
                a((StrongKeyWeakValueSegment<K, n>) ((StrongKeyWeakValueSegment) segment), (n<K, n>) ((n) gVar), (n) obj);
            }

            static <K, V> a<K, V> c() {
                return (a<K, V>) a;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public Strength a() {
                return Strength.STRONG;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public Strength b() {
                return Strength.WEAK;
            }

            /* renamed from: b */
            public StrongKeyWeakValueSegment<K, V> a(MapMakerInternalMap<K, V, n<K, V>, StrongKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
                return new StrongKeyWeakValueSegment<>(mapMakerInternalMap, i, i2);
            }

            public n<K, V> a(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, n<K, V> nVar, n<K, V> nVar2) {
                if (Segment.isCollected(nVar)) {
                    return null;
                }
                return nVar.a(((StrongKeyWeakValueSegment) strongKeyWeakValueSegment).queueForValues, nVar2);
            }

            public void a(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, n<K, V> nVar, V v) {
                nVar.a(v, ((StrongKeyWeakValueSegment) strongKeyWeakValueSegment).queueForValues);
            }

            public n<K, V> a(StrongKeyWeakValueSegment<K, V> strongKeyWeakValueSegment, K k, int i, n<K, V> nVar) {
                return new n<>(k, i, nVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class l<K> extends a<K, MapMaker.Dummy, l<K>> implements o<K, MapMaker.Dummy, l<K>> {
        l(K k, int i, l<K> lVar) {
            super(k, i, lVar);
        }

        /* renamed from: d */
        public MapMaker.Dummy e() {
            return MapMaker.Dummy.VALUE;
        }

        /* access modifiers changed from: package-private */
        public l<K> a(l<K> lVar) {
            return new l<>(this.a, this.b, lVar);
        }

        static final class a<K> implements h<K, MapMaker.Dummy, l<K>, StrongKeyDummyValueSegment<K>> {
            private static final a<?> a = new a<>();

            public void a(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, l<K> lVar, MapMaker.Dummy dummy) {
            }

            a() {
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public /* bridge */ /* synthetic */ g a(Segment segment, Object obj, int i, g gVar) {
                return a((StrongKeyDummyValueSegment<StrongKeyDummyValueSegment>) ((StrongKeyDummyValueSegment) segment), (StrongKeyDummyValueSegment) obj, i, (l<StrongKeyDummyValueSegment>) ((l) gVar));
            }

            static <K> a<K> c() {
                return (a<K>) a;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public Strength a() {
                return Strength.STRONG;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public Strength b() {
                return Strength.STRONG;
            }

            /* renamed from: b */
            public StrongKeyDummyValueSegment<K> a(MapMakerInternalMap<K, MapMaker.Dummy, l<K>, StrongKeyDummyValueSegment<K>> mapMakerInternalMap, int i, int i2) {
                return new StrongKeyDummyValueSegment<>(mapMakerInternalMap, i, i2);
            }

            public l<K> a(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, l<K> lVar, l<K> lVar2) {
                return lVar.a(lVar2);
            }

            public l<K> a(StrongKeyDummyValueSegment<K> strongKeyDummyValueSegment, K k, int i, l<K> lVar) {
                return new l<>(k, i, lVar);
            }
        }
    }

    static abstract class b<K, V, E extends g<K, V, E>> extends WeakReference<K> implements g<K, V, E> {
        final int a;
        final E b;

        b(ReferenceQueue<K> referenceQueue, K k, int i, E e) {
            super(k, referenceQueue);
            this.a = i;
            this.b = e;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public K a() {
            return (K) get();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public int b() {
            return this.a;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public E c() {
            return this.b;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class r<K> extends b<K, MapMaker.Dummy, r<K>> implements o<K, MapMaker.Dummy, r<K>> {
        r(ReferenceQueue<K> referenceQueue, K k, int i, r<K> rVar) {
            super(referenceQueue, k, i, rVar);
        }

        /* renamed from: d */
        public MapMaker.Dummy e() {
            return MapMaker.Dummy.VALUE;
        }

        /* access modifiers changed from: package-private */
        public r<K> a(ReferenceQueue<K> referenceQueue, r<K> rVar) {
            return new r<>(referenceQueue, a(), this.a, rVar);
        }

        static final class a<K> implements h<K, MapMaker.Dummy, r<K>, WeakKeyDummyValueSegment<K>> {
            private static final a<?> a = new a<>();

            public void a(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, r<K> rVar, MapMaker.Dummy dummy) {
            }

            a() {
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public /* bridge */ /* synthetic */ g a(Segment segment, Object obj, int i, g gVar) {
                return a((WeakKeyDummyValueSegment<WeakKeyDummyValueSegment>) ((WeakKeyDummyValueSegment) segment), (WeakKeyDummyValueSegment) obj, i, (r<WeakKeyDummyValueSegment>) ((r) gVar));
            }

            static <K> a<K> c() {
                return (a<K>) a;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public Strength a() {
                return Strength.WEAK;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public Strength b() {
                return Strength.STRONG;
            }

            /* renamed from: b */
            public WeakKeyDummyValueSegment<K> a(MapMakerInternalMap<K, MapMaker.Dummy, r<K>, WeakKeyDummyValueSegment<K>> mapMakerInternalMap, int i, int i2) {
                return new WeakKeyDummyValueSegment<>(mapMakerInternalMap, i, i2);
            }

            public r<K> a(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, r<K> rVar, r<K> rVar2) {
                if (rVar.a() == null) {
                    return null;
                }
                return rVar.a(((WeakKeyDummyValueSegment) weakKeyDummyValueSegment).queueForKeys, rVar2);
            }

            public r<K> a(WeakKeyDummyValueSegment<K> weakKeyDummyValueSegment, K k, int i, r<K> rVar) {
                return new r<>(((WeakKeyDummyValueSegment) weakKeyDummyValueSegment).queueForKeys, k, i, rVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class s<K, V> extends b<K, V, s<K, V>> implements o<K, V, s<K, V>> {
        private volatile V c = null;

        s(ReferenceQueue<K> referenceQueue, K k, int i, s<K, V> sVar) {
            super(referenceQueue, k, i, sVar);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public V e() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public void a(V v) {
            this.c = v;
        }

        /* access modifiers changed from: package-private */
        public s<K, V> a(ReferenceQueue<K> referenceQueue, s<K, V> sVar) {
            s<K, V> sVar2 = new s<>(referenceQueue, a(), this.a, sVar);
            sVar2.a(this.c);
            return sVar2;
        }

        /* access modifiers changed from: package-private */
        public static final class a<K, V> implements h<K, V, s<K, V>, WeakKeyStrongValueSegment<K, V>> {
            private static final a<?, ?> a = new a<>();

            a() {
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public /* bridge */ /* synthetic */ g a(Segment segment, Object obj, int i, g gVar) {
                return a((WeakKeyStrongValueSegment<WeakKeyStrongValueSegment, V>) ((WeakKeyStrongValueSegment) segment), (WeakKeyStrongValueSegment) obj, i, (s<WeakKeyStrongValueSegment, V>) ((s) gVar));
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public /* bridge */ /* synthetic */ void a(Segment segment, g gVar, Object obj) {
                a((WeakKeyStrongValueSegment<K, s>) ((WeakKeyStrongValueSegment) segment), (s<K, s>) ((s) gVar), (s) obj);
            }

            static <K, V> a<K, V> c() {
                return (a<K, V>) a;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public Strength a() {
                return Strength.WEAK;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public Strength b() {
                return Strength.STRONG;
            }

            /* renamed from: b */
            public WeakKeyStrongValueSegment<K, V> a(MapMakerInternalMap<K, V, s<K, V>, WeakKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
                return new WeakKeyStrongValueSegment<>(mapMakerInternalMap, i, i2);
            }

            public s<K, V> a(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, s<K, V> sVar, s<K, V> sVar2) {
                if (sVar.a() == null) {
                    return null;
                }
                return sVar.a(((WeakKeyStrongValueSegment) weakKeyStrongValueSegment).queueForKeys, sVar2);
            }

            public void a(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, s<K, V> sVar, V v) {
                sVar.a(v);
            }

            public s<K, V> a(WeakKeyStrongValueSegment<K, V> weakKeyStrongValueSegment, K k, int i, s<K, V> sVar) {
                return new s<>(((WeakKeyStrongValueSegment) weakKeyStrongValueSegment).queueForKeys, k, i, sVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class t<K, V> extends b<K, V, t<K, V>> implements u<K, V, t<K, V>> {
        private volatile v<K, V, t<K, V>> c = MapMakerInternalMap.unsetWeakValueReference();

        t(ReferenceQueue<K> referenceQueue, K k, int i, t<K, V> tVar) {
            super(referenceQueue, k, i, tVar);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public V e() {
            return (V) this.c.get();
        }

        /* access modifiers changed from: package-private */
        public t<K, V> a(ReferenceQueue<K> referenceQueue, ReferenceQueue<V> referenceQueue2, t<K, V> tVar) {
            t<K, V> tVar2 = new t<>(referenceQueue, a(), this.a, tVar);
            tVar2.c = this.c.a(referenceQueue2, tVar2);
            return tVar2;
        }

        /* access modifiers changed from: package-private */
        public void a(V v, ReferenceQueue<V> referenceQueue) {
            v<K, V, t<K, V>> vVar = this.c;
            this.c = new w(referenceQueue, v, this);
            vVar.clear();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.u
        public v<K, V, t<K, V>> d() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public static final class a<K, V> implements h<K, V, t<K, V>, WeakKeyWeakValueSegment<K, V>> {
            private static final a<?, ?> a = new a<>();

            a() {
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public /* bridge */ /* synthetic */ g a(Segment segment, Object obj, int i, g gVar) {
                return a((WeakKeyWeakValueSegment<WeakKeyWeakValueSegment, V>) ((WeakKeyWeakValueSegment) segment), (WeakKeyWeakValueSegment) obj, i, (t<WeakKeyWeakValueSegment, V>) ((t) gVar));
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public /* bridge */ /* synthetic */ void a(Segment segment, g gVar, Object obj) {
                a((WeakKeyWeakValueSegment<K, t>) ((WeakKeyWeakValueSegment) segment), (t<K, t>) ((t) gVar), (t) obj);
            }

            static <K, V> a<K, V> c() {
                return (a<K, V>) a;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public Strength a() {
                return Strength.WEAK;
            }

            @Override // com.google.common.collect.MapMakerInternalMap.h
            public Strength b() {
                return Strength.WEAK;
            }

            /* renamed from: b */
            public WeakKeyWeakValueSegment<K, V> a(MapMakerInternalMap<K, V, t<K, V>, WeakKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
                return new WeakKeyWeakValueSegment<>(mapMakerInternalMap, i, i2);
            }

            public t<K, V> a(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, t<K, V> tVar, t<K, V> tVar2) {
                if (tVar.a() != null && !Segment.isCollected(tVar)) {
                    return tVar.a(((WeakKeyWeakValueSegment) weakKeyWeakValueSegment).queueForKeys, ((WeakKeyWeakValueSegment) weakKeyWeakValueSegment).queueForValues, tVar2);
                }
                return null;
            }

            public void a(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, t<K, V> tVar, V v) {
                tVar.a(v, ((WeakKeyWeakValueSegment) weakKeyWeakValueSegment).queueForValues);
            }

            public t<K, V> a(WeakKeyWeakValueSegment<K, V> weakKeyWeakValueSegment, K k, int i, t<K, V> tVar) {
                return new t<>(((WeakKeyWeakValueSegment) weakKeyWeakValueSegment).queueForKeys, k, i, tVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class c implements g<Object, Object, c> {
        private c() {
            throw new AssertionError();
        }

        /* renamed from: d */
        public c c() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public int b() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public Object a() {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.g
        public Object e() {
            throw new AssertionError();
        }
    }

    /* renamed from: com.google.common.collect.MapMakerInternalMap$1  reason: invalid class name */
    static class AnonymousClass1 implements v<Object, Object, c> {
        /* renamed from: a */
        public c b() {
            return null;
        }

        public v<Object, Object, c> a(ReferenceQueue<Object> referenceQueue, c cVar) {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.v
        public void clear() {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.v
        public Object get() {
            return null;
        }

        AnonymousClass1() {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.v
        public /* bridge */ /* synthetic */ v a(ReferenceQueue referenceQueue, g gVar) {
            return a((ReferenceQueue<Object>) referenceQueue, (c) gVar);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class w<K, V, E extends g<K, V, E>> extends WeakReference<V> implements v<K, V, E> {
        final E a;

        w(ReferenceQueue<V> referenceQueue, V v, E e) {
            super(v, referenceQueue);
            this.a = e;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.v
        public E b() {
            return this.a;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.v
        public v<K, V, E> a(ReferenceQueue<V> referenceQueue, E e) {
            return new w(referenceQueue, get(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public E copyEntry(E e2, E e3) {
        return (E) segmentFor(e2.b()).copyEntry(e2, e3);
    }

    /* access modifiers changed from: package-private */
    public int hash(Object obj) {
        return rehash(this.keyEquivalence.hash(obj));
    }

    /* access modifiers changed from: package-private */
    public void reclaimValue(v<K, V, E> vVar) {
        g b2 = vVar.b();
        int b3 = b2.b();
        segmentFor(b3).reclaimValue(b2.a(), b3, vVar);
    }

    /* access modifiers changed from: package-private */
    public void reclaimKey(E e2) {
        int b2 = e2.b();
        segmentFor(b2).reclaimKey(e2, b2);
    }

    /* access modifiers changed from: package-private */
    public boolean isLiveForTesting(g<K, V, ?> gVar) {
        return segmentFor(gVar.b()).getLiveValueForTesting(gVar) != null;
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V, E, S> segmentFor(int i2) {
        return this.segments[(i2 >>> this.segmentShift) & this.segmentMask];
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V, E, S> createSegment(int i2, int i3) {
        return this.entryHelper.a((MapMakerInternalMap<K, V, E, Segment<K, V, E, S>>) this, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public V getLiveValue(E e2) {
        if (e2.a() == null) {
            return null;
        }
        return (V) e2.e();
    }

    /* access modifiers changed from: package-private */
    public final Segment<K, V, E, S>[] newSegmentArray(int i2) {
        return new Segment[i2];
    }

    /* access modifiers changed from: package-private */
    public static abstract class Segment<K, V, E extends g<K, V, E>, S extends Segment<K, V, E, S>> extends ReentrantLock {
        volatile int count;
        final MapMakerInternalMap<K, V, E, S> map;
        final int maxSegmentSize;
        int modCount;
        final AtomicInteger readCount = new AtomicInteger();
        volatile AtomicReferenceArray<E> table;
        int threshold;

        /* access modifiers changed from: package-private */
        public abstract E castForTesting(g<K, V, ?> gVar);

        /* access modifiers changed from: package-private */
        public void maybeClearReferenceQueues() {
        }

        /* access modifiers changed from: package-private */
        public void maybeDrainReferenceQueues() {
        }

        /* access modifiers changed from: package-private */
        public abstract S self();

        Segment(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i, int i2) {
            this.map = mapMakerInternalMap;
            this.maxSegmentSize = i2;
            initTable(newEntryArray(i));
        }

        /* access modifiers changed from: package-private */
        public void setValue(E e, V v) {
            this.map.entryHelper.a((h<K, V, E, S>) self(), (Segment) e, (E) v);
        }

        /* access modifiers changed from: package-private */
        public E copyEntry(E e, E e2) {
            return (E) this.map.entryHelper.a((h<K, V, E, S>) self(), (g) e, (g) e2);
        }

        /* access modifiers changed from: package-private */
        public AtomicReferenceArray<E> newEntryArray(int i) {
            return new AtomicReferenceArray<>(i);
        }

        /* access modifiers changed from: package-private */
        public void initTable(AtomicReferenceArray<E> atomicReferenceArray) {
            this.threshold = (atomicReferenceArray.length() * 3) / 4;
            int i = this.threshold;
            if (i == this.maxSegmentSize) {
                this.threshold = i + 1;
            }
            this.table = atomicReferenceArray;
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public v<K, V, E> getWeakValueReferenceForTesting(g<K, V, ?> gVar) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public v<K, V, E> newWeakValueReferenceForTesting(g<K, V, ?> gVar, V v) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void setWeakValueReferenceForTesting(g<K, V, ?> gVar, v<K, V, ? extends g<K, V, ?>> vVar) {
            throw new AssertionError();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends com.google.common.collect.MapMakerInternalMap$g<K, V, E>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void setTableEntryForTesting(int i, g<K, V, ?> gVar) {
            this.table.set(i, castForTesting(gVar));
        }

        /* access modifiers changed from: package-private */
        public E copyForTesting(g<K, V, ?> gVar, g<K, V, ?> gVar2) {
            return (E) this.map.entryHelper.a((h<K, V, E, S>) self(), castForTesting(gVar), castForTesting(gVar2));
        }

        /* access modifiers changed from: package-private */
        public void setValueForTesting(g<K, V, ?> gVar, V v) {
            this.map.entryHelper.a((h<K, V, E, S>) self(), (Segment) castForTesting(gVar), (g) v);
        }

        /* access modifiers changed from: package-private */
        public E newEntryForTesting(K k, int i, g<K, V, ?> gVar) {
            return (E) this.map.entryHelper.a(self(), k, i, castForTesting(gVar));
        }

        /* access modifiers changed from: package-private */
        public boolean removeTableEntryForTesting(g<K, V, ?> gVar) {
            return removeEntryForTesting(castForTesting(gVar));
        }

        /* access modifiers changed from: package-private */
        public E removeFromChainForTesting(g<K, V, ?> gVar, g<K, V, ?> gVar2) {
            return (E) removeFromChain(castForTesting(gVar), castForTesting(gVar2));
        }

        /* access modifiers changed from: package-private */
        public V getLiveValueForTesting(g<K, V, ?> gVar) {
            return (V) getLiveValue(castForTesting(gVar));
        }

        /* access modifiers changed from: package-private */
        public void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    maybeDrainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void drainKeyReferenceQueue(ReferenceQueue<K> referenceQueue) {
            int i = 0;
            do {
                Reference<? extends K> poll = referenceQueue.poll();
                if (poll != null) {
                    this.map.reclaimKey((g) poll);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        public void drainValueReferenceQueue(ReferenceQueue<V> referenceQueue) {
            int i = 0;
            do {
                Reference<? extends V> poll = referenceQueue.poll();
                if (poll != null) {
                    this.map.reclaimValue((v) poll);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        public <T> void clearReferenceQueue(ReferenceQueue<T> referenceQueue) {
            do {
            } while (referenceQueue.poll() != null);
        }

        /* access modifiers changed from: package-private */
        public E getFirst(int i) {
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            return atomicReferenceArray.get(i & (atomicReferenceArray.length() - 1));
        }

        /* access modifiers changed from: package-private */
        public E getEntry(Object obj, int i) {
            if (this.count == 0) {
                return null;
            }
            for (E e = (E) getFirst(i); e != null; e = (E) e.c()) {
                if (e.b() == i) {
                    Object a = e.a();
                    if (a == null) {
                        tryDrainReferenceQueues();
                    } else if (this.map.keyEquivalence.equivalent(obj, a)) {
                        return e;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public E getLiveEntry(Object obj, int i) {
            return (E) getEntry(obj, i);
        }

        /* access modifiers changed from: package-private */
        public V get(Object obj, int i) {
            try {
                g liveEntry = getLiveEntry(obj, i);
                if (liveEntry == null) {
                    return null;
                }
                V v = (V) liveEntry.e();
                if (v == null) {
                    tryDrainReferenceQueues();
                }
                postReadCleanup();
                return v;
            } finally {
                postReadCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean containsKey(Object obj, int i) {
            try {
                boolean z = false;
                if (this.count != 0) {
                    g liveEntry = getLiveEntry(obj, i);
                    if (!(liveEntry == null || liveEntry.e() == null)) {
                        z = true;
                    }
                    return z;
                }
                postReadCleanup();
                return false;
            } finally {
                postReadCleanup();
            }
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        public boolean containsValue(Object obj) {
            try {
                if (this.count != 0) {
                    AtomicReferenceArray<E> atomicReferenceArray = this.table;
                    int length = atomicReferenceArray.length();
                    for (int i = 0; i < length; i++) {
                        for (E e = atomicReferenceArray.get(i); e != null; e = e.c()) {
                            Object liveValue = getLiveValue(e);
                            if (liveValue != null) {
                                if (this.map.valueEquivalence().equivalent(obj, liveValue)) {
                                    postReadCleanup();
                                    return true;
                                }
                            }
                        }
                    }
                }
                postReadCleanup();
                return false;
            } catch (Throwable th) {
                postReadCleanup();
                throw th;
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends com.google.common.collect.MapMakerInternalMap$g<K, V, E>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public V put(K k, int i, V v, boolean z) {
            lock();
            try {
                preWriteCleanup();
                int i2 = this.count + 1;
                if (i2 > this.threshold) {
                    expand();
                    i2 = this.count + 1;
                }
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                g gVar = (g) atomicReferenceArray.get(length);
                for (g gVar2 = gVar; gVar2 != null; gVar2 = gVar2.c()) {
                    Object a = gVar2.a();
                    if (gVar2.b() == i && a != null && this.map.keyEquivalence.equivalent(k, a)) {
                        V v2 = (V) gVar2.e();
                        if (v2 == null) {
                            this.modCount++;
                            setValue(gVar2, v);
                            this.count = this.count;
                            return null;
                        } else if (z) {
                            unlock();
                            return v2;
                        } else {
                            this.modCount++;
                            setValue(gVar2, v);
                            unlock();
                            return v2;
                        }
                    }
                }
                this.modCount++;
                g a2 = this.map.entryHelper.a(self(), k, i, gVar);
                setValue(a2, v);
                atomicReferenceArray.set(length, a2);
                this.count = i2;
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r7v0, types: [com.google.common.collect.MapMakerInternalMap$g] */
        /* JADX WARN: Type inference failed for: r7v4, types: [com.google.common.collect.MapMakerInternalMap$g] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void expand() {
            /*
                r11 = this;
                java.util.concurrent.atomic.AtomicReferenceArray<E extends com.google.common.collect.MapMakerInternalMap$g<K, V, E>> r0 = r11.table
                int r1 = r0.length()
                r2 = 1073741824(0x40000000, float:2.0)
                if (r1 < r2) goto L_0x000b
                return
            L_0x000b:
                int r2 = r11.count
                int r3 = r1 << 1
                java.util.concurrent.atomic.AtomicReferenceArray r3 = r11.newEntryArray(r3)
                int r4 = r3.length()
                int r4 = r4 * 3
                int r4 = r4 / 4
                r11.threshold = r4
                int r4 = r3.length()
                int r4 = r4 + -1
                r5 = 0
            L_0x0024:
                if (r5 >= r1) goto L_0x0072
                java.lang.Object r6 = r0.get(r5)
                com.google.common.collect.MapMakerInternalMap$g r6 = (com.google.common.collect.MapMakerInternalMap.g) r6
                if (r6 == 0) goto L_0x006f
                com.google.common.collect.MapMakerInternalMap$g r7 = r6.c()
                int r8 = r6.b()
                r8 = r8 & r4
                if (r7 != 0) goto L_0x003d
                r3.set(r8, r6)
                goto L_0x006f
            L_0x003d:
                r9 = r6
            L_0x003e:
                if (r7 == 0) goto L_0x004e
                int r10 = r7.b()
                r10 = r10 & r4
                if (r10 == r8) goto L_0x0049
                r9 = r7
                r8 = r10
            L_0x0049:
                com.google.common.collect.MapMakerInternalMap$g r7 = r7.c()
                goto L_0x003e
            L_0x004e:
                r3.set(r8, r9)
            L_0x0051:
                if (r6 == r9) goto L_0x006f
                int r7 = r6.b()
                r7 = r7 & r4
                java.lang.Object r8 = r3.get(r7)
                com.google.common.collect.MapMakerInternalMap$g r8 = (com.google.common.collect.MapMakerInternalMap.g) r8
                com.google.common.collect.MapMakerInternalMap$g r8 = r11.copyEntry(r6, r8)
                if (r8 == 0) goto L_0x0068
                r3.set(r7, r8)
                goto L_0x006a
            L_0x0068:
                int r2 = r2 + -1
            L_0x006a:
                com.google.common.collect.MapMakerInternalMap$g r6 = r6.c()
                goto L_0x0051
            L_0x006f:
                int r5 = r5 + 1
                goto L_0x0024
            L_0x0072:
                r11.table = r3
                r11.count = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.Segment.expand():void");
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends com.google.common.collect.MapMakerInternalMap$g<K, V, E>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public boolean replace(K k, int i, V v, V v2) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                g gVar = (g) atomicReferenceArray.get(length);
                for (g gVar2 = gVar; gVar2 != null; gVar2 = gVar2.c()) {
                    Object a = gVar2.a();
                    if (gVar2.b() == i && a != null && this.map.keyEquivalence.equivalent(k, a)) {
                        Object e = gVar2.e();
                        if (e == null) {
                            if (isCollected(gVar2)) {
                                int i2 = this.count;
                                this.modCount++;
                                atomicReferenceArray.set(length, removeFromChain(gVar, gVar2));
                                this.count--;
                            }
                            return false;
                        } else if (this.map.valueEquivalence().equivalent(v, e)) {
                            this.modCount++;
                            setValue(gVar2, v2);
                            unlock();
                            return true;
                        } else {
                            unlock();
                            return false;
                        }
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends com.google.common.collect.MapMakerInternalMap$g<K, V, E>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public V replace(K k, int i, V v) {
            lock();
            try {
                preWriteCleanup();
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                g gVar = (g) atomicReferenceArray.get(length);
                for (g gVar2 = gVar; gVar2 != null; gVar2 = gVar2.c()) {
                    Object a = gVar2.a();
                    if (gVar2.b() == i && a != null && this.map.keyEquivalence.equivalent(k, a)) {
                        V v2 = (V) gVar2.e();
                        if (v2 == null) {
                            if (isCollected(gVar2)) {
                                int i2 = this.count;
                                this.modCount++;
                                atomicReferenceArray.set(length, removeFromChain(gVar, gVar2));
                                this.count--;
                            }
                            return null;
                        }
                        this.modCount++;
                        setValue(gVar2, v);
                        unlock();
                        return v2;
                    }
                }
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends com.google.common.collect.MapMakerInternalMap$g<K, V, E>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public V remove(Object obj, int i) {
            lock();
            try {
                preWriteCleanup();
                int i2 = this.count;
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                g gVar = (g) atomicReferenceArray.get(length);
                for (g gVar2 = gVar; gVar2 != null; gVar2 = gVar2.c()) {
                    Object a = gVar2.a();
                    if (gVar2.b() == i && a != null && this.map.keyEquivalence.equivalent(obj, a)) {
                        V v = (V) gVar2.e();
                        if (v == null) {
                            if (!isCollected(gVar2)) {
                                unlock();
                                return null;
                            }
                        }
                        this.modCount++;
                        atomicReferenceArray.set(length, removeFromChain(gVar, gVar2));
                        this.count--;
                        return v;
                    }
                }
                unlock();
                return null;
            } finally {
                unlock();
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends com.google.common.collect.MapMakerInternalMap$g<K, V, E>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public boolean remove(Object obj, int i, Object obj2) {
            lock();
            try {
                preWriteCleanup();
                int i2 = this.count;
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                g gVar = (g) atomicReferenceArray.get(length);
                g gVar2 = gVar;
                while (true) {
                    boolean z = false;
                    if (gVar2 != null) {
                        Object a = gVar2.a();
                        if (gVar2.b() != i || a == null || !this.map.keyEquivalence.equivalent(obj, a)) {
                            gVar2 = gVar2.c();
                        } else {
                            if (this.map.valueEquivalence().equivalent(obj2, gVar2.e())) {
                                z = true;
                            } else if (!isCollected(gVar2)) {
                                unlock();
                                return false;
                            }
                            this.modCount++;
                            atomicReferenceArray.set(length, removeFromChain(gVar, gVar2));
                            this.count--;
                            return z;
                        }
                    } else {
                        unlock();
                        return false;
                    }
                }
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            if (this.count != 0) {
                lock();
                try {
                    AtomicReferenceArray<E> atomicReferenceArray = this.table;
                    for (int i = 0; i < atomicReferenceArray.length(); i++) {
                        atomicReferenceArray.set(i, null);
                    }
                    maybeClearReferenceQueues();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public E removeFromChain(E e, E e2) {
            int i = this.count;
            E e3 = (E) e2.c();
            while (e != e2) {
                Object copyEntry = copyEntry(e, e3);
                if (copyEntry != null) {
                    e3 = (E) copyEntry;
                } else {
                    i--;
                }
                e = (E) e.c();
            }
            this.count = i;
            return e3;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends com.google.common.collect.MapMakerInternalMap$g<K, V, E>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public boolean reclaimKey(E e, int i) {
            lock();
            try {
                int i2 = this.count;
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                g gVar = (g) atomicReferenceArray.get(length);
                for (g gVar2 = gVar; gVar2 != null; gVar2 = gVar2.c()) {
                    if (gVar2 == e) {
                        this.modCount++;
                        atomicReferenceArray.set(length, removeFromChain(gVar, gVar2));
                        this.count--;
                        return true;
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends com.google.common.collect.MapMakerInternalMap$g<K, V, E>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public boolean reclaimValue(K k, int i, v<K, V, E> vVar) {
            lock();
            try {
                int i2 = this.count;
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                g gVar = (g) atomicReferenceArray.get(length);
                for (g gVar2 = gVar; gVar2 != null; gVar2 = gVar2.c()) {
                    Object a = gVar2.a();
                    if (gVar2.b() == i && a != null && this.map.keyEquivalence.equivalent(k, a)) {
                        if (((u) gVar2).d() == vVar) {
                            this.modCount++;
                            atomicReferenceArray.set(length, removeFromChain(gVar, gVar2));
                            this.count--;
                            return true;
                        } else {
                            unlock();
                            return false;
                        }
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends com.google.common.collect.MapMakerInternalMap$g<K, V, E>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public boolean clearValueForTesting(K k, int i, v<K, V, ? extends g<K, V, ?>> vVar) {
            lock();
            try {
                AtomicReferenceArray<E> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                g gVar = (g) atomicReferenceArray.get(length);
                for (g gVar2 = gVar; gVar2 != null; gVar2 = gVar2.c()) {
                    Object a = gVar2.a();
                    if (gVar2.b() == i && a != null && this.map.keyEquivalence.equivalent(k, a)) {
                        if (((u) gVar2).d() == vVar) {
                            atomicReferenceArray.set(length, removeFromChain(gVar, gVar2));
                            return true;
                        } else {
                            unlock();
                            return false;
                        }
                    }
                }
                unlock();
                return false;
            } finally {
                unlock();
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends com.google.common.collect.MapMakerInternalMap$g<K, V, E>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public boolean removeEntryForTesting(E e) {
            int b = e.b();
            int i = this.count;
            AtomicReferenceArray<E> atomicReferenceArray = this.table;
            int length = b & (atomicReferenceArray.length() - 1);
            g gVar = (g) atomicReferenceArray.get(length);
            for (g gVar2 = gVar; gVar2 != null; gVar2 = gVar2.c()) {
                if (gVar2 == e) {
                    this.modCount++;
                    atomicReferenceArray.set(length, removeFromChain(gVar, gVar2));
                    this.count--;
                    return true;
                }
            }
            return false;
        }

        static <K, V, E extends g<K, V, E>> boolean isCollected(E e) {
            return e.e() == null;
        }

        /* access modifiers changed from: package-private */
        public V getLiveValue(E e) {
            if (e.a() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V v = (V) e.e();
            if (v != null) {
                return v;
            }
            tryDrainReferenceQueues();
            return null;
        }

        /* access modifiers changed from: package-private */
        public void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                runCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public void preWriteCleanup() {
            runLockedCleanup();
        }

        /* access modifiers changed from: package-private */
        public void runCleanup() {
            runLockedCleanup();
        }

        /* access modifiers changed from: package-private */
        public void runLockedCleanup() {
            if (tryLock()) {
                try {
                    maybeDrainReferenceQueues();
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class StrongKeyStrongValueSegment<K, V> extends Segment<K, V, m<K, V>, StrongKeyStrongValueSegment<K, V>> {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public StrongKeyStrongValueSegment<K, V> self() {
            return this;
        }

        StrongKeyStrongValueSegment(MapMakerInternalMap<K, V, m<K, V>, StrongKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public m<K, V> castForTesting(g<K, V, ?> gVar) {
            return (m) gVar;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class StrongKeyWeakValueSegment<K, V> extends Segment<K, V, n<K, V>, StrongKeyWeakValueSegment<K, V>> {
        private final ReferenceQueue<V> queueForValues = new ReferenceQueue<>();

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public StrongKeyWeakValueSegment<K, V> self() {
            return this;
        }

        StrongKeyWeakValueSegment(MapMakerInternalMap<K, V, n<K, V>, StrongKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            return this.queueForValues;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public n<K, V> castForTesting(g<K, V, ?> gVar) {
            return (n) gVar;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public v<K, V, n<K, V>> getWeakValueReferenceForTesting(g<K, V, ?> gVar) {
            return castForTesting((g) gVar).d();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public v<K, V, n<K, V>> newWeakValueReferenceForTesting(g<K, V, ?> gVar, V v) {
            return new w(this.queueForValues, v, castForTesting((g) gVar));
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void setWeakValueReferenceForTesting(g<K, V, ?> gVar, v<K, V, ? extends g<K, V, ?>> vVar) {
            n<K, V> castForTesting = castForTesting((g) gVar);
            v vVar2 = ((n) castForTesting).d;
            ((n) castForTesting).d = vVar;
            vVar2.clear();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeDrainReferenceQueues() {
            drainValueReferenceQueue(this.queueForValues);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForValues);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class StrongKeyDummyValueSegment<K> extends Segment<K, MapMaker.Dummy, l<K>, StrongKeyDummyValueSegment<K>> {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public StrongKeyDummyValueSegment<K> self() {
            return this;
        }

        StrongKeyDummyValueSegment(MapMakerInternalMap<K, MapMaker.Dummy, l<K>, StrongKeyDummyValueSegment<K>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public l<K> castForTesting(g<K, MapMaker.Dummy, ?> gVar) {
            return (l) gVar;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class WeakKeyStrongValueSegment<K, V> extends Segment<K, V, s<K, V>, WeakKeyStrongValueSegment<K, V>> {
        private final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakKeyStrongValueSegment<K, V> self() {
            return this;
        }

        WeakKeyStrongValueSegment(MapMakerInternalMap<K, V, s<K, V>, WeakKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public s<K, V> castForTesting(g<K, V, ?> gVar) {
            return (s) gVar;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class WeakKeyWeakValueSegment<K, V> extends Segment<K, V, t<K, V>, WeakKeyWeakValueSegment<K, V>> {
        private final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();
        private final ReferenceQueue<V> queueForValues = new ReferenceQueue<>();

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakKeyWeakValueSegment<K, V> self() {
            return this;
        }

        WeakKeyWeakValueSegment(MapMakerInternalMap<K, V, t<K, V>, WeakKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public ReferenceQueue<V> getValueReferenceQueueForTesting() {
            return this.queueForValues;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public t<K, V> castForTesting(g<K, V, ?> gVar) {
            return (t) gVar;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public v<K, V, t<K, V>> getWeakValueReferenceForTesting(g<K, V, ?> gVar) {
            return castForTesting((g) gVar).d();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public v<K, V, t<K, V>> newWeakValueReferenceForTesting(g<K, V, ?> gVar, V v) {
            return new w(this.queueForValues, v, castForTesting((g) gVar));
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void setWeakValueReferenceForTesting(g<K, V, ?> gVar, v<K, V, ? extends g<K, V, ?>> vVar) {
            t<K, V> castForTesting = castForTesting((g) gVar);
            v vVar2 = ((t) castForTesting).c;
            ((t) castForTesting).c = vVar;
            vVar2.clear();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
            drainValueReferenceQueue(this.queueForValues);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class WeakKeyDummyValueSegment<K> extends Segment<K, MapMaker.Dummy, r<K>, WeakKeyDummyValueSegment<K>> {
        private final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public WeakKeyDummyValueSegment<K> self() {
            return this;
        }

        WeakKeyDummyValueSegment(MapMakerInternalMap<K, MapMaker.Dummy, r<K>, WeakKeyDummyValueSegment<K>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public ReferenceQueue<K> getKeyReferenceQueueForTesting() {
            return this.queueForKeys;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public r<K> castForTesting(g<K, MapMaker.Dummy, ?> gVar) {
            return (r) gVar;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeDrainReferenceQueues() {
            drainKeyReferenceQueue(this.queueForKeys);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public void maybeClearReferenceQueues() {
            clearReferenceQueue(this.queueForKeys);
        }
    }

    /* access modifiers changed from: package-private */
    public Strength keyStrength() {
        return this.entryHelper.a();
    }

    /* access modifiers changed from: package-private */
    public Strength valueStrength() {
        return this.entryHelper.b();
    }

    /* access modifiers changed from: package-private */
    public Equivalence<Object> valueEquivalence() {
        return this.entryHelper.b().defaultEquivalence();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        Segment<K, V, E, S>[] segmentArr = this.segments;
        long j2 = 0;
        for (int i2 = 0; i2 < segmentArr.length; i2++) {
            if (segmentArr[i2].count != 0) {
                return false;
            }
            j2 += (long) segmentArr[i2].modCount;
        }
        if (j2 == 0) {
            return true;
        }
        for (int i3 = 0; i3 < segmentArr.length; i3++) {
            if (segmentArr[i3].count != 0) {
                return false;
            }
            j2 -= (long) segmentArr[i3].modCount;
        }
        if (j2 != 0) {
            return false;
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        Segment<K, V, E, S>[] segmentArr;
        long j2 = 0;
        for (Segment<K, V, E, S> segment : this.segments) {
            j2 += (long) segment.count;
        }
        return Ints.b(j2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return (V) segmentFor(hash).get(obj, hash);
    }

    /* access modifiers changed from: package-private */
    public E getEntry(Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return (E) segmentFor(hash).getEntry(obj, hash);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        int hash = hash(obj);
        return segmentFor(hash).containsKey(obj, hash);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1, types: [int] */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        Segment<K, V, E, S>[] segmentArr = this.segments;
        long j2 = -1;
        int i2 = 0;
        while (i2 < 3) {
            int length = segmentArr.length;
            long j3 = 0;
            int i3 = z;
            while (i3 < length) {
                Segment<K, V, E, S> segment = segmentArr[i3 == true ? 1 : 0];
                int i4 = segment.count;
                AtomicReferenceArray<E> atomicReferenceArray = segment.table;
                int i5 = z;
                while (i5 < atomicReferenceArray.length()) {
                    for (E e2 = atomicReferenceArray.get(i5 == true ? 1 : 0); e2 != null; e2 = e2.c()) {
                        Object liveValue = segment.getLiveValue(e2);
                        if (liveValue != null && valueEquivalence().equivalent(obj, liveValue)) {
                            return true;
                        }
                    }
                    i5++;
                }
                j3 += (long) segment.modCount;
                z = false;
                i3 = (i3 == true ? 1 : 0) + 1;
            }
            if (j3 == j2) {
                return false;
            }
            i2++;
            j2 = j3;
            z = false;
        }
        return z;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k2, V v2) {
        com.google.common.base.m.a(k2);
        com.google.common.base.m.a(v2);
        int hash = hash(k2);
        return (V) segmentFor(hash).put(k2, hash, v2, false);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K k2, V v2) {
        com.google.common.base.m.a(k2);
        com.google.common.base.m.a(v2);
        int hash = hash(k2);
        return (V) segmentFor(hash).put(k2, hash, v2, true);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        if (obj == null) {
            return null;
        }
        int hash = hash(obj);
        return (V) segmentFor(hash).remove(obj, hash);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int hash = hash(obj);
        return segmentFor(hash).remove(obj, hash, obj2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K k2, V v2, V v3) {
        com.google.common.base.m.a(k2);
        com.google.common.base.m.a(v3);
        if (v2 == null) {
            return false;
        }
        int hash = hash(k2);
        return segmentFor(hash).replace(k2, hash, v2, v3);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K k2, V v2) {
        com.google.common.base.m.a(k2);
        com.google.common.base.m.a(v2);
        int hash = hash(k2);
        return (V) segmentFor(hash).replace(k2, hash, v2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (Segment<K, V, E, S> segment : this.segments) {
            segment.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        j jVar = new j();
        this.keySet = jVar;
        return jVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        q qVar = new q();
        this.values = qVar;
        return qVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        e eVar = new e();
        this.entrySet = eVar;
        return eVar;
    }

    abstract class f<T> implements Iterator<T> {
        int b;
        int c = -1;
        Segment<K, V, E, S> d;
        AtomicReferenceArray<E> e;
        E f;
        MapMakerInternalMap<K, V, E, S>.x g;
        MapMakerInternalMap<K, V, E, S>.x h;

        f() {
            this.b = MapMakerInternalMap.this.segments.length - 1;
            b();
        }

        /* access modifiers changed from: package-private */
        public final void b() {
            this.g = null;
            if (!c() && !d()) {
                while (this.b >= 0) {
                    Segment<K, V, E, S>[] segmentArr = MapMakerInternalMap.this.segments;
                    int i = this.b;
                    this.b = i - 1;
                    this.d = segmentArr[i];
                    if (this.d.count != 0) {
                        this.e = this.d.table;
                        this.c = this.e.length() - 1;
                        if (d()) {
                            return;
                        }
                    }
                }
            }
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [com.google.common.collect.MapMakerInternalMap$g, E] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean c() {
            /*
                r1 = this;
                E r0 = r1.f
                if (r0 == 0) goto L_0x0019
            L_0x0004:
                com.google.common.collect.MapMakerInternalMap$g r0 = r0.c()
                r1.f = r0
                E r0 = r1.f
                if (r0 == 0) goto L_0x0019
                boolean r0 = r1.a(r0)
                if (r0 == 0) goto L_0x0016
                r0 = 1
                return r0
            L_0x0016:
                E r0 = r1.f
                goto L_0x0004
            L_0x0019:
                r0 = 0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.f.c():boolean");
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [com.google.common.collect.MapMakerInternalMap$g, E] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean d() {
            /*
                r3 = this;
            L_0x0000:
                int r0 = r3.c
                if (r0 < 0) goto L_0x0024
                java.util.concurrent.atomic.AtomicReferenceArray<E> r1 = r3.e
                int r2 = r0 + -1
                r3.c = r2
                java.lang.Object r0 = r1.get(r0)
                com.google.common.collect.MapMakerInternalMap$g r0 = (com.google.common.collect.MapMakerInternalMap.g) r0
                r3.f = r0
                if (r0 == 0) goto L_0x0000
                E r0 = r3.f
                boolean r0 = r3.a(r0)
                if (r0 != 0) goto L_0x0022
                boolean r0 = r3.c()
                if (r0 == 0) goto L_0x0000
            L_0x0022:
                r0 = 1
                return r0
            L_0x0024:
                r0 = 0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.f.d():boolean");
        }

        /* access modifiers changed from: package-private */
        public boolean a(E e) {
            boolean z;
            try {
                Object a = e.a();
                Object liveValue = MapMakerInternalMap.this.getLiveValue(e);
                if (liveValue != null) {
                    this.g = new x(a, liveValue);
                    z = true;
                } else {
                    z = false;
                }
                return z;
            } finally {
                this.d.postReadCleanup();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.g != null;
        }

        /* access modifiers changed from: package-private */
        public MapMakerInternalMap<K, V, E, S>.x e() {
            MapMakerInternalMap<K, V, E, S>.x xVar = this.g;
            if (xVar != null) {
                this.h = xVar;
                b();
                return this.h;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            n.a(this.h != null);
            MapMakerInternalMap.this.remove(this.h.getKey());
            this.h = null;
        }
    }

    final class i extends MapMakerInternalMap<K, V, E, S>.f {
        i() {
            super();
        }

        @Override // java.util.Iterator
        public K next() {
            return (K) e().getKey();
        }
    }

    final class p extends MapMakerInternalMap<K, V, E, S>.f {
        p() {
            super();
        }

        @Override // java.util.Iterator
        public V next() {
            return (V) e().getValue();
        }
    }

    /* access modifiers changed from: package-private */
    public final class x extends b<K, V> {
        final K a;
        V b;

        x(K k, V v) {
            this.a = k;
            this.b = v;
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public K getKey() {
            return this.a;
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public V getValue() {
            return this.b;
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry, java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!this.a.equals(entry.getKey()) || !this.b.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry, java.lang.Object
        public int hashCode() {
            return this.a.hashCode() ^ this.b.hashCode();
        }

        @Override // com.google.common.collect.b, java.util.Map.Entry
        public V setValue(V v) {
            V v2 = (V) MapMakerInternalMap.this.put(this.a, v);
            this.b = v;
            return v2;
        }
    }

    final class d extends MapMakerInternalMap<K, V, E, S>.f {
        d() {
            super();
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            return e();
        }
    }

    final class j extends k<K> {
        j() {
            super(null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return new i();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return MapMakerInternalMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return MapMakerInternalMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return MapMakerInternalMap.this.remove(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            MapMakerInternalMap.this.clear();
        }
    }

    final class q extends AbstractCollection<V> {
        q() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new p();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return MapMakerInternalMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return MapMakerInternalMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            MapMakerInternalMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return MapMakerInternalMap.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) MapMakerInternalMap.toArrayList(this).toArray(tArr);
        }
    }

    final class e extends k<Map.Entry<K, V>> {
        e() {
            super(null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new d();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Map.Entry entry;
            Object key;
            Object obj2;
            if ((obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (obj2 = MapMakerInternalMap.this.get(key)) != null && MapMakerInternalMap.this.valueEquivalence().equivalent(entry.getValue(), obj2)) {
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Map.Entry entry;
            Object key;
            if ((obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && MapMakerInternalMap.this.remove(key, entry.getValue())) {
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return MapMakerInternalMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return MapMakerInternalMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            MapMakerInternalMap.this.clear();
        }
    }

    private static abstract class k<E> extends AbstractSet<E> {
        private k() {
        }

        /* synthetic */ k(AnonymousClass1 r1) {
            this();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return MapMakerInternalMap.toArrayList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) MapMakerInternalMap.toArrayList(this).toArray(tArr);
        }
    }

    /* access modifiers changed from: private */
    public static <E> ArrayList<E> toArrayList(Collection<E> collection) {
        ArrayList<E> arrayList = new ArrayList<>(collection.size());
        Iterators.a(arrayList, collection.iterator());
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializationProxy(this.entryHelper.a(), this.entryHelper.b(), this.keyEquivalence, this.entryHelper.b().defaultEquivalence(), this.concurrencyLevel, this);
    }

    static abstract class AbstractSerializationProxy<K, V> extends t<K, V> implements Serializable {
        private static final long serialVersionUID = 3;
        final int concurrencyLevel;
        transient ConcurrentMap<K, V> delegate;
        final Equivalence<Object> keyEquivalence;
        final Strength keyStrength;
        final Equivalence<Object> valueEquivalence;
        final Strength valueStrength;

        AbstractSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i, ConcurrentMap<K, V> concurrentMap) {
            this.keyStrength = strength;
            this.valueStrength = strength2;
            this.keyEquivalence = equivalence;
            this.valueEquivalence = equivalence2;
            this.concurrencyLevel = i;
            this.delegate = concurrentMap;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.t, com.google.common.collect.v, com.google.common.collect.z
        public ConcurrentMap<K, V> delegate() {
            return this.delegate;
        }

        /* access modifiers changed from: package-private */
        public void writeMapTo(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeInt(this.delegate.size());
            for (Map.Entry<K, V> entry : this.delegate.entrySet()) {
                objectOutputStream.writeObject(entry.getKey());
                objectOutputStream.writeObject(entry.getValue());
            }
            objectOutputStream.writeObject(null);
        }

        /* access modifiers changed from: package-private */
        public MapMaker readMapMaker(ObjectInputStream objectInputStream) throws IOException {
            return new MapMaker().a(objectInputStream.readInt()).a(this.keyStrength).b(this.valueStrength).a(this.keyEquivalence).b(this.concurrencyLevel);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.ConcurrentMap<K, V> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void readEntries(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            while (true) {
                Object readObject = objectInputStream.readObject();
                if (readObject != null) {
                    this.delegate.put(readObject, objectInputStream.readObject());
                } else {
                    return;
                }
            }
        }
    }

    private static final class SerializationProxy<K, V> extends AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 3;

        SerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i, ConcurrentMap<K, V> concurrentMap) {
            super(strength, strength2, equivalence, equivalence2, i, concurrentMap);
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            writeMapTo(objectOutputStream);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.delegate = readMapMaker(objectInputStream).g();
            readEntries(objectInputStream);
        }

        private Object readResolve() {
            return this.delegate;
        }
    }
}
