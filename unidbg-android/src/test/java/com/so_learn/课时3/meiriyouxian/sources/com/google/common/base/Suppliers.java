package com.google.common.base;

import com.umeng.message.proguard.l;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public final class Suppliers {

    private interface a extends g {
    }

    private static class SupplierComposition<F, T> implements q<T>, Serializable {
        private static final long serialVersionUID = 0;
        final g<? super F, T> function;
        final q<F> supplier;

        SupplierComposition(g<? super F, T> gVar, q<F> qVar) {
            this.function = (g) m.a(gVar);
            this.supplier = (q) m.a(qVar);
        }

        @Override // com.google.common.base.q
        public T get() {
            return (T) this.function.apply(this.supplier.get());
        }

        @Override // java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof SupplierComposition)) {
                return false;
            }
            SupplierComposition supplierComposition = (SupplierComposition) obj;
            if (!this.function.equals(supplierComposition.function) || !this.supplier.equals(supplierComposition.supplier)) {
                return false;
            }
            return true;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return j.a(this.function, this.supplier);
        }

        @Override // java.lang.Object
        public String toString() {
            return "Suppliers.compose(" + this.function + ", " + this.supplier + l.t;
        }
    }

    static class MemoizingSupplier<T> implements q<T>, Serializable {
        private static final long serialVersionUID = 0;
        final q<T> delegate;
        volatile transient boolean initialized;
        transient T value;

        MemoizingSupplier(q<T> qVar) {
            this.delegate = (q) m.a(qVar);
        }

        @Override // com.google.common.base.q
        public T get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T t = (T) this.delegate.get();
                        this.value = t;
                        this.initialized = true;
                        return t;
                    }
                }
            }
            return this.value;
        }

        @Override // java.lang.Object
        public String toString() {
            Object obj;
            StringBuilder sb = new StringBuilder();
            sb.append("Suppliers.memoize(");
            if (this.initialized) {
                obj = "<supplier that returned " + ((Object) this.value) + ">";
            } else {
                obj = this.delegate;
            }
            sb.append(obj);
            sb.append(l.t);
            return sb.toString();
        }
    }

    static class ExpiringMemoizingSupplier<T> implements q<T>, Serializable {
        private static final long serialVersionUID = 0;
        final q<T> delegate;
        final long durationNanos;
        volatile transient long expirationNanos;
        volatile transient T value;

        ExpiringMemoizingSupplier(q<T> qVar, long j, TimeUnit timeUnit) {
            this.delegate = (q) m.a(qVar);
            this.durationNanos = timeUnit.toNanos(j);
            m.a(j > 0, "duration (%s %s) must be > 0", j, timeUnit);
        }

        @Override // com.google.common.base.q
        public T get() {
            long j = this.expirationNanos;
            long a = l.a();
            if (j == 0 || a - j >= 0) {
                synchronized (this) {
                    if (j == this.expirationNanos) {
                        T t = (T) this.delegate.get();
                        this.value = t;
                        long j2 = a + this.durationNanos;
                        if (j2 == 0) {
                            j2 = 1;
                        }
                        this.expirationNanos = j2;
                        return t;
                    }
                }
            }
            return this.value;
        }

        @Override // java.lang.Object
        public String toString() {
            return "Suppliers.memoizeWithExpiration(" + this.delegate + ", " + this.durationNanos + ", NANOS)";
        }
    }

    public static <T> q<T> a(T t) {
        return new SupplierOfInstance(t);
    }

    private static class SupplierOfInstance<T> implements q<T>, Serializable {
        private static final long serialVersionUID = 0;
        final T instance;

        SupplierOfInstance(T t) {
            this.instance = t;
        }

        @Override // com.google.common.base.q
        public T get() {
            return this.instance;
        }

        @Override // java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof SupplierOfInstance) {
                return j.a(this.instance, ((SupplierOfInstance) obj).instance);
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return j.a(this.instance);
        }

        @Override // java.lang.Object
        public String toString() {
            return "Suppliers.ofInstance(" + ((Object) this.instance) + l.t;
        }
    }

    private static class ThreadSafeSupplier<T> implements q<T>, Serializable {
        private static final long serialVersionUID = 0;
        final q<T> delegate;

        ThreadSafeSupplier(q<T> qVar) {
            this.delegate = (q) m.a(qVar);
        }

        @Override // com.google.common.base.q
        public T get() {
            T t;
            synchronized (this.delegate) {
                t = (T) this.delegate.get();
            }
            return t;
        }

        @Override // java.lang.Object
        public String toString() {
            return "Suppliers.synchronizedSupplier(" + this.delegate + l.t;
        }
    }

    private enum SupplierFunctionImpl implements a<Object> {
        INSTANCE;

        @Override // java.lang.Enum, java.lang.Object
        public String toString() {
            return "Suppliers.supplierFunction()";
        }

        public Object apply(q<Object> qVar) {
            return qVar.get();
        }
    }
}
