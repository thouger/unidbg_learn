package com.google.common.hash;

import com.google.common.primitives.UnsignedBytes;
import com.umeng.message.proguard.l;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* access modifiers changed from: package-private */
public final class Murmur3_128HashFunction extends b implements Serializable {
    static final e GOOD_FAST_HASH_128 = new Murmur3_128HashFunction(Hashing.a);
    static final e MURMUR3_128 = new Murmur3_128HashFunction(0);
    private static final long serialVersionUID = 0;
    private final int seed;

    public int bits() {
        return 128;
    }

    Murmur3_128HashFunction(int i) {
        this.seed = i;
    }

    @Override // com.google.common.hash.e
    public f newHasher() {
        return new a(this.seed);
    }

    @Override // java.lang.Object
    public String toString() {
        return "Hashing.murmur3_128(" + this.seed + l.t;
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (!(obj instanceof Murmur3_128HashFunction) || this.seed != ((Murmur3_128HashFunction) obj).seed) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Object
    public int hashCode() {
        return getClass().hashCode() ^ this.seed;
    }

    private static final class a extends d {
        private long a;
        private long b;
        private int c = 0;

        private static long c(long j) {
            long j2 = (j ^ (j >>> 33)) * -49064778989728563L;
            long j3 = (j2 ^ (j2 >>> 33)) * -4265267296055464877L;
            return j3 ^ (j3 >>> 33);
        }

        a(int i) {
            super(16);
            long j = (long) i;
            this.a = j;
            this.b = j;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.d
        public void a(ByteBuffer byteBuffer) {
            a(byteBuffer.getLong(), byteBuffer.getLong());
            this.c += 16;
        }

        private void a(long j, long j2) {
            this.a = d(j) ^ this.a;
            this.a = Long.rotateLeft(this.a, 27);
            long j3 = this.a;
            long j4 = this.b;
            this.a = j3 + j4;
            this.a = (this.a * 5) + 1390208809;
            this.b = e(j2) ^ j4;
            this.b = Long.rotateLeft(this.b, 31);
            this.b += this.a;
            this.b = (this.b * 5) + 944331445;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.d
        public void c(ByteBuffer byteBuffer) {
            long j;
            long j2;
            long j3;
            long j4;
            long j5;
            long j6;
            long j7;
            long j8;
            long j9;
            long j10;
            long j11;
            long j12;
            long j13;
            long j14;
            this.c += byteBuffer.remaining();
            switch (byteBuffer.remaining()) {
                case 1:
                    j3 = 0;
                    j = ((long) UnsignedBytes.a(byteBuffer.get(0))) ^ j3;
                    j2 = 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 2:
                    j4 = 0;
                    j3 = j4 ^ (((long) UnsignedBytes.a(byteBuffer.get(1))) << 8);
                    j = ((long) UnsignedBytes.a(byteBuffer.get(0))) ^ j3;
                    j2 = 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 3:
                    j5 = 0;
                    j4 = j5 ^ (((long) UnsignedBytes.a(byteBuffer.get(2))) << 16);
                    j3 = j4 ^ (((long) UnsignedBytes.a(byteBuffer.get(1))) << 8);
                    j = ((long) UnsignedBytes.a(byteBuffer.get(0))) ^ j3;
                    j2 = 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 4:
                    j6 = 0;
                    j5 = j6 ^ (((long) UnsignedBytes.a(byteBuffer.get(3))) << 24);
                    j4 = j5 ^ (((long) UnsignedBytes.a(byteBuffer.get(2))) << 16);
                    j3 = j4 ^ (((long) UnsignedBytes.a(byteBuffer.get(1))) << 8);
                    j = ((long) UnsignedBytes.a(byteBuffer.get(0))) ^ j3;
                    j2 = 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 5:
                    j7 = 0;
                    j6 = j7 ^ (((long) UnsignedBytes.a(byteBuffer.get(4))) << 32);
                    j5 = j6 ^ (((long) UnsignedBytes.a(byteBuffer.get(3))) << 24);
                    j4 = j5 ^ (((long) UnsignedBytes.a(byteBuffer.get(2))) << 16);
                    j3 = j4 ^ (((long) UnsignedBytes.a(byteBuffer.get(1))) << 8);
                    j = ((long) UnsignedBytes.a(byteBuffer.get(0))) ^ j3;
                    j2 = 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 6:
                    j8 = 0;
                    j7 = j8 ^ (((long) UnsignedBytes.a(byteBuffer.get(5))) << 40);
                    j6 = j7 ^ (((long) UnsignedBytes.a(byteBuffer.get(4))) << 32);
                    j5 = j6 ^ (((long) UnsignedBytes.a(byteBuffer.get(3))) << 24);
                    j4 = j5 ^ (((long) UnsignedBytes.a(byteBuffer.get(2))) << 16);
                    j3 = j4 ^ (((long) UnsignedBytes.a(byteBuffer.get(1))) << 8);
                    j = ((long) UnsignedBytes.a(byteBuffer.get(0))) ^ j3;
                    j2 = 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 7:
                    j8 = (((long) UnsignedBytes.a(byteBuffer.get(6))) << 48) ^ 0;
                    j7 = j8 ^ (((long) UnsignedBytes.a(byteBuffer.get(5))) << 40);
                    j6 = j7 ^ (((long) UnsignedBytes.a(byteBuffer.get(4))) << 32);
                    j5 = j6 ^ (((long) UnsignedBytes.a(byteBuffer.get(3))) << 24);
                    j4 = j5 ^ (((long) UnsignedBytes.a(byteBuffer.get(2))) << 16);
                    j3 = j4 ^ (((long) UnsignedBytes.a(byteBuffer.get(1))) << 8);
                    j = ((long) UnsignedBytes.a(byteBuffer.get(0))) ^ j3;
                    j2 = 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 8:
                    j2 = 0;
                    j = byteBuffer.getLong() ^ 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 9:
                    j9 = 0;
                    j2 = j9 ^ ((long) UnsignedBytes.a(byteBuffer.get(8)));
                    j = byteBuffer.getLong() ^ 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 10:
                    j10 = 0;
                    j9 = j10 ^ (((long) UnsignedBytes.a(byteBuffer.get(9))) << 8);
                    j2 = j9 ^ ((long) UnsignedBytes.a(byteBuffer.get(8)));
                    j = byteBuffer.getLong() ^ 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 11:
                    j11 = 0;
                    j10 = j11 ^ (((long) UnsignedBytes.a(byteBuffer.get(10))) << 16);
                    j9 = j10 ^ (((long) UnsignedBytes.a(byteBuffer.get(9))) << 8);
                    j2 = j9 ^ ((long) UnsignedBytes.a(byteBuffer.get(8)));
                    j = byteBuffer.getLong() ^ 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 12:
                    j12 = 0;
                    j11 = j12 ^ (((long) UnsignedBytes.a(byteBuffer.get(11))) << 24);
                    j10 = j11 ^ (((long) UnsignedBytes.a(byteBuffer.get(10))) << 16);
                    j9 = j10 ^ (((long) UnsignedBytes.a(byteBuffer.get(9))) << 8);
                    j2 = j9 ^ ((long) UnsignedBytes.a(byteBuffer.get(8)));
                    j = byteBuffer.getLong() ^ 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 13:
                    j13 = 0;
                    j12 = j13 ^ (((long) UnsignedBytes.a(byteBuffer.get(12))) << 32);
                    j11 = j12 ^ (((long) UnsignedBytes.a(byteBuffer.get(11))) << 24);
                    j10 = j11 ^ (((long) UnsignedBytes.a(byteBuffer.get(10))) << 16);
                    j9 = j10 ^ (((long) UnsignedBytes.a(byteBuffer.get(9))) << 8);
                    j2 = j9 ^ ((long) UnsignedBytes.a(byteBuffer.get(8)));
                    j = byteBuffer.getLong() ^ 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 14:
                    j14 = 0;
                    j13 = j14 ^ (((long) UnsignedBytes.a(byteBuffer.get(13))) << 40);
                    j12 = j13 ^ (((long) UnsignedBytes.a(byteBuffer.get(12))) << 32);
                    j11 = j12 ^ (((long) UnsignedBytes.a(byteBuffer.get(11))) << 24);
                    j10 = j11 ^ (((long) UnsignedBytes.a(byteBuffer.get(10))) << 16);
                    j9 = j10 ^ (((long) UnsignedBytes.a(byteBuffer.get(9))) << 8);
                    j2 = j9 ^ ((long) UnsignedBytes.a(byteBuffer.get(8)));
                    j = byteBuffer.getLong() ^ 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                case 15:
                    j14 = (((long) UnsignedBytes.a(byteBuffer.get(14))) << 48) ^ 0;
                    j13 = j14 ^ (((long) UnsignedBytes.a(byteBuffer.get(13))) << 40);
                    j12 = j13 ^ (((long) UnsignedBytes.a(byteBuffer.get(12))) << 32);
                    j11 = j12 ^ (((long) UnsignedBytes.a(byteBuffer.get(11))) << 24);
                    j10 = j11 ^ (((long) UnsignedBytes.a(byteBuffer.get(10))) << 16);
                    j9 = j10 ^ (((long) UnsignedBytes.a(byteBuffer.get(9))) << 8);
                    j2 = j9 ^ ((long) UnsignedBytes.a(byteBuffer.get(8)));
                    j = byteBuffer.getLong() ^ 0;
                    this.a = d(j) ^ this.a;
                    this.b = e(j2) ^ this.b;
                    return;
                default:
                    throw new AssertionError("Should never get here.");
            }
        }

        @Override // com.google.common.hash.d
        public HashCode b() {
            long j = this.a;
            int i = this.c;
            this.a = j ^ ((long) i);
            this.b ^= (long) i;
            long j2 = this.a;
            long j3 = this.b;
            this.a = j2 + j3;
            long j4 = this.a;
            this.b = j3 + j4;
            this.a = c(j4);
            this.b = c(this.b);
            long j5 = this.a;
            long j6 = this.b;
            this.a = j5 + j6;
            this.b = j6 + this.a;
            return HashCode.fromBytesNoCopy(ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.a).putLong(this.b).array());
        }

        private static long d(long j) {
            return Long.rotateLeft(j * -8663945395140668459L, 31) * 5545529020109919103L;
        }

        private static long e(long j) {
            return Long.rotateLeft(j * 5545529020109919103L, 33) * -8663945395140668459L;
        }
    }
}
