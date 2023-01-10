package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.util.Map;

public final class ImmutableClassToInstanceMap<B> extends v<Class<? extends B>, B> implements m<B>, Serializable {
    private static final ImmutableClassToInstanceMap<Object> EMPTY = new ImmutableClassToInstanceMap<>(ImmutableMap.of());
    private final ImmutableMap<Class<? extends B>, B> delegate;

    public static <B> ImmutableClassToInstanceMap<B> of() {
        return (ImmutableClassToInstanceMap<B>) EMPTY;
    }

    public static <B, T extends B> ImmutableClassToInstanceMap<B> of(Class<T> cls, T t) {
        return new ImmutableClassToInstanceMap<>(ImmutableMap.of(cls, t));
    }

    public static <B> a<B> builder() {
        return new a<>();
    }

    public static final class a<B> {
        private final ImmutableMap.a<Class<? extends B>, B> a = ImmutableMap.builder();

        public <T extends B> a<B> a(Map<? extends Class<? extends T>, ? extends T> map) {
            for (Map.Entry<? extends Class<? extends T>, ? extends T> entry : map.entrySet()) {
                Class cls = (Class) entry.getKey();
                this.a.b(cls, a(cls, entry.getValue()));
            }
            return this;
        }

        private static <B, T extends B> T a(Class<T> cls, B b) {
            return (T) com.google.common.primitives.a.a(cls).cast(b);
        }

        public ImmutableClassToInstanceMap<B> a() {
            ImmutableMap<Class<? extends B>, B> b = this.a.b();
            if (b.isEmpty()) {
                return ImmutableClassToInstanceMap.of();
            }
            return new ImmutableClassToInstanceMap<>(b);
        }
    }

    public static <B, S extends B> ImmutableClassToInstanceMap<B> copyOf(Map<? extends Class<? extends S>, ? extends S> map) {
        if (map instanceof ImmutableClassToInstanceMap) {
            return (ImmutableClassToInstanceMap) map;
        }
        return new a().a(map).a();
    }

    private ImmutableClassToInstanceMap(ImmutableMap<Class<? extends B>, B> immutableMap) {
        this.delegate = immutableMap;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.v, com.google.common.collect.z
    public Map<Class<? extends B>, B> delegate() {
        return this.delegate;
    }

    public <T extends B> T getInstance(Class<T> cls) {
        return (T) this.delegate.get(m.a(cls));
    }

    @Deprecated
    public <T extends B> T putInstance(Class<T> cls, T t) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return isEmpty() ? of() : this;
    }
}
