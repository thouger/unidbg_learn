package com.google.common.hash;

import com.google.common.primitives.Longs;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

final class LittleEndianByteArray {
    static final /* synthetic */ boolean a = (!LittleEndianByteArray.class.desiredAssertionStatus());
    private static final a b;

    private enum JavaLittleEndianBytes implements a {
        INSTANCE {
            public long getLongLittleEndian(byte[] bArr, int i) {
                return Longs.a(bArr[i + 7], bArr[i + 6], bArr[i + 5], bArr[i + 4], bArr[i + 3], bArr[i + 2], bArr[i + 1], bArr[i]);
            }

            public void putLongLittleEndian(byte[] bArr, int i, long j) {
                long j2 = 255;
                for (int i2 = 0; i2 < 8; i2++) {
                    bArr[i + i2] = (byte) ((int) ((j & j2) >> (i2 * 8)));
                    j2 <<= 8;
                }
            }
        };
    }

    private interface a {
    }

    static {
        a aVar = JavaLittleEndianBytes.INSTANCE;
        try {
            if ("amd64".equals(System.getProperty("os.arch"))) {
                aVar = ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN) ? UnsafeByteArray.UNSAFE_LITTLE_ENDIAN : UnsafeByteArray.UNSAFE_BIG_ENDIAN;
            }
        } catch (Throwable unused) {
        }
        b = aVar;
    }

    private enum UnsafeByteArray implements a {
        UNSAFE_LITTLE_ENDIAN {
            public long getLongLittleEndian(byte[] bArr, int i) {
                return UnsafeByteArray.theUnsafe.getLong(bArr, ((long) i) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET));
            }

            public void putLongLittleEndian(byte[] bArr, int i, long j) {
                UnsafeByteArray.theUnsafe.putLong(bArr, ((long) i) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET), j);
            }
        },
        UNSAFE_BIG_ENDIAN {
            public long getLongLittleEndian(byte[] bArr, int i) {
                return Long.reverseBytes(UnsafeByteArray.theUnsafe.getLong(bArr, ((long) i) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET)));
            }

            public void putLongLittleEndian(byte[] bArr, int i, long j) {
                UnsafeByteArray.theUnsafe.putLong(bArr, ((long) i) + ((long) UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET), Long.reverseBytes(j));
            }
        };
        
        private static final int BYTE_ARRAY_BASE_OFFSET = theUnsafe.arrayBaseOffset(byte[].class);
        private static final Unsafe theUnsafe = getUnsafe();

        static {
            if (theUnsafe.arrayIndexScale(byte[].class) != 1) {
                throw new AssertionError();
            }
        }

        private static Unsafe getUnsafe() {
            try {
                return Unsafe.getUnsafe();
            } catch (SecurityException unused) {
                try {
                    return (Unsafe) AccessController.doPrivileged(new AnonymousClass3());
                } catch (PrivilegedActionException e) {
                    throw new RuntimeException("Could not initialize intrinsics", e.getCause());
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.google.common.hash.LittleEndianByteArray$UnsafeByteArray$3  reason: invalid class name */
        public static class AnonymousClass3 implements PrivilegedExceptionAction<Unsafe> {
            AnonymousClass3() {
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

    private LittleEndianByteArray() {
    }
}
