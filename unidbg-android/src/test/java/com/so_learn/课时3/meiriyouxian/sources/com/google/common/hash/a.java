package com.google.common.hash;

import com.google.common.base.m;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: AbstractByteHasher */
/* access modifiers changed from: package-private */
public abstract class a extends c {
    private final ByteBuffer a = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

    /* access modifiers changed from: protected */
    public abstract void a(byte b);

    a() {
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr) {
        a(bArr, 0, bArr.length);
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            a(bArr[i3]);
        }
    }

    /* access modifiers changed from: protected */
    public void a(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            a(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            byteBuffer.position(byteBuffer.limit());
            return;
        }
        for (int remaining = byteBuffer.remaining(); remaining > 0; remaining--) {
            a(byteBuffer.get());
        }
    }

    private f c(int i) {
        try {
            a(this.a.array(), 0, i);
            return this;
        } finally {
            this.a.clear();
        }
    }

    @Override // com.google.common.hash.f
    public f b(byte b) {
        a(b);
        return this;
    }

    @Override // com.google.common.hash.c
    /* renamed from: b */
    public f c(byte[] bArr) {
        m.a(bArr);
        a(bArr);
        return this;
    }

    @Override // com.google.common.hash.c, com.google.common.hash.f
    public f b(byte[] bArr, int i, int i2) {
        m.a(i, i + i2, bArr.length);
        a(bArr, i, i2);
        return this;
    }

    @Override // com.google.common.hash.c, com.google.common.hash.f
    public f b(ByteBuffer byteBuffer) {
        a(byteBuffer);
        return this;
    }

    @Override // com.google.common.hash.c, com.google.common.hash.f
    /* renamed from: a */
    public f b(int i) {
        this.a.putInt(i);
        return c(4);
    }

    @Override // com.google.common.hash.c, com.google.common.hash.f
    /* renamed from: a */
    public f b(long j) {
        this.a.putLong(j);
        return c(8);
    }

    @Override // com.google.common.hash.c
    public f a(char c) {
        this.a.putChar(c);
        return c(2);
    }
}
