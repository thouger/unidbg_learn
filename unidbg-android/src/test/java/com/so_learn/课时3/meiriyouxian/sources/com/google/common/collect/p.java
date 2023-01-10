package com.google.common.collect;

/* compiled from: ComparisonChain */
public abstract class p {
    private static final p a = new AnonymousClass1();
    private static final p b = new a(-1);
    private static final p c = new a(1);

    public abstract p a(Comparable<?> comparable, Comparable<?> comparable2);

    public abstract int b();

    /* synthetic */ p(AnonymousClass1 r1) {
        this();
    }

    private p() {
    }

    public static p a() {
        return a;
    }

    /* compiled from: ComparisonChain */
    /* renamed from: com.google.common.collect.p$1  reason: invalid class name */
    static class AnonymousClass1 extends p {
        @Override // com.google.common.collect.p
        public int b() {
            return 0;
        }

        AnonymousClass1() {
            super(null);
        }

        @Override // com.google.common.collect.p
        public p a(Comparable comparable, Comparable comparable2) {
            return a(comparable.compareTo(comparable2));
        }

        /* access modifiers changed from: package-private */
        public p a(int i) {
            if (i < 0) {
                return p.b;
            }
            return i > 0 ? p.c : p.a;
        }
    }

    /* compiled from: ComparisonChain */
    private static final class a extends p {
        final int a;

        @Override // com.google.common.collect.p
        public p a(Comparable comparable, Comparable comparable2) {
            return this;
        }

        a(int i) {
            super(null);
            this.a = i;
        }

        @Override // com.google.common.collect.p
        public int b() {
            return this.a;
        }
    }
}
