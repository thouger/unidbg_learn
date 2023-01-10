package com.google.common.hash;

import com.google.common.base.m;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* compiled from: AbstractHasher */
/* access modifiers changed from: package-private */
public abstract class c implements f {
    c() {
    }

    @Override // com.google.common.hash.f
    /* renamed from: a */
    public f b(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            a(charSequence.charAt(i));
        }
        return this;
    }

    @Override // com.google.common.hash.f
    /* renamed from: a */
    public f b(CharSequence charSequence, Charset charset) {
        return c(charSequence.toString().getBytes(charset));
    }

    /* renamed from: b */
    public f c(byte[] bArr) {
        return b(bArr, 0, bArr.length);
    }

    @Override // com.google.common.hash.f
    public f b(byte[] bArr, int i, int i2) {
        m.a(i, i + i2, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            b(bArr[i + i3]);
        }
        return this;
    }

    @Override // com.google.common.hash.f
    public f b(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            b(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            byteBuffer.position(byteBuffer.limit());
        } else {
            for (int remaining = byteBuffer.remaining(); remaining > 0; remaining--) {
                b(byteBuffer.get());
            }
        }
        return this;
    }

    @Override // com.google.common.hash.f
    /* renamed from: a */
    public f b(int i) {
        b((byte) i);
        b((byte) (i >>> 8));
        b((byte) (i >>> 16));
        b((byte) (i >>> 24));
        return this;
    }

    @Override // com.google.common.hash.f
    /* renamed from: a */
    public f b(long j) {
        for (int i = 0; i < 64; i += 8) {
            b((byte) ((int) (j >>> i)));
        }
        return this;
    }

    public f a(char c) {
        b((byte) c);
        b((byte) (c >>> '\b'));
        return this;
    }

    @Override // com.google.common.hash.f
    public <T> f a(T t, Funnel<? super T> funnel) {
        funnel.funnel(t, this);
        return this;
    }
}
