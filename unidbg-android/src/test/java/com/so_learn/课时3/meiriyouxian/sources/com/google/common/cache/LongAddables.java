package com.google.common.cache;

import com.google.common.base.q;
import java.util.concurrent.atomic.AtomicLong;

/* access modifiers changed from: package-private */
public final class LongAddables {
    private static final q<f> a;

    static {
        q<f> qVar;
        try {
            new LongAdder();
            qVar = new AnonymousClass1();
        } catch (Throwable unused) {
            qVar = new AnonymousClass2();
        }
        a = qVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.cache.LongAddables$1  reason: invalid class name */
    public static class AnonymousClass1 implements q<f> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public f get() {
            return new LongAdder();
        }
    }

    /* renamed from: com.google.common.cache.LongAddables$2  reason: invalid class name */
    static class AnonymousClass2 implements q<f> {
        AnonymousClass2() {
        }

        /* renamed from: a */
        public f get() {
            return new PureJavaLongAddable(null);
        }
    }

    public static f a() {
        return (f) a.get();
    }

    /* access modifiers changed from: private */
    public static final class PureJavaLongAddable extends AtomicLong implements f {
        private PureJavaLongAddable() {
        }

        /* synthetic */ PureJavaLongAddable(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.common.cache.f
        public void increment() {
            getAndIncrement();
        }

        @Override // com.google.common.cache.f
        public void add(long j) {
            getAndAdd(j);
        }

        @Override // com.google.common.cache.f
        public long sum() {
            return get();
        }
    }
}
