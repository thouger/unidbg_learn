package com.google.common.base;

import com.umeng.message.proguard.l;
import java.io.Serializable;
import java.util.Map;

public final class Functions {

    private enum IdentityFunction implements g<Object, Object> {
        INSTANCE;

        @Override // com.google.common.base.g
        public Object apply(Object obj) {
            return obj;
        }

        @Override // java.lang.Enum, java.lang.Object
        public String toString() {
            return "Functions.identity()";
        }
    }

    private enum ToStringFunction implements g<Object, String> {
        INSTANCE;

        @Override // java.lang.Enum, java.lang.Object
        public String toString() {
            return "Functions.toStringFunction()";
        }

        @Override // com.google.common.base.g
        public String apply(Object obj) {
            m.a(obj);
            return obj.toString();
        }
    }

    private static class FunctionForMapNoDefault<K, V> implements g<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final Map<K, V> map;

        FunctionForMapNoDefault(Map<K, V> map) {
            this.map = (Map) m.a(map);
        }

        @Override // com.google.common.base.g
        public V apply(K k) {
            V v = this.map.get(k);
            m.a(v != null || this.map.containsKey(k), "Key '%s' not present in map", k);
            return v;
        }

        @Override // com.google.common.base.g, java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof FunctionForMapNoDefault) {
                return this.map.equals(((FunctionForMapNoDefault) obj).map);
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.map.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return "Functions.forMap(" + this.map + l.t;
        }
    }

    private static class ForMapWithDefault<K, V> implements g<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final V defaultValue;
        final Map<K, ? extends V> map;

        ForMapWithDefault(Map<K, ? extends V> map, V v) {
            this.map = (Map) m.a(map);
            this.defaultValue = v;
        }

        @Override // com.google.common.base.g
        public V apply(K k) {
            V v = (V) this.map.get(k);
            return (v != null || this.map.containsKey(k)) ? v : this.defaultValue;
        }

        @Override // com.google.common.base.g, java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof ForMapWithDefault)) {
                return false;
            }
            ForMapWithDefault forMapWithDefault = (ForMapWithDefault) obj;
            if (!this.map.equals(forMapWithDefault.map) || !j.a(this.defaultValue, forMapWithDefault.defaultValue)) {
                return false;
            }
            return true;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return j.a(this.map, this.defaultValue);
        }

        @Override // java.lang.Object
        public String toString() {
            return "Functions.forMap(" + this.map + ", defaultValue=" + ((Object) this.defaultValue) + l.t;
        }
    }

    private static class FunctionComposition<A, B, C> implements g<A, C>, Serializable {
        private static final long serialVersionUID = 0;
        private final g<A, ? extends B> f;
        private final g<B, C> g;

        public FunctionComposition(g<B, C> gVar, g<A, ? extends B> gVar2) {
            this.g = (g) m.a(gVar);
            this.f = (g) m.a(gVar2);
        }

        @Override // com.google.common.base.g
        public C apply(A a) {
            return (C) this.g.apply(this.f.apply(a));
        }

        @Override // com.google.common.base.g, java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof FunctionComposition)) {
                return false;
            }
            FunctionComposition functionComposition = (FunctionComposition) obj;
            if (!this.f.equals(functionComposition.f) || !this.g.equals(functionComposition.g)) {
                return false;
            }
            return true;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.f.hashCode() ^ this.g.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return this.g + l.s + this.f + l.t;
        }
    }

    private static class PredicateFunction<T> implements g<T, Boolean>, Serializable {
        private static final long serialVersionUID = 0;
        private final n<T> predicate;

        private PredicateFunction(n<T> nVar) {
            this.predicate = (n) m.a(nVar);
        }

        @Override // com.google.common.base.g
        public Boolean apply(T t) {
            return Boolean.valueOf(this.predicate.apply(t));
        }

        @Override // com.google.common.base.g, java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof PredicateFunction) {
                return this.predicate.equals(((PredicateFunction) obj).predicate);
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.predicate.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return "Functions.forPredicate(" + this.predicate + l.t;
        }
    }

    private static class ConstantFunction<E> implements g<Object, E>, Serializable {
        private static final long serialVersionUID = 0;
        private final E value;

        public ConstantFunction(E e) {
            this.value = e;
        }

        @Override // com.google.common.base.g
        public E apply(Object obj) {
            return this.value;
        }

        @Override // com.google.common.base.g, java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof ConstantFunction) {
                return j.a(this.value, ((ConstantFunction) obj).value);
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            E e = this.value;
            if (e == null) {
                return 0;
            }
            return e.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return "Functions.constant(" + ((Object) this.value) + l.t;
        }
    }

    private static class SupplierFunction<T> implements g<Object, T>, Serializable {
        private static final long serialVersionUID = 0;
        private final q<T> supplier;

        private SupplierFunction(q<T> qVar) {
            this.supplier = (q) m.a(qVar);
        }

        @Override // com.google.common.base.g
        public T apply(Object obj) {
            return (T) this.supplier.get();
        }

        @Override // com.google.common.base.g, java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof SupplierFunction) {
                return this.supplier.equals(((SupplierFunction) obj).supplier);
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.supplier.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return "Functions.forSupplier(" + this.supplier + l.t;
        }
    }
}
