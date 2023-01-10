package com.google.common.hash;

import com.google.common.base.m;
import java.io.Serializable;
import java.util.zip.Checksum;

/* access modifiers changed from: package-private */
public final class ChecksumHashFunction extends b implements Serializable {
    private static final long serialVersionUID = 0;
    private final int bits;
    private final g<? extends Checksum> checksumSupplier;
    private final String toString;

    ChecksumHashFunction(g<? extends Checksum> gVar, int i, String str) {
        this.checksumSupplier = (g) m.a(gVar);
        m.a(i == 32 || i == 64, "bits (%s) must be either 32 or 64", i);
        this.bits = i;
        this.toString = (String) m.a(str);
    }

    public int bits() {
        return this.bits;
    }

    @Override // com.google.common.hash.e
    public f newHasher() {
        return new a(this.checksumSupplier.get());
    }

    @Override // java.lang.Object
    public String toString() {
        return this.toString;
    }

    private final class a extends a {
        private final Checksum b;

        private a(Checksum checksum) {
            this.b = (Checksum) m.a(checksum);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.a
        public void a(byte b) {
            this.b.update(b);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.hash.a
        public void a(byte[] bArr, int i, int i2) {
            this.b.update(bArr, i, i2);
        }

        @Override // com.google.common.hash.f
        public HashCode a() {
            long value = this.b.getValue();
            if (ChecksumHashFunction.this.bits == 32) {
                return HashCode.fromInt((int) value);
            }
            return HashCode.fromLong(value);
        }
    }
}
