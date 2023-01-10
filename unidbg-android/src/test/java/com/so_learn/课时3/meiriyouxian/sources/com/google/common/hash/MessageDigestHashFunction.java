package com.google.common.hash;

import com.google.common.base.m;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

final class MessageDigestHashFunction extends b implements Serializable {
    private final int bytes;
    private final MessageDigest prototype;
    private final boolean supportsClone;
    private final String toString;

    MessageDigestHashFunction(String str, String str2) {
        this.prototype = getMessageDigest(str);
        this.bytes = this.prototype.getDigestLength();
        this.toString = (String) m.a(str2);
        this.supportsClone = supportsClone(this.prototype);
    }

    MessageDigestHashFunction(String str, int i, String str2) {
        this.toString = (String) m.a(str2);
        this.prototype = getMessageDigest(str);
        int digestLength = this.prototype.getDigestLength();
        m.a(i >= 4 && i <= digestLength, "bytes (%s) must be >= 4 and < %s", i, digestLength);
        this.bytes = i;
        this.supportsClone = supportsClone(this.prototype);
    }

    private static boolean supportsClone(MessageDigest messageDigest) {
        try {
            messageDigest.clone();
            return true;
        } catch (CloneNotSupportedException unused) {
            return false;
        }
    }

    public int bits() {
        return this.bytes * 8;
    }

    @Override // java.lang.Object
    public String toString() {
        return this.toString;
    }

    private static MessageDigest getMessageDigest(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    @Override // com.google.common.hash.e
    public f newHasher() {
        if (this.supportsClone) {
            try {
                return new a((MessageDigest) this.prototype.clone(), this.bytes);
            } catch (CloneNotSupportedException unused) {
            }
        }
        return new a(getMessageDigest(this.prototype.getAlgorithm()), this.bytes);
    }

    private static final class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final String algorithmName;
        private final int bytes;
        private final String toString;

        private SerializedForm(String str, int i, String str2) {
            this.algorithmName = str;
            this.bytes = i;
            this.toString = str2;
        }

        private Object readResolve() {
            return new MessageDigestHashFunction(this.algorithmName, this.bytes, this.toString);
        }
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this.prototype.getAlgorithm(), this.bytes, this.toString);
    }

    private static final class a extends a {
        private final MessageDigest a;
        private final int b;
        private boolean c;

        private a(MessageDigest messageDigest, int i) {
            this.a = messageDigest;
            this.b = i;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.a
        public void a(byte b) {
            b();
            this.a.update(b);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.a
        public void a(byte[] bArr, int i, int i2) {
            b();
            this.a.update(bArr, i, i2);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.a
        public void a(ByteBuffer byteBuffer) {
            b();
            this.a.update(byteBuffer);
        }

        private void b() {
            m.b(!this.c, "Cannot re-use a Hasher after calling hash() on it");
        }

        @Override // com.google.common.hash.f
        public HashCode a() {
            b();
            this.c = true;
            if (this.b == this.a.getDigestLength()) {
                return HashCode.fromBytesNoCopy(this.a.digest());
            }
            return HashCode.fromBytesNoCopy(Arrays.copyOf(this.a.digest(), this.b));
        }
    }
}
