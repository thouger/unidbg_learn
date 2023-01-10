package com.google.common.hash;

import com.google.common.base.q;
import java.util.concurrent.atomic.AtomicLong;

/* access modifiers changed from: package-private */
public final class LongAddables {
    private static final q<h> a;

    static {
        q<h> qVar;
        try {
            new LongAdder();
            qVar = new AnonymousClass1();
        } catch (Throwable unused) {
            qVar = new AnonymousClass2();
        }
        a = qVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.hash.LongAddables$1  reason: invalid class name */
    public static class AnonymousClass1 implements q<h> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public h get() {
            return new LongAdder();
        }
    }

    /* renamed from: com.google.common.hash.LongAddables$2  reason: invalid class name */
    static class AnonymousClass2 implements q<h> {
        AnonymousClass2() {
        }

        /* renamed from: a */
        public h get() {
            return new PureJavaLongAddable(null);
        }
    }

    public static h a() {
        return (h) a.get();
    }

    /* access modifiers changed from: private */
    public static final class PureJavaLongAddable extends AtomicLong implements h {
        private PureJavaLongAddable() {
        }

        /* synthetic */ PureJavaLongAddable(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.common.hash.h
        public void increment() {
            getAndIncrement();
        }

        @Override // com.google.common.hash.h
        public void add(long j) {
            getAndAdd(j);
        }

        @Override // com.google.common.hash.h
        public long sum() {
            return get();
        }
    }
}
