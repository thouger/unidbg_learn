package com.google.common.base;

import com.umeng.message.proguard.l;
import java.io.Serializable;
import java.util.Iterator;

public abstract class Converter<A, B> implements g<A, B> {
    private final boolean handleNullAutomatically;
    private transient Converter<B, A> reverse;

    /* access modifiers changed from: protected */
    public abstract A doBackward(B b);

    /* access modifiers changed from: protected */
    public abstract B doForward(A a);

    protected Converter() {
        this(true);
    }

    Converter(boolean z) {
        this.handleNullAutomatically = z;
    }

    public final B convert(A a) {
        return (B) correctedDoForward(a);
    }

    /* access modifiers changed from: package-private */
    public B correctedDoForward(A a) {
        if (!this.handleNullAutomatically) {
            return (B) doForward(a);
        }
        if (a == null) {
            return null;
        }
        return (B) m.a(doForward(a));
    }

    /* access modifiers changed from: package-private */
    public A correctedDoBackward(B b) {
        if (!this.handleNullAutomatically) {
            return (A) doBackward(b);
        }
        if (b == null) {
            return null;
        }
        return (A) m.a(doBackward(b));
    }

    /* renamed from: com.google.common.base.Converter$1  reason: invalid class name */
    class AnonymousClass1 implements Iterable<B> {
        final /* synthetic */ Iterable a;

        AnonymousClass1(Iterable iterable) {
            this.a = iterable;
        }

        /* renamed from: com.google.common.base.Converter$1$1  reason: invalid class name */
        class AnonymousClass1 implements Iterator<B> {
            private final Iterator<? extends A> b = AnonymousClass1.this.a.iterator();

            AnonymousClass1() {
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.b.hasNext();
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: com.google.common.base.Converter */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Iterator
            public B next() {
                return (B) Converter.this.convert(this.b.next());
            }

            @Override // java.util.Iterator
            public void remove() {
                this.b.remove();
            }
        }

        @Override // java.lang.Iterable
        public Iterator<B> iterator() {
            return new AnonymousClass1();
        }
    }

    public Iterable<B> convertAll(Iterable<? extends A> iterable) {
        m.a(iterable, "fromIterable");
        return new AnonymousClass1(iterable);
    }

    public Converter<B, A> reverse() {
        Converter<B, A> converter = this.reverse;
        if (converter != null) {
            return converter;
        }
        ReverseConverter reverseConverter = new ReverseConverter(this);
        this.reverse = reverseConverter;
        return reverseConverter;
    }

    private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> original;

        ReverseConverter(Converter<A, B> converter) {
            this.original = converter;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public A doForward(B b) {
            throw new AssertionError();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public B doBackward(A a) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.Converter
        public A correctedDoForward(B b) {
            return (A) this.original.correctedDoBackward(b);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.Converter
        public B correctedDoBackward(A a) {
            return (B) this.original.correctedDoForward(a);
        }

        @Override // com.google.common.base.Converter
        public Converter<A, B> reverse() {
            return this.original;
        }

        @Override // com.google.common.base.Converter, com.google.common.base.g, java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof ReverseConverter) {
                return this.original.equals(((ReverseConverter) obj).original);
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return ~this.original.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return this.original + ".reverse()";
        }
    }

    public final <C> Converter<A, C> andThen(Converter<B, C> converter) {
        return doAndThen(converter);
    }

    /* access modifiers changed from: package-private */
    public <C> Converter<A, C> doAndThen(Converter<B, C> converter) {
        return new ConverterComposition(this, (Converter) m.a(converter));
    }

    /* access modifiers changed from: private */
    public static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> first;
        final Converter<B, C> second;

        ConverterComposition(Converter<A, B> converter, Converter<B, C> converter2) {
            this.first = converter;
            this.second = converter2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public C doForward(A a) {
            throw new AssertionError();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public A doBackward(C c) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.Converter
        public C correctedDoForward(A a) {
            return (C) this.second.correctedDoForward(this.first.correctedDoForward(a));
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.Converter
        public A correctedDoBackward(C c) {
            return (A) this.first.correctedDoBackward(this.second.correctedDoBackward(c));
        }

        @Override // com.google.common.base.Converter, com.google.common.base.g, java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof ConverterComposition)) {
                return false;
            }
            ConverterComposition converterComposition = (ConverterComposition) obj;
            if (!this.first.equals(converterComposition.first) || !this.second.equals(converterComposition.second)) {
                return false;
            }
            return true;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return (this.first.hashCode() * 31) + this.second.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return this.first + ".andThen(" + this.second + l.t;
        }
    }

    @Override // com.google.common.base.g
    @Deprecated
    public final B apply(A a) {
        return (B) convert(a);
    }

    @Override // com.google.common.base.g, java.lang.Object
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static <A, B> Converter<A, B> from(g<? super A, ? extends B> gVar, g<? super B, ? extends A> gVar2) {
        return new FunctionBasedConverter(gVar, gVar2, null);
    }

    private static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
        private final g<? super B, ? extends A> backwardFunction;
        private final g<? super A, ? extends B> forwardFunction;

        /* synthetic */ FunctionBasedConverter(g gVar, g gVar2, AnonymousClass1 r3) {
            this(gVar, gVar2);
        }

        private FunctionBasedConverter(g<? super A, ? extends B> gVar, g<? super B, ? extends A> gVar2) {
            this.forwardFunction = (g) m.a(gVar);
            this.backwardFunction = (g) m.a(gVar2);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public B doForward(A a) {
            return (B) this.forwardFunction.apply(a);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public A doBackward(B b) {
            return (A) this.backwardFunction.apply(b);
        }

        @Override // com.google.common.base.Converter, com.google.common.base.g, java.lang.Object
        public boolean equals(Object obj) {
            if (!(obj instanceof FunctionBasedConverter)) {
                return false;
            }
            FunctionBasedConverter functionBasedConverter = (FunctionBasedConverter) obj;
            if (!this.forwardFunction.equals(functionBasedConverter.forwardFunction) || !this.backwardFunction.equals(functionBasedConverter.backwardFunction)) {
                return false;
            }
            return true;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return (this.forwardFunction.hashCode() * 31) + this.backwardFunction.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return "Converter.from(" + this.forwardFunction + ", " + this.backwardFunction + l.t;
        }
    }

    public static <T> Converter<T, T> identity() {
        return IdentityConverter.INSTANCE;
    }

    private static final class IdentityConverter<T> extends Converter<T, T> implements Serializable {
        static final IdentityConverter INSTANCE = new IdentityConverter();
        private static final long serialVersionUID = 0;

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public T doBackward(T t) {
            return t;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public T doForward(T t) {
            return t;
        }

        @Override // com.google.common.base.Converter
        public IdentityConverter<T> reverse() {
            return this;
        }

        @Override // java.lang.Object
        public String toString() {
            return "Converter.identity()";
        }

        private IdentityConverter() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.Converter
        public <S> Converter<T, S> doAndThen(Converter<T, S> converter) {
            return (Converter) m.a(converter, "otherConverter");
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }
}
