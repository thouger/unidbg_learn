package com.google.common.base;

import com.umeng.message.proguard.l;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class Predicates {

    /* access modifiers changed from: package-private */
    public enum ObjectPredicate implements n<Object> {
        ALWAYS_TRUE {
            @Override // com.google.common.base.n
            public boolean apply(Object obj) {
                return true;
            }

            @Override // java.lang.Enum, java.lang.Object
            public String toString() {
                return "Predicates.alwaysTrue()";
            }
        },
        ALWAYS_FALSE {
            @Override // com.google.common.base.n
            public boolean apply(Object obj) {
                return false;
            }

            @Override // java.lang.Enum, java.lang.Object
            public String toString() {
                return "Predicates.alwaysFalse()";
            }
        },
        IS_NULL {
            @Override // com.google.common.base.n
            public boolean apply(Object obj) {
                return obj == null;
            }

            @Override // java.lang.Enum, java.lang.Object
            public String toString() {
                return "Predicates.isNull()";
            }
        },
        NOT_NULL {
            @Override // com.google.common.base.n
            public boolean apply(Object obj) {
                return obj != null;
            }

            @Override // java.lang.Enum, java.lang.Object
            public String toString() {
                return "Predicates.notNull()";
            }
        };

        /* access modifiers changed from: package-private */
        public <T> n<T> withNarrowedType() {
            return this;
        }
    }

    public static <T> n<T> a() {
        return ObjectPredicate.ALWAYS_TRUE.withNarrowedType();
    }

    public static <T> n<T> b() {
        return ObjectPredicate.IS_NULL.withNarrowedType();
    }

    public static <T> n<T> a(n<T> nVar) {
        return new NotPredicate(nVar);
    }

    public static <T> n<T> a(n<? super T> nVar, n<? super T> nVar2) {
        return new AndPredicate(b((n) m.a(nVar), (n) m.a(nVar2)));
    }

    public static <T> n<T> a(T t) {
        return t == null ? b() : new IsEqualToPredicate(t);
    }

    public static n<Object> a(Class<?> cls) {
        return new InstanceOfPredicate(cls);
    }

    public static <T> n<T> a(Collection<? extends T> collection) {
        return new InPredicate(collection);
    }

    public static <A, B> n<A> a(n<B> nVar, g<A, ? extends B> gVar) {
        return new CompositionPredicate(nVar, gVar);
    }

    private static class NotPredicate<T> implements n<T>, Serializable {
        private static final long serialVersionUID = 0;
        final n<T> predicate;

        NotPredicate(n<T> nVar) {
            this.predicate = (n) m.a(nVar);
        }

        @Override // com.google.common.base.n
        public boolean apply(T t) {
            return !this.predicate.apply(t);
        }

        @Override // java.lang.Object
        public int hashCode() {
            return ~this.predicate.hashCode();
        }

        @Override // com.google.common.base.n, java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof NotPredicate) {
                return this.predicate.equals(((NotPredicate) obj).predicate);
            }
            return false;
        }

        @Override // java.lang.Object
        public String toString() {
            return "Predicates.not(" + this.predicate + l.t;
        }
    }

    private static class AndPredicate<T> implements n<T>, Serializable {
        private static final long serialVersionUID = 0;
        private final List<? extends n<? super T>> components;

        private AndPredicate(List<? extends n<? super T>> list) {
            this.components = list;
        }

        @Override // com.google.common.base.n
        public boolean apply(T t) {
            for (int i = 0; i < this.components.size(); i++) {
                if (!((n) this.components.get(i)).apply(t)) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.components.hashCode() + 306654252;
        }

        @Override // com.google.common.base.n, java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof AndPredicate) {
                return this.components.equals(((AndPredicate) obj).components);
            }
            return false;
        }

        @Override // java.lang.Object
        public String toString() {
            return Predicates.b("and", this.components);
        }
    }

    private static class OrPredicate<T> implements n<T>, Serializable {
        private static final long serialVersionUID = 0;
        private final List<? extends n<? super T>> components;

        private OrPredicate(List<? extends n<? super T>> list) {
            this.components = list;
        }

        @Override // com.google.common.base.n
        public boolean apply(T t) {
            for (int i = 0; i < this.components.size(); i++) {
                if (((n) this.components.get(i)).apply(t)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.components.hashCode() + 87855567;
        }

        @Override // com.google.common.base.n, java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof OrPredicate) {
                return this.components.equals(((OrPredicate) obj).components);
            }
            return false;
        }

        @Override // java.lang.Object
        public String toString() {
            return Predicates.b("or", this.components);
        }
    }

    /* access modifiers changed from: private */
    public static String b(String str, Iterable<?> iterable) {
        StringBuilder sb = new StringBuilder("Predicates.");
        sb.append(str);
        sb.append('(');
        boolean z = true;
        for (Object obj : iterable) {
            if (!z) {
                sb.append(',');
            }
            sb.append(obj);
            z = false;
        }
        sb.append(')');
        return sb.toString();
    }

    private static class IsEqualToPredicate<T> implements n<T>, Serializable {
        private static final long serialVersionUID = 0;
        private final T target;

        private IsEqualToPredicate(T t) {
            this.target = t;
        }

        @Override // com.google.common.base.n
        public boolean apply(T t) {
            return this.target.equals(t);
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.target.hashCode();
        }

        @Override // com.google.common.base.n, java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof IsEqualToPredicate) {
                return this.target.equals(((IsEqualToPredicate) obj).target);
            }
            return false;
        }

        @Override // java.lang.Object
        public String toString() {
            return "Predicates.equalTo(" + ((Object) this.target) + l.t;
        }
    }

    private static class InstanceOfPredicate implements n<Object>, Serializable {
        private static final long serialVersionUID = 0;
        private final Class<?> clazz;

        private InstanceOfPredicate(Class<?> cls) {
            this.clazz = (Class) m.a(cls);
        }

        @Override // com.google.common.base.n
        public boolean apply(Object obj) {
            return this.clazz.isInstance(obj);
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.clazz.hashCode();
        }

        @Override // com.google.common.base.n, java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof InstanceOfPredicate) || this.clazz != ((InstanceOfPredicate) obj).clazz) {
                return false;
            }
            return true;
        }

        @Override // java.lang.Object
        public String toString() {
            return "Predicates.instanceOf(" + this.clazz.getName() + l.t;
        }
    }

    private static class SubtypeOfPredicate implements n<Class<?>>, Serializable {
        private static final long serialVersionUID = 0;
        private final Class<?> clazz;

        private SubtypeOfPredicate(Class<?> cls) {
            this.clazz = (Class) m.a(cls);
        }

        public boolean apply(Class<?> cls) {
            return this.clazz.isAssignableFrom(cls);
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.clazz.hashCode();
        }

        @Override // com.google.common.base.n, java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof SubtypeOfPredicate) || this.clazz != ((SubtypeOfPredicate) obj).clazz) {
                return false;
            }
            return true;
        }

        @Override // java.lang.Object
        public String toString() {
            return "Predicates.subtypeOf(" + this.clazz.getName() + l.t;
        }
    }

    private static class InPredicate<T> implements n<T>, Serializable {
        private static final long serialVersionUID = 0;
        private final Collection<?> target;

        private InPredicate(Collection<?> collection) {
            this.target = (Collection) m.a(collection);
        }

        @Override // com.google.common.base.n
        public boolean apply(T t) {
            try {
                return this.target.contains(t);
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        }

        @Override // com.google.common.base.n, java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof InPredicate) {
                return this.target.equals(((InPredicate) obj).target);
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.target.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return "Predicates.in(" + this.target + l.t;
        }
    }

    private static class CompositionPredicate<A, B> implements n<A>, Serializable {
        private static final long serialVersionUID = 0;
        final g<A, ? extends B> f;
        final n<B> p;

        private CompositionPredicate(n<B> nVar, g<A, ? extends B> gVar) {
            this.p = (n) m.a(nVar);
            this.f = (g) m.a(gVar);
        }

        @Override // com.google.common.base.n
        public boolean apply(A a) {
            return this.p.apply(this.f.apply(a));
        }

        @Override // com.google.common.base.n, java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof CompositionPredicate)) {
                return false;
            }
            CompositionPredicate compositionPredicate = (CompositionPredicate) obj;
            if (!this.f.equals(compositionPredicate.f) || !this.p.equals(compositionPredicate.p)) {
                return false;
            }
            return true;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.f.hashCode() ^ this.p.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return this.p + l.s + this.f + l.t;
        }
    }

    private static class ContainsPatternPredicate implements n<CharSequence>, Serializable {
        private static final long serialVersionUID = 0;
        final e pattern;

        ContainsPatternPredicate(e eVar) {
            this.pattern = (e) m.a(eVar);
        }

        public boolean apply(CharSequence charSequence) {
            return this.pattern.matcher(charSequence).a();
        }

        @Override // java.lang.Object
        public int hashCode() {
            return j.a(this.pattern.pattern(), Integer.valueOf(this.pattern.flags()));
        }

        @Override // com.google.common.base.n, java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof ContainsPatternPredicate)) {
                return false;
            }
            ContainsPatternPredicate containsPatternPredicate = (ContainsPatternPredicate) obj;
            if (!j.a(this.pattern.pattern(), containsPatternPredicate.pattern.pattern()) || this.pattern.flags() != containsPatternPredicate.pattern.flags()) {
                return false;
            }
            return true;
        }

        @Override // java.lang.Object
        public String toString() {
            String aVar = i.a(this.pattern).a("pattern", this.pattern.pattern()).a("pattern.flags", this.pattern.flags()).toString();
            return "Predicates.contains(" + aVar + l.t;
        }
    }

    private static class ContainsPatternFromStringPredicate extends ContainsPatternPredicate {
        private static final long serialVersionUID = 0;

        ContainsPatternFromStringPredicate(String str) {
            super(l.b(str));
        }

        @Override // com.google.common.base.Predicates.ContainsPatternPredicate, java.lang.Object
        public String toString() {
            return "Predicates.containsPattern(" + this.pattern.pattern() + l.t;
        }
    }

    private static <T> List<n<? super T>> b(n<? super T> nVar, n<? super T> nVar2) {
        return Arrays.asList(nVar, nVar2);
    }
}
