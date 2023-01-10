package com.google.common.primitives;

import com.google.common.base.m;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Comparator;
import sun.misc.Unsafe;

public final class UnsignedBytes {
    public static int a(byte b) {
        return b & 255;
    }

    public static byte a(long j) {
        m.a((j >> 8) == 0, "out of range: %s", j);
        return (byte) ((int) j);
    }

    public static int a(byte b, byte b2) {
        return a(b) - a(b2);
    }

    static Comparator<byte[]> a() {
        return LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
    }

    static class LexicographicalComparatorHolder {
        static final String a = (LexicographicalComparatorHolder.class.getName() + "$UnsafeComparator");
        static final Comparator<byte[]> b = a();

        LexicographicalComparatorHolder() {
        }

        enum UnsafeComparator implements Comparator<byte[]> {
            INSTANCE;
            
            static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
            static final int BYTE_ARRAY_BASE_OFFSET = theUnsafe.arrayBaseOffset(byte[].class);
            static final Unsafe theUnsafe = getUnsafe();

            @Override // java.lang.Enum, java.lang.Object
            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (sun.misc.Unsafe version)";
            }

            static {
                if (!"64".equals(System.getProperty("sun.arch.data.model")) || BYTE_ARRAY_BASE_OFFSET % 8 != 0 || theUnsafe.arrayIndexScale(byte[].class) != 1) {
                    throw new Error();
                }
            }

            private static Unsafe getUnsafe() {
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
            /* renamed from: com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator$1  reason: invalid class name */
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

            public int compare(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                int i = min & -8;
                int i2 = 0;
                while (i2 < i) {
                    long j = (long) i2;
                    long j2 = theUnsafe.getLong(bArr, ((long) BYTE_ARRAY_BASE_OFFSET) + j);
                    long j3 = theUnsafe.getLong(bArr2, ((long) BYTE_ARRAY_BASE_OFFSET) + j);
                    if (j2 == j3) {
                        i2 += 8;
                    } else if (BIG_ENDIAN) {
                        return UnsignedLongs.a(j2, j3);
                    } else {
                        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j2 ^ j3) & -8;
                        return ((int) ((j2 >>> numberOfTrailingZeros) & 255)) - ((int) ((j3 >>> numberOfTrailingZeros) & 255));
                    }
                }
                while (i2 < min) {
                    int a = UnsignedBytes.a(bArr[i2], bArr2[i2]);
                    if (a != 0) {
                        return a;
                    }
                    i2++;
                }
                return bArr.length - bArr2.length;
            }
        }

        /* access modifiers changed from: package-private */
        public enum PureJavaComparator implements Comparator<byte[]> {
            INSTANCE;

            @Override // java.lang.Enum, java.lang.Object
            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
            }

            public int compare(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                for (int i = 0; i < min; i++) {
                    int a = UnsignedBytes.a(bArr[i], bArr2[i]);
                    if (a != 0) {
                        return a;
                    }
                }
                return bArr.length - bArr2.length;
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v4, resolved type: java.lang.Object[] */
        /* JADX WARN: Multi-variable type inference failed */
        static Comparator<byte[]> a() {
            try {
                return (Comparator) Class.forName(a).getEnumConstants()[0];
            } catch (Throwable unused) {
                return UnsignedBytes.a();
            }
        }
    }
}
