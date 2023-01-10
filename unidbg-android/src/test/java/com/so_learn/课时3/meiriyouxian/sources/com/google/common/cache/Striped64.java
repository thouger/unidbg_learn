package com.google.common.cache;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Random;
import sun.misc.Unsafe;

/* access modifiers changed from: package-private */
public abstract class Striped64 extends Number {
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final Unsafe UNSAFE;
    private static final long baseOffset;
    private static final long busyOffset;
    static final Random rng = new Random();
    static final ThreadLocal<int[]> threadHashCode = new ThreadLocal<>();
    volatile transient long base;
    volatile transient int busy;
    volatile transient a[] cells;

    /* access modifiers changed from: package-private */
    public abstract long fn(long j, long j2);

    /* access modifiers changed from: package-private */
    public static final class a {
        private static final Unsafe b;
        private static final long c;
        volatile long a;

        a(long j) {
            this.a = j;
        }

        /* access modifiers changed from: package-private */
        public final boolean a(long j, long j2) {
            return b.compareAndSwapLong(this, c, j, j2);
        }

        static {
            try {
                b = Striped64.getUnsafe();
                c = b.objectFieldOffset(a.class.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    static {
        try {
            UNSAFE = getUnsafe();
            baseOffset = UNSAFE.objectFieldOffset(Striped64.class.getDeclaredField("base"));
            busyOffset = UNSAFE.objectFieldOffset(Striped64.class.getDeclaredField("busy"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    Striped64() {
    }

    /* access modifiers changed from: package-private */
    public final boolean casBase(long j, long j2) {
        return UNSAFE.compareAndSwapLong(this, baseOffset, j, j2);
    }

    /* access modifiers changed from: package-private */
    public final boolean casBusy() {
        return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x008d, code lost:
        if (r16.cells != r9) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x008f, code lost:
        r8 = new com.google.common.cache.Striped64.a[(r10 << 1)];
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0094, code lost:
        if (r11 >= r10) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0096, code lost:
        r8[r11] = r9[r11];
        r11 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x009d, code lost:
        r16.cells = r8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00ec A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0022 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void retryUpdate(long r17, int[] r19, boolean r20) {
        /*
        // Method dump skipped, instructions count: 237
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.cache.Striped64.retryUpdate(long, int[], boolean):void");
    }

    /* access modifiers changed from: package-private */
    public final void internalReset(long j) {
        a[] aVarArr = this.cells;
        this.base = j;
        if (aVarArr != null) {
            for (a aVar : aVarArr) {
                if (aVar != null) {
                    aVar.a = j;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static Unsafe getUnsafe() {
        try {
            return Unsafe.getUnsafe();
        } catch (SecurityException unused) {
            try {
                return (Unsafe) AccessController.doPrivileged(new AnonymousClass1());
            } catch (PrivilegedActionException e) {
                throw new RuntimeException("Could not initialize intrinsics", e.getCause());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.cache.Striped64$1  reason: invalid class name */
    public static class AnonymousClass1 implements PrivilegedExceptionAction<Unsafe> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public Unsafe run() throws Exception {
            Field[] declaredFields = Unsafe.class.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (Unsafe.class.isInstance(obj)) {
                    return (Unsafe) Unsafe.class.cast(obj);
                }
            }
            throw new NoSuchFieldError("the Unsafe");
        }
    }
}
