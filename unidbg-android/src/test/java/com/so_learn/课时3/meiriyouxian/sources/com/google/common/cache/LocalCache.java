package com.google.common.cache;

import com.google.common.base.Equivalence;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.a;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.ExecutionError;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.huawei.hms.framework.common.ContainerUtils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/* access modifiers changed from: package-private */
public class LocalCache<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    static final Logger a = Logger.getLogger(LocalCache.class.getName());
    static final q<Object, Object> u = new AnonymousClass1();
    static final Queue<?> v = new AnonymousClass2();
    final int b;
    final int c;
    final Segment<K, V>[] d;
    final int e;
    final Equivalence<Object> f;
    final Equivalence<Object> g;
    final Strength h;
    final Strength i;
    final long j;
    final i<K, V> k;
    final long l;
    final long m;
    final long n;
    final Queue<RemovalNotification<K, V>> o;
    final h<K, V> p;
    final com.google.common.base.s q;
    final EntryFactory r;
    final a.b s;
    final CacheLoader<? super K, V> t;
    Set<K> w;
    Collection<V> x;
    Set<Map.Entry<K, V>> y;

    /* access modifiers changed from: private */
    public enum NullEntry implements g<Object, Object> {
        INSTANCE;

        @Override // com.google.common.cache.g
        public long getAccessTime() {
            return 0;
        }

        @Override // com.google.common.cache.g
        public int getHash() {
            return 0;
        }

        @Override // com.google.common.cache.g
        public Object getKey() {
            return null;
        }

        @Override // com.google.common.cache.g
        public g<Object, Object> getNext() {
            return null;
        }

        @Override // com.google.common.cache.g
        public g<Object, Object> getNextInAccessQueue() {
            return this;
        }

        @Override // com.google.common.cache.g
        public g<Object, Object> getNextInWriteQueue() {
            return this;
        }

        @Override // com.google.common.cache.g
        public g<Object, Object> getPreviousInAccessQueue() {
            return this;
        }

        @Override // com.google.common.cache.g
        public g<Object, Object> getPreviousInWriteQueue() {
            return this;
        }

        @Override // com.google.common.cache.g
        public q<Object, Object> getValueReference() {
            return null;
        }

        @Override // com.google.common.cache.g
        public long getWriteTime() {
            return 0;
        }

        @Override // com.google.common.cache.g
        public void setAccessTime(long j) {
        }

        @Override // com.google.common.cache.g
        public void setNextInAccessQueue(g<Object, Object> gVar) {
        }

        @Override // com.google.common.cache.g
        public void setNextInWriteQueue(g<Object, Object> gVar) {
        }

        @Override // com.google.common.cache.g
        public void setPreviousInAccessQueue(g<Object, Object> gVar) {
        }

        @Override // com.google.common.cache.g
        public void setPreviousInWriteQueue(g<Object, Object> gVar) {
        }

        @Override // com.google.common.cache.g
        public void setValueReference(q<Object, Object> qVar) {
        }

        @Override // com.google.common.cache.g
        public void setWriteTime(long j) {
        }
    }

    /* access modifiers changed from: package-private */
    public interface q<K, V> {
        int a();

        q<K, V> a(ReferenceQueue<V> referenceQueue, V v, g<K, V> gVar);

        void a(V v);

        g<K, V> b();

        boolean c();

        boolean d();

        V e() throws ExecutionException;

        V get();
    }

    static int a(int i2) {
        int i3 = i2 + ((i2 << 15) ^ -12931);
        int i4 = i3 ^ (i3 >>> 10);
        int i5 = i4 + (i4 << 3);
        int i6 = i5 ^ (i5 >>> 6);
        int i7 = i6 + (i6 << 2) + (i6 << 14);
        return i7 ^ (i7 >>> 16);
    }

    LocalCache(CacheBuilder<? super K, ? super V> cacheBuilder, CacheLoader<? super K, V> cacheLoader) {
        this.e = Math.min(cacheBuilder.e(), 65536);
        this.h = cacheBuilder.h();
        this.i = cacheBuilder.i();
        this.f = cacheBuilder.b();
        this.g = cacheBuilder.c();
        this.j = cacheBuilder.f();
        this.k = cacheBuilder.g();
        this.l = cacheBuilder.k();
        this.m = cacheBuilder.j();
        this.n = cacheBuilder.l();
        this.p = cacheBuilder.m();
        this.o = this.p == CacheBuilder.NullListener.INSTANCE ? q() : new ConcurrentLinkedQueue<>();
        this.q = cacheBuilder.a(j());
        this.r = EntryFactory.getFactory(this.h, l(), k());
        this.s = (a.b) cacheBuilder.n().get();
        this.t = cacheLoader;
        int min = Math.min(cacheBuilder.d(), 1073741824);
        if (a() && !b()) {
            min = (int) Math.min((long) min, this.j);
        }
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        int i5 = 1;
        while (i5 < this.e && (!a() || ((long) (i5 * 20)) <= this.j)) {
            i4++;
            i5 <<= 1;
        }
        this.c = 32 - i4;
        this.b = i5 - 1;
        this.d = c(i5);
        int i6 = min / i5;
        while (i3 < (i6 * i5 < min ? i6 + 1 : i6)) {
            i3 <<= 1;
        }
        if (a()) {
            long j2 = this.j;
            long j3 = (long) i5;
            long j4 = (j2 / j3) + 1;
            long j5 = j2 % j3;
            while (i2 < this.d.length) {
                if (((long) i2) == j5) {
                    j4--;
                }
                this.d[i2] = a(i3, j4, (a.b) cacheBuilder.n().get());
                i2++;
            }
            return;
        }
        while (true) {
            Segment<K, V>[] segmentArr = this.d;
            if (i2 < segmentArr.length) {
                segmentArr[i2] = a(i3, -1, (a.b) cacheBuilder.n().get());
                i2++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.j >= 0;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.k != CacheBuilder.OneWeigher.INSTANCE;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.m > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.l > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.n > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return d() || a();
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return c();
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        return c() || e();
    }

    /* access modifiers changed from: package-private */
    public boolean i() {
        return d();
    }

    /* access modifiers changed from: package-private */
    public boolean j() {
        return h() || i();
    }

    /* access modifiers changed from: package-private */
    public boolean k() {
        return g() || h();
    }

    /* access modifiers changed from: package-private */
    public boolean l() {
        return f() || i();
    }

    /* access modifiers changed from: package-private */
    public boolean m() {
        return this.h != Strength.STRONG;
    }

    /* access modifiers changed from: package-private */
    public boolean n() {
        return this.i != Strength.STRONG;
    }

    /* access modifiers changed from: package-private */
    public enum Strength {
        STRONG {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.Strength
            public <K, V> q<K, V> referenceValue(Segment<K, V> segment, g<K, V> gVar, V v, int i) {
                return i == 1 ? new n(v) : new y(v, i);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.equals();
            }
        },
        SOFT {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.Strength
            public <K, V> q<K, V> referenceValue(Segment<K, V> segment, g<K, V> gVar, V v, int i) {
                return i == 1 ? new j(segment.valueReferenceQueue, v, gVar) : new x(segment.valueReferenceQueue, v, gVar, i);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        },
        WEAK {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.Strength
            public <K, V> q<K, V> referenceValue(Segment<K, V> segment, g<K, V> gVar, V v, int i) {
                return i == 1 ? new v(segment.valueReferenceQueue, v, gVar) : new z(segment.valueReferenceQueue, v, gVar, i);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.identity();
            }
        };

        /* access modifiers changed from: package-private */
        public abstract Equivalence<Object> defaultEquivalence();

        /* access modifiers changed from: package-private */
        public abstract <K, V> q<K, V> referenceValue(Segment<K, V> segment, g<K, V> gVar, V v, int i);

        /* synthetic */ Strength(AnonymousClass1 r3) {
            this();
        }
    }

    /* access modifiers changed from: package-private */
    public enum EntryFactory {
        STRONG {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> newEntry(Segment<K, V> segment, K k, int i, g<K, V> gVar) {
                return new m(k, i, gVar);
            }
        },
        STRONG_ACCESS {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> newEntry(Segment<K, V> segment, K k, int i, g<K, V> gVar) {
                return new k(k, i, gVar);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> copyEntry(Segment<K, V> segment, g<K, V> gVar, g<K, V> gVar2) {
                g<K, V> copyEntry = super.copyEntry(segment, gVar, gVar2);
                copyAccessEntry(gVar, copyEntry);
                return copyEntry;
            }
        },
        STRONG_WRITE {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> newEntry(Segment<K, V> segment, K k, int i, g<K, V> gVar) {
                return new o(k, i, gVar);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> copyEntry(Segment<K, V> segment, g<K, V> gVar, g<K, V> gVar2) {
                g<K, V> copyEntry = super.copyEntry(segment, gVar, gVar2);
                copyWriteEntry(gVar, copyEntry);
                return copyEntry;
            }
        },
        STRONG_ACCESS_WRITE {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> newEntry(Segment<K, V> segment, K k, int i, g<K, V> gVar) {
                return new l(k, i, gVar);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> copyEntry(Segment<K, V> segment, g<K, V> gVar, g<K, V> gVar2) {
                g<K, V> copyEntry = super.copyEntry(segment, gVar, gVar2);
                copyAccessEntry(gVar, copyEntry);
                copyWriteEntry(gVar, copyEntry);
                return copyEntry;
            }
        },
        WEAK {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> newEntry(Segment<K, V> segment, K k, int i, g<K, V> gVar) {
                return new u(segment.keyReferenceQueue, k, i, gVar);
            }
        },
        WEAK_ACCESS {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> newEntry(Segment<K, V> segment, K k, int i, g<K, V> gVar) {
                return new s(segment.keyReferenceQueue, k, i, gVar);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> copyEntry(Segment<K, V> segment, g<K, V> gVar, g<K, V> gVar2) {
                g<K, V> copyEntry = super.copyEntry(segment, gVar, gVar2);
                copyAccessEntry(gVar, copyEntry);
                return copyEntry;
            }
        },
        WEAK_WRITE {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> newEntry(Segment<K, V> segment, K k, int i, g<K, V> gVar) {
                return new w(segment.keyReferenceQueue, k, i, gVar);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> copyEntry(Segment<K, V> segment, g<K, V> gVar, g<K, V> gVar2) {
                g<K, V> copyEntry = super.copyEntry(segment, gVar, gVar2);
                copyWriteEntry(gVar, copyEntry);
                return copyEntry;
            }
        },
        WEAK_ACCESS_WRITE {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> newEntry(Segment<K, V> segment, K k, int i, g<K, V> gVar) {
                return new t(segment.keyReferenceQueue, k, i, gVar);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.cache.LocalCache.EntryFactory
            public <K, V> g<K, V> copyEntry(Segment<K, V> segment, g<K, V> gVar, g<K, V> gVar2) {
                g<K, V> copyEntry = super.copyEntry(segment, gVar, gVar2);
                copyAccessEntry(gVar, copyEntry);
                copyWriteEntry(gVar, copyEntry);
                return copyEntry;
            }
        };
        
        static final int ACCESS_MASK = 1;
        static final int WEAK_MASK = 4;
        static final int WRITE_MASK = 2;
        static final EntryFactory[] factories = {STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE};

        /* access modifiers changed from: package-private */
        public abstract <K, V> g<K, V> newEntry(Segment<K, V> segment, K k, int i, g<K, V> gVar);

        /* synthetic */ EntryFactory(AnonymousClass1 r3) {
            this();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v2, resolved type: boolean */
        /* JADX WARN: Multi-variable type inference failed */
        static EntryFactory getFactory(Strength strength, boolean z, boolean z2) {
            int i = 0;
            int i2 = (strength == Strength.WEAK ? 4 : 0) | (z ? 1 : 0);
            if (z2) {
                i = 2;
            }
            return factories[i2 | i];
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.common.cache.LocalCache$EntryFactory */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public <K, V> g<K, V> copyEntry(Segment<K, V> segment, g<K, V> gVar, g<K, V> gVar2) {
            return newEntry(segment, gVar.getKey(), gVar.getHash(), gVar2);
        }

        /* access modifiers changed from: package-private */
        public <K, V> void copyAccessEntry(g<K, V> gVar, g<K, V> gVar2) {
            gVar2.setAccessTime(gVar.getAccessTime());
            LocalCache.a(gVar.getPreviousInAccessQueue(), gVar2);
            LocalCache.a(gVar2, gVar.getNextInAccessQueue());
            LocalCache.b((g) gVar);
        }

        /* access modifiers changed from: package-private */
        public <K, V> void copyWriteEntry(g<K, V> gVar, g<K, V> gVar2) {
            gVar2.setWriteTime(gVar.getWriteTime());
            LocalCache.b(gVar.getPreviousInWriteQueue(), gVar2);
            LocalCache.b(gVar2, gVar.getNextInWriteQueue());
            LocalCache.c((g) gVar);
        }
    }

    /* renamed from: com.google.common.cache.LocalCache$1  reason: invalid class name */
    static class AnonymousClass1 implements q<Object, Object> {
        @Override // com.google.common.cache.LocalCache.q
        public int a() {
            return 0;
        }

        @Override // com.google.common.cache.LocalCache.q
        public q<Object, Object> a(ReferenceQueue<Object> referenceQueue, Object obj, g<Object, Object> gVar) {
            return this;
        }

        @Override // com.google.common.cache.LocalCache.q
        public void a(Object obj) {
        }

        @Override // com.google.common.cache.LocalCache.q
        public g<Object, Object> b() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.q
        public boolean c() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.q
        public boolean d() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.q
        public Object e() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.q
        public Object get() {
            return null;
        }

        AnonymousClass1() {
        }
    }

    static <K, V> q<K, V> o() {
        return (q<K, V>) u;
    }

    static abstract class b<K, V> implements g<K, V> {
        b() {
        }

        @Override // com.google.common.cache.g
        public q<K, V> getValueReference() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setValueReference(q<K, V> qVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public g<K, V> getNext() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public int getHash() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public K getKey() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setAccessTime(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public g<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setNextInAccessQueue(g<K, V> gVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public g<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setPreviousInAccessQueue(g<K, V> gVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setWriteTime(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public g<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setNextInWriteQueue(g<K, V> gVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public g<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setPreviousInWriteQueue(g<K, V> gVar) {
            throw new UnsupportedOperationException();
        }
    }

    static <K, V> g<K, V> p() {
        return NullEntry.INSTANCE;
    }

    /* renamed from: com.google.common.cache.LocalCache$2  reason: invalid class name */
    static class AnonymousClass2 extends AbstractQueue<Object> {
        @Override // java.util.Queue
        public boolean offer(Object obj) {
            return true;
        }

        @Override // java.util.Queue
        public Object peek() {
            return null;
        }

        @Override // java.util.Queue
        public Object poll() {
            return null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return 0;
        }

        AnonymousClass2() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Object> iterator() {
            return ImmutableSet.of().iterator();
        }
    }

    static <E> Queue<E> q() {
        return (Queue<E>) v;
    }

    static class m<K, V> extends b<K, V> {
        final K g;
        final int h;
        final g<K, V> i;
        volatile q<K, V> j = LocalCache.o();

        m(K k, int i, g<K, V> gVar) {
            this.g = k;
            this.h = i;
            this.i = gVar;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public K getKey() {
            return this.g;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public q<K, V> getValueReference() {
            return this.j;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setValueReference(q<K, V> qVar) {
            this.j = qVar;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public int getHash() {
            return this.h;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public g<K, V> getNext() {
            return this.i;
        }
    }

    static final class k<K, V> extends m<K, V> {
        volatile long a = Long.MAX_VALUE;
        g<K, V> b = LocalCache.p();
        g<K, V> c = LocalCache.p();

        k(K k, int i, g<K, V> gVar) {
            super(k, i, gVar);
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public long getAccessTime() {
            return this.a;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setAccessTime(long j) {
            this.a = j;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public g<K, V> getNextInAccessQueue() {
            return this.b;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setNextInAccessQueue(g<K, V> gVar) {
            this.b = gVar;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public g<K, V> getPreviousInAccessQueue() {
            return this.c;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setPreviousInAccessQueue(g<K, V> gVar) {
            this.c = gVar;
        }
    }

    static final class o<K, V> extends m<K, V> {
        volatile long a = Long.MAX_VALUE;
        g<K, V> b = LocalCache.p();
        g<K, V> c = LocalCache.p();

        o(K k, int i, g<K, V> gVar) {
            super(k, i, gVar);
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public long getWriteTime() {
            return this.a;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setWriteTime(long j) {
            this.a = j;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public g<K, V> getNextInWriteQueue() {
            return this.b;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setNextInWriteQueue(g<K, V> gVar) {
            this.b = gVar;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public g<K, V> getPreviousInWriteQueue() {
            return this.c;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setPreviousInWriteQueue(g<K, V> gVar) {
            this.c = gVar;
        }
    }

    static final class l<K, V> extends m<K, V> {
        volatile long a = Long.MAX_VALUE;
        g<K, V> b = LocalCache.p();
        g<K, V> c = LocalCache.p();
        volatile long d = Long.MAX_VALUE;
        g<K, V> e = LocalCache.p();
        g<K, V> f = LocalCache.p();

        l(K k, int i, g<K, V> gVar) {
            super(k, i, gVar);
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public long getAccessTime() {
            return this.a;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setAccessTime(long j) {
            this.a = j;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public g<K, V> getNextInAccessQueue() {
            return this.b;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setNextInAccessQueue(g<K, V> gVar) {
            this.b = gVar;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public g<K, V> getPreviousInAccessQueue() {
            return this.c;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setPreviousInAccessQueue(g<K, V> gVar) {
            this.c = gVar;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public long getWriteTime() {
            return this.d;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setWriteTime(long j) {
            this.d = j;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public g<K, V> getNextInWriteQueue() {
            return this.e;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setNextInWriteQueue(g<K, V> gVar) {
            this.e = gVar;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public g<K, V> getPreviousInWriteQueue() {
            return this.f;
        }

        @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
        public void setPreviousInWriteQueue(g<K, V> gVar) {
            this.f = gVar;
        }
    }

    static class u<K, V> extends WeakReference<K> implements g<K, V> {
        final int g;
        final g<K, V> h;
        volatile q<K, V> i = LocalCache.o();

        u(ReferenceQueue<K> referenceQueue, K k, int i, g<K, V> gVar) {
            super(k, referenceQueue);
            this.g = i;
            this.h = gVar;
        }

        @Override // com.google.common.cache.g
        public K getKey() {
            return (K) get();
        }

        @Override // com.google.common.cache.g
        public long getAccessTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setAccessTime(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public g<K, V> getNextInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setNextInAccessQueue(g<K, V> gVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public g<K, V> getPreviousInAccessQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setPreviousInAccessQueue(g<K, V> gVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public long getWriteTime() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setWriteTime(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public g<K, V> getNextInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setNextInWriteQueue(g<K, V> gVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public g<K, V> getPreviousInWriteQueue() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public void setPreviousInWriteQueue(g<K, V> gVar) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.cache.g
        public q<K, V> getValueReference() {
            return this.i;
        }

        @Override // com.google.common.cache.g
        public void setValueReference(q<K, V> qVar) {
            this.i = qVar;
        }

        @Override // com.google.common.cache.g
        public int getHash() {
            return this.g;
        }

        @Override // com.google.common.cache.g
        public g<K, V> getNext() {
            return this.h;
        }
    }

    static final class s<K, V> extends u<K, V> {
        volatile long a = Long.MAX_VALUE;
        g<K, V> b = LocalCache.p();
        g<K, V> c = LocalCache.p();

        s(ReferenceQueue<K> referenceQueue, K k, int i, g<K, V> gVar) {
            super(referenceQueue, k, i, gVar);
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public long getAccessTime() {
            return this.a;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public void setAccessTime(long j) {
            this.a = j;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public g<K, V> getNextInAccessQueue() {
            return this.b;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public void setNextInAccessQueue(g<K, V> gVar) {
            this.b = gVar;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public g<K, V> getPreviousInAccessQueue() {
            return this.c;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public void setPreviousInAccessQueue(g<K, V> gVar) {
            this.c = gVar;
        }
    }

    static final class w<K, V> extends u<K, V> {
        volatile long a = Long.MAX_VALUE;
        g<K, V> b = LocalCache.p();
        g<K, V> c = LocalCache.p();

        w(ReferenceQueue<K> referenceQueue, K k, int i, g<K, V> gVar) {
            super(referenceQueue, k, i, gVar);
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public long getWriteTime() {
            return this.a;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public void setWriteTime(long j) {
            this.a = j;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public g<K, V> getNextInWriteQueue() {
            return this.b;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public void setNextInWriteQueue(g<K, V> gVar) {
            this.b = gVar;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public g<K, V> getPreviousInWriteQueue() {
            return this.c;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public void setPreviousInWriteQueue(g<K, V> gVar) {
            this.c = gVar;
        }
    }

    static final class t<K, V> extends u<K, V> {
        volatile long a = Long.MAX_VALUE;
        g<K, V> b = LocalCache.p();
        g<K, V> c = LocalCache.p();
        volatile long d = Long.MAX_VALUE;
        g<K, V> e = LocalCache.p();
        g<K, V> f = LocalCache.p();

        t(ReferenceQueue<K> referenceQueue, K k, int i, g<K, V> gVar) {
            super(referenceQueue, k, i, gVar);
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public long getAccessTime() {
            return this.a;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public void setAccessTime(long j) {
            this.a = j;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public g<K, V> getNextInAccessQueue() {
            return this.b;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public void setNextInAccessQueue(g<K, V> gVar) {
            this.b = gVar;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public g<K, V> getPreviousInAccessQueue() {
            return this.c;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public void setPreviousInAccessQueue(g<K, V> gVar) {
            this.c = gVar;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public long getWriteTime() {
            return this.d;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public void setWriteTime(long j) {
            this.d = j;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public g<K, V> getNextInWriteQueue() {
            return this.e;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public void setNextInWriteQueue(g<K, V> gVar) {
            this.e = gVar;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public g<K, V> getPreviousInWriteQueue() {
            return this.f;
        }

        @Override // com.google.common.cache.LocalCache.u, com.google.common.cache.g
        public void setPreviousInWriteQueue(g<K, V> gVar) {
            this.f = gVar;
        }
    }

    static class v<K, V> extends WeakReference<V> implements q<K, V> {
        final g<K, V> a;

        @Override // com.google.common.cache.LocalCache.q
        public int a() {
            return 1;
        }

        @Override // com.google.common.cache.LocalCache.q
        public void a(V v) {
        }

        @Override // com.google.common.cache.LocalCache.q
        public boolean c() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.q
        public boolean d() {
            return true;
        }

        v(ReferenceQueue<V> referenceQueue, V v, g<K, V> gVar) {
            super(v, referenceQueue);
            this.a = gVar;
        }

        @Override // com.google.common.cache.LocalCache.q
        public g<K, V> b() {
            return this.a;
        }

        @Override // com.google.common.cache.LocalCache.q
        public q<K, V> a(ReferenceQueue<V> referenceQueue, V v, g<K, V> gVar) {
            return new v(referenceQueue, v, gVar);
        }

        @Override // com.google.common.cache.LocalCache.q
        public V e() {
            return get();
        }
    }

    static class j<K, V> extends SoftReference<V> implements q<K, V> {
        final g<K, V> a;

        @Override // com.google.common.cache.LocalCache.q
        public int a() {
            return 1;
        }

        @Override // com.google.common.cache.LocalCache.q
        public void a(V v) {
        }

        @Override // com.google.common.cache.LocalCache.q
        public boolean c() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.q
        public boolean d() {
            return true;
        }

        j(ReferenceQueue<V> referenceQueue, V v, g<K, V> gVar) {
            super(v, referenceQueue);
            this.a = gVar;
        }

        @Override // com.google.common.cache.LocalCache.q
        public g<K, V> b() {
            return this.a;
        }

        @Override // com.google.common.cache.LocalCache.q
        public q<K, V> a(ReferenceQueue<V> referenceQueue, V v, g<K, V> gVar) {
            return new j(referenceQueue, v, gVar);
        }

        @Override // com.google.common.cache.LocalCache.q
        public V e() {
            return get();
        }
    }

    static class n<K, V> implements q<K, V> {
        final V a;

        @Override // com.google.common.cache.LocalCache.q
        public int a() {
            return 1;
        }

        @Override // com.google.common.cache.LocalCache.q
        public q<K, V> a(ReferenceQueue<V> referenceQueue, V v, g<K, V> gVar) {
            return this;
        }

        @Override // com.google.common.cache.LocalCache.q
        public void a(V v) {
        }

        @Override // com.google.common.cache.LocalCache.q
        public g<K, V> b() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.q
        public boolean c() {
            return false;
        }

        @Override // com.google.common.cache.LocalCache.q
        public boolean d() {
            return true;
        }

        n(V v) {
            this.a = v;
        }

        @Override // com.google.common.cache.LocalCache.q
        public V get() {
            return this.a;
        }

        @Override // com.google.common.cache.LocalCache.q
        public V e() {
            return (V) get();
        }
    }

    static final class z<K, V> extends v<K, V> {
        final int b;

        z(ReferenceQueue<V> referenceQueue, V v, g<K, V> gVar, int i) {
            super(referenceQueue, v, gVar);
            this.b = i;
        }

        @Override // com.google.common.cache.LocalCache.v, com.google.common.cache.LocalCache.q
        public int a() {
            return this.b;
        }

        @Override // com.google.common.cache.LocalCache.v, com.google.common.cache.LocalCache.q
        public q<K, V> a(ReferenceQueue<V> referenceQueue, V v, g<K, V> gVar) {
            return new z(referenceQueue, v, gVar, this.b);
        }
    }

    static final class x<K, V> extends j<K, V> {
        final int b;

        x(ReferenceQueue<V> referenceQueue, V v, g<K, V> gVar, int i) {
            super(referenceQueue, v, gVar);
            this.b = i;
        }

        @Override // com.google.common.cache.LocalCache.j, com.google.common.cache.LocalCache.q
        public int a() {
            return this.b;
        }

        @Override // com.google.common.cache.LocalCache.j, com.google.common.cache.LocalCache.q
        public q<K, V> a(ReferenceQueue<V> referenceQueue, V v, g<K, V> gVar) {
            return new x(referenceQueue, v, gVar, this.b);
        }
    }

    static final class y<K, V> extends n<K, V> {
        final int b;

        y(V v, int i) {
            super(v);
            this.b = i;
        }

        @Override // com.google.common.cache.LocalCache.n, com.google.common.cache.LocalCache.q
        public int a() {
            return this.b;
        }
    }

    /* access modifiers changed from: package-private */
    public int a(Object obj) {
        return a(this.f.hash(obj));
    }

    /* access modifiers changed from: package-private */
    public void a(q<K, V> qVar) {
        g<K, V> b2 = qVar.b();
        int hash = b2.getHash();
        b(hash).reclaimValue(b2.getKey(), hash, qVar);
    }

    /* access modifiers changed from: package-private */
    public void a(g<K, V> gVar) {
        int hash = gVar.getHash();
        b(hash).reclaimKey(gVar, hash);
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V> b(int i2) {
        return this.d[(i2 >>> this.c) & this.b];
    }

    /* access modifiers changed from: package-private */
    public Segment<K, V> a(int i2, long j2, a.b bVar) {
        return new Segment<>(this, i2, j2, bVar);
    }

    /* access modifiers changed from: package-private */
    public V a(g<K, V> gVar, long j2) {
        V v2;
        if (gVar.getKey() == null || (v2 = (V) gVar.getValueReference().get()) == null || b(gVar, j2)) {
            return null;
        }
        return v2;
    }

    /* access modifiers changed from: package-private */
    public boolean b(g<K, V> gVar, long j2) {
        com.google.common.base.m.a(gVar);
        if (d() && j2 - gVar.getAccessTime() >= this.l) {
            return true;
        }
        if (!c() || j2 - gVar.getWriteTime() < this.m) {
            return false;
        }
        return true;
    }

    static <K, V> void a(g<K, V> gVar, g<K, V> gVar2) {
        gVar.setNextInAccessQueue(gVar2);
        gVar2.setPreviousInAccessQueue(gVar);
    }

    static <K, V> void b(g<K, V> gVar) {
        g p2 = p();
        gVar.setNextInAccessQueue(p2);
        gVar.setPreviousInAccessQueue(p2);
    }

    static <K, V> void b(g<K, V> gVar, g<K, V> gVar2) {
        gVar.setNextInWriteQueue(gVar2);
        gVar2.setPreviousInWriteQueue(gVar);
    }

    static <K, V> void c(g<K, V> gVar) {
        g p2 = p();
        gVar.setNextInWriteQueue(p2);
        gVar.setPreviousInWriteQueue(p2);
    }

    /* access modifiers changed from: package-private */
    public void r() {
        while (true) {
            RemovalNotification<K, V> poll = this.o.poll();
            if (poll != null) {
                try {
                    this.p.onRemoval(poll);
                } catch (Throwable th) {
                    a.log(Level.WARNING, "Exception thrown by removal listener", th);
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Segment<K, V>[] c(int i2) {
        return new Segment[i2];
    }

    /* access modifiers changed from: package-private */
    public static class Segment<K, V> extends ReentrantLock {
        final Queue<g<K, V>> accessQueue;
        volatile int count;
        final ReferenceQueue<K> keyReferenceQueue;
        final LocalCache<K, V> map;
        final long maxSegmentWeight;
        int modCount;
        final AtomicInteger readCount = new AtomicInteger();
        final Queue<g<K, V>> recencyQueue;
        final a.b statsCounter;
        volatile AtomicReferenceArray<g<K, V>> table;
        int threshold;
        long totalWeight;
        final ReferenceQueue<V> valueReferenceQueue;
        final Queue<g<K, V>> writeQueue;

        Segment(LocalCache<K, V> localCache, int i, long j, a.b bVar) {
            Queue<g<K, V>> queue;
            Queue<g<K, V>> queue2;
            Queue<g<K, V>> queue3;
            this.map = localCache;
            this.maxSegmentWeight = j;
            this.statsCounter = (a.b) com.google.common.base.m.a(bVar);
            initTable(newEntryArray(i));
            ReferenceQueue<V> referenceQueue = null;
            this.keyReferenceQueue = localCache.m() ? new ReferenceQueue<>() : null;
            this.valueReferenceQueue = localCache.n() ? new ReferenceQueue<>() : referenceQueue;
            if (localCache.f()) {
                queue = new ConcurrentLinkedQueue<>();
            } else {
                queue = LocalCache.q();
            }
            this.recencyQueue = queue;
            if (localCache.g()) {
                queue2 = new aa<>();
            } else {
                queue2 = LocalCache.q();
            }
            this.writeQueue = queue2;
            if (localCache.f()) {
                queue3 = new c<>();
            } else {
                queue3 = LocalCache.q();
            }
            this.accessQueue = queue3;
        }

        /* access modifiers changed from: package-private */
        public AtomicReferenceArray<g<K, V>> newEntryArray(int i) {
            return new AtomicReferenceArray<>(i);
        }

        /* access modifiers changed from: package-private */
        public void initTable(AtomicReferenceArray<g<K, V>> atomicReferenceArray) {
            this.threshold = (atomicReferenceArray.length() * 3) / 4;
            if (!this.map.b()) {
                int i = this.threshold;
                if (((long) i) == this.maxSegmentWeight) {
                    this.threshold = i + 1;
                }
            }
            this.table = atomicReferenceArray;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: com.google.common.cache.LocalCache$EntryFactory */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public g<K, V> newEntry(K k, int i, g<K, V> gVar) {
            return this.map.r.newEntry(this, com.google.common.base.m.a(k), i, gVar);
        }

        /* access modifiers changed from: package-private */
        public g<K, V> copyEntry(g<K, V> gVar, g<K, V> gVar2) {
            if (gVar.getKey() == null) {
                return null;
            }
            q<K, V> valueReference = gVar.getValueReference();
            Object obj = valueReference.get();
            if (obj == null && valueReference.d()) {
                return null;
            }
            g<K, V> copyEntry = this.map.r.copyEntry(this, gVar, gVar2);
            copyEntry.setValueReference(valueReference.a(this.valueReferenceQueue, obj, copyEntry));
            return copyEntry;
        }

        /* access modifiers changed from: package-private */
        public void setValue(g<K, V> gVar, K k, V v, long j) {
            q<K, V> valueReference = gVar.getValueReference();
            int weigh = this.map.k.weigh(k, v);
            com.google.common.base.m.b(weigh >= 0, "Weights must be non-negative");
            gVar.setValueReference(this.map.i.referenceValue(this, gVar, v, weigh));
            recordWrite(gVar, weigh, j);
            valueReference.a(v);
        }

        /* access modifiers changed from: package-private */
        public V get(K k, int i, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            g<K, V> entry;
            com.google.common.base.m.a(k);
            com.google.common.base.m.a(cacheLoader);
            try {
                if (!(this.count == 0 || (entry = getEntry(k, i)) == null)) {
                    long a = this.map.q.a();
                    Object liveValue = getLiveValue(entry, a);
                    if (liveValue != null) {
                        recordRead(entry, a);
                        this.statsCounter.a(1);
                        V v = (V) scheduleRefresh(entry, k, i, liveValue, a, cacheLoader);
                        postReadCleanup();
                        return v;
                    }
                    q<K, V> valueReference = entry.getValueReference();
                    if (valueReference.c()) {
                        V v2 = (V) waitForLoadingValue(entry, k, valueReference);
                        postReadCleanup();
                        return v2;
                    }
                }
                V v3 = (V) lockedGetOrLoad(k, i, cacheLoader);
                postReadCleanup();
                return v3;
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause instanceof Error) {
                    throw new ExecutionError((Error) cause);
                } else if (cause instanceof RuntimeException) {
                    throw new UncheckedExecutionException(cause);
                } else {
                    throw e;
                }
            } catch (Throwable th) {
                postReadCleanup();
                throw th;
            }
        }

        /* access modifiers changed from: package-private */
        public V get(Object obj, int i) {
            try {
                if (this.count != 0) {
                    long a = this.map.q.a();
                    g<K, V> liveEntry = getLiveEntry(obj, i, a);
                    if (liveEntry == null) {
                        return null;
                    }
                    Object obj2 = liveEntry.getValueReference().get();
                    if (obj2 != null) {
                        recordRead(liveEntry, a);
                        V v = (V) scheduleRefresh(liveEntry, liveEntry.getKey(), i, obj2, a, this.map.t);
                        postReadCleanup();
                        return v;
                    }
                    tryDrainReferenceQueues();
                }
                postReadCleanup();
                return null;
            } finally {
                postReadCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public V lockedGetOrLoad(K k, int i, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            i iVar;
            boolean z;
            q<K, V> qVar;
            V v;
            lock();
            try {
                long a = this.map.q.a();
                preWriteCleanup(a);
                int i2 = 1;
                int i3 = this.count - 1;
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                g<K, V> gVar = atomicReferenceArray.get(length);
                g<K, V> gVar2 = gVar;
                while (true) {
                    iVar = null;
                    if (gVar2 == null) {
                        z = true;
                        qVar = null;
                        break;
                    }
                    Object key = gVar2.getKey();
                    if (gVar2.getHash() != i || key == null || !this.map.f.equivalent(k, key)) {
                        gVar2 = gVar2.getNext();
                    } else {
                        q<K, V> valueReference = gVar2.getValueReference();
                        if (valueReference.c()) {
                            z = false;
                        } else {
                            V v2 = (V) valueReference.get();
                            if (v2 == null) {
                                enqueueNotification(key, i, v2, valueReference.a(), RemovalCause.COLLECTED);
                            } else if (this.map.b(gVar2, a)) {
                                enqueueNotification(key, i, v2, valueReference.a(), RemovalCause.EXPIRED);
                            } else {
                                recordLockedRead(gVar2, a);
                                this.statsCounter.a(1);
                                unlock();
                                postWriteCleanup();
                                return v2;
                            }
                            this.writeQueue.remove(gVar2);
                            this.accessQueue.remove(gVar2);
                            this.count = i3;
                            z = true;
                        }
                        qVar = valueReference;
                    }
                }
                if (z) {
                    iVar = new i();
                    if (gVar2 == null) {
                        gVar2 = newEntry(k, i, gVar);
                        gVar2.setValueReference(iVar);
                        atomicReferenceArray.set(length, gVar2);
                    } else {
                        gVar2.setValueReference(iVar);
                    }
                }
                if (!z) {
                    return (V) waitForLoadingValue(gVar2, k, qVar);
                }
                try {
                    synchronized (gVar2) {
                        v = (V) loadSync(k, i, iVar, cacheLoader);
                    }
                    return v;
                } finally {
                    this.statsCounter.b(i2);
                }
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public V waitForLoadingValue(g<K, V> gVar, K k, q<K, V> qVar) throws ExecutionException {
            if (qVar.c()) {
                int i = 1;
                com.google.common.base.m.b(!Thread.holdsLock(gVar), "Recursive load of: %s", k);
                try {
                    V v = (V) qVar.e();
                    if (v != null) {
                        recordRead(gVar, this.map.q.a());
                        return v;
                    }
                    throw new CacheLoader.InvalidCacheLoadException("CacheLoader returned null for key " + ((Object) k) + ".");
                } finally {
                    this.statsCounter.b(i);
                }
            } else {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public V loadSync(K k, int i, i<K, V> iVar, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
            return (V) getAndRecordStats(k, i, iVar, iVar.a(k, cacheLoader));
        }

        /* access modifiers changed from: package-private */
        public com.google.common.util.concurrent.p<V> loadAsync(K k, int i, i<K, V> iVar, CacheLoader<? super K, V> cacheLoader) {
            com.google.common.util.concurrent.p<V> a = iVar.a(k, cacheLoader);
            a.a(new AnonymousClass1(k, i, iVar, a), com.google.common.util.concurrent.t.a());
            return a;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.cache.LocalCache$Segment$1  reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ Object a;
            final /* synthetic */ int b;
            final /* synthetic */ i c;
            final /* synthetic */ com.google.common.util.concurrent.p d;

            AnonymousClass1(Object obj, int i, i iVar, com.google.common.util.concurrent.p pVar) {
                this.a = obj;
                this.b = i;
                this.c = iVar;
                this.d = pVar;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: com.google.common.cache.LocalCache$Segment */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Segment.this.getAndRecordStats(this.a, this.b, this.c, this.d);
                } catch (Throwable th) {
                    LocalCache.a.log(Level.WARNING, "Exception thrown during refresh", th);
                    this.c.a(th);
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0045  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V getAndRecordStats(K r4, int r5, com.google.common.cache.LocalCache.i<K, V> r6, com.google.common.util.concurrent.p<V> r7) throws java.util.concurrent.ExecutionException {
            /*
                r3 = this;
                java.lang.Object r7 = com.google.common.util.concurrent.w.a(r7)     // Catch:{ all -> 0x0041 }
                if (r7 == 0) goto L_0x0023
                com.google.common.cache.a$b r0 = r3.statsCounter     // Catch:{ all -> 0x0021 }
                long r1 = r6.f()     // Catch:{ all -> 0x0021 }
                r0.a(r1)     // Catch:{ all -> 0x0021 }
                r3.storeLoadedValue(r4, r5, r6, r7)     // Catch:{ all -> 0x0021 }
                if (r7 != 0) goto L_0x0020
                com.google.common.cache.a$b r0 = r3.statsCounter
                long r1 = r6.f()
                r0.b(r1)
                r3.removeLoadingValue(r4, r5, r6)
            L_0x0020:
                return r7
            L_0x0021:
                r0 = move-exception
                goto L_0x0043
            L_0x0023:
                com.google.common.cache.CacheLoader$InvalidCacheLoadException r0 = new com.google.common.cache.CacheLoader$InvalidCacheLoadException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "CacheLoader returned null for key "
                r1.append(r2)
                r1.append(r4)
                java.lang.String r2 = "."
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x0041:
                r0 = move-exception
                r7 = 0
            L_0x0043:
                if (r7 != 0) goto L_0x0051
                com.google.common.cache.a$b r7 = r3.statsCounter
                long r1 = r6.f()
                r7.b(r1)
                r3.removeLoadingValue(r4, r5, r6)
            L_0x0051:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.LocalCache.Segment.getAndRecordStats(java.lang.Object, int, com.google.common.cache.LocalCache$i, com.google.common.util.concurrent.p):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        public V scheduleRefresh(g<K, V> gVar, K k, int i, V v, long j, CacheLoader<? super K, V> cacheLoader) {
            V v2;
            return (!this.map.e() || j - gVar.getWriteTime() <= this.map.n || gVar.getValueReference().c() || (v2 = (V) refresh(k, i, cacheLoader, true)) == null) ? v : v2;
        }

        /* access modifiers changed from: package-private */
        public V refresh(K k, int i, CacheLoader<? super K, V> cacheLoader, boolean z) {
            i<K, V> insertLoadingValueReference = insertLoadingValueReference(k, i, z);
            if (insertLoadingValueReference == null) {
                return null;
            }
            com.google.common.util.concurrent.p<V> loadAsync = loadAsync(k, i, insertLoadingValueReference, cacheLoader);
            if (loadAsync.isDone()) {
                try {
                    return (V) com.google.common.util.concurrent.w.a(loadAsync);
                } catch (Throwable unused) {
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public i<K, V> insertLoadingValueReference(K k, int i, boolean z) {
            lock();
            try {
                long a = this.map.q.a();
                preWriteCleanup(a);
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                g<K, V> gVar = atomicReferenceArray.get(length);
                for (g<K, V> gVar2 = gVar; gVar2 != null; gVar2 = gVar2.getNext()) {
                    Object key = gVar2.getKey();
                    if (gVar2.getHash() == i && key != null && this.map.f.equivalent(k, key)) {
                        q<K, V> valueReference = gVar2.getValueReference();
                        if (!valueReference.c()) {
                            if (!z || a - gVar2.getWriteTime() >= this.map.n) {
                                this.modCount++;
                                i<K, V> iVar = new i<>(valueReference);
                                gVar2.setValueReference(iVar);
                                unlock();
                                postWriteCleanup();
                                return iVar;
                            }
                        }
                        return null;
                    }
                }
                this.modCount++;
                i<K, V> iVar2 = new i<>();
                g<K, V> newEntry = newEntry(k, i, gVar);
                newEntry.setValueReference(iVar2);
                atomicReferenceArray.set(length, newEntry);
                unlock();
                postWriteCleanup();
                return iVar2;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void drainReferenceQueues() {
            if (this.map.m()) {
                drainKeyReferenceQueue();
            }
            if (this.map.n()) {
                drainValueReferenceQueue();
            }
        }

        /* access modifiers changed from: package-private */
        public void drainKeyReferenceQueue() {
            int i = 0;
            do {
                Reference<? extends K> poll = this.keyReferenceQueue.poll();
                if (poll != null) {
                    this.map.a((g) poll);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        public void drainValueReferenceQueue() {
            int i = 0;
            do {
                Reference<? extends V> poll = this.valueReferenceQueue.poll();
                if (poll != null) {
                    this.map.a((q) poll);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* access modifiers changed from: package-private */
        public void clearReferenceQueues() {
            if (this.map.m()) {
                clearKeyReferenceQueue();
            }
            if (this.map.n()) {
                clearValueReferenceQueue();
            }
        }

        /* access modifiers changed from: package-private */
        public void clearKeyReferenceQueue() {
            do {
            } while (this.keyReferenceQueue.poll() != null);
        }

        /* access modifiers changed from: package-private */
        public void clearValueReferenceQueue() {
            do {
            } while (this.valueReferenceQueue.poll() != null);
        }

        /* access modifiers changed from: package-private */
        public void recordRead(g<K, V> gVar, long j) {
            if (this.map.i()) {
                gVar.setAccessTime(j);
            }
            this.recencyQueue.add(gVar);
        }

        /* access modifiers changed from: package-private */
        public void recordLockedRead(g<K, V> gVar, long j) {
            if (this.map.i()) {
                gVar.setAccessTime(j);
            }
            this.accessQueue.add(gVar);
        }

        /* access modifiers changed from: package-private */
        public void recordWrite(g<K, V> gVar, int i, long j) {
            drainRecencyQueue();
            this.totalWeight += (long) i;
            if (this.map.i()) {
                gVar.setAccessTime(j);
            }
            if (this.map.h()) {
                gVar.setWriteTime(j);
            }
            this.accessQueue.add(gVar);
            this.writeQueue.add(gVar);
        }

        /* access modifiers changed from: package-private */
        public void drainRecencyQueue() {
            while (true) {
                g<K, V> poll = this.recencyQueue.poll();
                if (poll == null) {
                    return;
                }
                if (this.accessQueue.contains(poll)) {
                    this.accessQueue.add(poll);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void tryExpireEntries(long j) {
            if (tryLock()) {
                try {
                    expireEntries(j);
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void expireEntries(long j) {
            g<K, V> peek;
            g<K, V> peek2;
            drainRecencyQueue();
            do {
                peek = this.writeQueue.peek();
                if (peek == null || !this.map.b(peek, j)) {
                    do {
                        peek2 = this.accessQueue.peek();
                        if (peek2 == null || !this.map.b(peek2, j)) {
                            return;
                        }
                    } while (removeEntry(peek2, peek2.getHash(), RemovalCause.EXPIRED));
                    throw new AssertionError();
                }
            } while (removeEntry(peek, peek.getHash(), RemovalCause.EXPIRED));
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public void enqueueNotification(K k, int i, V v, int i2, RemovalCause removalCause) {
            this.totalWeight -= (long) i2;
            if (removalCause.wasEvicted()) {
                this.statsCounter.a();
            }
            if (this.map.o != LocalCache.v) {
                this.map.o.offer(RemovalNotification.create(k, v, removalCause));
            }
        }

        /* access modifiers changed from: package-private */
        public void evictEntries(g<K, V> gVar) {
            if (this.map.a()) {
                drainRecencyQueue();
                if (((long) gVar.getValueReference().a()) <= this.maxSegmentWeight || removeEntry(gVar, gVar.getHash(), RemovalCause.SIZE)) {
                    while (this.totalWeight > this.maxSegmentWeight) {
                        g<K, V> nextEvictable = getNextEvictable();
                        if (!removeEntry(nextEvictable, nextEvictable.getHash(), RemovalCause.SIZE)) {
                            throw new AssertionError();
                        }
                    }
                    return;
                }
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public g<K, V> getNextEvictable() {
            for (g<K, V> gVar : this.accessQueue) {
                if (gVar.getValueReference().a() > 0) {
                    return gVar;
                }
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        public g<K, V> getFirst(int i) {
            AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
            return atomicReferenceArray.get(i & (atomicReferenceArray.length() - 1));
        }

        /* access modifiers changed from: package-private */
        public g<K, V> getEntry(Object obj, int i) {
            for (g<K, V> first = getFirst(i); first != null; first = first.getNext()) {
                if (first.getHash() == i) {
                    Object key = first.getKey();
                    if (key == null) {
                        tryDrainReferenceQueues();
                    } else if (this.map.f.equivalent(obj, key)) {
                        return first;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public g<K, V> getLiveEntry(Object obj, int i, long j) {
            g<K, V> entry = getEntry(obj, i);
            if (entry == null) {
                return null;
            }
            if (!this.map.b(entry, j)) {
                return entry;
            }
            tryExpireEntries(j);
            return null;
        }

        /* access modifiers changed from: package-private */
        public V getLiveValue(g<K, V> gVar, long j) {
            if (gVar.getKey() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V v = (V) gVar.getValueReference().get();
            if (v == null) {
                tryDrainReferenceQueues();
                return null;
            } else if (!this.map.b(gVar, j)) {
                return v;
            } else {
                tryExpireEntries(j);
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean containsKey(Object obj, int i) {
            try {
                boolean z = false;
                if (this.count != 0) {
                    g<K, V> liveEntry = getLiveEntry(obj, i, this.map.q.a());
                    if (liveEntry == null) {
                        return false;
                    }
                    if (liveEntry.getValueReference().get() != null) {
                        z = true;
                    }
                    postReadCleanup();
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
                    long a = this.map.q.a();
                    AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                    int length = atomicReferenceArray.length();
                    for (int i = 0; i < length; i++) {
                        for (g<K, V> gVar = atomicReferenceArray.get(i); gVar != null; gVar = gVar.getNext()) {
                            Object liveValue = getLiveValue(gVar, a);
                            if (liveValue != null) {
                                if (this.map.g.equivalent(obj, liveValue)) {
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

        /* access modifiers changed from: package-private */
        public V put(K k, int i, V v, boolean z) {
            int i2;
            lock();
            try {
                long a = this.map.q.a();
                preWriteCleanup(a);
                if (this.count + 1 > this.threshold) {
                    expand();
                    int i3 = this.count;
                }
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                g<K, V> gVar = atomicReferenceArray.get(length);
                g<K, V> gVar2 = gVar;
                while (true) {
                    if (gVar2 == null) {
                        this.modCount++;
                        g<K, V> newEntry = newEntry(k, i, gVar);
                        setValue(newEntry, k, v, a);
                        atomicReferenceArray.set(length, newEntry);
                        this.count++;
                        evictEntries(newEntry);
                        break;
                    }
                    Object key = gVar2.getKey();
                    if (gVar2.getHash() != i || key == null || !this.map.f.equivalent(k, key)) {
                        gVar2 = gVar2.getNext();
                    } else {
                        q<K, V> valueReference = gVar2.getValueReference();
                        V v2 = (V) valueReference.get();
                        if (v2 == null) {
                            this.modCount++;
                            if (valueReference.d()) {
                                enqueueNotification(k, i, v2, valueReference.a(), RemovalCause.COLLECTED);
                                setValue(gVar2, k, v, a);
                                i2 = this.count;
                            } else {
                                setValue(gVar2, k, v, a);
                                i2 = this.count + 1;
                            }
                            this.count = i2;
                            evictEntries(gVar2);
                        } else {
                            if (z) {
                                recordLockedRead(gVar2, a);
                            } else {
                                this.modCount++;
                                enqueueNotification(k, i, v2, valueReference.a(), RemovalCause.REPLACED);
                                setValue(gVar2, k, v, a);
                                evictEntries(gVar2);
                            }
                            unlock();
                            postWriteCleanup();
                            return v2;
                        }
                    }
                }
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public void expand() {
            AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
            int length = atomicReferenceArray.length();
            if (length < 1073741824) {
                int i = this.count;
                AtomicReferenceArray<g<K, V>> newEntryArray = newEntryArray(length << 1);
                this.threshold = (newEntryArray.length() * 3) / 4;
                int length2 = newEntryArray.length() - 1;
                for (int i2 = 0; i2 < length; i2++) {
                    g<K, V> gVar = atomicReferenceArray.get(i2);
                    if (gVar != null) {
                        g<K, V> next = gVar.getNext();
                        int hash = gVar.getHash() & length2;
                        if (next == null) {
                            newEntryArray.set(hash, gVar);
                        } else {
                            g<K, V> gVar2 = gVar;
                            while (next != null) {
                                int hash2 = next.getHash() & length2;
                                if (hash2 != hash) {
                                    gVar2 = next;
                                    hash = hash2;
                                }
                                next = next.getNext();
                            }
                            newEntryArray.set(hash, gVar2);
                            while (gVar != gVar2) {
                                int hash3 = gVar.getHash() & length2;
                                g<K, V> copyEntry = copyEntry(gVar, newEntryArray.get(hash3));
                                if (copyEntry != null) {
                                    newEntryArray.set(hash3, copyEntry);
                                } else {
                                    removeCollectedEntry(gVar);
                                    i--;
                                }
                                gVar = gVar.getNext();
                            }
                        }
                    }
                }
                this.table = newEntryArray;
                this.count = i;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean replace(K k, int i, V v, V v2) {
            lock();
            try {
                long a = this.map.q.a();
                preWriteCleanup(a);
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                g<K, V> gVar = atomicReferenceArray.get(length);
                g<K, V> gVar2 = gVar;
                while (true) {
                    if (gVar2 == null) {
                        break;
                    }
                    Object key = gVar2.getKey();
                    if (gVar2.getHash() == i && key != null) {
                        if (this.map.f.equivalent(k, key)) {
                            q<K, V> valueReference = gVar2.getValueReference();
                            Object obj = valueReference.get();
                            if (obj == null) {
                                if (valueReference.d()) {
                                    int i2 = this.count;
                                    this.modCount++;
                                    atomicReferenceArray.set(length, removeValueFromChain(gVar, gVar2, key, i, obj, valueReference, RemovalCause.COLLECTED));
                                    this.count--;
                                }
                            } else if (this.map.g.equivalent(v, obj)) {
                                this.modCount++;
                                enqueueNotification(k, i, obj, valueReference.a(), RemovalCause.REPLACED);
                                setValue(gVar2, k, v2, a);
                                evictEntries(gVar2);
                                unlock();
                                postWriteCleanup();
                                return true;
                            } else {
                                recordLockedRead(gVar2, a);
                            }
                        }
                    }
                    gVar2 = gVar2.getNext();
                }
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public V replace(K k, int i, V v) {
            lock();
            try {
                long a = this.map.q.a();
                preWriteCleanup(a);
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                g<K, V> gVar = atomicReferenceArray.get(length);
                g<K, V> gVar2 = gVar;
                while (true) {
                    if (gVar2 == null) {
                        break;
                    }
                    Object key = gVar2.getKey();
                    if (gVar2.getHash() == i && key != null) {
                        if (this.map.f.equivalent(k, key)) {
                            q<K, V> valueReference = gVar2.getValueReference();
                            V v2 = (V) valueReference.get();
                            if (v2 != null) {
                                this.modCount++;
                                enqueueNotification(k, i, v2, valueReference.a(), RemovalCause.REPLACED);
                                setValue(gVar2, k, v, a);
                                evictEntries(gVar2);
                                unlock();
                                postWriteCleanup();
                                return v2;
                            } else if (valueReference.d()) {
                                int i2 = this.count;
                                this.modCount++;
                                atomicReferenceArray.set(length, removeValueFromChain(gVar, gVar2, key, i, v2, valueReference, RemovalCause.COLLECTED));
                                this.count--;
                            }
                        }
                    }
                    gVar2 = gVar2.getNext();
                }
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public V remove(Object obj, int i) {
            RemovalCause removalCause;
            lock();
            try {
                preWriteCleanup(this.map.q.a());
                int i2 = this.count;
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                g<K, V> gVar = atomicReferenceArray.get(length);
                g<K, V> gVar2 = gVar;
                while (true) {
                    if (gVar2 == null) {
                        break;
                    }
                    Object key = gVar2.getKey();
                    if (gVar2.getHash() != i || key == null || !this.map.f.equivalent(obj, key)) {
                        gVar2 = gVar2.getNext();
                    } else {
                        q<K, V> valueReference = gVar2.getValueReference();
                        V v = (V) valueReference.get();
                        if (v != null) {
                            removalCause = RemovalCause.EXPLICIT;
                        } else if (valueReference.d()) {
                            removalCause = RemovalCause.COLLECTED;
                        }
                        this.modCount++;
                        atomicReferenceArray.set(length, removeValueFromChain(gVar, gVar2, key, i, v, valueReference, removalCause));
                        this.count--;
                        return v;
                    }
                }
                unlock();
                postWriteCleanup();
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean remove(Object obj, int i, Object obj2) {
            RemovalCause removalCause;
            lock();
            try {
                preWriteCleanup(this.map.q.a());
                int i2 = this.count;
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                boolean z = true;
                int length = (atomicReferenceArray.length() - 1) & i;
                g<K, V> gVar = atomicReferenceArray.get(length);
                g<K, V> gVar2 = gVar;
                while (true) {
                    if (gVar2 == null) {
                        break;
                    }
                    Object key = gVar2.getKey();
                    if (gVar2.getHash() != i || key == null || !this.map.f.equivalent(obj, key)) {
                        gVar2 = gVar2.getNext();
                    } else {
                        q<K, V> valueReference = gVar2.getValueReference();
                        Object obj3 = valueReference.get();
                        if (this.map.g.equivalent(obj2, obj3)) {
                            removalCause = RemovalCause.EXPLICIT;
                        } else if (obj3 == null && valueReference.d()) {
                            removalCause = RemovalCause.COLLECTED;
                        }
                        this.modCount++;
                        atomicReferenceArray.set(length, removeValueFromChain(gVar, gVar2, key, i, obj3, valueReference, removalCause));
                        this.count--;
                        if (removalCause != RemovalCause.EXPLICIT) {
                            z = false;
                        }
                        return z;
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean storeLoadedValue(K k, int i, i<K, V> iVar, V v) {
            lock();
            try {
                long a = this.map.q.a();
                preWriteCleanup(a);
                int i2 = this.count + 1;
                if (i2 > this.threshold) {
                    expand();
                    i2 = this.count + 1;
                }
                int i3 = i2;
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                int length = i & (atomicReferenceArray.length() - 1);
                g<K, V> gVar = atomicReferenceArray.get(length);
                g<K, V> gVar2 = gVar;
                while (true) {
                    if (gVar2 == null) {
                        this.modCount++;
                        g<K, V> newEntry = newEntry(k, i, gVar);
                        setValue(newEntry, k, v, a);
                        atomicReferenceArray.set(length, newEntry);
                        this.count = i3;
                        evictEntries(newEntry);
                        break;
                    }
                    Object key = gVar2.getKey();
                    if (gVar2.getHash() != i || key == null || !this.map.f.equivalent(k, key)) {
                        gVar2 = gVar2.getNext();
                    } else {
                        q<K, V> valueReference = gVar2.getValueReference();
                        Object obj = valueReference.get();
                        if (iVar != valueReference) {
                            if (obj != null || valueReference == LocalCache.u) {
                                enqueueNotification(k, i, v, 0, RemovalCause.REPLACED);
                                unlock();
                                postWriteCleanup();
                                return false;
                            }
                        }
                        this.modCount++;
                        if (iVar.d()) {
                            enqueueNotification(k, i, obj, iVar.a(), obj == null ? RemovalCause.COLLECTED : RemovalCause.REPLACED);
                            i3--;
                        }
                        setValue(gVar2, k, v, a);
                        this.count = i3;
                        evictEntries(gVar2);
                    }
                }
                return true;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            RemovalCause removalCause;
            if (this.count != 0) {
                lock();
                try {
                    preWriteCleanup(this.map.q.a());
                    AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                    for (int i = 0; i < atomicReferenceArray.length(); i++) {
                        for (g<K, V> gVar = atomicReferenceArray.get(i); gVar != null; gVar = gVar.getNext()) {
                            if (gVar.getValueReference().d()) {
                                Object key = gVar.getKey();
                                Object obj = gVar.getValueReference().get();
                                if (key != null) {
                                    if (obj != null) {
                                        removalCause = RemovalCause.EXPLICIT;
                                        enqueueNotification(key, gVar.getHash(), obj, gVar.getValueReference().a(), removalCause);
                                    }
                                }
                                removalCause = RemovalCause.COLLECTED;
                                enqueueNotification(key, gVar.getHash(), obj, gVar.getValueReference().a(), removalCause);
                            }
                        }
                    }
                    for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
                        atomicReferenceArray.set(i2, null);
                    }
                    clearReferenceQueues();
                    this.writeQueue.clear();
                    this.accessQueue.clear();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                    postWriteCleanup();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public g<K, V> removeValueFromChain(g<K, V> gVar, g<K, V> gVar2, K k, int i, V v, q<K, V> qVar, RemovalCause removalCause) {
            enqueueNotification(k, i, v, qVar.a(), removalCause);
            this.writeQueue.remove(gVar2);
            this.accessQueue.remove(gVar2);
            if (!qVar.c()) {
                return removeEntryFromChain(gVar, gVar2);
            }
            qVar.a(null);
            return gVar;
        }

        /* access modifiers changed from: package-private */
        public g<K, V> removeEntryFromChain(g<K, V> gVar, g<K, V> gVar2) {
            int i = this.count;
            g<K, V> next = gVar2.getNext();
            while (gVar != gVar2) {
                g<K, V> copyEntry = copyEntry(gVar, next);
                if (copyEntry != null) {
                    next = copyEntry;
                } else {
                    removeCollectedEntry(gVar);
                    i--;
                }
                gVar = gVar.getNext();
            }
            this.count = i;
            return next;
        }

        /* access modifiers changed from: package-private */
        public void removeCollectedEntry(g<K, V> gVar) {
            enqueueNotification(gVar.getKey(), gVar.getHash(), gVar.getValueReference().get(), gVar.getValueReference().a(), RemovalCause.COLLECTED);
            this.writeQueue.remove(gVar);
            this.accessQueue.remove(gVar);
        }

        /* access modifiers changed from: package-private */
        public boolean reclaimKey(g<K, V> gVar, int i) {
            lock();
            try {
                int i2 = this.count;
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                g<K, V> gVar2 = atomicReferenceArray.get(length);
                for (g<K, V> gVar3 = gVar2; gVar3 != null; gVar3 = gVar3.getNext()) {
                    if (gVar3 == gVar) {
                        this.modCount++;
                        atomicReferenceArray.set(length, removeValueFromChain(gVar2, gVar3, gVar3.getKey(), i, gVar3.getValueReference().get(), gVar3.getValueReference(), RemovalCause.COLLECTED));
                        this.count--;
                        return true;
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean reclaimValue(K k, int i, q<K, V> qVar) {
            lock();
            try {
                int i2 = this.count;
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                g<K, V> gVar = atomicReferenceArray.get(length);
                for (g<K, V> gVar2 = gVar; gVar2 != null; gVar2 = gVar2.getNext()) {
                    Object key = gVar2.getKey();
                    if (gVar2.getHash() == i && key != null && this.map.f.equivalent(k, key)) {
                        if (gVar2.getValueReference() == qVar) {
                            this.modCount++;
                            atomicReferenceArray.set(length, removeValueFromChain(gVar, gVar2, key, i, qVar.get(), qVar, RemovalCause.COLLECTED));
                            this.count--;
                            return true;
                        } else {
                            unlock();
                            if (!isHeldByCurrentThread()) {
                                postWriteCleanup();
                            }
                            return false;
                        }
                    }
                }
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
                return false;
            } finally {
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean removeLoadingValue(K k, int i, i<K, V> iVar) {
            lock();
            try {
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
                int length = (atomicReferenceArray.length() - 1) & i;
                g<K, V> gVar = atomicReferenceArray.get(length);
                g<K, V> gVar2 = gVar;
                while (true) {
                    if (gVar2 == null) {
                        break;
                    }
                    Object key = gVar2.getKey();
                    if (gVar2.getHash() != i || key == null || !this.map.f.equivalent(k, key)) {
                        gVar2 = gVar2.getNext();
                    } else if (gVar2.getValueReference() == iVar) {
                        if (iVar.d()) {
                            gVar2.setValueReference(iVar.g());
                        } else {
                            atomicReferenceArray.set(length, removeEntryFromChain(gVar, gVar2));
                        }
                        return true;
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean removeEntry(g<K, V> gVar, int i, RemovalCause removalCause) {
            int i2 = this.count;
            AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.table;
            int length = (atomicReferenceArray.length() - 1) & i;
            g<K, V> gVar2 = atomicReferenceArray.get(length);
            for (g<K, V> gVar3 = gVar2; gVar3 != null; gVar3 = gVar3.getNext()) {
                if (gVar3 == gVar) {
                    this.modCount++;
                    atomicReferenceArray.set(length, removeValueFromChain(gVar2, gVar3, gVar3.getKey(), i, gVar3.getValueReference().get(), gVar3.getValueReference(), removalCause));
                    this.count--;
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                cleanUp();
            }
        }

        /* access modifiers changed from: package-private */
        public void preWriteCleanup(long j) {
            runLockedCleanup(j);
        }

        /* access modifiers changed from: package-private */
        public void postWriteCleanup() {
            runUnlockedCleanup();
        }

        /* access modifiers changed from: package-private */
        public void cleanUp() {
            runLockedCleanup(this.map.q.a());
            runUnlockedCleanup();
        }

        /* access modifiers changed from: package-private */
        public void runLockedCleanup(long j) {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                    expireEntries(j);
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void runUnlockedCleanup() {
            if (!isHeldByCurrentThread()) {
                this.map.r();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class i<K, V> implements q<K, V> {
        volatile q<K, V> a;
        final com.google.common.util.concurrent.u<V> b;
        final com.google.common.base.o c;

        @Override // com.google.common.cache.LocalCache.q
        public q<K, V> a(ReferenceQueue<V> referenceQueue, V v, g<K, V> gVar) {
            return this;
        }

        @Override // com.google.common.cache.LocalCache.q
        public g<K, V> b() {
            return null;
        }

        @Override // com.google.common.cache.LocalCache.q
        public boolean c() {
            return true;
        }

        public i() {
            this(LocalCache.o());
        }

        public i(q<K, V> qVar) {
            this.b = com.google.common.util.concurrent.u.h();
            this.c = com.google.common.base.o.a();
            this.a = qVar;
        }

        @Override // com.google.common.cache.LocalCache.q
        public boolean d() {
            return this.a.d();
        }

        @Override // com.google.common.cache.LocalCache.q
        public int a() {
            return this.a.a();
        }

        public boolean b(V v) {
            return this.b.a((com.google.common.util.concurrent.u<V>) v);
        }

        public boolean a(Throwable th) {
            return this.b.a(th);
        }

        private com.google.common.util.concurrent.p<V> b(Throwable th) {
            return com.google.common.util.concurrent.l.a(th);
        }

        @Override // com.google.common.cache.LocalCache.q
        public void a(V v) {
            if (v != null) {
                b((i<K, V>) v);
            } else {
                this.a = LocalCache.o();
            }
        }

        public com.google.common.util.concurrent.p<V> a(K k, CacheLoader<? super K, V> cacheLoader) {
            try {
                this.c.c();
                Object obj = this.a.get();
                if (obj == null) {
                    Object load = cacheLoader.load(k);
                    return b(load) ? this.b : com.google.common.util.concurrent.l.a(load);
                }
                com.google.common.util.concurrent.p<V> reload = cacheLoader.reload(k, obj);
                if (reload == null) {
                    return com.google.common.util.concurrent.l.a((Object) null);
                }
                return com.google.common.util.concurrent.l.a(reload, new AnonymousClass1(), com.google.common.util.concurrent.t.a());
            } catch (Throwable th) {
                com.google.common.util.concurrent.p<V> b = a(th) ? this.b : b(th);
                if (th instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                return b;
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.cache.LocalCache$i$1  reason: invalid class name */
        public class AnonymousClass1 implements com.google.common.base.g<V, V> {
            AnonymousClass1() {
            }

            @Override // com.google.common.base.g
            public V apply(V v) {
                i.this.b((i) v);
                return v;
            }
        }

        public long f() {
            return this.c.a(TimeUnit.NANOSECONDS);
        }

        @Override // com.google.common.cache.LocalCache.q
        public V e() throws ExecutionException {
            return (V) com.google.common.util.concurrent.w.a(this.b);
        }

        @Override // com.google.common.cache.LocalCache.q
        public V get() {
            return (V) this.a.get();
        }

        public q<K, V> g() {
            return this.a;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class aa<K, V> extends AbstractQueue<g<K, V>> {
        final g<K, V> a = new AnonymousClass1();

        aa() {
        }

        /* renamed from: com.google.common.cache.LocalCache$aa$1  reason: invalid class name */
        class AnonymousClass1 extends b<K, V> {
            g<K, V> a = this;
            g<K, V> b = this;

            @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
            public long getWriteTime() {
                return Long.MAX_VALUE;
            }

            @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
            public void setWriteTime(long j) {
            }

            AnonymousClass1() {
            }

            @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
            public g<K, V> getNextInWriteQueue() {
                return this.a;
            }

            @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
            public void setNextInWriteQueue(g<K, V> gVar) {
                this.a = gVar;
            }

            @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
            public g<K, V> getPreviousInWriteQueue() {
                return this.b;
            }

            @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
            public void setPreviousInWriteQueue(g<K, V> gVar) {
                this.b = gVar;
            }
        }

        /* renamed from: a */
        public boolean offer(g<K, V> gVar) {
            LocalCache.b(gVar.getPreviousInWriteQueue(), gVar.getNextInWriteQueue());
            LocalCache.b(this.a.getPreviousInWriteQueue(), gVar);
            LocalCache.b(gVar, this.a);
            return true;
        }

        /* renamed from: a */
        public g<K, V> peek() {
            g<K, V> nextInWriteQueue = this.a.getNextInWriteQueue();
            if (nextInWriteQueue == this.a) {
                return null;
            }
            return nextInWriteQueue;
        }

        /* renamed from: b */
        public g<K, V> poll() {
            g<K, V> nextInWriteQueue = this.a.getNextInWriteQueue();
            if (nextInWriteQueue == this.a) {
                return null;
            }
            remove(nextInWriteQueue);
            return nextInWriteQueue;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            g gVar = (g) obj;
            g<K, V> previousInWriteQueue = gVar.getPreviousInWriteQueue();
            g<K, V> nextInWriteQueue = gVar.getNextInWriteQueue();
            LocalCache.b(previousInWriteQueue, nextInWriteQueue);
            LocalCache.c(gVar);
            return nextInWriteQueue != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return ((g) obj).getNextInWriteQueue() != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.a.getNextInWriteQueue() == this.a;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i = 0;
            for (g<K, V> nextInWriteQueue = this.a.getNextInWriteQueue(); nextInWriteQueue != this.a; nextInWriteQueue = nextInWriteQueue.getNextInWriteQueue()) {
                i++;
            }
            return i;
        }

        @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            g<K, V> nextInWriteQueue = this.a.getNextInWriteQueue();
            while (true) {
                g<K, V> gVar = this.a;
                if (nextInWriteQueue != gVar) {
                    g<K, V> nextInWriteQueue2 = nextInWriteQueue.getNextInWriteQueue();
                    LocalCache.c((g) nextInWriteQueue);
                    nextInWriteQueue = nextInWriteQueue2;
                } else {
                    gVar.setNextInWriteQueue(gVar);
                    g<K, V> gVar2 = this.a;
                    gVar2.setPreviousInWriteQueue(gVar2);
                    return;
                }
            }
        }

        /* renamed from: com.google.common.cache.LocalCache$aa$2  reason: invalid class name */
        class AnonymousClass2 extends com.google.common.collect.g<g<K, V>> {
            AnonymousClass2(g gVar) {
                super(gVar);
            }

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.g
            public /* bridge */ /* synthetic */ Object a(Object obj) {
                return a((g) ((g) obj));
            }

            /* access modifiers changed from: protected */
            public g<K, V> a(g<K, V> gVar) {
                g<K, V> nextInWriteQueue = gVar.getNextInWriteQueue();
                if (nextInWriteQueue == aa.this.a) {
                    return null;
                }
                return nextInWriteQueue;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<g<K, V>> iterator() {
            return new AnonymousClass2(peek());
        }
    }

    /* access modifiers changed from: package-private */
    public static final class c<K, V> extends AbstractQueue<g<K, V>> {
        final g<K, V> a = new AnonymousClass1();

        c() {
        }

        /* renamed from: com.google.common.cache.LocalCache$c$1  reason: invalid class name */
        class AnonymousClass1 extends b<K, V> {
            g<K, V> a = this;
            g<K, V> b = this;

            @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
            public long getAccessTime() {
                return Long.MAX_VALUE;
            }

            @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
            public void setAccessTime(long j) {
            }

            AnonymousClass1() {
            }

            @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
            public g<K, V> getNextInAccessQueue() {
                return this.a;
            }

            @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
            public void setNextInAccessQueue(g<K, V> gVar) {
                this.a = gVar;
            }

            @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
            public g<K, V> getPreviousInAccessQueue() {
                return this.b;
            }

            @Override // com.google.common.cache.LocalCache.b, com.google.common.cache.g
            public void setPreviousInAccessQueue(g<K, V> gVar) {
                this.b = gVar;
            }
        }

        /* renamed from: a */
        public boolean offer(g<K, V> gVar) {
            LocalCache.a(gVar.getPreviousInAccessQueue(), gVar.getNextInAccessQueue());
            LocalCache.a(this.a.getPreviousInAccessQueue(), gVar);
            LocalCache.a(gVar, this.a);
            return true;
        }

        /* renamed from: a */
        public g<K, V> peek() {
            g<K, V> nextInAccessQueue = this.a.getNextInAccessQueue();
            if (nextInAccessQueue == this.a) {
                return null;
            }
            return nextInAccessQueue;
        }

        /* renamed from: b */
        public g<K, V> poll() {
            g<K, V> nextInAccessQueue = this.a.getNextInAccessQueue();
            if (nextInAccessQueue == this.a) {
                return null;
            }
            remove(nextInAccessQueue);
            return nextInAccessQueue;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            g gVar = (g) obj;
            g<K, V> previousInAccessQueue = gVar.getPreviousInAccessQueue();
            g<K, V> nextInAccessQueue = gVar.getNextInAccessQueue();
            LocalCache.a(previousInAccessQueue, nextInAccessQueue);
            LocalCache.b(gVar);
            return nextInAccessQueue != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return ((g) obj).getNextInAccessQueue() != NullEntry.INSTANCE;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.a.getNextInAccessQueue() == this.a;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            int i = 0;
            for (g<K, V> nextInAccessQueue = this.a.getNextInAccessQueue(); nextInAccessQueue != this.a; nextInAccessQueue = nextInAccessQueue.getNextInAccessQueue()) {
                i++;
            }
            return i;
        }

        @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            g<K, V> nextInAccessQueue = this.a.getNextInAccessQueue();
            while (true) {
                g<K, V> gVar = this.a;
                if (nextInAccessQueue != gVar) {
                    g<K, V> nextInAccessQueue2 = nextInAccessQueue.getNextInAccessQueue();
                    LocalCache.b((g) nextInAccessQueue);
                    nextInAccessQueue = nextInAccessQueue2;
                } else {
                    gVar.setNextInAccessQueue(gVar);
                    g<K, V> gVar2 = this.a;
                    gVar2.setPreviousInAccessQueue(gVar2);
                    return;
                }
            }
        }

        /* renamed from: com.google.common.cache.LocalCache$c$2  reason: invalid class name */
        class AnonymousClass2 extends com.google.common.collect.g<g<K, V>> {
            AnonymousClass2(g gVar) {
                super(gVar);
            }

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.g
            public /* bridge */ /* synthetic */ Object a(Object obj) {
                return a((g) ((g) obj));
            }

            /* access modifiers changed from: protected */
            public g<K, V> a(g<K, V> gVar) {
                g<K, V> nextInAccessQueue = gVar.getNextInAccessQueue();
                if (nextInAccessQueue == c.this.a) {
                    return null;
                }
                return nextInAccessQueue;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<g<K, V>> iterator() {
            return new AnonymousClass2(peek());
        }
    }

    public void s() {
        for (Segment<K, V> segment : this.d) {
            segment.cleanUp();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        Segment<K, V>[] segmentArr = this.d;
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

    /* access modifiers changed from: package-private */
    public long t() {
        Segment<K, V>[] segmentArr;
        long j2 = 0;
        for (Segment<K, V> segment : this.d) {
            j2 += (long) Math.max(0, segment.count);
        }
        return j2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return Ints.b(t());
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        if (obj == null) {
            return null;
        }
        int a2 = a(obj);
        return (V) b(a2).get(obj, a2);
    }

    /* access modifiers changed from: package-private */
    public V a(K k2, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
        int a2 = a(com.google.common.base.m.a(k2));
        return (V) b(a2).get(k2, a2, cacheLoader);
    }

    public V b(Object obj) {
        int a2 = a(com.google.common.base.m.a(obj));
        V v2 = (V) b(a2).get(obj, a2);
        if (v2 == null) {
            this.s.b(1);
        } else {
            this.s.a(1);
        }
        return v2;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V getOrDefault(Object obj, V v2) {
        V v3 = (V) get(obj);
        return v3 != null ? v3 : v2;
    }

    /* access modifiers changed from: package-private */
    public V c(K k2) throws ExecutionException {
        return (V) a((LocalCache<K, V>) k2, this.t);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.LinkedHashMap */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    public ImmutableMap<K, V> a(Iterable<?> iterable) {
        LinkedHashMap d2 = Maps.d();
        int i2 = 0;
        int i3 = 0;
        for (Object obj : iterable) {
            Object obj2 = get(obj);
            if (obj2 == null) {
                i3++;
            } else {
                d2.put(obj, obj2);
                i2++;
            }
        }
        this.s.a(i2);
        this.s.b(i3);
        return ImmutableMap.copyOf(d2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.LinkedHashMap */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    public ImmutableMap<K, V> b(Iterable<? extends K> iterable) throws ExecutionException {
        LinkedHashMap d2 = Maps.d();
        LinkedHashSet c2 = Sets.c();
        int i2 = 0;
        int i3 = 0;
        for (Object obj : iterable) {
            Object obj2 = get(obj);
            if (!d2.containsKey(obj)) {
                d2.put(obj, obj2);
                if (obj2 == null) {
                    i3++;
                    c2.add(obj);
                } else {
                    i2++;
                }
            }
        }
        try {
            if (!c2.isEmpty()) {
                try {
                    Map<K, V> a2 = a((Set) c2, (CacheLoader) this.t);
                    for (Object obj3 : c2) {
                        V v2 = a2.get(obj3);
                        if (v2 != null) {
                            d2.put(obj3, v2);
                        } else {
                            throw new CacheLoader.InvalidCacheLoadException("loadAll failed to return a value for " + obj3);
                        }
                    }
                } catch (CacheLoader.UnsupportedLoadingOperationException unused) {
                    for (Object obj4 : c2) {
                        i3--;
                        d2.put(obj4, a((LocalCache<K, V>) obj4, this.t));
                    }
                }
            }
            ImmutableMap<K, V> copyOf = ImmutableMap.copyOf(d2);
            return copyOf;
        } finally {
            this.s.a(i2);
            this.s.b(i3);
        }
    }

    /* access modifiers changed from: package-private */
    public Map<K, V> a(Set<? extends K> set, CacheLoader<? super K, V> cacheLoader) throws ExecutionException {
        Throwable th;
        com.google.common.base.m.a(cacheLoader);
        com.google.common.base.m.a(set);
        com.google.common.base.o b2 = com.google.common.base.o.b();
        boolean z2 = false;
        try {
            Map<K, V> loadAll = cacheLoader.loadAll(set);
            if (loadAll != null) {
                b2.d();
                for (Map.Entry<K, V> entry : loadAll.entrySet()) {
                    K key = entry.getKey();
                    V value = entry.getValue();
                    if (key == null || value == null) {
                        z2 = true;
                    } else {
                        put(key, value);
                    }
                }
                if (!z2) {
                    this.s.a(b2.a(TimeUnit.NANOSECONDS));
                    return loadAll;
                }
                this.s.b(b2.a(TimeUnit.NANOSECONDS));
                throw new CacheLoader.InvalidCacheLoadException(cacheLoader + " returned null keys or values from loadAll");
            }
            this.s.b(b2.a(TimeUnit.NANOSECONDS));
            throw new CacheLoader.InvalidCacheLoadException(cacheLoader + " returned null map from loadAll");
        } catch (CacheLoader.UnsupportedLoadingOperationException e2) {
            throw e2;
        } catch (InterruptedException e3) {
            Thread.currentThread().interrupt();
            throw new ExecutionException(e3);
        } catch (RuntimeException e4) {
            throw new UncheckedExecutionException(e4);
        } catch (Exception e5) {
            throw new ExecutionException(e5);
        } catch (Error e6) {
            throw new ExecutionError(e6);
        } catch (Throwable th2) {
            th = th2;
            z2 = true;
        }
        if (!z2) {
            this.s.b(b2.a(TimeUnit.NANOSECONDS));
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public void d(K k2) {
        int a2 = a(com.google.common.base.m.a(k2));
        b(a2).refresh(k2, a2, this.t, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        if (obj == null) {
            return false;
        }
        int a2 = a(obj);
        return b(a2).containsKey(obj, a2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [int] */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object obj) {
        boolean z2 = false;
        if (obj == null) {
            return false;
        }
        long a2 = this.q.a();
        Segment<K, V>[] segmentArr = this.d;
        long j2 = -1;
        int i2 = 0;
        while (i2 < 3) {
            int length = segmentArr.length;
            long j3 = 0;
            int i3 = z2;
            while (i3 < length) {
                Segment<K, V> segment = segmentArr[i3 == true ? 1 : 0];
                int i4 = segment.count;
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = segment.table;
                int i5 = z2;
                while (i5 < atomicReferenceArray.length()) {
                    g<K, V> gVar = atomicReferenceArray.get(i5 == true ? 1 : 0);
                    while (gVar != null) {
                        Object liveValue = segment.getLiveValue(gVar, a2);
                        if (liveValue != null && this.g.equivalent(obj, liveValue)) {
                            return true;
                        }
                        gVar = gVar.getNext();
                        segmentArr = segmentArr;
                        a2 = a2;
                    }
                    i5++;
                }
                j3 += (long) segment.modCount;
                a2 = a2;
                z2 = false;
                i3 = (i3 == true ? 1 : 0) + 1;
            }
            if (j3 == j2) {
                return false;
            }
            i2++;
            j2 = j3;
            segmentArr = segmentArr;
            a2 = a2;
            z2 = false;
        }
        return z2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k2, V v2) {
        com.google.common.base.m.a(k2);
        com.google.common.base.m.a(v2);
        int a2 = a((Object) k2);
        return (V) b(a2).put(k2, a2, v2, false);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K k2, V v2) {
        com.google.common.base.m.a(k2);
        com.google.common.base.m.a(v2);
        int a2 = a((Object) k2);
        return (V) b(a2).put(k2, a2, v2, true);
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
        int a2 = a(obj);
        return (V) b(a2).remove(obj, a2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            return false;
        }
        int a2 = a(obj);
        return b(a2).remove(obj, a2, obj2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K k2, V v2, V v3) {
        com.google.common.base.m.a(k2);
        com.google.common.base.m.a(v3);
        if (v2 == null) {
            return false;
        }
        int a2 = a((Object) k2);
        return b(a2).replace(k2, a2, v2, v3);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K k2, V v2) {
        com.google.common.base.m.a(k2);
        com.google.common.base.m.a(v2);
        int a2 = a((Object) k2);
        return (V) b(a2).replace(k2, a2, v2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (Segment<K, V> segment : this.d) {
            segment.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void c(Iterable<?> iterable) {
        Iterator<?> it2 = iterable.iterator();
        while (it2.hasNext()) {
            remove(it2.next());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.w;
        if (set != null) {
            return set;
        }
        h hVar = new h(this);
        this.w = hVar;
        return hVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.x;
        if (collection != null) {
            return collection;
        }
        r rVar = new r(this);
        this.x = rVar;
        return rVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.y;
        if (set != null) {
            return set;
        }
        e eVar = new e(this);
        this.y = eVar;
        return eVar;
    }

    abstract class f<T> implements Iterator<T> {
        int b;
        int c = -1;
        Segment<K, V> d;
        AtomicReferenceArray<g<K, V>> e;
        g<K, V> f;
        LocalCache<K, V>.ab g;
        LocalCache<K, V>.ab h;

        f() {
            this.b = LocalCache.this.d.length - 1;
            b();
        }

        /* access modifiers changed from: package-private */
        public final void b() {
            this.g = null;
            if (!c() && !d()) {
                while (this.b >= 0) {
                    Segment<K, V>[] segmentArr = LocalCache.this.d;
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

        /* access modifiers changed from: package-private */
        public boolean c() {
            g<K, V> gVar = this.f;
            if (gVar == null) {
                return false;
            }
            while (true) {
                this.f = gVar.getNext();
                g<K, V> gVar2 = this.f;
                if (gVar2 == null) {
                    return false;
                }
                if (a(gVar2)) {
                    return true;
                }
                gVar = this.f;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean d() {
            while (true) {
                int i = this.c;
                if (i < 0) {
                    return false;
                }
                AtomicReferenceArray<g<K, V>> atomicReferenceArray = this.e;
                this.c = i - 1;
                g<K, V> gVar = atomicReferenceArray.get(i);
                this.f = gVar;
                if (gVar != null && (a(this.f) || c())) {
                    return true;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(g<K, V> gVar) {
            boolean z;
            try {
                long a = LocalCache.this.q.a();
                Object key = gVar.getKey();
                Object a2 = LocalCache.this.a(gVar, a);
                if (a2 != null) {
                    this.g = new ab(key, a2);
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
        public LocalCache<K, V>.ab e() {
            LocalCache<K, V>.ab abVar = this.g;
            if (abVar != null) {
                this.h = abVar;
                b();
                return this.h;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            com.google.common.base.m.b(this.h != null);
            LocalCache.this.remove(this.h.getKey());
            this.h = null;
        }
    }

    final class g extends LocalCache<K, V>.f {
        g() {
            super();
        }

        @Override // java.util.Iterator
        public K next() {
            return (K) e().getKey();
        }
    }

    final class p extends LocalCache<K, V>.f {
        p() {
            super();
        }

        @Override // java.util.Iterator
        public V next() {
            return (V) e().getValue();
        }
    }

    /* access modifiers changed from: package-private */
    public final class ab implements Map.Entry<K, V> {
        final K a;
        V b;

        ab(K k, V v) {
            this.a = k;
            this.b = v;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.a;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.b;
        }

        @Override // java.util.Map.Entry, java.lang.Object
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

        @Override // java.util.Map.Entry, java.lang.Object
        public int hashCode() {
            return this.a.hashCode() ^ this.b.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = (V) LocalCache.this.put(this.a, v);
            this.b = v;
            return v2;
        }

        @Override // java.lang.Object
        public String toString() {
            return getKey() + ContainerUtils.KEY_VALUE_DELIMITER + getValue();
        }
    }

    final class d extends LocalCache<K, V>.f {
        d() {
            super();
        }

        /* renamed from: a */
        public Map.Entry<K, V> next() {
            return e();
        }
    }

    abstract class a<T> extends AbstractSet<T> {
        final ConcurrentMap<?, ?> a;

        a(ConcurrentMap<?, ?> concurrentMap) {
            this.a = concurrentMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.a.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.a.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.a.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return LocalCache.b((Collection) this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <E> E[] toArray(E[] eArr) {
            return (E[]) LocalCache.b((Collection) this).toArray(eArr);
        }
    }

    /* access modifiers changed from: private */
    public static <E> ArrayList<E> b(Collection<E> collection) {
        ArrayList<E> arrayList = new ArrayList<>(collection.size());
        Iterators.a(arrayList, collection.iterator());
        return arrayList;
    }

    final class h extends LocalCache<K, V>.a {
        h(ConcurrentMap<?, ?> concurrentMap) {
            super(concurrentMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return new g();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.a.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return this.a.remove(obj) != null;
        }
    }

    final class r extends AbstractCollection<V> {
        private final ConcurrentMap<?, ?> b;

        r(ConcurrentMap<?, ?> concurrentMap) {
            this.b = concurrentMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.b.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.b.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.b.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new p();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return this.b.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return LocalCache.b((Collection) this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <E> E[] toArray(E[] eArr) {
            return (E[]) LocalCache.b((Collection) this).toArray(eArr);
        }
    }

    final class e extends LocalCache<K, V>.a {
        e(ConcurrentMap<?, ?> concurrentMap) {
            super(concurrentMap);
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
            if ((obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (obj2 = LocalCache.this.get(key)) != null && LocalCache.this.g.equivalent(entry.getValue(), obj2)) {
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Map.Entry entry;
            Object key;
            if ((obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && LocalCache.this.remove(key, entry.getValue())) {
                return true;
            }
            return false;
        }
    }

    static class ManualSerializationProxy<K, V> extends d<K, V> implements Serializable {
        private static final long serialVersionUID = 1;
        final int concurrencyLevel;
        transient b<K, V> delegate;
        final long expireAfterAccessNanos;
        final long expireAfterWriteNanos;
        final Equivalence<Object> keyEquivalence;
        final Strength keyStrength;
        final CacheLoader<? super K, V> loader;
        final long maxWeight;
        final h<? super K, ? super V> removalListener;
        final com.google.common.base.s ticker;
        final Equivalence<Object> valueEquivalence;
        final Strength valueStrength;
        final i<K, V> weigher;

        ManualSerializationProxy(LocalCache<K, V> localCache) {
            this(localCache.h, localCache.i, localCache.f, localCache.g, localCache.m, localCache.l, localCache.j, localCache.k, localCache.e, localCache.p, localCache.q, localCache.t);
        }

        private ManualSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long j, long j2, long j3, i<K, V> iVar, int i, h<? super K, ? super V> hVar, com.google.common.base.s sVar, CacheLoader<? super K, V> cacheLoader) {
            this.keyStrength = strength;
            this.valueStrength = strength2;
            this.keyEquivalence = equivalence;
            this.valueEquivalence = equivalence2;
            this.expireAfterWriteNanos = j;
            this.expireAfterAccessNanos = j2;
            this.maxWeight = j3;
            this.weigher = iVar;
            this.concurrencyLevel = i;
            this.removalListener = hVar;
            this.ticker = (sVar == com.google.common.base.s.b() || sVar == CacheBuilder.d) ? null : sVar;
            this.loader = cacheLoader;
        }

        /* access modifiers changed from: package-private */
        public CacheBuilder<K, V> recreateCacheBuilder() {
            CacheBuilder<K, V> a = CacheBuilder.a().a(this.keyStrength).b(this.valueStrength).a(this.keyEquivalence).b(this.valueEquivalence).a(this.concurrencyLevel).a(this.removalListener);
            a.e = false;
            long j = this.expireAfterWriteNanos;
            if (j > 0) {
                a.a(j, TimeUnit.NANOSECONDS);
            }
            long j2 = this.expireAfterAccessNanos;
            if (j2 > 0) {
                a.b(j2, TimeUnit.NANOSECONDS);
            }
            if (this.weigher != CacheBuilder.OneWeigher.INSTANCE) {
                a.a(this.weigher);
                long j3 = this.maxWeight;
                if (j3 != -1) {
                    a.b(j3);
                }
            } else {
                long j4 = this.maxWeight;
                if (j4 != -1) {
                    a.a(j4);
                }
            }
            com.google.common.base.s sVar = this.ticker;
            if (sVar != null) {
                a.a(sVar);
            }
            return a;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.delegate = recreateCacheBuilder().o();
        }

        private Object readResolve() {
            return this.delegate;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.cache.d, com.google.common.collect.z
        public b<K, V> delegate() {
            return this.delegate;
        }
    }

    static final class LoadingSerializationProxy<K, V> extends ManualSerializationProxy<K, V> implements e<K, V>, Serializable {
        private static final long serialVersionUID = 1;
        transient e<K, V> autoDelegate;

        LoadingSerializationProxy(LocalCache<K, V> localCache) {
            super(localCache);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.autoDelegate = recreateCacheBuilder().a(this.loader);
        }

        @Override // com.google.common.cache.e
        public V get(K k) throws ExecutionException {
            return (V) this.autoDelegate.get(k);
        }

        @Override // com.google.common.cache.e
        public V getUnchecked(K k) {
            return (V) this.autoDelegate.getUnchecked(k);
        }

        @Override // com.google.common.cache.e
        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
            return this.autoDelegate.getAll(iterable);
        }

        @Override // com.google.common.cache.e, com.google.common.base.g
        public final V apply(K k) {
            return (V) this.autoDelegate.apply(k);
        }

        @Override // com.google.common.cache.e
        public void refresh(K k) {
            this.autoDelegate.refresh(k);
        }

        private Object readResolve() {
            return this.autoDelegate;
        }
    }

    /* access modifiers changed from: package-private */
    public static class LocalManualCache<K, V> implements b<K, V>, Serializable {
        private static final long serialVersionUID = 1;
        final LocalCache<K, V> localCache;

        /* synthetic */ LocalManualCache(LocalCache localCache, AnonymousClass1 r2) {
            this(localCache);
        }

        LocalManualCache(CacheBuilder<? super K, ? super V> cacheBuilder) {
            this(new LocalCache(cacheBuilder, null));
        }

        private LocalManualCache(LocalCache<K, V> localCache) {
            this.localCache = localCache;
        }

        @Override // com.google.common.cache.b
        public V getIfPresent(Object obj) {
            return (V) this.localCache.b(obj);
        }

        @Override // com.google.common.cache.b
        public V get(K k, Callable<? extends V> callable) throws ExecutionException {
            com.google.common.base.m.a(callable);
            return (V) this.localCache.a((LocalCache<K, V>) k, (CacheLoader<? super LocalCache<K, V>, Object>) new AnonymousClass1(callable));
        }

        /* renamed from: com.google.common.cache.LocalCache$LocalManualCache$1  reason: invalid class name */
        class AnonymousClass1 extends CacheLoader<Object, V> {
            final /* synthetic */ Callable a;

            AnonymousClass1(Callable callable) {
                this.a = callable;
            }

            @Override // com.google.common.cache.CacheLoader
            public V load(Object obj) throws Exception {
                return (V) this.a.call();
            }
        }

        @Override // com.google.common.cache.b
        public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
            return this.localCache.a(iterable);
        }

        @Override // com.google.common.cache.b
        public void put(K k, V v) {
            this.localCache.put(k, v);
        }

        @Override // com.google.common.cache.b
        public void putAll(Map<? extends K, ? extends V> map) {
            this.localCache.putAll(map);
        }

        @Override // com.google.common.cache.b
        public void invalidate(Object obj) {
            com.google.common.base.m.a(obj);
            this.localCache.remove(obj);
        }

        @Override // com.google.common.cache.b
        public void invalidateAll(Iterable<?> iterable) {
            this.localCache.c(iterable);
        }

        @Override // com.google.common.cache.b
        public void invalidateAll() {
            this.localCache.clear();
        }

        @Override // com.google.common.cache.b
        public long size() {
            return this.localCache.t();
        }

        @Override // com.google.common.cache.b
        public ConcurrentMap<K, V> asMap() {
            return this.localCache;
        }

        @Override // com.google.common.cache.b
        public c stats() {
            a.C0103a aVar = new a.C0103a();
            aVar.a(this.localCache.s);
            for (Segment<K, V> segment : this.localCache.d) {
                aVar.a(segment.statsCounter);
            }
            return aVar.b();
        }

        @Override // com.google.common.cache.b
        public void cleanUp() {
            this.localCache.s();
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new ManualSerializationProxy(this.localCache);
        }
    }

    /* access modifiers changed from: package-private */
    public static class LocalLoadingCache<K, V> extends LocalManualCache<K, V> implements e<K, V> {
        private static final long serialVersionUID = 1;

        LocalLoadingCache(CacheBuilder<? super K, ? super V> cacheBuilder, CacheLoader<? super K, V> cacheLoader) {
            super(new LocalCache(cacheBuilder, (CacheLoader) com.google.common.base.m.a(cacheLoader)), null);
        }

        @Override // com.google.common.cache.e
        public V get(K k) throws ExecutionException {
            return (V) this.localCache.c((LocalCache) k);
        }

        @Override // com.google.common.cache.e
        public V getUnchecked(K k) {
            try {
                return (V) get(k);
            } catch (ExecutionException e) {
                throw new UncheckedExecutionException(e.getCause());
            }
        }

        @Override // com.google.common.cache.e
        public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
            return this.localCache.b((Iterable) iterable);
        }

        @Override // com.google.common.cache.e
        public void refresh(K k) {
            this.localCache.d(k);
        }

        @Override // com.google.common.cache.e, com.google.common.base.g
        public final V apply(K k) {
            return (V) getUnchecked(k);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.cache.LocalCache.LocalManualCache
        public Object writeReplace() {
            return new LoadingSerializationProxy(this.localCache);
        }
    }
}
