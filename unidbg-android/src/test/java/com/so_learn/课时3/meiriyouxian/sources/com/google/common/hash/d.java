package com.google.common.hash;

import com.google.common.base.m;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: AbstractStreamingHasher */
/* access modifiers changed from: package-private */
public abstract class d extends c {
    private final ByteBuffer a;
    private final int b;
    private final int c;

    /* access modifiers changed from: protected */
    public abstract void a(ByteBuffer byteBuffer);

    /* access modifiers changed from: protected */
    public abstract HashCode b();

    protected d(int i) {
        this(i, i);
    }

    protected d(int i, int i2) {
        m.a(i2 % i == 0);
        this.a = ByteBuffer.allocate(i2 + 7).order(ByteOrder.LITTLE_ENDIAN);
        this.b = i2;
        this.c = i;
    }

    /* access modifiers changed from: protected */
    public void c(ByteBuffer byteBuffer) {
        byteBuffer.position(byteBuffer.limit());
        byteBuffer.limit(this.c + 7);
        while (true) {
            int position = byteBuffer.position();
            int i = this.c;
            if (position < i) {
                byteBuffer.putLong(0);
            } else {
                byteBuffer.limit(i);
                byteBuffer.flip();
                a(byteBuffer);
                return;
            }
        }
    }

    @Override // com.google.common.hash.c, com.google.common.hash.f
    public final f b(byte[] bArr, int i, int i2) {
        return d(ByteBuffer.wrap(bArr, i, i2).order(ByteOrder.LITTLE_ENDIAN));
    }

    /* JADX INFO: finally extract failed */
    @Override // com.google.common.hash.c, com.google.common.hash.f
    public final f b(ByteBuffer byteBuffer) {
        ByteOrder order = byteBuffer.order();
        try {
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            f d = d(byteBuffer);
            byteBuffer.order(order);
            return d;
        } catch (Throwable th) {
            byteBuffer.order(order);
            throw th;
        }
    }

    private f d(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() <= this.a.remaining()) {
            this.a.put(byteBuffer);
            c();
            return this;
        }
        int position = this.b - this.a.position();
        for (int i = 0; i < position; i++) {
            this.a.put(byteBuffer.get());
        }
        d();
        while (byteBuffer.remaining() >= this.c) {
            a(byteBuffer);
        }
        this.a.put(byteBuffer);
        return this;
    }

    @Override // com.google.common.hash.f
    public final f b(byte b) {
        this.a.put(b);
        c();
        return this;
    }

    @Override // com.google.common.hash.c
    public final f a(char c) {
        this.a.putChar(c);
        c();
        return this;
    }

    @Override // com.google.common.hash.c, com.google.common.hash.f
    /* renamed from: a */
    public final f b(int i) {
        this.a.putInt(i);
        c();
        return this;
    }

    @Override // com.google.common.hash.c, com.google.common.hash.f
    /* renamed from: a */
    public final f b(long j) {
        this.a.putLong(j);
        c();
        return this;
    }

    @Override // com.google.common.hash.f
    public final HashCode a() {
        d();
        this.a.flip();
        if (this.a.remaining() > 0) {
            c(this.a);
            ByteBuffer byteBuffer = this.a;
            byteBuffer.position(byteBuffer.limit());
        }
        return b();
    }

    private void c() {
        if (this.a.remaining() < 8) {
            d();
        }
    }

    private void d() {
        this.a.flip();
        while (this.a.remaining() >= this.c) {
            a(this.a);
        }
        this.a.compact();
    }
}
