package com.google.common.hash;

import com.google.common.base.m;
import com.google.common.hash.BloomFilter;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLongArray;

enum BloomFilterStrategies implements BloomFilter.Strategy {
    MURMUR128_MITZ_32 {
        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean put(T t, Funnel<? super T> funnel, int i, a aVar) {
            long a = aVar.a();
            long asLong = Hashing.a().hashObject(t, funnel).asLong();
            int i2 = (int) asLong;
            int i3 = (int) (asLong >>> 32);
            boolean z = false;
            for (int i4 = 1; i4 <= i; i4++) {
                int i5 = (i4 * i3) + i2;
                if (i5 < 0) {
                    i5 = ~i5;
                }
                z |= aVar.a(((long) i5) % a);
            }
            return z;
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, a aVar) {
            long a = aVar.a();
            long asLong = Hashing.a().hashObject(t, funnel).asLong();
            int i2 = (int) asLong;
            int i3 = (int) (asLong >>> 32);
            for (int i4 = 1; i4 <= i; i4++) {
                int i5 = (i4 * i3) + i2;
                if (i5 < 0) {
                    i5 = ~i5;
                }
                if (!aVar.b(((long) i5) % a)) {
                    return false;
                }
            }
            return true;
        }
    },
    MURMUR128_MITZ_64 {
        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean put(T t, Funnel<? super T> funnel, int i, a aVar) {
            long a = aVar.a();
            byte[] bytesInternal = Hashing.a().hashObject(t, funnel).getBytesInternal();
            long lowerEight = lowerEight(bytesInternal);
            long upperEight = upperEight(bytesInternal);
            long j = lowerEight;
            boolean z = false;
            for (int i2 = 0; i2 < i; i2++) {
                z |= aVar.a((Long.MAX_VALUE & j) % a);
                j += upperEight;
            }
            return z;
        }

        @Override // com.google.common.hash.BloomFilter.Strategy
        public <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, a aVar) {
            long a = aVar.a();
            byte[] bytesInternal = Hashing.a().hashObject(t, funnel).getBytesInternal();
            long lowerEight = lowerEight(bytesInternal);
            long upperEight = upperEight(bytesInternal);
            long j = lowerEight;
            for (int i2 = 0; i2 < i; i2++) {
                if (!aVar.b((Long.MAX_VALUE & j) % a)) {
                    return false;
                }
                j += upperEight;
            }
            return true;
        }

        private long lowerEight(byte[] bArr) {
            return Longs.a(bArr[7], bArr[6], bArr[5], bArr[4], bArr[3], bArr[2], bArr[1], bArr[0]);
        }

        private long upperEight(byte[] bArr) {
            return Longs.a(bArr[15], bArr[14], bArr[13], bArr[12], bArr[11], bArr[10], bArr[9], bArr[8]);
        }
    };

    /* access modifiers changed from: package-private */
    public static final class a {
        final AtomicLongArray a;
        private final h b;

        a(long j) {
            m.a(j > 0, "data length is zero!");
            this.a = new AtomicLongArray(Ints.a(LongMath.a(j, 64, RoundingMode.CEILING)));
            this.b = LongAddables.a();
        }

        a(long[] jArr) {
            m.a(jArr.length > 0, "data length is zero!");
            this.a = new AtomicLongArray(jArr);
            this.b = LongAddables.a();
            long j = 0;
            for (long j2 : jArr) {
                j += (long) Long.bitCount(j2);
            }
            this.b.add(j);
        }

        /* access modifiers changed from: package-private */
        public boolean a(long j) {
            long j2;
            long j3;
            if (b(j)) {
                return false;
            }
            int i = (int) (j >>> 6);
            long j4 = 1 << ((int) j);
            do {
                j2 = this.a.get(i);
                j3 = j2 | j4;
                if (j2 == j3) {
                    return false;
                }
            } while (!this.a.compareAndSet(i, j2, j3));
            this.b.increment();
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean b(long j) {
            return ((1 << ((int) j)) & this.a.get((int) (j >>> 6))) != 0;
        }

        public static long[] a(AtomicLongArray atomicLongArray) {
            long[] jArr = new long[atomicLongArray.length()];
            for (int i = 0; i < jArr.length; i++) {
                jArr[i] = atomicLongArray.get(i);
            }
            return jArr;
        }

        /* access modifiers changed from: package-private */
        public long a() {
            return ((long) this.a.length()) * 64;
        }

        /* access modifiers changed from: package-private */
        public long b() {
            return this.b.sum();
        }

        /* access modifiers changed from: package-private */
        public a c() {
            return new a(a(this.a));
        }

        /* access modifiers changed from: package-private */
        public void a(a aVar) {
            long j;
            long j2;
            boolean z;
            m.a(this.a.length() == aVar.a.length(), "BitArrays must be of equal length (%s != %s)", this.a.length(), aVar.a.length());
            for (int i = 0; i < this.a.length(); i++) {
                long j3 = aVar.a.get(i);
                while (true) {
                    j = this.a.get(i);
                    j2 = j | j3;
                    if (j != j2) {
                        if (this.a.compareAndSet(i, j, j2)) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    this.b.add((long) (Long.bitCount(j2) - Long.bitCount(j)));
                }
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                return Arrays.equals(a(this.a), a(((a) obj).a));
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(a(this.a));
        }
    }
}
