package com.google.common.hash;

import com.google.common.base.c;
import com.google.common.base.m;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import com.umeng.message.proguard.l;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* access modifiers changed from: package-private */
public final class Murmur3_32HashFunction extends b implements Serializable {
    private static final int C1 = -862048943;
    private static final int C2 = 461845907;
    private static final int CHUNK_SIZE = 4;
    static final e GOOD_FAST_HASH_32 = new Murmur3_32HashFunction(Hashing.a);
    static final e MURMUR3_32 = new Murmur3_32HashFunction(0);
    private static final long serialVersionUID = 0;
    private final int seed;

    /* access modifiers changed from: private */
    public static long charToThreeUtf8Bytes(char c) {
        return (long) ((((c & '?') | 128) << 16) | (((c >>> '\f') | 480) & 255) | ((((c >>> 6) & 63) | 128) << 8));
    }

    /* access modifiers changed from: private */
    public static long charToTwoUtf8Bytes(char c) {
        return (long) ((((c & '?') | 128) << 8) | (((c >>> 6) | 960) & 255));
    }

    /* access modifiers changed from: private */
    public static long codePointToFourUtf8Bytes(int i) {
        return ((((long) (i >>> 18)) | 240) & 255) | ((((long) ((i >>> 12) & 63)) | 128) << 8) | ((((long) ((i >>> 6) & 63)) | 128) << 16) | ((((long) (i & 63)) | 128) << 24);
    }

    public int bits() {
        return 32;
    }

    Murmur3_32HashFunction(int i) {
        this.seed = i;
    }

    @Override // com.google.common.hash.e
    public f newHasher() {
        return new a(this.seed);
    }

    @Override // java.lang.Object
    public String toString() {
        return "Hashing.murmur3_32(" + this.seed + l.t;
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (!(obj instanceof Murmur3_32HashFunction) || this.seed != ((Murmur3_32HashFunction) obj).seed) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Object
    public int hashCode() {
        return getClass().hashCode() ^ this.seed;
    }

    @Override // com.google.common.hash.b
    public HashCode hashInt(int i) {
        return fmix(mixH1(this.seed, mixK1(i)), 4);
    }

    @Override // com.google.common.hash.b
    public HashCode hashLong(long j) {
        return fmix(mixH1(mixH1(this.seed, mixK1((int) j)), mixK1((int) (j >>> 32))), 8);
    }

    @Override // com.google.common.hash.b
    public HashCode hashUnencodedChars(CharSequence charSequence) {
        int i = this.seed;
        for (int i2 = 1; i2 < charSequence.length(); i2 += 2) {
            i = mixH1(i, mixK1(charSequence.charAt(i2 - 1) | (charSequence.charAt(i2) << 16)));
        }
        if ((charSequence.length() & 1) == 1) {
            i ^= mixK1(charSequence.charAt(charSequence.length() - 1));
        }
        return fmix(i, charSequence.length() * 2);
    }

    @Override // com.google.common.hash.b
    public HashCode hashString(CharSequence charSequence, Charset charset) {
        if (!c.c.equals(charset)) {
            return hashBytes(charSequence.toString().getBytes(charset));
        }
        int length = charSequence.length();
        int i = 0;
        int i2 = this.seed;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = i3 + 4;
            if (i5 > length) {
                break;
            }
            char charAt = charSequence.charAt(i3);
            char charAt2 = charSequence.charAt(i3 + 1);
            char charAt3 = charSequence.charAt(i3 + 2);
            char charAt4 = charSequence.charAt(i3 + 3);
            if (charAt >= '\u0080' || charAt2 >= '\u0080' || charAt3 >= '\u0080' || charAt4 >= '\u0080') {
                break;
            }
            i2 = mixH1(i2, mixK1((charAt2 << '\b') | charAt | (charAt3 << 16) | (charAt4 << 24)));
            i4 += 4;
            i3 = i5;
        }
        long j = 0;
        while (i3 < length) {
            char charAt5 = charSequence.charAt(i3);
            if (charAt5 < '\u0080') {
                j |= ((long) charAt5) << i;
                i += 8;
                i4++;
            } else if (charAt5 < '\u0800') {
                j |= charToTwoUtf8Bytes(charAt5) << i;
                i += 16;
                i4 += 2;
            } else if (charAt5 < '\ud800' || charAt5 > '\udfff') {
                j |= charToThreeUtf8Bytes(charAt5) << i;
                i += 24;
                i4 += 3;
            } else {
                int codePointAt = Character.codePointAt(charSequence, i3);
                if (codePointAt == charAt5) {
                    return hashBytes(charSequence.toString().getBytes(charset));
                }
                i3++;
                j |= codePointToFourUtf8Bytes(codePointAt) << i;
                i4 += 4;
            }
            if (i >= 32) {
                i2 = mixH1(i2, mixK1((int) j));
                j >>>= 32;
                i -= 32;
            }
            i3++;
        }
        return fmix(mixK1((int) j) ^ i2, i4);
    }

    @Override // com.google.common.hash.b
    public HashCode hashBytes(byte[] bArr, int i, int i2) {
        m.a(i, i + i2, bArr.length);
        int i3 = 0;
        int i4 = this.seed;
        int i5 = 0;
        while (true) {
            int i6 = i5 + 4;
            if (i6 > i2) {
                break;
            }
            i4 = mixH1(i4, mixK1(getIntLittleEndian(bArr, i5 + i)));
            i5 = i6;
        }
        int i7 = 0;
        while (i5 < i2) {
            i3 ^= UnsignedBytes.a(bArr[i + i5]) << i7;
            i5++;
            i7 += 8;
        }
        return fmix(mixK1(i3) ^ i4, i2);
    }

    /* access modifiers changed from: private */
    public static int getIntLittleEndian(byte[] bArr, int i) {
        return Ints.a(bArr[i + 3], bArr[i + 2], bArr[i + 1], bArr[i]);
    }

    /* access modifiers changed from: private */
    public static int mixK1(int i) {
        return Integer.rotateLeft(i * C1, 15) * C2;
    }

    /* access modifiers changed from: private */
    public static int mixH1(int i, int i2) {
        return (Integer.rotateLeft(i ^ i2, 13) * 5) - 430675100;
    }

    /* access modifiers changed from: private */
    public static HashCode fmix(int i, int i2) {
        int i3 = i ^ i2;
        int i4 = (i3 ^ (i3 >>> 16)) * -2048144789;
        int i5 = (i4 ^ (i4 >>> 13)) * -1028477387;
        return HashCode.fromInt(i5 ^ (i5 >>> 16));
    }

    private static final class a extends c {
        private int a;
        private long b;
        private int c;
        private int d = 0;
        private boolean e = false;

        a(int i) {
            this.a = i;
        }

        private void a(int i, long j) {
            long j2 = this.b;
            int i2 = this.c;
            this.b = ((j & 4294967295L) << i2) | j2;
            this.c = i2 + (i * 8);
            this.d += i;
            if (this.c >= 32) {
                this.a = Murmur3_32HashFunction.mixH1(this.a, Murmur3_32HashFunction.mixK1((int) this.b));
                this.b >>>= 32;
                this.c -= 32;
            }
        }

        @Override // com.google.common.hash.f
        public f b(byte b) {
            a(1, (long) (b & 255));
            return this;
        }

        @Override // com.google.common.hash.c, com.google.common.hash.f
        public f b(byte[] bArr, int i, int i2) {
            m.a(i, i + i2, bArr.length);
            int i3 = 0;
            while (true) {
                int i4 = i3 + 4;
                if (i4 > i2) {
                    break;
                }
                a(4, (long) Murmur3_32HashFunction.getIntLittleEndian(bArr, i3 + i));
                i3 = i4;
            }
            while (i3 < i2) {
                b(bArr[i + i3]);
                i3++;
            }
            return this;
        }

        @Override // com.google.common.hash.c, com.google.common.hash.f
        public f b(ByteBuffer byteBuffer) {
            ByteOrder order = byteBuffer.order();
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            while (byteBuffer.remaining() >= 4) {
                b(byteBuffer.getInt());
            }
            while (byteBuffer.hasRemaining()) {
                b(byteBuffer.get());
            }
            byteBuffer.order(order);
            return this;
        }

        @Override // com.google.common.hash.c, com.google.common.hash.f
        /* renamed from: a */
        public f b(int i) {
            a(4, (long) i);
            return this;
        }

        @Override // com.google.common.hash.c, com.google.common.hash.f
        /* renamed from: a */
        public f b(long j) {
            a(4, (long) ((int) j));
            a(4, j >>> 32);
            return this;
        }

        @Override // com.google.common.hash.c
        public f a(char c) {
            a(2, (long) c);
            return this;
        }

        @Override // com.google.common.hash.c, com.google.common.hash.f
        /* renamed from: a */
        public f b(CharSequence charSequence, Charset charset) {
            if (!c.c.equals(charset)) {
                return super.b(charSequence, charset);
            }
            int length = charSequence.length();
            int i = 0;
            while (true) {
                int i2 = i + 4;
                if (i2 > length) {
                    break;
                }
                char charAt = charSequence.charAt(i);
                char charAt2 = charSequence.charAt(i + 1);
                char charAt3 = charSequence.charAt(i + 2);
                char charAt4 = charSequence.charAt(i + 3);
                if (charAt >= '\u0080' || charAt2 >= '\u0080' || charAt3 >= '\u0080' || charAt4 >= '\u0080') {
                    break;
                }
                a(4, (long) ((charAt2 << '\b') | charAt | (charAt3 << 16) | (charAt4 << 24)));
                i = i2;
            }
            while (i < length) {
                char charAt5 = charSequence.charAt(i);
                if (charAt5 < '\u0080') {
                    a(1, (long) charAt5);
                } else if (charAt5 < '\u0800') {
                    a(2, Murmur3_32HashFunction.charToTwoUtf8Bytes(charAt5));
                } else if (charAt5 < '\ud800' || charAt5 > '\udfff') {
                    a(3, Murmur3_32HashFunction.charToThreeUtf8Bytes(charAt5));
                } else {
                    int codePointAt = Character.codePointAt(charSequence, i);
                    if (codePointAt == charAt5) {
                        c(charSequence.subSequence(i, length).toString().getBytes(charset));
                        return this;
                    }
                    i++;
                    a(4, Murmur3_32HashFunction.codePointToFourUtf8Bytes(codePointAt));
                }
                i++;
            }
            return this;
        }

        @Override // com.google.common.hash.f
        public HashCode a() {
            m.b(!this.e);
            this.e = true;
            this.a ^= Murmur3_32HashFunction.mixK1((int) this.b);
            return Murmur3_32HashFunction.fmix(this.a, this.d);
        }
    }
}
