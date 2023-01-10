package com.google.common.base;

import java.util.Collections;
import java.util.Set;

/* access modifiers changed from: package-private */
public final class Absent<T> extends Optional<T> {
    static final Absent<Object> INSTANCE = new Absent<>();
    private static final long serialVersionUID = 0;

    @Override // com.google.common.base.Optional, java.lang.Object
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.google.common.base.Optional, java.lang.Object
    public int hashCode() {
        return 2040732332;
    }

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return false;
    }

    @Override // com.google.common.base.Optional
    public T orNull() {
        return null;
    }

    @Override // com.google.common.base.Optional, java.lang.Object
    public String toString() {
        return "Optional.absent()";
    }

    static <T> Optional<T> withType() {
        return INSTANCE;
    }

    private Absent() {
    }

    @Override // com.google.common.base.Optional
    public T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.common.base.Optional
    public T or(T t) {
        return (T) m.a(t, "use Optional.orNull() instead of Optional.or(null)");
    }

    @Override // com.google.common.base.Optional
    public Optional<T> or(Optional<? extends T> optional) {
        return (Optional) m.a(optional);
    }

    @Override // com.google.common.base.Optional
    public T or(q<? extends T> qVar) {
        return (T) m.a(qVar.get(), "use Optional.orNull() instead of a Supplier that returns null");
    }

    @Override // com.google.common.base.Optional
    public Set<T> asSet() {
        return Collections.emptySet();
    }

    @Override // com.google.common.base.Optional
    public <V> Optional<V> transform(g<? super T, V> gVar) {
        m.a(gVar);
        return Optional.absent();
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
