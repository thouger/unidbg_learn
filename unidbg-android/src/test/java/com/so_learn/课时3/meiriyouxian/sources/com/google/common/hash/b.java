package com.google.common.hash;

import com.google.common.base.m;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* compiled from: AbstractHashFunction */
/* access modifiers changed from: package-private */
public abstract class b implements e {
    b() {
    }

    @Override // com.google.common.hash.e
    public <T> HashCode hashObject(T t, Funnel<? super T> funnel) {
        return newHasher().a(t, funnel).a();
    }

    public HashCode hashUnencodedChars(CharSequence charSequence) {
        return newHasher(charSequence.length() * 2).a(charSequence).a();
    }

    public HashCode hashString(CharSequence charSequence, Charset charset) {
        return newHasher().a(charSequence, charset).a();
    }

    public HashCode hashInt(int i) {
        return newHasher(4).a(i).a();
    }

    public HashCode hashLong(long j) {
        return newHasher(8).a(j).a();
    }

    public HashCode hashBytes(byte[] bArr) {
        return hashBytes(bArr, 0, bArr.length);
    }

    public HashCode hashBytes(byte[] bArr, int i, int i2) {
        m.a(i, i + i2, bArr.length);
        return newHasher(i2).b(bArr, i, i2).a();
    }

    public HashCode hashBytes(ByteBuffer byteBuffer) {
        return newHasher(byteBuffer.remaining()).b(byteBuffer).a();
    }

    public f newHasher(int i) {
        m.a(i >= 0, "expectedInputSize must be >= 0 but was %s", i);
        return newHasher();
    }
}
