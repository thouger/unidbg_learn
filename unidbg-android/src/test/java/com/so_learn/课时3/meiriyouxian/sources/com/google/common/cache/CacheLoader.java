package com.google.common.cache;

import com.google.common.base.g;
import com.google.common.base.m;
import com.google.common.base.q;
import com.google.common.util.concurrent.l;
import com.google.common.util.concurrent.p;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public abstract class CacheLoader<K, V> {
    public abstract V load(K k) throws Exception;

    protected CacheLoader() {
    }

    public p<V> reload(K k, V v) throws Exception {
        m.a(k);
        m.a(v);
        return l.a(load(k));
    }

    public Map<K, V> loadAll(Iterable<? extends K> iterable) throws Exception {
        throw new UnsupportedLoadingOperationException();
    }

    public static <K, V> CacheLoader<K, V> from(g<K, V> gVar) {
        return new FunctionToCacheLoader(gVar);
    }

    public static <V> CacheLoader<Object, V> from(q<V> qVar) {
        return new SupplierToCacheLoader(qVar);
    }

    private static final class FunctionToCacheLoader<K, V> extends CacheLoader<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final g<K, V> computingFunction;

        public FunctionToCacheLoader(g<K, V> gVar) {
            this.computingFunction = (g) m.a(gVar);
        }

        @Override // com.google.common.cache.CacheLoader
        public V load(K k) {
            return (V) this.computingFunction.apply(m.a(k));
        }
    }

    public static <K, V> CacheLoader<K, V> asyncReloading(CacheLoader<K, V> cacheLoader, Executor executor) {
        m.a(cacheLoader);
        m.a(executor);
        return new AnonymousClass1(cacheLoader, executor);
    }

    /* renamed from: com.google.common.cache.CacheLoader$1  reason: invalid class name */
    static class AnonymousClass1 extends CacheLoader<K, V> {
        final /* synthetic */ CacheLoader a;
        final /* synthetic */ Executor b;

        AnonymousClass1(CacheLoader cacheLoader, Executor executor) {
            this.a = cacheLoader;
            this.b = executor;
        }

        @Override // com.google.common.cache.CacheLoader
        public V load(K k) throws Exception {
            return (V) this.a.load(k);
        }

        @Override // com.google.common.cache.CacheLoader
        public p<V> reload(K k, V v) throws Exception {
            com.google.common.util.concurrent.q a = com.google.common.util.concurrent.q.a(new AnonymousClass1(k, v));
            this.b.execute(a);
            return a;
        }

        /* renamed from: com.google.common.cache.CacheLoader$1$1  reason: invalid class name */
        class AnonymousClass1 implements Callable<V> {
            final /* synthetic */ Object a;
            final /* synthetic */ Object b;

            AnonymousClass1(Object obj, Object obj2) {
                this.a = obj;
                this.b = obj2;
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: com.google.common.cache.CacheLoader */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public V call() throws Exception {
                return AnonymousClass1.this.a.reload(this.a, this.b).get();
            }
        }

        @Override // com.google.common.cache.CacheLoader
        public Map<K, V> loadAll(Iterable<? extends K> iterable) throws Exception {
            return this.a.loadAll(iterable);
        }
    }

    private static final class SupplierToCacheLoader<V> extends CacheLoader<Object, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final q<V> computingSupplier;

        public SupplierToCacheLoader(q<V> qVar) {
            this.computingSupplier = (q) m.a(qVar);
        }

        @Override // com.google.common.cache.CacheLoader
        public V load(Object obj) {
            m.a(obj);
            return (V) this.computingSupplier.get();
        }
    }

    public static final class UnsupportedLoadingOperationException extends UnsupportedOperationException {
        UnsupportedLoadingOperationException() {
        }
    }

    public static final class InvalidCacheLoadException extends RuntimeException {
        public InvalidCacheLoadException(String str) {
            super(str);
        }
    }
}
