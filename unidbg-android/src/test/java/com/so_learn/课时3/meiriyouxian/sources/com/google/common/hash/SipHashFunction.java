package com.google.common.hash;

import com.google.common.base.m;
import com.umeng.message.proguard.l;
import java.io.Serializable;
import java.nio.ByteBuffer;

final class SipHashFunction extends b implements Serializable {
    static final e SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;
    private final int c;
    private final int d;
    private final long k0;
    private final long k1;

    public int bits() {
        return 64;
    }

    SipHashFunction(int i, int i2, long j, long j2) {
        boolean z = true;
        m.a(i > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", i);
        m.a(i2 <= 0 ? false : z, "The number of SipRound iterations (d=%s) during Finalization must be positive.", i2);
        this.c = i;
        this.d = i2;
        this.k0 = j;
        this.k1 = j2;
    }

    @Override // com.google.common.hash.e
    public f newHasher() {
        return new a(this.c, this.d, this.k0, this.k1);
    }

    @Override // java.lang.Object
    public String toString() {
        return "Hashing.sipHash" + this.c + "" + this.d + l.s + this.k0 + ", " + this.k1 + l.t;
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (!(obj instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction sipHashFunction = (SipHashFunction) obj;
        if (this.c == sipHashFunction.c && this.d == sipHashFunction.d && this.k0 == sipHashFunction.k0 && this.k1 == sipHashFunction.k1) {
            return true;
        }
        return false;
    }

    @Override // java.lang.Object
    public int hashCode() {
        return (int) ((((long) ((getClass().hashCode() ^ this.c) ^ this.d)) ^ this.k0) ^ this.k1);
    }

    private static final class a extends d {
        private final int a;
        private final int b;
        private long c = 8317987319222330741L;
        private long d = 7237128888997146477L;
        private long e = 7816392313619706465L;
        private long f = 8387220255154660723L;
        private long g = 0;
        private long h = 0;

        a(int i, int i2, long j, long j2) {
            super(8);
            this.a = i;
            this.b = i2;
            this.c ^= j;
            this.d ^= j2;
            this.e ^= j;
            this.f ^= j2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.d
        public void a(ByteBuffer byteBuffer) {
            this.g += 8;
            c(byteBuffer.getLong());
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.d
        public void c(ByteBuffer byteBuffer) {
            this.g += (long) byteBuffer.remaining();
            int i = 0;
            while (byteBuffer.hasRemaining()) {
                this.h ^= (((long) byteBuffer.get()) & 255) << i;
                i += 8;
            }
        }

        @Override // com.google.common.hash.d
        public HashCode b() {
            this.h ^= this.g << 56;
            c(this.h);
            this.e ^= 255;
            c(this.b);
            return HashCode.fromLong(((this.c ^ this.d) ^ this.e) ^ this.f);
        }

        private void c(long j) {
            this.f ^= j;
            c(this.a);
            this.c = j ^ this.c;
        }

        private void c(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                long j = this.c;
                long j2 = this.d;
                this.c = j + j2;
                this.e += this.f;
                this.d = Long.rotateLeft(j2, 13);
                this.f = Long.rotateLeft(this.f, 16);
                long j3 = this.d;
                long j4 = this.c;
                this.d = j3 ^ j4;
                this.f ^= this.e;
                this.c = Long.rotateLeft(j4, 32);
                long j5 = this.e;
                long j6 = this.d;
                this.e = j5 + j6;
                this.c += this.f;
                this.d = Long.rotateLeft(j6, 17);
                this.f = Long.rotateLeft(this.f, 21);
                long j7 = this.d;
                long j8 = this.e;
                this.d = j7 ^ j8;
                this.f ^= this.c;
                this.e = Long.rotateLeft(j8, 32);
            }
        }
    }
}
